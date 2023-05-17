package Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * QueryAccount.java - class used to create database queries for accounts 
 * CSIS 643 - D01
 * @author NoFac3
 */
public class QueryAccount extends Query
{
    String userID, passID, empID,newUserID;
    int userkey;
    String type, name, firstName, lastName;
    String department, dateCreated;
    String admin, manager, engineer, analyst, tech;
    boolean isAdmin, isManager, isEngineer, isAnalyst, isTech, valid;
    ResultSet userProfile;
    DefaultTableModel accountModel, accountsModel;
    String timeOut, hours, minutes,seconds;
    String defaultPassID = "";
    
    /**
     * Login method for verifying login credentials
     * @param userID
     * @param passID
     * @return Boolean validation
     */
    public boolean Login(String userID, String passID)
    {
        boolean valid = false;
        
        try{
            // create encrypted login query and set result set
            ResultSet rs = resultSet("select * from LOGIN where USERID = '" 
                + encrypt(userID) + "' and PASSWORD = '" + encrypt(passID) + "'");
            // check for null results
            if (!rs.next())
            {
                valid = false;
            }
            else// result found
            {
                valid = true;
                // decrypt and get user ID
                this.userID = decrypt(rs.getString("USERID"));
                // set user profile
                setUserProfile(userID);
            }
        }
        catch (SQLException ex) {}// catch exception
        return valid;
    }// end Login method 
    
    /**
     * setUserProfile method queries for user profile information
     * @param userID 
     */
    public void setUserProfile(String userID)
    {
        // create encrypted account query
        String query = "SELECT * FROM LOGIN WHERE USERID = '" + encrypt(userID.replace("\'", "")) +  "'";
        // set results model
        setModel(query);        
        try {
            // get and set resutls set
            ResultSet rs = resultSet(query);
            this.userProfile = rs;
            
            while (rs.next())
            {
                // decrypt and get account profile info
                this.type = decrypt(rs.getString("USERTYPE"));
                this.name = decrypt(rs.getString("UserName"));
                this.passID = decrypt(rs.getString("PASSWORD"));
                this.firstName = decrypt(rs.getString("FirstName"));
                this.lastName = decrypt(rs.getString("LastName"));
                this.department = decrypt(rs.getString("Department"));
                this.dateCreated = decrypt(rs.getString("DateCreated"));
                this.isAdmin = rs.getBoolean("AdminPermission");
                this.isManager = rs.getBoolean("ManagerPermission");
                this.isEngineer = rs.getBoolean("EngineerPermission");
                this.isAnalyst = rs.getBoolean("AnalystPermission");
                this.isTech = rs.getBoolean("TechPermission");
            }
        }
        catch (SQLException ex) {}//catch exception
    }// end setUserProfile method 
    
    /**
     * matchProfile method for setting and validating new usernames
     * @param userID
     * @param firstName
     * @param lastName 
     */
    public void matchProfile(String userID, String firstName, String lastName)
    {
        String newUserID = "";
        
        try {
            ResultSet rs = resultSet("SELECT * FROM LOGIN WHERE FIRSTNAME = '" + encrypt(firstName) + "' AND LASTNAME = '"+encrypt(lastName)+"'");
            this.userProfile = rs;
            
            List<String> ids = new ArrayList<String>();
            // check for null resutls
            if (rs.next()) 
            {
                while (rs.next()) 
                {
                    // decrypt and get user names
                    String name = decrypt(rs.getString("USERID"));
                    // append to ids
                    ids.add(name);
                }

                String[] userIDs = ids.toArray(new String[0]);
                List<Integer> idNum = new ArrayList<Integer>();

                // split user name and get id numbers
                for (int i = 0; i < userIDs.length; i++) {
                    idNum.add(Integer.parseInt(userIDs[i].replace(userID, "")));
                }
                
                // sort userIDs
                Arrays.sort(userIDs);
                // set max number and new id number
                int index = idNum.size() - 1;
                int maxNum = idNum.get(index);
                int newNum = maxNum + 1;
                // combine username base plus new id number
                newUserID = userID + newNum;
            }
            else // null results
            {
                newUserID = userID + "1";
            }
            // set new userID
            this.newUserID = newUserID;
        }
        catch (SQLException ex) {}//catch exception
    }// end matchProfile method 
    
