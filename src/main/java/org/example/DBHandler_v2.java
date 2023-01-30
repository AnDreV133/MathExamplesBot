package org.example;

import org.glassfish.grizzly.nio.transport.DefaultStreamWriter;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
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

  public ExcepDB addNewPlayer(long id, String name, String info) {
    try {
      statementDB.execute(
          format("insert into %s values (%d, '%s', 0, 0, now(), '%s');",
              nameTable1, id, name, info)
      );

      return ExcepDB.CORRECT;
    } catch (SQLException e) {
      return ExcepDB.ID_EQUALS;
    }
  }

  public ExcepDB addNewPlayer(long id, String name) {
    return addNewPlayer(id, name, "__empty__");
  }

  public ExcepDB updateSeasonScore(long id, int score) {
    try {
      statementDB.execute(
          format("update %s set season_score = season_score + %d where id_of_player = %d;",
              nameTable1, score, id)
      );

      return ExcepDB.CORRECT;
    } catch (SQLException e) {
      return ExcepDB.EXCEP;
    }
  }

  public ExcepDB updateTotalScore() {
    try {
      statementDB.execute(
          format("update %s set total_score = total_score + season_score, season_score = 0;",
              nameTable1)

      );

      return ExcepDB.CORRECT;
    } catch (SQLException e) {
      return ExcepDB.EXCEP;
    }
  }

  private static String[] getRowOfTableWithNotInitPlace(ResultSet rowOfData) throws SQLException {
    return new String[]{
        "__position_not_initialised__",
        rowOfData.getString("player_name"),
        String.valueOf(rowOfData.getInt("season_score")),
        String.valueOf(rowOfData.getInt("total_score")),
        String.valueOf(rowOfData.getDate("data_of_last_activity")),
        rowOfData.getString("additional_information")
    };
  }

  public ArrayList<String[]> getTableOfRateWithPosition(int numOfPos) {
    ArrayList<String[]> result = new ArrayList<>();
    try {
      ResultSet rowOfData = statementDB.executeQuery(
          format("select * from %s order by season_score desc, total_score desc;",
              nameTable1)
      );

      rowOfData.next();
      result.set(0, getRowOfTableWithNotInitPlace(rowOfData))[0] = "1";
      System.out.println(1);
      int currPlace = 1;
      for (int pos = 1; rowOfData.next() && pos < numOfPos; pos++) {
        result.set(pos, getRowOfTableWithNotInitPlace(rowOfData));
        String[] row = result.get(pos);
        if (row[2].equals(result.get(pos - 1)[2]))
          row[0] = String.valueOf(currPlace);
        else
          row[0] = String.valueOf(++currPlace);
      }

      return result;
    } catch (
        SQLException e) {
      return null;
    }
  }

  public static void outputArrayListStringArr(ArrayList<String[]> arr) {
    int i = 0;
    while (arr.isEmpty()) {
      for (int j = 0; j < arr.get(0).length; j++) {
        System.out.print(arr.get(i)[j] + " ");
      }
      System.out.print("\n");
      i++;
    }
  }

  public void closeAccessToDB() {
    try {
      if (!connectionDB.isClosed()) connectionDB.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] arg) {
    DBHandler_v2 db = new DBHandler_v2();

//    System.out.println(db.addNewPlayer(72, "ivan"));
//    System.out.println(db.updateSeasonScore(72, 5));
//    System.out.println(db.updateTotalScore());
    outputArrayListStringArr(db.getTableOfRateWithPosition(5));

    db.closeAccessToDB();
  }
}
