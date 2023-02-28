package org.example;

public class Test {
    private static void outputArrayList(String[] playerInfo) {
        for (String s : playerInfo)
            System.out.print(s + " ");
        System.out.print("\n");
    }
    public static void test() {
        DBHandler db = new DBHandler();
        int id = 1;

//      db.addNewPlayer(
//          id,
//          "tester:'I think that I will`t delete'",
//          "pleas, I hope you");

//        db.updateSeasonScore(id, 2);
//        outputArrayList(db.getPlayerInfo(id));
//        db.updateSeasonScore(id, 5);
//        db.updateTotalScore();

        db.closeAccessToDB();
    }
    public static void main (String[] args) {

        test();
    }
}
