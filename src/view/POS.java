package view;

import DAO.AreaImplements;
import DAO.CategoryImplements;
import DAO.CoffeeTableImplements;
import DAO.CustomerImplements;
import DAO.OrderCoffeeImplements;
import DAO.OrderDetailImplements;
import DAO.ProductImplements;
import DAO.TableProductImplements;
import DAO.UserImplements;
import Helpers.DialogHelpers;
import Helpers.UserHelpers;
import data.Area;
import data.Category;
import data.CoffeeTable;
import data.Customer;
import data.OrderCoffee;
import data.OrderDetail;
import data.Product;
import data.TableProduct;
import data.UserAccount;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.jfree.data.time.Minute;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author omar
 */
public class POS extends javax.swing.JFrame {

    /**
     * Creates new form LoginForm
     */
    List<JButton> listButton = new ArrayList<JButton>();
    List<JPanel> listJPanel = new ArrayList<JPanel>();
    List<JPanel> listJPanelCategory = new ArrayList<JPanel>();
    JPanel jp;
    JPanel jpCategory;
    JButton jb;
    Label MaHD = new Label();
    AreaImplements ai = new AreaImplements();
    CoffeeTableImplements cti = new CoffeeTableImplements();
    OrderCoffeeImplements oci = new OrderCoffeeImplements();
    OrderDetailImplements odi = new OrderDetailImplements();
    UserImplements ui = new UserImplements();
    CategoryImplements ci = new CategoryImplements();
    CustomerImplements cusi = new CustomerImplements();
    ProductImplements pi = new ProductImplements();
    TableProductImplements tpi = new TableProductImplements();
    help al;
    List<String> check = new ArrayList<>();
    AssistantTable at = new AssistantTable();
    NumberFormat formatNumber = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    NumberFormat numberFormatter = new DecimalFormat("###,###,###");
    Double totalMoney = 0.0;
    updateOrderDetailJFrame uodj;
    SwitchTableJFrame stj;
    JoinTableJFrame jtj;
    int selectedIndexTab;
    DialogHelpers dialog;
    UserAccount userCurrent = UserHelpers.User;

    public POS() {
        initComponents();
        setLocationRelativeTo(null);
        loadAreasTables();
    }

    public void loadAreasTables() {
        bookTable.setEnabled(false);
        joinTable.setEnabled(false);
        switchTable.setEnabled(false);
        seeBill.setEnabled(false);
        payOrder.setEnabled(false);
        orderProduct.setEnabled(false);
        txtNgay.setEditable(false);
        txtgio.setEditable(false);
        areaName.setEditable(false);
        customerName.setEnabled(false);
        table.setEditable(false);
        nameProduct.setEditable(false);
        cancelTable.setEnabled(false);
        plusProductQuantity.setEnabled(false);
        substractProductQuantity.setEnabled(false);

        new Timer(1000, new ActionListener() {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");

            public void actionPerformed(ActionEvent e) {
                txtgio.setText(format.format(new Date()));
                txtNgay.setText(date.format(new Date()));
            }
        }).start();
        List<Area> allAreas = ai.getAllAreas();
        List<Category> allCategories = ci.listOfCategory();

        for (Category c : allCategories) {
            jpCategory = new JPanel();
            jpCategory.setName(c.getCateName());
            jpCategory.setBackground(new java.awt.Color(255, 255, 255));
            listJPanelCategory.add(jp);
            category.add(jpCategory);
        }

        for (Area a : allAreas) {
            jp = new JPanel();
            jp.setName(a.getAreaName());
            listJPanel.add(jp);
            check.add(a.getAreaId() + "-" + a.getAreaName());
        }
        List<CoffeeTable> allCoffeeTables = cti.getAllCoffeeTablesWithoutAreId();
        for (CoffeeTable ct : allCoffeeTables) {
            jb = new JButton();
            jb.setText(ct.getTableName());
            jb.setIcon(UserHelpers.readImages("admin.png"));
            jb.setName(ct.getAreaId().getAreaId() + "-" + ct.getTableId());
            jb.setBackground(Color.green);
            jb.setSize(100, 100);
            listButton.add(jb);
            checkOrderedTable(jb);
            String areaId = String.valueOf(ct.getAreaId().getAreaId());
            String tableId = String.valueOf(ct.getTableId());
            jb.addActionListener((ae) -> {
                selectedIndexTab = tab.getSelectedIndex();
                loadTable(areaId, tableId);
                loadOrderDetail();
            });
        }
        for (int i = 0; i < listJPanel.size(); i++) {
            String[] splitCheck = check.get(i).split("-");
            if (splitCheck[1].equals(listJPanel.get(i).getName())) {
                for (int j = 0; j < listButton.size(); j++) {
                    String nameButton = listButton.get(j).getName();
                    String[] splitNameButton = nameButton.split("-");
                    if (splitNameButton[0].equals(splitCheck[0])) {
                        listJPanel.get(i).add(listButton.get(j));
                        tab.add(listJPanel.get(i));
                    }
                }
            }
            listJPanel.get(i).setBackground(Color.DARK_GRAY);
            tab.add(listJPanel.get(i));
            tab.repaint();
        }

        DefaultTableModel tableProduct = (DefaultTableModel) viewProduct.getModel();
        tableProduct.setRowCount(0);
        String cateName = category.getTitleAt(0);
        Category findCategory = ci.findCategoryByName(cateName);
        List<Product> listProduct = pi.showProductByCategory(findCategory.getCateId());
        for (Product p : listProduct) {
            String cost = formatNumber.format(p.getPrice());
            Object[] data = {
                p.getProName(),
                cost,
                p.getQuantity()
            };
            tableProduct.addRow(data);
        }
        viewProduct.setModel(tableProduct);

    }

    public void login() {
        this.dispose();
        Login lg = new Login();
        lg.setVisible(true);
        lg.setLocationRelativeTo(null);
        alert("Hãy đăng nhập");
    }

    public void setNameTableArea(String nameTable, String nameArea, int selectedIndex) {
        table.setText(nameTable);
        areaName.setText(nameArea);
        switchTable.setEnabled(true);
        joinTable.setEnabled(true);
        seeBill.setEnabled(true);
        payOrder.setEnabled(true);
        tab.setSelectedIndex(selectedIndex);
    }

    public void loadOrderDetail() {
        totalMoney = 0.0;
        DefaultTableModel model = (DefaultTableModel) viewOrderDetail.getModel();
        model.setRowCount(0);
        String nameTable = this.table.getText();
        String nameArea = areaName.getText();
        if (!"".equals(nameTable)) {
            Area findArea = ai.findAreaByName(nameArea);
            CoffeeTable findTableCoffee = cti.findTableCoffeeByName(nameTable, findArea.getAreaId());
            List<TableProduct> tableProduct = tpi.getTableProductByTableStatus(findTableCoffee.getTableId(), 0);
            Double totalCost = 0.0;
            for (TableProduct tp : tableProduct) {
                String money = formatNumber.format(tp.getProId().getPrice());
                String totalMoneyPerRow = formatNumber.format(tp.getProId().getPrice() * tp.getQuantityOrder());
                totalCost += tp.getProId().getPrice() * tp.getQuantityOrder();
                Object[] data = {tp.getProId().getProName(), money, tp.getQuantityOrder(), totalMoneyPerRow};
                model.addRow(data);
            }
            totalMoney = totalCost;
            viewOrderDetail.setModel(model);
            String formatTotalMoney = numberFormatter.format(totalMoney);
            sumMoney.setText(formatTotalMoney);
        }
    }

