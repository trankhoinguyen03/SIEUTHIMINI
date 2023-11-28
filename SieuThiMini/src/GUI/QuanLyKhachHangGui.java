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
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import BLL.DangNhapBLL;
import BLL.KhachHangBLL;
import BLL.LoaiHangBLL;
import BLL.NhanVienBLL;
import DTO.KhachHang;
import DTO.LoaiHang;
import DAL.KhachHangDAL;
import DTO.TaiKhoan;

import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class QuanLyKhachHangGui extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldMakh;
    private JTextField textFieldTenkh;
    private JTextField textFieldSDT;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QuanLyKhachHangGui frame = new QuanLyKhachHangGui();
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    File selectedFile;
    ImageIcon icon = new ImageIcon();
    Object lastValueMaKh;
    JButton btnSua = new JButton("Sửa");
    JButton btnAn = new JButton("Ẩn");
    boolean isNumber = true;
    JButton btnThem = new JButton("Thêm");
    JButton btnLuu = new JButton("Lưu");
    int lastRow;
    JRadioButton radio1 = new JRadioButton("Mã khách hàng");
    JRadioButton radio2 = new JRadioButton("Tên");
    ButtonGroup btg1 = new ButtonGroup();
    ButtonGroup btg2 = new ButtonGroup();
    JScrollPane scrollPane = new JScrollPane();
    boolean addbtn, fixbtn = false;
