package Query;

import Chronology.Chronology;
import Cypher.Cypher;
import DB.Database;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 * Query.java - Parent class for all query classes. Facilitates database, 
 * encryption, and time queries.
 * CSIS 643 - D01
 * @author Kore Woody
 */
public class Query 
{
    ResultSet rs;
    DefaultTableModel model;
    Map<Integer, Map<Object, Object>> map;
    private String customerID;
    private String[] departments;
    int totalTickets, newNumber;
    
    /**
     * insert method executes insert query into database
     * @param query SQL query
     */
    public void insert(String query)
    {
        Database db = new Database(query);
        db.insert();
    }// end insert method 
    
    /**
     * execute method executes SQL query
     * @param query SQL query
     */
    public void execute(String query)
    {
        Database db = new Database(query);
        db.execute();
    }// end execute method 
    
    /**
     * resultSet method queries database and returns result set
     * @param query SQL query
     * @return return query result set
     */
    public ResultSet resultSet(String query)
    {
        Database db = new Database(query);
        ResultSet rs = db.select();
        return rs;
    }// end resultSet method 
    
    /**
     * count method counts total number of rows in table and returns next 
     * increment of value
     * @param table table being counted
     */
    public void count(String table)
    {
      int count = 0;
      // create and execute query
      ResultSet rs = resultSet("SELECT COUNT(*) FROM " + table);
      try{
          while(rs.next())
          {
              count = rs.getInt(1);
              this.newNumber = count+1;
          }
      }catch (SQLException e){}// catch exceptions
    }// end count method 
    
    /**
     * startup method for setting the cipher key on startup
     */
    public void startup()
    {
        Cypher cypher = new Cypher();
        cypher.fileKey();
    }// end startup method 
    
    /**
     * encrypt method encrypts and returns encrypted string
     * @param value decrypted string value
     * @return 
     */
    public String encrypt(String value)
    {
        // check for null values
        if (value==null)
        {
            return value;
        }
        else // not null
        {
            Cypher cypher = new Cypher();
            return cypher.encrypt(value);
        }
    }// end encrypt method 
    
    /**
     * decrypt method decrypts and returns encrypted string
     * @param value encrypted string value
     * @return 
     */
    public String decrypt(String value)
    {
        // check for null values
        if (value==null)
        {
            return value;
        }
        else // not null
        {
            Cypher cypher = new Cypher();
            return cypher.decrypt(value);
        }
    }// end decrypt method 
    
    /**
     * setKey method for setting the cipher key
     * @param key cipher key
     */
    public void setKey(String key)
    {
        Cypher cypher = new Cypher();
        cypher.setKey(key);
    }// end setKey method 
    
    /**
     * currentDate method returns the current date
     * @return current date
     */
    public String currentDate()
    {
        Chronology c = new Chronology();
        return c.getDate();
    }// end currentDate method 
    
    /**
     * currentTime method returns the current time
     * @return current time
     */
    public String currentTime()
    {
        Chronology c = new Chronology();
        return c.getTime();
    }// end currentTime method 
    
