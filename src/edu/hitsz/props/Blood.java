package edu.hitsz.props;


import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

import java.util.List;

/**
加血道具：可使英雄机恢复一定血量， 但不能超过英雄机初始的最大血量
*/

public class Blood extends  AbstractProps{


    public int power = -30;


    public Blood(int locationX, int locationY, int speedX, int speedY,int power){
        super(locationX, locationY, speedX, speedY);
        this.power=power;
    }

    /**
     *撞击道具，可使英雄机恢复一定血量， 但不能超过英雄机初始的最大血量
     */
    @Override
    public int getEffect(HeroAircraft heroAircraft, List<AbstractEnemy> enemyAircrafts, List<BaseBullet> enemyBullets) {
        heroAircraft.decreaseHp(power);
        this.vanish();
        return 0;
    }

    public int getPower() {
        return power;
    }

}
