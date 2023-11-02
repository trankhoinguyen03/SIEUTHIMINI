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
		closeConnection();
		return arrPn;
	}
	
	public boolean themChiTietPN(PhieuNhapChiTiet obj) throws SQLException {
		String sql =  "INSERT INTO CHITIETPHIEUNHAP(MaPN, MaSP, SoLuong, ThanhTien, TrangThai) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		try {
            pstm.setString(1, obj.getMaPN());
			pstm.setString(2, obj.getMaSP());
			pstm.setString(3, obj.getSoLuong());
			pstm.setString(4, obj.getThanhTien());
			pstm.setString(5, ""+1);			
			pstm.executeUpdate();
			closeConnection();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			closeConnection();
			return false;
		}
} 
       public boolean anChiTietPN(String masp, String mapn) throws SQLException {
		    String sql = "UPDATE CHITIETPHIEUNHAP SET TrangThai = 0  WHERE MaSP = ? AND MaPN = ?";
		    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
		        pstm.setString(1, masp);
		        pstm.setString(2, mapn);
		        int rowsUpdated = pstm.executeUpdate();
		        return rowsUpdated > 0;
		    }
       }
}
