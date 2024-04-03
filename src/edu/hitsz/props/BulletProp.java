package edu.hitsz.props;


import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.bullet.BaseBullet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import java.util.List;

/**
 火力道具：英雄机切换弹道并持续一段时间，结束后恢复原状态
 */

public class BulletProp extends  AbstractProps{
    public int power = -30;

    public static final int WIDTH=6;
    public static final int HEIGHT=18;

    public BulletProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
        this.power=power;
    }
    public int getPower(){
        return this.power;
    }
    @Override
    public int getEffect(HeroAircraft heroAircraft, List<AbstractAircraft> enemyAircrafts, List<BaseBullet> enemyBullets) {

       ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        heroAircraft.setShootNum(3);

        // 延迟5秒后执行恢复默认射击次数的操作
        scheduler.schedule(() -> {
            heroAircraft.setShootNum(1);
        }, 5, TimeUnit.SECONDS);

        return 0;
    }


}
