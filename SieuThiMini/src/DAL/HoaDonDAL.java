
 
package DAL;
import java.beans.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DTO.HoaDon;

public class HoaDonDAL extends connectSql{
    private static final String TichDiem = null;

	public HoaDonDAL() throws SQLException{
            super();
    }
    public ArrayList<HoaDon> docHoaDon(String condition, String value){
        String sql="";
        ArrayList<HoaDon> arr =new ArrayList<HoaDon>();
        try{
            if(condition.equals("docHoaDon")){
                sql="sql=select* from DSHOADON WHERE TrangThai=1";
            }
            
            PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				HoaDon hd = new HoaDon(); 
				hd.setMaHD(rs.getString("MaHD"));
				hd.setMaNV(rs.getString("MaNV"));
				hd.setMaKH(rs.getString("MaKH"));
				hd.setTongTien(rs.getString("TongTien"));
				hd.setTongTienSauKM(rs.getString("TongTienSauKM"));
				hd.setThoiDiemLap(rs.getString("ThoiDiemLap"));
			}
        }catch (Exception e){
}
    return arr;
}
 public int LayMaHd (String mahd) throws SQLException{
     String sql = "SELECT MaHD FROM HOADONCHITIET WHERE MaSP = ?,SoLuong=?;";
		    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
		        pstm.setString(1, mahd);
		        ResultSet rs = pstm.executeQuery();
		        if (rs.next()) {
		            return rs.getInt("MaHD");
		        } else {
		            return -1; // or throw an exception
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return -1; // or throw an exception
		    }
	}

 public boolean themhoadon(HoaDon hd,String condition,String MaHDcu) throws SQLException {
	
		String sql = "";
		if(condition.equals("themhoadon")) {
			sql =  "INSERT INTO DSHOADON (MaHD, MaNV, MaKH, TongTien, TongTienSauKM, ThoiDiemLap, MaKM,TrangThai) VALUES (?,?,?,?,?,?,?,?)";
		}
		PreparedStatement pstm = conn.prepareStatement(sql);
		try {

			pstm.setString(1,hd.getMaHD());
			pstm.setString(2, hd.getMaNV());
			pstm.setString(3, hd.getMaKH());
			pstm.setString(4, Integer.parseInt(hd.getTongTien())+"");
			pstm.setString(5, Integer.parseInt(hd.getTongTienSauKM())+"");
			pstm.setString(6, hd.getThoiDiemLap());
			pstm.setString(7, hd.getKhuyenMai());
			pstm.setString(8, ""+1);
			
			
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
} 
 public String getLastMaHD() throws SQLException {
        String lastMaHD = "HD000";
        String sql = "SELECT MAX(MaHD) FROM DSHOADON";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                lastMaHD = rs.getString(1);
            }
        }
        return lastMaHD;
    }
 public  int getmahd(int TrangThai) throws SQLException {
        int mahoadon = 0;
        
        String sql = "SELECT MaHD FROM HOADON where TrangThai=1";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) { 
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                mahoadon = rs.getInt("MaHD");
            }
        }
        return mahoadon;
 }
 public void deleteBill(String maSP){
         String sqlDelete="DELETE FROM HoaDon WHERE MaSP = ?";
            try{
                PreparedStatement pstmt = conn.prepareStatement(sqlDelete);
            
                pstmt=conn.prepareStatement(sqlDelete);
                pstmt.setString(1, maSP);
                pstmt.executeUpdate();
                
            
 } catch(Exception ex){
                ex.printStackTrace();
            }
}
 public int getmakh(int mahd ) throws SQLException {
        String sql = "SELECT MaKH FROM SANPHAM where MaHD=?";
try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    pstmt.setFetchSize(100); // chỉ định số lượng bản ghi được trả về trong mỗi lần truy xuất
    ResultSet rs = pstmt.executeQuery();
    if (rs.next()) {
        return rs.getInt("MaKH");
    } else {
        return -1;
    }
}
}
 
	 public boolean updatehoadon(HoaDon hd, String mahd) throws SQLException {
		    String updateSQL = "UPDATE DSHOADON SET TongTien = ?, TongTienSauKM = ?, KhuyenMai = ? WHERE MaHD = ?";
		    boolean success = false;

		    try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
		        pstmt.setString(1, Integer.parseInt(hd.getTongTien())+"");
		        pstmt.setString(2, Integer.parseInt(hd.getTongTienSauKM())+"");
		        pstmt.setString(3, hd.getKhuyenMai());
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

		    return success; // Trả về giá trị success để biết kết quả cập nhật
		}


 public String getMucGiam(String makm) throws SQLException{
	 String giamgia="0";
	 String sql="Select MucGiam FROM KHUYENMAI,DSHOADON WHERE KHUYENMAI.MaKM=DSHOADON.MaKM and DSHOADON.MaKM=? ";
	 try (PreparedStatement pstmt = conn.prepareStatement(sql)) { 
     	 pstmt.setString(1, makm);
         ResultSet rs = pstmt.executeQuery();
         if (rs.next()) {
             giamgia = rs.getString("MucGiam");
         }
     }
     return giamgia;
 }
 public int getdiemtong(String makh, String makm) throws SQLException {
	    int diem = 0;
	    String updateSQL = "UPDATE KHACHHANG SET TichDiem = " +
	        "CASE " +
	            "WHEN D.MaKM = 'KM1' THEN TichDiem - 0 " +
	            "WHEN D.MaKM = 'KM2' THEN TichDiem - 15 " +
	            "WHEN D.MaKM = 'KM3' THEN TichDiem - 30 " +
	            "WHEN D.MaKM = 'KM4' THEN TichDiem - 45 " +
	        "END " +
	        "FROM KHACHHANG K " +
	        "INNER JOIN DSHOADON D ON K.MaKH = D.MaKH " +
	        "WHERE D.MaKM = ? " +
	        "AND K.MaKH = ?";

	    try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
	        pstmt.setString(1, makm);
	        pstmt.setString(2, makh);
	        int affectedRows = pstmt.executeUpdate();
	        if (affectedRows > 0) {
	            String selectSQL = "SELECT TichDiem FROM KHACHHANG WHERE MaKH = ?";
	            try (PreparedStatement selectStmt = conn.prepareStatement(selectSQL)) {
	                selectStmt.setString(1, makh);
	                ResultSet resultSet = selectStmt.executeQuery();
	                if (resultSet.next()) {
	                    diem = resultSet.getInt("TichDiem");
	                }
	            }
	        }
	    }
	    return diem;
	}

 public int gettangdiemtong(String makh, String tongtien) throws SQLException {
	    int diem = 0;

	    String updateSQL = "UPDATE KHACHHANG " +
	        "SET TichDiem = " +
	        "CASE " +
	            "WHEN D.TongTien > 50000 AND D.TongTien <= 100000 THEN TichDiem + 5 " +
	            "WHEN D.TongTien > 100000 AND D.TongTien <= 200000 THEN TichDiem + 8 " +
	            "WHEN D.TongTien > 200000 AND D.TongTien <=350000 THEN TichDiem + 12 " +
	            "WHEN D.TongTien >350000 THEN TichDiem + 20 " +
	        "END " +
	        "FROM KHACHHANG K " +
	        "INNER JOIN DSHOADON D ON K.MaKH = D.MaKH " +
	        "WHERE D.TongTien = ? AND K.MaKH = ?";

	    try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
	        pstmt.setString(1, makh);
	        pstmt.setString(2, tongtien);
	        int affectedRows = pstmt.executeUpdate();
	        if (affectedRows > 0) {
	            String selectSQL = "SELECT TichDiem FROM KHACHHANG WHERE MaKH = ?";
	            try (PreparedStatement selectStmt = conn.prepareStatement(selectSQL)) {
	                selectStmt.setString(1, makh);
	                ResultSet resultSet = selectStmt.executeQuery();
	                if (resultSet.next()) {
	                    diem = resultSet.getInt("TichDiem");
	                }
	            }
	        }
	    }
	    return diem;
	}

 public  String getdiemthuong(String makh) throws SQLException {
     String diemthuong = "0";
     
     String sql = "SELECT TichDiem FROM KHACHHANG where makh=?";
     try (PreparedStatement pstmt = conn.prepareStatement(sql)) { 
     	 pstmt.setString(1, makh);
         ResultSet rs = pstmt.executeQuery();
         if (rs.next()) {
             diemthuong = rs.getString("TichDiem");
         }
     }
     return diemthuong;
}
 
 
// int TichDiemnew=T
// String sql="UPDATE KHACHHANG SET TichDiem="
 public int getTongKho(String masp) throws SQLException {
	 String sql = "{ call soluongkho(?) }";

	    try (CallableStatement cstmt = conn.prepareCall(sql)) {
	        cstmt.setString(1, masp);
	        try (ResultSet rs = cstmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt("soluong");
	            }
	        }
	    } catch (SQLException e) {
	        // Xử lý exception, có thể throw hoặc log lỗi
	        e.printStackTrace();
	    }
	    return 0; // hoặc giá trị mặc định khác nếu không có kết quả
	}

	public int getTongBan(String masp) throws SQLException {
		String sql = "{ call soluongban(?) }";

	    try (CallableStatement cstmt = conn.prepareCall(sql)) {
	        cstmt.setString(1, masp);
	        try (ResultSet rs = cstmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt("soluong");
	            }
	        }
	    } catch (SQLException e) {
	        // Xử lý exception, có thể throw hoặc log lỗi
	        e.printStackTrace();
	    }
	    return 0; // hoặc giá trị mặc định khác nếu không có kết quả
	}

	public int getSoLuongTonKho(String masp) throws SQLException {
	    int tongKho = getTongKho(masp);
	    int tongBan = getTongBan(masp);
	    return tongKho - tongBan;
	}

 public static void main(String[] args) throws SQLException {
	HoaDonDAL hdd = new HoaDonDAL();
	System.out.println(hdd.getSoLuongTonKho("SP002"));
}


}






