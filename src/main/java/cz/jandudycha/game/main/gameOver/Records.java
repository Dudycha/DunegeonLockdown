package cz.jandudycha.game.main.gameOver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Records {
    private final SQLiteDatabase sql = new SQLiteDatabase();
    private final ArrayList<Record> listOfRecords = new ArrayList<>();

    public Records() {


    }

    public void update() {
        listOfRecords.clear();
        try {
            ResultSet rs = sql.displayRecords();
            while (rs.next()) {
                listOfRecords.add(new Record(rs.getString("timeStamp"),
                        rs.getInt("score"),
                        rs.getInt("bulletsFired"),
                        rs.getInt("DMGreceived"),
                        rs.getInt("bulletsFired")));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public void addRecord(String timeStamp, int score, int enemyKilled, int bulletsFired, int DMGreceived, int coinsEarned) {
        try {
            sql.addRecord(timeStamp, score, enemyKilled, bulletsFired, DMGreceived, coinsEarned);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        update();
    }

    public ArrayList<Record> getListOfRecords() {
        return listOfRecords;
    }

    public void clear() {
        try {
            sql.clearDatabase();
            update();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
