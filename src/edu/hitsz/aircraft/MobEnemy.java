package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;


import java.util.LinkedList;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class MobEnemy extends AbstractAircraft {

    /*private int score=30;

    public int getScore(){
        return this.score;
    }*/

    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp,int score) {
        super(locationX, locationY, speedX, speedY, hp, score);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    @Override

    public List<BaseBullet> shoot() {
        return new LinkedList<>();
    }


}
