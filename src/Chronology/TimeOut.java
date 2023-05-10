/**
 * TimeOut.java - Class implements Runnable to run simultaneously with the 
 * application to track user activity and logs out when inactivity limit reached.
 * CSIS 643 - D01
 * @author Kore Woody
 */
package Chronology;

import Query.QueryAccount;      
import java.util.TimerTask;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.Math;
import java.util.Timer;

/**
 *
 * @author consp
 */
public class TimeOut extends TimerTask
{
    private Thread thread;
    private Timer timer;
    private long limit;
    
    /**
     * run method overrides the Runnable run method for concurrent threading
     * @param thread
     * @param timer
     */
    public TimeOut(Thread thread, Timer timer)
    {
        this.thread = thread;
        this.timer = timer;
    }// end TimeOut constructor
    
    /**
     * run method initializes the timeout interrupter
     */
    @Override
    public void run()
    {
        if (thread != null && thread.isAlive())
        {
            thread.interrupt();
            timer.cancel();
        }
    }// end run override method
    
    /**
     * setLimit method for setting the timeout limit in nanoseconds
     */
    public long Limit()
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
        return limit;
    }// end setLimit method
    
}// end TimeOut Class
