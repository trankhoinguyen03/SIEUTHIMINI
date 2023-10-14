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
			if (condition.equals("sapxeptheoten")) {
				sql = "select * from SANPHAM where isDeleted = 1 order by TenSP";
			}
			if (condition.equals("docsanpham")) {
				sql = "select * from SANPHAM where isDeleted = 1";
			}
			if (condition.equals("sapxeptheogia")) {
				sql = "select * from SANPHAM where isDeleted = 1 order by GiaBan";
			}
			if (condition.equals("timkiem")) {
				String[] parts = value.split(",");
				 part1 = parts[0];
				 part2 = parts[1]; 
				
				 if(part2.equals("0")) {
				
					 sql = "select * from SANPHAM where isDeleted = 1 and TenSP LIKE ?";
				 }
				 else {
					 sql = "select * from SANPHAM where isDeleted = 1 and TenSP LIKE ? and MaLH = ?";
				 }

			}
			if(condition.equals("timkiemgia")) {
				String[] parts = value.split(",");
				 part1 = parts[0];
				 part2 = parts[1]; 
				 partPriceFrom = parts[2];
				 partPriceTo=parts[3];
				 if(part2.equals("0")) {
						
					 sql = "select * from SANPHAM where isDeleted = 1 and TenSP LIKE ? and GiaBan BETWEEN  ? AND  ? ";
				 }
				 else {
					 sql = "select * from SANPHAM where isDeleted = 1 and TenSP LIKE ? and MaLH = ? and GiaBan BETWEEN  ? AND  ?";
				 }
				 
			}
			if (condition.equals("docsanphamtheoid")) {
				sql = "select * from SANPHAM where isDeleted = 1 and MaLH =" + value;
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
			if(condition.equals("timkiemgia")) {
				if(part2.equals("0")) {
					pstm.setString(1, "%" + part1 + "%");
					pstm.setInt(2, Integer.parseInt(partPriceFrom));
					pstm.setInt(3, Integer.parseInt(partPriceTo));
				}
				if(part2.equals("0")==false) {
					pstm.setString(1, "%" + part1 + "%");
					pstm.setString(2, part2);
					pstm.setInt(3, Integer.parseInt(partPriceFrom));
					pstm.setInt(4, Integer.parseInt(partPriceTo));
				}
			}
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				SanPham sp = new SanPham();
				sp.setMaSp(rs.getInt("MaSP"));
				sp.setTenSp(rs.getString("TenSP"));
				sp.setGiaMua(rs.getInt("GiaMua"));
				sp.setGiaBan(rs.getInt("GiaBan"));
				sp.setHanSuDung(rs.getString("HSD"));
				sp.setMaLh(rs.getInt("MaLH"));
				sp.setDonVi(rs.getString("DonVi"));
				sp.setImg(rs.getString("img"));
				sp.setIsDeleted(rs.getInt("isDeleted"));
				arrList.add(sp);
			}
		} catch (Exception e) {

		}
		return arrList;
	}

	public int layMaLoaiSP(String tenMaLoai) throws SQLException {
		String sql = "SELECT MaLH FROM LOAIHANG WHERE TenLH = ?";
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setString(1, tenMaLoai);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				return rs.getInt("MaLH");
			} else {
				return -1; // or throw an exception
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1; // or throw an exception
		}
	}

	public boolean xoaSanPham(String masp) throws SQLException {
		String sql = "UPDATE SANPHAM SET isDeleted = " + 0 + " where MaSP = " + masp;
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			int rowsUpdated = pstm.executeUpdate();

			return rowsUpdated > 0;
		}
	}

	public boolean themsanpham(SanPham sp, String condition, String oldMaSP) throws SQLException {
		String sql = "";
		if (condition.equals("themsanpham")) {
			sql = "INSERT INTO SANPHAM (TenSP, GiaMua, GiaBan, HSD, MaLH, DonVi, img,isDeleted,MaSP) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";
		}
		if (condition.equals("suasanpham")) {
			sql = "UPDATE SANPHAM SET TenSP = ?, GiaMua = ?, GiaBan = ?, HSD = ?, MaLH = ?, DonVi = ?, img = ?, isDeleted = ?,MaSP = ? WHERE MaSP = ?";

		}
		PreparedStatement pstm = conn.prepareStatement(sql);

		try {

			pstm.setString(1, sp.getTenSp());
			pstm.setFloat(2, sp.getGiaMua());
			pstm.setFloat(3, sp.getGiaBan());
			pstm.setString(4, sp.getHanSuDung());
			pstm.setInt(5, sp.getMaLh());
			pstm.setString(6, sp.getDonVi());
			pstm.setString(7, ".//Image//" + sp.getImg());
			pstm.setInt(8, 1);
			pstm.setInt(9, sp.getMaSp());

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
