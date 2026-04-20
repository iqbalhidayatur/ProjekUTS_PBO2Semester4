package Controller;

import com.mycompany.pertemuan_7.LoginUI;
import javax.swing.JFrame;
import com.mycompany.pertemuan_7.UI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
/**
 *
 * @author Administrator
 */
public class KelolaUser {
    
    public void loadDataUser(JTable table) {
    DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Username");
        model.addColumn("Role");
        model.addColumn("NIM");

        try {
            Connection conn = koneksidb.getConnection();

            String sql = "SELECT id, username, role, nim FROM users";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("role"),
                    rs.getString("nim")
                });
            }

            table.setModel(model);

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
}
    }
    
    public void searchByUsernameFromField(JTable jtKelolaUser, javax.swing.JTextField tfKelolaUser) {

    DefaultTableModel model = new DefaultTableModel();

    model.addColumn("ID");
    model.addColumn("Username");
    model.addColumn("Role");
    model.addColumn("NIM");

    try {
        Connection conn = koneksidb.getConnection();

        String username = tfKelolaUser.getText();

        String sql = "SELECT id, username, role, nim FROM users " +
                     "WHERE username LIKE '%" + username + "%'";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("role"),
                rs.getString("nim")
            });
        }

        jtKelolaUser.setModel(model);

        rs.close();
        st.close();
        conn.close();

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
    
    public void insertUser(
        JTextField tfUsername,
        JTextField tfPassword,
        JTextField tfNimUser,
        JComboBox<String> cbRole
) {

    try {
        Connection conn = koneksidb.getConnection();

        String sql = "INSERT INTO users (username, password, role, nim) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, tfUsername.getText());
        ps.setString(2, tfPassword.getText());
        ps.setString(3, cbRole.getSelectedItem().toString());
        String role = cbRole.getSelectedItem().toString();

        if (role.equals("MAHASISWA")) {
        ps.setString(4, tfNimUser.getText());
        } else {
        ps.setNull(4, java.sql.Types.VARCHAR);
        }

        ps.executeUpdate();

        ps.close();
        conn.close();

        System.out.println("User berhasil ditambahkan");

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
    
    public void changePassword(
        JTextField tfUser,
        JTextField tfPasswordLama,
        JTextField tfPasswordBaru
) {

    try {
        Connection conn = koneksidb.getConnection();

        String cekSql = "SELECT password FROM users WHERE username = ?";
        PreparedStatement cekPs = conn.prepareStatement(cekSql);
        cekPs.setString(1, tfUser.getText());

        ResultSet rs = cekPs.executeQuery();

        if (rs.next()) {

            String passwordDb = rs.getString("password");

            if (passwordDb.equals(tfPasswordLama.getText())) {

                String updateSql = "UPDATE users SET password = ? WHERE username = ?";
                PreparedStatement updatePs = conn.prepareStatement(updateSql);

                updatePs.setString(1, tfPasswordBaru.getText());
                updatePs.setString(2, tfUser.getText());

                updatePs.executeUpdate();

                updatePs.close();

                JOptionPane.showMessageDialog(null, "Password berhasil diubah");

            } else {
                JOptionPane.showMessageDialog(null, "Password lama salah");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Username tidak ditemukan");
        }

        rs.close();
        cekPs.close();
        conn.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}
    
    public void login(JTextField tfUsername, JTextField tfPassword, JFrame currentFrame) {

    try {
        Connection conn = koneksidb.getConnection();

        String sql = "SELECT role FROM users WHERE username = ? AND password = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, tfUsername.getText());
        ps.setString(2, tfPassword.getText());

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            JOptionPane.showMessageDialog(currentFrame, "Login berhasil");

            UI ui = new UI(rs.getString("role"));
            ui.setVisible(true);
            currentFrame.dispose();

        } else {
            JOptionPane.showMessageDialog(currentFrame, "Username atau password salah");
        }

        rs.close();
        ps.close();
        conn.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(currentFrame, "Error: " + e.getMessage());
    }
}
    
}
