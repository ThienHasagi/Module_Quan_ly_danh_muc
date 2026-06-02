package dao;

import entity.DaiLyCon;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaiLyConDAO extends BaseDAO {

    public List<DaiLyCon> timKiem(String tuKhoa) throws Exception {
        List<DaiLyCon> list = new ArrayList<>();
        String sql = "SELECT * FROM tblDaiLyCon WHERE maDL LIKE ? OR tenDL LIKE ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + tuKhoa + "%");
            ps.setString(2, "%" + tuKhoa + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new DaiLyCon(
                        rs.getInt("id"),
                        rs.getString("maDL"),
                        rs.getString("tenDL"),
                        rs.getString("diaChi"),
                        rs.getString("soDT")
                    ));
                }
            }
        }
        return list;
    }

    public boolean kiemTraTrungMa(String maDL) throws Exception {
        String sql = "SELECT COUNT(*) FROM tblDaiLyCon WHERE maDL = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maDL);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    public boolean themMoi(DaiLyCon dl) throws Exception {
        String sql = "INSERT INTO tblDaiLyCon (maDL, tenDL, diaChi, soDT) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dl.getMaDL());
            ps.setString(2, dl.getTenDL());
            ps.setString(3, dl.getDiaChi());
            ps.setString(4, dl.getSoDienThoai());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean capNhat(DaiLyCon dl) throws Exception {
        String sql = "UPDATE tblDaiLyCon SET tenDL = ?, diaChi = ?, soDT = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dl.getTenDL());
            ps.setString(2, dl.getDiaChi());
            ps.setString(3, dl.getSoDienThoai());
            ps.setInt(4, dl.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean kiemTraXoa(int idDL) throws Exception {
        String sql = "SELECT COUNT(*) FROM tblCTPhieuXuat WHERE daiLyConId = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idDL);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) == 0;
            }
        }
        return true;
    }

    public boolean xoa(int idDL) throws Exception {
        String sql = "DELETE FROM tblDaiLyCon WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idDL);
            return ps.executeUpdate() > 0;
        }
    }
}