    /**
     * setUserModels method queries and sets the user table model for admin frames
     * @param rs result set
     */
    public void setUserModels(ResultSet rs) {
        try {
            //append table Headers
            accountsModel = new DefaultTableModel(
                    new String[]{
                        "Username","Name", "First Name", " Last Name",
                        "Type", "Department", "Date Created",
                        "Admin", "Manager", "Engineer","Analyst", "Tech"}, 0);
            while (rs.next()) {
                if (!rs.getString("USERID").matches(encrypt("default")))// dont return if default profile
                {
                    // get user account attributes
                    String empID = decrypt(rs.getString("USERID"));
                    String type = decrypt(rs.getString("USERTYPE"));
                    String name = decrypt(rs.getString("UserName"));
                    String firstName = decrypt(rs.getString("FirstName"));
                    String lastName = decrypt(rs.getString("LastName"));
                    String department = decrypt(rs.getString("Department"));
                    String dateCreated = decrypt(rs.getString("DateCreated"));

                    // get account  permissions
                    boolean isAdmin = rs.getBoolean("AdminPermission");
                    boolean isManager = rs.getBoolean("ManagerPermission");
                    boolean isEngineer = rs.getBoolean("EngineerPermission");
                    boolean isAnalyst = rs.getBoolean("AnalystPermission");
                    boolean isTech = rs.getBoolean("TechPermission");

                    // append attributesd to model for table
                    accountsModel.addRow(new Object[]{
                        empID, name, firstName, lastName,
                        type, department, dateCreated,
                        isAdmin, isManager,isEngineer, isAnalyst, isTech}); //replyBy});
                }
            }
        } catch (SQLException ex) {}// catch exception
    }// end setUserModels method 
    
    /**
     * selectUsers method queries database for setting user account table model
     */
    public void selectUsers() {
        setModel("SELECT "+ "USERID,USERTYPE,FIRSTNAME,LASTNAME,DEPARTMENT,"+
                "ADMINPERMISSION,MANAGERPERMISSION,ENGINEERPERMISSION,TECHPERMISSION,ANALYSTPERMISSION"+
                " FROM APP.LOGIN ");
    }// end selectUsers method 

    /**
     * selectUsersByPermissions method for filtering admin frame for user profiles
     * based on selected permissions
     * @param permissions Boolean array of user permissions
     */
    public void selectUsersByPermissions(boolean[] permissions) {
        String[] permissionType = {
            "AdminPermission", "ManagerPermission", "EngineerPermission",
            "AnalystPermission", "TechPermission"};
        
        // create start of query
        String queryStart = "SELECT * FROM LOGIN";
        
        // generate query by permissions
        int count = 0;
        for (int p = 0; p < permissions.length; p++) 
        {
            // check for permissions selected
            if (permissions[p] == true) {
                if (count > 0) {
                    queryStart = queryStart + " AND "
                            + permissionType[p] + " = " + permissions[p];
                } else {
                    queryStart = queryStart + " WHERE "
                            + permissionType[p] + " = " + permissions[p];
                    count += 1;
                }
            }
        }
        // create final query
        final String query = queryStart;
        // get results set and set user models
        ResultSet rs = resultSet(query);
        setUserModels(rs);
    }// end selectUsersByPermissions method 

    /**
     * selectUsersByType method for querying database for user profiles with
     * given permissions
     * @param permissions Boolean user permissions
     */
    public void selectUsersByType(boolean[] permissions) {
        setModel("SELECT "+ "USERID,USERTYPE,FIRSTNAME,LASTNAME,DEPARTMENT,"+
                "ADMINPERMISSION,MANAGERPERMISSION,ENGINEERPERMISSION,TECHPERMISSION,ANALYSTPERMISSION"+
                " FROM APP.LOGIN " 
                + "WHERE AdminPermission = " + permissions[0]
                + " AND ManagerPermission = " + permissions[1]
                + " AND EngineerPermission = " + permissions[2]
                + " AND AnalystPermission = " + permissions[3]
                + " AND TechPermission = " + permissions[4] + " ");
    }// end selectUsersByType method 
    
