/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import java.util.Calendar;
import java.util.GregorianCalendar;
import DTO.HoaDon;
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

import BLL.XuatHoaDon;

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
import javax.swing.JComboBox;
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
import net.sf.jasperreports.components.table.Cell;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
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

public class HoaDon1 extends javax.swing.JFrame {

	public String formatDateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateFormat.format(date);
		return dateString;
	}

	/**
	 * Creates new form HoaDon1
	 */
	TaiKhoan taiKhoan = ShareDAta.taiKhoan;

	public HoaDon1() throws SQLException {
		initComponents();
		/*
		 * DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
		 * model.setRowCount(0);
		 */
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

	public void annut() {
		btnxoa.setEnabled(false);
		btnsua.setEnabled(false);
		btnluu.setEnabled(false);
		btnthanhtoan.setEnabled(false);
		btnthem.setEnabled(true);
		btnthemsanpham2.setEnabled(false);
		btnxoasanpham.setEnabled(false);
		jButton6.setEnabled(false);

	}

	public void hiennut() {
		btnxoa.setEnabled(true);
		btnsua.setEnabled(true);
		btnluu.setEnabled(true);
		btnthanhtoan.setEnabled(true);
		btnthem.setEnabled(true);
	}

	public void hientext() {
		TFtenkh.setEnabled(true);
		TFsdt.setEnabled(true);
		TFmakh.setEnabled(true);
		// TFngay.setEnabled(true);
		TFsoluong.setEnabled(true);
		TFmahh.setEnabled(true);
		TFthanhtien.setEnabled(true);
		TFgia.setEnabled(true);
		TFmahh.setEnabled(true);
		TFtongtien.setEnabled(true);
		TFdiemthuong.setEnabled(true);
		TFtienkhach.setEnabled(true);
		TFgiamgia.setEnabled(true);
		TFtienthoi.setEnabled(true);
		CBtenhh.setEnabled(true);
	}

	public void antext() {
		CBtenhh.setEnabled(false);
		TFtenkh.setEnabled(false);
		TFsdt.setEnabled(false);
		TFmakh.setEnabled(false);
		// TFngay.setEnabled(false);
		TFsoluong.setEnabled(false);
		TFmahh.setEnabled(false);
		TFthanhtien.setEnabled(false);
		TFgia.setEnabled(false);
		TFmahh.setEnabled(false);
		TFdiemthuong.setEnabled(false);
		TFtienkhach.setEnabled(false);
		TFgiamgia.setEnabled(false);
		TFtienthoi.setEnabled(false);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		btnTimsdt = new javax.swing.JButton();
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		TFtenkh = new javax.swing.JTextField();
		TFsdt = new javax.swing.JTextField();
		// jLabel5 = new javax.swing.JLabel();
		TFmakh = new javax.swing.JTextField();
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
		TFtongtien.setEnabled(false);
		btnxoa = new javax.swing.JButton();
		btnsua = new javax.swing.JButton();
		btnluu = new javax.swing.JButton();
		jButton6 = new javax.swing.JButton();
		btnthem = new javax.swing.JButton();
		jLabel13 = new javax.swing.JLabel();
		jLabel14 = new javax.swing.JLabel();
		jLabel15 = new javax.swing.JLabel();
		jLabel16 = new javax.swing.JLabel();
		TFtienkhach = new javax.swing.JTextField();
		TFtienthoi = new javax.swing.JTextField();
		TFdiemthuong = new javax.swing.JTextField();
		TFgiamgia = new javax.swing.JTextField();
		btnthanhtoan = new javax.swing.JButton();
		TFmahd = new javax.swing.JTextField();
		btnthemsanpham2 = new javax.swing.JButton();
		jLabel21 = new javax.swing.JLabel();
		TFtong = new javax.swing.JTextField();
		btnxoasanpham = new javax.swing.JButton();
		jPanel5 = new javax.swing.JPanel();
		jPanel6 = new javax.swing.JPanel();
		jButton7 = new javax.swing.JButton();
		jPanel7 = new javax.swing.JPanel();
		jLabel17 = new javax.swing.JLabel();
		jLabel18 = new javax.swing.JLabel();
		jLabel20 = new javax.swing.JLabel();
		LBgio = new javax.swing.JLabel();
		jLabel19 = new javax.swing.JLabel();
		jLabel19.setText(taiKhoan.getQuyen() + "");

		LBmanv = new javax.swing.JLabel();
		LBmanv.setText(taiKhoan.getMaNV() + "");
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTable2 = new javax.swing.JTable();
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

		btnTimsdt.setText("Tìm sdt");
		// jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		// jLabel5.setText("Ngày Đặt:");

		TFmakh.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					TFmakhActionPerformed(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnxoa.setEnabled(true);
		// TFngay.setMinSelectableDate(new java.util.Date(-62135791093000L));

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
								.addGroup(jPanel2Layout.createSequentialGroup().addGap(10).addComponent(jLabel3,
										GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
								.addGroup(jPanel2Layout.createSequentialGroup().addGap(4).addComponent(jLabel2,
										GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
								.addComponent(TFtenkh, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
								.addComponent(TFsdt, 173, 173, 173))
						.addGap(18)
						.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
								.addGroup(jPanel2Layout.createSequentialGroup()
										.addComponent(jLabel4, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(TFmakh, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
										.addGap(39))
								.addGroup(jPanel2Layout.createSequentialGroup()
										.addComponent(btnTimsdt, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
										.addGap(42)
										// .addComponent(TFngay, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
										.addGap(1)))
						.addGap(6)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel2Layout
				.createSequentialGroup().addGap(16)
				.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(TFtenkh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addComponent(TFmakh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(TFsdt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnTimsdt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addGroup(jPanel2Layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
								.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addComponent(jLabel3,
										Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 33,
										GroupLayout.PREFERRED_SIZE))))
				// .addComponent(TFngay, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
				// GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
				.addContainerGap()));
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
			public void keyReleased(java.awt.event.KeyEvent evt) {
				TFsoluongKeyReleased(evt);
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

		btnxoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		btnxoa.setIcon(new ImageIcon(HoaDon1.class.getResource("/GUI/Image/Delete.png"))); // NOI18N
		btnxoa.setText("Hóa Đơn");
		btnxoa.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnxoaActionPerformed(evt);
			}
		});

		btnsua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		btnsua.setIcon(new ImageIcon(HoaDon1.class.getResource("/GUI/Image/Refresh-icon.png"))); // NOI18N
		btnsua.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// btnsuaActionPerformed(evt);
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
				TFdiemthuongKeyReleased(evt);
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
				btnthanhtoanActionPerformed(evt);
			}
		});

		TFmahd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				TFmahdActionPerformed(evt);
			}
		});

		btnthemsanpham2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		btnthemsanpham2.setIcon(new ImageIcon(HoaDon1.class.getResource("/GUI/Image/Add.png"))); // NOI18N
		btnthemsanpham2.setText("Sản Phẩm");
		btnthemsanpham2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnthemsanpham2ActionPerformed(evt);
			}
		});

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

		btnxoasanpham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		btnxoasanpham.setIcon(new ImageIcon(HoaDon1.class.getResource("/GUI/Image/Delete.png"))); // NOI18N
		btnxoasanpham.setText("Sản Phẩm");
		btnxoasanpham.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// btnxoasanphamActionPerformed(evt);
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

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel4Layout
				.createSequentialGroup()
				.addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel4Layout.createSequentialGroup()
								.addComponent(btnthem, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE).addGap(41)
								.addComponent(btnxoa, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
						.addGroup(jPanel4Layout.createSequentialGroup().addGap(16)
								.addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
										.addGroup(jPanel4Layout.createSequentialGroup()
												.addComponent(TFmahd, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
												.addGap(62).addComponent(jLabel14, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(jPanel4Layout.createSequentialGroup()
												.addComponent(jLabel11, GroupLayout.PREFERRED_SIZE, 128,
														Short.MAX_VALUE)
												.addGap(54).addComponent(jLabel12, GroupLayout.DEFAULT_SIZE, 132,
														Short.MAX_VALUE)))))
				.addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel4Layout.createSequentialGroup().addGap(144)
								.addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
										.addComponent(TFdiemthuong, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 134,
												Short.MAX_VALUE)
										.addComponent(TFtongtien, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 134,
												Short.MAX_VALUE))
								.addGap(34))
						.addGroup(jPanel4Layout.createSequentialGroup().addGap(18)
								.addComponent(btnsua, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnthemsanpham2, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)))
				.addGap(18).addComponent(btnxoasanpham, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE).addGap(24)
				.addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel4Layout.createSequentialGroup()
								.addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
										.addComponent(jLabel13, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
										.addComponent(jLabel15, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
								.addGap(34)
								.addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
										.addComponent(TFtienkhach, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
										.addComponent(TFgiamgia, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
								.addGap(38)
								.addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
										.addGroup(jPanel4Layout.createSequentialGroup()
												.addComponent(jLabel16, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
												.addGap(109).addComponent(TFtienthoi, GroupLayout.DEFAULT_SIZE, 108,
														Short.MAX_VALUE))
										.addGroup(jPanel4Layout.createSequentialGroup()
												.addComponent(jLabel21, GroupLayout.PREFERRED_SIZE, 248,
														Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(TFtong, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))))
						.addGroup(jPanel4Layout.createSequentialGroup().addGap(15)
								.addComponent(btnluu, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnthanhtoan, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE).addGap(18)
								.addComponent(jButton6, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnThe, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE).addGap(15)))
				.addGap(8)));
		jPanel4Layout
				.setVerticalGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel4Layout.createSequentialGroup().addGap(21).addGroup(jPanel4Layout
								.createParallelGroup(Alignment.BASELINE).addComponent(jLabel11).addComponent(jLabel12)
								.addComponent(TFtongtien, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel13, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel16, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(TFtienkhach, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(TFtienthoi, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
								.addGap(32)
								.addGroup(jPanel4Layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel14)
										.addComponent(jLabel15, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(TFdiemthuong, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(
												TFgiamgia, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
										.addComponent(TFmahd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel21).addComponent(TFtong, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel4Layout
										.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
										.addGroup(jPanel4Layout.createParallelGroup(Alignment.TRAILING)
												.addGroup(jPanel4Layout.createParallelGroup(Alignment.BASELINE)
														.addComponent(btnxoa, GroupLayout.PREFERRED_SIZE, 51,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(btnthem, GroupLayout.PREFERRED_SIZE, 54,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(btnluu, GroupLayout.PREFERRED_SIZE, 51,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(btnthemsanpham2, GroupLayout.PREFERRED_SIZE, 54,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(btnxoasanpham, GroupLayout.PREFERRED_SIZE, 54,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(btnthanhtoan, GroupLayout.PREFERRED_SIZE, 54,
																GroupLayout.PREFERRED_SIZE))
												.addComponent(btnsua, GroupLayout.PREFERRED_SIZE, 51,
														GroupLayout.PREFERRED_SIZE))
										.addContainerGap())
										.addGroup(jPanel4Layout.createSequentialGroup().addGap(18)
												.addGroup(jPanel4Layout.createParallelGroup(Alignment.BASELINE)
														.addComponent(jButton6, GroupLayout.PREFERRED_SIZE, 51,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(btnThe, GroupLayout.PREFERRED_SIZE, 51,
																GroupLayout.PREFERRED_SIZE))
												.addContainerGap()))));
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

		javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
		jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel7Layout
				.createSequentialGroup().addContainerGap()
				.addGroup(jPanel7Layout.createParallelGroup(Alignment.TRAILING).addGroup(jPanel7Layout
						.createSequentialGroup()
						.addComponent(jLabel20, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(LBgio, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE).addGap(131))
						.addGroup(jPanel7Layout.createSequentialGroup()
								.addComponent(jLabel17, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jLabel19, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
								.addGap(22))
						.addGroup(jPanel7Layout.createSequentialGroup()
								.addComponent(jLabel18, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(LBmanv, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
								.addGap(70)))
				.addGap(4)));
		jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel7Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel7Layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel17)
								.addComponent(jLabel19, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(jPanel7Layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel18)
								.addComponent(LBmanv))
						.addPreferredGap(ComponentPlacement.UNRELATED).addGroup(jPanel7Layout
								.createParallelGroup(Alignment.BASELINE).addComponent(jLabel20).addComponent(LBgio))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel7.setLayout(jPanel7Layout);

		String[] columnNames_sp = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Thành Tiền" };
		DefaultTableModel model_sp = new DefaultTableModel(columnNames_sp, 0);

		jTable2.setModel(model_sp);
		model_sp.setRowCount(0);
		for (int i = 0; i < 30; i++) {
			Object[] row = new Object[] {};

			model_sp.addRow(row);
		}
		jTable2.setRowHeight(30);

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
		jScrollPane2.setViewportView(jTable2);

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

		String[] columnNames_hd = { "Mã Hóa Đơn", "Nhân Viên", "Khách Hàng", "Tổng Tiền", "Khuyến Mãi",
				"Sau Khuyến Mãi", "Ngày Lập" };
		DefaultTableModel model_hd = new DefaultTableModel(columnNames_hd, 0);

		jTable1.setModel(model_hd);
		model_hd.setRowCount(0);
		for (int i = 0; i < 30; i++) {
			Object[] row = new Object[] {};
			model_hd.addRow(row);
		}
		jTable1.setRowHeight(30);

		jScrollPane1.setViewportView(jTable1);

		lbstatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		lbstatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbstatus.setText("  Trạng Thái");

		JLabel lblNewLabel_hd = new JLabel("HÓA ĐƠN");
		lblNewLabel_hd.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_hd.setFont(new Font("Arial", Font.BOLD, 20));

		JPanel panel_sp = new JPanel();
		JPanel panel_hd = new JPanel();

		GroupLayout gl_panel_hd = new GroupLayout(panel_hd);
		gl_panel_hd.setHorizontalGroup(gl_panel_hd.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel_hd, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
				.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE));
		gl_panel_hd.setVerticalGroup(gl_panel_hd.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_hd.createSequentialGroup()
						.addComponent(lblNewLabel_hd, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)));
		panel_hd.setLayout(gl_panel_hd);

		JLabel lblNewLabel_sp = new JLabel("DANH SÁCH SẢN PHẨM TRONG HÓA ĐƠN");
		lblNewLabel_sp.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_sp.setFont(new Font("Arial", Font.BOLD, 20));

		GroupLayout gl_panel_sp = new GroupLayout(panel_sp);
		gl_panel_sp.setHorizontalGroup(gl_panel_sp.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_sp.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_sp.createParallelGroup(Alignment.LEADING)
								.addComponent(jScrollPane2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 600,
										Short.MAX_VALUE)
								.addComponent(lblNewLabel_sp, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE))));
		gl_panel_sp.setVerticalGroup(gl_panel_sp.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_sp.createSequentialGroup()
						.addComponent(lblNewLabel_sp, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)));
		panel_sp.setLayout(gl_panel_sp);

		JPanel panel_table = new JPanel();

		GroupLayout gl_panel_table = new GroupLayout(panel_table);
		gl_panel_table.setHorizontalGroup(gl_panel_table.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_table
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_table.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_table.createSequentialGroup().addGroup(gl_panel_table.createSequentialGroup()
								.addComponent(panel_sp, GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE).addGap(18)
								.addComponent(panel_hd, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE))))
				.addContainerGap()));
		gl_panel_table.setVerticalGroup(gl_panel_table.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_table
				.createSequentialGroup()
				.addGroup(gl_panel_table.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_sp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_hd, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
				.addContainerGap()));
		panel_table.setLayout(gl_panel_table);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
						.addComponent(jPanel6, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(jPanel7, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE).addGap(0))
						.addGroup(layout.createSequentialGroup()
								.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.TRAILING)
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addGroup(layout.createSequentialGroup().addGap(372)
														.addComponent(lbstatus, GroupLayout.DEFAULT_SIZE, 567,
																Short.MAX_VALUE)
														.addGap(565))
												.addComponent(panel_table))
										.addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, 1504, Short.MAX_VALUE))
								.addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
				.addGap(26)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(jPanel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addComponent(jPanel7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
				.addGap(18)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
						.addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup()
								.addComponent(panel_table, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lbstatus, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))));
		getContentPane().setLayout(layout);

		pack();
	}// </editor-fold>

	private void TFtenkhActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
		KhachHangDAL khd = new KhachHangDAL();
		int makh = khd.getmakh(TFtenkh.getText());
		if (makh == 0) {
			JOptionPane.showMessageDialog(rootPane, "Khong ton tai ten khach hang!");
			btnThe.setEnabled(true);
		} else if (makh != 0) {

			TFmakh.setText(makh + "");
		}
	}

	private void TFsdtActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void TFmakhActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
		try {
			KhachHangDAL khd = new KhachHangDAL();

//         String maText = TFmakh.getText(); 
//         int ma = Integer.parseInt(maText);

			int makh = khd.getmakh(TFtenkh.getText());
			TFtenkh.setText(makh + "");
		} catch (SQLException ex) {
			Logger.getLogger(HoaDon1.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

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

	private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {

		try {

			annut();
			antext();
			add = true;

			hienthisanpham("them");
			btnluu.setEnabled(true);
			TFmahd.setEnabled(false);
			TFtenkh.setEnabled(true);
			TFmakh.setEnabled(true);
			TFsdt.setEnabled(true);
			// TFngay.setEnabled(true);
			TFmahh.setEnabled(true);
			TFsoluong.setEnabled(true);
			TFgia.setEnabled(true);
			TFtienkhach.setEnabled(true);
			CBtenhh.setEnabled(true);
			HoaDonDAL hoadonDAL = new HoaDonDAL();
			int lastMaHD = hoadonDAL.getLastMaHD();
			KhachHangDAL khd = new KhachHangDAL();

			TFmahd.setText("" + (lastMaHD + 1));
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

	public boolean check() throws SQLException {

		if (TFtenkh.getText().isEmpty()) {
			JOptionPane.showMessageDialog(rootPane, "Tên khách hàng trống");
			TFtenkh.requestFocus();
		}

		String phone = TFsdt.getText();
		if (phone.length() != 10) {
			JOptionPane.showMessageDialog(null, "Số điện thoại phải có 10 số.");
			return false;
		}
		if (phone.charAt(0) != '0') {
			JOptionPane.showMessageDialog(null, "Số điện thoại phải bắt đầu bằng số 0.");
			return false;
		}
		for (int i = 0; i < phone.length(); i++) {
			if (!Character.isDigit(phone.charAt(i))) {
				JOptionPane.showMessageDialog(null, "Số điện thoại chỉ chứa các ký tự số.");
				return false;
			}
		}
		String makh = TFmakh.getText();
		for (int i = 0; i < makh.length(); i++) {
			if (!Character.isDigit(makh.charAt(i))) {
				JOptionPane.showMessageDialog(null, "Mã khách hàng chỉ chứa các ký tự số.");
				return false;
			}
		}
		return true;

	}

	private void btnluuActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			HoaDonDAL goiham = new HoaDonDAL();
			btnthemsanpham2.setEnabled(true);
			btnxoasanpham.setEnabled(true);
			btnthanhtoan.setEnabled(true);
			if (check() == true) {
				HoaDon hd = new HoaDon();
				HoaDonDAL hdDAL = new HoaDonDAL();
				if (add) {
					/*
					 * if(!TFdiemthuong.getText().isEmpty() &&!TFdiemthuong.getText().isBlank()) {
					 * hd.setDiemThuong(Integer.parseInt(TFdiemthuong.getText()));
					 * hd.setTienTra(Float.parseFloat(TFtong.getText()));
					 * hd.setMucGiam(Float.parseFloat(TFgiamgia.getText())); }
					 * hd.setMaHd(Integer.parseInt(TFmahd.getText()));
					 * 
					 * hd.setGio(LBgio.getText()); hd.setMaKh(Integer.parseInt(TFmakh.getText()));
					 * hd.setMaNv(Integer.parseInt(LBmanv.getText()));
					 */
					// java.util.Date dateFromUtil = TFngay.getDate();

					// java.sql.Date dateFromSql = new java.sql.Date(dateFromUtil.getTime());

					// hd.setThoiDiem(formatDateToString(dateFromSql));

					boolean kiemtra = goiham.themhoadon(hd, "themhoadon", null);
					if (kiemtra) {

						hiennut();
						hientext();
						btnthanhtoan.setEnabled(true);
						hienthisanpham("hien thi");
						add = false;

					}

				}
				if (addsp) {
					hienthisanpham("hien thi");
					addsp = false;
				}
				ChiTietHoaDonDAL hdctdal = new ChiTietHoaDonDAL();
				ChiTietHoaDon hdct = new ChiTietHoaDon();

				hdct.setMaHd(Integer.parseInt(TFmahd.getText()));
				hdct.setMaSp(Integer.parseInt(TFmahh.getText()));
				hdct.setSl(Integer.parseInt(TFsoluong.getText()));
				boolean kiemtra2 = hdctdal.themhoadon(hdct, "themhoadon");
				int soluong = Integer.parseInt(TFsoluong.getText());
				if (kiemtra2 && soluong > 0) {
					JOptionPane.showMessageDialog(rootPane, "Thêm thành công");

				} else {
					JOptionPane.showMessageDialog(rootPane, "Thêm thất bại");
				}
				hiennut();
				hientext();
				btnthanhtoan.setEnabled(true);
				this.lbstatus.setText("Thêm đơn thành công");

			}

		} catch (SQLException ex) {
			Logger.getLogger(HoaDon1.class.getName()).log(Level.SEVERE, null, ex);

		}
	}

	public void start() {

	}

	private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
		QuanLyHome home = new QuanLyHome();
		this.setVisible(false);
		home.setVisible(true);
	}

	private void TFtienkhachActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {

		int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa Hóa đơn này?", "Confirmation",
				JOptionPane.YES_NO_OPTION);
		if (confirmed == JOptionPane.YES_OPTION) {
			ChiTietHoaDonDAL deletehdct;
			HoaDonDAL deletehd;
			try {
				deletehd = new HoaDonDAL();
				deletehdct = new ChiTietHoaDonDAL();
				if (deletehdct.xoaHoaDon(TFmahd.getText()) && deletehd.xoaHoaDon(TFmahd.getText())) {
					JOptionPane.showMessageDialog(rootPane, "Xóa hóa đơn thành công!");
					hienthisanpham("hien thi");
					hiennut();
					annut();
					this.lbstatus.setText("Xóa đơn thành công");
				}

			} catch (SQLException ex) {
				Logger.getLogger(HoaDon1.class.getName()).log(Level.SEVERE, null, ex);
			}

		}

	}

	private void TFmahdActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void CBtenhhMouseClicked(java.awt.event.MouseEvent evt) {

	}

	private void CBtenhhActionPerformed(java.awt.event.ActionEvent evt) {
		try {

			SanPhamDAL sanphamDAL = new SanPhamDAL();
			String tensp = (String) CBtenhh.getSelectedItem();

			int ma = sanphamDAL.getma(tensp);
			TFmahh.setText("" + ma);
			float giatien = sanphamDAL.getgia(tensp);
			TFgia.setText("" + giatien);

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
			TFmahh.setEnabled(true);
			TFsoluong.setEnabled(true);
			TFgia.setEnabled(true);
			TFtienkhach.setEnabled(true);
			CBtenhh.setEnabled(true);
			TFsoluong.setText("");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void btnthanhtoanActionPerformed(java.awt.event.ActionEvent evt) {

		hiennut();
		hientext();
		float tongtien = Float.parseFloat(TFtong.getText());
		float tienkhach = Float.parseFloat(TFtienkhach.getText());
		if (tienkhach < tongtien) {
			JOptionPane.showMessageDialog(rootPane, "Số tiền không đủ để thực hiện giao dịch");
		} else {
			float tienthoilai = tienkhach - tongtien;
			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

			// Format the tienthoilai value as currency
			String formattedTienthoilai = currencyFormat.format(tienthoilai);
			TFtienthoi.setText(String.valueOf(tienthoilai));
			this.lbstatus.setText("Thanh toán thành công");
			jButton6.setEnabled(true);
		}

	}

	private void TFdiemthuongActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void TFgiamgiaActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void TFthanhtienKeyReleased(java.awt.event.KeyEvent evt) {

	}

	public float tongtien = 0;
	private int soluongtruocdo = 0;

	private void TFsoluongKeyReleased(java.awt.event.KeyEvent evt) {
		String str = TFsoluong.getText();
		if (!str.isEmpty()) {
			try {
				int soluong = Integer.parseInt(str);
				soluongtruocdo = soluong;
				try {
					HoaDonDAL hdd = new HoaDonDAL();
					int tonkho = hdd.getSoLuongTonKho(Integer.parseInt(TFmahh.getText()));
					if (soluong > tonkho) {
						JOptionPane.showMessageDialog(rootPane, "Số lượng tồn kho không đủ!còn lại:" + tonkho);
					} else {
						float gia = Float.parseFloat(TFgia.getText());
						float thanhtien = soluong * gia;
						TFthanhtien.setText(String.valueOf(thanhtien));
						float thanhtien2 = Float.parseFloat(TFthanhtien.getText());

						tongtien += thanhtien2;
						TFtongtien.setText(String.valueOf(tongtien));
					}
				} catch (SQLException ex) {
					Logger.getLogger(HoaDon1.class.getName()).log(Level.SEVERE, null, ex);
				}

			} catch (NumberFormatException e) {

				System.out.println("Số lượng không hợp lệ!");
			}
		} else {
			// Xử lý trường hợp chuỗi đầu vào rỗng
			System.out.println("Chưa nhập số lượng!");
		}

		float value = Float.parseFloat(TFtongtien.getText());
		int diemthuong = 0;
		if (value >= 50000) {
			int bonus = (int) (value / 50000);
			diemthuong += bonus;
			TFdiemthuong.setText(String.valueOf(diemthuong));
		}
		int value2 = 0;
		if (!TFdiemthuong.getText().isEmpty() && !TFdiemthuong.getText().isBlank()) {
			value2 = Integer.parseInt(TFdiemthuong.getText());
		}
//        int value2 = Integer.parseInt(TFdiemthuong.getText());
		float giamgia = 0;
		if (value2 >= 5 && value2 < 10) {
			giamgia = 25000;
		} else if (value2 >= 10 && value2 < 15) {
			giamgia = 50000;
		} else if (value2 >= 15) {
			giamgia = 100000;

		}
		TFgiamgia.setText(String.valueOf(giamgia));
		float value3 = Float.parseFloat(TFgiamgia.getText());
		float value4 = Float.parseFloat(TFtongtien.getText());
		float tong = value4 - value3;
		TFtong.setText(String.valueOf(tong));
	}

	private void TFdiemthuongKeyReleased(java.awt.event.KeyEvent evt) {

	}

	private void TFtongtienKeyReleased(java.awt.event.KeyEvent evt) {

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
	private javax.swing.JButton btnTimsdt;
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
	private javax.swing.JButton btnthemsanpham2;
	private javax.swing.JButton btnxoa;
	private javax.swing.JButton btnxoasanpham;
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
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTable jTable2;
	private javax.swing.JLabel lbstatus;
	private JButton btnThe;

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

//        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

//
//        String tensanpham = (String) CBtenhh.getSelectedItem();
//              int masanpham = Integer.parseInt(TFmahh.getText());
//        int soluong = Integer.parseInt(TFsoluong.getText());
//        double thanhtien = Double.parseDouble(TFthanhtien.getText());
//
//        
//        Object[] row = { tensanpham, masanpham, soluong,  thanhtien };
//
//      
//        model.addRow(row);
		/*
		 * DefaultTableModel model = (DefaultTableModel) jTable1.getModel(); if
		 * (condition == "hien thi") { int masanpham =
		 * Integer.parseInt(TFmahh.getText()); int soluong =
		 * Integer.parseInt(TFsoluong.getText()); double thanhtien =
		 * Double.parseDouble(TFthanhtien.getText());
		 * 
		 * boolean found = false;
		 * 
		 * for (int i = 0; i < model.getRowCount(); i++) { int productId = (int)
		 * model.getValueAt(i, 1); if (productId == masanpham) { int oldQuantity = (int)
		 * model.getValueAt(i, 2); model.setValueAt(oldQuantity + soluong, i, 2); double
		 * thanhtien2=(double) model.getValueAt(i, 3); model.setValueAt(thanhtien2 +
		 * thanhtien, i, 3); found = true; break; } }
		 * 
		 * if (!found) { String tensanpham = (String) CBtenhh.getSelectedItem();
		 * Object[] row = {tensanpham, masanpham, soluong, thanhtien};
		 * model.addRow(row); } }
		 */

	}
}
