package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.common.collect.Table.Cell;
import com.orsoncharts.plot.PiePlot3D;
import com.toedter.calendar.JDateChooser;

import DTO.NhapHang;
import BLL.SanPhamBLL;
import BLL.ThongKeBLL;
import DAL.LoaiHangDAL;
import DAL.SanPhamDAL;
import DAL.ThongKeDAL;
import DTO.DoanhThu;
import DTO.HoaDon;
import DTO.LoaiHang;
import DTO.PhieuNhapChiTiet;
import DTO.SanPham;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale.Category;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;


import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.io.FileOutputStream;
import javax.swing.JTable;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
public class ThongKeGui extends JFrame {

	private JPanel contentPane;
	Object lastValueMaSp;
	int lastRow;
	private JTable table;
	private JTable table_1;
	JDateChooser jDateChooser1 = new JDateChooser();
	JDateChooser jDateChooser1_1 = new JDateChooser();
	JLabel lblTextMoney = new JLabel("");
	JLabel lblTextMoney_1 = new JLabel("");
	JButton btnNewButton_1_2 = new JButton("In Thống Kê");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongKeGui frame = new ThongKeGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	
	public void showTableInput(String condition) throws SQLException {
		String[] columnNames = { "Mã Phiếu Nhập", "Nhân Viên", "Nhà Cung Cấp", "Tổng Tiền", "Ngày Nhập" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		ThongKeBLL tkbll = new ThongKeBLL();
		ArrayList<NhapHang> arr = new ArrayList<NhapHang>();
		arr = tkbll.renderPurchaseOrder();
		table_1.setModel(model);
		model.setRowCount(0);
		for (NhapHang nhdata : arr) {
			
			Object[] row = new Object[] {nhdata.getMaPn(),nhdata.getMaNv(),nhdata.getMaNcc(),nhdata.getTongTien(),nhdata.getNgayNhap() };

			model.addRow(row);
		}
		JTableHeader header = table_1.getTableHeader();
		Font headerFont = header.getFont(); // get the current font of the header
		header.setFont(new Font(headerFont.getName(), Font.BOLD, 14)); // set the new font for the header with size 16
	}
	public void showTableOutput(String condition) throws SQLException {
		String[] columnNames = { "Mã Hóa Đơn", "Nhân Viên", "Khách Hàng", "Tổng Tiền", "Ngày Lập" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		ThongKeBLL tkbll = new ThongKeBLL();
		
		  ArrayList<HoaDon> arr = new ArrayList<HoaDon>();
		  
		  arr = tkbll.RenderOrders();
		  
		  table.setModel(model);
		  model.setRowCount(0); 
		  for (HoaDon hddata : arr) {			  
			  Object[] row = new Object[]{hddata.getMaHD(),hddata.getMaNV(),hddata.getMaKH(),hddata.getTongTienSauKM(),
					  hddata.getThoiDiemLap() };
			  
			  model.addRow(row); 
		  }
		 
		JTableHeader header = table.getTableHeader();
		Font headerFont = header.getFont(); // get the current font of the header
		header.setFont(new Font(headerFont.getName(), Font.BOLD, 14)); // set the new font for the header with size 16
	}
	public static void exportData(ArrayList<DoanhThu> doanhThuList, int tienDoanhThu) throws SQLException {
	    // Create a file chooser to let the user select the location to save the file
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Save Excel File");
	    int userSelection = fileChooser.showSaveDialog(null);

	    if (userSelection == JFileChooser.APPROVE_OPTION) {
	        File fileToSave = fileChooser.getSelectedFile();
	        String fileName = fileToSave.getName();
	        if (!fileName.endsWith(".xls")) {
	            fileToSave = new File(fileToSave.getParentFile(), fileName + ".xls");
	        }
	        try {
	            WritableWorkbook workbook = Workbook.createWorkbook(fileToSave);
	            WritableSheet sheet = workbook.createSheet("Sheet1", 0);
	            // Add title row
	            Label titleLabel = new Label(0, 0, "Doanh Thu");
	            WritableCellFormat titleFormat = new WritableCellFormat();
	            titleFormat.setAlignment(jxl.format.Alignment.CENTRE);
	            titleFormat.setBackground(Colour.GREY_25_PERCENT);
	            titleFormat.setFont(new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.RED));
	            
	            sheet.addCell(titleLabel);
	            sheet.mergeCells(0, 0, 1, 0);
	            titleLabel.setCellFormat(titleFormat);

	            // Export column headers to Excel file
	            Label thoiGianLabel = new Label(0, 1, "Thời Gian");
	            Label loiNhuanLabel = new Label(1, 1, "Lợi Nhuận");
	            sheet.addCell(thoiGianLabel);
	            sheet.addCell(loiNhuanLabel);

	            // Export data rows to Excel file
	            int rowCount = doanhThuList.size();
	            for (int i = 0; i < rowCount; i++) {
	                DoanhThu doanhThu = doanhThuList.get(i);
	                Label thoiGianValue = new Label(0, i + 2, String.valueOf(doanhThu.getTime()));
	                Locale locale = new Locale("vi", "VN");
	                NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
	                String money = currencyFormatter.format(doanhThu.getLoiNhuan());               
	                Label loiNhuanValue = new Label(1, i + 2, money);
	                sheet.addCell(thoiGianValue);
	                sheet.addCell(loiNhuanValue);
	                sheet.setColumnView(i, 30);
	            }
	            Label doanhThuLabel = new Label(0, rowCount+3, "Tổng Doanh Thu");
                sheet.addCell(doanhThuLabel);
              
             // Create a red font
                WritableFont font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.RED);

                // Create a cell format with the red font
                WritableCellFormat cellFormat = new WritableCellFormat(font);

                // Set the text color of the cell to red
                Locale locale = new Locale("vi", "VN");
                NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
                String moneyDoanhThu = currencyFormatter.format(tienDoanhThu);
                Label doanhThu = new Label(1, rowCount+3, moneyDoanhThu, cellFormat);

                // Add the cells to the sheet
                sheet.addCell(doanhThu);

	            workbook.write();
	            workbook.close();
	            JOptionPane.showMessageDialog(null, "Xuất Dữ Liệu Thành Công!.");
	        } catch (IOException | WriteException e) {
	            JOptionPane.showMessageDialog(null, "Error exporting list to Excel: " + e.getMessage());
	        }
	    }
	}


