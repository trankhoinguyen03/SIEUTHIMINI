package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.ChucVu;
import DTO.NhanVien;
import DTO.TaiKhoan;
public class NhanVienDAL extends connectSql {

	public NhanVienDAL() throws SQLException {
		super();	
	}


	

	    public ArrayList<NhanVien> docNhanVien(String condition) {
	        String sql = "";
	        ArrayList<NhanVien> arrList = new ArrayList<NhanVien>();
	        try {
	            if (condition.equals("sapxeptheoten")) {
	                sql = "select * from NHANVIEN order by TenNV";
	            }
	            if (condition.equals("docnhanvien")) {
	                sql = "select * from NHANVIEN where TrangThai=1";
	            }
	            if (condition.equals("sapxeptheoma")) {
	                sql = "select * from NHANVIEN order by MaNV";
	            }
	            PreparedStatement pstm = conn.prepareStatement(sql);
	            ResultSet rs = pstm.executeQuery();
	            while (rs.next()) {
	                NhanVien nv = new NhanVien();
	                nv.setMaNv(rs.getString("MaNV"));
	                nv.setTenNv(rs.getString("TenNV"));
	                nv.setNgaySinh(rs.getString("NgaySinh"));
	                nv.setSdt(rs.getString("SDT"));
	                nv.setDiaChi(rs.getString("DiaChi"));
	                nv.setGioiTinh(rs.getString("GioiTinh"));
	                nv.setCccd(rs.getString("CCCD"));
	                nv.setNgayVaoLam(rs.getString("NgayVaoLam"));
	                nv.setChucVu(rs.getString("MaCV"));
	                nv.setTinhTrang(rs.getString("TrangThai"));
	                arrList.add(nv);
	            }
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return arrList;
	    }
	    
	    public String docTenNV(String value) throws SQLException{
			String sql = "select TenNV from NHANVIEN where MaNV LIKE ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, value);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				return rs.getString("TenNV");
			}
			return null;
		}
	    
	    public String docMaNV(String value) throws SQLException{
			String sql = "select MaNV from NHANVIEN where TenNV LIKE ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, value);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				return rs.getString("MaNV");
			}
		
			return null;
		}
	    
	    public String layMaNVcuoi() throws SQLException {
	        String sql = "SELECT MAX(MaNV) FROM NHANVIEN";
	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                return rs.getString(1);
	            }
	        }
	        return null;
	    }
	    
	    public ArrayList<ChucVu> docChucVu() {
	        String sql = "";
	        ArrayList<ChucVu> arrList = new ArrayList<ChucVu>();
	        try {
                sql = "select * from CHUCVU";
	            PreparedStatement pstm = conn.prepareStatement(sql);
	            ResultSet rs = pstm.executeQuery();
	            while (rs.next()) {
	                ChucVu cv = new ChucVu();
	                cv.setMaCV(rs.getString("MaCV"));
	                cv.setTenCV(rs.getString("TenCV"));
	                arrList.add(cv);
	            }
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return arrList;
	    }
	    
	    public String docChucVuNV(String name) throws SQLException {
	        String sql = "SELECT MaCV FROM NHANVIEN WHERE MaNV = ?";
	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        	pstmt.setString(1, name);
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                return rs.getString(1);
	            }
	        }
	        return null;
	    }
	    
	    public String layMaCV(String name) throws SQLException {
	        String sql = "SELECT MaCV FROM CHUCVU WHERE TenCV LIKE ?";
	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        	pstmt.setString(1, name);
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                return rs.getString(1);
	            }
	        }
	        return null;
	    }
	    
	    public String layTenCV(String id) throws SQLException {
	        String sql = "SELECT TenCV FROM CHUCVU WHERE MaCV = ?";
	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        	pstmt.setString(1, id);
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                return rs.getString(1);
	            }
	        }
	        return null;
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
	    
	    

	    public boolean anNhanVien(String id) throws SQLException {
	        String sql = "UPDATE NHANVIEN SET TrangThai = 0 WHERE MaNV = ?";
	        try ( PreparedStatement pstm = conn.prepareStatement(sql)) {
	        	pstm.setString(1, id);
	            int rowsUpdated = pstm.executeUpdate();
	            return rowsUpdated > 0;
	        }
	    }
	    
	    public boolean suaNhanVien(NhanVien nv) throws SQLException {
	        String sql = "UPDATE NHANVIEN SET TenNV = ?, NgaySinh = ?, SDT = ?, DiaChi = ?, GioiTinh = ?, CCCD = ?, NgayVaoLam = ?, MaCV = ? WHERE MaNV = ?";
	        try ( PreparedStatement pstm = conn.prepareStatement(sql)) {
	        	pstm.setString(1, nv.getTenNv());
	            pstm.setString(2, nv.getNgaySinh());
	            pstm.setString(3, nv.getSdt());
	            pstm.setString(4, nv.getDiaChi());
	            pstm.setString(5, nv.getGioiTinh());
	            pstm.setString(6, nv.getCccd());
	            pstm.setString(7, nv.getNgayVaoLam());
	            pstm.setString(8, nv.getChucVu());
	            pstm.setString(9, nv.getMaNv());
	            int rowsUpdated = pstm.executeUpdate();
	            return rowsUpdated > 0;
	        }
	    }


	    public boolean themNhanVien(NhanVien nv) throws SQLException {
	        String sql = "INSERT INTO NHANVIEN (TenNV, NgaySinh, SDT, DiaChi, GioiTinh, CCCD, NgayVaoLam, MaCV, TrangThai, MaNV) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        try {           
	            pstm.setString(1, nv.getTenNv());
	            pstm.setString(2, nv.getNgaySinh());
	            pstm.setString(3, nv.getSdt());
	            pstm.setString(4, nv.getDiaChi());
	            pstm.setString(5, nv.getGioiTinh());
	            pstm.setString(6, nv.getCccd());
	            pstm.setString(7, nv.getNgayVaoLam());
	            pstm.setString(8, nv.getChucVu());
	            pstm.setString(9, ""+1);
	            pstm.setString(10, nv.getMaNv());
	            pstm.executeUpdate();
	            closeConnection();
	            return true;
	        } catch (Exception e) {
	            // TODO: handle exception
	        	closeConnection();
	            return false;
	        }
	    }
	


}
