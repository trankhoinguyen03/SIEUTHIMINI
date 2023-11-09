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
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

import BLL.ChiTietPhieuNhapBLL;
import BLL.DangNhapBLL;
import BLL.KhoBLL;
import BLL.LoaiHangBLL;
import BLL.NhaCungCapBLL;
import BLL.NhanVienBLL;
import BLL.NhapHangBLL;
import BLL.SanPhamBLL;
import DTO.NhapHang;
import DAL.NhapHangDAL;
import DAL.NhanVienDAL;
import DTO.NhanVien;

import DAL.SanPhamDAL;
import DTO.LoaiHang;
import DTO.NhaCungCap;
import DTO.PhieuNhapChiTiet;
import DTO.SanPham;
import DTO.TaiKhoan;
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

public class NhapHangGui extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldMapn;
    private JTextField textFieldNhanVien;
    private JTextField textFieldMaNhanVien;
    private JComboBox comboBoxNhaCC;
    private JComboBox comboBoxSanPham;
    private JComboBox comboBoxLoaiHang;
    //private JTextField textFieldNgaylap;
    private JTextField textFieldGiaNhap;
    private JTextField textFieldSoluong;
    //private JTextField textFieldNgaysx;
    private JTextField textFieldSanPhamMoi;
    private JDateChooser dateFieldNsx;
    private JDateChooser dateFieldHsd;
    private JTable table;
    private JTable table_chitiet;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NhapHangGui frame = new NhapHangGui();
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
    //JButton btnAn = new JButton("Ẩn");
    JButton btnThemSp = new JButton("Chi tiết");
    boolean check = true;
    JButton btnThem = new JButton("Thêm phiếu");
    JButton btnLuu = new JButton("Lưu");
    JButton btnChitiet = new JButton("Nhập cũ");
    int lastRow;
    JRadioButton radioSapxepsoluong = new JRadioButton("Số lượng");
    JRadioButton radioSapxepma = new JRadioButton("Mã phiếu nhập");
    ButtonGroup groupSapxep = new ButtonGroup();
    JScrollPane scrollPane = new JScrollPane();
    JScrollPane scrollPane_chitiet = new JScrollPane();
    boolean addbtn, addnewbtn, detailbtn = false;
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
    boolean checkHide = true;
   
    
    TaiKhoan taiKhoan = DangNhapBLL.taiKhoan;
    public void hienthiphieunhap(String condition) throws SQLException {
        NhapHangBLL nhBll = new NhapHangBLL();
        ChiTietPhieuNhapBLL ctpnBll = new ChiTietPhieuNhapBLL();
        ArrayList<NhapHang> arrNh = new ArrayList<NhapHang>();
        ArrayList<PhieuNhapChiTiet> arrCt = new ArrayList<PhieuNhapChiTiet>();
        if (condition == "phieunhap" || condition == "themchitiet" || condition == "themsanphammoi") {
        	if(taiKhoan.getQuyen().equals("RL2")) {
                arrNh = nhBll.getPhieuNhap();
        	} else {
        		arrNh = nhBll.getPhieuNhapNV(taiKhoan.getMaNV());
        	}
        }
        if (condition == "chitiet" || condition == "themchitiet" || condition == "themsanphammoi") {
            arrCt = ctpnBll.getChiTietPN(textFieldMapn.getText());
        }
        if (condition == "themphieunhap") {
        	if(taiKhoan.getQuyen().equals("RL2")) {
                arrNh = nhBll.getPhieuNhap();
        	} else {
        		arrNh = nhBll.getPhieuNhapNV(taiKhoan.getMaNV());
        	}
            NhanVienBLL testnv = new NhanVienBLL();
            String lastMaPn = nhBll.getLastMaPN();
            String maPn = lastMaPn.substring(lastMaPn.length()-3, lastMaPn.length());
            int check = Integer.parseInt(maPn);
            if(check < 9) {
            	textFieldMapn.setText("PN00"+(check+1));
            } else if(check < 99) {
            	textFieldMapn.setText("PN0"+(check+1));
            } else {
            	textFieldMapn.setText("PN"+(check+1));
            }
            textFieldNhanVien.setText(testnv.getTenNV(taiKhoan.getMaNV()));
            textFieldMaNhanVien.setText(taiKhoan.getMaNV());
            NhaCungCapBLL testncc = new NhaCungCapBLL();
            ArrayList<NhaCungCap> arrMaNCC = testncc.getNhaCungCap();
            DefaultComboBoxModel comboncc = new DefaultComboBoxModel();
            comboBoxNhaCC.setModel(comboncc);
            for (NhaCungCap mancc : arrMaNCC) {
                comboncc.addElement(mancc.getTenNCC());
            }
        }
        if (condition == "themchitiet") {
            //arrNh = nhBll.docNhapHang("docchitiet", textFieldMapn.getText());
            SanPhamBLL testsp = new SanPhamBLL();
            ArrayList<SanPham> arrMaSP = testsp.getSanPham();
            DefaultComboBoxModel combosp = new DefaultComboBoxModel();
            comboBoxSanPham.setModel(combosp);
            for (SanPham masp : arrMaSP) {
                combosp.addElement(masp.getTenSp());
            }
        }
        if (condition == "themsanphammoi") {
            //arrNh = nhBll.docNhapHang("docchitiet", textFieldMapn.getText());
            LoaiHangBLL testlh = new LoaiHangBLL();
            ArrayList<LoaiHang> arrMaLH = testlh.getLoaiHang();
            DefaultComboBoxModel combosp = new DefaultComboBoxModel();
            comboBoxLoaiHang.setModel(combosp);
            for (LoaiHang malh : arrMaLH) {
                combosp.addElement(malh.getTenLH());
            }
        }
        
        if (condition == "phieunhap" || condition == "themphieunhap") {
        	
            String[] columnNames = {"Mã Phiếu Nhập", "Nhân Viên", "Nhà Cung Cấp", "Tổng Tiền", "Ngày Nhập"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            table.setModel(model);
            model.setRowCount(0);
            for (NhapHang nhdata : arrNh) {
                //NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
                Object[] row = new Object[]{nhdata.getMaPn(), nhdata.getMaNv(), nhdata.getMaNcc(), nhdata.getTongTien(), nhdata.getNgayNhap()};
                model.addRow(row);
            }
            String[] columnNames_chitiet = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Thành Tiền"};
            DefaultTableModel model_chitiet = new DefaultTableModel(columnNames_chitiet, 0);
            
            table_chitiet.setModel(model_chitiet);
            model_chitiet.setRowCount(0);
            for (PhieuNhapChiTiet ctdata : arrCt) {
                Object[] row = new Object[]{};
                model_chitiet.addRow(row);
            }
        }
        if (condition == "chitiet") {
        	SanPhamBLL spbll = new SanPhamBLL();
            String[] columnNames_chitiet = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Thành Tiền"};
            DefaultTableModel model_chitiet = new DefaultTableModel(columnNames_chitiet, 0);
            
            table_chitiet.setModel(model_chitiet);
            model_chitiet.setRowCount(0);
            for (PhieuNhapChiTiet ctdata : arrCt) {
            	String nameSp = spbll.getTenSP(ctdata.getMaSP());
                Object[] row = new Object[]{ctdata.getMaSP(), nameSp, ctdata.getSoLuong(), ctdata.getThanhTien()};
                model_chitiet.addRow(row);
            }
        }
        if (condition == "themchitiet" || condition == "themsanphammoi") {
        	String[] columnNames = {"Mã Phiếu Nhập", "Nhân Viên", "Nhà Cung Cấp", "Tổng Tiền", "Ngày Nhập"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            table.setModel(model);
            model.setRowCount(0);
            for (NhapHang nhdata : arrNh) {
                //NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
                Object[] row = new Object[]{nhdata.getMaPn(), nhdata.getMaNv(), nhdata.getMaNcc(), nhdata.getTongTien(), nhdata.getNgayNhap()};
                model.addRow(row);
            }
        	SanPhamBLL spbll = new SanPhamBLL();
            String[] columnNames_chitiet = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Thành Tiền"};
            DefaultTableModel model_chitiet = new DefaultTableModel(columnNames_chitiet, 0);
            
            table_chitiet.setModel(model_chitiet);
            model_chitiet.setRowCount(0);
            for (PhieuNhapChiTiet ctdata : arrCt) {
            	String nameSp = spbll.getTenSP(ctdata.getMaSP());
                Object[] row = new Object[]{ctdata.getMaSP(), nameSp, ctdata.getSoLuong(), ctdata.getThanhTien()};
                model_chitiet.addRow(row);
            }
        }
    }
    
	public String formatDateToString(java.util.Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateFormat.format(date);
		return dateString;
	}
    public void resetValue(String condition) {
        if (condition == "load") {
            textFieldMapn.setText("");
            comboBoxNhaCC.setSelectedItem(null);
            comboBoxNhaCC.setEnabled(false);
            comboBoxSanPham.setSelectedItem(null);
            comboBoxSanPham.setEnabled(false);
            comboBoxLoaiHang.setSelectedItem(null);
            comboBoxLoaiHang.setEnabled(false);
            textFieldGiaNhap.setText("");
            textFieldGiaNhap.setEnabled(false);
            textFieldSoluong.setText("");
            textFieldSoluong.setEnabled(false);
            textFieldSanPhamMoi.setText("");
            textFieldSanPhamMoi.setEnabled(false);
            dateFieldNsx.setDate(null);;
            dateFieldNsx.setEnabled(false);
            dateFieldHsd.setDate(null);
            dateFieldHsd.setEnabled(false);
            btnThem.setEnabled(true);
            btnLuu.setEnabled(false);
            //btnAn.setEnabled(false);
        }
        if (condition == "themphieunhap") {
        	comboBoxNhaCC.setSelectedItem(null);
        	comboBoxNhaCC.setEnabled(true);
            textFieldMapn.setText("");
            btnThem.setEnabled(false);
            btnLuu.setEnabled(true);
            //btnAn.setEnabled(false);
        }
        if (condition == "themchitiet") {
        	comboBoxSanPham.setSelectedItem(null);
        	comboBoxSanPham.setEnabled(true);
            textFieldSoluong.setText("");
            textFieldSoluong.setEnabled(true);
            btnThem.setEnabled(false);
            btnThemSp.setEnabled(false);
            btnLuu.setEnabled(true);
            //btnAn.setEnabled(false);
        }
        if (condition == "themsanphammoi") {
        	comboBoxLoaiHang.setSelectedItem(null);
        	comboBoxLoaiHang.setEnabled(true);
        	textFieldGiaNhap.setText("");
        	textFieldGiaNhap.setEnabled(true);
            textFieldSoluong.setText("");
            textFieldSoluong.setEnabled(true);
            textFieldSanPhamMoi.setText("");
            textFieldSanPhamMoi.setEnabled(true);
            dateFieldNsx.setDate(null);
            dateFieldNsx.setEnabled(true);
            dateFieldHsd.setDate(null);
            dateFieldHsd.setEnabled(true);
            btnThem.setEnabled(false);
            btnChitiet.setEnabled(false);
            btnLuu.setEnabled(true);
            //btnAn.setEnabled(false);
        }

    }
    
    public void hideField() {
    	btnThemSp.setVisible(false);
    	btnChitiet.setVisible(false);
		comboBoxSanPham.setVisible(false);
		comboBoxLoaiHang.setVisible(false);
		textFieldGiaNhap.setVisible(false);
		textFieldSoluong.setVisible(false);
		textFieldSanPhamMoi.setVisible(false);
		dateFieldNsx.setVisible(false);
		dateFieldHsd.setVisible(false);
    }
    public void unHideField(String condition) {
    	if(condition == "themchitiet") {
    		comboBoxSanPham.setVisible(true);
    		comboBoxLoaiHang.setVisible(true);
    		textFieldGiaNhap.setVisible(true);
    		textFieldSoluong.setVisible(true);
    		textFieldSanPhamMoi.setVisible(false);
    		dateFieldNsx.setVisible(false);
    		dateFieldHsd.setVisible(false);
    	}
    	if(condition == "themsanphammoi") {
    		comboBoxSanPham.setVisible(false);
    		comboBoxLoaiHang.setVisible(true);
    		textFieldGiaNhap.setVisible(true);
    		textFieldSoluong.setVisible(true);
    		textFieldSanPhamMoi.setVisible(true);
    		dateFieldNsx.setVisible(true);
    		dateFieldHsd.setVisible(true);
    	}
    }
    public void unSetEnable(String condition) {
        if (condition == "themphieunhap") {
            comboBoxNhaCC.setEnabled(true);
            btnThem.setEnabled(true);
            btnLuu.setEnabled(false);
        }
        if (condition == "nhapsanphammoi") {
            comboBoxLoaiHang.setEnabled(true);
            textFieldGiaNhap.setEnabled(true);
            textFieldSoluong.setEnabled(true);
            textFieldSanPhamMoi.setEnabled(true);
            dateFieldNsx.setEnabled(true);
            dateFieldHsd.setEnabled(true);
            btnThem.setEnabled(false);
            btnLuu.setEnabled(true);
        }
        if (condition == "themchitiet") {
            comboBoxNhaCC.setEnabled(false);
            comboBoxSanPham.setEnabled(true);
            comboBoxLoaiHang.setEnabled(false);
            textFieldGiaNhap.setEnabled(false);
            textFieldSoluong.setEnabled(true);
            btnThem.setEnabled(false);
            btnLuu.setEnabled(true);
        }
    }

    public void setEnable() {
        comboBoxSanPham.setEnabled(false);
        comboBoxLoaiHang.setEnabled(false);
        comboBoxNhaCC.setEnabled(false);
        textFieldGiaNhap.setEnabled(false);
        textFieldSoluong.setEnabled(false);
        textFieldSanPhamMoi.setEnabled(false);
        dateFieldNsx.setEnabled(false);
        dateFieldHsd.setEnabled(false);
    }

    public Boolean checkEmtyValue(String condition) throws SQLException {
        // regular expression pattern
        if (condition == "themphieunhap") {
        	return true;
            }
        if (condition == "themchitiet") {
            if (textFieldSoluong.getText().isEmpty()) {
                JOptionPane.showMessageDialog(contentPane, "Số lượng rỗng!");
                textFieldSoluong.requestFocus();
                return false;
            } else {
            	boolean flag = true;
            	for (char c : textFieldSoluong.getText().toCharArray()) {
                    if (!Character.isDigit(c)) {
                        flag = false;
                    }
                }
            	if(!flag) {
                    JOptionPane.showMessageDialog(contentPane, "Số lượng phải là số nguyên dương!");
                    textFieldSoluong.requestFocus();
                    return false;
            	}
            }
    	}
        if (condition == "themsanphammoi") {
            if (textFieldSanPhamMoi.getText().isEmpty()) {
                JOptionPane.showMessageDialog(contentPane, "Tên sản phẩm rỗng!");
                textFieldSanPhamMoi.requestFocus();
                return false;
            } else {
            	boolean flag = true;
                SanPhamBLL testsp = new SanPhamBLL();
                ArrayList<SanPham> arrMaSP = testsp.getSanPham();
                for (SanPham masp : arrMaSP) {
                    if(masp.getTenSp().equalsIgnoreCase(textFieldSanPhamMoi.getText())) {
                    	flag = false;
                    }
                }
                if(!flag) {
                    JOptionPane.showMessageDialog(contentPane, "Tên sản phẩm đã tồn tại!");
                    textFieldSanPhamMoi.requestFocus();
                    return false;
                }
            }
            if (textFieldSoluong.getText().isEmpty()) {
                JOptionPane.showMessageDialog(contentPane, "Số lượng rỗng!");
                textFieldSoluong.requestFocus();
                return false;
            } else {
            	boolean flag = true;
            	for (char c : textFieldSoluong.getText().toCharArray()) {
                    if (!Character.isDigit(c)) {
                        flag = false;
                    }
                }
            	if(!flag) {
                    JOptionPane.showMessageDialog(contentPane, "Số lượng phải là số nguyên dương!");
                    textFieldSoluong.requestFocus();
                    return false;
            	}
            }
            if (textFieldGiaNhap.getText().isEmpty()) {
                JOptionPane.showMessageDialog(contentPane, "Giá nhập rỗng!");
                textFieldGiaNhap.requestFocus();
                return false;
            } else {
            	boolean flag = true;
            	for (char c : textFieldGiaNhap.getText().toCharArray()) {
                    if (!Character.isDigit(c)) {
                        flag = false;
                    }
                }
            	if(!flag) {
                    JOptionPane.showMessageDialog(contentPane, "Giá nhập phải là số!");
                    textFieldGiaNhap.requestFocus();
                    return false;
            	} else if(Integer.parseInt(textFieldGiaNhap.getText()) < 1000) {
                    JOptionPane.showMessageDialog(contentPane, "Giá nhập phải lớn hơn 1000!");
                    textFieldGiaNhap.requestFocus();
                    return false;
            	}
            }
            java.util.Date nsx = dateFieldNsx.getDate();
            java.util.Date hsd = dateFieldHsd.getDate();
            LocalDate currentDate = LocalDate.now();
            Date now = Date.valueOf(currentDate);
            if(dateFieldNsx.getDate() == null) {
            	JOptionPane.showMessageDialog(contentPane, "Ngày sản xuất rỗng!");
            	dateFieldNsx.requestFocus();
                return false;
            } else if(dateFieldHsd.getDate() == null) {
            	JOptionPane.showMessageDialog(contentPane, "Hạn sử dụng rỗng!");
            	dateFieldHsd.requestFocus();
                return false;
            } else if(!(hsd.compareTo(now) > 0)) {
            	JOptionPane.showMessageDialog(contentPane, "Hạn sử dụng phải lớn hơn hiện tại!");
            	dateFieldHsd.requestFocus();
                return false;
            } else if(nsx.compareTo(hsd) > 0) {
            	JOptionPane.showMessageDialog(contentPane, "Ngày sản xuất đang lớn hơn hạn sử dụng!");
            	dateFieldNsx.requestFocus();
                return false;
            }
        }
        return true;
    }
    public NhapHangGui() throws SQLException {

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

        textFieldNhanVien = new JTextField();
        textFieldNhanVien.setBounds(130, 50, 200, 25);
        textFieldNhanVien.setEnabled(false);
        textFieldNhanVien.setColumns(10);
        
        JLabel lblNewLabel_manhanvien = new JLabel("Mã nhân viên");
        lblNewLabel_manhanvien.setBounds(20, 90, 100, 25);

        textFieldMaNhanVien = new JTextField();
        textFieldMaNhanVien.setBounds(130, 90, 200, 25);
        textFieldMaNhanVien.setEnabled(false);
        textFieldMaNhanVien.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("Sản phẩm");
        lblNewLabel_6.setBounds(20, 130, 100, 25);
        lblNewLabel_6.setVisible(false);

        comboBoxSanPham = new JComboBox();
        comboBoxSanPham.setBounds(130, 130, 200, 25);
        comboBoxSanPham.setEnabled(false);
        comboBoxSanPham.setVisible(false);

        JLabel lblNewLabel_loaihang = new JLabel("Loại hàng");
        lblNewLabel_loaihang.setBounds(20, 170, 100, 25);
        lblNewLabel_loaihang.setVisible(false);

        comboBoxLoaiHang = new JComboBox();
        comboBoxLoaiHang.setBounds(130, 170, 200, 25);
        comboBoxLoaiHang.setEnabled(false);
        comboBoxLoaiHang.setVisible(false);

        JLabel lblNewLabel_7 = new JLabel("Nhà Cung Cấp");
        lblNewLabel_7.setBounds(350, 10, 100, 25);

        comboBoxNhaCC = new JComboBox();
        comboBoxNhaCC.setBounds(460, 10, 200, 25);
        comboBoxNhaCC.setEnabled(false);
        
        JLabel lblNewLabel_gianhap = new JLabel("Giá nhập");
        lblNewLabel_gianhap.setBounds(350, 90, 100, 25);
        lblNewLabel_gianhap.setVisible(false);

        textFieldGiaNhap = new JTextField();
        textFieldGiaNhap.setBounds(460, 90, 200, 25);
        textFieldGiaNhap.setEnabled(false);
        textFieldGiaNhap.setColumns(10);
        textFieldGiaNhap.setVisible(false);

        JLabel lblNewLabel_8 = new JLabel("Số lượng");
        lblNewLabel_8.setBounds(350, 50, 100, 25);
        lblNewLabel_8.setVisible(false);

        textFieldSoluong = new JTextField();
        textFieldSoluong.setBounds(460, 50, 200, 25);
        textFieldSoluong.setEnabled(false);
        textFieldSoluong.setColumns(10);
        textFieldSoluong.setVisible(false);

		
		JLabel lblNewLabel_9 = new JLabel("Sản phẩm");
		lblNewLabel_9.setBounds(20, 130, 100, 25);
		lblNewLabel_9.setVisible(false);
		  
		textFieldSanPhamMoi = new JTextField();
		textFieldSanPhamMoi.setBounds(130, 130, 200, 25);
		textFieldSanPhamMoi.setColumns(10);
		textFieldSanPhamMoi.setVisible(false);
		
		JLabel lblNewLabel_10 = new JLabel("Ngày sản xuất");
        lblNewLabel_10.setBounds(350, 130, 100, 25);
        lblNewLabel_10.setVisible(false);
        
        dateFieldNsx = new JDateChooser();
        dateFieldNsx.setBounds(460, 130, 200, 25);
        dateFieldNsx.setVisible(false);
        
        JLabel lblNewLabel_11 = new JLabel("Hạn sử dụng");
		lblNewLabel_11.setBounds(350, 170, 100, 25);
		lblNewLabel_11.setVisible(false);
		
		dateFieldHsd = new JDateChooser();
		dateFieldHsd.setBounds(460, 170, 200, 25);
		dateFieldHsd.setVisible(false);
		

		/*
		 * btnXem.setBounds(700, 10, 200, 100); btnXem.setEnabled(false);
		 * btnXem.setFocusPainted(false); btnXem.addActionListener(new ActionListener()
		 * { public void actionPerformed(ActionEvent e) { try {
		 * resetValue("themchitiet"); setEnable(); //btnAn.setVisible(false);
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
                    if (addbtn) {
                        // -------------------Copy img vao thu muc Image cua project
                        NhapHang nh = new NhapHang();
                        NhapHangBLL luu;
                        NhaCungCapBLL ncc;
                        if (checkEmtyValue("themphieunhap")) {
	      					  int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn thêm phiếu nhập "+textFieldMapn.getText(),
	    							  "Confirmation", JOptionPane.YES_NO_OPTION);
	    					  if (confirmed == JOptionPane.YES_OPTION) {
	                            try {
	                                luu = new NhapHangBLL();
	                                ncc = new NhaCungCapBLL();
	                                nh.setMaPn(textFieldMapn.getText());
	                                nh.setMaNv(textFieldMaNhanVien.getText());
	                                nh.setMaNcc(ncc.getMaNCC(comboBoxNhaCC.getSelectedItem().toString()));
	                                nh.setTongTien("0");
	                                LocalDate currentDate = LocalDate.now();
	                                nh.setNgayNhap(currentDate.toString());;
	                                boolean checkAddPro = luu.addPhieuNhap(nh);
	                                if (checkAddPro) {
	                                    JOptionPane.showMessageDialog(contentPane, "Thêm phiếu nhập thành công");
	                                    resetValue("themphieunhap");
	                                    setEnable();
	                                    hienthiphieunhap("phieunhap");
	                                    addbtn = false;
	                                } else {
	                                    JOptionPane.showMessageDialog(contentPane, "Thêm phiếu nhập thất bại");
	                                }
	                            } catch (SQLException e2) {
	                                // TODO Auto-generated catch block
	                                e2.printStackTrace();
	                            }
	                        }
                       }
                    }
                    else if (detailbtn) {
                        NhapHang nh = new NhapHang();
                        PhieuNhapChiTiet ct = new PhieuNhapChiTiet();
                        ChiTietPhieuNhapBLL luuct;
                        NhapHangBLL luunh;
                        SanPhamBLL sp;
                        KhoBLL kho;
                        if (checkEmtyValue("themchitiet")) {
                        	int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn thêm chi tiết cho phiếu nhập "+textFieldMapn.getText(),
	    							  "Confirmation", JOptionPane.YES_NO_OPTION);
	    					  if (confirmed == JOptionPane.YES_OPTION) {
		                            try {
		                                luuct = new ChiTietPhieuNhapBLL();
		                                luunh = new NhapHangBLL();
		                                sp = new SanPhamBLL();
		                                kho = new KhoBLL();
		                                ct.setMaPN(textFieldMapn.getText());
		                                ct.setMaSP(sp.getMaSP(comboBoxSanPham.getSelectedItem().toString()));
		                                ct.setSoLuong(textFieldSoluong.getText());
		                                int giaNhap = Integer.parseInt(sp.getGiaNhap(ct.getMaSP()));
		                                int thanhTien = Integer.parseInt(ct.getSoLuong()) * giaNhap;
		                                ct.setThanhTien(""+thanhTien);
		                                boolean checkAddPro = luuct.addChiTietPN(ct);
		                                if (checkAddPro) {
		                                    JOptionPane.showMessageDialog(contentPane, "Thêm chi tiết thành công");
		                                    int tongTien = Integer.parseInt(luunh.getTongTien(ct.getMaPN())) + thanhTien ;
		                                    luunh.updateTongTien(String.valueOf(tongTien), ct.getMaPN());
		                                    int soLuong = Integer.parseInt(kho.getSoLuong(ct.getMaSP()));
		                                    int upSoLuong = soLuong + Integer.parseInt(textFieldSoluong.getText());
		                                    kho.updateSoLuong(String.valueOf(upSoLuong), ct.getMaSP());
		                                    resetValue("themchitiet");
		                                    setEnable();
		                                    hienthiphieunhap("themchitiet");
		                                    detailbtn = false;
		                                } else {
		                                    JOptionPane.showMessageDialog(contentPane, "Thêm chi tiết thất bại");
		                                }
		                            } catch (SQLException e2) {
		                                // TODO Auto-generated catch block
		                                e2.printStackTrace();
		                            }
	    					  }
                        }
                    }
                    else if (addnewbtn) {
                        NhapHang nh = new NhapHang();
                        PhieuNhapChiTiet ct = new PhieuNhapChiTiet();
                        ChiTietPhieuNhapBLL luuct;
                        NhapHangBLL luunh;
                        SanPhamBLL spbll;
                        LoaiHangBLL lhbll;
                        SanPham sp = new SanPham();
                        KhoBLL khobll;                        
                        if (checkEmtyValue("themsanphammoi")) {
                            String nsx = formatDateToString(dateFieldNsx.getDate());
                            String hsd = formatDateToString(dateFieldHsd.getDate());
                        	int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn thêm chi tiết cho phiếu nhập "+textFieldMapn.getText(),
	    							  "Confirmation", JOptionPane.YES_NO_OPTION);
	    					  if (confirmed == JOptionPane.YES_OPTION) {
		                            try {
		                                luuct = new ChiTietPhieuNhapBLL();
		                                luunh = new NhapHangBLL();
		                                spbll = new SanPhamBLL();
		                                lhbll = new LoaiHangBLL();
		                                khobll = new KhoBLL();
		                                String lastMaSp = spbll.getLastMaSP();
		                                String maSp = lastMaSp.substring(lastMaSp.length()-3, lastMaSp.length());
		                                int check = Integer.parseInt(maSp);
		                                if(check < 9) {
		                                	sp.setMaSp("SP00"+(check+1));
		                                } else if(check < 99) {
		                                	sp.setMaSp("SP0"+(check+1));
		                                } else {
		                                	sp.setMaSp("SP"+(check+1));
		                                }
		                                sp.setTenSp(textFieldSanPhamMoi.getText());
		                                sp.setMaLh(lhbll.getMaLH(comboBoxLoaiHang.getSelectedItem().toString()));
		                                sp.setGiaMua(textFieldGiaNhap.getText());
		                                int giaMua = Integer.parseInt(textFieldGiaNhap.getText());
		                                int giaBan = (giaMua*20/100) + giaMua;
		                                sp.setGiaBan(String.valueOf(giaBan));
		                                sp.setNgaySanXuat(nsx);
		                                sp.setHanSuDung(hsd);
		                                ct.setMaPN(textFieldMapn.getText());
		                                ct.setMaSP(sp.getMaSp());
		                                ct.setSoLuong(textFieldSoluong.getText());
		                                int giaNhap = Integer.parseInt(sp.getGiaMua());
		                                int thanhTien = Integer.parseInt(ct.getSoLuong()) * giaNhap;
		                                ct.setThanhTien(""+thanhTien);
		                                boolean checkAddPro = spbll.addSanPham(sp);
		                                if(checkAddPro) {
			                                boolean checkAddSto = khobll.addSoLuong(textFieldSoluong.getText(), sp.getMaSp());
		                                	if (checkAddSto) {
				                                boolean checkAddDetail = luuct.addChiTietPN(ct);
				                                if (checkAddDetail) {
				                                    JOptionPane.showMessageDialog(contentPane, "Thêm chi tiết thành công");
				                                    int tongTien = Integer.parseInt(luunh.getTongTien(ct.getMaPN())) + thanhTien ;
				                                    luunh.updateTongTien(String.valueOf(tongTien), ct.getMaPN());
				                                    resetValue("themchitiet");
				                                    setEnable();
				                                    hienthiphieunhap("themchitiet");
				                                    detailbtn = false;
				                                } else {
				                                    JOptionPane.showMessageDialog(contentPane, "Thêm chi tiết thất bại");
				                                }
		                                	}
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
        btnThem.setBounds(146, 10, 150, 53);
        btnThem.setFocusPainted(false);
        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addbtn = true;
                addnewbtn = false;
                detailbtn = false;
                resetValue("themphieunhap");
                hideField();
                try {
                    hienthiphieunhap("themphieunhap");
                } catch (SQLException e3) {
                    // TODO Auto-generated catch block
                    e3.printStackTrace();
                }
            }
        });
        btnThem.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Add.png"))));
		
        btnThemSp.setBounds(610, 10, 150, 53);
        btnThemSp.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(taiKhoan.getMaNV().equals(textFieldMaNhanVien.getText())) {
            		resetValue("themsanphammoi");
            		unHideField("themsanphammoi");
            		lblNewLabel_6.setVisible(false);
            		lblNewLabel_loaihang.setVisible(true);
            		lblNewLabel_gianhap.setVisible(true);
            		lblNewLabel_8.setVisible(true);
            		lblNewLabel_9.setVisible(true);
            		lblNewLabel_10.setVisible(true);
            		lblNewLabel_11.setVisible(true);
            		btnThemSp.setEnabled(false);
                    addbtn = false;
                    addnewbtn = true;
                    detailbtn = false;
                    try {
                        hienthiphieunhap("themsanphammoi");
                    } catch (SQLException e3) {
                        // TODO Auto-generated catch block
                        e3.printStackTrace();
                    }
        		} else {
        			JOptionPane.showMessageDialog(contentPane, "Đây không phải phiếu nhập của bạn");
        		}
            }
        });
        btnThemSp.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Add.png"))));
        btnThemSp.setFocusPainted(false);
        btnThemSp.setVisible(false);
		  
		/*
		 * btnAn.setBounds(469, 10, 104, 53); if(taiKhoan.getQuyen().equals("RL2")) {
		 * btnAn.setVisible(true); } else { btnAn.setVisible(false); }
		 * btnAn.setEnabled(false); btnAn.setIcon(new ImageIcon(
		 * Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(
		 * ".\\Image\\Delete.png")))); btnAn.setFocusPainted(false);
		 * btnAn.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { if(checkHide) { int confirmed =
		 * JOptionPane.showConfirmDialog(null,
		 * "Bạn muốn ẩn phiếu nhập "+textFieldMapn.getText(), "Confirmation",
		 * JOptionPane.YES_NO_OPTION); if (confirmed == JOptionPane.YES_OPTION) {
		 * NhapHangBLL testnh; try { testnh = new NhapHangBLL(); if
		 * (testnh.hidePhieuNhap(textFieldMapn.getText())) {
		 * JOptionPane.showMessageDialog(contentPane, "Ẩn thành công!");
		 * hienthiphieunhap("themphieunhap"); resetValue("load"); setEnable(); } } catch
		 * (SQLException e1) { // TODO Auto-generated catch block e1.printStackTrace();
		 * } } } else { int confirmed = JOptionPane.showConfirmDialog(null,
		 * "Bạn muốn ẩn chi tiết này", "Confirmation", JOptionPane.YES_NO_OPTION); if
		 * (confirmed == JOptionPane.YES_OPTION) { ChiTietPhieuNhapBLL testct;
		 * SanPhamBLL testsp; try { testct = new ChiTietPhieuNhapBLL(); testsp = new
		 * SanPhamBLL(); if
		 * (testct.hideChiTietPN(testsp.getMaSP(comboBoxSanPham.getSelectedItem().
		 * toString()) ,textFieldMapn.getText())) {
		 * JOptionPane.showMessageDialog(contentPane, "Ẩn thành công!");
		 * hienthiphieunhap("themchitiet"); resetValue("themchitiet"); setEnable(); } }
		 * catch (SQLException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); } } }
		 * 
		 * } });
		 */
		 

        JButton btnDongBo = new JButton("");
        btnDongBo.setBounds(330, 10, 104, 53);
        btnDongBo.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(NhapHangGui.class.getResource(".\\Image\\Refresh-icon.png"))));
        btnDongBo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	hideField();
                	lblNewLabel_6.setVisible(false);
                	lblNewLabel_gianhap.setVisible(false);
                	lblNewLabel_loaihang.setVisible(false);
                	lblNewLabel_8.setVisible(false);
            		lblNewLabel_9.setVisible(false);
            		lblNewLabel_10.setVisible(false);
            		lblNewLabel_11.setVisible(false);
                    resetValue("load");
                    textFieldNhanVien.setText(null);
                    textFieldMaNhanVien.setText(null);
                    setEnable();
                    
                    hienthiphieunhap("phieunhap");
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        //btnChitiet.setBounds(610, 10, 150, 53);
        btnChitiet.setVisible(false);
        btnChitiet.setFocusPainted(false);
        btnChitiet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(taiKhoan.getMaNV().equals(textFieldMaNhanVien.getText())) {
                	unHideField("themchitiet");
            		textFieldGiaNhap.setVisible(false);
            		comboBoxLoaiHang.setVisible(false);
                	resetValue("themchitiet");
                	lblNewLabel_6.setVisible(true);
                	lblNewLabel_gianhap.setVisible(false);
                	lblNewLabel_loaihang.setVisible(false);
                	lblNewLabel_8.setVisible(true);
            		lblNewLabel_9.setVisible(false);
            		lblNewLabel_10.setVisible(false);
            		lblNewLabel_11.setVisible(false);
            		btnChitiet.setEnabled(false);
            		addbtn = false;
                    addnewbtn = false;
                    detailbtn = true;
                    try {
                        hienthiphieunhap("themchitiet");
                    } catch (SQLException e3) {
                        // TODO Auto-generated catch block
                        e3.printStackTrace();
                    }
            	} else {
            		JOptionPane.showMessageDialog(contentPane, "Đây không phải phiếu nhập của bạn");
            	}
            }
        });
        btnChitiet.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Add.png"))));
		

        JPanel panel_8 = new JPanel();

        panel_8.setBounds(
                0, 250, 1067, 300);
        
        lbThongbao_chitiet.setBounds(
                580, 40, 569, 32);

        lbThongbao_chitiet.setText(
                "DANH SÁCH SẢN PHẨM TRONG PHIẾU NHẬP");

        lbThongbao_chitiet.setForeground(
                new Color(255, 128, 128));

        lbThongbao_chitiet.setFont(
                new Font("Arial", Font.BOLD, 20));
        
        lbThongbao.setBounds(
                130, 40, 569, 32);

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
                int row = table.getSelectedRow(); // get the selected row
                String maPN = table.getModel().getValueAt(row, 0).toString();
                String maNV = table.getModel().getValueAt(row, 1).toString(); // get the value of the first column
                String maNCC = table.getModel().getValueAt(row, 2).toString(); // get the value of the second column
                textFieldMapn.setText(maPN);
                textFieldMaNhanVien.setText(maNV);
                
                setEnable();
                hideField();
            	lblNewLabel_6.setVisible(false);
            	lblNewLabel_gianhap.setVisible(false);
            	lblNewLabel_loaihang.setVisible(false);
            	lblNewLabel_8.setVisible(false);
        		lblNewLabel_9.setVisible(false);
        		lblNewLabel_10.setVisible(false);
        		lblNewLabel_11.setVisible(false);
                btnLuu.setEnabled(false);
                btnThem.setEnabled(true);
                //btnAn.setEnabled(true);
                checkHide = true;
                btnThemSp.setVisible(true);
                btnThemSp.setEnabled(true);
                btnChitiet.setVisible(true);
                btnChitiet.setEnabled(true);
                
                
                

                //g.dipnose();
                NhanVienBLL testnv;
                try {
                    testnv = new NhanVienBLL();
                    textFieldNhanVien.setText(testnv.getTenNV(maNV));

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                NhaCungCapBLL testncc;
                try {
                    testncc = new NhaCungCapBLL();
                    DefaultComboBoxModel combo = new DefaultComboBoxModel();
                    comboBoxNhaCC.setModel(combo);

                    combo.addElement(testncc.getTenNCC(maNCC));

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
					hienthiphieunhap("chitiet");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }

        });
        table_chitiet.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                int row = table_chitiet.getSelectedRow(); // get the selected row
                String maSp = table_chitiet.getModel().getValueAt(row, 0).toString();
                String soLuong = table_chitiet.getModel().getValueAt(row, 2).toString(); // get the value of the second column
                textFieldSoluong.setText(soLuong);

                setEnable();
                unHideField("themchitiet");
            	lblNewLabel_6.setVisible(true);
            	lblNewLabel_gianhap.setVisible(true);
            	lblNewLabel_loaihang.setVisible(true);
            	lblNewLabel_8.setVisible(true);
        		lblNewLabel_9.setVisible(false);
        		lblNewLabel_10.setVisible(false);
        		lblNewLabel_11.setVisible(false);
                btnLuu.setEnabled(false);
                btnThem.setEnabled(true);
                //btnAn.setEnabled(true);
                checkHide = false;
                btnThemSp.setVisible(true);
                btnThemSp.setEnabled(true);
                btnChitiet.setVisible(true);
                btnChitiet.setEnabled(true);
                
                //g.dipnose();
                SanPhamBLL testsp;
                LoaiHangBLL testlh;
                try {
                    testsp = new SanPhamBLL();
                    testlh = new LoaiHangBLL();
                    DefaultComboBoxModel combo = new DefaultComboBoxModel();
                    comboBoxSanPham.setModel(combo);
                    combo.addElement(testsp.getTenSP(maSp));
                    textFieldGiaNhap.setText(testsp.getGiaNhap(maSp));
                    DefaultComboBoxModel combo1 = new DefaultComboBoxModel();
                    comboBoxLoaiHang.setModel(combo1);
                    combo1.addElement(testlh.getTenLH(testsp.getMaLoaiHang(maSp)));
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

        });
        scrollPane_chitiet.setBounds(540, 70, 520, 210);

        scrollPane_chitiet.setViewportView(table_chitiet);
        
        scrollPane.setBounds(0, 70, 520, 210);

        scrollPane.setViewportView(table);
        panel_8.setLayout(null);
        panel_8.add(lbThongbao);
        panel_8.add(lbThongbao_chitiet);
        panel_8.add(scrollPane_chitiet);
        panel_8.add(scrollPane);
        panel_6.setLayout(null);
        panel_6.add(btnLuu);
        panel_6.add(btnThem);
        panel_6.add(btnThemSp);
        //panel_6.add(btnAn);
        panel_6.add(btnDongBo);
        panel_6.add(btnChitiet);
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
        panel_5.add(textFieldNhanVien);
        panel_5.add(lblNewLabel_manhanvien);
        panel_5.add(textFieldMaNhanVien);
        panel_5.add(lblNewLabel_3);
        //panel_5.add(lblNewLabel_4);
        panel_5.add(comboBoxSanPham);
        panel_5.add(lblNewLabel_loaihang);
        panel_5.add(comboBoxLoaiHang);
        panel_5.add(comboBoxNhaCC);
        panel_5.add(lblNewLabel_8);
        panel_5.add(lblNewLabel_9);
        panel_5.add(textFieldSanPhamMoi);
        panel_5.add(lblNewLabel_10);
        panel_5.add(dateFieldNsx);
        panel_5.add(lblNewLabel_11);
        panel_5.add(dateFieldHsd);
        panel_5.add(lblNewLabel_gianhap);
        panel_5.add(textFieldGiaNhap);
        panel_5.add(textFieldSoluong);
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

        JLabel lblNewLabel_20 = new JLabel("");
        icon = new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Background2.png")));
        Image image2 = icon.getImage();
        Image resizedImg2 = image2.getScaledInstance(1300, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(resizedImg2);
        lblNewLabel_20.setIcon(resizedIcon2);
        lblNewLabel_20.setBounds(0, 0, 1078, 104);
        panel.add(lblNewLabel_20);
        contentPane.setLayout(null);
        contentPane.add(tabbedPane);
        contentPane.add(panel_1);
        contentPane.add(panel);
        hienthiphieunhap("phieunhap");

    }
}
