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
public class HoaDon {

    private int id;
    private int maLSP;
    private Date NgayVao;
    private Date NgayThanhToan;
    private String MaNV;
    private int MaSP;
    private String TenSP;
    private String TenKH;
    private String maKH;
    private String Ban;
    private String TrangThai;
    private int idHoaDon;
    private int soluong;
    private int maGiamGia;
    private double TongTien;
    private Date ngaytao = new Date();

    public HoaDon() {
    }
//Hoa DOn

    public HoaDon(int id, Date NgayThanhToan, String MaNV, String maKH, String Ban, String TrangThai) {
        this.id = id;
        this.NgayThanhToan = NgayThanhToan;
        this.MaNV = MaNV;
        this.maKH = maKH;
        this.Ban = Ban;
        this.TrangThai = TrangThai;
    }
//Hoa Don Chi Tiet

    public HoaDon(int MaSP, int idHoaDon, int soluong, int maGiamGia, double TongTien) {
        this.MaSP = MaSP;
        this.idHoaDon = idHoaDon;
        this.soluong = soluong;
        this.maGiamGia = maGiamGia;
        this.TongTien = TongTien;
    }
//InHoaDon

    public HoaDon(int id, Date NgayThanhToan, String Ban, String TrangThai, int soluong, double TongTien) {
        this.id = id;
        this.NgayThanhToan = NgayThanhToan;
        this.Ban = Ban;
        this.TrangThai = TrangThai;
        this.soluong = soluong;
        this.TongTien = TongTien;
    }

    public int getMaLSP() {
        return maLSP;
    }

    public void setMaLSP(int maLSP) {
        this.maLSP = maLSP;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNgayVao() {
        return NgayVao;
    }

    public void setNgayVao(Date NgayVao) {
        this.NgayVao = NgayVao;
    }

    public Date getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(Date NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int MaSP) {
        this.MaSP = MaSP;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getBan() {
        return Ban;
    }

    public void setBan(String Ban) {
        this.Ban = Ban;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(int maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

}
