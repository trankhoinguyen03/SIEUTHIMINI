package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.util.Comparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JToolBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import BLL.SanPhamBLL;
import DTO.LoaiHang;
import DTO.NhaCungCap;
import DTO.NhanVien;
import DTO.SanPham;
import DAL.LoaiHangDAL;
import DAL.NhaCungCapDAL;
import DAL.SanPhamDAL;

import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class KhoGui extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldImg;
	private JTextField textFieldMasp;
	private JTextField textFieldTensp;
	private JTextField textFieldGianhap;
	private JTextField textFieldHansd;
	private JTextField textFieldGiaban;
	private JTable table;
	private JTextField textFieldDonvi;

	/**
	 * Launch the application.
	 */
	
	NhanVien nhanVien = ShareDAta.nhanVien;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KhoGui frame = new KhoGui();
					frame.setVisible(true);
					
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	JComboBox comboBox;
	JLabel lbThemanh = new JLabel();
	File selectedFile;
	ImageIcon icon = new ImageIcon();
	JButton btnCapNhatAnh = new JButton();
	Object lastValueMaSp;
	JButton btnXoa = new JButton("Xóa");
	JButton btnSua = new JButton("Sửa");
	boolean isNumber = true;
	JButton btnThem = new JButton("Thêm");
	JButton btnLuu = new JButton("Lưu");
	int lastRow = 0;
	JRadioButton radio1 = new JRadioButton("Tên sản phẩm");
	JLabel lblNewLabel = new JLabel("KHO");
	JRadioButton radio2 = new JRadioButton("Tên loại hàng");
	ButtonGroup btg = new ButtonGroup();
	JScrollPane scrollPane = new JScrollPane();
	boolean addbtn, fixbtn = false;
	JComboBox comboBoxSearch = new JComboBox();
	int rsRenderType = 0;
	JTextField textFieldSearch = new JTextField();
	JTextField textFieldTo = new JTextField();
	JTextField textFieldFrom = new JTextField();
	boolean btnfind = false;
//	dung grap 2d tao size cho anh
	int newWidth = 130;
	int newHeight = 110;
	BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
	Graphics2D g = resizedImage.createGraphics();
	String patternNumber = "\\d+(\\.\\d+)?";
	JLabel lbThongbao = new JLabel();
	String oldMaSP = null;
	String valueFind = null;
	boolean checkFix = false;
	private JTextField textMaSP1;
	private JTextField textTenSp;

//	Tabbed 2
	JButton btnThem_1 = new JButton("Thêm");
	JButton btnSua_1 = new JButton("Sửa");
	JButton btnXoa_1 = new JButton("Xóa");
	JButton btnLuu_1 = new JButton("Lưu");
	JTable table_1 = new JTable();
	Object lastValueMaLh;
	String oldTenMaLH = null;
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JTextField textNhaCC;
	private JTextField textTenNcc;
	private JTextField textDiaChiCC;
	private JTextField textSDTNCC;

