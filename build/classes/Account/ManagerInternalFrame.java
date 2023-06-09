package Account;

import Query.QueryTicketLog;
import javax.swing.table.DefaultTableModel;

/**
 * ManagerInternalFrame.java - for users with manager permissions. Shows the logs
 * for users and their activity within their department.
 * CSIS 643 - D01
 * @author Kore Woody
 */
public class ManagerInternalFrame extends javax.swing.JInternalFrame {

    String userID, department;
    boolean isManager;
    /**
     * Creates new form ManagerInternalFrame
     * @param userID
     * @param department
     * @param isManager
     */
    public ManagerInternalFrame(String userID, String department, boolean isManager) {
        super("",true,false,true);
        initComponents();
        
        // set user values
        this.userID = userID;
        this.department = department;
        this.isManager = isManager;
        // set title
        setTitle(department + ": Logs");
        //update tables
        refresh();
        this.pack();
        
        // instantiate new Account notification
        AccountNotification notify = new AccountNotification(this);
    }

    /**
     * refresh method updates the table data
     */
    public void refresh() 
    {
        // instantiate new QueryTicketLog object
        QueryTicketLog qtl = new QueryTicketLog();
        // execute log query
        qtl.selectTicketLog(userID, department, isManager);

        // get today model
        DefaultTableModel model = qtl.getTicketLogModelToday();
        if (model != null) {
            tblLogToday.setAutoCreateRowSorter(true);
            tblLogToday.setModel(model);
            tblLogToday.repaint();
            tblLogToday.getSelectionModel().addListSelectionListener(this.tblLogToday);
        }

        // get week model
        model = qtl.getTicketLogModelWeek();
        if (model != null) {
            tblLogWeek.setAutoCreateRowSorter(true);
            tblLogWeek.setModel(model);
            tblLogWeek.repaint();
            tblLogWeek.getSelectionModel().addListSelectionListener(this.tblLogWeek);
        }

        // get month model
        model = qtl.getTicketLogModelMonth();
        if (model != null) {
            tblLogMonth.setAutoCreateRowSorter(true);
            tblLogMonth.setModel(model);
            tblLogMonth.repaint();
            tblLogMonth.getSelectionModel().addListSelectionListener(this.tblLogMonth);
        }
        //get year model
        model = qtl.getTicketLogModelYear();
        if (model != null) {
            tblUsers.setAutoCreateRowSorter(true);
            tblUsers.setModel(model);
            tblUsers.repaint();
            tblUsers.getSelectionModel().addListSelectionListener(this.tblUsers);

        }
    }// end refresh method
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblLogMonth = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLogWeek = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblLogToday = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        tblLogMonth.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Value", "#"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLogMonth.setRowHeight(30);
        jScrollPane1.setViewportView(tblLogMonth);

        tblLogWeek.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Value", "#"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLogWeek.setRowHeight(30);
        jScrollPane2.setViewportView(tblLogWeek);

        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Value", "#"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsers.setRowHeight(30);
        jScrollPane3.setViewportView(tblUsers);

        tblLogToday.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Value", "#"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLogToday.setRowHeight(30);
        jScrollPane4.setViewportView(tblLogToday);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblLogMonth;
    private javax.swing.JTable tblLogToday;
    private javax.swing.JTable tblLogWeek;
    private javax.swing.JTable tblUsers;
    // End of variables declaration//GEN-END:variables
}// end ManagerInternalFrame class
