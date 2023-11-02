package BLL;

import java.sql.SQLException;
import java.util.ArrayList;

import DAL.ChiTietPhieuNhapDAL;
import DTO.PhieuNhapChiTiet;

public class ChiTietPhieuNhapBLL {
	public ArrayList<PhieuNhapChiTiet> getChiTietPN(String value) throws SQLException {
		ChiTietPhieuNhapDAL ctpnd = new ChiTietPhieuNhapDAL();
		ArrayList<PhieuNhapChiTiet> arr = new ArrayList<PhieuNhapChiTiet>();
		arr = ctpnd.docCtPhieuNhap(value);
		return arr;
	}
	public boolean addChiTietPN(PhieuNhapChiTiet obj) throws SQLException {
		ChiTietPhieuNhapDAL ctpnd = new ChiTietPhieuNhapDAL();
		return ctpnd.themChiTietPN(obj);
	}
	public boolean hideChiTietPN(String masp, String mapn) throws SQLException {
		ChiTietPhieuNhapDAL ctpnd = new ChiTietPhieuNhapDAL();
		return ctpnd.anChiTietPN(masp, mapn);
	}
}
