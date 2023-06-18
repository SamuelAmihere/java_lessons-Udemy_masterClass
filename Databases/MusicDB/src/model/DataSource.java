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
    private Statement statement;

    /* --------------------TABLES & FIELDS-------------------------*/
    // 1. albums table
    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";

    // albums schema
    public static String albums_schema = "(" +
            DataSource.COLUMN_ALBUM_ID + " INTEGER, " +
            DataSource.COLUMN_ALBUM_NAME + " TEXT, " +
            DataSource.COLUMN_ALBUM_ARTIST + " INTEGER)";
    public static List<String> albums_columns = new ArrayList<>();

    // 2. artists table
    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";

    //artists schema
    public static String artists_schema = "(" +
            DataSource.COLUMN_ARTIST_ID + " INTEGER, " +
            DataSource.COLUMN_ARTIST_NAME + " TEXT)";
    public static List<String> artists_columns = new ArrayList<>();

    // 3. songs table
    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_ID = "_id";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";

    // songs schema
    public static String songs_schema = "(" +
            DataSource.COLUMN_SONG_ALBUM + " INTEGER, " +
            DataSource.COLUMN_SONG_TITLE + " TEXT, " +
            DataSource.COLUMN_SONG_TRACK + " INTEGER)";
    public static List<String> song_columns = new ArrayList<>();

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
            return (true);
        } catch (SQLException e){
            System.out.println(ThreadColor.RED+"Couldn't connect to database: " +
                    e.getMessage()+ThreadColor.RESET);
            return (false);
        }
    }

    // Close Database Connection & Statement
    public void close(String resource){
        try { //close resources: Connection AND Statement
            if (resource.toLowerCase() == CONNECTION_NAME){
                if (conn != null){
                    conn.close();
                }
            } else if (resource.toLowerCase() == STATEMENT_NAME) {
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
                artist.setSchema(artists_schema);

                /*Fill artists*/
                artists.add(artist);
            }
            System.out.println("-----------------------------------");
            System.out.println(i + " artists retrieved from database.");
            System.out.println("===================================");

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

            System.out.println("-----------------------------------");
            System.out.println(i + " albums retrieved from database.");
            System.out.println("====================================");
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

            System.out.println("-----------------------------------");
            System.out.println(i + " songs retrieved from database.");
            System.out.println("====================================");
            return (songs);

        } catch (SQLException e){
            System.out.println(ThreadColor.RED+"Couldn't create statement"+
                    ThreadColor.RESET);
            e.printStackTrace();
            return (null);
        }
    }
}