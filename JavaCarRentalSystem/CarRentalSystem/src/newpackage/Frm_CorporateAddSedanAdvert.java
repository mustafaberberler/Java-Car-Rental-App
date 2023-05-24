/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package newpackage;


import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.sql.*;
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
import static newpackage.Frm_AdminAddUser.DB_URL;
import static newpackage.Frm_AdminAddUser.PASS;
import static newpackage.Frm_AdminAddUser.USER;
import java.util.Date;


/**
 *
 * @author mustafa
 */
public class Frm_CorporateAddSedanAdvert extends javax.swing.JFrame {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DOMAIN_NAME = "localhost:3306";
    static final String DB_NAME = "db_carrentalsystem";
    static final String DB_URL = "jdbc:mysql://" + DOMAIN_NAME + "/" + DB_NAME;
    
    static final String USER = "root";
    static final String PASS = "1234";
    
    
    File file = null;  // Resim ekleme işlemleri için
    
    
    
    
    
    String imagedirectorypath = null;
    
    
    /**
     * Creates new form Frm_CorporateAddAdvert
     */
    public Frm_CorporateAddSedanAdvert() {
        initComponents();
        addWindowListener(new WindowHandler());
    }

    
    private class WindowHandler implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e) {
            fillComboFuelType();
            fillComboTransmission();
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
            
            Date date = new java.util.Date();
            dateTimePicker_AdvertDate.setDate(date);
            
            dateTimePicker_AdvertDate.setEnabled(false);
    }
    
    private void fillComboFuelType(){
        comboBox_FuelType.addItem("Diesel");
        comboBox_FuelType.addItem("Gasoline");
        comboBox_FuelType.addItem("LPG");
        comboBox_FuelType.addItem("Hybrid");
        comboBox_FuelType.addItem("Electric");
    }
    
    private void fillComboTransmission(){
        comboBox_Transmission.addItem("Manual");
        comboBox_Transmission.addItem("Automatic");
        
    }
    
   
    
    private void clear(){
        
        txt_AdvertNumber.setText("");
        txt_Brand.setText("");
        txt_Series.setText("");
        txt_Model.setText("");
        txt_Year.setText("");
        comboBox_FuelType.setSelectedIndex(0);
        comboBox_Transmission.setSelectedIndex(0);
        txt_KM.setText("");        
        txt_Color.setText("");
        txt_Consumption.setText("");
        txt_Tank.setText("");
        txt_RentCost.setText("");
        dateTimePicker_AdvertDate.setCalendar(null);
        lbl_AddPicture.setIcon(null);
        
    }
    
    
    private void addAdvert() throws SQLException{
        boolean check = false;
        String chassis = "Sedan";
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        
        String date = null;
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.format(dateTimePicker_AdvertDate.getDate());
        
        
        Vehicle vehicle = new Vehicle(uuid, "", "", "", "", "", "", "", "", "", "", txt_AdvertNumber.getText().toString(), date, txt_Brand.getText().toString(), txt_Series.getText().toString(), txt_Model.getText().toString(), txt_Year.getText().toString(), comboBox_FuelType.getSelectedItem().toString(), comboBox_Transmission.getSelectedItem().toString(), txt_KM.getText().toString(), chassis, txt_Color.getText().toString(), Double.parseDouble(txt_Consumption.getText().toString()), Integer.parseInt(txt_Tank.getText().toString()), Double.parseDouble(txt_RentCost.getText().toString()), 0, 0, 0);
        
        Connection connection = null;
        PreparedStatement preparedstatement = null; 
        ResultSet set = null;
        
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            
            String query = "select * from vehicles where advertNumber='" + txt_AdvertNumber.getText() + "'";
            
            preparedstatement = connection.prepareStatement(query);
            set = preparedstatement.executeQuery();
            
            while(set.next()){
                check = true;
                break;
            }
            connection.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error occurred while searching", "Isim", JOptionPane.ERROR_MESSAGE);
            connection.close();
        }
        
        if (check == false){
            if(txt_AdvertNumber.getText().length() < 11 ){
                lbl_AdvertNumber.setForeground(Color.RED);
                txt_AdvertNumber.setBackground(Color.RED);
            }
            else{
                lbl_AdvertNumber.setForeground(Color.BLACK);
                txt_AdvertNumber.setBackground(null);
            }
            
            if (txt_Brand.getText().length() < 2 || txt_Brand.getText() == ""){
                lbl_Brand.setForeground(Color.RED);
                txt_Brand.setBackground(Color.RED);
            }
            else {
                lbl_Brand.setForeground(Color.BLACK);
                txt_Brand.setBackground(null);
            }
            
            if (txt_Series.getText().length() < 2 || txt_Series.getText() == ""){
                lbl_Series.setForeground(Color.RED);
                txt_Series.setBackground(Color.RED);
            }
            else {
                lbl_Series.setForeground(Color.BLACK);
                txt_Series.setBackground(null);
            }
            
            if (txt_Model.getText() == ""){
                lbl_Model.setForeground(Color.RED);
                txt_Model.setBackground(Color.RED);
            }
            else {
                lbl_Model.setForeground(Color.BLACK);
                txt_Model.setBackground(null);
            }
            
            if (txt_Year.getText() == ""){
                lbl_Year.setForeground(Color.RED);
                txt_Year.setBackground(Color.RED);
            }
            else {
                lbl_Year.setForeground(Color.BLACK);
                txt_Year.setBackground(null);
            }
            
            if (comboBox_FuelType.getSelectedIndex() == -1){
                lbl_FuelType.setForeground(Color.RED);
            }
            else {
                lbl_FuelType.setForeground(Color.BLACK);
            }
            
            if (comboBox_Transmission.getSelectedIndex() == -1){
                lbl_Transmission.setForeground(Color.RED);
            }
            else {
                lbl_Transmission.setForeground(Color.BLACK);
            }
            
            if (txt_KM.getText() == ""){
                lbl_KM.setForeground(Color.RED);
                txt_KM.setBackground(Color.RED);
            }
            else {
                lbl_KM.setForeground(Color.BLACK);
                txt_KM.setBackground(null);
            }
            
            
            
            if (txt_Color.getText() == ""){
                lbl_Color.setForeground(Color.RED);
                txt_Color.setBackground(Color.RED);
            }
            else {
                lbl_Color.setForeground(Color.BLACK);
                txt_Color.setBackground(null);
            }
            
            if (txt_Consumption.getText() == ""){
                lbl_Consumption.setForeground(Color.RED);
                txt_Consumption.setBackground(Color.RED);
            }
            else {
                lbl_Consumption.setForeground(Color.BLACK);
                txt_Consumption.setBackground(null);
            }
            
            if (txt_Tank.getText() == ""){
                lbl_Tank.setForeground(Color.RED);
                txt_Tank.setBackground(Color.RED);
            }
            else {
                lbl_Tank.setForeground(Color.BLACK);
                txt_Tank.setBackground(null);
            }
            
            if (txt_RentCost.getText() == ""){
                lbl_RentCost.setForeground(Color.RED);
                txt_RentCost.setBackground(Color.RED);
            }
            else {
                lbl_RentCost.setForeground(Color.BLACK);
                txt_RentCost.setBackground(null);
            }
            
            if (lbl_AddPicture.getIcon() == null){
                lbl_Picture.setForeground(Color.RED);
            }
            else {
                lbl_Picture.setForeground(Color.BLACK);
            }
            
            if (txt_AdvertNumber.getText().length() == 11 && txt_AdvertNumber.getText() != "" && txt_Brand.getText().length() >= 2 && txt_Brand.getText() != "" && txt_Series.getText() != "" && txt_Model.getText() != "" && txt_Year.getText() != "" && comboBox_FuelType.getSelectedIndex() != -1 && comboBox_Transmission.getSelectedIndex() != -1 && txt_KM.getText() != "" && txt_Color.getText() != "" && txt_Consumption.getText() != "" && txt_Tank.getText() != "" && txt_RentCost.getText() != "" && lbl_AddPicture.getIcon() != null){
                
                try{
                    String driver = "com.mysql.cj.jdbc.Driver";
                    Class.forName(driver);
                    connection = DriverManager.getConnection(DB_URL, USER, PASS);
                    
                        String query2 = "insert into vehicles (uniqueID, ownerid, advertNumber, brand, series, model, year, fueltype, transmission, km, chassis, color, fuelConsumption, fuelTank, rentCost, advertDate, traction, offroad, carryload, rented)" + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        preparedstatement = connection.prepareStatement(query2);
                        
                        preparedstatement.setString(1, uuid);
                        preparedstatement.setString(2, Frm_Corporate.ID);
                        preparedstatement.setString(3, vehicle.getAdvertNumber());
                        preparedstatement.setString(4, vehicle.getBrand());
                        preparedstatement.setString(5, vehicle.getSeries());
                        preparedstatement.setString(6, vehicle.getModel());
                        preparedstatement.setString(7, vehicle.getYear());
                        preparedstatement.setString(8, vehicle.getFuel());
                        preparedstatement.setString(9, vehicle.getTransmission());
                        preparedstatement.setString(10, vehicle.getKm());
                        preparedstatement.setString(11, vehicle.getChassis());
                        preparedstatement.setString(12, vehicle.getColor());
                        preparedstatement.setDouble(13, vehicle.getFuelConsumption());
                        preparedstatement.setInt(14, vehicle.getFuelTank());
                        preparedstatement.setDouble(15, vehicle.getRentCost());
                        preparedstatement.setString(16, date);
                        preparedstatement.setString(17, "");
                        preparedstatement.setString(18, "");
                        preparedstatement.setString(19, "");
                        preparedstatement.setBoolean(20, false);
                        
                        preparedstatement.executeUpdate();
            
                        connection.close();
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Error occurred while adding new advert", "Isim", JOptionPane.ERROR_MESSAGE);
                    
                    connection.close();
                }
                
                try {
                    BufferedImage img = ImageIO.read(file);
                    String iconpath = imagedirectorypath + "\\" + txt_AdvertNumber.getText() + ".png";
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
            JOptionPane.showMessageDialog(null, "The advert number that you want to insert is already inserted to the system. Please insert another advert number.", "Isim", JOptionPane.ERROR_MESSAGE);
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

        lbl_AdvertNumber = new javax.swing.JLabel();
        lbl_Brand = new javax.swing.JLabel();
        lbl_Series = new javax.swing.JLabel();
        lbl_Model = new javax.swing.JLabel();
        lbl_Year = new javax.swing.JLabel();
        lbl_FuelType = new javax.swing.JLabel();
        lbl_Transmission = new javax.swing.JLabel();
        lbl_KM = new javax.swing.JLabel();
        lbl_Color = new javax.swing.JLabel();
        lbl_Consumption = new javax.swing.JLabel();
        lbl_Tank = new javax.swing.JLabel();
        lbl_RentCost = new javax.swing.JLabel();
        lbl_AdvertDate = new javax.swing.JLabel();
        txt_AdvertNumber = new javax.swing.JTextField();
        txt_Brand = new javax.swing.JTextField();
        txt_Series = new javax.swing.JTextField();
        txt_Year = new javax.swing.JTextField();
        txt_Model = new javax.swing.JTextField();
        comboBox_FuelType = new javax.swing.JComboBox<>();
        comboBox_Transmission = new javax.swing.JComboBox<>();
        txt_KM = new javax.swing.JTextField();
        txt_Color = new javax.swing.JTextField();
        txt_Consumption = new javax.swing.JTextField();
        txt_Tank = new javax.swing.JTextField();
        txt_RentCost = new javax.swing.JTextField();
        dateTimePicker_AdvertDate = new com.toedter.calendar.JDateChooser();
        btn_Clear = new javax.swing.JButton();
        btn_Add = new javax.swing.JButton();
        btn_Back = new javax.swing.JButton();
        btn_Exit = new javax.swing.JButton();
        lbl_Picture = new javax.swing.JLabel();
        lbl_AddPicture = new javax.swing.JLabel();
        btn_Browse = new javax.swing.JButton();
        lbl_LoginPicture = new javax.swing.JLabel();
        lbl_LoginName = new javax.swing.JLabel();
        btn_SignOut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_AdvertNumber.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_AdvertNumber.setText("Advert Number:");

        lbl_Brand.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Brand.setText("Brand:");

        lbl_Series.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Series.setText("Series:");
        lbl_Series.setToolTipText("");

        lbl_Model.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Model.setText("Model:");

        lbl_Year.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Year.setText("Year:");

        lbl_FuelType.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_FuelType.setText("Fuel Type:");

        lbl_Transmission.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Transmission.setText("Transmission:");
        lbl_Transmission.setToolTipText("");

        lbl_KM.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_KM.setText("KM:");

        lbl_Color.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Color.setText("Color:");

        lbl_Consumption.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Consumption.setText("Fuel Consumption (100km):");

        lbl_Tank.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Tank.setText("Fuel Tank:");

        lbl_RentCost.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_RentCost.setText("Rent Cost:");

        lbl_AdvertDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_AdvertDate.setText("Advert Date:");

        txt_AdvertNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_AdvertNumberKeyTyped(evt);
            }
        });

        txt_Brand.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_BrandKeyPressed(evt);
            }
        });

        txt_Year.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_YearKeyTyped(evt);
            }
        });

        comboBox_FuelType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        txt_KM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_KMKeyTyped(evt);
            }
        });

        txt_Consumption.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_ConsumptionKeyTyped(evt);
            }
        });

        txt_Tank.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_TankKeyTyped(evt);
            }
        });

        txt_RentCost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_RentCostKeyTyped(evt);
            }
        });

        btn_Clear.setText("Clear");
        btn_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClearActionPerformed(evt);
            }
        });

        btn_Add.setText("Add Advert");
        btn_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddActionPerformed(evt);
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

        lbl_Picture.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Picture.setText("Picture:");

        lbl_AddPicture.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_Browse.setText("Browse");
        btn_Browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BrowseActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_SignOut, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbl_AdvertDate, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_RentCost, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_Tank, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_Consumption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_Color, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_Picture, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_Color, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                .addComponent(txt_Consumption, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                .addComponent(txt_Tank, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                .addComponent(txt_RentCost, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                .addComponent(dateTimePicker_AdvertDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_AddPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Browse, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_KM, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Transmission, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_FuelType, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Year, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Model, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Series, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Brand, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_AdvertNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_AdvertNumber)
                            .addComponent(txt_Brand, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(txt_Series, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(txt_Year, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(txt_Model, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(comboBox_FuelType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboBox_Transmission, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_KM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 218, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_LoginPicture, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_LoginName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Back)
                    .addComponent(btn_Exit)
                    .addComponent(btn_SignOut))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_AdvertNumber)
                            .addComponent(txt_AdvertNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_Brand)
                            .addComponent(txt_Brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_Series)
                            .addComponent(txt_Series, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_Model)
                            .addComponent(txt_Model, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lbl_LoginPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Year)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_Year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_LoginName)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_FuelType)
                    .addComponent(comboBox_FuelType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Transmission)
                    .addComponent(comboBox_Transmission, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_KM)
                    .addComponent(txt_KM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Color)
                    .addComponent(txt_Color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Consumption)
                    .addComponent(txt_Consumption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Tank)
                    .addComponent(txt_Tank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_RentCost)
                    .addComponent(txt_RentCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_AdvertDate)
                    .addComponent(dateTimePicker_AdvertDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_Picture)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lbl_AddPicture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_Browse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Clear)
                    .addComponent(btn_Add))
                .addGap(44, 44, 44))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClearActionPerformed
        // TODO add your handling code here:
        
        clear();
    }//GEN-LAST:event_btn_ClearActionPerformed

    private void txt_AdvertNumberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_AdvertNumberKeyTyped
        // TODO add your handling code here:
        
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)){
            evt.consume();
        }
    }//GEN-LAST:event_txt_AdvertNumberKeyTyped

    private void txt_BrandKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BrandKeyPressed
        // TODO add your handling code here:
        
        char c = evt.getKeyChar();
        
        if (Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c)){
            //iso = delete ve backspace tuşları için
            txt_Brand.setEditable(true);            
        }
        else {
            txt_Brand.setEditable(false);
        }
    }//GEN-LAST:event_txt_BrandKeyPressed

    private void txt_YearKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_YearKeyTyped
        // TODO add your handling code here:
        
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)){
            evt.consume();
        }
    }//GEN-LAST:event_txt_YearKeyTyped

    private void txt_KMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_KMKeyTyped
        // TODO add your handling code here:
        
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)){
            evt.consume();
        }
        
        
    }//GEN-LAST:event_txt_KMKeyTyped

    private void txt_ConsumptionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ConsumptionKeyTyped
        // TODO add your handling code here:
        
        
        
    }//GEN-LAST:event_txt_ConsumptionKeyTyped

    private void txt_TankKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TankKeyTyped
        // TODO add your handling code here:
        
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)){
            evt.consume();
        }
    }//GEN-LAST:event_txt_TankKeyTyped

    private void txt_RentCostKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RentCostKeyTyped
        // TODO add your handling code here:
        
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_COMMA)){
            evt.consume();
        }
    }//GEN-LAST:event_txt_RentCostKeyTyped

    
    
    // Browse butonu
    private void btn_BrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BrowseActionPerformed
        // TODO add your handling code here:
        
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int res = chooser.showOpenDialog(Frm_CorporateAddSedanAdvert.this);
        if (res == JFileChooser.APPROVE_OPTION){
            file = chooser.getSelectedFile();
            String path = file.getAbsolutePath();
            ImageIcon image = new ImageIcon(file.getAbsolutePath());
            Rectangle rec = lbl_AddPicture.getBounds();
            Image scaledimage = image.getImage().getScaledInstance(rec.width, rec.height, Image.SCALE_SMOOTH);
            image = new ImageIcon(scaledimage);
            lbl_AddPicture.setIcon(image);
            
            String directorypath = Paths.get("").toAbsolutePath().toString();
            
            imagedirectorypath = directorypath + "\\vehicleimages";
            
            File directory = new File(imagedirectorypath);
            if (!directory.exists()){
                directory.mkdirs();
            }
                        
        }
        else {
            JOptionPane.showMessageDialog(null, "No new image selected");
        }
    }//GEN-LAST:event_btn_BrowseActionPerformed

    private void btn_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BackActionPerformed
        // TODO add your handling code here:
        
        Frm_CustomerLogin login = new Frm_CustomerLogin();
        setVisible(false);
        login.setVisible(true);
            
    }//GEN-LAST:event_btn_BackActionPerformed

    private void btn_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddActionPerformed
        try {
            // TODO add your handling code here:

            addAdvert();
        } catch (SQLException ex) {
            Logger.getLogger(Frm_CorporateAddSedanAdvert.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_AddActionPerformed

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
            java.util.logging.Logger.getLogger(Frm_CorporateAddSedanAdvert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_CorporateAddSedanAdvert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_CorporateAddSedanAdvert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_CorporateAddSedanAdvert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_CorporateAddSedanAdvert().setVisible(true);
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
    private javax.swing.JComboBox<String> comboBox_FuelType;
    private javax.swing.JComboBox<String> comboBox_Transmission;
    private com.toedter.calendar.JDateChooser dateTimePicker_AdvertDate;
    private javax.swing.JLabel lbl_AddPicture;
    private javax.swing.JLabel lbl_AdvertDate;
    private javax.swing.JLabel lbl_AdvertNumber;
    private javax.swing.JLabel lbl_Brand;
    private javax.swing.JLabel lbl_Color;
    private javax.swing.JLabel lbl_Consumption;
    private javax.swing.JLabel lbl_FuelType;
    private javax.swing.JLabel lbl_KM;
    private javax.swing.JLabel lbl_LoginName;
    private javax.swing.JLabel lbl_LoginPicture;
    private javax.swing.JLabel lbl_Model;
    private javax.swing.JLabel lbl_Picture;
    private javax.swing.JLabel lbl_RentCost;
    private javax.swing.JLabel lbl_Series;
    private javax.swing.JLabel lbl_Tank;
    private javax.swing.JLabel lbl_Transmission;
    private javax.swing.JLabel lbl_Year;
    private javax.swing.JTextField txt_AdvertNumber;
    private javax.swing.JTextField txt_Brand;
    private javax.swing.JTextField txt_Color;
    private javax.swing.JTextField txt_Consumption;
    private javax.swing.JTextField txt_KM;
    private javax.swing.JTextField txt_Model;
    private javax.swing.JTextField txt_RentCost;
    private javax.swing.JTextField txt_Series;
    private javax.swing.JTextField txt_Tank;
    private javax.swing.JTextField txt_Year;
    // End of variables declaration//GEN-END:variables
}
