package dao;

import entity.HangHoa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HangHoaDAO extends BaseDAO {

    public List<HangHoa> timKiem(String tuKhoa) throws Exception {
        List<HangHoa> list = new ArrayList<>();
        String sql = "SELECT * FROM tblHangHoa WHERE maHang LIKE ? OR tenHang LIKE ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + tuKhoa + "%");
            ps.setString(2, "%" + tuKhoa + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new HangHoa(
                        rs.getInt("id"),
                        rs.getString("maHang"),
                        rs.getString("tenHang"),
                        rs.getString("moTa"),
                        rs.getInt("soLuongTon")
                    ));
                }
            }
        }
        return list;
    }

    public boolean kiemTraTrungMa(String maHang) throws Exception {
        String sql = "SELECT COUNT(*) FROM tblHangHoa WHERE maHang = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maHang);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    public boolean themMoi(HangHoa hh) throws Exception {
        String sql = "INSERT INTO tblHangHoa (maHang, tenHang, moTa, soLuongTon) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hh.getMaHang());
            ps.setString(2, hh.getTenHang());
            ps.setString(3, hh.getMoTa());
            ps.setInt(4, hh.getSoLuongTon()); // Sẽ ghi nhận giá trị mặc định bằng 0
            return ps.executeUpdate() > 0;
        }
    }

    public boolean capNhat(HangHoa hh) throws Exception {
        // Ràng buộc: Tuyệt đối không cho cập nhật maHang và soLuongTon tại phân hệ này
        String sql = "UPDATE tblHangHoa SET tenHang = ?, moTa = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hh.getTenHang());
            ps.setString(2, hh.getMoTa());
            ps.setInt(3, hh.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean kiemTraXoa(int idHangHoa) throws Exception {
        // Kiểm tra xem mã id này có đang tồn tại dưới dạng Khóa ngoại ở các bảng chứng từ giao dịch hay không
        String checkNhap = "SELECT COUNT(*) FROM tblCTPhieuNhap WHERE hangHoaId = ?";
        String checkXuat = "SELECT COUNT(*) FROM tblCTPhieuXuat WHERE hangHoaId = ?";
        
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps1 = conn.prepareStatement(checkNhap)) {
                ps1.setInt(1, idHangHoa);
                try (ResultSet rs1 = ps1.executeQuery()) {
                    if (rs1.next() && rs1.getInt(1) > 0) return false; // Đã phát sinh giao dịch nhập
                }
            }
            try (PreparedStatement ps2 = conn.prepareStatement(checkXuat)) {
                ps2.setInt(1, idHangHoa);
                try (ResultSet rs2 = ps2.executeQuery()) {
                    if (rs2.next() && rs2.getInt(1) > 0) return false; // Đã phát sinh giao dịch xuất
                }
            }
        }
        return true; // Hợp lệ, được phép xóa dữ liệu
    }

    public boolean xoa(int idHangHoa) throws Exception {
        String sql = "DELETE FROM tblHangHoa WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idHangHoa);
            return ps.executeUpdate() > 0;
        }
    }
}
