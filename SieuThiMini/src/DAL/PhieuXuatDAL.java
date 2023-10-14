package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.HoaDon;
import DTO.ChiTietHoaDon;
import DTO.PhieuXuat;

public class PhieuXuatDAL extends connectSql {
	
	
	public PhieuXuatDAL() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public ArrayList<PhieuXuat> docCtHoaDon(){
		ArrayList<PhieuXuat> arrPx = new ArrayList<PhieuXuat>();
		try {
			String sql = "select * from PHIEUXUAT";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				PhieuXuat px = new PhieuXuat();
				px.setMaNV(rs.getInt("MaNV")); 
				px.setMaPX(rs.getInt("MaPX"));
                                px.setThoiDiemLap(rs.getString("ThoiDiemLap"));
                                
				arrPx.add(px);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return arrPx;
	}
	
	
       public boolean xoaSanPham(String masp, String mahd) throws SQLException {
    String sql = "DELETE FROM HANDONCHITIET WHERE MaSP = ? AND MaHD = ?";
    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
        pstm.setString(1, masp);
        pstm.setString(2, mahd);
        int rowsUpdated = pstm.executeUpdate();
    }
    return true;
}
public boolean themhoadon(PhieuXuat px,String condition) throws SQLException {
		String sql = "";
		if(condition.equals("themhoadon")) {
			sql =  "INSERT INTO PHIEUXUAT (MaNV,ThoiDiemLap) VALUES (?, ?)";
		}
                
			
		try {
                    PreparedStatement pstm = conn.prepareStatement(sql);
//			pstm.setInt(1,hd.getMaHd());
			pstm.setInt(1, px.getMaNV());
			pstm.setString(2, px.getThoiDiemLap());
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

} 
 public int getLastMaPX() throws SQLException {
        int lastMaPX = 0;
        String sql = "SELECT MAX(MaPX) FROM PHIEUXUAT";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                lastMaPX = rs.getInt(1);
            }
        }
        return lastMaPX;
    }
         public boolean xoaHoaDon(String maHoaDon) throws SQLException {
    String sql = "DELETE FROM HOADONCHITIET WHERE MaHD = ?";
    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
        pstm.setString(1, maHoaDon);
        int rowsDeleted = pstm.executeUpdate();
    }
            return true;
}
}
