/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import java.util.Calendar;
import java.util.GregorianCalendar;
import DTO.HoaDon;
import DTO.KhachHang;
import DTO.KhuyenMai;
import DTO.ChiTietHoaDon;
import DAL.HoaDonDAL;
import DAL.KhachHangDAL;
import DAL.ChiTietHoaDonDAL;
import DAL.LoaiHangDAL;
import DAL.SanPhamDAL;
import DTO.LoaiHang;
import DTO.NhanVien;
import DTO.SanPham;
import DTO.TaiKhoan;

import com.sun.jdi.connect.spi.Connection;

import BLL.DangNhapBLL;
import BLL.NhanVienBLL;
import BLL.XuatHoaDonBLL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.management.Query.value;
import javax.swing.DefaultComboBoxModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.JTable;

public class HoaDon1 extends javax.swing.JFrame {

	
	JDateChooser TFngay = new JDateChooser(); 
	String formatDateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateFormat.format(date);
		return dateString;
	}

	/**
	 * Creates new form HoaDon1
	 */
	TaiKhoan taiKhoan = DangNhapBLL.taiKhoan;

	public HoaDon1() throws SQLException {
		initComponents();
		antext();
		new Thread() {
			public void run() {
				while (true) {
					Calendar ca = new GregorianCalendar();
					int hour = ca.get(Calendar.HOUR);
					int minute = ca.get(Calendar.MINUTE);
					int second = ca.get(Calendar.SECOND);
					int PM_AM = ca.get(Calendar.AM_PM);
					String day_night;
					if (PM_AM == 1) {
						day_night = "PM";
					} else {
						day_night = "AM";
					}
					String time = hour + ":" + minute + ":" + second + " " + day_night;
					LBgio.setText(time);
				}
			}
		}.start();
	}

	boolean add, chon, fix, addsp, addpx = false;
//	JDateChooser TFngay = new JDateChooser();

	public void annut() {
		btngg.setEnabled(false);
		btnsua.setEnabled(false);
		btnluu.setEnabled(false);
		btnthanhtoan.setEnabled(false);
		btnthem.setEnabled(true);
		//btnthemsanpham2.setEnabled(false);
		jButton6.setEnabled(false);

	}

	public void hiennut() {
		btnsua.setEnabled(true);
		btnluu.setEnabled(true);
		btnthem.setEnabled(true);
		btngg.setEnabled(true);
	}

	public void hientext() {
		TFtenkh.setEnabled(true);
	   TFngay.setEnabled(true);
		TFsoluong.setEnabled(true);
		CBtenhh.setEnabled(true);
	}

	public void antext() {
		CBtenhh.setEnabled(false);
		TFtenkh.setEnabled(false);
		TFsdt.setEnabled(false);
		TFmakh.setEnabled(false);
	    TFngay.setEnabled(false);
		TFsoluong.setEnabled(false);
		TFmahh.setEnabled(false);
		TFthanhtien.setEnabled(false);
		TFgia.setEnabled(false);
		TFmahh.setEnabled(false);
		TFdiemthuong.setEnabled(false);
		TFtienkhach.setEnabled(false);
		TFgiamgia.setEnabled(false);
		TFtienthoi.setEnabled(false);
		TFmahd.setEnabled(false);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		TFtenkh = new javax.swing.JTextField();
		TFsdt = new javax.swing.JTextField();
		TFsdt.setEnabled(false);
		TFngay = new JDateChooser();
//		TFngay.getCalendarButton().addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.out.println(TFngay.getDate());
//			}
//		});
		// jLabel5 = new javax.swing.JLabel();
		TFmakh = new javax.swing.JTextField();
		TFmakh.setEnabled(false);
		// TFngay = new com.toedter.calendar.JDateChooser();
		jPanel3 = new javax.swing.JPanel();
		jPanel3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		TFmahh = new javax.swing.JTextField();
		jLabel8 = new javax.swing.JLabel();
		TFsoluong = new javax.swing.JTextField();
		jLabel9 = new javax.swing.JLabel();
		TFgia = new javax.swing.JTextField();
		jLabel10 = new javax.swing.JLabel();
		TFthanhtien = new javax.swing.JTextField();
		CBtenhh = new javax.swing.JComboBox<>();
		// TFmapx = new javax.swing.JTextField();
		// jLabel22 = new javax.swing.JLabel();
		jPanel4 = new javax.swing.JPanel();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		TFtongtien = new javax.swing.JTextField();
		TFtongtien.setText("0");
		TFtongtien.setEnabled(false);
		btngg = new javax.swing.JButton();
		btngg.setText("Giảm Giá");
		btngg.setEnabled(false);
		btnsua = new javax.swing.JButton();
		btnluu = new javax.swing.JButton();
		jButton6 = new javax.swing.JButton();
		jButton6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            HoaDonDAL hdd = new HoaDonDAL();
		            HoaDon hd = new HoaDon();
		            String mahd = TFmahd.getText();
		            hd.setTongTien(TFtongtien.getText());
		            hd.setTongTienSauKM(TFtong.getText());
		            hd.setKhuyenMai(TFmagiam.getText());
		            boolean updateSuccess = hdd.updatehoadon(hd, mahd);

		            if (updateSuccess) {
		              
		            } else {
		                // Xử lý khi cập nhật không thành công
		            }

		            
		            if (add) {
		                hd.setTongTien(TFtongtien.getText());
		                hd.setTongTienSauKM(TFtong.getText());
		                hd.setKhuyenMai(TFmagiam.getText());
		                boolean kiemtra = hdd.updatehoadon(hd, mahd);
		                
		                if (kiemtra) {
		                    add = false;
		                    // Cập nhật thành công
		                } else {
		                    // Xử lý khi cập nhật không thành công
		                }
		            }
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		            // Xử lý ngoại lệ nếu có lỗi
		        }
				                          
				try{
				    XSSFWorkbook wordkbook=new XSSFWorkbook();
				    XSSFSheet sheet=wordkbook.createSheet("Hóa Đơn");
				    XSSFCellStyle style = wordkbook.createCellStyle();
				      XSSFCellStyle style2 = wordkbook.createCellStyle();// tạo mới một XSSFCellStyle
				XSSFFont font = wordkbook.createFont();
				XSSFFont font2 = wordkbook.createFont();// tạo mới một XSSFFont
				font.setFontName("Calibri");
				font2.setFontName("Calibri");// thiết lập tên font chữ
				font.setFontHeightInPoints((short) 20); 
				font2.setFontHeightInPoints((short) 14); // thiết lập kích thước font chữ
				font.setColor(IndexedColors.BLUE.getIndex());
				font2.setColor(IndexedColors.RED.getIndex());// thiết lập màu chữ
				style.setFont(font); 
				style2.setFont(font2);// thiết lập font chữ cho style
				style.setAlignment(HorizontalAlignment.CENTER); // căn giữa ngang
				style.setVerticalAlignment(VerticalAlignment.CENTER); // căn giữa dọc
				style.setFillForegroundColor(IndexedColors.WHITE.getIndex()); // màu nền là trắng
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				style2.setAlignment(HorizontalAlignment.CENTER); // căn giữa ngang
				style2.setVerticalAlignment(VerticalAlignment.CENTER); // căn giữa dọc
				style2.setFillForegroundColor(IndexedColors.WHITE.getIndex()); // màu nền là trắng
				style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				    FileInputStream fileIn = new FileInputStream("D://DANHSACH.xlsx");
				 
				    TableModel model=jtable1.getModel();
				     XSSFRow headerRow3 = sheet.createRow(1);
				     headerRow3.createCell(6).setCellValue("HÓA ĐƠN CỦA KHÁCH HÀNG");
				     headerRow3.getCell(6).setCellStyle(style);
				   XSSFRow headerRow = sheet.createRow(2);
				headerRow.createCell(0).setCellValue("Tên Khách Hàng");
				headerRow.createCell(1).setCellValue("Mã Khách Hàng");
				headerRow.createCell(2).setCellValue("Số Điện Thoại");
				headerRow.createCell(3).setCellValue("Ngày");
				headerRow.createCell(4).setCellValue("Giờ");
				headerRow.createCell(5).setCellValue("Tổng tiền");
				headerRow.createCell(6).setCellValue("Tiền khách đưa");
				headerRow.createCell(7).setCellValue("Tổng thối");
				XSSFRow dataRow = sheet.createRow(3);
				dataRow.createCell(0).setCellValue(TFtenkh.getText());
				dataRow.createCell(1).setCellValue(TFmakh.getText());
				dataRow.createCell(2).setCellValue(TFsdt.getText());
				 java.util.Date dateFromUtil = TFngay.getDate();
				                      
				                    java.sql.Date dateFromSql = new java.sql.Date(dateFromUtil.getTime());

				                   
				dataRow.createCell(3).setCellValue(formatDateToString(dateFromSql));
				dataRow.createCell(4).setCellValue(LBgio.getText());
				dataRow.createCell(5).setCellValue(TFtong.getText());
				dataRow.createCell(6).setCellValue(TFtienkhach.getText());
				dataRow.createCell(7).setCellValue(TFtienthoi.getText());
				 XSSFRow headerRow4 = sheet.createRow(4);
				  headerRow4.createCell(3).setCellValue("CHI TIẾT ĐƠN HÀNG:");
				   headerRow4.getCell(3).setCellStyle(style2);
				    XSSFRow headerRow2 = sheet.createRow(5);
				    for (int j = 0; j < model.getColumnCount(); j++) {
				        XSSFCell headerCell = headerRow2.createCell(j);
				        headerCell.setCellValue(model.getColumnName(j));
				    }
				    for (int i = 0; i < model.getRowCount(); i++) {
				        XSSFRow dataRow2 = sheet.createRow(i + 6);
				        for (int j = 0; j < model.getColumnCount(); j++) {
				            XSSFCell cell = dataRow2.createCell(j);
				            cell.setCellValue(model.getValueAt(i, j).toString());
				        }
				    }
				FileOutputStream fileOut = new FileOutputStream("D://DANHSACH.xlsx");
				JOptionPane.showMessageDialog(rootPane, "xuất hóa đơn thành công");
				wordkbook.write(fileOut);
				fileOut.close();
				} catch(IOException ex)
				{
					ex.printStackTrace();					
				}

				   }       
		
		});
		btnthem = new javax.swing.JButton();
		jLabel13 = new javax.swing.JLabel();
		jLabel14 = new javax.swing.JLabel();
		jLabel15 = new javax.swing.JLabel();
		jLabel16 = new javax.swing.JLabel();
		TFtienkhach = new javax.swing.JTextField();
		TFtienkhach.setEnabled(false);
		TFtienthoi = new javax.swing.JTextField();
		TFdiemthuong = new javax.swing.JTextField();
		TFgiamgia = new javax.swing.JTextField();
		btnthanhtoan = new javax.swing.JButton();
		TFmahd = new javax.swing.JTextField();
		//btnthemsanpham2 = new javax.swing.JButton();
		jLabel21 = new javax.swing.JLabel();
		TFtong = new javax.swing.JTextField();
		TFtong.setText("0");
		TFtong.setEnabled(false);
		jPanel5 = new javax.swing.JPanel();
		jPanel6 = new javax.swing.JPanel();
		jButton7 = new javax.swing.JButton();
		jPanel7 = new javax.swing.JPanel();
		jLabel17 = new javax.swing.JLabel();
		jLabel18 = new javax.swing.JLabel();
		jLabel20 = new javax.swing.JLabel();
		LBgio = new javax.swing.JLabel();
		jLabel19 = new javax.swing.JLabel();
