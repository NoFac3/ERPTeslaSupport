package Chronology;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Chronology.java - class for determining the date and time for current and past
 * date
 * CSIS 643 - D01
 * @author Kore Woody
 */
public class Chronology 
{
    DateFormat fmtYMD = new SimpleDateFormat("yyyy-mm-dd");
    DateFormat fmtDMY = new SimpleDateFormat("dd-mm-yyyy");
    DateFormat fmtDMY2 = new SimpleDateFormat("mm/dd/yyyy");
    
    DateFormat fmtYMD_HM = new SimpleDateFormat("yyyy-mm-dd HH:mm");
    //DateFormat fmtDMY_HM = new SimpleDateFormat("dd-mm-yyyy HH:mm");
    //DateFormat fmtDMY2_HM = new SimpleDateFormat("mm/dd/yyyy HH:mm");
    
    //DateFormat fmtYMD_HMS = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
    //DateFormat fmtDMY_HMS = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
    //DateFormat fmtDMY2_HMS = new SimpleDateFormat("mm/dd/yyyy HH:mm:ss");
    
    DateFormat fmtHM = new SimpleDateFormat("HH:mm");
    //DateFormat fmtHMS = new SimpleDateFormat("HH:mm:ss");
    
    //DateFormat defaultDateFormat = fmtYMD;
    //DateFormat defaultDateTimeFormat = fmtYMD_HM;
    //DateFormat defaultTimeFormat = fmtHM;
    
    String currentYearDate, currentYear;
    String currentDateTime, currentDate, currentTime;
    String today, yesterday, weekAgo, monthAgo, yearAgo;
    String[] weekDates;
    //Date date, dateTime, time;
    int monthDays, dayOfWeek, dayOfMonth, dayOfYear, dayOfWeekInMonth;
    int weekOfMonth, weekOfYear;
    
    /**
     * Chronology constructor method sets the current and past date and time
     */
    public Chronology()
    {
        setDateTime();// set date and time
        setDay();// set day value relative to month and week
        setWeek();
        setPast();
        
    }// end Chronology constructor
        
    /**
     * setDateTime method sets the current date and time with different formats
     */
    private void setDateTime()
    {
        // set current date with diffent formatting
        this.currentDateTime = formatDate("yyyy-MM-dd HH:mm");
        this.today = formatDate("yyyy-MM-dd");
        this.currentYearDate = formatDate("yyyy-MM");
        this.currentYear = formatDate("yyyy");
        this.currentTime = formatDate("HH:mm");
    }// end setDateTime method
    
    /**
     * formatDate method returns current date with given formatting
     * @param pattern date formatting
     * @return current date with format
     */
    private String formatDate(String pattern)
    {
        // Instantiate new date and date format objects
        DateFormat df = new SimpleDateFormat(pattern);
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        // format and return current date
        return df.format(date);
    }// end formatDate method
    
    /**
     * setDay method for setting the day number in relation to its week, month, 
     * and year
     */
    public void setDay()
    {
        Calendar cal = Calendar.getInstance();
        
        int day = cal.get(Calendar.DAY_OF_WEEK);
        this.dayOfWeek = day;
        
        day = cal.get(Calendar.DAY_OF_MONTH);
        this.dayOfMonth = day;
                
        day = cal.get(Calendar.DAY_OF_YEAR);
        this.dayOfYear = day;
        
        day = cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        this.dayOfWeekInMonth = day;
    }// end setDay method
    
    /**
     * setWeek method for setting the current week number
     */
    public void setWeek()
    {
        // instantiate new Calendar object
        Calendar cal = Calendar.getInstance();
        // get and set week number in month
        int week = cal.get(Calendar.WEEK_OF_MONTH);
        this.weekOfMonth = week;
        // get and set week number in year
        week = cal.get(Calendar.WEEK_OF_YEAR);
        this.weekOfYear = week;
    }// end setWeek method
    
    /**
     * setPast method for setting of dates for yesterday, a 
     * a week ago, a month ago, and a year ago
     */
    public void setPast()
    {
        // get and set date for yesterday
        this.yesterday = setPastDate(-1);
        // get and set date for a week ago
        this.weekAgo = setPastDate(-7);
        setWeekDays();// set dates in week
        // get and set date for a month ago
        this.monthAgo = setPastDate(-30);
        // get and set date for a year ago
        this.yearAgo = setPastDate(-365);
        
    }// end setPast method
   
    /**
     * setWeekAgo method method for setting the date based on the given days prior
     * to the current date
     */
    public String setPastDate(int daysPrior)
    {
        // instantiate new Calendar object
        Calendar cal = Calendar.getInstance();
        // set date format
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        // get calendar date
        cal.add(Calendar.DATE, daysPrior);
        return df.format(cal.getTime());
    }// end setWeekAgo
    
