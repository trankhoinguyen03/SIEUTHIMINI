package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.LoaiHang;
import DTO.SanPham;

public class LoaiHangDAL extends connectSql {
	
	
	public LoaiHangDAL() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public ArrayList<LoaiHang> docLoaiHang(){
		ArrayList<LoaiHang> arrLoaihang = new ArrayList<LoaiHang>();
		try {
			String sql = "select * from LOAIHANG where isDeleted = 1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				LoaiHang lh = new LoaiHang();
				lh.setMaLH(rs.getInt(1)); 
				lh.setTenLH(rs.getString(2));
				arrLoaihang.add(lh);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
//		closeConnection();
		return arrLoaihang;
	}
	public String getNameLoaiHang(int maLH) throws SQLException {
		String sql = "select TenLH from LOAIHANG where MaLH = "+ maLH;
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
		    String tenLH = rs.getString("TenLH");
		    return tenLH;
		}
		return "";
	}
	public int getLastMaLH() throws SQLException {
		String sql = "select top 1 MaLH from LOAIHANG order by MaLH DESC";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		  if (rs.next()) {
		        int maxColumnValue = rs.getInt("MaLH");
		        return maxColumnValue;
		    }
		  closeConnection();
		  return 0;
	}
	public ArrayList<LoaiHang> docLoaiHangMaLH(int maLh) throws SQLException{
		ArrayList<LoaiHang> arrLoaihang = new ArrayList<LoaiHang>();
		String sql = "select * from LOAIHANG where MaLH =" + maLh;
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		 
		while(rs.next()) {
			LoaiHang lh = new LoaiHang();
			lh.setMaLH(rs.getInt("MaLH"));
			lh.setTenLH(rs.getString(2));
			arrLoaihang.add(lh);
		}
	closeConnection();
	return arrLoaihang;
	}
	public int getMaLh(String value) throws SQLException {
		String sql = "";
		sql ="select MaLH from LOAIHANG where TenLH = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, value);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			return rs.getInt("MaLH");
		}
		return 0;
	}
	public boolean ThemLoaiHang(LoaiHang Lh, String condition) throws SQLException {
	    String sql = "";
	    switch (condition) {
	        case "themloaihang":
	            sql = "INSERT INTO LOAIHANG (TenLH, isDeleted) VALUES (?, ?)";
	            break;
	        case "sualoaihang":
	            sql = "UPDATE LOAIHANG SET TenLH = ? WHERE MaLH = ?";
	            break;
	        case "xoaloaihang":
	            sql = "UPDATE LOAIHANG SET isDeleted = 0 WHERE MaLH = ?";
	            break;
	        default:
	            return false;
	    }
	    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
	        if (condition.equals("sualoaihang")) {
	            pstm.setString(1, Lh.getTenLH());
	            pstm.setInt(2, Lh.getMaLH());
	        }
	        if (condition.equals("xoaloaihang")) {
	            pstm.setInt(1, Lh.getMaLH());
	        }
	        if (condition.equals("themloaihang")) {
	            pstm.setString(1, Lh.getTenLH());
	            pstm.setInt(2, 1);
	        }

	        int result = pstm.executeUpdate();
	        if (result == 0) {
	            return false;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        closeConnection();
	    }
	    return true;
	}

	
	public static void main(String[] args) throws SQLException {
		LoaiHangDAL test = new LoaiHangDAL();
		 
		System.out.println(test.getNameLoaiHang(31));
		
	}
}
