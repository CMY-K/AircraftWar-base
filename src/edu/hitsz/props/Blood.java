package edu.hitsz.props;


import edu.hitsz.aircraft.AbstractAircraft;
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

    /** 攻击方式 */

    public int power = -30;

    public static final int WIDTH=6;
    public static final int HEIGHT=18;

    public Blood(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
        this.power=power;
    }
    public int getPower(){
        return this.power;
    }
    @Override
    public int getEffect(HeroAircraft heroAircraft, List<AbstractAircraft> enemyAircrafts, List<BaseBullet> enemyBullets) {
        heroAircraft.decreaseHp(power);
        return 0;
    }
    @Override
    public void forward() {
        super.forward();

        // 判定 x 轴出界
        if (locationX <= 0 || locationX >= Main.WINDOW_WIDTH) {
            vanish();
        }

        // 判定 y 轴出界
        if (speedY > 0 && locationY >= Main.WINDOW_HEIGHT ) {
            // 向下飞行出界
            vanish();
        }else if (locationY <= 0){
            // 向上飞行出界
            vanish();
        }
    }


}
