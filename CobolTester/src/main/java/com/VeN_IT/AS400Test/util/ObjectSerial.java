package com.VeN_IT.AS400Test.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.VeN_IT.AS400Test.CobolTester.managePCML;
import com.VeN_IT.AS400Test.CobolTester.objTester;
import com.VeN_IT.AS400Test.CobolTester.serParamObj;

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

public class ObjectSerial {
	
	// Serialize parameter object.
    public static boolean Serialize(objTester gui, String[][] refValues) throws FileNotFoundException, IOException {
        serParamObj paramObj = new serParamObj();
        
        // Filling up all parameters.
        paramObj.setModule(gui.getModul());
        paramObj.setMethode(gui.getMethodeIndex());
        paramObj.setRefValues(refValues);
        paramObj.setCommitSwitch(gui.commitNeeded());
        paramObj.setDisconnectSwitch(gui.disconnectNeeded());
        
        ObjectOutputStream outStream = null;
        String filePath;
        try {
            filePath = getFileChooser(gui, "S");
            if (filePath.equals("-")) return false;
            FileOutputStream fileOut = new FileOutputStream(filePath + ".sav");
            outStream = new ObjectOutputStream(fileOut);
            outStream.writeObject(paramObj);
            outStream.close();
            fileOut.close();
        } catch (FileNotFoundException ex) {
            reuse.errorBox(ex.getLocalizedMessage(), "ObjectSerial.Serialize");
            return false;
        } catch (IOException ex) {
            reuse.errorBox(ex.getLocalizedMessage(), "ObjectSerial.Serialize");
            return false;
        } finally {
            try {
                if (outStream != null) {
                    outStream.flush();
                    outStream.close();
                }
            } catch (IOException ex) {
                reuse.errorBox(ex.getLocalizedMessage(), "ObjectSerial.Serialize");
                return false;
            }
        }
        return true;
    }
    
    
    // Deserialize parameter object.
    @SuppressWarnings("finally")
	public static boolean Deserialize(objTester gui) {
        serParamObj paramObj = null;
        String filePath = "-";
        
        try {
            filePath = getFileChooser(gui, "O");
            if (filePath.equals("-")) return false;
            FileInputStream fileIn = new FileInputStream(filePath);  
            ObjectInputStream in = new ObjectInputStream(fileIn);  
            paramObj = (serParamObj) in.readObject();  
            in.close();
            fileIn.close();
        } catch(IOException ex) {
            reuse.errorBox(ex.getLocalizedMessage(), "ObjectSerial.Deserialize");
            return false;
        } catch(ClassNotFoundException ex) {  
            reuse.errorBox(ex.getLocalizedMessage(), "ObjectSerial.Deserialize");
            return false;
        } finally {
            if (!filePath.equals("-")) {
                // Filling back all parameters.
                gui.setModul(paramObj.getModule());
                // Calling ">>" button
                gui.callModuleButton();
                gui.setMethodIndex(paramObj.getMethode());
                // Calling "Parameters" button
                gui.callParametersButton();
                managePCML.setReFValues(paramObj.getRefValues());
                gui.setCommit(paramObj.isCommitSwitch());
                gui.setDisconnect(paramObj.isDisconnectSwitch());
                return true;
            } else return false;
        }
    }
    
    
    /**
     * File choose
     * 	Modes:
     * 		O: Open mode
     * 		S: Save mode
    */
    private static String getFileChooser (objTester gui, String mode) {
    	String workingDir = System.getProperty("user.dir");
    	int retValue;
    	File fl = new File(workingDir + "/Save");
    	JFileChooser chooser = new JFileChooser();
    	
    	// When "Save" folder as default folder set in properties file.
    	if (ManageProperty.getValue("defaultSave").equals("Y")) {
    		chooser.setCurrentDirectory(fl);
    	}
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "Cobol tester save file", "sav");
        chooser.setFileFilter(filter);
        
        // Open mode.
        if (mode.equals("O")) {
            retValue = chooser.showOpenDialog(gui);
        } else {
            retValue = chooser.showSaveDialog(gui);
        }
        
        if (retValue == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getPath();
        } else {
            return "-";
        }
    }
    
}
