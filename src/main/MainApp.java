package main;
import assets.*;
import database.AppManagement;
import database.DashboardManagement;
import database.InventoryManagement;
import database.PurchaseManagement;
import database.UserManagement;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

@Author("Josuan Leonardo Hulom")
public final class MainApp extends javax.swing.JFrame implements AppInitializers, ImageManagement, FileManagement{
    private final UserManagement userManagement = new UserManagement(this);
    private final PurchaseManagement purchaseManagement = new PurchaseManagement();
    private final DashboardManagement dashboardManagement = new DashboardManagement();
    private final InventoryManagement inventoryManagement = new InventoryManagement(this);
    
    public MainApp(){
        Image appIcon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo/appLogo2.png"));
        this.setIconImage(appIcon);
        this.setTitle("College of Computer Studies Campus Corner Shop");
        initComponents();
        initialize();
        HoverBtn(true);
        Utilities.backLabelActions(backLabel, new String[]{"back2.png","back1.png","back3.png"});
        
        if (this.getExtendedState() == MainApp.MAXIMIZED_BOTH) {
            this.setExtendedState(MainApp.NORMAL);
        } else {
            this.setExtendedState(MainApp.MAXIMIZED_BOTH);
        }
    }
    
    @Override
    public void init_table() {
        try {
            AppManagement.tableData(usersTable,userManagement.table,userManagement.columns);
            AppManagement.tableData(inventoryTable,inventoryManagement.table,inventoryManagement.columns);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error Code#: "+e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void init_models(){
        SpinnerNumberModel quantitySpinner = new SpinnerNumberModel(0,0,Integer.MAX_VALUE,1);
        inventoryQuantityInput.setModel(quantitySpinner);
        //CMT.AddElementToComboBox()sd
        inventoryTable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JComboBox(new String[]{"test 1","test 2"})));
        inventoryTable.getColumnModel().getColumn(4).setCellEditor(new Utilities.IntCustomJSpinner());
        inventoryTable.getColumnModel().getColumn(5).setCellEditor(new Utilities.DoubleCustomJSpinner());  
    }
    
    @Override
    public void init_dashboard(){
        try {
            totalProductsLabel.setText(Integer.toString(dashboardManagement.countProducts()));
            soldOldLabel.setText(Integer.toString(dashboardManagement.getProductSold()));
            soldTotayLabel.setText(Integer.toString(dashboardManagement.getProductSoldToday()));
            outStockLabel.setText(Integer.toString(dashboardManagement.getOutOfStocks()));
            
            double getTotalSales = dashboardManagement.getTotalSales();
            totalSalesLabel.setText(Helper.currency+" "+getTotalSales);
            totalSalesLabel.setText(Helper.currency+" "+Utilities.formatNumber(getTotalSales));      
            totalSalesLabel.setToolTipText(Helper.currency+" "+getTotalSales);
            
            double getTotalSalesToday = dashboardManagement.getTotalSalesToday();
            todaysalesLabel.setText(Helper.currency+" "+getTotalSalesToday);
            todaysalesLabel.setText(Helper.currency+" "+Utilities.formatNumber(getTotalSalesToday));
            todaysalesLabel.setToolTipText(Helper.currency+" "+getTotalSalesToday);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error Code#: "+e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void init_user() {
        try{
            String get_id = AppManagement.getCurrentUser(this);
            if(!get_id.equals("nullUser")){
                String get_image = userManagement.getImagePath(get_id);
                String get_fname = userManagement.getFName(get_id);
                String get_lname = userManagement.getLName(get_id);            
                String get_username = userManagement.getUName(get_id);
                String get_gender = userManagement.getGender(get_id);
                String get_usertype = userManagement.getUserType(get_id);

                if(get_usertype.equals(userManagement.listOfUserType[0])){
                    Utilities.switchPanel(menuLayere,adminMenu);
                }else if(get_usertype.equals(userManagement.listOfUserType[1])){
                    Utilities.switchPanel(menuLayere,sellerMenu);
                    adminBtn.setVisible(false);
                    adminBtn.setEnabled(false);
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
                updatableUserData();
                setImageToAvatar(imageAvatar1,get_image); 
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error Code#: "+e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updatableUserData(){
        try {
            String get_id = AppManagement.getCurrentUser(this);
                if(!get_id.equals("nullUser")){
                String get_fname = userManagement.getFName(get_id);
                String get_lname = userManagement.getLName(get_id);
                String get_fullname = get_fname + " " + get_lname;  
                fullnameLabel.setText(Utilities.capitalizeEachWord(get_fullname));

                int get_soldItem = purchaseManagement.sellerTotalSold_Item(get_fname, get_lname);
                double get_sold = purchaseManagement.sellerTotalSold(get_fname, get_lname);
                userSoldItemLabel.setText(Utilities.formatNumber(get_soldItem));
                userTotalSalesLabel.setText(Utilities.formatNumber(get_sold));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error Code#: "+e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
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
                    dashboardBtn.setBackground(Helper.colors[4]);
                }
            });
            usersBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    usersBtn.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    usersBtn.setBackground(Helper.colors[4]);
                }
            });
            inventoryBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    inventoryBtn.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    inventoryBtn.setBackground(Helper.colors[4]);
                }
            });
            categoryBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    categoryBtn.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    categoryBtn.setBackground(Helper.colors[4]);
                }
            });
            salesBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    salesBtn.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    salesBtn.setBackground(Helper.colors[4]);
                }
            });
            returnItemBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    returnItemBtn.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    returnItemBtn.setBackground(Helper.colors[4]);
                }
            });
            priceListBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    priceListBtn.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    priceListBtn.setBackground(Helper.colors[4]);
                }
            });
            reportBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    reportBtn.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    reportBtn.setBackground(Helper.colors[4]);
                }
            });    
            logoutBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    logoutBtn.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    logoutBtn.setBackground(Helper.colors[4]);
                }
            });  
            dashboardBtn1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    dashboardBtn1.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    dashboardBtn1.setBackground(Helper.colors[4]);
                }
            });
            usersBtn1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    usersBtn1.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    usersBtn1.setBackground(Helper.colors[4]);
                }
            });  
            salesBtn1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    salesBtn1.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    salesBtn1.setBackground(Helper.colors[4]);
                }
            });
            returnItemBtn1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    returnItemBtn1.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    returnItemBtn1.setBackground(Helper.colors[4]);
                }
            });
            priceListBtn1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    priceListBtn1.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    priceListBtn1.setBackground(Helper.colors[4]);
                }
            });
            logoutBtn1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    logoutBtn1.setBackground(Helper.colors[3]);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    logoutBtn1.setBackground(Helper.colors[4]);
                }
            }); 
        }
    }
     
    private void logoutMethod(){
        try{
            AppManagement.setCurrentUser("nullUser", this);
            new LoginApp().setVisible(true);
            this.dispose();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error Code#: "+e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
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
        pictureBox1 = new customComponents.PictureBox();
        jLabel18 = new javax.swing.JLabel();
        sellerMenu = new customComponents.PanelRound();
        dashboardBtn1 = new customComponents.ButtonRound();
        usersBtn1 = new customComponents.ButtonRound();
        salesBtn1 = new customComponents.ButtonRound();
        returnItemBtn1 = new customComponents.ButtonRound();
        priceListBtn1 = new customComponents.ButtonRound();
        logoutBtn1 = new customComponents.ButtonRound();
        pictureBox3 = new customComponents.PictureBox();
        jLabel19 = new javax.swing.JLabel();
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
        changeProfileBtn = new customComponents.ButtonRound();
        inventoryPanel = new customComponents.PanelRound();
        inventoryScrollPane = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        panelRound15 = new customComponents.PanelRound();
        jLabel29 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        inventoryQuantityInput = new javax.swing.JSpinner();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        jSpinner4 = new javax.swing.JSpinner();
        jLabel33 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel34 = new javax.swing.JLabel();
        inventoryImgDisplay = new customComponents.PictureBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        inventoryTable = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        textFieldWithIcon3 = new customComponents.TextFieldWithIcon();
        inventoryVariantPanel = new customComponents.PanelRound();
        addVariantBtn = new javax.swing.JButton();
        toeditVariant = new javax.swing.JComboBox<>();
        toaddVariant = new javax.swing.JTextField();
        editVariantBtn = new javax.swing.JButton();
        deleteVariantBtn = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        todeleteVariant = new javax.swing.JComboBox<>();
        categoryPanel = new customComponents.PanelRound();
        jScrollPane2 = new javax.swing.JScrollPane();
        CategoryTable = new javax.swing.JTable();
        panelRound14 = new customComponents.PanelRound();
        jLabel36 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        textFieldWithIcon4 = new customComponents.TextFieldWithIcon();
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
        jLabel8 = new javax.swing.JLabel();
        adminFnameInput = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        adminLnameInput = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        adminUnameInput = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        adminPasswordInput = new javax.swing.JPasswordField();
        jLabel13 = new javax.swing.JLabel();
        adminPasswordInput1 = new javax.swing.JPasswordField();
        adminBdayInput = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        adminGenderInput = new javax.swing.JComboBox<>();
        adminUserTypeInput = new javax.swing.JComboBox<>();
        adminGetImageBtn = new javax.swing.JButton();
        addUserBtn = new javax.swing.JButton();
        adminShowC = new javax.swing.JCheckBox();
        panelRound9 = new customComponents.PanelRound();
        testbtn1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        layere1.setLayout(new java.awt.CardLayout());

        title.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("POINT OF SALES AND INVENTORY MANAGEMENT SYSTEM");

        menuLayere.setLayout(new java.awt.CardLayout());

        adminMenu.setBackground(new java.awt.Color(160, 47, 47));
        adminMenu.setRoundTopRight(50);

        dashboardBtn.setBackground(new java.awt.Color(176, 62, 62));
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

        usersBtn.setBackground(new java.awt.Color(176, 62, 62));
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

        inventoryBtn.setBackground(new java.awt.Color(176, 62, 62));
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

        categoryBtn.setBackground(new java.awt.Color(176, 62, 62));
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

        salesBtn.setBackground(new java.awt.Color(176, 62, 62));
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

        returnItemBtn.setBackground(new java.awt.Color(176, 62, 62));
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

        priceListBtn.setBackground(new java.awt.Color(176, 62, 62));
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

        reportBtn.setBackground(new java.awt.Color(176, 62, 62));
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

        logoutBtn.setBackground(new java.awt.Color(176, 62, 62));
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

        pictureBox1.setToolTipText("(CRMC) College of Computer Studies");
        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/logo/appLogo.png"))); // NOI18N
        pictureBox1.setOpaque(false);

        javax.swing.GroupLayout pictureBox1Layout = new javax.swing.GroupLayout(pictureBox1);
        pictureBox1.setLayout(pictureBox1Layout);
        pictureBox1Layout.setHorizontalGroup(
            pictureBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 121, Short.MAX_VALUE)
        );
        pictureBox1Layout.setVerticalGroup(
            pictureBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        jLabel18.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Campus Corner Shop");

        javax.swing.GroupLayout adminMenuLayout = new javax.swing.GroupLayout(adminMenu);
        adminMenu.setLayout(adminMenuLayout);
        adminMenuLayout.setHorizontalGroup(
            adminMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminMenuLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adminMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(adminMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usersBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dashboardBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inventoryBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(categoryBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(salesBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(returnItemBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(priceListBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(reportBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adminMenuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(adminMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(logoutBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        adminMenuLayout.setVerticalGroup(
            adminMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        menuLayere.add(adminMenu, "card2");

        sellerMenu.setBackground(new java.awt.Color(160, 47, 47));
        sellerMenu.setRoundTopRight(50);

        dashboardBtn1.setBackground(new java.awt.Color(176, 62, 62));
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

        usersBtn1.setBackground(new java.awt.Color(176, 62, 62));
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

        salesBtn1.setBackground(new java.awt.Color(176, 62, 62));
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

        returnItemBtn1.setBackground(new java.awt.Color(176, 62, 62));
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

        priceListBtn1.setBackground(new java.awt.Color(176, 62, 62));
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

        logoutBtn1.setBackground(new java.awt.Color(176, 62, 62));
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

        pictureBox3.setToolTipText("(CRMC) College of Computer Studies");
        pictureBox3.setImage(new javax.swing.ImageIcon(getClass().getResource("/logo/appLogo.png"))); // NOI18N
        pictureBox3.setOpaque(false);

        javax.swing.GroupLayout pictureBox3Layout = new javax.swing.GroupLayout(pictureBox3);
        pictureBox3.setLayout(pictureBox3Layout);
        pictureBox3Layout.setHorizontalGroup(
            pictureBox3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 121, Short.MAX_VALUE)
        );
        pictureBox3Layout.setVerticalGroup(
            pictureBox3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Campus Corner Shop");

        javax.swing.GroupLayout sellerMenuLayout = new javax.swing.GroupLayout(sellerMenu);
        sellerMenu.setLayout(sellerMenuLayout);
        sellerMenuLayout.setHorizontalGroup(
            sellerMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sellerMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sellerMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dashboardBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(usersBtn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(salesBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(returnItemBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(priceListBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoutBtn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(sellerMenuLayout.createSequentialGroup()
                        .addGroup(sellerMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sellerMenuLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(pictureBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        sellerMenuLayout.setVerticalGroup(
            sellerMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sellerMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pictureBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(dashboardBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usersBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salesBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(returnItemBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(priceListBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                .addComponent(logoutBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        menuLayere.add(sellerMenu, "card2");

        contentLayere.setLayout(new java.awt.CardLayout());

        dashboardPanel.setBackground(new java.awt.Color(245, 205, 205));
        dashboardPanel.setRoundTopLeft(50);

        d1.setBackground(new java.awt.Color(218, 136, 136));
        d1.setRoundBottomLeft(25);
        d1.setRoundBottomRight(25);
        d1.setRoundTopLeft(25);
        d1.setRoundTopRight(25);

        panelRound2.setBackground(new java.awt.Color(176, 62, 62));
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

        d3.setBackground(new java.awt.Color(218, 136, 136));
        d3.setRoundBottomLeft(25);
        d3.setRoundBottomRight(25);
        d3.setRoundTopLeft(25);
        d3.setRoundTopRight(25);

        panelRound4.setBackground(new java.awt.Color(176, 62, 62));
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

        d4.setBackground(new java.awt.Color(218, 136, 136));
        d4.setRoundBottomLeft(25);
        d4.setRoundBottomRight(25);
        d4.setRoundTopLeft(25);
        d4.setRoundTopRight(25);

        panelRound6.setBackground(new java.awt.Color(176, 62, 62));
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

        d5.setBackground(new java.awt.Color(218, 136, 136));
        d5.setRoundBottomLeft(25);
        d5.setRoundBottomRight(25);
        d5.setRoundTopLeft(25);
        d5.setRoundTopRight(25);

        panelRound7.setBackground(new java.awt.Color(176, 62, 62));
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

        d6.setBackground(new java.awt.Color(218, 136, 136));
        d6.setRoundBottomLeft(25);
        d6.setRoundBottomRight(25);
        d6.setRoundTopLeft(25);
        d6.setRoundTopRight(25);

        panelRound11.setBackground(new java.awt.Color(176, 62, 62));
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

        d7.setBackground(new java.awt.Color(218, 136, 136));
        d7.setRoundBottomLeft(25);
        d7.setRoundBottomRight(25);
        d7.setRoundTopLeft(25);
        d7.setRoundTopRight(25);

        panelRound12.setBackground(new java.awt.Color(176, 62, 62));
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

        usersPanel.setBackground(new java.awt.Color(245, 205, 205));
        usersPanel.setRoundTopLeft(50);

        panelRound1.setBackground(new java.awt.Color(218, 136, 136));
        panelRound1.setRoundBottomLeft(25);
        panelRound1.setRoundBottomRight(25);
        panelRound1.setRoundTopLeft(50);
        panelRound1.setRoundTopRight(25);

        imageAvatar1.setForeground(new java.awt.Color(176, 62, 62));
        imageAvatar1.setBorderSize(3);
        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nullProfile.jpg"))); // NOI18N

        usernameLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usernameLabel.setText("Example Username");

        adminBtn.setBackground(new java.awt.Color(176, 62, 62));
        adminBtn.setForeground(new java.awt.Color(255, 255, 255));
        adminBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/adminConfig.png"))); // NOI18N
        adminBtn.setText("Admin Config");
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
                        .addGap(20, 20, 20)
                        .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 14, Short.MAX_VALUE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(adminBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(adminBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        panelRound5.setBackground(new java.awt.Color(218, 136, 136));
        panelRound5.setRoundBottomLeft(25);
        panelRound5.setRoundBottomRight(25);
        panelRound5.setRoundTopLeft(25);
        panelRound5.setRoundTopRight(25);

        panelRound8.setBackground(new java.awt.Color(176, 62, 62));
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
                .addComponent(fullnameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        genderLabel.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        genderLabel.setForeground(new java.awt.Color(255, 255, 255));
        genderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        genderLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/nogender.png"))); // NOI18N

        userTypeLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        userTypeLabel.setForeground(new java.awt.Color(255, 255, 255));
        userTypeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userTypeLabel.setText("Admin");

        d2.setBackground(new java.awt.Color(245, 205, 205));
        d2.setRoundBottomLeft(25);
        d2.setRoundBottomRight(25);
        d2.setRoundTopLeft(25);
        d2.setRoundTopRight(25);

        panelRound3.setBackground(new java.awt.Color(176, 62, 62));
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

        d9.setBackground(new java.awt.Color(245, 205, 205));
        d9.setRoundBottomLeft(25);
        d9.setRoundBottomRight(25);
        d9.setRoundTopLeft(25);
        d9.setRoundTopRight(25);

        panelRound10.setBackground(new java.awt.Color(176, 62, 62));
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

        changeProfileBtn.setBackground(new java.awt.Color(176, 62, 62));
        changeProfileBtn.setForeground(new java.awt.Color(255, 255, 255));
        changeProfileBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/change.png"))); // NOI18N
        changeProfileBtn.setText("Change Profile Picture");
        changeProfileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeProfileBtnActionPerformed(evt);
            }
        });

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
                            .addComponent(d2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(changeProfileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 374, Short.MAX_VALUE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(changeProfileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

        inventoryPanel.setBackground(new java.awt.Color(245, 205, 205));
        inventoryPanel.setRoundTopLeft(50);

        inventoryScrollPane.setBorder(null);
        inventoryScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(245, 205, 205));

        panelRound15.setBackground(new java.awt.Color(218, 136, 136));
        panelRound15.setRoundBottomLeft(25);
        panelRound15.setRoundBottomRight(25);
        panelRound15.setRoundTopLeft(50);
        panelRound15.setRoundTopRight(25);

        jLabel29.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("Product");

        jTextField5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(51, 51, 51));

        jLabel30.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("Description");

        jTextField6.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(51, 51, 51));

        jLabel31.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 51, 51));
        jLabel31.setText("Quantity");

        inventoryQuantityInput.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jComboBox3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel32.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 51, 51));
        jLabel32.setText("Category");

        jSpinner4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel33.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setText("Retail Price");

        jDateChooser3.setForeground(new java.awt.Color(51, 51, 51));
        jDateChooser3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel34.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setText("Date of Purchase");

        javax.swing.GroupLayout panelRound15Layout = new javax.swing.GroupLayout(panelRound15);
        panelRound15.setLayout(panelRound15Layout);
        panelRound15Layout.setHorizontalGroup(
            panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound15Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField5)
                    .addGroup(panelRound15Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(101, 101, 101))
                    .addComponent(jTextField6)
                    .addGroup(panelRound15Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(81, 81, 81)))
                .addGap(65, 65, 65)
                .addGroup(panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound15Layout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(96, 96, 96))
                    .addComponent(inventoryQuantityInput)
                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound15Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(94, 94, 94)))
                .addGap(65, 65, 65)
                .addGroup(panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinner4)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound15Layout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(81, 81, 81))
                    .addGroup(panelRound15Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(49, 49, 49)))
                .addGap(21, 21, 21))
        );
        panelRound15Layout.setVerticalGroup(
            panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel31)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(inventoryQuantityInput, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30)
                        .addComponent(jLabel32))
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jComboBox3)
                    .addComponent(jTextField6))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        inventoryImgDisplay.setImage(new javax.swing.ImageIcon(getClass().getResource("/item_images/no_pic_item.png"))); // NOI18N
        inventoryImgDisplay.setOpaque(false);
        inventoryImgDisplay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inventoryImgDisplayMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout inventoryImgDisplayLayout = new javax.swing.GroupLayout(inventoryImgDisplay);
        inventoryImgDisplay.setLayout(inventoryImgDisplayLayout);
        inventoryImgDisplayLayout.setHorizontalGroup(
            inventoryImgDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 231, Short.MAX_VALUE)
        );
        inventoryImgDisplayLayout.setVerticalGroup(
            inventoryImgDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        inventoryTable.setBackground(new java.awt.Color(218, 136, 136));
        inventoryTable.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        inventoryTable.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        inventoryTable.setForeground(new java.awt.Color(255, 255, 255));
        inventoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Category", "Product Name", "Description", "Quantity", "Retail Price", "Date of Purchase"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        inventoryTable.setSelectionBackground(new java.awt.Color(245, 205, 205));
        inventoryTable.setSelectionForeground(new java.awt.Color(96, 0, 0));
        inventoryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inventoryTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(inventoryTable);

        jButton7.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(51, 51, 51));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        jButton7.setText("ADD");

        jButton8.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(51, 51, 51));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        jButton8.setText("Edit");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(51, 51, 51));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        jButton9.setText("DELETE");

        textFieldWithIcon3.setForeground(new java.awt.Color(51, 51, 51));
        textFieldWithIcon3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        textFieldWithIcon3.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N

        inventoryVariantPanel.setBackground(new java.awt.Color(218, 136, 136));
        inventoryVariantPanel.setRoundBottomLeft(25);
        inventoryVariantPanel.setRoundBottomRight(25);
        inventoryVariantPanel.setRoundTopLeft(25);
        inventoryVariantPanel.setRoundTopRight(25);

        addVariantBtn.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        addVariantBtn.setForeground(new java.awt.Color(51, 51, 51));
        addVariantBtn.setText("ADD VARIANT");

        toeditVariant.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        toeditVariant.setForeground(new java.awt.Color(51, 51, 51));
        toeditVariant.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        toaddVariant.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        toaddVariant.setForeground(new java.awt.Color(51, 51, 51));

        editVariantBtn.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        editVariantBtn.setForeground(new java.awt.Color(51, 51, 51));
        editVariantBtn.setText("EDIT VARIANT");
        editVariantBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editVariantBtnActionPerformed(evt);
            }
        });

        deleteVariantBtn.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        deleteVariantBtn.setForeground(new java.awt.Color(51, 51, 51));
        deleteVariantBtn.setText("DELETE VARIANT");

        jLabel35.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Selected Product: ");

        todeleteVariant.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        todeleteVariant.setForeground(new java.awt.Color(51, 51, 51));
        todeleteVariant.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout inventoryVariantPanelLayout = new javax.swing.GroupLayout(inventoryVariantPanel);
        inventoryVariantPanel.setLayout(inventoryVariantPanelLayout);
        inventoryVariantPanelLayout.setHorizontalGroup(
            inventoryVariantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inventoryVariantPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(inventoryVariantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(inventoryVariantPanelLayout.createSequentialGroup()
                        .addGroup(inventoryVariantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(toaddVariant)
                            .addComponent(addVariantBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(65, 65, 65)
                        .addGroup(inventoryVariantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(toeditVariant, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editVariantBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(65, 65, 65)
                        .addGroup(inventoryVariantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deleteVariantBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(todeleteVariant, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(21, 21, 21))
        );
        inventoryVariantPanelLayout.setVerticalGroup(
            inventoryVariantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryVariantPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addGap(8, 8, 8)
                .addGroup(inventoryVariantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toaddVariant, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toeditVariant, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(todeleteVariant, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inventoryVariantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addVariantBtn)
                    .addComponent(editVariantBtn)
                    .addComponent(deleteVariantBtn))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textFieldWithIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panelRound15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(inventoryImgDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(inventoryVariantPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(135, 135, 135))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelRound15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldWithIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(inventoryImgDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inventoryVariantPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        inventoryScrollPane.setViewportView(jPanel1);

        javax.swing.GroupLayout inventoryPanelLayout = new javax.swing.GroupLayout(inventoryPanel);
        inventoryPanel.setLayout(inventoryPanelLayout);
        inventoryPanelLayout.setHorizontalGroup(
            inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(inventoryScrollPane)
        );
        inventoryPanelLayout.setVerticalGroup(
            inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inventoryPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(inventoryScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE))
        );

        contentLayere.add(inventoryPanel, "card2");

        categoryPanel.setBackground(new java.awt.Color(245, 205, 205));
        categoryPanel.setRoundTopLeft(50);

        CategoryTable.setBackground(new java.awt.Color(218, 136, 136));
        CategoryTable.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        CategoryTable.setForeground(new java.awt.Color(255, 255, 255));
        CategoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        CategoryTable.setSelectionBackground(new java.awt.Color(245, 205, 205));
        CategoryTable.setSelectionForeground(new java.awt.Color(96, 0, 0));
        jScrollPane2.setViewportView(CategoryTable);

        panelRound14.setBackground(new java.awt.Color(218, 136, 136));
        panelRound14.setRoundBottomLeft(25);
        panelRound14.setRoundBottomRight(25);
        panelRound14.setRoundTopLeft(50);
        panelRound14.setRoundTopRight(25);

        jLabel36.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("Category");

        jTextField7.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jTextField7.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout panelRound14Layout = new javax.swing.GroupLayout(panelRound14);
        panelRound14.setLayout(panelRound14Layout);
        panelRound14Layout.setHorizontalGroup(
            panelRound14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound14Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelRound14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        panelRound14Layout.setVerticalGroup(
            panelRound14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton10.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(51, 51, 51));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        jButton10.setText("ADD");

        jButton11.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButton11.setForeground(new java.awt.Color(51, 51, 51));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        jButton11.setText("Edit");

        jButton12.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButton12.setForeground(new java.awt.Color(51, 51, 51));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        jButton12.setText("DELETE");

        textFieldWithIcon4.setForeground(new java.awt.Color(51, 51, 51));
        textFieldWithIcon4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        textFieldWithIcon4.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N

        javax.swing.GroupLayout categoryPanelLayout = new javax.swing.GroupLayout(categoryPanel);
        categoryPanel.setLayout(categoryPanelLayout);
        categoryPanelLayout.setHorizontalGroup(
            categoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(categoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(categoryPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(categoryPanelLayout.createSequentialGroup()
                        .addComponent(panelRound14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textFieldWithIcon4, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );
        categoryPanelLayout.setVerticalGroup(
            categoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, categoryPanelLayout.createSequentialGroup()
                .addGroup(categoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(categoryPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(panelRound14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(categoryPanelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(categoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldWithIcon4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addContainerGap())
        );

        contentLayere.add(categoryPanel, "card2");

        salesPanel.setBackground(new java.awt.Color(245, 205, 205));
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

        returnItemPanel.setBackground(new java.awt.Color(245, 205, 205));
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

        priceListPanel.setBackground(new java.awt.Color(245, 205, 205));
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

        reportPanel.setBackground(new java.awt.Color(245, 205, 205));
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
                    .addComponent(menuLayere, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
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

        panelRound13.setBackground(new java.awt.Color(245, 205, 205));
        panelRound13.setRoundTopLeft(25);
        panelRound13.setRoundTopRight(25);

        usersTable.setBackground(new java.awt.Color(218, 136, 136));
        usersTable.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        usersTable.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        usersTable.setForeground(new java.awt.Color(51, 51, 51));
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
        usersTable.setSelectionBackground(new java.awt.Color(245, 205, 205));
        usersTable.setSelectionForeground(new java.awt.Color(96, 0, 0));
        usersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usersTableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                usersTableMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(usersTable);

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Firstname");

        adminFnameInput.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        adminFnameInput.setForeground(new java.awt.Color(51, 51, 51));
        adminFnameInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                adminFnameInputKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Lastname");

        adminLnameInput.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        adminLnameInput.setForeground(new java.awt.Color(51, 51, 51));
        adminLnameInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                adminLnameInputKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Username");

        adminUnameInput.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        adminUnameInput.setForeground(new java.awt.Color(51, 51, 51));
        adminUnameInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                adminUnameInputKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Password");

        adminPasswordInput.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        adminPasswordInput.setForeground(new java.awt.Color(51, 51, 51));
        adminPasswordInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                adminPasswordInputKeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Confirm Password");

        adminPasswordInput1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        adminPasswordInput1.setForeground(new java.awt.Color(51, 51, 51));

        adminBdayInput.setForeground(new java.awt.Color(51, 51, 51));
        adminBdayInput.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Birth Date");

        jLabel15.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Gender");

        jLabel16.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("User Type");

        adminGenderInput.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        adminGenderInput.setForeground(new java.awt.Color(51, 51, 51));
        adminGenderInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        adminUserTypeInput.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        adminUserTypeInput.setForeground(new java.awt.Color(51, 51, 51));
        adminUserTypeInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Seller" }));

        adminGetImageBtn.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        adminGetImageBtn.setText("Select Image");
        adminGetImageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminGetImageBtnActionPerformed(evt);
            }
        });

        addUserBtn.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        addUserBtn.setText("ADD USER");
        addUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserBtnActionPerformed(evt);
            }
        });

        adminShowC.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        adminShowC.setForeground(new java.awt.Color(51, 51, 51));
        adminShowC.setText("Show");
        adminShowC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminShowCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound13Layout = new javax.swing.GroupLayout(panelRound13);
        panelRound13.setLayout(panelRound13Layout);
        panelRound13Layout.setHorizontalGroup(
            panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound13Layout.createSequentialGroup()
                .addGroup(panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelRound13Layout.createSequentialGroup()
                                .addComponent(adminGenderInput, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(adminUserTypeInput, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel14)
                            .addGroup(panelRound13Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(57, 57, 57)
                                .addComponent(jLabel16))
                            .addComponent(adminLnameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adminFnameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(adminPasswordInput)
                            .addComponent(adminUnameInput)
                            .addGroup(panelRound13Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(adminShowC)
                                .addGap(9, 9, 9))))
                    .addGroup(panelRound13Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(adminGetImageBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addUserBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(adminPasswordInput1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adminBdayInput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound13Layout.setVerticalGroup(
            panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound13Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(adminFnameInput, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(adminLnameInput, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(adminUnameInput, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(adminPasswordInput, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(adminShowC, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(adminPasswordInput1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(adminBdayInput, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addGap(9, 9, 9)
                        .addGroup(panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound13Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                                .addGap(39, 39, 39))
                            .addGroup(panelRound13Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(adminGenderInput)
                                    .addComponent(adminUserTypeInput))
                                .addGap(9, 9, 9)))
                        .addComponent(adminGetImageBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addUserBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        jTabbedPane1.addTab("User Config", panelRound13);

        panelRound9.setBackground(new java.awt.Color(245, 205, 205));
        panelRound9.setRoundTopLeft(25);
        panelRound9.setRoundTopRight(25);

        testbtn1.setText("Test Btn1");
        testbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testbtn1ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(192, 77, 77));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelRound9Layout = new javax.swing.GroupLayout(panelRound9);
        panelRound9.setLayout(panelRound9Layout);
        panelRound9Layout.setHorizontalGroup(
            panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound9Layout.createSequentialGroup()
                .addGroup(panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound9Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(testbtn1))
                    .addGroup(panelRound9Layout.createSequentialGroup()
                        .addGap(398, 398, 398)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(545, Short.MAX_VALUE))
        );
        panelRound9Layout.setVerticalGroup(
            panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(testbtn1)
                .addGap(88, 88, 88)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(299, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Test Panel", panelRound9);

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
        Utilities.switchPanel(contentLayere,dashboardPanel);
    }//GEN-LAST:event_dashboardBtnActionPerformed

    private void usersBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersBtnActionPerformed
        Utilities.switchPanel(contentLayere,usersPanel);
    }//GEN-LAST:event_usersBtnActionPerformed

    private void inventoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventoryBtnActionPerformed
        Utilities.switchPanel(contentLayere,inventoryPanel);
    }//GEN-LAST:event_inventoryBtnActionPerformed

    private void categoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryBtnActionPerformed
        Utilities.switchPanel(contentLayere,categoryPanel);
    }//GEN-LAST:event_categoryBtnActionPerformed

    private void salesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesBtnActionPerformed
        Utilities.switchPanel(contentLayere,salesPanel);
    }//GEN-LAST:event_salesBtnActionPerformed

    private void returnItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnItemBtnActionPerformed
        Utilities.switchPanel(contentLayere,returnItemPanel);
    }//GEN-LAST:event_returnItemBtnActionPerformed

    private void priceListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceListBtnActionPerformed
        Utilities.switchPanel(contentLayere,priceListPanel);
    }//GEN-LAST:event_priceListBtnActionPerformed

    private void reportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportBtnActionPerformed
        Utilities.switchPanel(contentLayere,reportPanel);
    }//GEN-LAST:event_reportBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        logoutMethod();
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void dashboardBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardBtn1ActionPerformed
        Utilities.switchPanel(contentLayere,dashboardPanel);
    }//GEN-LAST:event_dashboardBtn1ActionPerformed

    private void usersBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersBtn1ActionPerformed
        Utilities.switchPanel(contentLayere,usersPanel);
    }//GEN-LAST:event_usersBtn1ActionPerformed

    private void salesBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesBtn1ActionPerformed
        Utilities.switchPanel(contentLayere,salesPanel);
    }//GEN-LAST:event_salesBtn1ActionPerformed

    private void returnItemBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnItemBtn1ActionPerformed
        Utilities.switchPanel(contentLayere,returnItemPanel);
    }//GEN-LAST:event_returnItemBtn1ActionPerformed

    private void priceListBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceListBtn1ActionPerformed
        Utilities.switchPanel(contentLayere,priceListPanel);
    }//GEN-LAST:event_priceListBtn1ActionPerformed

    private void logoutBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtn1ActionPerformed
        logoutMethod();
    }//GEN-LAST:event_logoutBtn1ActionPerformed

    private void adminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminBtnActionPerformed
        Utilities.switchPanel(layere1,adminPanel);
    }//GEN-LAST:event_adminBtnActionPerformed

    private void backLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backLabelMouseClicked
        Utilities.switchPanel(layere1,panel1);
    }//GEN-LAST:event_backLabelMouseClicked

    private void usersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersTableMouseClicked
        try{
            int get_selectedColumn = usersTable.getSelectedColumn();
            Object up_get_id;
            String new_val;

            switch(get_selectedColumn){
                case 0:
                    if (evt.getClickCount() == 2){
                        String userInput = (String) JOptionPane.showInputDialog(
                                this,
                                "",
                                "",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                new String[]{"Delete User","Generate User QR Code"},
                                "Delete User");        
                        up_get_id = get_TableRecordID(usersTable);
                            String get_fName = usersTable.getValueAt(usersTable.getSelectedRow(), 1).toString();

                        DefaultTableModel to_delete = (DefaultTableModel) usersTable.getModel();

                        if (userInput.equals("Delete User")) {
                            try {
                                int getSelectedRow = usersTable.getSelectedRow();
                                userManagement.DeleteUser(up_get_id);
                                String get_image = usersTable.getValueAt(getSelectedRow, 7).toString();
                                to_delete.removeRow(getSelectedRow);
                                if(!get_image.equals("nullProfile.jpg")){
                                    deleteImage(get_image);
                                }
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }else if(userInput.equals("Generate User QR Code")){
                            Utilities.generateQRCodeImage(up_get_id.toString(), get_fName.replaceAll(" ", "_")+"_QRCode.png",350, 350,this);
                        }
                    }
                    break;
                case 1:
                    up_get_id = get_TableRecordID(usersTable);
                    if(evt.getClickCount() == 2){
                        do{
                            new_val = JOptionPane.showInputDialog(this, "Enter new firstname for user "+up_get_id+":", "Change Firstname", JOptionPane.QUESTION_MESSAGE).toLowerCase();
                            if(Utilities.containsNumbers(new_val)){
                                JOptionPane.showMessageDialog(this, "First name can only contain letters", "Invalid Firstname", JOptionPane.ERROR_MESSAGE);
                                updatableUserData();
                            }
                        }while(Utilities.containsNumbers(new_val));

                        if(new_val != null && up_get_id != null){
                            String fnameOld = usersTable.getValueAt(usersTable.getSelectedRow(), 1).toString();
                            if(changeStringData(new_val,up_get_id,1)){
                                purchaseManagement.updateSellerName(fnameOld, new_val,7);
                                JOptionPane.showMessageDialog(this, "Change Successfully", "Firstname", JOptionPane.INFORMATION_MESSAGE);
                                updatableUserData();
                            }
                        }
                    }
                    break;
                case 2:
                    up_get_id = get_TableRecordID(usersTable);
                    if(evt.getClickCount() == 2){
                        do{
                            new_val = JOptionPane.showInputDialog(this, "Enter new lastname for user "+up_get_id+":", "Change Lastname", JOptionPane.QUESTION_MESSAGE);
                            if(Utilities.containsNumbers(new_val)){
                                JOptionPane.showMessageDialog(this, "Last name can only contain letters", "Invalid Lastname", JOptionPane.ERROR_MESSAGE);
                            }
                        }while(Utilities.containsNumbers(new_val));

                        if(new_val != null && up_get_id != null){
                            String lnameOld = usersTable.getValueAt(usersTable.getSelectedRow(), 2).toString();
                            if(changeStringData(new_val,up_get_id,2)){
                                purchaseManagement.updateSellerName(lnameOld, new_val,8);
                                JOptionPane.showMessageDialog(this, "Change Successfully", "Lastname", JOptionPane.INFORMATION_MESSAGE);
                                updatableUserData();
                            }
                        }
                    }
                    break;
                case 3:
                    up_get_id = get_TableRecordID(usersTable);
                    if(evt.getClickCount() == 2){
                        do{
                            new_val = JOptionPane.showInputDialog(this, "Enter new username for user "+up_get_id+":", "Change Username", JOptionPane.QUESTION_MESSAGE);
                            if(!Utilities.validateUsername(new_val)){
                                JOptionPane.showMessageDialog(this, "Username must follow the pattern: ^[a-zA-Z]+@[0-9]+$", "Invalid Username", JOptionPane.ERROR_MESSAGE);
                            }
                        }while(!Utilities.validateUsername(new_val));

                        if(new_val != null && up_get_id != null){
                            if(changeStringData(new_val,up_get_id,3)){
                                    JOptionPane.showMessageDialog(this, "Change Successfully", "Username", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                    break;
                case 4:
                    up_get_id = get_TableRecordID(usersTable);
                    if(evt.getClickCount() == 2){
                        do{
                            new_val = JOptionPane.showInputDialog(this, "Enter new password for user "+up_get_id+":", "Change Password", JOptionPane.QUESTION_MESSAGE);
                        }while(!Utilities.validatePassword(new_val, this));

                        String confirmPass = null;

                        if(new_val != null){
                            do{
                                confirmPass = JOptionPane.showInputDialog(this, "Confirm new password for user "+up_get_id+":", "Change Password", JOptionPane.QUESTION_MESSAGE);
                            }while(!Utilities.validatePassword(confirmPass, this));
                        }

                        if(new_val != null && confirmPass != null && up_get_id != null){
                            if(confirmPass.equals(new_val)){
                                if(changeStringData(confirmPass,up_get_id,4)){
                                    JOptionPane.showMessageDialog(this, "Change Successfully", "Password", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "Password mismatch", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    break;
                case 5:
                    up_get_id = get_TableRecordID(usersTable);
                    if(evt.getClickCount() == 2){
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
                            if (selectedDate != null && up_get_id != null) {
                                SimpleDateFormat dateFormat = new SimpleDateFormat(Helper.dateFormat);
                                if(changeStringData(dateFormat.format(selectedDate),up_get_id,5)){
                                    JOptionPane.showMessageDialog(this, "Change Successfully", "Birth date", JOptionPane.INFORMATION_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "No date selected");
                            }
                        }
                    }
                    break;
                case 6:
                    up_get_id = get_TableRecordID(usersTable);
                    if(evt.getClickCount() == 2){
                        new_val = (String) JOptionPane.showInputDialog(
                            this,
                            "Choose new gender for user " + up_get_id + ":",
                            "Change Gender",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            Helper.listOfGender,
                            Helper.listOfGender[0]);

                        if(new_val != null && up_get_id != null){
                            if(changeStringData(new_val,up_get_id,6)){
                                JOptionPane.showMessageDialog(this, "Change Successfully", "Gender", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                    break;
                case 8:
                    up_get_id = get_TableRecordID(usersTable);
                    new_val = (String) JOptionPane.showInputDialog(
                        this,
                        "Select new user type for user " + up_get_id + ":",
                        "Change User type",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        userManagement.listOfUserType,
                        userManagement.listOfUserType[0]);

                    if(new_val != null && up_get_id != null){
                        if(changeStringData(new_val,up_get_id,8)){
                            JOptionPane.showMessageDialog(this, "Change Successfully", "UserType", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    break;
                default:
                    break;
            }
            AppManagement.tableData(usersTable,userManagement.table,userManagement.columns);
        } catch(NullPointerException e){} 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error Code#: "+e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_usersTableMouseClicked

    private void usersTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersTableMouseEntered
        int column = usersTable.columnAtPoint(evt.getPoint());

        if (column == 0) {
            usersTable.setToolTipText("Double click to delete user or generate new QR Code.");
        }
    }//GEN-LAST:event_usersTableMouseEntered
    private File get_imagefile;
    
    private void clearAddUserInputs(){
        adminFnameInput.setText("");
        adminLnameInput.setText("");
        adminUnameInput.setText("");
        adminPasswordInput.setText("");
        adminPasswordInput1.setText("");
        adminBdayInput.setDate(null);
    }
    
    private void addUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserBtnActionPerformed
        int response = JOptionPane.showConfirmDialog(this, "Firstname: "+adminFnameInput.getText()+
                                                                       "\nLastname: "+adminLnameInput.getText()+
                                                                       "\nUsername: "+adminUnameInput.getText()+
                                                                       "\nPassword: "+Utilities.getCurrentDate(adminBdayInput)+
                                                                       "\nBirth Date: "+adminBdayInput.getDate()+ 
                                                                       "\nGender: "+adminGenderInput.getSelectedItem()+ 
                                                                       "\nUser Type: "+adminUserTypeInput.getSelectedItem(),
                "Confirmation", JOptionPane.YES_NO_OPTION);

        if(response == JOptionPane.YES_OPTION){
            String get_fname = adminFnameInput.getText().toLowerCase();
            String get_lname = adminLnameInput.getText().toLowerCase();
            String get_uname = adminUnameInput.getText().toLowerCase();
            String get_password = new String(adminPasswordInput.getPassword());
            String get_cpassword = new String(adminPasswordInput1.getPassword());
            Date get_bday = adminBdayInput.getDate();
            String get_bdate = Utilities.get_AddedDate(adminBdayInput);
            String get_gender = adminGenderInput.getSelectedItem().toString();
            String get_usertype = adminUserTypeInput.getSelectedItem().toString();

            String cur_date = Utilities.getCurrentDate(Helper.dateFormat);
            String image_name = null;

            if(get_imagefile != null){
                image_name = cur_date+"_"+get_fname;
                image_name = checkDuplicateImageName(image_name+"."+FileManagement.IMAGE_EXTENSIONS[1]);
            }else{
                image_name = "nullProfile";
            }

            try{
                String generate_id = null;
                if(get_usertype.equals(userManagement.listOfUserType[0])){
                    generate_id = userManagement.userIDGenerator(userManagement.listOfUserType[0]);
                }else{
                    generate_id = userManagement.userIDGenerator(userManagement.listOfUserType[1]);
                }

                if(get_password.equals(get_cpassword)){
                    if(Utilities.validateAge(adminBdayInput)){
                        if(Utilities.validatePassword(get_cpassword, this)){
                            if(Utilities.validateUsername(get_uname)){
                                userManagement.addUser(generate_id, get_fname, get_lname, get_uname, get_cpassword, get_bdate, get_gender, image_name+"."+FileManagement.IMAGE_EXTENSIONS[1], get_usertype);
                                if(get_imagefile != null){
                                    insert_Image(get_imagefile,image_name,this,0);
                                }
                                AppManagement.tableData(usersTable,userManagement.table,userManagement.columns);
                                clearAddUserInputs();
                                adminGetImageBtn.setEnabled(true);
                                
                                Utilities.generateQRCodeImage(generate_id, get_fname.replaceAll(" ", "_")+"QRCode.png",350, 350,this);
                            }else{
                                JOptionPane.showMessageDialog(this, "Username must follow the pattern: ^[a-zA-Z]+@[0-9]+$", "Invalid Username", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(this, "Age must be a legal age.", "Invalid Age", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "The passwords do not match.", "Password Error", JOptionPane.ERROR_MESSAGE);
                }
            }catch (SQLException e) {
                if (e.getErrorCode() == 1062) { 
                    JOptionPane.showMessageDialog(this, "User already exists.", "Duplicate Entry", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Error Code#: "+e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_addUserBtnActionPerformed

    private void adminFnameInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adminFnameInputKeyReleased
        final String value = adminFnameInput.getText();
        if(Utilities.containsNumbers(value)){
            JOptionPane.showMessageDialog(this, "First name can only contain letters", "Invalid Firstname", JOptionPane.ERROR_MESSAGE);
            for(int i = 0; i < value.length(); i++){
                char cur_char = value.charAt(i);
                if(Character.isDigit(cur_char)){
                    adminFnameInput.setText(value.replaceAll("\\d", ""));
                }
            }
        }
    }//GEN-LAST:event_adminFnameInputKeyReleased

    private void adminLnameInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adminLnameInputKeyReleased
        final String value = adminLnameInput.getText();
        if(Utilities.containsNumbers(value)){
            JOptionPane.showMessageDialog(this, "Last name can only contain letters", "Invalid Firstname", JOptionPane.ERROR_MESSAGE);
            for(int i = 0; i < value.length(); i++){
                char cur_char = value.charAt(i);
                if(Character.isDigit(cur_char)){
                    adminLnameInput.setText(value.replaceAll("\\d", ""));
                }
            }
        }
    }//GEN-LAST:event_adminLnameInputKeyReleased

    private void adminUnameInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adminUnameInputKeyReleased

    }//GEN-LAST:event_adminUnameInputKeyReleased

    private void adminPasswordInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adminPasswordInputKeyReleased

    }//GEN-LAST:event_adminPasswordInputKeyReleased
    
    private void adminGetImageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminGetImageBtnActionPerformed
        get_imagefile = getFile_Image(FileManagement.IMAGE_EXTENSIONS[1]);
        
        if(get_imagefile != null){
            ImageIcon icon = new ImageIcon(get_imagefile.getAbsolutePath());

            int originalWidth = icon.getIconWidth();
            int originalHeight = icon.getIconHeight();
            double aspectRatio = (double) originalWidth / originalHeight;

            int desiredWidth = 100; 
            int desiredHeight = (int) (desiredWidth / aspectRatio);

            Image image = icon.getImage().getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(image);

            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Do you want to use this image?",
                    "Image Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    resizedIcon
            );
            
            if(result == JOptionPane.NO_OPTION){
               get_imagefile = null; 
            }else{
               adminGetImageBtn.setEnabled(false);
            }
        }
    }//GEN-LAST:event_adminGetImageBtnActionPerformed
    
    SpinnerDateModel dateModel = new SpinnerDateModel();
    JSpinner inventorySpinner = new JSpinner(dateModel);
                        
    private void inventoryTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventoryTableMouseClicked
        int getSelectedRow = inventoryTable.getSelectedRow();
        int getSelectedColumn = inventoryTable.getSelectedColumn();
        
        inventorySpinner.setEditor(new JSpinner.DateEditor(inventorySpinner, Helper.dateFormat));
        int getItem_id = Integer.parseInt(inventoryTable.getValueAt(inventoryTable.getSelectedRow(), 0).toString());
        String getItem = inventoryTable.getValueAt(inventoryTable.getSelectedRow(), 2).toString();
        
        try {
            setImageToPictureBox(inventoryImgDisplay,inventoryManagement.getItemImage(getItem_id));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error Code#: "+e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        
        if(getSelectedColumn == 6){
            String message = "Please enter a date in the format "+Helper.dateFormat;
            int option = JOptionPane.showOptionDialog(
                                this,
                                new Object[]{message, inventorySpinner},
                                "Select new date",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                new Object[]{"OK", "Cancel"},
                                "OK");
            
            if(option == JOptionPane.YES_OPTION){
                String get_dateVal = inventorySpinner.getValue().toString();
                System.out.println(get_dateVal);
            }
        }
        jLabel35.setText("Selected Product: "+getItem);
    }//GEN-LAST:event_inventoryTableMouseClicked

    private void editVariantBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editVariantBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editVariantBtnActionPerformed

    private void testbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testbtn1ActionPerformed
        File get_testFile = getFile_Image(FileManagement.IMAGE_EXTENSIONS[0]);
       
        System.out.println(get_testFile.getName());
    }//GEN-LAST:event_testbtn1ActionPerformed

    private void adminShowCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminShowCActionPerformed
        if(adminShowC.isSelected()){
            adminPasswordInput.setEchoChar((char) 0);
            adminPasswordInput1.setEchoChar((char) 0);
        }else{
            adminPasswordInput.setEchoChar('*');
            adminPasswordInput1.setEchoChar('*');
        }
    }//GEN-LAST:event_adminShowCActionPerformed

    private void inventoryImgDisplayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventoryImgDisplayMouseClicked
        if(evt.isControlDown()){
            if(evt.getClickCount() == 2){
                openImageDesktop(inventoryImgDisplay.getImage());
            }
        }
    }//GEN-LAST:event_inventoryImgDisplayMouseClicked

    private void changeProfileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeProfileBtnActionPerformed
        try {
            String get_id = AppManagement.getCurrentUser(this);
            String get_image = userManagement.getImagePath(get_id);   
            File get_new_image = getFile_Image(FileManagement.IMAGE_EXTENSIONS[1]);

            if(get_new_image != null){
                int userInput = JOptionPane.showConfirmDialog(
                    this,
                                "Are you sure you want to change your profile picture?",
                                "Change Confirmation",
                                JOptionPane.YES_NO_OPTION
                            );      
                if(!get_new_image.getName().equals("nullProfile.jpg")){
                    if(!get_image.equals("nullProfile.jpg")){
                        insert_Image(get_new_image,get_image.replaceAll(".jpg", ""),this,0);

                        ImageIcon temp_profile = new ImageIcon(get_new_image.getAbsolutePath());
                        imageAvatar1.setIcon(temp_profile);
                        imageAvatar1.repaint();
                    }else{
                        String get_fname = userManagement.getFName(get_id);
                        String cur_date = Utilities.getCurrentDate(Helper.dateFormat);

                        String image_name = cur_date+"_"+get_fname;
                        image_name = checkDuplicateImageName(image_name+"."+FileManagement.IMAGE_EXTENSIONS[1]);

                        userManagement.updateStringData(image_name+"."+FileManagement.IMAGE_EXTENSIONS[1],get_id, 7);

                        insert_Image(get_new_image,image_name,this,0);

                        ImageIcon temp_profile = new ImageIcon(get_new_image.getAbsolutePath());
                        imageAvatar1.setIcon(temp_profile);
                        imageAvatar1.repaint();
                    }
                }else{
                    userManagement.updateStringData("nullProfile.jpg",get_id, 7);
                    ImageIcon temp_profile = new ImageIcon(get_new_image.getAbsolutePath());
                    imageAvatar1.setIcon(temp_profile);
                    imageAvatar1.repaint();
                    
                    if(!get_image.equals("nullProfile.jpg")){
                        deleteImage(get_image);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error Code#: "+e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_changeProfileBtnActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int getSelectedRow = inventoryTable.getSelectedRow();
        int getSelectedColumn = inventoryTable.getSelectedColumn();
        
        String get_val = null;
        
        if(getSelectedColumn != 1){
            get_val = Utilities.getSpinnerFromTable(inventoryTable);
        }else{
            get_val = Utilities.getComboxFromTable(inventoryTable, 1);
        }
        
        System.out.println("Value at "+get_val);
    }//GEN-LAST:event_jButton8ActionPerformed
    
    private boolean changeStringData(String newVal, Object id,int column_index){
        try{
            userManagement.updateStringData(newVal, id,column_index);
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error Code#: "+e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CategoryTable;
    private javax.swing.JButton addUserBtn;
    private javax.swing.JButton addVariantBtn;
    private com.toedter.calendar.JDateChooser adminBdayInput;
    private customComponents.ButtonRound adminBtn;
    private javax.swing.JTextField adminFnameInput;
    private javax.swing.JComboBox<String> adminGenderInput;
    private javax.swing.JButton adminGetImageBtn;
    private javax.swing.JTextField adminLnameInput;
    private customComponents.PanelRound adminMenu;
    private javax.swing.JPanel adminPanel;
    private javax.swing.JPasswordField adminPasswordInput;
    private javax.swing.JPasswordField adminPasswordInput1;
    private javax.swing.JCheckBox adminShowC;
    private javax.swing.JTextField adminUnameInput;
    private javax.swing.JComboBox<String> adminUserTypeInput;
    private javax.swing.JLabel backLabel;
    private customComponents.ButtonRound categoryBtn;
    private customComponents.PanelRound categoryPanel;
    private customComponents.ButtonRound changeProfileBtn;
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
    private javax.swing.JButton deleteVariantBtn;
    private javax.swing.JButton editVariantBtn;
    private javax.swing.JLabel fullnameLabel;
    private javax.swing.JLabel genderLabel;
    public static customComponents.ImageAvatar imageAvatar1;
    private customComponents.ButtonRound inventoryBtn;
    private customComponents.PictureBox inventoryImgDisplay;
    private customComponents.PanelRound inventoryPanel;
    private javax.swing.JSpinner inventoryQuantityInput;
    private javax.swing.JScrollPane inventoryScrollPane;
    private javax.swing.JTable inventoryTable;
    private customComponents.PanelRound inventoryVariantPanel;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox3;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JLayeredPane layere1;
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
    private customComponents.PanelRound panelRound2;
    private customComponents.PanelRound panelRound3;
    private customComponents.PanelRound panelRound4;
    private customComponents.PanelRound panelRound5;
    private customComponents.PanelRound panelRound6;
    private customComponents.PanelRound panelRound7;
    private customComponents.PanelRound panelRound8;
    private customComponents.PanelRound panelRound9;
    private customComponents.PictureBox pictureBox1;
    private customComponents.PictureBox pictureBox3;
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
    private javax.swing.JButton testbtn1;
    private customComponents.TextFieldWithIcon textFieldWithIcon3;
    private customComponents.TextFieldWithIcon textFieldWithIcon4;
    private javax.swing.JLabel title;
    private javax.swing.JTextField toaddVariant;
    private javax.swing.JLabel todaysalesLabel;
    private javax.swing.JComboBox<String> todeleteVariant;
    private javax.swing.JComboBox<String> toeditVariant;
    private javax.swing.JLabel totalProductsLabel;
    private javax.swing.JLabel totalSalesLabel;
    private javax.swing.JLabel userSoldItemLabel;
    private javax.swing.JLabel userTotalSalesLabel;
    private javax.swing.JLabel userTypeLabel;
    private javax.swing.JLabel usernameLabel;
    private customComponents.ButtonRound usersBtn;
    private customComponents.ButtonRound usersBtn1;
    private customComponents.PanelRound usersPanel;
    private javax.swing.JTable usersTable;
    // End of variables declaration//GEN-END:variables

}
