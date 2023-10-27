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
}
