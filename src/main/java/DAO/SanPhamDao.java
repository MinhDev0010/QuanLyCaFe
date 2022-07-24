/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ENTITY.SanPham;
import UTILS.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class SanPhamDao extends MainDAO<SanPham, Integer> {

    final String INSERT_SQL = "insert into SanPham(TenSP,MaLSP,giatien,soluong,DVTinh) values(?,?,?,?,?)";
    final String UPDATE_SQL = "Update SanPham set TenSP = ?,MaLSP=?,giatien=?,soluong=?,DVTinh=? where MaSP = ?";
    final String DELETE_SQL = "delete from SanPham where MaSP=?";
    final String SELECALL_SQL = "select * from SanPham";
    final String SELLECTBYID_SQL = "Select * from SanPham where MaSP=?";
    final String SELLECTBYKITU_SQL = "Select sp.MaSP,sp.TenSP,lsp.TenLoaiSP,sp.giatien,sp.soluong,sp.DVTinh from LoaiSanPham as lsp,SanPham as sp where lsp.MaLSP = sp.MaLSP and TenSP like ?";

    @Override
    public int insert(SanPham entity) {
        return XJdbc.update(INSERT_SQL,
                entity.getTenSP(),
                entity.getLoaiSP(),
                entity.getGiaTien(),
                entity.getSoluong(),
                entity.getDvTinh()
        );
    }

    @Override
    public int update(SanPham entity) {
        return XJdbc.update(UPDATE_SQL,
                entity.getTenSP(),
                entity.getLoaiSP(),
                entity.getGiaTien(),
                entity.getSoluong(),
                entity.getDvTinh(),
                entity.getMaSP()
        );
    }

    @Override
    public void delete(Integer MaSP) {
        XJdbc.update(DELETE_SQL, MaSP);
    }

    @Override
    public List<SanPham> selectAll() {
        return selectBySQL(SELECALL_SQL);
    }

    @Override
    public SanPham selectByID(Integer MaSP) {
        List<SanPham> sp = selectBySQL(SELLECTBYID_SQL, MaSP);
        if (sp.isEmpty()) {
            return null;
        }
        return sp.get(0);
    }

    @Override
    public List<SanPham> selectBySQL(String sql, Object... args) {
        List<SanPham> sp = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                SanPham sanpham = new SanPham();
                sanpham.setMaSP(rs.getInt("MaSP"));
                sanpham.setTenSP(rs.getString("TenSP"));
                sanpham.setLoaiSP(rs.getInt("MaLSP"));
                sanpham.setGiaTien(rs.getDouble("giatien"));
                sanpham.setSoluong(rs.getInt("soluong"));
                sanpham.setDvTinh(rs.getString("DVTinh"));
                sp.add(sanpham);
            }
        } catch (Exception e) {
        }
        return sp;
    }

    public List<SanPham> selectbytensp(String tsp) {
//        return selectTenLoaiSP(SELLECTBYKITU_SQL, tsp + "%");
        List<SanPham> hd = new ArrayList<>();
        ResultSet rs = XJdbc.query(SELLECTBYKITU_SQL, tsp + "%");
        try {
            while (rs.next()) {
                SanPham sanpham = new SanPham();
                sanpham.setMaSP(rs.getInt("MaSP"));
                sanpham.setTenSP(rs.getString("TenSP"));
                sanpham.setTenLSP(rs.getString("TenLoaiSP"));
                sanpham.setGiaTien(rs.getDouble("giatien"));
                sanpham.setSoluong(rs.getInt("soluong"));
                sanpham.setDvTinh(rs.getString("DVTinh"));
                hd.add(sanpham);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return hd;
    }

    public List<SanPham> selectTenLoaiSP() {
        String sql = "select sp.MaSP,sp.TenSP,lsp.TenLoaiSP,sp.giatien,sp.soluong,sp.DVTinh from LoaiSanPham as lsp,SanPham as sp where lsp.MaLSP = sp.MaLSP ";
        List<SanPham> hd = new ArrayList<>();
        ResultSet rs = XJdbc.query(sql);
        try {
            while (rs.next()) {
                SanPham sanpham = new SanPham();
                sanpham.setMaSP(rs.getInt("MaSP"));
                sanpham.setTenSP(rs.getString("TenSP"));
                sanpham.setTenLSP(rs.getString("TenLoaiSP"));
                sanpham.setGiaTien(rs.getDouble("giatien"));
                sanpham.setSoluong(rs.getInt("soluong"));
                sanpham.setDvTinh(rs.getString("DVTinh"));
                hd.add(sanpham);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return hd;
    }

    public List<SanPham> selectTenSPByMaLSP(int maloai) {
        String sql = "select * from SanPham where MaLSP = ?";
        return selectBySQL(sql, maloai);
    }
}
