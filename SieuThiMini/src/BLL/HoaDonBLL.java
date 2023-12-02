package BLL;
import java.sql.SQLException;
import java.util.ArrayList;

import DAL.ChiTietPhieuNhapDAL;
import DAL.HoaDonDAL;
import DAL.KhachHangDAL;
import DTO.HoaDon;
import DTO.KhachHang;
import DTO.PhieuNhapChiTiet;
public class HoaDonBLL {
		public ArrayList<HoaDon> getHoaDon(String value) throws SQLException {
			HoaDonDAL hdd = new HoaDonDAL();
			ArrayList<HoaDon> arr = new ArrayList<HoaDon>();
			arr = hdd.docHoaDon(value, value);
			return arr;
		}
		public boolean addHoaDon(HoaDon obj) throws SQLException {
			HoaDonDAL hdd = new HoaDonDAL();
			return hdd.themhoadon(obj, null, null);
		}
		public String getLastmahd() throws SQLException
		{
			HoaDonDAL hdd = new HoaDonDAL();
			return hdd.getLastMaHD();
		}
		public boolean updatehoadon(HoaDon obj) throws SQLException {
			HoaDonDAL hdd = new HoaDonDAL();
			return hdd.updatehoadon(obj, null);
		}
		public boolean anhoadon(String obj) throws SQLException {
			HoaDonDAL hdd = new HoaDonDAL();
			return hdd.updatetrangthai(obj);
		}
		public int giamsoluong(String obj) throws SQLException {
			
			HoaDonDAL hdd = new HoaDonDAL();
			return hdd.getgiamsoluong(obj);
		}
//			public boolean tangdiemtong(String obj,String obj2) throws SQLException {
//						
//						HoaDonDAL hdd = new HoaDonDAL();
//						return hdd.gettangdiemtong(,obj2);
//					}
		public String diemthuong(String obj) throws SQLException {
			
			HoaDonDAL hdd = new HoaDonDAL();
			return hdd.getdiemthuong(obj);
		}
		public int tongkho(String obj) throws SQLException {
			
			HoaDonDAL hdd = new HoaDonDAL();
			return hdd.getTongKho(obj);
		}
		public int tongban(String obj) throws SQLException {
			
			HoaDonDAL hdd = new HoaDonDAL();
			return hdd.getTongBan(obj);
		}
		public int soluongtonkho(String obj) throws SQLException {
			
			HoaDonDAL hdd = new HoaDonDAL();
			return hdd.getSoLuongTonKho(obj);
		}
		public boolean diemtong(String obj,String obj2) throws SQLException {
			
			HoaDonDAL hdd = new HoaDonDAL();
			return hdd.getdiemtong(obj,obj2);
		}
			public String mucgiam(String obj) throws SQLException {
						
						HoaDonDAL hdd = new HoaDonDAL();
						return hdd.getMucGiam(obj);
					}
			}
		

