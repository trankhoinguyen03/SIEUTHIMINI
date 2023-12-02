package BLL;

import java.sql.SQLException;
import java.util.ArrayList;

import DAL.TaiKhoanDAL;
import DTO.TaiKhoan;

public class TaiKhoanBLL {
	public ArrayList<TaiKhoan> getTaiKhoan(String condition) throws SQLException {
		TaiKhoanDAL tkd = new TaiKhoanDAL();
		ArrayList<TaiKhoan> arr = new ArrayList<TaiKhoan>();
		arr = tkd.docTaiKhoan(condition);
		return arr;
	}
	public String getTenQuyen(String id) throws SQLException {
		TaiKhoanDAL tkd = new TaiKhoanDAL();
		return tkd.layTenQuyen(id);
	}
	public String getMaQuyen(String name) throws SQLException {
		TaiKhoanDAL tkd = new TaiKhoanDAL();
		return tkd.layMaQuyen(name);
	}
	public String getQuyen(String name) throws SQLException {
		TaiKhoanDAL tkd = new TaiKhoanDAL();
		return tkd.MaQuyen(name);
	}
	public boolean hideTaiKhoan(String id) throws SQLException {
		TaiKhoanDAL tkd = new TaiKhoanDAL();
		return tkd.anTaiKhoan(id);
	}
	public boolean fixTaiKhoan(TaiKhoan obj) throws SQLException {
		TaiKhoanDAL tkd = new TaiKhoanDAL();
		return tkd.suaTaiKhoan(obj);
	}
	public boolean addTaiKhoan(TaiKhoan obj) throws SQLException {
		TaiKhoanDAL tkd = new TaiKhoanDAL();
		return tkd.themTaiKhoan(obj);
	}
}
