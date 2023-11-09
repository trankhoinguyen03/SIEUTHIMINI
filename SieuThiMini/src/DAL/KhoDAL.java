package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.LoaiHang;
import DTO.SanPham;
import DTO.Kho;

public class KhoDAL extends connectSql {
	
	
	public KhoDAL() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public ArrayList<Kho> docKho(){
		ArrayList<Kho> arrKho = new ArrayList<Kho>();
		try {
			String sql = "select * from KHO where TrangThai = 1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				Kho kho = new Kho();
				kho.setMaSP(rs.getString(1)); 
				kho.setSoLuong(rs.getString(2));
				arrKho.add(kho);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
//		closeConnection();
		return arrKho;
	}
	public int getLastMaSP() throws SQLException {
		String sql = "select top 1 MaSP from KHO order by MaSP DESC";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		  if (rs.next()) {
		        int maxColumnValue = rs.getInt("MaSP");
		        return maxColumnValue;
		    }
		  closeConnection();
		  return 0;
	}
	
	public boolean anSanPham(String masp) throws SQLException {
		String sql = "UPDATE KHO SET TrangThai = 0 where MaSP = ?";
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setString(1, masp);
			int rowsUpdated = pstm.executeUpdate();

			return rowsUpdated > 0;
		}
	}
	
    public String laySoLuong(String value) throws SQLException {
        String sql = "SELECT SoLuong FROM KHO WHERE MaSP = ?";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setString(1, value);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("SoLuong");
            }
        }
        return null;
    }
	
    public boolean capNhatSoLuong(String value, String id) throws SQLException {
        String sql = "UPDATE KHO SET SoLuong = ? WHERE MaSP = ?";
        try ( PreparedStatement pstm = conn.prepareStatement(sql)) {
        	pstm.setString(1, value);
        	pstm.setString(2, id);
            int rowsUpdated = pstm.executeUpdate();
            return rowsUpdated > 0;
        }
    }
	public boolean themSoLuong(String value, String id) throws SQLException {
		String sql = "INSERT INTO KHO (SoLuong, TrangThai, MaSP) VALUES (?, ?, ?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		try {
			pstm.setString(1, value);
			pstm.setString(2, ""+1);
			pstm.setString(3, id);
			pstm.executeUpdate();
			closeConnection();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			closeConnection();
			return false;
		}
	}
    
	public static void main(String[] args) throws SQLException {
		KhoDAL kho = new KhoDAL();
		if(kho.themSoLuong("12", "SP011"))
			System.out.println("thêm thành công");
	}
}
