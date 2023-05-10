package Query;

import Chronology.Chronology;
import DB.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * QueryTicketLog.java - class for querying total counts for the database tables
 * CSIS 643 - D01
 * @author Kore Woody
 */
public class QueryTicketLog extends Query
{
    DefaultTableModel logModel, logTotal,logToday, logWeek,logMonth,logYear;
    
    /**
     * selectTicketLog method for creating the queries for ticket logs 
     * @param userID
     * @param department
     * @param isManager
     */
    public void selectTicketLog(String userID, String department, boolean isManager)
    {
        // instantiate new LocalDate object
        LocalDate today = LocalDate.now();
        // get date a week, month, year ago
        String weekAgo = today.plusDays(-7).toString();
        String monthAgo = today.plusDays(-30).toString();
        String yearAgo = today.plusDays(-365).toString();
        
        // instantiate new Chronology object
        Chronology c = new Chronology();
        String currentDate = c.getDate();// get current time
        
        // start of query
        String queryStart = "SELECT COUNT(*) FROM TICKET WHERE ";
        
        // check account permissions
        if (isManager==false)
        {
            //change start of query
            String queryByUser = " CREATEDBY = '" + encrypt(userID) + "' OR " + 
                    "EDITBY = '" + encrypt(userID) + "' OR " + 
                    "CLOSEBY = '" + encrypt(userID) + "' AND ";
            queryStart = queryStart + queryByUser;
        }
        // create queries for all time
        String queryTotalAssigned = queryStart + 
                " AssignedTo = '" + encrypt(department) + "' ";
        String queryTotalOpen = queryStart + 
                " AssignedTo = '" + encrypt(department) + "' AND STATUS = '" + encrypt("Open") + "' ";
        String queryTotalClose = queryStart + 
                " AssignedTo = '"+ encrypt(department) + "' AND STATUS = '"+encrypt("Closed")+"' ";

        // create queries for day
        String queryTodayAssigned = queryStart + 
                "AssignedTo = '" + encrypt(department) + "' ";
        
        String queryTodayOpen = queryStart + 
                "AssignedTo = '" + encrypt(department) + "' AND " +
                "AssignedOn = '" + encrypt(currentDate) + "' AND " + 
                "STATUS = '" + encrypt("Open") + "' ";
        
        String queryTodayClose = queryStart + 
                "AssignedTo = '" + encrypt(department) + "' AND " + 
                "AssignedOn = '" + encrypt(currentDate) + "' AND " + 
                "STATUS = '"+encrypt("Closed")+"' ";
        
        // create queries for week
        String queryWeekAssigned = queryStart + 
                "AssignedTo = '" + encrypt(department) + "' AND " + 
                " AssignedOn BETWEEN '" + encrypt(weekAgo) + "' AND '" + encrypt(currentDate) + "'";
        String queryWeekOpen = queryStart + 
                "AssignedTo = '" + encrypt(department) + "' AND " + 
                "STATUS = '" + encrypt("Open") + "' AND " + 
                "AssignedOn BETWEEN '" + encrypt(weekAgo) + "' AND '" + encrypt(currentDate) + "'";
        String queryWeekClose = queryStart + 
                "AssignedTo = '" + encrypt(department) + "' AND " + 
                "STATUS = '"+encrypt("Closed")+"' AND " + 
                "AssignedOn BETWEEN '" + encrypt(weekAgo) + "' AND '" + encrypt(currentDate) + "'";
        
        // create queries for month
        String queryMonthAssigned = queryStart + 
                "AssignedTo = '"+encrypt(department) + "' AND "+
                "AssignedOn BETWEEN '" + encrypt(monthAgo) + "' AND '" + encrypt(currentDate) + "'";
        String queryMonthOpen = queryStart + 
                "AssignedTo = '" + encrypt(department) + "' AND " + 
                "STATUS = '" + encrypt("Open") + "' AND " +
                "AssignedOn BETWEEN '" + encrypt(monthAgo) + "' AND '" + encrypt(currentDate) + "'";
        String queryMonthClose = queryStart + 
                "AssignedTo = '" + encrypt(department) + "' AND " + 
                "STATUS = '"+encrypt("Closed")+"' AND " +
                "AssignedOn BETWEEN '" + encrypt(monthAgo) + "' AND '" + encrypt(currentDate) + "'";
        
        // create queries for year
        String queryYearAssigned = queryStart + 
                "AssignedTo = '"+encrypt(department) + "' AND "+
                "AssignedOn BETWEEN '" + encrypt(yearAgo) + "' AND '" + encrypt(currentDate) + "'";
        String queryYearOpen = queryStart + 
                "AssignedTo = '" + encrypt(department) + "' AND " + 
                "STATUS = '" + encrypt("Open") + "' AND " +
                "AssignedOn BETWEEN '" + encrypt(yearAgo) + "' AND '" + encrypt(currentDate) + "'";
        String queryYearClose = queryStart + 
                "AssignedTo = '" + encrypt(department) + "' AND " + 
                "STATUS = '"+encrypt("Closed")+"' AND " +
                "AssignedOn BETWEEN '" + encrypt(yearAgo) + "' AND '" + encrypt(currentDate) + "'";
        
        // call individual methods to set the table model for the given queries
        setTicketLogModelTotal(queryTotalAssigned,queryTotalOpen, queryTotalClose);
        setTicketLogModelToday(queryTodayAssigned,queryTodayOpen, queryTodayClose);
        setTicketLogModelWeek(queryWeekAssigned,queryWeekOpen, queryWeekClose);
        setTicketLogModelMonth(queryMonthAssigned,queryMonthOpen,  queryMonthClose);
        setTicketLogModelYear(queryYearAssigned,queryYearOpen,  queryYearClose);
    }// end selectTicketLog method
    
