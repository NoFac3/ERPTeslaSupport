package Query;

import Chronology.Chronology;
import DB.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * QueryMessages.java - class used by the message frames to query and update 
 * the messaging database table
 * CSIS 643 - D01
 * @author Kore Woody
 */
public class QueryMessages extends Query
{
    DefaultTableModel messageTable, messageModel, messagesModel, userModel;
    String received,sent,empName, empID, messageLock, textOnData;
    String messageType,sentID,receivedID,subject,message,responseType,reply,thread;
    String dateSent,timeSent, dateRead,timeRead, dateReceived,timeReceived;
    boolean isRead,isApproved;
    int messageKey;
    
    /**
     * selectMessages method for querying messages that are sent or received by 
     * the given user
     * @param userID username
     * @param type  Received/Sent
     */
    public void selectMessages(String userID, String type)
    {
        setModel("SELECT MESSAGEKEY,ISREAD,MESSAGETYPE,SENTID,RECEIVEDID,DATESENT,TIMESENT,SUBJECT FROM MESSAGES WHERE " + 
                type + "ID = '" + encrypt(userID) + "'");
    }// end selectMessages method
    
    /**
     * selectMessage method for querying messages database for message number and 
     * setting the results set and table model
     * @param messagekey message number
     */
    public void selectMessage(String messagekey)
    {
        // query messages for message number
        setModel("SELECT * FROM MESSAGES WHERE MESSAGEKEY = " + messagekey + " ");
        //get result set
        ResultSet rs = resultSet("SELECT * FROM MESSAGES WHERE MESSAGEKEY = " + messagekey + " ");

        try {
            //title of attributes
            while (rs.next()) 
            {
                // get and set decrypt message values
                this.messageType = decrypt(rs.getString("MESSAGETYPE"));
                this.isRead = rs.getBoolean("isRead");
                this.sentID = decrypt(rs.getString("SENTID"));
                this.receivedID = decrypt(rs.getString("RECEIVEDID"));
                this.subject = decrypt(rs.getString("SUBJECT"));
                this.dateSent = decrypt(rs.getString("DATESENT"));
                this.timeSent = decrypt(rs.getString("TIMESENT"));
                this.dateReceived = decrypt(rs.getString("DATERECEIVED"));
                this.timeReceived = decrypt(rs.getString("TIMERECEIVED"));
                this.dateRead = decrypt(rs.getString("DATEREAD"));
                this.timeRead = decrypt(rs.getString("TIMEREAD"));
                this.message = decrypt(rs.getString("MESSAGE"));
                this.responseType = decrypt(rs.getString("RESPONSETYPE"));
                this.isApproved = rs.getBoolean("APPROVED");
                this.reply = decrypt(rs.getString("REPLY"));
                this.thread = decrypt(rs.getString("THREAD"));
                
                // concatenate sent and recieved data and time
                this.sent = dateSent+" "+timeSent;
                this.received = dateReceived + " " +timeReceived;
                this.dateSent = sent;
                this.dateReceived = received;
                
                // instantiate new chronology obejct
                Chronology c = new Chronology();
                //get and set Date and time
                String date = c.getDate();
                String time = c.getTime();
                this.dateRead = date;
                this.timeRead = time;
                
                // set is read to true
                isRead = true;
                // convert message number to integer
                int messageID = Integer.parseInt(messagekey);
                // update database message
                updateMessage(messageID,messageType,sentID,receivedID,dateSent,timeSent,dateReceived,timeReceived,dateRead,timeRead,isRead,subject,message,responseType,isApproved,reply,thread);
            }
        } catch (SQLException ex){}// catch exception
    }// end selectMessage method
    
    /**
     * updateMessage method for updating database message attributes for the given
     * message number
     * @param messageID
     * @param type
     * @param sentID
     * @param receivedID
     * @param dateSent
     * @param timeSent
     * @param dateReceived
     * @param timeReceived
     * @param dateRead
     * @param timeRead
     * @param isRead
     * @param subject
     * @param message
     * @param responseType
     * @param approved
     * @param reply
     * @param thread
     */
    public void updateMessage(int messageID, String type, String sentID, String receivedID,String dateSent,String timeSent,String dateReceived,String timeReceived,String dateRead,String timeRead,boolean isRead,String subject,String message,String responseType,boolean approved,String reply,String thread)
    {
        String query = "UPDATE MESSAGES SET " + 
                "MESSAGETYPE = '" + encrypt(type) + "','" + 
                "SENTID = '" + encrypt(sentID) + "','" + 
                "RECEIVEDID = '" + encrypt(receivedID) + "','" + 
                "DATESENT = '" + encrypt(dateSent) + "','" + 
                "TIMESENT = '" + encrypt(timeSent) + "','" + 
                "DATERECEIVED = '" + encrypt(dateReceived) + "','" + 
                "TIMERECEIVED = '" + encrypt(timeReceived) + "','" + 
                "DATEREAD = '" + encrypt(dateRead) + "','" + 
                "TIMEREAD = '" + encrypt(timeRead) + "','" + 
                "ISREAD = " + isRead + ",'" + 
                "SUBJECT = '" + encrypt(subject) + "','" + 
                "MESSAGE = '" + encrypt(message) + "','" + 
                "RESPONSETYPE = '" + encrypt(responseType) + "','" + 
                "APPROVED = " + approved + ",'" + 
                "REPLY = '" + encrypt(reply) + "','" + 
                "THREAD = '" + encrypt(thread) + "','" + 
                " WHERE MESSAGEID = " + messageID + " ";
    }// end updateMessage method
    
