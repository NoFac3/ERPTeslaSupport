package Messaging;

import Chronology.Chronology;
import Query.QueryMessages;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * MessageFrame.java - JFrame class called by MessagesInternalFrame for displaying 
 * internal messages and request system
 * CSIS 643 - D01
 * @author NoFac3
 */
public class MessageFrame extends JFrame {

    String messageID,userID, name, firstName, lastName, department;
    boolean isManager = false;
    boolean newMessage;
    /**
     * Creates new form MessageFrame
     * @param newMessage
     * @param lastName
     * @param messageKey
     * @param userID
     * @param firstName
     * @param name
     * @param isManager
     * @param department
     * @param isAdmin
     */
    public MessageFrame(boolean newMessage, String messageKey, String userID, String name, 
            String firstName, String lastName, String department, boolean isManager,boolean isAdmin) {
        initComponents();
        // set user id and manager permission
        this.userID = userID;
        this.isManager = isManager;
        this.messageID = messageKey;
        this.newMessage = newMessage;
        this.department = department;
        // check if new message
        if (newMessage==false)// existing message
        {
            // initialize new QueryMessage object
            QueryMessages qm = new QueryMessages();
            // query for message
            qm.selectMessage(messageKey);
            
            // get message values
            String to = qm.getSentID();
            String subject = qm.getSubject();
            String message = qm.getMessage();
            String thread = qm.getThread();
            String receiver = userID;
            String currentDateTime = qm.currentDateTime();
            String space = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            
            // format thread
            thread = thread + "<h3><span style=\"font-weight:bold\">\n"
                + "From: " + userID + "</a><br><sup>\n"
                + "To: " + receiver + "<br>Date: "
                + currentDateTime + "</span></sup></span><hr></h3><br>\n"
                + "<h4>Subject: " + subject + "</h4>"
                + "<p>"+message+"</p>";
            
            // get sent id
            String sentID = qm.getSentID();
            // populate text fields
            txtSubject.setText("Re:" + subject);
            txtTo.setText(sentID);
            txtThread.setText(thread);
            // update approval and type comboboxes
            cmbApproval.setSelectedItem(qm.getApproved());
            cmbType.setSelectedItem(qm.getMessageType());
            // change button text to reply
            btnSend.setText("Reply");   
        }
        else// new message
        {
            // set approval visibility
            cmbApproval.setEnabled(isManager);
            cmbApproval.setVisible(isManager);
            lblApproval.setVisible(isManager);
            // change button text to send
            btnSend.setText("Send");
        }

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
        txtTo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtSubject = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnSend = new javax.swing.JButton();
        btnQuit = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        cmbType = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbApproval = new javax.swing.JComboBox<>();
        lblApproval = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtThread = new javax.swing.JEditorPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtReply = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(1000, 1000));

        jLabel1.setText("To");

        jLabel2.setText("Subject");

        jLabel3.setText("Thread");

        btnSend.setText("Send");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        btnQuit.setText("Quit");

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        cmbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "General", "Request" }));

        jLabel4.setText("Message Type");

        cmbApproval.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Denied", "Approved" }));

        lblApproval.setText("Approval Status");

        txtThread.setEditable(false);
        txtThread.setContentType("text/html"); // NOI18N
        txtThread.setText("");
        jScrollPane2.setViewportView(txtThread);

        txtReply.setColumns(20);
        txtReply.setRows(5);
        jScrollPane1.setViewportView(txtReply);

        jLabel5.setText("Message");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSubject)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnQuit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSend))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(txtTo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 294, Short.MAX_VALUE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbApproval, 0, 153, Short.MAX_VALUE)
                            .addComponent(lblApproval, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblApproval))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbApproval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnQuit)
                    .addComponent(btnSend))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * btnSendActionPerformed search listener opens user search frame
     * @param evt 
     */
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        JFrame frame = new MessageSearchFrame();
        frame.setLocation(this.getLocation());
        frame.setVisible(true);
    }//GEN-LAST:event_btnSearchActionPerformed
    //end btnSendActionPerformed method

    /**
     * btnSendActionPerformed send button listener inserts message into database
     * @param evt 
     */
    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // initialize new QueryMessage object
        QueryMessages qm = new QueryMessages();
        // get message form text
        String receiver = txtTo.getText();
        String type = cmbType.getSelectedItem().toString();
        String subject = txtSubject.getText();
        String thread = txtThread.getDocument().toString();
        String message = txtReply.getText();
        // clear text fields
        
        
        // instantiate new chronology object
        Chronology c = new Chronology();
        // set current date and time
        String currentDate = c.getDate();
        String currentTime = c.getTime();
        String currentDateTime = c.getDateTime();
        // get approval
        String approval = cmbApproval.getSelectedItem().toString();
        boolean isApproved;
        
        // check appoval status and convert to boolean
        if (approval.matches("Approved"))
        {
            isApproved = true;
        }
        else// Denied
        {
            isApproved = false;
        }
        //thread = thread.replace("</body></html>","");
                
        // format thread message
        thread = thread.replace("</body></html>","")
                + "<h3><hr><hr><span style=\"font-weight:bold\">"
                + "From: " + userID + "</a><br><sup>"
                + "To: " + receiver + "<br>Date: "
                + currentDateTime + "</span></sup></span><hr></h3><br>\n"
                + "<h4>Subject: " + subject + "</h4>"
                + "<p>"+message+"</p><hr><hr><h3></body></html>";
        
        // display thread text
        txtThread.setText("");
        txtReply.setText("");
        txtThread.setEditable(true);
        txtThread.setText(thread);
        txtThread.setEditable(false);
        
        // insert messsage to database
        try {
            if (newMessage==true)
            {
                qm.insertMessage(userID,receiver,type,subject,thread,approval);
            }
            else
            {
                qm.updateMessage(
                        Integer.valueOf(messageID), type, userID, 
                        receiver, currentDate, currentTime, 
                        " ", " ", " ", " ", 
                        false, subject, message, type, 
                        isApproved, message, thread);
            }
        } 
        catch (SQLException ex) 
        {
            // display error message for null results
            JOptionPane.showMessageDialog(this, "Invalid Input. Please contact your Manager or Administrator.", "Error", JOptionPane.ERROR_MESSAGE);
        }//catch exception
    }//GEN-LAST:event_btnSendActionPerformed
    // end btnSendActionPerformed method
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQuit;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSend;
    private javax.swing.JComboBox<String> cmbApproval;
    private javax.swing.JComboBox<String> cmbType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblApproval;
    private javax.swing.JTextArea txtReply;
    private javax.swing.JTextField txtSubject;
    private javax.swing.JEditorPane txtThread;
    public static javax.swing.JTextField txtTo;
    // End of variables declaration//GEN-END:variables
}// end MessageFrame class
