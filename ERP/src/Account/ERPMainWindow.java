package Account;

import Messaging.MessagesInternalFrame;
import Chronology.CalendarInternalFrame;
import Messaging.AccountNotification;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.event.InternalFrameListener;

/**
 * ERPMainWindow.java - main Frame for initializing and housing all internal 
 * frames based on the user's account permissions.
 * CSIS 643 - D01
 * @author Kore Woody
 */
public class ERPMainWindow extends javax.swing.JFrame implements Runnable {

    public static String nameofTech = null;
    public static String name,firstName,lastName,department,userID,type;
    public static boolean isAdmin,isManager,isEngineer,isAnalyst,isTech;
    public InternalFrameListener calendarListener,messageListener,techListener,analystListener,engineerListener,managerListener,adminListener;
    public JInternalFrame ifrmCalendar;
    public JInternalFrame ifrmMessages,ifrmTickets,ifrmLogs,ifrmUsers,ifrmEngineer,ifrmAnalyst,ifrmAnalyst2,ifrmAnalyst3;
    public String title = "";
    boolean resizeable = true;
    boolean closeable = true;
    boolean maximizable = true;
    boolean visibleAdmin=false,visibleManager=false,visibleEngineer=false,visibleTickets=false,visibleAnalyst=false;
    boolean visibleCalendar = false, visibleMessages=false;

