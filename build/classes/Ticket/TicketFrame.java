package Ticket;

import Query.QueryData;
import Query.QueryTicket;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 * TicketFrame.java - frame for displaying, creating, and updating ticket 
 * information. 
 * CSIS 643 - D01
 * @author Kore Woody
 */
public class TicketFrame extends JFrame {

    boolean ticketClosed=false;
    String ticketNumber, userID, department;
    String customerName, firstName, lastName;
    String status, priority;
    String assignedTo, openBy, editBy, closeBy;
    String assignOn, openOn, editOn, closedOn;
    String openDpt, editDpt, closeDpt;
    String title, description, vin, ticketLock;
    String year, model;
    String phone, email;
    String[] customerVehicle;
    /**
     * Creates new form TicketFrame
     * @param newTicket
     * @param empName
     * @param userID
     * @param ticketNumber
     * @param isEngineer
     * @param empLastName
     * @param empFirstName
     * @param isAdmin
     * @param department
     * @param isManager
     * @param isTech
     * @param isAnalyst
     */
    public TicketFrame(boolean newTicket,
            String ticketNumber,String userID, String empName, 
            String empFirstName, String empLastName, String department, 
            boolean isAdmin, boolean isManager, boolean isEngineer, 
            boolean isAnalyst, boolean isTech) {
        this.ticketNumber = ticketNumber;
        this.userID = userID;
        this.department = department;
        initComponents();
        
        updateCombos();       
        
        // instantiate new QueryTicket object
        QueryTicket qt = new QueryTicket();
        // check for new or existing ticket
        if(newTicket==false)// existing ticket
        {
            // change button text to update
            btnSave.setText("Update");
            
            // get and set ticket tabla model
            qt.selectTicket(ticketNumber);
            DefaultTableModel model = qt.getModel();
            
            // check for null model
            if (model!=null)
            {
                // populate ticket table with model
                tblTicket.setModel(model);
                tblTicket.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                tblTicket.repaint();
                
                // get ticket status
                String status = qt.getStatus();
                
                // check for null and closed status
                if (status!=null && status.matches("Closed"))
                {
                    this.ticketClosed = true;
                }
                
                // get and set priority and assigned department
                String priority = qt.getPriority();
                String assignedTo = qt.getAssignedTo();
                this.priority = priority;
                this.assignedTo = assignedTo;

                // set status priority and assigned department comboboxs
                cmbStatus.setSelectedItem(status);
                cmbPriority.setSelectedItem(priority);
                cmbAssignedTo.setSelectedItem(assignedTo);

                // update ticket table model
                tblTicketStatus.setModel(qt.getStatusTable());
                tblTicketStatus.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                tblTicketStatus.repaint();
                
                // get and set comboboxes for selected year and model
                cmbYear.setSelectedItem(qt.getVehicleYear());
                cmbModel.setSelectedItem(qt.getVehicleModel());
                // get and set vin text
                txtVin.setText(qt.getVIN());
                
                // get customerID
                String customerID = qt.getCustomerID();
                // query for customer information
                qt.selectCustomer(customerID);
                // populate text fields with customer infromation
                txtFirstName.setText(qt.getFirstName());
                txtLastName.setText(qt.getLastName());
                txtPhone.setText(qt.getPhone());
                txtEmail.setText(qt.getEmail());
                // Set title and description
                txtTitle.setText(qt.getTicketTitle());
                txtDescription.setText(qt.getDescription());
                // get and set ticket lock status
                this.ticketLock = String.valueOf(qt.getTicketLock());
            }
        }
        else// new ticket
        {
            // change button text to create
            btnSave.setText("Create");
            // set and get new ticket number
            qt.setTicketNumber();
            ticketNumber = String.valueOf(qt.getNewNumber());
            this.ticketNumber = ticketNumber;
            
            // set ui Visibility
            mnuEdit.setVisible(false);
            mnuDelete.setVisible(false);
            btnSave.setVisible(true);
            cmbStatus.setVisible(true);
            cmbPriority.setVisible(true);
            cmbAssignedTo.setVisible(true);
            lblStatus.setVisible(true);
            lblPriority.setVisible(true);
            lblAssignedTo.setVisible(true);

            // set ui editability
            cmbStatus.setEditable(true);
            cmbPriority.setEditable(true);
            cmbAssignedTo.setEditable(true);
            txtFirstName.setEditable(true);
            txtLastName.setEditable(true);
            txtPhone.setEditable(true);
            txtEmail.setEditable(true);
            txtTitle.setEditable(true);
            txtDescription.setEditable(true);
        }
        // set Ticket Number menue label
        mnuTicket.setText("Ticket Number: " + ticketNumber);
        // check ticket lock
        if (ticketLock!=null && ticketLock.matches("true"))// ticket locked
        {
            // update menu values
            mnuEdit.setVisible(false);
            mnuLock.setText("Locked");
        }
    }// end TicketFrame constructor

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupVehicle = new javax.swing.JPopupMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        popYear = new javax.swing.JPopupMenu();
        tbCustomer = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        btnSearchCustomer = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        cmbModel = new javax.swing.JComboBox<>();
        lblModel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbYear = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtVin = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTicket = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblTicketStatus = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        lblDescription = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblPriority = new javax.swing.JLabel();
        cmbAssignedTo = new javax.swing.JComboBox<>();
        lblAssignedTo = new javax.swing.JLabel();
        cmbPriority = new javax.swing.JComboBox<>();
        cmbStatus = new javax.swing.JComboBox<>();
        lblStatus = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        MenuBar = new javax.swing.JMenuBar();
        mnEdit = new javax.swing.JMenu();
        mnuDelete = new javax.swing.JMenuItem();
        mnuEdit = new javax.swing.JRadioButtonMenuItem();
        mnuLock = new javax.swing.JRadioButtonMenuItem();
        jMenu1 = new javax.swing.JMenu();
        mnuTicket = new javax.swing.JMenu();