//	Tabbed 3
	JTable table_2 = new JTable();
	JButton btnThem_2 = new JButton("Thêm");
	JButton btnSua_2 = new JButton("Sửa");
	JButton btnXoa_2 = new JButton("Xóa");
	JButton btnLuu_2 = new JButton("Lưu");
	String oldTenNcc = null;

	public void resetValueTabbed3() {
		textNhaCC.setText("");
		textDiaChiCC.setText("");
		textSDTNCC.setText("");
		textTenNcc.setText("");
		textDiaChiCC.setEnabled(false);
		textSDTNCC.setEnabled(false);
		textTenNcc.setEnabled(false);
		btnSua_2.setEnabled(false);
		btnXoa_2.setEnabled(false);
		btnLuu_2.setEnabled(false);
	}

	public static boolean validatePhone(String phoneNumber) {
		String regex = "^0[0-9]{9}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}

	public void hienthisanpham(String condition) throws SQLException {
		

		SanPhamDAL spDal = new SanPhamDAL();
		ArrayList<SanPham> arrSp = new ArrayList<SanPham>();
		SanPhamBLL spbll = new SanPhamBLL();
		if (condition == "hien thi") {

			arrSp = spDal.docSanPham("docsanpham", null);
		}
		if (condition == "hienthitheoid") {
			arrSp = spDal.docSanPham("docsanphamtheoid", rsRenderType + "");
		}
		if (condition == "sapxeptheoten") {
			arrSp = spDal.docSanPham("sapxeptheoten", null);
		}
		if (condition == "sapxeptheogia") {
			arrSp = spDal.docSanPham("sapxeptheogia", null);
		}
		if (condition == "timkiemtheoid") {
			arrSp = spbll.searchProductById(textFieldSearch.getText(), rsRenderType + "");

		}
		if (condition == "suatheoid") {
			if (rsRenderType > 0) {

				arrSp = spbll.searchProductById(textFieldSearch.getText(), rsRenderType + "");
				LoaiHangDAL test = new LoaiHangDAL();
				ArrayList<LoaiHang> arrMaLH = test.docLoaiHang();
				DefaultComboBoxModel combo = new DefaultComboBoxModel();
				comboBox.setModel(combo);
				for (LoaiHang malh : arrMaLH) {
					combo.addElement(malh.getTenLH());

				}
			} else {

				arrSp = spDal.docSanPham("docsanpham", null);
				LoaiHangDAL test = new LoaiHangDAL();
				ArrayList<LoaiHang> arrMaLH = test.docLoaiHang();
				DefaultComboBoxModel combo = new DefaultComboBoxModel();
				comboBox.setModel(combo);
				for (LoaiHang malh : arrMaLH) {
					combo.addElement(malh.getTenLH());

				}
			}

		}
		if (condition == "suatheogia") {
			if (rsRenderType > 0) {

				arrSp = spbll.searchProductById(textFieldSearch.getText(), rsRenderType + "");
				LoaiHangDAL test = new LoaiHangDAL();
				ArrayList<LoaiHang> arrMaLH = test.docLoaiHang();
				DefaultComboBoxModel combo = new DefaultComboBoxModel();
				comboBox.setModel(combo);
				for (LoaiHang malh : arrMaLH) {
					combo.addElement(malh.getTenLH());

				}
			} else {

				arrSp = spDal.docSanPham("docsanpham", null);
				LoaiHangDAL test = new LoaiHangDAL();
				ArrayList<LoaiHang> arrMaLH = test.docLoaiHang();
				DefaultComboBoxModel combo = new DefaultComboBoxModel();
				comboBox.setModel(combo);
				for (LoaiHang malh : arrMaLH) {
					combo.addElement(malh.getTenLH());

				}
			}

		}
		if (condition == "timkiemtheogia") {

			String priceFrom = textFieldFrom.getText().replaceAll(",", "");
			String priceTo = textFieldTo.getText().replaceAll(",", "");
			arrSp = spbll.searchProductByPrice(textFieldSearch.getText(),
					rsRenderType + "" + "," + priceFrom + "," + priceTo);
//			if (rsRenderType > 0) {
//
//				arrSp = spbll.searchProductById(textFieldSearch.getText(), rsRenderType + "");
//				LoaiHangDAL test = new LoaiHangDAL();
//				ArrayList<LoaiHang> arrMaLH = test.docLoaiHang();
//				DefaultComboBoxModel combo = new DefaultComboBoxModel();
//				comboBox.setModel(combo);
//				for (LoaiHang malh : arrMaLH) {
//					combo.addElement(malh.getTenLH());
//
//				}
//			} else {
//
//				arrSp = spDal.docSanPham("docsanpham", null);
//				LoaiHangDAL test = new LoaiHangDAL();
//				ArrayList<LoaiHang> arrMaLH = test.docLoaiHang();
//				DefaultComboBoxModel combo = new DefaultComboBoxModel();
//				comboBox.setModel(combo);
//				for (LoaiHang malh : arrMaLH) {
//					combo.addElement(malh.getTenLH());
//
//				}
//			}

		}
		if (condition == "them") {
			arrSp = spDal.docSanPham("docsanpham", null);
			LoaiHangDAL test = new LoaiHangDAL();
			ArrayList<LoaiHang> arrMaLH = test.docLoaiHang();
			DefaultComboBoxModel combo = new DefaultComboBoxModel();
			comboBox.setModel(combo);
			for (LoaiHang malh : arrMaLH) {
				combo.addElement(malh.getTenLH());

			}
		}
		if (arrSp.isEmpty() && btnfind || arrSp.isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Không tìm thấy sản phẩm!");
			btnfind = false;
			return;
		}
		String[] columnNames = { "Tên Loại Hàng", "Tên Sản Phẩm", "Số Lượng" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);

		table.setModel(model);
		model.setRowCount(0);
		for (SanPham spdata : arrSp) {
			NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
			String formatGiaBan = numberFormat.format(spdata.getGiaBan());
			String formatGiaMua = numberFormat.format(spdata.getGiaMua());
			Object[] row = new Object[] { };//spdata.getMaLh(), spdata.getMaSp(), spdata.getTenSp(), spdata.getDonVi(),spdata.getHanSuDung(), formatGiaMua, formatGiaBan, spdata.getImg() };

			model.addRow(row);
		}
		JTableHeader header = table.getTableHeader();
		Font headerFont = header.getFont(); // get the current font of the header
		header.setFont(new Font(headerFont.getName(), Font.BOLD, 14)); // set the new font for the header with size 16
		lastRow = table.getRowCount() - 1; // get index of the last row
		lastValueMaSp = table.getValueAt(lastRow, 1); // get the value at the last row and column n
	}

	public void hienThiMaSanPham() throws SQLException {

		LoaiHangDAL lhDal = new LoaiHangDAL();
		ArrayList<LoaiHang> arrLH = new ArrayList<LoaiHang>();
		String[] columnNames = { "Mã Loại Hàng", "Tên Loại Hàng" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		table_1.setModel(model);
		model.setRowCount(0);
		arrLH = lhDal.docLoaiHang();
		for (LoaiHang spdata : arrLH) {

			Object[] row = new Object[] { spdata.getMaLH(), spdata.getTenLH() };
			model.addRow(row);
		}
		JTableHeader header = table_1.getTableHeader();
		Font headerFont = header.getFont(); // get the current font of the header
		header.setFont(new Font(headerFont.getName(), Font.BOLD, 14)); // set the new font for the header with size 16
		lastRow = table_1.getRowCount() - 1; // get index of the last row
		lastValueMaLh = table_1.getValueAt(lastRow, 0); // get the value at the last row and column n

	}

	public void hienThiNhaCungCap() throws SQLException {
		ArrayList<NhaCungCap> arrNCC = new ArrayList<NhaCungCap>();
		NhaCungCapDAL ncc = new NhaCungCapDAL();
		arrNCC = ncc.docNhaCungCap();
		String[] columnNames = { "Mã Nhà Cung Cấp", "Tên Nhà Cung Cấp", "Địa Chỉ", "Số Điện Thoại" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		table_2.setModel(model);
		model.setRowCount(0);
		for (NhaCungCap nccData : arrNCC) {

			Object[] row = new Object[] { nccData.getMaNCC(), nccData.getTenNCC(), nccData.getDiaChi(),
					nccData.getSoDT() };
			model.addRow(row);
		}
		JTableHeader header = table_2.getTableHeader();
		Font headerFont = header.getFont(); // get the current font of the header
		header.setFont(new Font(headerFont.getName(), Font.BOLD, 14)); // set the new font for the header with size 16
		lastRow = table_2.getRowCount() - 1; // get index of the last row
		lastValueMaLh = table_2.getValueAt(lastRow, 0); // get the value at the last row and column n

	}

	public void resetValue() {
		textFieldImg.setText("");
		textFieldImg.setEnabled(true);
		textFieldMasp.setText("");
		textFieldMasp.setEnabled(true);
		textFieldDonvi.setText("");
		textFieldDonvi.setEnabled(true);
		textFieldGiaban.setText("");
		textFieldGiaban.setEnabled(true);
		textFieldGianhap.setText("");
		textFieldGianhap.setEnabled(true);
		textFieldHansd.setText("");
		textFieldHansd.setEnabled(true);
		textFieldTensp.setText("");
		textFieldTensp.setEnabled(true);
		comboBox.setEnabled(true);
		lbThemanh.setIcon(null);
		btnCapNhatAnh.setEnabled(true);
		btnThem.setEnabled(true);
		btnXoa.setEnabled(false);
		btnSua.setEnabled(false);
		btnLuu.setEnabled(false);
		comboBox.setSelectedItem(null);

//		Tabbed 2
		textMaSP1.setText("");
		textMaSP1.setEnabled(true);
		textTenSp.setText("");
		textTenSp.setEnabled(true);
		btnXoa_1.setEnabled(false);
		btnSua_1.setEnabled(false);
		btnLuu_1.setEnabled(false);

	}

	public void unSetEnable() {
		textFieldImg.setEnabled(true);
		textFieldMasp.setEnabled(true);
		textFieldDonvi.setEnabled(true);
		textFieldGiaban.setEnabled(true);
		textFieldGianhap.setEnabled(true);
		textFieldHansd.setEnabled(true);
		textFieldTensp.setEnabled(true);

		comboBox.setEnabled(true);
		btnCapNhatAnh.setEnabled(true);
		btnThem.setEnabled(true);
		btnXoa.setEnabled(false);
		btnSua.setEnabled(false);
		btnLuu.setEnabled(false);
//		Tabbed 2
		btnThem_1.setEnabled(true);
		btnXoa_1.setEnabled(false);
		btnSua_1.setEnabled(false);
		btnLuu_1.setEnabled(false);
		textMaSP1.setEnabled(true);
		textTenSp.setEnabled(true);

	}

	public void setEnable() {

		textFieldImg.setEnabled(false);

		textFieldMasp.setEnabled(false);

		textFieldDonvi.setEnabled(false);
		textFieldGiaban.setEnabled(false);
		textFieldGianhap.setEnabled(false);
		textFieldHansd.setEnabled(false);
		textFieldTensp.setEnabled(false);
		comboBox.setEnabled(false);
		btnCapNhatAnh.setEnabled(false);
//		Tabbed 2

		textMaSP1.setEnabled(false);
		textTenSp.setEnabled(false);
	}

	public Boolean checkEmtyValue() throws SQLException {
		// regular expression pattern
		if (textFieldMasp.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Mã sản phẩm trống!");
			textFieldMasp.requestFocus();
			return false;
		}
		if (!textFieldMasp.getText().isEmpty()) {
			SanPhamDAL spd = new SanPhamDAL();
			ArrayList<SanPham> arrPro = new ArrayList<SanPham>();
			arrPro = spd.docSanPham("docsanpham", null);
			if (fixbtn) {
				for (SanPham sp : arrPro) {
					if (Integer.parseInt(oldMaSP) != Integer.parseInt(textFieldMasp.getText())
							&& sp.getMaSp() == Integer.parseInt(textFieldMasp.getText())) {
						JOptionPane.showMessageDialog(contentPane, "Mã sản phẩm đã tồn tại!");
						textFieldMasp.requestFocus();
						return false;

					}
				}
			}
			if (addbtn) {
				for (SanPham sp : arrPro) {
					if (sp.getMaSp() == Integer.parseInt(textFieldMasp.getText())) {
						JOptionPane.showMessageDialog(contentPane, "Mã sản phẩm đã tồn tại!");
						textFieldMasp.requestFocus();
						return false;

					}
				}
			}

		}
		if (selectedFile == null && textFieldImg.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Chưa chọn ảnh cho sản phẩm");
			return false;
		}
		if (textFieldGiaban.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Giá bán rỗng");
			textFieldGiaban.requestFocus();
			return false;

		}
		if (!textFieldGiaban.getText().isEmpty()) {
			String input = textFieldGiaban.getText().replaceAll(",", "");
			isNumber = input.matches(patternNumber);
			if (!isNumber) {
				JOptionPane.showMessageDialog(contentPane, "Giá trị phải là số");
				textFieldGiaban.requestFocus();
				textFieldGiaban.selectAll();
				return false;
			}
		}
		if (textFieldGianhap.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Giá nhập rỗng");
			textFieldGianhap.requestFocus();
			return false;

		}
		if (!textFieldGianhap.getText().isEmpty()) {
			String input = textFieldGianhap.getText().replaceAll(",", "");
			isNumber = input.matches(patternNumber);
			if (!isNumber) {
				JOptionPane.showMessageDialog(contentPane, "Giá trị phải là số");
				textFieldGianhap.requestFocus();
				textFieldGianhap.selectAll();
				return false;
			}

		}

		if (textFieldDonvi.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Đơn vị rỗng");
			textFieldDonvi.requestFocus();
			return false;
		}
		if (textFieldTensp.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Tên sản phẩm rỗng");
			textFieldTensp.requestFocus();
			return false;
		}
		if (textFieldHansd.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Hạn sử dụng rỗng");
			textFieldHansd.requestFocus();
			return false;
		}

		if (Integer.parseInt(textFieldGianhap.getText().replaceAll(",", "")) > Integer
				.parseInt(textFieldGiaban.getText().replaceAll(",", ""))) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Giá bán nhỏ hơn giá nhập,Bạn muốn tiếp tục",
					"Confirmation", JOptionPane.YES_NO_OPTION);
			if (dialogResult == JOptionPane.YES_OPTION) {
				return true;
			} else if (dialogResult == JOptionPane.NO_OPTION) {
				return false;
			} else if (dialogResult == JOptionPane.CANCEL_OPTION) {
				return false;
			}
		}
		return true;
	}

//Tabbed 2
	public Boolean checkEmtyValueTabbed2() throws SQLException {
		if (textTenSp.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Tên Mã Loại Hàng Trống!");
			textTenSp.requestFocus();
			return false;

		}
		if (!textTenSp.getText().isEmpty()) {
			LoaiHangDAL LHD = new LoaiHangDAL();
			ArrayList<LoaiHang> arrLh = new ArrayList<LoaiHang>();
			arrLh = LHD.docLoaiHang();

			for (LoaiHang lh : arrLh) {

				if (fixbtn && oldTenMaLH.equals(textTenSp.getText()) == false
						&& lh.getTenLH().equals(textTenSp.getText())) {
					JOptionPane.showMessageDialog(contentPane, "Tên Loại Hàng Đã Tồn Tại!");
					textTenSp.requestFocus();
					textTenSp.selectAll();

					return false;
				}

				if (addbtn && lh.getTenLH().equals(textTenSp.getText())) {
					JOptionPane.showMessageDialog(contentPane, "Tên Loại Hàng Đã Tồn Tại!");
					textTenSp.requestFocus();
					textTenSp.selectAll();

					return false;
				}
			}

		}
		return true;
	}

	// Tabbed 3
	public Boolean checkEmtyValueTabbed3() throws SQLException {
		if (textTenNcc.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Tên Nhà Cung Cấp Trống!");
			textTenNcc.requestFocus();
			return false;

		}
		if (!textTenNcc.getText().isEmpty()) {
			NhaCungCapDAL NCCD = new NhaCungCapDAL();
			ArrayList<NhaCungCap> arrNcc = new ArrayList<NhaCungCap>();
			arrNcc = NCCD.docNhaCungCap();

			for (NhaCungCap lh : arrNcc) {

				if (fixbtn && oldTenNcc.equals(textTenNcc.getText()) == false
						&& lh.getTenNCC().equals(textTenNcc.getText())) {
					JOptionPane.showMessageDialog(contentPane, "Tên Nhà Cung Cấp Đã Tồn Tại!");
					textTenNcc.requestFocus();
					textTenNcc.selectAll();

					return false;
				}

				if (addbtn && lh.getTenNCC().equals(textTenNcc.getText())) {
					JOptionPane.showMessageDialog(contentPane, "Tên Nhà Cung Cấp Đã Tồn Tại!");
					textTenNcc.requestFocus();
					textTenNcc.selectAll();

					return false;
				}
			}

		}
		if (textDiaChiCC.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Địa Chỉ Nhà Cung Cấp Trống!");
			textDiaChiCC.requestFocus();
			return false;
		}
		if (textSDTNCC.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Số Điện Thoại Nhà Cung Cấp Trống!");
			textSDTNCC.requestFocus();
			return false;
		}
		if (!textSDTNCC.getText().isEmpty()) {
			if (!validatePhone(textSDTNCC.getText())) {
				JOptionPane.showMessageDialog(contentPane, "Số Điện Thoại Không hợp lệ,gồm 10 số và bắt đầu bằng 0");
				textSDTNCC.requestFocus();
				return false;
			}

		}
		return true;
	}

	public KhoGui() throws SQLException {

		
		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int selectedIndex = tabbedPane.getSelectedIndex();
				if (selectedIndex == 0) {

					lblNewLabel.setText("QUẢN LÝ KHO");
					LoaiHangDAL lhd;
					try {
						lhd = new LoaiHangDAL();
						ArrayList<LoaiHang> arrLh = new ArrayList<LoaiHang>();
						arrLh = lhd.docLoaiHang();

						DefaultComboBoxModel combo = new DefaultComboBoxModel();
						comboBoxSearch.setModel(combo);
						combo.addElement("Tất cả");
						for (LoaiHang malh : arrLh) {
							combo.addElement(malh.getTenLH());

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				if (selectedIndex == 1) {
					try {
						hienThiMaSanPham();
						lblNewLabel.setText("QUẢN LÝ LOẠI HÀNG");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				if (selectedIndex == 2) {
					try {
						hienThiNhaCungCap();
						lblNewLabel.setText("QUẢN LÝ NHÀ CUNG CẤP");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		setTitle("Quản Lý Kho");

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener((WindowListener) new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn muốn thoát chương trình?", "Confirmation",
						JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		setBounds(100, 100, 1106, 701);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		// Get an array of all buttons in the application

		JPanel panel = new JPanel();

		JPanel panel_1 = new JPanel();

		JPanel panel_2 = new JPanel();

		tabbedPane.addTab("Kho", null, panel_2, null);

		//JPanel panel_5 = new JPanel();
		//panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));

		btnCapNhatAnh.setText("Cập nhật ảnh");
		btnCapNhatAnh.setEnabled(false);
		btnCapNhatAnh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Images", "jpg", "png");
				fileChooser.setFileFilter(imageFilter);
				fileChooser.setMultiSelectionEnabled(false);
				int returnVal = fileChooser.showDialog(tabbedPane, "Chọn ảnh");
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();

					ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
					Image image = icon.getImage();
					g.drawImage(image, 0, 0, newWidth, newHeight, null);
//					g.dispose();
					ImageIcon resizedIcon = new ImageIcon(resizedImage);
					lbThemanh.setIcon(resizedIcon);
					textFieldImg.setText(selectedFile.getAbsolutePath());

				}
			}
		});
		btnCapNhatAnh.setFocusPainted(false);
		btnCapNhatAnh.setFont(new Font("Arial", Font.BOLD, 12));

		textFieldImg = new JTextField();
		textFieldImg.setEnabled(false);
		textFieldImg.setEditable(false);
		textFieldImg.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Mã sản phẩm");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 12));

		textFieldMasp = new JTextField();
		textFieldMasp.setFont(new Font("Arial", Font.BOLD, 14));
		textFieldMasp.setEnabled(false);
		textFieldMasp.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Tên sản phẩm");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 12));

		textFieldTensp = new JTextField();
		textFieldTensp.setFont(new Font("Arial", Font.BOLD, 14));
		textFieldTensp.setEnabled(false);
		textFieldTensp.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Giá nhập");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 12));

		textFieldGianhap = new JTextField();
		textFieldGianhap.setFont(new Font("Arial", Font.BOLD, 14));
		textFieldGianhap.setEnabled(false);
		textFieldGianhap.setColumns(10);

		textFieldGianhap.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				formatInput();
			}

			private void formatInput() {
			    String input = textFieldGianhap.getText().replaceAll("[\\p{Punct}\\s]", "");

			    if (input.isEmpty()) {
			        return;
			    }

			    try {
			        long number = Long.parseLong(input);
			        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
			        String formattedNumber = numberFormat.format(number);
			        textFieldGianhap.setText(formattedNumber);
			    } catch (NumberFormatException ex) {
			        // Ignore invalid input
			    }
			}

		});

		JLabel lblNewLabel_6 = new JLabel("Loại sản phẩm");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 12));

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial", Font.BOLD, 14));
		comboBox.setEnabled(false);

		JLabel lblNewLabel_7 = new JLabel("Đơn vị");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 12));

		JLabel lblNewLabel_8 = new JLabel("Hạn sử dụng");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Arial", Font.BOLD, 12));

		textFieldHansd = new JTextField();
		textFieldHansd.setFont(new Font("Arial", Font.BOLD, 14));
		textFieldHansd.setEnabled(false);
		textFieldHansd.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Giá bán");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setFont(new Font("Arial", Font.BOLD, 12));

		textFieldGiaban = new JTextField();
		textFieldGiaban.setFont(new Font("Arial", Font.BOLD, 14));
		textFieldGiaban.setEnabled(false);
		textFieldGiaban.setColumns(10);
		textFieldGiaban.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {

				formatInput();
			}

			private void formatInput() {
				String input = textFieldGiaban.getText().replaceAll(",", "");
				if (input.isEmpty()) {
					return;
				}
				try {
					int number = Integer.parseInt(input);
					NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
					String formattedNumber = numberFormat.format(number);
					textFieldGiaban.setText(formattedNumber);
				} catch (NumberFormatException ex) {
					// Ignore invalid input
				}
			}
		});
		textFieldDonvi = new JTextField();
		textFieldDonvi.setFont(new Font("Arial", Font.BOLD, 14));
		textFieldDonvi.setEnabled(false);
		textFieldDonvi.setColumns(10);

		//JPanel panel_6 = new JPanel();
		btnLuu.setFont(new Font("Arial", Font.BOLD, 12));

		btnLuu.setEnabled(false);
		btnLuu.setFocusPainted(false);
		btnLuu.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Save.png"))));
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkEmtyValue()) {
						// -------------------Copy img vao thu muc Image cua project
						if (selectedFile != null) {
							Path sourcePath = selectedFile.toPath();
							Path projectPath = Paths.get(System.getProperty("user.dir")); // get the path to the project
																							// directory
							Path imageDirectory = projectPath.resolve("src//GUI//Image"); // resolve the path to the
																							// Image

							try {
								Files.createDirectories(imageDirectory);
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} // create the Image directory if it does not exist
							Path destinationPath = imageDirectory.resolve(selectedFile.getName());

							try {
								Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

						SanPham sp = new SanPham();
						SanPhamDAL luusp;
						if (addbtn) {
							try {
								luusp = new SanPhamDAL();
								int malh = luusp.layMaLoaiSP((String) (comboBox.getSelectedItem()));
								sp.setMaLh(malh);
								sp.setMaSp(Integer.parseInt(textFieldMasp.getText()));
								sp.setDonVi(textFieldDonvi.getText());
								String inputBan = textFieldGiaban.getText().replaceAll(",", "");
								sp.setGiaBan(Float.parseFloat(inputBan));
								String inputNhap = textFieldGianhap.getText().replaceAll(",", "");
								sp.setGiaMua(Float.parseFloat(inputNhap));
								sp.setHanSuDung(textFieldHansd.getText());
								sp.setTenSp(textFieldTensp.getText());
								sp.setImg(selectedFile.getName());
								boolean checkAddPro = luusp.themsanpham(sp, "themsanpham", null);
								if (checkAddPro) {
									JOptionPane.showMessageDialog(contentPane, "Thêm thành công");
									resetValue();
									setEnable();
									hienthisanpham("hien thi");
									addbtn = false;
								} else {
									JOptionPane.showMessageDialog(contentPane, "Thêm thất bại");
								}
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						}
						if (fixbtn) {

							try {
								if (selectedFile == null) {
									File file = new File(textFieldImg.getText());
									String fileName = file.getName();
									sp.setImg(fileName);
								} else {
									sp.setImg(selectedFile.getName());
								}
								luusp = new SanPhamDAL();
								int malh = luusp.layMaLoaiSP((String) (comboBox.getSelectedItem()));
								sp.setMaLh(malh);
								sp.setMaSp(Integer.parseInt(textFieldMasp.getText()));
								sp.setDonVi(textFieldDonvi.getText());
								String inputBan = textFieldGiaban.getText().replaceAll(",", "");
								sp.setGiaBan(Float.parseFloat(inputBan));
								String inputNhap = textFieldGianhap.getText().replaceAll(",", "");
								sp.setGiaMua(Float.parseFloat(inputNhap));
								sp.setHanSuDung(textFieldHansd.getText());
								sp.setTenSp(textFieldTensp.getText());

								boolean checkAddPro = luusp.themsanpham(sp, "suasanpham", oldMaSP);
								if (checkAddPro) {
									JOptionPane.showMessageDialog(contentPane, "Sửa thành công");
									resetValue();
									setEnable();
									if (rsRenderType > 0) {
										if (textFieldFrom.getText().isEmpty()) {
											hienthisanpham("suatheoid");
										} else if (textFieldFrom.getText().isEmpty() == false) {
											hienthisanpham("timkiemtheogia");
										}

									} else if (rsRenderType == 0) {
										hienthisanpham("hien thi");
									}
									fixbtn = false;
								} else {
									JOptionPane.showMessageDialog(contentPane, "Sửa thất bại");
								}
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						}

					}
				} catch (NumberFormatException | HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnThem.setFont(new Font("Arial", Font.BOLD, 12));
		btnThem.setFocusPainted(false);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addbtn = true;
				resetValue();
				SanPhamDAL spdDal;
				try {
					spdDal = new SanPhamDAL();
					int masp = spdDal.getLastMaSP();
					masp++;
					textFieldMasp.setText("" + masp);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				textFieldMasp.setEnabled(false);

				btnThem.setEnabled(false);
				btnLuu.setEnabled(true);
				btnXoa.setEnabled(false);
				btnSua.setEnabled(false);
				try {
					hienthisanpham("them");
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}

			}
		});
		btnThem.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Add.png"))));
		btnSua.setFont(new Font("Arial", Font.BOLD, 12));

		btnSua.setEnabled(false);
		btnSua.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Change.png"))));
		btnSua.setFocusPainted(false);
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fixbtn = true;
				addbtn = false;
				oldMaSP = textFieldMasp.getText();
				unSetEnable();
				btnThem.setEnabled(false);
				btnLuu.setEnabled(true);

				try {
					String valueSelect = comboBox.getSelectedItem().toString();
					if (textFieldFrom.getText().isEmpty()&&textFieldFrom.getText().isEmpty()) {
						hienthisanpham("suatheoid");
						
					} else if (textFieldFrom.getText().isEmpty() == false) {
						hienthisanpham("suatheogia");
					}

					comboBox.setSelectedItem(valueSelect);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnXoa.setFont(new Font("Arial", Font.BOLD, 12));

		btnXoa.setEnabled(false);
		btnXoa.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Delete.png"))));
		btnXoa.setFocusPainted(false);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa sản phẩm này", "Confirmation",
						JOptionPane.YES_NO_OPTION);
				if (confirmed == JOptionPane.YES_OPTION) {
					SanPhamDAL deleteSp;
					try {
						deleteSp = new SanPhamDAL();
						if (deleteSp.xoaSanPham(textFieldMasp.getText())) {
							JOptionPane.showMessageDialog(contentPane, "Xóa sản phẩm thành công!");
							hienthisanpham("hien thi");
							resetValue();
							comboBoxSearch.setSelectedItem("Tất cả");
							setEnable();

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));

		JButton btnDongBo = new JButton("");
		btnDongBo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.createImage(KhoGui.class.getResource(".\\Image\\Refresh-icon.png"))));
		btnDongBo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					hienthisanpham("hien thi");
					resetValue();
					setEnable();
					btg.clearSelection();
					comboBoxSearch.setSelectedItem("Tất cả");

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDongBo.setFocusPainted(false);

		JButton btnSapxep = new JButton("Sắp xếp");
		btnSapxep.setFont(new Font("Arial", Font.BOLD, 12));
		btnSapxep.setFocusPainted(false);
		btnSapxep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SanPhamDAL spd;
				// Create an ArrayList to store the data from the JTable
				ArrayList<SanPham> data = new ArrayList<>();

				// Get the number of rows and columns in the JTable
				int numRows = table.getRowCount();
				// Loop through each row in the JTable and add the data to the ArrayList
				for (int i = 0; i < numRows; i++) {
					SanPham sp = new SanPham();
					sp.setMaLh((int) table.getValueAt(i, 0));
					sp.setMaSp((int) table.getValueAt(i, 1));
					sp.setTenSp((String) table.getValueAt(i, 2));
					sp.setDonVi((String) table.getValueAt(i, 3));
					sp.setHanSuDung((String) table.getValueAt(i, 4));
					sp.setGiaMua(Float.parseFloat(((String) table.getValueAt(i, 5)).replaceAll(",", "")));
					sp.setGiaBan(Float.parseFloat(((String) table.getValueAt(i, 6)).replaceAll(",", "")));
					sp.setImg((String) table.getValueAt(i, 7));
					data.add(sp);

				}
				if (radio1.isSelected()) {
					

					Collections.sort(data, new Comparator<SanPham>() {
					    public int compare(SanPham sp1, SanPham sp2) {
					        return sp1.getTenSp().compareTo(sp2.getTenSp());
					    }
					});
			
					
					
				}
				if (radio2.isSelected()) {

					Collections.sort(data, new Comparator<SanPham>() {
					    public int compare(SanPham sp1, SanPham sp2) {
					        return Float.compare(sp1.getGiaBan(), sp2.getGiaBan());
					    }
					});
				}
				
				String[] columnNames = { "Tên Loại Hàng", "Tên Sản Phẩm", "Số Lượng" };
				DefaultTableModel model = new DefaultTableModel(columnNames, 0);

				table.setModel(model);
				model.setRowCount(0);
				for (SanPham sp : data) {
					NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
					String formatGiaBan = numberFormat.format(sp.getGiaBan());
					String formatGiaMua = numberFormat.format(sp.getGiaMua());
					Object[] row = {};//sp.getMaLh(), sp.getMaSp(), sp.getTenSp(),sp.getDonVi(),sp.getHanSuDung(),formatGiaMua, formatGiaBan,sp.getImg() };
					model.addRow(row);
				}
				
			}
		});

		btg.add(radio1);
		btg.add(radio2);
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup().addContainerGap()
						.addComponent(btnSapxep, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE).addGap(18)
						.addComponent(radio1, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(radio2, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE).addGap(13)));
		gl_panel_7.setVerticalGroup(gl_panel_7.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_7.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSapxep, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
								.addComponent(radio1).addComponent(radio2))
						.addContainerGap()));
		radio1.setFont(new Font("Arial", Font.BOLD, 12));
		radio2.setFont(new Font("Arial", Font.BOLD, 12));
		panel_7.setLayout(gl_panel_7);

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));

		lbThongbao.setText("SẢN PHẨM TRONG KHO");

		lbThongbao.setForeground(new Color(255, 128, 128));
		lbThongbao.setHorizontalAlignment(SwingConstants.CENTER);
		lbThongbao.setFont(new Font("Arial", Font.BOLD, 20));

		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.setDefaultEditor(Object.class, null);
		JTableHeader header = table.getTableHeader();
		header.setPreferredSize(new Dimension(header.getWidth(), 30));

		table.setRowHeight(30);
		table.setFocusable(false);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lbThemanh.setIcon(null);
				int row = table.getSelectedRow(); // get the selected row
				String maLh = table.getModel().getValueAt(row, 0).toString();
				String maSP = table.getModel().getValueAt(row, 1).toString(); // get the value of the first column
				String tenSP = table.getModel().getValueAt(row, 2).toString(); // get the value of the second column
				String donVi = table.getModel().getValueAt(row, 3).toString();
				String hSD = table.getModel().getValueAt(row, 4).toString();
				String giaMua = table.getModel().getValueAt(row, 5).toString();
				String giaBan = table.getModel().getValueAt(row, 6).toString();
