import model.*;
import utilities.ThreadColor;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    /* ---------------- GLOBALS --------------*/
    /* FLAGS */
    public static boolean artists_flag = false;
    public static boolean albums_flag = false;
    public static boolean songs_flag = true;

    public static void main(String[] args) throws SQLException {

        //Initialize variables
        DataSource data_src;

        Statement statement;
        int i;

        MusicTable songs_table;
        MusicTable albums_table;
        MusicTable artists_table;

        String query_songs, query_albums, query_artists;

        List<Artist> artists_data;
        List<Album> albums_data;
        List<Song> songs_data;


        /* -------------------DATABASE-------------------------*/
        //Create DataSource
        data_src = new DataSource();

        //Open Connection for data_source
        if (!data_src.open()){
            System.out.println(ThreadColor.RED+"Can't open data_src"+
                    ThreadColor.RESET);
            return;
        }



        // ------------------Create Tables
        //1. songs
        songs_table = new MusicTable(DataSource.TABLE_SONGS);
        songs_table.create_schema(DataSource.songs_schema);

        //2. albums
        albums_table = new MusicTable(DataSource.TABLE_ALBUMS);
        albums_table.create_schema(DataSource.albums_schema);

        //3. artists
        artists_table = new MusicTable(DataSource.TABLE_ARTISTS);
        artists_table.create_schema(DataSource.artists_schema);

        //------------------Create sql statements
        query_songs = new SQLStatement(songs_table).queryStmt("*");
        query_albums =new SQLStatement(albums_table).queryStmt("*");
        query_artists = new SQLStatement(artists_table).queryStmt("*");

        /* -------------------------RESULTS------------------------*/
        /* QUERY DATA*/
        if (artists_flag){
            System.out.println(artists_table.getSchema());
            System.out.println(query_artists);
            System.out.println("======================\n");

            /* Artist query data*/
            artists_data =  data_src.queryArtists(query_artists);
            if (artists_data != null) {
                for (i = 0; i < artists_data.size(); i++) {
                    System.out.println("id=" + artists_data.get(i).getId() + " name=" +
                            artists_data.get(i).getName());
                }
            }
        }

        if (albums_flag){
            System.out.println();
            System.out.println(albums_table.getSchema());
            System.out.println(query_albums);
            System.out.println("======================\n");

            /* Artist query data*/
            albums_data =  data_src.queryAlbum(query_albums);
            if (albums_data != null) {
                for (i = 0; i < albums_data.size(); i++) {
                    System.out.println(
                            "id=" + albums_data.get(i).getId() +
                                    " name=" + albums_data.get(i).getName() +
                                    " artist_id=" + albums_data.get(i).getArtist_id());
                }
            }
        }

        if (songs_flag){
            System.out.println();
            System.out.println(songs_table.getSchema());
            System.out.println(query_songs);
            System.out.println("======================\n");

            /* Artist query data*/
            songs_data =  data_src.querySong(query_songs);
            if (songs_data != null) {
                for (i = 0; i < songs_data.size(); i++) {
                    System.out.println(
                            "id=" + songs_data.get(i).getId() +
                            " track=" + songs_data.get(i).getTract_id() +
                            " title=" + songs_data.get(i).getTitle() +
                            " album=" + songs_data.get(i).getAlbum_id());
                }
            }
        }


        data_src.close(DataSource.CONNECTION_NAME);
    }
}