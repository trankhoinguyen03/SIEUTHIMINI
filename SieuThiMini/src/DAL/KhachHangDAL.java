package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.KhachHang;

public class KhachHangDAL extends connectSql {
	
	
	public KhachHangDAL() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
//	public  String getTen(String ten) throws SQLException {
//        
//        
//        String sql = "SELECT HoTen FROM KHACHHANG  ";
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) { 
//             pstmt.setInt(1,makh);
//    pstmt.setFetchSize(100);
//            ResultSet rs = pstmt.executeQuery();
//            if (rs.next()) {
//                ten = rs.getString("HoTen");
//            }
//        }
//        return ten;
//    }
         public  int getmakh(String name) throws SQLException {
        int makhachhang = 0;
        
        String sql = "SELECT MaKH FROM KHACHHANG where TrangThai=1 and TenKH Like ? ";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) { 
        	pstmt.setString(1,name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                makhachhang = rs.getInt("MaKH");
            }
        }
        return makhachhang;
 }
	public ArrayList<KhachHang> docKhachHang(String condition)  {
		String sql = "";
		ArrayList<KhachHang> arrList = new ArrayList<KhachHang>();
                try {
			if (condition.equals("sapxeptheoten")) {
				sql = "select * from KHACHHANG where TrangThai = 1 order by TenKH";
			}
			if (condition.equals("dockhachhang")) {
				sql = "select * from KHACHHANG where TrangThai = 1";
			}
			if (condition.equals("sapxeptheomakh")) {
				sql = "select * from KHACHHANG where TrangThai = 1 order by MaKH";
			}
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();	
			while (rs.next()) {
				KhachHang kh = new KhachHang();
				kh.setMaKh(rs.getString("MaKH"));
                kh.setTenKh(rs.getString("TenKH"));
                kh.setSoDienThoai(rs.getString("SDT"));
                kh.setTichDiem(rs.getString("TichDiem"));
				arrList.add(kh);
			}
		} catch (Exception e) {
			
		}
		return arrList;
	}
    public String layMaKHcuoi() throws SQLException {
        String sql = "SELECT MAX(MaKH) FROM KHACHHANG";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        }
        return null;
    }
        
    public boolean anKhachHang(String id) throws SQLException {
		String sql = "UPDATE KHACHHANG SET TrangThai = 0 WHERE MaKH = ?";
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setString(1, id);
			int rowsUpdated = pstm.executeUpdate();
			return rowsUpdated > 0;
		}
	}
        public boolean themKhachHang(KhachHang kh) throws SQLException {
		String sql = "INSERT INTO KHACHHANG (TenKH, SDT, TichDiem, TrangThai, MaKH) VALUES (?, ?, ?, ?, ?)";	
		PreparedStatement pstm = conn.prepareStatement(sql);
		try {		
			pstm.setString(1, kh.getTenKh());
            pstm.setString(2, kh.getSoDienThoai());
			pstm.setString(3, kh.getTichDiem());
			pstm.setString(4, ""+1);
			pstm.setString(5, kh.getMaKh());
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
		KhachHangDAL kh = new KhachHangDAL();
		KhachHang khthem = new KhachHang();
        }
}