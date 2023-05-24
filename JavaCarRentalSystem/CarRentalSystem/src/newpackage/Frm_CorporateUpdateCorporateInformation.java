/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package newpackage;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.nio.file.Paths;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static newpackage.Frm_CustomerUpdateCustomerInformation.DB_URL;
import static newpackage.Frm_CustomerUpdateCustomerInformation.PASS;
import static newpackage.Frm_CustomerUpdateCustomerInformation.USER;

/**
 *
 * @author mustafa
 */
public class Frm_CorporateUpdateCorporateInformation extends javax.swing.JFrame {

    /**
     * Creates new form Frm_CorporateUpdateCorporateInformation
     */
    public Frm_CorporateUpdateCorporateInformation() {
        initComponents();
        addWindowListener(new WindowHandler());
    }

    
    
    private class WindowHandler implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e) {
            getData();
            start();
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
        String loginname = Frm_Corporate.name;
        lbl_LoginName.setText(loginname);
        path = Paths.get("").toAbsolutePath().toString();
            imgpath = path + "\\images\\" + Frm_Corporate.ID + ".png";
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
    
    
    private void getData(){
        Connection connection = null;
        PreparedStatement preparedstatement = null; 
        ResultSet set = null;
        
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            
            String query = "select * from users where identityNumber='" + Frm_Corporate.ID + "'";
            preparedstatement = connection.prepareStatement(query);
            set = preparedstatement.executeQuery();
            
            while (set.next()){
                txt_ID.setText(set.getString("identityNumber"));
                txt_Name.setText(set.getString("name"));
                txt_Username.setText(set.getString("username"));
                txt_Password.setText(set.getString("password"));
                txt_Email.setText(set.getString("email"));
                
                java.util.Date date;
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(set.getString("date"));
                    dateTimePicker_Date.setDate(date);
                } catch (ParseException ex) {
                    Logger.getLogger(Frm_AdminUpdateUser.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error occurred while getting the data from database", "Isim", JOptionPane.ERROR_MESSAGE);          

            
        }
    }
    
    
    private void clear(){
        txt_Name.setText("");        
        txt_Username.setText("");
        txt_Password.setText("");
        dateTimePicker_Date.setCalendar(null);
        txt_Email.setText("");        
        
    }
    
    private void update() throws SQLException{
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        String gender = null;
        String authority = "Corporate";
        String date = null;
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.format(dateTimePicker_Date.getDate());
        
        User user = new User(uuid, txt_ID.getText().toString(), txt_Name.getText().toString(), "", gender, authority, txt_Username.getText().toString(), txt_Password.getText().toString(), "", date, txt_Email.getText().toString());
        
        Connection connection = null;
        PreparedStatement preparedstatement = null; 
        ResultSet set = null;
        
        
        if(txt_ID.getText().length() < 11 ){
                lbl_ID.setForeground(Color.RED);
                txt_ID.setBackground(Color.RED);
            }
            else{
                lbl_ID.setForeground(Color.BLACK);
                txt_ID.setBackground(null);
            }
            
            if (txt_Name.getText().length() < 2 || txt_Name.getText() == ""){
                lbl_Name.setForeground(Color.RED);
                txt_Name.setBackground(Color.RED);
            }
            else {
                lbl_Name.setForeground(Color.BLACK);
                txt_Name.setBackground(null);
            }
            if (txt_Username.getText() == ""){
                lbl_Username.setForeground(Color.RED);
                txt_Username.setBackground(Color.RED);
            }
            else {
                lbl_Username.setForeground(Color.BLACK);
                txt_Username.setBackground(null);
            }
            
            if (txt_Password.getText() == ""){
                lbl_Password.setForeground(Color.RED);
                txt_Password.setBackground(Color.RED);
            }
            else {
                lbl_Password.setForeground(Color.BLACK);
                txt_Password.setBackground(null);
            }
            
            if (txt_Email.getText() == ""){
                lbl_Email.setForeground(Color.RED);
                txt_Email.setBackground(Color.RED);
            }
            else {
                lbl_Email.setForeground(Color.BLACK);
                txt_Email.setBackground(null);
            }
            
            
            if(txt_ID.getText().length() == 11 && txt_ID.getText() != "" && txt_Name.getText().length() >= 2 && txt_Name.getText() != ""  && txt_Username.getText() != "" && txt_Password.getText() != "" && txt_Email.getText() != ""){
                try{
                    String driver = "com.mysql.cj.jdbc.Driver";
                    Class.forName(driver);
                    connection = DriverManager.getConnection(DB_URL, USER, PASS);
                    
                    String query = "update users set identityNumber=?, name=?, username=?, password=?, date=?, email=? where identityNumber=?";
                    preparedstatement = connection.prepareStatement(query);
                    
                    preparedstatement.setString(1, user.getIdentityNumber());
                    preparedstatement.setString(2, user.getName());
                    preparedstatement.setString(3, user.getUsername());
                    preparedstatement.setString(4, user.getPassword());
                    preparedstatement.setString(5, date);
                    preparedstatement.setString(6, user.getEmail());
                    preparedstatement.setString(7, user.getIdentityNumber());
                    
                    preparedstatement.executeUpdate();     
                    
                    connection.close();
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Error occurred while updating.", "Isim", JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(this, e.getMessage());
                    connection.close();
                    
                }
                
                
                
                
                
                // Kiralayanlar tablosunda da değişiklik yapmayı unutma
                
                
                
            }
            else{
                JOptionPane.showMessageDialog(null, "Please fix the incorrect parts.", "Isim", JOptionPane.ERROR_MESSAGE);
            }
        
        
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_ID = new javax.swing.JLabel();
        lbl_Name = new javax.swing.JLabel();
        lbl_Username = new javax.swing.JLabel();
        lbl_Date = new javax.swing.JLabel();
        lbl_Password = new javax.swing.JLabel();
        lbl_Email = new javax.swing.JLabel();
        txt_ID = new javax.swing.JTextField();
        txt_Name = new javax.swing.JTextField();
        txt_Email = new javax.swing.JTextField();
        txt_Username = new javax.swing.JTextField();
        dateTimePicker_Date = new com.toedter.calendar.JDateChooser();
        btn_Clear = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        btn_Back = new javax.swing.JButton();
        btn_Exit = new javax.swing.JButton();
        lbl_LoginPicture = new javax.swing.JLabel();
        lbl_LoginName = new javax.swing.JLabel();
        btn_SignOut = new javax.swing.JButton();
        btn_ShowPassword = new javax.swing.JCheckBox();
        txt_Password = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_ID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_ID.setText("ID:");

        lbl_Name.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Name.setText("Name:");

        lbl_Username.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Username.setText("Username:");

        lbl_Date.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Date.setText("Date:");

        lbl_Password.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Password.setText("Password:");

        lbl_Email.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Email.setText("E-mail:");

        txt_ID.setEnabled(false);

        btn_Clear.setText("Clear");
        btn_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClearActionPerformed(evt);
            }
        });

        btn_Update.setText("Update");
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
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

        lbl_LoginPicture.setText("jLabel1");

        lbl_LoginName.setText("jLabel2");

        btn_SignOut.setText("Sign Out");
        btn_SignOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SignOutActionPerformed(evt);
            }
        });

        btn_ShowPassword.setText("Show Password");
        btn_ShowPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ShowPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lbl_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(dateTimePicker_Date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lbl_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btn_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_Username, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_Username, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_ShowPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_LoginPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_LoginName, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(35, 35, 35))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btn_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_SignOut, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Exit)
                            .addComponent(btn_Back)
                            .addComponent(btn_SignOut))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_ID)
                            .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_Name)
                            .addComponent(txt_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_Username)
                            .addComponent(txt_Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_Password)
                            .addComponent(btn_ShowPassword)
                            .addComponent(txt_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(lbl_LoginPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_LoginName)))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_Date)
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_Email)
                            .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(dateTimePicker_Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Clear)
                    .addComponent(btn_Update))
                .addGap(51, 51, 51))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BackActionPerformed
        // TODO add your handling code here:
        Frm_CorporateLogin login = new Frm_CorporateLogin();
        setVisible(false);
        login.setVisible(true);
    }//GEN-LAST:event_btn_BackActionPerformed

    private void btn_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btn_ClearActionPerformed

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        try {
            // TODO add your handling code here:
            update();
        } catch (SQLException ex) {
            Logger.getLogger(Frm_CorporateUpdateCorporateInformation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_UpdateActionPerformed

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

    private void btn_ShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ShowPasswordActionPerformed
        // TODO add your handling code here:
        
        if (btn_ShowPassword.isSelected()){
            txt_Password.setEchoChar((char)0);
        }
        else {
            txt_Password.setEchoChar('*');
        }
    }//GEN-LAST:event_btn_ShowPasswordActionPerformed

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
            java.util.logging.Logger.getLogger(Frm_CorporateUpdateCorporateInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_CorporateUpdateCorporateInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_CorporateUpdateCorporateInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_CorporateUpdateCorporateInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_CorporateUpdateCorporateInformation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Back;
    private javax.swing.JButton btn_Clear;
    private javax.swing.JButton btn_Exit;
    private javax.swing.JCheckBox btn_ShowPassword;
    private javax.swing.JButton btn_SignOut;
    private javax.swing.JButton btn_Update;
    private com.toedter.calendar.JDateChooser dateTimePicker_Date;
    private javax.swing.JLabel lbl_Date;
    private javax.swing.JLabel lbl_Email;
    private javax.swing.JLabel lbl_ID;
    private javax.swing.JLabel lbl_LoginName;
    private javax.swing.JLabel lbl_LoginPicture;
    private javax.swing.JLabel lbl_Name;
    private javax.swing.JLabel lbl_Password;
    private javax.swing.JLabel lbl_Username;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_ID;
    private javax.swing.JTextField txt_Name;
    private javax.swing.JPasswordField txt_Password;
    private javax.swing.JTextField txt_Username;
    // End of variables declaration//GEN-END:variables
}
