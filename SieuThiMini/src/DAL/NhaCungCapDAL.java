package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.LoaiHang;
import DTO.NhaCungCap;

public class NhaCungCapDAL extends connectSql {

	public NhaCungCapDAL() throws SQLException {
		super();
	
	}
	public ArrayList<NhaCungCap> docNhaCungCap() throws SQLException{
		ArrayList<NhaCungCap> arrNCC = new ArrayList<NhaCungCap>();
		String sql="";
		sql = "select * from NHACUNGCAP where TrangThai = 1";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			NhaCungCap ncc = new NhaCungCap();
			ncc.setMaNCC(rs.getString("MaNCC"));
			ncc.setTenNCC(rs.getString("TenNCC"));
			ncc.setDiaChi(rs.getString("DiaChi"));
			ncc.setSoDT(rs.getString("SDT"));
		arrNCC.add(ncc);
		}
		closeConnection();
		return arrNCC;
	}
    public String docTenNCC(String value) throws SQLException{
		String sql = "select TenNCC from NHACUNGCAP where MaNCC LIKE ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, value);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			return rs.getString("TenNCC");
		}
	return null;
	}
    public String docMaNCC(String value) throws SQLException{
		String sql = "select MaNCC from NHACUNGCAP where TenNCC LIKE ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, value);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			return rs.getString("MaNCC");
		}
	
	return null;
	}
	public String layMaNCCcuoi() throws SQLException {
		String sql = "SELECT MAX(MaNCC) FROM NHACUNGCAP";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		  if (rs.next()) {
		        String maxColumnValue = rs.getString(1);
		        return maxColumnValue;
		    }
		  return null;
	}
	
    public boolean anNhaCungCap(String mancc) throws SQLException {
		String sql = "UPDATE NHACUNGCAP SET TrangThai = 0 where MaNCC = ?";
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setString(1, mancc);
			int rowsUpdated = pstm.executeUpdate();

			return rowsUpdated > 0;
		}
    }
    
    public boolean suaNhaCungCap(String mancc, String tenncc, String diachi, String sdt) throws SQLException {
		String sql = "UPDATE NHACUNGCAP SET TenNCC = ?, DiaChi = ?, SDT = ? where MaNCC = ?";
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setString(1, tenncc);
			pstm.setString(2, diachi);
			pstm.setString(3, sdt);
			pstm.setString(4, mancc);
			int rowsUpdated = pstm.executeUpdate();

			return rowsUpdated > 0;
		}
    }
	
	public boolean BtnNhaCungCap(NhaCungCap Ncc,String condition) throws SQLException {
		String sql ="";
		if(condition.equals("themnhacungcap")) {
			sql = "insert into NHACUNGCAP (TenNCC,DiaChi,SDT,TrangThai,MaNCC) values (?,?,?,?,?)";
		}
		if(condition.equals("xoanhacungcap")) {
			sql = "update NHACUNGCAP set TrangThai = 0 where MaNCC = ?";
		}
		PreparedStatement pstm = conn.prepareStatement(sql);
		try {
			if(condition.equals("xoanhacungcap")) {
				pstm.setString(1, Ncc.getMaNCC());
			}
			if(condition.equals("themnhacungcap")) {
				pstm.setString(1, Ncc.getTenNCC());
				pstm.setString(2,Ncc.getDiaChi());
				pstm.setString(3,Ncc.getSoDT());
				pstm.setString(4, "1");
				pstm.setString(5, Ncc.getMaNCC());
			}
			
			pstm.executeUpdate();
		} catch (Exception e) {

			return false;
		}
		
		return true;
		
	}
	public static void main(String[] args) throws SQLException {
		NhaCungCapDAL test = new NhaCungCapDAL();
		
	}

}