//				String img = table.getModel().getValueAt(row, 7).toString();
				textFieldMasp.setText(maSP);
				textFieldTensp.setText(tenSP);
				textFieldHansd.setText(hSD);
				textFieldGianhap.setText(giaMua);
				textFieldGiaban.setText(giaBan);
				textFieldDonvi.setText(donVi);

				setEnable();
				btnXoa.setEnabled(true);
				btnSua.setEnabled(true);
				btnThem.setEnabled(true);
				btnLuu.setEnabled(false);

				String img = null;
				Image image = null;
				Object value = table.getModel().getValueAt(row, 7);
				
				if (value != null) {
					img = value.toString();
				}
				if (img == null || LoginGui.class.getResource(img) == null || img.isEmpty()) {
					// If the image path is null or empty, use a default image instead

					icon = new ImageIcon(Toolkit.getDefaultToolkit()
							.createImage(LoginGui.class.getResource(".//Image//default.png")));
					image = icon.getImage();
					textFieldImg.setText("");
					Image resizedImg = image.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
					ImageIcon resizedIcon = new ImageIcon(resizedImg);
					lbThemanh.setIcon(resizedIcon);

				} else {
					try {
						
						icon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(img)));

						image = icon.getImage();
						textFieldImg.setText(img);
						g.drawImage(image, 0, 0, newWidth, newHeight, null);
						ImageIcon resizedIcon = new ImageIcon(resizedImage);
						lbThemanh.setIcon(resizedIcon);

					} catch (Exception e1) {
						e1.printStackTrace();

					}
				}

