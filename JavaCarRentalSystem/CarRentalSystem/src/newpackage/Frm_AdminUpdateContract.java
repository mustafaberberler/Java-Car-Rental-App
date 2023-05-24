/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package newpackage;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.nio.file.Paths;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static newpackage.Frm_AdminDeleteCarAdvert.DB_URL;
import static newpackage.Frm_AdminDeleteCarAdvert.PASS;
import static newpackage.Frm_AdminDeleteCarAdvert.USER;

/**
 *
 * @author mustafa
 */
public class Frm_AdminUpdateContract extends javax.swing.JFrame {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DOMAIN_NAME = "localhost:3306";
    static final String DB_NAME = "db_carrentalsystem";
    static final String DB_URL = "jdbc:mysql://" + DOMAIN_NAME + "/" + DB_NAME;
    
    static final String USER = "root";
    static final String PASS = "1234";
    
    /**
     * Creates new form Frm_AdminUpdateContract
     */
    public Frm_AdminUpdateContract() {
        initComponents();
        addWindowListener(new WindowHandler());
    }
    
    
    private class WindowHandler implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e) {
            start();
            try {
                showContract();
            } catch (SQLException ex) {
                Logger.getLogger(Frm_AdminUpdateContract.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void windowClosing(WindowEvent e) {
            
        }

        @Override
        public void windowClosed(WindowEvent e) {
            
        }

        @Override
        public void windowIconified(WindowEvent e) {
            
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            
        }

        @Override
        public void windowActivated(WindowEvent e) {
            
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            
        }
        
    }
    
    private void start(){
        String path;
        String imgpath;
        String loginname = Frm_Admin.name + " " + Frm_Admin.surname;
        lbl_LoginName.setText(loginname);
        path = Paths.get("").toAbsolutePath().toString();
            imgpath = path + "\\images\\" + Frm_Admin.ID + ".png";
            ImageIcon image = new ImageIcon(imgpath);
            Rectangle rec = lbl_LoginPicture.getBounds();
            Image scaledimage = image.getImage().getScaledInstance(rec.width, rec.height, Image.SCALE_SMOOTH);
            image = new ImageIcon(scaledimage);
            lbl_LoginPicture.setIcon(image);
            if (lbl_LoginPicture.getIcon() != null){
                path = Paths.get("").toAbsolutePath().toString();
            imgpath = path + "\\images\\nouser.png" ;
             image = new ImageIcon(imgpath);
           rec = lbl_LoginPicture.getBounds();
            scaledimage = image.getImage().getScaledInstance(rec.width, rec.height, Image.SCALE_SMOOTH);
            image = new ImageIcon(scaledimage);
            lbl_LoginPicture.setIcon(image);
            }
    }
    
    private void showContract() throws SQLException{
        boolean check = false;
        
        Connection connection = null;
        PreparedStatement preparedstatement = null; 
        ResultSet set = null;
        
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            
            String query = "select conditions from contract";
            preparedstatement = connection.prepareStatement(query);
            set = preparedstatement.executeQuery();
            
            
            
            while (set.next()){
                check = true;
                txt_Contract.append(set.getString("conditions") + "\n");
            }
            
            connection.close();
            if(check == false){
                JOptionPane.showMessageDialog(null, "No registered contracts were found in the system.", "Isim", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error occurred while getting the contract from database", "Isim", JOptionPane.ERROR_MESSAGE);
            connection.close();
        }
        
        
    }
    
    
    private void searchCondition(){
        boolean check = false;
        
        Connection connection = null;
        PreparedStatement preparedstatement = null; 
        ResultSet set = null;
        
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            
            String query = "select conditions from contract where number='" + txt_ConditionNumber.getText() + "'";
            
            preparedstatement = connection.prepareStatement(query);
            set = preparedstatement.executeQuery();
            
            while (set.next()){
                check = true;
                break;
            }
            connection.close();
            if (check == true){
                try{                    
                    Class.forName(driver);
                    connection = DriverManager.getConnection(DB_URL, USER, PASS);
                    
                    String query2 = "select conditions from contract where number='" + txt_ConditionNumber.getText() + "'";
                    preparedstatement = connection.prepareStatement(query);
                    set = preparedstatement.executeQuery();
                    
                    while(set.next()){
                        txt_Selected.setText(set.getString("conditions"));
                    }
                    connection.close();
                    txt_ConditionNumber.setEnabled(false);
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Error occurred while getting the condition from database.", "Isim", JOptionPane.ERROR_MESSAGE);
                    connection.close();
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "The condition you are looking for is not in the contract.", "Isim", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private void updateCondition() throws SQLException{
        Connection connection = null;
        PreparedStatement preparedstatement = null; 
        ResultSet set = null;
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            
            String query = "update contract set conditions=? where number=?";
            preparedstatement = connection.prepareStatement(query);
            
            preparedstatement.setString(1, txt_Selected.getText());
            preparedstatement.setString(2, txt_ConditionNumber.getText());
            
            preparedstatement.executeUpdate();                  
                    
            
            connection.close();
            
            txt_Contract.setText("");
            showContract();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error occurred while updating the condition.", "Isim", JOptionPane.ERROR_MESSAGE);
            connection.close();
        }
    }
    
    
    private void clear(){        
        txt_ConditionNumber.setText("");
        txt_Selected.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txt_Contract = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_ConditionNumber = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_Selected = new javax.swing.JTextArea();
        btn_Update = new javax.swing.JButton();
        btn_Clear = new javax.swing.JButton();
        btn_Search = new javax.swing.JButton();
        btn_Back = new javax.swing.JButton();
        btn_Exit = new javax.swing.JButton();
        lbl_LoginPicture = new javax.swing.JLabel();
        lbl_LoginName = new javax.swing.JLabel();
        btn_SignOut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txt_Contract.setColumns(20);
        txt_Contract.setRows(5);
        jScrollPane1.setViewportView(txt_Contract);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Enter the condition number that you want to update:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Update the condition:");

        txt_ConditionNumber.setToolTipText("");

        txt_Selected.setColumns(20);
        txt_Selected.setRows(5);
        jScrollPane2.setViewportView(txt_Selected);

        btn_Update.setText("Update");
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });

        btn_Clear.setText("Clear");
        btn_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClearActionPerformed(evt);
            }
        });

        btn_Search.setText("Search");
        btn_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SearchActionPerformed(evt);
            }
        });

        btn_Back.setText("Back");
        btn_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BackActionPerformed(evt);
            }
        });

        btn_Exit.setText("Exit");
        btn_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ExitActionPerformed(evt);
            }
        });

        lbl_LoginPicture.setText("jLabel3");

        lbl_LoginName.setText("jLabel4");

        btn_SignOut.setText("Sign Out");
        btn_SignOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SignOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(txt_ConditionNumber)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(lbl_LoginPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(lbl_LoginName, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(40, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_SignOut, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_Back))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Exit)
                            .addComponent(btn_SignOut))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txt_ConditionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_Search))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_Update)
                                    .addComponent(btn_Clear)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(lbl_LoginPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_LoginName)))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SearchActionPerformed
        // TODO add your handling code here:
        
        searchCondition();
    }//GEN-LAST:event_btn_SearchActionPerformed

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        try {
            // TODO add your handling code here:

            updateCondition();
        } catch (SQLException ex) {
            Logger.getLogger(Frm_AdminUpdateContract.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_UpdateActionPerformed

    private void btn_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClearActionPerformed
        // TODO add your handling code here:
        clear();
        txt_ConditionNumber.setEnabled(true);
    }//GEN-LAST:event_btn_ClearActionPerformed

    private void btn_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BackActionPerformed
        // TODO add your handling code here:
        
        Frm_AdminLogin login = new Frm_AdminLogin();
        setVisible(false);
        login.setVisible(true);
            
    }//GEN-LAST:event_btn_BackActionPerformed

    private void btn_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ExitActionPerformed
        // TODO add your handling code here:
        JFrame frame = new JFrame("EXIT");
        if(JOptionPane.showConfirmDialog(frame, "Do you really want to exit?", "EXIT", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_btn_ExitActionPerformed

    private void btn_SignOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SignOutActionPerformed
        // TODO add your handling code here:

        JFrame frame = new JFrame("SIGN OUT");
        if(JOptionPane.showConfirmDialog(frame, "Do you really want to sign out?", "SIGN OUT", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
            Welcome welcome = new Welcome();
            setVisible(false);
            welcome.setVisible(true);
        }
    }//GEN-LAST:event_btn_SignOutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frm_AdminUpdateContract.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_AdminUpdateContract.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_AdminUpdateContract.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_AdminUpdateContract.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_AdminUpdateContract().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Back;
    private javax.swing.JButton btn_Clear;
    private javax.swing.JButton btn_Exit;
    private javax.swing.JButton btn_Search;
    private javax.swing.JButton btn_SignOut;
    private javax.swing.JButton btn_Update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_LoginName;
    private javax.swing.JLabel lbl_LoginPicture;
    private javax.swing.JTextField txt_ConditionNumber;
    private javax.swing.JTextArea txt_Contract;
    private javax.swing.JTextArea txt_Selected;
    // End of variables declaration//GEN-END:variables
}
