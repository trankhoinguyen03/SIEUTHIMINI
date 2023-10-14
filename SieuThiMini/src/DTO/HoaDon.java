
package DTO;

public class HoaDon {
    private int MaHd,MaNv,MaKh,isDeleted;
    private float TienTra,MucGiam,DiemThuong;
    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
    private String ThoiDiem,Gio;

    public int getMaHd() {
        return MaHd;
    }

    public void setMaHd(int MaHd) {
        this.MaHd = MaHd;
    }

    public float getTienTra() {
        return TienTra;
    }

    public void setTienTra(float TienTra) {
        this.TienTra = TienTra;
    }

    public float getMucGiam() {
        return MucGiam;
    }

    public void setMucGiam(float MucGiam) {
        this.MucGiam = MucGiam;
    }

    public float getDiemThuong() {
        return DiemThuong;
    }

    public void setDiemThuong(float DiemThuong) {
        this.DiemThuong = DiemThuong;
    }

    public int getMaNv() {
        return MaNv;
    }

    public void setMaNv(int MaNv) {
        this.MaNv = MaNv;
    }

    public int getMaKh() {
        return MaKh;
    }

    public void setMaKh(int MaKh) {
        this.MaKh = MaKh;
    }

    public String getThoiDiem() {
        return ThoiDiem;
    }

    public void setThoiDiem(String ThoiDiem) {
        this.ThoiDiem = ThoiDiem;
    }

    public String getGio() {
        return Gio;
    }

    public void setGio(String Gio) {
        this.Gio = Gio;
    }
   
}
