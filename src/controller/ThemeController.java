/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author rohanayan
 */
public interface ThemeController {
    void setTheme(String c1, String c2);
    void setTheme(String c1, String c2, boolean b);
    void setTheme(boolean labelIsBlack);
}
