package com.VeN_IT.AS400Test.interfaces;

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

// You have to implement this interface when you have a different PCML structure.
// Please take a look at com.VeN_IT.AS400Test.CobolTester.readPCML.java!

public interface readPCMLInt {
	static final String[] pcmlMethode = new String[170];				// Store PCML methodes here.
	static final String[][][] pcmlDataIn = new String[170][250][4];		// Store PCML reference variables here.
	static final String[][][] pcmlDataOut = new String[170][250][4];	// Store PCML output variables here.
	static int countPcmlDataOut[] = new int[170];
	
	
	public abstract void parsePCML(IFSFileInputStream filePath);		// Parsing PCML.
	public abstract String[] getPCMLMethodes();							// Filling PCML methodes into pcmlMethode.
	public abstract String[][][] getPCMLIn();
	public abstract String[][][] getPCMLOut();
	public abstract int[] getPCMLOutCounter();
	
}
