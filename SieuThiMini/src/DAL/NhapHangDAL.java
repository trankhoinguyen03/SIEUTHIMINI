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
                sql = "select * from PHIEUNHAP where isDeleted = 1";
            }
            if (condition.equals("docchitiet")) {
                sql = "select * from CHITIETPHIEUNHAP where isDeleted = 1 and MaPN ="+value;
            }
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                NhapHang nh = new NhapHang();
                if (condition.equals("docphieunhap")) {
                    nh.setMaPn(rs.getInt("MaPN"));
                    nh.setMaNv(rs.getInt("MaNV"));
                    nh.setThoiDiemLap(rs.getString("ThoiDiemLap"));
                    nh.setIsDeleted(rs.getInt("isDeleted"));
                }
                if (condition.equals("docchitiet")) {
                    nh.setMaPn(rs.getInt("MaPN"));
                    nh.setMaSp(rs.getInt("MaSP"));
                    nh.setMaNcc(rs.getInt("MaNCC"));
                    nh.setSoLuong(rs.getInt("SoLuong"));
                    nh.setNgaySanXuat(rs.getString("NgaySX"));
                    nh.setNgayNhap(rs.getString("NgayNhap"));
                    nh.setIsDeleted(rs.getInt("isDeleted"));
                }
                arrList.add(nh);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return arrList;
    }

    public int layMaNv(String tenMaNv) throws SQLException {
        String sql = "SELECT MaNV FROM NHANVIEN WHERE HoTen = ?";
        try ( PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, tenMaNv);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getInt("MaNV");
            } else {
                return -1; // or throw an exception
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // or throw an exception
        }
    }
    
    public ArrayList<NhanVien> docNhanVienMaNV(int maNv) throws SQLException{
		ArrayList<NhanVien> arrNhanvien = new ArrayList<NhanVien>();
		String sql = "select * from NHANVIEN where MaNV =" + maNv;
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			NhanVien nv = new NhanVien();
			nv.setMaNv(rs.getInt("MaNV"));
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
			sp.setMaSp(rs.getInt("MaSP"));
			sp.setTenSp(rs.getString(2));
			arrSanpham.add(sp);
		}
	
	return arrSanpham;
	}
    
    public int layMaNcc(String tenMaNcc) throws SQLException {
        String sql = "SELECT MaNCC FROM NHACC WHERE TenNCC = ?";
        try ( PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, tenMaNcc);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getInt("MaNCC");
            } else {
                return -1; // or throw an exception
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // or throw an exception
        }
    }
    
    public ArrayList<DTO.NhaCungCap> docNhaCungCapMaNCC(int maNcc) throws SQLException{
		ArrayList<DTO.NhaCungCap> arrNhacungcap = new ArrayList<DTO.NhaCungCap>();
		String sql = "select * from NHACC where MaNCC =" + maNcc;
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			DTO.NhaCungCap ncc = new DTO.NhaCungCap();
			ncc.setMaNCC(rs.getInt("MaNCC"));
			ncc.setTenNCC(rs.getString(2));
			arrNhacungcap.add(ncc);
		}
	
	return arrNhacungcap;
	}

    public boolean xoaNhapHang(String mapn) throws SQLException {
        String sql = "UPDATE PHIEUNHAP SET isDeleted = " + 0 + " where MaPN = " + mapn;
        try ( PreparedStatement pstm = conn.prepareStatement(sql)) {
            int rowsUpdated = pstm.executeUpdate();

            return rowsUpdated > 0;
        }
    }

    public boolean xoaChiTiet(String mapn, String masp, String mancc) throws SQLException {
        String sql = "UPDATE CHITIETPHIEUNHAP SET isDeleted = " + 0 + " where MaPN = " + mapn +"and MaSP =" + masp + "and MaNCC ="+mancc;
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
            sql = "INSERT INTO PHIEUNHAP (MaNV, ThoiDiemLap, isDeleted) VALUES (?, ?, ?)";
        }
        if (condition.equals("suaphieunhap")) {
            sql = "UPDATE PHIEUNHAP SET MaNV = ?, ThoiDiemLap = ?, isDeleted = ? WHERE MaPN = ?";
        }
        if (condition.equals("themchitiet")) {
            sql = "INSERT INTO CHITIETPHIEUNHAP (MaPN, MaSP, MaNCC, SoLuong, NgaySX, NgayNhap, isDeleted) VALUES (?, ?, ?, ?, ?, ?, ?)";
        }
        if (condition.equals("suachitiet")) {
            sql = "UPDATE CHITIETPHIEUNHAP SET SoLuong = ?, NgaySX = ?, NgayNhap = ?, isDeleted = ? WHERE MaPN = ? and MaSP = ? and MaNCC = ?";
        }
        PreparedStatement pstm = conn.prepareStatement(sql);

        try {
            if (condition.equals("themphieunhap")) {
                pstm.setInt(1, nh.getMaNv());
                pstm.setString(2, nh.getThoiDiemLap());
                pstm.setInt(3, 1);
            }
            if (condition.equals("suaphieunhap")) {
                pstm.setInt(1, nh.getMaNv());
                pstm.setString(2, nh.getThoiDiemLap());
                pstm.setInt(3, 1);
                pstm.setInt(4, nh.getMaPn());
            }
            if (condition.equals("themchitiet")) {
                pstm.setInt(1, nh.getMaPn());
                pstm.setInt(2, nh.getMaSp());
                pstm.setInt(3, nh.getMaNcc());
                pstm.setInt(4, nh.getSoLuong());
                pstm.setString(5, nh.getNgaySanXuat());
                pstm.setString(6, nh.getNgayNhap());
                pstm.setInt(7, 1);
            }
            if (condition.equals("suachitiet")) {
                pstm.setInt(1, nh.getSoLuong());
                pstm.setString(2, nh.getNgaySanXuat());
                pstm.setString(3, nh.getNgayNhap());
                pstm.setInt(4, 1);
                pstm.setInt(5, nh.getMaPn());
                pstm.setInt(6, nh.getMaSp());
                pstm.setInt(7, nh.getMaNcc());
            }
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }
}
