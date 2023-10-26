package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.NhanVien;
public class NhanVienDAL extends connectSql {

	public NhanVienDAL() throws SQLException {
		super();	
	}


	

	    public ArrayList<NhanVien> docNhanVien(String condition, String value) {
	        String sql = "";
	        ArrayList<NhanVien> arrList = new ArrayList<NhanVien>();
	        try {
	            if (condition.equals("sapxeptheoten")) {
	                sql = "select * from NHANVIEN where TrangThai = 1 order by TenNV";
	            }
	            if (condition.equals("docnhanvien")) {
	                sql = "select * from NHANVIEN where TrangThai = 1";
	            }
	            if (condition.equals("sapxeptheoma")) {
	                sql = "select * from NHANVIEN where TrangThai = 1 order by MaNV";
	            }
	            if (condition.equals("timkiem")) {
	                sql = "select * from NHANVIEN where TrangThai = 1 and MaNV LIKE %" + value + "% ";
	            }
	            PreparedStatement pstm = conn.prepareStatement(sql);
	            ResultSet rs = pstm.executeQuery();
	            while (rs.next()) {
	                NhanVien nv = new NhanVien();
	                nv.setMaNv(rs.getString("MaNV"));
	                nv.setTenNv(rs.getString("TenNV"));
	                nv.setNgaySinh(rs.getString("NgaySinh"));
	                nv.setSdt(sql);
	                nv.setDiaChi(rs.getString("DiaChi"));
	                nv.setGioiTinh(rs.getString("GioiTinh"));
	                nv.setCccd(rs.getString("CCCD"));
	                nv.setNgayVaoLam(rs.getString("NgayVaoLam"));
	                nv.setChucVu(rs.getString("MaCV"));
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
	        String sql = "UPDATE NHANVIEN SET TrangThai = " + 0 + " where MaNV = " + manv;
	        try ( PreparedStatement pstm = conn.prepareStatement(sql)) {
	            int rowsUpdated = pstm.executeUpdate();

	            return rowsUpdated > 0;
	        }
	    }

	    public boolean themnhanvien(NhanVien nv, String condition, String oldMaNV) throws SQLException {
	        String sql = "";
	        if (condition.equals("themnhanvien")) {
	            sql = "INSERT INTO NHANVIEN (TenNV, NgaySinh, SDT, DiaChi, GioiTinh, CCCD, NgayVaoLam, MaCV, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        }
	        PreparedStatement pstm = conn.prepareStatement(sql);

	        try {
	            
	            pstm.setString(1, nv.getTenNv());
	            pstm.setString(2, nv.getNgaySinh());
	            pstm.setString(3, nv.getGioiTinh());
	            pstm.setString(4, nv.getDiaChi());
	            pstm.setString(5, nv.getCccd());
	            pstm.setString(6, nv.getSdt());
	            pstm.setString(7, nv.getNgayVaoLam());
	            pstm.setString(8, nv.getChucVu());
	            pstm.setString(9, ""+1);

	            pstm.executeUpdate();
	            System.out.println(sql);
	            return true;
	        } catch (Exception e) {
	            // TODO: handle exception
	            return false;
	        }
	    }
	


}
