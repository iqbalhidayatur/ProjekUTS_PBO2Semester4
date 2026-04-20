package Controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class KelolaNilai {
    public void loadDataNilai(JTable jtNilai) {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("NIM");
        model.addColumn("UTS");
        model.addColumn("Tugas");
        model.addColumn("UAS");
        model.addColumn("Absensi");

        try {
            Connection conn = koneksidb.getConnection();

            String sql = "SELECT id, nim, uts, tugas, uas, absensi FROM nilai";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nim"),
                    rs.getString("uts"),
                    rs.getString("tugas"),
                    rs.getString("uas"),
                    rs.getString("absensi")
                });
            }

            jtNilai.setModel(model);

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void insertNilai(
        JComboBox<String> cbNimNilai,
        JTextField tfUts,
        JTextField tfTugas,
        JTextField tfUas,
        JTextField tfAbsensi
) {

    try {
        Connection conn = koneksidb.getConnection();

        String sql = "INSERT INTO nilai (nim, uts, tugas, uas, absensi) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, cbNimNilai.getSelectedItem().toString());
        ps.setString(2, tfUts.getText());
        ps.setString(3, tfTugas.getText());
        ps.setString(4, tfUas.getText());
        ps.setString(5, tfAbsensi.getText());

        ps.executeUpdate();

        ps.close();
        conn.close();

        System.out.println("Data nilai berhasil ditambahkan");

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
}