    /**
     * setPermissionQuery method for building permission query for given permissions
     * @param permissions   Boolean permissions array
     * @param queryStart    String start of query
     * @return combined SQL query 
     */
    public String setPermissionQuery(boolean[] permissions, String queryStart) {
        String[] permissionType = {
            "AdminPermission", "ManagerPermission", "EngineerPermission",
            "AnalystPermission", "TechPermission"};
        // generate query by permissions
        int count = 0;
        for (int p = 0; p < permissions.length; p++) 
        {
            // check for permissions selected
            if (permissions[p] == true) {
                if (count > 0) {
                    queryStart = queryStart + " AND "
                            + permissionType[p] + " = " + permissions[p];
                } else {
                    queryStart = queryStart + " WHERE " 
                            + permissionType[p] + " = " + permissions[p];
                    count += 1;
                }
            }
        }      
        // create and return final query
        String query = queryStart;
        return query;
    }// end setPermissionQuery method 
    
    /**
     * selectUsersByFilter method for displaying users accounts based on selected filters
     * @param permissions   Boolean array of permissions
     * @param type          account type
     * @param department    account department
     * @param dateRange     date range for account creation
     * @param order         sort order
     * @return result set
     */
    public ResultSet selectUsersByFilter(boolean[] permissions, String type, String department, String dateRange, String[] order)
    {
        boolean hasPermissions = false;
        boolean hasType = false;
        
        String[] pt = {
            "AdminPermission", "ManagerPermission", "EngineerPermission",
            "AnalystPermission", "TechPermission"};

        // start of query
        String queryBuilder = "SELECT USERID,DATECREATED,USERNAME,TYPE,DEPARTMENT,ADMINPERMISSION,MANAGERPERMISSION,ENGINEERPERMISSION,ANALYSTPERMISSION,TECHPERMISSION FROM LOGIN ";
               
        //String[] types = {"Admin","Manager","Engineer", "Analyst", "Tech"};
        //String prms = "Permission ";
        
        boolean[] p = permissions;
        // check if permission filter set
        if (p[0] == true || p[1] == true || p[2] == true || p[3] == true || p[4] == true) {
            queryBuilder = setPermissionQuery(permissions, queryBuilder);
            hasPermissions = true;
        }
        
        // check for bad type input
        if (type != null && !type.contains("Filter By Type...")) {
            if (hasPermissions == true) {
                queryBuilder = queryBuilder + " AND " + " USERTYPE = '" + encrypt(type) + "'";
            } else {
                queryBuilder = queryBuilder + " WHERE " + " USERTYPE = '" + encrypt(type) + "'";
            }
            hasType = true;
        }
        // check for bad department input
        if (department != null && !department.contains("Filter By Department...")) {
            if (hasPermissions == true || hasType == true) {
                queryBuilder = queryBuilder + " AND " + " DEPARTMENT = '" + encrypt(department) + "'";
            } else {
                queryBuilder = queryBuilder + " WHERE " + " DEPARTMENT = '" + encrypt(department) + "'";
            }
        }
        // commbine queries
        final String query = queryBuilder;
        // execute select query
        setModel(query);
        // get results set and set user models
        ResultSet rs = resultSet(query);
        setUserModels(rs);
        return rs;
    }// end selectUsersByFilter method 
    
    /**
     * generateDefaultPassID method queries database for default password
     */
    public void generateDefaultPassID()
    {
        ResultSet rs = resultSet("SELECT PASSWORD FROM LOGIN WHERE USERID = '"+encrypt("default")+"' ");
        try{
            while (rs.next()){
                this.defaultPassID = decrypt(rs.getString("PASSWORD"));
            }
        } catch (SQLException ex) {}// exception thrown
    }// end generateDefaultPassID method 
    
