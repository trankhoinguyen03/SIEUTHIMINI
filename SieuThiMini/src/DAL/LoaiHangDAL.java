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
			String sql = "select * from LOAIHANG where TrangThai = 1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				LoaiHang lh = new LoaiHang();
				lh.setMaLH(rs.getString(1)); 
				lh.setTenLH(rs.getString(2));
				arrLoaihang.add(lh);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		closeConnection();
		return arrLoaihang;
	}
	public String getNameLoaiHang(int maLH) throws SQLException {
		String sql = "select TenLH from LOAIHANG where MaLH = "+ maLH;
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
		    String tenLH = rs.getString("TenLH");
		    closeConnection();
		    return tenLH;
		}
		closeConnection();
		return "";
	}
	public String getLastMaLH() throws SQLException {
		String sql = "select top 1 MaLH from LOAIHANG order by MaLH DESC";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		  if (rs.next()) {
		        String maxColumnValue = rs.getString("MaLH");
		        return maxColumnValue;
		  }
		closeConnection();
		return null;
	}
	public String docLoaiHangMaLH(String value) throws SQLException{
		String sql = "select TenLH from LOAIHANG where MaLH = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, value);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			return rs.getString(1);
		}
		closeConnection();
		return null;
	}
	public String layMaLh(String value) throws SQLException {
		String sql = "";
		sql ="select MaLH from LOAIHANG where TenLH = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, value);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			return rs.getString(1);
		}
		return null;
	}
	
    public boolean anLoaiHang(String malh) throws SQLException {
		String sql = "UPDATE LOAIHANG SET TrangThai = 0 where MaLH = ?";
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setString(1, malh);
			int rowsUpdated = pstm.executeUpdate();

			return rowsUpdated > 0;
		}
    }
    
    public boolean suaLoaiHang(String malh, String tenlh) throws SQLException {
		String sql = "UPDATE LOAIHANG SET TenLH = ? where MaLH = ?";
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setString(1, tenlh);
			pstm.setString(2, malh);
			int rowsUpdated = pstm.executeUpdate();

			return rowsUpdated > 0;
		}
    }
	
	public boolean BtnLoaiHang(LoaiHang Lh, String condition) throws SQLException {
	    String sql = "";
	    switch (condition) {
	        case "themloaihang":
	            sql = "INSERT INTO LOAIHANG (TenLH, TrangThai, MaLH) VALUES (?, ?, ?)";
	            break;
	        case "xoaloaihang":
	            sql = "UPDATE LOAIHANG SET TrangThai = 0 WHERE MaLH = ?";
	            break;
	        default:
	            return false;
	    }
	    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
	        if (condition.equals("xoaloaihang")) {
	            pstm.setString(1, Lh.getMaLH());
	        }
	        if (condition.equals("themloaihang")) {
	            pstm.setString(1, Lh.getTenLH());
	            pstm.setString(2, ""+1);
	            pstm.setString(3, Lh.getMaLH());
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
