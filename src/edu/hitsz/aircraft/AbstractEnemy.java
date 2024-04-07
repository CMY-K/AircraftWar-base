package edu.hitsz.aircraft;

/**
 * 敌机的抽象父类：
 * 敌机（BOSS, ELITE, MOB）
 *
 * @author hitsz
 */

public abstract  class AbstractEnemy extends AbstractAircraft{
    /**
     * 歼灭该敌机可以获得的分数
     */
    protected int score;

    public AbstractEnemy(int locationX, int locationY, int speedX, int speedY, int hp,int score) {
        super(locationX, locationY, speedX, speedY,hp);
        this.score=score;
    }

    public int getScore(){return score;}

}
