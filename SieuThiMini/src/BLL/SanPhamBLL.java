package BLL;

import java.sql.SQLException;
import java.util.ArrayList;

import DAL.SanPhamDAL;
import DTO.SanPham;

public class SanPhamBLL {
	public ArrayList<SanPham> findByLoaiHang(String value) throws SQLException {
		SanPhamDAL spd = new SanPhamDAL();
		return spd.docSanPham("timkiemtheoloaihang", value);
	}

	public ArrayList<SanPham> searchSanPham(String value) throws SQLException {
		SanPhamDAL spd = new SanPhamDAL();
		ArrayList<SanPham> arr = new ArrayList<SanPham>();
		arr = spd.docSanPham("timkiem", value);
		return arr;
	}
	public ArrayList<SanPham> getSanPham() throws SQLException {
		SanPhamDAL spd = new SanPhamDAL();
		ArrayList<SanPham> arr = new ArrayList<SanPham>();
		arr = spd.docSanPham("docsanpham", null);
		return arr;
	}
	public boolean hideSanPham(String value) throws SQLException {
		SanPhamDAL spd = new SanPhamDAL();
		return spd.anSanPham(value);
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
	public static void main(String[] args) throws SQLException {
		SanPhamBLL test = new SanPhamBLL();
		System.out.println(test.searchSanPham("a"));
	}
}
