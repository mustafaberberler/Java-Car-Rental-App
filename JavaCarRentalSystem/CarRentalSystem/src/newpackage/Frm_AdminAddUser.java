/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package newpackage;

import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static newpackage.Frm_CustomerSignUp.DB_URL;
import static newpackage.Frm_CustomerSignUp.PASS;
import static newpackage.Frm_CustomerSignUp.USER;


/**
 *
 * @author mustafa
 */
public class Frm_AdminAddUser extends javax.swing.JFrame {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DOMAIN_NAME = "localhost:3306";
    static final String DB_NAME = "db_carrentalsystem";
    static final String DB_URL = "jdbc:mysql://" + DOMAIN_NAME + "/" + DB_NAME;
    
    static final String USER = "root";
    static final String PASS = "1234";
    
    
    
    
    File file = null;  // Resim ekleme işlemleri için
    
    
    
    
    
    String imagedirectorypath = null;
    /**
     * Creates new form Frm_AdminAddUser
     */
    public Frm_AdminAddUser() {
        initComponents();
        addWindowListener(new WindowHandler());
        jRadioButton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(jRadioButton5.isSelected()){
                    txt_Surname.setText("");
                    txt_Surname.setEnabled(false);
                }
            }
        });
        
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
                jRadioButton1.setEnabled(false);
                jRadioButton2.setEnabled(false);
                
            }
            
        }                
        );
        // Gender için de aynı şeyi yapmayı unutma
        
        
        
        
        
    }
    
    
   
    
    
    private class WindowHandler implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e) {
            showData();
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
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setCalendar(null);
        txt_Email.setText("");
        lbl_AddPicture.setIcon(null);
        //picturebox ı unutma
        
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
    
    public void add() throws SQLException{
        boolean check = false;
        
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
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            
            String query = "select * from users where identityNumber='" + txt_ID.getText() + "'";  // Girilen ID'ye ait herhangi bir kayıt olup olmadığı veritabanında aranıyor
            preparedstatement = connection.prepareStatement(query);
            set = preparedstatement.executeQuery();
            
            while(set.next()){
                check = true;  // Kayıt bulundu
                break;
            }
            
            connection.close();
        }
        
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error occurred while searching", "Isim", JOptionPane.ERROR_MESSAGE);
            connection.close();
        }
        
        if (check == false){   // ID'ye göre aranan kayıt sistemde yoksa
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
            
            if (lbl_AddPicture.getIcon() == null){
                lbl_Picture.setForeground(Color.RED);
            }
            else {
                lbl_Picture.setForeground(Color.BLACK);
            }
            
            if(txt_ID.getText().length() == 11 && txt_ID.getText() != "" && txt_Name.getText().length() >= 2 && txt_Name.getText() != "" && txt_Surname.getText().length() >= 2 && txt_Surname.getText() != "" && txt_Username.getText() != "" && txt_Password.getText() != "" && txt_Email.getText() != "" && lbl_AddPicture.getIcon() != null){
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
                
                
                
                try{  // ekleme kısmı
                    String driver = "com.mysql.cj.jdbc.Driver";
                    Class.forName(driver);
                    connection = DriverManager.getConnection(DB_URL, USER, PASS);
                    
                    String query2 = "insert into users (uniqueID, identityNumber, name, surname, gender, authority, username, password, date, email)" + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    
                    preparedstatement = connection.prepareStatement(query2);
                    preparedstatement.setString(1, uuid);
                    preparedstatement.setString(2, user.getIdentityNumber());
                    preparedstatement.setString(3, user.getName());
                    preparedstatement.setString(4, user.getSurname());
                    preparedstatement.setString(5, user.getGender());
                    preparedstatement.setString(6, user.getAuthority());  // otomatik olarak atandı
                    preparedstatement.setString(7, user.getUsername());
                    preparedstatement.setString(8, user.getPassword());
                    preparedstatement.setString(9, date);
                    preparedstatement.setString(10, user.getEmail());
                
                    preparedstatement.executeUpdate();
            
                    connection.close();
                    
                    showData();
                    
                    
                    // Resim eklemeyi unutma
                    
                    
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Error occurred while adding new user", "Isim", JOptionPane.ERROR_MESSAGE);
                    
                    connection.close();
                }
                
                
                try{  // Resim ekleme kısmı
                    BufferedImage img = ImageIO.read(file);
                    String iconpath = imagedirectorypath + "\\" + txt_ID.getText() + ".png";
                    String imgformat = "PNG";
                    ImageIO.write(img, imgformat, new File(iconpath));
                    JOptionPane.showMessageDialog(null, "Image saved successfully");
                    
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                
            }
            else {
                JOptionPane.showMessageDialog(null, "Please fix the incorrect parts.", "Isim", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
                JOptionPane.showMessageDialog(null, "The ID that you want to insert is already inserted to the system. Please insert another ID.", "Isim", JOptionPane.ERROR_MESSAGE);

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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        lbl_ID = new javax.swing.JLabel();
        lbl_Name = new javax.swing.JLabel();
        lbl_Surname = new javax.swing.JLabel();
        lbl_Gender = new javax.swing.JLabel();
        lbl_Authority = new javax.swing.JLabel();
        lbl_Password = new javax.swing.JLabel();
        lbl_Username = new javax.swing.JLabel();
        lbl_Date = new javax.swing.JLabel();
        lbl_Email = new javax.swing.JLabel();
        lbl_Picture = new javax.swing.JLabel();
        txt_ID = new javax.swing.JTextField();
        txt_Name = new javax.swing.JTextField();
        txt_Username = new javax.swing.JTextField();
        txt_Password = new javax.swing.JTextField();
        txt_Email = new javax.swing.JTextField();
        txt_Surname = new javax.swing.JTextField();
        dateTimePicker_Date = new com.toedter.calendar.JDateChooser();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        lbl_AddPicture = new javax.swing.JLabel();
        btn_Exit = new javax.swing.JButton();
        btn_Back = new javax.swing.JButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        btn_Browse = new javax.swing.JButton();
        btn_Clear = new javax.swing.JButton();
        btn_Add = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        lbl_LoginPicture = new javax.swing.JLabel();
        lbl_LoginName = new javax.swing.JLabel();
        btn_SignOut = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_ID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_ID.setText("ID:");

        lbl_Name.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Name.setText("Name:");

        lbl_Surname.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Surname.setText("Surname:");

        lbl_Gender.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Gender.setText("Gender:");

        lbl_Authority.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Authority.setText("Authority:");

        lbl_Password.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Password.setText("Password:");

        lbl_Username.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Username.setText("Username:");

        lbl_Date.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Date.setText("Date:");

        lbl_Email.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Email.setText("E-mail:");

        lbl_Picture.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Picture.setText("Picture:");

        txt_ID.setToolTipText("");
        txt_ID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_IDKeyTyped(evt);
            }
        });

        txt_Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_NameKeyPressed(evt);
            }
        });

        txt_Surname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_SurnameKeyPressed(evt);
            }
        });

        jRadioButton1.setText("Male");

        jRadioButton2.setText("Female");

        lbl_AddPicture.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        jRadioButton3.setText("Admin");

        jRadioButton4.setText("Customer");

        jRadioButton5.setText("Corporate");

        btn_Browse.setText("Browse");
        btn_Browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BrowseActionPerformed(evt);
            }
        });

        btn_Clear.setText("Clear");
        btn_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClearActionPerformed(evt);
            }
        });

        btn_Add.setText("Insert");
        btn_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddActionPerformed(evt);
            }
        });

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Identity Number", "Name", "Surname", "Gender", "Authority", "Username", "Password", "Date", "E-mail"
            }
        ));
        jScrollPane3.setViewportView(table1);

        lbl_LoginPicture.setText("jLabel1");
        lbl_LoginPicture.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_LoginName.setText("jLabel2");

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
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Name, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Surname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Gender, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Authority, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Password, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Username, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Email, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Picture, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_ID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_ID)
                                .addComponent(txt_Name, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                .addComponent(txt_Username, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                .addComponent(txt_Password, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                .addComponent(txt_Email, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                .addComponent(txt_Surname, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                .addComponent(dateTimePicker_Date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 439, Short.MAX_VALUE)
                        .addComponent(lbl_LoginPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_AddPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_Browse, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_LoginName, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_SignOut, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Exit)
                    .addComponent(btn_Back)
                    .addComponent(btn_SignOut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_ID)
                    .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_Name)
                            .addComponent(txt_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_Surname)
                            .addComponent(txt_Surname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_Gender)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbl_LoginPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Authority)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton5)
                    .addComponent(lbl_LoginName))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Username)
                    .addComponent(txt_Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Password)
                    .addComponent(txt_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_Date)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_Email)
                            .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(dateTimePicker_Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_Picture)
                    .addComponent(lbl_AddPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_Browse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Clear)
                            .addComponent(btn_Add))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(329, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BackActionPerformed
        // TODO add your handling code here:
        Frm_AdminLogin login = new Frm_AdminLogin();
        setVisible(false);
        login.setVisible(true);
    }//GEN-LAST:event_btn_BackActionPerformed

    
    // Browse butonu
    private void btn_BrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BrowseActionPerformed
        // TODO add your handling code here:
        
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int res = chooser.showOpenDialog(Frm_AdminAddUser.this);
        if (res == JFileChooser.APPROVE_OPTION){
            file = chooser.getSelectedFile();
            String path = file.getAbsolutePath();
            ImageIcon image = new ImageIcon(file.getAbsolutePath());
            Rectangle rec = lbl_AddPicture.getBounds();
            Image scaledimage = image.getImage().getScaledInstance(rec.width, rec.height, Image.SCALE_SMOOTH);
            image = new ImageIcon(scaledimage);
            lbl_AddPicture.setIcon(image);
            
            String directorypath = Paths.get("").toAbsolutePath().toString();
            
            imagedirectorypath = directorypath + "\\images";
            
            File directory = new File(imagedirectorypath);
            if (!directory.exists()){
                directory.mkdirs();
            }
                        
        }
        else {
            JOptionPane.showMessageDialog(null, "No new image selected");
        }
        
    }//GEN-LAST:event_btn_BrowseActionPerformed

    
    // Clear butonu
    private void btn_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btn_ClearActionPerformed

    
    // Add butonu
    private void btn_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddActionPerformed
        try {
            // TODO add your handling code here:
            add();
        } catch (SQLException ex) {
            Logger.getLogger(Frm_AdminAddUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_AddActionPerformed

    private void txt_IDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_IDKeyTyped
        // TODO add your handling code here:
        
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)){
            evt.consume();
        }
    }//GEN-LAST:event_txt_IDKeyTyped

    private void txt_NameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NameKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if (Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c) ){
            //iso = delete ve backspace tuşları için
            txt_Name.setEditable(true);            
        }
        else {
            txt_Name.setEditable(false);
        }
    }//GEN-LAST:event_txt_NameKeyPressed

    private void txt_SurnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_SurnameKeyPressed
        // TODO add your handling code here:
        
        char c = evt.getKeyChar();
        
        if (Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c)){
            //iso = delete ve backspace tuşları için
            txt_Name.setEditable(true);            
        }
        else {
            txt_Name.setEditable(false);
        }
    }//GEN-LAST:event_txt_SurnameKeyPressed

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
            java.util.logging.Logger.getLogger(Frm_AdminAddUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_AdminAddUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_AdminAddUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_AdminAddUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_AdminAddUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Add;
    private javax.swing.JButton btn_Back;
    private javax.swing.JButton btn_Browse;
    private javax.swing.JButton btn_Clear;
    private javax.swing.JButton btn_Exit;
    private javax.swing.JButton btn_SignOut;
    private com.toedter.calendar.JDateChooser dateTimePicker_Date;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lbl_AddPicture;
    private javax.swing.JLabel lbl_Authority;
    private javax.swing.JLabel lbl_Date;
    private javax.swing.JLabel lbl_Email;
    private javax.swing.JLabel lbl_Gender;
    private javax.swing.JLabel lbl_ID;
    private javax.swing.JLabel lbl_LoginName;
    private javax.swing.JLabel lbl_LoginPicture;
    private javax.swing.JLabel lbl_Name;
    private javax.swing.JLabel lbl_Password;
    private javax.swing.JLabel lbl_Picture;
    private javax.swing.JLabel lbl_Surname;
    private javax.swing.JLabel lbl_Username;
    private javax.swing.JTable table1;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_ID;
    private javax.swing.JTextField txt_Name;
    private javax.swing.JTextField txt_Password;
    private javax.swing.JTextField txt_Surname;
    private javax.swing.JTextField txt_Username;
    // End of variables declaration//GEN-END:variables
}
