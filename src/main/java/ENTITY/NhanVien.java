/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ENTITY;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class NhanVien {

    private String maNV;
    private String MatKhau;
    private String HoTen;
    private boolean VaiTro;
//    private int sDT;
    private Date NgaySinh;
    private boolean gioiTinh;
    private String HinhAnh;

    public NhanVien() {
    }

    public NhanVien(String maNV, String MatKhau, String HoTen, boolean VaiTro,Date NgaySinh, boolean gioiTinh, String HinhAnh) {
        this.maNV = maNV;
        this.MatKhau = MatKhau;
        this.HoTen = HoTen;
        this.VaiTro = VaiTro;
//        this.sDT = sDT;
        this.NgaySinh = NgaySinh;
        this.gioiTinh = gioiTinh;
        this.HinhAnh = HinhAnh;
    }

    @Override
    public String toString() {
        return  maNV;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public boolean isVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(boolean VaiTro) {
        this.VaiTro = VaiTro;
    }

//    public int getsDT() {
//        return sDT;
//    }
//
//    public void setsDT(int sDT) {
//        this.sDT = sDT;
//    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

}
