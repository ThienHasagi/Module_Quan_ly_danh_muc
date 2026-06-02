package entity;

public class HangHoa {
    private int id; // Khóa chính tự tăng mức vật lý
    private String maHang; // Mã định danh nghiệp vụ (Unique)
    private String tenHang;
    private String moTa;
    private int soLuongTon; // Giá trị khởi tạo mặc định bằng 0

    public HangHoa() {
        this.soLuongTon = 0; // Mặc định gán bằng 0 khi thêm mới
    }

    public HangHoa(int id, String maHang, String tenHang, String moTa, int soLuongTon) {
        this.id = id;
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.moTa = moTa;
        this.soLuongTon = soLuongTon;
    }

    // Encapsulation: Getters và Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMaHang() { return maHang; }
    public void setMaHang(String maHang) { this.maHang = maHang; }

    public String getTenHang() { return tenHang; }
    public void setTenHang(String tenHang) { this.tenHang = tenHang; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public int getSoLuongTon() { return soLuongTon; }
    public void setSoLuongTon(int soLuongTon) { this.soLuongTon = soLuongTon; }
}