    public Dimension desktopSize;
    public int frameX, frameY, frameW, frameH;
    public Rectangle full,full1,full2;
    public Rectangle left;
    public Rectangle ul,ml, ll, ulBox,llBoxFull, mlBox,llBox;
    public Rectangle middle;
    public Rectangle um,mm, lm,umBox, lmBoxFull, mmBox, lmBox,lllmBox;
    public Rectangle right,rightSmall,rightFull;
    public Rectangle ur,mr,lr,urBox,lrBoxFull,mrBox,lrBox;
    public long activityTime;
    /**
     * Creates new form ERPMainWindow
     * @param userID
     * @param name
     * @param firstName
     * @param isManager
     * @param lastName
     * @param frameH
     * @param isAdmin
     * @param type
     * @param isTech
     * @param department
     * @param frameW
     * @param isAnalyst
     * @param frameX
     * @param isEngineer
     * @param desktopSize
     * @param frameY
     */
    public ERPMainWindow(
            String userID, String name, 
            String firstName, String lastName, String type, String department, 
            boolean isAdmin, boolean isManager, boolean isEngineer, 
            boolean isAnalyst, boolean isTech, 
            int frameX, int frameY, int frameW, int frameH, Dimension desktopSize) {
        this.type = type;
        this.desktopSize = desktopSize;
        this.nameofTech = name;
        this.userID = userID;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.isAdmin = isAdmin;
        this.isManager = isManager;
        this.isEngineer = isEngineer;
        this.isAnalyst = isAnalyst;
        this.isTech = isTech;
        // set title
        setTitle("Tech Support Technician: "+name);
        initComponents();
        // set frame width & height
        int width = frameW-15;
        int height = frameH-75;
        // instantiate new LayoputPosition object
        LayoutPosition lp = new LayoutPosition();
        // set layout positions
        lp.setRectangleDefault(width,height);
        
        // check user permissions
        if (isAdmin==true)// is admin
        {            
            //create and display  Logs
            ifrmLogs = new ManagerInternalFrame(userID,department,isManager);
            desktopPane.add(ifrmLogs);
            ifrmLogs.setBounds(lp.llBoxFull);
            ifrmLogs.addInternalFrameListener(managerListener);
            ifrmLogs.setVisible(true);
            this.visibleManager = true;
            
            //create and display User Account Table
            ifrmUsers = new AdminInternalFrame(
                    userID,name,firstName,lastName,department,
                    isAdmin,isManager,isEngineer,isAnalyst,isTech);
            desktopPane.add(ifrmUsers);
            ifrmUsers.setBounds(lp.mmBox);
            ifrmUsers.addInternalFrameListener(adminListener);
            ifrmUsers.setVisible(true);
            this.visibleAdmin = true;
            
            //create and display Ticket Table
            ifrmTickets = new TechSupportInternalFrame(
                    userID,name,firstName,lastName,department,
                    isAdmin,isManager,isEngineer,isAnalyst,isTech);
            desktopPane.add(ifrmTickets);
            ifrmTickets.setBounds(lp.lmBox);
            ifrmTickets.addInternalFrameListener(techListener);
            ifrmTickets.setVisible(true);
            this.visibleTickets = true;
            
            //create and display  Guide Viewer
            ifrmEngineer = new EngineerInternalFrame(
                    userID,name,firstName,lastName,department,
                    isAdmin,isManager,isEngineer,isAnalyst,isTech);
            desktopPane.add(ifrmEngineer);
            ifrmEngineer.setBounds(lp.rightFull);
            ifrmEngineer.addInternalFrameListener(engineerListener);
            ifrmEngineer.setVisible(true);
            this.visibleEngineer = true;
        }
        
        if (isManager==true && isAdmin==false)// is manager
        {
            //create and display  Logs
            ifrmLogs = new ManagerInternalFrame(userID,department,isManager);
            desktopPane.add(ifrmLogs);
            ifrmLogs.setBounds(lp.llBoxFull);
            ifrmLogs.addInternalFrameListener(managerListener);
            ifrmLogs.setVisible(true);    
            this.visibleManager = true;
            
            //create and display Ticket Table
            ifrmTickets = new TechSupportInternalFrame(
                    userID,name,firstName,lastName,department,
                    isAdmin,isManager,isEngineer,isAnalyst,isTech);
            desktopPane.add(ifrmTickets);
            ifrmTickets.setBounds(lp.lmBoxFull);
            ifrmTickets.addInternalFrameListener(techListener);
            ifrmTickets.setVisible(true);
            this.visibleTickets = true;
            
            //create and display  Guide Viewer
            ifrmEngineer = new EngineerInternalFrame(
                    userID,name,firstName,lastName,department,
                    isAdmin,isManager,isEngineer,isAnalyst,isTech);
            desktopPane.add(ifrmEngineer);
            ifrmEngineer.setBounds(lp.rightFull);
            ifrmEngineer.addInternalFrameListener(engineerListener);
            ifrmEngineer.setVisible(true);
            this.visibleEngineer = true;
        }
        
        if (isEngineer==true && isAdmin==false && isManager==false)// is engineer
        {
            
            //create and display Guide Viewer
            ifrmEngineer = new EngineerInternalFrame(
                    userID,name,firstName,lastName,department,
                    isAdmin,isManager,isEngineer,isAnalyst,isTech);
            desktopPane.add(ifrmEngineer);
            ifrmEngineer.setBounds(lp.rightFull);
            ifrmEngineer.addInternalFrameListener(engineerListener);
            ifrmEngineer.setVisible(true);
            this.visibleEngineer = true;
            
            if (isTech==true)// is tech
            {
                //create and display Ticket Table
                ifrmTickets = new TechSupportInternalFrame(
                        userID,name,firstName,lastName,department,
                        isAdmin,isManager,isEngineer,isAnalyst,isTech);
                desktopPane.add(ifrmTickets);
                ifrmTickets.setBounds(lp.umBox);
                ifrmTickets.addInternalFrameListener(techListener);
                ifrmTickets.setVisible(true);
                this.visibleTickets = true;
            }
        }
        
        if (isAnalyst==true && isAdmin==false && isManager==false)// is analyst
        {
            //create and display first Analyst frame
            ifrmAnalyst = new AnalystInternalFrame(
                    userID,name,firstName,lastName,department,
                    isAdmin,isManager,isEngineer,isAnalyst,isTech,"VehicleInventory");
            desktopPane.add(ifrmAnalyst);
            ifrmAnalyst.setBounds(lp.rightFull);//(305, 0, 980, 250);
            ifrmAnalyst.addInternalFrameListener(analystListener);
            ifrmAnalyst.setVisible(true);
            
            //create and diaplay second Analyst frame
            ifrmAnalyst2 = new AnalystInternalFrame(
                    userID,name,firstName,lastName,department,
                    isAdmin,isManager,isEngineer,isAnalyst,isTech,"VehicleDeaths");
            desktopPane.add(ifrmAnalyst2);
            ifrmAnalyst2.setBounds(lp.lllmBox1);//(305, 255, 980, 270);
            ifrmAnalyst.addInternalFrameListener(analystListener);
            ifrmAnalyst2.setVisible(true);
            
            //create and diaplay third Analyst frame
            ifrmAnalyst3 = new AnalystInternalFrame(
                    userID,name,firstName,lastName,department,
                    isAdmin,isManager,isEngineer,isAnalyst,isTech,"SuddenAcceleration");
            desktopPane.add(ifrmAnalyst3);
            ifrmAnalyst3.setBounds(lp.lllmBox2);//(305, 530, 980, 275);
            ifrmAnalyst.addInternalFrameListener(analystListener);
            ifrmAnalyst3.setVisible(true);
            this.visibleAnalyst = true;
        }
        
        if (isTech==true && isManager==false && isAdmin==false && isEngineer==false)// is tech
        {
            //create and diaplay Ticket Table
            ifrmTickets = new TechSupportInternalFrame(
                    userID, name, firstName, lastName, department,
                    isAdmin, isManager, isEngineer, isAnalyst, isTech);
            desktopPane.add(ifrmTickets);
            ifrmTickets.setBounds(lp.lllmBox);
            ifrmTickets.addInternalFrameListener(techListener);
            ifrmTickets.setVisible(true);
            this.visibleTickets = true;
            
            //create and diaplay Guide
            ifrmEngineer = new EngineerInternalFrame(
                    userID,name,firstName,lastName,department,
                    isAdmin,isManager,isEngineer,isAnalyst,isTech);
            desktopPane.add(ifrmEngineer);
            ifrmEngineer.setBounds(lp.rightFull);
            ifrmEngineer.addInternalFrameListener(engineerListener);
            ifrmEngineer.setVisible(true);
            this.visibleEngineer = true;
        }

        // Default frames for all accounts
        //create and display Messages
        ifrmMessages = new MessagesInternalFrame(
                userID,name,firstName,lastName,department,
                isAdmin,isManager,isEngineer,isAnalyst,isTech);
        desktopPane.add(ifrmMessages);
        ifrmMessages.setBounds(lp.umBox);
        ifrmMessages.addInternalFrameListener(messageListener);
        ifrmMessages.setVisible(true);
        this.visibleMessages = true;
        
        //create and diaplay Calendar
        ifrmCalendar = new CalendarInternalFrame();
        desktopPane.add(ifrmCalendar);
        ifrmCalendar.setBounds(lp.ulBox);
        ifrmCalendar.addInternalFrameListener(calendarListener);
        ifrmCalendar.setVisible(true);
        this.visibleCalendar = true;
        
        this.pack();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        mnuClose = new javax.swing.JMenuItem();
        mnuLogout = new javax.swing.JMenuItem();
        mnuLayout = new javax.swing.JMenu();
        mnuResetLayout = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(1000, 600));

