package assets;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

@Author("Josuan Leonardo Hulom")
public interface FileManagement {
    public static final String[] PROJECT_PACKAGES = {"src/images","src/item_images"};
    static final String[] IMAGE_EXTENSIONS = {"png", "jpg", "jpeg"};
    
    public default File getFile_Image(String get_extensions){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(get_extensions+" files", get_extensions);
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            return null;
        }        
    }
}
