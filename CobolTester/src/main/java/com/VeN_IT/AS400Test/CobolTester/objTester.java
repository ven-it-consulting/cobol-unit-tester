package com.VeN_IT.AS400Test.CobolTester;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.VeN_IT.AS400Test.util.ReadEnvList;
import com.VeN_IT.AS400Test.util.reuse;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.ObjectDoesNotExistException;
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

public class objTester extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
    private static managePCML mPCML;
    
    // Creates new form objTester
    public objTester(managePCML pcmlObj) {
        mPCML = pcmlObj;
        initComponents();
        
        // Positioning window to center.
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    
    
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel1 = new javax.swing.JPanel();
        jTextField21 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<String>();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jCheckBoxCommit = new javax.swing.JCheckBox();
        jCheckBoxDisconnect = new javax.swing.JCheckBox();
        
        // Reading environmental folders from properties file. 
        List<String> devStr = ReadEnvList.ReadProperty();
        if (devStr != null) {
        	jDev = new javax.swing.JComboBox<String>();
            jDev.setEditable(false);
            jDev.setEnabled(true);
            for (String str:devStr) {
            	jDev.addItem(str);
            }
            jDev.setSelectedIndex(0);
        }
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cobol modul tester v.1.2.0");
        
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Parameters (in-out)"));
        jPanel2.setAutoscrolls(true);
        
        jButton2.setText("Run...");
        jButton2.setToolTipText("Run given methode of the module with given parameters.");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton2ActionPerformed(evt);
                } catch (PcmlException ex) {
                    reuse.errorBox("PcmlException: " + ex.getLocalizedMessage(), "objTester.initComponents");
                } catch (ParseException ex) {
                    reuse.errorBox("ParseException: " + ex.getLocalizedMessage(), "objTester.initComponents");
                } catch (FileNotFoundException ex) {
                    reuse.errorBox("FileNotFoundException: " + ex.getLocalizedMessage(), "objTester.initComponents");
                } catch (PropertyVetoException ex) {
                    reuse.errorBox("PropertyVetoException: " + ex.getLocalizedMessage(), "objTester.initComponents");
                } catch (AS400SecurityException ex) {
                    reuse.errorBox("AS400SecurityException: " + ex.getLocalizedMessage(), "objTester.initComponents");;
                } catch (ErrorCompletingRequestException ex) {
                    reuse.errorBox("ErrorCompletingRequestException: " + ex.getLocalizedMessage(), "objTester.initComponents");
                } catch (IOException ex) {
                    reuse.errorBox("IOException: " + ex.getLocalizedMessage(), "objTester.initComponents");
                } catch (InterruptedException ex) {
                    reuse.errorBox("InterruptedException: " + ex.getLocalizedMessage(), "objTester.initComponents");
                } catch (ObjectDoesNotExistException ex) {
                    reuse.errorBox("ObjectDoesNotExistException: " + ex.getLocalizedMessage(), "objTester.initComponents");
                } finally {
                    setCursorState(0);
                }
            }
        });

        jButton4.setText("Prev.");
        jButton4.setToolTipText("Go to previous parameter page.");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton4ActionPerformed(evt);
                } catch (FileNotFoundException ex) {
                    reuse.errorBox("FileNotFoundException: " + ex.getLocalizedMessage(), "objTester.initComponents");
                } catch (PcmlException ex) {
                    reuse.errorBox("PcmlException: " + ex.getLocalizedMessage(), "objTester.initComponents");
                } catch (PropertyVetoException ex) {
                	reuse.errorBox("PropertyVetoException: " + ex.getLocalizedMessage(), "objTester.initComponents");
				}
            }
        });

        jButton5.setText("Next");
        jButton5.setToolTipText("Go to next parameter page.");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton5ActionPerformed(evt);
                } catch (FileNotFoundException ex) {
                    reuse.errorBox("FileNotFoundException: " + ex.getLocalizedMessage(), "objTester.initComponents");
                } catch (PcmlException ex) {
                    reuse.errorBox("PcmlException: " + ex.getLocalizedMessage(), "objTester.initComponents");
                } catch (PropertyVetoException ex) {
                	reuse.errorBox("PropertyVetoException: " + ex.getLocalizedMessage(), "objTester.initComponents");
				}
            }
        });
        
        jButton6.setText("Load");
        jButton6.setToolTipText("Load saved parameters back.");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton6ActionPerformed(evt);
                } catch (PcmlException ex) {
                    reuse.errorBox("PcmlException: " + ex.getLocalizedMessage(), "objTester.initComponents");
                }
                
            }
        });

        jButton7.setText("Save");
        jButton7.setToolTipText("Save actual parameters into given file.");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton7ActionPerformed(evt);
                } catch (FileNotFoundException ex) {
                    reuse.errorBox("FileNotFoundException: " + ex.getLocalizedMessage(), "objTester.initComponents");
                } catch (IOException ex) {
                    reuse.errorBox("IOException: " + ex.getLocalizedMessage(), "objTester.initComponents");
                } catch (PcmlException ex) {
                    reuse.errorBox("PcmlException: " + ex.getLocalizedMessage(), "objTester.initComponents");
                } catch (PropertyVetoException ex) {
                	reuse.errorBox("PropertyVetoException: " + ex.getLocalizedMessage(), "objTester.initComponents");
				}
            }
        });
        
        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel10, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel12, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel14, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel15, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel16, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel17, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel18, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel19, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel20, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .add(jButton4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jButton5)
                        
                        .add(jButton6)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton7)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 47, Short.MAX_VALUE)
                        .add(jButton2)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel6))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel7))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel8))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel9))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel10))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel11))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel12))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel13))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel14))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel15))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel16))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel17))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel18))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel19))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel20))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton2)
                    .add(jButton4)
                    .add(jButton5)
                    .add(jButton6)
                    .add(jButton7)))
        );

        jButton4.getAccessibleContext().setAccessibleName("jButtonPrev");
        jButton5.getAccessibleContext().setAccessibleName("jButtonNext");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Result"));

        jScrollPane1.setViewportView(jTextPane1);

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jScrollPane1)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Object to test"));

        jLabel21.setText("Whole name of module to test:");
        jLabel22.setText("Short name of methode to test:");
        jLabel23.setText("Where to find PCML on AS400:");
        jTextField21.setText("");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "-" }));

        jButton1.setText("Parameters");
        jButton1.setToolTipText("Show parameters for given methode.");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton1ActionPerformed(evt);
                } catch (FileNotFoundException ex) {
                    reuse.errorBox("FileNotFoundException: " + ex.getLocalizedMessage(), "objTester.initComponents");
                } catch (PcmlException ex) {
                    reuse.errorBox("PcmlException: " + ex.getLocalizedMessage(), "objTester.initComponents");
                } catch (PropertyVetoException ex) {
                	reuse.errorBox("PropertyVetoException: " + ex.getLocalizedMessage(), "objTester.initComponents");
				}
            }
        });
        
        jButton3.setText(">>");
        jButton3.setToolTipText("Read and fill up list with available methodes.");
        jButton3.setBounds(new java.awt.Rectangle(0, 0, 10, 10));
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        
        jCheckBoxCommit.setText("Call COMMIT after run ");
        jCheckBoxCommit.setToolTipText("Check this box if you want to call COMMIT after running of given module.");
        
        jCheckBoxDisconnect.setText("Disconnect after run");
        jCheckBoxDisconnect.setToolTipText("Check this box if you want to disconnect from AS400 after running of given module.");
        jCheckBoxDisconnect.setSelected(true);
        
        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel22)
                                .add(2, 2, 2)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jComboBox1, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel23)
                                .add(10, 10, 10)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jDev, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel21)
                                .add(12, 12, 12)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTextField21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 52, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jCheckBoxCommit)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jCheckBoxDisconnect)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel23)
                    .add(jDev, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel21)
                    .add(jTextField21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton3))
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel22)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jButton1)
                        .add(jCheckBoxCommit))
                    .add(jCheckBoxDisconnect)))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(0, 0, 0)))
                .add(0, 0, 0))
        );

        pack();
        
        
        // Calls filling of methodes for Enter.
        jTextField21.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    jButton3.doClick();
                }
            }
        });
        
        
        // Calls filling of parameters for Enter.
        jButton3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    jButton3.doClick();
                }
            }
        });
        
        
        // Calls filling of methodes for Enter.
        jButton1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    jButton1.doClick();
                }
            }
        });
        
        
        // Calls "Run" for Enter.
        jButton2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    jButton2.doClick();
                }
            }
        });
        
        
        // Calls "Prev." for Enter.
        jButton4.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    jButton4.doClick();
                }
            }
        });
        
        
        // Calls "Next" for Enter.
        jButton5.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    jButton5.doClick();
                }
            }
        });
    }                    
    
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBoxCommit;
    private javax.swing.JCheckBox jCheckBoxDisconnect;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextPane jTextPane1;
 	private javax.swing.JComboBox<String> jDev;
    
    
    // Calling of "Parameters" button.
    public void callParametersButton() {
        jButton1.doClick();
    }
    
    
    // Code for "Parameters"
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws FileNotFoundException, PcmlException, PropertyVetoException {                                         
        String module = getModul();
        if (managePCML.getFlowStat() > 0) {
        	// Setting cursor to "wait cursor".
            setCursorState(1);
            
            setResult("");
            resetParam();
            mPCML.initFieldIndex();
            mPCML.emptyOutParam();
//            mPCML.resetParamCounter();
            mPCML.fillReference(module, getEnvironment());
            managePCML.setFlowStat(2);
            
            // Setting cursor back.
            setCursorState(0);
        } else {
            reuse.warningBox("You have to use '>>' button first!", "objTester");
        }
        
    }
    
    
    // Calling of "Parameters" button.
    public void callModuleButton() {
        jButton3.doClick();
    }
    
    
    // Code for ">>"
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        String module = getModul();
        
        if (!module.equals("-") && !module.equals("")) {
        	// Setting cursor to "wait cursor".
            setCursorState(1);
            
            setResult("");
            resetParam();
            mPCML.initFieldIndex();
            mPCML.emptyPCML();
            mPCML.pcmlBuild(module, getEnvironment(), true);
            managePCML.setFlowStat(1);
            
            // Setting cursor back.
            setCursorState(0);
        } else {
            reuse.errorBox("Module name is empty!", "objTester");
        }
        
    }
    
    
    // Code for "Run..."
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) throws PcmlException, ParseException, FileNotFoundException, PropertyVetoException, AS400SecurityException, ErrorCompletingRequestException, IOException, InterruptedException, ObjectDoesNotExistException {
        String module = getModul();
        
//        mPCML.listAS400();
        if (managePCML.getFlowStat() >= 2) {
            // Setting cursor to "wait cursor".
            setCursorState(1);
            while (mPCML.isRecordForward()) {
                mPCML.setPaging("F");
                mPCML.fillReference(module, getEnvironment());
            }
            managePCML.setFlowStat(3);
            mPCML.setPaging("R");
            // Only when call was succesful.
            if (mPCML.callPCML(getModul(), getMethod())) {
            	mPCML.initFieldIndex();
                mPCML.fillReference(module, getEnvironment());
            }
            
        } else {
            reuse.warningBox("You have to set all of the input parameters!", "objTester");
        }
    }
    
    
    // Code for "Prev."
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) throws FileNotFoundException, PcmlException, PropertyVetoException {
        String module = getModul();
        
        if (mPCML.isRecordBackrward() && managePCML.getFlowStat() >= 2) {
            mPCML.setPaging("B");
//            resetParam();
            mPCML.fillReference(module, getEnvironment());
        }
    }                                        
    
    
    // Code for "Next"
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) throws FileNotFoundException, PcmlException, PropertyVetoException {                                         
        String module = getModul();
        
        if (mPCML.isRecordForward() && managePCML.getFlowStat() >= 2) {
            mPCML.setPaging("F");
//            resetParam();
            mPCML.fillReference(module, getEnvironment());
        }
    }
    
    
    // Code for "Load"
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) throws PcmlException {
//        mPCML.setPaging("L");
        if (mPCML.LoadParam(this)) {
            mPCML.getRefValueBack(getModul(), getMethod());
        }
    }
    
    
    // Code for "Save"
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) throws FileNotFoundException, IOException, PcmlException, PropertyVetoException {
        if (managePCML.getFlowStat() >= 2) {
            if (mPCML.isRecordForward()) {
                while (mPCML.isRecordForward()) {
                    mPCML.setPaging("F");
                    mPCML.fillReference(getModul(), getEnvironment());
                }
            } else {
                mPCML.setPaging("S");
                mPCML.fillReference(getModul(), getEnvironment());
            }

            mPCML.SaveParam(this);
            mPCML.setPaging("-");
        }
    } 
    
    
    // Resetting all parameter fields.
    public void resetParam() {
        jLabel1.setVisible(false);
        jLabel1.setText("");
        jTextField1.setVisible(false);
        jTextField1.setText("");
        jLabel2.setVisible(false);
        jLabel2.setText("");
        jTextField2.setVisible(false);
        jTextField2.setText("");
        jLabel3.setVisible(false);
        jLabel3.setText("");
        jTextField3.setVisible(false);
        jTextField3.setText("");
        jLabel4.setVisible(false);
        jLabel4.setText("");
        jTextField4.setVisible(false);
        jTextField4.setText("");
        jLabel5.setVisible(false);
        jLabel5.setText("");
        jTextField5.setVisible(false);
        jTextField5.setText("");
        jLabel6.setVisible(false);
        jLabel6.setText("");
        jTextField6.setVisible(false);
        jTextField6.setText("");
        jLabel7.setVisible(false);
        jLabel7.setText("");
        jTextField7.setVisible(false);
        jTextField7.setText("");
        jLabel8.setVisible(false);
        jLabel8.setText("");
        jTextField8.setVisible(false);
        jTextField8.setText("");
        jLabel9.setVisible(false);
        jLabel9.setText("");
        jTextField9.setVisible(false);
        jTextField9.setText("");
        jLabel10.setVisible(false);
        jLabel10.setText("");
        jTextField10.setVisible(false);
        jTextField10.setText("");
        jLabel11.setVisible(false);
        jLabel11.setText("");
        jTextField11.setVisible(false);
        jTextField11.setText("");
        jLabel12.setVisible(false);
        jLabel12.setText("");
        jTextField12.setVisible(false);
        jTextField12.setText("");
        jLabel13.setVisible(false);
        jLabel13.setText("");
        jTextField13.setVisible(false);
        jTextField13.setText("");
        jLabel14.setVisible(false);
        jLabel14.setText("");
        jTextField14.setVisible(false);
        jTextField14.setText("");
        jLabel15.setVisible(false);
        jLabel15.setText("");
        jTextField15.setVisible(false);
        jTextField15.setText("");
        jLabel16.setVisible(false);
        jLabel16.setText("");
        jTextField16.setVisible(false);
        jTextField16.setText("");
        jLabel17.setVisible(false);
        jLabel17.setText("");
        jTextField17.setVisible(false);
        jTextField17.setText("");
        jLabel18.setVisible(false);
        jLabel18.setText("");
        jTextField18.setVisible(false);
        jTextField18.setText("");
        jLabel19.setVisible(false);
        jLabel19.setText("");
        jTextField19.setVisible(false);
        jTextField19.setText("");
        jLabel20.setVisible(false);
        jLabel20.setText("");
        jTextField20.setVisible(false);
        jTextField20.setText("");
        
        this.getContentPane().validate();
        this.getContentPane().repaint();
    }
    
    
    // Setting of module name.
    public void setModul(String inTxt) {
        jTextField21.setText(inTxt);
    }
    
    
    // Returns given module name.
    public String getModul() {
        String value;
        
        value = jTextField21.getText().trim();
        if (value != null && !value.equals("")) {
            value = value.toUpperCase();
            jTextField21.setText(value);
            return value;
        } else {
            return "-";
        }
    }
    
    
    // Setting all selectable methodes.
    public void setMethod(String[] inArray) {
        jComboBox1.removeAllItems();
        for (int i = 0; i < inArray.length; i++ ) {
            if (inArray[i] != null) {
                jComboBox1.addItem(inArray[i]);
            }
        }
    }
    
    
    // Setting selected method.
    public void setMethodIndex(int index) {
        jComboBox1.setSelectedIndex(index);
    }
    
    
    // Getting selected methode.
    public String getMethod() {
        return jComboBox1.getSelectedItem().toString().trim();
    }
    
    
    // Getting selected index.
    public Integer getMethodeIndex() {
        return jComboBox1.getSelectedIndex();
    }
    
    
    // Returns if "Commit" checkbox is checked.
    public boolean commitNeeded() {
        return jCheckBoxCommit.isSelected();
    }
    
    
    // Sets commit status.
    public void setCommit(boolean state) {
        jCheckBoxCommit.setSelected(state);
    }
    
    
    // Returns if "Disconnect" checbox is checked. 
    public boolean disconnectNeeded() {
        return jCheckBoxDisconnect.isSelected();
    }
    
    
    // Sets commit status.
    public void setDisconnect(boolean state) {
        jCheckBoxDisconnect.setSelected(state);
    }
    
    
    // Setting of input-output parameter labels.
    public void setParamLabel(Integer index, String inTxt, Boolean state) {
        switch (index) {
            case 0: jLabel1.setText(inTxt);
                    jLabel1.setVisible(state);
                    jTextField1.setVisible(state);
                    break;
            case 1: jLabel2.setText(inTxt);
                    jLabel2.setVisible(state);
                    jTextField2.setVisible(state);
                    break;
            case 2: jLabel3.setText(inTxt);
                    jLabel3.setVisible(state);
                    jTextField3.setVisible(state);
                    break;
            case 3: jLabel4.setText(inTxt);
                    jLabel4.setVisible(state);
                    jTextField4.setVisible(state);
                    break;
            case 4: jLabel5.setText(inTxt);
                    jLabel5.setVisible(state);
                    jTextField5.setVisible(state);
                    break;
            case 5: jLabel6.setText(inTxt);
                    jLabel6.setVisible(state);
                    jTextField6.setVisible(state);
                    break;
            case 6: jLabel7.setText(inTxt);
                    jLabel7.setVisible(state);
                    jTextField7.setVisible(state);
                    break;
            case 7: jLabel8.setText(inTxt);
                    jLabel8.setVisible(state);
                    jTextField8.setVisible(state);
                    break;
            case 8: jLabel9.setText(inTxt);
                    jLabel9.setVisible(state);
                    jTextField9.setVisible(state);
                    break;
            case 9: jLabel10.setText(inTxt);
                    jLabel10.setVisible(state);
                    jTextField10.setVisible(state);
                    break;
            case 10: jLabel11.setText(inTxt);
                     jLabel11.setVisible(state);
                     jTextField11.setVisible(state);
                     break;
            case 11: jLabel12.setText(inTxt);
                     jLabel12.setVisible(state);
                     jTextField12.setVisible(state);
                     break;
            case 12: jLabel13.setText(inTxt);
                     jLabel13.setVisible(state);
                     jTextField13.setVisible(state);
                     break;
            case 13: jLabel14.setText(inTxt);
                     jLabel14.setVisible(state);
                     jTextField14.setVisible(state);
                     break;
            case 14: jLabel15.setText(inTxt);
                     jLabel15.setVisible(state);
                     jTextField15.setVisible(state);
                     break;
            case 15: jLabel16.setText(inTxt);
                     jLabel16.setVisible(state);
                     jTextField16.setVisible(state);
                     break;
            case 16: jLabel17.setText(inTxt);
                     jLabel17.setVisible(state);
                     jTextField17.setVisible(state);
                     break;
            case 17: jLabel18.setText(inTxt);
                     jLabel18.setVisible(state);
                     jTextField18.setVisible(state);
                     break;
            case 18: jLabel19.setText(inTxt);
                     jLabel19.setVisible(state);
                     jTextField19.setVisible(state);
                     break;
            case 19: jLabel20.setText(inTxt);
                     jLabel20.setVisible(state);
                     jTextField20.setVisible(state);
                     break;
            default: reuse.errorBox("Given index is greater than number of definied fields in the screen!", "objTester.setParamLabel");
                     break;
        }
    }
    
    
    // Setting of input-output field values.
    public void setParamValues(Integer index, String inTxt) {
        switch (index) {
            case 0: jTextField1.setText(inTxt);
                    break;
            case 1: jTextField2.setText(inTxt);
                    break;
            case 2: jTextField3.setText(inTxt);
                    break;
            case 3: jTextField4.setText(inTxt);
                    break;
            case 4: jTextField5.setText(inTxt);
                    break;
            case 5: jTextField6.setText(inTxt);
                    break;
            case 6: jTextField7.setText(inTxt);
                    break;
            case 7: jTextField8.setText(inTxt);
                    break;
            case 8: jTextField9.setText(inTxt);
                    break;
            case 9: jTextField10.setText(inTxt);
                    break;
            case 10: jTextField11.setText(inTxt);
                     break;
            case 11: jTextField12.setText(inTxt);
                     break;
            case 12: jTextField13.setText(inTxt);
                     break;
            case 13: jTextField14.setText(inTxt);
                     break;
            case 14: jTextField15.setText(inTxt);
                     break;
            case 15: jTextField16.setText(inTxt);
                     break;
            case 16: jTextField17.setText(inTxt);
                     break;
            case 17: jTextField18.setText(inTxt);
                     break;
            case 18: jTextField19.setText(inTxt);
                     break;
            case 19: jTextField20.setText(inTxt);
                     break;
            default: reuse.errorBox("Given index is greater than number of definied fields in the screen!", "objTester.setParamValues");
                     break;
        }
    }
    
    
    // Getting of parameter values.
    public String getParamValue(Integer index) {
        
        String fieldValue;
        switch (index) {
            case 0: if (jTextField1.isVisible()) {
                        fieldValue = jTextField1.getText();
                    } else {fieldValue = null;}
                    break;
            case 1: if (jTextField2.isVisible()) {
                        fieldValue = jTextField2.getText();
                    } else {fieldValue = null;}
                    break;
            case 2: if (jTextField3.isVisible()) {
                        fieldValue = jTextField3.getText();
                    } else {fieldValue = null;}
                    break;
            case 3: if (jTextField4.isVisible()) {
                        fieldValue = jTextField4.getText();
                    } else {fieldValue = null;}
                    break;
            case 4: if (jTextField5.isVisible()) {
                        fieldValue = jTextField5.getText();
                    } else {fieldValue = null;}
                    break;
            case 5: if (jTextField6.isVisible()) {
                        fieldValue = jTextField6.getText();
                    } else {fieldValue = null;}
                    break;
            case 6: if (jTextField7.isVisible()) {
                        fieldValue = jTextField7.getText();
                    } else {fieldValue = null;}
                    break;
            case 7: if (jTextField8.isVisible()) {
                        fieldValue = jTextField8.getText();
                    } else {fieldValue = null;}
                    break;
            case 8: if (jTextField9.isVisible()) {
                        fieldValue = jTextField9.getText();
                    } else {fieldValue = null;}
                    break;
            case 9: if (jTextField10.isVisible()) {
                        fieldValue = jTextField10.getText();
                    } else {fieldValue = null;}
                    break;
            case 10: if (jTextField11.isVisible()) {
                        fieldValue = jTextField11.getText();
                    } else {fieldValue = null;}
                    break;
            case 11: if (jTextField12.isVisible()) {
                        fieldValue = jTextField12.getText();
                    } else {fieldValue = null;}
                    break;
            case 12: if (jTextField13.isVisible()) {
                        fieldValue = jTextField13.getText();
                    } else {fieldValue = null;}
                    break;
            case 13: if (jTextField14.isVisible()) {
                        fieldValue = jTextField14.getText();
                    } else {fieldValue = null;}
                    break;
            case 14: if (jTextField15.isVisible()) {
                        fieldValue = jTextField15.getText();
                    } else {fieldValue = null;}
                    break;
            case 15: if (jTextField16.isVisible()) {
                        fieldValue = jTextField16.getText();
                    } else {fieldValue = null;}
                    break;
            case 16: if (jTextField17.isVisible()) {
                        fieldValue = jTextField17.getText();
                    } else {fieldValue = null;}
                    break;
            case 17: if (jTextField18.isVisible()) {
                        fieldValue = jTextField18.getText();
                    } else {fieldValue = null;}
                    break;
            case 18: if (jTextField19.isVisible()) {
                        fieldValue = jTextField19.getText();
                    } else {fieldValue = null;}
                    break;
            case 19: if (jTextField20.isVisible()) {
                        fieldValue = jTextField20.getText();
                    } else {fieldValue = null;}
                    break;
            default: fieldValue = "";
                     break;
        }
        if (fieldValue != null) {
            return fieldValue;
        } else {
            return null;
        }
    }
    
    
    // Setting cursor state to waiting or normal.
    private void setCursorState(int state) {
        if (state <= 0) {
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        
    }
    
    
    // Getting environment String value.
    public String getEnvironment() {
    	return jDev.getSelectedItem().toString();
    }
    
    
    // Setting of result in the text field.
    public void setResult(String inTxt) {
        jTextPane1.setText("");
        jTextPane1.setText(inTxt);
    }
    
}