    /**
     * selectUsers method for querying for userIDs based on a LIKE query for the 
     * given user name.
     * @param name name of user
     */
    public void selectUsers(String name)
    {
        // Instantiate new Database object and execute LIKE query for user name
        Database db = new Database("SELECT USERID,FIRSTNAME,LASTNAME,DEPARTMENT FROM APP.LOGIN WHERE " + 
                "UserName LIKE '" + encrypt(name) + "%' OR " +
                "UserName LIKE '" + encrypt(name) + "%' OR " +
                "UserName LIKE '%" + encrypt(name) + "'  ");
        ResultSet rs = db.select();

        try {
            //set model first row headers
            userModel = new DefaultTableModel(
                    new String[]{
                        "First Name", "Last Name", "Username","Department"}, 0);
            // 
            while (rs.next()) 
            {
                // decrypt and get user IS, name, and department
                String userID = decrypt(rs.getString("USERID"));
                String fName = decrypt(rs.getString("firstName"));
                String lName = decrypt(rs.getString("lastName"));
                String department = decrypt(rs.getString("Department"));
                
                // append to user table model
                userModel.addRow(new Object[]{
                            fName, lName, userID,department});   
            }
        } 
        catch (SQLException ex) {}//catch exception
    }// end selectUsers method
    
    /**
     * selectUsers method queries database and setting model for users ID,
     * name, and department
     */
    public void selectUsers()
    {
        setModel("SELECT USERID,FIRSTNAME,LASTNAME,DEPARTMENT FROM APP.LOGIN "); 
    } // end selectUsers method
    
    
    /**
     * insertResetRequest method for user who forget their password and/or username
     * @param userID    user sending the message
     * @param name      users name 
     * @param department users department 
     * @param type      type of message (Request)
     * @param approved  Boolean for manager approval of Account requests
     * @throws SQLException     for catching database errors and null results  
     */
    public void insertResetRequest(
            String userID, String name, String department, String type, 
            boolean approved) throws SQLException
    {
        // Instantiate new Database object
        Database db = new Database();
        String query = "";
        ResultSet rs = null;
        // get userID and Department
        // check for null isername
        if (userID==null)
        {
            // check for null department and user's name
            if (department!=null&&name!=null)
            {
                // create query, instantiate new Database object and execute query
                db = new Database("SELECT USERID, DEPARTMENT FROM APP.LOGIN WHERE DEPARTMENT = '"+encrypt(department)+
                        "' AND USERNAME = '"+encrypt(name)+"' ");// Instantiate new Database object
                rs = db.select();
                while(rs.next()){
                userID = decrypt(rs.getString("USERID"));
                }
            }
            else if (department==null&&name!=null)
            {
                // create query, instantiate new Database object and execute query
                query = "SELECT USERID, DEPARTMENT FROM APP.LOGIN WHERE USERNAME = '"+encrypt(name)+"' ";
                db = new Database(query);// Instantiate new Database object
                rs = db.select();
                while(rs.next()){
                userID = decrypt(rs.getString("USERID"));
                department = decrypt(rs.getString("DEPARTMENT"));
                }
            }
        }
        else {
            // create query for user's department
            query = "SELECT DEPARTMENT FROM APP.LOGIN WHERE USERID = '" + encrypt(userID) + "' ";
            db = new Database(query);// Instantiate new Database object
            // get results set
            rs = db.select();
            while (rs.next()) {
                // decrypt and get department
                department = decrypt(rs.getString("Department"));
            }
        }
        // check for null department and userID
        if(department!=null && userID!=null)
        {
            // create query to get user's Manager(s)
            query = "SELECT USERID FROM LOGIN WHERE DEPARTMENT = '"+encrypt(department)+"' AND "+
                    "ManagerPermission = true ";
            // Instantiate new Database object
            db = new Database(query);
            rs = db.select();

            // instantiate new list to store manager IDs
            List<String> managers = new ArrayList<String>();
            // append ids from result set
            while(rs.next())
            {
                managers.add(decrypt(rs.getString("USERID")));
            }
            
            // Instantiate new Chronology object
            Chronology c = new Chronology();
            //get Date and time
            String date = c.getDate();
            String time = c.getTime();
            String message = type + " Reset Request for...\nName: " + name + 
                    "\nDepartment: " + department;

            for (String manager: managers)
            {
                //execute insert query
                insert("INSERT INTO MESSAGES" + 
                        "(MESSAGETYPE,SENTID,RECEIVEDID,DATESENT,TIMESENT,SUBJECT,MESSAGE,APPROVED) "+
                        "VALUES ("+ encrypt("Request") + ",'" + encrypt(userID) + "','" + 
                        encrypt(manager) + "','" + encrypt(date) + "', '" 
                        + encrypt(time) + "', 'Reset Request" + 
                        "', '" + encrypt(message) + "', " + approved + ")");
            }
        }
    }// end insertResetRequest method
    
