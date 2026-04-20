package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class KelolaMatkul {
    
    public void loadDataMatkul(JTable jtMatkul) {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Kode MK");
        model.addColumn("Nama MK");
        model.addColumn("SKS");
        model.addColumn("ID Dosen");

        try {
            Connection conn = koneksidb.getConnection();

            String sql = "SELECT kode_mk, nama_mk, sks, id_dosen FROM matakuliah";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("kode_mk"),
                    rs.getString("nama_mk"),
                    rs.getInt("sks"),
                    rs.getString("id_dosen")
                });
            }

            jtMatkul.setModel(model);

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void loadKodeMkToComboBox(JComboBox<String> combo) {

        combo.removeAllItems();

        try {
            Connection conn = koneksidb.getConnection();

            String sql = "SELECT kode_mk FROM matakuliah";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                combo.addItem(rs.getString("kode_mk"));
            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            }
    }
    
    public void insertMatkul(
        JTextField tfKodeMk,
        JTextField tfNamaMk,
        JTextField tfSks,
        JComboBox<String> cbIdDosen)   
    {

        try {
        Connection conn = koneksidb.getConnection();

        String sql = "INSERT INTO matakuliah (kode_mk, nama_mk, sks, id_dosen) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        String kodeMk = tfKodeMk.getText();
        String namaMk = tfNamaMk.getText();
        int sks = Integer.parseInt(tfSks.getText());
        String idDosen = cbIdDosen.getSelectedItem().toString();

        ps.setString(1, kodeMk);
        ps.setString(2, namaMk);
        ps.setInt(3, sks);
        ps.setString(4, idDosen);

        ps.executeUpdate();

        ps.close();
        conn.close();

        System.out.println("Data matakuliah berhasil ditambahkan");

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
}
