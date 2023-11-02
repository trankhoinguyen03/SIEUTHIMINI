/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import java.sql.SQLException;
import java.util.ArrayList;

import DAL.NhanVienDAL;
import DTO.NhanVien;

/**
 *
 * @author Acer
 */
public class NhanVienBLL {

	public ArrayList<NhanVien> getNhanVien() throws SQLException {
		NhanVienDAL nvd = new NhanVienDAL();
		ArrayList<NhanVien> arr = new ArrayList<NhanVien>();
		arr = nvd.docNhanVien("docnhanvien", null);
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
	public static void main(String[] args) throws SQLException {
		NhanVienBLL test = new NhanVienBLL();
		System.out.println(""+test.getLastMaNV());
	}
}
