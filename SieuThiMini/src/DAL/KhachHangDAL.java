package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BLL.KhachHang;

public class KhachHangDAL extends connectSql {
	
	
	public KhachHangDAL() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
//	public  String getTen(String ten) throws SQLException {
//        
//        
//        String sql = "SELECT HoTen FROM KHTT  ";
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
        
        String sql = "SELECT MaKH FROM KHTT where isDeleted=1 and HoTen Like ? ";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) { 
        	pstmt.setString(1,name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                makhachhang = rs.getInt("MaKH");
            }
        }
        return makhachhang;
 }
	public ArrayList<KhachHang> docKhachHang(String condition, String value)  {
		String sql = "";
		ArrayList<KhachHang> arrList = new ArrayList<KhachHang>();
                try {
			if (condition.equals("sapxeptheoten")) {
				sql = "select * from KHTT where isDeleted = 1 order by HoTen";
			}
			if (condition.equals("dockhachhang")) {
				sql = "select * from KHTT where isDeleted = 1";
			}
			if (condition.equals("sapxeptheomakh")) {
				sql = "select * from KHTT where isDeleted = 1 order by MaKH";
			}
			if (condition.equals("timkiem")) {
				sql = "select * from KHTT where isDeleted = 1 and MaKH = ?";

			}
			PreparedStatement pstm = conn.prepareStatement(sql);
			if (condition.equals("timkiem")) {
				pstm.setString(1,value);
			}
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				KhachHang kh = new KhachHang();
				kh.setMakh(rs.getInt("MaKH"));
                                kh.setHoTen(rs.getString("HoTen"));
				kh.setDiemThuong(rs.getFloat("DiemThuong"));
                                kh.setDiaChi(rs.getString("DiaChi"));
                                kh.setImg(rs.getString("img"));
                                kh.setNgayCapThe(rs.getString("NgayCapThe"));
                                kh.setNgayMuaGanNhat(rs.getString("NgayMuaGanNhat"));
				arrList.add(kh);
			}
		} catch (Exception e) {
			
		}
		return arrList;
	}
        
        public boolean xoaKhachHang(String makh) throws SQLException {
		String sql = "UPDATE KHTT SET isDeleted = " + 0 + " where MaKH = " + makh;
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			int rowsUpdated = pstm.executeUpdate();

			return rowsUpdated > 0;
		}
	}
        public boolean themkhachhang(KhachHang kh, String condition, String oldMaKH) throws SQLException {
		String sql = "";
		if (condition.equals("themkhachhang")) {
			sql = "INSERT INTO KHTT (HoTen, DiaChi, NgayCapThe, NgayMuaGanNhat, DiemThuong, isDeleted) VALUES (?, ?, ?, ?, ?, ?)";
		}
		if (condition.equals("suakhachhang")) {
			sql = "UPDATE KHTT SET HoTen = ?, DiaChi = ?, NgayCapThe = ?, NgayMuaGanNhat = ?, DiemThuong = ?, isDeleted = ? WHERE MaKH = ?";

		}
		PreparedStatement pstm = conn.prepareStatement(sql);

		try {
			
			pstm.setString(1, kh.getHoTen());
                        pstm.setString(2, kh.getDiaChi());
			pstm.setString(3, kh.getNgayCapThe());
                        pstm.setString(4, kh.getNgayMuaGanNhat());
                        pstm.setFloat(5, kh.getDiemThuong());
			pstm.setInt(6, 1);

			if (condition.equals("suakhachhang")) {
				pstm.setInt(7, Integer.parseInt(oldMaKH));
			}

			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
        public static void main(String[] args) throws SQLException {
		KhachHangDAL kh = new KhachHangDAL();
		KhachHang khthem = new KhachHang();
        }
}