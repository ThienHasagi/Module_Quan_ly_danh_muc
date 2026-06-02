package controller;

import dao.HangHoaDAO;
import dao.NhaCungCapDAO;
import dao.DaiLyConDAO;
import entity.HangHoa;
import entity.NhaCungCap;
import entity.DaiLyCon;
import java.util.List;

public class DanhMucController {
    private HangHoaDAO hangHoaDAO;
    private NhaCungCapDAO nhaCungCapDAO;
    private DaiLyConDAO daiLyConDAO;

    public DanhMucController() {
        hangHoaDAO = new HangHoaDAO();
        nhaCungCapDAO = new NhaCungCapDAO();
        daiLyConDAO = new DaiLyConDAO();
    }

    // ================= KHU VỰC ĐIỀU KHIỂN HÀNG HÓA =================
    public List<HangHoa> timKiemHangHoa(String tuKhoa) throws Exception {
        return hangHoaDAO.timKiem(tuKhoa);
    }

    public String themHangHoa(HangHoa hh) {
        try {
            if (hangHoaDAO.kiemTraTrungMa(hh.getMaHang())) {
                return "Mã đối tượng đã tồn tại, vui lòng kiểm tra lại"; // 
            }
            return hangHoaDAO.themMoi(hh) ? "Thành công" : "Thất bại";
        } catch (Exception e) {
            return "Lỗi hệ thống: " + e.getMessage();
        }
    }

    public String capNhatHangHoa(HangHoa hh) {
        try {
            return hangHoaDAO.capNhat(hh) ? "Thành công" : "Thất bại";
        } catch (Exception e) {
            return "Lỗi hệ thống: " + e.getMessage();
        }
    }

    public String xoaHangHoa(int id) {
        try {
            if (!hangHoaDAO.kiemTraXoa(id)) {
                return "Từ chối xóa. Đối tượng hiện đang có lịch sử giao dịch trong hệ thống!"; // [cite: 39, 84]
            }
            return hangHoaDAO.xoa(id) ? "Thành công" : "Thất bại";
        } catch (Exception e) {
            return "Lỗi hệ thống: " + e.getMessage();
        }
    }

    // ================= KHU VỰC ĐIỀU KHIỂN NHÀ CUNG CẤP =================
    public List<NhaCungCap> timKiemNCC(String tuKhoa) throws Exception {
        return nhaCungCapDAO.timKiem(tuKhoa);
    }

    public String themNCC(NhaCungCap ncc) {
        try {
            if (nhaCungCapDAO.kiemTraTrungMa(ncc.getMaNCC())) {
                return "Mã đối tượng đã tồn tại, vui lòng kiểm tra lại";
            }
            return nhaCungCapDAO.themMoi(ncc) ? "Thành công" : "Thất bại";
        } catch (Exception e) {
            return "Lỗi hệ thống: " + e.getMessage();
        }
    }

    public String capNhatNCC(NhaCungCap ncc) {
        try {
            return nhaCungCapDAO.capNhat(ncc) ? "Thành công" : "Thất bại";
        } catch (Exception e) {
            return "Lỗi hệ thống: " + e.getMessage();
        }
    }

    public String xoaNCC(int id) {
        try {
            if (!nhaCungCapDAO.kiemTraXoa(id)) {
                return "Từ chối xóa. Đối tượng hiện đang có lịch sử giao dịch trong hệ thống!";
            }
            return nhaCungCapDAO.xoa(id) ? "Thành công" : "Thất bại";
        } catch (Exception e) {
            return "Lỗi hệ thống: " + e.getMessage();
        }
    }

    // ================= KHU VỰC ĐIỀU KHIỂN ĐẠI LÝ CON =================
    public List<DaiLyCon> timKiemDaiLy(String tuKhoa) throws Exception {
        return daiLyConDAO.timKiem(tuKhoa);
    }

    public String themDaiLy(DaiLyCon dl) {
        try {
            if (daiLyConDAO.kiemTraTrungMa(dl.getMaDL())) {
                return "Mã đối tượng đã tồn tại, vui lòng kiểm tra lại";
            }
            return daiLyConDAO.themMoi(dl) ? "Thành công" : "Thất bại";
        } catch (Exception e) {
            return "Lỗi hệ thống: " + e.getMessage();
        }
    }

    public String capNhatDaiLy(DaiLyCon dl) {
        try {
            return daiLyConDAO.capNhat(dl) ? "Thành công" : "Thất bại";
        } catch (Exception e) {
            return "Lỗi hệ thống: " + e.getMessage();
        }
    }

    public String xoaDaiLy(int id) {
        try {
            if (!daiLyConDAO.kiemTraXoa(id)) {
                return "Từ chối xóa. Đối tượng hiện đang có lịch sử giao dịch trong hệ thống!";
            }
            return daiLyConDAO.xoa(id) ? "Thành công" : "Thất bại";
        } catch (Exception e) {
            return "Lỗi hệ thống: " + e.getMessage();
        }
    }
}
