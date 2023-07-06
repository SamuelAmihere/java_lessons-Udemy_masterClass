package model;

import utilities.ThreadColor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
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

    /* -----------Create Prepared Statements---------------*/
    private PreparedStatement querySongInfoView;

    /* ----------------------METHODS----------------------------- */

    //Constructor
    public DataSource() {
        song_columns.add(COLUMN_SONG_ALBUM);
        song_columns.add(DataSource.COLUMN_SONG_TITLE);
        song_columns.add(DataSource.COLUMN_SONG_TRACK);

        albums_columns.add(COLUMN_ALBUM_NAME);
        albums_columns.add(COLUMN_ALBUM_ARTIST);

        artists_columns.add(COLUMN_ARTIST_NAME);
    }

    /*----------------------------- METHODS ---------------------------------*/
    // Open Database Connection
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

    // Close Database Connection & Statement
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

    /* --------QUERIES--------------*/
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

    /*-----------QUERY VIEWS----------*/
    /*1. Query: SongArtistsView*/
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