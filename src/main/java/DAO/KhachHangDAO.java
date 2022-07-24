/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ENTITY.KhachHang;
import UTILS.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class KhachHangDAO extends MainDAO<KhachHang, String> {

    final String INSERT_SQL = "Insert into KhachHang (MaKH,TenKhachHang,SDT) values (?,?,?)";
    final String UPDATE_SQL = "update KhachHang set TenKhachHang = ? , SDT = ? where MaKH = ?";
    final String DELETE_SQL = "delete from KhachHang where MaKH = ?";
    final String SELECALL_SQL = "select * from KhachHang";
    final String SELLECTBYID_SQL = "Select * from KhachHang where MaKH=?";

    @Override
    public int insert(KhachHang entity) {
      return  XJdbc.update(INSERT_SQL,
                entity.getMaKH(),
                entity.getTenKhachHang(),
                entity.getSDT());
    }

    @Override
    public int update(KhachHang entity) {
      return XJdbc.update(UPDATE_SQL,
                entity.getTenKhachHang(),
                entity.getSDT(),
                entity.getMaKH()
        );
    }

    @Override
    public void delete(String ID) {
        XJdbc.update(DELETE_SQL, ID);
    }

    @Override
    public List<KhachHang> selectAll() {
        return selectBySQL(SELECALL_SQL);
    }

    @Override
    public KhachHang selectByID(String MaKH) {
       List<KhachHang> sp = selectBySQL(SELLECTBYID_SQL, MaKH);
        if (sp.isEmpty()) {
            return null;
        }
        return sp.get(0);
    }

    @Override
    public List<KhachHang> selectBySQL(String sql, Object... args) {
         List<KhachHang> ls = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                KhachHang hd = new KhachHang();
                hd.setMaKH(rs.getString("MaKH"));
                hd.setTenKhachHang(rs.getString("TenKhachHang"));
                hd.setSDT(rs.getString("SDT"));
                ls.add(hd);
            }

        } catch (Exception e) {
        }
        return ls;
    }

}
