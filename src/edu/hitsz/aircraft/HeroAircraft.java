package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.*;

import java.util.LinkedList;
import java.util.List;

/**
 * 单例模式创造英雄机
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */

public class HeroAircraft extends AbstractAircraft {

    private static HeroAircraft instance = null ;


    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.maxHp=hp;
        this.direction=-1;
        this.strategy=new DirectShoot();
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    public void setShootNum(int num){
        this.shootNum=Math.max(0,num);
    }


    public static synchronized HeroAircraft getInstance() {
        if (instance == null) {
            instance = new HeroAircraft(Main.WINDOW_WIDTH / 2,
                    Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
                    0, 0, 1000);
        }
        return instance;
    }

}
