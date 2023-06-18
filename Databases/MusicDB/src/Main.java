import model.DataSource;
import model.MusicTable;
import model.SQLStatement;
import utilities.ThreadColor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    /* ---------------- GLOBALS --------------*/


    public static void main(String[] args) throws SQLException {

        //Initialize variables
        DataSource data_src;

        Statement statement;

        MusicTable songs_table;
        MusicTable albums_table;
        MusicTable artists_table;

        String query_songs, query_albums, query_artists;


        /* -------------------DATABASE-------------------------*/
        //Create DataSource
        data_src = new DataSource();

        //Open Connection for data_source
        if (!data_src.open()){
            System.out.println(ThreadColor.RED+"Can't open data_src"+
                    ThreadColor.RESET);
            return;
        }

        //Create Statement for data_source
        statement = data_src.getStatement();
        if (statement == null){
            System.out.println(ThreadColor.RED+"Can't create statement for " +
                    "data_src"+ThreadColor.RESET);
            return;
        }

        // ------------------Create Tables
        //1. songs
        songs_table = new MusicTable(DataSource.TABLE_SONGS);
        songs_table.create_schema(DataSource.songs_fields);

        //2. albums
        albums_table = new MusicTable(DataSource.TABLE_ALBUMS);
        albums_table.create_schema(DataSource.albums_fields);

        //3. artists
        artists_table = new MusicTable(DataSource.TABLE_ARTISTS);
        artists_table.create_schema(DataSource.artists_fields);

        //------------------Create sql statements
        query_songs = new SQLStatement(songs_table).queryStmt("*");
        query_albums =new SQLStatement(albums_table).queryStmt("*");
        query_artists = new SQLStatement(artists_table).queryStmt("*");

        /* -------------------------RESULTS------------------------*/
        System.out.println(songs_table.getTable_name());
        System.out.println(songs_table.getSchema());
        System.out.println(query_songs);

        if (data_src.executeSQL(statement, query_songs)){
            ResultSet result = statement.getResultSet();
            while (result.next()){
                System.out.println(result.getInt(DataSource.song_columns.get(0)) +
                        " " + result.getString(DataSource.song_columns.get(1)) + " " +
                        result.getInt(DataSource.song_columns.get(2)));
            }
            result.close();
        }











        data_src.close(DataSource.STATEMENT_NAME);
        data_src.close(DataSource.CONNECTION_NAME);
    }
}