package dao;

import entity.NhaCungCap;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhaCungCapDAO extends BaseDAO {

    public List<NhaCungCap> timKiem(String tuKhoa) throws Exception {
        List<NhaCungCap> list = new ArrayList<>();
        String sql = "SELECT * FROM tblNhaCungCap WHERE maNCC LIKE ? OR tenNCC LIKE ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + tuKhoa + "%");
            ps.setString(2, "%" + tuKhoa + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new NhaCungCap(
                        rs.getInt("id"),
                        rs.getString("maNCC"),
                        rs.getString("tenNCC"),
                        rs.getString("diaChi"),
                        rs.getString("soDT")
                    ));
                }
            }
        }
        return list;
    }

    public boolean kiemTraTrungMa(String maNCC) throws Exception {
        String sql = "SELECT COUNT(*) FROM tblNhaCungCap WHERE maNCC = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maNCC);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    public boolean themMoi(NhaCungCap ncc) throws Exception {
        String sql = "INSERT INTO tblNhaCungCap (maNCC, tenNCC, diaChi, soDT) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ncc.getMaNCC());
            ps.setString(2, ncc.getTenNCC());
            ps.setString(3, ncc.getDiaChi());
            ps.setString(4, ncc.getSoDienThoai());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean capNhat(NhaCungCap ncc) throws Exception {
        String sql = "UPDATE tblNhaCungCap SET tenNCC = ?, diaChi = ?, soDT = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ncc.getTenNCC());
            ps.setString(2, ncc.getDiaChi());
            ps.setString(3, ncc.getSoDienThoai());
            ps.setInt(4, ncc.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean kiemTraXoa(int idNCC) throws Exception {
        String sql = "SELECT COUNT(*) FROM tblCTPhieuNhap WHERE nhaCungCapId = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idNCC);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) == 0;
            }
        }
        return true;
    }

    public boolean xoa(int idNCC) throws Exception {
        String sql = "DELETE FROM tblNhaCungCap WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idNCC);
            return ps.executeUpdate() > 0;
        }
    }
}
