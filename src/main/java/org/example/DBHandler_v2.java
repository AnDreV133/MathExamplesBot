package org.example;

import org.glassfish.grizzly.nio.transport.DefaultStreamWriter;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

import static java.lang.String.format;

public class DBHandler_v2 {
  private static final String nameDB = mainData().getProperty("NameDB");
  private static final String passwordDB = mainData().getProperty("PasswordDB");
  private static final String urlDB = mainData().getProperty("URLDB");
  private static final Connection connectionDB;
  private static final Statement statementDB;
  private static final String nameTable1 = mainData().getProperty("NameTable1");
//  private static final String nameTable2 = mainData().getProperty("NameTable2");

  static {
    try {
      connectionDB = DriverManager.getConnection(urlDB, nameDB, passwordDB);
      statementDB = connectionDB.createStatement();
    } catch (SQLException e) {
      System.err.println("No connection to DB");
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

  

  public void addNewPlayer(long id, String name, String info) {
    try {
      statementDB.execute(
          format("insert into %s values (%d, '%s', 0, 0, now(), " + (info == null ? "" : "'%s'") + ");",
              nameTable1, id, name, info)
      );

    } catch (SQLException e) {
      System.err.println("add new player error");
    }
  }

  public void updateSeasonScore(long id, int score) {
    try {
      statementDB.execute(
          format("update %s set season_score = season_score + %d where id_of_player = %d;",
              nameTable1, score, id)
      );

    } catch (SQLException e) {
      System.out.println("");
    }
  }

  public void updateTotalScore() {
    try {
      statementDB.execute(
          format("update %s set total_score = total_score + season_score, season_score = 0;",
              nameTable1)
      );
    } catch (SQLException e) {
      System.out.println("");
    }
  }

  private static String getFormatDate(java.sql.Date date) {
    return date.toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
  }
  private static ArrayList<String> getRowOfTableWithNotInitPlace(ResultSet table) throws SQLException {
    ArrayList<String> row = new ArrayList<>(6);
    row.add("__position_not_initialised__");
    row.add(table.getString("player_name"));
    row.add(String.valueOf(table.getInt("season_score")));
    row.add(String.valueOf(table.getInt("total_score")));
    row.add(getFormatDate(table.getDate("date_of_registration")));
    row.add(table.getString("additional_information"));

    return row;
  }

  // Return ArrayList<ArrayList<str>> of info all players and them places
  // for show info to user.
  public ArrayList<ArrayList<String>> getTableOfRateWithPosition(int numOfPos) {
    ArrayList<ArrayList<String>> table = new ArrayList<>();
    try {
      // query
      ResultSet resultSet = statementDB.executeQuery(
          format("select * from %s order by season_score desc, total_score desc;",
              nameTable1)
      );

      // set first place
      resultSet.next();
      table.add(getRowOfTableWithNotInitPlace(resultSet));
      table.get(0).set(0, "1");

      // set other place
      int currPlace = 1;
      for (int pos = 1; resultSet.next() && pos < numOfPos; pos++) {
        table.add(getRowOfTableWithNotInitPlace(resultSet));

        if (table.get(pos).get(2).equals(table.get(pos - 1).get(2)))
          table.get(pos).set(0, String.valueOf(currPlace));
        else
          table.get(pos).set(0, String.valueOf(++currPlace));
      }

      return table;
    } catch (
        SQLException e) {
      return null;
    }
  }

  public static void outputArrayListStringArr(ArrayList<ArrayList<String>> arr) {
    for (ArrayList<String> strings : arr) {
      for (String string : strings) {
        System.out.print(string + "\t ");
      }
      System.out.print("\n");
    }
  }

  static boolean isDBConnected() {
    try {
      return !connectionDB.isClosed();
    } catch (SQLException e) {
      return false;
    }
  }

  public void closeAccessToDB() {
    try {
      if (isDBConnected()) {
        connectionDB.close();
      }
    } catch (SQLException e) {
      System.out.println("");
    }


  }

  public static void main(String[] arg) {
    DBHandler_v2 db = new DBHandler_v2();

//    db.addNewPlayer(50, "elya", "Im gigachadman");
//    System.out.println(db.updateSeasonScore(72, 5));
//    System.out.println(db.updateTotalScore());

    outputArrayListStringArr(db.getTableOfRateWithPosition(5));

    db.closeAccessToDB();
  }
}
