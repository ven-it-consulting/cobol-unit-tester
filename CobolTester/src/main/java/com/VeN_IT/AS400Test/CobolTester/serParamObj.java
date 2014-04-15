package com.VeN_IT.AS400Test.CobolTester;

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

@SuppressWarnings("serial")
public class serParamObj implements java.io.Serializable{
    private static long serialVersionUID = 1L;
    
    public String module;                                                 // Given module
    public int methode;                                                   // Selected methode
    public String[] pcmlMethode = new String[170];                        // Available methodes
    public String[][] refValues = new String[250][2];
    private boolean commitSwitch;
    private boolean disconnectSwitch;
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    
    /**
     * @return the pcmlMethode
     */
    public String[] getPcmlMethode() {
        return pcmlMethode;
    }

    /**
     * @param aPcmlMethode the pcmlMethode to set
     */
    public void setPcmlMethode(String[] aPcmlMethode) {
        pcmlMethode = aPcmlMethode;
    }

    /**
     * @return the module
     */
    public String getModule() {
        return module;
    }

    /**
     * @param aModule the module to set
     */
    public void setModule(String aModule) {
        module = aModule;
    }

    /**
     * @return the methode
     */
    public int getMethode() {
        return methode;
    }

    /**
     * @param aMethode the methode to set
     */
    public void setMethode(int aMethode) {
        methode = aMethode;
    }

    /**
     * @return the refValues
     */
    public String[][] getRefValues() {
        return refValues;
    }

    /**
     * @param aRefValues the refValues to set
     */
    public void setRefValues(String[][] aRefValues) {
        refValues = aRefValues;
    }

    /**
     * @return the commitSwitch
     */
    public boolean isCommitSwitch() {
        return commitSwitch;
    }

    /**
     * @param commitSwitch the commitSwitch to set
     */
    public void setCommitSwitch(boolean commitSwitch) {
        this.commitSwitch = commitSwitch;
    }

    /**
     * @return the disconnectSwitch
     */
    public boolean isDisconnectSwitch() {
        return disconnectSwitch;
    }

    /**
     * @param disconnectSwitch the disconnectSwitch to set
     */
    public void setDisconnectSwitch(boolean disconnectSwitch) {
        this.disconnectSwitch = disconnectSwitch;
    }
    
}
