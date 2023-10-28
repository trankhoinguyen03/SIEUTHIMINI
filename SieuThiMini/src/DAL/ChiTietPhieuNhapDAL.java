package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.NhapHang;
import DTO.PhieuNhapChiTiet;

public class ChiTietPhieuNhapDAL extends connectSql {
	
	
	public ChiTietPhieuNhapDAL() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public ArrayList<PhieuNhapChiTiet> docCtPhieuNhap(String value) throws SQLException{
		ArrayList<PhieuNhapChiTiet> arrPn = new ArrayList<PhieuNhapChiTiet>();
		String sql = "select * from CHITIETPHIEUNHAP where TrangThai = 1 and MaPN LIKE ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, value);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			PhieuNhapChiTiet ctpn = new PhieuNhapChiTiet();
			ctpn.setMaPN(rs.getString(1)); 
			ctpn.setMaSP(rs.getString(2));
			ctpn.setSoLuong(rs.getString(3));
			ctpn.setThanhTien(rs.getString(4));
			arrPn.add(ctpn);			
		}
		return arrPn;
	}
	
	public boolean themphieunhap(PhieuNhapChiTiet pnct,String condition) throws SQLException {
		String sql = "";
		if(condition.equals("themphieunhap")) {
			sql =  "INSERT INTO CHITIETPHIEUNHAP( MaPN, MaSP, SoLuong, ThanhTien, TrangThai) VALUES (?, ?, ?, ?, ?)";
		}
	
		PreparedStatement pstm = conn.prepareStatement(sql);
			
		try {

            pstm.setString(1,pnct.getMaPN());
			pstm.setString(2,pnct.getMaSP());
			pstm.setString(3, pnct.getSoLuong());
			pstm.setString(4, pnct.getThanhTien());
			pstm.setString(5, ""+1);
//			if(condition.equals("suahoadon")) {
//				System.out.println(hd.getMaHd());
//				System.out.println(MaHDcu);
//				pstm.setInt(10, Integer.parseInt(MaHDcu));
//			}
			
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return true;
		}
} 
       public boolean xoaSanPham(String masp, String mapn) throws SQLException {
    String sql = "UPDATE CHITIETPHIEUNHAP SET TrangThai = 0  WHERE MaSP = ? AND MaPN = ?";
    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
        pstm.setString(1, masp);
        pstm.setString(2, mapn);
        int rowsUpdated = pstm.executeUpdate();
    }
    return true;
}

         public boolean xoaPhieuNhap(String maPhieuNhap) throws SQLException {
    String sql = "UPDATE CHITIETPHIEUNHAP SET TrangThai = 0 WHERE MaHD = ?";
    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
        pstm.setString(1, maPhieuNhap);
        int rowsUpdated = pstm.executeUpdate();
    }
            return true;
}
}