    /**
     * currentDateTime method returns the current date and time
     * @return current date and time
     */
    public String currentDateTime()
    {
        return currentDate() + " " + currentTime();
    }// end currentDateTime method 
    
    
    /**
     * changeEncryptionKey method for changing the encryption key and 
     * re-encrypting the database
     * @param newKey new encryption key
     */
    public void changeEncryptionKey(String newKey)
    {
        String[] tables = {"LOGIN", "TICKET", "TICKETLOG", "ACCOUNTLOG", "MESSAGES", "CUSTOMERS", "CUSTOMERVEHICLES", "VEHICLEINVENTORY", "ERRORCODES", "VEHICLEDEATHS", "SUDDENACCELERATION"};

        for (String table : tables) {
            ResultSet rs = resultSet("SELECT * FROM " + table);
            List<String> encRes = new ArrayList<String>();
            List<String> results = new ArrayList<String>();
            List<String> headers = new ArrayList<String>();
            int columns = 0;
            int row = 0;
            String columnType = "";
            String columnName = "";

            try {
                while (rs.next()) {
                    ResultSetMetaData md = rs.getMetaData();
                    columns = md.getColumnCount();
                    row = rs.getRow();
                    for (int i = 1; i <= columns; i++) {
                        columnType = md.getColumnTypeName(i);
                        columnName = md.getColumnName(i);

                        if (row == 1) {
                            headers.add(columnName);
                        }
                        if (columnType.matches("VARCHAR")) {
                            if (rs.getString(columnName) == null || rs.getString(columnName).matches("null")) {
                                results.add(rs.getString(columnName));
                            } else {
                                results.add("'" + decrypt(rs.getString(columnName)) + "'");
                            }
                        } else if (columnType.matches("INTEGER")) {
                            results.add(String.valueOf((int) rs.getInt(columnName)));
                        } else if (columnType.matches("DOUBLE")) {
                            results.add(String.valueOf((double) rs.getDouble(columnName)));
                        } else {
                            results.add(rs.getString(columnName));
                        }
                    }
                }
                setKey(newKey);
                if (headers.size() > 0) {
                    for (int r = 0; r < results.size() / headers.size(); r++) {
                        String query = "\nUPDATE " + table + " SET ";
                        for (int h = 1; h <= headers.size() - 1; h++) {
                            String x = "";
                            String res = results.get(h);
                            if (res != null && res.contains("\'") && !res.matches("null")) {
                                res = res.substring(1, res.length() - 1);
                                x = "'";
                            }
                            res = encrypt(res);
                            if (h == headers.size() - 1) {
                                query = query + headers.get(h) + " = " + x + res + x
                                        + " WHERE " + headers.get(0) + " = " + res + " ";
                            } else {
                                query = query + headers.get(h) + " = " + x + res + x + ", ";
                            }

                        }

                    }
                }
            } catch (SQLException ex) {} //catch exceptons
        }
    }// end changeEncryptionKey method
    
    /**
     * setModel method sets the model and map for the given SQL query
     * @param query SQL query to execute
     */
    public void setModel(String query)
    {
        int columns = 0;
        int row = 0;
        String columnType = "";
        String columnName = "";
        List<String> list = new ArrayList<String>();
        List<String> results = new ArrayList<String>();
        List<String> headers = new ArrayList<String>();
        Map<Integer,Map<Object,Object>> map = new HashMap<>();
        String[] values = {};
        boolean ticketCount = false;
        int totalTickets = 0;
        
        // execute query and get result set
        ResultSet rs = resultSet(query);
        if (rs!=null)
        {
            this.rs = rs;
            try { // process result set
                while (rs.next()) 
                {
                    ResultSetMetaData md = rs.getMetaData();
                    columns = md.getColumnCount();
                    row = rs.getRow();
                    // loop for each column
                    for (int i = 1; i <= columns; i++) 
                    {
                        // get column nam,e and type
                        columnType = md.getColumnTypeName(i);
                        columnName = md.getColumnName(i);

                        if (row == 1) // add column names to list
                        {
                            headers.add(columnName);// appeand to name to headers
                        }
                        // check for stings that will require ciphering
                        if (columnType.matches("VARCHAR")||columnType=="VARCHAR") 
                        {
                            // check for null results
                            if (rs.getString(columnName) == null || 
                                    rs.getString(columnName).matches("null")) 
                            {
                                results.add(rs.getString(columnName));
                            } 
                            else // not null
                            {
                                // append to resuls
                                results.add(decrypt(rs.getString(columnName)));
                            }
                        }
                        // check for integer value type
                        else if (columnType.matches("INTEGER")||columnType=="INTEGER") 
                        {
                            String value = rs.getString(columnName);
                            if (value==null) // null value
                            {
                                value = "";
                            }
                            else // not null
                            {
                                results.add(value);
                            }
                        }
                        // check for double data type
                        else if (columnType.matches("DOUBLE")||columnType=="DOUBLE") 
                        {
                            results.add(String.valueOf((double) rs.getDouble(columnName)));
                        }
                        else 
                        {
                            results.add(rs.getString(columnName));
                        }
                    }
                    this.totalTickets = row;// set final row as total row count
                }
                if (headers.size()>0)
                {
                    // set model first row with headers converted to string array
                    String[] modelHeaders = headers.toArray(new String[headers.size()]);
                    this.model = new DefaultTableModel(modelHeaders,0);
                    
                    // check for null results
                    if (results.size()>0)
                    {
                        int k = 0;
                        // loop for eack row
                        for (int i = 0; i < row; i++)
                        {
                            // create new object array and hashmap
                            Object[] data = new Object[headers.size()];
                            Map<Object,Object> mapData = new HashMap<>();

                            // loop for each column
                            for (int c = 0; c < headers.size(); c++ )
                            {
                                Object value = results.get(k+c);// get cell value
                                // cehck for null value
                                if (value!=null)
                                {
                                    //check for boolean as string then convert to boolean
                                    if (value.equals("true")||value=="true")
                                    {
                                        value = true;
                                    }
                                    else if (value.equals("false")||value=="false")
                                    {
                                        value = false;
                                    }
                                    // append to data array
                                    data[c] = value;
                                    // append data array to map with header
                                    mapData.put(headers.get(c),data[c]);
                                }
                            }
                            k+=headers.size();// increment array position
                            // append date to map
                            map.put(i, mapData);
                            model.addRow(data);// append to row
                        }
                        this.map = map;//set map
                    }
                }
            } 
            catch (SQLException ex) {} // catch exception
        }
    }// end setModel method 
    
