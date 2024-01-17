package assets;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;

@Author("Josuan Leonardo Hulom")
public class Utilities {
    public static boolean validateUsername(String _username){
        final String pattern = "^[a-zA-Z]+@[0-9]+$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(_username);
        return matcher.matches();
    }    
    
    public static boolean validatePassword(String _password, Component parentComponent) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(_password);
        boolean isPhoneNumber = _password.matches("\\d{10}"); 
        
        if (isPhoneNumber) {
            JOptionPane.showMessageDialog(parentComponent, "You've used a phone number as a password. Please choose a stronger password.","",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!matcher.matches()) {
            if (!_password.matches(".*[0-9].*")) {
                JOptionPane.showMessageDialog(parentComponent, "Password should contain at least one digit.","",JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (!_password.matches(".*[a-z].*")) {
                JOptionPane.showMessageDialog(parentComponent, "Password should contain at least one lowercase letter.","",JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (!_password.matches(".*[A-Z].*")) {
                JOptionPane.showMessageDialog(parentComponent, "Password should contain at least one uppercase letter.","",JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (!_password.matches(".*[@#$%^&+=].*")) {
                JOptionPane.showMessageDialog(parentComponent, "Password should contain at least one special character (@#$%^&+=).","",JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (_password.length() < 8) {
                JOptionPane.showMessageDialog(parentComponent, "Password should be at least 8 characters long.","",JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (_password.contains(" ")) {
                JOptionPane.showMessageDialog(parentComponent, "Password should not contain spaces.","",JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return false;
        }
        return true;
    }
    
    public static boolean containsNumbers(String str) {
        Pattern pattern = Pattern.compile(".*\\d.*");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    
    public static String capitalizeEachWord(String fullName) {
        String[] words = fullName.split("\\s+"); 

        StringBuilder capitalizedFullName = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                String capitalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
                capitalizedFullName.append(capitalizedWord).append(" ");
            }
        }
        return capitalizedFullName.toString().trim(); 
    }
    
    public static String formatNumber(double number) {
        String[] suffix = new String[]{"", "k", "M", "B", "T"};
        int index = 0;
        while (number >= 1000 && index < suffix.length - 1) {
            number /= 1000;
            index++;
        }
        return String.format("%.1f%s", number, suffix[index]);
    }
    public static String formatNumber(int number) {
        String[] suffix = new String[]{"", "k", "M", "B", "T"};
        int index = 0;
        while (number >= 1000 && index < suffix.length - 1) {
            number /= 1000;
            index++;
        }
        return String.format("%d%s", number, suffix[index]);        
    }
    
    public class LandscapePrintable implements Printable {
        private final JTable table;

        public LandscapePrintable(JTable table) {
            this.table = table;
        }

        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            g2d.rotate(Math.toRadians(90));

            double scaleX = pageFormat.getImageableWidth() / table.getWidth();
            double scaleY = pageFormat.getImageableHeight() / table.getHeight();
            double scale = Math.min(scaleX, scaleY);

            g2d.scale(scale, scale);

            table.print(g2d);
            
            return Printable.PAGE_EXISTS;
        }
    }
}
