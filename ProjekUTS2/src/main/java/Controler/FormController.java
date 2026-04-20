package Controler;

import javax.swing.table.DefaultTableModel;

public class FormController {

    String[] nim = new String[5];
    double[] uts = new double[5];
    double[] tugas = new double[5];
    double[] uas = new double[5];
    double[] absensi = new double[5];
    double[] akhir = new double[5];

    int index = 0;

    public void inputData(javax.swing.JTextField txtNim,
                          javax.swing.JTextField txtUts,
                          javax.swing.JTextField txtTugas,
                          javax.swing.JTextField txtUas,
                          javax.swing.JTextField txtAbsensi,
                          javax.swing.JTable tableData) {

        if (index >= 5) {
            System.out.println("Data penuh");
            return;
        }

        nim[index] = txtNim.getText();
        uts[index] = Double.parseDouble(txtUts.getText());
        tugas[index] = Double.parseDouble(txtTugas.getText());
        uas[index] = Double.parseDouble(txtUas.getText());
        absensi[index] = Double.parseDouble(txtAbsensi.getText());

        akhir[index] = (uts[index] * 0.2) +
                       (tugas[index] * 0.3) +
                       (uas[index] * 0.4) +
                       (absensi[index] * 0.1);

        DefaultTableModel model = (DefaultTableModel) tableData.getModel();

        model.addRow(new Object[]{
            nim[index],
            uts[index],
            tugas[index],
            uas[index],
            absensi[index],
            akhir[index]
        });

        index++;
    }

    public void clearInput(javax.swing.JTextField txtNim,
                           javax.swing.JTextField txtUts,
                           javax.swing.JTextField txtTugas,
                           javax.swing.JTextField txtUas,
                           javax.swing.JTextField txtAbsensi) {

        txtNim.setText("");
        txtUts.setText("");
        txtTugas.setText("");
        txtUas.setText("");
        txtAbsensi.setText("");
    }
}
