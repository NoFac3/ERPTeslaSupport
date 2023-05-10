package Query;

import DB.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 * QueryTicket.java - class for querying database for support tickets
 * CSIS 643 - D01
 * @author Kore Woody
 */
public class QueryTicket extends Query{

    DefaultTableModel model, ticketTable, customerTable, statusTable, vehicleTable;
    
    String firstName, lastName, customerName,empName, empID;
    String ticketNumber, assignedTo,status, priority, title, description;
    String customerID, phone, email,reply, ticket, vehicle, vin;
    String openOn, editOn, closedOn,openBy, editBy, closeBy,ticketLock;
    String[] vehicles, tickets;
    boolean multipleVehicles, multipleTickets;
    private String vehicleModel;
    private String vehicleYear;
    
    /**
     * setTicketNumber method calls count method to set the new ticket number
     */
    public void setTicketNumber()
    {
        String table = "Ticket";
        count(table);
    }// end setTicketNumber method
    
    /**
     * selectTickets method for querying all support tickets in database
     */
    public void selectTickets()
    {
        // create select query
        setModel("SELECT TICKETNUMBER,STATUS,PRIORITY,"+
                "ASSIGNEDTO,ASSIGNEDON,OPENON,EDITON,CLOSEDON,TITLE FROM APP.Ticket ");
    }// end selectTickets method
    
    /**
     * selectTickets for querying all tickets by a user
     * @param userID user who opened, edited, or closed a ticket
     */
    public void selectTickets(String userID)
    {
        setModel("SELECT "+
                "TICKETNUMBER,STATUS,PRIORITY,ASSIGNEDTO,ASSIGNEDON,"+
                "OPENON,EDITON,CLOSEDON,TITLE FROM APP.Ticket "+
                "WHERE OpenBy = '" + encrypt(userID) + "' " +
                " OR EditBy = '" + encrypt(userID) + "'" + 
                " OR CloseBy = '" + encrypt(userID) + "'");
    }//end selectTickets method
    

    /**
     * selectTicket method for querying and setting results for a specific ticket
     * @param TicketNumber number of ticket to search for
     */
    public void selectTicket(String TicketNumber) {
        
        // query ticket number
        setModel("SELECT TICKETNUMBER,CUSTOMERNAME,STATUS,PRIORITY,ASSIGNEDTO FROM Ticket WHERE TICKETNUMBER = " + TicketNumber + "");
        // get ticket map
        Map<Integer,Map<Object,Object>> map = getMap();
        // check for null results
        if (map!=null)
        {
            //get ticket status, priority, and assignment
            this.status = map.get(0).get("STATUS").toString();
            this.priority = map.get(0).get("PRIORITY").toString();
            this.assignedTo = map.get(0).get("ASSIGNEDTO").toString();
        }
        //Instantiate new database object and query for ticket
        Database db = new Database("SELECT * FROM Ticket WHERE TICKETNUMBER = " + Integer.parseInt(TicketNumber) + "");
        ResultSet rs = db.select();
        try {
            // set column headers
            vehicleTable = new DefaultTableModel(
                    new String[]{
                        "Year", "Model","VIN"}, 0);
            
            statusTable = new DefaultTableModel(
                    new String[]{
                        "Status Log", "By", "Data"}, 0);
            
            while (rs.next()) 
            {         
                // get ticket data
                this.ticketNumber = decrypt(rs.getString("TicketNumber"));
                this.firstName = decrypt(rs.getString("FirstName"));
                this.lastName = decrypt(rs.getString("LastName"));
                this.status = decrypt(rs.getString("Status"));
                this.priority = decrypt(rs.getString("Priority"));
                this.assignedTo = decrypt(rs.getString("AssignedTo"));
                this.openOn = decrypt(rs.getString("OpenOn"));
                this.editOn = decrypt(rs.getString("EditOn"));
                this.closedOn = decrypt(rs.getString("ClosedOn"));
                this.openBy = decrypt(rs.getString("OpenBy"));
                this.editBy = decrypt(rs.getString("EditBy"));
                this.closeBy = decrypt(rs.getString("CloseBy"));
                this.title = decrypt(rs.getString("title"));
                this.description = decrypt(rs.getString("Description"));
                this.customerID = decrypt(rs.getString("CustomerID"));
                this.vin = decrypt(rs.getString("VIN"));                
                this.ticketLock = decrypt(rs.getString("TICKETLOCK"));
                
                // add rows to status model
                this.statusTable.addRow(new Object[]{"Opened", openBy, openOn});
                this.statusTable.addRow(new Object[]{"Edited", editBy, editOn});
                this.statusTable.addRow(new Object[]{"Closed", closeBy, closedOn});
            }
            if (this.customerID!=null || !this.customerID.matches(""))
            {
                //Instantiate new database object and query for customer
                db = new Database("SELECT * FROM CUSTOMERS WHERE CUSTOMERID = '" + encrypt(this.customerID) + "' ");
                rs = db.select();
                // check for null values
                if (rs!=null)
                {
                    while(rs.next())
                    {
                        // get customer data
                        this.phone = decrypt(rs.getString("PHONE"));
                        this.email = decrypt(rs.getString("EMAIL"));
                        this.customerName = decrypt(rs.getString("CUSTOMERNAME"));
                        this.vehicles = decrypt(rs.getString("VEHICLES")).split(":");
                        this.tickets = decrypt(rs.getString("TICKETS")).split(":");
                    }
                }
            }
            // check for null pin
            if (vin!=null || !vin.matches(""))
            {
                //Instantiate new database object and query for customer vehicle
                db = new Database("SELECT * FROM CUSTOMERVEHICLES WHERE VIN = '" + encrypt(vin) + "'");
                rs = db.select();
                // check for null results
                if (rs!=null)
                {
                    while(rs.next())
                    {
                        // get vehicle data
                        String year = rs.getString("VYEAR");
                        String vmodel = decrypt(rs.getString("MODEL"));
                        this.vin = decrypt(rs.getString("VIN"));
                        // append to vehicle table model
                        vehicleTable.addRow(new Object[]{year, vmodel, vin});
                    }
                }
            }
        } catch (SQLException ex) {}// catch exception
    }// end selectTicket method
    