    /**
     * setCustomerID method customerID base
     * @param customerName first and last name
     */
    public void setCustomerID(String customerName)
    {
        // create and execute query
        setModel("SELECT * FROM CUSTOMERS WHERE "+
                "CUSTOMERNAME = '" + encrypt(customerName)+"' ");
        // ger results map
        Map<Integer,Map<Object,Object>> map = getMap();
        
        int[] ids = new int[map.size()];
        String[] cUsers = new String[map.size()];
        int temp = 0;
        int hi = 0;
        
        // loop for highest id number
        for (int i : map.keySet())
        {
            cUsers[i] = map.get(i).get("CUSTOMERID").toString();
            ids[i] = (int) Integer.parseInt(map.get(i).get("CUSTOMERID").toString().replaceAll("[^0-9]",""));
            // compare current vs  previous
            if (ids[i]>temp)
            {
                temp = ids[i]; // set temp with highest id number
            }
        }
        // set new id number 1 plus the largest
        customerID = customerID + (temp+1);
        this.customerID = customerID;
    }// end setCustomerID method
    
    /**
     * setCustomerID method Sets the customer ID based on the specified first 
     * name, last name, phone, and email parameters.
     * @param firstName The first name of the customer.
     * @param lastName The last name of the customer.
     * @param phone
     * @param email The email address of the customer.
     */
    public void setCustomerID(String firstName, String lastName, String phone, String email)
    {
        //set base of customer id
        String customerID = firstName.toLowerCase() + lastName.toLowerCase();
        setModel("SELECT CUSTOMERID FROM CUSTOMERS WHERE "+
                "FIRSTNAME = '" + encrypt(firstName) + 
                "' AND LASTNAME =  '" + encrypt(lastName) + 
                "' AND PHONE = '" + encrypt(phone) + 
                "' AND EMAIL = '" + encrypt(email) + "' ");
        
        Map<Integer,Map<Object,Object>> map = getMap();
        // check if customer exists in database
        if(map!=null) // existing customer
        {
            customerID = String.valueOf(map.get(0).get("CUSTOMERID"));
            this.customerID = customerID;
        }
        else // new customer
        {
            // create and execute select query
            setModel("SELECT * FROM CUSTOMERS WHERE "+
                "FIRSTNAME = '" + encrypt(firstName) + 
                "' AND LASTNAME =  '" + encrypt(lastName) + "' ");
            map = getMap();
            if(map!=null) // matching ID base found
            {
                int[] ids = new int[map.size()];
                String[] cUsers = new String[map.size()];
                int temp = 0;

                // compare user id number 
                for (int i : map.keySet())
                {
                    cUsers[i] = map.get(i).get("CUSTOMERID").toString();
                    ids[i] = (int) Integer.parseInt(map.get(i).get("CUSTOMERID").toString().replaceAll("[^0-9]",""));
                    // set as new temp max if greater than previous
                    if (ids[i]>temp)
                    {
                        temp = ids[i];
                    }
                }
                // set customer ID
                customerID = customerID + (temp+1);
                this.customerID = customerID;
            }
            else // unique customerID
            {
                // set customer ID
                customerID = customerID+1;
                this.customerID = customerID;
            }
        }
    }// end setCustomerID method
    
