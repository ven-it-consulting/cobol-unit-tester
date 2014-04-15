package com.VeN_IT.AS400Test.CobolTester;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.VeN_IT.AS400Test.interfaces.readPCMLInt;
import com.VeN_IT.AS400Test.util.ManageProperty;
import com.VeN_IT.AS400Test.util.reuse;
import com.ibm.as400.access.IFSFileInputStream;

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

public class readPCML implements readPCMLInt {
    
	
    readPCML(IFSFileInputStream filePath){
        parsePCML(filePath);
    }
    
    
    public void parsePCML(IFSFileInputStream filePath) {
        
    try {
 
	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();
                        
        DefaultHandler handler = new DefaultHandler() {
        int i = 0, j = 0;
        int o = 0;
        boolean guiParam = false, outParam = false, fixParam = false;
        
        public void startElement (String uri, String localName,
            String qName, Attributes attributes) throws SAXException{
            String methodeName;
            String parName;
            String parType;
            String parLeng;
            String parPrec;
            
            /**
             * Reading data from PCML into pcmlData
             * Dimensions:
             *  - First dimension is METHODE.
             *  - Second dimension is NAME.
             *  - Third dimensions are type, length and precision attributes.
             */
            
            // First level is struct. Here we separete input and output section.
            if (qName.equalsIgnoreCase("struct")) {
                parName = attributes.getValue("name");
                
                if (parName.equalsIgnoreCase(ManageProperty.getValue("pcmlStrRef"))) {
                    // This section includes input-output GUI reference.
                    guiParam = true;
                    outParam = false;
                    fixParam = false;
                } else if (parName.equalsIgnoreCase(ManageProperty.getValue("pcmlStrPar"))) {
                    // This section includes output paramaters.
                    outParam = true;
                    guiParam = false;
                    fixParam = false;
                    countPcmlDataOut[i - 1] = 1;
                } else if(parName.equalsIgnoreCase(ManageProperty.getValue("pcmlStrOut"))) {
                    // This section includes output paramaters.
                    outParam = true;
                    guiParam = false;
                    fixParam = false;
                    countPcmlDataOut[i - 1] = Integer.parseInt(attributes.getValue("count"));
                } else if (parName.equalsIgnoreCase(ManageProperty.getValue("pcmlStrRes")) ||
                		parName.equalsIgnoreCase(ManageProperty.getValue("pcmlStrErr"))) {
                    // This section is out of scope because it's fix.
                    guiParam = false;
                    outParam = false;
                    fixParam = true;
                }
                
            }
            
            // Gaining methode names and attributes.
            if (qName.equalsIgnoreCase("data")) {
                parName = attributes.getValue("name");
                parType = attributes.getValue("type");
                parLeng = attributes.getValue("length");
                parPrec = attributes.getValue("precision");
                
                if (parName.equalsIgnoreCase(ManageProperty.getValue("pcmlStrMet"))) {
                    String check = attributes.getValue("usage");
                    if (check.equalsIgnoreCase("input")) {
                        methodeName = attributes.getValue("init");
                        pcmlMethode[i] = methodeName;

                        i++;
                        j = 0;
                        o = 0;
                        if (i > 169) {
                            i = 169;
                            reuse.warningBox("Reached the parameter (methode) limit of 170 !", "readPCML");
                        }
                    } else {
                        // Hacking: There is a modul (OV121A) which has a field name "METHODE".
                        j++;
                        if (j > 249) {
                            j = 249;
                            reuse.warningBox("Reached the parameter (in-out field) limit of 250 !", "readPCML (in-out reference)");
                        }

                        pcmlDataIn[i-1][j][0] = parName;
                        pcmlDataIn[i-1][j][1] = parType;
                        pcmlDataIn[i-1][j][2] = parLeng;
                        pcmlDataIn[i-1][j][3] = parPrec;
                    }
                    
                  // Gaining reference names and attributes.  
                } else if (!parName.equalsIgnoreCase(ManageProperty.getValue("pcmlStrMet")) && !parName.equalsIgnoreCase("DUMMY") &&
                        guiParam && !fixParam) {
                    j++;
                    if (j > 249) {
                        j = 249;
                        reuse.warningBox("Reached the parameter (in-out field) limit of 250 !", "readPCML (in-out reference)");
                    }
                    
                    pcmlDataIn[i-1][j][0] = parName;
                    pcmlDataIn[i-1][j][1] = parType;
                    pcmlDataIn[i-1][j][2] = parLeng;
                    pcmlDataIn[i-1][j][3] = parPrec;
                    
                  // Gaining output parameter names.
                } else if (!parName.equalsIgnoreCase(ManageProperty.getValue("pcmlStrMet")) && !parName.equalsIgnoreCase("DUMMY") &&
                        outParam && !fixParam) {
                    o++;
                    if (o > 249) {
                        o = 249;
                        reuse.warningBox("Reached the parameter (out field) limit of 250 !", "readPCML (output param)");
                    }
                    
                    pcmlDataOut[i-1][o][0] = parName;
                    pcmlDataOut[i-1][o][1] = parType;
                    pcmlDataOut[i-1][o][2] = parLeng;
                    pcmlDataOut[i-1][o][3] = parPrec;
                } 
                
            }
        }
     };
     
     
     saxParser.parse(filePath, handler);
     
     
     } catch (Exception e) {
         reuse.errorBox(e.getLocalizedMessage(), "readPCML");
     }
    
    }
    
    // Returns all methodes found in PCML file.
    public String[] getPCMLMethodes() {
        return pcmlMethode; 
    }
    
    
    // Return all reference information found in PCML file.
    public String[][][] getPCMLIn() {
        return pcmlDataIn;
    }
    
    
    // Return all output parameters found in PCML file.
    public String[][][] getPCMLOut() {
        return pcmlDataOut;
    }
    
    
    // Returns counter of array pcmlDataOut index.
    public int[] getPCMLOutCounter() {
        return countPcmlDataOut;
    }
        
}