    /**
     * selectCustomer method for querying customer and vehicle information then 
     * setting the table results for both
     * @param customerID 
     */
    public void selectCustomer(String customerID)
    {
        // query and set table model for customer
        setModel("SELECT CustomerName,FirstName,LastName,Phone,Email "+
                "FROM APP.CUSTOMERS WHERE CustomerID = '" + encrypt(customerID.trim()) + "'");
        this.customerTable = getModel();
        // query and set table model for customer vehicle
        setModel("SELECT * FROM CUSTOMERVEHICLES WHERE CUSTOMERID = '" + encrypt(customerID.trim()) + "'");
        this.vehicleTable = getModel();
    }// end selectCustomer method
    
    /**
     * selectCustomer for searching for customers by given customer information
     * @param firstName first name of customer
     * @param lastName  last name of customer
     * @param phone     customer phone number  
     * @param email     customer email
     */
    public void selectCustomer(String firstName, String lastName, String phone, String email) {
        setModel("SELECT * FROM APP.CUSTOMERS WHERE "
                + "FIRSTNAME = '" + encrypt(firstName) + "' OR "
                + "LASTNAME = '" + encrypt(lastName) + "' OR "
                + "PHONE = '" + encrypt(phone) + "' OR "
                + "EMAIL = '" + encrypt(email) + "' ");
    }// end selectCustomer Method
    
    /**
     * selectVehicle method for searching for customer vehicles given the 
     * customer's ID
     * @param customerID 
     */
    public void selectVehicle(String customerID) {
        setModel("SELECT * FROM CUSTOMERVEHICLES "
                + "WHERE CUSTOMERID = '" + encrypt(customerID.trim()) + "'");
    }// end selectVehicle method

    /**
     * setTicketLock for updating the ticket lock and unlock status
     * @param ticketNumber
     * @param ticketLock
     * @return Boolean successful update/ number validation
     */
    public boolean setTicketLock(String ticketNumber, boolean ticketLock) {
        ticketNumber = ticketNumber.trim();
        boolean valid = true;

        // check for null values
        if (ticketNumber != null) {
            // convert ticket to string to int
            try {
                int ticket = Integer.parseInt(ticketNumber);
            } catch (NumberFormatException n) {// exception thrown
                valid = false;
            }

            // check for invalid ticket number input
            if (valid == true) 
            {
                // update ticket number lock status
                execute("UPDATE TICKET" + " SET TICKETLOCK = " + ticketLock
                        + " WHERE TICKETNUMBER = " + encrypt(ticketNumber) + "");
            }
        } 
        else // null ticket number 
        {
            valid = false;
        }
        return valid;
    } // end setTicketLock method
    
