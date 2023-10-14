package DTO;

public class SanPham {
	private int maSp,maLh,isDeleted;
	private float giaMua,giaBan;
	private String tenSp,hanSuDung,img,donVi;
	public int getMaSp() {
		return maSp;
	}
	public void setMaSp(int maSp) {
		this.maSp = maSp;
	}
	public float getGiaMua() {
		return giaMua;
	}
	public void setGiaMua(float giaMua) {
		this.giaMua = giaMua;
	}
	public float getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(float giaBan) {
		this.giaBan = giaBan;
	} 
	public int getMaLh() {
		return maLh;
	}
	public void setMaLh(int maLh) {
		this.maLh = maLh;
	}
	public String getDonVi() {
		return donVi;
	}
	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getTenSp() {
		return tenSp;
	}
	public void setTenSp(String tenSp) {
		this.tenSp = tenSp;
	}
	public String getHanSuDung() {
		return hanSuDung;
	}
	public void setHanSuDung(String hanSuDung) {
		this.hanSuDung = hanSuDung;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	

}
