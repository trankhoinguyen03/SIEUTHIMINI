package DAL;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.NhapHang;
import DTO.NhanVien;


public class NhapHangDAL extends connectSql {

    public NhapHangDAL() throws SQLException {
        super();
        // TODO Auto-generated constructor stub
    }

    public ArrayList<NhapHang> docNhapHang() {
        String sql = "";
        ArrayList<NhapHang> arrList = new ArrayList<NhapHang>();
        try {
            sql = "select * from DSPHIEUNHAP where TrangThai = 1";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                NhapHang nh = new NhapHang();
                nh.setMaPn(rs.getString("MaPN"));
                nh.setMaNv(rs.getString("MaNV"));
                nh.setMaNcc(rs.getString("MaNCC"));
                nh.setTongTien(rs.getString("TongTien"));
                nh.setNgayNhap(rs.getString("NgayNhap"));
                arrList.add(nh);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        closeConnection();
        return arrList;
    }
    public ArrayList<NhapHang> docNhapHangNV(String value) {
        String sql = "";
        ArrayList<NhapHang> arrList = new ArrayList<NhapHang>();
        try {
            sql = "select * from DSPHIEUNHAP where TrangThai = 1 and MaNV = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, value);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                NhapHang nh = new NhapHang();
                nh.setMaPn(rs.getString("MaPN"));
                nh.setMaNv(rs.getString("MaNV"));
                nh.setMaNcc(rs.getString("MaNCC"));
                nh.setTongTien(rs.getString("TongTien"));
                nh.setNgayNhap(rs.getString("NgayNhap"));
                arrList.add(nh);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        closeConnection();
        return arrList;
    }
    
    public ArrayList<DTO.SanPham> docSanPhamMaSP(int maSp) throws SQLException{
		ArrayList<DTO.SanPham> arrSanpham = new ArrayList<DTO.SanPham>();
		String sql = "select * from SANPHAM where MaSP =" + maSp;
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			DTO.SanPham sp = new DTO.SanPham();
			sp.setMaSp(rs.getString("MaSP"));
			sp.setTenSp(rs.getString(2));
			arrSanpham.add(sp);
		}
	
	return arrSanpham;
	}

    public boolean anPhieuNhap(String value) throws SQLException {
        String sql = "UPDATE DSPHIEUNHAP SET TrangThai = 0 WHERE MaPN = ?";
        try ( PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, value);
            int rowsUpdated = pstm.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    public String layMaPNcuoi() throws SQLException {
        String sql = "SELECT MAX(MaPN) FROM DSPHIEUNHAP";
        try ( PreparedStatement pstm = conn.prepareStatement(sql)) {
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        }
        return null;
    }

    public boolean themPhieuNhap(NhapHang obj) throws SQLException {
        String sql = "";
        sql = "INSERT INTO DSPHIEUNHAP (MaNV, MaNCC, TongTien, NgayNhap, TrangThai, MaPN) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = conn.prepareStatement(sql);

        try {
            pstm.setString(1, obj.getMaNv());
            pstm.setString(2, obj.getMaNcc());
            pstm.setString(3, obj.getTongTien());
            pstm.setString(4, obj.getNgayNhap());
            pstm.setString(5, ""+1);
            pstm.setString(6, obj.getMaPn());
            pstm.executeUpdate();
            closeConnection();
            return true;
        } catch (Exception e) {
            // TODO: handle exception
        	closeConnection();
            return false;
        }
    }
    public String layTongTien(String value) throws SQLException {
        String sql = "SELECT TongTien FROM DSPHIEUNHAP WHERE MaPN = ?";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setString(1, value);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("TongTien");
            }
        }
        return null;
    }
    public boolean capNhatTongTien(String tong, String mapn) throws SQLException {
        String sql = "UPDATE DSPHIEUNHAP SET TongTien = ? WHERE MaPN = ?";
        try ( PreparedStatement pstm = conn.prepareStatement(sql)) {
        	pstm.setString(1, tong);
        	pstm.setString(2, mapn);
            int rowsUpdated = pstm.executeUpdate();
            return rowsUpdated > 0;
        }
    }
    
}
