
 
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
    public HoaDonDAL() throws SQLException{
            super();
    }
    public ArrayList<HoaDon> docHoaDon(String condition, String value){
        String sql="";
        ArrayList<HoaDon> arr =new ArrayList<HoaDon>();
        try{
            if(condition.equals("docHoaDon")){
                sql="sql=select* from HOADON WHERE isDeleted=1";
            }
            
            PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				HoaDon hd = new HoaDon(); 
				hd.setMaHd(rs.getInt("MaHD"));
				hd.setMucGiam(rs.getFloat("MucGiam"));
				hd.setMaNv(rs.getInt("MaNV"));
				hd.setMaKh(rs.getInt("MaKH"));
				hd.setThoiDiem(rs.getString("ThoiDiemLap"));
				hd.setGio(rs.getString("GioMua"));
				hd.setTienTra(rs.getFloat("TienTra"));
                                hd.setDiemThuong(rs.getFloat("DiemThuong"));
				hd.setIsDeleted(rs.getInt("isDeleted"));
				arr.add(hd);
			}
        }catch (Exception e){
}
    return arr;
}
 public int LayMaHd (String mahd) throws SQLException{
     String sql = "SELECT MaLH FROM HOADONCHITIET WHERE MaSP = ?,SoLuong=?;";
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
public boolean xoaHoaDon(String maHoaDon) throws SQLException {
  String sql = "DELETE FROM HOADON WHERE MaHD = ?";
   try (PreparedStatement pstm = conn.prepareStatement(sql)) {
       pstm.setString(1, maHoaDon);
      int rowsDeleted = pstm.executeUpdate();
  }
   
        return true;
}
 public boolean themhoadon(HoaDon hd,String condition,String MaHDcu) throws SQLException {
		String sql = "";
		if(condition.equals("themhoadon")) {
			sql =  "INSERT INTO HOADON ( ThoiDiemLap, TongTienTra, MucGiam, DiemThuong, MaNV, MaKH,GioMua,isDeleted) VALUES (?, ?, ?, ?, ?, ?,?,?)";
		}
		if(condition.equals("suahoadon")) {
		sql = "UPDATE HOADON SET MaHd = ?, ThoiDiemLap = ?, TongTienTra = ?, MucGiam = ?, DiemThuong = ?, MaNV = ?, MaKH = ?, isDeleted = ?,GioMua = ? WHERE MaHD = ?";

		}
		PreparedStatement pstm = conn.prepareStatement(sql);
			
		try {

//			pstm.setInt(1,hd.getMaHd());
			pstm.setString(1, hd.getThoiDiem());
			pstm.setFloat(2, hd.getTienTra());
			pstm.setFloat(3, hd.getMucGiam());
			pstm.setFloat(4,hd.getDiemThuong());
			pstm.setInt(5, hd.getMaNv());
			pstm.setInt(6,hd.getMaKh());
			pstm.setString(7,hd.getGio());
			pstm.setInt(8, 1);
			
			if(condition.equals("suahoadon")) {
				System.out.println(hd.getMaHd());
				System.out.println(MaHDcu);
				pstm.setInt(10, Integer.parseInt(MaHDcu));
			}
			
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
} 
 public int getLastMaHD() throws SQLException {
        int lastMaHD = 0;
        String sql = "SELECT MAX(MaHD) FROM HOADON";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                lastMaHD = rs.getInt(1);
            }
        }
        return lastMaHD;
    }
 public  int getmahd(int isdeleted) throws SQLException {
        int mahoadon = 0;
        
        String sql = "SELECT MaHD FROM HOADON where isDeleted=1";
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
 public int getTongKho(int masp) throws SQLException {
	 String sql = "{ call tongkhoa(?) }"; // Modify the SQL statement to use the correct number of parameters
		
		
	    CallableStatement cstmt = conn.prepareCall(sql);
	    cstmt.setInt(1,masp);
	    ResultSet rs = cstmt.executeQuery();
	while(rs.next()) {
		return rs.getInt("SoLuongHang");
	}
	return 1;
 }
 public int getTongBan(int masp) throws SQLException {
	 String sql = "{ call tongban(?) }"; // Modify the SQL statement to use the correct number of parameters
		
		
	    CallableStatement cstmt = conn.prepareCall(sql);
	    cstmt.setInt(1,masp);
	    ResultSet rs = cstmt.executeQuery();
	while(rs.next()) {
		return rs.getInt("SoLuongHang");
	}
	return 1;
 }
 public int getSoLuongTonKho(int masp) throws SQLException {
	 return getTongKho(masp)-getTongBan(masp);
 }
 public static void main(String[] args) throws SQLException {
	HoaDonDAL hdd = new HoaDonDAL();
	System.out.println(hdd.getSoLuongTonKho(3));
}
}






