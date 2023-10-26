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
		String partPriceFrom = "";
		String partPriceTo="";
		ArrayList<SanPham> arrList = new ArrayList<SanPham>();
		try {
			if (condition.equals("docsanpham")) {
				sql = "select * from SANPHAM where TrangThai = 1 order by MaSP";
			}
			if (condition.equals("timkiem")) {
				String[] parts = value.split(",");
				 part1 = parts[0];
				 part2 = parts[1]; 
				
				 if(part2.equals("0")) {
				
					 sql = "select * from SANPHAM where TrangThai = 1 and TenSP LIKE ?";
				 }
				 else {
					 sql = "select * from SANPHAM where TrangThai = 1 and TenSP LIKE ? and MaLH = ?";
				 }

			}
			if(condition.equals("timkiemtheoloaihang")) {
				if(value.equals("Tất cả")) {
					sql = "select * from SANPHAM where TrangThai = 1 order by MaSP";
				}
				else sql = "select * from SANPHAM where TrangThai = 1 and MaLH LIKE ?";
			}
			if (condition.equals("docsanphamtheoid")) {
				sql = "select * from SANPHAM where TrangThai = 1 and MaLH =" + value;
			}
			PreparedStatement pstm = conn.prepareStatement(sql);
			if (condition.equals("timkiem")) {
				if(part2.equals("0")) {
					pstm.setString(1, "%" + part1 + "%");
				}
				if(part2.equals("0")==false) {
					pstm.setString(1, "%" + part1 + "%");
					pstm.setString(2, part2);
				}
				
			}
			if(condition.equals("timkiemtheoloaihang")) {
				if(!value.equals("Tất cả")) {
					LoaiHangDAL lh = new LoaiHangDAL();
					pstm.setString(1, lh.getMaLh(value));
				}
			}
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				SanPham sp = new SanPham();
				sp.setMaSp(rs.getString("MaSP"));
				sp.setTenSp(rs.getString("TenSP"));
				sp.setGiaMua(rs.getString("GiaMua"));
				sp.setGiaBan(rs.getString("GiaBan"));
				sp.setHanSuDung(rs.getString("HSD"));
				sp.setMaLh(rs.getString("MaLH"));
				arrList.add(sp);
			}
		} catch (Exception e) {

		}
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

	public boolean anSanPham(String masp) throws SQLException {
		String sql = "UPDATE SANPHAM SET TrangThai = " + 0 + " where MaSP = " + masp;
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			int rowsUpdated = pstm.executeUpdate();

			return rowsUpdated > 0;
		}
	}

	public boolean themsanpham(SanPham sp, String condition, String oldMaSP) throws SQLException {
		String sql = "";
		if (condition.equals("themsanpham")) {
			sql = "INSERT INTO SANPHAM (TenSP, GiaMua, GiaBan, NSX, HSD, MaLH, TrangThai, MaSP) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		}
		if (condition.equals("suasanpham")) {
			sql = "UPDATE SANPHAM SET TenSP = ?, GiaMua = ?, GiaBan = ?, NSX = ?, HSD = ?, MaLH = ?, TrangThai = ? WHERE MaSP = ?";

		}
		PreparedStatement pstm = conn.prepareStatement(sql);

		try {

			pstm.setString(1, sp.getTenSp());
			pstm.setString(2, sp.getGiaMua());
			pstm.setString(3, sp.getGiaBan());
			pstm.setString(4, sp.getHanSuDung());
			pstm.setString(5, sp.getMaLh());
			pstm.setString(7, ""+1);
			pstm.setString(8, sp.getMaSp());

			if (condition.equals("suasanpham")) {
				pstm.setInt(10, Integer.parseInt(oldMaSP));
			}

			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public String getNameSanPham(int maSP) throws SQLException {
		String sql = "select TenSP from SANPHAM where MaSP = "+ maSP;
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
		    String tenSP = rs.getString("TenSP");
		    return tenSP;
		}
		return "";
	
	}
	public  float getgia(String tensp) throws SQLException {
        float giatien = 0;
        
        String sql = "SELECT GiaBan FROM SANPHAM where TenSP=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) { 
            pstmt.setString(1,tensp);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                giatien = rs.getFloat("GiaBan");
            }
        }
        return giatien;
    }
public int getma(String tensp) throws SQLException {
        String sql = "SELECT MaSP FROM SANPHAM where TenSP=?";
try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    pstmt.setString(1,tensp);
    pstmt.setFetchSize(100); // chỉ định số lượng bản ghi được trả về trong mỗi lần truy xuất
    ResultSet rs = pstmt.executeQuery();
    if (rs.next()) {
        return rs.getInt("MaSP");
    } else {
        return -1;
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
