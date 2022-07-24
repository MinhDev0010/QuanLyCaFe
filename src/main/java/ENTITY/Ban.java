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
public class Ban {
    private int IdBan;
    private String TenBan;

    public Ban() {
    }

    public Ban(int IdBan, String TenBan) {
        this.IdBan = IdBan;
        this.TenBan = TenBan;
    }

    @Override
    public String toString() {
        return TenBan;
    }

    public int getIdBan() {
        return IdBan;
    }

    public void setIdBan(int IdBan) {
        this.IdBan = IdBan;
    }

    public String getTenBan() {
        return TenBan;
    }

    public void setTenBan(String TenBan) {
        this.TenBan = TenBan;
    }
    
}
