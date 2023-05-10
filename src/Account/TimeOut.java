/**
 * TimeOut.java - Class implements Runnable to run simultaneously with the 
 * application to track user activity and logs out when inactivity limit reached.
 * CSIS 643 - D01
 * @author Kore Woody
 */
package Account;

import Query.QueryAccount;      
import java.util.TimerTask;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.Math;

public class TimeOut extends Object implements Runnable
{
    TimerTask timerTask;
    long timer;
    long limit;
    
    /**
     * run method overrides the Runnable run method for concurrent threading
     */
    @Override
    public void run()
    {
        //System.out.println("Timer task started");
        setLimit();
        TimedOut();
        //System.out.println("Timer task finished");
    }
    
    /**
     * TimeOut method
     */
    public void TimedOut()
    {
        try{
        Thread.sleep(limit);
        } catch (Exception e){
            e.printStackTrace();
        }
    }// end TimeOut method
    
    /**
     * setLimit method for setting the timeout limit in nanoseconds
     */
    public void setLimit()
    {
        long limit = 0;
        // Instantiate new QueryAccount object
        QueryAccount qa = new QueryAccount();
            // get Timeout Settings
            ResultSet rs = qa.selectAccountSettings();
        // get time values and convert to int
        int hour = Integer.parseInt(qa.getHours());
        int min = Integer.parseInt(qa.getMinutes());
        int sec = Integer.parseInt(qa.getSeconds());
        // convert hours, min, and sec to nanoseconds
        double lim = ((hour*3600)+(min*60)+sec)*Math.pow(10, 9);
        int i = (int) lim;
        limit = i;
        // set limit
        this.limit = limit;
    }// end setLimit method
    
    /**
     * startTimer method starts the timer and checks if current time has reached
     * the set time out limit
     * @return true/false if timer 
     */
    public boolean startTimer()
    {
        // set timeout limit
        setLimit();
        //start timer
        this.timer = System.nanoTime();
        //check if limit reached
        if (timer==limit)
        {
            return cancelTimer();
        }
        else
        {
            return false;
        }
    }// end startTimer method
    
    /**
     * cancelTimer method for stopping the timer
     * @return true when timer stopped
     */
    public boolean cancelTimer()
    {
        this.timer = System.nanoTime();
        return true;
    }//end cancelTimer method
    
}// end TimeOut Class
