import model.*;
import utilities.ThreadColor;

import java.util.List;
import java.util.Scanner;

public class Main {
    /* ---------------- GLOBALS --------------*/
    /* FLAGS */
    public static boolean artists_flag = false;
    public static boolean albums_flag = false;
    public static boolean songs_flag = false;
    public static boolean artist_list_info = true;

    /* -----------METHODS----------------------*/
    /*MAIN*/
    public static void main(String[] args){

        //Initialize variables
        DataSource data_src;
        String select_count;
        int i;

        //TABLES
        MusicTable songs_table;
        MusicTable albums_table;
        MusicTable artists_table;

        //view tables
        MusicTable artist_list_vt;

        //QUERY STATEMENTS
        String query_songs, query_albums, query_artists;
        String query_songs_count, query_albums_count, query_artists_count;

        //Query View Tables
        String query_view_song_info;

        //Arg data
        String title;

        //Queried Data
        List<Artist> artists_data;
        List<Album> albums_data;
        List<Song> songs_data;

        List<SongArtist> artists_view_data;

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

        //VIEWS
        //4. songArtists view table
        artist_list_vt = new MusicTable(DataSource.TABLE_ARTIST_SONG_VIEW);
        artist_list_vt.create_schema(DataSource.artist_list_schema);


        // Query counts
        // query_albums_count =new SQLStatement(albums_table).queryStmt("*");
        // query_artists_count = new SQLStatement(artists_table).queryStmt("COUNT(*)");



        /* -------------------------RESULTS------------------------*/
        /* QUERY DATA*/
        if (artists_flag){
            //Query statement
            query_artists = new SQLStatement(artists_table).queryStmt("*");

            data_src.querySongMetaData(query_artists);
            System.out.println("==========================================\n");

            /* Artist query data*/
            artists_data =  data_src.queryArtists(query_artists);
            if (!artists_data.isEmpty()) {
                for (i = 0; i < artists_data.size(); i++) {
                    System.out.println("id=" + artists_data.get(i).getId() + " name=" +
                            artists_data.get(i).getName());
                }
            }
        }

        if (albums_flag){
            //Query statement
            query_albums =new SQLStatement(albums_table).queryStmt("*");

            data_src.querySongMetaData(query_albums);
            System.out.println("==========================================\n");

            /* Artist query data*/
            albums_data =  data_src.queryAlbum(query_albums);
            if (!albums_data.isEmpty()) {
                for (i = 0; i < albums_data.size(); i++) {
                    System.out.println(
                            "id=" + albums_data.get(i).getId() +
                                    " name=" + albums_data.get(i).getName() +
                                    " artist_id=" + albums_data.get(i).getArtist_id());
                }
            }
        }

        if (songs_flag){
            // Query statement
            query_songs = new SQLStatement(songs_table).queryStmt("*");
            query_songs_count = new SQLStatement(songs_table).queryStmt("COUNT(*)");

            data_src.querySongMetaData(query_songs);
            System.out.println("Queried: "+data_src.getCount(query_songs_count));
            System.out.println("==========================================\n");

            /* Artist query data*/
            songs_data =  data_src.querySong(query_songs);
            if (!songs_data.isEmpty()) {
                for (i = 0; i < songs_data.size(); i++) {
                    System.out.println(
                            "id=" + songs_data.get(i).getId() +
                            " track=" + songs_data.get(i).getTract_id() +
                            " title=" + songs_data.get(i).getTitle() +
                            " album=" + songs_data.get(i).getAlbum_id());
                }
            }
        }

        /*Query data from views*/
        if (artist_list_info){
            //Prompt user for song title
            title = getSongTitle();

            System.out.println("artist_list view created? " +
                    (data_src.createViewForSongArtists()? ThreadColor.GREEN + "YES." :
                            ThreadColor.RED + "NO.") + ThreadColor.RESET);

            /* Artist query data from view*/
            artists_view_data = data_src.querySongInfoView(title);

            if (!artists_view_data.isEmpty()){
                for (i = 0; i < artists_view_data.size(); i++){
                    System.out.println(
                            "name= " + artists_view_data.get(i).getArtistName() + ", "+
                            "album= " + artists_view_data.get(i).getAlbumName() + ", "+
                            "track= " + artists_view_data.get(i).getTrack());
                }
            }
        }


        data_src.close(DataSource.CONNECTION_NAME);
    }






    /* Function to get song title from user*/
    public static String getSongTitle(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a song title: ");
        return scanner.nextLine();
    }
}