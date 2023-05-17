package Messaging;

import Query.QueryMessages;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 * MessageSearchFrame.java - frame used by MessageFrame for search fro user IDs
 * CSIS 643 - D01
 * @author NoFac3
 */
public class MessageSearchFrame extends JFrame {

    public static String ID;
    /**
     * Creates new form MessageSearchFrame
     */
    public MessageSearchFrame() {
        initComponents();
    }// end constructor

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(1000, 1000));

        jLabel1.setText("Search Name");

        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNameKeyPressed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
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
        tblUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsers);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSearch))
                    .addComponent(txtName))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * btnSearchActionPerformed search button listener
     * @param evt 
     */
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String name = txtName.getText();
        
        if (name!=null)// check for null input
        {
            // initialize new QueryMessages object
            QueryMessages qm = new QueryMessages();
            qm.selectUsers(name);
            DefaultTableModel model = qm.getUserModel();
            
            // update table
            tblUsers.setAutoCreateRowSorter(true);
            tblUsers.setModel(model);
            tblUsers.repaint();
            tblUsers.getSelectionModel().addListSelectionListener(this.tblUsers);
        }
    }//GEN-LAST:event_btnSearchActionPerformed
    //end btnSearchActionPerformed method
    
    /**
     * tblUsersMouseClicked mouse listener for table row selection
     * @param evt 
     */
    private void tblUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsersMouseClicked
        int row = tblUsers.getSelectedRow();
        
        if (row!=-1)// row selected
        {
            // get userID from row
            String userID = tblUsers.getModel().getValueAt(row, 2).toString();
            this.ID = userID;
            // update message frame
            MessageFrame.txtTo.setText(ID);
            this.dispose();// close frame
        }
    }//GEN-LAST:event_tblUsersMouseClicked
    //end tblUsersMouseClicked method
    
    /**
     * txtNameKeyPressed key listener for enter key
     * @param evt 
     */
    private void txtNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyPressed
        // TODO add your handling code here:
        int key = evt.getKeyCode(); // get key value
        
        if (key==java.awt.event.KeyEvent.VK_ENTER) // key is enter
        {
            btnSearch.doClick();// click button
        }
    }//GEN-LAST:event_txtNameKeyPressed
    //end txtNameKeyPressed method

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUsers;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}// end MessageSearchFrame class
