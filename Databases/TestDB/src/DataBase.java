import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class DataBase {
    private final String name;
    private final String url;
//    private final String conPasswd;
    private final HashMap<String, DBTable> db_tables = new HashMap<>();

    public DataBase(String name, String url) {
        this.name = name;
        this.url = url;
//        this.conPasswd = conPasswd;
    }


    public void setDb_tables(ArrayList<DBTable> tables) {
        int i;

        for (i = 0; i < tables.size(); i++){
            this.db_tables.put(tables.get(i).getName(),
                    tables.get(i));
        }
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public HashMap<String, DBTable> getDb_tables() {
        return db_tables;
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