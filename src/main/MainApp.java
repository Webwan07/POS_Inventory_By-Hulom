package main;
import assets.*;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

@Author("Josuan Leonardo Hulom")
public final class MainApp extends javax.swing.JFrame {

    public MainApp() {
        Image appIcon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo/appLogo.png"));
        this.setIconImage(appIcon);
        
        initComponents();
        HoverBtn(true);
        
        if (this.getExtendedState() == this.MAXIMIZED_BOTH) {
            this.setExtendedState(this.NORMAL);
        } else {
            this.setExtendedState(this.MAXIMIZED_BOTH);
        }
    }
    
    public void HoverBtn(boolean check){
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
    
    public static void switchPanel(JLayeredPane layered, JPanel panel){
        layered.removeAll();
        layered.add(panel);
        layered.repaint();
        layered.revalidate();         
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
        notAdminMenu = new customComponents.PanelRound();
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
        jLabel10 = new javax.swing.JLabel();
        panelRound5 = new customComponents.PanelRound();
        panelRound8 = new customComponents.PanelRound();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        changeOption = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        inventoryPanel = new customComponents.PanelRound();
        categoryPanel = new customComponents.PanelRound();
        salesPanel = new customComponents.PanelRound();
        returnItemPanel = new customComponents.PanelRound();
        priceListPanel = new customComponents.PanelRound();
        reportPanel = new customComponents.PanelRound();
        adminPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        menuLayere.add(adminMenu, "card2");

        notAdminMenu.setBackground(new java.awt.Color(79, 70, 229));
        notAdminMenu.setRoundTopRight(50);

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

        javax.swing.GroupLayout notAdminMenuLayout = new javax.swing.GroupLayout(notAdminMenu);
        notAdminMenu.setLayout(notAdminMenuLayout);
        notAdminMenuLayout.setHorizontalGroup(
            notAdminMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notAdminMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(notAdminMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dashboardBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(usersBtn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(salesBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(returnItemBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(priceListBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoutBtn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        notAdminMenuLayout.setVerticalGroup(
            notAdminMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notAdminMenuLayout.createSequentialGroup()
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 265, Short.MAX_VALUE)
                .addComponent(logoutBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        menuLayere.add(notAdminMenu, "card2");

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
                .addContainerGap(173, Short.MAX_VALUE))
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
                .addContainerGap(173, Short.MAX_VALUE))
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
                .addContainerGap(172, Short.MAX_VALUE))
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
                .addContainerGap(172, Short.MAX_VALUE))
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
                .addContainerGap(172, Short.MAX_VALUE))
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

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Example Username");

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(323, Short.MAX_VALUE))
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

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Example Name");

        javax.swing.GroupLayout panelRound8Layout = new javax.swing.GroupLayout(panelRound8);
        panelRound8.setLayout(panelRound8Layout);
        panelRound8Layout.setHorizontalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound8Layout.setVerticalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound8Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/nogender.png"))); // NOI18N

        changeOption.setBackground(new java.awt.Color(99, 102, 241));
        changeOption.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        changeOption.setForeground(new java.awt.Color(255, 255, 255));
        changeOption.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Username", "Password", "Firstname", "Lastname" }));
        changeOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeOptionActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Change");

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound5Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(changeOption, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelRound8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRound8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changeOption, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
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
            .addGap(0, 543, Short.MAX_VALUE)
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
            .addGap(0, 543, Short.MAX_VALUE)
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
            .addGap(0, 543, Short.MAX_VALUE)
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
            .addGap(0, 543, Short.MAX_VALUE)
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
            .addGap(0, 543, Short.MAX_VALUE)
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
            .addGap(0, 543, Short.MAX_VALUE)
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

        javax.swing.GroupLayout adminPanelLayout = new javax.swing.GroupLayout(adminPanel);
        adminPanel.setLayout(adminPanelLayout);
        adminPanelLayout.setHorizontalGroup(
            adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1054, Short.MAX_VALUE)
        );
        adminPanelLayout.setVerticalGroup(
            adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 584, Short.MAX_VALUE)
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
        // TODO add your handling code here:
    }//GEN-LAST:event_inventoryBtnActionPerformed

    private void categoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryBtnActionPerformed

    private void salesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salesBtnActionPerformed

    private void returnItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnItemBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_returnItemBtnActionPerformed

    private void priceListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceListBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceListBtnActionPerformed

    private void reportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reportBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void dashboardBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dashboardBtn1ActionPerformed

    private void usersBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usersBtn1ActionPerformed

    private void salesBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salesBtn1ActionPerformed

    private void returnItemBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnItemBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_returnItemBtn1ActionPerformed

    private void priceListBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceListBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceListBtn1ActionPerformed

    private void logoutBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logoutBtn1ActionPerformed
    
    
    private void changeOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeOptionActionPerformed
        String selectedOption = changeOption.getSelectedItem().toString();

        switch (selectedOption) {
            case "Username":
                System.out.println("Username changed");
                break;
            case "Password":
                System.out.println("Password changed");
                break;
            case "Firstname":
                System.out.println("Firstname changed");
                break;
            case "Lastname":
                System.out.println("Lastname changed");
                break;
            default:
                break;
        }
    }//GEN-LAST:event_changeOptionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private customComponents.PanelRound adminMenu;
    private javax.swing.JPanel adminPanel;
    private customComponents.ButtonRound categoryBtn;
    private customComponents.PanelRound categoryPanel;
    private javax.swing.JComboBox<String> changeOption;
    private javax.swing.JLayeredPane contentLayere;
    private customComponents.PanelRound d1;
    private customComponents.PanelRound d2;
    private customComponents.PanelRound d3;
    private customComponents.PanelRound d4;
    private customComponents.PanelRound d5;
    private customComponents.PanelRound d6;
    private customComponents.PanelRound d7;
    private customComponents.ButtonRound dashboardBtn;
    private customComponents.ButtonRound dashboardBtn1;
    private customComponents.PanelRound dashboardPanel;
    private customComponents.ImageAvatar imageAvatar1;
    private customComponents.ButtonRound inventoryBtn;
    private customComponents.PanelRound inventoryPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane layere1;
    private customComponents.ButtonRound logoutBtn;
    private customComponents.ButtonRound logoutBtn1;
    private javax.swing.JLayeredPane menuLayere;
    private customComponents.PanelRound notAdminMenu;
    private javax.swing.JLabel outStockLabel;
    private javax.swing.JPanel panel1;
    private customComponents.PanelRound panelRound1;
    private customComponents.PanelRound panelRound11;
    private customComponents.PanelRound panelRound12;
    private customComponents.PanelRound panelRound2;
    private customComponents.PanelRound panelRound3;
    private customComponents.PanelRound panelRound4;
    private customComponents.PanelRound panelRound5;
    private customComponents.PanelRound panelRound6;
    private customComponents.PanelRound panelRound7;
    private customComponents.PanelRound panelRound8;
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
    private javax.swing.JLabel soldOldLabel;
    private javax.swing.JLabel soldTotayLabel;
    private javax.swing.JLabel title;
    private javax.swing.JLabel todaysalesLabel;
    private javax.swing.JLabel totalProductsLabel;
    private javax.swing.JLabel totalSalesLabel;
    private customComponents.ButtonRound usersBtn;
    private customComponents.ButtonRound usersBtn1;
    private customComponents.PanelRound usersPanel;
    // End of variables declaration//GEN-END:variables
}
