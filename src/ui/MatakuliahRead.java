package ui;

import helper.MyConnection;
import model.Matakuliah;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MatakuliahRead extends JFrame {

    public JPanel panel1;
    public JTable matakuliahTable;

    public void createUIComponents() {
        Matakuliah matakuliah;
        ArrayList<Matakuliah> matakuliahList = new ArrayList<>();
        MyConnection myConnection = new MyConnection();
        Connection con = myConnection.getConnection();

        String selectQuery = "Select * FROM matakuliah ";
        Statement statement;
        ResultSet resultSet;
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                matakuliah = new Matakuliah(
                        resultSet.getInt("id_matakuliah"),
                        resultSet.getString("nama_matakuliah"),
                        resultSet.getString("singkatan_matakuliah"),
                        resultSet.getString("nama_dosen"),
                        resultSet.getString("kontak_dosen"),
                        resultSet.getBoolean("aktif")
                );
                matakuliahList.add(matakuliah);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Object[][] row = new Object[matakuliahList.size()][6];

        for (int i = 0; i < matakuliahList.size(); i++) {
            row[i][0] = matakuliahList.get(i).getIdMataKuliah();
            row[i][1] = matakuliahList.get(i).getNamaMatakuliah();
            row[i][2] = matakuliahList.get(i).getSingkatanMatakuliah();
            row[i][3] = matakuliahList.get(i).getNamaDosen();
            row[i][4] = matakuliahList.get(i).getKontakDosen();
            row[i][5] = matakuliahList.get(i).isAktif();
        }
            String matakuliahHeader[] = {"ID","Matakuliah","Singkatan","Kontak","Aktif"};
            TableModel tableModel = new DefaultTableModel(row, matakuliahHeader);
            matakuliahTable = new JTable(tableModel);
        }

        public static void main(String[] args) {
            MatakuliahRead matakuliahRead = new MatakuliahRead();
            matakuliahRead.setContentPane(new MatakuliahRead().panel1);
            matakuliahRead.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            matakuliahRead.createUIComponents();
            matakuliahRead.pack();
            matakuliahRead.setLocationRelativeTo(null);
            matakuliahRead.setVisible(true);
    }
}
