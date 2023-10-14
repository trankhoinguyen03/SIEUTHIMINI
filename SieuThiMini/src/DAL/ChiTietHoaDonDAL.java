package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.HoaDon;
import DTO.ChiTietHoaDon;

public class ChiTietHoaDonDAL extends connectSql {
	
	
	public ChiTietHoaDonDAL() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public ArrayList<ChiTietHoaDon> docCtHoaDon(){
		ArrayList<ChiTietHoaDon> arrHd = new ArrayList<ChiTietHoaDon>();
		try {
			String sql = "select * from HOADONCHITIET";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				ChiTietHoaDon cthd = new ChiTietHoaDon();
				cthd.setMaHd(rs.getInt(1)); 
				cthd.setMaSp(rs.getInt(2));
                                cthd.setSl(rs.getInt(3));
                                
				arrHd.add(cthd);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return arrHd;
	}
	
	public boolean themhoadon(ChiTietHoaDon hdct,String condition) throws SQLException {
		String sql = "";
		if(condition.equals("themhoadon")) {
			sql =  "INSERT INTO HOADONCHITIET( MaHD,MaSP, SoLuong) VALUES (?,?, ?)";
		}
	
		PreparedStatement pstm = conn.prepareStatement(sql);
			
		try {

                       pstm.setInt(1,hdct.getMaHd());
			pstm.setInt(2,hdct.getMaSp());
			pstm.setInt(3, hdct.getSl());
			
//			if(condition.equals("suahoadon")) {
//				System.out.println(hd.getMaHd());
//				System.out.println(MaHDcu);
//				pstm.setInt(10, Integer.parseInt(MaHDcu));
//			}
			
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return true;
		}
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

         public boolean xoaHoaDon(String maHoaDon) throws SQLException {
    String sql = "DELETE FROM HOADONCHITIET WHERE MaHD = ?";
    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
        pstm.setString(1, maHoaDon);
        int rowsDeleted = pstm.executeUpdate();
    }
            return true;
}
}
