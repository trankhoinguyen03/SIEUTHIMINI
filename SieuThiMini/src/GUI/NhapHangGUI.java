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

import BLL.NhapHang;
import DAL.NhapHangDAL;
import DAL.NhanVienDAL;
import BLL.NhanVien;

import DAL.SanPhamDAL;
import DTO.NhaCungCap;
import DTO.SanPham;
import DAL.NhaCungCapDAL;

import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class NhapHangGUI extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldMapn;
    private JComboBox comboBoxNhanVien;
    private JComboBox comboBoxNhaCC;
    private JTextField textFieldSanPham;
    private JTextField textFieldLoaiHang;
    //private JTextField textFieldNgaylap;
    private JTextField textFieldGiaNhap;
    private JTextField textFieldSoluong;
    //private JTextField textFieldNgaysx;
    private JTextField textFieldNgaynhap;
    private JTable table;
    private JTable table_chitiet;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NhapHangGUI frame = new NhapHangGUI();
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
    Object lastValueMaPn;
    JButton btnXoa = new JButton("Ẩn");
    JButton btnSua = new JButton("Sửa");
    boolean check = true;
    JButton btnThem = new JButton("Thêm");
    JButton btnLuu = new JButton("Lưu");
    //JButton btnXem = new JButton("Xem Danh Sách");
    //JButton btnNhap = new JButton("Nhập");
    //JButton btnSuaChitiet = new JButton("Sửa Chi Tiết");
    //JButton btnXoaChitiet = new JButton("Xóa");
    int lastRow;
    JRadioButton radioSapxepsoluong = new JRadioButton("Số lượng");
    JRadioButton radioSapxepma = new JRadioButton("Mã phiếu nhập");
    ButtonGroup groupSapxep = new ButtonGroup();
    JScrollPane scrollPane = new JScrollPane();
    JScrollPane scrollPane_chitiet = new JScrollPane();
    boolean addbtn, fixbtn, addifbtn, fixifbtn = false;
