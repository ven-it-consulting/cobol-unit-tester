package com.VeN_IT.AS400Test.util;

import java.util.ArrayList;
import java.util.List;

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

public class ReadEnvList {
	
	// Reading environment folders out of properties file. 
	public static List<String> ReadProperty() {
		String[] keys = {"devStr01", "devStr02", "devStr03", "devStr04", "devStr05"};
		List<String> foundFolder = new ArrayList<String>();
		String value;
		
		for (String str:keys) {
			value = ManageProperty.getValue(str);
			if (value == null || value.equals(str)) value = "";
			if (!value.equals("")) {
				foundFolder.add(value);
			}
		}
		return foundFolder;
	}
	
}
