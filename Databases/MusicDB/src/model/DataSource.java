/*=================================Import Packages===========================*/
package model;

/*==================================Import Resources=========================*/
import utilities.ThreadColor;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

/*=============================== DataSource ==================================
 * DataSource Class Definition
 *
 * FIELDS:
 * ------
 * globals: (Database connection): -  DB_NAME, DB_DRIVER, DB_PATH, DB_URL
 *                                   CONNECTION_NAME, STATEMENT_NAME
 *
 *         (albums) -  TABLE_ALBUMS,COLUMN_ALBUM_ID,COLUMN_ALBUM_NAME,
 *                     COLUMN_ALBUM_ARTIST,albums_schema, albums_columns
 *
 *         (artists) - TABLE_ARTISTS,COLUMN_ARTIST_ID, COLUMN_ARTIST_NAME,
 *                           artists_schema, artists_columns

 *          (songs) -   TABLE_SONGS,COLUMN_SONG_ID,COLUMN_SONG_TRACK,
 *                      COLUMN_SONG_TITLE,COLUMN_SONG_ALBUM,songs_schema,
 *                      song_columns
 *
 *          (artist_list) - TABLE_ARTIST_SONG_VIEW,CREATE_ARTIST_FOR_SONG_VIEW,
 *                          QUERY_VIEW_SONG_INFO_PREP,artist_list_schema
 *
 *          (artists - Insert) -    INSERT_ARTIST,
 *
 * privates: (Database connection): - conn
 *
 *           (Prepared Statements): - querySongInfoView
 *
 * METHODS:
 * --------
 * global:  public boolean open()
 *          public void close(String resource)
 *          public List<Artist> queryArtists(String select)
 *          public List<Album> queryAlbum(String select)
 *          public List<Song> querySong(String select)
 *          public void querySongMetaData(String select)
 *          public int getCount(String select)
 *          public  List<SongArtist> querySongInfoView(String title)
 *          public boolean createViewForSongArtists()
 */

public class DataSource {
    /* ---------------------------- GLOBALS VARIABLES --------------------*/
    /* --------------------------DATABASE-------------------------*/
    public static final String DB_NAME = "/resources/music.db";
    public static final String DB_DRIVER = "jdbc:sqlite";
    public static final String DB_PATH = System.getProperty("user.dir");
    public static final String DB_URL = DB_DRIVER + ":" + DB_PATH + DB_NAME;
    public static final String CONNECTION_NAME = "conn";
    public static final String STATEMENT_NAME = "statement";
    private Connection conn;

    /* --------------------TABLES & FIELDS-------------------------*/
    // 1. albums table
    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";

    // albums schema
    public static String albums_schema = "(" +
            COLUMN_ALBUM_ID + " INTEGER, " +
            COLUMN_ALBUM_NAME + " TEXT, " +
            COLUMN_ALBUM_ARTIST + " INTEGER)";
    public static List<String> albums_columns = new ArrayList<>();

    // 2. artists table
    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";

    //artists schema
    public static String artists_schema = "(" +
            COLUMN_ARTIST_ID + " INTEGER, " +
            COLUMN_ARTIST_NAME + " TEXT)";
    public static List<String> artists_columns = new ArrayList<>();

    // 3. songs table
    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_ID = "_id";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";

    // songs schema
    public static String songs_schema = "(" +
            COLUMN_SONG_ALBUM + " INTEGER, " +
            COLUMN_SONG_TITLE + " TEXT, " +
            COLUMN_SONG_TRACK + " INTEGER)";
    public static List<String> song_columns = new ArrayList<>();

    // TABLE VIEWS
    // 4. artist_list
    public static final String TABLE_ARTIST_SONG_VIEW = "artist_list";
    public static final String CREATE_ARTIST_FOR_SONG_VIEW = "CREATE " +
            "VIEW IF NOT EXISTS " + TABLE_ARTIST_SONG_VIEW + " AS SELECT " +
            TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " AS " + COLUMN_SONG_ALBUM +
            ", " + TABLE_SONGS + "." + COLUMN_SONG_TRACK + ", " + TABLE_SONGS + "." +
            COLUMN_SONG_TITLE + " FROM " + TABLE_SONGS +
            " INNER JOIN " + TABLE_ALBUMS + " ON " + TABLE_SONGS + "." + COLUMN_SONG_ALBUM +
            " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID +
            " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST +
            " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
            " ORDER BY " +
            TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", " +
            TABLE_SONGS + "." + COLUMN_SONG_TRACK;

