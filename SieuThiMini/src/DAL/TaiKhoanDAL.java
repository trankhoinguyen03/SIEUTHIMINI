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

	

	    public ArrayList<TaiKhoan> doctaikhoan(String condition, String value) {
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
	            if (condition.equals("timkiem")) {
	                sql = "select * from TAIKHOAN where TrangThai = 1 and MaNV LIKE %" + value + "% ";
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
	    
	    public int getLastMaNV() throws SQLException {
	        int lastMaNV = 0;
	        String sql = "SELECT MAX(MaNV) FROM TAIKHOAN";
	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                lastMaNV = rs.getInt(1);
	            }
	        }
	        return lastMaNV;
	    }

	    public boolean xoataikhoan(String manv) throws SQLException {
	        String sql = "UPDATE TAIKHOAN SET TrangThai = " + 0 + " where MaNV = " + manv;
	        try ( PreparedStatement pstm = conn.prepareStatement(sql)) {
	            int rowsUpdated = pstm.executeUpdate();

	            return rowsUpdated > 0;
	        }
	    }

	    public boolean themtaikhoan(TaiKhoan tk, String condition, String oldMaNV) throws SQLException {
	        String sql = "";
	        if (condition.equals("themtaikhoan")) {
	            sql = "INSERT INTO TAIKHOAN (TenDangNhap, MatKhau, MaQuyen, TrangThai) VALUES (?, ?, ?, ?)";
	        }
	        PreparedStatement pstm = conn.prepareStatement(sql);

	        try {
	            
	            pstm.setString(1, tk.getTenDangNhap());
	            pstm.setString(2, tk.getMatKhau());
	            pstm.setString(3, tk.getQuyen());
	            pstm.setString(4, ""+1);

	            pstm.executeUpdate();
	            System.out.println(sql);
	            return true;
	        } catch (Exception e) {
	            // TODO: handle exception
	            return false;
	        }
	    }
		public static void main(String[] args) throws SQLException {
			TaiKhoanDAL tkd = new TaiKhoanDAL();
			System.out.println(tkd.kiemTraDangNhap());
		}
}