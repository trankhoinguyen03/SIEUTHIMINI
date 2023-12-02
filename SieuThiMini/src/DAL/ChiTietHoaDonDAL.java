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
			String sql = "select * from CHITIETHOADON";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				ChiTietHoaDon cthd = new ChiTietHoaDon();
				cthd.setMaHd(rs.getString(1)); 
				cthd.setMaSp(rs.getString(2));
                cthd.setSoLuong(rs.getString(3));
                cthd.setThanhTien(rs.getString(4));
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
			sql =  "INSERT INTO CHITIETHOADON( MaHD, MaSP, SoLuong, ThanhTien, TrangThai) VALUES (?, ?, ?, ?, ?)";
		}
	
		PreparedStatement pstm = conn.prepareStatement(sql);
			
		try {

            pstm.setString(1,hdct.getMaHd());
			pstm.setString(2,hdct.getMaSp());
			pstm.setString(3, hdct.getSoLuong());
			pstm.setString(4, Integer.parseInt(hdct.getThanhTien())+"");
			pstm.setString(5, ""+1);
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
	public boolean updatechitiethoadon(ChiTietHoaDon hdct, String mahd) throws SQLException {
	    String updateSQL = "UPDATE CHITIETHOADON SET MaSP=?, SoLuong=?, ThanhTien=? WHERE MaHD = ?";
	    boolean success = false;

	    try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
	    	pstmt.setString(1,hdct.getMaSp());
			pstmt.setString(2, hdct.getSoLuong());
			pstmt.setString(3, Integer.parseInt(hdct.getThanhTien())+"");
			pstmt.setString(4, mahd);
	        int affectedRows = pstmt.executeUpdate();

	        if (affectedRows > 0) {
	            // Nếu có hàng bị ảnh hưởng bởi cập nhật
	            success = true; // Đặt biến success thành true
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Xử lý lỗi khi cập nhật không thành công
	    }

	    return success; // Trả về giá tr ị success để biết kết quả cập nhật
	}
       public boolean xoaSanPham(String masp, String mahd) throws SQLException {
    String sql = "UPDATE CHITIETHOADON SET TrangThai = 0 WHERE MaSP = ? AND MaHD = ?";
    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
        pstm.setString(1, masp);
        pstm.setString(2, mahd);
        int rowsUpdated = pstm.executeUpdate();
    }
    return true;
}

         public boolean xoaHoaDon(String maHoaDon) throws SQLException {
    String sql = "UPDATE HOADONCHITIET SET TrangThai = 0 WHERE MaHD = ?";
    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
        pstm.setString(1, maHoaDon);
        int rowsDeleted = pstm.executeUpdate();
    }
            return true;
}
}
