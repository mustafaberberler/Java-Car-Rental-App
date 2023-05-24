/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package newpackage;

import java.sql.*;
import java.util.Date;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static newpackage.Frm_AdminAddUser.DB_URL;
import static newpackage.Frm_AdminAddUser.PASS;
import static newpackage.Frm_AdminAddUser.USER;

/**
 *
 * @author mustafa
 */
public class Frm_AdminUpdateUser extends javax.swing.JFrame {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DOMAIN_NAME = "localhost:3306";
    static final String DB_NAME = "db_carrentalsystem";
    static final String DB_URL = "jdbc:mysql://" + DOMAIN_NAME + "/" + DB_NAME;
    
    static final String USER = "root";
    static final String PASS = "1234";
    /**
     * Creates new form Frm_AdminUpdateUser
     */
    public Frm_AdminUpdateUser() {
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
        
        jRadioButton3.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                jRadioButton4.setSelected(false);
                jRadioButton5.setSelected(false);
                jRadioButton1.setEnabled(true);
                jRadioButton2.setEnabled(true);
                txt_Surname.setEnabled(true);
            }
            
        }                
        );
        
        jRadioButton4.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                jRadioButton3.setSelected(false);
                jRadioButton5.setSelected(false);
                jRadioButton1.setEnabled(true);
                jRadioButton2.setEnabled(true);
                txt_Surname.setEnabled(true);
            }
            
        }                
        );
        
        
        jRadioButton5.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                jRadioButton3.setSelected(false);
                jRadioButton4.setSelected(false);
                jRadioButton1.setSelected(false);
                jRadioButton2.setSelected(false);
                jRadioButton1.setEnabled(false);
                jRadioButton2.setEnabled(false);
                txt_Surname.setText("");
                txt_Surname.setEnabled(false);
            }
            
        }                
        );
    }
    
    private class WindowHandler implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e) {
            start();
            showData();
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

    private void clear(){
        txt_ID.setText("");
        txt_Name.setText("");
        txt_Surname.setText("");
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        jRadioButton3.setSelected(false);
        jRadioButton4.setSelected(false);
        jRadioButton5.setSelected(false);
        txt_Username.setText("");
        txt_Password.setText("");
        dateTimePicker_Date.setCalendar(null);
        txt_Email.setText("");
    }
    
    private void showData(){
        Connection connection = null;
        PreparedStatement preparedstatement = null; 
        ResultSet set = null;
        
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            
            String query = "select * from users";
            preparedstatement = connection.prepareStatement(query);
            set = preparedstatement.executeQuery();
            DefaultTableModel tm = (DefaultTableModel)table1.getModel();
            tm.setRowCount(0);
            while (set.next()){
                Object o[] = {set.getString("identityNumber"), set.getString("name"), set.getString("surname"), set.getString("gender"), set.getString("authority"), set.getString("username"), set.getString("password"), set.getString("date"), set.getString("email")};
                tm.addRow(o);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private void update() throws SQLException{
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        String gender = null;
        String authority = null;
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
            
            
            
            if(txt_ID.getText().length() == 11 && txt_ID.getText() != "" && txt_Name.getText().length() >= 2 && txt_Name.getText() != "" &&  txt_Username.getText() != "" && txt_Password.getText() != "" && txt_Email.getText() != ""){
              // Eğer girilen her şey uygunsa  
                
                if (jRadioButton1.isSelected()){
                    user.setGender("Male");
                }
                else if (jRadioButton2.isSelected()){
                    user.setGender("Female");
                }
                if (jRadioButton3.isSelected()){
                    user.setAuthority("Admin");
                }
                else if (jRadioButton4.isSelected()){
                    user.setAuthority("Customer");                        
                }
                else if (jRadioButton5.isSelected()){
                    user.setAuthority("Corporate");
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
                    
                    showData();
                    
                }
                catch(Exception e ){
                    JOptionPane.showMessageDialog(null, "Error occurred while updating the user", "Isim", JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(this, e.getMessage());
                    connection.close();
                }
        
                
                try{
                    String driver = "com.mysql.cj.jdbc.Driver";
                    Class.forName(driver);
                    connection = DriverManager.getConnection(DB_URL, USER, PASS);
                    
                    String query = "update hirerlist set identityNumber=?, hirer_name=?, hirer_surname=? where identityNumber=?";
                    preparedstatement = connection.prepareStatement(query);
                    
                    preparedstatement.setString(1, user.getIdentityNumber());
                    preparedstatement.setString(2, user.getName());
                    preparedstatement.setString(3, user.getSurname());
                    preparedstatement.setString(1, user.getIdentityNumber());
                    
                    preparedstatement.executeUpdate();                  
                    
                                        
                    connection.close();
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(this, e.getMessage());
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
        lbl_Username = new javax.swing.JLabel();
        lbl_Authority = new javax.swing.JLabel();
        lbl_Password = new javax.swing.JLabel();
        lbl_Date = new javax.swing.JLabel();
        lbl_Email = new javax.swing.JLabel();
        txt_Name = new javax.swing.JTextField();
        txt_Surname = new javax.swing.JTextField();
        txt_ID = new javax.swing.JTextField();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        txt_Username = new javax.swing.JTextField();
        txt_Email = new javax.swing.JTextField();
        dateTimePicker_Date = new com.toedter.calendar.JDateChooser();
        btn_Exit = new javax.swing.JButton();
        btn_Clear = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        btn_Back = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        lbl_LoginPicture = new javax.swing.JLabel();
        lbl_LoginName = new javax.swing.JLabel();
        btn_SignOut = new javax.swing.JButton();
        btn_ShowPassword = new javax.swing.JCheckBox();
        txt_Password = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_ID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_ID.setText("ID:");

        lbl_Surname.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Surname.setText("Surname:");

        lbl_Name.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Name.setText("Name:");

        lbl_Gender.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Gender.setText("Gender:");

        lbl_Username.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Username.setText("Username:");

        lbl_Authority.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Authority.setText("Authority:");

        lbl_Password.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Password.setText("Password:");

        lbl_Date.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Date.setText("Date:");

        lbl_Email.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Email.setText("E-mail:");

        txt_ID.setEnabled(false);

        jRadioButton3.setText("Admin");

        jRadioButton4.setText("Customer");

        jRadioButton5.setText("Corporate");

        btn_Exit.setText("Exit");
        btn_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ExitActionPerformed(evt);
            }
        });

        btn_Clear.setText("Clear");

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

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Identity Number", "Name", "Surname", "Gender", "Authority", "Username", "Password", "Date", "E-mail"
            }
        ));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table1);

        jRadioButton2.setText("Female");

        jRadioButton1.setText("Male");

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
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_Authority, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lbl_Gender, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lbl_Username, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lbl_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txt_Username, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lbl_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(dateTimePicker_Date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lbl_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(btn_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(29, 29, 29)
                                                        .addComponent(btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lbl_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lbl_Surname, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txt_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_Surname, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lbl_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_ShowPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)))
                        .addGap(93, 93, 93)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_LoginName, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_LoginPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(67, 67, 67))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_SignOut, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Exit)
                            .addComponent(btn_SignOut)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btn_Back)))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_LoginPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_LoginName))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_ID)
                            .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_Name)
                            .addComponent(txt_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_Surname)
                            .addComponent(txt_Surname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jRadioButton2)
                                .addComponent(jRadioButton1))
                            .addComponent(lbl_Gender))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Authority)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Username)
                    .addComponent(txt_Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Password)
                    .addComponent(btn_ShowPassword)
                    .addComponent(txt_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Date)
                    .addComponent(dateTimePicker_Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Email)
                    .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Clear)
                    .addComponent(btn_Update))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BackActionPerformed
        // TODO add your handling code here:
        
        Frm_AdminLogin login = new Frm_AdminLogin();
        setVisible(false);
        login.setVisible(true);
    }//GEN-LAST:event_btn_BackActionPerformed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        // TODO add your handling code here:
        clear();
        DefaultTableModel model = (DefaultTableModel)table1.getModel();
        int selectedRowIndex = table1.getSelectedRow();
        
        txt_ID.setText(model.getValueAt(selectedRowIndex, 0).toString());
        txt_Name.setText(model.getValueAt(selectedRowIndex, 1).toString());
        txt_Surname.setText(model.getValueAt(selectedRowIndex, 2).toString());
        if(model.getValueAt(selectedRowIndex, 3).equals("Male")){
            jRadioButton2.setSelected(false);
            jRadioButton1.setSelected(true);
        }
        else if (model.getValueAt(selectedRowIndex, 3).equals("Female")){
            jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(true);
        }
        if(model.getValueAt(selectedRowIndex, 4).equals("Admin")){
             jRadioButton5.setSelected(false);
            jRadioButton4.setSelected(false);
            jRadioButton3.setSelected(true);
        }
        else if(model.getValueAt(selectedRowIndex, 4).equals("Customer")  ){
            jRadioButton5.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(true);
        }
        else if(model.getValueAt(selectedRowIndex, 4).equals("Corporate")  ){
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
            jRadioButton5.setSelected(true);
        }
        txt_Username.setText(model.getValueAt(selectedRowIndex, 5).toString());
        txt_Password.setText(model.getValueAt(selectedRowIndex, 6).toString());       
        txt_Email.setText(model.getValueAt(selectedRowIndex, 8).toString()); 
        
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(selectedRowIndex, 7));
            dateTimePicker_Date.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(Frm_AdminUpdateUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_table1MouseClicked

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        try {
            // TODO add your handling code here:

            update();
        } catch (SQLException ex) {
            Logger.getLogger(Frm_AdminUpdateUser.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(Frm_AdminUpdateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_AdminUpdateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_AdminUpdateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_AdminUpdateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_AdminUpdateUser().setVisible(true);
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
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_Authority;
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
    private javax.swing.JTable table1;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_ID;
    private javax.swing.JTextField txt_Name;
    private javax.swing.JPasswordField txt_Password;
    private javax.swing.JTextField txt_Surname;
    private javax.swing.JTextField txt_Username;
    // End of variables declaration//GEN-END:variables
}
