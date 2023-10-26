package BLL;

import java.sql.SQLException;
import java.util.ArrayList;

import DAL.LoaiHangDAL;
import DAL.SanPhamDAL;
import DTO.SanPham;

public class SanPhamBLL {
	public String docsanpham() {

		return "";
	}

	public ArrayList<SanPham> findByLoaiHang(String value) throws SQLException {
		SanPhamDAL spd = new SanPhamDAL();
		return spd.docSanPham("timkiemtheoloaihang", value);
	}

	public ArrayList<SanPham> search(String value) throws SQLException {
		SanPhamDAL spd = new SanPhamDAL();
		ArrayList<SanPham> arr = new ArrayList<SanPham>();
		arr = spd.docSanPham("timkiem", value);
		return arr;
	}
	public static void main(String[] args) throws SQLException {
		SanPhamBLL test = new SanPhamBLL();
		System.out.println(test.search("a"));
	}
}
