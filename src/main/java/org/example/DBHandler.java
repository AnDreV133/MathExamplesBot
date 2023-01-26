package org.example;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBHandler {
    private static final String nameDB = mainData().getProperty("NameDB");
    private static final String passwordDB = mainData().getProperty("PasswordDB");
    private static final String urlDB = mainData().getProperty("URLDB");
    private static final Connection connectionDB;
    private static final Statement statementDB;

    static {
        try {
            connectionDB = DriverManager.getConnection(urlDB, nameDB, passwordDB);
            statementDB = connectionDB.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    static Properties mainData() {
        Properties mainData;
        try {
            mainData = new Properties();
            FileInputStream file = new FileInputStream("src/main/resources/SecretInfoOfTelegramBot.properties");
            mainData.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return mainData;
    }

    @SneakyThrows
    static void updateDB(String request) {

    }

    public String getData(String columnDB) {

        return "0";
    }

    public void removeData(String columnDB) {

    }

    public void addData(String columnDB) {

    }
}

class TestDB {
    public static void main(String[] arg) {
        DBHandler db = new DBHandler();
        System.out.println(db.getData("name"));
    }
}

//    public String answer = "0";
//    public String state = "0";
//    public String changedLvl = "0";
//    public long totalScore = 0;
//    public int minNumber = 0;
//    public int maxNumber = 0;
//    public int maxSmallNumber = 0; вдруг пригодится