    //Query artist_list
    public static final String QUERY_VIEW_SONG_INFO_PREP = "SELECT " + COLUMN_ARTIST_NAME +
            ", " + COLUMN_SONG_ALBUM + ", " + COLUMN_SONG_TRACK + " FROM " +
            TABLE_ARTIST_SONG_VIEW + " WHERE " + COLUMN_SONG_TITLE + " = ?";
    // artist_list schema
    public static final String artist_list_schema = "(" +
            COLUMN_ARTIST_NAME + " TEXT, " +
            COLUMN_SONG_ALBUM + " TEXT, " +
            COLUMN_SONG_TRACK + " INTEGER, " +
            COLUMN_SONG_TITLE + " TEXT)";

    /*--------------- Insert Records ----------------------*/
    // artists - Insert
    public static final String INSERT_ARTIST = "";

    /* -----------Create Prepared Statements---------------*/
    private PreparedStatement querySongInfoView;

    /* ----------------------METHODS----------------------------- */

    /*=====================DataSource===============
    * Constructor for DataSource Class
    * */
    public DataSource() {
        song_columns.add(COLUMN_SONG_ALBUM);
        song_columns.add(DataSource.COLUMN_SONG_TITLE);
        song_columns.add(DataSource.COLUMN_SONG_TRACK);

        albums_columns.add(COLUMN_ALBUM_NAME);
        albums_columns.add(COLUMN_ALBUM_ARTIST);

        artists_columns.add(COLUMN_ARTIST_NAME);
    }

    /*===================== Open ====================
    * Open Database Connection
    * Return[boolean]: true on success, false on failure
     */
    public boolean open(){
        try {
            conn = DriverManager.getConnection(DB_URL);
            System.out.println(ThreadColor.GREEN+"Connection Success!"+
                    ThreadColor.RESET);
            querySongInfoView = conn.prepareStatement(QUERY_VIEW_SONG_INFO_PREP);
            return (true);
        } catch (SQLException e){
            System.out.println(ThreadColor.RED+"Couldn't connect to database: " +
                    e.getMessage()+ThreadColor.RESET);
            return (false);
        }
    }

    /*===================== close ===================
     * Close Database Connection & Statement
     * @resource[String]: Connection name
     * Return: void
     */
    public void close(String resource){
        try { //close resources: PreparedStatement, Connection AND Statement
            if (querySongInfoView != null){
                querySongInfoView.close();
            }
            if (resource.equalsIgnoreCase(CONNECTION_NAME)){
                if (conn != null){
                    conn.close();
                }
            } else if (resource.equalsIgnoreCase(STATEMENT_NAME)) {
                if (conn != null){
                    conn.close();
                }
            }else {
                System.out.println(ThreadColor.RED+"Couldn't close " + resource+
                        ": Check your resource name ["+ThreadColor.BLUE+resource+
                        ThreadColor.RED+"]"+ThreadColor.RESET);
                return;
            }
            System.out.println(ThreadColor.GREEN+resource + " closed "+
                    ThreadColor.RESET);

        } catch (SQLException e){
            System.out.println(ThreadColor.RED+"Couldn't close " + resource+": "+
                    e.getMessage()+ThreadColor.RESET);
        }
    }