    /**
     * insertResetRequest method for adding new message/request to the Messages database
     * @param userID    user sending the message
     * @param receiver  user receiving the message
     * @param type      type of message (General/ Account Request)
     * @param subject   message subject
     * @param message   message body
     * @param approved  Boolean for manager approval of Account requests
     * @throws SQLException     for catching database errors and null results
     */
    public void insertMessage(
            String userID, String receiver, String type, 
            String subject, String message, String approved) throws SQLException
    {
        //Instantiate nw Chronology object
        Chronology c = new Chronology();        
        //get current Date and time
        String date = c.getDate();
        String time = c.getTime();
        
        // check messsage approval
        if (approved.matches("APPROVED"))// message approved
        {
            approved = "true";
        }
        else // message denied
        {
            approved = "false";
        }
        // create and execute insert query
        execute("INSERT INTO MESSAGES"
                + "(MESSAGETYPE,SENTID,RECEIVEDID,DATESENT,TIMESENT,SUBJECT,MESSAGE,APPROVED) "
                + "VALUES ('"+encrypt(type)+"','" + encrypt(userID) + "','"
                + encrypt(receiver) + "','" + encrypt(date) + "', '" + 
                encrypt(time) + "', '"+ encrypt(subject) + "', '" + 
                encrypt(message) + "', " + approved + ")");

    }// end insertResetRequest method

    /**
     * getMessageType method returns the type of message
     * @return message type (General/Request)
     */
    public String getMessageType() {
        return messageType;
    }// end getMessageType method
    
    /**
     * getSentID method returns the message sender username
     * @return sendID
     */
    public String getSentID() {
        return sentID;
    }// end getSentID method
    
    /**
     * getReceivedID method returns the message receiver id
     * @return receiverID
     */
    public String getReceivedID()
    {
        return receivedID;
    }// end getReceivedID method
    
    /**
     * getReply method method returns the message reply
     * @return reply
     */
    public String getReply()
    {
        return reply;
    }// end getReply method
    
    /**
     * getThread method method returns message thread
     * @return thread
     */
    public String getThread()
    {
        return thread;
    }// end getThread  method
    
    /**
     * getDateSent method returns date message was sent
     * @return 
     */
    public String getDateSent()
    {
        return dateSent;
    }// end getDateSent method
    
    /**
     * getDateReceived method returns the message received date
     * @return receive date
     */
    public String getDateReceived()
    {
        return dateReceived;
    }// end getDateReceived method
    
    /**
     * getDateRead method returns date message was read
     * @return date read
     */
    public String getDateRead()
    {
        return dateRead;
    }// end getDateRead method
    
    /**
     * getTimeRead method returns time message was read
     * @return time read
     */
    public String getTimeRead()
    {
        return timeRead;
    }// end getTimeRead method
    
    /**
     * getMessage method returns message text
     * @return message
     */
    public String getMessage()
    {
        return message;
    }// end getMessage method
    
    /**
     * getSubject  method returns message subject
     * @return subject
     */
    public String getSubject()
    {
        return subject;
    }// end getSubject method
    
    /**
     * getMessageKey method returns message number
     * @return message key number
     */
    public int getMessageKey()
    {
        return messageKey;
    }// end getMessageKey method
    
    /**
     * getUserModel method returns the user model
     * @return user model
     */
    public DefaultTableModel getUserModel()
    {
        return userModel;
    }// end getUserModel method
    
    /**
     * getMessages method returns table model for all messages
     * @return messages table model
     */
    public DefaultTableModel getMessages() {
        return messageTable;
    }// end getMessages method
    
    /**
     * getApproved method returns whether the request message has been approved
     * @return
     */
    public String getApproved()
    {
        if (isApproved==false)
        {
            return "Approved";
        }
        else
        {
            return "Denied";
        }
    }// end getApproved method

    /**
     * getRead method returns whether the message is read
     * @return Boolean isRead
     */
    public boolean getRead() {
        return isRead;
    }// end getRead method
    
}// end QueryMessages class
