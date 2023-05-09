package Account;

import Query.QueryMessages;
import javax.swing.JOptionPane;
import java.sql.SQLException;

/**
 * ForgotUsernameFrame.java - frame displays form for submitting a username and
 * password reset 
 * CSIS 643 - D01
 * @author Kore Woody
 */
public class ForgotUsernameFrame extends javax.swing.JFrame {

    /**
     * Creates new form ForgotUsernameFrame
     */
    public ForgotUsernameFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        btnSendAccountRequest = new javax.swing.JButton();
        txtFirstName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        cmbDepartment = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnSendAccountRequest.setText("Send Account Request");
        btnSendAccountRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendAccountRequestActionPerformed(evt);
            }
        });

        jLabel1.setText("First Name");

        jLabel2.setText("Last Name");

        cmbDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select your department...", "Tech Support", "Engineering", "Engineering: Electrical", "Engineering: Hardware", "Engineering: Software", "Manager", "Administrator", "General" }));

        jLabel3.setText("Department");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbDepartment, 0, 265, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSendAccountRequest))
                    .addComponent(txtLastName)
                    .addComponent(txtFirstName)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(btnSendAccountRequest)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    /**
     * btnSendAccountRequestActionPerformed listener for send request button
     * @param evt 
     */
    private void btnSendAccountRequestActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        // get first and last name
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        // combine first and last name for query
        String name = firstName + " " + lastName;
        // get department
        String department = cmbDepartment.getSelectedItem().toString();
        // check for null input
        if (!firstName.matches("") || !lastName.matches("") || !department.matches("Select your department..."))
        {
            //instantialize new QueryMessages object
            QueryMessages qm = new QueryMessages();
            try{
                // create and insert request message
                qm.insertResetRequest(null,name, department,"Username and Password", false);
                // show success dialog
                JOptionPane.showMessageDialog(rootPane, "Message Sent to your department Manager.", "Request Sent", JOptionPane.OK_OPTION);
                this.setVisible(false);
                this.dispose();
            } catch (SQLException e){// exception thrown
                // display error message for null results
                JOptionPane.showMessageDialog(rootPane, "Invalid Input. Please contact your Manager or Administrator.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, "Invalid Input. Values cannot be null.", "Missing Values", JOptionPane.ERROR_MESSAGE);
        }
    }                                                     
    // end btnSendAccountRequestActionPerformed method

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnSendAccountRequest;
    private javax.swing.JComboBox<String> cmbDepartment;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    // End of variables declaration                   
}// end ForgotUsernameFrame class
