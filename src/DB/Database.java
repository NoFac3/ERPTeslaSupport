package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.driver.OracleConnection;

/**
 * Database.java - class handles connection and queries to database
 * CSIS 643 - D01
 * @author Kore Woody
 */
public class Database {
	
    String query;
    public Connection conn = null;
    OracleConnection oCon = null;
    Statement stmt = null;
    ResultSet rs = null;
    final String DATABASE_URL = "jdbc:derby://localhost:1527/TeslaSupportDatabase;create=true;dataEncryption=true;bootPassword=teslaSupport0123";
    final String DATABASE_DRIVER = "org.apache.derby.jdbc.ClientDriver";
        
    /**
     * Database constructor initializes database connection
     */
    public Database()
    {
        try {// try database driver
            Class.forName(DATABASE_DRIVER).newInstance();
        } catch (Exception e) {} // catch exception

        try {// create connection
            conn = DriverManager.getConnection(DATABASE_URL);

        } catch (SQLException ex) {}// catch exception
    }// end Database constructor
        
    /**
     * getConn method returns database connection
     * @return Connection
     */
    public Connection getConn()
    {
        return conn;
    }// end getConn constructor
        
    /**
     * Database constructor initializes database connection and sets query 
     * statement
     * @param query SQL query
     */
    public Database(String query) 
    {
        // set query
        this.query = query;

        try {// try database driver
            Class.forName(DATABASE_DRIVER).newInstance();
        } catch (Exception e) {}// catch exception

        try {// try connection and create statement
            conn = DriverManager.getConnection(DATABASE_URL);
            stmt = conn.createStatement();
        } catch (SQLException ex) {}// catch exception
    }// end Database Constructor

    /**
     * select method for executing database select queries
     * @return
     */
    public ResultSet select() 
    {
        // try query
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {}// catch exception
        return rs;
    }// end select method
	
    /**
     * insert method for executing database insert queries
     */
    public void insert() 
    {
        // try query
        try {
            stmt.execute(query);
            // instantiate new DatabaseListener object update
            DatabaseListener dl = new DatabaseListener();
        } catch (SQLException e) {}// catch exception
    }// end insertt method
        
    /**
     * execute method for executing database update/delete queries
     */
    public void execute()
    {
        // try query
        try {
            stmt.execute(query);
            // instantiate new DatabaseListener object update
            DatabaseListener dl = new DatabaseListener();
        } catch (SQLException e) {}// catch exception
    }// end execute method
    
}// end Database class
