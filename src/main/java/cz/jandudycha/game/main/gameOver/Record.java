package cz.jandudycha.game.main.gameOver;

public class Record {
    private final String timeStamp;
    private final int score, bulletsFired, DMGreceived, coinsEarned;

    public Record(String timeStamp, int score, int bulletsFired, int DMGreceived, int coinsEarned) {
        this.timeStamp = timeStamp;
        this.score = score;
        this.bulletsFired = bulletsFired;
        this.DMGreceived = DMGreceived;
        this.coinsEarned = coinsEarned;
    }

    public String getTimeStamp() {
        return timeStamp;
    }


    public int getScore() {
        return score;
    }


    public int getBulletsFired() {
        return bulletsFired;
    }


    public int getDMGreceived() {
        return DMGreceived;
    }


    public int getCoinsEarned() {
        return coinsEarned;
    }


}
