package org.example;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.sql.*;
import java.util.Properties;

import static java.lang.String.format;

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

  private static int getLastPlace() throws SQLException {
    ResultSet query = statementDB.executeQuery(
        "select * from main order by place desc, season_score asc, total_score asc limit 1;"
    );

    query.next();

    int minPlace = query.getInt("place");
    int isMinScoreBiggerThanZero = query.getInt("season_score") +
        query.getInt("total_score") > 0 ? 1 : 0;

    return minPlace + isMinScoreBiggerThanZero;
  }

//  public ExcepDB updateSeasonScore(long id, int score) {
//    try {
//      statementDB.execute(format("UPDATE main SET season_score = season_score + %d WHERE id_of_player = %d LIMIT 1;",
//          score, id));
//      statementDB.execute();
//      return ExcepDB.CORRECT;
//    } catch (SQLException e) {
//      return ExcepDB.NOT_UPDATE;
//    }
//  }

  public String getData(String columnDB) {

    return "0";
  }

  public ExcepDB removePlayerProgress(long id) {
    try {
      statementDB.execute(format("DELETE FROM main WHERE id_of_player = %d LIMIT 1;", id));

      return ExcepDB.CORRECT;
    } catch (SQLException e) {
      return ExcepDB.NOT_REMOVE;
    }
  }

  public ExcepDB addNewPlayer(long id, String name, String info) {
    try {
      statementDB.execute(
          format("insert into main  values (%d, '%s', %d, 0, 0, now(), '%s');",
              getLastPlace(), name, id, info)
      );
      return ExcepDB.CORRECT;
    } catch (SQLException e) {
      return ExcepDB.ID_EQUALS;
    }
  }

  public ExcepDB addNewPlayer(long id, String name) {
    return addNewPlayer(id, name, "__null__");
  }

  public boolean isDBClosed() {
    try {
      return connectionDB.isClosed();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void closeAccessToDB() {
    try {
      if (!connectionDB.isClosed()) connectionDB.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}

class TestDB {
  public static void main(String[] arg) {
    DBHandler db = new DBHandler();

//    System.out.println(db.addNewPlayer(41, "steve"));
    System.out.println(db.removePlayerProgress(41));

    db.closeAccessToDB();
  }
}