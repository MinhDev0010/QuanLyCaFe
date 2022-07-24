/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTILS;

import java.security.MessageDigest;

/**
 *
 * @author DELL
 */
public class XMatKhau {
   public static String md5tao(String c) {
        try {
            MessageDigest digs = MessageDigest.getInstance("MD5");
            digs.update(c.getBytes());
            String str = new String(digs.digest());
            return str;
        } catch (Exception ex) {
            return "Lỗi";
        }
    }
    public static boolean md5dangNhap(String pass,String txt) {
        String str ="";
        try {
            MessageDigest digs = MessageDigest.getInstance("MD5");
            digs.update(txt.getBytes());
            str = new String(digs.digest());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return str.equals(pass);
    }
//    public static void main(String[] args) {
//        String text = "123";
//        System.out.println(XMatKhau.md5tao(text));
//    }
}
