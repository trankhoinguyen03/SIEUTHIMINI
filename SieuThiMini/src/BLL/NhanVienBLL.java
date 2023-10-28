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
	public ArrayList<NhanVien> getNhanVienMaNV(String value) throws SQLException {
		NhanVienDAL nvd = new NhanVienDAL();
		ArrayList<NhanVien> arr = new ArrayList<NhanVien>();
		arr = nvd.docNhanVienMaNV(value);
		return arr;
	}
}