//				g.dispose();

				LoaiHangDAL test;
				try {
					test = new LoaiHangDAL();
					ArrayList<LoaiHang> arrMaLH = test.docLoaiHangMaLH(Integer.parseInt(maLh));
					DefaultComboBoxModel combo = new DefaultComboBoxModel();
					comboBox.setModel(combo);

					combo.addElement(arrMaLH.get(0).getTenLH());

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		scrollPane.setViewportView(table);
		icon = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource("/GUI/Image/Background.png")));
		Image image_bg = icon.getImage();
		Image resizedImg_bg = image_bg.getScaledInstance(1300, 130, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon_bg = new ImageIcon(resizedImg_bg);

		ImageIcon iconSearch = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Find.png")));
		Image imageSearch = iconSearch.getImage();
		Image resizedImgSearch = imageSearch.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon resizedIconSearch = new ImageIcon(resizedImgSearch);

		JButton btnNewButton = new JButton("Hệ thống");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				QuanLyHome hnv = new QuanLyHome();
				hnv.setLocationRelativeTo(null);
				hnv.setVisible(true);
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					//.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 1063, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					//.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 1053, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_8, GroupLayout.DEFAULT_SIZE, 1053, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					//.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 127, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					//.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_8, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
					.addContainerGap())
		);

		JPanel panel_7_1 = new JPanel();
		panel_7_1.setBorder(null);

		comboBoxSearch.setFont(new Font("Arial", Font.BOLD, 14));

		comboBoxSearch.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				btg.clearSelection();
				textFieldFrom.setText("");
				textFieldTo.setText("");
				textFieldSearch.setText("");
				try {
					SanPhamBLL spbll = new SanPhamBLL();
					rsRenderType = spbll.renderProType(comboBoxSearch.getSelectedItem().toString());

					if (rsRenderType > 0) {

						hienthisanpham("hienthitheoid");

					}
					if (rsRenderType == 0) {
						hienthisanpham("hien thi");

					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		textFieldFrom.setColumns(10);
		textFieldFrom.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				formatInput();
			}

			private void formatInput() {
				String input = textFieldFrom.getText().replaceAll("[\\p{Punct}\\s]", "");
				
				if (input.isEmpty()) {
					return;
				}
				try {
					int number = Integer.parseInt(input);
					NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
					String formattedNumber = numberFormat.format(number);
					textFieldFrom.setText(formattedNumber);
				} catch (NumberFormatException ex) {
					// Ignore invalid input
				}
			}
		});

		textFieldTo.setColumns(10);
		textFieldTo.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				formatInput();
			}

			private void formatInput() {
				String input = textFieldTo.getText().replaceAll("[\\p{Punct}\\s]", "");
				
				if (input.isEmpty()) {
					return;
				}
				try {
					int number = Integer.parseInt(input);
					NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
					String formattedNumber = numberFormat.format(number);
					textFieldTo.setText(formattedNumber);
				} catch (NumberFormatException ex) {
					// Ignore invalid input
				}
			}
		});

		JLabel lblNewLabel_15 = new JLabel("Từ");
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_15.setFont(new Font("Arial", Font.BOLD, 14));

		JLabel lblNewLabel_16 = new JLabel("Tới");
		lblNewLabel_16.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_16.setFont(new Font("Arial", Font.BOLD, 14));

		textFieldSearch.setColumns(10);

		JButton btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				btg.clearSelection();
				if (textFieldSearch.getText().isEmpty()) {

					JOptionPane.showMessageDialog(contentPane, "Nội dung tìm kiếm trống!");
					textFieldSearch.requestFocus();

				}
				if (!textFieldSearch.getText().isEmpty()) {
					btnfind = true;
					SanPhamBLL spbll = new SanPhamBLL();
					ArrayList<SanPham> arr = new ArrayList<SanPham>();
					try {
						arr = spbll.searchProductById(textFieldSearch.getText(), rsRenderType + "");
						if (!arr.isEmpty()) {
							if (!textFieldFrom.getText().isEmpty() || !textFieldTo.getText().isEmpty()) {
								if (textFieldFrom.getText().isEmpty()) {
									JOptionPane.showMessageDialog(contentPane, "Khoảng giá cần tìm trống!");
									textFieldFrom.requestFocus();
								} else if (textFieldTo.getText().isEmpty()) {
									JOptionPane.showMessageDialog(contentPane, "Khoảng giá cần tìm trống!");
									textFieldTo.requestFocus();
								} else {
									String inputFrom = textFieldFrom.getText().replaceAll("[\\p{Punct}\\s]", "");
									
									
									String inputTo = textFieldTo.getText().replaceAll("[\\p{Punct}\\s]", "");
									isNumber = inputFrom.matches(patternNumber);
									boolean isNumber2 = inputTo.matches(patternNumber);

									if (!isNumber) {
										JOptionPane.showMessageDialog(contentPane, "Giá trị phải là số");
										textFieldFrom.requestFocus();
										textFieldFrom.selectAll();

									} else if (!isNumber2) {
										JOptionPane.showMessageDialog(contentPane, "Giá trị phải là số");
										textFieldTo.requestFocus();
										textFieldTo.selectAll();

									} else {
										if (Integer.parseInt(textFieldFrom.getText().replaceAll(",", "")) > Integer
												.parseInt(textFieldTo.getText().replaceAll(",", ""))) {
											JOptionPane.showMessageDialog(contentPane, "Khoảng giá không hợp lê!");
											textFieldFrom.requestFocus();
										} else {

											hienthisanpham("timkiemtheogia");
										}
									}

								}
							} else {
								hienthisanpham("timkiemtheoid");
							}

						} else {
							JOptionPane.showMessageDialog(contentPane, "Không tìm thấy sản phẩm!");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 12));
		btnTimKiem.setFocusPainted(false);
		GroupLayout gl_panel_7_1 = new GroupLayout(panel_7_1);
		gl_panel_7_1.setHorizontalGroup(
			gl_panel_7_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_7_1.createSequentialGroup()
					.addGap(24)
					.addComponent(panel_7, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
					.addGap(24)
					.addComponent(btnTimKiem, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldSearch, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
					.addGap(37)
					.addComponent(comboBoxSearch, 0, 200, Short.MAX_VALUE)
					.addGap(49)
					.addComponent(btnDongBo,GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
					.addGap(3)
					.addComponent(btnNewButton,GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
					.addGap(3))
					
					//.addComponent(lblNewLabel_15, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
					//.addGap(18)
					//.addComponent(textFieldFrom, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
					//.addGap(10)
					//.addComponent(lblNewLabel_16, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
					//.addPreferredGap(ComponentPlacement.RELATED)
					//.addComponent(textFieldTo)
					//.addGap(66))
		);
		gl_panel_7_1.setVerticalGroup(
			gl_panel_7_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_7_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_7, GroupLayout.DEFAULT_SIZE,30, Short.MAX_VALUE)
						.addComponent(btnTimKiem, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
						.addGroup(gl_panel_7_1.createSequentialGroup()
							.addGap(0)
							.addGroup(gl_panel_7_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldSearch, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
								.addComponent(btnDongBo,GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
								.addComponent(btnNewButton,GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
								.addComponent(comboBoxSearch, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
								.addGap(0)))))
									//.addComponent(lblNewLabel_15, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
								//.addGroup(gl_panel_7_1.createSequentialGroup()
									//.addGap(3)
									//.addGroup(gl_panel_7_1.createParallelGroup(Alignment.BASELINE)
										//.addComponent(lblNewLabel_16, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
										//.addComponent(textFieldTo, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
										//.addComponent(textFieldFrom, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))))))
					//.addGap(9))
		);
		panel_7_1.setLayout(gl_panel_7_1);
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(
			gl_panel_8.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addGap(211)
					.addComponent(lbThongbao, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
					.addGap(211))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1051, Short.MAX_VALUE)
				.addComponent(panel_7_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		gl_panel_8.setVerticalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addComponent(panel_7_1, GroupLayout.DEFAULT_SIZE, 0, 0)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbThongbao, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_8.setLayout(gl_panel_8);
		/*
		 * GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		 * gl_panel_6.setHorizontalGroup(
		 * gl_panel_6.createParallelGroup(Alignment.LEADING)
		 * .addGroup(gl_panel_6.createSequentialGroup() .addGap(10)
		 * .addComponent(btnLuu, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
		 * .addGap(32) .addComponent(btnThem, GroupLayout.DEFAULT_SIZE, 102,
		 * Short.MAX_VALUE) .addGap(33) .addComponent(btnSua, GroupLayout.DEFAULT_SIZE,
		 * 102, Short.MAX_VALUE) .addGap(32) .addComponent(btnXoa,
		 * GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE) .addGap(40)
		 * .addComponent(btnDongBo, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
		 * .addGap(30) .addComponent(panel_7, GroupLayout.DEFAULT_SIZE, 366,
		 * Short.MAX_VALUE)) ); gl_panel_6.setVerticalGroup(
		 * gl_panel_6.createParallelGroup(Alignment.LEADING)
		 * .addGroup(gl_panel_6.createSequentialGroup() .addGap(10)
		 * .addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
		 * .addComponent(btnLuu, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
		 * .addComponent(btnThem, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
		 * .addComponent(btnSua, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
		 * .addComponent(btnXoa, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
		 * .addComponent(btnDongBo, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
		 * .addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 53, Short.MAX_VALUE))
		 * .addGap(15)) ); panel_6.setLayout(gl_panel_6);
		 */
		/*
		 * GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		 * gl_panel_5.setHorizontalGroup(
		 * gl_panel_5.createParallelGroup(Alignment.LEADING)
		 * .addGroup(gl_panel_5.createSequentialGroup() .addGap(9)
		 * .addComponent(lbThemanh, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
		 * .addGap(10) .addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING)
		 * .addComponent(lblNewLabel_6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
		 * 110, Short.MAX_VALUE) .addComponent(btnCapNhatAnh, Alignment.LEADING,
		 * GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
		 * .addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
		 * .addGroup(gl_panel_5.createSequentialGroup() .addGap(10)
		 * .addComponent(textFieldImg, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
		 * .addGap(10)) .addGroup(gl_panel_5.createSequentialGroup() .addGap(9)
		 * .addComponent(comboBox, 0, 127, Short.MAX_VALUE) .addGap(11)))
		 * .addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
		 * .addComponent(lblNewLabel_7, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
		 * GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) .addComponent(lblNewLabel_1,
		 * Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
		 * .addPreferredGap(ComponentPlacement.RELATED)
		 * .addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
		 * .addGroup(Alignment.TRAILING, gl_panel_5.createSequentialGroup() .addGap(141)
		 * .addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
		 * .addGroup(gl_panel_5.createSequentialGroup()
		 * .addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING)
		 * .addComponent(textFieldDonvi, Alignment.LEADING, GroupLayout.PREFERRED_SIZE,
		 * 0, Short.MAX_VALUE) .addComponent(textFieldMasp, Alignment.LEADING,
		 * GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)) .addGap(10)
		 * .addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 106,
		 * Short.MAX_VALUE))) .addPreferredGap(ComponentPlacement.RELATED)
		 * .addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
		 * .addComponent(textFieldTensp, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
		 * .addComponent(textFieldGianhap, GroupLayout.DEFAULT_SIZE, 137,
		 * Short.MAX_VALUE)) .addGap(10)
		 * .addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING)
		 * .addGroup(gl_panel_5.createSequentialGroup() .addGap(127)
		 * .addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
		 * .addGap(1)) .addGroup(gl_panel_5.createSequentialGroup()
		 * .addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
		 * .addComponent(lblNewLabel_9, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
		 * .addComponent(lblNewLabel_8, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
		 * 75, Short.MAX_VALUE)) .addPreferredGap(ComponentPlacement.RELATED)
		 * .addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
		 * .addComponent(textFieldGiaban, GroupLayout.DEFAULT_SIZE, 125,
		 * Short.MAX_VALUE) .addComponent(textFieldHansd, Alignment.TRAILING,
		 * GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)))) .addContainerGap()) );
		 * gl_panel_5.setVerticalGroup(
		 * gl_panel_5.createParallelGroup(Alignment.LEADING)
		 * .addGroup(gl_panel_5.createSequentialGroup()
		 * .addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
		 * .addComponent(lbThemanh, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
		 * .addGroup(gl_panel_5.createSequentialGroup() .addGap(14)
		 * .addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
		 * .addGroup(gl_panel_5.createSequentialGroup() .addGap(3)
		 * .addComponent(lblNewLabel_8, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
		 * .addComponent(textFieldHansd, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
		 * .addGap(5) .addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
		 * .addGroup(gl_panel_5.createSequentialGroup() .addComponent(textFieldGiaban,
		 * GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE) .addGap(8)
		 * .addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
		 * .addGroup(gl_panel_5.createSequentialGroup() .addGap(1)
		 * .addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 26,
		 * GroupLayout.PREFERRED_SIZE))) .addGap(11))
		 * .addGroup(gl_panel_5.createSequentialGroup()
		 * .addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
		 * .addGroup(gl_panel_5.createSequentialGroup() .addGap(10)
		 * .addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
		 * .addGroup(gl_panel_5.createSequentialGroup() .addGap(1)
		 * .addComponent(btnCapNhatAnh, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
		 * .addGroup(gl_panel_5.createSequentialGroup() .addGap(2)
		 * .addComponent(textFieldImg, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
		 * .addGroup(gl_panel_5.createSequentialGroup() .addGap(4)
		 * .addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 25,
		 * Short.MAX_VALUE)))) .addGroup(gl_panel_5.createSequentialGroup()
		 * .addContainerGap() .addComponent(textFieldMasp, GroupLayout.DEFAULT_SIZE, 29,
		 * Short.MAX_VALUE)) .addGroup(gl_panel_5.createSequentialGroup() .addGap(14)
		 * .addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)))
		 * .addPreferredGap(ComponentPlacement.RELATED)
		 * .addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
		 * .addGroup(gl_panel_5.createSequentialGroup() .addGap(4)
		 * .addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
		 * .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 28,
		 * GroupLayout.PREFERRED_SIZE) .addComponent(lblNewLabel_6,
		 * GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
		 * .addGroup(gl_panel_5.createSequentialGroup() .addGap(6)
		 * .addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
		 * .addGroup(gl_panel_5.createSequentialGroup() .addGap(2)
		 * .addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
		 * .addGroup(gl_panel_5.createSequentialGroup() .addGap(5)
		 * .addComponent(lblNewLabel_7, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
		 * .addComponent(textFieldDonvi, GroupLayout.DEFAULT_SIZE, 28,
		 * Short.MAX_VALUE)))) .addGap(50)) .addGroup(gl_panel_5.createSequentialGroup()
		 * .addGap(11) .addComponent(textFieldTensp, GroupLayout.DEFAULT_SIZE, 28,
		 * Short.MAX_VALUE) .addGap(3) .addComponent(textFieldGianhap,
		 * GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE) .addGap(52))) .addGap(2)) );
		 * panel_5.setLayout(gl_panel_5);
		 */
		panel_2.setLayout(gl_panel_2);
		header.setPreferredSize(new Dimension(header.getWidth(), 30));

		JPanel panel_3 = new JPanel();
		//tabbedPane.addTab("Thông Tin Loại Hàng", null, panel_3, null);

		textMaSP1 = new JTextField();
		textMaSP1.setHorizontalAlignment(SwingConstants.CENTER);
		textMaSP1.setFont(new Font("Arial", Font.BOLD, 14));
		textMaSP1.setEnabled(false);
		textMaSP1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Mã Sản Phẩm");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);

		textTenSp = new JTextField();
		textTenSp.setFont(new Font("Arial", Font.BOLD, 14));
		textTenSp.setEnabled(false);
		textTenSp.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Tên Loại Hàng");
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		btnThem_1.setFont(new Font("Arial", Font.BOLD, 14));
		btnThem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue();
				textTenSp.setEnabled(true);
				btnLuu_1.setEnabled(true);
				try {
					LoaiHangDAL LHD = new LoaiHangDAL();
					int maLh = LHD.getLastMaLH();
					maLh++;
					textMaSP1.setText("" + maLh);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				textMaSP1.setEnabled(false);
				addbtn = true;
				fixbtn = false;

			}
		});

		btnThem_1.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Add.png"))));

		btnThem_1.setFocusPainted(false);
		btnSua_1.setFont(new Font("Arial", Font.BOLD, 14));
		btnSua_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnThem_1.setEnabled(false);
				btnLuu_1.setEnabled(true);
				btnXoa_1.setEnabled(false);
				textTenSp.setEnabled(true);
				try {
					hienThiMaSanPham();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				oldTenMaLH = textTenSp.getText();
				fixbtn = true;
				addbtn = false;

			}
		});

		btnSua_1.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Change.png"))));
		btnSua_1.setFocusPainted(false);
		btnSua_1.setEnabled(false);
		btnXoa_1.setFont(new Font("Arial", Font.BOLD, 14));
		btnXoa_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					LoaiHang LH = new LoaiHang();
					LH.setMaLH(Integer.parseInt(textMaSP1.getText()));
					LoaiHangDAL LHD = new LoaiHangDAL();
					int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa loại hàng này", "Confirmation",
							JOptionPane.YES_NO_OPTION);
					if (confirmed == JOptionPane.YES_OPTION) {
						if (LHD.ThemLoaiHang(LH, "xoaloaihang")) {

							JOptionPane.showMessageDialog(contentPane, "Xóa Loại Hàng Thành Công!");
							resetValue();
							hienThiMaSanPham();
							btnXoa_1.setEnabled(false);
							btnThem_1.setEnabled(true);
						}
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		btnXoa_1.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Delete.png"))));
		btnXoa_1.setFocusPainted(false);
		btnXoa_1.setEnabled(false);
		btnLuu_1.setFont(new Font("Arial", Font.BOLD, 14));
		btnLuu_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkEmtyValueTabbed2()) {
						LoaiHang LH = new LoaiHang();
						LH.setMaLH(Integer.parseInt(textMaSP1.getText()));
						LH.setTenLH(textTenSp.getText());
						LoaiHangDAL LHD;
						if (addbtn) {
							try {
								LHD = new LoaiHangDAL();
								if (LHD.ThemLoaiHang(LH, "themloaihang")) {
									JOptionPane.showMessageDialog(contentPane, "Thêm Thành công!");
									resetValue();
									hienThiMaSanPham();
									addbtn = false;
								}
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(contentPane, "Thêm Thất bại!");
							}
						}
						if (fixbtn) {
							try {
								LHD = new LoaiHangDAL();
								if (LHD.ThemLoaiHang(LH, "sualoaihang")) {
									JOptionPane.showMessageDialog(contentPane, "Sửa Thành công!");
									resetValue();
									btnThem_1.setEnabled(true);
									hienThiMaSanPham();
									fixbtn = false;
								}
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(contentPane, "Sửa Thất bại!");
							}
						}

					}
				} catch (NumberFormatException | HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnLuu_1.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Save.png"))));
		btnLuu_1.setFocusPainted(false);
		btnLuu_1.setEnabled(false);

		JPanel panel_9 = new JPanel();

		JScrollPane scrollPane_1 = new JScrollPane();

		JLabel lblLoiHngSiu = new JLabel();
		lblLoiHngSiu.setText("LOẠI HÀNG SIÊU THỊ");
		lblLoiHngSiu.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoiHngSiu.setForeground(new Color(255, 128, 128));
		lblLoiHngSiu.setFont(new Font("Arial", Font.BOLD, 20));
		table_1.setDefaultEditor(Object.class, null);
		JTableHeader header1 = table_1.getTableHeader();
		scrollPane_1.setViewportView(table_1);
		table_1.setRowHeight(30);
		table_1.setFocusable(false);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup().addGap(140)
						.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE).addGap(10)
						.addComponent(textMaSP1, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE).addGap(125)
						.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE).addGap(10)
						.addComponent(textTenSp, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE).addGap(226))
				.addGroup(gl_panel_3.createSequentialGroup().addGap(169)
						.addComponent(btnThem_1, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE).addGap(54)
						.addComponent(btnSua_1, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE).addGap(73)
						.addComponent(btnXoa_1, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE).addGap(88)
						.addComponent(btnLuu_1, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE).addGap(151))
				.addComponent(panel_9, GroupLayout.DEFAULT_SIZE, 1077, Short.MAX_VALUE));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup().addGap(10)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(textMaSP1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(textTenSp, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(btnThem_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSua_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnXoa_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnLuu_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
						.addGap(10)
						.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)));
		GroupLayout gl_panel_9 = new GroupLayout(panel_9);
		gl_panel_9.setHorizontalGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createSequentialGroup().addGap(209)
						.addComponent(lblLoiHngSiu, GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE).addGap(264))
				.addGroup(gl_panel_9.createSequentialGroup().addGap(133)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE).addGap(152)));
		gl_panel_9.setVerticalGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createSequentialGroup().addGap(10)
						.addComponent(lblLoiHngSiu, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addGap(6)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)));
		panel_9.setLayout(gl_panel_9);
		panel_3.setLayout(gl_panel_3);
		table_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table_1.getSelectedRow(); // get the selected row
				String maLh = table_1.getModel().getValueAt(row, 0).toString();
				String tenLh = table_1.getModel().getValueAt(row, 1).toString(); // get the value of the first column

				textMaSP1.setText(maLh);
				textTenSp.setText(tenLh);

				setEnable();
				btnXoa_1.setEnabled(true);
				btnSua_1.setEnabled(true);
				btnThem_1.setEnabled(true);
				btnLuu_1.setEnabled(false);

			}
		});

		
		JPanel panel_4 = new JPanel();
		//tabbedPane.addTab("Thông Tin Nhà Cung Cấp",null, panel_4, null);
		 

		JLabel lblNewLabel_11 = new JLabel("Mã Nhà Cung Cấp");
		lblNewLabel_11.setFont(new Font("Arial", Font.BOLD, 12));

		JLabel lblNewLabel_12 = new JLabel("Tên Nhà Cung Cấp");
		lblNewLabel_12.setFont(new Font("Arial", Font.BOLD, 12));

		JLabel lblNewLabel_13 = new JLabel("Địa Chỉ");
		lblNewLabel_13.setFont(new Font("Arial", Font.BOLD, 12));

		JLabel lblNewLabel_14 = new JLabel("Số Điện Thoại");
		lblNewLabel_14.setFont(new Font("Arial", Font.BOLD, 12));

		textNhaCC = new JTextField();
		textNhaCC.setHorizontalAlignment(SwingConstants.CENTER);
		textNhaCC.setFont(new Font("Arial", Font.BOLD, 14));
		textNhaCC.setEnabled(false);
		textNhaCC.setColumns(10);

		textTenNcc = new JTextField();
		textTenNcc.setFont(new Font("Arial", Font.BOLD, 14));
		textTenNcc.setEnabled(false);
		textTenNcc.setColumns(10);

		textDiaChiCC = new JTextField();
		textDiaChiCC.setFont(new Font("Arial", Font.BOLD, 14));
		textDiaChiCC.setEnabled(false);
		textDiaChiCC.setColumns(10);

		textSDTNCC = new JTextField();
		textSDTNCC.setFont(new Font("Arial", Font.BOLD, 14));
		textSDTNCC.setEnabled(false);
		textSDTNCC.setColumns(10);

		JPanel panel_10 = new JPanel();

		JScrollPane scrollPane_2 = new JScrollPane();
		JTableHeader header2 = table.getTableHeader();
		header2.setPreferredSize(new Dimension(header2.getWidth(), 30));
		table_2.setDefaultEditor(Object.class, null);
		table_2.setRowHeight(30);
		table_2.setFocusable(false);
		table_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table_2.getSelectedRow(); // get the selected row
				String maNCC = table_2.getModel().getValueAt(row, 0).toString();
				String tenNCC = table_2.getModel().getValueAt(row, 1).toString(); // get the value of the first column
				String diaChi = table_2.getModel().getValueAt(row, 2).toString();
				String dienThoai = table_2.getModel().getValueAt(row, 3).toString();
				textNhaCC.setText(maNCC);
				textTenNcc.setText(tenNCC);
				textDiaChiCC.setText(diaChi);
				textSDTNCC.setText(dienThoai);
				textDiaChiCC.setEnabled(false);
				textSDTNCC.setEnabled(false);
				textTenNcc.setEnabled(false);
				btnXoa_2.setEnabled(true);
				btnSua_2.setEnabled(true);
				btnThem_2.setEnabled(true);
				btnLuu_2.setEnabled(false);

			}
		});

		JLabel lblNhCungCp = new JLabel();
		lblNhCungCp.setText("NHÀ CUNG CẤP");
		lblNhCungCp.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhCungCp.setForeground(new Color(255, 128, 128));
		lblNhCungCp.setFont(new Font("Arial", Font.BOLD, 20));
		GroupLayout gl_panel_10 = new GroupLayout(panel_10);
		gl_panel_10.setHorizontalGroup(gl_panel_10.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_10
				.createSequentialGroup()
				.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_10.createSequentialGroup().addGap(163)
								.addComponent(lblNhCungCp, GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE).addGap(157))
						.addGroup(gl_panel_10.createSequentialGroup().addGap(51).addComponent(scrollPane_2,
								GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE)))
				.addGap(57)));
		gl_panel_10.setVerticalGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup().addContainerGap()
						.addComponent(lblNhCungCp, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(43, Short.MAX_VALUE)));

		scrollPane_2.setViewportView(table_2);
		panel_10.setLayout(gl_panel_10);
		btnThem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNhaCC.setText("");
				textDiaChiCC.setText("");
				textSDTNCC.setText("");
				textTenNcc.setText("");
