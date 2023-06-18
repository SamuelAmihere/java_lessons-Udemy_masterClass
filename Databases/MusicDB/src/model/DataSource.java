package model;

import utilities.ThreadColor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
    public static final String COLUMN_ALBUM_ID = "_d";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";

    // albums schema
    public static String albums_fields = "(" +
            DataSource.COLUMN_ALBUM_ID + " INTEGER, " +
            DataSource.COLUMN_ALBUM_NAME + " TEXT, " +
            DataSource.COLUMN_ALBUM_ARTIST + " INTEGER)";
    public static List<String> albums_columns = new ArrayList<>();

    // 2. artists table
    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_d";
    public static final String COLUMN_ARTIST_NAME = "name";

    //artists schema
    public static String artists_fields = "(" +
            DataSource.COLUMN_ARTIST_ID + " INTEGER, " +
            DataSource.COLUMN_ARTIST_NAME + " TEXT)";
    public static List<String> artists_columns = new ArrayList<>();

    // 3. songs table
    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";

    // songs schema
    public static String songs_fields = "(" +
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

    //Get Statement
    public Statement getStatement() {
        try {
            if (this.conn != null) { // use connection if already created
                this.statement = this.conn.createStatement();
                return this.statement;
            } else {
                System.out.println("Connection not established");
                System.out.println("Creating new connection...");
                if (this.open()){ // Create new connection if not created yet
                    this.statement = this.conn.createStatement();
                    return this.statement;
                } else
                {
                    System.out.println(ThreadColor.RED+"Connection attempt not "+
                            "possible"+ThreadColor.RESET);
                }
                return (null);
            }
        } catch (SQLException e){
            System.out.println(ThreadColor.RED+"Couldn't create statement"+
                    ThreadColor.RESET);
            return (null);
        }
    }

    public boolean executeSQL(Statement st, String sql_statement){
        boolean flag = false;
        try {
            st.execute(sql_statement);
            flag = true;
        }catch (SQLException e){
            System.out.println("Something went wrong: " +
                    e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    // Execute Table Query (SELECT)
    public boolean executeQuery(Statement st, String sql_statement){
        boolean flag = false;
        try {
            st.execute(sql_statement);
            flag = true;
        }catch (SQLException e){
            System.out.println("Something went wrong: " +
                    e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }
}