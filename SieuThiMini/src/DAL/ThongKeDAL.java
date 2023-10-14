package DAL;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.DoanhThu;
import DTO.PhieuNhapChiTiet;
import DTO.PhieuXuatChiTiet;

public class ThongKeDAL extends connectSql {

	public ThongKeDAL() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArrayList<PhieuNhapChiTiet> readPurchaseOrder(String condition) throws SQLException{
		ArrayList<PhieuNhapChiTiet> arr = new ArrayList<PhieuNhapChiTiet>();
		String sql="";
		if(condition.equals("readall")) {
			sql = "Select * from CHITIETPHIEUNHAP";
		}

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			PhieuNhapChiTiet pn = new PhieuNhapChiTiet();
			pn.setMaPN(rs.getInt("MaPN"));
			pn.setMaSP(rs.getInt("MaSP"));
			pn.setMaNCC(rs.getInt("MaNCC"));
			pn.setSoLuong(rs.getInt("SoLuong"));
			pn.setNgaySX(rs.getString("NgaySX"));
			pn.setNgayNhap(rs.getString("NgayNhap"));
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
			pn.setMaPN(rs.getInt("MaPN"));
			pn.setMaSP(rs.getInt("MaSP"));
			pn.setMaNCC(rs.getInt("MaNCC"));
			pn.setSoLuong(rs.getInt("SoLuong"));
			pn.setNgaySX(rs.getString("NgaySX"));
			pn.setNgayNhap(rs.getString("NgayNhap"));
			arr.add(pn);
			
		}
		return arr;
	}
	public ArrayList<PhieuXuatChiTiet> readTableOutPut(String condition) throws SQLException{
		ArrayList<PhieuXuatChiTiet> arr = new ArrayList<PhieuXuatChiTiet>();
		String sql="";
		if(condition.equals("readall")) {
				sql = "Select * from CHITIETPHIEUXUAT";
		}

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			PhieuXuatChiTiet pn = new PhieuXuatChiTiet();
			pn.setMaPX(rs.getInt("MaPX"));
			pn.setMaSP(rs.getInt("MaSP"));
			pn.setSoLuongBan(rs.getInt("SoLuongBan"));
			pn.setNgayXuat(rs.getString("NgayXuat"));
			arr.add(pn);
			
		}
		return arr;
	}
	public ArrayList<PhieuXuatChiTiet> readTableOutPutMaLH(String condition) throws SQLException{
		ArrayList<PhieuXuatChiTiet> arr = new ArrayList<PhieuXuatChiTiet>();
		 String sql = "{ call SP_getPhieuXuatTheoMa(?) }"; // Modify the SQL statement to use the correct number of parameters
	
		
		    CallableStatement cstmt = conn.prepareCall(sql);
		    cstmt.setString(1, condition);
		    ResultSet rs = cstmt.executeQuery();
	
	
		while(rs.next()) {
			PhieuXuatChiTiet pn = new PhieuXuatChiTiet();
			pn.setMaPX(rs.getInt("MaPX"));
			pn.setMaSP(rs.getInt("MaSP"));
			pn.setSoLuongBan(rs.getInt("SoLuongBan"));
			pn.setNgayXuat(rs.getString("NgayXuat"));
			arr.add(pn);
			
		}
		return arr;
	}
	public int getTongChiPhi(String dateFrom,String dateTo,int malh) throws SQLException {
		String sql ="";
		if(malh==0) {
			
			sql = "{call SP_TongChiPhi(?,?)}";
		}
		else if(malh!=0) {
			
			sql ="{call SP_TongChiPhiTheoLoai(?,?,?)}";
		}
		
		CallableStatement cstmt = conn.prepareCall(sql);
		
		cstmt.setString(1, dateFrom);
		cstmt.setString(2, dateTo);
			if(malh!=0) {
			
			cstmt.setInt(3, malh);
			}
		ResultSet rs = cstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt("TongChiPhi");
		}
		
		return -1;
	}
	public int getTongDoanhThu(String dateFrom,String dateTo,int malh) throws SQLException {
		String sql ="";
		if(malh==0) {
			
			sql = "{call SP_TongDoanhThu(?,?)}";
		}
		else if(malh!=0) {
			
			sql ="{call SP_TongDoanhThuTheoLoai(?,?,?)}";
		}
		
		CallableStatement cstmt = conn.prepareCall(sql);
		
		cstmt.setString(1, dateFrom);
		cstmt.setString(2, dateTo);
			if(malh!=0) {
			
			cstmt.setInt(3, malh);
			}
		ResultSet rs = cstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt("Doanhthu");
		}
		
		return -1;
	}
	public ArrayList<DoanhThu> doanhThu(String dateFrom,String dateTo,String maLh) throws SQLException{
		 String sql ="";
		 ArrayList<DoanhThu> arr = new ArrayList<DoanhThu>();
		 if(maLh == null) {
			sql = "{ call SP_LoiNhuan(?, ?) }"; 
		 }
		 else {
			 sql = "{call SP_LoiNhuanTheoMa(?,?,?)}";
		 }
		    
		    CallableStatement cstmt = conn.prepareCall(sql);
		  
		    cstmt.setString(1, dateFrom);
		    cstmt.setString(2, dateTo);
  if(maLh!= null) {
		    	cstmt.setString(3, maLh);
		    }
		    ResultSet rs = cstmt.executeQuery();
		    while (rs.next()) {
		        // Retrieve data from the result set and create a new DoanhThu object
		        DoanhThu doanhThu = new DoanhThu();
		        doanhThu.setMaSP(rs.getInt("MaSP"));
		        doanhThu.setLoiNhuan(rs.getInt("Loi nhuan"));
		        // Set other properties of the DoanhThu object
		        
		        arr.add(doanhThu);
		    }
		    rs.close();
		    cstmt.close();
		    conn.close();
		    return arr;
	}
	public static void main(String[] args) throws SQLException {
		ThongKeDAL tkd = new ThongKeDAL();
		System.out.println(tkd.getTongDoanhThu("2018-01-01", "2020-05-05",0));
		
	}

}
