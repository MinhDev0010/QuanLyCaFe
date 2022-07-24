/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ENTITY.LoaiSP;
import UTILS.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class LoaiSPDao extends MainDAO<LoaiSP, Integer>{
    final String INSERT_SQL = "insert into LoaiSanPham values (?)";
    final String UPDATE_SQL = "Update LoaiSanPham set TenLoaiSP = ? where MaLSP = ?";
    final String DELETE_SQL = "delete from LoaiSanPham where MaLSP = ?";
    final String SELECALL_SQL = "select * from LoaiSanPham";
    final String SELLECTBYID_SQL = "Select * from LoaiSanPham where MaLSP =?";

    @Override
    public int insert(LoaiSP entity) {
        return XJdbc.update(INSERT_SQL, entity.getTenLSP());
    }

    @Override
    public int update(LoaiSP entity) {
        return XJdbc.update(UPDATE_SQL,
                entity.getTenLSP(),
                entity.getMaLSP()
                );
    }

    @Override
    public void delete(Integer ID) {
          XJdbc.update(DELETE_SQL,ID);
    }

    @Override
    public List<LoaiSP> selectAll() {
       return selectBySQL(SELECALL_SQL);
    }

    @Override
    public LoaiSP selectByID(Integer MaSP) {
           List<LoaiSP> sp = selectBySQL(SELLECTBYID_SQL, MaSP);
        if (sp.isEmpty()) {
            return null;
        }
        return sp.get(0);
    }
    

    @Override
    public List<LoaiSP> selectBySQL(String sql, Object... args) {
         List<LoaiSP> sp = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                LoaiSP sanpham = new LoaiSP();
                sanpham.setMaLSP(rs.getInt("MaLSP"));
                sanpham.setTenLSP(rs.getString("TenLoaiSP"));
                
                sp.add(sanpham);
            }
        } catch (Exception e) {
        }
        return sp;
    }
    
}
