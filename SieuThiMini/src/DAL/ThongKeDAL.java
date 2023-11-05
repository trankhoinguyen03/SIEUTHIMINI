package DAL;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.NhapHang;
import DTO.DoanhThu;
import DTO.HoaDon;
import DTO.PhieuNhapChiTiet;

public class ThongKeDAL extends connectSql {

	public ThongKeDAL() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArrayList<HoaDon> RenderOrders() throws SQLException{
		ArrayList<HoaDon> arr = new ArrayList<HoaDon>();
		String sql = "Select * from DSHOADON WHERE TrangThai = 1";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			HoaDon hd = new HoaDon();
			hd.setMaHD(rs.getString("MaHD"));
			hd.setMaNV(rs.getString("MaNV"));
			hd.setMaKH(rs.getString("MaKH"));
			hd.setTongTienSauKM(rs.getString("TongTienSauKM"));
			hd.setThoiDiemLap(rs.getString("ThoiDiemLap"));
			arr.add(hd);
			
		}
		return arr;
	}
	public ArrayList<HoaDon> thongKeHD(String start, String end) throws SQLException{
		ArrayList<HoaDon> arr = new ArrayList<HoaDon>();
		String sql = "Select * from DSHOADON WHERE TrangThai = 1 and ThoiDiemLap between ? and ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, start);
		pstm.setString(2, end);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			HoaDon hd = new HoaDon();
			hd.setMaHD(rs.getString("MaHD"));
			hd.setMaNV(rs.getString("MaNV"));
			hd.setMaKH(rs.getString("MaKH"));
			hd.setTongTienSauKM(rs.getString("TongTienSauKM"));
			hd.setThoiDiemLap(rs.getString("ThoiDiemLap"));
			arr.add(hd);
			
		}
		return arr;
	}
	public ArrayList<NhapHang> readPurchaseOrder() throws SQLException{
		ArrayList<NhapHang> arr = new ArrayList<NhapHang>();
		String sql = "Select * from DSPHIEUNHAP WHERE TrangThai = 1";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			NhapHang pn = new NhapHang();
			pn.setMaPn(rs.getString("MaPN"));
			pn.setMaNv(rs.getString("MaNV"));
			pn.setMaNcc(rs.getString("MaNCC"));
			pn.setTongTien(rs.getString("TongTien"));
			pn.setNgayNhap(rs.getString("NgayNhap"));
			arr.add(pn);
			
		}
		return arr;
	}
	public ArrayList<NhapHang> thongKePN(String start, String end) throws SQLException{
		ArrayList<NhapHang> arr = new ArrayList<NhapHang>();
		String sql = "Select * from DSPHIEUNHAP WHERE TrangThai = 1 and NgayNhap between ? and ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, start);
		pstm.setString(2, end);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			NhapHang pn = new NhapHang();
			pn.setMaPn(rs.getString("MaPN"));
			pn.setMaNv(rs.getString("MaNV"));
			pn.setMaNcc(rs.getString("MaNCC"));
			pn.setTongTien(rs.getString("TongTien"));
			pn.setNgayNhap(rs.getString("NgayNhap"));
			arr.add(pn);
			
		}
		return arr;
	}

}
