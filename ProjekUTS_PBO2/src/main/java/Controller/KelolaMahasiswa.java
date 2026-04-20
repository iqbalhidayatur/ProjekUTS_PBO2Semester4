package Controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class KelolaMahasiswa {
    public void loadDataMahasiswa(JTable jtMahasiswa) {

    DefaultTableModel model = new DefaultTableModel();

    model.addColumn("NIM");
    model.addColumn("Nama");
    model.addColumn("Nomor HP");

    try {
        Connection conn = koneksidb.getConnection();

        String sql = "SELECT nim, nama, nomorhp FROM mahasiswa";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("nim"),
                rs.getString("nama"),
                rs.getString("nomorhp")
            });

        }

        jtMahasiswa.setModel(model);

        rs.close();
        st.close();
        conn.close();

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void loadNimToComboBox(JComboBox<String> combo) {

    combo.removeAllItems();

    try {
        Connection conn = koneksidb.getConnection();

        String sql = "SELECT nim FROM mahasiswa";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            combo.addItem(rs.getString("nim"));
        }

        rs.close();
        st.close();
        conn.close();

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
    
    public void insertMahasiswa(
        JTextField tfNim,
        JTextField tfNama,
        JTextField tfNomorHp
) {

    try {
        Connection conn = koneksidb.getConnection();

        String sql = "INSERT INTO mahasiswa (nim, nama, nomorhp) VALUES (?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, tfNim.getText());
        ps.setString(2, tfNama.getText());
        ps.setString(3, tfNomorHp.getText());

        ps.executeUpdate();

        ps.close();
        conn.close();

        System.out.println("Data mahasiswa berhasil ditambahkan");

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
}
