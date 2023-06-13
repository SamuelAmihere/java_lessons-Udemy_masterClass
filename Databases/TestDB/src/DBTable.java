import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBTable {
    private String name;
    private String schema;
    private List<String> columns = new ArrayList<>();
    private String fields;
    private HashMap<String, String> history = new HashMap<>();
    private Integer count = 0;

    public DBTable(String name) {
        this.name = name;
    }

    public void create_schema(String schema) {
        this.schema = schema;
    }

    public void setFields(List<String> columns) {
        Integer i;
        StringBuilder sb = new StringBuilder("(");

        for (i = 0; i < columns.size(); i++){
            this.columns.add(columns.get(i));

            if (i + 1 == columns.size()){
                sb.append(columns.get(i) + ")");
            } else {
                sb.append(columns.get(i) + ", ");
            }
        }
        this.fields = sb.toString();
    }

    public List<String> getColumns() {
        return columns;
    }

    public String getName() {
        return name;
    }

    public String getSchema() {
        return schema;
    }

    public String getFields() {
        return fields;
    }

    public void upDateHistory(String name, String stmt){
        history.put(
                name,
                stmt);
    }
}