    /* --------------------QUERIES-------------------*/
    /*=================queryArtists==================
    * Queries Artists table
    * @select[String]: SQL select statement
    * Return[List]: List of artists (id, name)
    */
    public List<Artist> queryArtists(String select) {
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(select)) {
            int i = 0;

            List<Artist> artists = new ArrayList<>();
            while (results.next()) {
                i++;
                /*create artist*/
                Artist artist = new Artist();

                artist.setId(results.getInt(COLUMN_ARTIST_ID));
                artist.setName(results.getString(COLUMN_ARTIST_NAME));

                /*Fill artists*/
                artists.add(artist);
            }
            System.out.println("------------------------------------------");
            System.out.println(i + " artists retrieved from Artists database.");
            System.out.println("==========================================");

            return (artists);
        } catch (SQLException e) {
            System.out.println(ThreadColor.RED+"Couldn't create statement"+
                    ThreadColor.RESET);
            e.printStackTrace();
            return (null);
        }
    }

    /*=================queryAlbum==================
     * Queries Album table
     * @select[String]: SQL select statement
     * Return[List]: List of albums (id, name, artist_id)
     */
    public List<Album> queryAlbum(String select){
        try (Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(select)){
            int i = 0;

            /*create album*/
            List<Album> albums = new ArrayList<>();
            while (results.next()){
                i++;

                Album album = new Album();

                album.setId(results.getInt(COLUMN_ALBUM_ID));
                album.setName(results.getString(COLUMN_ALBUM_NAME));
                album.setArtist_id(results.getInt(COLUMN_ALBUM_ARTIST));
                album.setSchema(albums_schema);

                albums.add(album);
            }

            System.out.println("----------------------------------------");
            System.out.println(i + " albums retrieved from Album database.");
            System.out.println("=========================================");
            return (albums);

        } catch (SQLException e){
            System.out.println(ThreadColor.RED+"Couldn't create statement"+
                    ThreadColor.RESET);
            e.printStackTrace();
            return (null);
        }
    }

    /*=================querySong==================
     * Queries Song table
     * @select[String]: SQL select statement
     * Return[List]: List of songs (id, track, title, album_id)
     */
    public List<Song> querySong(String select){
        try (Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(select)){
            int i = 0;

            List<Song> songs = new ArrayList<>();
            while (results.next()){
                i++;

                Song song = new Song();

                song.setId(results.getInt(COLUMN_SONG_ID));
                song.setTract_id(results.getInt(COLUMN_SONG_TRACK));
                song.setTitle(results.getString(COLUMN_SONG_TITLE));
                song.setAlbum_id(results.getInt(COLUMN_ALBUM_ID));

                songs.add(song);
            }

            System.out.println("----------------------------------------");
            System.out.println(i + " songs retrieved from Songs database.");
            System.out.println("=========================================");
            return (songs);

        } catch (SQLException e){
            System.out.println(ThreadColor.RED+"Couldn't create statement"+
                    ThreadColor.RESET);
            e.printStackTrace();
            return (null);
        }
    }

    /*=================querySongMetaData=======
     * Queries Song table for its MetaData info
     * @select[String]: SQL select statement
     * Return: void
     */
    public void querySongMetaData(String select){
        int i;

        try (Statement statement = conn.createStatement();
        ResultSet results = statement.executeQuery(select)) {

            ResultSetMetaData meta = results.getMetaData();
            int numColumns = meta.getColumnCount();
            for (i = 1; i <= numColumns; i++){
                System.out.format("Column "+ThreadColor.YELLOW+"%d"+ThreadColor.RESET+" in the songs table is names"+ThreadColor.YELLOW+" %s\n"+ThreadColor.RESET,
                        i, meta.getColumnName(i));
            }
        } catch (SQLException e){
            System.out.println(ThreadColor.RED+"Couldn't create statement"+
                    ThreadColor.RESET);
            e.printStackTrace();
        }
    }

    /*=================getCount============================
     * Gets the total counts of records from a queries
     * @select[String]: SQL select statement
     * Return[int]: total counts based on a query statement
     */
    public int getCount(String select){

        try (Statement statement = conn.createStatement();
        ResultSet results = statement.executeQuery(select)){

            return (results.getInt(1));

        } catch (SQLException e){
            System.out.println(ThreadColor.RED+"Couldn't create statement"+
                    ThreadColor.RESET);
            e.printStackTrace();
        }
        return (-1);
    }

    /*------------------------QUERY VIEWS------------------*/
    /*=================querySongInfoView============================
     * Gets the total counts of records from a queries
     * @title[String]: Song's title to be queried
     * Return[SongArtist]: a list of SongArtists
     */
    public  List<SongArtist> querySongInfoView(String title){
        System.out.println("Querying \""+ title +"\"...");

        try{
            int i = 0;

            //pre-compile SQL statement
            querySongInfoView.setString(1, title);
            //execute the pre-compiled SQL statement
            ResultSet results = querySongInfoView.executeQuery();

            List<SongArtist> songArtists = new ArrayList<>();
            while(results.next()){
                i++;
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(results.getString(1));
                songArtist.setAlbumName(results.getString(2));
                songArtist.setTrack(results.getInt(3));

                songArtists.add(songArtist);
            }

            System.out.println("----------------------------------------");
            System.out.println(i + " artists retrieved from artist_list view table.");
            System.out.println("=========================================");
            return (songArtists);

        } catch (SQLException e){
            System.out.println(ThreadColor.RED+"Couldn't create statement"+
                    ThreadColor.RESET);
            e.printStackTrace();

            return (null);
        }
    }







    /* ------------------------CREATE----------------------------------- */
    /*-----------CREATE VIEWS--------------*/
    /*1. Create: SongArtistsView */
    public boolean createViewForSongArtists() {
        try (Statement statement = conn.createStatement()) {

           statement.execute(CREATE_ARTIST_FOR_SONG_VIEW);

            return (true);

        } catch (SQLException e) {
            System.out.println(ThreadColor.RED + "Couldn't create statement" +
                    ThreadColor.RESET);
            e.printStackTrace();
            return (false);
        }
    }
}