package entity;

public class DaiLyCon {
    private int id;
    private String maDL;
    private String tenDL;
    private String diaChi;
    private String soDienThoai;

    public DaiLyCon() {}

    public DaiLyCon(int id, String maDL, String tenDL, String diaChi, String soDienThoai) {
        this.id = id;
        this.maDL = maDL;
        this.tenDL = tenDL;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMaDL() { return maDL; }
    public void setMaDL(String maDL) { this.maDL = maDL; }

    public String getTenDL() { return tenDL; }
    public void setTenDL(String tenDL) { this.tenDL = tenDL; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }
}
