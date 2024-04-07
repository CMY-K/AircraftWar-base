package edu.hitsz.props;


import edu.hitsz.aircraft.*;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;


import java.util.List;

/**
 炸弹道具：清除界面上除 boss 机外的所 有敌机和敌机子弹，英雄机可获得坠毁 的所有敌机的分数。
 */

public class Bomb extends  AbstractProps{


    public Bomb(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    /**
     *撞击道具，清除界面上除 boss 机外的所 有敌机和敌机子弹，英雄机可获得坠毁 的所有敌机的分数。
     */
    @Override
    public int getEffect(HeroAircraft heroAircraft, List<AbstractEnemy> enemyAircrafts, List<BaseBullet> enemyBullets) {

        int nowscore=0;
        for(AbstractEnemy Enemy:enemyAircrafts){
            if(!Enemy.notValid()) {
                nowscore+=(Enemy.getScore());
                Enemy.vanish();
            }
        }

        for(BaseBullet EnemyBullet:enemyBullets){
            EnemyBullet.vanish();
        }
        System.out.println("BombSupply active!");
        this.vanish();
        return nowscore;
    }

}
