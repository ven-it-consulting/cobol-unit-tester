package com.VeN_IT.AS400Test.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

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

public class ManageProperty {
	
	// Setting of default property values.
	public static void setDefaultValue() {
		Properties prop = new Properties();
		OutputStream output = null;
		try {
			output = new FileOutputStream("config.properties");
			
			// set the properties value
			prop.setProperty("serverIP", "111.111.11.13");
			prop.setProperty("pcmlPathPrefix", "/xxx/xxx/xxx/xxx/");
			prop.setProperty("pcmlPathPostfix", "/pcml/");
			prop.setProperty("libl01", "ValDDB");
			prop.setProperty("libl02", "ValDPGM");
			prop.setProperty("libl03", "ValIDB");
			prop.setProperty("libl04", "ValIPGM");
			prop.setProperty("libl05", "");
			prop.setProperty("libl06", "");
			prop.setProperty("libl07", "");
			prop.setProperty("libl08", "");
			prop.setProperty("libl09", "");
			prop.setProperty("libl10", "");
			prop.setProperty("devStr01", "");
			prop.setProperty("devStr02", "");
			prop.setProperty("devStr03", "");
			prop.setProperty("devStr04", "");
			prop.setProperty("devStr05", "");
			
			// save properties to project root folder
			prop.store(output, null);
			
		} catch (IOException io) {
			reuse.errorBox(io.getLocalizedMessage(), "manageProperty.setValue");
		} finally {
			if (output != null) {
				try {
					output.close();
					} catch (IOException e) {
						reuse.errorBox(e.getLocalizedMessage(), "manageProperty.setValue");
					}
			}
		}
	}
	
	
	// Get given value from properties file.
	public static String getValue(String key) {
		String retValue = "";
		Properties prop = new Properties();
    	InputStream input = null;
    	
    	try {
    		input = new FileInputStream("config.properties");
    		
    		// load a properties file
    		prop.load(input);
    		
    		// get the property value.
    		retValue = prop.getProperty(key);
    		
    	} catch (IOException ex) {
    		reuse.infoBox(ex.getLocalizedMessage(), "manageProperty.getValue");
    	} finally {
    		if (input != null) {
    			try {
    				input.close();
    			} catch (IOException e) {
    				reuse.infoBox(e.getLocalizedMessage(), "manageProperty.getValue");
				}
			}
		}
    	return retValue;
	}
	
}