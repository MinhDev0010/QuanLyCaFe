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
public class SanPham {

    private int MaSP;
    private String tenSP;
    private String tenLSP;
    private int loaiSP;
    private double giaTien;
    private int Soluong;
    private String dvTinh;

    public SanPham() {
    }

    public SanPham(int MaSP, String tenSP, int loaiSP, double giaTien, int Soluong, String dvTinh) {
        this.MaSP = MaSP;
        this.tenSP = tenSP;
        this.loaiSP = loaiSP;
        this.giaTien = giaTien;
        this.Soluong = Soluong;
        this.dvTinh = dvTinh;
    }

    public String getTenLSP() {
        return tenLSP;
    }

    public void setTenLSP(String tenLSP) {
        this.tenLSP = tenLSP;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(int loaiSP) {
        this.loaiSP = loaiSP;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int Soluong) {
        this.Soluong = Soluong;
    }

    public String getDvTinh() {
        return dvTinh;
    }

    public void setDvTinh(String dvTinh) {
        this.dvTinh = dvTinh;
    }

    @Override
    public String toString() {
        return tenSP;
    }

}
