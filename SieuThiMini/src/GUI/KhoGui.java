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
import java.util.List;

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
import BLL.SanPhamBLL;
import DTO.Kho;
import DTO.NhaCungCap;
import DTO.SanPham;
import DTO.TaiKhoan;
import DAL.KhoDAL;


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
	private JTextField textFieldDonvi;

	/**
	 * Launch the application.
	 */
	
	//NhanVien nhanVien = ShareDAta.nhanVien;
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
	JRadioButton radio1 = new JRadioButton("Mã Sản Phẩm");
	JLabel lblNewLabel = new JLabel("KHO");
	JRadioButton radio2 = new JRadioButton("Số Lượng");
	ButtonGroup btg = new ButtonGroup();
	JScrollPane scrollPane = new JScrollPane();
	boolean addbtn, fixbtn = false;
	int rsRenderType = 0;
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
	private JTable table = new JTable();

	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);



	public static boolean validatePhone(String phoneNumber) {
		String regex = "^0[0-9]{9}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}

	public void hienThiKho() throws SQLException {

		KhoBLL khoBll = new KhoBLL();
		SanPhamBLL spBll = new SanPhamBLL();
		ArrayList<Kho> arrKho = new ArrayList<Kho>();
		ArrayList<SanPham> arrsp = new ArrayList<SanPham>();
		String[] columnNames = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		table.setModel(model);
		model.setRowCount(0);
		arrsp = spBll.getSanPham();
		arrKho = khoBll.getKho();
		for (Kho khodata : arrKho) {
			for(SanPham spdata: arrsp) {
				if(khodata.getMaSP().equals(spdata.getMaSp())) {
					Object[] row = new Object[] { khodata.getMaSP(),spBll.getTenSP(khodata.getMaSP()), khodata.getSoLuong() };
					model.addRow(row);
				}
			}
		}
		JTableHeader header = table.getTableHeader();
		Font headerFont = header.getFont(); // get the current font of the header
		header.setFont(new Font(headerFont.getName(), Font.BOLD, 14)); // set the new font for the header with size 16

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
		lbThemanh.setIcon(null);
		btnCapNhatAnh.setEnabled(true);
		btnThem.setEnabled(true);
		btnXoa.setEnabled(false);
		btnSua.setEnabled(false);
		btnLuu.setEnabled(false);


	}


	public Boolean checkEmtyValue() throws SQLException {
		// regular expression pattern
		if (textFieldMasp.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Mã sản phẩm trống!");
			textFieldMasp.requestFocus();
			return false;
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


	// Tabbed 3

	TaiKhoan taiKhoan = DangNhapBLL.taiKhoan;
	private JTextField txtSearch;
	public KhoGui() throws SQLException {

		
		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int selectedIndex = tabbedPane.getSelectedIndex();
				if (selectedIndex == 0) {
					try {
						hienThiKho();
						lblNewLabel.setText("QUẢN LÝ KHO");
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
		
		

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));


		JButton btnSapxep = new JButton("Sắp xếp");
		btnSapxep.setFont(new Font("Arial", Font.BOLD, 12));
		btnSapxep.setFocusPainted(false);
		btnSapxep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Kho> data = new ArrayList<>();

				// Get the number of rows and columns in the JTable
				int numRows = table.getRowCount();
				// Loop through each row in the JTable and add the data to the ArrayList
				for (int i = 0; i < numRows; i++) {
					Kho sp = new Kho();
					sp.setMaSP((String) table.getValueAt(i, 0));
					sp.setSoLuong((String) table.getValueAt(i, 2));
					data.add(sp);
				}
				  if (radio1.isSelected()) {
					  Collections.sort(data, new Comparator<Kho>() {
						  public int compare(Kho sp1, Kho sp2) { 
							  return sp1.getMaSP().compareTo(sp2.getMaSP()); 
						  } 
					  });
				  }
				 
				
				  if (radio2.isSelected()) {
					  Collections.sort(data, new Comparator<Kho>(){
							public int compare(Kho sp1, Kho sp2) {
								return Integer.parseInt(sp1.getSoLuong()) - Integer.parseInt(sp2.getSoLuong());
							}
					  });
				  }
				 
				
				  String[] columnNames = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng" };
				  DefaultTableModel model = new DefaultTableModel(columnNames, 0);
				  SanPhamBLL spbll;
				  try {
					  spbll = new SanPhamBLL();
					  table.setModel(model);
					  model.setRowCount(0);
					  for (Kho sp : data) {
					  Object[] row = { sp.getMaSP(), spbll.getTenSP(sp.getMaSP()) ,sp.getSoLuong()};
					  model.addRow(row); }	
				  } catch (SQLException e1) {
					  
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

				/*
				 * LoaiHangDAL test; try { test = new LoaiHangDAL(); ArrayList<LoaiHang> arrMaLH
				 * = test.docLoaiHangMaLH(Integer.parseInt(maLh)); DefaultComboBoxModel combo =
				 * new DefaultComboBoxModel(); comboBox.setModel(combo);
				 * 
				 * combo.addElement(arrMaLH.get(0).getTenLH());
				 * 
				 * } catch (SQLException e1) { // TODO Auto-generated catch block
				 * e1.printStackTrace(); }
				 */

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


		/*
		 * btnTimKiem.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) {
		 * 
		 * btg.clearSelection(); if (textFieldSearch.getText().isEmpty()) {
		 * 
		 * JOptionPane.showMessageDialog(contentPane, "Nội dung tìm kiếm trống!");
		 * textFieldSearch.requestFocus();
		 * 
		 * } if (!textFieldSearch.getText().isEmpty()) { btnfind = true; SanPhamBLL
		 * spbll = new SanPhamBLL(); ArrayList<SanPham> arr = new ArrayList<SanPham>();
		 * try { arr = spbll.searchProductById(textFieldSearch.getText(), rsRenderType +
		 * ""); if (!arr.isEmpty()) { if (!textFieldFrom.getText().isEmpty() ||
		 * !textFieldTo.getText().isEmpty()) { if (textFieldFrom.getText().isEmpty()) {
		 * JOptionPane.showMessageDialog(contentPane, "Khoảng giá cần tìm trống!");
		 * textFieldFrom.requestFocus(); } else if (textFieldTo.getText().isEmpty()) {
		 * JOptionPane.showMessageDialog(contentPane, "Khoảng giá cần tìm trống!");
		 * textFieldTo.requestFocus(); } else { String inputFrom =
		 * textFieldFrom.getText().replaceAll("[\\p{Punct}\\s]", "");
		 * 
		 * 
		 * String inputTo = textFieldTo.getText().replaceAll("[\\p{Punct}\\s]", "");
		 * isNumber = inputFrom.matches(patternNumber); boolean isNumber2 =
		 * inputTo.matches(patternNumber);
		 * 
		 * if (!isNumber) { JOptionPane.showMessageDialog(contentPane,
		 * "Giá trị phải là số"); textFieldFrom.requestFocus();
		 * textFieldFrom.selectAll();
		 * 
		 * } else if (!isNumber2) { JOptionPane.showMessageDialog(contentPane,
		 * "Giá trị phải là số"); textFieldTo.requestFocus(); textFieldTo.selectAll();
		 * 
		 * } else { if (Integer.parseInt(textFieldFrom.getText().replaceAll(",", "")) >
		 * Integer .parseInt(textFieldTo.getText().replaceAll(",", ""))) {
		 * JOptionPane.showMessageDialog(contentPane, "Khoảng giá không hợp lê!");
		 * textFieldFrom.requestFocus(); } else {
		 * 
		 * hienthisanpham("timkiemtheogia"); } }
		 * 
		 * } } else { hienthisanpham("timkiemtheoid"); }
		 * 
		 * } else { JOptionPane.showMessageDialog(contentPane,
		 * "Không tìm thấy sản phẩm!"); } } catch (SQLException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); } }
		 * 
		 * } });
		 */
		
		txtSearch = new JTextField();
		txtSearch.setColumns(10);
		txtSearch.setText("Tìm kiếm theo tên sản phẩm");
		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					hienThiKho();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ArrayList<Kho> data = new ArrayList<>();
				// Get the number of rows and columns in the JTable
				int numRows = table.getRowCount();
				// Loop through each row in the JTable and add the data to the ArrayList
				for (int i = 0; i < numRows; i++) {
					Kho kho = new Kho();
					kho.setMaSP((String) table.getValueAt(i, 0));
					kho.setSoLuong((String) table.getValueAt(i, 2));
					data.add(kho);
				}
				try {
					String[] columnNames = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng" };
					DefaultTableModel model = new DefaultTableModel(columnNames, 0);
					table.setModel(model);
					model.setRowCount(0);
					SanPhamBLL spBll = new SanPhamBLL();
					if(!txtSearch.getText().isEmpty()) {
						for (Kho khodata : data) {
							if (spBll.getTenSP(khodata.getMaSP()).toLowerCase().contains(txtSearch.getText().toLowerCase())) {
								Object[] row = new Object[] { khodata.getMaSP(),spBll.getTenSP(khodata.getMaSP()), khodata.getSoLuong() };
								model.addRow(row);
							}
						}
					} else {
						try {
							hienThiKho();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(table.getRowCount() == 0) {
						JOptionPane.showMessageDialog(contentPane, "Không tìm thấy sản phẩm!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtSearch.setText("");
			}
		});
		GroupLayout gl_panel_7_1 = new GroupLayout(panel_7_1);
		gl_panel_7_1.setHorizontalGroup(
			gl_panel_7_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_7_1.createSequentialGroup()
					.addGap(24)
					.addComponent(panel_7, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
					.addGap(28)
					.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
					.addGap(3))
		);
		gl_panel_7_1.setVerticalGroup(
			gl_panel_7_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_7_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel_7_1.createSequentialGroup()
							.addGap(0)
							.addGroup(gl_panel_7_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
								.addGap(30)
								.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))))
					.addGap(25))
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
		/*
		 * btnThem_1.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { resetValue(); textTenSp.setEnabled(true);
		 * btnLuu_1.setEnabled(true); try { LoaiHangDAL LHD = new LoaiHangDAL(); int
		 * maLh = LHD.getLastMaLH(); maLh++; textMaSP1.setText("" + maLh); } catch
		 * (SQLException e1) { // TODO Auto-generated catch block e1.printStackTrace();
		 * }
		 * 
		 * textMaSP1.setEnabled(false); addbtn = true; fixbtn = false;
		 * 
		 * } });
		 */
		/*
		 * btnSua_1.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { btnThem_1.setEnabled(false);
		 * btnLuu_1.setEnabled(true); btnXoa_1.setEnabled(false);
		 * textTenSp.setEnabled(true); try { hienThiMaSanPham(); } catch (SQLException
		 * e1) { // TODO Auto-generated catch block e1.printStackTrace(); } oldTenMaLH =
		 * textTenSp.getText(); fixbtn = true; addbtn = false;
		 * 
		 * } });
		 */

		/*
		 * btnXoa_1.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { try {
		 * 
		 * LoaiHang LH = new LoaiHang(); LH.setMaLH(textMaSP1.getText()); LoaiHangDAL
		 * LHD = new LoaiHangDAL(); int confirmed = JOptionPane.showConfirmDialog(null,
		 * "Bạn muốn xóa loại hàng này", "Confirmation", JOptionPane.YES_NO_OPTION); if
		 * (confirmed == JOptionPane.YES_OPTION) { if (LHD.ThemLoaiHang(LH,
		 * "xoaloaihang")) {
		 * 
		 * JOptionPane.showMessageDialog(contentPane, "Xóa Loại Hàng Thành Công!");
		 * resetValue(); hienThiMaSanPham(); btnXoa_1.setEnabled(false);
		 * btnThem_1.setEnabled(true); } }
		 * 
		 * } catch (SQLException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 * 
		 * } });
		 */

		/*
		 * btnLuu_1.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { try { if (checkEmtyValueTabbed2()) {
		 * LoaiHang LH = new LoaiHang(); LH.setMaLH(textMaSP1.getText());
		 * LH.setTenLH(textTenSp.getText()); LoaiHangDAL LHD; if (addbtn) { try { LHD =
		 * new LoaiHangDAL(); if (LHD.ThemLoaiHang(LH, "themloaihang")) {
		 * JOptionPane.showMessageDialog(contentPane, "Thêm Thành công!"); resetValue();
		 * hienThiMaSanPham(); addbtn = false; } } catch (SQLException e1) {
		 * JOptionPane.showMessageDialog(contentPane, "Thêm Thất bại!"); } } if (fixbtn)
		 * { try { LHD = new LoaiHangDAL(); if (LHD.ThemLoaiHang(LH, "sualoaihang")) {
		 * JOptionPane.showMessageDialog(contentPane, "Sửa Thành công!"); resetValue();
		 * btnThem_1.setEnabled(true); hienThiMaSanPham(); fixbtn = false; } } catch
		 * (SQLException e1) { JOptionPane.showMessageDialog(contentPane,
		 * "Sửa Thất bại!"); } }
		 * 
		 * } } catch (NumberFormatException | HeadlessException | SQLException e1) { //
		 * TODO Auto-generated catch block e1.printStackTrace(); } } });
		 */


		JPanel panel_9 = new JPanel();

		JScrollPane scrollPane_1 = new JScrollPane();

		JLabel lblLoiHngSiu = new JLabel();
		lblLoiHngSiu.setText("LOẠI HÀNG SIÊU THỊ");
		lblLoiHngSiu.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoiHngSiu.setForeground(new Color(255, 128, 128));
		lblLoiHngSiu.setFont(new Font("Arial", Font.BOLD, 20));
		

		
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

		JPanel panel_10 = new JPanel();

		JScrollPane scrollPane_2 = new JScrollPane();
		JTableHeader header2 = table.getTableHeader();
		header2.setPreferredSize(new Dimension(header2.getWidth(), 30));

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

		panel_10.setLayout(gl_panel_10);
		/*
		 * btnThem_2.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { textNhaCC.setText("");
		 * textDiaChiCC.setText(""); textSDTNCC.setText(""); textTenNcc.setText(""); //
		 * textNhaCC.setEnabled(true); textDiaChiCC.setEnabled(true);
		 * textSDTNCC.setEnabled(true); textTenNcc.setEnabled(true);
		 * btnSua_2.setEnabled(false); btnXoa_2.setEnabled(false);
		 * btnLuu_2.setEnabled(true); try { NhaCungCapDAL LHD = new NhaCungCapDAL(); int
		 * maNcc = LHD.getLastMaNCC(); maNcc++; textNhaCC.setText("" + maNcc); } catch
		 * (SQLException e1) { // TODO Auto-generated catch block e1.printStackTrace();
		 * }
		 * 
		 * addbtn = true; fixbtn = false; } });
		 */
		 
		/*
		 * btnXoa_2.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { try {
		 * 
		 * NhaCungCap NCC = new NhaCungCap();
		 * 
		 * NCC.setMaNCC(textNhaCC.getText()); NhaCungCapDAL LHD = new NhaCungCapDAL();
		 * int confirmed = JOptionPane.showConfirmDialog(null,
		 * "Bạn muốn xóa nhà cung cấp này", "Confirmation", JOptionPane.YES_NO_OPTION);
		 * if (confirmed == JOptionPane.YES_OPTION) { if (LHD.ThemNhaCungCap(NCC,
		 * "xoanhacungcap")) {
		 * 
		 * JOptionPane.showMessageDialog(contentPane, "Xóa Nhà Cung Cấp Thành Công!");
		 * resetValueTabbed3(); hienThiNhaCungCap(); btnXoa_2.setEnabled(false);
		 * btnThem_2.setEnabled(true); } }
		 * 
		 * } catch (SQLException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 * 
		 * }
		 * 
		 * });
		 */

		/*
		 * btnLuu_2.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { try { if (checkEmtyValueTabbed3()) {
		 * NhaCungCap NCC = new NhaCungCap(); NCC.setMaNCC(textNhaCC.getText());
		 * NCC.setTenNCC(textTenNcc.getText()); NCC.setDiaChi(textDiaChiCC.getText());
		 * NCC.setSoDT(textSDTNCC.getText()); NhaCungCapDAL NCCD; if (addbtn) { try {
		 * NCCD = new NhaCungCapDAL(); if (NCCD.ThemNhaCungCap(NCC, "themnhacungcap")) {
		 * JOptionPane.showMessageDialog(contentPane, "Thêm Thành công!");
		 * resetValueTabbed3(); hienThiNhaCungCap(); addbtn = false; } } catch
		 * (SQLException e1) { JOptionPane.showMessageDialog(contentPane,
		 * "Thêm Thất bại!"); } } if (fixbtn) { try { NCCD = new NhaCungCapDAL(); if
		 * (NCCD.ThemNhaCungCap(NCC, "suanhacungcap")) {
		 * JOptionPane.showMessageDialog(contentPane, "Sửa Thành công!");
		 * resetValueTabbed3(); btnThem_2.setEnabled(true); hienThiNhaCungCap(); fixbtn
		 * = false; } } catch (SQLException e1) {
		 * JOptionPane.showMessageDialog(contentPane, "Sửa Thất bại!"); } }
		 * 
		 * } } catch (NumberFormatException | HeadlessException | SQLException e1) { //
		 * TODO Auto-generated catch block e1.printStackTrace(); } } });
		 */

		
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
		hienThiKho();

	}
}
