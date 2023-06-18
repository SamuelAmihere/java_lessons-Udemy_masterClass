package model;

public class Artist {
    private int id;
    private String name;
    private String schema;

    /*----Setters---*/
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    /*----Getters---*/
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSchema() {
        return schema;
    }
}
