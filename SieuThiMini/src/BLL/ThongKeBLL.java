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
	public ArrayList<HoaDon> RenderOrders () throws SQLException{
		ArrayList<HoaDon> arrHd = new ArrayList<HoaDon>();
		ThongKeDAL tkd = new ThongKeDAL();
		arrHd = tkd.RenderOrders();
		return arrHd;
	}
	public ArrayList<NhapHang> renderPurchaseOrder(String conditon) throws SQLException{
		ArrayList<NhapHang> arr = new ArrayList<NhapHang>();
		ThongKeDAL tkd = new ThongKeDAL();
		if(conditon.equals("0")) {
			return arr = tkd.readPurchaseOrder("readall");
		}
		return arr = tkd.readPurchaseOrderMaLH(conditon);
	}

}
