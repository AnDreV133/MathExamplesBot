package org.example;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.lang.String.format;

public class DBHandler {
   // initialization begin
   private static final GetterSecretInformation SI = new GetterSecretInformation();
   private static final Connection connectionDB;
   private static final Statement statementDB;

   static {
      try {
         connectionDB = DriverManager.getConnection(
             SI.get("URLDB"),
             SI.get("NameDB"),
             SI.get("PasswordDB")
         );

         statementDB = connectionDB.createStatement();

         if (connectionDB.isClosed())
            System.err.println("no connection to DB");

      } catch (SQLException e) {
         System.err.println("No connection to DB - error");
         throw new RuntimeException();
      }
   } // connection to database
   // initialization end

//public void test_connection() {
//      statementDB.execute("")
//}
   public void addNewPlayer(long id, String name, String info) {
      try {
         statementDB.execute(
             format("insert into db_of_rate.main " +
                 "(id_of_player, player_name, date_of_registration, additional_information) " +
                 "values (%d, '%s', now(), '%s');", id, name, info)
         );
      } catch (SQLException e) {
         System.err.println("add new player error");
      }
   } // error

   public void addNewPlayer(long id, String name) {
      addNewPlayer(id, name, "");
   }

   public void updateSeasonScore(long id, int numOfPoints) {
      try {
         statementDB.execute(
             format("update db_of_rate.main set season_score = season_score + %d where id_of_player = %d;",
                 numOfPoints, id)
         );
      } catch (SQLException e) {
         System.err.println("update score error");
      }
   }

   public void updateTotalScore() {
      try {
         statementDB.execute(
             "update db_of_rate.main set total_score = total_score + season_score;"
         );
      } catch (SQLException e) {
         System.err.println("update total score error");
      }
   }

   // method sort a table after it update place depending on season_score _O(n*log(n))+O(n)_
   private void setPlaces() {
      try {
         ResultSet resultSet = statementDB.executeQuery(
             "select id_of_player, season_score from db_of_rate.main order by season_score desc, total_score desc;"
         );
         resultSet.next();

         int prevScore = resultSet.getInt(1);
         int place = 1;
         System.err.println(prevScore);
         statementDB.execute(format("update db_of_rate.main set place = %d where id_of_player = %d;",
             place, prevScore)
         );

         while (resultSet.next()) {
            if (resultSet.getInt("id_of_player") < prevScore)
               place++;

            statementDB.execute(format("update db_of_rate.main set place = %d where id_of_player = %d limit 1;",
                place, resultSet.getInt("id_of_player"))
            );
         }
      } catch (SQLException e) {
         System.err.println("set places error");
      }
   }

   private String getFormatDate(java.sql.Date date) {
      return date.toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
   }

   private String[] takeDataFromRow(ResultSet rs) throws SQLException {
      return new String[]{
          rs.getString(1),
          rs.getString(2),
          rs.getString(3),
          rs.getString(4),
          rs.getString(5),
          getFormatDate(rs.getDate(6)),
          rs.getString(7)
      };
   }

   public String[] getPlayerInfo(long id) {
      setPlaces();

      try {
         ResultSet resultSet = statementDB.executeQuery(
             format("select id_of_player = %d from db_of_rate.main;",
                 id
             )
         );
         resultSet.next();

         return takeDataFromRow(resultSet);
      } catch (SQLException e) {
         System.err.println("get player info error");
         return new String[]{"not info, data base error"};
      }
   } // error

   public void outputArrayListStringArr(ArrayList<ArrayList<String>> arr) {
      for (ArrayList<String> strings : arr) {
         for (String string : strings) {
            System.out.print(string + "\t ");
         }
         System.out.print("\n");
      }
   }

   boolean isDBConnected() {
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
}
