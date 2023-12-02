package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.DangNhapBLL;
import DTO.NhanVien;
import DTO.TaiKhoan;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;

public class LoginGui extends JFrame {

	private JPanel contentPane;
	
	JTextField textField = new JTextField();
	JPasswordField textField_1 = new JPasswordField();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGui frame = new LoginGui();
					// Set the location of the frame to the center of the screen
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public LoginGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 509, 306);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 479, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
					.addGap(4))
		);
		
		
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tên Đăng Nhập:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		
		JLabel lblNewLabel_2 = new JLabel("");
//		new ImageIcon("QuanLiSieuThiMini\\src\\GUI\\Image\\User.png")
		lblNewLabel_2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\User.png"))));
		

		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Mật Khẩu :");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 14));
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Key.png"))));
		
		JButton btnNewButton_1 = new JButton("Đăng Nhập");
		btnNewButton_1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty() || textField_1.getText().isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Chưa nhập tài khoản hoặc mật khẩu");
				}
				else {
					DangNhapBLL dn = new DangNhapBLL();
					TaiKhoan tk = new TaiKhoan();
					try {
						
						tk = dn.checkLogin(textField.getText(), textField_1.getText());
						if(tk.getTenDangNhap().equals("Mật khẩu không chính xác, vui lòng kiểm tra lại!")==false && tk.getTenDangNhap().equals("Tài Khoản không tồn tại, vui lòng kiểm tra lại!")==false) {
							if(tk.getQuyen().equals("RL1")) {
								AdminHome admin=new AdminHome();
					            setVisible(false);
					            DangNhapBLL.taiKhoan = tk;
					            
					            admin.setVisible(true);
								admin.setLocationRelativeTo(null);
							}
							else if(tk.getQuyen().equals("RL2")) {
							
								QuanLyHome home = new QuanLyHome();
								setVisible(false);
								DangNhapBLL.taiKhoan = tk;
							
								home.setVisible(true);
								home.setLocationRelativeTo(null);
							}
							else if(tk.getQuyen().equals("RL3")) {
								
								NhanVienBanHangHome home = new NhanVienBanHangHome();
								setVisible(false);
								DangNhapBLL.taiKhoan = tk;
							
								home.setVisible(true);
								home.setLocationRelativeTo(null);
							}
							else if(tk.getQuyen().equals("RL4")) {
								
								NhanVienKhoHome home = new NhanVienKhoHome();
								setVisible(false);
								DangNhapBLL.taiKhoan = tk;
							
								home.setVisible(true);
								home.setLocationRelativeTo(null);
							}
						}
						else {
							JOptionPane.showMessageDialog(contentPane,tk.getTenDangNhap());
						};
						
						
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Login.png"))));
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JButton btnNewButton_2 = new JButton("Thoát");
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.setFont(new Font("Arial", Font.PLAIN, 13));
		btnNewButton_2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Exit mini.png"))));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int lick=JOptionPane.showConfirmDialog(null,"Bạn Có Muốn Thoát Khỏi Chương Trình Hay Không?","Thông Báo",2);
			        if(lick==JOptionPane.OK_OPTION){
			            System.exit(0);
			        }
			        else{
			            if(lick==JOptionPane.CANCEL_OPTION){    
			               setVisible(true);
			            }
			        }
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(10)
									.addComponent(lblNewLabel_4)))
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(18)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
									.addGap(34)))
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(textField)
								.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewButton_1)
							.addGap(18)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(21) 
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addGap(23))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("ĐĂNG NHẬP VÀO HỆ THỐNG");
		lblNewLabel.setBackground(new Color(128, 128, 128));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		panel.add(lblNewLabel);
		contentPane.setLayout(gl_contentPane);
		
	}
}

