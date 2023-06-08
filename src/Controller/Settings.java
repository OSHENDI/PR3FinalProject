/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author hp
 */
public class Settings {
    private boolean isDarkModeEnabled;

    private static final Settings instance = new Settings();

    private Settings() {
       
    }

    public static Settings getInstance() {
        return instance;
    }

    public boolean isDarkModeEnabled() {
        return isDarkModeEnabled;
    }

    public void setDarkModeEnabled(boolean isDarkModeEnabled) {
        this.isDarkModeEnabled = isDarkModeEnabled;
    }
}
