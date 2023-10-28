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
}