    /**
     * setTicketLogModelTotal method for creating the total ticket log table
     * model
     * @param queryAssigned
     * @param queryOpened
     * @param queryClosed
     */
    public void setTicketLogModelTotal(String queryAssigned, String queryOpened, String queryClosed)
    {
        // instantiate new database object and execute query
        Database db = new Database(queryAssigned);
        ResultSet rsTotalAssigned = db.select();// get results
        // instantiate new database object and execute open query
        db = new Database(queryOpened);
        ResultSet rsTotalOpen = db.select();// get results
        // instantiate new database object and execute closed query
        db = new Database(queryClosed);
        ResultSet rsTotalClose = db.select();// get results

        int intTotalAssigned = 0;
        int intTotalOpen = 0;
        int intTotalEdit = 0;
        int intTotalClose = 0;

        try {
            // append column headers
            logTotal = new DefaultTableModel(
                    new String[]{
                        "Total", "#"}, 0);

            // get total counts for tickets
            while (rsTotalAssigned.next()) {
                intTotalAssigned = rsTotalAssigned.getInt(1);
            }
            while (rsTotalOpen.next()) {
                intTotalOpen = rsTotalOpen.getInt(1);
            }
            while (rsTotalClose.next()) {
                intTotalClose = rsTotalClose.getInt(1);
            }
            // append rows to model
            logTotal.addRow(new Object[]{"Assigned", String.valueOf(intTotalAssigned)});
            logTotal.addRow(new Object[]{"Open", String.valueOf(intTotalOpen)});
            logTotal.addRow(new Object[]{"Closed", String.valueOf(intTotalClose)});
        } catch (SQLException ex) {}//catch exception
    }// end setTicketLogModelTotal method
    
    /**
     * setTicketLogModelToday method
     * @param queryAssigned
     * @param queryOpened
     * @param queryClosed
     */
    public void setTicketLogModelToday(String queryAssigned, String queryOpened,  String queryClosed)
    {
        // TODAY
        Database db = new Database(queryAssigned);
        ResultSet rsTodayAssigned = db.select();
        db = new Database(queryOpened);
        ResultSet rsTodayOpen = db.select();
        db = new Database(queryClosed);
        ResultSet rsTodayClose = db.select();

        int intTodayAssigned = 0;
        int intTodayOpen = 0;
        int intTodayEdit = 0;
        int intTodayClose = 0;

        try {
            // append column headers
            logToday = new DefaultTableModel(
                    new String[]{
                        "Today", "#"}, 0);

            // get total counts for tickets
            while (rsTodayAssigned.next()) {
                intTodayAssigned = rsTodayAssigned.getInt(1);
            }
            while (rsTodayOpen.next()) {
                intTodayOpen = rsTodayOpen.getInt(1);
            }
            while (rsTodayClose.next()) {
                intTodayClose = rsTodayClose.getInt(1);
            }

            // append rows to model
            logToday.addRow(new Object[]{"Assigned", String.valueOf(intTodayAssigned)});
            logToday.addRow(new Object[]{"Open", String.valueOf(intTodayOpen)});
            logToday.addRow(new Object[]{"Closed", String.valueOf(intTodayClose)});
        } catch (SQLException ex) {}//catch exception

    }// end setTicketLogModelToday method
    
    /**
     * setTicketLogModelWeek method
     * @param queryAssigned
     * @param queryOpened
     * @param queryClosed
     */
    public void setTicketLogModelWeek(String queryAssigned, String queryOpened,  String queryClosed)
    {
        //WEEK
        Database db = new Database(queryAssigned);
        ResultSet rsWeekAssigned = db.select();//
        db = new Database(queryOpened);
        ResultSet rsWeekOpen = db.select();
        db = new Database(queryClosed);
        ResultSet rsWeekClose = db.select();

        int intWeekAssigned = 0;
        int intWeekOpen = 0;
        int intWeekEdit = 0;
        int intWeekClose = 0;

        try {
            // append column headers
            logWeek = new DefaultTableModel(
                    new String[]{
                        "Week", "#"}, 0);

            // get total counts for tickets
            while (rsWeekAssigned.next()) {
                intWeekAssigned = rsWeekAssigned.getInt(1);
            }
            while (rsWeekOpen.next()) {
                intWeekOpen = rsWeekOpen.getInt(1);
            }
            while (rsWeekClose.next()) {
                intWeekClose = rsWeekClose.getInt(1);
            }
            // append rows to model
            logWeek.addRow(new Object[]{"Assigned", String.valueOf(intWeekAssigned)});
            logWeek.addRow(new Object[]{"Open", String.valueOf(intWeekOpen)});
            logWeek.addRow(new Object[]{"Closed", String.valueOf(intWeekClose)});
        } catch (SQLException ex) {}// catch exception
    }// end setTicketLogModelWeek method
    
