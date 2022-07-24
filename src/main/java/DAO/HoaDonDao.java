/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ENTITY.HoaDon;
import UTILS.XDecimal;
import UTILS.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class HoaDonDao extends MainDAO<HoaDon, Integer> {

    final String INSERT_SQL = "insert into HoaDon(id,MaNV,MaKH,Ban) values (?,?,?,?)";
    final String UPDATE_SQL = "Update HoaDon set TrangThai = ? where Ban = ?";
    final String DELETE_SQL = "delete from HoaDon";
    final String SELECALL_SQL = "select * from HoaDon";
    final String SELLECTBYID_SQL = "Select * from HoaDon where id=?";

    public int InsertSoluong(HoaDon hd) {
        String sql = "{Call sp_TruSoLuong(?,?,?,?,?,?,?)}";
        return XJdbc.update(sql,
                hd.getIdHoaDon(),
                hd.getMaNV(),
                hd.getMaKH(),
                hd.getBan(),
                hd.getMaSP(),
                hd.getSoluong(),
                hd.getTongTien()
        );
    }

    @Override
    public int insert(HoaDon entity) {
        return XJdbc.update(INSERT_SQL,
                entity.getIdHoaDon(),
                entity.getMaNV(),
                entity.getMaKH(),
                entity.getBan()
        );
    }

    @Override
    public int update(HoaDon entity) {
        return XJdbc.update(UPDATE_SQL,
                entity.getTrangThai(),
                entity.getBan()
        );
    }

    @Override
    public void delete(Integer ID) {
        XJdbc.update(DELETE_SQL, ID);
    }

    @Override
    public List<HoaDon> selectAll() {
        return selectBySQL(SELECALL_SQL);
    }

    @Override
    public HoaDon selectByID(Integer id) {
        List<HoaDon> sp = selectBySQL(SELLECTBYID_SQL, id);
        if (sp.isEmpty()) {
            return null;
        }
        return sp.get(0);
    }

    @Override
    public List<HoaDon> selectBySQL(String sql, Object... args) {
        List<HoaDon> ls = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt("id"));
                hd.setNgayVao(rs.getDate("NgayVao"));
                hd.setNgayThanhToan(rs.getDate("NgayThanhToan"));
                hd.setMaNV(rs.getString("MaNV"));
                hd.setMaKH(rs.getString("MaKH"));
                hd.setBan(rs.getString("Ban"));
                hd.setTrangThai(rs.getString("TrangThai"));
                ls.add(hd);
            }

        } catch (Exception e) {
        }
        return ls;
    }

