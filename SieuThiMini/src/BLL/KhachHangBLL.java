
package BLL;

import java.sql.SQLException;
import java.util.ArrayList;

import DAL.KhachHangDAL;
import DAL.NhanVienDAL;
import DTO.KhachHang;

public class KhachHangBLL {
	public ArrayList<KhachHang> getKhachHang(String condition) throws SQLException {
		KhachHangDAL khd = new KhachHangDAL();
		ArrayList<KhachHang> arr = new ArrayList<KhachHang>();
		arr = khd.docKhachHang(condition);
		return arr;
	}
	public boolean fixKhachHang(String id, String name, String phone) throws SQLException {
		KhachHangDAL khd = new KhachHangDAL();
		return khd.suaKhachHang(id, name, phone);
	}
	public boolean hideKhachHang(String id) throws SQLException {
		KhachHangDAL khd = new KhachHangDAL();
		return khd.anKhachHang(id);
	}
	public boolean addKhachHang(KhachHang obj) throws SQLException {
		KhachHangDAL khd = new KhachHangDAL();
		return khd.themKhachHang(obj);
	}
	public String getLastMaKH() throws SQLException {
		KhachHangDAL nvd = new KhachHangDAL();
		return nvd.layMaKHcuoi();
	}
	
}
