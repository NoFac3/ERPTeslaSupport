package Account;

import Admin.ApplicationSettingsFrame;
import Admin.DefaultPasswordFrame;
import Admin.UserAccountFrame;
import Query.QueryAccount;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;

/**
 * AdminInternalFrame.java - for users with administrator permissions. Enables 
 * user to view and update app settings and user accounts
 * CSIS 643 - D01
 * @author Kore Woody
 */
public class AdminInternalFrame extends javax.swing.JInternalFrame {

    String name, firstName, lastName, department, userID;
    String filterType, filterDepartment;
    boolean isAdmin, isManager, isEngineer, isAnalyst, isTech;
    boolean Admin=false, Manager=false, Engineer=false, Analyst=false, Tech=false;
    String order="", order1="",order2="",order3="", type="";
    String[] orders={};
    String dateRange="";
    /**
     * Creates new form UserAccountsJInternalFrame
     * @param userID
     * @param name
     * @param isEngineer
     * @param firstName
     * @param lastName
     * @param department
     * @param isManager
     * @param isAnalyst
     * @param isAdmin
     * @param isTech
     */
    public AdminInternalFrame(
            String userID, String name, 
            String firstName, String lastName, String department, 
            boolean isAdmin, boolean isManager, boolean isEngineer, 
            boolean isAnalyst, boolean isTech) {
        super("",true,false,true);
        initComponents();

        // set user attributes
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
        
        initComponents();
        
        // set title
        setTitle("User Accounts");
        // Initialize new QueryAccount object and get users
        QueryAccount qa = new QueryAccount();
        qa.selectUsers();
        // get table model
        DefaultTableModel model = qa.getModel();
        // check for null results
        if (model!=null)
        {
            // update table
            tblUsers.setAutoCreateRowSorter(true);
            tblUsers.setModel(model);
            tblUsers.repaint();
            tblUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tblUsers.getSelectionModel().addListSelectionListener(this.tblUsers);
        }
        
        // instantiate new Account notification
        AccountNotification notify = new AccountNotification(this);
    }// end AdminInternalFrame constructor
    
    /**
     * refresh method updates the table
     */
    public void refresh()
    {
        // Initialize new QueryAccount object and get users
        QueryAccount qa = new QueryAccount();
        qa.selectUsers();
        // get table model
        DefaultTableModel model = qa.getModel();
        // update table
        tblUsers.setAutoCreateRowSorter(true);
        tblUsers.setModel(model);
        tblUsers.repaint();
        tblUsers.getSelectionModel().addListSelectionListener(this.tblUsers);
    }// end refresh method

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlDateDepartmentFilters = new javax.swing.JPanel();
        cmbFilterDate = new javax.swing.JComboBox<>();
        cmbFilterDepartment = new javax.swing.JComboBox<>();
        pnlPrivileges1 = new javax.swing.JPanel();
        cbAdmin1 = new javax.swing.JCheckBox();
        cbManager1 = new javax.swing.JCheckBox();
        cbEngineer1 = new javax.swing.JCheckBox();
        cbAnalyst1 = new javax.swing.JCheckBox();
        cbTech1 = new javax.swing.JCheckBox();
        cmbFilterType = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();
        btnNewUser = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        mnuFileReset = new javax.swing.JMenuItem();
        mnuFileDefaultPassword = new javax.swing.JMenuItem();
        mnuSettings = new javax.swing.JMenu();
        mnuTimeout = new javax.swing.JMenuItem();

