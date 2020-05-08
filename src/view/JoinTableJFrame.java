package view;

import DAO.AreaImplements;
import DAO.CoffeeTableImplements;
import DAO.CustomerImplements;
import DAO.OrderCoffeeImplements;
import DAO.OrderDetailImplements;
import DAO.ProductImplements;
import DAO.TableProductImplements;
import data.Area;
import data.CoffeeTable;
import data.Customer;
import data.OrderDetail;
import data.TableProduct;
import java.awt.HeadlessException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author omar
 */
public class JoinTableJFrame extends javax.swing.JFrame {

    /**
     * Creates new form LoginForm
     */
    AreaImplements ai = new AreaImplements();
    CoffeeTableImplements cti = new CoffeeTableImplements();
    OrderDetailImplements odi = new OrderDetailImplements();
    OrderCoffeeImplements oci = new OrderCoffeeImplements();
    TableProductImplements tpi = new TableProductImplements();
    CustomerImplements cusi = new CustomerImplements();
    ProductImplements pi = new ProductImplements();
    DefaultComboBoxModel<Area> comboxModelArea = new DefaultComboBoxModel();
    DefaultComboBoxModel<CoffeeTable> comboxModelTable = new DefaultComboBoxModel();
    help al;

    public JoinTableJFrame() {
        initComponents();
        this.setLocationRelativeTo(null);// center form in the screen
        loadInformationTable();
    }
    POS sell;
    String nameTable;
    String nameArea;
    int tableId;
    int rowCount;

    public JoinTableJFrame(POS sell, String nameTable, String nameArea, int tableId, int rowCount) {
        initComponents();
        this.setLocationRelativeTo(null);// center form in the screen
        this.sell = sell;
        this.nameTable = nameTable;
        this.nameArea = nameArea;
        this.tableId = tableId;
        this.rowCount = rowCount;
        loadInformationTable();
    }

