package BLL;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
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
		LocalDate currentDate = LocalDate.now();
		Date now = Date.valueOf(currentDate);
		for(SanPham data: arr) {
			Date hsd = Date.valueOf(data.getHanSuDung());
			if(now.compareTo(hsd) > 0) {
				data.setTenSp(data.getTenSp()+" (hết hạn)");
			}
		}
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
		LocalDate currentDate = LocalDate.now();
		Date now = Date.valueOf(currentDate);
		Date hsd = Date.valueOf(obj.getHanSuDung());
		if(now.compareTo(hsd) > 0) {
			obj.setTenSp(obj.getTenSp()+" (hết hạn)");
			obj.setTinhTrang("0");
		}
		return spd.themSanPham(obj);
	}
	public static void main(String[] args) throws SQLException {
		SanPhamBLL test = new SanPhamBLL();
		System.out.println(test.searchSanPham("a"));
	}
}
