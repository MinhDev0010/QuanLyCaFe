/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTILS;

import ENTITY.NhanVien;

/**
 *
 * @author DELL
 */
    public class Auth {//authentication : xác thực nhân viên 
    /**
     * Đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
     */
    public static NhanVien user = null;
    /**
     * Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
     */
    public static void clear() {
        Auth.user = null;
    }
    /**
     * Kiểm tra xem đăng nhập hay chưa
     * @return 
     * @return  
     */
    public static boolean isLogin() {
        return Auth.user != null;
    }
     /**
     * Kiểm tra xem có phải là 
     * public class Auth {//authentication : xác thực nhân viên 
    /**
     // Đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
     */

    public static boolean isManager() {
        return Auth.isLogin() && user.isVaiTro();
    }
}
