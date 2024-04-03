package edu.hitsz.props;


import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.bullet.BaseBullet;


import java.util.List;

/**
 炸弹道具：清除界面上除 boss 机外的所 有敌机和敌机子弹，英雄机可获得坠毁 的所有敌机的分数。
 */

public class Bomb extends  AbstractProps{

    public static final int WIDTH=6;
    public static final int HEIGHT=18;


    public Bomb(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public int getEffect(HeroAircraft heroAircraft, List<AbstractAircraft> enemyAircrafts, List<BaseBullet> enemyBullets) {

        int nowscore=0;
        for(AbstractAircraft Enemy:enemyAircrafts){
            if(!Enemy.notValid()) {
                nowscore+=(Enemy.getScore());
                Enemy.vanish();
            }
        }

        for(BaseBullet EnemyBullet:enemyBullets){
            EnemyBullet.vanish();
        }
        return nowscore;
    }


}
