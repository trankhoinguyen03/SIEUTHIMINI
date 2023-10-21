package BLL;

import java.sql.SQLException;
import java.util.ArrayList;

import DAL.HoaDonDAL;
import DAL.ThongKeDAL;
import DTO.DoanhThu;
import DTO.HoaDon;
import DTO.PhieuNhapChiTiet;

public class ThongKeBLL {
	public ArrayList<HoaDon> RenderOrders () throws SQLException{
		ArrayList<HoaDon> arrHd = new ArrayList<HoaDon>();
		HoaDonDAL hdDal = new HoaDonDAL();
		arrHd = hdDal.docHoaDon("docHoaDon", null);
		return arrHd;
	}
	public ArrayList<PhieuNhapChiTiet> renderPurchaseOrder(String conditon) throws SQLException{
		ArrayList<PhieuNhapChiTiet> arr = new ArrayList<PhieuNhapChiTiet>();
		ThongKeDAL tkd = new ThongKeDAL();
		if(conditon.equals("0")) {
			return arr = tkd.readPurchaseOrder("readall");
		}
		return arr = tkd.readPurchaseOrderMaLH(conditon);
	}

}
