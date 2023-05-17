package Account;

import Engineer.GuideFrame;
import Query.QueryGuide;
import javax.swing.JFrame;

/**
 * EngineerInternalFrame.java - for users with engineering permissions. 
 * CSIS 643 - D01
 * @author Kore Woody
 */
public class EngineerInternalFrame extends javax.swing.JInternalFrame {

    /**
     * Creates new form EngineeringInternalFrame
     * @param userID
     * @param firstName
     * @param name
     * @param lastName
     * @param isAnalyst
     * @param isAdmin
     * @param isTech
     * @param department
     * @param isEngineer
     * @param isManager
     */
    public EngineerInternalFrame(String userID, String name, 
            String firstName, String lastName, String department, 
            boolean isAdmin, boolean isManager, boolean isEngineer, 
            boolean isAnalyst, boolean isTech) {
        super("Guide",true,false,true);
        initComponents();
        
        // start up with all guides
        QueryGuide qg = new QueryGuide();
        qg.selectGuides();
        epGuide.setText(qg.getSolution()); 
        
        // set editability and permission accessable content
        epGuide.setEditable(false);
        mnuEdit.setVisible(isEngineer);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        txtErrorCode = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        epGuide = new javax.swing.JEditorPane();
        menuBar = new javax.swing.JMenuBar();
        mnuEdit = new javax.swing.JMenu();
        mnuAddGuide = new javax.swing.JMenuItem();
        mnuEditGuide = new javax.swing.JMenuItem();

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        txtErrorCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtErrorCodeKeyPressed(evt);
            }
        });

        jLabel1.setText("Enter Error Code");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtErrorCode, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch)
                    .addComponent(txtErrorCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        epGuide.setContentType("text/html"); // NOI18N
        epGuide.setText("");
        jScrollPane1.setViewportView(epGuide);

        mnuEdit.setText("Edit");

        mnuAddGuide.setText("Add Guide");
        mnuAddGuide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAddGuideActionPerformed(evt);
            }
        });
        mnuEdit.add(mnuAddGuide);

        mnuEditGuide.setText("Edit Guide");
        mnuEditGuide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEditGuideActionPerformed(evt);
            }
        });
        mnuEdit.add(mnuEditGuide);

        menuBar.add(mnuEdit);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * txtErrorCodeKeyPressed key listener for enter key
     * @param evt 
     */
    private void txtErrorCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtErrorCodeKeyPressed
        int key = evt.getKeyCode(); // get key value

        if (key==java.awt.event.KeyEvent.VK_ENTER) // key is enter
        {
            btnSearch.doClick();// click button
        }
    }//GEN-LAST:event_txtErrorCodeKeyPressed
    // end txtErrorCodeKeyPressed
    
    /**
     * btnSearchActionPerformed method search button listener
     * @param evt 
     */
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String errorCode = txtErrorCode.getText();// get guide
        //instantiate new QueryGuide object
        QueryGuide qg = new QueryGuide();
        // check or null input
        if (!errorCode.matches(""))// not null
        {
            // get and display guide
            qg.selectGuide(errorCode);
            epGuide.setText(qg.getSolution()); 
        }
        else// null
        {
            // get and display all guides
            qg.selectGuides();
            epGuide.setText(qg.getSolution()); 
        }
    }//GEN-LAST:event_btnSearchActionPerformed
    //end btnSearchActionPerformed method
    
    /**
     * mnuAddGuideActionPerformed method edit guide button listener
     * @param evt 
     */
    private void mnuEditGuideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEditGuideActionPerformed
        //get guide
        String guide = epGuide.getText();
        // open guide frame
        JFrame frame = new GuideFrame(false,guide);
        frame.setLocation(this.getLocation());
        frame.setVisible(true);
    }//GEN-LAST:event_mnuEditGuideActionPerformed
    //end mnuAddGuideActionPerformed method
    
    /**
     * mnuAddGuideActionPerformed method add guide button listener
     * @param evt 
     */
    private void mnuAddGuideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAddGuideActionPerformed
       // gopen guide frame
       JFrame frame = new GuideFrame(true,null);
        frame.setLocation(this.getLocation());
        frame.setVisible(true);
       
    }//GEN-LAST:event_mnuAddGuideActionPerformed
    //end mnuAddGuideActionPerformed method

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JEditorPane epGuide;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem mnuAddGuide;
    private javax.swing.JMenu mnuEdit;
    private javax.swing.JMenuItem mnuEditGuide;
    private javax.swing.JTextField txtErrorCode;
    // End of variables declaration//GEN-END:variables
}// end EngineerInternalFrame class