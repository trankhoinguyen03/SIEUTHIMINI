package BLL;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;

import DAL.KhoDAL;
import DAL.SanPhamDAL;
import DTO.SanPham;

public class SanPhamBLL {
	public ArrayList<SanPham> findByLoaiHang(String value) throws SQLException {
		SanPhamDAL spd = new SanPhamDAL();
		return spd.docSanPham("timkiemtheoloaihang", value);
	}
	public ArrayList<SanPham> getSanPham() throws SQLException {
		SanPhamDAL spd = new SanPhamDAL();
		ArrayList<SanPham> arr = new ArrayList<SanPham>();
		arr = spd.docSanPham("docsanpham", null);
		return arr;
	}
	public String getTenSP(String value) throws SQLException {
		SanPhamDAL spd = new SanPhamDAL();
		return spd.layTenSanPham(value);
	}
	public String getMaSP(String value) throws SQLException {
		SanPhamDAL spd = new SanPhamDAL();
		return spd.layMaSp(value);
	}
	public String getGiaNhap(String value) throws SQLException {
		SanPhamDAL spd = new SanPhamDAL();
		return spd.layGiaNhap(value);
	}
	public String getLastMaSP() throws SQLException {
		SanPhamDAL spd = new SanPhamDAL();
		return spd.layMaSPcuoi();
	}
	public String getMaLoaiHang(String value) throws SQLException {
		SanPhamDAL spd = new SanPhamDAL();
		return spd.layMaLh(value);
	}
	public boolean addSanPham(SanPham obj) throws SQLException {
		SanPhamDAL spd = new SanPhamDAL();
		return spd.themSanPham(obj);
	}
	public boolean fixSanPham(String id, String name) throws SQLException {
		SanPhamDAL spd = new SanPhamDAL();
		return spd.suaSanPham(id, name);
	}
	public boolean hideSanPham(String id) throws SQLException {
		SanPhamDAL spd = new SanPhamDAL();
		return spd.anSanPham(id);
	}
	public static void main(String[] args) throws SQLException {
		SanPhamBLL test = new SanPhamBLL();
	}
}
