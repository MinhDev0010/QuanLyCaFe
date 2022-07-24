/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ENTITY.Ban;
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
public class BanDao extends MainDAO<Ban, Integer> {

    @Override
    public int insert(Ban entity) {
        String sql = "Insert into Ban Values (?)";
        return XJdbc.update(sql, entity.getTenBan());
    }

    @Override
    public int update(Ban entity) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "Update Ban set TenBan = ? where IdBan = ? ";
        return XJdbc.update(sql, 
                entity.getTenBan(),
                entity.getIdBan());
    }

    @Override
    public void delete(Integer ID) {
        String sql = "Delete form Ban where IdBan";
        XJdbc.update(sql, ID);
    }

    @Override
    public List<Ban> selectAll() {
        String sql = "SELECT * FROM dbo.Ban";
        return selectBySQL(sql);
    }

    @Override
    public Ban selectByID(Integer id) {
        String sql = "Select * from Ban where IdBan=?";
        List<Ban> sp = selectBySQL(sql, id);
        if (sp.isEmpty()) {
            return null;
        }
        return sp.get(0);
    }

    @Override
    public List<Ban> selectBySQL(String sql, Object... args) {
        List<Ban> list = new ArrayList<>();
        ResultSet rs = XJdbc.query(sql, args);
        try {
            while (rs.next()) {
                Ban bn = new Ban();
                bn.setIdBan(rs.getInt("IdBan"));
                bn.setTenBan(rs.getString("TenBan"));
                list.add(bn);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int TongBan() {
        String sql = "SELECT COUNT(*) TongSo FROM dbo.Ban";
        ResultSet rs = XJdbc.query(sql);
        try {
            if (rs.next()) {
                return rs.getInt("TongSo");
            }
        } catch (SQLException ex) {
//            Logger.getLogger(BanDao.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return 0;
    }
//    public static void main(String[] args) {
//        System.out.println(new BanDao().selectAll().size());
//    }
}
