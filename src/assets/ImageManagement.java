package assets;

import customComponents.ImageAvatar;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

@Author("Josuan Leonardo Hulom")
public interface ImageManagement {
    default void setImageToAvatar(ImageAvatar imgAvatar, String imageName){
        ImageIcon img = new ImageIcon("src/images/"+imageName);
        imgAvatar.setIcon(img);
        imgAvatar.repaint();
    }
    
    default File get_ImageFile(Component p_c) {
        JFileChooser fileChooser = new JFileChooser();
        String picturesDir = System.getProperty("user.home");
        fileChooser.setCurrentDirectory(new File(picturesDir)); 
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg"));
        int result = fileChooser.showOpenDialog(p_c);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }
    
    default void insertImage(File newImageFile, String newImageName) {
        File sourceDir = new File("src/images");
        File destinationFile = new File(sourceDir, newImageName);

        try {
            Files.copy(newImageFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Image inserted successfully to src/images as " + newImageName);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
