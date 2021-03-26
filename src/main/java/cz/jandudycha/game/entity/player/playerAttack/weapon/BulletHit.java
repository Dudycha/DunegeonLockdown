package cz.jandudycha.game.entity.player.playerAttack.weapon;

public class BulletHit {
    private final int x, y;
    private final boolean hitOnEnemy;
    int positionOfEnemyInList;

    public BulletHit(int x, int y, boolean hitOnEnemy, int positionOfEnemyInList) {
        this.x = x;
        this.y = y;
        this.hitOnEnemy = hitOnEnemy;
        this.positionOfEnemyInList = positionOfEnemyInList;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isHitOnEnemy() {
        return hitOnEnemy;
    }


    public int getPositionOfEnemyInList() {
        return positionOfEnemyInList;
    }


}