    /**
     * getTicketLock method converting the ticket lock string result to a Boolean
     * value
     * @return Boolean true/false
     */
    public boolean getTicketLock() {
        if (ticketLock.toLowerCase().matches("true")) {
            return true;
        } else {
            return false;
        }
    }// end getTicketLock method
    
    /**
     * insertTicket method for adding new ticket to database
     * @param userID
     * @param ticketNumber
     * @param customerName
     * @param firstName
     * @param lastName
     * @param status
     * @param priority
     * @param assignTo
     * @param openBy
     * @param editBy
     * @param closeBy
     * @param assignOn
     * @param openOn
     * @param editOn
     * @param closedOn
     * @param title
     * @param description
     * @param vin
     * @param ticketLock
     * @param year
     * @param model
     * @param phone
     * @param email 
     */
    public void insertTicket(
            String userID,String ticketNumber, 
            String customerName, String firstName, String lastName, 
            String status, String priority, 
            String assignTo, String openBy, String editBy, String closeBy,
            String assignOn,String openOn, String editOn, String closedOn,
            String title, String description, String vin, String ticketLock,
            String year,String model,
            String phone, String email)
    {
        String replyby = userID;
        // set and get customerID
        setCustomerID(firstName,lastName,phone,email);
        this.customerID = getCustomerID();
        
        // create insert queries
        String[] querys = {
            "INSERT INTO  Customers (CustomerID, FirstName, LastName, Age, Phone, Email) VALUES ('" + 
                encrypt(customerID) + "', '" + encrypt(firstName) + 
                "', '" + encrypt(lastName) + "','" + 
                encrypt(phone) + "', '" + encrypt(email) + "','" + 
                encrypt(vehicle) + "','" + encrypt(ticket) +  "')",
            "INSERT INTO Ticket "+
                "(FirstName,LastName,Status,Priority,AssignedTo,OpenOn,OpenBy,reply,title,Description,CustomerID) "+
                "VALUES ('"+ 
                encrypt(firstName) + "','" + lastName + status + "','" + priority + "','" + 
                openOn + "','" + openBy + "','" + title + "','" + description + "','" +
                customerID + "','" + replyby + "')"};
        // query execution loop
        for (String query : querys)
        {
            insert(query);
        }
    }// end insertTicket method
    
    /**
     * updateTicket method for updating ticket attributes
     * @param userID
     * @param ticketNumber
     * @param customerName
     * @param firstName
     * @param lastName
     * @param status
     * @param priority
     * @param assignTo
     * @param openBy
     * @param editBy
     * @param closeBy
     * @param assignOn
     * @param openOn
     * @param editOn
     * @param closedOn
     * @param title
     * @param description
     * @param vin
     * @param ticketLock
     * @param year
     * @param model
     * @param phone
     * @param email 
     */
    public void updateTicket( 
            String userID,String ticketNumber,
            String customerName, String firstName, String lastName, 
            String status, String priority, 
            String assignTo, String openBy, String editBy, String closeBy,
            String assignOn,String openOn, String editOn, String closedOn,
            String title, String description, String vin, String ticketLock,
            String year,String model,
            String phone, String email) 
    {
        // create query and det model
        setModel("SELECT * FROM CUSTOMERS ");
        // set and get  departments
        setDepartments(openBy,editBy,closeBy);
        String[] departments = getDepartments();
        // set and get customerID
        setCustomerID(firstName,lastName,phone,email);
        this.customerID = getCustomerID();
        
        //create and execute update query with current Date and Time
        execute("UPDATE Ticket SET "+
                "CustomerName = '" + encrypt(customerName) + "', "+
                "firstName = '" + encrypt(firstName) + "', "+
                "lastName = '" + encrypt(lastName) + "', "+
                "Status = '" + encrypt(status) + "', "+
                "Priority = '" + encrypt(priority) + "', "+
                "AssignedTo = '" + encrypt(assignTo) + "', "+
                "AssignedOn = '" + encrypt(assignOn) + "', "+
                "OpenOn = '" + encrypt(openOn) + "', "+
                "EditOn = '" + encrypt(editOn) + "', "+
                "ClosedOn = '" + encrypt(closedOn) + "', "+
                "OpenBy = '" + encrypt(openBy) + "', "+
                "EditBy = '" + encrypt(editBy) + "', "+      
                "OpenDpt = '" + departments[0] + "', "+
                "EditDpt = '" + departments[1] + "', "+
                "CloseDpt = '" + departments[2] + "', "+
                "Title = '" + encrypt(title) + "', "+
                "Description = '" + encrypt(description) + "', "+
                "CustomerID = '" + encrypt(customerID) + "', " +
                "VIN = '" + encrypt(vin) + "', " +
                "TicketLock = '" + encrypt(ticketLock) + "' " + 
                "WHERE TicketNumber = " + ticketNumber + "");
        
    }// end updateTicket method
    
