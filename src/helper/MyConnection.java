package helper;

import model.Matakuliah;

import java.sql.*;
import java.util.ArrayList;

public class MyConnection {
    private static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/";
    private static String DB_NAME = "db_pekerjaan_rumah";
    private static String TIMEZONE = "?serverTimezone=Asia/Makassar";
    private static String USER = "root";
    private static String PASS = "";

    public Connection getConnection() {
        Connection con = null;

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Sedang menghubungkan...");
            con = DriverManager.getConnection(URL + DB_NAME + TIMEZONE,USER,PASS);
            System.out.println("Berhasil");
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Matakuliah matakuliah;
        ArrayList<Matakuliah> matakuiahList = new ArrayList<>();
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
                matakuiahList.add(matakuliah);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Matakuliah value : matakuiahList) {
            System.out.println(value.getIdMataKuliah());
            System.out.println("\t");
            System.out.println(value.getSingkatanMatakuliah());
            System.out.println("\t");
            System.out.println(value.getNamaDosen());
            System.out.println();
        }
    }
}