package view;

import DAO.AreaImplements;
import DAO.CategoryImplements;
import DAO.CoffeeTableImplements;
import DAO.ProductImplements;
import Helpers.DialogHelpers;
import Helpers.UserHelpers;
import data.Area;
import data.Category;
import data.CoffeeTable;
import data.Product;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author omar
 */
public class MainJFrame extends javax.swing.JFrame {

    /**
     * Creates new form LoginForm
     */
    AreaImplements ai = new AreaImplements();
    List<Area> allAreas = ai.getAllAreas();
    CoffeeTableImplements ci = new CoffeeTableImplements();
    List<CoffeeTable> allCoffeeTablesWithoutAreId = ci.getAllCoffeeTablesWithoutAreId();
    CategoryImplements catei = new CategoryImplements();
    List<Category> listOfCategory = catei.listOfCategory();
    ProductImplements pi = new ProductImplements();
    List<Product> listOfProduct = pi.listOfProduct();

    public MainJFrame() {
        initComponents();
        this.setLocationRelativeTo(null);// center form in the screen
        loadImageNhanVien();
        loadImageSanPham();
        loadImageBanHang();
        loadImageKhongGianQuan();
        loadImageThongKe();
        loadImageThongTin();
        lblheadder.setText("HOME");
        username.setText(UserHelpers.User.getUsername().toUpperCase());
        loadAvatarCurrentUser();
    }
    help al;
    ChangePasswordJFrame cp;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabelClose = new javax.swing.JLabel();
        lblheadder = new javax.swing.JLabel();
        jLabelMin = new javax.swing.JLabel();
        avatar = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jpannhanvien = new javax.swing.JPanel();
        lblEmployee = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblSanPham = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblThucDon = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        areaTable = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblThongKe = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblThongTin = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        changePassword = new javax.swing.JButton();
        signOut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setPreferredSize(new java.awt.Dimension(1366, 768));

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

