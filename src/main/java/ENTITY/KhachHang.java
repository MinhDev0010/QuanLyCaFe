/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ENTITY;

/**
 *
 * @author DELL
 */
public class KhachHang {
    private String MaKH;
    private String TenKhachHang;
    private String SDT;

    public KhachHang() {
    }

    public KhachHang(String MaKH, String TenKhachHang, String SDT) {
        this.MaKH = MaKH;
        this.TenKhachHang = TenKhachHang;
        this.SDT = SDT;
    }

    @Override
    public String toString() {
        return  TenKhachHang;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void setTenKhachHang(String TenKhachHang) {
        this.TenKhachHang = TenKhachHang;
    }

  
    
}
