package com.VeN_IT.AS400Test.CobolTester;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.VeN_IT.AS400Test.appIcon.MacIcon;
import com.VeN_IT.AS400Test.appIcon.WinIcon;
import com.VeN_IT.AS400Test.util.ManageProperty;
import com.VeN_IT.AS400Test.util.reuse;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.data.PcmlException;


/**
 * @author Tamas.Matanyi
 *
 * © Copyright 2014 VeN-IT Ltd.
 * 
 * This file is part of CobolTester.
 *
 * CobolTester is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * CobolTester is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CobolTester. If not, see <http://www.gnu.org/licenses/>.
 *
 */

@SuppressWarnings({"serial"})
public class AS400Test extends javax.swing.JFrame implements Serializable {
   
   private static JFrame jFrameConnect = new JFrame("Connection parameters");  			// Container GUI for connection data.
   private static javax.swing.JButton jButtonCancel = new javax.swing.JButton();
   private static javax.swing.JButton jButtonConnect = new javax.swing.JButton();
   private static javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
   private static javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
   private static javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
   private static javax.swing.JPasswordField jPasswordFieldUserPWD = new javax.swing.JPasswordField();
   private static javax.swing.JTextField jTextFieldServerIP = new javax.swing.JTextField();
   private static javax.swing.JTextField jTextFieldUserName = new javax.swing.JTextField();
   
   private static final managePCML mPCML = new managePCML();
   
   
   static JComponent createVerticalSeparator() {  
	   JSeparator x = new JSeparator(SwingConstants.HORIZONTAL);  
       x.setPreferredSize(new Dimension(55, 15));  
       return x; 
   }  
   

