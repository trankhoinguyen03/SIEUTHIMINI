package DTO;

public class NhanVien {
private int MaNv,ChucVu;
private String HoTen,NgaySinh,DiaChi,CMND,DienThoai,NgayVaoLam,TaiKhoan,MatKhau,img,Email;
private boolean GioiTinh;
public boolean isGioiTinh() {
	return GioiTinh;
}
public void setGioiTinh(boolean gioiTinh) {
	GioiTinh = gioiTinh;
}
public int getMaNv() {
	return MaNv;
}
public void setMaNv(int maNv) {
	MaNv = maNv;
}
public int getChucVu() {
	return ChucVu;
}
public void setChucVu(int chucVu) {
	ChucVu = chucVu;
}
public String getHoTen() {
	return HoTen;
}
public void setHoTen(String hoTen) {
	HoTen = hoTen;
}
public String getNgaySinh() {
	return NgaySinh;
}
public void setNgaySinh(String ngaySinh) {
	NgaySinh = ngaySinh;
}
public String getDiaChi() {
	return DiaChi;
}
public void setDiaChi(String diaChi) {
	DiaChi = diaChi;
}
public String getCMND() {
	return CMND;
}
public void setCMND(String cMND) {
	CMND = cMND;
}
public String getDienThoai() {
	return DienThoai;
}
public void setDienThoai(String dienThoai) {
	DienThoai = dienThoai;
}
public String getNgayVaoLam() {
	return NgayVaoLam;
}
public void setNgayVaoLam(String ngayVaoLam) {
	NgayVaoLam = ngayVaoLam;
}
public String getTaiKhoan() {
	return TaiKhoan;
}
public void setTaiKhoan(String taiKhoan) {
	TaiKhoan = taiKhoan;
}
public String getMatKhau() {
	return MatKhau;
}
public void setMatKhau(String matKhau) {
	MatKhau = matKhau;
}
public String getImg() {
	return img;
}
public void setImg(String img) {
	this.img = img;
}
public String getEmail() {
	return Email;
}
public void setEmail(String email) {
	Email = email;
}

}
