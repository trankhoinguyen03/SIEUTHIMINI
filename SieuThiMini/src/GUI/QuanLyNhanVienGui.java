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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

import BLL.DangNhapBLL;
import BLL.NhaCungCapBLL;
import BLL.NhanVienBLL;
import BLL.TaiKhoanBLL;
import DTO.ChucVu;
import DTO.KhachHang;
import DTO.NhaCungCap;
import DTO.NhanVien;
import DTO.TaiKhoan;
import DAL.NhanVienDAL;


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

public class QuanLyNhanVienGui extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldManv;
    private JTextField textFieldTennv;
    private JDateChooser dateFieldNgaysinh;
    private JTextField textFieldNgaysinh;
    private JTextField textFieldCmnd;
    private JTextField textFieldDienthoai;
    private JDateChooser dateFieldNgayvaolam;
    private JTextField textFieldNgayvaolam;
    private JTextField textFieldDiachi;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QuanLyNhanVienGui frame = new QuanLyNhanVienGui();
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
    Object lastValueMaNv;
    JButton btnXoa = new JButton("Nghỉ việc");
    boolean check = true;
    JButton btnSua = new JButton("Sửa");
    JButton btnThem = new JButton("Thêm");
    JButton btnLuu = new JButton("Lưu");
    int lastRow;
    JRadioButton radioSapxepten = new JRadioButton("Tên nhân viên");
    JRadioButton radioSapxepma = new JRadioButton("Mã nhân viên");
    ButtonGroup groupSapxep = new ButtonGroup();
    ButtonGroup groupGioitinh = new ButtonGroup();
    JRadioButton radioNam = new JRadioButton("Nam");
    JRadioButton radioNu = new JRadioButton("Nữ");
    JScrollPane scrollPane = new JScrollPane();
    boolean addbtn, fixbtn = false;
