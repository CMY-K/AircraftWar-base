package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.CircumShoot;
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
        this.setStrategy(new CircumShoot());
    }


    @Override
    public  void CreateProp(List<AbstractProps> props){
        for(int i=1;i<=3;i++) {
            PropCreatorPattern creator = new PropCreatorPattern();
            creator.CreatorPattern(props);
        }
    }

}




