package DAL;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.SanPham;

public class SanPhamDAL extends connectSql {
	public SanPhamDAL() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getLastMaSP() throws SQLException {
		String sql = "select top 1 MaSP from SANPHAM order by MaSP DESC";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		  if (rs.next()) {
		        int maxColumnValue = rs.getInt("MaSP");
		        return maxColumnValue;
		    }
		  closeConnection();
		  return 0;
	}
	
	public ArrayList<SanPham> docSanPham(String condition, String value) {
		String sql = "";
		String part2="";
		String part1 ="";
		ArrayList<SanPham> arrList = new ArrayList<SanPham>();
		try {
			if (condition.equals("docsanpham")) {
				sql = "SELECT * FROM SANPHAM INNER JOIN KHO ON SANPHAM.MaSP = KHO.MaSP where HSD > GETDATE() and SoLuong <> 0 and SANPHAM.TrangThai = 1 order by SANPHAM.MaSP";
			}
			if(condition.equals("timkiemtheoloaihang")) {
				if(value.equals("Tất cả")) {
					sql = "SELECT * FROM SANPHAM INNER JOIN KHO ON SANPHAM.MaSP = KHO.MaSP where HSD > GETDATE() and SoLuong <> 0 and SANPHAM.TrangThai = 1 order by SANPHAM.MaSP";
				}
				else sql = "SELECT * FROM SANPHAM INNER JOIN KHO ON SANPHAM.MaSP = KHO.MaSP where HSD > GETDATE() and SoLuong <> 0 and SANPHAM.TrangThai = 1 and SANPHAM.MaLH LIKE ?";
			}
			if (condition.equals("docsanphamtheoid")) {
				sql = "select * from SANPHAM where TrangThai = 1 and MaLH =" + value;
			}
			PreparedStatement pstm = conn.prepareStatement(sql);
			if (condition.equals("timkiem")) {
				pstm.setString(1, value);
			}
			if(condition.equals("timkiemtheoloaihang")) {
				if(!value.equals("Tất cả")) {
					LoaiHangDAL lh = new LoaiHangDAL();
					pstm.setString(1, lh.layMaLh(value));
				}
			}
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				SanPham sp = new SanPham();
				sp.setMaSp(rs.getString("MaSP"));
				sp.setTenSp(rs.getString("TenSP"));
				sp.setGiaMua(rs.getString("GiaMua"));
				sp.setGiaBan(rs.getString("GiaBan"));
				sp.setNgaySanXuat(rs.getString("NSX"));
				sp.setHanSuDung(rs.getString("HSD"));
				sp.setMaLh(rs.getString("MaLH"));
				sp.setTinhTrang(rs.getString("TrangThai"));
				arrList.add(sp);
			}
		} catch (Exception e) {

		}
		closeConnection();
		return arrList;
	}
	public String layMaLoaiSP(String tenMaLoai) throws SQLException {
		String sql = "SELECT MaLH FROM LOAIHANG WHERE TenLH = ?";
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setString(1, tenMaLoai);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				return rs.getString("MaLH");
			} else {
				return null; // or throw an exception
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null; // or throw an exception
		}
	}
	
	public boolean suaSanPham(String id, String name ) throws SQLException {
		String sql = "UPDATE SANPHAM SET TenSP = ? where MaSP = ?";
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setString(1, name);
			pstm.setString(2, id);
			int rowsUpdated = pstm.executeUpdate();
			return rowsUpdated > 0;
		}
	}
	
	public boolean anSanPham(String masp) throws SQLException {
		String sql = "UPDATE SANPHAM SET TrangThai = 0 where MaSP = ?";
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setString(1, masp);
			int rowsUpdated = pstm.executeUpdate();

			return rowsUpdated > 0;
		}
	}

	public boolean themSanPham(SanPham sp) throws SQLException {
		String sql = "";
		sql = "INSERT INTO SANPHAM (TenSP, GiaMua, GiaBan, NSX, HSD, MaLH, TrangThai, MaSP) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstm = conn.prepareStatement(sql);

		try {
			pstm.setString(1, sp.getTenSp());
			pstm.setString(2, sp.getGiaMua());
			pstm.setString(3, sp.getGiaBan());
			pstm.setString(4, sp.getNgaySanXuat());
			pstm.setString(5, sp.getHanSuDung());
			pstm.setString(6, sp.getMaLh());
			pstm.setString(7, ""+1);
			pstm.setString(8, sp.getMaSp());
			pstm.executeUpdate();
			closeConnection();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			closeConnection();
			return false;
		}
	}

	public String layTenSanPham(String maSP) throws SQLException {
		String sql = "select TenSP from SANPHAM where MaSP like ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maSP);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
		    String tenSP = rs.getString("TenSP");
		    return tenSP;
		}
		return null;
	
	}
    public String layMaSp(String tenSp) throws SQLException {
        String sql = "SELECT MaSP FROM SANPHAM where TenSP = ? and HSD > GETDATE()";
        try ( PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, tenSp);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getString("MaSP");
            } else {
                return null; // or throw an exception
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // or throw an exception
        }
    }
    public String layMaLh(String value) throws SQLException {
        String sql = "SELECT MaLH FROM SANPHAM WHERE MaSP = ?";
        try ( PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, value);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getString("MaLH");
            } else {
                return null; // or throw an exception
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // or throw an exception
        }
    }
	public int getgia(String tensp) throws SQLException {
        int giatien = 0;
        
        String sql = "SELECT GiaBan FROM SANPHAM where TenSP=? and HSD > GETDATE()";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) { 
            pstmt.setString(1,tensp);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                giatien = rs.getInt("GiaBan");
            }
        }
        return giatien;
    }
	public String layGiaNhap(String value) throws SQLException {
        String giaNhap = null;
        
        String sql = "SELECT GiaMua FROM SANPHAM where  MaSP = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) { 
            pstmt.setString(1, value);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                giaNhap = rs.getString("GiaMua");
            }
        }
        return giaNhap;
    }
    public String layMaSPcuoi() throws SQLException {
        String sql = "SELECT MAX(MaSP) FROM SANPHAM";
        try ( PreparedStatement pstm = conn.prepareStatement(sql)) {
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        }
        return null;
    }
	public String getma(String tensp) throws SQLException {
        String sql = "SELECT MaSP FROM SANPHAM where TenSP = ? and HSD > GETDATE()";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		    pstmt.setString(1,tensp);
		    pstmt.setFetchSize(100); // chỉ định số lượng bản ghi được trả về trong mỗi lần truy xuất
		    ResultSet rs = pstmt.executeQuery();
		    if (rs.next()) {
		        return rs.getString("MaSP");
		    } else {
		        return null;
		    }
		}
	}
	public String[] getTenSP() throws SQLException {
	    String sql = "SELECT TenSP FROM SANPHAM";
	    ArrayList<String> tenSPList = new ArrayList<>();
	
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        ResultSet rs = pstmt.executeQuery();
	
	        while (rs.next()) {
	            String tenSP = rs.getString("TenSP");
	            tenSPList.add(tenSP);
	        }
	    }
	
	    String[] tenSPArray = tenSPList.toArray(new String[tenSPList.size()]);
	    return tenSPArray;
	}


	public static void main(String[] args) throws SQLException {
		SanPhamDAL sp = new SanPhamDAL();
		SanPham spthem = new SanPham();
		ArrayList<SanPham> arr = new ArrayList<SanPham>();
		arr = sp.docSanPham("timkiem", "a,0");

	}

}
