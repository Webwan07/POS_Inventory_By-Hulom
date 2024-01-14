package assets;

import customComponents.ImageAvatar;
import javax.swing.ImageIcon;

@Author("Josuan Leonardo Hulom")
public interface ImageManagement {
    default void setImageToAvatar(ImageAvatar imgAvatar, String imageName){
        ImageIcon img = new ImageIcon("src/images/"+imageName);
        imgAvatar.setIcon(img);
        imgAvatar.repaint();
    }
}
