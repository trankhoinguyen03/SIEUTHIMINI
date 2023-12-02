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

import BLL.DangNhapBLL;
import BLL.KhoBLL;
import BLL.LoaiHangBLL;
import BLL.NhaCungCapBLL;
import BLL.SanPhamBLL;
import BLL.TaiKhoanBLL;
import DTO.LoaiHang;
import DTO.NhaCungCap;
import DTO.NhanVien;
import DTO.SanPham;
import DTO.TaiKhoan;
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

public class QuanLySanPhamGui extends JFrame {

	private JPanel contentPane;
	//private JTextField textFieldImg;
	private JTextField textFieldMasp;
	private JTextField textFieldTensp;
	private JTextField textFieldGianhap;
	private JTextField textFieldNsx;
	private JTextField textFieldGiaban;
	private JTable table;
	private JTextField textFieldHsd;
	private JTextField textFieldLoaiHang;

	/**
	 * Launch the application.
	 */
	
	//NhanVien nhanVien = ShareDAta.nhanVien;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLySanPhamGui frame = new QuanLySanPhamGui();
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
	JLabel lbThemanh = new JLabel();
	File selectedFile;
	ImageIcon icon = new ImageIcon();
	//JButton btnCapNhatAnh = new JButton();
	Object lastValueMaSp;
	JButton btnAn = new JButton("Ẩn");
	JButton btnSua = new JButton("Sửa");
	boolean isNumber = true;
	//JButton btnThem = new JButton("Thêm");
	int lastRow = 0;
	JRadioButton radio1 = new JRadioButton("Loại hàng");
	JLabel lblNewLabel = new JLabel("QUẢN LÝ SẢN PHẨM");
	JRadioButton radio2 = new JRadioButton("Giá bán");
	ButtonGroup btg = new ButtonGroup();
	JScrollPane scrollPane = new JScrollPane();
	boolean addbtn, fixbtn = false;
	JComboBox comboBoxSearch = new JComboBox();
	int rsRenderType = 0;
	//JTextField textFieldSearch = new JTextField();
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
	//JButton btnAn_1 = new JButton("Ẩn");
	JButton btnLuu_1 = new JButton("Lưu");
	JButton btnAn_1 = new JButton("Ẩn");
	JButton btnSua_1 = new JButton("Sửa");
	boolean addlh, fixlh = false;
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
	JButton btnAn_2 = new JButton("Ẩn");
	JButton btnLuu_2 = new JButton("Lưu");
	boolean addncc, fixncc = false;
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
		btnAn_2.setEnabled(false);
		btnLuu_2.setEnabled(false);
	}

	public static boolean validatePhone(String phoneNumber) {
		String regex = "^0[0-9]{9}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}

	public void hienthisanpham(String condition) throws SQLException {
		

		ArrayList<SanPham> arrSp = new ArrayList<SanPham>();
		SanPhamBLL spbll = new SanPhamBLL();
		if (condition == "hien thi") {

			arrSp = spbll.getSanPham();
		}
		if (condition == "timkiemtheoloaihang") {

			arrSp = spbll.findByLoaiHang(comboBoxSearch.getSelectedItem().toString());

		}
		if (arrSp.isEmpty() && btnfind || arrSp.isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Không tìm thấy sản phẩm!");
			btnfind = false;
			return;
		}
		String[] columnNames = { "Loại Hàng", "Mã Sản Phẩm", "Tên Sản Phẩm", "Giá Mua", "Giá Bán", "Ngày Sản Xuất", "Hạn Sử Dụng" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);

		table.setModel(model);
		model.setRowCount(0);
		for (SanPham spdata : arrSp) {
			Object[] row = new Object[] {spdata.getMaLh(), spdata.getMaSp(), spdata.getTenSp(),
					spdata.getGiaMua(), spdata.getGiaBan(), spdata.getNgaySanXuat() ,spdata.getHanSuDung() };

			model.addRow(row);
		}
		JTableHeader header = table.getTableHeader();
		Font headerFont = header.getFont(); // get the current font of the header
		header.setFont(new Font(headerFont.getName(), Font.BOLD, 14)); // set the new font for the header with size 16
		lastRow = table.getRowCount() - 1; // get index of the last row
		lastValueMaSp = table.getValueAt(lastRow, 1); // get the value at the last row and column n
	}

	public void hienThiLoaiHang() throws SQLException {

		LoaiHangBLL lhb = new LoaiHangBLL();
		ArrayList<LoaiHang> arrLH = new ArrayList<LoaiHang>();
		String[] columnNames = { "Mã Loại Hàng", "Tên Loại Hàng" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		table_1.setModel(model);
		model.setRowCount(0);
		arrLH = lhb.getLoaiHang();
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
		NhaCungCapBLL ncc = new NhaCungCapBLL();
		arrNCC = ncc.getNhaCungCap();
		String[] columnNames = { "Mã Nhà Cung Cấp", "Tên Nhà Cung Cấp", "Địa Chỉ", "Số Điện Thoại" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		table_2.setModel(model);
		model.setRowCount(0);
		for (NhaCungCap nccData : arrNCC) {

			Object[] row = new Object[] {nccData.getMaNCC(), nccData.getTenNCC(), nccData.getDiaChi(),
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
		//textFieldImg.setText("");
		//textFieldImg.setEnabled(true);
		textFieldLoaiHang.setText("");
		textFieldLoaiHang.setEnabled(true);
		textFieldMasp.setText("");
		textFieldMasp.setEnabled(true);
		textFieldHsd.setText("");
		textFieldHsd.setEnabled(true);
		textFieldGiaban.setText("");
		textFieldGiaban.setEnabled(true);
		textFieldGianhap.setText("");
		textFieldGianhap.setEnabled(true);
		textFieldNsx.setText("");
		textFieldNsx.setEnabled(true);
		textFieldTensp.setText("");
		textFieldTensp.setEnabled(true);
		//btnCapNhatAnh.setEnabled(true);
		btnSua.setEnabled(false);
		btnAn.setEnabled(false);

//		Tabbed 2
		textMaSP1.setText("");
		textMaSP1.setEnabled(true);
		textTenSp.setText("");
		textTenSp.setEnabled(true);
		btnAn_1.setEnabled(false);
		btnLuu_1.setEnabled(false);
		btnSua_1.setEnabled(false);

	}

	public void unSetEnable() {
		//textFieldImg.setEnabled(true);
		textFieldLoaiHang.setEnabled(true);
		textFieldMasp.setEnabled(true);
		textFieldHsd.setEnabled(true);
		textFieldGiaban.setEnabled(true);
		textFieldGianhap.setEnabled(true);
		textFieldNsx.setEnabled(true);
		textFieldTensp.setEnabled(true);

		//btnCapNhatAnh.setEnabled(true);
		//btnThem.setEnabled(true);
		//btnAn.setEnabled(false);
		btnThem_1.setEnabled(true);
		btnSua_1.setEnabled(false);
		btnLuu_1.setEnabled(false);
		textTenSp.setEnabled(true);

	}

	public void setEnable() {

		//textFieldImg.setEnabled(false);
		textFieldLoaiHang.setEnabled(false);
		textFieldMasp.setEnabled(false);
		textFieldHsd.setEnabled(false);
		textFieldGiaban.setEnabled(false);
		textFieldGianhap.setEnabled(false);
		textFieldNsx.setEnabled(false);
		textFieldTensp.setEnabled(false);
		btnAn.setEnabled(false);
		btnSua.setEnabled(false);
		textTenSp.setEnabled(false);
	}

	public Boolean checkEmtyValue() throws SQLException {
		// regular expression pattern
		if(textFieldTensp.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Tên Sản Phẩm Trống!");
			textFieldTensp.requestFocus();
			return false;
		}
		else {
			SanPhamBLL spbll = new SanPhamBLL();
			ArrayList<SanPham> arr = new ArrayList<SanPham>();
			arr = spbll.getSanPham();
			boolean flag = true;
			for(SanPham data: arr) {
				if (data.getTenSp().equalsIgnoreCase(textFieldTensp.getText()) && !data.getMaSp().equals(textFieldMasp.getText())) {
					flag = false;
				}
				if (data.getTenSp().equalsIgnoreCase(textFieldTensp.getText().toLowerCase()) && data.getMaSp().equals(textFieldMasp.getText())) {
    				flag = true;
    				break;
				}
			}
			if(!flag) {
				JOptionPane.showMessageDialog(contentPane, "Tên sản phẩm đã tồn tại!");
				textFieldTensp.requestFocus();
				textFieldTensp.selectAll();
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
		else {
			LoaiHangBLL bll = new LoaiHangBLL();
			ArrayList<LoaiHang> arr = new ArrayList<LoaiHang>();
			arr = bll.getLoaiHang();
			boolean flag = true;
			for(LoaiHang data: arr) {
				if (data.getTenLH().equalsIgnoreCase(textTenSp.getText().toLowerCase()) && !data.getMaLH().equals(textMaSP1.getText())) {
					flag = false;
				}
				if (data.getTenLH().equalsIgnoreCase(textTenSp.getText().toLowerCase()) && data.getMaLH().equals(textMaSP1.getText())) {
    				flag = true;
    				break;
				}
			}
			if(!flag) {
				JOptionPane.showMessageDialog(contentPane, "Tên loại hàng đã tồn tại!");
				textTenSp.requestFocus();
				textTenSp.selectAll();
				return false;
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
		else {
			NhaCungCapBLL bll = new NhaCungCapBLL();
			ArrayList<NhaCungCap> arr = new ArrayList<NhaCungCap>();
			arr = bll.getNhaCungCap();
			boolean flag = true;
			for(NhaCungCap data: arr) {
				if (data.getTenNCC().equalsIgnoreCase(textTenNcc.getText().toLowerCase()) && !data.getMaNCC().equals(textNhaCC.getText())) {
					flag = false;
				}
				if (data.getTenNCC().equalsIgnoreCase(textTenNcc.getText().toLowerCase()) && data.getMaNCC().equals(textNhaCC.getText())) {
    				flag = true;
    				break;
				}
			}
			if(!flag) {
				JOptionPane.showMessageDialog(contentPane, "Tên nhà cung cấp đã tồn tại!");
				textTenNcc.requestFocus();
				textTenNcc.selectAll();
				return false;
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
			Pattern reg = Pattern.compile("((09|03|07|08|05)+([0-9]{8})\\b)");
            boolean kt = reg.matcher(textSDTNCC.getText()).matches();
            if(kt == false) {
                JOptionPane.showMessageDialog(contentPane, "Không tồn tại định dạng số điện thoại này!");
                textSDTNCC.requestFocus();
                return false;
            }

		}
		return true;
	}
	
	TaiKhoan taiKhoan = DangNhapBLL.taiKhoan;
	private JTextField txtSearch;
	private JTextField txtTimKiem;
	public QuanLySanPhamGui() throws SQLException {

		
		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int selectedIndex = tabbedPane.getSelectedIndex();
				if (selectedIndex == 0) {

					lblNewLabel.setText("QUẢN LÝ SẢN PHẨM");
					LoaiHangBLL lhb;
					try {
						lhb = new LoaiHangBLL();
						ArrayList<LoaiHang> arrLh = new ArrayList<LoaiHang>();
						arrLh = lhb.getLoaiHang();

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
						hienThiLoaiHang();
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
		setTitle("Quản lý sản phẩm");

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

		tabbedPane.addTab("Thông tin sản phẩm", null, panel_2, null);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));

		/*
		 * btnCapNhatAnh.setText("Cập nhật ảnh"); btnCapNhatAnh.setEnabled(false);
		 * btnCapNhatAnh.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { JFileChooser fileChooser = new
		 * JFileChooser(); FileNameExtensionFilter imageFilter = new
		 * FileNameExtensionFilter("Images", "jpg", "png");
		 * fileChooser.setFileFilter(imageFilter);
		 * fileChooser.setMultiSelectionEnabled(false); int returnVal =
		 * fileChooser.showDialog(tabbedPane, "Chọn ảnh"); if (returnVal ==
		 * JFileChooser.APPROVE_OPTION) { selectedFile = fileChooser.getSelectedFile();
		 * 
		 * ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath()); Image image =
		 * icon.getImage(); g.drawImage(image, 0, 0, newWidth, newHeight, null); //
		 * g.dispose(); ImageIcon resizedIcon = new ImageIcon(resizedImage);
		 * lbThemanh.setIcon(resizedIcon);
		 * textFieldImg.setText(selectedFile.getAbsolutePath());
		 * 
		 * } } }); btnCapNhatAnh.setFocusPainted(false); btnCapNhatAnh.setFont(new
		 * Font("Arial", Font.BOLD, 12));
		 * 
		 * textFieldImg = new JTextField(); textFieldImg.setEnabled(false);
		 * textFieldImg.setEditable(false); textFieldImg.setColumns(10);
		 */

		JLabel lblNewLabel_1 = new JLabel("Mã sản phẩm");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 12));

		textFieldMasp = new JTextField();
		textFieldMasp.setFont(new Font("Arial", Font.BOLD, 12));
		textFieldMasp.setEnabled(false);
		textFieldMasp.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Tên sản phẩm");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 12));

		textFieldTensp = new JTextField();
		textFieldTensp.setFont(new Font("Arial", Font.BOLD, 12));
		textFieldTensp.setEnabled(false);
		textFieldTensp.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Giá nhập");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 12));

		textFieldGianhap = new JTextField();
		textFieldGianhap.setFont(new Font("Arial", Font.BOLD, 12));
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

		JLabel lblNewLabel_6 = new JLabel("Loại hàng");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 12));

		textFieldLoaiHang = new JTextField();
		textFieldLoaiHang.setFont(new Font("Arial", Font.BOLD, 10));
		textFieldLoaiHang.setEnabled(false);
		textFieldLoaiHang.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Hạn sử dụng");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 12));

		JLabel lblNewLabel_8 = new JLabel("Ngày sản xuất");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Arial", Font.BOLD, 12));

		textFieldNsx = new JTextField();
		textFieldNsx.setFont(new Font("Arial", Font.BOLD, 12));
		textFieldNsx.setEnabled(false);
		textFieldNsx.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Giá bán");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setFont(new Font("Arial", Font.BOLD, 12));

		textFieldGiaban = new JTextField();
		textFieldGiaban.setFont(new Font("Arial", Font.BOLD, 12));
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
		textFieldHsd = new JTextField();
		textFieldHsd.setFont(new Font("Arial", Font.BOLD, 12));
		textFieldHsd.setEnabled(false);
		textFieldHsd.setColumns(10);

		JPanel panel_6 = new JPanel();
		
		/*
		 * btnAn.setFont(new Font("Arial", Font.BOLD, 12));
		 * if(taiKhoan.getQuyen().equals("RL2")) { btnAn.setVisible(true); } else {
		 * btnAn.setVisible(false); } btnAn.setEnabled(false); btnAn.setIcon(new
		 * ImageIcon(
		 * Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(
		 * ".\\Image\\Delete.png")))); btnAn.setFocusPainted(false);
		 * btnAn.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { int confirmed =
		 * JOptionPane.showConfirmDialog(null, "Bạn muốn ẩn sản phẩm này",
		 * "Confirmation", JOptionPane.YES_NO_OPTION); if (confirmed ==
		 * JOptionPane.YES_OPTION) { SanPhamBLL hideSp; KhoBLL kho; try { hideSp = new
		 * SanPhamBLL(); kho = new KhoBLL(); if
		 * (hideSp.hideSanPham(textFieldMasp.getText())) {
		 * if(kho.hideSanPham(textFieldMasp.getText())) {
		 * JOptionPane.showMessageDialog(contentPane, "Ẩn sản phẩm thành công!");
		 * hienthisanpham("hien thi"); resetValue();
		 * comboBoxSearch.setSelectedItem("Tất cả"); setEnable(); } } } catch
		 * (SQLException e1) { // TODO Auto-generated catch block e1.printStackTrace();
		 * }
		 * 
		 * }
		 * 
		 * } });
		 */

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));

		JButton btnDongBo = new JButton("");
		btnDongBo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.createImage(QuanLySanPhamGui.class.getResource(".\\Image\\Refresh-icon.png"))));
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
				// Create an ArrayList to store the data from the JTable
				ArrayList<SanPham> data = new ArrayList<>();

				// Get the number of rows and columns in the JTable
				int numRows = table.getRowCount();
				// Loop through each row in the JTable and add the data to the ArrayList
				for (int i = 0; i < numRows; i++) {
					SanPham sp = new SanPham();
					sp.setMaLh((String) table.getValueAt(i, 0));
					sp.setMaSp((String) table.getValueAt(i, 1));
					sp.setTenSp((String) table.getValueAt(i, 2));
					sp.setGiaMua(((String) table.getValueAt(i, 3)));
					sp.setGiaBan(((String) table.getValueAt(i, 4)));
					sp.setNgaySanXuat((String) table.getValueAt(i, 5));
					sp.setHanSuDung((String) table.getValueAt(i, 6));
					data.add(sp);

				}
				if (radio1.isSelected()) {
					

					Collections.sort(data, new Comparator<SanPham>() {
					    public int compare(SanPham sp1, SanPham sp2) {
					        return sp1.getMaLh().compareTo(sp2.getMaLh());
					    }
					});
				}
				
				if (radio2.isSelected()) {
				  
					Collections.sort(data, new Comparator<SanPham>(){
						public int compare(SanPham sp1, SanPham sp2) {
							return Integer.parseInt(sp1.getGiaBan()) - Integer.parseInt(sp2.getGiaBan());
						}
					});
				}
				resetValue();
				
				String[] columnNames = { "Loại Hàng", "Mã Sản Phẩm", "Tên Sản Phẩm", "Giá Mua", "Giá Bán", "Ngày Sản Xuất", "Hạn Sử Dụng" };
				DefaultTableModel model = new DefaultTableModel(columnNames, 0);

				table.setModel(model);
				model.setRowCount(0);
				for (SanPham sp : data) {
					Object[] row = {sp.getMaLh(), sp.getMaSp(), sp.getTenSp(), sp.getGiaMua(), sp.getGiaBan(),sp.getNgaySanXuat(),sp.getHanSuDung() };
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

		lbThongbao.setText("SẢN PHẨM SIÊU THỊ");

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
				String giaMua = table.getModel().getValueAt(row, 3).toString();
				String giaBan = table.getModel().getValueAt(row, 4).toString();
				String nSX = table.getModel().getValueAt(row, 5).toString();
				String hSD = table.getModel().getValueAt(row, 6).toString();
				textFieldMasp.setText(maSP);
				textFieldTensp.setText(tenSP);
				textFieldGianhap.setText(giaMua);
				textFieldGiaban.setText(giaBan);
				textFieldNsx.setText(nSX);
				textFieldHsd.setText(hSD);

				setEnable();
				textFieldTensp.setEnabled(true);
				btnSua.setEnabled(true);
				btnAn.setEnabled(true);
				

//				g.dispose();

				LoaiHangBLL test;
				try {
					test = new LoaiHangBLL();
					String loaiHang = test.getTenLH(maLh);
					textFieldLoaiHang.setText(loaiHang);

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
 				if(taiKhoan.getQuyen().equals("RL4")) {
 					NhanVienKhoHome hnv = new NhanVienKhoHome();
 					hnv.setLocationRelativeTo(null);
 					hnv.setVisible(true);
 				}
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 1063, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 1053, Short.MAX_VALUE)
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
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 127, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
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
				try {
					resetValue();
					hienthisanpham("timkiemtheoloaihang");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});


		GroupLayout gl_panel_7_1 = new GroupLayout(panel_7_1);
		gl_panel_7_1.setHorizontalGroup(
			gl_panel_7_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_7_1.createSequentialGroup()
					.addGap(24)
					.addComponent(comboBoxSearch, 0, 189, Short.MAX_VALUE)
					.addGap(49)
					.addPreferredGap(ComponentPlacement.RELATED)
		));
		gl_panel_7_1.setVerticalGroup(
			gl_panel_7_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_7_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_7_1.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_panel_7_1.createParallelGroup(Alignment.BASELINE)
								.addGroup(gl_panel_7_1.createSequentialGroup()
									.addGap(4)
									.addComponent(comboBoxSearch, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
								.addGroup(gl_panel_7_1.createSequentialGroup()
									.addGap(3))))))
		);
		panel_7_1.setLayout(gl_panel_7_1);
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(
			gl_panel_8.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addGap(211)
					.addComponent(lbThongbao, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
					.addGap(269))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1051, Short.MAX_VALUE)
				.addComponent(panel_7_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		gl_panel_8.setVerticalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addComponent(panel_7_1, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbThongbao, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_8.setLayout(gl_panel_8);
		if(taiKhoan.getQuyen().equals("RL2")) {
			btnSua.setVisible(true);
			btnAn.setVisible(true);
			btnSua_1.setVisible(true);
			btnAn_1.setVisible(true);
			btnSua_2.setVisible(true);
			btnAn_2.setVisible(true);
		} else {
			btnSua.setVisible(false);
			btnAn.setVisible(false);
			btnSua_1.setVisible(false);
			btnAn_1.setVisible(false);
			btnSua_2.setVisible(false);
			btnAn_2.setVisible(false);
		}
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SanPhamBLL sp = new SanPhamBLL();
				try {
					if(checkEmtyValue()) {
						int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn sửa sản phẩm "+textFieldMasp.getText(), "Confirmation",
								JOptionPane.YES_NO_OPTION);
						if (confirmed == JOptionPane.YES_OPTION) {
							try {
								if(sp.fixSanPham(textFieldMasp.getText(), textFieldTensp.getText())) {
									JOptionPane.showMessageDialog(contentPane, "Sửa thành công!");
									resetValue();
									setEnable();
									hienthisanpham("hien thi");
								}
								else {
									JOptionPane.showMessageDialog(contentPane, "Sửa thất bại!");
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSua.setEnabled(false);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSua.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Change.png"))));

		btnSua.setFocusPainted(false);
		
		btnAn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn ẩn sản phẩm "+textFieldMasp.getText(), "Confirmation",
						JOptionPane.YES_NO_OPTION);
				if (confirmed == JOptionPane.YES_OPTION) {
					SanPhamBLL spbll = new SanPhamBLL();
					try {
						if(spbll.hideSanPham(textFieldMasp.getText())) {
							JOptionPane.showMessageDialog(contentPane, "Ẩn thành công!");
							resetValue();
							setEnable();
							hienthisanpham("hien thi");
						}
						else {
							JOptionPane.showMessageDialog(contentPane, "Ẩn thất bại!");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		btnAn.setEnabled(false);
		btnAn.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAn.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Delete.png"))));
		btnAn.setFocusPainted(false);
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(25)
					.addComponent(btnSua, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAn, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addGap(24)
					.addComponent(btnDongBo, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(panel_7, GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_6.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addComponent(btnAn, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_6.createSequentialGroup()
								.addComponent(btnSua, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_panel_6.createSequentialGroup()
								.addGroup(gl_panel_6.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnDongBo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
									.addComponent(panel_7, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 53, Short.MAX_VALUE))
								.addGap(15)))))
		);
		panel_6.setLayout(gl_panel_6);
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(9)
					.addComponent(lbThemanh, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(textFieldLoaiHang, 0, 170, Short.MAX_VALUE)
							.addGap(5)))
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_7, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_5.createSequentialGroup()
							.addGap(141)
							.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING)
								.addComponent(textFieldHsd, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addComponent(textFieldMasp, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
							.addGap(10)
							.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldTensp, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
						.addComponent(textFieldGianhap, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
					.addGap(10)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGap(127)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_9, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
								.addComponent(lblNewLabel_8, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldGiaban, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
								.addComponent(textFieldNsx, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(lbThemanh, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGap(14)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
								.addGroup(gl_panel_5.createSequentialGroup()
									.addGap(3)
									.addComponent(lblNewLabel_8, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
								.addComponent(textFieldNsx, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
							.addGap(5)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_5.createSequentialGroup()
									.addComponent(textFieldGiaban, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
									.addGap(8)
									.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
								.addGroup(gl_panel_5.createSequentialGroup()
									.addGap(1)
									.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
							.addGap(11))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_5.createSequentialGroup()
									.addGap(10)
									.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_5.createSequentialGroup()
											.addGap(1))
											//.addComponent(btnCapNhatAnh, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
										.addGroup(gl_panel_5.createSequentialGroup()
											.addGap(2))
											//.addComponent(textFieldImg, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
										.addGroup(gl_panel_5.createSequentialGroup()
											.addGap(4)
											.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))))
								.addGroup(gl_panel_5.createSequentialGroup()
									.addContainerGap()
									.addComponent(textFieldMasp, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
								.addGroup(gl_panel_5.createSequentialGroup()
									.addGap(14)
									.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_5.createSequentialGroup()
									.addGap(4)
									.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
										.addComponent(textFieldLoaiHang, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel_5.createSequentialGroup()
									.addGap(6)
									.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
										.addGroup(gl_panel_5.createSequentialGroup()
											.addGap(2)
											.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
										.addGroup(gl_panel_5.createSequentialGroup()
											.addGap(5)
											.addComponent(lblNewLabel_7, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
										.addComponent(textFieldHsd, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))))
							.addGap(50))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGap(11)
							.addComponent(textFieldTensp, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
							.addGap(3)
							.addComponent(textFieldGianhap, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
							.addGap(52)))
					.addGap(2))
		);
		panel_5.setLayout(gl_panel_5);
		panel_2.setLayout(gl_panel_2);
		header.setPreferredSize(new Dimension(header.getWidth(), 30));

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Thông Tin Loại Hàng", null, panel_3, null);

		textMaSP1 = new JTextField();
		textMaSP1.setHorizontalAlignment(SwingConstants.CENTER);
		textMaSP1.setFont(new Font("Arial", Font.BOLD, 14));
		textMaSP1.setEnabled(false);
		textMaSP1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Mã Loại Hàng");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);

		textTenSp = new JTextField();
		textTenSp.setEnabled(false);
		textTenSp.setFont(new Font("Arial", Font.BOLD, 10));
		textTenSp.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Tên Loại Hàng");
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		btnThem_1.setFont(new Font("Arial", Font.BOLD, 14));
		btnThem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textMaSP1.setText("");
				textTenSp.setText("");
				textTenSp.setEnabled(true);
				btnLuu_1.setEnabled(true);
				try {
					LoaiHangBLL lhbll = new LoaiHangBLL();
                    String lastMaNv = lhbll.getLastMaLH();
                    String maNv = lastMaNv.substring(lastMaNv.length()-3, lastMaNv.length());
                    int check = Integer.parseInt(maNv);
                    if(check < 9) {
                    	textMaSP1.setText("LH00"+(check+1));
                    } else if(check < 99) {
                    	textMaSP1.setText("LH0"+(check+1));
                    } else {
                    	textMaSP1.setText("LH"+(check+1));
                    }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				btnThem_1.setEnabled(false);
				btnSua_1.setEnabled(false);
				btnAn_1.setEnabled(false);
				textMaSP1.setEnabled(false);
				addlh = true;
			}
		});

		btnThem_1.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Add.png"))));

		btnThem_1.setFocusPainted(false);

		/*
		 * btnAn_1.setFont(new Font("Arial", Font.BOLD, 14));
		 * btnAn_1.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { try { LoaiHang LH = new LoaiHang();
		 * LH.setMaLH(textMaSP1.getText()); LoaiHangBLL lhb = new LoaiHangBLL(); int
		 * confirmed = JOptionPane.showConfirmDialog(null,
		 * "Bạn muốn ẩn loại hàng "+textMaSP1.getText(), "Confirmation",
		 * JOptionPane.YES_NO_OPTION); if (confirmed == JOptionPane.YES_OPTION) { if
		 * (lhb.BtnLoaiHang(LH, "xoaloaihang")) {
		 * JOptionPane.showMessageDialog(contentPane, "Ẩn Loại Hàng Thành Công!");
		 * resetValue(); hienThiLoaiHang(); btnAn_1.setEnabled(false);
		 * btnThem_1.setEnabled(true); } }
		 * 
		 * } catch (SQLException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 * 
		 * } });
		 * 
		 * btnAn_1.setIcon(new ImageIcon(
		 * Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(
		 * ".//Image//Delete.png")))); btnAn_1.setFocusPainted(false);
		 * btnAn_1.setEnabled(false); if(taiKhoan.getQuyen().equals("RL2")) {
		 * btnAn_1.setVisible(true); } else { btnAn_1.setVisible(false); }
		 */
		btnLuu_1.setFont(new Font("Arial", Font.BOLD, 14));
		btnLuu_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(addlh) {
					try {
						if (checkEmtyValueTabbed2()) {
							LoaiHang LH = new LoaiHang();
							LH.setMaLH(textMaSP1.getText());
							LH.setTenLH(textTenSp.getText());
							LoaiHangBLL LHD;
							int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn thêm loại hàng "+textMaSP1.getText(), "Confirmation",
									JOptionPane.YES_NO_OPTION);
							if (confirmed == JOptionPane.YES_OPTION) {
								try {
									LHD = new LoaiHangBLL();
									if (LHD.BtnLoaiHang(LH, "themloaihang")) {
										JOptionPane.showMessageDialog(contentPane, "Thêm Thành công!");
										resetValue();
										hienThiLoaiHang();
										addlh = false;
									}
								} catch (SQLException e1) {
									JOptionPane.showMessageDialog(contentPane, "Thêm Thất bại!");
								}
							}
						}
					} catch (NumberFormatException | HeadlessException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(fixlh) {
					try {
						if (checkEmtyValueTabbed2()) {
							LoaiHangBLL LHD;
							int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn sửa loại hàng "+textMaSP1.getText(), "Confirmation",
									JOptionPane.YES_NO_OPTION);
							if (confirmed == JOptionPane.YES_OPTION) {
								try {
									LHD = new LoaiHangBLL();
									if (LHD.fixLoaiHang(textMaSP1.getText(), textTenSp.getText())) {
										JOptionPane.showMessageDialog(contentPane, "Sửa thành công!");
										resetValue();
										hienThiLoaiHang();
										fixlh = false;
									}
								} catch (SQLException e1) {
									JOptionPane.showMessageDialog(contentPane, "Sửa thất bại!");
								}
							}
						}
					} catch (NumberFormatException | HeadlessException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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

		
		txtSearch = new JTextField();
		txtSearch.setColumns(10);
		txtSearch.setText("Tìm kiếm theo tên");
		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					hienThiLoaiHang();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ArrayList<LoaiHang> data = new ArrayList<>();

				// Get the number of rows and columns in the JTable
				int numRows = table_1.getRowCount();
				// Loop through each row in the JTable and add the data to the ArrayList
				for (int i = 0; i < numRows; i++) {
					LoaiHang lh = new LoaiHang();
					lh.setMaLH((String) table_1.getValueAt(i, 0));
					lh.setTenLH((String) table_1.getValueAt(i, 1));
					data.add(lh);

				}
				 				
				String[] columnNames = { "Mã Loại Hàng", "Tên Loại Hàng" };
				DefaultTableModel model = new DefaultTableModel(columnNames, 0);
				table_1.setModel(model);
				model.setRowCount(0);
				if(!txtSearch.getText().isEmpty()) {
					for (LoaiHang lh : data) {
						if(lh.getTenLH().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
							Object[] row = new Object[] { lh.getMaLH(), lh.getTenLH() };
							model.addRow(row);
						}
					}
				} else {
					try {
						hienThiLoaiHang();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(table_1.getRowCount() == 0) {
					JOptionPane.showMessageDialog(contentPane, "Không tìm thấy loại hàng!");
				}
				resetValue();
				txtSearch.setText("");
			}
		});
		btnSua_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textTenSp.setEnabled(true);
				btnLuu_1.setEnabled(true);
				btnThem_1.setEnabled(false);
				btnAn_1.setEnabled(false);
				btnSua_1.setEnabled(false);
				fixlh = true;
				textMaSP1.setEnabled(false);
			}
		});
		btnSua_1.setEnabled(false);
		btnSua_1.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Change.png"))));
		btnSua_1.setFont(new Font("Arial", Font.BOLD, 14));
		btnSua_1.setFocusPainted(false);
		btnAn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn ẩn loại hàng "+textMaSP1.getText(), "Confirmation",
						JOptionPane.YES_NO_OPTION);
				if (confirmed == JOptionPane.YES_OPTION) {
					LoaiHangBLL lh = new LoaiHangBLL();
					try {
						if(lh.hideLoaiHang(textMaSP1.getText())) {
							JOptionPane.showMessageDialog(contentPane, "Ẩn thành công!");
							textMaSP1.setText("");
							textTenSp.setText("");
							hienThiLoaiHang();
						}
						else {
							JOptionPane.showMessageDialog(contentPane, "Ẩn thất bại!");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		btnAn_1.setEnabled(false);
		btnAn_1.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Delete.png"))));
		btnAn_1.setFont(new Font("Arial", Font.BOLD, 14));
		btnAn_1.setFocusPainted(false);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(140)
					.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(textMaSP1, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
					.addGap(125)
					.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(textTenSp, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
					.addGap(226))
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_9, GroupLayout.DEFAULT_SIZE, 1063, Short.MAX_VALUE))
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(169)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(btnThem_1, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnLuu_1, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSua_1, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnAn_1, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)))
					.addGap(314))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(textMaSP1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(textTenSp, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(35)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnThem_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnLuu_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSua_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
							.addGap(26))
						.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnAn_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addGap(26)))
					.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtSearch, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
						.addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE))
		);
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
				textMaSP1.setEnabled(false);
				btnSua_1.setEnabled(true);
				btnAn_1.setEnabled(true);
				btnThem_1.setEnabled(true);
				btnLuu_1.setEnabled(false);

			}
		});
		
		JPanel panel_4 = new JPanel();
		if(!taiKhoan.getQuyen().equals("RL3")) {
			tabbedPane.addTab("Thông Tin Nhà Cung Cấp",null, panel_4, null);
		}
		String tabNameToRemove = "Thông Tin Loại Hàng";

		 if (taiKhoan.getQuyen().equals("RL3")) {
		     for (int i = 0; i < tabbedPane.getTabCount(); i++) {
		         String tabTitle = tabbedPane.getTitleAt(i);
		         if (tabTitle.equals(tabNameToRemove)) {
		             tabbedPane.remove(i);
		             break;
		         }
		     }
		 }
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
		textNhaCC.setFont(new Font("Arial", Font.BOLD, 13));
		textNhaCC.setEnabled(false);
		textNhaCC.setColumns(10);

		textTenNcc = new JTextField();
		textTenNcc.setFont(new Font("Arial", Font.BOLD, 10));
		textTenNcc.setEnabled(false);
		textTenNcc.setColumns(10);

		textDiaChiCC = new JTextField();
		textDiaChiCC.setFont(new Font("Arial", Font.BOLD, 12));
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
				btnSua_2.setEnabled(true);
				btnAn_2.setEnabled(true);
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
		btnThem_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDiaChiCC.setText("");
				textSDTNCC.setText("");
				textTenNcc.setText("");
				textDiaChiCC.setEnabled(true);
				textSDTNCC.setEnabled(true);
				textTenNcc.setEnabled(true);
				btnAn_2.setEnabled(false);
				btnSua_2.setEnabled(false);
				btnLuu_2.setEnabled(true);
				btnThem_2.setEnabled(false);
				try {
					NhaCungCapBLL nccbll = new NhaCungCapBLL();
					String lastMaNcc = nccbll.getLastMaNCC();
                    String maNcc = lastMaNcc.substring(lastMaNcc.length()-3, lastMaNcc.length());
                    int check = Integer.parseInt(maNcc);
                    if(check < 9) {
                    	textNhaCC.setText("CC00"+(check+1));
                    } else if(check < 99) {
                    	textNhaCC.setText("CC0"+(check+1));
                    } else {
                    	textNhaCC.setText("CC"+(check+1));
                    }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				addncc = true;
			}
		});

		btnThem_2.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Add.png"))));
		btnThem_2.setFocusPainted(false);


		/*
		 * btnAn_2.setIcon(new ImageIcon(
		 * Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(
		 * ".\\Image\\Delete.png")))); btnAn_2.addActionListener(new ActionListener() {
		 * public void actionPerformed(ActionEvent e) { try {
		 * 
		 * NhaCungCap NCC = new NhaCungCap(); NCC.setMaNCC(textNhaCC.getText());
		 * NhaCungCapBLL LHB = new NhaCungCapBLL(); int confirmed =
		 * JOptionPane.showConfirmDialog(null,
		 * "Bạn muốn ẩn nhà cung cấp "+textNhaCC.getText(), "Confirmation",
		 * JOptionPane.YES_NO_OPTION); if (confirmed == JOptionPane.YES_OPTION) { if
		 * (LHB.BtnNhaCungCap(NCC, "xoanhacungcap")) {
		 * 
		 * JOptionPane.showMessageDialog(contentPane, "Ẩn Nhà Cung Cấp Thành Công!");
		 * resetValueTabbed3(); hienThiNhaCungCap(); btnAn_2.setEnabled(false);
		 * btnThem_2.setEnabled(true); } }
		 * 
		 * } catch (SQLException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 * 
		 * }
		 * 
		 * }); btnAn_2.setFocusPainted(false); btnAn_2.setEnabled(false);
		 * if(taiKhoan.getQuyen().equals("RL2")) { btnAn_2.setVisible(true); } else {
		 * btnAn_2.setVisible(false); }
		 */
		btnLuu_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLuu_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(addncc) {
					try {
						if (checkEmtyValueTabbed3()) {
							NhaCungCap NCC = new NhaCungCap();
							NCC.setMaNCC(textNhaCC.getText());
							NCC.setTenNCC(textTenNcc.getText());
							NCC.setDiaChi(textDiaChiCC.getText());
							NCC.setSoDT(textSDTNCC.getText());
							NhaCungCapBLL NCCB;
							int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn thêm nhà cung cấp "+textNhaCC.getText(), "Confirmation",
									JOptionPane.YES_NO_OPTION);
							if (confirmed == JOptionPane.YES_OPTION) {
								try {
									NCCB = new NhaCungCapBLL();
									if (NCCB.BtnNhaCungCap(NCC, "themnhacungcap")) {
										JOptionPane.showMessageDialog(contentPane, "Thêm Thành công!");
										resetValueTabbed3();
										hienThiNhaCungCap();
										addncc = false;
									}
								} catch (SQLException e1) {
									JOptionPane.showMessageDialog(contentPane, "Thêm Thất bại!");
								}
							}
						}
					} catch (NumberFormatException | HeadlessException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(fixncc) {
					try {
						if (checkEmtyValueTabbed3()) {
							NhaCungCapBLL NCCB;
							int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn sửa nhà cung cấp "+textNhaCC.getText(), "Confirmation",
									JOptionPane.YES_NO_OPTION);
							if (confirmed == JOptionPane.YES_OPTION) {
								try {
									NCCB = new NhaCungCapBLL();
									if (NCCB.fixNhaCungCap(textNhaCC.getText(), textTenNcc.getText(), textDiaChiCC.getText(), textSDTNCC.getText())) {
										JOptionPane.showMessageDialog(contentPane, "Sửa thành công!");
										resetValueTabbed3();
										hienThiNhaCungCap();
										fixncc = false;
									}
								} catch (SQLException e1) {
									JOptionPane.showMessageDialog(contentPane, "Sửa thất bại!");
								}
							}
						}
					} catch (NumberFormatException | HeadlessException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});

		btnLuu_2.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Save.png"))));
		btnLuu_2.setFocusPainted(false);
		btnLuu_2.setEnabled(false);
		
		
		txtTimKiem = new JTextField();
		txtTimKiem.setColumns(10);
		txtTimKiem.setText("Tìm kiếm theo tên");
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					hienThiNhaCungCap();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ArrayList<NhaCungCap> data = new ArrayList<>();

				// Get the number of rows and columns in the JTable
				int numRows = table_2.getRowCount();
				// Loop through each row in the JTable and add the data to the ArrayList
				for (int i = 0; i < numRows; i++) {
					NhaCungCap ncc = new NhaCungCap();
					ncc.setMaNCC((String) table_2.getValueAt(i, 0));
					ncc.setTenNCC((String) table_2.getValueAt(i, 1));
					ncc.setDiaChi((String) table_2.getValueAt(i, 2));
					ncc.setSoDT((String) table_2.getValueAt(i, 3));
					data.add(ncc);

				}
						
				String[] columnNames = { "Mã Nhà Cung Cấp", "Tên Nhà Cung Cấp", "Địa Chỉ", "Số Điện Thoại" };
				DefaultTableModel model = new DefaultTableModel(columnNames, 0);
				table_2.setModel(model);
				model.setRowCount(0);
				if(!txtTimKiem.getText().isEmpty()) {
					for (NhaCungCap nccData : data) {
						if(nccData.getTenNCC().toLowerCase().contains(txtTimKiem.getText().toLowerCase())) {
							Object[] row = new Object[] {nccData.getMaNCC(), nccData.getTenNCC(), nccData.getDiaChi(),
									nccData.getSoDT() };
							model.addRow(row);
						}
					}
				} else {
					try {
						hienThiNhaCungCap();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(table_1.getRowCount() == 0) {
					JOptionPane.showMessageDialog(contentPane, "Không tìm thấy nhà cung cấp!");
				}
				resetValueTabbed3();
				txtTimKiem.setText("");
			}
		});
		btnSua_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDiaChiCC.setEnabled(true);
				textSDTNCC.setEnabled(true);
				textTenNcc.setEnabled(true);
				btnAn_2.setEnabled(false);
				btnLuu_2.setEnabled(true);
				btnThem_2.setEnabled(false);
				btnSua_2.setEnabled(false);
				fixncc = true;
			}
		});
		
		btnSua_2.setEnabled(false);
		btnSua_2.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Change.png"))));
		btnSua_2.setFont(new Font("Arial", Font.BOLD, 14));
		btnSua_2.setFocusPainted(false);
		btnAn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn ẩn nhà cung cấp "+textNhaCC.getText(), "Confirmation",
						JOptionPane.YES_NO_OPTION);
				if (confirmed == JOptionPane.YES_OPTION) {
					NhaCungCapBLL ncc = new NhaCungCapBLL();
					try {
						if(ncc.hideNhaCungCap(textNhaCC.getText())) {
							JOptionPane.showMessageDialog(contentPane, "Ẩn thành công!");
							resetValueTabbed3();
							hienThiNhaCungCap();
						}
						else {
							JOptionPane.showMessageDialog(contentPane, "Ẩn thất bại!");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		
		btnAn_2.setEnabled(false);
		btnAn_2.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Delete.png"))));
		btnAn_2.setFont(new Font("Arial", Font.BOLD, 14));
		btnAn_2.setFocusPainted(false);
		
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewLabel_11, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
					.addGap(3)
					.addComponent(textNhaCC, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(lblNewLabel_12, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(textTenNcc, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
					.addGap(20)
					.addComponent(lblNewLabel_13, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(textDiaChiCC, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
					.addGap(29)
					.addComponent(lblNewLabel_14, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
					.addGap(13)
					.addComponent(textSDTNCC, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(63)
					.addComponent(panel_10, GroupLayout.DEFAULT_SIZE, 942, Short.MAX_VALUE)
					.addGap(68))
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(100)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(btnThem_2, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
							.addGap(50)
							.addComponent(btnLuu_2, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
							.addGap(50)
							.addComponent(btnSua_2, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
							.addGap(50)
							.addComponent(btnAn_2, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
							.addGap(50))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(btnTimKiem, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtTimKiem, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(textNhaCC, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(textTenNcc, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_13, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGap(1)
							.addComponent(textDiaChiCC, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_14, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(textSDTNCC, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(btnThem_2, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLuu_2, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSua_2, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAn_2, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtTimKiem)
						.addComponent(btnTimKiem, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
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
		hienThiLoaiHang();

	}
}
