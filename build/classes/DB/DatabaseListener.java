
package DB;

import Account.AccountNotification;

/**
 * DatabaseListener.java - child of Database class. Initializes account
 * account notification on database changes
 * CSIS 643 - D01
 * @author Kore Woody
 */
public class DatabaseListener extends Database
{
    /**
     * DatabaseListener constructor triggers AcountNotification
     */
    public DatabaseListener()
    {
        AccountNotification notify = new AccountNotification();
        notify.updateFrames();
    }// end DatabaseListener constructor
    
}// end DatabaseListener class
