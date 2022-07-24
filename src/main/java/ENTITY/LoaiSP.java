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
public class LoaiSP {
    private int MaLSP;
    private String TenLSP;

    public LoaiSP() {
    }

    public LoaiSP(int MaLSP, String TenLSP) {
        this.MaLSP = MaLSP;
        this.TenLSP = TenLSP;
    }

    @Override
    public String toString() {
        return  TenLSP;
    }

    public int getMaLSP() {
        return MaLSP;
    }

    public void setMaLSP(int MaLSP) {
        this.MaLSP = MaLSP;
    }

    public String getTenLSP() {
        return TenLSP;
    }

    public void setTenLSP(String TenLSP) {
        this.TenLSP = TenLSP;
    }
    
}
