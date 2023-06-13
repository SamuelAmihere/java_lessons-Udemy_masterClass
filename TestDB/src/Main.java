import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * -------------CREATING DATABASE---------
 * 1. Create connection
 * 2. Create Statement
 * 3. Execute Statement
 * 4. Close resources (statement and connection) - use try with resources
 */

public class Main {
    /* ------------------------------------------------------------------------
                                  GLOBAL VARIABLES
    ---------------------------------------------------------------------------
     */
    // ===============DATABASE==========
    public static String db_name = "testjava.db";
    public static String db_driver = "jdbc:sqlite";
    public static String db_path = "/home/samuel/amihere2830/programmingLessons" +
            "/Udemy/java-master-class/Database/TestDB/";
    public static String db_url = db_driver + ":" + db_path + db_name;

    // ===============STATEMENTS========
    //---Fields
    public static String contact_fields = "(name TEXT, phone INTEGER, email TEXT)";

    //--Flags
    public static boolean new_record = false;
    public static boolean update_record = false;
    public static boolean delete_record = false;
    public static boolean query_record = true;
    public static boolean drop_table = false;

    public static void main(String[] args) {
        int i;
        Map<String, List<String>> tb_name_cols = new HashMap<>();
        ArrayList<DBTable> tables;
        ArrayList<String>  schemas =  new ArrayList<>();
        DBTable contact_table;
        List<String> columns = new ArrayList<>();
        SQLStatement sql;
        Map<String, String> sql_stmts = new HashMap<>();

        DataBase db;
        List<String> data = new ArrayList<>();


        Map<String, String> data_update = new HashMap<>(); //Update: Data

        Map<String, Boolean> data_delete = new HashMap<>(); //Delete: Data

        String select; // Query data

        //CREATE TABLES
        //add table column names and schema
        columns.add("name");
        columns.add("phone");
        columns.add("email");
        schemas.add(contact_fields);

        tb_name_cols.put("contacts", columns);

        //Create tables
        tables = createTables(tb_name_cols, schemas);
        //Get contact table

        contact_table = tables.get(0);

        //Data: new
        data.add("('Samuel', 21737363, 'sam@gmail.com')");
        data.add("('Johnson', 454334522, 'jn@gmail.com')");
        data.add("('Adwoa', 435626565, 'adowa@gmail.com')");
        data.add("('Nana', 34544544, 'nana1@gmail.com')");

        //Data: update
        data_update.put("name='Johnson'", "name='John'");
        data_update.put("name='Adwoa'", "name='Adowa'");
        data_update.put("name='Nana'", "name='Nana Boah I'");

        //Dara: delete
        data_delete.put("name='Kofi'", true);
        data_delete.put("name='Yaw'", true);

        //Query:
        select = "*";

        //CREATE SQL STATEMENTS FOR THE CONTACT TABLE
        sql = new SQLStatement(contact_table);

        //Add insert statements
        for (i = 0; i < data.size(); i++){
            sql_stmts.put("insert_" + (i+1), sql.createStmt(data.get(i)));
        }

        //========================CREATE CONNECTION =========================//
        //CREATE DataBase
        db = new DataBase(db_name, db_url);
        try(Connection conn = DriverManager.getConnection(db.getUrl());
            Statement statement = conn.createStatement()) {

            System.out.println("Connection Established");

            // ===Execute Statements to create database and insert values===//
            if (new_record){
                executeSQLstatements(db, statement, sql, contact_table,
                        sql_stmts);
            }

            // =======Execute Statements to update database==========//
            if (update_record){
                utterTable(db, statement, sql, contact_table, data_update);
            }

            if (delete_record){
                utterTable(db, statement, sql, contact_table, data_delete,
                        true);
            }

            if (query_record){
                queryContact(db, statement, sql, contact_table, select);
            }

            if (drop_table){
                executeSQLstatements(db, statement, sql, contact_table);
            }

            //Add table to db
            db.setDb_tables(tables);

        } catch(SQLException e){
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ======================== FUNCTIONS =====================================
    //------------- 1. Create tqables ------------------
    public static ArrayList<DBTable> createTables(Map<String,
            List<String>> tb_name_cols, ArrayList<String>  schemas){
        ArrayList<DBTable> tables = new ArrayList<>();
        int i = 0;
        DBTable table;

        for (Map.Entry<String, List<String>> tb: tb_name_cols.entrySet()){
            table = new DBTable(tb.getKey());
            table.setFields(tb.getValue());
            table.create_schema(schemas.get(i));
            tables.add(table);
            i++;
        }
        return tables;
    }

    //------------- 2. execute SQL statements: create & add record --------------
    public static void executeSQLstatements(DataBase db, Statement st,
            SQLStatement sql, DBTable table, Map<String, String> sql_stmts){
        String sql_create; //sql statement to create table
        String sql_insert; //sql statement to insert data into table

//        String sql_update; //sql statement to update record
//        String newValue; //new value for update
//        String whereValue; //update condition

        //create table in database
        sql_create = sql.createStmt();

        if (db.executeSQL(st, sql_create)) {
            System.out.println(table.getName() + ThreadColor.GREEN +
                    " table created successfully."+ThreadColor.RESET);
        } else System.out.println(table.getName() + ThreadColor.BLUE +
                " table exists."+ThreadColor.RESET);

        //add data to database

        for (Map.Entry<String, String> stmt: sql_stmts.entrySet()){

            sql_insert = stmt.getValue();

            table.upDateHistory(stmt.getKey(), sql_insert);

            if (db.executeSQL(st, sql_insert)){
                System.out.println(stmt.getKey() + ThreadColor.GREEN +
                        " successful."+ThreadColor.RESET);
            } else System.out.println(stmt.getKey() + ThreadColor.RED +
                    " unsuccessful."+ThreadColor.RESET);
        }
    }

    // ----------- 3. execute SQL statements: update existing record ------------
    public static void executeSQLstatements(DataBase db, Statement st,
                                            SQLStatement sql, DBTable table){
        String sql_drop;

        sql_drop = sql.dropTable_stmt();
        if (db.executeSQL(st, sql_drop)) {
            System.out.println(table.getName() + ThreadColor.GREEN +
                    " table deleted successfully."+ThreadColor.RESET);
        } else System.out.println(table.getName() + ThreadColor.BLUE +
                " not deleted."+ThreadColor.RESET);
    }

    //---------- 4. Modify table by changing values of existing records ----------
    public static void utterTable(
            DataBase db, Statement st, SQLStatement sql,
            DBTable table, Map<String, String> data_update){

        String sql_update; //sql statement to update record
        String newValue, whereValue; // values to build sql_update

        for (Map.Entry<String, String> stmt: data_update.entrySet()){
            whereValue = stmt.getKey();
            newValue = stmt.getValue();

            sql_update = sql.createStmt(newValue, whereValue);

            if (db.executeSQL(st, sql_update)){
                System.out.println(ThreadColor.GREEN +
                        "Updated " + table.getName() + " table " +
                        "@ " + whereValue + " with " + newValue + "." +
                        ThreadColor.RESET);
            } else System.out.println(ThreadColor.RED +
                    "Update unsuccessful." +
                    ThreadColor.RESET);
        }
    }

    // ------------------ 5. Delete record from a table -----------------------
    public static void utterTable(
            DataBase db, Statement st, SQLStatement sql,
            DBTable table, Map<String, Boolean> data_delete, boolean delete){

        String sql_delete, whereValue; //sql statement to update record
        boolean newValue; // values to build sql_update

        if (delete){
            for (Map.Entry<String, Boolean> stmt: data_delete.entrySet()){
                whereValue = stmt.getKey();
                newValue = stmt.getValue();

                sql_delete = sql.createStmt(whereValue, newValue);

                if (db.executeSQL(st, sql_delete)){
                    System.out.println(ThreadColor.GREEN +
                            "Deleted " + "a record in " + table.getName() + " table " +
                            "@ " + whereValue + "." +
                            ThreadColor.RESET);
                } else System.out.println(ThreadColor.RED +
                        "Deletion unsuccessful." +
                        ThreadColor.RESET);
            }
        }
    }

    // ------------- 6. Select records from contact table -------------------
    public static void queryContact(
            DataBase db, Statement st, SQLStatement sql,
            DBTable table, String select) throws SQLException {

        String sql_select; //sql statement select record

        sql_select = sql.queryStmt(select);

        if (db.executeSQL(st, sql_select)){
            ResultSet result = st.getResultSet();
            while (result.next()){
                System.out.println(result.getString(table.getColumns().get(0)) +
                        " " + result.getInt(table.getColumns().get(1)) + " " +
                        result.getString(table.getColumns().get(2)));
            }
            result.close();
        }
    }
}