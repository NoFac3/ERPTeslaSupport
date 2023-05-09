package Messaging;

import Account.*;
import Chronology.CalendarInternalFrame;
import DB.DatabaseListener;
import java.util.Map;

/**
 * AcountNotification - class initiates updates to the internal frames that are
 * currently active
 * CSIS 643 - D01
 * @author Kore Woody
 */
public class AccountNotification extends DatabaseListener
{
    Map<String,Boolean> frameMap;
    ERPMainWindow mainFrame;
    CalendarInternalFrame calFrame;
    MessagesInternalFrame messageFrame;
    AdminInternalFrame adminFrame;
    ManagerInternalFrame managerFrame;        
    EngineerInternalFrame engrFrame;
    AnalystInternalFrame analystFrame;
    TechSupportInternalFrame techFrame;
    boolean mainVisible = false,calendarVisible = false,messageVisible = false,adminVisible = false,managerVisible = false,engineerVisible = false, analystVisible = false,techVisible = false;
    
    /**
     *  AccountNofification default constructor 
     */
    public AccountNotification(){}// end AccountNotification constructor
    
    /**
     * AccountNofification constructor for ERPMainWindow
     * @param frame desktop pane
     */
    public AccountNotification(ERPMainWindow frame)
    {
        this.mainFrame = frame;
        this.mainVisible = true;
    }// end AccountNotification constructor
    
    /**
     * AccountNofification constructor for Calendar frame
     * @param frame calendar internal frame
     */
    public AccountNotification(CalendarInternalFrame frame)
    {
        this.calFrame = frame;
        this.calendarVisible = true;        
    }// end AccountNotification constructor
    
    /**
     * AccountNofification constructor for Message frame
     * @param frame message internal frame
     */
    public AccountNotification(MessagesInternalFrame frame)
    {
        this.messageFrame = frame;
        this.messageVisible = true;
    }// end AccountNotification constructor
    
    
    /**
     * AccountNofification constructor for Admin frame
     * @param frame admin internal frame
     */
    public AccountNotification(AdminInternalFrame frame)
    {
        this.adminFrame = frame;
        this.adminVisible = true;        
    }// end AccountNotification constructor
    
    /**
     * AccountNofification constructor for manager frame
     * @param frame manager internal frame
     */
    public AccountNotification(ManagerInternalFrame frame)
    {
        this.managerFrame = frame;
        this.managerVisible = true;        
    }// end AccountNotification constructor

    /**
     * AccountNofification constructor for tech support ticket frame
     * @param frame tech internal frame
     */
    public AccountNotification(TechSupportInternalFrame frame)
    {
        this.techFrame = frame;
        this.techVisible = true;
    }// end AccountNotification constructor
    
    /**
     * updateFrames method for updating all frames that are visible
     */
    public void updateFrames()
    {
        if(messageVisible == true)
        {
            messageFrame.refresh();
        }
        
        if(adminVisible == true)
        {
            adminFrame.refresh();
        }
        if(managerVisible == true)
        {
            managerFrame.refresh();
        }
        if(techVisible == true)
        {
            techFrame.refresh();
        }
    }// end updateFrames method
    
}// end AccountNotification class
