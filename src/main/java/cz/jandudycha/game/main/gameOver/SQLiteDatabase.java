package cz.jandudycha.game.main.gameOver;


import java.sql.*;

public class SQLiteDatabase {


    private static Connection con;
    private static boolean hasData = false;


    public ResultSet displayRecords() throws SQLException, ClassNotFoundException {
        if (con == null) {
            getConnection();
        }
        Statement state = con.createStatement();
        return state.executeQuery("SELECT timeStamp, score,enemyKilled, bulletsFired, DMGreceived, coinsEarned FROM records");
    }

    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:SQLiteScoreData.db");
        initialise();
    }


    private void initialise() throws SQLException {
        if (!hasData) {
            hasData = true;

            Statement state = con.createStatement();
            ResultSet res = state.executeQuery("SELECT name FROM sqlite_master master WHERE type='table' AND name='records';");
            if (!res.next()) {
                System.out.println("Building the records table with prepopulated values");
                //need to build table
                Statement state2 = con.createStatement();
                state2.execute("CREATE TABLE records(id integer,"
                        + "timeStamp varchar(60)," + "score integer," + "enemyKilled integer," + "bulletsFired integer,"
                        + "DMGreceived integer," + "coinsEarned integer,"
                        + "primary key(id));");
            }

            //inserting some data
//            PreparedStatement prep = con.prepareStatement("INSERT INTO records values(?,?,?,?,?,?,?);");
//            prep.setString(2, "2021-03-02 16:32:10");
//            prep.setInt(3, 16520);
//            prep.setInt(4, 1172);
//            prep.setInt(5, 3588);
//            prep.setInt(6, 287);
//            prep.setInt(7, 4030);
//            prep.execute();



        }
    }

    public void addRecord(String timeStamp, int score, int enemyKilled, int bulletsFired, int DMGreceived, int coinsEarned)
            throws SQLException, ClassNotFoundException {
        if (con == null) {
            getConnection();
        }

        PreparedStatement prep = con.prepareStatement("INSERT INTO records values(?,?,?,?,?,?,?);");
        prep.setString(2, timeStamp);
        prep.setInt(3, score);
        prep.setInt(4, bulletsFired);
        prep.setInt(5, enemyKilled);
        prep.setInt(6, DMGreceived);
        prep.setInt(7, coinsEarned);
        prep.execute();

    }

    public void clearDatabase() throws SQLException, ClassNotFoundException {
        if (con == null) {
            getConnection();
        }
        Statement state = con.createStatement();
        state.execute("DELETE FROM records;");
    }

//    public ResultSet getCountOfRecords() throws SQLException, ClassNotFoundException {
//        if (con == null) {
//            getConnection();
//        }
//        Statement state = con.createStatement();
//        ResultSet res = state.executeQuery("SELECT COUNT(*) FROM records;");
//        return res;
//    }
}
