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
	public static void main(String[] args) throws SQLException {
		SanPhamBLL test = new SanPhamBLL();
		System.out.println(test.searchSanPham("a"));
	}
}
