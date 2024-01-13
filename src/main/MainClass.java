package main;

import com.formdev.flatlaf.IntelliJTheme;
import org.opencv.core.Core;
import assets.*;

@Author("Josuan Leonardo Hulom")
public class MainClass {
    public static void main(String args[]) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        IntelliJTheme.setup(MainApp.class.getResourceAsStream("/theme_eclipse.theme.json"));
        java.awt.EventQueue.invokeLater(() -> {
            new MainApp().setVisible(true);
        });
    }   
}
