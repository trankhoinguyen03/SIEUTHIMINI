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

import BLL.NhanVien;
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
    private JTextField textFieldNgaysinh;
    private JTextField textFieldCmnd;
    private JTextField textFieldDienthoai;
    private JTextField textFieldNgayvaolam;
    private JTextField textFieldDiachi;
    //private JTextField textFieldTaikhoan;
    //private JTextField textFieldMatkhau;
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
    JButton btnXoa = new JButton("Ẩn");
    JButton btnSua = new JButton("Sửa");
    boolean check = true;
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
    boolean checkFix = false;

    public void hienthinhanvien(String condition) throws SQLException {
        NhanVienDAL nvDal = new NhanVienDAL();
        ArrayList<NhanVien> arrNv = new ArrayList<NhanVien>();
        if (condition == "hien thi") {

            arrNv = nvDal.docNhanVien("docnhanvien", null);
        }
        if (condition == "sapxeptheoten") {
            arrNv = nvDal.docNhanVien("sapxeptheoten", null);
        }
        if (condition == "sapxeptheoma") {
            arrNv = nvDal.docNhanVien("sapxeptheoma", null);
        }
        if (condition == "them") {

            arrNv = nvDal.docNhanVien("docnhanvien", null);
            /*
			ArrayList<LoaiHang> arrMaLH = test.docLoaiHang();
			DefaultComboBoxModel combo = new DefaultComboBoxModel();
			comboBox.setModel(combo);
			for (LoaiHang malh : arrMaLH) {
				combo.addElement(malh.getTenLH());
                        
			}*/
        }

        String[] columnNames = {"Mã Nhân Viên", "Tên Nhân Viên", "Ngày Sinh", "Giới Tính", "Địa Chỉ", "CCCD", "SDT", "Ngày Vào Làm", "Chức Vụ"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        table.setModel(model);
        model.setRowCount(0);
        for (NhanVien nvdata : arrNv) {
            //NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
            String formatGioiTinh;
            if (nvdata.getGioiTinh() == 1) {
                formatGioiTinh = "Nam";
            } else {
                formatGioiTinh = "Nữ";
            }
            
            Object[] row = new Object[]{};/*nvdata.getMaNv(), nvdata.getTenNv(), nvdata.getNgaySinh(), formatGioiTinh,
                nvdata.getDiaChi(), nvdata.getCmnd(), nvdata.getSdt(), nvdata.getNgayVaoLam(),
                nvdata.getTaiKhoan(), nvdata.getMatKhau()};*/

            model.addRow(row);
        }

        lastRow = table.getRowCount() - 1; // get index of the last row
        lastValueMaNv = table.getValueAt(lastRow, 0); // get the value at the last row and column n

    }

    public void resetValue() {
        textFieldManv.setText("");
        textFieldManv.setEnabled(true);
        textFieldCmnd.setText("");
        textFieldCmnd.setEnabled(true);
        textFieldNgayvaolam.setText("");
        textFieldNgayvaolam.setEnabled(true);
        textFieldNgaysinh.setText("");
        textFieldNgaysinh.setEnabled(true);
        radioNam.setSelected(false);
        radioNam.setEnabled(true);
        radioNu.setSelected(false);
        radioNu.setEnabled(true);
        textFieldDienthoai.setText("");
        textFieldDienthoai.setEnabled(true);
        textFieldTennv.setText("");
        textFieldTennv.setEnabled(true);
        textFieldDiachi.setText("");
        textFieldDiachi.setEnabled(true);
		/*
		 * textFieldTaikhoan.setText(""); textFieldTaikhoan.setEnabled(true);
		 * textFieldMatkhau.setText(""); textFieldMatkhau.setEnabled(true);
		 */
        lbThemanh.setIcon(null);
        btnCapNhatAnh.setEnabled(true);
        btnThem.setEnabled(true);
        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);
        btnLuu.setEnabled(false);

    }

    public void unSetEnable() {
        textFieldManv.setEnabled(true);
        textFieldCmnd.setEnabled(true);
        textFieldNgayvaolam.setEnabled(true);
        textFieldNgaysinh.setEnabled(true);
        radioNam.setEnabled(true);
        radioNu.setEnabled(true);
        textFieldDienthoai.setEnabled(true);
        textFieldTennv.setEnabled(true);
        textFieldDiachi.setEnabled(true);
        //textFieldTaikhoan.setEnabled(true);
        //textFieldMatkhau.setEnabled(true);
        btnCapNhatAnh.setEnabled(true);
        btnThem.setEnabled(true);
        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);
        btnLuu.setEnabled(false);
    }

    public void setEnable() {
        textFieldManv.setEnabled(false);
        textFieldCmnd.setEnabled(false);
        textFieldNgayvaolam.setEnabled(false);
        textFieldNgaysinh.setEnabled(false);
        radioNam.setEnabled(false);
        radioNu.setEnabled(false);
        textFieldDienthoai.setEnabled(false);
        textFieldTennv.setEnabled(false);
        textFieldDiachi.setEnabled(false);
        //textFieldTaikhoan.setEnabled(false);
        //textFieldMatkhau.setEnabled(false);
        btnCapNhatAnh.setEnabled(false);
    }

    	public Boolean checkEmtyValue() throws SQLException {
		// regular expression pattern
		if(textFieldManv.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Mã nhân viên trống!");
			textFieldManv.requestFocus();
			return false;
		}
		if(!textFieldManv.getText().isEmpty()) {
			NhanVienDAL nvd = new NhanVienDAL();
			ArrayList<NhanVien> arrPro = new ArrayList<NhanVien>();
			arrPro = nvd.docNhanVien("docnhanvien",null);
			if(fixbtn) {
				for(NhanVien nv:arrPro) {
					if(Integer.parseInt(oldMaNV)!=Integer.parseInt(textFieldManv.getText()) && nv.getMaNv()== Integer.parseInt(textFieldManv.getText())) {
						JOptionPane.showMessageDialog(contentPane, "Mã nhân viên đã tồn tại!");
						textFieldManv.requestFocus();
						return false;
						
					}
				}
			}
			if(addbtn) {
				for(NhanVien nv:arrPro) {
					if(nv.getMaNv()== Integer.parseInt(textFieldManv.getText())) {
						JOptionPane.showMessageDialog(contentPane, "Mã nhân viên đã tồn tại!");
						textFieldManv.requestFocus();
						return false;
						
					}
				}
			}
		if (textFieldTennv.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Tên nhân viên rỗng!");
			textFieldTennv.requestFocus();
			return false;
		}
                if (!textFieldTennv.getText().isEmpty()) {
			Pattern reg = Pattern.compile("[0-9]*$");
                        boolean kt = reg.matcher(textFieldTennv.getText()).matches();
                        if(kt == true) {
                            JOptionPane.showMessageDialog(contentPane, "Tên không chứa số!");
			textFieldTennv.requestFocus();
			return false;
                        }
		}
                if (textFieldNgaysinh.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Ngày sinh rỗng!");
			textFieldNgaysinh.requestFocus();
			return false;
		}
                if (!textFieldNgaysinh.getText().isEmpty()) {
			Pattern reg = Pattern.compile("^\\d{4}[\\-](0?[1-9]|1[012])[\\-](0?[1-9]|[12][0-9]|3[01])$");
                        boolean kt = reg.matcher(textFieldNgaysinh.getText()).matches();
                        if(kt == false) {
                            JOptionPane.showMessageDialog(contentPane, "Ngày sinh phải có định dạng yyyy-mm-dd!");
			textFieldNgaysinh.requestFocus();
			return false;
                        }
		}
		if(!radioNam.isSelected() && !radioNu.isSelected()) {
                    JOptionPane.showMessageDialog(contentPane, "Chưa chọn giới tính!");
                    return false;
                }	
                
                if (textFieldCmnd.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Cmnd rỗng!");
			textFieldCmnd.requestFocus();
			return false;
		}
                if (!textFieldCmnd.getText().isEmpty()) {
			Pattern reg = Pattern.compile("^(0?)(0-9){8}|[0-9]{9}$");
                        boolean kt = reg.matcher(textFieldCmnd.getText()).matches();
                        if(kt == false) {
                            JOptionPane.showMessageDialog(contentPane, "Cmnd phải có 9 số!");
			textFieldCmnd.requestFocus();
			return false;
                        }
		}
                if (textFieldDienthoai.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Số điện thoại rỗng!");
			textFieldDienthoai.requestFocus();
			return false;
		}
                if (!textFieldDienthoai.getText().isEmpty()) {
			Pattern reg = Pattern.compile("^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$");
                        boolean kt = reg.matcher(textFieldDienthoai.getText()).matches();
                        if(kt == false) {
                            JOptionPane.showMessageDialog(contentPane, "Không tồn tại định dạng số điện thoại này!");
			textFieldDienthoai.requestFocus();
			return false;
                        }
		}
                if (textFieldNgayvaolam.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Ngày vào làm rỗng");
			textFieldNgayvaolam.requestFocus();
			return false;

		}
                
                if (textFieldNgayvaolam.getText().isEmpty()) {
			Pattern reg = Pattern.compile("^\\d{4}[\\-](0?[1-9]|1[012])[\\-](0?[1-9]|[12][0-9]|3[01])$");
                        boolean kt = reg.matcher(textFieldNgayvaolam.getText()).matches();
                        if(kt == false) {
                            JOptionPane.showMessageDialog(contentPane, "Ngày vào làm phải có định dạng yyyy-mm-dd!");
			textFieldNgayvaolam.requestFocus();
			return false;
                        }
		}
		/*
		 * if (textFieldTaikhoan.getText().isEmpty()) {
		 * JOptionPane.showMessageDialog(contentPane, "Tài khoản rỗng!");
		 * textFieldTaikhoan.requestFocus(); return false; } if
		 * (textFieldMatkhau.getText().isEmpty()) {
		 * JOptionPane.showMessageDialog(contentPane, "Mật khẩu rỗng!");
		 * textFieldMatkhau.requestFocus(); return false; }
		 */
                if (textFieldDiachi.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Địa chỉ rỗng!");
			textFieldDiachi.requestFocus();
			return false;
		}
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

        textFieldNgaysinh = new JTextField();
        textFieldNgaysinh.setBounds(130, 90, 200, 25);
        textFieldNgaysinh.setEnabled(false);
        textFieldNgaysinh.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("Giới tính");
        lblNewLabel_6.setBounds(20, 130, 100, 25);

        radioNam.setBounds(130, 130, 100, 25);
        radioNu.setBounds(130, 160, 100, 25);
        groupGioitinh.add(radioNam);
        groupGioitinh.add(radioNu);
        radioNam.setEnabled(false);
        radioNu.setEnabled(false);

        JLabel lblNewLabel_7 = new JLabel("CMND");
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

        textFieldNgayvaolam = new JTextField();
        textFieldNgayvaolam.setBounds(460, 90, 200, 25);
        textFieldNgayvaolam.setEnabled(false);
        textFieldNgayvaolam.setColumns(10);
        
        JLabel lblNewLabel_Chucvu = new JLabel("Chức vụ");
        lblNewLabel_Chucvu.setBounds(350, 130, 100, 25);
        
        comboBox = new JComboBox();
        comboBox.setBounds(460, 130, 200, 25);
        
        JLabel lblNewLabel_13 = new JLabel("Địa chỉ");
        lblNewLabel_13.setBounds(680, 10, 100, 25);

        textFieldDiachi = new JTextField();
        textFieldDiachi.setBounds(790, 10, 250, 50);
        textFieldDiachi.setEnabled(false);
        textFieldDiachi.setColumns(10);

		/*
		 * JLabel lblNewLabel_11 = new JLabel("Tài khoản");
		 * lblNewLabel_11.setBounds(350, 130, 100, 25);
		 * 
		 * 
		 * textFieldTaikhoan = new JTextField(); textFieldTaikhoan.setBounds(460, 130,
		 * 200, 25); textFieldTaikhoan.setEnabled(false);
		 * textFieldTaikhoan.setColumns(10);
		 * 
		 * 
		 * JLabel lblNewLabel_12 = new JLabel("Mật khẩu"); lblNewLabel_12.setBounds(350,
		 * 170, 100, 25);
		 * 
		 * 
		 * textFieldMatkhau = new JTextField(); textFieldMatkhau.setBounds(460, 170,
		 * 200, 25); textFieldMatkhau.setEnabled(false);
		 * textFieldMatkhau.setColumns(10);
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
					
						NhanVien nv = new NhanVien();
						NhanVienDAL luunv;
						if(addbtn) {
							try {
								luunv = new NhanVienDAL();
								nv.setMaNv(Integer.parseInt(textFieldManv.getText()));
                                                                nv.setTenNv(textFieldTennv.getText());
                                                                nv.setNgaySinh(textFieldNgaysinh.getText());
                                                                int gioitinh;
                                                                if(radioNam.isSelected()) gioitinh = 1;
                                                                else gioitinh = 0;
                                                                nv.setGioiTinh(gioitinh);
                                                                nv.setDiaChi(textFieldDiachi.getText());
								nv.setCmnd(textFieldCmnd.getText());
                                                                nv.setSdt(textFieldDienthoai.getText());
								nv.setNgayVaoLam(textFieldNgayvaolam.getText());
								//nv.setTaiKhoan(textFieldTaikhoan.getText());
								//nv.setMatKhau(textFieldMatkhau.getText());
								boolean checkAddPro = luunv.themnhanvien(nv,"themnhanvien",null);
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
						if(fixbtn) {
							
							try {
								luunv = new NhanVienDAL();
								nv.setMaNv(Integer.parseInt(textFieldManv.getText()));
                                                                nv.setTenNv(textFieldTennv.getText());
                                                                nv.setNgaySinh(textFieldNgaysinh.getText());
                                                                int gioitinh;
                                                                if(radioNam.isSelected()) gioitinh = 1;
                                                                else gioitinh = 0;
                                                                nv.setGioiTinh(gioitinh);
                                                                nv.setDiaChi(textFieldDiachi.getText());
								nv.setCmnd(textFieldCmnd.getText());
                                                                nv.setSdt(textFieldDienthoai.getText());
								nv.setNgayVaoLam(textFieldNgayvaolam.getText());
								//nv.setTaiKhoan(textFieldTaikhoan.getText());
								//nv.setMatKhau(textFieldMatkhau.getText());
								
								boolean checkAddPro = luunv.themnhanvien(nv,"suanhanvien",oldMaNV);
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
                                    resetValue();
                                    textFieldManv.setEnabled(false);
                                    textFieldSearch.setEnabled(false);
                                    NhanVienDAL nv;
                                    nv = new NhanVienDAL();
                                    int lastMaHD = nv.getLastMaNV();
                                    textFieldManv.setText("" + (lastMaHD + 1));
                                    btnThem.setEnabled(false);
                                    btnLuu.setEnabled(true);
                                    btnXoa.setEnabled(false);
                                    btnSua.setEnabled(false);
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
        btnSua.setBounds(283, 10, 104, 53);

        btnSua.setEnabled(false);
        btnSua.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Change.png"))));
        btnSua.setFocusPainted(false);
        		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                                addbtn = false;
				fixbtn = true;
                                textFieldManv.setEnabled(false);
				oldMaNV = textFieldManv.getText();
				unSetEnable();
				btnThem.setEnabled(false);
				btnLuu.setEnabled(true);
				
				try {
					hienthinhanvien("them");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
        btnXoa.setBounds(419, 10, 104, 53);

        btnXoa.setEnabled(false);
        btnXoa.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Delete.png"))));
        btnXoa.setFocusPainted(false);
                	btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa nhân viên này", "Confirmation",
						JOptionPane.YES_NO_OPTION);
				if (confirmed == JOptionPane.YES_OPTION) {
					NhanVienDAL deleteNv;
					try {
						deleteNv = new NhanVienDAL();
						if (deleteNv.xoaNhanVien(textFieldManv.getText())) {
							JOptionPane.showMessageDialog(contentPane, "Xóa thành công!");
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
		});

        JPanel panel_7 = new JPanel();
        panel_7.setBounds(697, 10, 370, 53);
        panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));

        JButton btnDongBo = new JButton("");
        btnDongBo.setBounds(563, 10, 104, 53);
        btnDongBo.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienGui.class.getResource(".\\Image\\Refresh-icon.png"))));
        		btnDongBo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
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
				NhanVienDAL nvd;
				if (radioSapxepten.isSelected()) {

					
					try {
						nvd = new NhanVienDAL();

						hienthinhanvien("sapxeptheoten");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				if (radioSapxepma.isSelected()) {

					
					try {
						nvd = new NhanVienDAL();

						hienthinhanvien("sapxeptheoma");
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
				String cmnd = table.getModel().getValueAt(row, 5).toString();
				String sdt = table.getModel().getValueAt(row, 6).toString();
                                String ngayVao = table.getModel().getValueAt(row, 7).toString();
                                String taiKhoan = table.getModel().getValueAt(row, 8).toString();
                                String matKhau = table.getModel().getValueAt(row, 9).toString();
//				String img = table.getModel().getValueAt(row, 10).toString();
				textFieldManv.setText(maNV);
				textFieldTennv.setText(tenNV);
                                textFieldNgaysinh.setText(ngaySinh);
                                if(gioiTinh.equals("Nam")) radioNam.setSelected(true);
                                else radioNu.setSelected(true);
                                textFieldDiachi.setText(diaChi);
                                textFieldCmnd.setText(cmnd);
				textFieldDienthoai.setText(sdt);
				textFieldNgayvaolam.setText(ngayVao);
				//textFieldTaikhoan.setText(taiKhoan);
				//textFieldMatkhau.setText(matKhau);
				
				setEnable();
				btnXoa.setEnabled(true);
				btnSua.setEnabled(true);
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
        panel_5.add(textFieldNgaysinh);
        panel_5.add(lblNewLabel_8);
        panel_5.add(lblNewLabel_9);
        panel_5.add(textFieldDienthoai);
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
        		 HomeAdmin home=new HomeAdmin();
                 setVisible(false);
              
                 home.setVisible(true);
                 home.setLocationRelativeTo(null);
        	}
        });
        btnNewButton.setBounds(938, 153, 119, 39);
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
