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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author mustafa
 */
public class Frm_CorporateAddAdvert extends javax.swing.JFrame {

    /**
     * Creates new form Frm_CorporateAddAdvert
     */
    public Frm_CorporateAddAdvert() {
        initComponents();
        addWindowListener(new WindowHandler());
    }

    private class WindowHandler implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e) {
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
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_SedanAdvert = new javax.swing.JButton();
        btn_SUVAdvert = new javax.swing.JButton();
        btn_PickupAdvert = new javax.swing.JButton();
        btn_Back = new javax.swing.JButton();
        btn_Exit = new javax.swing.JButton();
        lbl_LoginPicture = new javax.swing.JLabel();
        lbl_LoginName = new javax.swing.JLabel();
        btn_SignOut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_SedanAdvert.setText("Add Sedan Advert");
        btn_SedanAdvert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SedanAdvertActionPerformed(evt);
            }
        });

        btn_SUVAdvert.setText("Add SUV Advert");
        btn_SUVAdvert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SUVAdvertActionPerformed(evt);
            }
        });

        btn_PickupAdvert.setText("Add Pickup Advert");
        btn_PickupAdvert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PickupAdvertActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(162, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_PickupAdvert, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_SedanAdvert, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_SUVAdvert, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(lbl_LoginPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(lbl_LoginName, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btn_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_SignOut, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Back)
                    .addComponent(btn_Exit)
                    .addComponent(btn_SignOut))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_SedanAdvert)
                        .addGap(42, 42, 42)
                        .addComponent(btn_SUVAdvert)
                        .addGap(47, 47, 47)
                        .addComponent(btn_PickupAdvert))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_LoginPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_LoginName)))
                .addContainerGap(77, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    
    
    private void btn_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BackActionPerformed
        // TODO add your handling code here:
        
        Frm_CorporateLogin login = new Frm_CorporateLogin();
        setVisible(false);
        login.setVisible(true);
    }//GEN-LAST:event_btn_BackActionPerformed

    private void btn_SedanAdvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SedanAdvertActionPerformed
        // TODO add your handling code here:
        
        Frm_CorporateAddSedanAdvert addsedan = new Frm_CorporateAddSedanAdvert();
        setVisible(false);
        addsedan.setVisible(true);
    }//GEN-LAST:event_btn_SedanAdvertActionPerformed

    private void btn_SUVAdvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SUVAdvertActionPerformed
        // TODO add your handling code here:
        
        Frm_CorporateAddSUVAdvert addsuv = new Frm_CorporateAddSUVAdvert();
        setVisible(false);
        addsuv.setVisible(true);
    }//GEN-LAST:event_btn_SUVAdvertActionPerformed

    private void btn_PickupAdvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PickupAdvertActionPerformed
        // TODO add your handling code here:
        
        Frm_CorporateAddPickupAdvert addpickup = new Frm_CorporateAddPickupAdvert();
        setVisible(false);
        addpickup.setVisible(true);
    }//GEN-LAST:event_btn_PickupAdvertActionPerformed

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
            java.util.logging.Logger.getLogger(Frm_CorporateAddAdvert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_CorporateAddAdvert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_CorporateAddAdvert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_CorporateAddAdvert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_CorporateAddAdvert().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Back;
    private javax.swing.JButton btn_Exit;
    private javax.swing.JButton btn_PickupAdvert;
    private javax.swing.JButton btn_SUVAdvert;
    private javax.swing.JButton btn_SedanAdvert;
    private javax.swing.JButton btn_SignOut;
    private javax.swing.JLabel lbl_LoginName;
    private javax.swing.JLabel lbl_LoginPicture;
    // End of variables declaration//GEN-END:variables
}