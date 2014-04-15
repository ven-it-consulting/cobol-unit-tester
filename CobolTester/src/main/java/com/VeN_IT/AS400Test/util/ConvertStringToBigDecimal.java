package com.VeN_IT.AS400Test.util;

import java.math.BigDecimal;

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

public class ConvertStringToBigDecimal {
	
	// Converting String to BigDecimal 
	public static BigDecimal convert(String str) {
		if (isNumeric(str)) {
			return new BigDecimal(str);
		} else {
			return new BigDecimal(0);
		}
		
	}
	
	
	// Checking if String contains numeric value;
	private static boolean isNumeric(String str) {
		
		try {
			Double.parseDouble(str);  
		}
		catch (NumberFormatException e) {
			return false;  
		}
		return true;  
	}
}
