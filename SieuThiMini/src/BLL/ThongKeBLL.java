package BLL;

import java.sql.SQLException;
import java.util.ArrayList;

import DAL.HoaDonDAL;
import DAL.ThongKeDAL;
import DTO.DoanhThu;
import DTO.HoaDon;
import DTO.PhieuNhapChiTiet;
import DTO.PhieuXuatChiTiet;

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
	public ArrayList<PhieuXuatChiTiet> renderTableOutput(String conditon) throws SQLException{
		ArrayList<PhieuXuatChiTiet> arr = new ArrayList<PhieuXuatChiTiet>();
		ThongKeDAL tkd = new ThongKeDAL();
		if(conditon.equals("0")) {
			return arr = tkd.readTableOutPut("readall");
		}
		return arr = tkd.readTableOutPutMaLH(conditon);
	
	}
	
	public ArrayList<DoanhThu> getDataDoanhThu(String dateFrom,String dateTo,String malh) throws SQLException{
		ArrayList<DoanhThu> arr = new ArrayList<DoanhThu>();
		ThongKeDAL tkd = new ThongKeDAL();
		if(malh.equals("0")) {
			arr = tkd.doanhThu(dateFrom, dateTo,null);
		}
		else {
			arr = tkd.doanhThu(dateFrom, dateTo,malh);
		}
		

		return arr;
		
	}
	public int getTongChiPhi(String dateFrom,String dateTo,String malh) throws SQLException {
		ThongKeDAL tkd = new ThongKeDAL();
		
			return tkd.getTongChiPhi(dateFrom, dateTo, Integer.parseInt(malh));
			
	
	
	}
	public int getTongDoanhThu(String dateFrom,String dateTo,String malh) throws SQLException {
		ThongKeDAL tkd = new ThongKeDAL();
		
			return tkd.getTongDoanhThu(dateFrom, dateTo, Integer.parseInt(malh));
			
	
	
	}
	public static void main(String[] args) throws SQLException {
		ThongKeBLL tkbll = new ThongKeBLL();
		System.out.println(tkbll.getTongChiPhi("2018-01-01", "2020-05-05","0"));
	}

}
