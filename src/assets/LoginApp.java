package assets;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import database.AppManagement;
import database.UserManagement;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import main.MainApp;

@Author("Josuan Leonardo Hulom")
public class LoginApp extends javax.swing.JFrame implements Runnable,ThreadFactory{
    private final UserManagement userManagement = new UserManagement(this);

    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);    
    
    public LoginApp() {
        Image appIcon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo/loginPage.png"));
        this.setIconImage(appIcon);
        
        initComponents();
        Utilities.backLabelActions(jLabel2, Helper.colors[6],Helper.fontColors[0],Helper.colors[3],Helper.fontColors[0]);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layere1 = new javax.swing.JLayeredPane();
        panel1 = new javax.swing.JPanel();
        panelRound1 = new customComponents.PanelRound();
        usernameTextField = new customComponents.TextField();
        passwordField = new customComponents.PasswordField();
        loginBtn = new customComponents.ButtonRound();
        panelRound2 = new customComponents.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panel2 = new javax.swing.JPanel();
        camscreen = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        layere1.setLayout(new java.awt.CardLayout());

        panelRound1.setBackground(new java.awt.Color(218, 136, 136));
        panelRound1.setRoundBottomLeft(25);
        panelRound1.setRoundTopLeft(25);

        usernameTextField.setBackground(new java.awt.Color(218, 136, 136));
        usernameTextField.setForeground(new java.awt.Color(255, 255, 255));
        usernameTextField.setCaretColor(new java.awt.Color(176, 62, 62));
        usernameTextField.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        usernameTextField.setLabelText("Username");
        usernameTextField.setLineColor(new java.awt.Color(255, 255, 255));
        usernameTextField.setSelectionColor(new java.awt.Color(79, 70, 229));
        usernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextFieldActionPerformed(evt);
            }
        });
        usernameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usernameTextFieldKeyReleased(evt);
            }
        });

        passwordField.setBackground(new java.awt.Color(218, 136, 136));
        passwordField.setForeground(new java.awt.Color(255, 255, 255));
        passwordField.setCaretColor(new java.awt.Color(176, 62, 62));
        passwordField.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        passwordField.setLabelText("Password");
        passwordField.setLineColor(new java.awt.Color(255, 255, 255));
        passwordField.setSelectionColor(new java.awt.Color(79, 70, 229));
        passwordField.setShowAndHide(true);
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });
        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passwordFieldKeyReleased(evt);
            }
        });

        loginBtn.setBackground(new java.awt.Color(176, 62, 62));
        loginBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/login.png"))); // NOI18N
        loginBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginBtnMouseEntered(evt);
            }
        });
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(0, 64, Short.MAX_VALUE)
                        .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usernameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        panelRound2.setBackground(new java.awt.Color(176, 62, 62));
        panelRound2.setRoundBottomRight(25);
        panelRound2.setRoundTopRight(25);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LOG IN");

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/qr-code.png"))); // NOI18N
        jLabel2.setText("Log in using QR code");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        layere1.add(panel1, "card2");

        camscreen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back2.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(camscreen, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(camscreen, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        layere1.add(panel2, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layere1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layere1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void usernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTextFieldActionPerformed
        String get_username = usernameTextField.getText();
        char[] get_p = passwordField.getPassword();
        String get_password = new String(get_p);   
        
        try {
            loginMethod(get_username,get_password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_usernameTextFieldActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        String get_username = usernameTextField.getText();
        char[] get_p = passwordField.getPassword();
        String get_password = new String(get_p);  
        
        try {
            loginMethod(get_username,get_password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        String get_username = usernameTextField.getText();
        char[] get_p = passwordField.getPassword();
        String get_password = new String(get_p); 
        
        try {
            loginMethod(get_username,get_password);
        } catch (SQLException ex) {
            Logger.getLogger(LoginApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_loginBtnActionPerformed

    private Point currentLocation = new Point(112, 295);
    private void loginBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginBtnMouseEntered
        String get_username = usernameTextField.getText();
        char[] get_p = passwordField.getPassword();
        String get_password = new String(get_p);         
        
        boolean emptyUsername = get_username.trim().isEmpty();
        boolean emptyPassword = get_password.trim().isEmpty();
        Point newLocation;
    
        if (emptyUsername || emptyPassword) {
            if (currentLocation.equals(new Point(112, 343))) {
                newLocation = new Point(112, 295);
            } else {
                newLocation = new Point(112, 343);
            }
            loginBtn.setLocation(newLocation);
            currentLocation = newLocation;
        }
    }//GEN-LAST:event_loginBtnMouseEntered

    private void usernameTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameTextFieldKeyReleased
        if(!usernameTextField.getText().trim().isEmpty()){
            loginBtn.setLocation(new Point(112, 295));
        }
    }//GEN-LAST:event_usernameTextFieldKeyReleased

    private void passwordFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyReleased
        char[] get_p = passwordField.getPassword();
        String get_password = new String(get_p); 
        
        if(!get_password.trim().isEmpty()){
            loginBtn.setLocation(new Point(112, 295));
        }
    }//GEN-LAST:event_passwordFieldKeyReleased
  
    private volatile boolean scanning = true;
    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        Utilities.switchPanel(layere1, panel2);
        initWebcam();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        Utilities.switchPanel(layere1, panel1);
        webcam.close();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void loginMethod(String username, String password) throws SQLException{
        short result = userManagement.checkUserCredentials(username, password);

        switch (result) {
            case 1:
                String current_user = userManagement.getUserId(username, password);
                String get_fname = userManagement.getFName(current_user);
                JOptionPane.showMessageDialog(this, "Welcome "+get_fname,"Login successful",JOptionPane.INFORMATION_MESSAGE);
                AppManagement.setCurrentUser(current_user, this);         
                
                MainApp newApp = new MainApp();    
                newApp.setVisible(true);
                this.dispose();
                break;

            case 2:
                JOptionPane.showMessageDialog(this, "Incorrect password!");
                break;
            case 3:
                JOptionPane.showMessageDialog(this, "Incorrect username!");
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid username and password!");
                break;
        }       
    }
    
    private boolean loginMethod(String useid) throws SQLException{
        short result = userManagement.checkUserCredentials(useid);

        switch (result) {
            case 1:
                String current_id = useid;
                String get_fname = userManagement.getFName(current_id);
                JOptionPane.showMessageDialog(this, "Welcome "+get_fname,"Login successful",JOptionPane.INFORMATION_MESSAGE);
                AppManagement.setCurrentUser(current_id, this);           
                
                MainApp newApp = new MainApp();    
                newApp.setVisible(true);
                webcam.close();
                this.dispose();
                return true;
            case 0:
                JOptionPane.showMessageDialog(this, "User ID is not found");
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid User ID");
                break;
        }     
        return false;
    }
    
    private void initWebcam(){
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        camscreen.add(panel,new org.netbeans.lib.awtextra.AbsoluteConstraints( 0,0,643,464));
        executor.execute(this);
    }
    
    @Override
    public void run() {
        do {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {}

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                image = webcam.getImage();
            }

            if (image != null) {
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                try {
                    result = new MultiFormatReader().decode(bitmap);
                } catch (NotFoundException ex) {}

                if (result != null) {
                    String get_id = result.getText();
                    try {
                        if(loginMethod(get_id)){
                            break;
                        }
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, e.getMessage(), "Error Code: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            
            if (!scanning) {
                break;
            }
        } while (true);
    }

    
    @Override
    public Thread newThread(Runnable r){
        Thread t = new Thread(r,"My Thread");
        t.setDaemon(true);
        return t;
    }
                          

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel camscreen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane layere1;
    private customComponents.ButtonRound loginBtn;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private customComponents.PanelRound panelRound1;
    private customComponents.PanelRound panelRound2;
    private customComponents.PasswordField passwordField;
    private customComponents.TextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
