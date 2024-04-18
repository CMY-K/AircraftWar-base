package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.props.AbstractProps;
import edu.hitsz.props.PropCreatorPattern;

import java.util.LinkedList;
import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * 分数达到设定阈值，可多次出现
 * 悬浮于界面上方左右移动
 * 环射弹道 同时发射20颗子弹，呈环形
 * 随机掉落<=3个道具
 * @author hitsz
 */
public class BossEnemy extends AbstractEnemy {



    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp, int score) {
        super(locationX, locationY, speedX, speedY, hp,score);
        this.shootNum=20;
    }



    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    /*
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + super.direction*2;
        int speedX = 0;
        int speedY = this.getSpeedY() + super.direction*5;
        BaseBullet bullet;
        for(int i=0; i<this.shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
            res.add(bullet);
        }
        return res;
    }
*/
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();

        int x = this.getLocationX();
        int y = this.getLocationY() + super.direction * 5;
        double radius = 20; // 环的半径
        double angleIncrement = 2 * Math.PI / shootNum; // 每颗子弹的角度增量

        for (int i = 0; i < this.shootNum; i++) {
            // 计算每颗子弹的角度
            double angle = i * angleIncrement;
            // 计算子弹的坐标
            int bulletX = (int) (x + radius * Math.cos(angle));
            int bulletY = (int) (y + radius * Math.sin(angle));
            // 计算子弹的速度
            int speedX = (int) (2 + super.direction * 5 * Math.cos(angle));
            int speedY = (int) (2 + super.direction * 5 * Math.sin(angle));
            // 创建子弹并添加到列表中
            BaseBullet bullet = new EnemyBullet(bulletX, bulletY, speedX, speedY, power);
            res.add(bullet);
        }

        return res;
    }
/*
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction*2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction*5;
        BaseBullet bullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new EnemyBullet(x + (int)(200 * Math.sin((i * 2 - shootNum + 1) * 10 * Math.PI / 180)), y + (int)(200 * Math.cos((i * 2 - shootNum + 1) * 10 * Math.PI / 180)), speedX, speedY, power);
            res.add(bullet);
        }
        return res;
    }*/

    @Override
    public  void CreateProp(List<AbstractProps> props){
        for(int i=1;i<=3;i++) {
            PropCreatorPattern creator = new PropCreatorPattern();
            creator.CreatorPattern(props);
        }
    }

}




