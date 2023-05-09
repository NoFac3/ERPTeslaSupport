
package Admin;

import Query.QueryAccount;

/**
 * DefaultPasswordDialog.java - dialog with form for setting new default password
 * CSIS 643 - D01
 * @author Kore Woody
 */
public class DefaultPasswordDialog extends javax.swing.JDialog {

    /**
     * Creates new form DefaultPasswordDialog
     * @param parent
     * @param modal
     */
    public DefaultPasswordDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        lblResults.setVisible(false);
    }// end DefaultPasswordDialog constructor

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtPassword1 = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        txtPassword2 = new javax.swing.JPasswordField();
        btnCancel = new javax.swing.JButton();
        btnSet = new javax.swing.JButton();
        lblResults = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Default Password");
        setLocationByPlatform(true);
        setResizable(false);

        jLabel1.setText("Enter new default password...");

        txtPassword1.setText("jPasswordField1");

        jLabel2.setText("Confirm Default Password");

        txtPassword2.setText("jPasswordField2");

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnSet.setText("Set");
        btnSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetActionPerformed(evt);
            }
        });

        lblResults.setText("Results");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSet)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(txtPassword2)
                            .addComponent(txtPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblResults)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblResults)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnSet))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    /**
     * btnCancelActionPerformed cancel button listener
     * @param evt 
     */
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {                                          
        this.dispose();
    }                                         
    // end btnCancelActionPerformed
    
    /**
     * btnSetActionPerformed set button listener for setting new default password
     * @param evt 
     */
    private void btnSetActionPerformed(java.awt.event.ActionEvent evt) {                                       
       // get default passwords
        String pass1 = new String(txtPassword1.getPassword());
        String pass2 = new String(txtPassword2.getPassword());

        // check for null
        if (!pass1.matches("") && !pass2.matches("") && pass1!=null && pass2!=null)
        {
            // check for matching 
            if (pass1.matches(pass2))
            {
                // instantiate new QueryAccount object
                QueryAccount qa = new QueryAccount();
                // update password
                qa.setDefaultPassID(pass1);
                // show results
                lblResults.setText("Default Password Updated Successfully");
                lblResults.setVisible(true);
            }
            else// dont match
            {
                lblResults.setText("Invalid Input: Values don't match!");
                lblResults.setVisible(true);
            }
        }
        else// null values
        {
            lblResults.setText("Invalid Input: Values cannot be empty!");
            lblResults.setVisible(true);
        }
    }                                      
    // end btnSetActionPerformed

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblResults;
    private javax.swing.JPasswordField txtPassword1;
    private javax.swing.JPasswordField txtPassword2;
    // End of variables declaration                   
}//end DefaultPasswordDialog class
