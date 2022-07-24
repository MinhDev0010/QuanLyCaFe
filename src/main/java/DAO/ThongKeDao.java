/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import UTILS.XDecimal;
import UTILS.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class ThongKeDao {

    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Object[]> getDoanhThu(int nam) {
        String sql = "{CALL TK_sp_DoanhThu (?)}";
        String[] cols = {"Ban", "MaSP", "Soluong", "TongTien", "NgayThanhToan"};
        return this.getListOfArray(sql, cols, nam);
    }

    public List<Integer> selectYears() {
        String sql = "SELECT DISTINCT year (NgayThanhToan) Year from HoaDon ORDER BY Year Desc";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String SelectTongtienTheoNam(int Nam) {
        String tien = "";
        String sql = "SELECT \n"
                + "	SUM(TongTien) TongTien FROM dbo.HoaDonChiTiet \n"
                + "	JOIN dbo.HoaDon on Hoadon.id = HoaDonChiTiet.idHoaDon\n"
                + "	where year(HoaDon.NgayThanhToan)  =  ?";
        ResultSet rs = XJdbc.query(sql, Nam);
        try {
            while (rs.next()) {
                tien += XDecimal.formatr.format(rs.getDouble(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tien;
    }

    public List<Object[]> getDoanhThuNgay(Date ngaybatdau, Date ngayketthuc) {
        String sql = "{CALL TK_SP_TheoNgay (?,?)}";
        String[] cols = {"Ban", "MaSP", "Soluong", "TongTien", "NgayThanhToan"};
        return this.getListOfArray(sql, cols, ngaybatdau, ngayketthuc);
    }

    public String SelectTongtienTheoNgay(Date ngaybatdau, Date ngayketthuc) {
        String tien = "";
        String sql = "{Call TK_SP_TienNgay(?,?)}";
        ResultSet rs = XJdbc.query(sql, ngaybatdau, ngayketthuc);
        try {
            while (rs.next()) {
                tien += XDecimal.formatr.format(rs.getDouble(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tien;
    }
    public List<Object[]> getTop10() {
        String sql = "{CALL TK_sp_Top5}";
        String[] cols = {"TenSP", "SoLuong", "TongTien"};
        return this.getListOfArray(sql, cols);
    }
}
