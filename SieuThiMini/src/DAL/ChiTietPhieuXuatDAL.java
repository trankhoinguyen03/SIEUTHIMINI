
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.HoaDon;
import DTO.ChiTietHoaDon;
import DTO.ChiTietPhieuXuat;

public class ChiTietPhieuXuatDAL extends connectSql {
	
	
	public ChiTietPhieuXuatDAL() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public ArrayList<ChiTietPhieuXuat> docCtHoaDon(){
		ArrayList<ChiTietPhieuXuat> arrHd = new ArrayList<ChiTietPhieuXuat>();
		try {
			String sql = "select * from CHITIETPHIEUXUAT";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
                    ChiTietPhieuXuat ctpx = new ChiTietPhieuXuat();
		ctpx.setMaPX(rs.getInt(1));
                ctpx.setMaSP(rs.getInt(2));
                ctpx.setSoLuongBan(rs.getInt(3));
                ctpx.setNgayXuat(rs.getString(4));
                
                                
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return arrHd;
	}
	
	public boolean themhoadon(ChiTietPhieuXuat ctpx,String condition) throws SQLException {
            System.out.println(ctpx.getSoLuongBan());
		String sql = "";
		if(condition.equals("soluong")) {
			sql =  "INSERT INTO CHITIETPHIEUXUAT( MaPX,MaSP, SoLuongBan,NgayXuat) VALUES (?,?, ?,?)";
		}
                
		PreparedStatement pstm = conn.prepareStatement(sql);
			
		try {

                       pstm.setInt(1,ctpx.getMaPX());
			pstm.setInt(2,ctpx.getMaSP());
			pstm.setInt(3, ctpx.getSoLuongBan());
                        pstm.setString(4, ctpx.getNgayXuat());
			
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
      
    public static void main(String[] args) throws SQLException {
        ChiTietPhieuXuatDAL ctd = new ChiTietPhieuXuatDAL();
//        System.out.println(ctd.themhoadon(ctpx, "themhhoadon"));
    }
        
}
