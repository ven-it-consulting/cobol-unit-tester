package com.VeN_IT.AS400Test.util;

import java.util.Arrays;
import javax.swing.JOptionPane;

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

public class reuse {
    
    // Simple info-box.
    public static void infoBox(String infoMessage, String location)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "Üzenet (" + location + ")", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    // Simple error-box.
    public static void errorBox(String errorMessage, String location)
    {
        JOptionPane.showMessageDialog(null, errorMessage, "Hiba (" + location + ")", JOptionPane.ERROR_MESSAGE);
    }
    
    
    // Multi error-box.
    public static void errorsBox(String[] errorsMessage, String location) {
        String errorMessage = Arrays.toString(errorsMessage);
        JOptionPane.showMessageDialog(null, errorMessage, "Hiba (" + location + ")", JOptionPane.ERROR_MESSAGE);
    }
    
    
    // Simple warning-box.
    public static void warningBox(String warningMessage, String location)
    {
        JOptionPane.showMessageDialog(null, warningMessage, "Figyelem: (" + location + ")", JOptionPane.WARNING_MESSAGE);
    }
}