//		ChiTietHoaDonBLL ctbll = new ChiTietHoaDonBLL();
		NhanVienBLL ten = new NhanVienBLL();
		try {
			jLabel19.setText(ten.getTenNV(taiKhoan.getMaNV()));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		LBmanv = new javax.swing.JLabel();
		lbstatus = new javax.swing.JLabel();
		annut();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 28)); // NOI18N
		jLabel1.setText("HÓA ĐƠN");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(368)
						.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(571)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING).addGroup(jPanel1Layout
				.createSequentialGroup().addContainerGap(44, Short.MAX_VALUE).addComponent(jLabel1).addGap(23)));
		jPanel1.setLayout(jPanel1Layout);

		jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel2.setText("Tên Khách Hàng:");

		jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel3.setText("Số Điện Thoại:");

		jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel4.setText("Mã Khách Hàng:");

		TFtenkh.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					TFtenkhActionPerformed(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		TFsdt.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				TFsdtActionPerformed(evt);
			}
		});
		// jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		// jLabel5.setText("Ngày Đặt:");

//		TFmakh.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				try {
//					TFmakhActionPerformed(evt);
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
		btngg.setEnabled(false);
		
		JLabel lblNgy = new JLabel();
		lblNgy.setText("Ngày:");
		lblNgy.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		
		
	
		// TFngay.setMinSelectableDate(new java.util.Date(-62135791093000L));

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2Layout.setHorizontalGroup(
			jPanel2Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(jPanel2Layout.createSequentialGroup()
							.addGap(10)
							.addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
						.addGroup(jPanel2Layout.createSequentialGroup()
							.addGap(4)
							.addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
						.addComponent(TFtenkh, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
						.addComponent(TFsdt, 173, 173, 173))
					.addGap(18)
					.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel2Layout.createSequentialGroup()
							.addComponent(jLabel4, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(TFmakh, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
						.addGroup(jPanel2Layout.createSequentialGroup()
							.addComponent(lblNgy, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(TFngay, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))
					.addGap(45))
		);
		jPanel2Layout.setVerticalGroup(
			jPanel2Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
					.addGap(16)
					.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
							.addComponent(TFtenkh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addComponent(TFmakh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(20)
					.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
							.addComponent(TFsdt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNgy, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(TFngay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		jPanel2.setLayout(jPanel2Layout);
		

		jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

		jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel6.setText("Tên Hàng Hóa:");

		jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel7.setText("Mã Hàng Hóa:");

		TFmahh.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				TFmahhActionPerformed(evt);
			}
		});

		jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel8.setText("Số Lượng:");

		TFsoluong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				TFsoluongActionPerformed(evt);
			}
		});
		TFsoluong.addKeyListener(new java.awt.event.KeyAdapter() {
			private int soluongtruocdo=0;
			public void keyReleased(KeyEvent e) {
				btnluu.setEnabled(true);
					    try {
					        int soluong = Integer.parseInt(TFsoluong.getText());
					      soluongtruocdo=soluong;
					        try {
					            HoaDonDAL hdd=new HoaDonDAL();
					            int tonkho=hdd.getSoLuongTonKho(TFmahh.getText());
					            if(soluong>tonkho)
					            {
					                JOptionPane.showMessageDialog(rootPane, "Số lượng tồn kho không đủ!còn lại:"+tonkho);
					                TFsoluong.requestFocus();
					                TFsoluong.setText("");
					                btnluu.setEnabled(false);
					            }
					            else
					            {
					                 int gia = Integer.parseInt(TFgia.getText());
					        int thanhtien = soluong * gia;
					        TFthanhtien.setText(String.valueOf(thanhtien));
					       
					            }
					        } catch (SQLException ex) {
					            Logger.getLogger(HoaDon1.class.getName()).log(Level.SEVERE, null, ex);
					        }
					      
					      
					    } catch (NumberFormatException e1) {
					    	  JOptionPane.showMessageDialog(rootPane, "Số lượng không hợp lệ!");
					    	  TFsoluong.requestFocus();
					    	  TFsoluong.setText("");
					    	  btnluu.setEnabled(false);
					    }
					
//					        float value = Float.parseFloat(TFtongtien.getText());
//					        int diemthuong = 0;
//					        if (value >= 50000) {
//					            int bonus =  (int) (value / 50000);
//					            diemthuong += bonus;
//					            TFdiemthuong.setText(String.valueOf(diemthuong));
//					        }
//					        int value2 = 0;
//					        if(!TFdiemthuong.getText().isEmpty() &&!TFdiemthuong.getText().isBlank()) {
//					        	 value2 = Integer.parseInt(TFdiemthuong.getText());
//					   	}
////					        int value2 = Integer.parseInt(TFdiemthuong.getText());
//					        float giamgia = 0;
//					        if (value2 >= 5 && value2 < 10) {
//					            giamgia = 25000;
//					        } else if (value2 >= 10 && value2 < 15) {
//					            giamgia = 50000;
//					        } else if (value2 >= 15) {
//					            giamgia = 100000;
//
//					        }
//					        TFgiamgia.setText(String.valueOf(giamgia));
//					        float value3 = Float.parseFloat(TFgiamgia.getText());
//					        float value4 = Float.parseFloat(TFtongtien.getText());
//					        float tong = value4 - value3;
//					        TFtong.setText(String.valueOf(tong));
		}
		});

		jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel9.setText("Giá:");

		TFgia.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				TFgiaActionPerformed(evt);
			}
		});

		jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel10.setText("Thành Tiền:");

		TFthanhtien.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				TFthanhtienActionPerformed(evt);
			}
		});
		TFthanhtien.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				TFthanhtienKeyReleased(evt);
			}
		});

		CBtenhh.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				CBtenhhMouseClicked(evt);
			}
		});
		CBtenhh.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				CBtenhhActionPerformed(evt);
			}
		});

		// jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		// jLabel22.setText("Mã Phiếu Xuất");

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel3Layout
						.createSequentialGroup().addGap(
								69)
						.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
								.addGroup(jPanel3Layout.createSequentialGroup()
										.addComponent(jLabel8, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
										.addGap(31))
								.addComponent(jLabel6, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
								.addGroup(jPanel3Layout.createSequentialGroup()
										.addComponent(jLabel10, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
										.addGap(31)))
						.addGap(18)
						.addGroup(
								jPanel3Layout.createParallelGroup(Alignment.LEADING)
										.addComponent(TFsoluong, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
										.addComponent(CBtenhh, GroupLayout.PREFERRED_SIZE, 122,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(jPanel3Layout.createSequentialGroup()
												.addComponent(TFthanhtien, GroupLayout.DEFAULT_SIZE, 122,
														Short.MAX_VALUE)
												.addGap(1)))
						.addGap(43)
						.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
								.addComponent(jLabel7, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE).addComponent(
										jLabel9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						// .addComponent(jLabel22, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
						// Short.MAX_VALUE))
						.addGap(46)
						.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
								.addComponent(TFgia, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
								.addComponent(TFmahh, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
						// .addComponent(TFmapx, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
						.addGap(106)));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel3Layout
				.createSequentialGroup().addContainerGap()
				.addGroup(jPanel3Layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(CBtenhh, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE).addComponent(jLabel6)
						.addComponent(jLabel7).addComponent(TFmahh, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(jPanel3Layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel8)
						.addComponent(TFsoluong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel9).addComponent(TFgia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
						// .addComponent(jLabel22, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 24,
						// Short.MAX_VALUE)
						.addGroup(jPanel3Layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel10)
								.addComponent(TFthanhtien, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
				// .addComponent(TFmapx, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
				// GroupLayout.PREFERRED_SIZE)))
				.addGap(16)));
		jPanel3.setLayout(jPanel3Layout);

		jLabel11.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
		jLabel11.setText("Mã Hóa Đơn:");

		jLabel12.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
		jLabel12.setText("Tổng Tiền :");

		TFtongtien.addCaretListener(new javax.swing.event.CaretListener() {
			public void caretUpdate(javax.swing.event.CaretEvent evt) {
				TFtongtienCaretUpdate(evt);
			}
		});
		TFtongtien.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				TFtongtienActionPerformed(evt);
			}
		});
		TFtongtien.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				TFtongtienKeyReleased(evt);
			}
		});
		
		btngg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		btngg.setIcon(new ImageIcon(HoaDon1.class.getResource("/GUI/Image/Pay.png")));
		btngg.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
			    hiennut();
			    antext();
			    btnluu.setEnabled(false);
			    TFdiemthuong.setEnabled(false);
			    int choice = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn dùng điểm thưởng để giảm giá không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
			   
			    if (choice == JOptionPane.YES_OPTION) {
			        KhachHangDAL khachhang = null;
			        try {
			            khachhang = new KhachHangDAL();
			            KhachHang kh=new KhachHang();
			            String diemthuong = khachhang.getdiemthuong(TFmakh.getText());
			            TFdiemthuong.setText(diemthuong);
			            String diemthuong2=TFdiemthuong.getText();
			            HoaDonDAL hdd=new HoaDonDAL();
			            int value=Integer.parseInt(diemthuong2);
			            KhuyenMai km=new KhuyenMai();
			            if(value>0&&value<=15)
			            {
			            	km.setMaKM("KM1");
			            	TFmagiam.setText(km.getMaKM());
			            	String mucgiam=hdd.getMucGiam(km.getMaKM());
			            	int giatri= Integer.parseInt(mucgiam);
			            	int tongtien= Integer.parseInt(TFtongtien.getText());
			            	int giamgia=giatri*tongtien/100;
			            	TFgiamgia.setText(""+giamgia);
			            
//			            	 float tong=tongtien-giamgia;
//			            	TFtong.setText(String.valueOf(tong));
//			            	String makh=TFmakh.getText();
//			            	String makm=TFmagiam.getText();
//			            	String tongdiemthuong=hdd.getdiemtong(makh, makm);
//			            	float tongcuoidiemthuong=Float.parseFloat(tongdiemthuong);
//			            	kh.setDiemThuong(tongcuoidiemthuong);
//			            	System.out.print(kh.getDiemThuong());
			            	
			            }
			            else if(value>15&&value<=30)
			            {
			            	km.setMaKM("KM2");
			            	TFmagiam.setText(km.getMaKM());
			            	String mucgiam=hdd.getMucGiam(km.getMaKM());
			            	int giatri= Integer.parseInt(mucgiam);
			            	int tongtien= Integer.parseInt(TFtongtien.getText());
			            	int giamgia=giatri*tongtien/100;
			            	TFgiamgia.setText(""+giamgia);
//			            	 tong=tongtien-giamgia;
//			            	TFtong.setText(String.valueOf(tong));
//			            	String makh=TFmakh.getText();
//			            	String makm=TFmagiam.getText();
//			            	String tongdiemthuong=hdd.getdiemtong(makh, makm);
//			            	float tongcuoidiemthuong=Float.parseFloat(tongdiemthuong);
//			            	kh.setDiemThuong(tongcuoidiemthuong);
			            }
			            else if(value>30&&value<=45)
			            {
			            	km.setMaKM("KM3");
			            	TFmagiam.setText(km.getMaKM());
			            	String mucgiam=hdd.getMucGiam(km.getMaKM());
			            	int giatri= Integer.parseInt(mucgiam);
			            	int tongtien= Integer.parseInt(TFtongtien.getText());
			            	int giamgia=giatri*tongtien/100;
			            	TFgiamgia.setText(""+giamgia);
//			            	 tong=tongtien-giamgia;
//			            	TFtong.setText(String.valueOf(tong));
//			            	String makh=TFmakh.getText();
//			            	String makm=TFmagiam.getText();
//			            	String tongdiemthuong=hdd.getdiemtong(makh, makm);
//			            	float tongcuoidiemthuong=Float.parseFloat(tongdiemthuong);
//			            	kh.setDiemThuong(tongcuoidiemthuong);
			            }
			            else 
			            {
			            	km.setMaKM("KM4");
			            	TFmagiam.setText(km.getMaKM());
			            	String mucgiam=hdd.getMucGiam(km.getMaKM());
			            	int giatri= Integer.parseInt(mucgiam);
			            	int tongtien= Integer.parseInt(TFtongtien.getText());
			            	int giamgia=giatri*tongtien/100;
			            	TFgiamgia.setText(""+giamgia);
//			            	 tong=tongtien-giamgia;
//			            	TFtong.setText(String.valueOf(tong));
//			            	String makh=TFmakh.getText();
//			            	String makm=TFmagiam.getText();
//			            	String tongdiemthuong=hdd.getdiemtong(makh, makm);
//			            	float tongcuoidiemthuong=Float.parseFloat(tongdiemthuong);
//			            	kh.setDiemThuong(tongcuoidiemthuong);
			            }
//			            TFgiamgia.setText(TFdiemthuong.getText());
//			            float value3=Float.parseFloat(TFgiamgia.getText());
//			            float value4=Float.parseFloat(TFtongtien.getText());
//			            if(value4>value3)
//			            {
//			            	float tong=value4-value3;
//			            	TFtong.setText(String.valueOf(tong));
//			            }
//			            else
//			            {
//			            	TFtong.setText(""+0);
//			            	
//			            }
			        } catch (SQLException e) {
			            e.printStackTrace();
			        }
			        btnxacnhan.setEnabled(true);
			        btngg.setEnabled(false);
			    } else {
			        // Nếu người dùng không chọn YES
			        // Đặt giá trị diemthuong về 0
			        TFdiemthuong.setText("0");
			        KhuyenMai km=new KhuyenMai();
			        HoaDonDAL hdd;
					try {
						hdd = new HoaDonDAL();
						 km.setMaKM("KM1");
			            	TFmagiam.setText(km.getMaKM());
			            	String mucgiam=hdd.getMucGiam(km.getMaKM());
			            	int giatri= Integer.parseInt(mucgiam);
			            	int tongtien= Integer.parseInt(TFtongtien.getText());
			            	int giamgia=giatri*tongtien/100;
			            	TFgiamgia.setText(""+giamgia);
			            	
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			       btnxacnhan.setEnabled(true);
			       btngg.setEnabled(false);
			        
			    }
			}

			    
			        

//			            TFdiemthuong.addKeyListener(new KeyAdapter() {
//			                public void keyReleased(KeyEvent e) {
//			                    if (!TFdiemthuong.getText().isEmpty()) {
//			                        try {
//			                            float giamgia = 0;
//			                            int diemthuong2 = Integer.parseInt(TFdiemthuong.getText());
//			                            // Kiểm tra xem diemthuong2 có lớn hơn hoặc bằng 1 không
//			                            if (diemthuong2 >= 1) {
//			                                giamgia = diemthuong2; // Cập nhật giá trị giảm giá tương ứng với số điểm thưởng
//			                                TFgiamgia.setText(String.valueOf(giamgia));
//			                                float giamgia3 = Float.parseFloat(TFgiamgia.getText());
//			                                float tongtien = Float.parseFloat(TFtongtien.getText());
//			                                float tiencuoi = 0;
//			                                if (giamgia3 > tongtien) {
//			                                    TFtong.setText(String.valueOf(tiencuoi));
//			                                } else {
//			                                    tiencuoi = tongtien - giamgia3;
//			                                    TFtong.setText(String.valueOf(tiencuoi));
//			                                }
//			                            } else {
//			                                giamgia = 0;
//			                                TFgiamgia.setText(String.valueOf(giamgia));
//			                            }
//			                        } catch (NumberFormatException ex) {
//			                            // Xử lý lỗi NumberFormatException
//			                            ex.printStackTrace();
//			                        }
//			                    }
//			                }
//			            });
//
//			            TFtienkhach.setEnabled(true);
//			        } catch (SQLException e) {
//			            // Xử lý lỗi SQLException
//			            e.printStackTrace();
//			        }
		    
			    }

			
		);

		btnsua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		btnsua.setIcon(new ImageIcon(HoaDon1.class.getResource("/GUI/Image/Refresh-icon.png"))); // NOI18N
		btnsua.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		        
		        DefaultTableModel model = (DefaultTableModel)jtable1.getModel();
		        model.setRowCount(0);
		        reset();
		        TFmagiam.setText("");
				TFmahh.setText("");
				TFgia.setText("");
				TFtong.setText("0");
				CBtenhh.setSelectedIndex(0);
				TFngay.setToolTipText("");
		    }
		});

		btnluu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		btnluu.setIcon(new ImageIcon(HoaDon1.class.getResource("/GUI/Image/Save.png"))); // NOI18N
		btnluu.setText("Lưu");
		btnluu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnluuActionPerformed(evt);
			}
		});

		jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jButton6.setIcon(new ImageIcon(HoaDon1.class.getResource("/GUI/Image/Print Sale.png"))); // NOI18N
		jButton6.setText("Xuất Hóa Đơn");
		/*
		 * jButton6.addActionListener(new java.awt.event.ActionListener() { public void
		 * actionPerformed(java.awt.event.ActionEvent evt) {
		 * jButton6ActionPerformed(evt); } });
		 */

		btnthem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		btnthem.setIcon(new ImageIcon(HoaDon1.class.getResource("/GUI/Image/Add.png"))); // NOI18N
		btnthem.setText("Hóa Đơn");
		btnthem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnthemActionPerformed(evt);
			}
		});

		jLabel13.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
		jLabel13.setText("Tiền Khách:");

		jLabel14.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
		jLabel14.setText("Điểm Thưởng:");

		jLabel15.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
		jLabel15.setText("Giảm giá:");

		jLabel16.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
		jLabel16.setText("Tiền Thối Lại:");

		TFtienkhach.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				TFtienkhachActionPerformed(evt);
			}
		});

		TFdiemthuong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				TFdiemthuongActionPerformed(evt);
			}
		});
		TFdiemthuong.addKeyListener(new java.awt.event.KeyAdapter() {
		    public void keyReleased(java.awt.event.KeyEvent evt) {
		        if (!TFdiemthuong.getText().isEmpty()) {
		            int diemthuong2 = Integer.parseInt(TFdiemthuong.getText());
		            
		            
		            if (diemthuong2 >= 1) {
		               TFgiamgia.setText(""+diemthuong2);
		            }
		            else
		            {
		            	TFgiamgia.setText(""+0);
		            }
		            
		        } else {
		            // Nếu TFdiemthuong rỗng, bạn có thể xử lý tại đây nếu cần
		        }
		    }
		});


		TFgiamgia.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				TFgiamgiaActionPerformed(evt);
			}
		});
		TFgiamgia.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				TFgiamgiaKeyReleased(evt);
			}
		});

		btnthanhtoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		btnthanhtoan.setIcon(new ImageIcon(HoaDon1.class.getResource("/GUI/Image/Pay.png"))); // NOI18N
		btnthanhtoan.setText("Thanh Toán");
		btnthanhtoan.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if(TFtienkhach.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPane, "Chưa nhập số tiền của khách!");
				} else if (!TFtienkhach.getText().matches("\\d+")) {
					JOptionPane.showMessageDialog(rootPane, "Tiền khách phải là số!");
				} else {
					try {
						HoaDonDAL hdd=new HoaDonDAL();
						String makh=TFmakh.getText();
						String tongtien1=TFtongtien.getText();
						int tongdiem=hdd.gettangdiemtong(makh, tongtien1);
						KhachHang kh=new KhachHang();
						kh.setTichDiem(tongdiem);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}              
			        int tongtien = Integer.parseInt(TFtong.getText());
			        int tienkhach = Integer.parseInt(TFtienkhach.getText());
			        if (tienkhach < tongtien) {
			            JOptionPane.showMessageDialog(rootPane, "Số tiền không đủ để thực hiện giao dịch");
			        } else {
			            int tienthoilai = tienkhach - tongtien;
			            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
			         // Format the tienthoilai value as currency
			         String formattedTienthoilai = currencyFormat.format(tienthoilai);
			            TFtienthoi.setText(String.valueOf(tienthoilai));
			            btnthem.setEnabled(true);
			            btnthanhtoan.setEnabled(false);
			            jButton6.setEnabled(true);
			        }
				}
				  
	        }
		});

		TFmahd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				TFmahdActionPerformed(evt);
			}
		});

		/*
		 * btnthemsanpham2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		 * btnthemsanpham2.setIcon(new
		 * ImageIcon(HoaDon1.class.getResource("/GUI/Image/Add.png"))); // NOI18N
		 * btnthemsanpham2.setText("Sản Phẩm"); btnthemsanpham2.addActionListener(new
		 * java.awt.event.ActionListener() { public void
		 * actionPerformed(java.awt.event.ActionEvent evt) {
		 * btnthemsanpham2ActionPerformed(evt); } });
		 */

		jLabel21.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
		jLabel21.setText("Tổng Tiền Sau Khi Giảm:");

		TFtong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				TFtongActionPerformed(evt);
			}
		});
		TFtong.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				TFtongKeyReleased(evt);
			}
		});

		btnThe = new JButton();
		btnThe.setIcon(new ImageIcon(HoaDon1.class.getResource("/GUI/Image/Add.png")));
		btnThe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuanLyKhachHangGui kh;
				try {
					kh = new QuanLyKhachHangGui();
					setVisible(false);
					kh.setVisible(true);
					kh.setLocationRelativeTo(null);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnThe.setText("Thêm Khách Hàng");
		btnThe.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnThe.setEnabled(false);
		
		lblMGim = new JLabel();
		lblMGim.setText("Mã Giảm :");
		lblMGim.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		
		TFmagiam = new JTextField();
		TFmagiam.setEnabled(false);
		
		btnxacnhan = new JButton();
		btnxacnhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				int tongtien= Integer.parseInt(TFtongtien.getText());
				int giamgia= Integer.parseInt(TFgiamgia.getText());
				int tong=tongtien-giamgia;
            	TFtong.setText(String.valueOf(tong));
            	try {
					HoaDonDAL hdd=new HoaDonDAL();
					String makh=TFmakh.getText();
					String magiam=TFmagiam.getText();
					int tongdiem=hdd.getdiemtong(makh,magiam);
					KhachHang kh=new KhachHang();
					kh.setTichDiem(tongdiem);
					btngg.setEnabled(false);
					btnxacnhan.setEnabled(false);
					TFtienkhach.setEnabled(true);
					btnthanhtoan.setEnabled(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
			}
		});
		btnxacnhan.setIcon(new ImageIcon(HoaDon1.class.getResource("/GUI/Image/Change.png")));
		btnxacnhan.setText("Xác Nhận");
		btnxacnhan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnxacnhan.setEnabled(false);

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4Layout.setHorizontalGroup(
			jPanel4Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup()
					.addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel4Layout.createSequentialGroup()
							.addGap(16)
							.addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
								.addGroup(jPanel4Layout.createSequentialGroup()
									.addComponent(TFmahd, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
									.addGap(62)
									.addComponent(jLabel14, GroupLayout.PREFERRED_SIZE, 133, Short.MAX_VALUE))
								.addGroup(jPanel4Layout.createSequentialGroup()
									.addComponent(jLabel11, GroupLayout.PREFERRED_SIZE, 130, Short.MAX_VALUE)
									.addGap(54)
									.addComponent(jLabel12, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
								.addGroup(jPanel4Layout.createSequentialGroup()
									.addComponent(TFtongtien, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(TFdiemthuong, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
							.addGap(133))
						.addGroup(jPanel4Layout.createSequentialGroup()
							.addComponent(btnthem, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btngg, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
							.addGap(14)
							.addComponent(btnsua, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
							//.addComponent(btnthemsanpham2, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)))
					.addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel4Layout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(TFmagiam, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addGroup(jPanel4Layout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMGim, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
						.addGroup(jPanel4Layout.createSequentialGroup()
							.addGap(18)
							.addComponent(btnluu, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel4Layout.createSequentialGroup()
							.addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
								.addComponent(jLabel13, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
								.addComponent(jLabel15, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
							.addGap(34)
							.addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
								.addComponent(TFtienkhach, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
								.addComponent(TFgiamgia, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
							.addGap(38)
							.addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
								.addGroup(jPanel4Layout.createSequentialGroup()
									.addComponent(jLabel16, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
									.addGap(59)
									.addComponent(TFtienthoi, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
								.addGroup(jPanel4Layout.createSequentialGroup()
									.addComponent(jLabel21)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(TFtong, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)))
							.addGap(50))
						.addGroup(jPanel4Layout.createSequentialGroup()
							.addGap(11)
							.addComponent(btnxacnhan, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnthanhtoan, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(jButton6, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnThe)
							.addGap(22)))
					.addContainerGap())
		);
		jPanel4Layout.setVerticalGroup(
			jPanel4Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup()
					.addGap(21)
					.addGroup(jPanel4Layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jLabel11)
						.addComponent(jLabel12)
						.addComponent(jLabel13, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel16, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(TFtienkhach, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(TFtongtien, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMGim, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(TFtienthoi, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(jPanel4Layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jLabel14)
						.addComponent(jLabel15, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(TFgiamgia, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(TFmahd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel21)
						.addComponent(TFdiemthuong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(TFmagiam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(TFtong, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(jPanel4Layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(jPanel4Layout.createParallelGroup(Alignment.BASELINE)
							.addComponent(jButton6, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnThe, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
						.addGroup(jPanel4Layout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnthem, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnthanhtoan, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addComponent(btngg, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnsua, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
							//.addComponent(btnthemsanpham2, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnxacnhan, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnluu, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		jPanel4.setLayout(jPanel4Layout);

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 241, Short.MAX_VALUE));

		jButton7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
		jButton7.setIcon(new ImageIcon(HoaDon1.class.getResource("/GUI/Image/Back.png"))); // NOI18N
		jButton7.setText("Hệ Thống");
		jButton7.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton7ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
		jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(jPanel6Layout.createSequentialGroup().addContainerGap()
						.addComponent(jButton7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));
		jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel6Layout.createSequentialGroup().addGap(18)
						.addComponent(jButton7, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(20, Short.MAX_VALUE)));
		jPanel6.setLayout(jPanel6Layout);

		jPanel7.setToolTipText("Tên");

		jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel17.setText("Tên:");

		jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel18.setText("Mã Nhân Viên:");

		jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel20.setText("Giờ:");

		LBgio.setForeground(new java.awt.Color(51, 51, 255));
		LBgio.setText("2:19:00 PM");
		
		TFmanv = new JTextField();
		TFmanv.setText(taiKhoan.getMaNV());
		TFmanv.setEnabled(false);

		javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
		jPanel7Layout.setHorizontalGroup(
			jPanel7Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel7Layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(jPanel7Layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(jPanel7Layout.createSequentialGroup()
							.addComponent(jLabel20, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(LBgio, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(131))
						.addGroup(jPanel7Layout.createSequentialGroup()
							.addComponent(jLabel17, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jLabel19, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
							.addGap(22))
						.addGroup(jPanel7Layout.createSequentialGroup()
							.addComponent(jLabel18, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(jPanel7Layout.createParallelGroup(Alignment.LEADING)
								.addGroup(jPanel7Layout.createSequentialGroup()
									.addGap(36)
									.addComponent(LBmanv, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
								.addGroup(jPanel7Layout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(TFmanv, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))
							.addGap(79)))
					.addGap(4))
		);
		jPanel7Layout.setVerticalGroup(
			jPanel7Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel7Layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(jPanel7Layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jLabel17)
						.addComponent(jLabel19, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(jPanel7Layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jLabel18)
						.addComponent(LBmanv)
						.addComponent(TFmanv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(jPanel7Layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jLabel20)
						.addComponent(LBgio))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		jPanel7.setLayout(jPanel7Layout);

	
		/*
		 * jTable2.setModel(new javax.swing.table.DefaultTableModel( new Object [][] {
		 * 
		 * }, new String [] { "Mã Khách Hàng", "Tên Khách Hàng", "SDT", "Tích Điểm" }
		 * )); jTable2.addAncestorListener(new javax.swing.event.AncestorListener() {
		 * public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
		 * jTable1AncestorAdded(evt); } public void
		 * ancestorMoved(javax.swing.event.AncestorEvent evt) { } public void
		 * ancestorRemoved(javax.swing.event.AncestorEvent evt) { } });
		 */

		/*
		 * Table1.setModel(new javax.swing.table.DefaultTableModel( new Object [][] {
		 * 
		 * }, new String [] { "Mã Hóa Đơn", "Nhân Viên", "Tổng Tiền",
		 * "Khuyến Mãi","Tổng Tiền Sau Khuyến Mãi" } )); jTable1.addAncestorListener(new
		 * javax.swing.event.AncestorListener() { public void
		 * ancestorAdded(javax.swing.event.AncestorEvent evt) {
		 * jTable1AncestorAdded(evt); } public void
		 * ancestorMoved(javax.swing.event.AncestorEvent evt) { } public void
		 * ancestorRemoved(javax.swing.event.AncestorEvent evt) { } });
		 */

	
		lbstatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		lbstatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbstatus.setText("  Trạng Thái");

		JPanel panel_table = new JPanel();
		
				JLabel lblNewLabel_sp = new JLabel("DANH SÁCH SẢN PHẨM TRONG HÓA ĐƠN");
				lblNewLabel_sp.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_sp.setFont(new Font("Arial", Font.BOLD, 20));
		
		scrollPane = new JScrollPane();

		GroupLayout gl_panel_table = new GroupLayout(panel_table);
		gl_panel_table.setHorizontalGroup(
			gl_panel_table.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_table.createSequentialGroup()
					.addContainerGap(542, Short.MAX_VALUE)
					.addComponent(lblNewLabel_sp, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE)
					.addGap(522))
				.addGroup(Alignment.LEADING, gl_panel_table.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1396, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(111, Short.MAX_VALUE))
		);
		gl_panel_table.setVerticalGroup(
			gl_panel_table.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_table.createSequentialGroup()
					.addGap(24)
					.addComponent(lblNewLabel_sp, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(300, Short.MAX_VALUE))
		);
		jtable1 = new JTable();
		scrollPane.setViewportView(jtable1);
		panel_table.setLayout(gl_panel_table);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(jPanel4, GroupLayout.DEFAULT_SIZE, 1530, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, layout.createParallelGroup(Alignment.TRAILING)
							.addGroup(layout.createSequentialGroup()
								.addComponent(jPanel6, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(jPanel7, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE))
							.addGroup(layout.createSequentialGroup()
								.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, 819, Short.MAX_VALUE))
							.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
									.addGroup(layout.createSequentialGroup()
										.addGap(372)
										.addComponent(lbstatus, GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
										.addGap(565))
									.addGroup(layout.createSequentialGroup()
										.addGap(10)
										.addComponent(panel_table, GroupLayout.PREFERRED_SIZE, 1488, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)))
								.addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(26))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(jPanel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup()
							.addContainerGap()
							.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addComponent(jPanel7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
						.addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup()
							.addComponent(panel_table, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lbstatus, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))))
		);
		getContentPane().setLayout(layout);

		pack();
	}// </editor-fold>

	private void TFtenkhActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
		KhachHangDAL khd = new KhachHangDAL();
		String makh = khd.getmakh(TFtenkh.getText());
		String sodt=khd.getsdt(TFtenkh.getText());
		if (makh.equals("KH000")) {
			JOptionPane.showMessageDialog(rootPane, "Khong ton tai ten khach hang!");
			btnThe.setEnabled(true);
		} else  {

			TFmakh.setText(makh);
			TFsdt.setText(sodt);
		}
	}

	private void TFsdtActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

//	private void TFmakhActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
//		try {
//			KhachHangDAL khd = new KhachHangDAL();
//
////         String maText = TFmakh.getText(); 
////         int ma = Integer.parseInt(maText);
//
//			int makh = khd.getmakh(TFtenkh.getText());
//			TFtenkh.setText(makh + "");
//		} catch (SQLException ex) {
//			Logger.getLogger(HoaDon1.class.getName()).log(Level.SEVERE, null, ex);
//		}
//
//	}

	private void TFmahhActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void TFsoluongActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void TFgiaActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void TFthanhtienActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void TFtongtienActionPerformed(java.awt.event.ActionEvent evt) {

	}
	public void calculateTotal() {
	    int tongtien = Integer.parseInt(TFtongtien.getText());
	   // hoặc bạn có thể sử dụng giá trị hiện tại của tong nếu không khởi tạo lại
	    int giamgia = Integer.parseInt(TFgiamgia.getText()); // giả sử giamgia cũng thay đổi tùy theo giá trị tongtien

	    // Thực hiện việc tính toán mới cho giá trị tong dựa trên tongtien và các yếu tố khác
	    // ở đây giả sử công thức tính là tổng = tongtien - giamgia
	    int tong = tongtien - giamgia;

	    // Cập nhật giá trị cho TFtong
	    TFtong.setText(String.valueOf(tong));
	}

	private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {

		try {

			annut();
			antext();
			add = true;
			String[] columnNames = {"Tên Hàng", "Mã Hàng", "Số Lượng", "Thành Tiền"};
			model = new DefaultTableModel(columnNames,0);
			jtable1.setModel(model);
			hienthisanpham("them");
			btnluu.setEnabled(true);
			TFngay.setEnabled(true);
			TFtenkh.setEnabled(true);
			TFsoluong.setEnabled(true);
			CBtenhh.setEnabled(true);
			HoaDonDAL hoadonDAL = new HoaDonDAL();
			String lastMaHD = hoadonDAL.getLastMaHD();
		    int lastnumber = Integer.parseInt(lastMaHD.substring(2));
		    String newMaHD = "HD" + String.format("%03d", lastnumber + 1);

		    TFmahd.setText(newMaHD);
			KhachHangDAL khd = new KhachHangDAL();
//            SanPhamDAL sanphamDAL = new SanPhamDAL();
//            String tensp = sanphamDAL.gettensp();
//            
//            String[] tenSPArray = sanphamDAL.getTenSP();
//String tenSanPhamText = String.join(", ", tenSPArray); // Nối các phần tử trong mảng bằng dấu phẩy và khoảng trắng
//List<String> keywordList = new ArrayList<>();
//keywordList.addAll(Arrays.asList(tenSPArray));
//String[] keywords = keywordList.toArray(new String[keywordList.size()]);
//
//// Tạo một TextField
//

//
//// Tạo một AutoCompletionBinding và đặt danh sách từ khóa cho nó
//AutoCompletionBinding<String> autoCompleteBinding = TFsanpham.bindAutoCompletion(TFsanpham, keywords);

//
//// Đặt số lượng hàng hiển thị trong danh sách gợi ý
//autoCompleteBinding.setVisibleRowCount(10);

//TFsanpham.setText(tenSanPhamText);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

//	public boolean check() throws SQLException {
//
//		if (TFtenkh.getText().isEmpty()) {
//			JOptionPane.showMessageDialog(rootPane, "Tên khách hàng trống");
//			TFtenkh.requestFocus();
//		}
//
//		String phone = TFsdt.getText();
//		if (phone.length() != 10) {
//			JOptionPane.showMessageDialog(null, "Số điện thoại phải có 10 số.");
//			return false;
//		}
//		if (phone.charAt(0) != '0') {
//			JOptionPane.showMessageDialog(null, "Số điện thoại phải bắt đầu bằng số 0.");
//			return false;
//		}
//		for (int i = 0; i < phone.length(); i++) {
//			if (!Character.isDigit(phone.charAt(i))) {
//				JOptionPane.showMessageDialog(null, "Số điện thoại chỉ chứa các ký tự số.");
//				return false;
//			}
//		}
//		String makh = TFmakh.getText();
//		for (int i = 0; i < makh.length(); i++) {
//			if (!Character.isDigit(makh.charAt(i))) {
//				JOptionPane.showMessageDialog(null, "Mã khách hàng chỉ chứa các ký tự số.");
//				return false;
//			}
//		}
//		return true;
//
//	}

	 private void btnluuActionPerformed(java.awt.event.ActionEvent evt) {
		 	try {
		 		HoaDonDAL goiham = new HoaDonDAL();					
				HoaDon hd = new HoaDon();
				HoaDonDAL hdDAL = new HoaDonDAL();
				boolean flag = false;
				if(TFmakh.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPane, "Chưa nhận diện được khách hàng, hãy nhập tên khách hàng và nhấn enter!");
					TFtenkh.requestFocus();
				} else if (TFngay.getDate() == null) {
					JOptionPane.showMessageDialog(rootPane, "Ngày chưa hợp lệ");
					TFngay.requestFocus();
				} else {
					if (add) {					
						hd.setMaHD(TFmahd.getText());
						hd.setMaNV(TFmanv.getText());
						hd.setMaKH(TFmakh.getText());
						hd.setTongTien("0");
						hd.setTongTienSauKM("0");
						hd.setKhuyenMai("KM1");
						java.util.Date dateFromUtil = TFngay.getDate();
						java.sql.Date dateFromSql = new java.sql.Date(dateFromUtil.getTime());
						hd.setThoiDiemLap(formatDateToString(dateFromSql));
						boolean kiemtra = goiham.themhoadon(hd, "themhoadon", null);
						if (kiemtra) {
							JOptionPane.showMessageDialog(rootPane, "Thêm hóa đơn");
							add = false;
						}
					}
					ChiTietHoaDonDAL hdctdal = new ChiTietHoaDonDAL();
					ChiTietHoaDon hdct = new ChiTietHoaDon();
	
					hdct.setMaHd(TFmahd.getText());
					hdct.setMaSp(TFmahh.getText());
					hdct.setSoLuong(TFsoluong.getText());
					hdct.setThanhTien(TFthanhtien.getText());
					if(TFsoluong.getText().equals("")) {
						JOptionPane.showMessageDialog(rootPane, "Số lượng rỗng");
					} else {						
						boolean kiemtra2 = hdctdal.themhoadon(hdct, "themhoadon");
						int soluong = Integer.parseInt(TFsoluong.getText());
						if (kiemtra2 && soluong > 0) {
							KhachHang kh=new KhachHang();
							KhuyenMai km=new KhuyenMai();
							HoaDonDAL hdd=new HoaDonDAL();					
							String mucgiam=hdd.getMucGiam(TFmagiam.getText());
							int tongtien1 = Integer.parseInt(TFtongtien.getText());
			            	int giatri=Integer.parseInt(mucgiam);
			            	int thanhtien2= Integer.parseInt(TFthanhtien.getText());
						    tongtien1 += thanhtien2;
						    TFtongtien.setText(String.valueOf(tongtien1));
			            	int tongtien=Integer.parseInt(TFtongtien.getText());
			            	int giamgia=giatri*tongtien/100;
			            	TFgiamgia.setText(""+giamgia);
							JOptionPane.showMessageDialog(rootPane, "Thêm thành công");
							hiennut();
							hientext();
						} else {
							JOptionPane.showMessageDialog(rootPane, "Thêm thất bại");
						}
						hienthisanpham("hien thi");
					}
				}
			} catch (SQLException ex) {
				Logger.getLogger(HoaDon1.class.getName()).log(Level.SEVERE, null, ex);
	
			}	 
	}

	public void start() {

	}

	private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
		setVisible(false);
		if(taiKhoan.getQuyen().equals("RL2")) {
				QuanLyHome hnv = new QuanLyHome();
				hnv.setLocationRelativeTo(null);
				hnv.setVisible(true);
			}
			if(taiKhoan.getQuyen().equals("RL3")) {
				NhanVienBanHangHome hnv = new NhanVienBanHangHome();
				hnv.setLocationRelativeTo(null);
				hnv.setVisible(true);
			}
	}

	private void TFtienkhachActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

//	private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {
//
//		int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa Hóa đơn này?", "Confirmation",
//				JOptionPane.YES_NO_OPTION);
//		if (confirmed == JOptionPane.YES_OPTION) {
//			ChiTietHoaDonDAL deletehdct;
//			HoaDonDAL deletehd;
//			try {
//				deletehd = new HoaDonDAL();
//				deletehdct = new ChiTietHoaDonDAL();
//				if (deletehdct.xoaHoaDon(TFmahd.getText()) && deletehd.xoaHoaDon(TFmahd.getText())) {
//					JOptionPane.showMessageDialog(rootPane, "Xóa hóa đơn thành công!");
//					hienthisanpham("hien thi");
//					hiennut();
//					annut();
//					this.lbstatus.setText("Xóa đơn thành công");
//				}
//
//			} catch (SQLException ex) {
//				Logger.getLogger(HoaDon1.class.getName()).log(Level.SEVERE, null, ex);
//			}
//
//		}
//
//	}

	private void TFmahdActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void CBtenhhMouseClicked(java.awt.event.MouseEvent evt) {

	}

	private void CBtenhhActionPerformed(java.awt.event.ActionEvent evt) {
		try {

			SanPhamDAL sanphamDAL = new SanPhamDAL();
			String tensp = (String) CBtenhh.getSelectedItem();

			String ma = sanphamDAL.getma(tensp);
			TFmahh.setText (ma);
			int giatien = sanphamDAL.getgia(tensp);
			TFgia.setText("" + giatien);
			TFsoluong.setText("");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void btnthemsanpham2ActionPerformed(java.awt.event.ActionEvent evt) {
		try {

			annut();
			antext();
			addsp = true;
			hienthisanpham("them");
			btnluu.setEnabled(true);
			TFsoluong.setEnabled(true);
			CBtenhh.setEnabled(true);
			TFsoluong.setText("");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

//	private void btnthanhtoanActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
//
//		hiennut();
//		hientext();
//		TFdiemthuong.setEnabled(false);
//		 int choice = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn dùng điểm thưởng để giảm giá không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
//
//	        if (choice == JOptionPane.YES_OPTION) {
//	        	
//	        	KhachHangDAL khachhang = null;
//				try {
//					khachhang = new KhachHangDAL();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//	    		int diemthuong = khachhang.getdiemthuong(TFmakh.getText());
//	    		TFdiemthuong.setText(""+diemthuong);
//	    		TFdiemthuong.addKeyListener(new KeyAdapter() {
//	    		    public void keyReleased(KeyEvent e) {
//	    		        if (!TFdiemthuong.getText().isEmpty()) {
//	    		            int diemthuong2 = Integer.parseInt(TFdiemthuong.getText());
//	    		            float giamgia=0;
//	    		            
//	    		            			// Gán giá trị ban đầu cho giảm giá là số điểm thưởng
//	    		            
//	    		            if (diemthuong2 >= 1) {
//	    		                // Sử dụng điểm thưởng để áp dụng giảm giá
//	    		            	 giamgia=Float.parseFloat(TFdiemthuong.getText());
//	    		                giamgia = diemthuong2; // Cập nhật giá trị giảm giá tương ứng với số điểm thưởng
//	    		                TFgiamgia.setText(""+giamgia);
//	    		                float giamgia3=Float.parseFloat(TFgiamgia.getText());
//	    		                float tongtien=Float.parseFloat(TFtongtien.getText());
//	    		                float tiencuoi=0;
//	    		                if(giamgia3>tongtien)
//	    		                {
//	    		                	TFtong.setText(""+tiencuoi);
//	    		                }
//	    		                else
//	    		                {
//	    		                	tiencuoi=tongtien-giamgia3;
//	    		                	TFtong.setText(""+tiencuoi);
//	    		                	
//	    		                }
//	    		                
//	    		            } else {
//	    		                // Nếu số điểm thưởng ít hơn 1, không áp dụng giảm giá
//	    		                giamgia = 0;
//	    		                TFgiamgia.setText(""+giamgia);
//	    		           
//	    		        }
//	    		            
//	    		    }
//	    		    }});
//	    		
//	    		TFtienkhach.setEnabled(true);
//	    		float tongtien = Float.parseFloat(TFtong.getText());
//	    		float tienkhach = Float.parseFloat(TFtienkhach.getText());
//	    		if (tienkhach < tongtien) {
//	    			JOptionPane.showMessageDialog(rootPane, "Số tiền không đủ để thực hiện giao dịch");
//	    		} 
//	    			else {
//	    			float tienthoilai = tienkhach - tongtien;
//	    			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
//
//	    			// Format the tienthoilai value as currency
//	    			String formattedTienthoilai = currencyFormat.format(tienthoilai);
//	    			TFtienthoi.setText(String.valueOf(tienthoilai));
//	    			this.lbstatus.setText("Thanh toán thành công");
//	    		
//	    		
//	    			}} 
//	    		else {
//	    			float tongtien2 = Float.parseFloat(TFtongtien.getText());
//	    			float tienkhach = Float.parseFloat(TFtienkhach.getText());
//	    			if (tienkhach < tongtien2) {
//	    				JOptionPane.showMessageDialog(rootPane, "Số tiền không đủ để thực hiện giao dịch");
//	    			} else {
//	    				float tienthoilai2 = tienkhach - tongtien2;
//	    				NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
//
//	    				// Format the tienthoilai value as currency
//	    				String formattedTienthoilai = currencyFormat.format(tienthoilai2);
//	    				TFtienthoi.setText(String.valueOf(tienthoilai2));
//	    				this.lbstatus.setText("Thanh toán thành công");
//	    				jButton6.setEnabled(true);
//	        }}
////	        hiennut();
////	        hientext();
////	        float tongtien = Float.parseFloat(TFtong.getText());
////	        float tienkhach = Float.parseFloat(TFtienkhach.getText());
////	        if (tienkhach < tongtien) {
////	            JOptionPane.showMessageDialog(rootPane, "Số tiền không đủ để thực hiện giao dịch");
////	        } else {
////	            float tienthoilai = tienkhach - tongtien;
////	            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
////
////	         // Format the tienthoilai value as currency
////	         String formattedTienthoilai = currencyFormat.format(tienthoilai);
////	            TFtienthoi.setText(String.valueOf(tienthoilai));
////	            this.lbstatus.setText("Thanh toán thành công");
////	            jButton6.setEnabled(true);
////	        }
//
//	}

	private void TFdiemthuongActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void TFgiamgiaActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void TFthanhtienKeyReleased(java.awt.event.KeyEvent evt) {

	}

//	public float tongtien=0;
//	private int soluongtruocdo=0;
//	    private void TFsoluongKeyReleased(java.awt.event.KeyEvent evt) {                                      
//	      String str = TFsoluong.getText();
//	if (!str.isEmpty()) {
//	    try {
//	        int soluong = Integer.parseInt(str);
//	      soluongtruocdo=soluong;
//	        try {
//	            HoaDonDAL hdd=new HoaDonDAL();
//	            int tonkho=hdd.getSoLuongTonKho(TFmahh.getText());
//	            if(soluong>tonkho)
//	            {
//	                JOptionPane.showMessageDialog(rootPane, "Số lượng tồn kho không đủ!còn lại:"+tonkho);
//	            }
//	            else
//	            {
//	                 float gia = Float.parseFloat(TFgia.getText());
//	        float thanhtien = soluong * gia;
//	        TFthanhtien.setText(String.valueOf(thanhtien));
//	         float thanhtien2= Float.parseFloat(TFthanhtien.getText());
//	        
//	       tongtien += thanhtien2;
//	    TFtongtien.setText(String.valueOf(tongtien));
//	            }
//	        } catch (SQLException ex) {
//	            Logger.getLogger(HoaDon1.class.getName()).log(Level.SEVERE, null, ex);
//	        }
//	      
//	      
//	    } catch (NumberFormatException e) {
//	       
//	        System.out.println("Số lượng không hợp lệ!");
//	    }
//	} else {
//	    // Xử lý trường hợp chuỗi đầu vào rỗng
//	    System.out.println("Chưa nhập số lượng!");
//	}
//	        float value = Float.parseFloat(TFtongtien.getText());
//	        int diemthuong = 0;
//	        if (value >= 50000) {
//	            int bonus =  (int) (value / 50000);
//	            diemthuong += bonus;
//	            TFdiemthuong.setText(String.valueOf(diemthuong));
//	        }
//	        int value2 = 0;
//	        if(!TFdiemthuong.getText().isEmpty() &&!TFdiemthuong.getText().isBlank()) {
//	        	 value2 = Integer.parseInt(TFdiemthuong.getText());
//	   	}
////	        int value2 = Integer.parseInt(TFdiemthuong.getText());
//	        float giamgia = 0;
//	        if (value2 >= 5 && value2 < 10) {
//	            giamgia = 25000;
//	        } else if (value2 >= 10 && value2 < 15) {
//	            giamgia = 50000;
//	        } else if (value2 >= 15) {
//	            giamgia = 100000;
//
//	        }
//	        TFgiamgia.setText(String.valueOf(giamgia));
//	        float value3 = Float.parseFloat(TFgiamgia.getText());
//	        float value4 = Float.parseFloat(TFtongtien.getText());
//	        float tong = value4 - value3;
//	        TFtong.setText(String.valueOf(tong));
//	    }                      

	
 
	private void TFtongtienKeyReleased(java.awt.event.KeyEvent evt) {
		int tongtien=Integer.parseInt(TFtongtien.getText());
		int giamgia=Integer.parseInt(TFgiamgia.getText());
		int tong=tongtien-giamgia;
  	TFtong.setText(String.valueOf(tong));
	}

	private void TFgiamgiaKeyReleased(java.awt.event.KeyEvent evt) {
	
	}

	private void TFtongKeyReleased(java.awt.event.KeyEvent evt) {

	}

	private void TFtongActionPerformed(java.awt.event.ActionEvent evt) {

	}

	/*
	 * private void jTable1AncestorAdded(javax.swing.event.AncestorEvent evt) {
	 * 
	 * }
	 */

	/*
	 * private void btnxoasanphamActionPerformed(java.awt.event.ActionEvent evt) {
	 * int Click = JOptionPane.showConfirmDialog(null,
	 * "Bạn có muốn xóa sản phẩm hay không?", "Thông Báo", 2); if (Click ==
	 * JOptionPane.YES_OPTION) { ChiTietHoaDonDAL hdd = null; try { hdd = new
	 * ChiTietHoaDonDAL(); DefaultTableModel model = (DefaultTableModel)
	 * jTable1.getModel(); int selectedRowIndex = jTable1.getSelectedRow(); Object
	 * value = jTable1.getValueAt(selectedRowIndex, 1); // Lấy giá trị của cột được
	 * chọn
	 * 
	 * 
	 * 
	 * if (selectedRowIndex != -1) { model.removeRow(selectedRowIndex); }
	 * hdd.xoaSanPham(value.toString(), TFmahd.getText()); } catch (SQLException ex)
	 * { Logger.getLogger(HoaDon1.class.getName()).log(Level.SEVERE, null, ex); }
	 * 
	 * this.lbstatus.setText("Xóa sản phẩm thành công!");
	 * 
	 * } }
	 */

	private void TFtongtienCaretUpdate(javax.swing.event.CaretEvent evt) {

	}

	/*
	 * private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) { try {
	 * XuatHoaDon xhd = new XuatHoaDon(); //java.util.Date dayy = TFngay.getDate();
	 * 
	 * //java.sql.Date dateFromSql = new java.sql.Date(dayy.getTime());
	 * //xhd.exportToPDF(jTable1,TFtienkhach.getText(),TFtienthoi.getText(),TFmahh.
	 * getText() ,TFtenkh.getText(),formatDateToString(dateFromSql));
	 * 
	 * PhieuXuatDAL phieuxuatDAL = new PhieuXuatDAL(); int lastMaPX =
	 * phieuxuatDAL.getLastMaPX(); //TFmapx.setText("" + (lastMaPX + 1)); } catch
	 * (SQLException ex) {
	 * Logger.getLogger(HoaDon1.class.getName()).log(Level.SEVERE, null, ex); }
	 * PhieuXuat px=new PhieuXuat(); try { PhieuXuatDAL goiham= new PhieuXuatDAL();
	 * if(addpx) { px.setMaNV(Integer.parseInt(LBmanv.getText())); //java.util.Date
	 * dateFromUtil2 = TFngay.getDate();
	 * 
	 * //java.sql.Date dateFromSql2 = new java.sql.Date(dateFromUtil2.getTime());
	 * 
	 * //px.setThoiDiemLap(formatDateToString(dateFromSql2)); boolean
	 * kiemtra5=goiham.themhoadon(px, "themhoadon"); if(kiemtra5) { hiennut();
	 * hientext(); addpx=true;
	 * 
	 * }
	 * 
	 * } boolean kiemtra6 = false; ChiTietPhieuXuat ctpx=new ChiTietPhieuXuat();
	 * ChiTietPhieuXuatDAL goiham2=new ChiTietPhieuXuatDAL();
	 * 
	 * 
	 * int rowCount = jTable1.getRowCount(); // lấy index của hàng được chọn for(int
	 * i =0;i<rowCount;i++) { String masp = jTable1.getModel().getValueAt(i,
	 * 1).toString(); // lấy giá trị của cột 2 String soluong =
	 * jTable1.getModel().getValueAt(i, 2).toString(); // lấy giá trị của cột 3 //
	 * ctpx.setMaPX(Integer.parseInt(TFmapx.getText())); //java.util.Date
	 * dateFromUtil2 = TFngay.getDate();
	 * 
	 * //java.sql.Date dateFromSql2 = new java.sql.Date(dateFromUtil2.getTime());
	 * 
	 * //ctpx.setNgayXuat(formatDateToString(dateFromSql2));
	 * ctpx.setMaSP(Integer.parseInt(masp));
	 * ctpx.setSoLuongBan(Integer.parseInt(soluong));
	 * kiemtra6=goiham2.themhoadon(ctpx,"themhoadon"); }
	 * 
	 * 
	 * 
	 * 
	 * if(kiemtra6) { JOptionPane.showMessageDialog(rootPane,
	 * "Xuất Đơn Thành Công");
	 * 
	 * } else JOptionPane.showMessageDialog(rootPane, "Xuất Đơn Thất Bại");
	 * 
	 * } catch (SQLException ex) {
	 * Logger.getLogger(HoaDon1.class.getName()).log(Level.SEVERE, null, ex); }
	 * hiennut(); hientext(); this.lbstatus.setText("Xuất Đơn Thành Công"); }
	 */
	public void reset() {
		hiennut();
		hientext();
		TFtenkh.setText("");
		TFsdt.setText("");
		TFmakh.setText("");
		// TFngay.setToolTipText("");
		TFsoluong.setText("");
		TFtongtien.setText("");
		TFgiamgia.setText("");
		TFthanhtien.setText("");
		TFmahd.setText("");
		TFdiemthuong.setText("");
		TFtienkhach.setText("");
		TFtienthoi.setText("");
		TFmagiam.setText("");
		TFmahh.setText("");
		TFgia.setText("");
		TFtong.setText("0");
		CBtenhh.setSelectedIndex(0);
		TFngay.setToolTipText("");
		}
	/*
	 * private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) { reset();
	 * DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
	 * model.setRowCount(0); for (int i = 0; i < model.getRowCount(); i++) { for
	 * (int j = 0; j < model.getColumnCount(); j++) { model.setValueAt("", i, j); }
	 * } }
	 */

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("FlatLaf Dark".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(HoaDon1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(HoaDon1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(HoaDon1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(HoaDon1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(() -> {
			try {
				new HoaDon1().setVisible(true);
			} catch (SQLException ex) {
				Logger.getLogger(HoaDon1.class.getName()).log(Level.SEVERE, null, ex);
			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JComboBox<String> CBtenhh;
	private javax.swing.JLabel LBgio;
	private javax.swing.JLabel LBmanv;
	private javax.swing.JTextField TFdiemthuong;
	private javax.swing.JTextField TFgia;
	private javax.swing.JTextField TFgiamgia;
	private javax.swing.JTextField TFmahd;
	private javax.swing.JTextField TFmahh;
	private javax.swing.JTextField TFmakh;
	// private javax.swing.JTextField TFmapx;
	// private com.toedter.calendar.JDateChooser TFngay;
	private javax.swing.JTextField TFsdt;
	private javax.swing.JTextField TFsoluong;
	private javax.swing.JTextField TFtenkh;
	private javax.swing.JTextField TFthanhtien;
	private javax.swing.JTextField TFtienkhach;
	private javax.swing.JTextField TFtienthoi;
	private javax.swing.JTextField TFtong;
	private javax.swing.JTextField TFtongtien;
	private javax.swing.JButton btnluu;
	private javax.swing.JButton btnsua;
	private javax.swing.JButton btnthanhtoan;
	private javax.swing.JButton btnthem;
	//private javax.swing.JButton btnthemsanpham2;
	private javax.swing.JButton btngg;
	private javax.swing.JButton jButton6;
	private javax.swing.JButton jButton7;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel17;
	private javax.swing.JLabel jLabel18;
	private javax.swing.JLabel jLabel19;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel20;
	private javax.swing.JLabel jLabel21;
	// private javax.swing.JLabel jLabel22;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	// private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JLabel lbstatus;
	private JButton btnThe;
	private JTextField TFmanv;
	private JScrollPane scrollPane;
	private JTable jtable1;
	private DefaultTableModel model;
	private JLabel lblMGim;
	private JTextField TFmagiam;
	private JButton btnxacnhan;

	// End of variables declaration
	public void hienthihoadon(String condition) throws SQLException {
		HoaDonDAL HdDal = new HoaDonDAL();
		HoaDon hd = new HoaDon();
		ArrayList<HoaDon> arrHd = new ArrayList<HoaDon>();
		if (condition == "hien thi") {

			arrHd = HdDal.docHoaDon("dochoadon", null);
		}
		if (condition == "hienlen") {
			HoaDonDAL hdd = new HoaDonDAL();

			int mahd = hdd.getmahd(1);

			TFmahd.setText("" + mahd);
			int makh = hdd.getmakh(mahd);
			TFmakh.setText("" + makh);

		}
	}

	public void hienthisanpham(String condition) throws SQLException {
		SanPhamDAL SpDal = new SanPhamDAL();
		HoaDonDAL HdDal = new HoaDonDAL();
		ArrayList<SanPham> arrSp = new ArrayList<SanPham>();
		ArrayList<HoaDon> arrHd = new ArrayList<HoaDon>();
		if (condition == "them") {
			arrSp = SpDal.docSanPham("docsanpham", null);
			DefaultComboBoxModel combo = new DefaultComboBoxModel();
			CBtenhh.setModel(combo);
			for (SanPham tenSp : arrSp) {
				combo.addElement(tenSp.getTenSp());

			}
		}
		
		
		if (condition == "hien thi") {
		    String masanpham = TFmahh.getText();
		    int soluong = Integer.parseInt(TFsoluong.getText());
		    int thanhtien = Integer.parseInt(TFthanhtien.getText());
		    
		    boolean found = false;

		    for (int i = 0; i < model.getRowCount(); i++) {
		        String productId = (String) model.getValueAt(i, 1);
		        if (productId.equals(masanpham)) {
		            int oldQuantity = (int) model.getValueAt(i, 2);
		            model.setValueAt(oldQuantity + soluong, i, 2);
		            int thanhtien2=(int) model.getValueAt(i, 3);
		            model.setValueAt(thanhtien2 + thanhtien, i, 3);
		            found = true;
		        }
		    }

		    if (!found) {
		    	System.out.print("check");
		        String tensanpham = (String) CBtenhh.getSelectedItem();
		        Object[] row = {tensanpham, masanpham, soluong, thanhtien};
		        model.addRow(row);
		}
		                }
		   
	
}}