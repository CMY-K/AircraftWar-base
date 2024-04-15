package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;


import java.util.LinkedList;
import java.util.List;

/**
 *精英敌机，游戏玩家操控
 * @author hitsz
 */
public class EliteEnemy extends AbstractEnemy {
    /**
     * @param locationX 敌机位置x坐标
     * @param locationY 敌机位置y坐标
     * @param speedX 敌机射出的子弹的基准速度（敌机无特定速度）
     * @param speedY 敌机射出的子弹的基准速度（敌机无特定速度）
     * @param hp    初始生命值
     */


    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp,int score) {
        super(locationX, locationY, speedX, speedY, hp, score);
    }


    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + super.direction*2;
        int speedX = 0;
        int speedY = this.getSpeedY() + super.direction*5;
        BaseBullet bullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
            res.add(bullet);
        }
        return res;
    }

}
