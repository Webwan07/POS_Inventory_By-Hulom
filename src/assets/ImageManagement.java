package assets;

import customComponents.ImageAvatar;
import customComponents.PictureBox;
import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

@Author("Josuan Leonardo Hulom")
public interface ImageManagement {
    public default void setImageToAvatar(ImageAvatar imgAvatar, String imageName){
        if(imgAvatar != null && !Utilities.isEmptyOrSpacesOnly(imageName)){
            ImageIcon img = new ImageIcon(FileManagement.PROJECT_PACKAGES[0]+"/"+imageName);
            imgAvatar.setIcon(img);
            imgAvatar.repaint();
        }
    }
    
    public default void setImageToPictureBox(PictureBox imgAvatar, ImageIcon img){
        if(imgAvatar != null && img != null){
            imgAvatar.setImage(img);
            imgAvatar.repaint();
        }
    }
    
    public default void insert_Image(File new_image_file, String new_name,Component parent_component,int idx) {
        if (new_image_file != null && parent_component != null && idx >= 0) {
            String imagesDirectoryPath = FileManagement.PROJECT_PACKAGES[idx] + "/";
            File imagesDirectory = new File(imagesDirectoryPath);
            if (!imagesDirectory.exists()) {
                imagesDirectory.mkdirs();
            }
            try {
                String originalFileName = new_image_file.getName();
                String fileExtension = "";
                int lastDotIndex = originalFileName.lastIndexOf('.');
                if (lastDotIndex > 0) {
                    fileExtension = originalFileName.substring(lastDotIndex);
                }
                Path destination = new File(imagesDirectoryPath + new_name + fileExtension).toPath();
                Files.copy(new_image_file.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
                Files.move(destination, destination.resolveSibling(new_name + fileExtension), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(parent_component, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public default void deleteImage(String imageName) {
        String imagesFolderPath = FileManagement.PROJECT_PACKAGES[0];
        
        Path imagePath = Paths.get(imagesFolderPath, imageName);
        
        try {
            if (Files.exists(imagePath)) {
                Files.delete(imagePath);
            }
        } catch (IOException e) {
        }
    }
    
    public default String checkDuplicateImageName(String imageName) {
        File directory = new File("src/images");
        File[] files = directory.listFiles();

        if (files != null) {
            int count = 1;
            String imageNameWithoutExtension = imageName.substring(0, imageName.lastIndexOf('.'));
            String newImageName = imageNameWithoutExtension;

            for (File file : files) {
                if (file.isFile() && file.getName().startsWith(imageNameWithoutExtension)) {
                    newImageName = imageNameWithoutExtension + "(" + count + ")";
                    count++;
                }
            }

            return newImageName;
        }

        return imageName;
    }
    
    public default void openImageDesktop(Icon getImage){
        String imagePath = getImage.toString();
        
        try {
            File imageFile = new File(imagePath);
            if (imageFile.exists() && (imageFile.isFile() || imageFile.canRead())) {
                Desktop.getDesktop().open(imageFile);
            } 
        } catch (IOException ex) {
            ex.printStackTrace();
        }   
    }
}
