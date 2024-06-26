package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.DirectShoot;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.props.AbstractProps;
import edu.hitsz.props.*;

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
        this.strategy= new DirectShoot();
    }


    @Override
    public  void CreateProp(List<AbstractProps> props){
        PropCreatorPattern creator=new PropCreatorPattern();
        creator.CreatorPattern(props);

    }

}
