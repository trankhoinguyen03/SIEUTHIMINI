package BLL;

import java.sql.SQLException;
import java.util.ArrayList;

import DAL.LoaiHangDAL;
import DTO.LoaiHang;

public class LoaiHangBLL {
	public ArrayList<LoaiHang> getLoaiHang() throws SQLException {
		LoaiHangDAL lhd = new LoaiHangDAL();
		ArrayList<LoaiHang> arr = new ArrayList<LoaiHang>();
		arr = lhd.docLoaiHang();
		return arr;
	}
	public String getTenLH(String value) throws SQLException {
		LoaiHangDAL lhd = new LoaiHangDAL();
		return lhd.docLoaiHangMaLH(value);
	}
	public String getMaLH(String value) throws SQLException {
		LoaiHangDAL lhd = new LoaiHangDAL();
		return lhd.layMaLh(value);
	}
	public String getLastMaLH() throws SQLException {
		LoaiHangDAL lhd = new LoaiHangDAL();
		return lhd.getLastMaLH();
	}
	public boolean BtnLoaiHang(LoaiHang lh, String condition) throws SQLException {
		LoaiHangDAL lhd = new LoaiHangDAL();
		return lhd.BtnLoaiHang(lh, condition);
	}
	public boolean fixLoaiHang(String id, String name) throws SQLException {
		LoaiHangDAL lhd = new LoaiHangDAL();
		return lhd.suaLoaiHang(id, name);
	}
	public boolean hideLoaiHang(String id) throws SQLException {
		LoaiHangDAL lhd = new LoaiHangDAL();
		return lhd.anLoaiHang(id);
	}
}
