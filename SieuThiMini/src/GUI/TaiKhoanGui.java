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

import BLL.LoaiHangBLL;
import BLL.NhanVienBLL;
import BLL.TaiKhoanBLL;
import DTO.ChucVu;
import DTO.LoaiHang;
import DTO.NhanVien;
import DTO.TaiKhoan;
import DAL.TaiKhoanDAL;

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

public class TaiKhoanGui extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldTennv;
    //private JTextField textFieldTennv;
    //private JTextField textFieldNgaysinh;
    private JTextField textFieldTaikhoan;
    private JTextField textFieldMatkhau;
    private JTextField textFieldChucvu;
    //private JTextField textFieldNgayvaolam;
    //private JTextField textFieldDiachi;
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
                    TaiKhoanGui frame = new TaiKhoanGui();
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
    JComboBox comboTaiKhoan;
    JLabel lbThemanh = new JLabel();
    File selectedFile;
    ImageIcon icon = new ImageIcon();
    JButton btnCapNhatAnh = new JButton();
    Object lastValueMaNv;
    JButton btnXoa = new JButton("Ẩn");
    boolean check = true;
    JButton btnThem = new JButton("Thêm");
    JButton btnLuu = new JButton("Lưu");
    JButton btnSua = new JButton("Sửa");
    int lastRow;
    JRadioButton radioSapxepten = new JRadioButton("Tên đăng nhập");
    JRadioButton radioSapxepma = new JRadioButton("Mã nhân viên");
    ButtonGroup groupSapxep = new ButtonGroup();
    ButtonGroup groupGioitinh = new ButtonGroup();
    //JRadioButton radioNam = new JRadioButton("Nam");
    //JRadioButton radioNu = new JRadioButton("Nữ");
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
    private JTextField txtSearch;

    
    public void resetValue() {
    	textFieldTennv.setText("");
    	textFieldChucvu.setText("");
        textFieldTaikhoan.setText("");
        textFieldMatkhau.setText("");
        btnThem.setEnabled(true);
        btnXoa.setEnabled(false);
        btnLuu.setEnabled(false);
        btnSua.setEnabled(false);

    }

    public void unSetEnable() {
        textFieldTaikhoan.setEnabled(true);
        textFieldMatkhau.setEnabled(true);
        comboTaiKhoan.setEnabled(true);
        btnThem.setEnabled(true);
        btnXoa.setEnabled(false);
        btnLuu.setEnabled(false);
    }

    public void setEnable() {
        textFieldTennv.setEnabled(false);
        textFieldTaikhoan.setEnabled(false);
        textFieldMatkhau.setEnabled(false);
        comboTaiKhoan.setEnabled(false);
    }
    
    public void hienthitaikhoan(String condition) throws SQLException {
        TaiKhoanBLL tkBll = new TaiKhoanBLL();
        ArrayList<TaiKhoan> arrTk = new ArrayList<TaiKhoan>();
        if (condition == "hien thi") {

            arrTk = tkBll.getTaiKhoan("doctaikhoan");
        }
        if (condition == "sapxeptheoten") {
            arrTk = tkBll.getTaiKhoan("sapxeptheoten");
        }
        if (condition == "sapxeptheoma") {
            arrTk = tkBll.getTaiKhoan("sapxeptheoma");
        }
        if (condition == "them") {

            arrTk = tkBll.getTaiKhoan("doctaikhoan");
            NhanVienBLL test = new NhanVienBLL();
            ArrayList<NhanVien> arr = test.getNhanVien("docnhanvien");
            DefaultComboBoxModel combonv = new DefaultComboBoxModel();
            comboTaiKhoan.setModel(combonv);
            for (NhanVien manv : arr) {
            	boolean flag = true;
            	for(TaiKhoan tkdata: arrTk) {
            		if(manv.getMaNv().equals(tkdata.getMaNV())) {
            			flag = false;
            		}
            	}
            	if(flag) {
                    combonv.addElement(manv.getMaNv());                    
            	}
            }
            if(comboTaiKhoan.getSelectedItem() == null) {
            	resetValue();
            	setEnable();
            	JOptionPane.showMessageDialog(contentPane, "Tất cả nhân viên đều đã có tài khoản");
            }
        }

        String[] columnNames = {"Mã Nhân Viên","Tên Nhân Viên", "Tên Đăng Nhập", "Mật Khẩu", "Quyền"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        table.setModel(model);
        model.setRowCount(0);
        for (TaiKhoan tkdata : arrTk) {
        	NhanVienBLL nv = new NhanVienBLL();
            
            Object[] row = new Object[]{tkdata.getMaNV(), nv.getTenNV(tkdata.getMaNV()), tkdata.getTenDangNhap(),
            		tkdata.getMatKhau(), tkdata.getQuyen()};

            model.addRow(row);
        }

        lastRow = table.getRowCount() - 1; // get index of the last row
        lastValueMaNv = table.getValueAt(lastRow, 0); // get the value at the last row and column n

    }

    	public Boolean checkEmtyValue() throws SQLException {
    		if(textFieldTaikhoan.getText().isEmpty()) {
    			JOptionPane.showMessageDialog(contentPane, "Tài khoản rỗng!");
                textFieldTaikhoan.requestFocus();
                return false;
    		} else {
    			TaiKhoanBLL tkb = new TaiKhoanBLL();
    			ArrayList<TaiKhoan> arr = new ArrayList<TaiKhoan>();
    			arr = tkb.getTaiKhoan("doctaikhoan");
    			boolean flag = true;
    			for(TaiKhoan data: arr) {
					if (data.getTenDangNhap().equalsIgnoreCase(textFieldTaikhoan.getText()) && !data.getMaNV().equals(comboTaiKhoan.getSelectedItem().toString())) {
						flag = false;
					}
					if (data.getTenDangNhap().equalsIgnoreCase(textFieldTaikhoan.getText()) && data.getMaNV().equals(comboTaiKhoan.getSelectedItem().toString())) {
	    				flag = true;
	    				break;
					}
    			}
    			if(!flag) {
					JOptionPane.showMessageDialog(contentPane, "Tài khoản đã tồn tại!");
					textFieldTaikhoan.requestFocus();
					textFieldTaikhoan.selectAll();
					return false;
    			}
    		}   			
    		if(textFieldTaikhoan.getText().length()>10) {
            	JOptionPane.showMessageDialog(contentPane, "Tài khoản tối đa 10 kí tự");
                textFieldTaikhoan.requestFocus();
                return false;
            } else {
            	Pattern reg = Pattern.compile("^[A-Z]+$");
                boolean kt = reg.matcher(textFieldTaikhoan.getText()).matches();
                if(kt == false) {
                    JOptionPane.showMessageDialog(contentPane, "Tài khoản chỉ được chứa chữ in hoa");
                    textFieldTaikhoan.requestFocus();
                    return false;
                }
            }
            if (textFieldMatkhau.getText().isEmpty()) {
				JOptionPane.showMessageDialog(contentPane, "Mật khẩu rỗng!");
				textFieldMatkhau.requestFocus();
				return false;
            } else if(textFieldMatkhau.getText().length() > 5) {
            	JOptionPane.showMessageDialog(contentPane, "Mật khẩu tối đa 5 kí tự!");
				textFieldMatkhau.requestFocus();
				return false;
            } else {
            	Pattern reg = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{1,5})");
                boolean kt = reg.matcher(textFieldMatkhau.getText()).matches();
                if(kt == false) {
                    JOptionPane.showMessageDialog(contentPane, "Mật khẩu phải chứa ít nhất 1 số, 1 kí tự hoa, 1 kí tự thường, 1 kí tự đặc biệt: @#$%");
                    textFieldMatkhau.requestFocus();
                    return false;
                }
            }
		return true;
	}
     
    public TaiKhoanGui() throws SQLException {

        setTitle("Quản lý tài khoản");
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
        tabbedPane.addTab("Thông tin tài khoản", null, panel_2, null);

        JPanel panel_5 = new JPanel();
        panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_5.setBounds(0, 10, 1067, 200);

        JLabel lblNewLabel_1 = new JLabel("Tên nhân viên");
        lblNewLabel_1.setBounds(20, 10, 100, 25);

        textFieldTennv = new JTextField();
        textFieldTennv.setBounds(130, 10, 200, 25);
        textFieldTennv.setEnabled(false);
        textFieldTennv.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Mã Nhân viên");
        lblNewLabel_3.setBounds(20, 50, 100, 25);
        
        comboTaiKhoan = new JComboBox();
        comboTaiKhoan.setEnabled(false);
        comboTaiKhoan.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		NhanVienBLL test;
        		try {
        			test = new NhanVienBLL();
        			textFieldTennv.setText(test.getTenNV(comboTaiKhoan.getSelectedItem().toString()));
        			textFieldChucvu.setText(test.getTenCV(test.getChucVuNV(comboTaiKhoan.getSelectedItem().toString())));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        comboTaiKhoan.setBounds(130, 50, 200, 25);
		
		JLabel lblNewLabel_9 = new JLabel("Chức vụ");
		lblNewLabel_9.setBounds(20, 90, 100, 25);
		
		textFieldChucvu = new JTextField();
		textFieldChucvu.setBounds(130, 90, 200, 25);
		textFieldChucvu.setEnabled(false);
		textFieldChucvu.setColumns(10);

		/*
		 * textFieldTennv = new JTextField(); textFieldTennv.setBounds(130, 50, 200,
		 * 25); textFieldTennv.setEnabled(false); textFieldTennv.setColumns(10);
		 * 
		 * JLabel lblNewLabel_4 = new JLabel("Ngày sinh"); lblNewLabel_4.setBounds(20,
		 * 90, 100, 25);
		 * 
		 * textFieldNgaysinh = new JTextField(); textFieldNgaysinh.setBounds(130, 90,
		 * 200, 25); textFieldNgaysinh.setEnabled(false);
		 * textFieldNgaysinh.setColumns(10);
		 * 
		 * JLabel lblNewLabel_6 = new JLabel("Giới tính"); lblNewLabel_6.setBounds(20,
		 * 130, 100, 25);
		 * 
		 * radioNam.setBounds(130, 130, 100, 25); radioNu.setBounds(130, 160, 100, 25);
		 * groupGioitinh.add(radioNam); groupGioitinh.add(radioNu);
		 * radioNam.setEnabled(false); radioNu.setEnabled(false);
		 */

        JLabel lblNewLabel_7 = new JLabel("Tài khoản");
        lblNewLabel_7.setBounds(350, 10, 100, 25);

        textFieldTaikhoan = new JTextField();
        textFieldTaikhoan.setBounds(460, 10, 200, 25);
        textFieldTaikhoan.setEnabled(false);
        textFieldTaikhoan.setColumns(10);

        JLabel lblNewLabel_8 = new JLabel("Mật khẩu");
        lblNewLabel_8.setBounds(350, 50, 100, 25);

        textFieldMatkhau = new JTextField();
        textFieldMatkhau.setBounds(460, 50, 200, 25);
        textFieldMatkhau.setEnabled(false);
        textFieldMatkhau.setColumns(10);
		  
			/*
			 * textFieldNgayvaolam = new JTextField(); textFieldNgayvaolam.setBounds(460,
			 * 90, 200, 25); textFieldNgayvaolam.setEnabled(false);
			 * textFieldNgayvaolam.setColumns(10);
			 * 
			 * JLabel lblNewLabel_13 = new JLabel("Địa chỉ"); lblNewLabel_13.setBounds(680,
			 * 10, 100, 25);
			 * 
			 * textFieldDiachi = new JTextField(); textFieldDiachi.setBounds(790, 10, 250,
			 * 50); textFieldDiachi.setEnabled(false); textFieldDiachi.setColumns(10);
			 * 
			 * textFieldTaikhoan = new JTextField(); textFieldTaikhoan.setBounds(460, 130,
			 * 200, 25); textFieldTaikhoan.setEnabled(false);
			 * textFieldTaikhoan.setColumns(10);
			 * 
			 * JLabel lblNewLabel_12 = new JLabel("Mật khẩu"); lblNewLabel_12.setBounds(350,
			 * 170, 100, 25);
			 * 
			 * textFieldMatkhau = new JTextField(); textFieldMatkhau.setBounds(460, 170,
			 * 200, 25); textFieldMatkhau.setEnabled(false);
			 * textFieldMatkhau.setColumns(10);
			 */
		 

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
							int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn thêm tài khoản cho nhân viên "+comboTaiKhoan.getSelectedItem().toString(), "Confirmation",
									JOptionPane.YES_NO_OPTION);
							if (confirmed == JOptionPane.YES_OPTION) {
								TaiKhoan tk = new TaiKhoan();
								TaiKhoanBLL luutk;
								try {
									luutk = new TaiKhoanBLL();
									tk.setMaNV(comboTaiKhoan.getSelectedItem().toString());
									tk.setTenDangNhap(textFieldTaikhoan.getText());
									tk.setMatKhau(textFieldMatkhau.getText());
									tk.setQuyen(luutk.getMaQuyen(textFieldChucvu.getText()));
									boolean checkAddPro = luutk.addTaiKhoan(tk);
									if (checkAddPro) {
										JOptionPane.showMessageDialog(contentPane, "Thêm thành công");
										resetValue();                                                                   
										setEnable();
										hienthitaikhoan("hien thi");
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
							int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn sửa tài khoản cho nhân viên "+comboTaiKhoan.getSelectedItem().toString(), "Confirmation",
									JOptionPane.YES_NO_OPTION);
							if (confirmed == JOptionPane.YES_OPTION) {
								TaiKhoan tk = new TaiKhoan();
								TaiKhoanBLL luutk;
								try {
									luutk = new TaiKhoanBLL();
									tk.setMaNV(comboTaiKhoan.getSelectedItem().toString());
									tk.setTenDangNhap(textFieldTaikhoan.getText());
									tk.setMatKhau(textFieldMatkhau.getText());
									tk.setQuyen(luutk.getMaQuyen(textFieldChucvu.getText()));
									boolean checkAddPro = luutk.fixTaiKhoan(tk);
									if (checkAddPro) {
										JOptionPane.showMessageDialog(contentPane, "Sửa thành công");
										resetValue();                                                                   
										setEnable();
										hienthitaikhoan("hien thi");
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
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnThem.setBounds(124, 10, 104, 53);
        btnThem.setFocusPainted(false);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                resetValue();
                unSetEnable();
                btnSua.setEnabled(false);
                btnThem.setEnabled(false);
                btnLuu.setEnabled(true);
                btnXoa.setEnabled(false);
                addbtn = true;
                try {
                    hienthitaikhoan("them");
                } catch (SQLException e3) {
                    // TODO Auto-generated catch block
                    e3.printStackTrace();
                }                   
			}
		});
        btnThem.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Add.png"))));
        btnXoa.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        btnXoa.setBounds(466, 10, 104, 53);

        btnXoa.setEnabled(false);
        btnXoa.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Delete.png"))));
        btnXoa.setFocusPainted(false);
    	btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					NhanVienBLL testnv = new NhanVienBLL();
					boolean flag = true;
					ArrayList<NhanVien> arr = testnv.getNhanVien("docnhanvien");
					for (NhanVien nv : arr) {
						if(nv.getMaNv().equals(comboTaiKhoan.getSelectedItem().toString()) && nv.getTinhTrang().equals("1")) {
							flag = false;
						}
					}
					if(flag) {
						int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn ẩn tài khoản "+comboTaiKhoan.getSelectedItem().toString(), "Confirmation",
								JOptionPane.YES_NO_OPTION);
						if (confirmed == JOptionPane.YES_OPTION) {
							TaiKhoanBLL hideTk;
							hideTk = new TaiKhoanBLL();
							if (hideTk.hideTaiKhoan(comboTaiKhoan.getSelectedItem().toString())) {
								JOptionPane.showMessageDialog(contentPane, "Ẩn thành công!");
								hienthitaikhoan("hien thi");
								resetValue();
								setEnable();
							}
						}
					} else {
		            	setEnable();
		            	JOptionPane.showMessageDialog(contentPane, "Không thể ẩn tài khoản này!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		});

        JPanel panel_7 = new JPanel();
        panel_7.setBounds(697, 10, 370, 53);
        panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));

        JButton btnDongBo = new JButton("");
        btnDongBo.setBounds(238, 10, 104, 53);
        btnDongBo.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(TaiKhoanGui.class.getResource(".\\Image\\Refresh-icon.png"))));
        		btnDongBo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					resetValue();
					setEnable();
					hienthitaikhoan("hien thi");
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
				if (radioSapxepten.isSelected()) {				
					try {
						hienthitaikhoan("sapxeptheoten");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				if (radioSapxepma.isSelected()) {					
					try {
						hienthitaikhoan("sapxeptheoma");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				resetValue();
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

        lbThongbao.setText("DANH SÁCH TÀI KHOẢN");

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
				String tk = table.getModel().getValueAt(row, 2).toString(); // get the value of the second column
				String mk = table.getModel().getValueAt(row, 3).toString();
				String role = table.getModel().getValueAt(row, 4).toString();

				textFieldTennv.setText(tenNV);
				textFieldTaikhoan.setText(tk);
				textFieldMatkhau.setText(mk);
				setEnable();
				btnXoa.setEnabled(true);
				btnThem.setEnabled(true);
				btnLuu.setEnabled(false);
				btnSua.setEnabled(true);
				NhanVienBLL testnv;
				TaiKhoanBLL testtk;
				try {
					testnv = new NhanVienBLL();
					testtk = new TaiKhoanBLL();
	                
	                DefaultComboBoxModel combonv = new DefaultComboBoxModel();
	                comboTaiKhoan.setModel(combonv);
	                combonv.addElement(maNV);
					String chucVu = testnv.getChucVuNV(maNV);
					textFieldChucvu.setText(testnv.getTenCV(chucVu));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

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
        panel_5.add(lblNewLabel_1);
        panel_5.add(textFieldTennv);
        panel_5.add(lblNewLabel_7);
        panel_5.add(comboTaiKhoan);
        panel_5.add(textFieldTaikhoan);
        panel_5.add(lblNewLabel_3);
        panel_5.add(lblNewLabel_8);
        panel_5.add(textFieldMatkhau);
        panel_5.add(lblNewLabel_9);
        panel_5.add(textFieldChucvu);	
        /*
		 * panel_5.add(lblNewLabel_6); panel_5.add(radioNam); panel_5.add(radioNu);
		 * panel_5.add(lblNewLabel_4); panel_5.add(textFieldTennv);
		 * panel_5.add(textFieldNgaysinh); panel_5.add(textFieldNgayvaolam);
		 * panel_5.add(lblNewLabel_13); panel_5.add(textFieldDiachi);
		 * panel_5.add(textFieldTaikhoan); panel_5.add(lblNewLabel_12);
		 * panel_5.add(textFieldMatkhau);
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
                    TaiKhoanDAL tknv;
                    ArrayList<TaiKhoan> arrPro = new ArrayList<TaiKhoan>(); // TODO Auto-generated catch block
                    arrPro = tknv.doctaikhoan("timkiem",null);
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
        		 AdminHome home=new AdminHome();
                 setVisible(false);
              
                 home.setVisible(true);
                 home.setLocationRelativeTo(null);
        	}
        });
        btnNewButton.setBounds(938, 153, 119, 39);
        panel_5.add(btnNewButton);
        
        txtSearch = new JTextField();
        txtSearch.setBounds(130, 163, 205, 27);
        panel_5.add(txtSearch);
        txtSearch.setColumns(10);
        txtSearch.setText("Tìm kiếm theo tên nhân viên");
        
        JButton btnSearch = new JButton("Tìm kiếm");
        btnSearch.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			hienthitaikhoan("hien thi");
            		ArrayList<TaiKhoan> data = new ArrayList<>();
    				// Get the number of rows and columns in the JTable
    				int numRows = table.getRowCount();
    				// Loop through each row in the JTable and add the data to the ArrayList
    				for (int i = 0; i < numRows; i++) {
    					TaiKhoan tk = new TaiKhoan();
    					tk.setMaNV((String) table.getValueAt(i, 0));
    					tk.setTenDangNhap((String) table.getValueAt(i, 2));
    					tk.setMatKhau((String) table.getValueAt(i, 3));
    					tk.setQuyen((String) table.getValueAt(i, 4));
    					data.add(tk);
    				}		
    				
    				
					String[] columnNames = {"Mã Nhân Viên","Tên Nhân Viên", "Tên Đăng Nhập", "Mật Khẩu", "Quyền"};
			        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

			        table.setModel(model);
			        model.setRowCount(0);
    				if(!txtSearch.getText().isEmpty()) {
    			        for (TaiKhoan tkdata : data) {
    			        	NhanVienBLL nv = new NhanVienBLL();
    			        	if (nv.getTenNV(tkdata.getMaNV()).toLowerCase().contains(txtSearch.getText().toLowerCase()))
 {
        			        	
        			            Object[] row = new Object[]{tkdata.getMaNV(), nv.getTenNV(tkdata.getMaNV()), tkdata.getTenDangNhap(),
        			            		tkdata.getMatKhau(), tkdata.getQuyen()};

        			            model.addRow(row);
    			        	}
    			        }
    				} else {
    					try {
    						hienthitaikhoan("hien thi");
    					} catch (SQLException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					}
    				}
    				if(table.getRowCount() == 0) {
    					JOptionPane.showMessageDialog(contentPane, "Không tìm thấy tài khoản!");
    				}
        		} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		resetValue();
        		txtSearch.setText("");
        	}
        });
        btnSearch.setBounds(20, 162, 100, 28);
        panel_5.add(btnSearch);
        panel_2.add(panel_6);
        
        btnSua.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                unSetEnable();
                btnSua.setEnabled(false);
                btnThem.setEnabled(false);
                btnLuu.setEnabled(true);
                btnXoa.setEnabled(false);
                fixbtn = true;
        	}
        });
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSua.setFocusPainted(false);
        btnSua.setEnabled(false);
        btnSua.setBounds(352, 10, 104, 53);
        btnSua.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Change.png"))));
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

        JLabel lblNewLabel = new JLabel("QUẢN LÝ TÀI KHOẢN");
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
        hienthitaikhoan("hien thi");

    }
}