    /**
     * setDefaultPassID method for setting a new default password
     * @param passID new default password
     */
    public void setDefaultPassID(String passID)
    {
        this.defaultPassID = passID;
        insert("UPDATE LOGIN SET PASSWORD = '" + encrypt(passID) + 
                "' WHERE USERID = '"+encrypt("default")+"' ");
    }// end setDefaultPassID method 
    
    /**
     * createUserAccount method for inserting new user account into database
     * @param date  date account was created
     * @param userID    username
     * @param passID    password
     * @param type      account type
     * @param name      user's name
     * @param firstName user's first name
     * @param lastName  user's last name
     * @param department user's department
     * @param isAdmin   has admin permissions
     * @param isManager has manager permissions
     * @param isEngineer has engineer permissions
     * @param isAnalyst has analyst permissions
     * @param isTech    has tech support permissions
     * @return Boolean account created
     */
    public boolean createUserAccount(String date, String userID, String passID, String type, String name, String firstName, String lastName, String department, boolean isAdmin, boolean isManager, boolean isEngineer, boolean isAnalyst, boolean isTech)
    {        
        insert("INSERT INTO LOGIN(DATECREATED,USERID,PASSWORD,USERTYPE,USERNAME,FIRSTNAME,LASTNAME,DEPARTMENT,ADMINPERMISSION,MANAGERPERMISSION,ENGINEERPERMISSION,ANALYSTPERMISSION,TECHPERMISSION) VALUES ('"+
                encrypt(date) + "','" + encrypt(userID) + "','" + 
                encrypt(passID) + "','" + encrypt(type) + "','" + 
                encrypt(name) + "','" + encrypt(firstName) + "','" + 
                encrypt(lastName) + "','" + encrypt(department) + "'," + 
                isAdmin + "," + isManager + "," + 
                isEngineer + "," + isAnalyst + "," + isTech + ") ");
        return true;
    }// end createUserAccount method 
    
    /**
     * updateUserAccount method updated user account in database
     * @param date  date account was created
     * @param userID    username
     * @param passID    password
     * @param type      account type
     * @param name      user's name
     * @param firstName user's first name
     * @param lastName  user's last name
     * @param department user's department
     * @param isAdmin   has admin permissions
     * @param isManager has manager permissions
     * @param isEngineer has engineer permissions
     * @param isAnalyst has analyst permissions
     * @param isTech    has tech support permissions
     * @return Boolean account updated
     */
    public boolean updateUserAccount(String date, String userID, String passID, String type, String name, String firstName, String lastName, String department, boolean isAdmin, boolean isManager, boolean isEngineer, boolean isAnalyst, boolean isTech)
    {
        execute("UPDATE LOGIN SET " + 
                "DATECREATED = '" + encrypt(date) + "'," + 
                "PASSWORD = '" + encrypt(passID) + "'," +
                "USERTYPE = '" + encrypt(type) + "'," + 
                "USERNAME = '" + encrypt(name) + "'," + 
                "FIRSTNAME = '" + encrypt(firstName) + "'," + 
                "LASTNAME = '" + encrypt(lastName) + "'," + 
                "DEPARTMENT = '" + encrypt(department) + "'," + 
                "ADMINPERMISSION = " + isAdmin + "," + 
                "MANAGERPERMISSION = " + isManager + "," + 
                "ENGINEERPERMISSION = " + isEngineer + "," + 
                "ANALYSTPERMISSION = " + isAnalyst + "," + 
                "TECHPERMISSION = " + isTech +
                " WHERE USERID ='"+encrypt(userID)+"'");
        return true;
    }// end updateUserAccount method 
    
    /**
     * deleteUserAccount method removes user account from database
     * @param userID user being deleted
     * @return Boolean query executed
     */
    public boolean deleteUserAccount(String userID)
    {
        execute("DELETE FROM LOGIN WHERE " +  
                "USERID = '" + encrypt(userID) + "'");
        return true;
    }// end deleteUserAccount method 
    
