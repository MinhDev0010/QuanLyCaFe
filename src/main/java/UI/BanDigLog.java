/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.BanDao;
import ENTITY.Ban;
import UTILS.MsgBox;
import UTILS.Xban;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class BanDigLog extends java.awt.Dialog {

    BanDao dao = new BanDao();
    JButton btn;
    int location = 0;
    int surplus = 0;
    int width = 120;
    int height = 120;
    int columns = 0;
    int count = 0;
    public static int rows = 0;
    private ImageIcon icon3 = new ImageIcon(getClass().getResource("/images/coffee-table (1).png"));
    int index = 0;

    /**
     * Creates new form Ban
     */
    public BanDigLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        loadBan();
        filldataBan();

    }

    ImageIcon icon() {
        InputStream input = BanDigLog.class.getResourceAsStream("/images/coffee-table (1).png");
        BufferedImage but = null;
        try {
            but = ImageIO.read(input);
        } catch (IOException ex) {

        }
        return new ImageIcon(but);
    }

    void loadBan() {
        JButton btn1 = new JButton();
        btn1.setLocation(0, 0);
        btn1.setSize(0, 0);
        int w = (pannelBan.getWidth() / width);
        columns = w < dao.TongBan() ? dao.TongBan() - (dao.TongBan() - w) + 1 : w == dao.TongBan() ? w : dao.TongBan();
        rows = w < dao.TongBan() ? ((columns < dao.TongBan() + 1 && (dao.TongBan() - w) == 1) ? 2 : dao.TongBan() - w) : 0;
        surplus = pannelBan.getWidth() - ((columns - 1) * width);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (count < dao.TongBan()) {
                    btn = new JButton(dao.selectAll().get(count).getTenBan());
                    btn.setSize(width, height);
                    btn.setIcon(icon());
                    btn.setBackground(Color.decode("#ccffcc"));
                    btn.setVerticalTextPosition(JButton.BOTTOM);
                    btn.setHorizontalTextPosition(JButton.CENTER);
                    btn.setLocation(new Point(btn1.getLocation().x + btn1.getWidth(), btn1.getLocation().y));
                    pannelBan.add(btn);
                    pannelBan.setPreferredSize(new Dimension(pannelBan.getWidth(), pannelBan.getHeight()));
                    btn1 = btn;
                    pannelBan.validate();
                    pannelBan.repaint();

                }
                count++;
            }

            btn1.setSize(0, 0);
            btn1.setLocation(new Point(0, btn1.getLocation().y + height));
        }
    }

    void filldataBan() {
        DefaultTableModel model = (DefaultTableModel) TblBan.getModel();
        model.setRowCount(0);
        List<Ban> ls = dao.selectAll();
        for (Ban l : ls) {
            Object[] row = {
                l.getIdBan(),
                l.getTenBan()
            };
            model.addRow(row);
        }
    }

    Ban getModel() {
        Ban ban = new Ban();
//        ban.setIdBan(Integer.parseInt(txtid.getText()));
        ban.setTenBan(txtTenBan.getText());
        return ban;
    }

    Ban getModelUpdate() {
        Ban ban = new Ban();
        ban.setIdBan(Integer.parseInt(txtid.getText()));
        ban.setTenBan(txtTenBan.getText());
        return ban;
    }

    int Insert() {
        Ban bn = getModel();
        try {
            dao.insert(bn);
            MsgBox.alert(this, "Thêm Mới Thành Công");
            filldataBan();
//            loadBan();
            clear();
        } catch (Exception e) {
            e.getMessage();
        }

        return 0;
    }

    int Update() {
        Ban kh = getModelUpdate();
        try {
            MsgBox.alert(this, "Sửa Thành Công");
            filldataBan();
//            loadBan();
            clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dao.update(kh);

        return 0;
    }

    void delete() {
        Integer Malsp = Integer.parseInt(txtid.getText());
        if (MsgBox.confirm(this, "Bạn thực sự muốn loại sản phẩm này?")) {
            try {
                dao.delete(Malsp);
//                this.loadBan();
                filldataBan();
                this.clear();
                MsgBox.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Loại sản phẩm này đang được sử dụng");
            }
        }
    }

    void clear() {
        Ban kh = new Ban();
        this.setModel(kh);
    }

    void setModel(Ban idban) {
        txtid.setText(String.valueOf(idban.getIdBan()));
        txtTenBan.setText(idban.getTenBan());
    }

    void edit() {
        Integer model = (Integer) TblBan.getValueAt(this.index, 0);
        Ban kh = dao.selectByID(model);
        try {
            if (model != null) {
                this.setModel(kh);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    boolean isValidated() {
        if (txtTenBan.getText().isEmpty()) {
            MsgBox.alert(this, "Không để trống tên Bàn!!!");
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pannelBan = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtid = new javax.swing.JTextField();
        txtTenBan = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblBan = new javax.swing.JTable();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        pannelBan.setBackground(new java.awt.Color(204, 204, 255));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cập Nhật", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 255))); // NOI18N

        txtid.setEditable(false);
        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("MaBan");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Tên Bàn");
        jLabel2.setToolTipText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTenBan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(txtid))
                .addGap(26, 26, 26))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenBan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        btnThem.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon("E:\\Java\\duan1\\src\\main\\Resources\\images\\Create.png")); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThem.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnMoi.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnMoi.setIcon(new javax.swing.ImageIcon("E:\\Java\\duan1\\src\\main\\Resources\\images\\Refresh.png")); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMoi.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon("E:\\Java\\duan1\\src\\main\\Resources\\images\\Delete.png")); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnXoa.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon("E:\\Java\\duan1\\src\\main\\Resources\\images\\Edit.png")); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSua.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMoi)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnMoi)
                    .addComponent(btnThem))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        TblBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Ban", "Ten Ban"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblBanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblBan);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pannelBan, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pannelBan, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (isValidated()) {
            Insert();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (isValidated()) {
            Update();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void TblBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblBanMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            index = TblBan.rowAtPoint(evt.getPoint());
            if (index >= 0) {
                this.edit();
            }
        }
    }//GEN-LAST:event_TblBanMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BanDigLog dialog = new BanDigLog(new java.awt.Frame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TblBan;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pannelBan;
    private javax.swing.JTextField txtTenBan;
    private javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
}
