package cz.jandudycha.game.main.gameOver;

import java.text.SimpleDateFormat;


public class ScoreManager {
    private final Records records = new Records();


    public ScoreManager() {
        records.update();


    }


    public void update() {

    }

    public void newScore(int score, int enemyKilled, int bulletsFired, int DMGreceived, int coinsEarned) {
            records.addRecord(getTimeStamp(), score, enemyKilled, bulletsFired, DMGreceived, coinsEarned);

    }

    private String getTimeStamp() {
        return new SimpleDateFormat("yyyy'-'MM'-'dd' 'HH':'mm':'ss").format(new java.util.Date());
    }

    public void clear() {
        records.clear();
    }

    public Records getRecords() {
        return records;
    }
}