//    public List<HoaDon> selectHoadon(String ban) {
//        String sql = "select id,MaKH,MaSP,Ban,soLuong,TongTien "
//                + " from HoaDon Join HoaDonChiTiet "
//                + " on HoaDon.id = HoaDonChiTiet.idHoaDon where HoaDon.Ban = ?";
//        List<HoaDon> hd = new ArrayList<>();
//        ResultSet rs = XJdbc.query(sql, ban);
//        try {
//            while (rs.next()) {
//                HoaDon h = new HoaDon();
//                h.setId(rs.getInt("id"));
//                h.setMaKH(rs.getString("MaKH"));
//                h.setMaSP(rs.getInt("MaSP"));
//                h.setBan(rs.getString("Ban"));
//                h.setSoluong(rs.getInt("soLuong"));
//                h.setTongTien(rs.getDouble("TongTien"));
//                hd.add(h);
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return hd;
//    }
    public List<HoaDon> selectallHoadon(String ban) {
        String sql = "select hd.id , hd.Ban , kh.TenKhachHang , sp.TenSP , ct.soLuong , ct.TongTien  from HoaDon as hd,HoaDonChiTiet as ct ,SanPham as sp,KhachHang as kh "
                + " where hd.id = ct.idHoaDon  and ct.MaSP = sp.MaSP and hd.MaKH = kh.MaKH  and hd.TrangThai = N'Chưa Thanh Toán' and hd.Ban = ?";
        List<HoaDon> hd = new ArrayList<>();
        ResultSet rs = XJdbc.query(sql, ban);
        try {
            while (rs.next()) {
                HoaDon h = new HoaDon();
                h.setId(rs.getInt("id"));
                h.setBan(rs.getString("Ban"));
                h.setTenKH(rs.getString("TenKhachHang"));
                h.setTenSP(rs.getString("TenSP"));
                h.setSoluong(rs.getInt("soLuong"));
                h.setTongTien(rs.getDouble("TongTien"));
                hd.add(h);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return hd;
    }

    public List<HoaDon> selectChuaHoadon() {
        String sql = "select hd.id , hd.Ban , kh.TenKhachHang , sp.TenSP , ct.soLuong , ct.TongTien  from HoaDon as hd,HoaDonChiTiet as ct ,SanPham as sp,KhachHang as kh\n"
                + "where hd.id = ct.idHoaDon  and ct.MaSP = sp.MaSP and hd.MaKH = kh.MaKH  and hd.TrangThai = N'Chưa Thanh Toán' ";
        List<HoaDon> hd = new ArrayList<>();
        ResultSet rs = XJdbc.query(sql);
        try {
            while (rs.next()) {
                HoaDon h = new HoaDon();
                h.setId(rs.getInt("id"));
                h.setTenKH(rs.getString("TenKhachHang"));
                h.setTenSP(rs.getString("TenSP"));
                h.setBan(rs.getString("Ban"));
                h.setSoluong(rs.getInt("soLuong"));
                h.setTongTien(rs.getDouble("TongTien"));
                hd.add(h);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return hd;
    }

    public String SP(int hd) {
        String tensp = "";
        try {
            String sql = "select sp.TenSP from HoaDonChiTiet as ct,SanPham as sp where ct.MaSP = sp.MaSP and ct.idHoaDon =?";
            ResultSet rs = XJdbc.query(sql, hd);
            while (rs.next()) {
                tensp += rs.getString(1) + " , ";
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tensp;
    }

    public String GT(int hd) {
        String tensp = "";
        try {
            String sql = "select sp.giatien from HoaDonChiTiet as ct,SanPham as sp where ct.MaSP = sp.MaSP and ct.idHoaDon = ?";
            ResultSet rs = XJdbc.query(sql, hd);
            while (rs.next()) {
                tensp += XDecimal.formatr.format(rs.getDouble(1)) + " , ";
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tensp;
    }

    public String soluong(int hd) {
        String tensp = "";
        try {
            String sql = "select ct.soluong from HoaDonChiTiet as ct,SanPham as sp where ct.MaSP = sp.MaSP and ct.idHoaDon =?";
            ResultSet rs = XJdbc.query(sql, hd);
            while (rs.next()) {
                tensp += rs.getInt(1) + " , ";
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tensp;
    }

    public HoaDon printHoaDon(int idHoaDon) {
        HoaDon hd = new HoaDon();
        try {
            String sql = "select HoaDon.id,NgayThanhToan,Ban,TrangThai,sum(soLuong) Soluong,sum(TongTien) from HoaDonChiTiet \n"
                    + "Join HoaDon \n"
                    + "on HoaDonChiTiet.idHoaDon=HoaDon.id \n"
                    + "where idHoaDon = ? and TrangThai = N'Đã Thanh Toán'\n"
                    + "Group by HoaDon.id,NgayThanhToan,BAn,TrangThai";
            ResultSet rs = XJdbc.query(sql, idHoaDon);
            while (rs.next()) {
                hd.setId(rs.getInt(1));
                hd.setNgayThanhToan(rs.getDate(2));
                hd.setBan(rs.getString(3));
                hd.setTrangThai(rs.getString(4));
                hd.setSoluong(rs.getInt(5));
                hd.setTongTien(rs.getDouble(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hd;
    }

}
