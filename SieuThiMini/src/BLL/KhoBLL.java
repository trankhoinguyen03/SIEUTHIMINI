package BLL;

import java.sql.SQLException;
import java.util.ArrayList;

import DAL.KhoDAL;
import DTO.Kho;


public class KhoBLL {
	public ArrayList<Kho> getKho() throws SQLException {
		KhoDAL kd = new KhoDAL();
		ArrayList<Kho> arr = new ArrayList<Kho>();
		arr = kd.docKho();
		return arr;
	}
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
}