        lblheadder.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblheadder.setForeground(new java.awt.Color(255, 255, 255));
        lblheadder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblheadder.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabelMin.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelMin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMin.setText("-");
        jLabelMin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelMin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMinMouseClicked(evt);
            }
        });

        avatar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        avatar.setForeground(new java.awt.Color(255, 255, 255));
        avatar.setText("jLabel1");
        avatar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        username.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(avatar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(username)
                .addGap(18, 18, 18)
                .addComponent(lblheadder, javax.swing.GroupLayout.PREFERRED_SIZE, 1158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelMin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelClose)
                .addGap(990, 990, 990))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelClose)
                            .addComponent(jLabelMin, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(avatar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(username)
                        .addComponent(lblheadder, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(44, 62, 80));
        jPanel4.setPreferredSize(new java.awt.Dimension(1366, 700));

        jpannhanvien.setBackground(new java.awt.Color(44, 62, 80));
        jpannhanvien.setForeground(new java.awt.Color(204, 0, 0));

        lblEmployee.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblEmployee.setMaximumSize(new java.awt.Dimension(267, 160));
        lblEmployee.setMinimumSize(new java.awt.Dimension(267, 160));
        lblEmployee.setPreferredSize(new java.awt.Dimension(267, 160));
        lblEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEmployeeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblEmployeeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblEmployeeMouseExited(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("NHÂN VIÊN");

        javax.swing.GroupLayout jpannhanvienLayout = new javax.swing.GroupLayout(jpannhanvien);
        jpannhanvien.setLayout(jpannhanvienLayout);
        jpannhanvienLayout.setHorizontalGroup(
            jpannhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpannhanvienLayout.createSequentialGroup()
                .addGroup(jpannhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpannhanvienLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel3))
                    .addGroup(jpannhanvienLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpannhanvienLayout.setVerticalGroup(
            jpannhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpannhanvienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(44, 62, 80));
        jPanel5.setPreferredSize(new java.awt.Dimension(279, 172));

        lblSanPham.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSanPhamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblSanPhamMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblSanPhamMouseExited(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 204));
        jLabel5.setText("SẢN PHẨM");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel5))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(44, 62, 80));
        jPanel6.setPreferredSize(new java.awt.Dimension(279, 172));

        lblThucDon.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblThucDon.setPreferredSize(new java.awt.Dimension(267, 160));
        lblThucDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThucDonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblThucDonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblThucDonMouseExited(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 204));
        jLabel7.setText("POS BÁN HÀNG");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblThucDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblThucDon, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel7.setBackground(new java.awt.Color(44, 62, 80));
        jPanel7.setPreferredSize(new java.awt.Dimension(279, 172));

        areaTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        areaTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                areaTableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                areaTableMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                areaTableMouseExited(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 204));
        jLabel11.setText("KHÔNG GIAN QUÁN");
        jLabel11.setMaximumSize(new java.awt.Dimension(141, 29));
        jLabel11.setMinimumSize(new java.awt.Dimension(141, 29));
        jLabel11.setPreferredSize(new java.awt.Dimension(141, 29));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(areaTable, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(areaTable, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.setBackground(new java.awt.Color(44, 62, 80));
        jPanel8.setPreferredSize(new java.awt.Dimension(279, 172));

        lblThongKe.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThongKeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblThongKeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblThongKeMouseExited(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 204));
        jLabel13.setText("THỐNG KÊ");
        jLabel13.setMaximumSize(new java.awt.Dimension(141, 29));
        jLabel13.setPreferredSize(new java.awt.Dimension(141, 29));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel9.setBackground(new java.awt.Color(44, 62, 80));
        jPanel9.setPreferredSize(new java.awt.Dimension(279, 172));

        lblThongTin.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblThongTin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThongTinMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblThongTinMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblThongTinMouseExited(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 204));
        jLabel12.setText("KHÁCH HÀNG");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel12)
                .addContainerGap(62, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12))
        );

        jToolBar1.setBackground(new java.awt.Color(51, 51, 255));
        jToolBar1.setFloatable(false);
        jToolBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        changePassword.setBackground(new java.awt.Color(34, 167, 240));
        changePassword.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        changePassword.setForeground(new java.awt.Color(255, 255, 255));
        changePassword.setText("Đổi Mật Khẩu");
        changePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePasswordActionPerformed(evt);
            }
        });
        jToolBar1.add(changePassword);

        signOut.setBackground(new java.awt.Color(34, 167, 240));
        signOut.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        signOut.setForeground(new java.awt.Color(255, 255, 255));
        signOut.setText("Đăng Xuất");
        signOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signOutActionPerformed(evt);
            }
        });
        jToolBar1.add(signOut);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                    .addComponent(jpannhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(142, 142, 142)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                .addGap(128, 128, 128)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpannhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
                .addGap(102, 102, 102)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 184, Short.MAX_VALUE))
                .addContainerGap(146, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 2349, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void loadAvatarCurrentUser() {
        avatar.setSize(45, 45);
        avatar.setText("");
        if (UserHelpers.User.getImageUser() != null) {
            try {
                BufferedImage img = ImageIO.read(new File("logos", UserHelpers.User.getImageUser()));
                Image imageScale = img.getScaledInstance(avatar.getWidth(), avatar.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(imageScale);
                avatar.setIcon(imageIcon);
            } catch (Exception e) {
                avatar.setIcon(null);
                e.printStackTrace();
            }
        } else {
            try {
                BufferedImage img = ImageIO.read(new File("logos", "employs.jpg"));
                Image imageScale = img.getScaledInstance(avatar.getWidth(), avatar.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(imageScale);
                avatar.setIcon(imageIcon);
            } catch (Exception e) {
                avatar.setIcon(null);
                e.printStackTrace();
            }
        }
    }

    public void loadImageNhanVien() {
        try {
            BufferedImage img = ImageIO.read(new File("logos", "employs.jpg"));
            Image imageScale = img.getScaledInstance(lblEmployee.getWidth(), lblEmployee.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(imageScale);
            lblEmployee.setIcon(imageIcon);
        } catch (Exception e) {
            lblEmployee.setIcon(null);
            e.printStackTrace();
        }
    }

    public void loadImageSanPham() {
        try {
            BufferedImage img = ImageIO.read(new File("logos", "125-fall-in-love.jpg"));
            Image imageScale = img.getScaledInstance(lblSanPham.getWidth(), lblSanPham.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(imageScale);
            lblSanPham.setIcon(imageIcon);
        } catch (Exception e) {
            lblSanPham.setIcon(null);
            e.printStackTrace();
        }
    }

    public void loadImageBanHang() {
        try {
            BufferedImage img = ImageIO.read(new File("logos", "Post.png"));
            Image imageScale = img.getScaledInstance(lblThucDon.getWidth(), lblThucDon.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(imageScale);
            lblThucDon.setIcon(imageIcon);
        } catch (Exception e) {
            lblThucDon.setIcon(null);
            e.printStackTrace();
        }
    }

    public void loadImageKhongGianQuan() {
        try {
            BufferedImage img = ImageIO.read(new File("logos", "Loft.jpeg"));
            Image imageScale = img.getScaledInstance(areaTable.getWidth(), areaTable.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(imageScale);
            areaTable.setIcon(imageIcon);
        } catch (Exception e) {
            areaTable.setIcon(null);
            e.printStackTrace();
        }
    }

    public void loadImageThongKe() {
        try {
            BufferedImage img = ImageIO.read(new File("logos", "thongke.jpg"));
            Image imageScale = img.getScaledInstance(lblThongKe.getWidth(), lblThongKe.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(imageScale);
            lblThongKe.setIcon(imageIcon);
        } catch (Exception e) {
            lblThongKe.setIcon(null);
            e.printStackTrace();
        }
    }

    public void loadImageThongTin() {
        try {
            BufferedImage img = ImageIO.read(new File("logos", "thongtin.jpg"));
            Image imageScale = img.getScaledInstance(lblThongTin.getWidth(), lblThongTin.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(imageScale);
            lblThongTin.setIcon(imageIcon);
        } catch (Exception e) {
            lblThongTin.setIcon(null);
            e.printStackTrace();
        }
    }
    private void jLabelCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabelCloseMouseClicked

    private void jLabelMinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabelMinMouseClicked

    private void lblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSanPhamMouseClicked
        if (UserHelpers.User == null) {
            alert("Hãy đăng nhập");
        } else if (UserHelpers.User.getUserId() == 1) {
            ProductJFrame pj = new ProductJFrame();
            pj.setVisible(true);
            pj.setLocationRelativeTo(null);
            this.dispose();
        } else if (UserHelpers.User.getUserId() != 1) {
            alert("Chỉ admin mới có quyền truy cập");
        }

    }//GEN-LAST:event_lblSanPhamMouseClicked

    private void lblThucDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThucDonMouseClicked
        if (UserHelpers.User == null) {
            alert("Hãy đăng nhập");
        } else {
            int i = 0;
            if (i == 0 && !allAreas.isEmpty()) {
                i++;
                if (i == 1 && !allCoffeeTablesWithoutAreId.isEmpty()) {
                    i++;
                    if (i == 2 && !listOfCategory.isEmpty()) {
                        i++;
                        if (i == 3 && !listOfProduct.isEmpty()) {
                            POS p = new POS();
                            p.setVisible(true);
                            this.dispose();
                        } else {
                            alert("Hãy thêm sản phẩm");
                        }
                    } else {
                        alert("Hãy thêm loại sản phẩm");
                    }
                } else {
                    alert("Hãy thêm bàn");
                }
            } else {
                alert("Hãy thêm khu vực");
            }
        }
    }//GEN-LAST:event_lblThucDonMouseClicked

    private void areaTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_areaTableMouseClicked
        if (UserHelpers.User == null) {
            alert("Hãy đăng nhập");
        } else if (UserHelpers.User.getUserId() == 1) {
            AreaTable at = new AreaTable();
            at.setVisible(true);
            at.setLocationRelativeTo(null);
            this.dispose();
        } else if (UserHelpers.User.getUserId() != 1) {
            alert("Chỉ admin mới có quyền truy cập");
        }
    }//GEN-LAST:event_areaTableMouseClicked

    private void lblThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThongKeMouseClicked
        if (UserHelpers.User == null) {
            alert("Hãy đăng nhập");
        } else {
            StatisticJFrame sj = new StatisticJFrame();
            sj.setVisible(true);
            sj.setLocationRelativeTo(null);
            this.dispose();
        }
    }//GEN-LAST:event_lblThongKeMouseClicked

    private void lblThongTinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThongTinMouseClicked
        if (UserHelpers.User == null) {
            alert("Hãy đăng nhập");
        } else {
            CustomerJFrame c = new CustomerJFrame();
            c.setVisible(true);
            this.dispose();
        }

    }//GEN-LAST:event_lblThongTinMouseClicked

    private void lblEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEmployeeMouseClicked
        if (UserHelpers.User == null) {
            alert("Hãy đăng nhập");
        } else if (UserHelpers.User.getUserId() == 1) {
            openEmployee();
        } else if (UserHelpers.User.getUserId() != 1) {
            alert("Chỉ admin mới có quyền truy cập");
        }
    }//GEN-LAST:event_lblEmployeeMouseClicked
    public void openEmployee() {
        EmployeeJFrame ej = new EmployeeJFrame();
        ej.setVisible(true);
        ej.setLocationRelativeTo(null);
        this.dispose();
    }
    private void lblEmployeeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEmployeeMouseEntered
        lblEmployee.setBorder(BorderFactory.createEtchedBorder(Color.yellow, Color.black));
    }//GEN-LAST:event_lblEmployeeMouseEntered

    private void lblEmployeeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEmployeeMouseExited
        lblEmployee.setBorder(null);
      }//GEN-LAST:event_lblEmployeeMouseExited

    private void changePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePasswordActionPerformed
        if (UserHelpers.User != null) {
            changePassword();
            this.dispose();
        } else {
            alert("Hãy đăng nhập");
        }
    }//GEN-LAST:event_changePasswordActionPerformed

    private void signOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signOutActionPerformed
        int confirm = DialogHelpers.confirm(null, "Bạn có chắc muốn đăng xuất không?");
        if (confirm == JOptionPane.YES_OPTION) {
            Login lg = new Login();
            lg.setVisible(true);
            lg.setLocationRelativeTo(null);
            this.dispose();
        }
    }//GEN-LAST:event_signOutActionPerformed

    private void lblSanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSanPhamMouseEntered
        lblSanPham.setBorder(BorderFactory.createEtchedBorder(Color.yellow, Color.black));
    }//GEN-LAST:event_lblSanPhamMouseEntered

    private void lblSanPhamMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSanPhamMouseExited
        lblSanPham.setBorder(null);
    }//GEN-LAST:event_lblSanPhamMouseExited

    private void lblThongKeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThongKeMouseEntered
        lblThongKe.setBorder(BorderFactory.createEtchedBorder(Color.yellow, Color.black));
    }//GEN-LAST:event_lblThongKeMouseEntered

    private void areaTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_areaTableMouseEntered
        areaTable.setBorder(BorderFactory.createEtchedBorder(Color.yellow, Color.black));
    }//GEN-LAST:event_areaTableMouseEntered

    private void lblThucDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThucDonMouseEntered
        lblThucDon.setBorder(BorderFactory.createEtchedBorder(Color.yellow, Color.black));
    }//GEN-LAST:event_lblThucDonMouseEntered

    private void lblThongTinMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThongTinMouseEntered
        lblThongTin.setBorder(BorderFactory.createEtchedBorder(Color.yellow, Color.black));
    }//GEN-LAST:event_lblThongTinMouseEntered

    private void lblThongKeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThongKeMouseExited
        lblThongKe.setBorder(null);
    }//GEN-LAST:event_lblThongKeMouseExited

    private void lblThongTinMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThongTinMouseExited
        lblThongTin.setBorder(null);
    }//GEN-LAST:event_lblThongTinMouseExited

    private void areaTableMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_areaTableMouseExited
        areaTable.setBorder(null);
    }//GEN-LAST:event_areaTableMouseExited

    private void lblThucDonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThucDonMouseExited
        lblThucDon.setBorder(null);
    }//GEN-LAST:event_lblThucDonMouseExited

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
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new MainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel areaTable;
    private javax.swing.JLabel avatar;
    private javax.swing.JButton changePassword;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelClose;
    private javax.swing.JLabel jLabelMin;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel jpannhanvien;
    private javax.swing.JLabel lblEmployee;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblThongKe;
    private javax.swing.JLabel lblThongTin;
    private javax.swing.JLabel lblThucDon;
    private javax.swing.JLabel lblheadder;
    private javax.swing.JButton signOut;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
    private void alert(String message) {
        al = new help(message);
        al.setVisible(true);
    }

    private void changePassword() {
        cp = new ChangePasswordJFrame();
        cp.setVisible(true);
    }
}
