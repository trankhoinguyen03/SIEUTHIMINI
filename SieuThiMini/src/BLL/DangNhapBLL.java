package BLL;

import java.sql.SQLException;
import java.util.ArrayList;

import DAL.TaiKhoanDAL;
import DTO.TaiKhoan;

public class DangNhapBLL {
	public static TaiKhoan taiKhoan;
	public TaiKhoan checkLogin(String TenDangNhap,String MatKhau) throws SQLException {
		TaiKhoanDAL tkd = new TaiKhoanDAL();
		ArrayList<TaiKhoan> arrTk = new ArrayList<TaiKhoan>();
		arrTk = tkd.kiemTraDangNhap();
		if(arrTk.isEmpty()) {
			return null;
		}
		for(TaiKhoan tk:arrTk) {
			if(tk.getTenDangNhap().equals(TenDangNhap)) {
				if(tk.getMatKhau().equals(MatKhau)) {
//					if(nv.getChucVu()==1) {
//						return "Admin";
//					}
//					return "Nhan Vien";
					return tk;
				}
				else {
					 tk.setTenDangNhap("Mật khẩu không chính xác, vui lòng kiểm tra lại!");
					 return tk;
				}
			}
		}
		TaiKhoan tk = new TaiKhoan();
		tk.setTenDangNhap("Tài Khoản không tồn tại, vui lòng kiểm tra lại!");

		return tk;
	}
	public static void main(String[] args) throws SQLException {
		DangNhapBLL dnbll = new DangNhapBLL();
		System.out.println(dnbll.checkLogin("ADMIN", "123"));
	}
}
