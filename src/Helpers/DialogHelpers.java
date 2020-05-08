/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author tranq
 */
public class DialogHelpers {

    public static int confirm(Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(parent, message, "Hệ thống quản lí quán cafe", JOptionPane.YES_NO_OPTION);
        return result;
    }
}