    /**
     * setDepartments method sets the departments on the specified user who 
     * opens, edits, and closes a ticket
     * @param openBy user who created the ticket
     * @param editBy user who last edited the ticket
     * @param closeBy user who closed the ticket
     */
    public void setDepartments(String openBy, String editBy, String closeBy)
    {
        Map<Integer,Map<Object,Object>> map = new HashMap<>();
        String[] departments = new String[3];
        String dept = "";
        
        // create and execute query for the ticket opener
        setModel("SELECT Department FROM LOGIN WHERE USERID = '"+encrypt(openBy)+"'");
        map = getMap();
        // check for null results
        if (map!=null) // results not null
        {
            for (int i : map.keySet())// loop for null calues
            {
                // get department
                dept = String.valueOf(map.get(i).get("DEPARTMENT"));
                if (dept==null) 
                {
                    dept = "";
                }
                // append encrypted department
                departments[0] = encrypt(dept);
            }
            
            // create and execute query for the ticket editor
            setModel("SELECT DEPARTMENT FROM LOGIN WHERE USERID = '" + encrypt(editBy) + "'");
            map = getMap();
            for (int i : map.keySet()) {// loop for nul values
                // get department
                dept = String.valueOf(map.get(i).get("DEPARTMENT"));
                if (dept==null) 
                {
                    dept = "";
                }
                // append encrypted department
                departments[1] = encrypt(dept);
            }

            // create and execute query for the ticket closer
            setModel("SELECT DEPARTMENT FROM LOGIN WHERE USERID = '" + encrypt(closeBy) + "'");
            map = getMap();
            for (int i : map.keySet()) {
                // get department
                dept = String.valueOf(map.get(i).get("DEPARTMENT"));
                if (dept==null) 
                {
                    dept = "";
                }
                // append encrypted department
                departments[2] = encrypt(dept);
            }
            this.departments = departments;// set departmet
        }
    }// end setDepartments method
    
    /**
     * getDepartments method returns the departments
     * @return array of departments
     */
    public String[] getDepartments() {
        return departments;
    }// end getDepartments method

    /**
     * getCustomerID method returns customer ID
     * @return customer ID
     */
    public String getCustomerID() {
        return customerID;
    }// end getCustomerID method

    /**
     * getModel method table model
     * @return data table model
     */
    public DefaultTableModel getModel() {
        return model;
    }// end getModel method

    /**
     * getResultSet returns query result set
     * @return result set
     */
    public ResultSet getResultSet() {
        return rs;
    }// end getResultSet method

    /**
     * getMap method returns map of result set
     * @return data map
     */
    public Map<Integer, Map<Object, Object>> getMap() {
        return map;
    }// end getMap method

    /**
     * getTotalTickets method returns total ticket count
     * @return total number of tickets
     */
    public int getTotalTickets() {
        return totalTickets;
    }// end getTotalTickets method

    /**
     * getNewNumber method returns new count number
     * @return new customer number/ ticket number
     */
    public int getNewNumber() {
        return newNumber;
    }// end getNewNumber method
    
}// end Query class
