package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.LoaiHang;
import DTO.SanPham;
import DTO.Kho;

public class KhoDAL extends connectSql {
	
	
	public KhoDAL() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public ArrayList<Kho> dockho(){
		ArrayList<Kho> arrKho = new ArrayList<Kho>();
		try {
			String sql = "select * from KHO";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				Kho kho = new Kho();
				kho.setMaSP(rs.getString(1)); 
				kho.setSoLuong(rs.getString(2));
				arrKho.add(kho);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
//		closeConnection();
		return arrKho;
	}
	public int getLastMaSP() throws SQLException {
		String sql = "select top 1 MaSP from KHO order by MaSP DESC";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		  if (rs.next()) {
		        int maxColumnValue = rs.getInt("MaSP");
		        return maxColumnValue;
		    }
		  closeConnection();
		  return 0;
	}

	
	public static void main(String[] args) throws SQLException {
		LoaiHangDAL test = new LoaiHangDAL();
		 
		System.out.println(test.getNameLoaiHang(31));
		
	}
}
