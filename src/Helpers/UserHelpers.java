/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import data.UserAccount;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author tranq
 */
public class UserHelpers {

    public static Image Img;

    static {
        String file = "";
        Img = new ImageIcon(UserHelpers.class.getResource(file)).getImage();
    }

    public static boolean saveImage(File file) {
        File dir = new File("logos");
        File newFile = new File(dir, file.getName());

        Path source = Paths.get(file.getAbsolutePath());
        Path destination = Paths.get(newFile.getAbsolutePath());
        try {
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public static ImageIcon readImages(String fileName) {
        File path = new File("logos", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }

    public static UserAccount User = null;

    public static boolean authenticated() {
        return UserHelpers.User != null;
    }
}
