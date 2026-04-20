package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class KelolaDosen {
    public void loadDataDosen(JTable jtDosen) {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("No HP");

        try {
            Connection conn = koneksidb.getConnection();

            String sql = "SELECT id_dosen, nama, hp FROM dosen";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id_dosen"),
                    rs.getString("nama"),
                    rs.getString("hp")
                });
            }

            jtDosen.setModel(model);

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void loadIdDosenToComboBox(JComboBox<String> combo) {
        combo.removeAllItems();

        try {
            Connection conn = koneksidb.getConnection();

            String sql = "SELECT id_dosen FROM dosen";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                combo.addItem(rs.getString("id_dosen"));
            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void insertDosen(
        JTextField tfIdDosen,
        JTextField tfNamaDosen,
        JTextField tfHpDosen
) {

    try {
        Connection conn = koneksidb.getConnection();

        String sql = "INSERT INTO dosen (id_dosen, nama_dosen, hp) VALUES (?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, tfIdDosen.getText());
        ps.setString(2, tfNamaDosen.getText());
        ps.setString(3, tfHpDosen.getText());

        ps.executeUpdate();

        ps.close();
        conn.close();

        System.out.println("Data dosen berhasil ditambahkan");

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
}
