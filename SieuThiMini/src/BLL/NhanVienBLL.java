/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import java.sql.SQLException;
import java.util.ArrayList;

import DAL.NhanVienDAL;
import DTO.ChucVu;
import DTO.NhanVien;
import DTO.TaiKhoan;

/**
 *
 * @author Acer
 */
public class NhanVienBLL {

	public ArrayList<NhanVien> getNhanVien(String condition) throws SQLException {
		NhanVienDAL nvd = new NhanVienDAL();
		ArrayList<NhanVien> arr = new ArrayList<NhanVien>();
		arr = nvd.docNhanVien(condition);
		return arr;
	}
	public String getTenNV(String value) throws SQLException {
		NhanVienDAL nvd = new NhanVienDAL();
		return nvd.docTenNV(value);
	}
	public String getMaNV(String value) throws SQLException {
		NhanVienDAL nvd = new NhanVienDAL();
		return nvd.docMaNV(value);
	}
	public String getLastMaNV() throws SQLException {
		NhanVienDAL nvd = new NhanVienDAL();
		return nvd.layMaNVcuoi();
	}
	public boolean hideNhanVien(String id) throws SQLException {
		NhanVienDAL nvd = new NhanVienDAL();
		return nvd.anNhanVien(id);
	}
	public boolean addNhanVien(NhanVien obj) throws SQLException {
		NhanVienDAL nvd = new NhanVienDAL();
		return nvd.themNhanVien(obj);
	}
	public boolean fixNhanVien(NhanVien obj) throws SQLException {
		NhanVienDAL nvd = new NhanVienDAL();
		return nvd.suaNhanVien(obj);
	}
	public ArrayList<ChucVu> getChucVu() throws SQLException {
		NhanVienDAL nvd = new NhanVienDAL();
		ArrayList<ChucVu> arr = new ArrayList<ChucVu>();
		arr = nvd.docChucVu();
		return arr;
	}	
	public String getChucVuNV(String id) throws SQLException {
		NhanVienDAL nvd = new NhanVienDAL();
		return nvd.docChucVuNV(id);
	}
	public String getTenCV(String id) throws SQLException {
		NhanVienDAL nvd = new NhanVienDAL();
		return nvd.layTenCV(id);
	}
	public String getMaCV(String name) throws SQLException {
		NhanVienDAL nvd = new NhanVienDAL();
		return nvd.layMaCV(name);
	}
	
	public static void main(String[] args) throws SQLException {
		NhanVienBLL test = new NhanVienBLL();
		System.out.println(""+test.getLastMaNV());
	}
}