    public void loadInformationTable() {
        comboxModelArea.removeAllElements();
        List<Area> allAreas = ai.getAllAreas();
        for (Area a : allAreas) {
            comboxModelArea.addElement(a);
        }
        comboxArea.setModel(comboxModelArea);

        comboxModelTable.removeAllElements();
        Area area = (Area) comboxArea.getSelectedItem();
        Area findArea = ai.findAreaByName(area.getAreaName());
        List<CoffeeTable> coffeeTableWithoutCustomer = cti.getAllCoffeeTablesWithoutSelectedTable(findArea.getAreaId(), tableId);
        for (CoffeeTable c : coffeeTableWithoutCustomer) {
            if (c.getStatus() == 1) {
                Customer customerByTableId = cusi.getCustomerByTableId(c.getTableId(), 0);
                if (customerByTableId != null && customerByTableId.getStatus() == 0) {
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
                            long diff = gioDen.getTime() - gioHienTai.getTime();
                            int minutes = (int) (diff / (60 * 1000));
                            if (minutes >= 30) {
                                comboxModelTable.addElement(c);
                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(POS.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        comboxModelTable.addElement(c);
                    }
                } else {
                    comboxModelTable.addElement(c);
                }
            }
        }
        comboxTable.setModel(comboxModelTable);

        selectedArea.setEditable(false);
        selectedTable.setEditable(false);
        selectedArea.setText(nameArea);
        selectedTable.setText(nameTable);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        comboxArea = new javax.swing.JComboBox();
        closeJFrame = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        joinTable = new javax.swing.JButton();
        selectedArea = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        selectedTable = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        comboxTable = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(248, 148, 6));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ghép Bàn");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(446, 446, 446)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 51, 51));

        comboxArea.setBackground(new java.awt.Color(44, 62, 80));
        comboxArea.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        comboxArea.setForeground(new java.awt.Color(255, 255, 255));
        comboxArea.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboxArea.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                itemchange(evt);
            }
        });
        comboxArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboxAreaActionPerformed(evt);
            }
        });

        closeJFrame.setBackground(new java.awt.Color(242, 38, 19));
        closeJFrame.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        closeJFrame.setForeground(new java.awt.Color(255, 255, 255));
        closeJFrame.setText("ĐÓNG GIAO DIỆN");
        closeJFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeJFrameActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(236, 240, 241));
        jLabel6.setText("Khu vực:");

        joinTable.setBackground(new java.awt.Color(34, 167, 240));
        joinTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        joinTable.setForeground(new java.awt.Color(255, 255, 255));
        joinTable.setText("Ghép");
        joinTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinTableActionPerformed(evt);
            }
        });

        selectedArea.setBackground(new java.awt.Color(108, 122, 137));
        selectedArea.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        selectedArea.setForeground(new java.awt.Color(228, 241, 254));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(236, 240, 241));
        jLabel4.setText("Khu vực đang chọn:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(236, 240, 241));
        jLabel5.setText("Bàn đang chọn:");

        selectedTable.setBackground(new java.awt.Color(108, 122, 137));
        selectedTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        selectedTable.setForeground(new java.awt.Color(228, 241, 254));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(236, 240, 241));
        jLabel7.setText("Chuyển bàn:");

        comboxTable.setBackground(new java.awt.Color(44, 62, 80));
        comboxTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        comboxTable.setForeground(new java.awt.Color(255, 255, 255));
        comboxTable.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboxTable.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboxTableitemchange(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(47, 47, 47)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectedArea, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectedTable, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(388, 444, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(joinTable)
                        .addGap(65, 65, 65)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(comboxArea, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboxTable, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(closeJFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(383, 383, 383))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(selectedArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(comboxArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboxTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(selectedTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 48, Short.MAX_VALUE)
                .addComponent(joinTable, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(closeJFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void joinTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinTableActionPerformed
        CoffeeTable selectedTable = (CoffeeTable) comboxTable.getSelectedItem();
        Customer customerByTableId = cusi.getCustomerByTableId(tableId, 0);
        if (rowCount > 0) {
            if (selectedTable != null) {
                List<TableProduct> tableProductCurrent = tpi.getTableProductByTableStatus(tableId, 0);
                List<TableProduct> tableProductSelectedTable = tpi.getTableProductByTableStatus(selectedTable.getTableId(), 0);
                int totalQuantity;
                boolean bl;
                OrderDetail orderDetailByTableStatus = odi.getOrderDetailByTableStatus(tableId, 0);
                int orderDetailId = orderDetailByTableStatus.getOrderDetailId();
                int orderCoffeeId = orderDetailByTableStatus.getOrderId().getOrderId();
                boolean deleteOrderDetail = odi.deleteOrderDetail(orderDetailId);
                if (deleteOrderDetail) {
                    oci.deleteOrderCoffee(orderCoffeeId);
                }
                if (!tableProductSelectedTable.isEmpty()) {
                    for (TableProduct tpc : tableProductCurrent) {
                        List<TableProduct> checkProductExistInTableProduct = tpi.checkProductExistInTableProduct(tpc.getProId().getProId(), selectedTable.getTableId());
                        if (!checkProductExistInTableProduct.isEmpty()) {
                            for (TableProduct tps : tableProductSelectedTable) {
                                if (tps.getProId().getProId() == tpc.getProId().getProId()) {
                                    totalQuantity = tpc.getQuantityOrder() + tps.getQuantityOrder();
                                    boolean updateQuantityTableProductClickTable = tpi.updateQuantityTableProductClickTable(tps.getTblProId(), totalQuantity);
                                    if (updateQuantityTableProductClickTable) {
                                        boolean deleteTableProduct = tpi.deleteTableProduct(tpc.getTblProId());
                                        if (deleteTableProduct) {
                                            boolean updateStatusCoffeeTable = false;
                                            if (customerByTableId != null) {
                                                updateStatusCoffeeTable = cti.updateStatusCoffeeTable(2, tableId);
                                            } else {
                                                updateStatusCoffeeTable = cti.updateStatusCoffeeTable(0, tableId);
                                            }
                                            if (updateStatusCoffeeTable) {
                                                bl = true;
                                            } else {
                                                bl = false;
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            TableProduct tp = new TableProduct(0, tpc.getProId(), selectedTable, tpc.getQuantityOrder(), 0, null, null);
                            boolean createNewTableProductWithQuantity = tpi.createNewTableProductWithQuantity(tp);
                            if (createNewTableProductWithQuantity) {
                                boolean deleteTableProduct = tpi.deleteTableProduct(tpc.getTblProId());
                                if (deleteTableProduct) {
                                    boolean updateStatusCoffeeTable = false;
                                    if (customerByTableId != null) {
                                        updateStatusCoffeeTable = cti.updateStatusCoffeeTable(2, tableId);
                                    } else {
                                        updateStatusCoffeeTable = cti.updateStatusCoffeeTable(0, tableId);
                                    }
                                    if (updateStatusCoffeeTable) {
                                        bl = true;
                                    } else {
                                        bl = false;
                                    }
                                }
                            }
                        }
                    }
                    if (bl = true) {
                        sell.reload();
                        int selectedIndex = comboxArea.getSelectedIndex();
                        Area findArea = ai.findAreaById(selectedTable.getAreaId().getAreaId());
                        sell.setNameTableArea(selectedTable.getTableName(), findArea.getAreaName(), selectedIndex);
                        sell.loadOrderDetail();
                        alert("Ghép bàn thành công");
                        this.dispose();
                    } else {
                        alert("Lỗi khi ghép bàn");
                    }
                } else {
                    for (TableProduct tpc : tableProductCurrent) {
                        TableProduct tp = new TableProduct(0, tpc.getProId(), selectedTable, tpc.getQuantityOrder(), 0, null, null);
                        boolean createNewTableProductWithQuantity = tpi.createNewTableProductWithQuantity(tp);
                        if (createNewTableProductWithQuantity) {
                            boolean deleteTableProduct = tpi.deleteTableProduct(tpc.getTblProId());
                            if (deleteTableProduct) {
                                boolean updateStatusCoffeeTable = false;
                                if (customerByTableId != null) {
                                    updateStatusCoffeeTable = cti.updateStatusCoffeeTable(2, tableId);
                                } else {
                                    updateStatusCoffeeTable = cti.updateStatusCoffeeTable(0, tableId);
                                }
                                if (updateStatusCoffeeTable) {
                                    bl = true;
                                } else {
                                    bl = false;
                                }
                            }
                        }
                    }
                    if (bl = true) {
                        sell.reload();
                        int selectedIndex = comboxArea.getSelectedIndex();
                        Area findArea = ai.findAreaById(selectedTable.getAreaId().getAreaId());
                        sell.setNameTableArea(selectedTable.getTableName(), findArea.getAreaName(), selectedIndex);
                        sell.loadOrderDetail();
                        alert("Ghép bàn thành công");
                        this.dispose();
                    } else {
                        alert("Lỗi khi ghép bàn");
                    }
                }

            } else {
                alert("Hãy chọn bàn");
            }
        } else {
            alert("Không thể ghép khi bàn chưa có sản phẩm");
        }
    }//GEN-LAST:event_joinTableActionPerformed

    private void itemchange(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_itemchange

    }//GEN-LAST:event_itemchange

    private void closeJFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeJFrameActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_closeJFrameActionPerformed

    private void comboxTableitemchange(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboxTableitemchange
        // TODO add your handling code here:
    }//GEN-LAST:event_comboxTableitemchange

    private void comboxAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboxAreaActionPerformed
        comboxModelTable.removeAllElements();
        Area selectedArea = (Area) comboxArea.getSelectedItem();
        Area findArea = ai.findAreaByName(selectedArea.getAreaName());
        List<CoffeeTable> coffeeTableWithoutCustomer = cti.getAllCoffeeTablesWithoutSelectedTable(findArea.getAreaId(), tableId);
        for (CoffeeTable c : coffeeTableWithoutCustomer) {
            if (c.getStatus() == 1) {
                Customer customerByTableId = cusi.getCustomerByTableId(c.getTableId(), 0);
                if (customerByTableId != null && customerByTableId.getStatus() == 0) {
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
                            long diff = gioDen.getTime() - gioHienTai.getTime();
                            int minutes = (int) (diff / (60 * 1000));
                            if (minutes >= 30) {
                                comboxModelTable.addElement(c);
                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(POS.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        comboxModelTable.addElement(c);
                    }
                } else {
                    comboxModelTable.addElement(c);
                }
            }
        }
        comboxTable.setModel(comboxModelTable);
    }//GEN-LAST:event_comboxAreaActionPerformed
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
            java.util.logging.Logger.getLogger(JoinTableJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JoinTableJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JoinTableJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JoinTableJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new JoinTableJFrame().setVisible(true);
            }
        });
    }

    private void alert(String message) {
        al = new help(message);
        al.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeJFrame;
    private javax.swing.JComboBox comboxArea;
    private javax.swing.JComboBox comboxTable;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton joinTable;
    private javax.swing.JTextField selectedArea;
    private javax.swing.JTextField selectedTable;
    // End of variables declaration//GEN-END:variables

}
