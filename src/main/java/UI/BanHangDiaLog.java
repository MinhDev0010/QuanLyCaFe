/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.HDCTDao;
import DAO.HoaDonDao;
import DAO.KhachHangDAO;
import DAO.LoaiSPDao;
import DAO.SanPhamDao;
import ENTITY.HoaDon;
import ENTITY.KhachHang;
import ENTITY.LoaiSP;
import ENTITY.SanPham;
import UTILS.Auth;
import UTILS.MsgBox;
import UTILS.XDate;
import UTILS.XDecimal;
import UTILS.XImage;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class BanHangDiaLog extends java.awt.Dialog {

    SanPhamDao spdao = new SanPhamDao();
    HoaDonDao hddao = new HoaDonDao();
    HDCTDao hdctdao = new HDCTDao();
    KhachHangDAO khdao = new KhachHangDAO();
    LoaiSPDao lspdao = new LoaiSPDao();
    JButton[] btn;
    private ImageIcon icon1 = new ImageIcon(getClass().getResource("/images/coffee-table (1).png"));

    /**
     * Creates new form BanHangDiaLog
     */
    public BanHangDiaLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
//        setTotal();
    }

    void init() {
        setLocationRelativeTo(null);
        setTitle("Quản Lý Bán Hàng");
        setIconImage(XImage.getAppIcon());
        JPANEBAN.setLayout(new GridLayout(4, 3));
        JPANEBAN.repaint();
        filldatatableban(hddao.selectChuaHoadon());
//        fillComboBoxSP();
        fillComboBoxKH();
        clear();
        setbackground();
        setIcon();
        setnametabs();
        fillComboBoxTenLSP();
    }

    void filldatatableban(List<HoaDon> ls) {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        for (HoaDon l : ls) {
            Object[] row = new Object[6];
            row[0] = l.getId();
            row[1] = l.getBan();
            row[2] = l.getTenKH();
            row[3] = l.getTenSP();
            row[4] = l.getSoluong();
            row[5] = l.getTongTien();
            model.addRow(row);
        }
    }

    void selectComboBoxSP() {
        SanPham sp = (SanPham) cbbTenSanPham.getSelectedItem();
        if (sp != null) {
            txtGiaTien.setText(XDecimal.formatr.format(sp.getGiaTien()));
        }

    }

    void selectComboBoxKhachHang() {
        KhachHang kh = (KhachHang) cbbTenKhachHang.getSelectedItem();
    }

    void fillComboBoxKH() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbTenKhachHang.getModel();
        model.removeAllElements();
        try {
            List<KhachHang> list = khdao.selectAll();
            if (!list.isEmpty()) {
                for (KhachHang sp : list) {
                    model.addElement(sp);
                }
            }
        } catch (Exception e) {
            MsgBox.alert(this, e.getMessage());
        }
    }

    void fillComboBoxSP() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbTenSanPham.getModel();
        model.removeAllElements();
        try {
            LoaiSP tenlsp = (LoaiSP) cbblsp.getSelectedItem();
            List<SanPham> list = spdao.selectTenSPByMaLSP(tenlsp.getMaLSP());
            if (!list.isEmpty()) {
                for (SanPham sp : list) {
                    model.addElement(sp);
                }
            }
        } catch (Exception e) {
//            MsgBox.alert(this, e.getMessage());
            e.printStackTrace();
        }
    }

    void fillComboBoxTenLSP() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbblsp.getModel();
        model.removeAllElements();
        try {
            List<LoaiSP> list = lspdao.selectAll();
            if (!list.isEmpty()) {
                for (LoaiSP sp : list) {
                    model.addElement(sp);
                }
            }
        } catch (Exception e) {
//            MsgBox.alert(this, e.getMessage());
            e.printStackTrace();
        }
    }

    HoaDon getModelHoaDon() {
        HoaDon model = new HoaDon();
        KhachHang kh = (KhachHang) cbbTenKhachHang.getSelectedItem();
        SanPham sp = (SanPham) cbbTenSanPham.getSelectedItem();
        model.setIdHoaDon(Integer.parseInt(txtIDHoaDon.getText()));
        model.setMaKH(kh.getMaKH());
        model.setMaNV(txtMANV.getText());
        model.setBan(txtBan.getText());
        model.setMaSP(sp.getMaSP());
        model.setSoluong(Integer.parseInt(txtSoluong.getText()));
        model.setTongTien(Double.parseDouble(lbltongtien.getText()));
        return model;
    }

    void clear() {
        HoaDon model = new HoaDon();
        SanPham sp = (SanPham) cbbTenSanPham.getSelectedItem();
        if (sp != null) {
            cbbTenSanPham.setSelectedIndex(0);
        }
        model.setMaNV(Auth.user.getMaNV());
        model.setNgayVao(XDate.now());
        txtIDHoaDon.setText("");
        txtGiaTien.setText("");
        txtSoluong.setText("");
        txtBan.setText("");
        this.setModel(model);
    }

    void setModel(HoaDon model) {
        cbbTenSanPham.setToolTipText(String.valueOf(model.getId()));
        cbbTenSanPham.setSelectedItem(spdao.selectByID(model.getMaSP()));
        txtNgayOder.setText(XDate.toString(model.getNgayVao()));
        txtGiaTien.setText(String.valueOf(model.getTongTien()));
        txtMANV.setText(model.getMaNV());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        JPANEBAN = new javax.swing.JPanel();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btn10 = new javax.swing.JButton();
        btn11 = new javax.swing.JButton();
        btn12 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnODER = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        BtnHoaDon = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbbTenKhachHang = new javax.swing.JComboBox<>();
        txtIDHoaDon = new javax.swing.JTextField();
        lll = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMANV = new javax.swing.JTextField();
        cbbTenSanPham = new javax.swing.JComboBox<>();
        txtGiaTien = new javax.swing.JTextField();
        txtNgayOder = new javax.swing.JTextField();
        lblTrangThai = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtBan = new javax.swing.JTextField();
        lbltongtien = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblTr = new javax.swing.JLabel();
        txtSoluong = new javax.swing.JTextField();
        cbblsp = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtHoaDon = new javax.swing.JTextArea();
        btnInHoaDon = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản lý bán hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 0, 255))); // NOI18N

        JPANEBAN.setBackground(new java.awt.Color(255, 255, 255));
        JPANEBAN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bàn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        JPANEBAN.setLayout(new java.awt.GridLayout(1, 0));

        btn1.setText("Bàn 1");
        btn1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        JPANEBAN.add(btn1);

        btn2.setText("Bàn 2");
        btn2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });
        JPANEBAN.add(btn2);

        btn3.setText("Bàn 3");
        btn3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        JPANEBAN.add(btn3);

        btn4.setText("Bàn 4");
        btn4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
        JPANEBAN.add(btn4);

        btn6.setText("Bàn 5");
        btn6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });
        JPANEBAN.add(btn6);

        btn5.setText("Bàn 6");
        btn5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        JPANEBAN.add(btn5);

        btn7.setText("Bàn 7");
        btn7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });
        JPANEBAN.add(btn7);

        btn8.setText("Bàn 8");
        btn8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });
        JPANEBAN.add(btn8);

        btn9.setText("Bàn 9");
        btn9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });
        JPANEBAN.add(btn9);

        btn10.setText("Bàn 10");
        btn10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn10ActionPerformed(evt);
            }
        });
        JPANEBAN.add(btn10);

        btn11.setText("Bàn 11");
        btn11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn11ActionPerformed(evt);
            }
        });
        JPANEBAN.add(btn11);

        btn12.setText("Bàn 12");
        btn12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn12ActionPerformed(evt);
            }
        });
        JPANEBAN.add(btn12);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bán Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tblHoaDon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Bàn", "Tên Khách Hàng", "Tên Sản Phẩm", "Số Lượng", "Tổng Tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);
        if (tblHoaDon.getColumnModel().getColumnCount() > 0) {
            tblHoaDon.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblHoaDon.getColumnModel().getColumn(1).setPreferredWidth(25);
            tblHoaDon.getColumnModel().getColumn(3).setPreferredWidth(120);
            tblHoaDon.getColumnModel().getColumn(4).setPreferredWidth(30);
            tblHoaDon.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnODER.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnODER.setIcon(new javax.swing.ImageIcon("E:\\Java\\duan1\\src\\main\\Resources\\images\\Create.png")); // NOI18N
        btnODER.setText("Oder");
        btnODER.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnODER.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnODER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnODERActionPerformed(evt);
            }
        });

        btnThanhToan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThanhToan.setIcon(new javax.swing.ImageIcon("E:\\Java\\duan1\\src\\main\\Resources\\images\\Funny.png")); // NOI18N
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThanhToan.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        BtnHoaDon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BtnHoaDon.setIcon(new javax.swing.ImageIcon("E:\\Java\\duan1\\src\\main\\Resources\\images\\Lists.png")); // NOI18N
        BtnHoaDon.setText("Hoá Đơn");
        BtnHoaDon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnHoaDon.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        BtnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnODER, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(BtnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnODER, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BtnHoaDon))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Giá Tiền");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Tên Sản Phẩm");

        cbbTenKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTenKhachHangActionPerformed(evt);
            }
        });

        lll.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lll.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lll.setText("Thành Tiền");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Số Lượng");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Ngày Oder");

        txtMANV.setEditable(false);
        txtMANV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMANVActionPerformed(evt);
            }
        });

        cbbTenSanPham.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                cbbTenSanPhamCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        cbbTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTenSanPhamActionPerformed(evt);
            }
        });

        txtGiaTien.setEditable(false);

        lblTrangThai.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTrangThai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTrangThai.setText("Cập Nhật");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Bàn");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Tên Khách Hàng");
        jLabel5.setToolTipText("");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Mã Nhân Viên");
        jLabel8.setToolTipText("");

        txtBan.setEditable(false);

        lbltongtien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltongtien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbltongtien.setText("0 VNĐ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Loại sản phẩm");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("ID Hoá Đơn");
        jLabel10.setToolTipText("");

        lblTr.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTr.setText("Trạng Thái");

        txtSoluong.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSoluongCaretUpdate(evt);
            }
        });

        cbblsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbblspActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addGap(58, 58, 58)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBan)
                                    .addComponent(txtGiaTien)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(lblTr, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32)
                                        .addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(lll, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(lbltongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 8, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel9))
                                .addGap(13, 13, 13)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbTenKhachHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbblsp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel7))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbTenSanPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSoluong))))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIDHoaDon)
                            .addComponent(txtMANV))
                        .addGap(12, 12, 12))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(43, 43, 43)
                        .addComponent(txtNgayOder)
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDHoaDon)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtMANV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbbTenKhachHang))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbblsp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbTenSanPham)
                    .addComponent(jLabel2))
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoluong, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNgayOder))
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBan)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaTien)
                    .addComponent(jLabel6))
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbltongtien)
                    .addComponent(lll))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTrangThai)
                    .addComponent(lblTr))
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(JPANEBAN, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(9, 9, 9))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JPANEBAN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabs.addTab("tab1", jPanel1);

        txtHoaDon.setColumns(20);
        txtHoaDon.setRows(5);
        txtHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtHoaDon.setDoubleBuffered(true);
        jScrollPane2.setViewportView(txtHoaDon);

        btnInHoaDon.setText("In Hoa Don");
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnInHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        tabs.addTab("tab2", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void Oder() {
        try {
            String[] btn = {btn1.getText(), btn2.getText(), btn3.getText(), btn4.getText(), btn5.getText(),
                btn6.getText(), btn7.getText(), btn8.getText(), btn9.getText(), btn10.getText(), btn11.getText(), btn12.getText()};
            JButton[] btnn = {btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12};
            for (int j = 0; j < btn.length; j++) {
                if (txtBan.getText().equals(btn[j])) {
                    btnn[j].setBackground(Color.pink);
                    lblTrangThai.setText("Đang Có Khách");
                }
            }
            Insert();
            Hoadon();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void Insert() {
        HoaDon model = getModelHoaDon();
        hddao.InsertSoluong(model);
        MsgBox.alert(this, "Oder thành công!!");
        filldatatableban(hddao.selectChuaHoadon());
        clear();

    }

    boolean isvalidated() {
        SanPham sp = (SanPham) cbbTenSanPham.getSelectedItem();
        if (sp.getSoluong() == 0) {
            MsgBox.alert(this, "Hết hàng!!!");
            return false;
        }
        if (Integer.parseInt(txtSoluong.getText()) > sp.getSoluong()) {
            MsgBox.alert(this, "Số lượng không đủ!!");
            return false;
        }
        if (txtIDHoaDon.getText().isEmpty()) {
            MsgBox.alert(this, "Ban chua nhap id!!");
            return false;
        } else if (txtSoluong.getText().isEmpty()) {
            MsgBox.alert(this, "Ban chua nhap so luong!!");
            return false;
        } else if (!txtSoluong.getText().matches("^([1-9])+([0-9])*$")) {
            MsgBox.alert(this, "Số lượng lớn hơn 0!!");
            return false;
        } else {
            return true;
        }

    }

    boolean isthanhtoan() {
        if (txtBan.getText().isEmpty()) {
            MsgBox.alert(this, "Ban chua Chon ban!!");
            return false;
        }
        return true;
    }

    boolean isprinthoadon() {
        if (txtIDHoaDon.getText().isEmpty()) {
            MsgBox.alert(this, "Ban chua nhap id");
            return false;
        }
        return true;
    }

    void ThanhToan() {
        String[] btn = {btn1.getText(), btn2.getText(), btn3.getText(), btn4.getText(), btn5.getText(), btn6.getText(), 
            btn7.getText(), btn8.getText(), btn9.getText(), btn10.getText(), btn11.getText(), btn12.getText()};
        JButton[] btnn = {btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12};
        for (int j = 0; j < btn.length; j++) {
            if (txtBan.getText().equals(btn[j])) {
                btnn[j].setBackground(new Color(240, 240, 240));
            }
        }
        Update();
    }

    HoaDon getModelUpdate() {
        HoaDon model = new HoaDon();
        model.setBan(txtBan.getText());
        model.setTrangThai("Đã Thanh Toán");
        return model;
    }

    int Update() {
        HoaDon model = getModelUpdate();
        hddao.update(model);
        filldatatableban(hddao.selectChuaHoadon());
        clear();
        return 0;
    }

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        if (isthanhtoan()) {
            if (MsgBox.confirm(this, "Bạn có muốn thanh toán " + txtBan.getText() + " hay không??")) {
                ThanhToan();
            }

        }

    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        txtBan.setText(btn1.getText());
        if (btn1.getBackground() == Color.pink) {
            lblTrangThai.setText("Đang có Khách");
        } else {
            lblTrangThai.setText("Cập Nhật");
        }
        filldatatableban(hddao.selectallHoadon(btn1.getText()));
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        txtBan.setText(btn3.getText());
        if (btn3.getBackground() == Color.pink) {
            lblTrangThai.setText("Đang có Khách");
        } else {
            lblTrangThai.setText("Cập Nhật");
        }
        filldatatableban(hddao.selectallHoadon(btn3.getText()));
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        txtBan.setText(btn2.getText());
        if (btn2.getBackground() == Color.pink) {
            lblTrangThai.setText("Đang có Khách");
        } else {
            lblTrangThai.setText("Cập Nhật");
        }
        filldatatableban(hddao.selectallHoadon(btn2.getText()));
    }//GEN-LAST:event_btn2ActionPerformed

    private void btnODERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnODERActionPerformed
        if (isvalidated()) {
            Oder();
        }

    }//GEN-LAST:event_btnODERActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        // TODO add your handling code here:
        txtBan.setText(btn4.getText());
        if (btn4.getBackground() == Color.pink) {
            lblTrangThai.setText("Đang có Khách");
        } else {
            lblTrangThai.setText("Cập Nhật");
        }
        filldatatableban(hddao.selectallHoadon(btn4.getText()));
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        // TODO add your handling code here:
        txtBan.setText(btn5.getText());
        if (btn5.getBackground() == Color.pink) {
            lblTrangThai.setText("Đang có Khách");
        } else {
            lblTrangThai.setText("Cập Nhật");
        }
        filldatatableban(hddao.selectallHoadon(btn5.getText()));
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        // TODO add your handling code here:
        txtBan.setText(btn6.getText());
        if (btn6.getBackground() == Color.pink) {
            lblTrangThai.setText("Đang có Khách");
        } else {
            lblTrangThai.setText("Cập Nhật");
        }
        filldatatableban(hddao.selectallHoadon(btn6.getText()));
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        // TODO add your handling code here:
        txtBan.setText(btn7.getText());
        if (btn7.getBackground() == Color.pink) {
            lblTrangThai.setText("Đang có Khách");
        } else {
            lblTrangThai.setText("Cập Nhật");
        }
        filldatatableban(hddao.selectallHoadon(btn7.getText()));
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        // TODO add your handling code here:
        txtBan.setText(btn8.getText());
        if (btn8.getBackground() == Color.pink) {
            lblTrangThai.setText("Đang có Khách");
        } else {
            lblTrangThai.setText("Cập Nhật");
        }
        filldatatableban(hddao.selectallHoadon(btn8.getText()));
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        // TODO add your handling code here:
        txtBan.setText(btn9.getText());
        if (btn9.getBackground() == Color.pink) {
            lblTrangThai.setText("Đang có Khách");
        } else {
            lblTrangThai.setText("Cập Nhật");
        }
        filldatatableban(hddao.selectallHoadon(btn9.getText()));
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn10ActionPerformed
        // TODO add your handling code here:
        txtBan.setText(btn10.getText());
        if (btn10.getBackground() == Color.pink) {
            lblTrangThai.setText("Đang có Khách");
        } else {
            lblTrangThai.setText("Cập Nhật");
        }
        filldatatableban(hddao.selectallHoadon(btn10.getText()));
    }//GEN-LAST:event_btn10ActionPerformed

    private void btn11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn11ActionPerformed
        // TODO add your handling code here:
        txtBan.setText(btn11.getText());
        if (btn11.getBackground() == Color.pink) {
            lblTrangThai.setText("Đang có Khách");
        } else {
            lblTrangThai.setText("Cập Nhật");
        }
        filldatatableban(hddao.selectallHoadon(btn11.getText()));
    }//GEN-LAST:event_btn11ActionPerformed

    private void btn12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn12ActionPerformed
        // TODO add your handling code here:
        txtBan.setText(btn12.getText());
        if (btn12.getBackground() == Color.pink) {
            lblTrangThai.setText("Đang có Khách");
        } else {
            lblTrangThai.setText("Cập Nhật");
        }
        filldatatableban(hddao.selectallHoadon(btn12.getText()));
    }//GEN-LAST:event_btn12ActionPerformed

    private void cbbTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTenSanPhamActionPerformed
        // TODO add your handling code here:
        selectComboBoxSP();
    }//GEN-LAST:event_cbbTenSanPhamActionPerformed

    private void cbbTenKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTenKhachHangActionPerformed
        // TODO add your handling code here:
        selectComboBoxKhachHang();
    }//GEN-LAST:event_cbbTenKhachHangActionPerformed

    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonActionPerformed
        // TODO add your handling code here:
        try {
            txtHoaDon.print();
            txtHoaDon.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnInHoaDonActionPerformed

    private void BtnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHoaDonActionPerformed
        // TODO add your handling code here:
        if (isprinthoadon()) {
            tabs.setSelectedIndex(1);
            Hoadon();

        }

    }//GEN-LAST:event_BtnHoaDonActionPerformed

    private void txtSoluongCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSoluongCaretUpdate
        // TODO add your handling code here:
        setTotal();
    }//GEN-LAST:event_txtSoluongCaretUpdate

    private void txtMANVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMANVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMANVActionPerformed

    private void cbblspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbblspActionPerformed
        // TODO add your handling code here:
        fillComboBoxSP();
    }//GEN-LAST:event_cbblspActionPerformed

    private void cbbTenSanPhamCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cbbTenSanPhamCaretPositionChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_cbbTenSanPhamCaretPositionChanged
    void setbackground() {
        btn1.setBackground(new Color(240, 240, 240));
        btn2.setBackground(new Color(240, 240, 240));
        btn3.setBackground(new Color(240, 240, 240));
        btn4.setBackground(new Color(240, 240, 240));
        btn5.setBackground(new Color(240, 240, 240));
        btn6.setBackground(new Color(240, 240, 240));
        btn7.setBackground(new Color(240, 240, 240));
        btn8.setBackground(new Color(240, 240, 240));
        btn9.setBackground(new Color(240, 240, 240));
        btn10.setBackground(new Color(240, 240, 240));
        btn11.setBackground(new Color(240, 240, 240));
        btn12.setBackground(new Color(240, 240, 240));
    }

    public void Hoadon() {
        HoaDon hd = hddao.printHoaDon(Integer.parseInt(txtIDHoaDon.getText()));
        txtHoaDon.append("\t Hoá Đơn \n\n"
                + "Mã Hoá Đơn: \t\t\t" + hd.getId()
                + "\n=========================================\n"
                + "Tên Sản Phẩm \t\t\t" + hddao.SP(Integer.parseInt(txtIDHoaDon.getText())) + "\n\n"
                + "Giá Tiền \t\t\t" + hddao.GT(Integer.parseInt(txtIDHoaDon.getText())) + "\n\n"
                + "Số Lượng: \t\t\t" + hddao.soluong(Integer.parseInt(txtIDHoaDon.getText())) + "\n\n"
                + "Ngày Mua: \t\t\t" + hd.getNgayThanhToan() + "\n\n"
                + "Tên Khách Hàng: \t\t" + cbbTenKhachHang.getSelectedItem() + "\n\n"
                + "Trạng Thái: \t\t\t" + "Đã Thanh Toán" + "\n\n"
                + "Tổng Tiền: \t\t\t" + XDecimal.formatr.format(hd.getTongTien()) + "\n\n"
                + "==========================================="
        );
    }

    void setnametabs() {
        tabs.setTitleAt(0, "Bán Hàng");
        tabs.setTitleAt(1, "Hoá Đơn");
    }

    void setIcon() {
        btn1.setIcon(icon1);
        btn2.setIcon(icon1);
        btn3.setIcon(icon1);
        btn4.setIcon(icon1);
        btn5.setIcon(icon1);
        btn6.setIcon(icon1);
        btn7.setIcon(icon1);
        btn8.setIcon(icon1);
        btn9.setIcon(icon1);
        btn10.setIcon(icon1);
        btn11.setIcon(icon1);
        btn12.setIcon(icon1);
    }

    void setTotal() {
//        new Thread() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
        SanPham sp = (SanPham) cbbTenSanPham.getSelectedItem();
        if (!txtSoluong.getText().isEmpty() && txtSoluong.getText().matches("^([1-9])+([0-9])*$")) {
            int count = Integer.parseInt(txtSoluong.getText());
            double total = (count * sp.getGiaTien());
            lbltongtien.setText(XDecimal.format.format(total));
        } else {
            lbltongtien.setText("0 VND");
        }
//                        Thread.sleep(1000);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(BanHangDiaLog.class.getName()).log(Level.SEVERE, null, ex);
//                    }
    }
//            }
//
//        }.start();
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BanHangDiaLog dialog = new BanHangDiaLog(new java.awt.Frame(), true);
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
    private javax.swing.JButton BtnHoaDon;
    private javax.swing.JPanel JPANEBAN;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn10;
    private javax.swing.JButton btn11;
    private javax.swing.JButton btn12;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnODER;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JComboBox<String> cbbTenKhachHang;
    private javax.swing.JComboBox<String> cbbTenSanPham;
    private javax.swing.JComboBox<String> cbblsp;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTr;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JLabel lbltongtien;
    private javax.swing.JLabel lll;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtBan;
    private javax.swing.JTextField txtGiaTien;
    private javax.swing.JTextArea txtHoaDon;
    private javax.swing.JTextField txtIDHoaDon;
    private javax.swing.JTextField txtMANV;
    private javax.swing.JTextField txtNgayOder;
    private javax.swing.JTextField txtSoluong;
    // End of variables declaration//GEN-END:variables
}