//	dung grap 2d tao size cho anh
    int newWidth = 130;
    int newHeight = 110;
    BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = resizedImage.createGraphics();
    String patternNumber = "\\d+(\\.\\d+)?";
    JLabel lbThongbao = new JLabel();
    String oldMaNV = null;
    private JTextField textFieldSearch;
    boolean checkHide = false;
    
    TaiKhoan taiKhoan = DangNhapBLL.taiKhoan;
    private JTextField txtSearch;
    public void hienthinhanvien(String condition) throws SQLException {
        NhanVienBLL nvBll = new NhanVienBLL();
        ArrayList<NhanVien> arrNv = new ArrayList<NhanVien>();
        if (condition == "hien thi") {

            arrNv = nvBll.getNhanVien("docnhanvien");
            NhanVienBLL test = new NhanVienBLL();
            ArrayList<ChucVu> arr = test.getChucVu();
            DefaultComboBoxModel comboncc = new DefaultComboBoxModel();
            comboBox.setModel(comboncc);
            for (ChucVu macv : arr) {
                comboncc.addElement(macv.getTenCV());
            }
            
        }
        if (condition == "sapxeptheoten") {
            arrNv = nvBll.getNhanVien("sapxeptheoten");
        }
        if (condition == "sapxeptheoma") {
            arrNv = nvBll.getNhanVien("sapxeptheoma");
        }
        if (condition == "them") {
            arrNv = nvBll.getNhanVien("docnhanvien");
            NhanVienBLL test = new NhanVienBLL();
            ArrayList<ChucVu> arr = test.getChucVu();
            DefaultComboBoxModel comboncc = new DefaultComboBoxModel();
            comboBox.setModel(comboncc);
            for (ChucVu macv : arr) {
                comboncc.addElement(macv.getTenCV());
            }
        }

        String[] columnNames = {"Mã Nhân Viên", "Tên Nhân Viên", "Ngày Sinh", "Giới Tính", "Địa Chỉ", "SDT", "CCCD", "Ngày Vào Làm", "Chức Vụ", "Tình trạng"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        table.setModel(model);
        model.setRowCount(0);
        for (NhanVien nvdata : arrNv) {
        	String tinhTrang;
        	if(nvdata.getTinhTrang().equals("1")) {
        		tinhTrang = "Đang làm việc";
        	} else {
        		tinhTrang = "Đã nghỉ việc";
        	}
            Object[] row = new Object[]{nvdata.getMaNv(), nvdata.getTenNv(), nvdata.getNgaySinh(), nvdata.getGioiTinh(),
                nvdata.getDiaChi(), nvdata.getSdt(), nvdata.getCccd(), nvdata.getNgayVaoLam(),
                nvBll.getTenCV(nvdata.getChucVu()), tinhTrang};

            model.addRow(row);
        }
    }

    public void resetValue() {
        textFieldManv.setText("");
        textFieldCmnd.setText("");
        dateFieldNgayvaolam.setDate(null);
        textFieldNgayvaolam.setText("");
        dateFieldNgaysinh.setDate(null);
        textFieldNgaysinh.setText("");
        radioNam.setSelected(false);
        radioNu.setSelected(false);
        textFieldDienthoai.setText("");
        textFieldCmnd.setText("");
        textFieldTennv.setText("");
        textFieldDiachi.setText("");
        comboBox.setSelectedItem(null);
        btnThem.setEnabled(true);
        btnXoa.setEnabled(false);
        btnLuu.setEnabled(false);
        btnSua.setEnabled(false);

    }

    public void unSetEnable() {
        textFieldCmnd.setEnabled(true);
        dateFieldNgayvaolam.setEnabled(true);
        dateFieldNgaysinh.setEnabled(true);
        radioNam.setEnabled(true);
        radioNu.setEnabled(true);
        comboBox.setEnabled(true);
        textFieldDienthoai.setEnabled(true);
        textFieldTennv.setEnabled(true);
        textFieldDiachi.setEnabled(true);
        btnCapNhatAnh.setEnabled(true);
        btnThem.setEnabled(true);
        btnXoa.setEnabled(false);
        btnLuu.setEnabled(false);
        btnSua.setEnabled(false);
    }

    public void setEnable() {
        textFieldCmnd.setEnabled(false);
        dateFieldNgayvaolam.setEnabled(false);
        dateFieldNgaysinh.setEnabled(false);
        comboBox.setEnabled(false);
        radioNam.setEnabled(false);
        radioNu.setEnabled(false);
        textFieldDienthoai.setEnabled(false);
        textFieldTennv.setEnabled(false);
        textFieldDiachi.setEnabled(false);
    }
    
    public void hideField() {
    	dateFieldNgaysinh.setVisible(false);
    	dateFieldNgayvaolam.setVisible(false);
    	textFieldNgaysinh.setVisible(true);
    	textFieldNgayvaolam.setVisible(true);
    }
    
    public void unHideField() {
    	dateFieldNgaysinh.setVisible(true);
    	dateFieldNgayvaolam.setVisible(true);
    	textFieldNgaysinh.setVisible(false);
    	textFieldNgayvaolam.setVisible(false);
    }

	public String formatDateToString(java.util.Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateFormat.format(date);
		return dateString;
	}
    
	public Boolean checkEmtyValue() throws SQLException {
		// regular expression pattern	
		if (textFieldTennv.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Tên nhân viên rỗng!");
			textFieldTennv.requestFocus();
			return false;
		} else {
        	Pattern reg = Pattern.compile("^[A-Z][a-zàáạảãăẳẵắằặâấầẩẫậèéẻẽẹêếềễểệìíịĩỉýỳỹỷỵòóỏõọôỗổồốộơớờợỡởùúủũụưứừữửựđ]+( [A-Z][a-zàáạảãăẳẵắằặâấầẩẫậèéẻẽẹêếềễểệìíịĩỉýỳỹỷỵòóỏõọôỗổồốộơớờợỡởùúủũụưứừữửựđ]+)*( [A-Z][a-zàáạảãăẳẵắằặâấầẩẫậèéẻẽẹêếềễểệìíịĩỉýỳỹỷỵòóỏõọôỗổồốộơớờợỡởùúủũụưứừữửựđ]+)?$");
            boolean kt = reg.matcher(textFieldTennv.getText()).matches();
            if(kt == false) {
	            JOptionPane.showMessageDialog(contentPane, "Tên phải in hoa chữ đầu và chỉ chứa các chữ cái");
				textFieldTennv.requestFocus();
				return false;
            }
		}
		java.util.Date fieldNgaySinh = dateFieldNgaysinh.getDate();
        java.util.Date fieldNgayLam = dateFieldNgayvaolam.getDate();
		LocalDate currentDate = LocalDate.now();
		java.util.Date current = Date.valueOf(currentDate);
        if (dateFieldNgaysinh.getDate() == null) {
			JOptionPane.showMessageDialog(contentPane, "Ngày sinh rỗng!");
			dateFieldNgaysinh.requestFocus();
			return false;
		} else {
			String ngaySinh = formatDateToString(fieldNgaySinh);			 
			String hienTai = currentDate.toString();
			String namSinh = ngaySinh.substring(0, 4);
			String namHienTai = hienTai.substring(0, 4);
			int tuoi = Integer.parseInt(namHienTai) - Integer.parseInt(namSinh);
			if(tuoi < 18) {
				JOptionPane.showMessageDialog(contentPane, "Nhân viên chưa đủ 18!");
				dateFieldNgaysinh.requestFocus();
				return false;
			}
		}
		if(!radioNam.isSelected() && !radioNu.isSelected()) {
            JOptionPane.showMessageDialog(contentPane, "Chưa chọn giới tính!");
            return false;
        }	      
        if (textFieldCmnd.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Căn cước rỗng!");
			textFieldCmnd.requestFocus();
			return false;
		} else {
			Pattern reg = Pattern.compile("^[0-9]{12}$");
            boolean kt = reg.matcher(textFieldCmnd.getText()).matches();
            if(kt == false) {
                JOptionPane.showMessageDialog(contentPane, "Căn cước công dân phải có 12 số!");
                textFieldCmnd.requestFocus();
                return false;
            }
		}
        if (textFieldDienthoai.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Số điện thoại rỗng!");
			textFieldDienthoai.requestFocus();
			return false;
		} else {
			Pattern reg = Pattern.compile("((09|03|07|08|05)+([0-9]{8})\\b)");
            boolean kt = reg.matcher(textFieldDienthoai.getText()).matches();
            if(kt == false) {
                JOptionPane.showMessageDialog(contentPane, "Không tồn tại định dạng số điện thoại này!");
                textFieldDienthoai.requestFocus();
                return false;
            }
		}
        if (dateFieldNgayvaolam.getDate() == null) {
			JOptionPane.showMessageDialog(contentPane, "Ngày vào làm rỗng!");
			dateFieldNgayvaolam.requestFocus();
			return false;
		} else if (fieldNgayLam.compareTo(current) > 0 ) {
			JOptionPane.showMessageDialog(contentPane, "Ngày vào làm lớn hơn hiện tại!");
			dateFieldNgayvaolam.requestFocus();
			return false;
		}  
		if (textFieldDiachi.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Địa chỉ rỗng!");
			textFieldDiachi.requestFocus();
			return false;
		}	
		return true;
	}
     
    public QuanLyNhanVienGui() throws SQLException {

        setTitle("Quản lý nhân viên");
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
        tabbedPane.addTab("Thông tin nhân viên", null, panel_2, null);

        JPanel panel_5 = new JPanel();
        panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_5.setBounds(0, 10, 1067, 200);

        JLabel lblNewLabel_1 = new JLabel("Mã nhân viên");
        lblNewLabel_1.setBounds(20, 10, 100, 25);

        textFieldManv = new JTextField();
        textFieldManv.setBounds(130, 10, 200, 25);
        textFieldManv.setEnabled(false);
        textFieldManv.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Họ tên");
        lblNewLabel_3.setBounds(20, 50, 100, 25);

        textFieldTennv = new JTextField();
        textFieldTennv.setBounds(130, 50, 200, 25);
        textFieldTennv.setEnabled(false);
        textFieldTennv.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("Ngày sinh");
        lblNewLabel_4.setBounds(20, 90, 100, 25);

        dateFieldNgaysinh = new JDateChooser();
        dateFieldNgaysinh.setBounds(130, 90, 200, 25);
        dateFieldNgaysinh.setEnabled(false);
        
        textFieldNgaysinh = new JTextField();
        textFieldNgaysinh.setBounds(130, 90, 200, 25);
        textFieldNgaysinh.setEnabled(false);
        textFieldNgaysinh.setVisible(false);
        textFieldNgaysinh.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("Giới tính");
        lblNewLabel_6.setBounds(20, 130, 100, 25);

        radioNam.setBounds(130, 130, 100, 25);
        radioNu.setBounds(130, 160, 100, 25);
        groupGioitinh.add(radioNam);
        groupGioitinh.add(radioNu);
        radioNam.setEnabled(false);
        radioNu.setEnabled(false);

        JLabel lblNewLabel_7 = new JLabel("CCCD");
        lblNewLabel_7.setBounds(350, 10, 100, 25);

        textFieldCmnd = new JTextField();
        textFieldCmnd.setBounds(460, 10, 200, 25);
        textFieldCmnd.setEnabled(false);
        textFieldCmnd.setColumns(10);

        JLabel lblNewLabel_8 = new JLabel("Điện thoại");
        lblNewLabel_8.setBounds(350, 50, 100, 25);

        textFieldDienthoai = new JTextField();
        textFieldDienthoai.setBounds(460, 50, 200, 25);
        textFieldDienthoai.setEnabled(false);
        textFieldDienthoai.setColumns(10);

        JLabel lblNewLabel_9 = new JLabel("Ngày vào làm");
        lblNewLabel_9.setBounds(350, 90, 100, 25);

        dateFieldNgayvaolam = new JDateChooser();
        dateFieldNgayvaolam.setBounds(460, 90, 200, 25);
        dateFieldNgayvaolam.setEnabled(false);
        
        textFieldNgayvaolam = new JTextField();
        textFieldNgayvaolam.setBounds(460, 90, 200, 25);
        textFieldNgayvaolam.setEnabled(false);
        textFieldNgayvaolam.setVisible(false);
        textFieldNgayvaolam.setColumns(10);
        
        JLabel lblNewLabel_Chucvu = new JLabel("Chức vụ");
        lblNewLabel_Chucvu.setBounds(350, 130, 100, 25);
        
        comboBox = new JComboBox();
        comboBox.setBounds(460, 130, 200, 25);
        comboBox.setEnabled(false);
        
        JLabel lblNewLabel_13 = new JLabel("Địa chỉ");
        lblNewLabel_13.setBounds(350, 170, 100, 25);

        textFieldDiachi = new JTextField();
        textFieldDiachi.setBounds(460, 170, 200, 25);
        textFieldDiachi.setEnabled(false);
        textFieldDiachi.setColumns(10);

        JPanel panel_6 = new JPanel();
        panel_6.setBounds(0, 210, 1067, 78);
        btnLuu.setFont(new Font("Tahoma", Font.BOLD, 12));
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
	        				boolean flag = true;
	        				if(taiKhoan.getQuyen().equals("RL2")) {
	        					if(comboBox.getSelectedItem().toString().equals("ADMIN")) {
	        						flag = false;
	        						JOptionPane.showMessageDialog(contentPane, "Không thể thêm người có chức vụ cao hơn bạn!");
	        					} else if(comboBox.getSelectedItem().toString().equals("QUẢN LÝ")) {
	        						flag = false;
	        						JOptionPane.showMessageDialog(contentPane, "Không thể thêm người có chức vụ bằng bạn!");
	        					}
	        				}
	        				if(flag) {
	        					int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn thêm nhân viên "+textFieldManv.getText(), "Confirmation",
	    								JOptionPane.YES_NO_OPTION);
	    						if (confirmed == JOptionPane.YES_OPTION) {
	    							NhanVien nv = new NhanVien();
	    							NhanVienBLL luunv;
	    							try {
	    								luunv = new NhanVienBLL();
	    		                        nv.setMaNv(textFieldManv.getText());
	    		                        nv.setTenNv(textFieldTennv.getText());
	    		                		java.util.Date fieldNgaySinh = dateFieldNgaysinh.getDate();
	    		                        java.util.Date fieldNgayLam = dateFieldNgayvaolam.getDate();
	    		            			String ngaySinh = formatDateToString(fieldNgaySinh);
	    		            			String ngayLam = formatDateToString(fieldNgayLam);
	    		                        nv.setNgaySinh(ngaySinh);
	    		                        if(radioNam.isSelected()) {
	    		                        	nv.setGioiTinh("Nam");
	    		                        } else {
	    		                        	nv.setGioiTinh("Nữ");
	    		                        }
	    		                        nv.setCccd(textFieldCmnd.getText());
	    		                        nv.setSdt(textFieldDienthoai.getText());
	    		                        nv.setNgayVaoLam(ngayLam);
	    		                        nv.setDiaChi(textFieldDiachi.getText());
	    		                        nv.setChucVu(luunv.getMaCV(comboBox.getSelectedItem().toString()));
	    								boolean checkAddPro = luunv.addNhanVien(nv);
	    								if (checkAddPro) {
	    									JOptionPane.showMessageDialog(contentPane, "Thêm thành công");
	    									resetValue();                                                                 
	    									setEnable();
	    									hienthinhanvien("hien thi");
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
					}
					if(fixbtn) {
						if (checkEmtyValue()) {
	        				boolean flag = true;
	        				if(taiKhoan.getQuyen().equals("RL2")) {
	        					if(comboBox.getSelectedItem().toString().equals("ADMIN")) {
	        						flag = false;
	        						JOptionPane.showMessageDialog(contentPane, "Không thể sửa người có chức vụ cao hơn bạn!");
	        					} else if(comboBox.getSelectedItem().toString().equals("QUẢN LÝ") && !taiKhoan.getMaNV().equals(textFieldManv.getText())) {
	        						flag = false;
	        						JOptionPane.showMessageDialog(contentPane, "Không thể sửa người có chức vụ bằng bạn!");
	        					}
	        				}
	        				if(flag) {
	        					int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn sửa nhân viên "+textFieldManv.getText(), "Confirmation",
	    								JOptionPane.YES_NO_OPTION);
	    						if (confirmed == JOptionPane.YES_OPTION) {
	    							NhanVien nv = new NhanVien();
	    							TaiKhoan tk=new TaiKhoan();
	    							NhanVienBLL luunv;
	    							TaiKhoanBLL tkb;
	    							try {
	    								tkb=new TaiKhoanBLL();
	    								luunv = new NhanVienBLL();
	    		                        nv.setMaNv(textFieldManv.getText());
	    		                        nv.setTenNv(textFieldTennv.getText());
	    		                		java.util.Date fieldNgaySinh = dateFieldNgaysinh.getDate();
	    		                        java.util.Date fieldNgayLam = dateFieldNgayvaolam.getDate();
	    		            			String ngaySinh = formatDateToString(fieldNgaySinh);
	    		            			String ngayLam = formatDateToString(fieldNgayLam);
	    		                        nv.setNgaySinh(ngaySinh);
	    		                        if(radioNam.isSelected()) {
	    		                        	nv.setGioiTinh("Nam");
	    		                        } else {
	    		                        	nv.setGioiTinh("Nữ");
	    		                        }
	    		                        nv.setCccd(textFieldCmnd.getText());
	    		                        nv.setSdt(textFieldDienthoai.getText());
	    		                        nv.setNgayVaoLam(ngayLam);
	    		                        nv.setDiaChi(textFieldDiachi.getText());
	    		                        nv.setChucVu(luunv.getMaCV(comboBox.getSelectedItem().toString()));
//	    		                        tk.setMaNV(textFieldManv.getText());
//	    		                        String manv=textFieldManv.getText();
//	    		                        String maquyen2=tkb.getQuyen(manv);
//	    		                        System.out.println("Mã Quyền: '" + maquyen2 + "'");
//	    		                        tk.setQuyen(maquyen2);
	    								boolean checkAddPro = luunv.fixNhanVien(nv);
	    								if (checkAddPro) {
	    									JOptionPane.showMessageDialog(contentPane, "Sửa thành công");
	    									resetValue();                                                                 
	    									setEnable();
	    									hienthinhanvien("hien thi");
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
					}
				} catch (NumberFormatException | HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnThem.setBounds(124, 10, 104, 53);
        btnThem.setFocusPainted(false);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    resetValue();
                    unSetEnable();
                    unHideField();
                    NhanVienBLL nvbll;
                    nvbll = new NhanVienBLL();
                    String lastMaNv = nvbll.getLastMaNV();
                    String maNv = lastMaNv.substring(lastMaNv.length()-3, lastMaNv.length());
                    int check = Integer.parseInt(maNv);
                    if(check < 9) {
                    	textFieldManv.setText("NV00"+(check+1));
                    } else if(check < 99) {
                    	textFieldManv.setText("NV0"+(check+1));
                    } else {
                    	textFieldManv.setText("NV"+(check+1));
                    }
                    btnSua.setEnabled(false);
                    btnThem.setEnabled(false);
                    btnLuu.setEnabled(true);
                    btnXoa.setEnabled(false);
                    addbtn = true;
                    try {
                        hienthinhanvien("them");
                    } catch (SQLException e3) {
                        // TODO Auto-generated catch block
                        e3.printStackTrace();
                    }
                    
                } catch (SQLException ex) {
					Logger.getLogger(QuanLyNhanVienGui.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
		});
        btnThem.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Add.png"))));
        btnXoa.setFont(new Font("Tahoma", Font.BOLD, 12));
       
        
        btnXoa.setBounds(352, 10, 150, 53);
        btnXoa.setEnabled(false);
        btnXoa.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Delete.png"))));
        btnXoa.setFocusPainted(false);
    	btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = true;
				if(taiKhoan.getMaNV().equals(textFieldManv.getText())) {
					flag = false;
					JOptionPane.showMessageDialog(contentPane, "Không thể cho nghỉ việc chính bạn!");
				} else if(taiKhoan.getQuyen().equals("RL2")) {
					if(comboBox.getSelectedItem().toString().equals("ADMIN")) {
						flag = false;
						JOptionPane.showMessageDialog(contentPane, "Không thể cho nghỉ việc người có chức vụ cao hơn bạn!");
					} else if(comboBox.getSelectedItem().toString().equals("QUẢN LÝ")) {
						flag = false;
						JOptionPane.showMessageDialog(contentPane, "Không thể cho nghỉ việc người có chức vụ bằng bạn!");
					}
				}
				if(flag) {
					int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn cho nghỉ việc nhân viên "+textFieldManv.getText(), "Confirmation",
							JOptionPane.YES_NO_OPTION);
					if (confirmed == JOptionPane.YES_OPTION) {
						NhanVienBLL hideNv;
						try {
							hideNv = new NhanVienBLL();
							if (hideNv.hideNhanVien(textFieldManv.getText())) {
								JOptionPane.showMessageDialog(contentPane, "Cho nghỉ việc thành công!");
								hienthinhanvien("hien thi");
								resetValue();
								setEnable();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}
			}
		});

        JPanel panel_7 = new JPanel();
        panel_7.setBounds(697, 10, 370, 53);
        panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));

        JButton btnDongBo = new JButton("");
        btnDongBo.setBounds(238, 10, 104, 53);
        btnDongBo.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienGui.class.getResource(".\\Image\\Refresh-icon.png"))));
        		btnDongBo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					resetValue();
					setEnable();
					unHideField();
					hienthinhanvien("hien thi");
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
				unHideField();
				if (radioSapxepten.isSelected()) {				
					try {
						hienthinhanvien("sapxeptheoten");
						radioSapxepten.setSelected(false);;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				if (radioSapxepma.isSelected()) {
					try {
						hienthinhanvien("sapxeptheoma");
						radioSapxepma.setSelected(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});

        groupSapxep.add(radioSapxepten);
        groupSapxep.add(radioSapxepma);
        GroupLayout gl_panel_7 = new GroupLayout(panel_7);
        gl_panel_7.setHorizontalGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_7.createSequentialGroup().addContainerGap()
                        .addComponent(btnSapxep, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE).addGap(18)
                        .addComponent(radioSapxepten, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(radioSapxepma, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(15, Short.MAX_VALUE)));
        gl_panel_7.setVerticalGroup(gl_panel_7.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
                gl_panel_7.createSequentialGroup().addContainerGap()
                        .addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
                                .addComponent(btnSapxep, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                .addComponent(radioSapxepten).addComponent(radioSapxepma))
                        .addContainerGap()));
        panel_7.setLayout(gl_panel_7);

        JPanel panel_8 = new JPanel();
        panel_8.setBounds(0, 250, 1067, 300);
        lbThongbao.setBounds(227, 40, 569, 32);

        lbThongbao.setText("DANH SÁCH NHÂN VIÊN SIÊU THỊ");

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
				String maNV = table.getModel().getValueAt(row, 0).toString();
				String tenNV = table.getModel().getValueAt(row, 1).toString(); // get the value of the first column
				String ngaySinh = table.getModel().getValueAt(row, 2).toString(); // get the value of the second column
				String gioiTinh = table.getModel().getValueAt(row, 3).toString();
				String diaChi = table.getModel().getValueAt(row, 4).toString();
				String sdt = table.getModel().getValueAt(row, 5).toString();
				String cccd = table.getModel().getValueAt(row, 6).toString();
                String ngayVao = table.getModel().getValueAt(row, 7).toString();
                String chucVu = table.getModel().getValueAt(row, 8).toString();
//				String img = table.getModel().getValueAt(row, 10).toString();
				textFieldManv.setText(maNV);
				textFieldTennv.setText(tenNV);
                textFieldNgaysinh.setText(ngaySinh);
                if(gioiTinh.equalsIgnoreCase("Nam")) radioNam.setSelected(true);
                else radioNu.setSelected(true);
                textFieldDiachi.setText(diaChi);
                textFieldCmnd.setText(cccd);
				textFieldDienthoai.setText(sdt);
				textFieldNgayvaolam.setText(ngayVao);
                comboBox.setSelectedItem(chucVu);
				setEnable();
				hideField();
				btnSua.setEnabled(true);
				btnXoa.setEnabled(true);
				btnThem.setEnabled(true);
				btnLuu.setEnabled(false);
			   
				
//				g.dinvose();

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
        lbThemanh.setBounds(10, 0, 139, 123);
        panel_5.add(lbThemanh);
        panel_5.add(btnCapNhatAnh);
        panel_5.add(lblNewLabel_6);
        panel_5.add(radioNam);
        panel_5.add(radioNu);
        panel_5.add(lblNewLabel_1);
        panel_5.add(textFieldManv);
        panel_5.add(lblNewLabel_7);
        panel_5.add(textFieldCmnd);
        panel_5.add(lblNewLabel_3);
        panel_5.add(lblNewLabel_4);
        panel_5.add(textFieldTennv);
        panel_5.add(dateFieldNgaysinh);
        panel_5.add(textFieldNgaysinh);
        panel_5.add(lblNewLabel_8);
        panel_5.add(lblNewLabel_9);
        panel_5.add(textFieldDienthoai);
        panel_5.add(dateFieldNgayvaolam);
        panel_5.add(textFieldNgayvaolam);
        panel_5.add(lblNewLabel_13);
        panel_5.add(textFieldDiachi);
        panel_5.add(lblNewLabel_Chucvu);
        panel_5.add(comboBox);
		/*
		 * panel_5.add(lblNewLabel_11); panel_5.add(textFieldTaikhoan);
		 * panel_5.add(lblNewLabel_12); panel_5.add(textFieldMatkhau);
		 */
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
                    NhanVienDAL tknv;
                    ArrayList<NhanVien> arrPro = new ArrayList<NhanVien>(); // TODO Auto-generated catch block
                    arrPro = tknv.docNhanVien("timkiem",null);
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
        		if(taiKhoan.getQuyen().equals("RL2")) {
	           		QuanLyHome home=new QuanLyHome();
	                setVisible(false);               
	                home.setVisible(true);
	                home.setLocationRelativeTo(null);
        		} else {
        			AdminHome home=new AdminHome();
	                setVisible(false);               
	                home.setVisible(true);
	                home.setLocationRelativeTo(null);
        		}      		
        	}
        });
        btnNewButton.setBounds(938, 153, 119, 39);
        panel_5.add(btnNewButton);
        
        txtSearch = new JTextField();
        txtSearch.setBounds(800, 10, 257, 25);
        panel_5.add(txtSearch);
        txtSearch.setColumns(10);
        txtSearch.setText("Tìm kiếm theo tên");
        JButton btnSearch = new JButton("Tìm kiếm");
        btnSearch.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			hienthinhanvien("hien thi");
            		ArrayList<NhanVien> data = new ArrayList<>();
            		NhanVienBLL nvBll = new NhanVienBLL();
    				// Get the number of rows and columns in the JTable
    				int numRows = table.getRowCount();
    				// Loop through each row in the JTable and add the data to the ArrayList
    				for (int i = 0; i < numRows; i++) {
    					NhanVien nv = new NhanVien();
    					nv.setMaNv((String) table.getValueAt(i, 0));
    					nv.setTenNv((String) table.getValueAt(i, 1));
    					nv.setNgaySinh((String) table.getValueAt(i, 2));
    					nv.setGioiTinh((String) table.getValueAt(i, 3));
    					nv.setDiaChi((String) table.getValueAt(i, 4));
    					nv.setSdt((String) table.getValueAt(i, 5));
    					nv.setCccd((String) table.getValueAt(i, 6));
    					nv.setNgayVaoLam((String) table.getValueAt(i, 7));
    					nv.setChucVu(nvBll.getChucVuNV((String) table.getValueAt(i, 0)));
    					if(table.getValueAt(i, 9).equals("Đang làm việc")) {
    						nv.setTinhTrang("1");
            			} else {
            				nv.setTinhTrang("0");
            			}
    					data.add(nv);

    				}		
    				
    		        
    				
    				String[] columnNames = {"Mã Nhân Viên", "Tên Nhân Viên", "Ngày Sinh", "Giới Tính", "Địa Chỉ", "SDT", "CCCD", "Ngày Vào Làm", "Chức Vụ", "Tình trạng"};
    		        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

    		        table.setModel(model);
    		        model.setRowCount(0);
    				if(!txtSearch.getText().isEmpty()) {
    					for (NhanVien nvdata : data) {
    						if(nvdata.getTenNv().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
            		        	String tinhTrang;
            		        	if(nvdata.getTinhTrang().equals("1")) {
            		        		tinhTrang = "Đang làm việc";
            		        	} else {
            		        		tinhTrang = "Đã nghỉ việc";
            		        	}
            		            Object[] row = new Object[]{nvdata.getMaNv(), nvdata.getTenNv(), nvdata.getNgaySinh(), nvdata.getGioiTinh(),
            		                nvdata.getDiaChi(), nvdata.getSdt(), nvdata.getCccd(), nvdata.getNgayVaoLam(),
            		                nvBll.getTenCV(nvdata.getChucVu()), tinhTrang};

            		            model.addRow(row);
    						}
        		        }
    				} else {
    					try {
    						hienthinhanvien("hien thi");
    					} catch (SQLException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					}
    				}
    				if(table.getRowCount() == 0) {
    					JOptionPane.showMessageDialog(contentPane, "Không tìm thấy nhân viên!");
    				}
        		} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		txtSearch.setText("");
        	}
        });
        btnSearch.setBounds(682, 10, 108, 25);
        panel_5.add(btnSearch);
        panel_2.add(panel_6);
        
        btnSua.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				boolean flag = true;
				if(taiKhoan.getQuyen().equals("RL2")) {
					if(comboBox.getSelectedItem().toString().equals("ADMIN")) {
						flag = false;
						JOptionPane.showMessageDialog(contentPane, "Không thể sửa người có chức vụ cao hơn bạn!");
					} else if(comboBox.getSelectedItem().toString().equals("QUẢN LÝ") && !taiKhoan.getMaNV().equals(textFieldManv.getText())) {
						flag = false;
						JOptionPane.showMessageDialog(contentPane, "Không thể sửa người có chức vụ bằng bạn!");
					}
				}
				if(flag) {
                   unSetEnable();
                   java.sql.Date ngaysinh = java.sql.Date.valueOf(textFieldNgaysinh.getText());
                   java.sql.Date ngayvaolam = java.sql.Date.valueOf(textFieldNgayvaolam.getText());
                   dateFieldNgaysinh.setDate(ngaysinh);
                   dateFieldNgayvaolam.setDate(ngayvaolam);
                   unHideField();
                   btnSua.setEnabled(false);
                   btnThem.setEnabled(false);
                   btnLuu.setEnabled(true);
                   btnXoa.setEnabled(false);
                   fixbtn = true;
                   
				}
				if(taiKhoan.getMaNV().equals(textFieldManv.getText())) {
					comboBox.setEnabled(false);
				}
        	}
        });
        btnSua.setEnabled(false);
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSua.setFocusPainted(false);
        btnSua.setBounds(512, 10, 104, 53);
        panel_6.add(btnSua);
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

        JLabel lblNewLabel = new JLabel("QUẢN LÝ NHÂN VIÊN");
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
        hienthinhanvien("hien thi");

    }
}
