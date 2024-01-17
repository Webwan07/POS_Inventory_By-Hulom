package main;
import assets.*;
import database.AppManagement;
import database.DbConnection;
import database.PurchaseManagement;
import database.UserManagement;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;

@Author("Josuan Leonardo Hulom")
public final class MainApp extends javax.swing.JFrame implements AppInitializers, ImageManagement{
    private final UserManagement userManagement = new UserManagement(this);
    private final PurchaseManagement purchaseManagement = new PurchaseManagement(this);
    
    public MainApp() {
        Image appIcon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo/appLogo.png"));
        this.setIconImage(appIcon);
        
        initComponents();
        initialize();
        HoverBtn(true);
        backLabelActions(backLabel, new String[]{"back2.png","back1.png","back3.png"});
        
        if (this.getExtendedState() == this.MAXIMIZED_BOTH) {
            this.setExtendedState(this.NORMAL);
        } else {
            this.setExtendedState(this.MAXIMIZED_BOTH);
        }
    }
    
    @Override
    public void init_table() {
        try {
            DbConnection.getInstance().tableData(usersTable, userManagement.table);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void init_user() {
        try{
            String get_id = AppManagement.getCurrentUser(this);
            String get_image = userManagement.getImagePath(get_id);
            String get_fname = userManagement.getFName(get_id);
            String get_lname = userManagement.getLName(get_id);
            String get_fullname = get_fname + " " + get_lname;
            String get_username = userManagement.getUName(get_id);
            String get_gender = userManagement.getGender(get_id);
            String get_usertype = userManagement.getUserType(get_id);
            
            if(get_usertype.equals(userManagement.listOfUserType[0])){
                switchPanel(menuLayere,adminMenu);
            }else if(get_usertype.equals(userManagement.listOfUserType[1])){
                switchPanel(menuLayere,sellerMenu);
                adminBtn.setVisible(false);
                adminBtn.setEnabled(false);
            }else{
                
            }
            
            if(get_gender.equals(Helper.listOfGender[0])){
                genderLabel.setIcon(new ImageIcon("src/icons/male.png"));
            }else if(get_gender.equals(Helper.listOfGender[1])){
                genderLabel.setIcon(new ImageIcon("src/icons/female.png"));
            }else{
                genderLabel.setIcon(new ImageIcon("src/icons/nogender.png"));
            }         
            
            int get_soldItem = purchaseManagement.sellerTotalSold_Item(get_fname, get_lname);
            double get_sold = purchaseManagement.sellerTotalSold(get_fname, get_lname);
            
            userSoldItemLabel.setText(Utilities.formatNumber(get_soldItem));
            userTotalSalesLabel.setText(Utilities.formatNumber(get_sold));
            userTypeLabel.setText(get_usertype);
            usernameLabel.setText(get_username);
            fullnameLabel.setText(Utilities.capitalizeEachWord(get_fullname));
            setImageToAvatar(imageAvatar1,get_image);  
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static Object get_TableRecordID(JTable tb){
        try{
            return tb.getValueAt(tb.getSelectedRow(), 0);
        }catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
    }  
    
    private void HoverBtn(boolean check){
        if(check){
            dashboardBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    dashboardBtn.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    dashboardBtn.setBackground(Helper.colors[5]);
                }
            });
            usersBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    usersBtn.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    usersBtn.setBackground(Helper.colors[5]);
                }
            });
            inventoryBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    inventoryBtn.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    inventoryBtn.setBackground(Helper.colors[5]);
                }
            });
            categoryBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    categoryBtn.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    categoryBtn.setBackground(Helper.colors[5]);
                }
            });
            salesBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    salesBtn.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    salesBtn.setBackground(Helper.colors[5]);
                }
            });
            returnItemBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    returnItemBtn.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    returnItemBtn.setBackground(Helper.colors[5]);
                }
            });
            priceListBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    priceListBtn.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    priceListBtn.setBackground(Helper.colors[5]);
                }
            });
            reportBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    reportBtn.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    reportBtn.setBackground(Helper.colors[5]);
                }
            });    
            logoutBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    logoutBtn.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    logoutBtn.setBackground(Helper.colors[5]);
                }
            });  
            dashboardBtn1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    dashboardBtn1.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    dashboardBtn1.setBackground(Helper.colors[5]);
                }
            });
            usersBtn1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    usersBtn1.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    usersBtn1.setBackground(Helper.colors[5]);
                }
            });  
            salesBtn1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    salesBtn1.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    salesBtn1.setBackground(Helper.colors[5]);
                }
            });
            returnItemBtn1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    returnItemBtn1.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    returnItemBtn1.setBackground(Helper.colors[5]);
                }
            });
            priceListBtn1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    priceListBtn1.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    priceListBtn1.setBackground(Helper.colors[5]);
                }
            });
            logoutBtn1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    logoutBtn1.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    logoutBtn1.setBackground(Helper.colors[5]);
                }
            }); 
        }
    }
    
    private static void switchPanel(JLayeredPane layered, JPanel panel){
        layered.removeAll();
        layered.add(panel);
        layered.repaint();
        layered.revalidate();         
    }    
    
    private void logoutMethod(){
        try{
            AppManagement.setCurrentUser("nullUser", this);
            new LoginApp().setVisible(true);
            this.dispose();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layere1 = new javax.swing.JLayeredPane();
        panel1 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        menuLayere = new javax.swing.JLayeredPane();
        adminMenu = new customComponents.PanelRound();
        dashboardBtn = new customComponents.ButtonRound();
        usersBtn = new customComponents.ButtonRound();
        inventoryBtn = new customComponents.ButtonRound();
        categoryBtn = new customComponents.ButtonRound();
        salesBtn = new customComponents.ButtonRound();
        returnItemBtn = new customComponents.ButtonRound();
        priceListBtn = new customComponents.ButtonRound();
        reportBtn = new customComponents.ButtonRound();
        logoutBtn = new customComponents.ButtonRound();
        sellerMenu = new customComponents.PanelRound();
        dashboardBtn1 = new customComponents.ButtonRound();
        usersBtn1 = new customComponents.ButtonRound();
        salesBtn1 = new customComponents.ButtonRound();
        returnItemBtn1 = new customComponents.ButtonRound();
        priceListBtn1 = new customComponents.ButtonRound();
        logoutBtn1 = new customComponents.ButtonRound();
        contentLayere = new javax.swing.JLayeredPane();
        dashboardPanel = new customComponents.PanelRound();
        d1 = new customComponents.PanelRound();
        panelRound2 = new customComponents.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        totalProductsLabel = new javax.swing.JLabel();
        d3 = new customComponents.PanelRound();
        panelRound4 = new customComponents.PanelRound();
        jLabel3 = new javax.swing.JLabel();
        soldOldLabel = new javax.swing.JLabel();
        d4 = new customComponents.PanelRound();
        panelRound6 = new customComponents.PanelRound();
        jLabel4 = new javax.swing.JLabel();
        totalSalesLabel = new javax.swing.JLabel();
        d5 = new customComponents.PanelRound();
        panelRound7 = new customComponents.PanelRound();
        jLabel5 = new javax.swing.JLabel();
        outStockLabel = new javax.swing.JLabel();
        d6 = new customComponents.PanelRound();
        panelRound11 = new customComponents.PanelRound();
        jLabel6 = new javax.swing.JLabel();
        soldTotayLabel = new javax.swing.JLabel();
        d7 = new customComponents.PanelRound();
        panelRound12 = new customComponents.PanelRound();
        jLabel7 = new javax.swing.JLabel();
        todaysalesLabel = new javax.swing.JLabel();
        usersPanel = new customComponents.PanelRound();
        panelRound1 = new customComponents.PanelRound();
        imageAvatar1 = new customComponents.ImageAvatar();
        usernameLabel = new javax.swing.JLabel();
        adminBtn = new customComponents.ButtonRound();
        panelRound5 = new customComponents.PanelRound();
        panelRound8 = new customComponents.PanelRound();
        fullnameLabel = new javax.swing.JLabel();
        genderLabel = new javax.swing.JLabel();
        userTypeLabel = new javax.swing.JLabel();
        d2 = new customComponents.PanelRound();
        panelRound3 = new customComponents.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        userSoldItemLabel = new javax.swing.JLabel();
        d9 = new customComponents.PanelRound();
        panelRound10 = new customComponents.PanelRound();
        jLabel9 = new javax.swing.JLabel();
        userTotalSalesLabel = new javax.swing.JLabel();
        inventoryPanel = new customComponents.PanelRound();
        categoryPanel = new customComponents.PanelRound();
        salesPanel = new customComponents.PanelRound();
        returnItemPanel = new customComponents.PanelRound();
        priceListPanel = new customComponents.PanelRound();
        reportPanel = new customComponents.PanelRound();
        adminPanel = new javax.swing.JPanel();
        backLabel = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelRound13 = new customComponents.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        usersTable = new javax.swing.JTable();
        imageAvatar2 = new customComponents.ImageAvatar();
        panelRound15 = new customComponents.PanelRound();
        panelRound16 = new customComponents.PanelRound();
        jLabel23 = new javax.swing.JLabel();
        fnameInput = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        lnameInput = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        UnameInput = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel29 = new javax.swing.JLabel();
        genderInput1 = new javax.swing.JComboBox<>();
        userTypeInput1 = new javax.swing.JComboBox<>();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jLabel30 = new javax.swing.JLabel();
        panelRound9 = new customComponents.PanelRound();
        jButton4 = new javax.swing.JButton();
        panelRound14 = new customComponents.PanelRound();
        panelRound17 = new customComponents.PanelRound();
        imageAvatar3 = new customComponents.ImageAvatar();
        idFullname = new javax.swing.JLabel();
        idUsername = new javax.swing.JLabel();
        idGender = new javax.swing.JLabel();
        idUsertype = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        layere1.setLayout(new java.awt.CardLayout());

        title.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("POINT OF SALES AND INVENTORY MANAGEMENT SYSTEM");

        menuLayere.setLayout(new java.awt.CardLayout());

        adminMenu.setBackground(new java.awt.Color(79, 70, 229));
        adminMenu.setRoundTopRight(50);

        dashboardBtn.setBackground(new java.awt.Color(79, 70, 229));
        dashboardBtn.setForeground(new java.awt.Color(255, 255, 255));
        dashboardBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dashboard.png"))); // NOI18N
        dashboardBtn.setText("Dashboard");
        dashboardBtn.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        dashboardBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dashboardBtn.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        dashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardBtnActionPerformed(evt);
            }
        });

        usersBtn.setBackground(new java.awt.Color(79, 70, 229));
        usersBtn.setForeground(new java.awt.Color(255, 255, 255));
        usersBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user.png"))); // NOI18N
        usersBtn.setText("Users");
        usersBtn.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        usersBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        usersBtn.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        usersBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usersBtnActionPerformed(evt);
            }
        });

        inventoryBtn.setBackground(new java.awt.Color(79, 70, 229));
        inventoryBtn.setForeground(new java.awt.Color(255, 255, 255));
        inventoryBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inventory.png"))); // NOI18N
        inventoryBtn.setText("Inventory");
        inventoryBtn.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        inventoryBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        inventoryBtn.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        inventoryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventoryBtnActionPerformed(evt);
            }
        });

        categoryBtn.setBackground(new java.awt.Color(79, 70, 229));
        categoryBtn.setForeground(new java.awt.Color(255, 255, 255));
        categoryBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/category.png"))); // NOI18N
        categoryBtn.setText("Category");
        categoryBtn.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        categoryBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        categoryBtn.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        categoryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryBtnActionPerformed(evt);
            }
        });

        salesBtn.setBackground(new java.awt.Color(79, 70, 229));
        salesBtn.setForeground(new java.awt.Color(255, 255, 255));
        salesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sales.png"))); // NOI18N
        salesBtn.setText("Sales");
        salesBtn.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        salesBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        salesBtn.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        salesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesBtnActionPerformed(evt);
            }
        });

        returnItemBtn.setBackground(new java.awt.Color(79, 70, 229));
        returnItemBtn.setForeground(new java.awt.Color(255, 255, 255));
        returnItemBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/returnitem.png"))); // NOI18N
        returnItemBtn.setText("Return Item");
        returnItemBtn.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        returnItemBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        returnItemBtn.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        returnItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnItemBtnActionPerformed(evt);
            }
        });

        priceListBtn.setBackground(new java.awt.Color(79, 70, 229));
        priceListBtn.setForeground(new java.awt.Color(255, 255, 255));
        priceListBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pricelist.png"))); // NOI18N
        priceListBtn.setText("Price List");
        priceListBtn.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        priceListBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        priceListBtn.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        priceListBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceListBtnActionPerformed(evt);
            }
        });

        reportBtn.setBackground(new java.awt.Color(79, 70, 229));
        reportBtn.setForeground(new java.awt.Color(255, 255, 255));
        reportBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/report.png"))); // NOI18N
        reportBtn.setText("Report");
        reportBtn.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        reportBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        reportBtn.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        reportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportBtnActionPerformed(evt);
            }
        });

        logoutBtn.setBackground(new java.awt.Color(79, 70, 229));
        logoutBtn.setForeground(new java.awt.Color(255, 255, 255));
        logoutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logout.png"))); // NOI18N
        logoutBtn.setText("Log Out");
        logoutBtn.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        logoutBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        logoutBtn.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout adminMenuLayout = new javax.swing.GroupLayout(adminMenu);
        adminMenu.setLayout(adminMenuLayout);
        adminMenuLayout.setHorizontalGroup(
            adminMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(adminMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usersBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dashboardBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inventoryBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(categoryBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(salesBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(returnItemBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(priceListBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(reportBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(logoutBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                .addContainerGap())
        );
        adminMenuLayout.setVerticalGroup(
            adminMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminMenuLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(dashboardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usersBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inventoryBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(categoryBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(returnItemBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(priceListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        menuLayere.add(adminMenu, "card2");

        sellerMenu.setBackground(new java.awt.Color(79, 70, 229));
        sellerMenu.setRoundTopRight(50);

        dashboardBtn1.setBackground(new java.awt.Color(79, 70, 229));
        dashboardBtn1.setForeground(new java.awt.Color(255, 255, 255));
        dashboardBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dashboard.png"))); // NOI18N
        dashboardBtn1.setText("Dashboard");
        dashboardBtn1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        dashboardBtn1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dashboardBtn1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        dashboardBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardBtn1ActionPerformed(evt);
            }
        });

        usersBtn1.setBackground(new java.awt.Color(79, 70, 229));
        usersBtn1.setForeground(new java.awt.Color(255, 255, 255));
        usersBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user.png"))); // NOI18N
        usersBtn1.setText("Users");
        usersBtn1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        usersBtn1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        usersBtn1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        usersBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usersBtn1ActionPerformed(evt);
            }
        });

        salesBtn1.setBackground(new java.awt.Color(79, 70, 229));
        salesBtn1.setForeground(new java.awt.Color(255, 255, 255));
        salesBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sales.png"))); // NOI18N
        salesBtn1.setText("Sales");
        salesBtn1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        salesBtn1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        salesBtn1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        salesBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesBtn1ActionPerformed(evt);
            }
        });

        returnItemBtn1.setBackground(new java.awt.Color(79, 70, 229));
        returnItemBtn1.setForeground(new java.awt.Color(255, 255, 255));
        returnItemBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/returnitem.png"))); // NOI18N
        returnItemBtn1.setText("Return Item");
        returnItemBtn1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        returnItemBtn1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        returnItemBtn1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        returnItemBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnItemBtn1ActionPerformed(evt);
            }
        });

        priceListBtn1.setBackground(new java.awt.Color(79, 70, 229));
        priceListBtn1.setForeground(new java.awt.Color(255, 255, 255));
        priceListBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pricelist.png"))); // NOI18N
        priceListBtn1.setText("Price List");
        priceListBtn1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        priceListBtn1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        priceListBtn1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        priceListBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceListBtn1ActionPerformed(evt);
            }
        });

        logoutBtn1.setBackground(new java.awt.Color(79, 70, 229));
        logoutBtn1.setForeground(new java.awt.Color(255, 255, 255));
        logoutBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logout.png"))); // NOI18N
        logoutBtn1.setText("Log Out");
        logoutBtn1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        logoutBtn1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        logoutBtn1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        logoutBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sellerMenuLayout = new javax.swing.GroupLayout(sellerMenu);
        sellerMenu.setLayout(sellerMenuLayout);
        sellerMenuLayout.setHorizontalGroup(
            sellerMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sellerMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sellerMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dashboardBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(usersBtn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(salesBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(returnItemBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(priceListBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoutBtn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        sellerMenuLayout.setVerticalGroup(
            sellerMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sellerMenuLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(dashboardBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usersBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salesBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(returnItemBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(priceListBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 268, Short.MAX_VALUE)
                .addComponent(logoutBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        menuLayere.add(sellerMenu, "card2");

        contentLayere.setLayout(new java.awt.CardLayout());

        dashboardPanel.setBackground(new java.awt.Color(224, 231, 255));
        dashboardPanel.setRoundTopLeft(50);

        d1.setBackground(new java.awt.Color(165, 180, 252));
        d1.setRoundBottomLeft(25);
        d1.setRoundBottomRight(25);
        d1.setRoundTopLeft(25);
        d1.setRoundTopRight(25);

        panelRound2.setBackground(new java.awt.Color(99, 102, 241));
        panelRound2.setRoundBottomLeft(25);
        panelRound2.setRoundBottomRight(25);
        panelRound2.setRoundTopLeft(25);
        panelRound2.setRoundTopRight(25);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Total Products");

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        totalProductsLabel.setFont(new java.awt.Font("Calibri", 1, 60)); // NOI18N
        totalProductsLabel.setForeground(new java.awt.Color(255, 255, 255));
        totalProductsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalProductsLabel.setText("0");

        javax.swing.GroupLayout d1Layout = new javax.swing.GroupLayout(d1);
        d1.setLayout(d1Layout);
        d1Layout.setHorizontalGroup(
            d1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(d1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(d1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalProductsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        d1Layout.setVerticalGroup(
            d1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(d1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalProductsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        d3.setBackground(new java.awt.Color(165, 180, 252));
        d3.setRoundBottomLeft(25);
        d3.setRoundBottomRight(25);
        d3.setRoundTopLeft(25);
        d3.setRoundTopRight(25);

        panelRound4.setBackground(new java.awt.Color(99, 102, 241));
        panelRound4.setRoundBottomLeft(25);
        panelRound4.setRoundBottomRight(25);
        panelRound4.setRoundTopLeft(25);
        panelRound4.setRoundTopRight(25);

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Products Sold (old)");

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        soldOldLabel.setFont(new java.awt.Font("Calibri", 1, 60)); // NOI18N
        soldOldLabel.setForeground(new java.awt.Color(255, 255, 255));
        soldOldLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        soldOldLabel.setText("0");

        javax.swing.GroupLayout d3Layout = new javax.swing.GroupLayout(d3);
        d3.setLayout(d3Layout);
        d3Layout.setHorizontalGroup(
            d3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(d3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(d3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(d3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(soldOldLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        d3Layout.setVerticalGroup(
            d3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(d3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(174, Short.MAX_VALUE))
            .addGroup(d3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(d3Layout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addComponent(soldOldLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        d4.setBackground(new java.awt.Color(165, 180, 252));
        d4.setRoundBottomLeft(25);
        d4.setRoundBottomRight(25);
        d4.setRoundTopLeft(25);
        d4.setRoundTopRight(25);

        panelRound6.setBackground(new java.awt.Color(99, 102, 241));
        panelRound6.setRoundBottomLeft(25);
        panelRound6.setRoundBottomRight(25);
        panelRound6.setRoundTopLeft(25);
        panelRound6.setRoundTopRight(25);

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Total Sales");

        javax.swing.GroupLayout panelRound6Layout = new javax.swing.GroupLayout(panelRound6);
        panelRound6.setLayout(panelRound6Layout);
        panelRound6Layout.setHorizontalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound6Layout.setVerticalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        totalSalesLabel.setFont(new java.awt.Font("Calibri", 1, 60)); // NOI18N
        totalSalesLabel.setForeground(new java.awt.Color(255, 255, 255));
        totalSalesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalSalesLabel.setText("0");

        javax.swing.GroupLayout d4Layout = new javax.swing.GroupLayout(d4);
        d4.setLayout(d4Layout);
        d4Layout.setHorizontalGroup(
            d4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(d4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(d4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(d4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(totalSalesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        d4Layout.setVerticalGroup(
            d4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(d4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(174, Short.MAX_VALUE))
            .addGroup(d4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(d4Layout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addComponent(totalSalesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        d5.setBackground(new java.awt.Color(165, 180, 252));
        d5.setRoundBottomLeft(25);
        d5.setRoundBottomRight(25);
        d5.setRoundTopLeft(25);
        d5.setRoundTopRight(25);

        panelRound7.setBackground(new java.awt.Color(99, 102, 241));
        panelRound7.setRoundBottomLeft(25);
        panelRound7.setRoundBottomRight(25);
        panelRound7.setRoundTopLeft(25);
        panelRound7.setRoundTopRight(25);

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Out of Stocks");

        javax.swing.GroupLayout panelRound7Layout = new javax.swing.GroupLayout(panelRound7);
        panelRound7.setLayout(panelRound7Layout);
        panelRound7Layout.setHorizontalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound7Layout.setVerticalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        outStockLabel.setFont(new java.awt.Font("Calibri", 1, 60)); // NOI18N
        outStockLabel.setForeground(new java.awt.Color(255, 255, 255));
        outStockLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        outStockLabel.setText("0");

        javax.swing.GroupLayout d5Layout = new javax.swing.GroupLayout(d5);
        d5.setLayout(d5Layout);
        d5Layout.setHorizontalGroup(
            d5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(d5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(d5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(d5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(outStockLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        d5Layout.setVerticalGroup(
            d5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(d5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(174, Short.MAX_VALUE))
            .addGroup(d5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(d5Layout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addComponent(outStockLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        d6.setBackground(new java.awt.Color(165, 180, 252));
        d6.setRoundBottomLeft(25);
        d6.setRoundBottomRight(25);
        d6.setRoundTopLeft(25);
        d6.setRoundTopRight(25);

        panelRound11.setBackground(new java.awt.Color(99, 102, 241));
        panelRound11.setRoundBottomLeft(25);
        panelRound11.setRoundBottomRight(25);
        panelRound11.setRoundTopLeft(25);
        panelRound11.setRoundTopRight(25);

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Products Sold (today)");

        javax.swing.GroupLayout panelRound11Layout = new javax.swing.GroupLayout(panelRound11);
        panelRound11.setLayout(panelRound11Layout);
        panelRound11Layout.setHorizontalGroup(
            panelRound11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound11Layout.setVerticalGroup(
            panelRound11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        soldTotayLabel.setFont(new java.awt.Font("Calibri", 1, 60)); // NOI18N
        soldTotayLabel.setForeground(new java.awt.Color(255, 255, 255));
        soldTotayLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        soldTotayLabel.setText("0");

        javax.swing.GroupLayout d6Layout = new javax.swing.GroupLayout(d6);
        d6.setLayout(d6Layout);
        d6Layout.setHorizontalGroup(
            d6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(d6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(d6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(d6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(soldTotayLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        d6Layout.setVerticalGroup(
            d6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(d6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(174, Short.MAX_VALUE))
            .addGroup(d6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(d6Layout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addComponent(soldTotayLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        d7.setBackground(new java.awt.Color(165, 180, 252));
        d7.setRoundBottomLeft(25);
        d7.setRoundBottomRight(25);
        d7.setRoundTopLeft(25);
        d7.setRoundTopRight(25);

        panelRound12.setBackground(new java.awt.Color(99, 102, 241));
        panelRound12.setRoundBottomLeft(25);
        panelRound12.setRoundBottomRight(25);
        panelRound12.setRoundTopLeft(25);
        panelRound12.setRoundTopRight(25);

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Today's Sales");

        javax.swing.GroupLayout panelRound12Layout = new javax.swing.GroupLayout(panelRound12);
        panelRound12.setLayout(panelRound12Layout);
        panelRound12Layout.setHorizontalGroup(
            panelRound12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound12Layout.setVerticalGroup(
            panelRound12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        todaysalesLabel.setFont(new java.awt.Font("Calibri", 1, 60)); // NOI18N
        todaysalesLabel.setForeground(new java.awt.Color(255, 255, 255));
        todaysalesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        todaysalesLabel.setText("0");

        javax.swing.GroupLayout d7Layout = new javax.swing.GroupLayout(d7);
        d7.setLayout(d7Layout);
        d7Layout.setHorizontalGroup(
            d7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(d7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(d7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(d7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(todaysalesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        d7Layout.setVerticalGroup(
            d7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(d7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(174, Short.MAX_VALUE))
            .addGroup(d7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(d7Layout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addComponent(todaysalesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout dashboardPanelLayout = new javax.swing.GroupLayout(dashboardPanel);
        dashboardPanel.setLayout(dashboardPanelLayout);
        dashboardPanelLayout.setHorizontalGroup(
            dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPanelLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(d1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(d5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(53, 53, 53)
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(d6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(d3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(53, 53, 53)
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(d4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(d7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(53, 53, 53))
        );
        dashboardPanelLayout.setVerticalGroup(
            dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(d4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(d1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(d3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(54, 54, 54)
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(d5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(d6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(d7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );

        contentLayere.add(dashboardPanel, "card2");

        usersPanel.setBackground(new java.awt.Color(224, 231, 255));
        usersPanel.setRoundTopLeft(50);

        panelRound1.setBackground(new java.awt.Color(165, 180, 252));
        panelRound1.setRoundBottomLeft(25);
        panelRound1.setRoundBottomRight(25);
        panelRound1.setRoundTopLeft(50);
        panelRound1.setRoundTopRight(25);

        imageAvatar1.setForeground(new java.awt.Color(67, 56, 202));
        imageAvatar1.setBorderSize(3);
        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nullProfile.jpg"))); // NOI18N

        usernameLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usernameLabel.setText("Example Username");

        adminBtn.setBackground(new java.awt.Color(99, 102, 241));
        adminBtn.setForeground(new java.awt.Color(255, 255, 255));
        adminBtn.setText("Admin Panel");
        adminBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 14, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(adminBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 270, Short.MAX_VALUE)
                .addComponent(adminBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        panelRound5.setBackground(new java.awt.Color(165, 180, 252));
        panelRound5.setRoundBottomLeft(25);
        panelRound5.setRoundBottomRight(25);
        panelRound5.setRoundTopLeft(25);
        panelRound5.setRoundTopRight(25);

        panelRound8.setBackground(new java.awt.Color(99, 102, 241));
        panelRound8.setRoundBottomLeft(25);
        panelRound8.setRoundBottomRight(25);
        panelRound8.setRoundTopLeft(25);
        panelRound8.setRoundTopRight(25);

        fullnameLabel.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        fullnameLabel.setForeground(new java.awt.Color(255, 255, 255));
        fullnameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fullnameLabel.setText("Example Name");

        javax.swing.GroupLayout panelRound8Layout = new javax.swing.GroupLayout(panelRound8);
        panelRound8.setLayout(panelRound8Layout);
        panelRound8Layout.setHorizontalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fullnameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound8Layout.setVerticalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound8Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(fullnameLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        genderLabel.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        genderLabel.setForeground(new java.awt.Color(255, 255, 255));
        genderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        genderLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/nogender.png"))); // NOI18N

        userTypeLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        userTypeLabel.setForeground(new java.awt.Color(255, 255, 255));
        userTypeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userTypeLabel.setText("Admin");

        d2.setBackground(new java.awt.Color(224, 231, 255));
        d2.setRoundBottomLeft(25);
        d2.setRoundBottomRight(25);
        d2.setRoundTopLeft(25);
        d2.setRoundTopRight(25);

        panelRound3.setBackground(new java.awt.Color(99, 102, 241));
        panelRound3.setRoundBottomLeft(25);
        panelRound3.setRoundBottomRight(25);
        panelRound3.setRoundTopLeft(25);
        panelRound3.setRoundTopRight(25);

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Seller total Sold Item");

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        userSoldItemLabel.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        userSoldItemLabel.setForeground(new java.awt.Color(51, 51, 51));
        userSoldItemLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userSoldItemLabel.setText("0");

        javax.swing.GroupLayout d2Layout = new javax.swing.GroupLayout(d2);
        d2.setLayout(d2Layout);
        d2Layout.setHorizontalGroup(
            d2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(d2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(d2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userSoldItemLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        d2Layout.setVerticalGroup(
            d2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(d2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userSoldItemLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        d9.setBackground(new java.awt.Color(224, 231, 255));
        d9.setRoundBottomLeft(25);
        d9.setRoundBottomRight(25);
        d9.setRoundTopLeft(25);
        d9.setRoundTopRight(25);

        panelRound10.setBackground(new java.awt.Color(99, 102, 241));
        panelRound10.setRoundBottomLeft(25);
        panelRound10.setRoundBottomRight(25);
        panelRound10.setRoundTopLeft(25);
        panelRound10.setRoundTopRight(25);

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Seller total sold");

        javax.swing.GroupLayout panelRound10Layout = new javax.swing.GroupLayout(panelRound10);
        panelRound10.setLayout(panelRound10Layout);
        panelRound10Layout.setHorizontalGroup(
            panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound10Layout.setVerticalGroup(
            panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        userTotalSalesLabel.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        userTotalSalesLabel.setForeground(new java.awt.Color(51, 51, 51));
        userTotalSalesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userTotalSalesLabel.setText("0");

        javax.swing.GroupLayout d9Layout = new javax.swing.GroupLayout(d9);
        d9.setLayout(d9Layout);
        d9Layout.setHorizontalGroup(
            d9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(d9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(d9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userTotalSalesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        d9Layout.setVerticalGroup(
            d9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(d9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userTotalSalesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(genderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userTypeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound5Layout.createSequentialGroup()
                        .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(d9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(d2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(genderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRound8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(d2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(d9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout usersPanelLayout = new javax.swing.GroupLayout(usersPanel);
        usersPanel.setLayout(usersPanelLayout);
        usersPanelLayout.setHorizontalGroup(
            usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRound5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        usersPanelLayout.setVerticalGroup(
            usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, usersPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelRound5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        contentLayere.add(usersPanel, "card2");

        inventoryPanel.setBackground(new java.awt.Color(224, 231, 255));
        inventoryPanel.setRoundTopLeft(50);

        javax.swing.GroupLayout inventoryPanelLayout = new javax.swing.GroupLayout(inventoryPanel);
        inventoryPanel.setLayout(inventoryPanelLayout);
        inventoryPanelLayout.setHorizontalGroup(
            inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 881, Short.MAX_VALUE)
        );
        inventoryPanelLayout.setVerticalGroup(
            inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );

        contentLayere.add(inventoryPanel, "card2");

        categoryPanel.setBackground(new java.awt.Color(224, 231, 255));
        categoryPanel.setRoundTopLeft(50);

        javax.swing.GroupLayout categoryPanelLayout = new javax.swing.GroupLayout(categoryPanel);
        categoryPanel.setLayout(categoryPanelLayout);
        categoryPanelLayout.setHorizontalGroup(
            categoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 881, Short.MAX_VALUE)
        );
        categoryPanelLayout.setVerticalGroup(
            categoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );

        contentLayere.add(categoryPanel, "card2");

        salesPanel.setBackground(new java.awt.Color(224, 231, 255));
        salesPanel.setRoundTopLeft(50);

        javax.swing.GroupLayout salesPanelLayout = new javax.swing.GroupLayout(salesPanel);
        salesPanel.setLayout(salesPanelLayout);
        salesPanelLayout.setHorizontalGroup(
            salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 881, Short.MAX_VALUE)
        );
        salesPanelLayout.setVerticalGroup(
            salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );

        contentLayere.add(salesPanel, "card2");

        returnItemPanel.setBackground(new java.awt.Color(224, 231, 255));
        returnItemPanel.setRoundTopLeft(50);

        javax.swing.GroupLayout returnItemPanelLayout = new javax.swing.GroupLayout(returnItemPanel);
        returnItemPanel.setLayout(returnItemPanelLayout);
        returnItemPanelLayout.setHorizontalGroup(
            returnItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 881, Short.MAX_VALUE)
        );
        returnItemPanelLayout.setVerticalGroup(
            returnItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );

        contentLayere.add(returnItemPanel, "card2");

        priceListPanel.setBackground(new java.awt.Color(224, 231, 255));
        priceListPanel.setRoundTopLeft(50);

        javax.swing.GroupLayout priceListPanelLayout = new javax.swing.GroupLayout(priceListPanel);
        priceListPanel.setLayout(priceListPanelLayout);
        priceListPanelLayout.setHorizontalGroup(
            priceListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 881, Short.MAX_VALUE)
        );
        priceListPanelLayout.setVerticalGroup(
            priceListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );

        contentLayere.add(priceListPanel, "card2");

        reportPanel.setBackground(new java.awt.Color(224, 231, 255));
        reportPanel.setRoundTopLeft(50);

        javax.swing.GroupLayout reportPanelLayout = new javax.swing.GroupLayout(reportPanel);
        reportPanel.setLayout(reportPanelLayout);
        reportPanelLayout.setHorizontalGroup(
            reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 881, Short.MAX_VALUE)
        );
        reportPanelLayout.setVerticalGroup(
            reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );

        contentLayere.add(reportPanel, "card2");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addComponent(menuLayere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentLayere))
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuLayere)
                    .addComponent(contentLayere)))
        );

        layere1.add(panel1, "card2");

        backLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back2.png"))); // NOI18N
        backLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backLabelMouseClicked(evt);
            }
        });

        jTabbedPane1.setForeground(new java.awt.Color(51, 51, 51));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        panelRound13.setBackground(new java.awt.Color(224, 231, 255));
        panelRound13.setRoundTopLeft(25);
        panelRound13.setRoundTopRight(25);

        usersTable.setBackground(new java.awt.Color(224, 231, 255));
        usersTable.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        usersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "Firstname", "Lastname", "Username", "Password", "Birth Date", "Gender", "Image", "User Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        usersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usersTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(usersTable);

        imageAvatar2.setForeground(new java.awt.Color(165, 180, 252));
        imageAvatar2.setBorderSize(2);
        imageAvatar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nullProfile.jpg"))); // NOI18N

        javax.swing.GroupLayout panelRound13Layout = new javax.swing.GroupLayout(panelRound13);
        panelRound13.setLayout(panelRound13Layout);
        panelRound13Layout.setHorizontalGroup(
            panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addGap(18, 18, 18)
                .addComponent(imageAvatar2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        panelRound13Layout.setVerticalGroup(
            panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelRound13Layout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(imageAvatar2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("User Config", panelRound13);

        panelRound15.setBackground(new java.awt.Color(224, 231, 255));
        panelRound15.setRoundTopLeft(25);
        panelRound15.setRoundTopRight(25);

        panelRound16.setBackground(new java.awt.Color(165, 180, 252));
        panelRound16.setRoundBottomLeft(25);
        panelRound16.setRoundBottomRight(25);
        panelRound16.setRoundTopLeft(25);
        panelRound16.setRoundTopRight(25);

        jLabel23.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("Firstname");

        fnameInput.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        fnameInput.setForeground(new java.awt.Color(51, 51, 51));
        fnameInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fnameInputKeyReleased(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setText("Lastname");

        lnameInput.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lnameInput.setForeground(new java.awt.Color(51, 51, 51));
        lnameInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lnameInputKeyReleased(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText("Username");

        UnameInput.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        UnameInput.setForeground(new java.awt.Color(51, 51, 51));
        UnameInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UnameInputKeyReleased(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Password");

        jTextField12.setForeground(new java.awt.Color(51, 51, 51));

        jLabel27.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setText("Confirm Password");

        jTextField13.setForeground(new java.awt.Color(51, 51, 51));

        jLabel28.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setText("Birth Date");

        jDateChooser2.setForeground(new java.awt.Color(51, 51, 51));
        jDateChooser2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("Gender");

        genderInput1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        genderInput1.setForeground(new java.awt.Color(51, 51, 51));
        genderInput1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        genderInput1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderInput1ActionPerformed(evt);
            }
        });

        userTypeInput1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        userTypeInput1.setForeground(new java.awt.Color(51, 51, 51));
        userTypeInput1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Seller" }));
        userTypeInput1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userTypeInput1ActionPerformed(evt);
            }
        });

        jCheckBox2.setFont(new java.awt.Font("Calibri", 0, 10)); // NOI18N
        jCheckBox2.setForeground(new java.awt.Color(51, 51, 51));
        jCheckBox2.setText("Show Password");

        jCheckBox4.setFont(new java.awt.Font("Calibri", 0, 10)); // NOI18N
        jCheckBox4.setForeground(new java.awt.Color(51, 51, 51));
        jCheckBox4.setText("Show Password");

        jLabel30.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("User Type");

        panelRound9.setRoundBottomLeft(25);
        panelRound9.setRoundBottomRight(25);
        panelRound9.setRoundTopLeft(25);
        panelRound9.setRoundTopRight(25);

        javax.swing.GroupLayout panelRound9Layout = new javax.swing.GroupLayout(panelRound9);
        panelRound9.setLayout(panelRound9Layout);
        panelRound9Layout.setHorizontalGroup(
            panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelRound9Layout.setVerticalGroup(
            panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelRound16Layout = new javax.swing.GroupLayout(panelRound16);
        panelRound16.setLayout(panelRound16Layout);
        panelRound16Layout.setHorizontalGroup(
            panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound16Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound16Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jCheckBox2))
                    .addGroup(panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField12)
                        .addGroup(panelRound16Layout.createSequentialGroup()
                            .addComponent(jLabel26)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBox4))
                        .addComponent(jLabel28)
                        .addComponent(jTextField13)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lnameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25)
                        .addComponent(UnameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23)
                        .addComponent(fnameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addGroup(panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelRound9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(genderInput1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userTypeInput1, 0, 163, Short.MAX_VALUE))
                .addGap(109, 109, 109))
        );
        panelRound16Layout.setVerticalGroup(
            panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound16Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(panelRound16Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1)))
                .addGroup(panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fnameInput, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(genderInput1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lnameInput, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(userTypeInput1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound16Layout.createSequentialGroup()
                        .addComponent(UnameInput, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound16Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jCheckBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField12, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                    .addComponent(panelRound9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound16Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jCheckBox2, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField13, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        jButton4.setText("ADD");

        panelRound14.setBackground(new java.awt.Color(51, 51, 51));
        panelRound14.setRoundBottomLeft(25);
        panelRound14.setRoundBottomRight(25);
        panelRound14.setRoundTopLeft(25);
        panelRound14.setRoundTopRight(25);

        panelRound17.setBackground(new java.awt.Color(255, 255, 255));
        panelRound17.setRoundBottomLeft(25);
        panelRound17.setRoundBottomRight(25);
        panelRound17.setRoundTopLeft(25);
        panelRound17.setRoundTopRight(25);

        imageAvatar3.setForeground(new java.awt.Color(51, 51, 51));
        imageAvatar3.setBorderSize(1);
        imageAvatar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nullProfile.jpg"))); // NOI18N

        idFullname.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        idFullname.setForeground(new java.awt.Color(51, 51, 51));
        idFullname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        idUsername.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        idUsername.setForeground(new java.awt.Color(51, 51, 51));
        idUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        idGender.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        idGender.setForeground(new java.awt.Color(51, 51, 51));
        idGender.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        idUsertype.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        idUsertype.setForeground(new java.awt.Color(51, 51, 51));
        idUsertype.setText("Seller ID: example123.wan");

        javax.swing.GroupLayout panelRound17Layout = new javax.swing.GroupLayout(panelRound17);
        panelRound17.setLayout(panelRound17Layout);
        panelRound17Layout.setHorizontalGroup(
            panelRound17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound17Layout.createSequentialGroup()
                .addGroup(panelRound17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound17Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(imageAvatar3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRound17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idFullname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelRound17Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(idUsertype, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(panelRound17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(idUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(idGender, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound17Layout.setVerticalGroup(
            panelRound17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageAvatar3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound17Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(idFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idUsertype, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52)
                .addComponent(idUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idGender, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelRound14Layout = new javax.swing.GroupLayout(panelRound14);
        panelRound14.setLayout(panelRound14Layout);
        panelRound14Layout.setHorizontalGroup(
            panelRound14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound14Layout.setVerticalGroup(
            panelRound14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelRound15Layout = new javax.swing.GroupLayout(panelRound15);
        panelRound15.setLayout(panelRound15Layout);
        panelRound15Layout.setHorizontalGroup(
            panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound15Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(panelRound16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelRound15Layout.setVerticalGroup(
            panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound15Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound15Layout.createSequentialGroup()
                        .addComponent(panelRound14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelRound16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("Add Users", panelRound15);

        javax.swing.GroupLayout adminPanelLayout = new javax.swing.GroupLayout(adminPanel);
        adminPanel.setLayout(adminPanelLayout);
        adminPanelLayout.setHorizontalGroup(
            adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(backLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(adminPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        adminPanelLayout.setVerticalGroup(
            adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adminPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        layere1.add(adminPanel, "card3");

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

    private void dashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardBtnActionPerformed
        switchPanel(contentLayere,dashboardPanel);
    }//GEN-LAST:event_dashboardBtnActionPerformed

    private void usersBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersBtnActionPerformed
        switchPanel(contentLayere,usersPanel);
    }//GEN-LAST:event_usersBtnActionPerformed

    private void inventoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventoryBtnActionPerformed
        switchPanel(contentLayere,inventoryPanel);
    }//GEN-LAST:event_inventoryBtnActionPerformed

    private void categoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryBtnActionPerformed
        switchPanel(contentLayere,categoryPanel);
    }//GEN-LAST:event_categoryBtnActionPerformed

    private void salesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesBtnActionPerformed
        switchPanel(contentLayere,salesPanel);
    }//GEN-LAST:event_salesBtnActionPerformed

    private void returnItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnItemBtnActionPerformed
        switchPanel(contentLayere,returnItemPanel);
    }//GEN-LAST:event_returnItemBtnActionPerformed

    private void priceListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceListBtnActionPerformed
        switchPanel(contentLayere,priceListPanel);
    }//GEN-LAST:event_priceListBtnActionPerformed

    private void reportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportBtnActionPerformed
        switchPanel(contentLayere,reportPanel);
    }//GEN-LAST:event_reportBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        logoutMethod();
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void dashboardBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardBtn1ActionPerformed
        switchPanel(contentLayere,dashboardPanel);
    }//GEN-LAST:event_dashboardBtn1ActionPerformed

    private void usersBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersBtn1ActionPerformed
        switchPanel(contentLayere,usersPanel);
    }//GEN-LAST:event_usersBtn1ActionPerformed

    private void salesBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesBtn1ActionPerformed
        switchPanel(contentLayere,salesPanel);
    }//GEN-LAST:event_salesBtn1ActionPerformed

    private void returnItemBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnItemBtn1ActionPerformed
        switchPanel(contentLayere,returnItemPanel);
    }//GEN-LAST:event_returnItemBtn1ActionPerformed

    private void priceListBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceListBtn1ActionPerformed
        switchPanel(contentLayere,priceListPanel);
    }//GEN-LAST:event_priceListBtn1ActionPerformed

    private void logoutBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtn1ActionPerformed
        logoutMethod();
    }//GEN-LAST:event_logoutBtn1ActionPerformed

    private void adminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminBtnActionPerformed
        switchPanel(layere1,adminPanel);
    }//GEN-LAST:event_adminBtnActionPerformed

    private void backLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backLabelMouseClicked
        switchPanel(layere1,panel1);
    }//GEN-LAST:event_backLabelMouseClicked

    private void usersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersTableMouseClicked
        int get_selectedColumn = usersTable.getSelectedColumn();

        Object get_id;
        String new_val;

        switch(get_selectedColumn){
            case 1:
            get_id = get_TableRecordID(usersTable);
            new_val = JOptionPane.showInputDialog(this, "Enter new firstname for user "+get_id+":", "Change Firstname", JOptionPane.QUESTION_MESSAGE).toLowerCase();
            if(new_val != null && get_id != null){
                if(!Utilities.containsNumbers(new_val)){
                    if(changeStringData(new_val,get_id,1)){
                        JOptionPane.showMessageDialog(this, "Change Successfully", "Firstname", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "First name can only contain letters", "Invalid Firstname", JOptionPane.ERROR_MESSAGE);
                }
            }
            break;
            case 2:
            get_id = get_TableRecordID(usersTable);
            new_val = JOptionPane.showInputDialog(this, "Enter new lastname for user "+get_id+":", "Change Lastname", JOptionPane.QUESTION_MESSAGE);
            if(new_val != null && get_id != null){
                if(!Utilities.containsNumbers(new_val)){
                    if(changeStringData(new_val,get_id,2)){
                        JOptionPane.showMessageDialog(this, "Change Successfully", "Lastname", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Last name can only contain letters", "Invalid Lastname", JOptionPane.ERROR_MESSAGE);
                }
            }
            break;
            case 3:
            get_id = get_TableRecordID(usersTable);
            new_val = JOptionPane.showInputDialog(this, "Enter new username for user "+get_id+":", "Change Username", JOptionPane.QUESTION_MESSAGE);
            if(new_val != null && get_id != null){
                if(Utilities.validateUsername(new_val)){
                    if(changeStringData(new_val,get_id,3)){
                        JOptionPane.showMessageDialog(this, "Change Successfully", "Username", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Username must follow the pattern: ^[a-zA-Z]+@[0-9]+$", "Invalid Username", JOptionPane.ERROR_MESSAGE);
                }
            }
            break;
            case 4:
            get_id = get_TableRecordID(usersTable);
            new_val = JOptionPane.showInputDialog(this, "Enter new password for user "+get_id+":", "Change Password", JOptionPane.QUESTION_MESSAGE);
            String confirmPass = JOptionPane.showInputDialog(this, "Confirm new password for user "+get_id+":", "Change Password", JOptionPane.QUESTION_MESSAGE);

            if(new_val != null && confirmPass != null && get_id != null){
                if(confirmPass.equals(new_val)){
                    if(Utilities.validatePassword(confirmPass, this)){
                        if(changeStringData(confirmPass,get_id,4)){
                            JOptionPane.showMessageDialog(this, "Change Successfully", "Password", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Password mismatch", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            break;
            case 5:
            get_id = get_TableRecordID(usersTable);

            SpinnerDateModel dateModel = new SpinnerDateModel();
            JSpinner spinner = new JSpinner(dateModel);
            spinner.setEditor(new JSpinner.DateEditor(spinner, Helper.dateFormat));

            String message = "Please enter a date in the format "+Helper.dateFormat;
            int option = JOptionPane.showOptionDialog(
                this,
                new Object[]{message, spinner},
                "Select new birth date",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"OK", "Cancel"},
                "OK");

            if (option == JOptionPane.OK_OPTION) {
                Date selectedDate = (Date) spinner.getValue();
                if (selectedDate != null && get_id != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(Helper.dateFormat);
                    if(changeStringData(dateFormat.format(selectedDate),get_id,5)){
                        JOptionPane.showMessageDialog(this, "Change Successfully", "Birth date", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No date selected");
                }
            }
            break;
            case 6:
            get_id = get_TableRecordID(usersTable);
            new_val = (String) JOptionPane.showInputDialog(
                this,
                "Choose new gender for user " + get_id + ":",
                "Change Gender",
                JOptionPane.QUESTION_MESSAGE,
                null,
                Helper.listOfGender,
                Helper.listOfGender[0]);

            if(new_val != null && get_id != null){
                if(changeStringData(new_val,get_id,6)){
                    JOptionPane.showMessageDialog(this, "Change Successfully", "Gender", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            break;
            case 8:
            get_id = get_TableRecordID(usersTable);
            new_val = (String) JOptionPane.showInputDialog(
                this,
                "Select new user type for user " + get_id + ":",
                "Change User type",
                JOptionPane.QUESTION_MESSAGE,
                null,
                userManagement.listOfUserType,
                userManagement.listOfUserType[0]);

            if(new_val != null && get_id != null){
                if(changeStringData(new_val,get_id,8)){
                    JOptionPane.showMessageDialog(this, "Change Successfully", "UserType", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            break;
            default:
            break;
        }
        try {
            DbConnection.getInstance().tableData(usersTable, userManagement.table);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_usersTableMouseClicked

    private void fnameInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fnameInputKeyReleased
        idFullname.setText(fnameInput.getText()+" "+lnameInput.getText());
    }//GEN-LAST:event_fnameInputKeyReleased

    private void lnameInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lnameInputKeyReleased
        idFullname.setText(fnameInput.getText()+" "+lnameInput.getText());
    }//GEN-LAST:event_lnameInputKeyReleased

    private void UnameInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UnameInputKeyReleased
        String get_input = UnameInput.getText();
        if(!get_input.isEmpty()){
            idUsername.setText("Username: "+get_input);
        }else{
            idUsername.setText("");
        }
    }//GEN-LAST:event_UnameInputKeyReleased

    private void genderInput1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderInput1ActionPerformed
        String get_input = (String)genderInput1.getSelectedItem();
        if(!get_input.isEmpty()){
            idGender.setText("Gender: "+get_input);
        }else{
            idGender.setText("");
        }
    }//GEN-LAST:event_genderInput1ActionPerformed

    private void userTypeInput1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userTypeInput1ActionPerformed
        String get_input = (String) userTypeInput1.getSelectedItem();
        
         if(!get_input.isEmpty()){
            idUsertype.setText(get_input+" ID: ");
        }else{
            idUsertype.setText("");
        }
    }//GEN-LAST:event_userTypeInput1ActionPerformed
    
    private boolean changeStringData(String newVal, Object id,int column_index){
        try{
            userManagement.updateStringData(newVal, id,column_index);
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
        
    public void backLabelActions(JLabel label,String[] iconName){
        label.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                label.setIcon(new ImageIcon("src/icons/"+iconName[2]));
                label.repaint();
            }
            
            @Override
            public void mouseReleased(MouseEvent e){
                label.setIcon(new ImageIcon("src/icons/"+iconName[0]));
                label.repaint();                       
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setIcon(new ImageIcon("src/icons/"+iconName[1]));
                label.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setIcon(new ImageIcon("src/icons/"+iconName[0]));
                label.repaint();       
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField UnameInput;
    private customComponents.ButtonRound adminBtn;
    private customComponents.PanelRound adminMenu;
    private javax.swing.JPanel adminPanel;
    private javax.swing.JLabel backLabel;
    private customComponents.ButtonRound categoryBtn;
    private customComponents.PanelRound categoryPanel;
    private javax.swing.JLayeredPane contentLayere;
    private customComponents.PanelRound d1;
    private customComponents.PanelRound d2;
    private customComponents.PanelRound d3;
    private customComponents.PanelRound d4;
    private customComponents.PanelRound d5;
    private customComponents.PanelRound d6;
    private customComponents.PanelRound d7;
    private customComponents.PanelRound d9;
    private customComponents.ButtonRound dashboardBtn;
    private customComponents.ButtonRound dashboardBtn1;
    private customComponents.PanelRound dashboardPanel;
    private javax.swing.JTextField fnameInput;
    private javax.swing.JLabel fullnameLabel;
    private javax.swing.JComboBox<String> genderInput1;
    private javax.swing.JLabel genderLabel;
    private javax.swing.JLabel idFullname;
    private javax.swing.JLabel idGender;
    private javax.swing.JLabel idUsername;
    private javax.swing.JLabel idUsertype;
    public static customComponents.ImageAvatar imageAvatar1;
    private customComponents.ImageAvatar imageAvatar2;
    private customComponents.ImageAvatar imageAvatar3;
    private customComponents.ButtonRound inventoryBtn;
    private customComponents.PanelRound inventoryPanel;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox4;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JLayeredPane layere1;
    private javax.swing.JTextField lnameInput;
    private customComponents.ButtonRound logoutBtn;
    private customComponents.ButtonRound logoutBtn1;
    private javax.swing.JLayeredPane menuLayere;
    private javax.swing.JLabel outStockLabel;
    private javax.swing.JPanel panel1;
    private customComponents.PanelRound panelRound1;
    private customComponents.PanelRound panelRound10;
    private customComponents.PanelRound panelRound11;
    private customComponents.PanelRound panelRound12;
    private customComponents.PanelRound panelRound13;
    private customComponents.PanelRound panelRound14;
    private customComponents.PanelRound panelRound15;
    private customComponents.PanelRound panelRound16;
    private customComponents.PanelRound panelRound17;
    private customComponents.PanelRound panelRound2;
    private customComponents.PanelRound panelRound3;
    private customComponents.PanelRound panelRound4;
    private customComponents.PanelRound panelRound5;
    private customComponents.PanelRound panelRound6;
    private customComponents.PanelRound panelRound7;
    private customComponents.PanelRound panelRound8;
    private customComponents.PanelRound panelRound9;
    private customComponents.ButtonRound priceListBtn;
    private customComponents.ButtonRound priceListBtn1;
    private customComponents.PanelRound priceListPanel;
    private customComponents.ButtonRound reportBtn;
    private customComponents.PanelRound reportPanel;
    private customComponents.ButtonRound returnItemBtn;
    private customComponents.ButtonRound returnItemBtn1;
    private customComponents.PanelRound returnItemPanel;
    private customComponents.ButtonRound salesBtn;
    private customComponents.ButtonRound salesBtn1;
    private customComponents.PanelRound salesPanel;
    private customComponents.PanelRound sellerMenu;
    private javax.swing.JLabel soldOldLabel;
    private javax.swing.JLabel soldTotayLabel;
    private javax.swing.JLabel title;
    private javax.swing.JLabel todaysalesLabel;
    private javax.swing.JLabel totalProductsLabel;
    private javax.swing.JLabel totalSalesLabel;
    private javax.swing.JLabel userSoldItemLabel;
    private javax.swing.JLabel userTotalSalesLabel;
    private javax.swing.JComboBox<String> userTypeInput1;
    private javax.swing.JLabel userTypeLabel;
    private javax.swing.JLabel usernameLabel;
    private customComponents.ButtonRound usersBtn;
    private customComponents.ButtonRound usersBtn1;
    private customComponents.PanelRound usersPanel;
    private javax.swing.JTable usersTable;
    // End of variables declaration//GEN-END:variables

}