	public String formatDateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateFormat.format(date);
		return dateString;
	}
	public void drawChart() throws SQLException {
	    // create dataset
		ThongKeBLL tkbll = new ThongKeBLL();
		ArrayList<DoanhThu> arr = new ArrayList<DoanhThu>();
		List<String> arrTimeDup = new ArrayList<String>();
		ArrayList<HoaDon> arrhd = new ArrayList<HoaDon>();
		ArrayList<NhapHang> arrpn = new ArrayList<NhapHang>();
		java.util.Date dateFromUtil = jDateChooser1.getDate();
		java.util.Date dateToUtil = jDateChooser1_1.getDate();
		java.sql.Date dateFromSql = new java.sql.Date(dateFromUtil.getTime());
		java.sql.Date dateToSql = new java.sql.Date(dateToUtil.getTime());
		arrhd = tkbll.thongKeHD(formatDateToString(dateFromSql),formatDateToString(dateToSql));
		arrpn = tkbll.thongKePN(formatDateToString(dateFromSql),formatDateToString(dateToSql));
		for(NhapHang datapn: arrpn) {
			String timepn = datapn.getNgayNhap().substring(0,7);
			int tienNhap = Integer.parseInt(datapn.getTongTien());
			DoanhThu data = new DoanhThu();
			data.setTime(timepn);
			data.setLoiNhuan(0-tienNhap);
			arr.add(data);
		}
		for(HoaDon datahd: arrhd) {
			String timehd = datahd.getThoiDiemLap().substring(0,7);
			int tienBan = Integer.parseInt(datahd.getTongTienSauKM());
			DoanhThu data = new DoanhThu();
			data.setTime(timehd);
			data.setLoiNhuan(0+tienBan);
			arr.add(data);
		}
		for(DoanhThu data: arr) {			
			arrTimeDup.add(data.getTime());	
		}
		List<String> arrTime = arrTimeDup.stream().distinct().collect(Collectors.toList());
		ArrayList<DoanhThu> arrChart = new ArrayList<DoanhThu>();
		for(String time: arrTime) {
			DoanhThu data = new DoanhThu();
			data.setLoiNhuan(0);
			data.setTime(time);
			arrChart.add(data);
		}
		for(DoanhThu dataChart: arrChart) {
			for(DoanhThu data: arr) {
				if(dataChart.getTime().equals(data.getTime())) {
					dataChart.setLoiNhuan(dataChart.getLoiNhuan() + data.getLoiNhuan());
				}
			}
		}
		Collections.sort(arrChart, new Comparator<DoanhThu>() {
		    public int compare(DoanhThu dt1, DoanhThu dt2) {
		        return dt1.getTime().compareTo(dt2.getTime());
		    }
		});
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(DoanhThu dt: arrChart) {			
			dataset.addValue(dt.getLoiNhuan(), "Lợi nhuận", dt.getTime()); 
		}
		  
		// create chart 
		JFreeChart chart = ChartFactory.createBarChart(
		"LỢI NHUẬN SIÊU THỊ", "Thời gian", "Nghìn đồng", dataset, PlotOrientation.VERTICAL,
		false, true, false );
		 
	    
	    // create frame to display chart
	    JFrame frame = new JFrame("Chart");
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	    ChartPanel chartPanel = new ChartPanel(chart);
		frame.getContentPane().add(chartPanel);
		 
	    frame.pack();
	    frame.setVisible(true);
	    frame.setLocationRelativeTo(null);
	}
	public ThongKeGui() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1168, 726);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1130, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 1120, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 1111, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
		);
		
		
		lblTextMoney.setFont(new Font("Arial", Font.BOLD, 20));
		
		
		lblTextMoney_1.setFont(new Font("Arial", Font.BOLD, 20));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(70)
					.addComponent(lblTextMoney, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
					.addGap(191)
					.addComponent(lblTextMoney_1, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
					.addGap(128))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTextMoney_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTextMoney, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Thống kê từ ngày");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 12));
		
		
		jDateChooser1.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("Đến ngày");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 12));
		
		JButton btnNewButton_1_1 = new JButton("Thống Kê");
		btnNewButton_1_1.setFont(new Font("Arial", Font.BOLD, 12));
		btnNewButton_1_1.setIcon(new ImageIcon(ThongKeGui.class.getResource("/GUI/Image/Revenue.png")));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					if(jDateChooser1.getDate()== null || jDateChooser1_1.getDate()==null) {
						JOptionPane.showMessageDialog(contentPane, "Ngày thống kê trống!");				
					} else if(jDateChooser1.getDate().compareTo(jDateChooser1_1.getDate()) >0) {
						JOptionPane.showMessageDialog(contentPane, "Ngày thống kê không hợp lệ!");
					} else {											
						drawChart();
						btnNewButton_1_2.setEnabled(true);
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		btnNewButton_1_2.setEnabled(false);
		btnNewButton_1_2.setFont(new Font("Arial", Font.BOLD, 12));
		btnNewButton_1_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				


				ThongKeBLL tkbll = new ThongKeBLL();
				try {
					ArrayList<DoanhThu> arr = new ArrayList<DoanhThu>();
					List<String> arrTimeDup = new ArrayList<String>();
					ArrayList<HoaDon> arrhd = new ArrayList<HoaDon>();
					ArrayList<NhapHang> arrpn = new ArrayList<NhapHang>();
					java.util.Date dateFromUtil = jDateChooser1.getDate();
					java.util.Date dateToUtil = jDateChooser1_1.getDate();
					java.sql.Date dateFromSql = new java.sql.Date(dateFromUtil.getTime());
					java.sql.Date dateToSql = new java.sql.Date(dateToUtil.getTime());
					arrhd = tkbll.thongKeHD(formatDateToString(dateFromSql),formatDateToString(dateToSql));
					arrpn = tkbll.thongKePN(formatDateToString(dateFromSql),formatDateToString(dateToSql));
					for(NhapHang datapn: arrpn) {
						String timepn = datapn.getNgayNhap().substring(0,7);
						int tienNhap = Integer.parseInt(datapn.getTongTien());
						DoanhThu data = new DoanhThu();
						data.setTime(timepn);
						data.setLoiNhuan(0-tienNhap);
						arr.add(data);
					}
					for(HoaDon datahd: arrhd) {
						String timehd = datahd.getThoiDiemLap().substring(0,7);
						int tienBan = Integer.parseInt(datahd.getTongTienSauKM());
						DoanhThu data = new DoanhThu();
						data.setTime(timehd);
						data.setLoiNhuan(0+tienBan);
						arr.add(data);
					}
					for(DoanhThu data: arr) {			
						arrTimeDup.add(data.getTime());	
					}
					List<String> arrTime = arrTimeDup.stream().distinct().collect(Collectors.toList());
					ArrayList<DoanhThu> arrChart = new ArrayList<DoanhThu>();
					for(String time: arrTime) {
						DoanhThu data = new DoanhThu();
						data.setLoiNhuan(0);
						data.setTime(time);
						arrChart.add(data);
					}
					for(DoanhThu dataChart: arrChart) {
						for(DoanhThu data: arr) {
							if(dataChart.getTime().equals(data.getTime())) {
								dataChart.setLoiNhuan(dataChart.getLoiNhuan() + data.getLoiNhuan());
							}
						}
					}
					Collections.sort(arrChart, new Comparator<DoanhThu>() {
					    public int compare(DoanhThu dt1, DoanhThu dt2) {
					        return dt1.getTime().compareTo(dt2.getTime());
					    }
					});
					int tongDoanhThu = 0;
					for(DoanhThu data: arrChart) {			
						tongDoanhThu += data.getLoiNhuan();
					}
					exportData(arrChart, tongDoanhThu);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				int tienChiPhi;
				/*
				 * try { tienChiPhi = getChiPhi(malh); int tienDoanhThu = getDoanhThu(malh);
				 * exportData(arr,tienChiPhi,tienDoanhThu); } catch (SQLException e2) { // TODO
				 * Auto-generated catch block e2.printStackTrace(); }
				 */
				
			}
		});
		btnNewButton_1_2.setIcon(new ImageIcon(ThongKeGui.class.getResource("/GUI/Image/Print Sale.png")));
		
		JPanel panel_3 = new JPanel();
		
		JPanel panel_3_1 = new JPanel();
		
		
		
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jDateChooser1, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jDateChooser1_1, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
							.addGap(40)
							.addComponent(btnNewButton_1_1, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnNewButton_1_2, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
							.addGap(49))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(panel_3_1, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addContainerGap()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnNewButton_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnNewButton_1_2, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(29)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(jDateChooser1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
										.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jDateChooser1_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))))
							.addGap(45))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(24)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_3_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JLabel lblNewLabel_7_1 = new JLabel("PHIẾU NHẬP");
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1.setFont(new Font("Arial", Font.BOLD, 20));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel_3_1 = new GroupLayout(panel_3_1);
		gl_panel_3_1.setHorizontalGroup(
			gl_panel_3_1.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel_7_1, GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
		);
		gl_panel_3_1.setVerticalGroup(
			gl_panel_3_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3_1.createSequentialGroup()
					.addComponent(lblNewLabel_7_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))
		);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 Phi\u1EBFu Nh\u1EADp", "M\u00E3 S\u1EA3n Ph\u1EA9m", "S\u1ED1 L\u01B0\u1EE3ng", "Ng\u00E0y Nh\u1EADp"
			}
		));
		scrollPane_1.setViewportView(table_1);
		panel_3_1.setLayout(gl_panel_3_1);
		
		JLabel lblNewLabel_7 = new JLabel("HÓA ĐƠN");
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
						.addComponent(lblNewLabel_7, GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 Phi\u1EBFu Xu\u1EA5t", "M\u00E3 S\u1EA3n Ph\u1EA9m", "S\u1ED1 L\u01B0\u1EE3ng B\u00E1n", "Ng\u00E0y Xu\u1EA5t"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(83);
		scrollPane.setViewportView(table);
		panel_3.setLayout(gl_panel_3);
		
		
		
		
		
		
		panel_1.setLayout(gl_panel_1);
		
		JButton btnNewButton = new JButton("Hệ Thống");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				QuanLyHome hnv = new QuanLyHome();
				hnv.setLocationRelativeTo(null);
				hnv.setVisible(true);
			}
		});
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
		JLabel lblNewLabel = new JLabel("THỐNG KÊ DOANH THU");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addGap(179)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 543, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(223, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		showTableInput("0");
		showTableOutput("0");
	}
}
