package com.VeN_IT.AS400Test.AS400;

import java.io.IOException;

import com.VeN_IT.AS400Test.util.reuse;
import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400SecurityException;

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

public class AS400Connect {
	
	// Building connection to AS/400;
	public static AS400 buildConnection(AS400 connection, String server, String userName, String userPwd) throws AS400SecurityException, IOException {		
		
		if (connection == null) {
			connection = new AS400(server, userName, userPwd);
		}
		
		try {
			connection.connectService(0);
		} catch (IOException e) {
			reuse.errorBox(e.getLocalizedMessage(), "AS400Connect.buildConnection");
		} catch (AS400SecurityException e) {
			reuse.errorBox(e.getLocalizedMessage(), "AS400Connect.buildConnection");
		}
		
		if (connection.isConnected()) {
			  return connection;
		} else {
			  return null;
		}
		
	}
	
	
	// Closing connection on AS400
	public static void closeConnection(AS400 connection) {
		if (connection != null) {
			if (connection.isConnected()) {
				connection.disconnectAllServices();
				// disconnectService(0);
			}
		}
	}
	
}