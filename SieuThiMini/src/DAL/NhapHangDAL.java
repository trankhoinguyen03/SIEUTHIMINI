package DAL;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BLL.NhapHang;
import BLL.NhanVien;


public class NhapHangDAL extends connectSql {

    public NhapHangDAL() throws SQLException {
        super();
        // TODO Auto-generated constructor stub
    }

    public ArrayList<NhapHang> docNhapHang(String condition, String value) {
        String sql = "";
        ArrayList<NhapHang> arrList = new ArrayList<NhapHang>();
        try {
            if (condition.equals("docphieunhap")) {
                sql = "select * from DSPHIEUNHAP where TrangThai = 1";
            }
            if (condition.equals("docchitiet")) {
                sql = "select * from CHITIETPHIEUNHAP where TrangThai = 1 and MaPN ="+value;
            }
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                NhapHang nh = new NhapHang();
                if (condition.equals("docphieunhap")) {
                    nh.setMaPn(rs.getString("MaPN"));
                    nh.setMaNv(rs.getString("MaNV"));
                    nh.setMaNcc(rs.getString("MaNCC"));
                    nh.setTongTien(rs.getString("TongTien"));
                    nh.setNgayNhap(rs.getString("NgayNhap"));
                }
                if (condition.equals("docchitiet")) {
                    nh.setMaPn(rs.getString("MaPN"));
                    nh.setMaSp(rs.getString("MaSP"));
                    nh.setSoLuong(rs.getString("SoLuong"));
                    nh.setThanhTien(rs.getString("ThanhTien"));
                }
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
    
    public ArrayList<NhanVien> docNhanVienMaNV(int maNv) throws SQLException{
		ArrayList<NhanVien> arrNhanvien = new ArrayList<NhanVien>();
		String sql = "select * from NHANVIEN where MaNV =" + maNv;
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			NhanVien nv = new NhanVien();
			nv.setMaNv(rs.getString("MaNV"));
			nv.setTenNv(rs.getString(2));
			arrNhanvien.add(nv);
		}
	
	return arrNhanvien;
	}
    
    public int layMaSp(String tenMaSp) throws SQLException {
        String sql = "SELECT MaSP FROM SANPHAM WHERE TenSP = ?";
        try ( PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, tenMaSp);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getInt("MaSP");
            } else {
                return -1; // or throw an exception
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // or throw an exception
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
    
    public ArrayList<DTO.NhaCungCap> docNhaCungCapMaNCC(int maNcc) throws SQLException{
		ArrayList<DTO.NhaCungCap> arrNhacungcap = new ArrayList<DTO.NhaCungCap>();
		String sql = "select * from NHACUNGCAP where MaNCC =" + maNcc;
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			DTO.NhaCungCap ncc = new DTO.NhaCungCap();
			ncc.setMaNCC(rs.getString("MaNCC"));
			ncc.setTenNCC(rs.getString(2));
			arrNhacungcap.add(ncc);
		}
	
	return arrNhacungcap;
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

    public boolean themphieunhap(NhapHang nh, String condition, String oldMaPN, String oldMaSP, String oldMaNCC) throws SQLException {
        String sql = "";
        if (condition.equals("themphieunhap")) {
            sql = "INSERT INTO DSPHIEUNHAP (MaNV, MaNCC, TongTien, NgayNhap, TrangThai) VALUES (?, ?, ?, ?, ?)";
        }
        if (condition.equals("themchitiet")) {
            sql = "INSERT INTO CHITIETPHIEUNHAP (MaPN, MaSP, SoLuong, ThanhTien, TrangThai) VALUES (?, ?, ?, ?, ?)";
        }
        PreparedStatement pstm = conn.prepareStatement(sql);

        try {
            if (condition.equals("themphieunhap")) {
                pstm.setString(1, nh.getMaNv());
                pstm.setString(2, nh.getMaNcc());
                pstm.setString(3, nh.getTongTien());
                pstm.setString(4, nh.getNgayNhap());
                pstm.setString(5, ""+1);
            }
            if (condition.equals("themchitiet")) {
                pstm.setString(1, nh.getMaPn());
                pstm.setString(2, nh.getMaSp());
                pstm.setString(3, nh.getSoLuong());
                pstm.setString(4, nh.getThanhTien());
                pstm.setString(5, ""+1);
            }
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }
}
