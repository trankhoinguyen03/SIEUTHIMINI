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
	public ArrayList<NhaCungCap> getNhaCungCapMaNCC(String value) throws SQLException {
		NhaCungCapDAL nccd = new NhaCungCapDAL();
		ArrayList<NhaCungCap> arr = new ArrayList<NhaCungCap>();
		arr = nccd.docNhaCungCapMaNCC(value);
		return arr;
	}
}