//	dung grap 2d tao size cho anh
    int newWidth = 130;
    int newHeight = 110;
    BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = resizedImage.createGraphics();
    String patternNumber = "\\d+(\\.\\d+)?";
    JLabel lbThongbao = new JLabel();
    JLabel lbThongbao_chitiet = new JLabel();
    String oldMaPN = null;
    String oldMaNV = null;
    String oldMaSP = null;
    String oldMaNCC = null;
    private JTextField textFieldSearch;
    boolean checkFix = false;

    public void hienthiphieunhap(String condition) throws SQLException {
        NhapHangDAL nhDal = new NhapHangDAL();
        ArrayList<NhapHang> arrNh = new ArrayList<NhapHang>();
        if (condition == "phieunhap") {
            arrNh = nhDal.docNhapHang("docphieunhap", null);
        }
        if (condition == "chitiet") {
            arrNh = nhDal.docNhapHang("docchitiet", textFieldMapn.getText());
        }
        if (condition == "themphieunhap") {
            arrNh = nhDal.docNhapHang("docphieunhap", null);
            NhanVienDAL testnv = new NhanVienDAL();
            ArrayList<NhanVien> arrMaNV = testnv.docNhanVien("docnhanvien", null);
            DefaultComboBoxModel combonv = new DefaultComboBoxModel();
            comboBoxNhanVien.setModel(combonv);
            for (NhanVien manv : arrMaNV) {
                combonv.addElement(manv.getTenNv());
            }
        }
        if (condition == "themchitiet") {
            arrNh = nhDal.docNhapHang("docchitiet", textFieldMapn.getText());
            SanPhamDAL testsp = new SanPhamDAL();
            ArrayList<SanPham> arrMaSP = testsp.docSanPham("docsanpham", null);
            DefaultComboBoxModel combosp = new DefaultComboBoxModel();
            for (SanPham masp : arrMaSP) {
                combosp.addElement(masp.getTenSp());
            }

            NhaCungCapDAL testncc = new NhaCungCapDAL();
            ArrayList<NhaCungCap> arrMaNCC = testncc.docNhaCungCap();
            DefaultComboBoxModel comboncc = new DefaultComboBoxModel();
            comboBoxNhaCC.setModel(comboncc);
            for (NhaCungCap mancc : arrMaNCC) {
                comboncc.addElement(mancc.getTenNCC());
            }

        }
        
        if (condition == "phieunhap" || condition == "themphieunhap") {
        	
            String[] columnNames_chitiet = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Thành Tiền"};
            DefaultTableModel model_chitiet = new DefaultTableModel(columnNames_chitiet, 0);

            table_chitiet.setModel(model_chitiet);
            model_chitiet.setRowCount(0);
            for (NhapHang nhdata : arrNh) {
                //NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
                Object[] row = new Object[]{};//nhdata.getMaPn(), nhdata.getMaNv(), nhdata.getThoiDiemLap()};
                model_chitiet.addRow(row);
            }

            lastRow = table_chitiet.getRowCount() - 1; // get index of the last row
            lastValueMaPn = table_chitiet.getValueAt(lastRow, 0); // get the value at the last row and column n
        	
            String[] columnNames = {"Mã Phiếu Nhập", "Tên Nhân Viên", "Nhà Cung Cấp", "Tổng Tiền", "Ngày Nhập"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            table.setModel(model);
            model.setRowCount(0);
            for (NhapHang nhdata : arrNh) {
                //NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
                Object[] row = new Object[]{};//nhdata.getMaPn(), nhdata.getMaNv(), nhdata.getThoiDiemLap()};
                model.addRow(row);
            }

            lastRow = table.getRowCount() - 1; // get index of the last row
            lastValueMaPn = table.getValueAt(lastRow, 0); // get the value at the last row and column n
        }
        if (condition == "chitiet" || condition == "themchitiet") {
            String[] columnNames = {"MaPN", "MaSP", "MaNCC", "Soluong", "NgaySanXuat", "NgayNhap"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            table.setModel(model);
            model.setRowCount(0);
            for (NhapHang nhdata : arrNh) {
                //NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
                Object[] row = new Object[]{nhdata.getMaPn(), nhdata.getMaSp(), nhdata.getMaNcc(), nhdata.getSoLuong(),
                    nhdata.getNgaySanXuat(), nhdata.getNgayNhap()};
                model.addRow(row);
            }

            lastRow = table.getRowCount() - 1; // get index of the last row
            lastValueMaPn = table.getValueAt(lastRow, 0); // get the value at the last row and column n
        }

    }

    public void resetValue(String condition) {
        if (condition == "themphieunhap") {
            textFieldMapn.setText("");
            textFieldMapn.setEnabled(true);
            comboBoxNhanVien.setSelectedItem(null);
            comboBoxNhanVien.setEnabled(true);
            comboBoxNhaCC.setSelectedItem(null);
            comboBoxNhaCC.setEnabled(false);
            //textFieldNgaylap.setText("");
            //textFieldNgaylap.setEnabled(true);
            textFieldSoluong.setText("");
            textFieldSoluong.setEnabled(false);
            //textFieldNgaysx.setText("");
            //textFieldNgaysx.setEnabled(false);
            textFieldNgaynhap.setText("");
            textFieldNgaynhap.setEnabled(false);
            btnThem.setEnabled(true);
            //btnXoa.setEnabled(false);
            //btnSua.setEnabled(false);
            btnLuu.setEnabled(false);
            //btnNhap.setEnabled(false);
            //btnSuaChitiet.setEnabled(false);
            //btnXem.setEnabled(false);
            //btnXoaChitiet.setEnabled(false);
        }
        if (condition == "themchitiet") {
            textFieldMapn.setEnabled(false);
            comboBoxNhanVien.setEnabled(false);
            comboBoxNhaCC.setSelectedItem(null);
            comboBoxNhaCC.setEnabled(true);
            //textFieldNgaylap.setEnabled(false);
            textFieldSoluong.setText("");
            textFieldSoluong.setEnabled(true);
            //textFieldNgaysx.setText("");
            //textFieldNgaysx.setEnabled(true);
            textFieldNgaynhap.setText("");
            textFieldNgaynhap.setEnabled(true);
            btnThem.setEnabled(false);
            //btnXoa.setEnabled(false);
            //btnSua.setEnabled(false);
            btnLuu.setEnabled(false);
            //btnNhap.setEnabled(true);
            //btnSuaChitiet.setEnabled(false);
            //btnXem.setEnabled(false);
            //btnXoaChitiet.setEnabled(false);
        }

    }

    public void unSetEnable(String condition) {
        if (condition == "themphieunhap") {
            textFieldMapn.setEnabled(true);
            comboBoxNhanVien.setEnabled(true);
            //textFieldNgaylap.setEnabled(true);
            btnThem.setEnabled(true);
            //btnXoa.setEnabled(false);
            //btnSua.setEnabled(false);
            btnLuu.setEnabled(false);
            //btnNhap.setEnabled(false);
            //btnSuaChitiet.setEnabled(false);
            //btnXem.setEnabled(false);
            //btnXoaChitiet.setEnabled(false);
        }
        if (condition == "themchitiet") {
            textFieldMapn.setEnabled(false);
            comboBoxNhaCC.setEnabled(false);
            textFieldSoluong.setEnabled(true);
            //textFieldNgaysx.setEnabled(true);
            textFieldNgaynhap.setEnabled(true);
            btnThem.setEnabled(false);
            //btnXoa.setEnabled(false);
            //btnSua.setEnabled(false);
            btnLuu.setEnabled(false);
            //btnNhap.setEnabled(true);
            //btnSuaChitiet.setEnabled(false);
            //btnXem.setEnabled(false);
            //btnXoaChitiet.setEnabled(false);
        }
    }

    public void setEnable() {
        textFieldMapn.setEnabled(false);
        comboBoxNhanVien.setEnabled(false);
        comboBoxNhaCC.setEnabled(false);
        //textFieldNgaylap.setEnabled(false);
        textFieldSoluong.setEnabled(false);
        //textFieldNgaysx.setEnabled(false);
        textFieldNgaynhap.setEnabled(false);
    }

    public Boolean checkEmtyValue(String condition) throws SQLException {
        // regular expression pattern
        if (condition == "themphieunhap") {
            if (textFieldMapn.getText().isEmpty()) {
                JOptionPane.showMessageDialog(contentPane, "Mã phiếu nhập trống!");
                textFieldMapn.requestFocus();
                return false;
            }
            if (!textFieldMapn.getText().isEmpty()) {
                NhapHangDAL nhd = new NhapHangDAL();
                ArrayList<NhapHang> arrPro = new ArrayList<NhapHang>();
                arrPro = nhd.docNhapHang("docphieunhap", null);
                if (fixbtn) {
                    for (NhapHang nh : arrPro) {
                        if (Integer.parseInt(oldMaPN) != Integer.parseInt(textFieldMapn.getText()) && nh.getMaPn() == Integer.parseInt(textFieldMapn.getText())) {
                            JOptionPane.showMessageDialog(contentPane, "Mã phiếu nhập đã tồn tại!");
                            textFieldMapn.requestFocus();
                            return false;
                        }
                    }
                }
                if (addbtn) {
                    for (NhapHang nh : arrPro) {
                        if (nh.getMaNv() == Integer.parseInt(textFieldMapn.getText())) {
                            JOptionPane.showMessageDialog(contentPane, "Mã phiếu nhập đã tồn tại!");
                            textFieldMapn.requestFocus();
                            return false;

                        }
                    }
                }
				/*
				 * if (textFieldNgaylap.getText().isEmpty()) {
				 * JOptionPane.showMessageDialog(contentPane, "Thời điểm lập rỗng!");
				 * textFieldNgaylap.requestFocus(); return false; } if
				 * (!textFieldNgaylap.getText().isEmpty()) { Pattern reg =
				 * Pattern.compile("^\\d{4}[\\-](0?[1-9]|1[012])[\\-](0?[1-9]|[12][0-9]|3[01])$"
				 * ); boolean kt = reg.matcher(textFieldNgaylap.getText()).matches(); if (kt ==
				 * false) { JOptionPane.showMessageDialog(contentPane,
				 * "Thời điểm lập phải có định dạng yyyy-mm-dd!");
				 * textFieldNgaylap.requestFocus(); return false; } }
				 */
            }
            if (condition == "themchitiet") {
                
                if (textFieldSoluong.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(contentPane, "Số lượng rỗng!");
                    textFieldSoluong.requestFocus();
                    return false;
                }
                if (!textFieldSoluong.getText().isEmpty()) {
                    Pattern reg = Pattern.compile("^[1-9][0-9]*$");
                    boolean kt = reg.matcher(textFieldSoluong.getText()).matches();
                    if (kt == false) {
                        JOptionPane.showMessageDialog(contentPane, "Số lượng phải là số nguyên dương!");
                        textFieldSoluong.requestFocus();
                        return false;
                    }
                }
				/*
				 * if (textFieldNgaysx.getText().isEmpty()) {
				 * JOptionPane.showMessageDialog(contentPane, "Ngày sản xuất rỗng!");
				 * textFieldNgaysx.requestFocus(); return false; } if
				 * (!textFieldNgaysx.getText().isEmpty()) { Pattern reg =
				 * Pattern.compile("^\\d{4}[\\-](0?[1-9]|1[012])[\\-](0?[1-9]|[12][0-9]|3[01])$"
				 * ); boolean kt = reg.matcher(textFieldNgaysx.getText()).matches(); if (kt ==
				 * false) { JOptionPane.showMessageDialog(contentPane,
				 * "Ngày sản xuất phải có định dạng yyyy-mm-dd!");
				 * textFieldNgaysx.requestFocus(); return false; } }
				 */
                if (textFieldNgaynhap.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(contentPane, "Ngày nhập rỗng!");
                    textFieldNgaynhap.requestFocus();
                    return false;
                }
                if (!textFieldNgaynhap.getText().isEmpty()) {
                    Pattern reg = Pattern.compile("^\\d{4}[\\-](0?[1-9]|1[012])[\\-](0?[1-9]|[12][0-9]|3[01])$");
                    boolean kt = reg.matcher(textFieldNgaynhap.getText()).matches();
                    if (kt == false) {
                        JOptionPane.showMessageDialog(contentPane, "Ngày nhập phải có định dạng yyyy-mm-dd!");
                        textFieldNgaynhap.requestFocus();
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public NhapHangGUI() throws SQLException {

        setTitle("Quản lý nhập");
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
        tabbedPane.addTab("Thông tin phiếu nhập", null, panel_2, null);

        JPanel panel_5 = new JPanel();
        panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_5.setBounds(0, 10, 1067, 200);

        JLabel lblNewLabel_1 = new JLabel("Mã phiếu nhập");
        lblNewLabel_1.setBounds(20, 10, 100, 25);

        textFieldMapn = new JTextField();
        textFieldMapn.setBounds(130, 10, 200, 25);
        textFieldMapn.setEnabled(false);
        textFieldMapn.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Nhân viên");
        lblNewLabel_3.setBounds(20, 50, 100, 25);

        comboBoxNhanVien = new JComboBox();
        comboBoxNhanVien.setBounds(130, 50, 200, 25);
        comboBoxNhanVien.setEnabled(false);

		/*
		 * JLabel lblNewLabel_4 = new JLabel("Thời điểm lập");
		 * lblNewLabel_4.setBounds(20, 90, 100, 25);
		 * 
		 * 
		 * textFieldNgaylap = new JTextField(); textFieldNgaylap.setBounds(130, 90, 200,
		 * 25); textFieldNgaylap.setEnabled(false); textFieldNgaylap.setColumns(10);
		 */

        JLabel lblNewLabel_6 = new JLabel("Sản phẩm");
        lblNewLabel_6.setBounds(20, 90, 100, 25);

        textFieldSanPham = new JTextField();
        textFieldSanPham.setBounds(130, 90, 200, 25);
        textFieldSanPham.setEnabled(false);
        
        JLabel lblNewLabel_loaihang = new JLabel("Loại hàng");
        lblNewLabel_loaihang.setBounds(20, 130, 100, 25);

        textFieldLoaiHang = new JTextField();
        textFieldLoaiHang.setBounds(130, 130, 200, 25);
        textFieldLoaiHang.setEnabled(false);

        JLabel lblNewLabel_7 = new JLabel("Nhà Cung Cấp");
        lblNewLabel_7.setBounds(350, 10, 100, 25);

        comboBoxNhaCC = new JComboBox();
        comboBoxNhaCC.setBounds(460, 10, 200, 25);
        comboBoxNhaCC.setEnabled(false);
        
        JLabel lblNewLabel_gianhap = new JLabel("Giá nhập");
        lblNewLabel_gianhap.setBounds(350, 90, 100, 25);

        textFieldGiaNhap = new JTextField();
        textFieldGiaNhap.setBounds(460, 90, 200, 25);
        textFieldGiaNhap.setEnabled(false);
        textFieldGiaNhap.setColumns(10);

        JLabel lblNewLabel_8 = new JLabel("Số lượng");
        lblNewLabel_8.setBounds(350, 130, 100, 25);

        textFieldSoluong = new JTextField();
        textFieldSoluong.setBounds(460, 130, 200, 25);
        textFieldSoluong.setEnabled(false);
        textFieldSoluong.setColumns(10);

		/*
		 * JLabel lblNewLabel_9 = new JLabel("Ngày sản xuất");
		 * lblNewLabel_9.setBounds(350, 90, 100, 25);
		 * 
		 * 
		 * textFieldNgaysx = new JTextField(); textFieldNgaysx.setBounds(460, 90, 200,
		 * 25); textFieldNgaysx.setEnabled(false); textFieldNgaysx.setColumns(10);
		 * 
		 */
        JLabel lblNewLabel_13 = new JLabel("Ngày nhập");
        lblNewLabel_13.setBounds(350, 50, 100, 25);

        textFieldNgaynhap = new JTextField();
        textFieldNgaynhap.setBounds(460, 50, 200, 25);
        textFieldNgaynhap.setEnabled(false);
        textFieldNgaynhap.setColumns(10);

		/*
		 * btnXem.setBounds(700, 10, 200, 100); btnXem.setEnabled(false);
		 * btnXem.setFocusPainted(false); btnXem.addActionListener(new ActionListener()
		 * { public void actionPerformed(ActionEvent e) { try {
		 * resetValue("themchitiet"); setEnable(); //btnXoa.setVisible(false);
		 * hienthiphieunhap("chitiet"); } catch (SQLException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); } } });
		 */

        JPanel panel_6 = new JPanel();
        panel_6.setBounds(0, 210, 1067, 78);
        btnLuu.setBounds(10, 10, 104, 53);

        btnLuu.setEnabled(false);
        btnLuu.setFocusPainted(false);
        btnLuu.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Save.png"))));
        btnLuu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (checkEmtyValue("themphieunhap")) {
                        // -------------------Copy img vao thu muc Image cua project
                        if (selectedFile != null) {
                            Path sourcePath = selectedFile.toPath();
                            Path projectPath = Paths.get(System.getProperty("user.dir")); // get the path to the project
                            // directory
                            Path imageDirectory = projectPath.resolve("src//GUI//Image"); // resolve the path to the Image

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

                        NhapHang nh = new NhapHang();
                        NhapHangDAL luunh;
                        if (addbtn) {
                            try {
                                luunh = new NhapHangDAL();
                                nh.setMaPn(Integer.parseInt(textFieldMapn.getText()));
                                int manv = luunh.layMaNv((String) (comboBoxNhanVien.getSelectedItem()));
                                nh.setMaNv(manv);
                                //nh.setThoiDiemLap(textFieldNgaylap.getText());
                                boolean checkAddPro = luunh.themphieunhap(nh, "themphieunhap", null, null, null);
                                if (checkAddPro) {
                                    JOptionPane.showMessageDialog(contentPane, "Thêm thành công");
                                    resetValue("themphieunhap");
                                    setEnable();
                                    hienthiphieunhap("phieunhap");
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
                                luunh = new NhapHangDAL();
                                nh.setMaPn(Integer.parseInt(textFieldMapn.getText()));
                                int manv = luunh.layMaNv((String) (comboBoxNhanVien.getSelectedItem()));
                                nh.setMaNv(manv);
                                //nh.setThoiDiemLap(textFieldNgaylap.getText());
                                boolean checkAddPro = luunh.themphieunhap(nh, "suaphieunhap", oldMaPN, null, null);
                                if (checkAddPro) {
                                    JOptionPane.showMessageDialog(contentPane, "Sửa thành công");
                                    resetValue("themphieunhap");
                                    setEnable();
                                    hienthiphieunhap("phieunhap");
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
                    if (checkEmtyValue("themchitiet")) {
                        // -------------------Copy img vao thu muc Image cua project
                        if (selectedFile != null) {
                            Path sourcePath = selectedFile.toPath();
                            Path projectPath = Paths.get(System.getProperty("user.dir")); // get the path to the project
                            // directory
                            Path imageDirectory = projectPath.resolve("src//GUI//Image"); // resolve the path to the Image

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

                        NhapHang nh = new NhapHang();
                        NhapHangDAL luunh;
                        if (addifbtn) {
                            try {
                                luunh = new NhapHangDAL();
                                nh.setMaPn(Integer.parseInt(textFieldMapn.getText()));
                                int mancc = luunh.layMaNcc((String) (comboBoxNhaCC.getSelectedItem()));
                                nh.setMaNcc(mancc);
                                nh.setSoLuong(Integer.parseInt(textFieldSoluong.getText()));
                                //nh.setNgaySanXuat(textFieldNgaysx.getText());
                                nh.setNgayNhap(textFieldNgaynhap.getText());
                                boolean checkAddPro = luunh.themphieunhap(nh, "themchitiet", oldMaPN, oldMaSP, oldMaNCC);
                                if (checkAddPro) {
                                    JOptionPane.showMessageDialog(contentPane, "Thêm thành công");
                                    resetValue("themchitiet");
                                    setEnable();
                                    hienthiphieunhap("chitiet");
                                    addifbtn = false;
                                } else {
                                    JOptionPane.showMessageDialog(contentPane, "Thêm thất bại do trùng khóa");
                                }
                            } catch (SQLException e2) {
                                // TODO Auto-generated catch block
                                e2.printStackTrace();
                            }
                        }
                        if (fixifbtn) {
                            try {
                                luunh = new NhapHangDAL();
                                nh.setMaPn(Integer.parseInt(textFieldMapn.getText()));
                                int mancc = luunh.layMaNcc((String) (comboBoxNhaCC.getSelectedItem()));
                                nh.setMaNcc(mancc);
                                nh.setSoLuong(Integer.parseInt(textFieldSoluong.getText()));
                                //nh.setNgaySanXuat(textFieldNgaysx.getText());
                                nh.setNgayNhap(textFieldNgaynhap.getText());
                                boolean checkAddPro = luunh.themphieunhap(nh, "suachitiet", oldMaPN, oldMaSP, oldMaNCC);
                                if (checkAddPro) {
                                    JOptionPane.showMessageDialog(contentPane, "Sửa thành công");
                                    resetValue("themchitiet");
                                    setEnable();
                                    hienthiphieunhap("chitiet");
                                    fixifbtn = false;
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
        btnThem.setBounds(146, 10, 104, 53);
        btnThem.setFocusPainted(false);
        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    addbtn = true;
                    fixbtn = false;
                    addifbtn = false;
                    fixifbtn = false;
                    resetValue("themphieunhap");
                    textFieldMapn.setEnabled(false);
                    NhapHangDAL nh;
                    nh = new NhapHangDAL();
                    int lastMaPN = nh.getLastMaPN();
                    textFieldMapn.setText("" + (lastMaPN + 1));
                    btnThem.setEnabled(false);
                    btnLuu.setEnabled(true);
                    //btnXoa.setEnabled(false);
                    //btnSua.setEnabled(false);
                    try {
                        hienthiphieunhap("themphieunhap");
                    } catch (SQLException e3) {
                        // TODO Auto-generated catch block
                        e3.printStackTrace();
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(NhapHangGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        btnThem.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Add.png"))));
		
		  btnSua.setBounds(283, 10, 104, 53);
		  
		  btnSua.setEnabled(false); btnSua.setIcon(new ImageIcon(
		  Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(
		  ".\\Image\\Change.png")))); btnSua.setFocusPainted(false);
		  btnSua.addActionListener(new ActionListener() { public void
		  actionPerformed(ActionEvent e) { addbtn = false; fixbtn = true; addifbtn =
		  false; fixifbtn = false; textFieldMapn.setEnabled(false); oldMaPN =
		  textFieldMapn.getText(); unSetEnable("themphieunhap");
		  btnThem.setEnabled(false); btnLuu.setEnabled(true);
		  
		  try { hienthiphieunhap("themphieunhap"); } catch (SQLException e1) { // TODO
		  //Auto-generated catch block e1.printStackTrace(); 
			  } } });
		  /*btnXoaChitiet.setBounds(419, 10, 104, 53); btnXoaChitiet.setEnabled(false);
		  btnXoaChitiet.setIcon(new ImageIcon(
		  Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(
		  ".\\Image\\Delete.png")))); btnXoaChitiet.setFocusPainted(false);
		  btnXoaChitiet.addActionListener(new ActionListener() { public void
		  actionPerformed(ActionEvent e) { int confirmed =
		  JOptionPane.showConfirmDialog(null, "Bạn muốn xóa chi tiết này",
		  "Confirmation", JOptionPane.YES_NO_OPTION); if (confirmed ==
		  JOptionPane.YES_OPTION) { NhapHangDAL deletePn; try { deletePn = new
		  NhapHangDAL(); NhapHangDAL luunh; String sp=null, ncc=null; try { luunh = new
		  NhapHangDAL(); int masp = luunh.layMaSp((String)
		  (comboBoxSanPham.getSelectedItem())); System.out.println(masp); sp =
		  String.valueOf(masp); int mancc = luunh.layMaNcc((String)
		  (comboBoxNhaCC.getSelectedItem())); System.out.println(mancc); ncc =
		  String.valueOf(mancc); } catch (SQLException e2) { // TODO Auto-generated
		  catch block e2.printStackTrace(); } System.out.println(sp);
		  System.out.println(ncc); System.out.println(textFieldMapn.getText()); if
		  (deletePn.xoaChiTiet(textFieldMapn.getText(), sp, ncc)) {
		  JOptionPane.showMessageDialog(contentPane, "Xóa thành công!");
		  hienthiphieunhap("chitiet"); resetValue("themchitiet"); setEnable(); }
		  
		  } catch (SQLException e1) { // TODO Auto-generated catch block
		  e1.printStackTrace(); }
		  
		  }*/
		  
		  ;
		  
		  btnXoa.setBounds(419, 10, 104, 53); btnXoa.setVisible(true);
		  btnXoa.setEnabled(false); btnXoa.setIcon(new ImageIcon(
		  Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(
		  ".\\Image\\Delete.png")))); btnXoa.setFocusPainted(false);
		  btnXoa.addActionListener(new ActionListener() { public void
		  actionPerformed(ActionEvent e) { int confirmed =
		  JOptionPane.showConfirmDialog(null, "Bạn muốn xóa phiếu nhập này",
		  "Confirmation", JOptionPane.YES_NO_OPTION); if (confirmed ==
		  JOptionPane.YES_OPTION) { NhapHangDAL deletePn; try { deletePn = new
		  NhapHangDAL(); if (deletePn.xoaNhapHang(textFieldMapn.getText())) {
		  JOptionPane.showMessageDialog(contentPane, "Xóa thành công!");
		  hienthiphieunhap("themphieunhap"); resetValue("themphieunhap"); setEnable();
		  } } catch (SQLException e1) { // TODO Auto-generated catch block
		  e1.printStackTrace(); }
		  
		  }
		  
		  } });
		 

        JButton btnDongBo = new JButton("");
        btnDongBo.setBounds(563, 10, 104, 53);
        btnDongBo.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(NhapHangGUI.class.getResource(".\\Image\\Refresh-icon.png"))));
        btnDongBo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    resetValue("themphieunhap");
                    setEnable();
                    
                    hienthiphieunhap("phieunhap");
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
		/*
		 * btnNhap.setBounds(700, 10, 100, 53); btnNhap.setEnabled(false);
		 * btnNhap.setFocusPainted(false); btnNhap.addActionListener(new
		 * ActionListener() { public void actionPerformed(ActionEvent e) { addbtn =
		 * false; fixbtn = false; addifbtn = true; fixifbtn = false;
		 * resetValue("themchitiet"); btnThem.setEnabled(false);
		 * btnLuu.setEnabled(true); //btnXoa.setEnabled(false);
		 * //btnSua.setEnabled(false); btnXem.setEnabled(false);
		 * //btnSuaChitiet.setEnabled(false); //btnXoaChitiet.setEnabled(false);
		 * 
		 * try { hienthiphieunhap("themchitiet"); } catch (SQLException e3) { // TODO
		 * Auto-generated catch block e3.printStackTrace(); } } });
		 */
		/*
		 * btnSuaChitiet.setBounds(830, 10, 150, 53); btnSuaChitiet.setEnabled(false);
		 * btnSuaChitiet.setFocusPainted(false); btnSuaChitiet.addActionListener(new
		 * ActionListener() { public void actionPerformed(ActionEvent e) { addbtn =
		 * false; fixbtn = false; addifbtn = false; fixifbtn = true;
		 * textFieldMapn.setEnabled(false); comboBoxSanPham.setEnabled(false);
		 * comboBoxNhaCC.setEnabled(false); oldMaPN = textFieldMapn.getText();
		 * NhapHangDAL luunh; try { luunh = new NhapHangDAL(); int masp =
		 * luunh.layMaSp((String) (comboBoxSanPham.getSelectedItem())); oldMaSP =
		 * String.valueOf(masp); int mancc = luunh.layMaNcc((String)
		 * (comboBoxNhaCC.getSelectedItem())); oldMaNCC = String.valueOf(mancc); } catch
		 * (SQLException e2) { // TODO Auto-generated catch block e2.printStackTrace();
		 * } unSetEnable("themchitiet"); btnThem.setEnabled(false);
		 * btnNhap.setEnabled(false); btnXoa.setEnabled(false);
		 * btnXem.setEnabled(false); btnSua.setEnabled(false); btnLuu.setEnabled(true);
		 * 
		 * try { hienthiphieunhap("chitiet"); } catch (SQLException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); } } } );
		 */
        btnDongBo.setFocusPainted(
                false);

        JPanel panel_8 = new JPanel();

        panel_8.setBounds(
                0, 250, 1067, 300);
        
        lbThongbao_chitiet.setBounds(
                50, 40, 569, 32);

        lbThongbao_chitiet.setText(
                "DANH SÁCH SẢN PHẨM TRONG PHIẾU NHẬP");

        lbThongbao_chitiet.setForeground(
                new Color(255, 128, 128));

        lbThongbao_chitiet.setFont(
                new Font("Arial", Font.BOLD, 20));
        
        lbThongbao.setBounds(
                670, 40, 569, 32);

        lbThongbao.setText(
                "DANH SÁCH PHIẾU NHẬP");

        lbThongbao.setForeground(
                new Color(255, 128, 128));

        lbThongbao.setFont(
                new Font("Arial", Font.BOLD, 20));
        
        table_chitiet = new JTable();
        JTableHeader header_chitiet = table_chitiet.getTableHeader();

        header_chitiet.setPreferredSize(
                new Dimension(header_chitiet.getWidth(), 30));

        table_chitiet.setRowHeight(
                30);
        table_chitiet.setFocusable(
                false);

        table = new JTable();
        JTableHeader header = table.getTableHeader();

        header.setPreferredSize(
                new Dimension(header.getWidth(), 30));

        table.setRowHeight(
                30);
        table.setFocusable(
                false);
        table.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                int col = table.getColumnCount();
                if (col == 3) {
                    int row = table.getSelectedRow(); // get the selected row
                    String maPN = table.getModel().getValueAt(row, 0).toString();
                    String maNV = table.getModel().getValueAt(row, 1).toString(); // get the value of the first column
                    String thoiDiemLap = table.getModel().getValueAt(row, 2).toString(); // get the value of the second column
//				String img = table.getModel().getValueAt(row, 10).toString();
                    textFieldMapn.setText(maPN);
                    //textFieldNgaylap.setText(thoiDiemLap);

                    setEnable();
                    //btnXoa.setVisible(true);
                    //btnXoa.setEnabled(true);
                    //btnSua.setEnabled(true);
                    btnThem.setEnabled(true);
                    btnLuu.setEnabled(false);
                    //btnNhap.setEnabled(false);
                    //btnSuaChitiet.setEnabled(false);
                    //btnXem.setEnabled(true);

//				g.dipnose();
                    NhapHangDAL testnv;
                    try {
                        testnv = new NhapHangDAL();
                        ArrayList<NhanVien> arrMaNV = testnv.docNhanVienMaNV(Integer.parseInt(maNV));
                        DefaultComboBoxModel combo = new DefaultComboBoxModel();
                        comboBoxNhanVien.setModel(combo);

                        combo.addElement(arrMaNV.get(0).getTenNv());

                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                if (col == 6) {
                    int row = table.getSelectedRow(); // get the selected row
                    String maPN = table.getModel().getValueAt(row, 0).toString();
                    String maSP = table.getModel().getValueAt(row, 1).toString(); // get the value of the first column
                    String maNCC = table.getModel().getValueAt(row, 2).toString(); // get the value of the second column
                    String soLuong = table.getModel().getValueAt(row, 3).toString();
                    String ngaySanXuat = table.getModel().getValueAt(row, 4).toString();
                    String ngayNhap = table.getModel().getValueAt(row, 5).toString();
//				String img = table.getModel().getValueAt(row, 10).toString();
                    textFieldMapn.setText(maPN);
                    textFieldSoluong.setText(soLuong);
                    //textFieldNgaysx.setText(ngaySanXuat);
                    textFieldNgaynhap.setText(ngayNhap);

                    setEnable();
                    //btnXoa.setEnabled(false);
                    //btnSua.setEnabled(false);
                    btnThem.setEnabled(false);
                    btnLuu.setEnabled(false);
                    //btnNhap.setEnabled(false);
                    //btnSuaChitiet.setEnabled(true);
                    //btnXem.setEnabled(false);
                    //btnXoaChitiet.setEnabled(true);

//				g.dipnose();
                    NhapHangDAL testsp;
                    try {
                        testsp = new NhapHangDAL();
                        ArrayList<SanPham> arrMaSP = testsp.docSanPhamMaSP(Integer.parseInt(maSP));
                        DefaultComboBoxModel combo = new DefaultComboBoxModel();

                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    NhapHangDAL testncc;
                    try {
                        testncc = new NhapHangDAL();
                        ArrayList<NhaCungCap> arrMaNCC = testncc.docNhaCungCapMaNCC(Integer.parseInt(maNCC));
                        DefaultComboBoxModel combo = new DefaultComboBoxModel();
                        comboBoxNhaCC.setModel(combo);

                        combo.addElement(arrMaNCC.get(0).getTenNCC());

                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }

        });
        scrollPane_chitiet.setBounds(0, 70, 530, 210);

        scrollPane_chitiet.setViewportView(table_chitiet);
        
        scrollPane.setBounds(540, 70, 530, 210);

        scrollPane.setViewportView(table);
        panel_8.setLayout(null);
        panel_8.add(lbThongbao);
        panel_8.add(lbThongbao_chitiet);
        panel_8.add(scrollPane_chitiet);
        panel_8.add(scrollPane);
        panel_6.setLayout(null);
        panel_6.add(btnLuu);
        panel_6.add(btnThem);
        panel_6.add(btnSua);
        panel_6.add(btnXoa);
        panel_6.add(btnDongBo);
        //panel_6.add(btnNhap);
        //panel_6.add(btnXoaChitiet);
        //panel_6.add(btnSuaChitiet);
        icon = new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource("/GUI/Image/Background.png")));
        Image image_bg = icon.getImage();
        Image resizedImg_bg = image_bg.getScaledInstance(1300, 130, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon_bg = new ImageIcon(resizedImg_bg);
        panel_2.setLayout(null);
        panel_5.setLayout(null);
        lbThemanh.setBounds(10, 0, 139, 123);
        panel_5.add(lbThemanh);
        panel_5.add(btnCapNhatAnh);
        panel_5.add(lblNewLabel_6);
        panel_5.add(lblNewLabel_1);
        panel_5.add(textFieldMapn);
        panel_5.add(lblNewLabel_7);
        panel_5.add(comboBoxNhanVien);
        panel_5.add(lblNewLabel_3);
        //panel_5.add(lblNewLabel_4);
        panel_5.add(textFieldSanPham);
        panel_5.add(lblNewLabel_loaihang);
        panel_5.add(textFieldLoaiHang);
        panel_5.add(comboBoxNhaCC);
        panel_5.add(lblNewLabel_8);
        //panel_5.add(lblNewLabel_9);
        //panel_5.add(textFieldNgaylap);
        panel_5.add(lblNewLabel_gianhap);
        panel_5.add(textFieldGiaNhap);
        panel_5.add(textFieldSoluong);
        panel_5.add(lblNewLabel_13);
        //panel_5.add(textFieldNgaysx);
        panel_5.add(textFieldNgaynhap);
        //panel_5.add(btnXem);
        panel_2.add(panel_5);

        textFieldSearch = new JTextField();
        //textFieldSearch.setBounds(790, 100, 250, 25);
        panel_5.add(textFieldSearch);
        textFieldSearch.setColumns(10);

        JButton btnTimKiem = new JButton("Tìm Kiếm");
        btnTimKiem.setFocusPainted(false);
        btnTimKiem.setHorizontalAlignment(SwingConstants.LEADING);
        btnTimKiem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textFieldSearch.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(contentPane, "Chưa nhập nội dung tìm kiếm!");
                    textFieldSearch.requestFocus();
                } else {/*
                    NhapHangDAL tkpn;
                    ArrayList<NhapHang> arrPro = new ArrayList<NhapHang>(); // TODO Auto-generated catch block
                    arrPro = tkpn.docNhapHang("timkiem",null);
                     */
                }
            }
        });
        ImageIcon iconSearch = new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Find.png")));
        Image imageSearch = iconSearch.getImage();
        Image resizedImgSearch = imageSearch.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconSearch = new ImageIcon(resizedImgSearch);
        btnTimKiem.setIcon(resizedIconSearch);

        //btnTimKiem.setBounds(660, 100, 130, 25);
        panel_5.add(btnTimKiem);
        
        JButton btnNewButton = new JButton("Hệ Thống");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 HomeAdmin home=new HomeAdmin();
                 setVisible(false);
              
                 home.setVisible(true);
                 home.setLocationRelativeTo(null);
        	}
        });
        btnNewButton.setBounds(934, 10, 123, 41);
        panel_5.add(btnNewButton);
        panel_2.add(panel_6);
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

        JLabel lblNewLabel = new JLabel("QUẢN LÝ NHẬP HÀNG");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(198, 0, 664, 63);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lbIconShop = new JLabel("");
        lbIconShop.setBounds(10, 0, 170, 65);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
        icon = new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//shop.png")));
        Image image = icon.getImage();
        Image resizedImg = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);
        panel.setLayout(null);
        lbIconShop.setIcon(resizedIcon);
        panel.add(lbIconShop);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_10 = new JLabel("");
        icon = new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Background2.png")));
        Image image2 = icon.getImage();
        Image resizedImg2 = image2.getScaledInstance(1300, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(resizedImg2);
        lblNewLabel_10.setIcon(resizedIcon2);
        lblNewLabel_10.setBounds(0, 0, 1078, 104);
        panel.add(lblNewLabel_10);
        contentPane.setLayout(null);
        contentPane.add(tabbedPane);
        contentPane.add(panel_1);
        contentPane.add(panel);
        hienthiphieunhap("phieunhap");

    }
}
