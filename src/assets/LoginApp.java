package assets;

import com.formdev.flatlaf.IntelliJTheme;
import database.AppManagement;
import database.DbConnection;
import database.UserManagement;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import main.MainApp;

@Author("Josuan Leonardo Hulom")
public class LoginApp extends javax.swing.JFrame {
    private final DbConnection dbConnection = DbConnection.getInstance();
    private final UserManagement userManagement = new UserManagement(this);
    public LoginApp() {
        Image appIcon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/loginPage.png"));
        this.setIconImage(appIcon);
        
        initComponents();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        layere1.setLayout(new java.awt.CardLayout());

        panelRound1.setBackground(new java.awt.Color(165, 180, 252));
        panelRound1.setRoundBottomLeft(25);
        panelRound1.setRoundTopLeft(25);

        usernameTextField.setBackground(new java.awt.Color(165, 180, 252));
        usernameTextField.setForeground(new java.awt.Color(255, 255, 255));
        usernameTextField.setCaretColor(new java.awt.Color(79, 70, 229));
        usernameTextField.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        usernameTextField.setLabelText("Username");
        usernameTextField.setLineColor(new java.awt.Color(255, 255, 255));
        usernameTextField.setSelectionColor(new java.awt.Color(79, 70, 229));
        usernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextFieldActionPerformed(evt);
            }
        });

        passwordField.setBackground(new java.awt.Color(165, 180, 252));
        passwordField.setForeground(new java.awt.Color(255, 255, 255));
        passwordField.setCaretColor(new java.awt.Color(79, 70, 229));
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

        loginBtn.setBackground(new java.awt.Color(79, 70, 229));
        loginBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/login.png"))); // NOI18N
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
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addContainerGap(112, Short.MAX_VALUE)
                .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112))
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
                .addContainerGap(54, Short.MAX_VALUE))
        );

        panelRound2.setBackground(new java.awt.Color(79, 70, 229));
        panelRound2.setRoundBottomRight(25);
        panelRound2.setRoundTopRight(25);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LOG IN");

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap(48, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        layere1.add(panel1, "card2");

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

    private void loginMethod(String username, String password) throws SQLException{
        short result = userManagement.checkUserCredentials(username, password);

        switch (result) {
            case 1:
                JOptionPane.showMessageDialog(this, "Login successful!");
                String current_user = userManagement.getUserId(username, password);
                AppManagement.setCurrentUser(current_user, this);
                System.out.println("Current user: "+current_user);            
                
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
    
    public static void main(String args[]) {
        IntelliJTheme.setup(LoginApp.class.getResourceAsStream("/theme_eclipse.theme.json"));
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane layere1;
    private customComponents.ButtonRound loginBtn;
    private javax.swing.JPanel panel1;
    private customComponents.PanelRound panelRound1;
    private customComponents.PanelRound panelRound2;
    private customComponents.PasswordField passwordField;
    private customComponents.TextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