        cmbFilterDate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Filter By Date...", "Newest Accounts", "Oldest Accounts", "Today", "Yesterday", "This Week", "This Month", "This Year" }));
        cmbFilterDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFilterDateActionPerformed(evt);
            }
        });

        cmbFilterDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Filter By Department...", "Analyst", "Engineering", "Tech Support", "General" }));
        cmbFilterDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFilterDepartmentActionPerformed(evt);
            }
        });

        cbAdmin1.setText("Admin");
        cbAdmin1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbAdmin1ItemStateChanged(evt);
            }
        });

        cbManager1.setText("Manager");
        cbManager1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbManager1ItemStateChanged(evt);
            }
        });

        cbEngineer1.setText("Engineer");
        cbEngineer1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbEngineer1ItemStateChanged(evt);
            }
        });

        cbAnalyst1.setText("Analyst");
        cbAnalyst1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbAnalyst1ItemStateChanged(evt);
            }
        });

        cbTech1.setText("Tech");
        cbTech1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTech1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlPrivileges1Layout = new javax.swing.GroupLayout(pnlPrivileges1);
        pnlPrivileges1.setLayout(pnlPrivileges1Layout);
        pnlPrivileges1Layout.setHorizontalGroup(
            pnlPrivileges1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrivileges1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbAdmin1)
                .addGap(11, 11, 11)
                .addComponent(cbManager1)
                .addGap(18, 18, 18)
                .addComponent(cbEngineer1)
                .addGap(18, 18, 18)
                .addComponent(cbAnalyst1)
                .addGap(18, 18, 18)
                .addComponent(cbTech1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPrivileges1Layout.setVerticalGroup(
            pnlPrivileges1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrivileges1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlPrivileges1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbManager1)
                    .addComponent(cbAdmin1)
                    .addComponent(cbEngineer1)
                    .addComponent(cbAnalyst1)
                    .addComponent(cbTech1))
                .addGap(49, 49, 49))
        );

        cmbFilterType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Filter by Type...", "Admin", "Manager", "Engineer", "Analyst", "Tech" }));
        cmbFilterType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFilterTypeActionPerformed(evt);
            }
        });

        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "UserID", "Type", "First Name", "Last Name", "Department", "Admin", "Manager", "Engineer", "Analyst", "Tech"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblUsers.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsers);

        btnNewUser.setText("New User");
        btnNewUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDateDepartmentFiltersLayout = new javax.swing.GroupLayout(pnlDateDepartmentFilters);
        pnlDateDepartmentFilters.setLayout(pnlDateDepartmentFiltersLayout);
        pnlDateDepartmentFiltersLayout.setHorizontalGroup(
            pnlDateDepartmentFiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDateDepartmentFiltersLayout.createSequentialGroup()
                .addGroup(pnlDateDepartmentFiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDateDepartmentFiltersLayout.createSequentialGroup()
                        .addGroup(pnlDateDepartmentFiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(pnlPrivileges1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDateDepartmentFiltersLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cmbFilterDate, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbFilterDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(cmbFilterType, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                        .addComponent(btnNewUser))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        pnlDateDepartmentFiltersLayout.setVerticalGroup(
            pnlDateDepartmentFiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDateDepartmentFiltersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDateDepartmentFiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbFilterDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFilterDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFilterType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNewUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlPrivileges1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDateDepartmentFilters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlDateDepartmentFilters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mnuFile.setText("File");

        jMenuItem1.setText("Refresh");
        mnuFile.add(jMenuItem1);

        mnuFileReset.setText("Reset Filters");
        mnuFileReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFileResetActionPerformed(evt);
            }
        });
        mnuFile.add(mnuFileReset);

        mnuFileDefaultPassword.setText("Change Default Password");
        mnuFileDefaultPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFileDefaultPasswordActionPerformed(evt);
            }
        });
        mnuFile.add(mnuFileDefaultPassword);

        jMenuBar1.add(mnuFile);

        mnuSettings.setText("App Settings");

        mnuTimeout.setText("Time Out");
        mnuTimeout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTimeoutActionPerformed(evt);
            }
        });
        mnuSettings.add(mnuTimeout);

        jMenuBar1.add(mnuSettings);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * cbAdmin1ItemStateChanged checkbox listener method updates permission filter
     * @param evt 
     */
    private void cbAdmin1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbAdmin1ItemStateChanged
        if (cbAdmin1.isSelected()) {
            this.Admin = true;
        } else {
            this.Admin = false;
        }
        //updateAccountTable();
        update();
    }//GEN-LAST:event_cbAdmin1ItemStateChanged
    // end cbAdmin1ItemStateChanged
    
    /**
     * cbManager1ItemStateChanged checkbox listener method updates permission filter
     * @param evt 
     */
    private void cbManager1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbManager1ItemStateChanged
        if (cbManager1.isSelected()) {
            this.Manager = true;
        } else {
            this.Manager = false;
        }
        update();
    }//GEN-LAST:event_cbManager1ItemStateChanged
    // end cbManager1ItemStateChanged method                                    

    
    /**
     * cbEngineer1ItemStateChanged checkbox listener method updates permission filter
     * @param evt 
     */
    private void cbEngineer1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbEngineer1ItemStateChanged
        if (cbEngineer1.isSelected()) {
            this.Engineer = true;
        } else {
            this.Engineer = false;
        }
        update();
    }//GEN-LAST:event_cbEngineer1ItemStateChanged
    // end cbEngineer1ItemStateChanged method
    
    /**
     * cbTech1ItemStateChanged checkbox listener method updates permission filter
     * @param evt 
     */
    private void cbTech1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTech1ItemStateChanged
        if (cbTech1.isSelected()) {
            this.Tech = true;
        } else {
            this.Tech = false;
        }
        update();
    }//GEN-LAST:event_cbTech1ItemStateChanged
    // end cbTech1ItemStateChanged method
    
    /**
     * cbAnalyst1ItemStateChanged checkbox listener method updates permission filter
     * @param evt 
     */
    private void cbAnalyst1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbAnalyst1ItemStateChanged
        if (cbAnalyst1.isSelected()) {
            this.Analyst = true;
        } else {
            this.Analyst = false;
        }
        update();
    }//GEN-LAST:event_cbAnalyst1ItemStateChanged
    // end cbAnalyst1ItemStateChanged
    
    /**
     * mnuFileResetActionPerformed menu button action listener resets the table 
     * and filters
     * @param evt 
     */
    private void mnuFileResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFileResetActionPerformed
        updateTable();
    }//GEN-LAST:event_mnuFileResetActionPerformed
    // end mnuFileResetActionPerformed
    
    /**
     * cmbFilterTypeActionPerformed combo box action listener for filtering table
     * by user type
     * @param evt 
     */
    private void cmbFilterTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFilterTypeActionPerformed
        String type = cmbFilterType.getSelectedItem().toString();
        this.filterType = type;
        int t = cmbFilterType.getSelectedIndex();
        update();
    }//GEN-LAST:event_cmbFilterTypeActionPerformed
    // end cmbFilterTypeActionPerformed
    
    /**
     * cmbFilterDepartmentActionPerformed combo box action listener for filtering
     * table by department
     * @param evt 
     */
    private void cmbFilterDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFilterDepartmentActionPerformed
        String department = cmbFilterDepartment.getSelectedItem().toString();
        this.filterDepartment = department;
        update();
    }//GEN-LAST:event_cmbFilterDepartmentActionPerformed
    // end cmbFilterDepartmentActionPerformed
    
    /**
     * cmbFilterDateActionPerformed combo box action listener for filtering 
     * table by date
     * @param evt 
     */
    private void cmbFilterDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFilterDateActionPerformed
        String dateRange = cmbFilterDate.getSelectedItem().toString();
        this.dateRange = dateRange;
        update();
    }//GEN-LAST:event_cmbFilterDateActionPerformed
    // end cmbFilterDateActionPerformed
    
    /**
     * tblUsersMouseClicked mouse click listener opens user account frame when
     * row is selected
     * @param evt mouse click
     */
    private void tblUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsersMouseClicked
        int column = tblUsers.getSelectedColumn();
        int row = tblUsers.getSelectedRow();
        
        if (row!=-1)
        {
            String userID = tblUsers.getModel().getValueAt(row, 0).toString();
            JFrame frame = new UserAccountFrame(userID);
            frame.setLocation(this.getLocation());
            frame.setVisible(true);
        }
    }//GEN-LAST:event_tblUsersMouseClicked
    // end tblUsersMouseClicked
    
    /**
     * btnNewUserActionPerformed button action listener for creating new user 
     * account
     * @param evt 
     */
    private void btnNewUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewUserActionPerformed
        JFrame frame = new UserAccountFrame(null);
        frame.setLocation(this.getLocation());
        frame.setVisible(true);
    }//GEN-LAST:event_btnNewUserActionPerformed
    // end btnNewUserActionPerformed
    
    /**
     * mnuFileDefaultPasswordActionPerformed menu action listener for changing 
     * default password
     * @param evt 
     */
    private void mnuFileDefaultPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFileDefaultPasswordActionPerformed
        //ew DefaultPasswordFrame().setVisible(true);
        JFrame frame = new DefaultPasswordFrame();
        frame.setLocation(this.getLocation());
        frame.setVisible(true);
    }//GEN-LAST:event_mnuFileDefaultPasswordActionPerformed
    // end mnuFileDefaultPasswordActionPerformed
    
    /**
     * mnuTimeoutActionPerformed menu action listener for updating timeout
     * @param evt 
     */
    private void mnuTimeoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuTimeoutActionPerformed
       //new ApplicationSettingsFrame().setVisible(true);
       JFrame frame = new ApplicationSettingsFrame();
        frame.setLocation(this.getLocation());
        frame.setVisible(true);
    }//GEN-LAST:event_mnuTimeoutActionPerformed
    // end mnuTimeoutActionPerformed method
    
    /**
     * update method updates the table model with selected filters
     */
    public void update()
    {
        boolean[] permissions = {Admin, Manager, Engineer, Analyst, Tech};
        String[] orders = {order1,order2,order3};
        String[] pt = {
            "AdminPermission", "ManagerPermission", "EngineerPermission",
            "AnalystPermission", "TechPermission"};
        
        QueryAccount qa = new QueryAccount();
        // query and get new table model
        qa.selectUsersByFilter(permissions,filterType,filterDepartment,dateRange,orders);
        DefaultTableModel model = qa.getModel();
        if (model!=null)
        {
            // update table
            tblUsers.setModel(model);
            tblUsers.repaint();
            tblUsers.getSelectionModel().addListSelectionListener(this.tblUsers);
        }
    }// end update method
    
    /**
     * updateTable updates table with default query
     */
    public void updateTable()
    {
        QueryAccount qa = new QueryAccount();
        // query and get new table model
        qa.selectUsers();
        DefaultTableModel model = qa.getModel();
        // update table model
        tblUsers.setModel(model);
        tblUsers.repaint();
        tblUsers.getSelectionModel().addListSelectionListener(this.tblUsers);
    }// end updateTable method
    
    /**
     * updateAccountTable updates table with selected permission filters
     */
    public void updateAccountTable() {
        boolean[] p = {Admin, Manager, Engineer, Analyst, Tech};
        
        QueryAccount qa = new QueryAccount();

        if (p[0] == false && p[1] == false && p[2] == false && p[3] == false && p[4] == false) {
            updateTable();
        } else {
            // query and get new table model
            qa.selectUsersByPermissions(p);
            DefaultTableModel model = qa.getModel();
            // update table
            tblUsers.setModel(model);
            tblUsers.repaint();
            tblUsers.getSelectionModel().addListSelectionListener(this.tblUsers);
        }// end updateAccountTable
    }

    /**
     * updateAccountTableFilter method updates account table with filters
     * @param index
     * @param type
     */
    public void updateAccountTableFilter(int index, String type) {
        if (index != 0 || !type.contains("Filter by Type...")) {
            if (type.matches("Admin")) {
                this.Admin = true;
                this.Manager = false;
                this.Engineer = false;
                this.Analyst = false;
                this.Tech = false;
            } else if (type.matches("Manager")) {
                this.Manager = true;
                this.Admin = false;
                this.Engineer = false;
                this.Analyst = false;
                this.Tech = false;
            } else if (type.matches("Engineer")) {
                this.Engineer = true;
                this.Admin = false;
                this.Manager = false;
                this.Analyst = false;
                this.Tech = false;
            } else if (type.matches("Analyst")) {
                this.Analyst = true;
                this.Admin = false;
                this.Manager = false;
                this.Engineer = false;
                this.Tech = false;
            } else if (type.matches("Tech")) {
                this.Tech = true;
                this.Admin = false;
                this.Manager = false;
                this.Engineer = false;
                this.Analyst = false;
            }
        }
        boolean[] p = {isAdmin, isManager, isEngineer, isAnalyst, isTech};
        QueryAccount qa = new QueryAccount();
        if (p[0] == false && p[1] == false && p[2] == false && p[3] == false && p[4] == false) {
            updateTable();
        } else {
            // query and get new table model
            qa.selectUsersByType(p);
            DefaultTableModel model = qa.getModel();
            // update table
            tblUsers.setModel(model);
            tblUsers.repaint();
            tblUsers.getSelectionModel().addListSelectionListener(this.tblUsers);
        }
    }// end updateAccountTableFilter
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNewUser;
    private javax.swing.JCheckBox cbAdmin1;
    private javax.swing.JCheckBox cbAnalyst1;
    private javax.swing.JCheckBox cbEngineer1;
    private javax.swing.JCheckBox cbManager1;
    private javax.swing.JCheckBox cbTech1;
    private javax.swing.JComboBox<String> cmbFilterDate;
    private javax.swing.JComboBox<String> cmbFilterDepartment;
    private javax.swing.JComboBox<String> cmbFilterType;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenuItem mnuFileDefaultPassword;
    private javax.swing.JMenuItem mnuFileReset;
    private javax.swing.JMenu mnuSettings;
    private javax.swing.JMenuItem mnuTimeout;
    private javax.swing.JPanel pnlDateDepartmentFilters;
    private javax.swing.JPanel pnlPrivileges1;
    public static javax.swing.JTable tblUsers;
    // End of variables declaration//GEN-END:variables
}// end AdminInternalFrame class
