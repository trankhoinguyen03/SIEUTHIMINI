package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BLL.NhanVien;
public class NhanVienDAL extends connectSql {

	public NhanVienDAL() throws SQLException {
		super();	
	}
	public ArrayList<DTO.NhanVien> kiemTraDangNhap() throws SQLException{
		ArrayList<DTO.NhanVien> arrNv = new ArrayList<DTO.NhanVien>();
		String sql ="select * from NHANVIEN where isDeleted = 1";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			DTO.NhanVien nv = new DTO.NhanVien();
			nv.setTaiKhoan(rs.getString("TaiKhoan"));
			nv.setMatKhau(rs.getString("MatKhau"));
			nv.setChucVu(rs.getInt("ChucVu"));
			nv.setMaNv(rs.getInt("MaNV"));
			nv.setHoTen(rs.getString("HoTen"));
			
			arrNv.add(nv);
		}
		return arrNv;
	}

	

	    public ArrayList<NhanVien> docNhanVien(String condition, String value) {
	        String sql = "";
	        ArrayList<NhanVien> arrList = new ArrayList<NhanVien>();
	        try {
	            if (condition.equals("sapxeptheoten")) {
	                sql = "select * from NHANVIEN where isDeleted = 1 order by HoTen";
	            }
	            if (condition.equals("docnhanvien")) {
	                sql = "select * from NHANVIEN where isDeleted = 1";
	            }
	            if (condition.equals("sapxeptheoma")) {
	                sql = "select * from NHANVIEN where isDeleted = 1 order by MaNV";
	            }
	            if (condition.equals("timkiem")) {
	                sql = "select * from NHANVIEN where isDeleted = 1 and MaNV LIKE %" + value + "% ";
	            }
	            PreparedStatement pstm = conn.prepareStatement(sql);
	            ResultSet rs = pstm.executeQuery();
	            while (rs.next()) {
	                NhanVien nv = new NhanVien();
	                nv.setMaNv(rs.getInt("MaNV"));
	                nv.setTenNv(rs.getString("HoTen"));
	                nv.setNgaySinh(rs.getString("NgaySinh"));
	                nv.setGioiTinh(rs.getInt("GioiTinh"));
	                nv.setDiaChi(rs.getString("DiaChi"));
	                nv.setCmnd(rs.getString("CMND"));
	                nv.setSdt(rs.getString("DienThoai"));
	                nv.setNgayVaoLam(rs.getString("NgayVaoLam"));
	                nv.setTaiKhoan(rs.getString("TaiKhoan"));
	                nv.setMatKhau(rs.getString("MatKhau"));
	                nv.setIsDeleted(rs.getInt("isDeleted"));
	                arrList.add(nv);
	            }
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return arrList;
	    }
	    
	    public int getLastMaNV() throws SQLException {
	        int lastMaNV = 0;
	        String sql = "SELECT MAX(MaNV) FROM NHANVIEN";
	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                lastMaNV = rs.getInt(1);
	            }
	        }
	        return lastMaNV;
	    }

	    public boolean xoaNhanVien(String manv) throws SQLException {
	        String sql = "UPDATE NHANVIEN SET isDeleted = " + 0 + " where MaNV = " + manv;
	        try ( PreparedStatement pstm = conn.prepareStatement(sql)) {
	            int rowsUpdated = pstm.executeUpdate();

	            return rowsUpdated > 0;
	        }
	    }

	    public boolean themnhanvien(NhanVien nv, String condition, String oldMaNV) throws SQLException {
	        String sql = "";
	        if (condition.equals("themnhanvien")) {
	            sql = "INSERT INTO NHANVIEN (HoTen, NgaySinh, GioiTinh, DiaChi, CMND, DienThoai, NgayVaoLam, TaiKhoan, MatKhau, isDeleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        }
	        if (condition.equals("suanhanvien")) {
	            sql = "UPDATE NHANVIEN SET HoTen = ?, NgaySinh = ?, GioiTinh = ?, DiaChi = ?, CMND = ?, DienThoai = ?, NgayVaoLam = ?, TaiKhoan = ?, MatKhau = ?, isDeleted = ? WHERE MaNV = ?";

	        }
	        PreparedStatement pstm = conn.prepareStatement(sql);

	        try {
	            
	            pstm.setString(1, nv.getTenNv());
	            pstm.setString(2, nv.getNgaySinh());
	            pstm.setInt(3, nv.getGioiTinh());
	            pstm.setString(4, nv.getDiaChi());
	            pstm.setString(5, nv.getCmnd());
	            pstm.setString(6, nv.getSdt());
	            pstm.setString(7, nv.getNgayVaoLam());
	            pstm.setString(8, nv.getTaiKhoan());
	            pstm.setString(9, nv.getMatKhau());
	            pstm.setInt(10, 1);

	            if (condition.equals("suanhanvien")) {
	                pstm.setInt(11, Integer.parseInt(oldMaNV));
	            }
	            pstm.executeUpdate();
	            System.out.println(sql);
	            return true;
	        } catch (Exception e) {
	            // TODO: handle exception
	            return false;
	        }
	    }
	

	
	public static void main(String[] args) throws SQLException {
		NhanVienDAL nvd = new NhanVienDAL();
		System.out.println(nvd.kiemTraDangNhap());
	}
}
