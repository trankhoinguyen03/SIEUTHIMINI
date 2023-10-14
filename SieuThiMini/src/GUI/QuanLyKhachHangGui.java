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

import BLL.KhachHang;
import DAL.KhachHangDAL;

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
    //private JTextField textFieldDiemthuong;
    private JTextField textFieldSDT;
    //private JTextField textFieldNgaycapthe;
    //private JTextField textFieldNgaymuagannhat;
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
    JButton btnXoa = new JButton("Ẩn");
    JButton btnSua = new JButton("Sửa");
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
    private JTextField textFieldSearch;
    boolean checkFix = false;

    public void hienthikhachhang(String condition) throws SQLException {
        KhachHangDAL khDal = new KhachHangDAL();
        ArrayList<KhachHang> arrKh = new ArrayList<KhachHang>();
        if (condition == "hien thi") {

            arrKh = khDal.docKhachHang("dockhachhang", null);
        }
        if (condition == "timkiem") {
            arrKh = khDal.docKhachHang("timkiem", textFieldSearch.getText());
        }
        if (condition == "sapxeptheoten") {
            arrKh = khDal.docKhachHang("sapxeptheoten", null);
        }
        if (condition == "sapxeptheomakh") {
            arrKh = khDal.docKhachHang("sapxeptheomakh", null);
        }
        if (condition == "them") {
            arrKh = khDal.docKhachHang("dockhachhang", null);
            /*			LoaiHangDAL test = new LoaiHangDAL();
			ArrayList<LoaiHang> arrMaLH = test.docLoaiHang();
			DefaultComboBoxModel combo = new DefaultComboBoxModel();
			comboBox.setModel(combo);
			for (LoaiHang makh : arrMaLH) {
				combo.addElement(malh.getTenLH());
                        }
             */
        }

        String[] columnNames = {"Mã Khách Hàng", "Tên Khách Hàng", "SDT", "DiemThuong"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        if (condition == "timkiem") {
            table.setModel(model);
            model.setRowCount(0);
            for (KhachHang khdata : arrKh) {
                Object[] row = new Object[]{};//khdata.getMakh(), khdata.getHoTen(), khdata.getDiaChi(), khdata.getNgayCapThe(),khdata.getNgayMuaGanNhat(), khdata.getDiemThuong()};
                model.addRow(row);
            }
        } else {

            table.setModel(model);
            model.setRowCount(0);
            for (KhachHang khdata : arrKh) {
                Object[] row = new Object[]{};//khdata.getMakh(), khdata.getHoTen(), khdata.getDiaChi(), khdata.getNgayCapThe(),khdata.getNgayMuaGanNhat(), khdata.getDiemThuong()};
                model.addRow(row);
            }

            lastRow = table.getRowCount() - 1; // get index of the last row
            lastValueMaKh = table.getValueAt(lastRow, 0); // get the value at the last row and column n
        }
    }

    public void resetValue() {
        textFieldMakh.setText("");
        textFieldMakh.setEnabled(true);
        textFieldTenkh.setText("");
        textFieldTenkh.setEnabled(true);
		/*
		 * textFieldNgaycapthe.setText(""); textFieldNgaycapthe.setEnabled(true);
		 * textFieldNgaymuagannhat.setText("");
		 * textFieldNgaymuagannhat.setEnabled(true); textFieldDiemthuong.setText("");
		 * textFieldDiemthuong.setEnabled(true);
		 */
        textFieldSDT.setText("");
        textFieldSDT.setEnabled(true);
        
        btnThem.setEnabled(true);
        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);
        btnLuu.setEnabled(false);
    }

    public void unSetEnable() {
        textFieldMakh.setEnabled(true);
        textFieldTenkh.setEnabled(true);
		/*
		 * textFieldNgaycapthe.setEnabled(true);
		 * textFieldNgaymuagannhat.setEnabled(true);
		 * textFieldDiemthuong.setEnabled(true);
		 */
        textFieldSDT.setEnabled(true);
        btnThem.setEnabled(true);
        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);
        btnLuu.setEnabled(false);
    }

    public void setEnable() {

        textFieldMakh.setEnabled(false);
        textFieldTenkh.setEnabled(false);
		/*
		 * textFieldNgaycapthe.setEnabled(false);
		 * textFieldNgaymuagannhat.setEnabled(false);
		 * textFieldDiemthuong.setEnabled(false);
		 */
        textFieldSDT.setEnabled(false);
    }

    public Boolean checkEmtyValue() throws SQLException {
        // regular expression pattern
        if (textFieldMakh.getText().isEmpty()) {
            JOptionPane.showMessageDialog(contentPane, "Mã khách hàng trống!");
            textFieldMakh.requestFocus();
            return false;
        }
        if (!textFieldMakh.getText().isEmpty()) {
            KhachHangDAL nvd = new KhachHangDAL();
            ArrayList<KhachHang> arrPro = new ArrayList<KhachHang>();
            arrPro = nvd.docKhachHang("dockhachhang", null);
            if (fixbtn) {
                for (KhachHang nv : arrPro) {
                    if (Integer.parseInt(oldMaKH) != Integer.parseInt(textFieldMakh.getText()) && nv.getMakh() == Integer.parseInt(textFieldMakh.getText())) {
                        JOptionPane.showMessageDialog(contentPane, "Mã khách hàng đã tồn tại!");
                        textFieldMakh.requestFocus();
                        return false;

                    }
                }
            }
            if (addbtn) {
                for (KhachHang nv : arrPro) {
                    if (nv.getMakh() == Integer.parseInt(textFieldMakh.getText())) {
                        JOptionPane.showMessageDialog(contentPane, "Mã khách hàng đã tồn tại!");
                        textFieldMakh.requestFocus();
                        return false;

                    }
                }
            }
            

        }


		/*
		 * if (textFieldNgaycapthe.getText().isEmpty()) {
		 * JOptionPane.showMessageDialog(contentPane, "Ngày cấp thẻ trống");
		 * textFieldNgaycapthe.requestFocus(); return false;
		 * 
		 * }
		 * 
		 * if (!textFieldNgaycapthe.getText().isEmpty()) { Pattern reg =
		 * Pattern.compile("^\\d{4}[\\-](0?[1-9]|1[012])[\\-](0?[1-9]|[12][0-9]|3[01])$"
		 * ); boolean kt = reg.matcher(textFieldNgaycapthe.getText()).matches(); if(kt
		 * == false) { JOptionPane.showMessageDialog(contentPane,
		 * "Ngày cấp thẻ phải có định dạng yyyy-mm-dd!");
		 * textFieldNgaycapthe.requestFocus(); return false; }
		 * 
		 * } if (textFieldNgaymuagannhat.getText().isEmpty()) {
		 * JOptionPane.showMessageDialog(contentPane, "Ngày mua trống");
		 * textFieldNgaymuagannhat.requestFocus(); return false;
		 * 
		 * } if (!textFieldNgaymuagannhat.getText().isEmpty()) { Pattern reg =
		 * Pattern.compile("^\\d{4}[\\-](0?[1-9]|1[012])[\\-](0?[1-9]|[12][0-9]|3[01])$"
		 * ); boolean kt = reg.matcher(textFieldNgaymuagannhat.getText()).matches();
		 * if(kt == false) { JOptionPane.showMessageDialog(contentPane,
		 * "Ngày mua gần nhất phải có định dạng yyyy-mm-dd!");
		 * textFieldNgaymuagannhat.requestFocus(); return false; } }
		 * 
		 * if (textFieldDiemthuong.getText().isEmpty()) {
		 * JOptionPane.showMessageDialog(contentPane, "Điểm thưởng trống");
		 * textFieldDiemthuong.requestFocus(); return false;
		 * 
		 * } if (!textFieldDiemthuong.getText().isEmpty()) { isNumber =
		 * textFieldDiemthuong.getText().matches(patternNumber); if (!isNumber) {
		 * JOptionPane.showMessageDialog(contentPane, "Điểm thưởng phải là số");
		 * textFieldDiemthuong.requestFocus(); textFieldDiemthuong.selectAll(); return
		 * false; }
		 * 
		 * }
		 */
        if (textFieldTenkh.getText().isEmpty()) {
            JOptionPane.showMessageDialog(contentPane, "Tên khách hàng trống");
            textFieldTenkh.requestFocus();
            return false;
        }
        if (!textFieldTenkh.getText().isEmpty()) {
            Pattern reg = Pattern.compile("[0-9]*$");
            boolean kt = reg.matcher(textFieldTenkh.getText()).matches();
                        if(kt == true) {
                            JOptionPane.showMessageDialog(contentPane, "Tên không chứa số!");
			textFieldTenkh.requestFocus();
			return false;
                        }
        }
        
        if (textFieldSDT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(contentPane, "Dịa chỉ trống");
            textFieldSDT.requestFocus();
            return false;
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
        lblNewLabel_1.setBounds(420, 14, 100, 25);

        textFieldMakh = new JTextField();
        textFieldMakh.setBounds(506, 10, 116, 29);
        textFieldMakh.setEnabled(false);
        textFieldMakh.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Họ tên");
        lblNewLabel_3.setBounds(420, 48, 80, 25);

        textFieldTenkh = new JTextField();
        textFieldTenkh.setBounds(506, 50, 117, 28);
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
        lblNewLabel_10.setBounds(420, 86, 62, 26);

        textFieldSDT = new JTextField();
        textFieldSDT.setBounds(506, 88, 120, 29);
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
        btnLuu.setBounds(10, 10, 104, 53);

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

                        KhachHang kh = new KhachHang();
                        KhachHangDAL luukh;
                        if (addbtn) {
                            try {
                                luukh = new KhachHangDAL();
//								int malh = luukh.layMaLoaiSP((String) (comboBox.getSelectedItem()));
//								nv.setMaLh(malh);
                                kh.setMakh(Integer.parseInt(textFieldMakh.getText()));
                                kh.setHoTen(textFieldTenkh.getText());
                                kh.setDiaChi(textFieldSDT.getText());
                                //kh.setNgayCapThe(textFieldNgaycapthe.getText());
                                //kh.setNgayMuaGanNhat(textFieldNgaymuagannhat.getText());
                                //kh.setImg(selectedFile.getName());
                                boolean checkAddPro = luukh.themkhachhang(kh, "themkhachhang", null);
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
                        if (fixbtn) {

                            try {

                                luukh = new KhachHangDAL();
                                kh.setMakh(Integer.parseInt(textFieldMakh.getText()));
                                kh.setHoTen(textFieldTenkh.getText());
                                kh.setDiaChi(textFieldSDT.getText());
                                //kh.setNgayCapThe(textFieldNgaycapthe.getText());
                                //kh.setNgayMuaGanNhat(textFieldNgaymuagannhat.getText());

                                boolean checkAddPro = luukh.themkhachhang(kh, "suakhachhang", oldMaKH);
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
                addbtn = true;
                resetValue();
                textFieldMakh.setEnabled(false);
                int makh = (int) lastValueMaKh;
                makh++;
                textFieldMakh.setText("" + makh);
                btnThem.setEnabled(false);
                btnLuu.setEnabled(true);
                //btnXoa.setEnabled(false);
                //btnSua.setEnabled(false);
                try {
                    hienthikhachhang("them");
                } catch (SQLException e3) {
                    // TODO Auto-generated catch block
                    e3.printStackTrace();
                }

            }
        });
        btnThem.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Add.png"))));
		
		  btnSua.setBounds(283, 10, 104, 53); btnSua.setEnabled(false);
		  btnSua.setIcon(new ImageIcon(
		  Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(
		  ".\\Image\\Change.png")))); btnSua.setFocusPainted(false);
		  btnSua.addActionListener(new ActionListener() { public void
		  actionPerformed(ActionEvent e) { fixbtn = true; oldMaKH =
		  textFieldMakh.getText(); unSetEnable(); btnThem.setEnabled(false);
		  btnLuu.setEnabled(true);
		  
		  try { hienthikhachhang("them"); } catch (SQLException e1) { // TODO
		  //Auto-generated catch block e1.printStackTrace(); 
			  } } });
		  btnXoa.setBounds(419, 10, 104, 53);
		  
		  btnXoa.setEnabled(false); btnXoa.setIcon(new ImageIcon(
		  Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(
		  ".\\Image\\Delete.png")))); btnXoa.setFocusPainted(false);
		  btnXoa.addActionListener(new ActionListener() { public void
		  actionPerformed(ActionEvent e) { int confirmed =
		  JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa khách hàng",
		  "Confirmation", JOptionPane.YES_NO_OPTION); if (confirmed ==
		  JOptionPane.YES_OPTION) { KhachHangDAL deleteSp; try { deleteSp = new
		  KhachHangDAL(); if (deleteSp.xoaKhachHang(textFieldMakh.getText())) {
		  JOptionPane.showMessageDialog(contentPane, "Xóa khách hàng thành công!");
		  hienthikhachhang("hien thi"); resetValue(); setEnable(); } } catch
		  (SQLException e1) { // TODO Auto-generated catch block e1.printStackTrace();
		  }
		  
		  }
		  
		  } });
		 

        JPanel panel_7 = new JPanel();
        panel_7.setBounds(697, 10, 370, 53);
        panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));

        JButton btnDongBo = new JButton("");
        btnDongBo.setBounds(563, 10, 104, 53);
        btnDongBo.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(QuanLyKhachHangGui.class.getResource(".\\Image\\Refresh-icon.png"))));
        btnDongBo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
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
                KhachHangDAL nvd;
                if (radio1.isSelected()) {

                    try {
                        nvd = new KhachHangDAL();

                        hienthikhachhang("sapxeptheomakh");
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                }
                if (radio2.isSelected()) {

                    try {
                        nvd = new KhachHangDAL();

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
                String DC = table.getModel().getValueAt(row, 2).toString();
                String DiemTH = table.getModel().getValueAt(row, 5).toString();
                String NgayCT = table.getModel().getValueAt(row, 3).toString();
                String NgayMua = table.getModel().getValueAt(row, 4).toString();
                //String img = table.getModel().getValueAt(row, 6).toString();
                textFieldMakh.setText(maKH);
                textFieldTenkh.setText(TenKH);
                //textFieldDiemthuong.setText(DiemTH);
                textFieldSDT.setText(DC);
                //textFieldNgaycapthe.setText(NgayCT);
                //textFieldNgaymuagannhat.setText(NgayMua);

                setEnable();
                btnXoa.setEnabled(true);
                btnSua.setEnabled(true);
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
        panel_6.add(btnSua);
        panel_6.add(btnXoa);
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

        textFieldSearch = new JTextField();
        textFieldSearch.setBounds(547, 163, 341, 28);
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
                } else {
                    try {
                        hienthikhachhang("timkiem");
                    } catch (SQLException ex) {
                        Logger.getLogger(QuanLyKhachHangGui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        ImageIcon iconSearch = new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Find.png")));
        Image imageSearch = iconSearch.getImage();
        Image resizedImgSearch = imageSearch.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconSearch = new ImageIcon(resizedImgSearch);
        btnTimKiem.setIcon(resizedIconSearch);

        btnTimKiem.setBounds(420, 163, 123, 26);
        panel_5.add(btnTimKiem);
        
        JButton btnNewButton = new JButton("Hệ Thống");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		HomeNhanVien sanpham=new HomeNhanVien();
                setVisible(false);
             
                sanpham.setVisible(true);
                sanpham.setLocationRelativeTo(null);
        	}
        });
        btnNewButton.setBounds(10, 8, 95, 37);
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
