# Databases

## Table of Contents
- [TestDB](#testdb)

## TestDB

The Databases folder contains the TestDB project, which performs CRUD operations on the contact table in the testjava.db database. The database used is SQLite. The project includes three classes: DBTable, SQLStatement, and DataBase.

### Main Class

The Main class serves as the entry point for the TestDB project. It contains the main method and performs various operations on the contact table.
### Global Variables

The class declares several global variables that hold important information for the database and statements:

    db_name: Holds the name of the database file (testjava.db).
    db_driver: Holds the driver information for connecting to the database (jdbc:sqlite).
    db_path: Holds the path to the database file.
    db_url: Holds the complete URL for connecting to the database.
    contact_fields: Holds the field names and their types for the contact table.
    new_record, update_record, delete_record, query_record, drop_table: Boolean flags to control different operations.

### Main Method

The main method is where the execution of the TestDB project starts. Here are the steps performed in the method:

    Initialize variables and objects needed for the database operations.
    Create the contact table by providing the column names and schema.
    Insert data into the contact table.
    Update existing records in the contact table.
    Delete records from the contact table.
    Query and display the records from the contact table.
    Drop the contact table if necessary.
    Close the database connection.

### Functions

The Main class also includes several helper functions to perform different tasks:

    createTables: Creates table objects based on the provided table names, column names, and schemas.
    executeSQLstatements: Executes SQL statements to create tables and insert records into the database.
    executeSQLstatements: Executes SQL statements to drop tables from the database.
    utterTable: Modifies table records by updating their values.
    utterTable: Deletes records from a table.
    queryContact: Performs a query on the contact table and displays the selected records.

### Usage

To use the TestDB project, make sure you have the SQLite JDBC driver added to your project's dependencies. Adjust the database path in the Main class to match the location of your testjava.db file. You can then run the project and observe the CRUD operations on the contact table.

Note: This README assumes that you are familiar with Java programming and basic database concepts.

Feel free to explore the TestDB project and modify it according to your requirements.
