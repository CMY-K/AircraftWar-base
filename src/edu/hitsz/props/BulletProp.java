package edu.hitsz.props;


import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.BaseBullet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import java.util.List;

/**
 火力道具：英雄机切换弹道并持续一段时间，结束后恢复原状态
 */

public class BulletProp extends  AbstractProps{

    public BulletProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    /**
     *撞击道具，英雄机切换弹道（速度/个数/伤害值）并持续一段时间，结束后恢复原状态
     */
    @Override
    public int getEffect(HeroAircraft heroAircraft, List<AbstractEnemy> enemyAircrafts, List<BaseBullet> enemyBullets) {

       ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        heroAircraft.setShootNum(3);

        // 延迟5秒后执行恢复默认射击次数的操作
        scheduler.schedule(() -> {
            heroAircraft.setShootNum(1);
        }, 5, TimeUnit.SECONDS);
        System.out.println("FireSupply active!");
        this.vanish();
        return 0;
    }
}