    public void checkOrderedTable(JButton jb) {
        String nameButton = jb.getName();
        String[] splitNameButton = nameButton.split("-");
        Customer customerByTableId = cusi.getCustomerByTableId(Integer.parseInt(splitNameButton[1]), 0);
        CoffeeTable coffeeTable = cti.getCoffeeTableByTableId(Integer.parseInt(splitNameButton[1]));
        if (customerByTableId != null) {
            if (coffeeTable.getStatus() == 2 && customerByTableId.getStatus() == 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date now = new Date();
                String dateArriveString = String.valueOf(sdf.format(customerByTableId.getArriveDate()));
                String dateNowString = String.valueOf(sdf.format(now));
                SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
                if (dateArriveString.equals(dateNowString)) {
                    String arriveHourString = String.valueOf(customerByTableId.getArriveHour());
                    String timeNowString = sdfTime.format(now);
                    try {
                        Date gioDen = sdfTime.parse(arriveHourString);
                        Date gioHienTai = sdfTime.parse(timeNowString);
                        long duration = gioDen.getTime() - gioHienTai.getTime();
                        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration);
                        if (minutes <= 15) {
                            jb.setBackground(Color.red);
                            jb.setForeground(Color.yellow);
                        } else {
                            jb.setBackground(Color.green);
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(POS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    jb.setBackground(Color.green);
                }
            } else if (coffeeTable.getStatus() == 2 && customerByTableId.getStatus() == 1) {
                jb.setBackground(Color.red);
                jb.setForeground(Color.yellow);
            } else if (coffeeTable.getStatus() == 1) {
                jb.setBackground(Color.red);
                jb.setForeground(Color.yellow);
            } else {
                jb.setBackground(Color.green);
            }
        } else {
            if (coffeeTable.getStatus() == 1) {
                jb.setBackground(Color.red);
                jb.setForeground(Color.yellow);
            }
        }
    }

    public void loadTable(String areaId, String tableId) {
        Area findArea = ai.findAreaById(Integer.parseInt(areaId));
        CoffeeTable findTable = cti.getCoffeeTableByTableId(Integer.parseInt(tableId));
        sumMoney.setText("0");
        table.setText(findTable.getTableName());
        Menuorder.setVisible(false);
        bookTable.setEnabled(true);
        orderProduct.setEnabled(false);
        MaHD.setName(null);
        areaName.setText(findArea.getAreaName());
        joinTable.setEnabled(false);
        seeBill.setEnabled(false);
        payOrder.setEnabled(false);
        Customer customerByTableId = cusi.getCustomerByTableId(Integer.parseInt(tableId), 0);
        if (customerByTableId != null) {
            String cloneDateArriveString = "";
            if (findTable.getStatus() == 2 && customerByTableId.getStatus() == 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date now = new Date();
                String dateArriveString = String.valueOf(sdf.format(customerByTableId.getArriveDate()));
                cloneDateArriveString = dateArriveString;
                String dateNowString = String.valueOf(sdf.format(now));
                SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
                if (dateArriveString.equals(dateNowString)) {
                    inforOrderedTable.setText("Đặt trước: " + customerByTableId.getFullname() + " vào " + customerByTableId.getArriveHour() + " ngày hôm nay");
                    String arriveHourString = String.valueOf(customerByTableId.getArriveHour());
                    String timeNowString = sdfTime.format(now);
                    try {
                        Date gioDen = sdfTime.parse(arriveHourString);
                        Date gioHienTai = sdfTime.parse(timeNowString);
                        long diff = gioDen.getTime() - gioHienTai.getTime();
                        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
                        if (minutes <= 15) {
                            switchTable.setEnabled(true);
                            Menuorder.setVisible(true);
                            bookTable.setEnabled(false);
                            seeBill.setEnabled(true);
                            payOrder.setEnabled(true);
                            cancelTable.setEnabled(true);
                            switchTable.setEnabled(true);
                            Menuorder.setVisible(true);
                            bookTable.setEnabled(false);
                            seeBill.setEnabled(true);
                            payOrder.setEnabled(true);
                            cancelTable.setEnabled(true);
                            customerName.setText(customerByTableId.getFullname());
                        } else {
                            switchTable.setEnabled(false);
                            Menuorder.setVisible(false);
                            bookTable.setEnabled(true);
                            seeBill.setEnabled(false);
                            payOrder.setEnabled(false);
                            cancelTable.setEnabled(true);
                            customerName.setText("");
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(POS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    customerName.setText("");
                    switchTable.setEnabled(true);
                    bookTable.setEnabled(true);
                    cancelTable.setEnabled(true);
                    inforOrderedTable.setText("Đặt trước: " + customerByTableId.getFullname() + " vào " + customerByTableId.getArriveHour() + " ngày " + dateArriveString);
                }
            }
            if (findTable.getStatus() == 2 && customerByTableId.getStatus() == 1) {
                customerName.setText(customerByTableId.getFullname());
                inforOrderedTable.setText("");
                switchTable.setEnabled(true);
                Menuorder.setVisible(true);
                bookTable.setEnabled(false);
                seeBill.setEnabled(true);
                payOrder.setEnabled(true);
                cancelTable.setEnabled(true);
            }
            if (findTable.getStatus() == 1) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date now = new Date();
                String dateArriveString = String.valueOf(sdf.format(customerByTableId.getArriveDate()));
                cloneDateArriveString = dateArriveString;
                String dateNowString = String.valueOf(sdf.format(now));
                SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
                if (dateArriveString.equals(dateNowString)) {
                    String arriveHourString = String.valueOf(customerByTableId.getArriveHour());
                    String timeNowString = sdfTime.format(now);
                    try {
                        Date gioDen = sdfTime.parse(arriveHourString);
                        Date gioHienTai = sdfTime.parse(timeNowString);
                        long diff = gioDen.getTime() - gioHienTai.getTime();
                        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
                        if (minutes == 0) {
                            alert("Đã đến giờ đặt bàn của khách");
                        } else {
                            if (minutes <= 15) {
                                alert("Còn " + minutes + " phút " + " đến giờ đặt bàn");
                            }
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(POS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    inforOrderedTable.setText("Đặt trước: " + customerByTableId.getFullname() + " vào " + customerByTableId.getArriveHour() + " ngày hôm nay");
                } else {
                    inforOrderedTable.setText("Đặt trước: " + customerByTableId.getFullname() + " vào " + customerByTableId.getArriveHour() + " ngày " + cloneDateArriveString);
                }

                if (!"".equals(nameProduct.getText())) {
                    orderProduct.setEnabled(true);
                } else {
                    orderProduct.setEnabled(false);
                }
                switchTable.setEnabled(true);
                Menuorder.setVisible(true);
                bookTable.setEnabled(false);
                seeBill.setEnabled(true);
                payOrder.setEnabled(true);
                cancelTable.setEnabled(true);
                joinTable.setEnabled(true);
                customerName.setText("TRỐNG");
            }
        } else {
            inforOrderedTable.setText("");
            if (findTable.getStatus() == 1) {
                if (!"".equals(nameProduct.getText())) {
                    orderProduct.setEnabled(true);
                } else {
                    orderProduct.setEnabled(false);
                }
                switchTable.setEnabled(true);
                Menuorder.setVisible(true);
                bookTable.setEnabled(false);
                seeBill.setEnabled(true);
                payOrder.setEnabled(true);
                cancelTable.setEnabled(true);
                joinTable.setEnabled(true);
                customerName.setText("TRỐNG");
            } else {
                customerName.setText("");
                switchTable.setEnabled(false);
                cancelTable.setEnabled(false);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel13 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabelClose = new javax.swing.JLabel();
        jLabelMin = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        tab = new javax.swing.JTabbedPane();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        tutorial = new javax.swing.JButton();
        bookTable = new javax.swing.JButton();
        cancelTable = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        inforOrderedTable = new javax.swing.JLabel();
        Menuorder = new javax.swing.JPanel();
        category = new javax.swing.JTabbedPane();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        viewProduct = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        imageProduct = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        orderProduct = new javax.swing.JButton();
        nameProduct = new javax.swing.JTextField();
        substractProductQuantity = new javax.swing.JButton();
        plusProductQuantity = new javax.swing.JButton();
        productQuantity = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        search = new javax.swing.JButton();
        keyword = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        customerName = new javax.swing.JTextField();
        table = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        areaName = new javax.swing.JTextField();
        txtNgay = new javax.swing.JTextField();
        jcrollpane = new javax.swing.JScrollPane();
        viewOrderDetail = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        sumMoney = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtgio = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        payOrder = new javax.swing.JButton();
        joinTable = new javax.swing.JButton();
        switchTable = new javax.swing.JButton();
        seeBill = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel13.setBackground(new java.awt.Color(44, 62, 80));
        jPanel13.setForeground(new java.awt.Color(44, 62, 80));
        jPanel13.setPreferredSize(new java.awt.Dimension(1370, 780));
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel13MouseClicked(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(248, 148, 6));
        jPanel3.setPreferredSize(new java.awt.Dimension(1368, 780));

        jPanel1.setBackground(new java.awt.Color(248, 148, 6));

        jLabelClose.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelClose.setForeground(new java.awt.Color(255, 255, 255));
        jLabelClose.setText("X");
        jLabelClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCloseMouseClicked(evt);
            }
        });

        jLabelMin.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelMin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMin.setText("-");
        jLabelMin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelMin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMinMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Quản lý bán hàng");

        btnBack.setBackground(new java.awt.Color(34, 167, 240));
        btnBack.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Trở về");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(485, 485, 485)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelMin)
                .addGap(18, 18, 18)
                .addComponent(jLabelClose)
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelMin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelClose)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(44, 62, 80));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setMaximumSize(new java.awt.Dimension(482, 671));
        jPanel2.setMinimumSize(new java.awt.Dimension(482, 671));

        tab.setBackground(new java.awt.Color(44, 62, 80));
        tab.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tab.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tab.setMaximumSize(new java.awt.Dimension(32767, 548));
        tab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(236, 240, 241));
        jLabel5.setText("Thông tin bàn");

        jPanel4.setBackground(new java.awt.Color(44, 62, 80));

        tutorial.setBackground(new java.awt.Color(34, 167, 240));
        tutorial.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tutorial.setForeground(new java.awt.Color(255, 255, 255));
        tutorial.setText("?");
        tutorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tutorialActionPerformed(evt);
            }
        });
        jPanel4.add(tutorial);

        bookTable.setBackground(new java.awt.Color(34, 167, 240));
        bookTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bookTable.setForeground(new java.awt.Color(255, 255, 255));
        bookTable.setText("Đặt bàn");
        bookTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookTableActionPerformed(evt);
            }
        });
        jPanel4.add(bookTable);

        cancelTable.setBackground(new java.awt.Color(34, 167, 240));
        cancelTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cancelTable.setForeground(new java.awt.Color(255, 255, 255));
        cancelTable.setText("Hủy bàn");
        cancelTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelTableActionPerformed(evt);
            }
        });
        jPanel4.add(cancelTable);

        refresh.setBackground(new java.awt.Color(34, 167, 240));
        refresh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        refresh.setForeground(new java.awt.Color(255, 255, 255));
        refresh.setText("Làm mới");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });
        jPanel4.add(refresh);

        inforOrderedTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inforOrderedTable.setForeground(new java.awt.Color(255, 51, 0));
        inforOrderedTable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(inforOrderedTable, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel5)
                .addGap(6, 6, 6)
                .addComponent(inforOrderedTable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Menuorder.setBackground(new java.awt.Color(44, 62, 80));
        Menuorder.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        category.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        category.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categoryMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(236, 240, 241));
        jLabel4.setText("Loại sản phẩm");

        viewProduct.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        viewProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TÊN MÓN", "GIÁ TIỀN", "SỐ LƯỢNG"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        viewProduct.getTableHeader().setReorderingAllowed(false);
        viewProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(viewProduct);

        jPanel6.setBackground(new java.awt.Color(44, 62, 80));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(236, 240, 241));
        jLabel6.setText("Mô tả sản phẩm");

        imageProduct.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        imageProduct.setForeground(new java.awt.Color(236, 240, 241));

        jPanel9.setBackground(new java.awt.Color(44, 62, 80));

        orderProduct.setBackground(new java.awt.Color(34, 167, 240));
        orderProduct.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        orderProduct.setForeground(new java.awt.Color(255, 255, 255));
        orderProduct.setText("ODER");
        orderProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderProductActionPerformed(evt);
            }
        });

        nameProduct.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameProductActionPerformed(evt);
            }
        });

        substractProductQuantity.setBackground(new java.awt.Color(34, 167, 240));
        substractProductQuantity.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        substractProductQuantity.setForeground(new java.awt.Color(255, 255, 255));
        substractProductQuantity.setText("-");
        substractProductQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                substractProductQuantityActionPerformed(evt);
            }
        });

        plusProductQuantity.setBackground(new java.awt.Color(34, 167, 240));
        plusProductQuantity.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        plusProductQuantity.setForeground(new java.awt.Color(255, 255, 255));
        plusProductQuantity.setText("+");
        plusProductQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusProductQuantityActionPerformed(evt);
            }
        });

        productQuantity.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        productQuantity.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        productQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                productQuantityKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nameProduct)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(substractProductQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plusProductQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(orderProduct)
                .addGap(40, 40, 40))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(productQuantity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(substractProductQuantity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(plusProductQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(orderProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imageProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imageProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)))
        );

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));

        search.setBackground(new java.awt.Color(204, 0, 0));
        search.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        search.setForeground(new java.awt.Color(255, 255, 255));
        search.setText("Tìm nhanh");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        keyword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        keyword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                keywordKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(keyword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(search)
                .addGap(2, 2, 2))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(keyword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search))
                .addContainerGap())
        );

        javax.swing.GroupLayout MenuorderLayout = new javax.swing.GroupLayout(Menuorder);
        Menuorder.setLayout(MenuorderLayout);
        MenuorderLayout.setHorizontalGroup(
            MenuorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuorderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
            .addComponent(category, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MenuorderLayout.setVerticalGroup(
            MenuorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(27, 27, 27)
                .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(44, 62, 80));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setPreferredSize(new java.awt.Dimension(500, 524));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(236, 240, 241));
        jLabel7.setText("Tên khách:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(236, 240, 241));
        jLabel8.setText("Đang Chọn :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(236, 240, 241));
        jLabel9.setText("Ngày:");

        customerName.setBackground(new java.awt.Color(108, 122, 137));
        customerName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        customerName.setForeground(new java.awt.Color(228, 241, 254));
        customerName.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                customerNameInputMethodTextChanged(evt);
            }
        });

        table.setBackground(new java.awt.Color(108, 122, 137));
        table.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        table.setForeground(new java.awt.Color(228, 241, 254));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(236, 240, 241));
        jLabel10.setText("Khu vực:");

        areaName.setBackground(new java.awt.Color(108, 122, 137));
        areaName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        areaName.setForeground(new java.awt.Color(228, 241, 254));

        txtNgay.setBackground(new java.awt.Color(108, 122, 137));
        txtNgay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNgay.setForeground(new java.awt.Color(228, 241, 254));

        viewOrderDetail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        viewOrderDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TÊN MÓN", "GIÁ TIỀN", "SỐ LƯỢNG", "THÀNH TIỀN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        viewOrderDetail.getTableHeader().setReorderingAllowed(false);
        viewOrderDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewOrderDetailMouseClicked(evt);
            }
        });
        jcrollpane.setViewportView(viewOrderDetail);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(236, 240, 241));
        jLabel11.setText("DANH SÁCH ORDER SẢN PHẨM");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(236, 240, 241));
        jLabel12.setText("Tổng tiền:");

        jPanel8.setBackground(new java.awt.Color(44, 62, 80));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        sumMoney.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        sumMoney.setForeground(java.awt.Color.red);
        sumMoney.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sumMoney.setText("0");
        sumMoney.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sumMoney, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sumMoney, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
        );

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(236, 240, 241));
        jLabel13.setText("VNĐ");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(236, 240, 241));
        jLabel16.setText("Giờ:");

        txtgio.setBackground(new java.awt.Color(108, 122, 137));
        txtgio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtgio.setForeground(new java.awt.Color(228, 241, 254));

        jPanel5.setBackground(new java.awt.Color(44, 62, 80));

        payOrder.setBackground(new java.awt.Color(255, 51, 0));
        payOrder.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        payOrder.setForeground(new java.awt.Color(255, 255, 255));
        payOrder.setText("Thanh toán");
        payOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payOrderActionPerformed(evt);
            }
        });

        joinTable.setBackground(new java.awt.Color(34, 167, 240));
        joinTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        joinTable.setForeground(new java.awt.Color(255, 255, 255));
        joinTable.setText("Ghép bàn");
        joinTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinTableActionPerformed(evt);
            }
        });

        switchTable.setBackground(new java.awt.Color(34, 167, 240));
        switchTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        switchTable.setForeground(new java.awt.Color(255, 255, 255));
        switchTable.setText("Chuyển bàn");
        switchTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchTableActionPerformed(evt);
            }
        });

        seeBill.setBackground(new java.awt.Color(34, 167, 240));
        seeBill.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        seeBill.setForeground(new java.awt.Color(255, 255, 255));
        seeBill.setText("Xem bill");
        seeBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seeBillActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(seeBill, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(switchTable, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(joinTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(payOrder, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                .addGap(29, 29, 29))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(switchTable, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(joinTable, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(payOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seeBill, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(customerName, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtgio, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(21, 21, 21)
                                    .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(jLabel11)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(areaName, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jcrollpane, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(customerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(areaName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16))
                    .addComponent(txtgio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel12))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Menuorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Menuorder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1384, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 1384, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked

    }//GEN-LAST:event_jPanel13MouseClicked
    public void loadOrderProduct() {
        nameProduct.setText("");
        imageProduct.setIcon(null);
        orderProduct.setEnabled(false);
        plusProductQuantity.setEnabled(false);
        substractProductQuantity.setEnabled(false);
        productQuantity.setText("");
    }
    private void orderProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderProductActionPerformed
        String tableName = table.getText();
        String nameArea = areaName.getText();
        String proQuantity = productQuantity.getText();
        int index = viewProduct.getSelectedRow();
        String proName = (String) viewProduct.getValueAt(index, 0);
        Area findArea = ai.findAreaByName(nameArea);
        Product findProduct = pi.findProductByName(proName);
        CoffeeTable findTableCoffee = cti.findTableCoffeeByName(tableName, findArea.getAreaId());
        Customer customerByTableId = cusi.getCustomerByTableId(findTableCoffee.getTableId(), 0);
        boolean checkQuantity = false;
        int proQuantityInt = 0;
        if (findProduct.getQuantity() > 0) {
            if (proQuantity.length() == 0) {
                alert("Số lượng đang trống không thể đặt món");
            } else {
                proQuantityInt = Integer.parseInt(proQuantity);
                if (proQuantityInt > findProduct.getQuantity() && findProduct.getQuantity() > 0) {
                    alert("Không thể thêm số lượng lớn hơn số lượng đang có");
                    checkQuantity = false;
                } else if (proQuantityInt == 0 && findProduct.getQuantity() > 0) {
                    alert("Số lượng đặt phải lớn hơn 0");
                    checkQuantity = false;
                } else {
                    checkQuantity = true;
                }
            }
        }
        if (customerByTableId != null) {
            if (findTableCoffee.getStatus() == 1) {
                if (findProduct.getQuantity() > 0) {
                    if (checkQuantity == true) {
                        TableProduct checkExistTableProduct = tpi.checkExistTableProduct(findProduct.getProId(), findTableCoffee.getTableId(), 0);
                        if (checkExistTableProduct != null) {
                            boolean updatePlusQuantityOrderTableProduct = tpi.updatePlusQuantityOrderTableProduct(checkExistTableProduct.getTblProId(), proQuantityInt);
                            if (updatePlusQuantityOrderTableProduct) {
                                boolean updateSubstractQuantityProduct = pi.updateSubstractQuantityProduct(findProduct.getProId(), proQuantityInt);
                                if (updateSubstractQuantityProduct) {
                                    reloadCategoryProduct();
                                    alert("Thêm số lượng thành công");
                                    loadOrderProduct();
                                    DefaultTableModel model = (DefaultTableModel) viewOrderDetail.getModel();
                                    model.setRowCount(0);
                                    List<TableProduct> tableProduct = tpi.getTableProductByTableStatus(findTableCoffee.getTableId(), 0);
                                    Double totalCost = 0.0;
                                    for (TableProduct tp : tableProduct) {
                                        String money = formatNumber.format(tp.getProId().getPrice());
                                        String totalMoneyPerRow = formatNumber.format(tp.getProId().getPrice() * tp.getQuantityOrder());
                                        totalCost += tp.getProId().getPrice() * tp.getQuantityOrder();
                                        Object[] data = {tp.getProId().getProName(), money, tp.getQuantityOrder(), totalMoneyPerRow};
                                        model.addRow(data);
                                    }
                                    totalMoney = totalCost;
                                    viewOrderDetail.setModel(model);
                                    String totalMoneyString = String.valueOf(totalMoney);
                                    String formatTotalMoney = numberFormatter.format(totalMoney);
                                    sumMoney.setText(formatTotalMoney);
                                }
                            } else {
                                alert("Lỗi khi thêm số lượng");
                            }
                        } else {
                            TableProduct tp = new TableProduct(0, findProduct, findTableCoffee, proQuantityInt, 0, null, null);
                            boolean createNewTableProduct = tpi.createNewTableProduct(tp);
                            if (createNewTableProduct) {
                                boolean updateSubstractQuantityProduct = pi.updateSubstractQuantityProduct(findProduct.getProId(), proQuantityInt);
                                if (updateSubstractQuantityProduct) {
                                    reloadCategoryProduct();
                                    alert("Đặt món thành công");
                                    loadOrderProduct();
                                    DefaultTableModel model = (DefaultTableModel) viewOrderDetail.getModel();
                                    model.setRowCount(0);
                                    List<TableProduct> tableProduct = tpi.getTableProductByTableStatus(findTableCoffee.getTableId(), 0);
                                    Double totalCost = 0.0;
                                    for (TableProduct tpro : tableProduct) {
                                        String money = formatNumber.format(tpro.getProId().getPrice());
                                        String totalMoneyPerRow = formatNumber.format(tpro.getProId().getPrice() * tpro.getQuantityOrder());
                                        totalCost += tpro.getProId().getPrice() * tpro.getQuantityOrder();
                                        Object[] data = {tpro.getProId().getProName(), money, tpro.getQuantityOrder(), totalMoneyPerRow};
                                        model.addRow(data);
                                    }
                                    totalMoney = totalCost;
                                    viewOrderDetail.setModel(model);
                                    String totalMoneyString = String.valueOf(totalMoney);
                                    String formatTotalMoney = numberFormatter.format(totalMoney);
                                    sumMoney.setText(formatTotalMoney);
                                }
                            } else {
                                alert("Lỗi khi đặt món");
                            }
                        }
                    }
                } else {
                    alert("Sản phẩm đang hết hàng");
                }
            } else {
                if (customerByTableId.getStatus() == 1) {
                    if (findProduct.getQuantity() > 0) {
                        if (checkQuantity == true) {
                            TableProduct checkExistTableProduct = tpi.checkExistTableProduct(findProduct.getProId(), findTableCoffee.getTableId(), 0);
                            if (checkExistTableProduct != null) {
                                boolean updatePlusQuantityOrderTableProduct = tpi.updatePlusQuantityOrderTableProduct(checkExistTableProduct.getTblProId(), proQuantityInt);
                                if (updatePlusQuantityOrderTableProduct) {
                                    boolean updateSubstractQuantityProduct = pi.updateSubstractQuantityProduct(findProduct.getProId(), proQuantityInt);
                                    if (updateSubstractQuantityProduct) {
                                        reloadCategoryProduct();
                                        alert("Thêm số lượng thành công");
                                        loadOrderProduct();
                                        DefaultTableModel model = (DefaultTableModel) viewOrderDetail.getModel();
                                        model.setRowCount(0);
                                        List<TableProduct> tableProduct = tpi.getTableProductByTableStatus(findTableCoffee.getTableId(), 0);
                                        Double totalCost = 0.0;
                                        for (TableProduct tp : tableProduct) {
                                            String money = formatNumber.format(tp.getProId().getPrice());
                                            String totalMoneyPerRow = formatNumber.format(tp.getProId().getPrice() * tp.getQuantityOrder());
                                            totalCost += tp.getProId().getPrice() * tp.getQuantityOrder();
                                            Object[] data = {tp.getProId().getProName(), money, tp.getQuantityOrder(), totalMoneyPerRow};
                                            model.addRow(data);
                                        }
                                        totalMoney = totalCost;
                                        viewOrderDetail.setModel(model);
                                        String totalMoneyString = String.valueOf(totalMoney);
                                        String formatTotalMoney = numberFormatter.format(totalMoney);
                                        sumMoney.setText(formatTotalMoney);
                                    }
                                } else {
                                    alert("Lỗi khi thêm số lượng");
                                }
                            } else {
                                TableProduct tp = new TableProduct(0, findProduct, findTableCoffee, proQuantityInt, 0, null, null);
                                boolean createNewTableProduct = tpi.createNewTableProduct(tp);
                                if (createNewTableProduct) {
                                    boolean updateSubstractQuantityProduct = pi.updateSubstractQuantityProduct(findProduct.getProId(), proQuantityInt);
                                    if (updateSubstractQuantityProduct) {
                                        reloadCategoryProduct();
                                        alert("Đặt món thành công");
                                        loadOrderProduct();
                                        DefaultTableModel model = (DefaultTableModel) viewOrderDetail.getModel();
                                        model.setRowCount(0);
                                        List<TableProduct> tableProduct = tpi.getTableProductByTableStatus(findTableCoffee.getTableId(), 0);
                                        Double totalCost = 0.0;
                                        for (TableProduct tpro : tableProduct) {
                                            String money = formatNumber.format(tpro.getProId().getPrice());
                                            String totalMoneyPerRow = formatNumber.format(tpro.getProId().getPrice() * tpro.getQuantityOrder());
                                            totalCost += tpro.getProId().getPrice() * tpro.getQuantityOrder();
                                            Object[] data = {tpro.getProId().getProName(), money, tpro.getQuantityOrder(), totalMoneyPerRow};
                                            model.addRow(data);
                                        }
                                        totalMoney = totalCost;
                                        viewOrderDetail.setModel(model);
                                        String totalMoneyString = String.valueOf(totalMoney);
                                        String formatTotalMoney = numberFormatter.format(totalMoney);
                                        sumMoney.setText(formatTotalMoney);
                                    }
                                } else {
                                    alert("Lỗi khi đặt món");
                                }
                            }
                        }
                    } else {
                        alert("Sản phẩm đang hết hàng");
                    }
                } else {
                    alert("Khách hàng chưa đến, không thể đặt sản phẩm");
                }
            }
        } else {
            if (findProduct.getQuantity() > 0) {
                if (checkQuantity == true) {
                    TableProduct checkExistTableProduct = tpi.checkExistTableProduct(findProduct.getProId(), findTableCoffee.getTableId(), 0);
                    if (checkExistTableProduct != null) {
                        boolean updatePlusQuantityOrderTableProduct = tpi.updatePlusQuantityOrderTableProduct(checkExistTableProduct.getTblProId(), proQuantityInt);
                        if (updatePlusQuantityOrderTableProduct) {
                            boolean updateSubstractQuantityProduct = pi.updateSubstractQuantityProduct(findProduct.getProId(), proQuantityInt);
                            if (updateSubstractQuantityProduct) {
                                reloadCategoryProduct();
                                alert("Thêm số lượng thành công");
                                loadOrderProduct();
                                DefaultTableModel model = (DefaultTableModel) viewOrderDetail.getModel();
                                model.setRowCount(0);
                                List<TableProduct> tableProduct = tpi.getTableProductByTableStatus(findTableCoffee.getTableId(), 0);
                                Double totalCost = 0.0;
                                for (TableProduct tp : tableProduct) {
                                    String money = formatNumber.format(tp.getProId().getPrice());
                                    String totalMoneyPerRow = formatNumber.format(tp.getProId().getPrice() * tp.getQuantityOrder());
                                    totalCost += tp.getProId().getPrice() * tp.getQuantityOrder();
                                    Object[] data = {tp.getProId().getProName(), money, tp.getQuantityOrder(), totalMoneyPerRow};
                                    model.addRow(data);
                                }
                                totalMoney = totalCost;
                                viewOrderDetail.setModel(model);
                                String totalMoneyString = String.valueOf(totalMoney);
                                String formatTotalMoney = numberFormatter.format(totalMoney);
                                sumMoney.setText(formatTotalMoney);
                            }
                        } else {
                            alert("Lỗi khi thêm số lượng");
                        }
                    } else {
                        TableProduct tp = new TableProduct(0, findProduct, findTableCoffee, proQuantityInt, 0, null, null);
                        boolean createNewTableProduct = tpi.createNewTableProduct(tp);
                        if (createNewTableProduct) {
                            boolean updateSubstractQuantityProduct = pi.updateSubstractQuantityProduct(findProduct.getProId(), proQuantityInt);
                            if (updateSubstractQuantityProduct) {
                                reloadCategoryProduct();
                                alert("Đặt món thành công");
                                loadOrderProduct();
                                DefaultTableModel model = (DefaultTableModel) viewOrderDetail.getModel();
                                model.setRowCount(0);
                                List<TableProduct> tableProduct = tpi.getTableProductByTableStatus(findTableCoffee.getTableId(), 0);
                                Double totalCost = 0.0;
                                for (TableProduct tpro : tableProduct) {
                                    String money = formatNumber.format(tpro.getProId().getPrice());
                                    String totalMoneyPerRow = formatNumber.format(tpro.getProId().getPrice() * tpro.getQuantityOrder());
                                    totalCost += tpro.getProId().getPrice() * tpro.getQuantityOrder();
                                    Object[] data = {tpro.getProId().getProName(), money, tpro.getQuantityOrder(), totalMoneyPerRow};
                                    model.addRow(data);
                                }
                                totalMoney = totalCost;
                                viewOrderDetail.setModel(model);
                                String totalMoneyString = String.valueOf(totalMoney);
                                String formatTotalMoney = numberFormatter.format(totalMoney);
                                sumMoney.setText(formatTotalMoney);
                            }
                        } else {
                            alert("Lỗi khi đặt món");
                        }
                    }
                }
            } else {
                alert("Sản phẩm đang hết hàng");
            }
        }
    }//GEN-LAST:event_orderProductActionPerformed
    public void reloadCategoryProduct() {
        int index = category.getSelectedIndex();
        String cateName = category.getTitleAt(index);
        DefaultTableModel tableProduct = (DefaultTableModel) viewProduct.getModel();
        tableProduct.setRowCount(0);
        Category findCategory = ci.findCategoryByName(cateName);
        List<Product> listProduct = pi.showProductByCategory(findCategory.getCateId());
        for (Product p : listProduct) {
            String cost = formatNumber.format(p.getPrice());
            Object[] data = {
                p.getProName(),
                cost,
                p.getQuantity()
            };
            tableProduct.addRow(data);
        }
    }
    private void viewProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewProductMouseClicked
        int index = viewProduct.getSelectedRow();
        String proName = (String) viewProduct.getValueAt(index, 0);
        String price = (String) viewProduct.getValueAt(index, 1);
        int quantity = (int) viewProduct.getValueAt(index, 2);
        Product findProduct = pi.findProductByName(proName);
        if (findProduct.getProImage() != null) {
            BufferedImage bi = null;
            try {
                bi = ImageIO.read(new File("logos", findProduct.getProImage()));
                Image dimg = bi.getScaledInstance(imageProduct.getWidth(), imageProduct.getHeight(),
                        Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(dimg);
                imageProduct.setIcon(imageIcon);
            } catch (IOException ex) {
                Logger.getLogger(POS.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            imageProduct.setIcon(null);
        }
        if (proName.length() > 20) {
            if (proName.length() < 40) {
                int sizeName = (int) (proName.length() / 1.5);
                String substring = proName.substring(0, sizeName);
                nameProduct.setText(substring + "...");
            } else {
                int sizeName = (int) (proName.length() / 2.5);
                String substring = proName.substring(0, sizeName);
                nameProduct.setText(substring + "...");
            }
        } else {
            nameProduct.setText(proName);
        }
        productQuantity.setText("1");
        plusProductQuantity.setEnabled(true);
        substractProductQuantity.setEnabled(true);
        if (!"".equals(table.getText())) {
            orderProduct.setEnabled(true);
        }
    }//GEN-LAST:event_viewProductMouseClicked

    private void categoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoryMouseClicked
        int index = category.getSelectedIndex();
        String cateName = category.getTitleAt(index);
        DefaultTableModel tableProduct = (DefaultTableModel) viewProduct.getModel();
        tableProduct.setRowCount(0);
        Category findCategory = ci.findCategoryByName(cateName);
        List<Product> listProduct = pi.showProductByCategory(findCategory.getCateId());
        for (Product p : listProduct) {
            String cost = formatNumber.format(p.getPrice());
            Object[] data = {
                p.getProName(),
                cost,
                p.getQuantity()
            };
            tableProduct.addRow(data);
        }
        loadOrderProduct();
        //viewProduct.setModel(tableProduct);
    }//GEN-LAST:event_categoryMouseClicked

    private void cancelTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelTableActionPerformed
        String nameTable = table.getText();
        String nameArea = areaName.getText();
        int rowCount = viewOrderDetail.getRowCount();
        if (rowCount <= 0) {
            int confirm = dialog.confirm(this, "Bạn có chắc muốn hủy bàn " + nameTable + " " + nameArea);
            if (confirm == JOptionPane.YES_OPTION) {
                Area area = ai.findAreaByName(nameArea);
                CoffeeTable coffeeTable = cti.findTableCoffeeByName(nameTable, area.getAreaId());
                Customer customerByTableId = cusi.getCustomerByTableId(coffeeTable.getTableId(), 0);
                if (customerByTableId != null) {
                    if (coffeeTable.getStatus() == 1) {
                        OrderDetail orderDetailByTableStatus = odi.getOrderDetailByTableStatus(coffeeTable.getTableId(), 0);
                        int orderDetailId = orderDetailByTableStatus.getOrderDetailId();
                        int orderId = orderDetailByTableStatus.getOrderId().getOrderId();
                        boolean deleteOrderDetail = odi.deleteOrderDetail(orderDetailId);
                        if (deleteOrderDetail) {
                            boolean deleteOrderCoffee = oci.deleteOrderCoffee(orderId);
                            if (deleteOrderCoffee) {
                                boolean updateStatusCoffeeTable = cti.updateStatusCoffeeTable(2, coffeeTable.getTableId());
                                if (updateStatusCoffeeTable) {
                                    alert("Hủy bàn thành công");
                                    reload();
                                    refreshPOS();
                                    Menuorder.setVisible(false);
                                    tab.setSelectedIndex(selectedIndexTab);
                                }
                            }
                        }
                    } else {
                        OrderDetail orderDetailByTableStatus = odi.getOrderDetailByTableStatus(coffeeTable.getTableId(), 2);
                        int orderDetailId = orderDetailByTableStatus.getOrderDetailId();
                        int orderId = orderDetailByTableStatus.getOrderId().getOrderId();
                        List<CoffeeTable> coffeeTablesByCustomer = cti.getCoffeeTablesByCustomer(customerByTableId.getCusId());
                        if (coffeeTablesByCustomer.size() < 2) {
                            boolean deleteOrderDetail = odi.deleteOrderDetail(orderDetailId);
                            if (deleteOrderDetail) {
                                boolean deleteOrderCoffee = oci.deleteOrderCoffee(orderId);
                                if (deleteOrderCoffee) {
                                    boolean updateStatusCoffeeTable = cti.updateStatusCoffeeTable(0, coffeeTable.getTableId());
                                    if (updateStatusCoffeeTable) {
                                        boolean updateStatusCustomer = cusi.updateStatusCustomer(customerByTableId.getCusId(), 3);
                                        if (updateStatusCustomer) {
                                            alert("Hủy bàn thành công");
                                            reload();
                                            refreshPOS();
                                            Menuorder.setVisible(false);
                                            tab.setSelectedIndex(selectedIndexTab);
                                        }
                                    }
                                }
                            }
                        } else {
                            boolean deleteOrderDetail = odi.deleteOrderDetail(orderDetailId);
                            if (deleteOrderDetail) {
                                boolean updateStatusCoffeeTable = cti.updateStatusCoffeeTable(0, coffeeTable.getTableId());
                                if (updateStatusCoffeeTable) {
                                    alert("Hủy bàn thành công");
                                    reload();
                                    refreshPOS();
                                    Menuorder.setVisible(false);
                                    tab.setSelectedIndex(selectedIndexTab);
                                }
                            }
                        }
                    }
                } else {
                    OrderDetail orderDetailByTableStatus = odi.getOrderDetailByTableStatus(coffeeTable.getTableId(), 0);
                    int orderDetailId = orderDetailByTableStatus.getOrderDetailId();
                    int orderId = orderDetailByTableStatus.getOrderId().getOrderId();
                    boolean deleteOrderDetail = odi.deleteOrderDetail(orderDetailId);
                    if (deleteOrderDetail) {
                        boolean deleteOrderCoffee = oci.deleteOrderCoffee(orderId);
                        if (deleteOrderCoffee) {
                            boolean updateStatusCoffeeTable = cti.updateStatusCoffeeTable(0, coffeeTable.getTableId());
                            if (updateStatusCoffeeTable) {
                                alert("Hủy bàn thành công");
                                reload();
                                refreshPOS();
                                Menuorder.setVisible(false);
                                tab.setSelectedIndex(selectedIndexTab);
                            }
                        }
                    }
                }
            }
        } else {
            alert("Không thể hủy bàn khi đang có sản phẩm");
        }

    }//GEN-LAST:event_cancelTableActionPerformed

    private void bookTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookTableActionPerformed
        if (userCurrent != null) {
            int userId = userCurrent.getUserId();
            int orderId = oci.createNewOrderWithoutCustomerId(userId);
            if (orderId > 0) {
                OrderCoffee orderCoffee = oci.getOrderByOrderId(orderId);
                String nameTable = table.getText();
                String nameArea = areaName.getText();
                Area area = ai.findAreaByName(nameArea);
                CoffeeTable coffeeTable = cti.findTableCoffeeByName(nameTable, area.getAreaId());
                OrderDetail od = new OrderDetail(0, orderCoffee, coffeeTable, 0, null, null);
                Customer customerByTableId = cusi.getCustomerByTableId(coffeeTable.getTableId(), 0);
                boolean createNewOrderDetail = odi.createNewOrderDetail(od);
                if (createNewOrderDetail) {
                    boolean updateStatusCoffeeTable = cti.updateStatusCoffeeTable(1, coffeeTable.getTableId());
                    if (updateStatusCoffeeTable) {
                        alert("Đặt bàn thành công");
                        reload();
                        tab.setSelectedIndex(selectedIndexTab);
                        String tableIdString = String.valueOf(coffeeTable.getTableId());
                        String areaIdString = String.valueOf(area.getAreaId());
                        loadTable(areaIdString, tableIdString);
                        Menuorder.setVisible(true);
                    }
                }
            }
        } else {
            login();
        }

    }//GEN-LAST:event_bookTableActionPerformed

    public void reload() {
        for (int i = 0; i < listJPanel.size(); i++) {
            String[] splitCheck = check.get(i).split("-");
            if (splitCheck[1].equals(listJPanel.get(i).getName())) {
                for (int j = 0; j < listButton.size(); j++) {
                    String nameButton = listButton.get(j).getName();
                    String[] splitNameButton = nameButton.split("-");
                    if (splitNameButton[0].equals(splitCheck[0])) {
                        listJPanel.get(i).remove(listButton.get(j));
                        tab.remove(listJPanel.get(i));
                    }
                }
            }
            tab.remove(listJPanel.get(i));
        }
        listJPanel.removeAll(listJPanel);
        listButton.removeAll(listButton);
        category.removeAll();
        loadAreasTables();
    }

    private void tutorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tutorialActionPerformed
        at.setVisible(true);
    }//GEN-LAST:event_tutorialActionPerformed

    private void jLabelMinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinMouseClicked

        this.setState(JFrame.ICONIFIED);

    }//GEN-LAST:event_jLabelMinMouseClicked

    private void jLabelCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseMouseClicked

        System.exit(0);

    }//GEN-LAST:event_jLabelCloseMouseClicked

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        searchProduct();
    }//GEN-LAST:event_searchActionPerformed
    public void searchProduct() {
        DefaultTableModel tableProduct = (DefaultTableModel) viewProduct.getModel();
        tableProduct.setRowCount(0);
        String proName = keyword.getText();
        List<Product> searchProduct = pi.searchProductByName(proName);
        for (Product p : searchProduct) {
            String cost = formatNumber.format(p.getPrice());
            Object[] data = {
                p.getProName(),
                cost,
                p.getQuantity()
            };
            tableProduct.addRow(data);
        }
    }
    private void keywordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keywordKeyPressed
        searchProduct();
    }//GEN-LAST:event_keywordKeyPressed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        MainJFrame mj = new MainJFrame();
        mj.setVisible(true);
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed

    private void tabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabMouseClicked
    }//GEN-LAST:event_tabMouseClicked

    public void refreshPOS() {
        bookTable.setEnabled(false);
        cancelTable.setEnabled(false);
        reloadCategoryProduct();
        customerName.setText("");
        nameProduct.setText("");
        imageProduct.setIcon(null);
        orderProduct.setEnabled(false);
        table.setText("");
        areaName.setText("");
        loadOrderDetail();
        switchTable.setEnabled(false);
        joinTable.setEnabled(false);
        seeBill.setEnabled(false);
        payOrder.setEnabled(false);
        plusProductQuantity.setEnabled(false);
        substractProductQuantity.setEnabled(false);
        productQuantity.setText("");
        inforOrderedTable.setText("");
    }
    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        refreshPOS();
    }//GEN-LAST:event_refreshActionPerformed

    private void plusProductQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusProductQuantityActionPerformed
        int index = viewProduct.getSelectedRow();
        String nameProduct = (String) viewProduct.getValueAt(index, 0);
        Product findProduct = pi.findProductByName(nameProduct);
        int leftQuantityProduct = findProduct.getQuantity();
        String value = productQuantity.getText();
        if (value.length() > 0) {
            int valueInt = Integer.parseInt(value);
            int result = valueInt + 1;
            if (result > leftQuantityProduct) {
                plusProductQuantity.setEnabled(false);
                substractProductQuantity.setEnabled(false);
                substractProductQuantity.setEnabled(true);
                alert("Không thể thêm số lượng. Số lượng còn lại: " + leftQuantityProduct);
            } else {
                String valueString = String.valueOf(result);
                substractProductQuantity.setEnabled(true);
                plusProductQuantity.setEnabled(true);
                productQuantity.setText(valueString);
            }
        } else {
            productQuantity.setText("1");
        }
    }//GEN-LAST:event_plusProductQuantityActionPerformed

    private void substractProductQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_substractProductQuantityActionPerformed
        String value = productQuantity.getText();
        if (value.length() > 0) {
            int valueInt = Integer.parseInt(value);
            if (valueInt > 1) {
                String valueString = String.valueOf(valueInt - 1);
                productQuantity.setText(valueString);
                substractProductQuantity.setEnabled(true);
                plusProductQuantity.setEnabled(true);
            } else {
                alert("Không thể bớt số lượng được nữa");
                substractProductQuantity.setEnabled(false);
                plusProductQuantity.setEnabled(true);
            }
        } else {
            productQuantity.setText("1");
        }

    }//GEN-LAST:event_substractProductQuantityActionPerformed

    private void productQuantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productQuantityKeyPressed
        String value = productQuantity.getText();
        char keyChar = evt.getKeyChar();
        if (keyChar >= '0' && keyChar <= '9') {
            if (value.length() >= 3) {
                productQuantity.setEditable(false);
            } else {
                productQuantity.setEditable(true);
            }
        } else {
            if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
                productQuantity.setEditable(true);
            } else {
                productQuantity.setEditable(false);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_productQuantityKeyPressed

    private void seeBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seeBillActionPerformed
        if (userCurrent != null) {
            String tableName = table.getText();
            String nameArea = areaName.getText();
            Area findArea = ai.findAreaByName(nameArea);
            CoffeeTable findTableCoffee = cti.findTableCoffeeByName(tableName, findArea.getAreaId());
            Customer customerByTableId = cusi.getCustomerByTableId(findTableCoffee.getTableId(), 0);
            String cusName;
            OrderDetail orderDetailByTableStatus = null;
            if (customerByTableId != null) {
                if (findTableCoffee.getStatus() == 1) {
                    cusName = "TRỐNG";
                    orderDetailByTableStatus = odi.getOrderDetailByTableStatus(findTableCoffee.getTableId(), 0);
                } else {
                    cusName = customerByTableId.getFullname();
                    orderDetailByTableStatus = odi.getOrderDetailByTableStatus(findTableCoffee.getTableId(), 2);
                }
            } else {
                cusName = "TRỐNG";
                orderDetailByTableStatus = odi.getOrderDetailByTableStatus(findTableCoffee.getTableId(), 0);
            }
            int orderDetailId = orderDetailByTableStatus.getOrderDetailId();
            int orderId = orderDetailByTableStatus.getOrderId().getOrderId();
            OrderCoffee orderByOrderId = oci.getOrderByOrderId(orderId);

            FileOutputStream fos = null;
            File file = null;
            String inforDetail = "________________________________________________________________________________\r\n"
                    + "Tên Món                   Giá tiền             Số lượng            Thành Tiền\r\n"
                    + "--------------------------------------------------------------------------------\r\n";
            DefaultTableModel model = (DefaultTableModel) viewOrderDetail.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                String pricePro = (String) model.getValueAt(i, 1);
                int quantityPro = (int) model.getValueAt(i, 2);
                String totalMoneyPerRow = (String) model.getValueAt(i, 3);
                String namePro = (String) model.getValueAt(i, 0);
                if (namePro.length() < 24) {
                    for (int j = namePro.length(); j < 50 - namePro.length(); j++) {
                        namePro += " ";
                    }
                    inforDetail += namePro + " " + pricePro + "                " + quantityPro + "                 " + totalMoneyPerRow + "\r\n";
                } else {
                    String[] split = namePro.split(" ");
                    String start = "";
                    String end = "";
                    for (int a = 0; a < split.length / 2; a++) {
                        start += (split[a]) + " ";
                    }
                    for (int a = split.length / 2; a < split.length; a++) {
                        end += (split[a]) + " ";
                    }
                    if (namePro.length() == 49) {
                        namePro = start + "\n" + end;
                        inforDetail += namePro + pricePro + "                " + quantityPro + "                 " + totalMoneyPerRow + "\r\n";
                    } else {
                        for (int j = end.length(); j < 50 - end.length(); j++) {
                            end += " ";
                        }
                        namePro = start + "\n" + end;
                        inforDetail += namePro + " " + pricePro + "                " + quantityPro + "                 " + totalMoneyPerRow + "\r\n";
                    }
                }

            }
            try {
                file = new File("HoaDon.txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
                fos = new FileOutputStream(file);
                String content = "                               HÓA ĐƠN THANH TOÁN\r\n"
                        + "Ngày: " + txtNgay.getText() + "                                              "
                        + "Giờ: " + txtgio.getText() + "\r\n"
                        + "Mã Hóa Đơn: " + orderByOrderId.getOrderId() + "                                                "
                        + "Tiếp nhận: " + tableName + "-" + nameArea + "\r\n"
                        + "Nv Thu Ngân: " + userCurrent.getFullname() + "                                    "
                        + "Khách hàng: " + cusName + "\r\n"
                        + "--------------------------------------------------------------------------------\r\n"
                        + "                               THÔNG TIN THANH TOÁN\r\n"
                        + inforDetail + "\r\n" + ""
                        + "                                                         Tổng Tiền: " + sumMoney.getText() + " đồng."
                        + "\r\n"
                        + "--------------------------------CẢM ƠN QUÝ KHÁCH--------------------------------";
                byte[] bytes = content.getBytes();
                fos.write(bytes);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Desktop.getDesktop().browse(new File("HoaDon.txt").toURI());
            } catch (Exception e) {
                alert("Không tìm thấy file");
                e.printStackTrace();
            }
        } else {
            login();
        }
    }//GEN-LAST:event_seeBillActionPerformed

    private void switchTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchTableActionPerformed
        String nameTable = table.getText();
        String nameArea = areaName.getText();
        Area findArea = ai.findAreaByName(nameArea);
        int rowCount = viewOrderDetail.getRowCount();
        CoffeeTable findTableCoffee = cti.findTableCoffeeByName(nameTable, findArea.getAreaId());
        stj = new SwitchTableJFrame(this, nameTable, nameArea, findTableCoffee.getTableId(), rowCount);
        stj.setVisible(true);
    }//GEN-LAST:event_switchTableActionPerformed

    private void joinTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinTableActionPerformed
        int rowCount = viewOrderDetail.getRowCount();
        if (rowCount > 0) {
            String nameTable = table.getText();
            String nameArea = areaName.getText();
            Area findArea = ai.findAreaByName(nameArea);
            CoffeeTable findTableCoffee = cti.findTableCoffeeByName(nameTable, findArea.getAreaId());
            jtj = new JoinTableJFrame(this, nameTable, nameArea, findTableCoffee.getTableId(), rowCount);
            jtj.setVisible(true);
        } else {
            alert("Không thể ghép bàn khi chưa có sản phẩm");
        }
    }//GEN-LAST:event_joinTableActionPerformed

    private void payOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payOrderActionPerformed
        int rowCount = viewOrderDetail.getRowCount();
        String cusName = customerName.getText();
        String tableName = table.getText();
        String nameArea = areaName.getText();
        String totalMoney = sumMoney.getText();
        String formatTotalMoney = totalMoney.replace(",", "");
        double parseDoubleTotalMoney = Double.parseDouble(formatTotalMoney);
        boolean bl = false;
        boolean error = false;
        UserAccount userCurrent = UserHelpers.User;
        if (userCurrent != null) {
            if (rowCount > 0) {
                Area findArea = ai.findAreaByName(nameArea);
                CoffeeTable findTableCoffee = cti.findTableCoffeeByName(tableName, findArea.getAreaId());
                Customer customerByTableId = cusi.getCustomerByTableId(findTableCoffee.getTableId(), 0);
                OrderDetail orderDetailByTableStatusFindTable = null;
                if (customerByTableId != null) {
                    if (findTableCoffee.getStatus() == 1) {
                        orderDetailByTableStatusFindTable = odi.getOrderDetailByTableStatus(findTableCoffee.getTableId(), 0);
                    } else {
                        orderDetailByTableStatusFindTable = odi.getOrderDetailByTableStatus(findTableCoffee.getTableId(), 2);
                    }
                } else {
                    orderDetailByTableStatusFindTable = odi.getOrderDetailByTableStatus(findTableCoffee.getTableId(), 0);
                }
                if (customerByTableId == null) {
                    int confirm = dialog.confirm(this, "Bạn chắc chắn muốn thanh toán " + findTableCoffee.getTableName() + "-" + findTableCoffee.getAreaId().getAreaName() + " không?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        boolean updateStatusTableProduct = tpi.updateStatusTableProduct(findTableCoffee.getTableId(), orderDetailByTableStatusFindTable.getOrderDetailId());
                        if (updateStatusTableProduct) {
                            boolean updateStatusCoffeeTable = cti.updateStatusCoffeeTable(0, findTableCoffee.getTableId());
                            if (updateStatusCoffeeTable) {
                                boolean updateStatusOrderDetail = odi.updateStatusOrderDetail(orderDetailByTableStatusFindTable.getOrderDetailId(), 1);
                                if (updateStatusOrderDetail) {
                                    boolean updateStatusOrderCoffee = oci.updateStatusOrderCoffee(orderDetailByTableStatusFindTable.getOrderId().getOrderId(), 1);
                                    if (updateStatusOrderCoffee) {
                                        reload();
                                        boolean updateTotalMoneyOrderCoffee = oci.updateTotalMoneyOrderCoffee(orderDetailByTableStatusFindTable.getOrderId().getOrderId(), parseDoubleTotalMoney);
                                        if (updateTotalMoneyOrderCoffee) {
                                            boolean updateUserCashierOrderCoffee = oci.updateUserCashierOrderCoffee(orderDetailByTableStatusFindTable.getOrderId().getOrderId(), userCurrent.getUserId());
                                            if (updateUserCashierOrderCoffee) {
                                                boolean updateCreatedDateOrderCoffee = oci.updateCreatedDateOrderCoffee(orderDetailByTableStatusFindTable.getOrderId().getOrderId());
                                                if (updateCreatedDateOrderCoffee) {
                                                    alert("Thanh toán thành công");
                                                    loadOrderDetail();
                                                    tab.setSelectedIndex(selectedIndexTab);
                                                    Menuorder.setVisible(false);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (findTableCoffee.getStatus() == 1) {
                        int confirm = dialog.confirm(this, "Bạn chắc chắn muốn thanh toán " + findTableCoffee.getTableName() + "-" + findTableCoffee.getAreaId().getAreaName() + " không?");
                        if (confirm == JOptionPane.YES_OPTION) {
                            boolean updateStatusTableProduct = tpi.updateStatusTableProduct(findTableCoffee.getTableId(), orderDetailByTableStatusFindTable.getOrderDetailId());
                            if (updateStatusTableProduct) {
                                boolean updateStatusCoffeeTable = cti.updateStatusCoffeeTable(2, findTableCoffee.getTableId());
                                if (updateStatusCoffeeTable) {
                                    boolean updateStatusOrderDetail = odi.updateStatusOrderDetail(orderDetailByTableStatusFindTable.getOrderDetailId(), 1);
                                    if (updateStatusOrderDetail) {
                                        boolean updateStatusOrderCoffee = oci.updateStatusOrderCoffee(orderDetailByTableStatusFindTable.getOrderId().getOrderId(), 1);
                                        if (updateStatusOrderCoffee) {
                                            reload();
                                            boolean updateTotalMoneyOrderCoffee = oci.updateTotalMoneyOrderCoffee(orderDetailByTableStatusFindTable.getOrderId().getOrderId(), parseDoubleTotalMoney);
                                            if (updateTotalMoneyOrderCoffee) {
                                                boolean updateUserCashierOrderCoffee = oci.updateUserCashierOrderCoffee(orderDetailByTableStatusFindTable.getOrderId().getOrderId(), userCurrent.getUserId());
                                                if (updateUserCashierOrderCoffee) {
                                                    boolean updateCreatedDateOrderCoffee = oci.updateCreatedDateOrderCoffee(orderDetailByTableStatusFindTable.getOrderId().getOrderId());
                                                    if (updateCreatedDateOrderCoffee) {
                                                        alert("Thanh toán thành công");
                                                        loadOrderDetail();
                                                        tab.setSelectedIndex(selectedIndexTab);
                                                        Menuorder.setVisible(false);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        String tables = "";
                        List<CoffeeTable> coffeeTablesByCustomer = cti.getCoffeeTablesByCustomer(customerByTableId.getCusId());
                        double sumMoneyTableProductByCustomer = tpi.getSumMoneyTableProductByCustomer(customerByTableId.getCusId());
                        for (CoffeeTable ct : coffeeTablesByCustomer) {
                            tables += ct.getTableName() + "-" + ct.getAreaId().getAreaName() + ",";
                            List<TableProduct> tableProductByTableStatus = tpi.getTableProductByTableStatus(ct.getTableId(), 0);
                            if (tableProductByTableStatus.isEmpty()) {
                                error = true;
                                break;
                            } else {
                                error = false;
                            }
                        }
                        int confirm = dialog.confirm(this, "Bạn chắc chắn muốn thanh toán " + tables + "cho khách hàng " + customerByTableId.getFullname() + " không?");
                        if (confirm == JOptionPane.YES_OPTION) {
                            if (error) {
                                alert("Đang có bàn trống sản phẩm chưa thể thanh toán");
                            } else {
                                for (CoffeeTable ct : coffeeTablesByCustomer) {
                                    OrderDetail orderDetailByTableStatus = odi.getOrderDetailByTableStatus(ct.getTableId(), 2);
                                    boolean updateStatusTableProduct = tpi.updateStatusTableProduct(ct.getTableId(), orderDetailByTableStatus.getOrderDetailId());
                                    if (updateStatusTableProduct) {
                                        boolean updateStatusCoffeeTable = cti.updateStatusCoffeeTable(0, ct.getTableId());
                                        if (updateStatusCoffeeTable) {
                                            boolean updateStatusOrderDetail = odi.updateStatusOrderDetail(orderDetailByTableStatus.getOrderDetailId(), 1);
                                            if (updateStatusOrderDetail) {
                                                boolean updateStatusOrderCoffee = oci.updateStatusOrderCoffee(orderDetailByTableStatus.getOrderId().getOrderId(), 1);
                                                if (updateStatusOrderCoffee) {
                                                    boolean updateTotalMoneyOrderCoffee = oci.updateTotalMoneyOrderCoffee(orderDetailByTableStatus.getOrderId().getOrderId(), sumMoneyTableProductByCustomer);
                                                    if (updateTotalMoneyOrderCoffee) {
                                                        boolean updateUserCashierOrderCoffee = oci.updateUserCashierOrderCoffee(orderDetailByTableStatus.getOrderId().getOrderId(), userCurrent.getUserId());
                                                        if (updateUserCashierOrderCoffee) {
                                                            boolean updateCreatedDateOrderCoffee = oci.updateCreatedDateOrderCoffee(orderDetailByTableStatusFindTable.getOrderId().getOrderId());
                                                            if (updateCreatedDateOrderCoffee) {
                                                                bl = true;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (bl) {
                                boolean updateStatusCustomer = cusi.updateStatusCustomer(customerByTableId.getCusId(), 2);
                                if (updateStatusCustomer) {
                                    reload();
                                    alert("Thanh toán thành công");
                                    loadOrderDetail();
                                    tab.setSelectedIndex(selectedIndexTab);
                                    Menuorder.setVisible(false);
                                    sumMoneyTableProductByCustomer = 0;
                                }
                            }
                        }
                    }
                }
            } else {
                alert("Chưa có sản phẩm nào được đặt");
            }
        } else {
            login();
        }
    }//GEN-LAST:event_payOrderActionPerformed

    private void viewOrderDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewOrderDetailMouseClicked
        int index = viewOrderDetail.getSelectedRow();
        String nameProduct = (String) viewOrderDetail.getValueAt(index, 0);
        Product findProduct = pi.findProductByName(nameProduct);
        int quantityOrder = (int) viewOrderDetail.getValueAt(index, 2);
        String nameTable = table.getText();
        String nameArea = areaName.getText();
        Area findArea = ai.findAreaByName(nameArea);
        CoffeeTable findTableCoffee = cti.findTableCoffeeByName(nameTable, findArea.getAreaId());
        List<CoffeeTable> allOrderedCoffeeTables = cti.getAllCoffeeTablesWithoutAreIdByStatus(1);
        uodj = new updateOrderDetailJFrame(this, findTableCoffee.getTableId(), findProduct.getProId(), nameProduct, quantityOrder, findProduct.getQuantity(), allOrderedCoffeeTables, nameTable, nameArea, findArea.getAreaId());
        uodj.setVisible(true);
    }//GEN-LAST:event_viewOrderDetailMouseClicked

    private void customerNameInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_customerNameInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_customerNameInputMethodTextChanged

    private void nameProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameProductActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new POS().setVisible(true);
            }
        });
    }

    private void alert(String message) {
        al = new help(message);
        al.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Menuorder;
    private javax.swing.JTextField areaName;
    private javax.swing.JButton bookTable;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton cancelTable;
    private javax.swing.JTabbedPane category;
    private javax.swing.JTextField customerName;
    private javax.swing.JLabel imageProduct;
    private javax.swing.JLabel inforOrderedTable;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelClose;
    private javax.swing.JLabel jLabelMin;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jcrollpane;
    private javax.swing.JButton joinTable;
    private javax.swing.JTextField keyword;
    private javax.swing.JTextField nameProduct;
    private javax.swing.JButton orderProduct;
    private javax.swing.JButton payOrder;
    private javax.swing.JButton plusProductQuantity;
    private javax.swing.JTextField productQuantity;
    private javax.swing.JButton refresh;
    private javax.swing.JButton search;
    private javax.swing.JButton seeBill;
    private javax.swing.JButton substractProductQuantity;
    private javax.swing.JLabel sumMoney;
    private javax.swing.JButton switchTable;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTextField table;
    private javax.swing.JButton tutorial;
    private javax.swing.JTextField txtNgay;
    private javax.swing.JTextField txtgio;
    private javax.swing.JTable viewOrderDetail;
    private javax.swing.JTable viewProduct;
    // End of variables declaration//GEN-END:variables
}
