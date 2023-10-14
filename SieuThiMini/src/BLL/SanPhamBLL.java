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

	public int renderProType(String value) throws SQLException {
		SanPhamDAL spd = new SanPhamDAL();
		LoaiHangDAL lhd = new LoaiHangDAL();
		int rs = lhd.getMaLh(value);
		return rs;

	}

	public ArrayList<SanPham> searchProductById(String value, String id) throws SQLException {
		SanPhamDAL spd = new SanPhamDAL();
		ArrayList<SanPham> arr = new ArrayList<SanPham>();
		arr = spd.docSanPham("timkiem", value + "," + id);
		return arr;
	}
	public ArrayList<SanPham> searchProductByPrice(String value, String id) throws SQLException {
		SanPhamDAL spd = new SanPhamDAL();
		ArrayList<SanPham> arr = new ArrayList<SanPham>();
		arr = spd.docSanPham("timkiemgia", value + "," + id);
		return arr;
	}
	public static void main(String[] args) throws SQLException {
		SanPhamBLL test = new SanPhamBLL();
		System.out.println(test.searchProductById("a", "0"));
	}
}
