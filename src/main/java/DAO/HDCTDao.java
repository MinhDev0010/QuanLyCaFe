/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ENTITY.HoaDon;
import UTILS.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class HDCTDao extends MainDAO <HoaDon, Integer>{
    final String INSERT_SQL = "insert into HoaDonChiTiet(idHoaDon,MaSP,soLuong,TongTien) values (?,?,?,?)";
    final String UPDATE_SQL = "";
    final String DELETE_SQL = "delete from HoaDonChiTiet";
    final String SELECALL_SQL = "select * from HoaDonChiTiet";
    final String SELLECTBYID_SQL = "Select * from HoaDonChiTiet where idHoaDon=?";
    
    @Override
    public int insert(HoaDon entity) {
        return XJdbc.update(INSERT_SQL,
                entity.getIdHoaDon(),
                entity.getMaSP(),
                entity.getSoluong(),
                entity.getTongTien()
                );
    }

    @Override
    public int update(HoaDon entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HoaDon> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HoaDon selectByID(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HoaDon> selectBySQL(String sql, Object... args) {
        List<HoaDon> ls = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
             HoaDon hd = new HoaDon();
             hd.setMaSP(rs.getInt("MaSP"));
             hd.setSoluong(rs.getInt("SoLuong"));
             hd.setMaGiamGia(rs.getInt("maGiamGia"));
             hd.setTongTien(rs.getDouble("TongTien"));
             ls.add(hd);
            }
            
        } catch (Exception e) {
        }
        return ls;
    }
    
}
