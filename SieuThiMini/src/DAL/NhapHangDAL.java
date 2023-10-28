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
        return arrList;
    }

    public String layTenNV(String MaNV) throws SQLException {
        String sql = "SELECT TenNV FROM NHANVIEN WHERE MaNV = ?";
        try ( PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, MaNV);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getString("TenNV");
            } else {
                return null; // or throw an exception
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // or throw an exception
        }
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
    public String layTenNCC(String MaNCC) throws SQLException {
        String sql = "SELECT TenNCC FROM NHACUNGCAP WHERE MaNCC = ?";
        try ( PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, MaNCC);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getString("NCC");
            } else {
                return null; // or throw an exception
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // or throw an exception
        }
    }   

    public boolean xoaNhapHang(String mapn) throws SQLException {
        String sql = "UPDATE DSPHIEUNHAP SET TrangThai = " + 0 + " where MaPN = " + mapn;
        try ( PreparedStatement pstm = conn.prepareStatement(sql)) {
            int rowsUpdated = pstm.executeUpdate();

            return rowsUpdated > 0;
        }
    }

    public boolean xoaChiTiet(String mapn, String masp, String mancc) throws SQLException {
        String sql = "UPDATE CHITIETPHIEUNHAP SET TrangThai = " + 0 + " where MaPN = " + mapn +"and MaSP =" + masp + "and MaNCC ="+mancc;
        try ( PreparedStatement pstm = conn.prepareStatement(sql)) {
            int rowsUpdated = pstm.executeUpdate();

            return rowsUpdated > 0;
        }
    }

    public int getLastMaPN() throws SQLException {
        int lastMaPN = 0;
        String sql = "SELECT MAX(MaPN) FROM PHIEUNHAP";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                lastMaPN = rs.getInt(1);
            }
        }
        return lastMaPN;
    }

    public boolean themphieunhap(NhapHang nh, String oldMaPN, String oldMaSP, String oldMaNCC) throws SQLException {
        String sql = "";
        sql = "INSERT INTO DSPHIEUNHAP (MaNV, MaNCC, TongTien, NgayNhap, TrangThai) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstm = conn.prepareStatement(sql);

        try {
            pstm.setString(1, nh.getMaNv());
            pstm.setString(2, nh.getMaNcc());
            pstm.setString(3, nh.getTongTien());
            pstm.setString(4, nh.getNgayNhap());
            pstm.setString(5, ""+1);
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }
}
