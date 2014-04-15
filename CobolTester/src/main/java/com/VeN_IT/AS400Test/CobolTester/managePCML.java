package com.VeN_IT.AS400Test.CobolTester;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.VeN_IT.AS400Test.AS400.AS400Connect;
import com.VeN_IT.AS400Test.AS400.RunCommand;
import com.VeN_IT.AS400Test.interfaces.readPCMLInt;
import com.VeN_IT.AS400Test.util.ManageProperty;
import com.VeN_IT.AS400Test.util.ObjectSerial;
import com.VeN_IT.AS400Test.util.reuse;
import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.BidiStringType;
import com.ibm.as400.access.CommandCall;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.IFSFileInputStream;
import com.ibm.as400.access.ObjectDoesNotExistException;
import com.ibm.as400.data.PcmlException;
import com.ibm.as400.data.ProgramCallDocument;

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

public class managePCML {
	
    private static AS400 as400 = null;
    
    private static String outText = "";
    
    private static String[] methodes;
    private static String[][][] reference;
    private static String[][][] outParam;
    private static String[][] refValues = new String[250][2];                   // Reference values that user have entered.
    private static int fieldIndex = 1;                                          // Index of last read / wrote field.
    private static int[] outParamCount = new int[170];                          // Counter of array outParam index.
    private objTester gui;
    private static ProgramCallDocument pcml;                                    // com.ibm.as400.data.ProgramCallDocument
    private static IFSFileInputStream pcmlStream;								// PCML input stream
    private static readPCMLInt readpcml;
    private static String paging = "-";                                         // Indicates if there was a paging.
    private static String fieldName, fieldValue;
    private static int flowStat = 0;                                            // Indicates the actual "flow position".
    private int valMaxanzahl;
    private static String[] libraryList = new String[10];						// Library list of execution.
    
    
    // Building the connection to AS/400;
    public static boolean buildConnection(String server, String userName, String userPwd) throws AS400SecurityException, IOException {
        
        as400 = AS400Connect.buildConnection(as400, server, userName, userPwd);
        
        if (as400 != null) {
        	return true;
        } else {
        	return false;
        }
    }
    
    
    /**
     * Setting of actual program flow position indicator.
     * 
     * Indicators:	0: start of the program
     * 				1: module name set
     * 				2: methode set
     * 				3: done ("Futtatás" have pushed)
     */
    public static void setFlowStat(int stat) {
        flowStat = stat;
    }
    
    
    // Getting of actual flow position indicator.
    public static int getFlowStat() {
        return flowStat;
    }
    
    
    public static void setLib() {
        // Creating runtime library list.
        try {
            CommandCall command = new CommandCall(as400);
            
            // Setting system properties.
            command.run("STRCMTCTL LCKLVL(*CHG)");
            command.run("CHGJOB INQMSGRPY(*DFT)");
            
            String lastLibrary = null;
            String[] libList = libraryList;
            
            for (String library : libList)
            {
                String addLiblE = "ADDLIBLE LIB(";
                if (lastLibrary == null)
                {
                    addLiblE = addLiblE.concat(library + ") POSITION(*FIRST)");
                }
                else
                {
                    addLiblE = addLiblE.concat(library + ") POSITION(*AFTER " + lastLibrary + ")");
                }
                command.run(addLiblE);
                lastLibrary = library;
            }
        }

        catch (AS400SecurityException e)
        {
            reuse.errorBox("AS400SecurityException: " + e.getLocalizedMessage(), "managePCML.setLiB");
        }
        catch (ErrorCompletingRequestException e)
        {
            reuse.errorBox("ErrorCompletingRequestException: " + e.getLocalizedMessage(), "managePCML.setLiB");
        }
        catch (IOException e)
        {
            reuse.errorBox("IOException: " + e.getLocalizedMessage(), "managePCML.setLiB");
        }
        catch (InterruptedException e)
        {
            reuse.errorBox("InterruptedException: " + e.getLocalizedMessage(), "managePCML.setLiB");
        }
        catch (PropertyVetoException e)
        {
            reuse.errorBox("PropertyVetoException: " + e.getLocalizedMessage(), "managePCML.setLiB");
        }
    }
    
    
    public static void actionCancel() {
        closeAll();
    }
    
    
    // Setting of Library list in title.
    public static void setLibl(String libl, int index) {
    	switch (index) {
    		case 1: libraryList[0] = libl; 
    		case 2: libraryList[1] = libl;
    		case 3: libraryList[2] = libl;
    		case 4: libraryList[3] = libl;
    		case 5: libraryList[4] = libl;
    		case 6: libraryList[5] = libl;
    		case 7: libraryList[6] = libl;
    		case 8: libraryList[7] = libl;
    		case 9: libraryList[8] = libl;
    		case 10: libraryList[9] = libl;
    	}
    }
    
    
    // Setting title of the frame.
    public void setTitleLibl() {
    	String frameTitle = gui.getTitle();
    	String tmpLibl = "", actLibl = "";
    	
    	for (int i=0; i<10; i++) {
    		if (!libraryList[i].equals("")) {
    			tmpLibl += libraryList[i] + ", ";
    		}
    	}
    	actLibl = " (Actual library list is: " + tmpLibl.substring(0, tmpLibl.length()-2) + ")";
    	
    	gui.setTitle(frameTitle + actLibl);
    	
    	// Setting actual library list.
        setLib();
    }
    
    
    // Showing up tester GUI
    public void showTester() {
        gui.setVisible(true);
    }
    
    
    // Managing COBOL object calls.
    public void pcmlBuild(String cobObj, String env, boolean isGUI) {
        String prefix = ManageProperty.getValue("pcmlPathPrefix");
        String postfix = ManageProperty.getValue("pcmlPathPostfix");
        String serverPath = "";
        
		try {
			// When there's no environmental folder in path.
			if (env.equals("")) {
				serverPath = prefix.substring(0, prefix.length() - 1) + postfix.substring(1, prefix.length()) + cobObj.trim() + ".pcml";
			} else {
				serverPath = prefix + env + postfix + cobObj.trim() + ".pcml";
			}
			
			pcmlStream = new IFSFileInputStream(as400, serverPath);
			readpcml = new readPCML(pcmlStream);
			
			methodes = readpcml.getPCMLMethodes();
	        reference = readpcml.getPCMLIn();
	        outParam = readpcml.getPCMLOut();
	        outParamCount = readpcml.getPCMLOutCounter();
	        
	        // Setting methodes on GUI.
	        if (isGUI) gui.setMethod(methodes);
	        
		} catch (AS400SecurityException e) {
			reuse.errorBox("Problem with PCML" + e.getLocalizedMessage(), "managePCML.pcmlTest");
		} catch (IOException e) {
			reuse.errorBox("Can't find PCML: " + e.getLocalizedMessage(), "managePCML.pcmlTest");
		}
		
    }
    
    
    // Initializing fieldIndex.
    public void initFieldIndex() {
        fieldIndex = 1;
        setPaging("-");
    }
    
    
    // Setting of paging indicator.
    public void setPaging (String direction) {
        // 'F' is forward,
        // 'B' is backward.
        // 'R' is run
        paging = direction.toUpperCase();
        if (paging.equals("B")) {
            stepBack();
        }
    }
    
    
    // Setting back fieldIndex.
    private void stepBack() {
    
        if (fieldIndex >= 19) {
            if (fieldIndex == 19) {
                fieldIndex = 1;
            } else {
                if (paging.equals("R")) {
                    fieldIndex = Math.max(fieldIndex - 60, 1);
                } else {
                    fieldIndex = Math.max(fieldIndex - 40, 1);
                }
            }
        } 
    
    }
    
    
    // Filling PCML referencia values back to the refValues.
    public void getRefValueBack( String cobObj, String methode) throws PcmlException {
        
        Object retValue;
        BigDecimal convObj;
        
        try {
        	valMaxanzahl = 0;
            for (int j = 1; j < 250; j++) {
                if (refValues[j][0] != null) {
                    fieldName = refValues[j][0];
                    retValue = pcml.getValue(cobObj + "_" + methode + ".REFERENZ." + fieldName);
                    
                    if (retValue == null) return;
                    
                    if (retValue.getClass().toString().equals("class java.math.BigDecimal")) {
                        // It's normal numeric format.
                    	convObj = convertToBigDecimal(retValue);
                        
                        refValues[j][1] = convObj.toString();
                        
                        /*String tmp = refValues[j][1];
                        // We're thinking it's a date...
                        if (tmp.length() == 8) {
                        	refValues[j][1] = tmp.substring(0, 4);
                        	refValues[j][1] += "-" + tmp.substring(4, 6);
                        	refValues[j][1] += "-" + tmp.substring(6, 8) + ".";
                        }*/
                        
                        // Checking if is there any "MAXANZAHL" return value.
                        if (fieldName.equals("MAXANZAHL")) {
                            valMaxanzahl = convObj.intValue();
                        }
                        
                    } else {
                        refValues[j][1] = retValue.toString();
                    }
                }    
            } 
        } catch (PcmlException e) {
            	reuse.errorBox("PcmlException: " + e.getLocalizedMessage(), "managePCML.getRefBack");
        }    	
        finally {
        	// Page refreshing.
        	initFieldIndex();
            int index = gui.getMethodeIndex();
            for (int j = 0; j <= 20; j++) {
                if (reference[index][j][0] != null) {
                    gui.setParamValues(j - 1, refValues[j][1]);
                }
            }
            fieldIndex = 21;
        }
    }
    
    
    // Show parameters in GUI.
    public void fillReference(String cobObj, String env) throws PcmlException, PropertyVetoException {
        
        try
        {        
            // Uncomment the following to get debugging information
//            com.ibm.as400.data.PcmlMessageLog.setTraceEnabled(true);
            
            outText = "Calling of " + cobObj + "\r\n";
            outText += "    Setting given data into PCML...\r\n";
            
            // Construct ProgramCallDocument
            if (flowStat <= 2 && !paging.equals("S")) {
            	String prefix = ManageProperty.getValue("pcmlPathPrefix");
            	String postfix = ManageProperty.getValue("pcmlPathPostfix");
            	
            	try {
            		// Trying to replace incomplete path in PCML.
            		pcmlStream = new IFSFileInputStream(as400, prefix + env + postfix + cobObj.trim() + ".pcml");
 	                	        
        		} catch (AS400SecurityException e) {
        			reuse.errorBox("AS400SecurityException: " + e.getLocalizedMessage(), "managePCML.fillReference");
        		} catch (IOException e) {
        			reuse.errorBox("IOException: " + e.getLocalizedMessage(), "managePCML.fillReference");
        		}
            	
            	pcml = new ProgramCallDocument(as400, cobObj, pcmlStream, null, null, ProgramCallDocument.SOURCE_PCML);
            }
            
            int index = gui.getMethodeIndex();
            int saveCounter = 1;
            
            // Saving all the information from GUI into refValues.
            if (paging.equals("F")) {
                // Forward
                if (fieldIndex <= 21) {
                    saveCounter = 1;
                } else {
                    saveCounter = fieldIndex - 20;
                }
            } else if (paging.equals("B")) {
                // Back
                saveCounter = Math.min(249, fieldIndex + 20);
            } else if (paging.equals("R" ) || paging.equals("S")) {
                // Run
                saveCounter = Math.max(1, fieldIndex - 20);
            }
            
            int guiCount = 0;
            String parValue;
            
            if (!paging.equals("R") || (paging.equals("R") && !isRecordForward() && reference[index][fieldIndex - 20][0] != null)) {
                for (int i = saveCounter; i < saveCounter + 20; i++) {
                    parValue = gui.getParamValue(guiCount);
                    refValues[i][0] = reference[index][i][0];
                    if (parValue != null) {
                        refValues[i][1] = parValue;
                    }
                    guiCount++;
                }
                    
                // Reset of gui.
                gui.resetParam();
            
                // Setting labels and default values of input fields.
                String labelValue;
                int textFieldIndex = 0;
                int setIndex;
                
                if (!paging.equals("R") && !paging.equals("S")) {
                    setIndex = fieldIndex;
                } else {
                    setIndex = Math.max(1, fieldIndex - 20);
                }
                
                for (int j = setIndex; j < setIndex + 20; j++) {

                    if (reference[index][j][0] != null) {
                        labelValue = reference[index][j][0] + " (" +
                                     reference[index][j][1] + " " +
                                     reference[index][j][2];

                        if (reference[index][j][3] != null && refValues[j][0] == null) {
                            if (!reference[index][j][3].equals("")) {
                             labelValue += "," + reference[index][j][3] + ")";
                            }
                        }

                        if (!labelValue.substring(labelValue.length() - 1).equals(")")) {
                            labelValue += ")";
                        }

                        gui.setParamLabel(textFieldIndex, labelValue, true);
                        
                        if (refValues[j][1] != null) {
                            gui.setParamValues(textFieldIndex, refValues[j][1]);
                        } else {
                            if(reference[index][j][1].equals("char")) {
                            gui.setParamValues(textFieldIndex, "");
                            } else {
                                gui.setParamValues(textFieldIndex, "0");
                            }
                        }
                        textFieldIndex ++;
                    }
                    fieldIndex = j + 1;
                }
            
            }
            
        }catch (PcmlException e) {
            reuse.errorBox("*** Calling of " + cobObj + " is unsuccessful ***\r\n" + e.getLocalizedMessage(), "managePCML.fillReference");
        }
        
    }
    
    
    // Is there any records paging forward?
    public boolean isRecordForward() {
        Integer index = gui.getMethodeIndex();
        if (getFlowStat() > 1) {
            return reference[index][fieldIndex + 1][0] != null;
        } else {
            return false;
        }
    } 
    
    
    // Is there any records paging backward?
    public boolean isRecordBackrward() {
        if (getFlowStat() > 1) {
            return fieldIndex - 20 >= 1;
        } else {
            return false;
        }
        
    } 
    
    
    // Returns the actual field index.
    public int getFieldIndex() {
        return fieldIndex;
    }
    
    
    // Filling data from GUI into PCML. 
    private boolean fillPCML (String cobObj, String methode) throws PcmlException, ParseException, FileNotFoundException {
        String pcmlValue;
        
        // Filling reference values
        Integer index = gui.getMethodeIndex();
        String refName, typlen;
        int parlength = 0;
        int declength;
        
        try {
            for (int j = 1; j < 250; j++) {
            if (reference[index][j][0] != null) {
                fieldName = reference[index][j][0];
                pcmlValue = cobObj + "_" + methode + ".REFERENZ." + fieldName;
                refName = refValues[j][0];
                if (refName != null) {
                    refName = refName.trim();
                } else refName = "";
                
                // Checking the length of value.
                typlen = reference[index][j][2];
                if (typlen != null) {
                     parlength = Integer.parseInt(typlen);
                     if (!reference[index][j][1].trim().equals("char")) {
                         if (!reference[index][j][3].equals("0")) {
                             declength = Integer.parseInt(reference[index][j][3]);
                             parlength += declength + 1;
                         } 
                     }
                }
                
                if (refValues[j][1] != null) {
                    if (refValues[j][1].length() > parlength) {
                        reuse.warningBox("Given value is longer than limit at field: " + refValues[j][0], "managePCML.fillPCML");
                        return false;
                    }
                }
                
                if (fieldName.equals(refName)) {
                    if (refValues[j][1] != null) {
                        if (refValues[j][1].equals(" ") ) {
                            fieldValue = refValues[j][1];
                        } else {
                            fieldValue = refValues[j][1].trim();
                        }
                    }
                } else {
                	reuse.errorBox("Field names doesn't match: Index = " + Integer.toString(j) + " (fieldName = " + fieldName +
                          " refValue = " + refValues[j][0], "managePCML.fillPCML");
                    return false;
                }
                
                if(reference[index][j][1].equals("char")) {

                    pcml.setStringValue(pcmlValue, fieldValue, BidiStringType.DEFAULT);

                } else {
                    if (fieldValue != null) {
                        if (fieldValue.isEmpty() || fieldValue.equals(" ")) {
                            fieldValue = "0.0";
                        }
                        // US format is: "835,111.2" User have to use this format entering decimal numbers.
                        DecimalFormat format = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                        format.setParseBigDecimal(true);
                        BigDecimal number = (BigDecimal) format.parse(fieldValue);
                        
                        pcml.setValue(pcmlValue, number);
                    }
                    
                }

            }
            
        }
            String pcmlRef = "." + ManageProperty.getValue("pcmlStrRef");
            pcml.setStringValue(cobObj + "_" + methode + pcmlRef + ".DUMMY", " ", BidiStringType.DEFAULT);
        }
        catch (PcmlException e) {
            reuse.errorBox("PcmlException: " + e.getLocalizedMessage(), "managePCML.fillPCML");
            return false;
        }
        catch (ParseException e) {
            reuse.errorBox("ParseException: " + e.getLocalizedMessage(), "managePCML.fillPCML");
            return false;
        }
        return true;
    }
    
    
    // Calling of Cobol module by PCML.
    public boolean callPCML(String cobObj, String methode) throws PcmlException,
            ParseException,
            FileNotFoundException,
            PropertyVetoException,
            AS400SecurityException,
            ErrorCompletingRequestException,
            IOException,
            InterruptedException,
            ObjectDoesNotExistException {
        
        boolean rc = true;                  // Return code from ProgramCallDocument.callProgram()
        String msgId, msgText;              // Messages returned from the server
        Object value;                       // Return value from ProgramCallDocument.getValue()
        
        Object retValue;
        
        fillReference(cobObj, gui.getEnvironment());
        if (!fillPCML(cobObj, methode)) return false;            
            
            // Request to call the API
            outText = "    Call: " + cobObj + "-" + methode + "...\r\n";
            
            // Reconnect if needed.
            if (as400 != null) {
                if (!as400.isConnected()) {
                	as400.connectService(0);
                	// Setting library list.
                	setLib();
                }
            }
            
            try {
                rc = pcml.callProgram(cobObj + "_" + methode);

                // If return code is false, we receive messages from the server
                if(!rc) {
                    // Retrieve list of server messages
                    AS400Message[] msgs = pcml.getMessageList(cobObj + "_" + methode);
                    
                    // Iterate through messages and write them to standard output
                    for (int m = 0; m < msgs.length; m++) 
                    {
                        msgId = msgs[m].getID();
                        msgText = msgs[m].getText();
                        outText += "    " + msgId + " - " + msgText + "\r\n";
                    }
                    outText += "** Calling of " + cobObj + "-" + methode + " is unsucceessful. Please find message(s) above! **\r\n";
                }
                
                else {
                	String pcmlPath = "." + ManageProperty.getValue("pcmlStrRes") + "."
                			+ ManageProperty.getValue("pcmlResNode") + "." + ManageProperty.getValue("pcmlRetValue");
                	String pcmlVariable = "";
                	
                    value = pcml.getValue(cobObj + "_" + methode + pcmlPath);
                    outText += "      Return value: " + value + "\r\n";
                    
                    // When COMMIT is set and there wasn't any error, we call COMMIT.
                    if (gui.commitNeeded() && value.equals("0")) {
                        RunCommand.callCommit(as400);
                    }
                    
                    // Test section:
                    String fehler, fehlernr, korr, oszt, modul;
                    int[] indices = new int[2];
                    
                    pcmlPath = "." + ManageProperty.getValue("pcmlStrRes") + "." + ManageProperty.getValue("pcmlStrErr") + "."
                    		+ ManageProperty.getValue("pcmlFailVars") + ".";
                    
                    for (int i = 0; i < 20; i++) 
                    {
                        indices[0] = i;
                        pcmlVariable = ManageProperty.getValue("pcmlFailField");
                        fehler = (String)pcml.getValue(cobObj + "_" + methode + pcmlPath + pcmlVariable, indices);
                        pcmlVariable = ManageProperty.getValue("pcmlFailNum");
                        fehlernr = (String)pcml.getValue(cobObj + "_" + methode + pcmlPath + pcmlVariable, indices);
                        pcmlVariable = ManageProperty.getValue("pcmlFailCorr");
                        korr = (String)pcml.getValue(cobObj + "_" + methode + pcmlPath + pcmlVariable, indices);
                        pcmlVariable = ManageProperty.getValue("pcmlFailClass");
                        oszt = (String)pcml.getValue(cobObj + "_" + methode + pcmlPath + pcmlVariable, indices);
                        pcmlVariable = ManageProperty.getValue("pcmlFailMod");
                        modul = (String)pcml.getValue(cobObj + "_" + methode + pcmlPath + pcmlVariable, indices);

                        if (!fehler.equals("")) {
                            outText += "      Fehlerfeld:   " + fehler + "\r\n";
                            outText += "      Fehlercode:   " + fehlernr + "\r\n";
                            outText += "      Fehler korr.: " + korr + "\r\n";
                            outText += "      Fehler klass: " + oszt + "\r\n";
                            outText += "      Fehler modul: " + modul + "\r\n";
                        }
                    }

                    // Getting output parameter values
                    Integer index = gui.getMethodeIndex();
                    outText += "\r\nOutput parameters:\r\n\r\n";

                    // Filling of in-out parameter values back.
                    getRefValueBack(cobObj, methode);
                    String pcmlParam = "." + ManageProperty.getValue("pcmlStrPar") + ".";
                    String pcmlParamTab = pcmlParam + ManageProperty.getValue("pcmlStrOut") + ".";
                    
                    if (value.toString().equals("0") || (!value.toString().equals("0") && valMaxanzahl > 0)) {
	                    for (int i = 0; i < outParamCount[index]; i++) {
	                        // Quit when reached MAXANZAHL.
	                    	//  || !value.equals("0") (have been removed)
	                        if (valMaxanzahl > 0 && i >= valMaxanzahl) break;
	                        indices[0] = i;
	                        for (int j = 1; j < 250; j++) {
	                        	
	                            if (outParam[index][j][0] != null) {
	                                fieldName = outParam[index][j][0];
	                                if (outParamCount[index] > 1) {
	                                    retValue = pcml.getValue(cobObj + "_" + methode + pcmlParamTab + fieldName, indices);
	                                } else {
	                                    retValue = pcml.getValue(cobObj + "_" + methode + pcmlParam + fieldName, indices);
	                                }
	                                
	                                if (retValue != null) {
	                                	
	                                    if (retValue.getClass().toString().equals("class java.math.BigDecimal")) {
	                                        // It's normal numeric format.
	                                        final BigDecimal convObj;
	                                        convObj = convertToBigDecimal(retValue);
	                                        
	                                        fieldValue = convObj.toString();
	                                        
	                                        if (outParam[index][j][2].equals("8") && outParam[index][j][3].equals("0")) {
	                                            // We think it's a date...
	                                            String tmp;
	                                            final Integer intObj;
	                                            intObj = ((Number)retValue).intValue();
	                                            tmp = intObj.toString();
	                                            if (tmp.length() == 8) {
	                                                fieldValue = tmp.substring(0, 4);
	                                                fieldValue += "-" + tmp.substring(4, 6);
	                                                fieldValue += "-" + tmp.substring(6, 8) + ".";
	                                            } else {
	                                                fieldValue = "0";
	                                            }
	                                        }
	                                    } else {
	                                        fieldValue = (String)retValue;
	                                    }
	                                }
	                                outText += fieldName + ": " + fieldValue + "\r\n";
	                            }
	                        }
	                        outText += "----------------------------------------- " + Integer.toString(i + 1) + ". section\r\n";
	                    }
                    }
                }
            } catch (PcmlException e) {
                reuse.errorBox("PcmlException: " + e.getLocalizedMessage(), "managePCML.callPCML");
            }
            
            // When disconnect is set.
            if (gui.disconnectNeeded()) {
                closeAll();
            }
            setResult();
            return rc;
    }
    
    
    // Convert given object to BigDecimal.
    private static BigDecimal convertToBigDecimal(Object value) {
        BigDecimal ret = null;
        if( value != null ) {
            if( value instanceof BigDecimal ) {
                ret = (BigDecimal) value;
            } else if( value instanceof BigInteger ) {
                ret = new BigDecimal( (BigInteger) value );
            } else if( value instanceof Number ) {
                ret = new BigDecimal( ((Number)value).doubleValue() );
            } else {
            	reuse.errorBox("Not possible to coerce ["+value+"] from class "+value.getClass()+" into a BigDecimal.", "managePCML.convertToBigDecimal");
            }
        }
        return ret;
    }
    
    
    // Showing output text.
    private void setResult() {
        gui.setResult(outText);
    }
    
    
    // Disconnecting from AS/400
    private static void closeAll() {
        AS400Connect.closeConnection(as400);
    }
    
    
    // Flushing all of the PCML data.
    public void emptyPCML () {
        int i, j, k;
        
        if (methodes != null) {
            for (i = 0; i < 170; i++) {
                methodes[i] = null;
                for(j = 0; j < 250; j++) {
                    for (k = 0; k < 4; k++) {
                        reference[i][j][k] = null;
                        outParam[i][j][k] = null;
                        refValues[j][0] = null;
                        refValues[j][1] = null;
                    }
                }
            }
            pcml = null;
            setFlowStat(0);
        }
    }
    
    
    // Flushing outParam values.
    public void emptyOutParam () {
        if (refValues != null) {
        	for (int j = 0; j < 250; j++) {
        		refValues[j][0] = null;
        		refValues[j][1] = null;
        	}
        }
    }
    
    
    // Restore refValues on deserialization
    public static void setReFValues(String[][] value) {
    	refValues = value;
    }
    
    
    // Saving all the parameters.
    public boolean SaveParam(objTester gui) throws FileNotFoundException, IOException {
    	return ObjectSerial.Serialize(gui, refValues);
    }
    
    
    // Loading all the parameters.
    public boolean LoadParam(objTester gui) {
    	return ObjectSerial.Deserialize(gui);
    }
    
    
    // Constructor of managePCML.
    public managePCML() {
        this.gui = new objTester(this);
    }
    
}