    /**
     * setTicketLogModelMonth method
     * @param queryAssigned
     * @param queryOpened
     * @param queryClosed
     */
    public void setTicketLogModelMonth(String queryAssigned, String queryOpened, String queryClosed)
    {
        //MONTH
        Database db = new Database(queryAssigned);
        ResultSet rsMonthAssigned = db.select();
        db = new Database(queryOpened);
        ResultSet rsMonthOpen = db.select();
        db = new Database(queryClosed);
        ResultSet rsMonthClose = db.select();

        int intMonthAssigned = 0;
        int intMonthOpen = 0;
        int intMonthEdit = 0;
        int intMonthClose = 0;

        try {
            // append column headers
            logMonth = new DefaultTableModel(
                    new String[]{
                        "Month", "#"}, 0);

            // get total counts for tickets
            while (rsMonthAssigned.next()) {
                intMonthAssigned = rsMonthAssigned.getInt(1);
            }
            while (rsMonthOpen.next()) {
                intMonthOpen = rsMonthOpen.getInt(1);
            }
            while (rsMonthClose.next()) {
                intMonthClose = rsMonthClose.getInt(1);
            }
            // append rows to model
            logMonth.addRow(new Object[]{"Assigned", String.valueOf(intMonthAssigned)});
            logMonth.addRow(new Object[]{"Open", String.valueOf(intMonthOpen)});
            logMonth.addRow(new Object[]{"Closed", String.valueOf(intMonthClose)});
        } catch (SQLException ex) {}//catch exception
    }// end setTicketLogModelMonth method
    
    /**
     * setTicketLogModelYear method
     * @param queryAssigned
     * @param queryOpened
     * @param queryClosed
     */
    public void setTicketLogModelYear(String queryAssigned, String queryOpened, String queryClosed)
    {
        //YEAR
        Database db = new Database(queryAssigned);
        ResultSet rsYearAssigned = db.select();
        db = new Database(queryOpened);
        ResultSet rsYearOpen = db.select();
        db = new Database(queryClosed);
        ResultSet rsYearClose = db.select();

        int intYearAssigned = 0;
        int intYearOpen = 0;
        int intYearEdit = 0;
        int intYearClose = 0;

        try {
            // append column headers
            logYear = new DefaultTableModel(
                    new String[]{
                        "Year", "#"}, 0);
            // get total counts for tickets
            while (rsYearAssigned.next()) {
                intYearAssigned = rsYearAssigned.getInt(1);
            }
            while (rsYearOpen.next()) {
                intYearOpen = rsYearOpen.getInt(1);
            }
            while (rsYearClose.next()) {
                intYearClose = rsYearClose.getInt(1);
            }
            // append rows to model
            logYear.addRow(new Object[]{"Assigned", String.valueOf(intYearAssigned)});
            logYear.addRow(new Object[]{"Open", String.valueOf(intYearOpen)});
            logYear.addRow(new Object[]{"Closed", String.valueOf(intYearClose)});
        } catch (SQLException ex) {}//catch exception
    }// end setTicketLogModelYear method
    
    /**
     * getTicketLogModelTotal method returns model for total ticket
     *
     * @return total ticket model
     */
    public DefaultTableModel getTicketLogModelTotal() {
        return logTotal;
    }// end getTicketLogModelTotal method

    /**
     * getTicketLogModelToday method returns model for daily tickets
     *
     * @return day ticket log table model
     */
    public DefaultTableModel getTicketLogModelToday() {
        return logToday;
    }// end getTicketLogModelToday method

    /**
     * getTicketLogModelWeek method returns model for weekly tickets
     *
     * @return week ticket log table model
     */
    public DefaultTableModel getTicketLogModelWeek() {
        return logWeek;
    }// end getTicketLogModelWeek method

    /**
     * getTicketLogModelMonth method returns monthly tickets
     *
     * @return month ticket log table model
     */
    public DefaultTableModel getTicketLogModelMonth() {
        return logMonth;
    }// end getTicketLogModelMonth method

    /**
     * getTicketLogModelYear method returns yearly tickets
     *
     * @return year ticket log table model
     */
    public DefaultTableModel getTicketLogModelYear() {
        return logYear;
    }// end getTicketLogModelYear method

    /**
     * getTicketLogModel method returns default ticket log model
     *
     * @return log table model
     */
    public DefaultTableModel getTicketLogModel() {
        return logModel;
    }// end getTicketLogModel method
    
}// end class QueryTicketLog
