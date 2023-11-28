package BLL;

import java.sql.SQLException;
import java.util.ArrayList;

import DAL.NhaCungCapDAL;
import DTO.NhaCungCap;

public class NhaCungCapBLL {
	public ArrayList<NhaCungCap> getNhaCungCap() throws SQLException {
		NhaCungCapDAL nccd = new NhaCungCapDAL();
		ArrayList<NhaCungCap> arr = new ArrayList<NhaCungCap>();
		arr = nccd.docNhaCungCap();
		return arr;
	}
	public String getTenNCC(String value) throws SQLException {
		NhaCungCapDAL nccd = new NhaCungCapDAL();
		return nccd.docTenNCC(value);
	}
	public String getMaNCC(String value) throws SQLException {
		NhaCungCapDAL nccd = new NhaCungCapDAL();
		return nccd.docMaNCC(value);
	}
	public String getLastMaNCC() throws SQLException {
		NhaCungCapDAL nccd = new NhaCungCapDAL();
		return nccd.layMaNCCcuoi();
	}
	public boolean BtnNhaCungCap(NhaCungCap obj, String value) throws SQLException {
		NhaCungCapDAL nccd = new NhaCungCapDAL();
		return nccd.BtnNhaCungCap(obj, value);
	}
	public boolean fixNhaCungCap(String mancc, String tenncc, String diachi, String sdt) throws SQLException {
		NhaCungCapDAL nccd = new NhaCungCapDAL();
		return nccd.suaNhaCungCap(mancc, tenncc, diachi, sdt);
	}
	public boolean hideNhaCungCap(String id) throws SQLException {
		NhaCungCapDAL nccd = new NhaCungCapDAL();
		return nccd.anNhaCungCap(id);
	}
}
