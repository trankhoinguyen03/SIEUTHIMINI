package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.TaiKhoan;
public class TaiKhoanDAL extends connectSql {

	public TaiKhoanDAL() throws SQLException {
		super();	
	}
	public ArrayList<DTO.TaiKhoan> kiemTraDangNhap() throws SQLException{
		ArrayList<DTO.TaiKhoan> arrTk = new ArrayList<DTO.TaiKhoan>();
		String sql ="select * from TAIKHOAN where TrangThai = 1";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			DTO.TaiKhoan tk = new DTO.TaiKhoan();
			tk.setMaNV(rs.getString("MaNV"));
			tk.setTenDangNhap(rs.getString("TenDangNhap"));
			tk.setMatKhau(rs.getString("MatKhau"));
			tk.setQuyen(rs.getString("MaQuyen"));
			arrTk.add(tk);
		}
		return arrTk;
	}

	

	    public ArrayList<TaiKhoan> docTaiKhoan(String condition) {
	        String sql = "";
	        ArrayList<TaiKhoan> arrList = new ArrayList<TaiKhoan>();
	        try {
	            if (condition.equals("sapxeptheoten")) {
	                sql = "select * from TAIKHOAN where TrangThai = 1 order by TenDangNhap";
	            }
	            if (condition.equals("doctaikhoan")) {
	                sql = "select * from TAIKHOAN where TrangThai = 1";
	            }
	            if (condition.equals("sapxeptheoma")) {
	                sql = "select * from TAIKHOAN where TrangThai = 1 order by MaNV";
	            }
	            PreparedStatement pstm = conn.prepareStatement(sql);
	            ResultSet rs = pstm.executeQuery();
	            while (rs.next()) {
	                TaiKhoan tk = new TaiKhoan();
	                tk.setMaNV(rs.getString("MaNV"));
	                tk.setTenDangNhap(rs.getString("TenDangNhap"));
	                tk.setMatKhau(rs.getString("MatKhau"));
	                tk.setQuyen(rs.getString("MaQuyen"));
	                arrList.add(tk);
	            }
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return arrList;
	    }
	    
	    public String layTenQuyen(String id) throws SQLException {
	        String sql = "SELECT VaiTro FROM QUYEN WHERE MaQuyen = ?";
	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        	pstmt.setString(1, id);
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                return rs.getString(1);
	            }
	        }
	        return null;
	    }

	    public String layMaQuyen(String name) throws SQLException {
	        String sql = "SELECT MaQuyen FROM QUYEN WHERE VaiTro LIKE ?";
	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        	pstmt.setString(1, name);
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                return rs.getString(1);
	            }
	        }
	        return null;
	    }
	    public String MaQuyen(String name) throws SQLException {
	        String sql = "SELECT MaQuyen FROM TAIKHOAN WHERE MaNV=?";
	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        	pstmt.setString(1, name);
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                return rs.getString(1);
	            }
	        }
	        return null;
	    }
	    
	    public boolean anTaiKhoan(String id) throws SQLException {
	        String sql = "UPDATE TAIKHOAN SET TrangThai =  0 WHERE MaNV = ?";
	        try ( PreparedStatement pstm = conn.prepareStatement(sql)) {
	        	pstm.setString(1, id);
	            int rowsUpdated = pstm.executeUpdate();
	            return rowsUpdated > 0;
	        }
	    }
	    
	    public boolean suaTaiKhoan(TaiKhoan tk) throws SQLException {
	        String sql = "UPDATE TAIKHOAN SET TenDangNhap = ?, MatKhau = ?, MaQuyen = ? WHERE MaNV = ?";
	        try ( PreparedStatement pstm = conn.prepareStatement(sql)) {
	            pstm.setString(1, tk.getTenDangNhap());
	            pstm.setString(2, tk.getMatKhau());
	            pstm.setString(3, tk.getQuyen());
	            pstm.setString(4, tk.getMaNV());
	            int rowsUpdated = pstm.executeUpdate();
	            return rowsUpdated > 0;
	        }
	    }

	    public boolean themTaiKhoan(TaiKhoan tk) throws SQLException {
	        String sql = "INSERT INTO TAIKHOAN (TenDangNhap, MatKhau, MaQuyen, TrangThai, MaNV) VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement pstm = conn.prepareStatement(sql);

	        try {
	            
	            pstm.setString(1, tk.getTenDangNhap());
	            pstm.setString(2, tk.getMatKhau());
	            pstm.setString(3, tk.getQuyen());
	            pstm.setString(4, ""+1);
	            pstm.setString(5, tk.getMaNV());
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
			TaiKhoanDAL tkd = new TaiKhoanDAL();
			System.out.println(tkd.kiemTraDangNhap());
		}
}