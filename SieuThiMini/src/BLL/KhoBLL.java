package BLL;

import java.sql.SQLException;

import DAL.KhoDAL;


public class KhoBLL {
	public boolean updateSoLuong(String value, String id) throws SQLException {
		KhoDAL kd = new KhoDAL();
		return kd.capNhatSoLuong(value, id);
	}
	public String getSoLuong(String value) throws SQLException {
		KhoDAL kd = new KhoDAL();
		return kd.laySoLuong(value);
	}
	public boolean addSoLuong(String value, String id) throws SQLException {
		KhoDAL kd = new KhoDAL();
		return kd.themSoLuong(value, id);
	}
	public boolean hideSanPham(String value) throws SQLException {
		KhoDAL kd = new KhoDAL();
		return kd.anSanPham(value);
	}
}
