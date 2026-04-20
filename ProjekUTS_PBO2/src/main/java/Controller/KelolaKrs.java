package Controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class KelolaKrs {
    public void loadDataKrs(JTable jtKrs) {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("NIM");
        model.addColumn("Kode MK");

        try {
            Connection conn = koneksidb.getConnection();

            String sql = "SELECT id, nim, kode_mk FROM krs";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nim"),
                    rs.getString("kode_mk")
                });
            }

            jtKrs.setModel(model);

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void insertKrs(
        JTextField tfIdKrs,
        JComboBox<String> cbNimKrs,
        JComboBox<String> cbMatkul
) {

    try {
        Connection conn = koneksidb.getConnection();

        String sql = "INSERT INTO krs (id, nim, kode_mk) VALUES (?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, Integer.parseInt(tfIdKrs.getText()));
        ps.setString(2, cbNimKrs.getSelectedItem().toString());
        ps.setString(3, cbMatkul.getSelectedItem().toString());

        ps.executeUpdate();

        ps.close();
        conn.close();

        System.out.println("Data KRS berhasil ditambahkan");

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
}