//	dung grap 2d tao size cho anh
    int newWidth = 130;
    int newHeight = 110;
    BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = resizedImage.createGraphics();
    String patternNumber = "\\d+(\\.\\d+)?";
    JLabel lbThongbao = new JLabel();
    String oldMaKH = null;
    boolean checkFix = false;

    
    TaiKhoan taiKhoan = DangNhapBLL.taiKhoan;
    private JTextField txtSearch;
    
    public void hienthikhachhang(String condition) throws SQLException {
        KhachHangBLL khBll = new KhachHangBLL();
        ArrayList<KhachHang> arrKh = new ArrayList<KhachHang>();
        if (condition == "hien thi") {

            arrKh = khBll.getKhachHang("dockhachhang");
        }
        if (condition == "sapxeptheoten") {
            arrKh = khBll.getKhachHang("sapxeptheoten");
        }
        if (condition == "sapxeptheomakh") {
            arrKh = khBll.getKhachHang("sapxeptheomakh");
        }
        if (condition == "them") {
            arrKh = khBll.getKhachHang("dockhachhang");
        }

        String[] columnNames = {"Mã Khách Hàng", "Tên Khách Hàng", "SDT", "Điểm Thưởng"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        if (condition == "timkiem") {
            table.setModel(model);
            model.setRowCount(0);
            for (KhachHang khdata : arrKh) {
                Object[] row = new Object[]{khdata.getMaKh(), khdata.getTenKh(), khdata.getSoDienThoai(), khdata.getTichDiem()};
                model.addRow(row);
            }
        } else {

            table.setModel(model);
            model.setRowCount(0);
            for (KhachHang khdata : arrKh) {
                Object[] row = new Object[]{khdata.getMaKh(), khdata.getTenKh(), khdata.getSoDienThoai(), khdata.getTichDiem()};
                model.addRow(row);
            }

            lastRow = table.getRowCount() - 1; // get index of the last row
            lastValueMaKh = table.getValueAt(lastRow, 0); // get the value at the last row and column n
        }
    }

    public void resetValue() {
        textFieldMakh.setText("");
        textFieldTenkh.setText("");
        textFieldSDT.setText("");
        btnThem.setEnabled(true);
        btnAn.setEnabled(false);
        btnLuu.setEnabled(false);
        btnSua.setEnabled(false);
    }

    public void unSetEnable() {
        textFieldTenkh.setEnabled(true);
        textFieldSDT.setEnabled(true);
        btnThem.setEnabled(true);
        //btnXoa.setEnabled(false);
        btnLuu.setEnabled(false);
    }

    public void setEnable() {
        textFieldTenkh.setEnabled(false);
        textFieldSDT.setEnabled(false);
    }

    public Boolean checkEmtyValue() throws SQLException {
        if (textFieldTenkh.getText().isEmpty()) {
            JOptionPane.showMessageDialog(contentPane, "Tên khách hàng trống");
            textFieldTenkh.requestFocus();
            return false;
        } else {
        	Pattern reg = Pattern.compile("^[A-Z][a-zàáạảãăẳẵắằặâấầẩẫậèéẻẽẹêếềễểệìíịĩỉýỳỹỷỵòóỏõọôỗổồốộơớờợỡởùúủũụưứừữửựđ]+( [A-Z][a-zàáạảãăẳẵắằặâấầẩẫậèéẻẽẹêếềễểệìíịĩỉýỳỹỷỵòóỏõọôỗổồốộơớờợỡởùúủũụưứừữửựđ]+)*( [A-Z][a-zàáạảãăẳẵắằặâấầẩẫậèéẻẽẹêếềễểệìíịĩỉýỳỹỷỵòóỏõọôỗổồốộơớờợỡởùúủũụưứừữửựđ]+)?$");
            boolean kt = reg.matcher(textFieldTenkh.getText()).matches();
            if(kt == false) {
	            JOptionPane.showMessageDialog(contentPane, "Tên phải in hoa chữ đầu và chỉ chứa các chữ cái");
				textFieldTenkh.requestFocus();
				return false;
            }
        }
        if (textFieldSDT.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Số điện thoại rỗng!");
			textFieldSDT.requestFocus();
			return false;
		} else {
			Pattern reg = Pattern.compile("((09|03|07|08|05)+([0-9]{8})\\b)");
            boolean kt = reg.matcher(textFieldSDT.getText()).matches();
            if(kt == false) {
                JOptionPane.showMessageDialog(contentPane, "Không tồn tại định dạng số điện thoại này!");
                textFieldSDT.requestFocus();
                return false;
            }
		}
        return true;
    }
    public QuanLyKhachHangGui() throws SQLException {

        setTitle("Quản lý Khách Hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1106, 750);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        // Get an array of all buttons in the application

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 1088, 104);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(1093, 118, 0, 522);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(5, 86, 1082, 600);

        JPanel panel_2 = new JPanel();
        tabbedPane.addTab("Thông tin khách hàng", null, panel_2, null);

        JPanel panel_5 = new JPanel();
        panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_5.setBounds(0, 10, 1067, 200);

        JLabel lblNewLabel_1 = new JLabel("Mã khách hàng");
        lblNewLabel_1.setBounds(210, 10, 100, 25);

        textFieldMakh = new JTextField();
        textFieldMakh.setBounds(320, 10, 200, 25);
        textFieldMakh.setEnabled(false);
        textFieldMakh.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Họ tên");
        lblNewLabel_3.setBounds(210, 50, 100, 25);

        textFieldTenkh = new JTextField();
        textFieldTenkh.setBounds(320, 50, 200, 28);
        textFieldTenkh.setEnabled(false);
        textFieldTenkh.setColumns(10);

		/*
		 * JLabel lblNewLabel_8 = new JLabel("Điểm thưởng");
		 * lblNewLabel_8.setBounds(650, 48, 87, 25);
		 * 
		 * textFieldDiemthuong = new JTextField(); textFieldDiemthuong.setBounds(748,
		 * 50, 137, 28); textFieldDiemthuong.setEnabled(false);
		 * textFieldDiemthuong.setColumns(10);
		 * 
		 * JLabel lblNewLabel_9 = new JLabel("Ngày cấp thẻ");
		 * lblNewLabel_9.setBounds(650, 125, 87, 25);
		 * 
		 * textFieldNgaycapthe = new JTextField(); textFieldNgaycapthe.setBounds(748,
		 * 127, 138, 29); textFieldNgaycapthe.setEnabled(false);
		 * textFieldNgaycapthe.setColumns(10);
		 */

        JLabel lblNewLabel_10 = new JLabel("SDT");
        lblNewLabel_10.setBounds(210, 90, 100, 25);

        textFieldSDT = new JTextField();
        textFieldSDT.setEnabled(false);
        textFieldSDT.setBounds(320, 90, 200, 25);
        textFieldSDT.setColumns(10);

		/*
		 * JLabel lblNewLabel_11 = new JLabel("Ngày mua"); lblNewLabel_11.setBounds(420,
		 * 123, 62, 26);
		 * 
		 * textFieldNgaymuagannhat = new JTextField();
		 * textFieldNgaymuagannhat.setBounds(506, 127, 117, 29);
		 * textFieldNgaymuagannhat.setColumns(10);
		 */

        JPanel panel_6 = new JPanel();
        panel_6.setBounds(0, 210, 1067, 78);
        btnLuu.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnLuu.setBounds(10, 10, 104, 53);

        btnLuu.setEnabled(false);
        btnLuu.setFocusPainted(false);
        btnLuu.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Save.png"))));
        btnLuu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	if(addbtn) {
                        if (checkEmtyValue()) {
                        	int confirmed = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm khách hàng "+textFieldMakh.getText(),
            						  "Confirmation", JOptionPane.YES_NO_OPTION);
            				if (confirmed == JOptionPane.YES_OPTION) {
    	                        KhachHang kh = new KhachHang();
    	                        KhachHangBLL luukh;
                                try {
                                    luukh = new KhachHangBLL();
                                    kh.setMaKh(textFieldMakh.getText());
                                    kh.setTenKh(textFieldTenkh.getText());
                                    kh.setSoDienThoai(textFieldSDT.getText());
                                    kh.setTichDiem(0);
                                    boolean checkAddPro = luukh.addKhachHang(kh);
                                    if (checkAddPro) {
                                        JOptionPane.showMessageDialog(contentPane, "Thêm thành công");
                                        resetValue();
                                        setEnable();
                                        hienthikhachhang("hien thi");
                                        addbtn = false;
                                    } else {
                                        JOptionPane.showMessageDialog(contentPane, "Thêm thất bại");
                                    }
                                } catch (SQLException e2) {
                                    // TODO Auto-generated catch block
                                    e2.printStackTrace();
                                }
            				}
    			  		}
                	}
                	if(fixbtn) {
                        if (checkEmtyValue()) {
                        	int confirmed = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa khách hàng "+textFieldMakh.getText(),
            						  "Confirmation", JOptionPane.YES_NO_OPTION);
            				if (confirmed == JOptionPane.YES_OPTION) {
    	                        KhachHangBLL luukh;
                                try {
                                    luukh = new KhachHangBLL();
                                    boolean checkAddPro = luukh.fixKhachHang(textFieldMakh.getText(),textFieldTenkh.getText(),textFieldSDT.getText());
                                    if (checkAddPro) {
                                        JOptionPane.showMessageDialog(contentPane, "Sửa thành công");
                                        resetValue();
                                        setEnable();
                                        hienthikhachhang("hien thi");
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
                	}

                } catch (NumberFormatException | HeadlessException | SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnThem.setBounds(146, 10, 135, 53);
        btnThem.setFocusPainted(false);
        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
                    resetValue();
                    unSetEnable();
                    KhachHangBLL khbll;
                    khbll = new KhachHangBLL();
                    String lastMaKh = khbll.getLastMaKH();
                    String maKh = lastMaKh.substring(lastMaKh.length()-3, lastMaKh.length());
                    int check = Integer.parseInt(maKh);
                    if(check < 9) {
                    	textFieldMakh.setText("KH00"+(check+1));
                    } else if(check < 99) {
                    	textFieldMakh.setText("KH0"+(check+1));
                    } else {
                    	textFieldMakh.setText("KH"+(check+1));
                    }
                    btnAn.setEnabled(false);
                    btnSua.setEnabled(false);
                    btnThem.setEnabled(false);
                    btnLuu.setEnabled(true);
                    addbtn = true;
                    try {
                        hienthikhachhang("them");
                    } catch (SQLException e3) {
                        // TODO Auto-generated catch block
                        e3.printStackTrace();
                    }
            	} catch (SQLException ex) {
					Logger.getLogger(QuanLyKhachHangGui.class.getName()).log(Level.SEVERE, null, ex);
				}
            }
        });
        btnThem.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Add.png"))));
		
		/*
		 * btnXoa.setBounds(419, 10, 104, 53); if(taiKhoan.getQuyen().equals("RL2")) {
		 * btnXoa.setVisible(true); } else { btnXoa.setVisible(false); }
		 * btnXoa.setEnabled(false); btnXoa.setIcon(new ImageIcon(
		 * Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(
		 * ".\\Image\\Delete.png")))); btnXoa.setFocusPainted(false);
		 * btnXoa.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { int confirmed =
		 * JOptionPane.showConfirmDialog(null,
		 * "Bạn có muốn ẩn khách hàng "+textFieldMakh.getText(), "Confirmation",
		 * JOptionPane.YES_NO_OPTION); if (confirmed == JOptionPane.YES_OPTION) {
		 * KhachHangBLL hideKh; try { hideKh = new KhachHangBLL(); if
		 * (hideKh.hideKhachHang(textFieldMakh.getText())) {
		 * JOptionPane.showMessageDialog(contentPane, "Ẩn khách hàng thành công!");
		 * hienthikhachhang("hien thi"); resetValue(); setEnable(); } } catch
		 * (SQLException e1) { // TODO Auto-generated catch block e1.printStackTrace();
		 * }
		 * 
		 * } } });
		 */		 

        JPanel panel_7 = new JPanel();
        panel_7.setBounds(697, 10, 370, 53);
        panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));

        JButton btnDongBo = new JButton("");
        btnDongBo.setBounds(304, 10, 104, 53);
        btnDongBo.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(QuanLyKhachHangGui.class.getResource(".\\Image\\Refresh-icon.png"))));
        btnDongBo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	resetValue();
                	setEnable();
                    hienthikhachhang("hien thi");
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        btnDongBo.setFocusPainted(false);

        JButton btnSapxep = new JButton("Sắp xếp");
        btnSapxep.setFocusPainted(false);
        btnSapxep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	resetValue();
            	setEnable();
                if (radio1.isSelected()) {

                    try {
                        radio1.setSelected(false);
                        hienthikhachhang("sapxeptheomakh");
                        
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                if (radio2.isSelected()) {
                    try {
                        radio2.setSelected(false);
                        hienthikhachhang("sapxeptheoten");
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                }
            }
        });

        btg1.add(radio1);
        btg1.add(radio2);
        GroupLayout gl_panel_7 = new GroupLayout(panel_7);
        gl_panel_7.setHorizontalGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_7.createSequentialGroup().addContainerGap()
                        .addComponent(btnSapxep, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE).addGap(18)
                        .addComponent(radio1, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(radio2, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(15, Short.MAX_VALUE)));
        gl_panel_7.setVerticalGroup(gl_panel_7.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
                gl_panel_7.createSequentialGroup().addContainerGap()
                        .addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
                                .addComponent(btnSapxep, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                .addComponent(radio1).addComponent(radio2))
                        .addContainerGap()));
        panel_7.setLayout(gl_panel_7);

        JPanel panel_8 = new JPanel();
        panel_8.setBounds(0, 250, 1067, 300);
        lbThongbao.setBounds(227, 40, 569, 32);

        lbThongbao.setText("DANH SÁCH KHÁCH HÀNG");

        lbThongbao.setForeground(new Color(255, 128, 128));
        lbThongbao.setHorizontalAlignment(SwingConstants.CENTER);
        lbThongbao.setFont(new Font("Arial", Font.BOLD, 20));

        table = new JTable();
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 30));

        table.setRowHeight(30);
        table.setFocusable(false);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                int row = table.getSelectedRow(); // get the selected row
                String maKH = table.getModel().getValueAt(row, 0).toString(); // get the value of the first column
                String TenKH = table.getModel().getValueAt(row, 1).toString(); // get the value of the second column
                String Sdt = table.getModel().getValueAt(row, 2).toString();
                textFieldMakh.setText(maKH);
                textFieldTenkh.setText(TenKH);
                textFieldSDT.setText(Sdt);
                setEnable();
                btnSua.setEnabled(true);
                btnAn.setEnabled(true);
                btnThem.setEnabled(true);
                btnLuu.setEnabled(false);
            }

        });
        scrollPane.setBounds(0, 70, 1067, 210);

        scrollPane.setViewportView(table);
        panel_8.setLayout(null);
        panel_8.add(lbThongbao);
        panel_8.add(scrollPane);
        panel_6.setLayout(null);
        panel_6.add(btnLuu);
        panel_6.add(btnThem);
        //panel_6.add(btnXoa);
        panel_6.add(btnDongBo);
        panel_6.add(panel_7);
        icon = new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource("/GUI/Image/Background.png")));
        Image image_bg = icon.getImage();
        Image resizedImg_bg = image_bg.getScaledInstance(1300, 130, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon_bg = new ImageIcon(resizedImg_bg);
        panel_2.setLayout(null);
        panel_5.setLayout(null);
        panel_5.add(lblNewLabel_1);
        panel_5.add(textFieldMakh);

        panel_5.add(lblNewLabel_3);

        panel_5.add(textFieldTenkh);

        //panel_5.add(lblNewLabel_8);
        //panel_5.add(lblNewLabel_9);
        //panel_5.add(textFieldDiemthuong);
        //panel_5.add(textFieldNgaycapthe);
        panel_5.add(lblNewLabel_10);
        panel_5.add(textFieldSDT);
        //panel_5.add(lblNewLabel_11);
        //panel_5.add(textFieldNgaymuagannhat);

        panel_2.add(panel_5);

        //JButton btnTimKiem = new JButton("Tìm Kiếm");
        
        JButton btnNewButton = new JButton("Hệ Thống");
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
        	}
        });
        btnNewButton.setBounds(10, 8, 95, 37);
        panel_5.add(btnNewButton);
        
        txtSearch = new JTextField();
        txtSearch.setBounds(320, 165, 200, 25);
        txtSearch.setText("Tìm kiếm theo tên");
        panel_5.add(txtSearch);
        txtSearch.setColumns(10);
        
        JButton btnSearch = new JButton("Tìm kiếm");
        btnSearch.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					hienthikhachhang("hien thi");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		ArrayList<KhachHang> data = new ArrayList<>();

				// Get the number of rows and columns in the JTable
				int numRows = table.getRowCount();
				// Loop through each row in the JTable and add the data to the ArrayList
				for (int i = 0; i < numRows; i++) {
					KhachHang kh = new KhachHang();
					kh.setMaKh((String) table.getValueAt(i, 0));
					kh.setTenKh((String) table.getValueAt(i, 1));
					kh.setSoDienThoai((String) table.getValueAt(i, 2));
					kh.setTichDiem((int) table.getValueAt(i, 3));
					data.add(kh);

				}			
				
		        String[] columnNames = {"Mã Khách Hàng", "Tên Khách Hàng", "SDT", "Điểm Thưởng"};
		        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
				table.setModel(model);
				model.setRowCount(0);
				if(!txtSearch.getText().isEmpty()) {
					for (KhachHang khdata : data) {
						if(khdata.getTenKh().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
			                Object[] row = new Object[]{khdata.getMaKh(), khdata.getTenKh(), khdata.getSoDienThoai(), khdata.getTichDiem()};
			                model.addRow(row);
						}
		            }
				} else {
					try {
						hienthikhachhang("hien thi");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(table.getRowCount() == 0) {
					JOptionPane.showMessageDialog(contentPane, "Không tìm thấy khách hàng!");
				}
				txtSearch.setText("");
        	}
        });
        btnSearch.setBounds(199, 165, 111, 25);
        panel_5.add(btnSearch);
        panel_2.add(panel_6);
        
        if(taiKhoan.getQuyen().equals("RL2")) {
        	btnSua.setVisible(true);
        	btnAn.setVisible(true);
        } else {
        	btnSua.setVisible(false);
        	btnAn.setVisible(false);
        }
        btnSua.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                unSetEnable();
                btnAn.setEnabled(false);
                btnSua.setEnabled(false);
                btnThem.setEnabled(false);
                btnLuu.setEnabled(true);
                fixbtn = true;
        	}
        });
        
        
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSua.setFocusPainted(false);
        btnSua.setEnabled(false);
        btnSua.setBounds(432, 10, 104, 53);
        btnSua.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Change.png"))));
        panel_6.add(btnSua);
        btnAn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn ẩn khách hàng "+textFieldMakh.getText(), "Confirmation",
						JOptionPane.YES_NO_OPTION);
				if (confirmed == JOptionPane.YES_OPTION) {
					KhachHangBLL kh = new KhachHangBLL();
					try {
						if(kh.hideKhachHang(textFieldMakh.getText())) {
							JOptionPane.showMessageDialog(contentPane, "Ẩn thành công!");
					        hienthikhachhang("hien thi");
					        resetValue();
					        setEnable();
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
        
        btnAn.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAn.setFocusPainted(false);
        btnAn.setEnabled(false);
        btnAn.setBounds(560, 10, 104, 53);
        btnAn.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Delete.png"))));
        panel_6.add(btnAn);
        panel_2.add(panel_8);

        JPanel panel_3 = new JPanel();
        //tabbedPane.addTab("New tab", null, panel_3, null);
        panel_3.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("New label");
        lblNewLabel_2.setBounds(166, 78, 138, 55);
        panel_3.add(lblNewLabel_2);

        JPanel panel_4 = new JPanel();
        //tabbedPane.addTab("New tab", null, panel_4, null);
        panel_4.setLayout(null);
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1
                .setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGap(0, 1082, Short.MAX_VALUE));
        gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGap(0, 522, Short.MAX_VALUE));

        panel_1.setLayout(gl_panel_1);

        JLabel lblNewLabel = new JLabel("QUẢN LÝ KHÁCH HÀNG");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(198, 0, 664, 63);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lbIconShop = new JLabel("");
        lbIconShop.setBounds(10, 0, 170, 65);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
        icon = new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//shop.png")));
        Image imaged = icon.getImage();
        Image resizedImg = imaged.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);
        panel.setLayout(null);
        lbIconShop.setIcon(resizedIcon);
        panel.add(lbIconShop);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_icon = new JLabel("");
        icon = new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Background2.png")));
        Image image2 = icon.getImage();
        Image resizedImg2 = image2.getScaledInstance(1300, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(resizedImg2);
        lblNewLabel_icon.setIcon(resizedIcon2);
        lblNewLabel_icon.setBounds(0, 0, 1078, 104);
        panel.add(lblNewLabel_icon);
        contentPane.setLayout(null);
        contentPane.add(tabbedPane);
        contentPane.add(panel_1);
        contentPane.add(panel);
        hienthikhachhang("hien thi");
    }
}