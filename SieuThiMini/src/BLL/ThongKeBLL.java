package BLL;

import java.sql.SQLException;
import java.util.ArrayList;

import DAL.HoaDonDAL;
import DAL.ThongKeDAL;
import DTO.DoanhThu;
import DTO.HoaDon;
import DTO.PhieuNhapChiTiet;
import DTO.NhapHang;

public class ThongKeBLL {
	public ArrayList<HoaDon> RenderOrders() throws SQLException{
		ThongKeDAL tkd = new ThongKeDAL();
		return tkd.RenderOrders();
	
	}
	public ArrayList<HoaDon> thongKeHD(String start, String end) throws SQLException{
		ThongKeDAL tkd = new ThongKeDAL();
		return tkd.thongKeHD(start, end);
	
	}
	public ArrayList<NhapHang> renderPurchaseOrder() throws SQLException{
		ThongKeDAL tkd = new ThongKeDAL();
		return tkd.readPurchaseOrder();
	}
	public ArrayList<NhapHang> thongKePN(String start, String end) throws SQLException{
		ThongKeDAL tkd = new ThongKeDAL();
		return tkd.thongKePN(start, end);
	}
}