        desktopPane.setDragMode(javax.swing.JDesktopPane.OUTLINE_DRAG_MODE);
        desktopPane.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                desktopPaneMouseMoved(evt);
            }
        });
        desktopPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                desktopPaneMouseExited(evt);
            }
        });
        desktopPane.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                desktopPaneComponentResized(evt);
            }
        });
        desktopPane.setMaximumSize(desktopSize);
        desktopPane.setSize(frameW, frameH);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        mnuClose.setMnemonic('x');
        mnuClose.setText("Close");
        mnuClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCloseActionPerformed(evt);
            }
        });
        fileMenu.add(mnuClose);

        mnuLogout.setText("Logout");
        mnuLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLogoutActionPerformed(evt);
            }
        });
        fileMenu.add(mnuLogout);

        menuBar.add(fileMenu);

        mnuLayout.setText("Layout");

        mnuResetLayout.setText("Reset Layout");
        mnuResetLayout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuResetLayoutActionPerformed(evt);
            }
        });
        mnuLayout.add(mnuResetLayout);

        menuBar.add(mnuLayout);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Contents");
        helpMenu.add(contentMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1007, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void mnuCloseActionPerformed(java.awt.event.ActionEvent evt) {                                         
        //this.activityTime = System.nanoTime();
        System.exit(0);
    }                                        

    private void desktopPaneComponentResized(java.awt.event.ComponentEvent evt) {                                             
        resizeFrames();
    }                                            

    private void mnuResetLayoutActionPerformed(java.awt.event.ActionEvent evt) {                                               
        resizeFrames();
    }                                              

    private void desktopPaneMouseMoved(java.awt.event.MouseEvent evt) {                                       
        
    }                                      

    private void desktopPaneMouseExited(java.awt.event.MouseEvent evt) {                                        
      
    }                                       

    /**
     * mnuLogoutActionPerformed button listener closes window and opens login frame
     * @param evt 
     */
    private void mnuLogoutActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // create new login frame
        JFrame frame = new LoginFrame();
       frame.setLocation(this.getLocation());
       frame.setVisible(true);
       // close this frame
       this.setVisible(false);
       this.dispose();
    }                                         
    // end mnuLogoutActionPerformed method
    
    /**
     * resizeFrames method dynamically resizes internal frames based on the 
     * size of the desktop pane
     */
    public void resizeFrames()
    {
        // set desktop pane dimensions
        int width = desktopPane.getWidth();
        int height = desktopPane.getHeight();
        // get inter
        JInternalFrame[] intFrames = desktopPane.getAllFrames();
        
        LayoutPosition lp = new LayoutPosition();
        lp.setRectangleDefault(width-10,height-10);
        int a = 1;
        for (JInternalFrame intFrame: intFrames)
        {
            String frameName = intFrame.getClass().toString().replace(intFrame.getClass().getPackageName(),"").replace("class .","").replace("InternalFrame","");
            Rectangle r = lp.setSizePosition(frameName,type,a);
            intFrame.setSize(r.getSize());
            intFrame.setLocation(r.getLocation());
        }
    }// end resizeFrames method
    
    /**
     * changeNotification method for handling updates
     */
    public void changeNotification()
    {
    }// end changeNotification method
    
    /**
     * refreshFrames method for refreshing internal frames
     */
    public void refreshFrames()
    {
        JInternalFrame[] intFrames = desktopPane.getAllFrames();
        
        AccountNotification an = new AccountNotification(this);
        
    }// end refreshFrames method
    
    // Variables declaration - do not modify                     
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem mnuClose;
    private javax.swing.JMenu mnuLayout;
    private javax.swing.JMenuItem mnuLogout;
    private javax.swing.JMenuItem mnuResetLayout;
    // End of variables declaration                   

    /**
     * run method overrides runnable method. Starts threaded timer and logs out 
     * user when time out is reached 
     */
    @Override
    public void run() {
        for (int i = 0; i< Long.MAX_VALUE; i++) {
            // check if timeout reached
            if(Thread.interrupted()){
                // show time out dialog
                JOptionPane.showMessageDialog(rootPane, "You have timed out of the application due to inactivity. Please login again.", "Timed Out", JOptionPane.OK_OPTION);
                // open login frame
                JFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
                this.setVisible(false);
                // close this frame
                this.dispose();
                return;
            }
        }
    }

}