    /**
     * setWeekDays method for setting each day of the week's date
     */
    public void setWeekDays()
    {
        // instantiate new Calendar object
        Calendar cal = Calendar.getInstance();
        // set date format
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        // get and set calendar date for each day of the week
        cal.get(Calendar.SUNDAY);
        String sun = df.format(cal.getTime());
        cal.get(Calendar.MONDAY);
        String mon = df.format(cal.getTime());
        cal.get(Calendar.TUESDAY);
        String tue = df.format(cal.getTime());
        cal.get(Calendar.WEDNESDAY);
        String wed = df.format(cal.getTime());
        cal.get(Calendar.THURSDAY);
        String thu = df.format(cal.getTime());
        cal.get(Calendar.FRIDAY);
        String fri = df.format(cal.getTime());
        cal.get(Calendar.SATURDAY);
        String sat = df.format(cal.getTime());
        
        // append week dates into array
        String[] weekDates = {sun,mon,tue,wed,thu,fri,sat};
        this.weekDates = weekDates;
    }// end setWeekDays
    
    /**
     * setMonthDays method sets the number of days in a month give the year, month,
     * and day
     * @param month int month in year
     * @param year int year
     * @param day int day of month
     */
    public void setMonthDays(int month, int year, int day)
    {
        // instantiate new Calendar object
        Calendar cal = Calendar.getInstance();
        // set calendar year month and day
        cal.set(year,month,day);
        // get and set maximum month days
        int maxDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        this.monthDays = maxDays;
    }// end setMonthDays method

    /**
     * getDateTime method returns current date with time
     *
     * @return date and time
     */
    public String getDateTime() {
        return currentDateTime;
    }// end getDateTime method

    /**
     * getDate method returns current date
     *
     * @return date
     */
    public String getDate() {
        return today;
    }// end getDate method

    /**
     * getTime method returns current time
     *
     * @return time
     */
    public String getTime() {
        return currentTime;
    }// end getTime method

    /**
     * getCurrentYearDate method returns year date
     *
     * @return current date
     */
    public String getCurrentYearDate() {
        return currentYearDate;
    }// end getCurrentYearDate method

    /**
     * getCurrentYear method returns current year
     *
     * @return year
     */
    public String getCurrentYear() {
        return currentYear;
    }// end getCurrentYear method

    /**
     * getDayOfWeek method returns day number in week
     *
     * @return int day
     */
    public int getDayOfWeek() {
        return dayOfWeek;
    }// end getDayOfWeek method

    /**
     * getDayOfMonth method returns day number in month
     *
     * @return int day
     */
    public int getDayOfMonth() {
        return dayOfMonth;
    }// end getDayOfMonth method

    /**
     * getDayOfMonthInWeek method returns day number in week in month
     *
     * @return int day in week in month
     */
    public int getDayOfMonthInWeek() {
        return dayOfWeekInMonth;
    }// end getDayOfMonthInWeek method

    /**
     * getDayOfYear method returns day number in year
     *
     * @return int day
     */
    public int getDayOfYear() {
        return dayOfYear;
    }// end getDayOfYear method

    /**
     * getWeekDates method returns week dates
     *
     * @return array of week dates
     */
    public String[] getWeekDates() {
        return weekDates;
    }// end getWeekDates method

    /**
     * getYesterday method returns yesterdays date
     *
     * @return date
     */
    public String getYesterday() {
        return yesterday;
    }// end getYesterday method

    /**
     * getWeekAgo method returns week ago date
     *
     * @return date
     */
    public String getWeekAgo() {
        return weekAgo;
    }// end getWeekAgo method

    /**
     * getMonthAgo method returns month ago date
     *
     * @return date
     */
    public String getMonthAgo() {
        return monthAgo;
    }// end getMonthAgo method

    /**
     * getYearAgo method returns year ago date
     *
     * @return date
     */
    public String getYearAgo() {
        return yearAgo;
    }// end getYearAgo method

    /**
     * getMonthDays method returns number of days in month
     *
     * @return day count
     */
    public int getMonthDays() {
        return monthDays;
    }// end getMonthDays method

    /**
     * getWeekOfMonth method returns week number in month
     *
     * @return int week
     */
    public int getWeekOfMonth() {
        return weekOfMonth;
    }// end getWeekOfMonth method

    /**
     * getWeekOfYear method returns week number in year
     *
     * @return int week
     */
    public int getWeekOfYear() {
        return weekOfYear;
    }// end getWeekOfYear method
}// end Chronology class