    /**
     * selectAccountSettings method queries database and returns time out settings
     * @return return time out time 
     */
    public ResultSet selectAccountSettings()
    {
        // query for and return time out results
        ResultSet rs = resultSet("SELECT TIMEOUT FROM APP.APPSETTINGS ");
        try {
        while (rs.next()) {
            // set time out values
            String timeout = rs.getString("TIMEOUT").toString();
            this.timeOut = timeout;
            
            // split timeout into hours minutes and seconds
            String[] time = timeout.split(":");
            this.hours = time[0];
            this.minutes = time[1];
            this.seconds = time[2];
        }
        } catch (SQLException e){} //exception thrown
        return rs;
    }// end selectAccountSettings method 
    
    /**
     * updateAppSettings method updates timeout settings
     * @param hours timeout hours
     * @param minutes timeout minutes
     * @param seconds timeout seconds
     */
    public void updateAppSettings(String hours, String minutes, String seconds)
    {        
        execute("UPDATE APPSETTINGS SET "+
                "TIMEOUT = '"+hours+":"+minutes+":"+seconds+"' ");
    }// end updateAppSettings method 
    
    /**
     * getNewUserID method returns new user ID
     * @return user ID
     */
    public String getNewUserID() {
        return newUserID;
    }// end getNewUserID method 

    /**
     * getUserID method returns user ID
     * @return username
     */
    public String getUserID() {
        return userID;
    }// end getUserID method 

    /**
     * getPassID method returns user password
     * @return password
     */
    public String getPassID() {
        return passID;
    }// end getPassID method 

    /**
     * getType method returns user type
     * @return user type
     */
    public String getType() {
        return type;
    }// end getType method 

    /**
     * getName method returns user name
     * @return full name
     */
    public String getName() {
        return name;
    }// end getName method 

    /**
     * getFirstName method returns user's first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }// end getFirstName method 

    /**
     * getLastName method returns user's last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }// end getLastName method 

    /**
     * getDepartment method returns account department
     * @return department
     */
    public String getDepartment() {
        return department;
    }// end getDepartment method 

    /**
     * getDateCreated method returns date account was created
     * @return date created
     */
    public String getDateCreated() {
        return dateCreated;
    }// end getDateCreated method 

    /**
     * getDefaultPassID method returns default password
     * @return default password
     */
    public String getDefaultPassID() {
        return defaultPassID;
    }// end getDefaultPassID method 

    /**
     * getHours method returns timeout hours
     * @return hours
     */
    public String getHours() {
        return hours;
    }// end getHours method 

    /**
     * getMinutes method returns timeout minutes
     * @return minutes
     */
    public String getMinutes() {
        return minutes;
    }// end getMinutes method 

    /**
     * getSeconds method returns timeout seconds
     * @return seconds
     */
    public String getSeconds() {
        return seconds;
    }// end getSeconds method 

    /**
     * getIsAdmin method returns admin permission
     * @return Boolean is admin
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }// end getIsAdmin method 

    /**
     * getIsManager method return manager permission
     * @return Boolean is manager
     */
    public boolean getIsManager() {
        return isManager;
    }// end getIsManager method 

    /**
     * getIsEngineer method returns engineering permission
     * @return Boolean is engineer
     */
    public boolean getIsEngineer() {
        return isEngineer;
    }// end getIsEngineer method 

    /**
     * getIsAnalyst method returns analyst permission
     * @return Boolean is analyst
     */
    public boolean getIsAnalyst() {
        return isAnalyst;
    }// end getIsAnalyst method 

    /**
     * getIsTech method returns tech permission
     * @return Boolean is tech
     */
    public boolean getIsTech() {
        return isTech;
    }// end getIsTech method 

    /**
     * getUserProfile method returns the user profile result set
     * @return user profile information
     */
    public ResultSet getUserProfile() {
        return userProfile;
    }// end getUserProfile method 

    /**
     * getAccountsModel method returns the single account table model
     * @return accounts table model
     */
    public DefaultTableModel getAccountsModel() {
        return accountsModel;
    }// end getAccountsModel method 

    /**
     * getAccountModel method returns the total accounts table model
     * @return account table model
     */
    public DefaultTableModel getAccountModel() {
        return accountModel;
    }// end getAccountModel method
    
}// end QueryAccount class
