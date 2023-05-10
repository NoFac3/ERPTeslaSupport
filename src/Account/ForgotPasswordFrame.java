package Account;

import Query.QueryMessages;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * ForgotPaswordFrame.java - frame displays form for submitting password reset 
 * request
 * CSIS 643 - D01
 * @author Kore Woody
 */
public class ForgotPasswordFrame extends javax.swing.JFrame {

    /**
     * Creates new form ForgotPasswordFrame
     */
    public ForgotPasswordFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUserName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnSendResetRequest = new javax.swing.JButton();
        btnForgotUsername = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtUserName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUserNameKeyPressed(evt);
            }
        });

        jLabel1.setText("Enter Username");

        btnSendResetRequest.setText("Send Reset Request");
        btnSendResetRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendResetRequestActionPerformed(evt);
            }
        });

        btnForgotUsername.setText("Forgot Username");
        btnForgotUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForgotUsernameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnForgotUsername)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSendResetRequest))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSendResetRequest)
                    .addComponent(btnForgotUsername))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * btnSendResetRequestActionPerformed listener for send button
     * @param evt 
     */
    private void btnSendResetRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendResetRequestActionPerformed
        String userName = txtUserName.getText();
        // check for null input
        if (!userName.matches(""))
        {
            // initialize new QueryMessages object
            QueryMessages qm = new QueryMessages();
            try{
                // insert message
                qm.insertResetRequest(userName,null, null,"Password", false);
                // show success dialog then close frame
                JOptionPane.showMessageDialog(rootPane, "Message Sent to your department Manager.", "Request Sent", JOptionPane.OK_OPTION);
                this.setVisible(false);
                this.dispose();
            } catch (SQLException e){// exception thrown
                JOptionPane.showMessageDialog(rootPane, "Invalid Input. Please contact your Manager or Administrator.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnSendResetRequestActionPerformed
    // end btnSendResetRequestActionPerformed method
    
    /**
     * btnForgotUsernameActionPerformed listener for forgot username button
     * @param evt 
     */
    private void btnForgotUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForgotUsernameActionPerformed
        //new ForgotUsernameFrame().setVisible(true);
        
        JFrame frmUsernamePassword = new ForgotUsernameFrame();
        frmUsernamePassword.setBounds(this.getX(), this.getY(), 300, 300);
        frmUsernamePassword.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btnForgotUsernameActionPerformed
    // end btnForgotUsernameActionPerformed method
    
    /**
     * txtUserNameKeyPressed key listener for enter key
     * @param evt 
     */
    private void txtUserNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserNameKeyPressed
        int key = evt.getKeyCode(); // get key value

        if (key==java.awt.event.KeyEvent.VK_ENTER) // key is enter
        {
            btnSendResetRequest.doClick();// click button
        }
    }//GEN-LAST:event_txtUserNameKeyPressed
    // end txtUserNameKeyPressed method

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnForgotUsername;
    private javax.swing.JButton btnSendResetRequest;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}// end ForgotPasswordFrame class
