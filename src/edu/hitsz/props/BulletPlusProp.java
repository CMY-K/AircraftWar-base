package edu.hitsz.props;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.CircumShoot;
import edu.hitsz.bullet.DirectShoot;
import edu.hitsz.bullet.ScatterShoot;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



/**
 火力道具：英雄机切换弹道并持续一段时间，结束后恢复原状态
 */

public class BulletPlusProp extends  AbstractProps{

    public BulletPlusProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    /**
     *撞击道具，英雄机切换弹道（速度/个数/伤害值）并持续一段时间，结束后恢复原状态
     */
    @Override
    public int getEffect(HeroAircraft heroAircraft, List<AbstractEnemy> enemyAircrafts, List<BaseBullet> enemyBullets) {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        heroAircraft.setStrategy(new CircumShoot());

        // 延迟5秒后执行恢复默认射击次数的操作
        scheduler.schedule(() -> {
            heroAircraft.setStrategy(new DirectShoot());
        }, 5, TimeUnit.SECONDS);
        System.out.println("FireSupply active!");
        this.vanish();
        return 0;
    }
}