//				textNhaCC.setEnabled(true);
				textDiaChiCC.setEnabled(true);
				textSDTNCC.setEnabled(true);
				textTenNcc.setEnabled(true);
				btnSua_2.setEnabled(false);
				btnXoa_2.setEnabled(false);
				btnLuu_2.setEnabled(true);
				try {
					NhaCungCapDAL LHD = new NhaCungCapDAL();
					int maNcc = LHD.getLastMaNCC();
					maNcc++;
					textNhaCC.setText("" + maNcc);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				addbtn = true;
				fixbtn = false;
			}
		});

		btnThem_2.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Add.png"))));
		btnThem_2.setFocusPainted(false);
		btnSua_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnThem_2.setEnabled(false);
				btnLuu_2.setEnabled(true);
				btnXoa_2.setEnabled(false);
				textTenNcc.setEnabled(true);
				textDiaChiCC.setEnabled(true);
				textSDTNCC.setEnabled(true);
				try {
					hienThiNhaCungCap();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				oldTenNcc = textTenNcc.getText();
				fixbtn = true;
				addbtn = false;

			}

		});

		btnSua_2.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Change.png"))));
		btnSua_2.setFocusPainted(false);
		btnSua_2.setEnabled(false);

		btnXoa_2.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Delete.png"))));
		btnXoa_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					NhaCungCap NCC = new NhaCungCap();

					NCC.setMaNCC(Integer.parseInt(textNhaCC.getText()));
					NhaCungCapDAL LHD = new NhaCungCapDAL();
					int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa nhà cung cấp này", "Confirmation",
							JOptionPane.YES_NO_OPTION);
					if (confirmed == JOptionPane.YES_OPTION) {
						if (LHD.ThemNhaCungCap(NCC, "xoanhacungcap")) {

							JOptionPane.showMessageDialog(contentPane, "Xóa Nhà Cung Cấp Thành Công!");
							resetValueTabbed3();
							hienThiNhaCungCap();
							btnXoa_2.setEnabled(false);
							btnThem_2.setEnabled(true);
						}
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});
		btnXoa_2.setFocusPainted(false);
		btnXoa_2.setEnabled(false);
		btnLuu_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkEmtyValueTabbed3()) {
						NhaCungCap NCC = new NhaCungCap();
						NCC.setMaNCC(Integer.parseInt(textNhaCC.getText()));
						NCC.setTenNCC(textTenNcc.getText());
						NCC.setDiaChi(textDiaChiCC.getText());
						NCC.setSoDT(textSDTNCC.getText());
						NhaCungCapDAL NCCD;
						if (addbtn) {
							try {
								NCCD = new NhaCungCapDAL();
								if (NCCD.ThemNhaCungCap(NCC, "themnhacungcap")) {
									JOptionPane.showMessageDialog(contentPane, "Thêm Thành công!");
									resetValueTabbed3();
									hienThiNhaCungCap();
									addbtn = false;
								}
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(contentPane, "Thêm Thất bại!");
							}
						}
						if (fixbtn) {
							try {
								NCCD = new NhaCungCapDAL();
								if (NCCD.ThemNhaCungCap(NCC, "suanhacungcap")) {
									JOptionPane.showMessageDialog(contentPane, "Sửa Thành công!");
									resetValueTabbed3();
									btnThem_2.setEnabled(true);
									hienThiNhaCungCap();
									fixbtn = false;
								}
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(contentPane, "Sửa Thất bại!");
							}
						}

					}
				} catch (NumberFormatException | HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnLuu_2.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Save.png"))));
		btnLuu_2.setFocusPainted(false);
		btnLuu_2.setEnabled(false);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup().addGap(10)
						.addComponent(lblNewLabel_11, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE).addGap(3)
						.addComponent(textNhaCC, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE).addGap(10)
						.addComponent(lblNewLabel_12, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE).addGap(10)
						.addComponent(textTenNcc, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE).addGap(20)
						.addComponent(lblNewLabel_13, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE).addGap(10)
						.addComponent(textDiaChiCC, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE).addGap(29)
						.addComponent(lblNewLabel_14, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE).addGap(13)
						.addComponent(textSDTNCC, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE).addGap(10))
				.addGroup(gl_panel_4.createSequentialGroup().addGap(100)
						.addComponent(btnThem_2, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE).addGap(100)
						.addComponent(btnSua_2, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE).addGap(98)
						.addComponent(btnXoa_2, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE).addGap(90)
						.addComponent(btnLuu_2, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE).addGap(165))
				.addGroup(gl_panel_4.createSequentialGroup().addGap(61)
						.addComponent(panel_10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(70)));
		gl_panel_4.setVerticalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_4
				.createSequentialGroup().addGap(10)
				.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(textNhaCC, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(textTenNcc, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_13, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_4.createSequentialGroup().addGap(1).addComponent(textDiaChiCC,
								GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_14, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(textSDTNCC, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
				.addGap(34)
				.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(btnThem_2, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSua_2, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnXoa_2, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLuu_2, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
				.addGap(7).addComponent(panel_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)));
		panel_4.setLayout(gl_panel_4);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1
				.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGap(0, 1082, Short.MAX_VALUE));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGap(0, 522, Short.MAX_VALUE));

		panel_1.setLayout(gl_panel_1);

		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lbIconShop = new JLabel("");
		lbIconShop.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		icon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//shop.png")));
		Image image = icon.getImage();
		Image resizedImg = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImg);
		lbIconShop.setIcon(resizedIcon);

		JLabel lblNewLabel_10 = new JLabel("");
		icon = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Background2.png")));
		Image image2 = icon.getImage();
		Image resizedImg2 = image2.getScaledInstance(2000, 100, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon2 = new ImageIcon(resizedImg2);
		lblNewLabel_10.setIcon(resizedIcon2);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(837)
						.addComponent(lbIconShop, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE).addGap(81))
				.addGroup(gl_panel.createSequentialGroup().addGap(142)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE).addGap(282))
				.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 1088, Short.MAX_VALUE));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(18).addComponent(lbIconShop,
						GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup().addGap(20).addComponent(lblNewLabel,
						GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
				.addComponent(lblNewLabel_10, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE));
		panel.setLayout(gl_panel);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1078, Short.MAX_VALUE)
					.addGap(10))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
					.addGap(0))
		);
		contentPane.setLayout(gl_contentPane);
		hienthisanpham("hien thi");
		hienThiMaSanPham();

	}
}
