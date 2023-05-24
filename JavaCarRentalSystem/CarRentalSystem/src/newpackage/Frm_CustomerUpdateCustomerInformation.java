/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package newpackage;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.nio.file.Paths;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static newpackage.Frm_AdminAddUser.DB_URL;
import static newpackage.Frm_AdminAddUser.PASS;
import static newpackage.Frm_AdminAddUser.USER;
import java.util.Date;
import java.util.UUID;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static newpackage.Frm_AdminUpdateUser.DB_URL;
import static newpackage.Frm_AdminUpdateUser.PASS;
import static newpackage.Frm_AdminUpdateUser.USER;
/**
 *
 * @author mustafa
 */
public class Frm_CustomerUpdateCustomerInformation extends javax.swing.JFrame {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DOMAIN_NAME = "localhost:3306";
    static final String DB_NAME = "db_carrentalsystem";
    static final String DB_URL = "jdbc:mysql://" + DOMAIN_NAME + "/" + DB_NAME;
    
    static final String USER = "root";
    static final String PASS = "1234";
    
    
    
    /**
     * Creates new form Frm_CustomerUpdateCustomerInformatioin
     */
    public Frm_CustomerUpdateCustomerInformation() {
        initComponents();
        addWindowListener(new WindowHandler());
        jRadioButton1.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                jRadioButton2.setSelected(false);
            }
            
        }                
        );
        
        
        jRadioButton2.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                jRadioButton1.setSelected(false);
            }
            
        }                
        );
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
        String loginname = Frm_Customer.name + " " + Frm_Customer.surname;
        lbl_LoginName.setText(loginname);
        path = Paths.get("").toAbsolutePath().toString();
            imgpath = path + "\\images\\" + Frm_Customer.ID + ".png";
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
            
            String query = "select * from users where identityNumber='" + Frm_Customer.ID + "'";
            
            preparedstatement = connection.prepareStatement(query);
            set = preparedstatement.executeQuery();
            
            while(set.next()){
                txt_ID.setText(set.getString("identityNumber"));
                txt_Name.setText(set.getString("name"));
                txt_Surname.setText(set.getString("surname"));
                if(set.getString("gender").equals("Male")){
                    jRadioButton2.setSelected(false);
                    jRadioButton1.setSelected(true);
                }
                else if (set.getString("gender").equals("Female")){
                    jRadioButton1.setSelected(false);
                    jRadioButton2.setSelected(true);
                }
                
                txt_Username.setText(set.getString("username"));
                txt_Password.setText(set.getString("password"));
                
                
                txt_Email.setText(set.getString("email"));
                
                Date date;
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
        txt_Surname.setText("");
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        txt_Username.setText("");
        txt_Password.setText("");
        dateTimePicker_Date.setCalendar(null);
        txt_Email.setText("");
    }
    
    
    private void update() throws SQLException{
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        String gender = null;
        String authority = "Customer";
        String date = null;
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.format(dateTimePicker_Date.getDate());
        
        User user = new User(uuid, txt_ID.getText().toString(), txt_Name.getText().toString(), txt_Surname.getText().toString(), gender, authority, txt_Username.getText().toString(), txt_Password.getText().toString(), "", date, txt_Email.getText().toString());
        
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
            
            if (txt_Surname.getText().length() < 2 || txt_Surname.getText() == ""){
                lbl_Surname.setForeground(Color.RED);
                txt_Surname.setBackground(Color.RED);
            }
            else {
                lbl_Surname.setForeground(Color.BLACK);
                txt_Surname.setBackground(null);
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
               
            
            if(txt_ID.getText().length() == 11 && txt_ID.getText() != "" && txt_Name.getText().length() >= 2 && txt_Name.getText() != "" && txt_Surname.getText().length() >= 2 && txt_Surname.getText() != "" && txt_Username.getText() != "" && txt_Password.getText() != "" && txt_Email.getText() != ""){
              // Eğer girilen her şey uygunsa  
                
                if (jRadioButton1.isSelected()){
                    user.setGender("Male");
                }
                else if (jRadioButton2.isSelected()){
                    user.setGender("Female");
                }
                
                
                
                
                try{  // güncelleme kısmı
                    String driver = "com.mysql.cj.jdbc.Driver";
                    Class.forName(driver);
                    connection = DriverManager.getConnection(DB_URL, USER, PASS);
                    
                    String query = "update users set identityNumber=?, name=?, surname=?, gender=?, authority=?, username=?, password=?, date=?, email=? where identityNumber=?";
                    preparedstatement = connection.prepareStatement(query);
                    
                    
                    preparedstatement.setString(1, user.getIdentityNumber());
                    preparedstatement.setString(2, user.getName());
                    preparedstatement.setString(3, user.getSurname());
                    preparedstatement.setString(4, user.getGender());
                    preparedstatement.setString(5, user.getAuthority());
                    preparedstatement.setString(6, user.getUsername());
                    preparedstatement.setString(7, user.getPassword());
                    preparedstatement.setString(8, date);
                    preparedstatement.setString(9, user.getEmail());
                    preparedstatement.setString(10, user.getIdentityNumber());
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
        lbl_Surname = new javax.swing.JLabel();
        lbl_Name = new javax.swing.JLabel();
        lbl_Gender = new javax.swing.JLabel();
        lbl_Password = new javax.swing.JLabel();
        lbl_Username = new javax.swing.JLabel();
        lbl_Date = new javax.swing.JLabel();
        lbl_Email = new javax.swing.JLabel();
        txt_ID = new javax.swing.JTextField();
        txt_Name = new javax.swing.JTextField();
        txt_Surname = new javax.swing.JTextField();
        txt_Username = new javax.swing.JTextField();
        txt_Email = new javax.swing.JTextField();
        dateTimePicker_Date = new com.toedter.calendar.JDateChooser();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        btn_Clear = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        btn_Exit = new javax.swing.JButton();
        btn_Back = new javax.swing.JButton();
        lbl_LoginPicture = new javax.swing.JLabel();
        lbl_LoginName = new javax.swing.JLabel();
        btn_SignOut = new javax.swing.JButton();
        txt_Password = new javax.swing.JPasswordField();
        btn_ShowPassword = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_ID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_ID.setText("ID:");

        lbl_Surname.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Surname.setText("Surname:");

        lbl_Name.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Name.setText("Name:");

        lbl_Gender.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Gender.setText("Gender");
        lbl_Gender.setToolTipText("");

        lbl_Password.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Password.setText("Password:");

        lbl_Username.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Username.setText("Username:");

        lbl_Date.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Date.setText("Date:");

        lbl_Email.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Email.setText("E-mail:");

        txt_ID.setEnabled(false);

        jRadioButton1.setText("Male");

        jRadioButton2.setText("Female");

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

        btn_Exit.setText("Exit");
        btn_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ExitActionPerformed(evt);
            }
        });

        btn_Back.setText("Back");
        btn_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BackActionPerformed(evt);
            }
        });

        lbl_LoginPicture.setText("jLabel1");

        lbl_LoginName.setText("jLabel2");

        btn_SignOut.setText("Sign Out");

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
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Username, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Gender, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Surname, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_ID)
                                    .addComponent(txt_Name, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(txt_Surname, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(txt_Username, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(txt_Email, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(dateTimePicker_Date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_ShowPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(205, 205, 205)
                                .addComponent(lbl_LoginPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 18, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_LoginName, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btn_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_SignOut)
                .addGap(18, 18, 18)
                .addComponent(btn_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Exit)
                            .addComponent(btn_SignOut))
                        .addGap(17, 17, 17))
                    .addComponent(btn_Back, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_ID)
                            .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_Name)
                            .addComponent(txt_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_Surname)
                            .addComponent(txt_Surname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_Gender)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lbl_LoginPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_LoginName)))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Username)
                    .addComponent(txt_Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Password)
                    .addComponent(txt_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ShowPassword))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_Date)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_Email)
                            .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(dateTimePicker_Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Update)
                    .addComponent(btn_Clear))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClearActionPerformed
        // TODO add your handling code here:
        
        clear();
        
        
    }//GEN-LAST:event_btn_ClearActionPerformed

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        try {
            // TODO add your handling code here:

            update();
        } catch (SQLException ex) {
            Logger.getLogger(Frm_CustomerUpdateCustomerInformation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_UpdateActionPerformed

    private void btn_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BackActionPerformed
        // TODO add your handling code here:
        Frm_CustomerLogin login = new Frm_CustomerLogin();
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
            java.util.logging.Logger.getLogger(Frm_CustomerUpdateCustomerInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_CustomerUpdateCustomerInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_CustomerUpdateCustomerInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_CustomerUpdateCustomerInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_CustomerUpdateCustomerInformation().setVisible(true);
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
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JLabel lbl_Date;
    private javax.swing.JLabel lbl_Email;
    private javax.swing.JLabel lbl_Gender;
    private javax.swing.JLabel lbl_ID;
    private javax.swing.JLabel lbl_LoginName;
    private javax.swing.JLabel lbl_LoginPicture;
    private javax.swing.JLabel lbl_Name;
    private javax.swing.JLabel lbl_Password;
    private javax.swing.JLabel lbl_Surname;
    private javax.swing.JLabel lbl_Username;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_ID;
    private javax.swing.JTextField txt_Name;
    private javax.swing.JPasswordField txt_Password;
    private javax.swing.JTextField txt_Surname;
    private javax.swing.JTextField txt_Username;
    // End of variables declaration//GEN-END:variables
}