        popupVehicle.setOpaque(false);

        jMenu2.setText("jMenu2");

        jMenuItem2.setText("jMenuItem2");
        jMenu2.add(jMenuItem2);

        popupVehicle.add(jMenu2);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(1500, 750));

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setText("Email");

        jLabel7.setText("Phone");

        jLabel5.setText("Last Name");

        jLabel4.setText("First Name");

        btnSearchCustomer.setText("Search");
        btnSearchCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchCustomerActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Customer Information");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearchCustomer))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                            .addComponent(txtLastName)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(btnSearchCustomer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblModel.setText("Model");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Vehicle Information");

        jLabel3.setText("Year");

        jLabel11.setText("VIN");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblModel))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbModel, 0, 375, Short.MAX_VALUE)
                            .addComponent(cmbYear, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtVin))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtVin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(160, 160, 160))
        );

        tbCustomer.addTab("Customer Information", jPanel1);

        tblTicket.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Ticket Number", "Status", "Priority", "Assigned To"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTicket.setEnabled(false);
        tblTicket.setRowHeight(30);
        jScrollPane3.setViewportView(tblTicket);
        if (tblTicket.getColumnModel().getColumnCount() > 0) {
            tblTicket.getColumnModel().getColumn(0).setPreferredWidth(60);
            tblTicket.getColumnModel().getColumn(0).setMaxWidth(80);
        }

        tbCustomer.addTab("Ticket Status", jScrollPane3);

        tblTicketStatus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Opened", null, null},
                {"Edited", null, null},
                {"Closed", null, null}
            },
            new String [] {
                "Status Log", "By", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblTicketStatus.setEnabled(false);
        tblTicketStatus.setRowHeight(30);
        jScrollPane7.setViewportView(tblTicketStatus);

        tbCustomer.addTab("Log", jScrollPane7);

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane1.setViewportView(txtDescription);

        lblDescription.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDescription.setText("Description");

        lblPriority.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPriority.setText("Priority");

        cmbAssignedTo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tech Support", "Engineering", "Engineering: Electrical", "Engineering: Hardware", "Engineering: Software", "Manager", "Administrator", "General" }));

        lblAssignedTo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAssignedTo.setText("Assigned To");

        cmbPriority.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "Urgent", "LongTerm" }));

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Open", "Closed", "Closed - Resolved", "Closed - Unresolved" }));

        lblStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblStatus.setText("Status");

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTitle.setText("Title");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbPriority, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPriority))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbAssignedTo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAssignedTo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStatus)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPriority)
                            .addComponent(lblAssignedTo)
                            .addComponent(lblStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbPriority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbAssignedTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lblDescription)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblCustomer);

        mnEdit.setText("Ticket");

        mnuDelete.setText("Delete Ticket");
        mnuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuDeleteActionPerformed(evt);
            }
        });
        mnEdit.add(mnuDelete);

        mnuEdit.setSelected(true);
        mnuEdit.setText("Edit Ticket");
        mnuEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEditActionPerformed(evt);
            }
        });
        mnEdit.add(mnuEdit);

        mnuLock.setSelected(true);
        mnuLock.setText("Lock Ticket");
        mnuLock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLockActionPerformed(evt);
            }
        });
        mnEdit.add(mnuLock);

        MenuBar.add(mnEdit);

        jMenu1.setText("                                                                                                                                                                                                                                                    ");
        MenuBar.add(jMenu1);

        mnuTicket.setText("Ticket: ");
        mnuTicket.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        MenuBar.add(mnuTicket);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 231, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * btnSaveActionPerformed button for updating ticket information
     * @param evt button save clicked
     */
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // get vehicle information
        String year = cmbYear.getSelectedItem().toString();
        String model = cmbModel.getSelectedItem().toString();
        String vin = txtVin.getText();
        
        Boolean continueUpdate = true;
        
        // check for null vehicle values
        if (year==null||model==null||vin==null)// null
        {
            // display error option message
            Object[] options = {"Continue", "Cancel"};// dialog options
            int n = JOptionPane.showOptionDialog(this, "No vehicle information entered. Are you sure you want to update the ticket without vehicle information",
            "Missing Vehicle...",
            JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,
            options, options[1]);
            // check for user selection
            if (n==1)// for cancel selection
            {
                continueUpdate=false;
            }
        }
        // check for dialog selection
        if (continueUpdate==true)
        {
            // Instantiate new Date object
            Date dt = new Date();
            // format date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // get current date and time with format
            String currentTime = sdf.format(dt);
            // get ticket lock status
            String ticketLock = mnuLock.getText();
            
            //check ticket lock status
            if (ticketLock.matches("Unlocked")) // tick unlocked
            {
                ticketLock = "false";
            } else // ticket locked
            {
                ticketLock = "true";
            }
            // get ticket and customer information
            String ticketNumber = String.valueOf(tblTicket.getModel().getValueAt(0,0));
            String firstName = String.valueOf(tblTicket.getModel().getValueAt(0,1));
            String lastName = String.valueOf(tblTicket.getModel().getValueAt(0,2));
            String customerName = firstName + " " + lastName;// concatenate customer names creating full name string 
            String phone = txtPhone.getText();
            String email = txtEmail.getText();
            String title = txtTitle.getText();
            String description = txtDescription.getText();
            String status = cmbStatus.getSelectedItem().toString();
            String priority = cmbPriority.getSelectedItem().toString();
            String assignTo = cmbAssignedTo.getSelectedItem().toString();
            String openOn = String.valueOf(tblTicketStatus.getModel().getValueAt(0,2));
            String openBy = String.valueOf(tblTicketStatus.getModel().getValueAt(0,2));
            String editOn = currentTime;
            String editBy = userID;
            String closedOn = String.valueOf(tblTicketStatus.getModel().getValueAt(2,2));
            String closeBy = String.valueOf(tblTicketStatus.getModel().getValueAt(2,2));

            // check ticket for new department assignment
            if (!assignTo.matches(assignedTo))
            {
                assignOn = currentTime;// set assignment time
            }

            // check for closed ticket status
            if (status.matches("Closed")) {
                if (ticketClosed == false) {
                    closedOn = currentTime;// set close time as current time
                    closeBy = userID;// set close by as user
                }
            }
            // instantiate new QueryTicket object
            QueryTicket qt = new QueryTicket();
            // update ticket
            qt.updateTicket(
                    userID,ticketNumber,
                    customerName,firstName,lastName,
                    status,priority,
                    assignTo,openBy,editBy,closeBy,
                    assignOn,openOn,editOn,closedOn,
                    title,description,vin,ticketLock,
                    year,model,
                    phone,email);
            // show successful update message
            JOptionPane.showMessageDialog(this, "New ticket was successfully inserted");
            // close ticket frame
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_btnSaveActionPerformed
    //end btnSaveActionPerformed method
    
    /**
     * btnSearchCustomerActionPerformed search button listener for searching for 
     * customer name
     * @param evt search button clicked
     */
    private void btnSearchCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchCustomerActionPerformed
        // get customer information
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();

        // check for null input for names
        if (firstName!=null && lastName!=null)// not null
        {
            // instantiate new QueryTicket object
            QueryTicket qt = new QueryTicket();
            // search for customer and get table model
            qt.selectCustomer(firstName, lastName, phone, email);
            DefaultTableModel model = qt.getModel();
            // check for null table model
            if (model != null) {
                // update table
                tblCustomer.setModel(model);
                tblCustomer.setAutoCreateRowSorter(true);
                tblCustomer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                tblCustomer.getSelectionModel().addListSelectionListener(this.tblCustomer);
                tblCustomer.repaint();
            } 
            else {// not found in database
                JOptionPane.showMessageDialog(this, "Customer not found.");
            }
        } else {// null user input
            JOptionPane.showMessageDialog(this, "First and last name cannot be empty.");
        }
    }//GEN-LAST:event_btnSearchCustomerActionPerformed
    //end btnSearchCustomerActionPerformed method
    
    /**
     * mnuDeleteActionPerformed delete button listener to delete ticket
     * @param evt delete button clicked
     */
    private void mnuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuDeleteActionPerformed
        // show confirmation dialog
        Object[] options = {"Continue", "Cancel"};
        int n = JOptionPane.showOptionDialog(this, "Are you sure you want to delete this ticket?",
            "Confirm Delete Ticket...",
            JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,
            options,options[1]);
        // check for user selection
        if (n == 1) {// cancel
            return;
        }
        else// continue 
        {
            // instantiate new QueryTicket object
            QueryTicket qt = new QueryTicket();
            // delete ticket
            qt.deleteTicket(ticketNumber);
            // show success dialog
            JOptionPane.showMessageDialog(this, "Ticket number " + ticketNumber + " was successfully Deleted");
            // close frame
            setVisible(false);
            dispose();
        }
    }//GEN-LAST:event_mnuDeleteActionPerformed
    //end mnuDeleteActionPerformed method
    
    /**
     * mnuEditActionPerformed edit button listener for enabling and disabling
     * editing mode
     * @param evt edit ticker menu button selected 
     */
    private void mnuEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEditActionPerformed
        String text = mnuEdit.getText();
        setVisibility(text);
    }//GEN-LAST:event_mnuEditActionPerformed
    //end mnuEditActionPerformed method
    
    /**
     * mnuLockActionPerformed 
     * @param evt 
     */
    private void mnuLockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLockActionPerformed
            
    }//GEN-LAST:event_mnuLockActionPerformed
    //end mnuLockActionPerformed method
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearchCustomer;
    private javax.swing.JComboBox<String> cmbAssignedTo;
    public static javax.swing.JComboBox<String> cmbModel;
    private javax.swing.JComboBox<String> cmbPriority;
    private javax.swing.JComboBox<String> cmbStatus;
    public static javax.swing.JComboBox<String> cmbYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblAssignedTo;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblModel;
    private javax.swing.JLabel lblPriority;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JMenu mnEdit;
    private javax.swing.JMenuItem mnuDelete;
    private javax.swing.JRadioButtonMenuItem mnuEdit;
    private javax.swing.JRadioButtonMenuItem mnuLock;
    private javax.swing.JMenu mnuTicket;
    private javax.swing.JPopupMenu popYear;
    private javax.swing.JPopupMenu popupVehicle;
    private javax.swing.JTabbedPane tbCustomer;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTable tblTicket;
    private javax.swing.JTable tblTicketStatus;
    private javax.swing.JTextArea txtDescription;
    public static javax.swing.JTextField txtEmail;
    public static javax.swing.JTextField txtFirstName;
    public static javax.swing.JTextField txtLastName;
    public static javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtTitle;
    public static javax.swing.JTextField txtVin;
    // End of variables declaration//GEN-END:variables

    /**
     * updateCombos method updates the combo box lists
     */
    private void updateCombos()
    {
        // instantiate new QueryData object
        QueryData qd = new QueryData();
        // set year and model arrays
        qd.selectModelYears();
        // get year and model list
        String[] yearList = qd.getVehicleYears();
        String[] modelList = qd.getVehicleModels();
        // update year and model lists
        cmbYear.setModel(new DefaultComboBoxModel<>(qd.getVehicleYears()));
        cmbModel.setModel(new DefaultComboBoxModel<>(qd.getVehicleModels()));
    }// end updateCombos method
    
    /**
     * setVisibility method for switching between write and read only modes
     * @param text current text for edit menu button
     */
    private void setVisibility(String text)
    {
        // check for editing mode
        if (text.matches("Edit")) // enable editing mode
        {   
            // indicate edit mode by changing text
            mnuEdit.setText("Exit Editing Mode");
            //set visibility
            mnuDelete.setVisible(true);
            btnSave.setVisible(true);
            cmbStatus.setVisible(true);
            cmbPriority.setVisible(true);
            cmbAssignedTo.setVisible(true);
            lblStatus.setVisible(true);
            lblPriority.setVisible(true);
            lblAssignedTo.setVisible(true);
            // set Editible
            cmbStatus.setEditable(true);
            cmbPriority.setEditable(true);
            cmbAssignedTo.setEditable(true);
            txtFirstName.setEditable(true);
            txtLastName.setEditable(true);
            txtPhone.setEditable(true);
            txtEmail.setEditable(true);
            txtTitle.setEditable(true);
            txtDescription.setEditable(true);
        }
        else // disable editing modes
        {
            // indicate edit mode is disabled with text
            mnuEdit.setText("Edit");
            //set visibility
            mnuDelete.setVisible(false);
            btnSave.setVisible(false);          
            cmbStatus.setVisible(false);
            cmbPriority.setVisible(false);
            cmbAssignedTo.setVisible(false);
            lblStatus.setVisible(false);
            lblPriority.setVisible(false);
            lblAssignedTo.setVisible(false);
            // set editible
            cmbStatus.setEditable(false);
            cmbPriority.setEditable(false);
            cmbAssignedTo.setEditable(false);
            txtFirstName.setEditable(false);
            txtLastName.setEditable(false);
            txtPhone.setEditable(false);
            txtEmail.setEditable(false);
            txtTitle.setEditable(false);
            txtDescription.setEditable(false);
        }
    }// end setVisibility method
}// end TicketFrame class
