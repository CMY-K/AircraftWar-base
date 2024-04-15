package edu.hitsz.props;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public class Blood extends AbstractProps {

    private int power = -30;


    public Blood(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    /**
     *撞击道具，可使英雄机恢复一定血量， 但不能超过英雄机初始的最大血量
     */
    @Override
    public int getEffect(HeroAircraft heroAircraft, List<AbstractEnemy> enemyAircrafts, List<BaseBullet> enemyBullets) {
        heroAircraft.decreaseHp(power);
        heroAircraft.decreaseHp(this.power);
        this.vanish();
        return 0;
    }

    public int getPower() {
        return power;
    }
}
