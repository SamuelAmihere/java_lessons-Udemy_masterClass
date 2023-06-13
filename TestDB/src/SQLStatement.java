public class SQLStatement {
    private  DBTable table;

    public SQLStatement(DBTable table) {
        this.table = table;
    }

    public DBTable getTable() {
        return table;
    }

    public String createStmt(){

        return "CREATE TABLE IF NOT EXISTS " +
                this.table.getName() + " " +
                this.table.getSchema();
    }

    public String createStmt(String values_insert){

        return "INSERT INTO " +
                this.table.getName() + " " +
                this.table.getFields() + " " +
                "VALUES" +
                values_insert;
    }

    public String createStmt(String update_value, String where){

        return "UPDATE " +
                this.table.getName() + " " +
                "SET " +
                update_value + " " +
                "WHERE " +
                where;
    }

    public String createStmt(String where, boolean del){
        if (del){
            return "DELETE FROM " +
                    this.table.getName() + " " +
                    "WHERE " +
                    where;
        } else return "";
    }

    public String queryStmt(String headers){
        return "SELECT " +
                headers + " " +
                "FROM " +
                this.table.getName();
    }

    public String dropTable_stmt(){
        return "DROP TABLE IF EXISTS " +
                this.table.getName();
    }
}