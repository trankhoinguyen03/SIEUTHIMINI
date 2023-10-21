package DAL;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.DoanhThu;
import DTO.PhieuNhapChiTiet;

public class ThongKeDAL extends connectSql {

	public ThongKeDAL() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArrayList<PhieuNhapChiTiet> readPurchaseOrder(String condition) throws SQLException{
		ArrayList<PhieuNhapChiTiet> arr = new ArrayList<PhieuNhapChiTiet>();
		String sql="";
		if(condition.equals("readall")) {
			sql = "Select * from CHITIETPHIEUNHAP WHERE TrangThai = 1";
		}

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			PhieuNhapChiTiet pn = new PhieuNhapChiTiet();
			pn.setMaPN(rs.getString("MaPN"));
			pn.setMaSP(rs.getString("MaSP"));
			pn.setSoLuong(rs.getString("SoLuong"));
			pn.setThanhTien(rs.getString("ThanhTien"));
			arr.add(pn);
			
		}
		return arr;
	}
	public ArrayList<PhieuNhapChiTiet> readPurchaseOrderMaLH(String condition) throws SQLException{
		ArrayList<PhieuNhapChiTiet> arr = new ArrayList<PhieuNhapChiTiet>();
		 String sql = "{ call SP_getPhieuNhapTheoMa(?) }"; // Modify the SQL statement to use the correct number of parameters
			
			
		    CallableStatement cstmt = conn.prepareCall(sql);
		    cstmt.setString(1, condition);
		    ResultSet rs = cstmt.executeQuery();
		while(rs.next()) {
			PhieuNhapChiTiet pn = new PhieuNhapChiTiet();
			pn.setMaPN(rs.getString("MaPN"));
			pn.setMaSP(rs.getString("MaSP"));
			pn.setSoLuong(rs.getString("SoLuong"));
			pn.setThanhTien(rs.getString("ThanhTien"));
			arr.add(pn);
			
		}
		return arr;
	}

}
