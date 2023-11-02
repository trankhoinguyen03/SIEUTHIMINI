/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import java.sql.SQLException;
import java.util.ArrayList;

import DAL.NhapHangDAL;
import DTO.NhapHang;

/**
 *
 * @author Acer
 */
public class NhapHangBLL {
	public ArrayList<NhapHang> getPhieuNhap() throws SQLException {
		NhapHangDAL nhd = new NhapHangDAL();
		ArrayList<NhapHang> arr = new ArrayList<NhapHang>();
		arr = nhd.docNhapHang();
		return arr;
	}
	public ArrayList<NhapHang> getPhieuNhapNV(String value) throws SQLException {
		NhapHangDAL nhd = new NhapHangDAL();
		ArrayList<NhapHang> arr = new ArrayList<NhapHang>();
		arr = nhd.docNhapHangNV(value);
		return arr;
	}
	public String getLastMaPN() throws SQLException {
		NhapHangDAL nhd = new NhapHangDAL();
		return nhd.layMaPNcuoi();
	}
	public boolean hidePhieuNhap(String value) throws SQLException {
		NhapHangDAL nhd = new NhapHangDAL();
		return nhd.anPhieuNhap(value);
	}
	public boolean addPhieuNhap(NhapHang obj) throws SQLException {
		NhapHangDAL nhd = new NhapHangDAL();
		return nhd.themPhieuNhap(obj);
	}
	public boolean updateTongTien(String value, String id) throws SQLException {
		NhapHangDAL nhd = new NhapHangDAL();
		return nhd.capNhatTongTien(value, id);
	}
	public String getTongTien(String value) throws SQLException {
		NhapHangDAL nhd = new NhapHangDAL();
		return nhd.layTongTien(value);
	}
	public static void main (String[] args) throws SQLException {
		NhapHangBLL test = new NhapHangBLL();
	}
}