    /**
     * deleteTicket method for deleting a ticket from the database
     * @param ticketNumber ticket being deleted
     */
    public void deleteTicket(String ticketNumber) 
    {
        // create and execute delete query
        execute("DELETE FROM Ticket where TicketNumber = " + ticketNumber + "");;
    }//end deleteTicket method
    
    /**
     * getTicketTable method returns ticket table model
     * @return ticket model
     */
    public DefaultTableModel getTicketTable() {
        return ticketTable;
    }// end getTicketTable method

    /**
     * getCustomerTable method returns customer table model
     * @return customer model
     */
    public DefaultTableModel getCustomerTable() {
        return customerTable;
    }// end getCustomerTable method

    /**
     * getVehicleTable method returns vehicle table model
     * @return vehicle model
     */
    public DefaultTableModel getVehicleTable() {
        return vehicleTable;
    }// end getVehicleTable method

    /**
     * getStatusTable method returns ticket status table
     * @return status table model
     */
    public DefaultTableModel getStatusTable() {
        return statusTable;
    }// end getStatusTable method

    /**
     * getPhone method returns customer phone number
     * @return phone number
     */
    public String getPhone() {
        return phone;
    }// end getPhone method

    /**
     * getEmail method returns customer email
     * @return  email
     */
    public String getEmail() {
        return email;
    }// end getEmail method

    /**
     * getVehicle method returns  customer vehicle
     * @return  vehicle VIN
     */
    public String getVehicle() {
        return vehicle;
    }// end getVehicle method

    /**
     * getTicket method returns ticket number
     * @return  ticket number
     */
    public String getTicket() {
        return ticket;
    }// end getTicket method

    /**
     * getVehicles method returns array of customer vehicle VINs
     * @return  array of VINs
     */
    public String[] getVehicles() {
        return vehicles;
    }// end getVehicles method

    /**
     * getTickets method returns customer tickets
     * @return  array of ticket numbers
     */
    public String[] getTickets() {
        return tickets;
    }// end getTickets method

    /**
     * getTicketNumber method returns the ticket number
     * @return ticket number
     */
    public String getTicketNumber() {
        return ticketNumber;
    }// end getTicketNumber method

    /**
     * getTicketTitle method returns ticket title/subject
     * @return  title
     */
    public String getTicketTitle() {
        return title;
    }// end getTicketTitle method
    
    /**
     * getCustomerID method returns customer ID
     * @return customer ID
     */
    public String getCustomerID() {
        return customerID;
    }// end getCustomerID method

    /**
     * getFirstName method returns customer's first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }// end getFirstName method

    /**
     * getLastName method returns customer's last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }// end getLastName method

    /**
     * getStatus method returns ticket status
     * @return status
     */
    public String getStatus() {
        return status;
    }// end getStatus method

    /**
     * getPriority method returns ticket priority
     * @return priority
     */
    public String getPriority() {
        return priority;
    }// end getPriority method

    /**
     * getAssignedTo method returns ticket assigned department
     * @return assignment
     */
    public String getAssignedTo() {
        return assignedTo;
    }// end getAssignedTo method

    /**
     * getDescription method returns ticket description
     * @return description
     */
    public String getDescription() {
        return description;
    }// end getDescription method

    /**
     * getVehicleYear method returns vehicle year
     * @return year
     */
    public String getVehicleYear() {
        return vehicleYear;
    }// end getVehicleYear

    /**
     * getVehicleModel method returns the name of the vehicle model
     * @return vehicle model
     */
    public String getVehicleModel() {
        return vehicleModel;
    }// end getVehicleModel method

    /**
     * getVIN method returns vehicle VIN
     * @return  VIN
     */
    public String getVIN() {
        return vin;
    }// end getVIN
    
}// end QueryTicket class
