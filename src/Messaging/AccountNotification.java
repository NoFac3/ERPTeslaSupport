package Messaging;

import Account.*;
import Chronology.CalendarInternalFrame;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.util.Map;
import org.controlsfx.control.Notifications;
import javafx.geometry.Pos;

/**
 * AcountNotification - 
 * CSIS 643 - D01
 * @author Kore Woody
 */
public class AccountNotification 
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
     *
     */
    public AccountNotification(){};
    
    /**
     *
     * @param frame
     */
    public AccountNotification(ERPMainWindow frame)
    {
        this.mainFrame = frame;
        this.mainVisible = true;
    }
    
    /**
     *
     * @param frame
     */
    public AccountNotification(CalendarInternalFrame frame)
    {
        this.calFrame = frame;
        this.calendarVisible = true;        
    }
    
    /**
     *
     * @param frame
     */
    public AccountNotification(MessagesInternalFrame frame)
    {
        this.messageFrame = frame;
        this.messageVisible = true;

    }
    
    
    /**
     *
     * @param frame
     */
    public AccountNotification(AdminInternalFrame frame)
    {
        this.adminFrame = frame;
        this.adminVisible = true;        
    }
    
    /**
     *
     * @param frame
     */
    public AccountNotification(ManagerInternalFrame frame)
    {
        this.managerFrame = frame;
        this.managerVisible = true;        
    }
    
    /**
     *
     * @param frame
     */
    public AccountNotification(EngineerInternalFrame frame)
    {
        this.engrFrame = frame;
        this.engineerVisible = true;        
    }

    /**
     *
     * @param frame
     */
    public AccountNotification(AnalystInternalFrame frame)
    {
        this.analystFrame = frame;
        this.analystVisible = true;        
    }

    /**
     *
     * @param frame
     */
    public AccountNotification(TechSupportInternalFrame frame)
    {
        this.techFrame = frame;
        this.techVisible = true;
    }
    
    public void updateFrames()
    {
        if(mainVisible == true)
        {
            //mainFrame.refreshFrames();
        }
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
    
    
    /**
     *
     * @return
     */
    public Map<String,Boolean> getVisibleFrames(){return frameMap;}
    
    /**
     *
     * @throws AWTException
     */
    public void accountNotification() throws AWTException {
        if (SystemTray.isSupported()) {
            //AccountNotification td = new AccountNotification();
            //td.displayTray();
        } else {
            //System.err.println("System tray not supported!");
        }
    }

    /**
     *
     * @throws AWTException
     */
    public void displayTray() throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage("Hello, World", "notification demo", MessageType.INFO);
    }
    
    /**
     *
     * @param title
     * @param text
     */
    public void showNotification(String title,String text)
    {
        Notifications notificationTest=Notifications.create();
        notificationTest.position(Pos.BASELINE_RIGHT);
        notificationTest.title(title);
        notificationTest.text(text);
        notificationTest.show();//for error noti notificationTest.showError();
    }
    
}