   public static void initConnect() {
       String myCurrentDir = System.getProperty("user.dir");
       String os = System.getProperty("os.name");
       
       // Changing of application icon depending on host operating system.
       if (os.equals("Mac OS X") || os.equals("Mac OS")) {
    	   Image image = Toolkit.getDefaultToolkit().getImage(myCurrentDir + "/img/CT_64.gif");
    	   MacIcon.setIcon(image);
       } else if (os.substring(0, 6).equals("Windows")) {
    	   WinIcon.setIcon(jFrameConnect, myCurrentDir + "/img/CT_64.gif");
       }
       
       jFrameConnect.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
       
       jTextFieldUserName.setText("UserName");
       jTextFieldUserName.setToolTipText("Name of the AS400 user.");
       jTextFieldServerIP.setText(ManageProperty.getValue("serverIP"));
       jTextFieldServerIP.setToolTipText("IP address of the AS400 server.");
       
       jPasswordFieldUserPWD.setToolTipText("Password of the AS400 user.");
       
       jLabel2.setText("IP address:");

       jLabel3.setText("User name:");

       jLabel4.setText("User password:");
       
       jButtonConnect.setText("Connect");
       jButtonConnect.setToolTipText("Connect to AS400 server.");
       jButtonConnect.setSelected(true);
       jButtonConnect.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               // Converting "password" char array to String.
              String strPwd = new String(jPasswordFieldUserPWD.getPassword());
              // Calling connection to AS/400 server.
              try {
                  if (managePCML.buildConnection(jTextFieldServerIP.getText(), jTextFieldUserName.getText(), strPwd)) {
                      jFrameConnect.setVisible(false);
                      // Passing of given Library list.  
                      managePCML.setLibl(ManageProperty.getValue("libl01"), 1);
                      managePCML.setLibl(ManageProperty.getValue("libl02"), 2);
                      managePCML.setLibl(ManageProperty.getValue("libl03"), 3);
                      managePCML.setLibl(ManageProperty.getValue("libl04"), 4);
                      managePCML.setLibl(ManageProperty.getValue("libl05"), 5);
                      managePCML.setLibl(ManageProperty.getValue("libl06"), 6);
                      managePCML.setLibl(ManageProperty.getValue("libl07"), 7);
                      managePCML.setLibl(ManageProperty.getValue("libl08"), 8);
                      managePCML.setLibl(ManageProperty.getValue("libl09"), 9);
                      managePCML.setLibl(ManageProperty.getValue("libl10"), 10);
                      mPCML.setTitleLibl();
                      mPCML.showTester();
                  } else {
//                      reuse.errorBox("User name or password is incorrect.", "AS400Test.initConnect");
                  }
                  
              } catch (AS400SecurityException ex) {
                  reuse.errorBox("AS400SecurityException: " + ex.getLocalizedMessage(), "AS400Test.initConnect");
              } catch (IOException ex) {
                  reuse.errorBox("IOException: " + ex.getLocalizedMessage(), "AS400Test.initConnect");
              }
              
           }
       });
       
       jButtonCancel.setText("Cancel");
       jButtonCancel.setToolTipText("Cancel and close application.");
       jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               
               // Close all connections.
               managePCML.actionCancel();
               
               // Simply exit the application with return code: 0.
               System.exit(0);   
           }
       });

       org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(jFrameConnect.getContentPane());
       jFrameConnect.getContentPane().setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
           .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
               .addContainerGap(15, Short.MAX_VALUE)
               .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                   .add(jLabel2)
                   .add(jLabel3)
                   .add(jLabel4)
               .add(18, 18, 18))
               .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                   .add(layout.createSequentialGroup()
                       .add(jButtonConnect)
                       .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                       .add(jButtonCancel))
                   .add(jTextFieldUserName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 217, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                   .add(jPasswordFieldUserPWD, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 217, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                   .add(jTextFieldServerIP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 217, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
               .addContainerGap())
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
           .add(layout.createSequentialGroup()
               .add(14, 14, 14)
               .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                   .add(jLabel2)
                   .add(jTextFieldServerIP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
               .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
               .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                   .add(jTextFieldUserName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                   .add(jLabel3))
               .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
               .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                   .add(jPasswordFieldUserPWD, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                   .add(jLabel4))
               .add(18, 18, 18)
               .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                   .add(jButtonConnect)
                   .add(jButtonCancel))
               .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
       );
       
       // Calling connect on pushing Enter.
       jPasswordFieldUserPWD.addKeyListener(new KeyAdapter() {
           @Override
           public void keyPressed(KeyEvent e) {
               if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                   jButtonConnect.doClick();
               }
           }
       });
       
       
       // Calling connect on pushing Enter.
       jButtonConnect.addKeyListener(new KeyAdapter() {
           @Override
           public void keyPressed(KeyEvent e) {
               if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                   jButtonConnect.doClick();
               }
           }
       });
       
       
       // Calling cancel on pushing Enter.
       jButtonCancel.addKeyListener(new KeyAdapter() {
           @Override
           public void keyPressed(KeyEvent e) {
               if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                   jButtonCancel.doClick();
               }
           }
       });
   }
   
   
   public static void main(String[] args) throws PcmlException, SAXException, IOException, ParserConfigurationException, AS400SecurityException {
       Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
       
       // Initiation of "connection" fFrame.
       initConnect();
       
       // Adding components to jFrame.
       jFrameConnect.getContentPane().add(jLabel2);
       jFrameConnect.getContentPane().add(jLabel3);
       jFrameConnect.getContentPane().add(jLabel4);
       jFrameConnect.getContentPane().add(jTextFieldServerIP);
       jFrameConnect.getContentPane().add(jTextFieldUserName);
       jFrameConnect.getContentPane().add(jPasswordFieldUserPWD);
       jFrameConnect.getContentPane().add(jButtonConnect);
       jFrameConnect.getContentPane().add(jButtonCancel);
       
       // Open the connection data frame and setting its position to center.
       jFrameConnect.pack();
       jFrameConnect.setLocation(dim.width/2-jFrameConnect.getSize().width/2, dim.height/2-jFrameConnect.getSize().height/2);
       jFrameConnect.setVisible(true);    
   }
   
}