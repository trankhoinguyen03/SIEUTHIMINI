package BLL;

import java.sql.SQLException;
import java.util.ArrayList;

import DAL.NhanVienDAL;
import DTO.NhanVien;

public class DangNhapBLL {
	public NhanVien checkLogin(String TaiKhoan,String MatKhau) throws SQLException {
		NhanVienDAL nvd = new NhanVienDAL();
		ArrayList<NhanVien> arrNv = new ArrayList<NhanVien>();
		arrNv = nvd.kiemTraDangNhap();
		if(arrNv.isEmpty()) {
			return null;
		}
		for(NhanVien nv:arrNv) {
			if(nv.getTaiKhoan().equals(TaiKhoan)) {
				if(nv.getMatKhau().equals(MatKhau)) {
//					if(nv.getChucVu()==1) {
//						return "Admin";
//					}
//					return "Nhan Vien";
					return nv;
				}
				else {
					 nv.setHoTen("Mật khẩu không chính sát, vui lòng kiểm tra lại!");
					 return nv;
				}
			}
		}
		NhanVien nv = new NhanVien();
		nv.setHoTen("Tài Khoản không tồn tại, vui lòng kiểm tra lại!");

		return nv;
	}
	public static void main(String[] args) throws SQLException {
		DangNhapBLL dnbll = new DangNhapBLL();
		System.out.println(dnbll.checkLogin("ChiNV", "678"));
	}
}
