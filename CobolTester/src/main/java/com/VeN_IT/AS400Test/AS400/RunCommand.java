package com.VeN_IT.AS400Test.AS400;

import java.beans.PropertyVetoException;
import java.io.IOException;

import com.VeN_IT.AS400Test.util.reuse;
import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.CommandCall;
import com.ibm.as400.access.ErrorCompletingRequestException;

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

public class RunCommand {
	
	// Calls COMMIT on the server.
	public static boolean callCommit(AS400 as400) {
		CommandCall command = new CommandCall(as400);
        boolean success = false;
        
		try {
			success = command.run("COMMIT");
		} catch (AS400SecurityException e) {
			reuse.errorBox(e.getLocalizedMessage(), "RunCommand.callCommit");
		} catch (ErrorCompletingRequestException e) {
			reuse.errorBox(e.getLocalizedMessage(), "RunCommand.callCommit");
		} catch (IOException e) {
			reuse.errorBox(e.getLocalizedMessage(), "RunCommand.callCommit");
		} catch (InterruptedException e) {
			reuse.errorBox(e.getLocalizedMessage(), "RunCommand.callCommit");
		} catch (PropertyVetoException e) {
			reuse.errorBox(e.getLocalizedMessage(), "RunCommand.callCommit");
		}
		return success;
	}
	
	
	// Calls ROLLBACK on the server.
	public static boolean callRollback(AS400 as400) {
		CommandCall command = new CommandCall(as400);
        boolean success = false;
        
		try {
			success = command.run("ROLLBACK");
		} catch (AS400SecurityException e) {
			reuse.errorBox(e.getLocalizedMessage(), "RunCommand.callRollback");
		} catch (ErrorCompletingRequestException e) {
			reuse.errorBox(e.getLocalizedMessage(), "RunCommand.callRollback");
		} catch (IOException e) {
			reuse.errorBox(e.getLocalizedMessage(), "RunCommand.callRollback");
		} catch (InterruptedException e) {
			reuse.errorBox(e.getLocalizedMessage(), "RunCommand.callRollback");
		} catch (PropertyVetoException e) {
			reuse.errorBox(e.getLocalizedMessage(), "RunCommand.callRollback");
		}
		return success;
	}
	
	
	// Calls given command on the server.
	public static boolean callCommand(AS400 as400, String cmd) {
		CommandCall command = new CommandCall(as400);
        boolean success = false;
        
		try {
			success = command.run(cmd);
		} catch (AS400SecurityException e) {
			reuse.errorBox(e.getLocalizedMessage(), "RunCommand.callCommand");
		} catch (ErrorCompletingRequestException e) {
			reuse.errorBox(e.getLocalizedMessage(), "RunCommand.callCommand");
		} catch (IOException e) {
			reuse.errorBox(e.getLocalizedMessage(), "RunCommand.callCommand");
		} catch (InterruptedException e) {
			reuse.errorBox(e.getLocalizedMessage(), "RunCommand.callCommand");
		} catch (PropertyVetoException e) {
			reuse.errorBox(e.getLocalizedMessage(), "RunCommand.callCommand");
		}
		return success;
	}
	
}
