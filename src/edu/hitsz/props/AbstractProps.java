package edu.hitsz.props;


import edu.hitsz.aircraft.*;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.application.Main;
import java.util.List;

/**
 * 道具类。
 * 加血道具、火力道具和炸弹道具
 *
 * @author hitsz
 */

public abstract class AbstractProps extends AbstractFlyingObject {

    public  AbstractProps (int locationX,int locationY, int speedX, int speedY ){
        super(locationX,locationY, speedX,speedY );
    }

    /**
    *撞击道具，生效
     */
    public abstract int getEffect(HeroAircraft heroAircraft, List<AbstractEnemy> enemyAircrafts, List<BaseBullet> enemyBullets);

}
