package edu.hitsz.props;


import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.application.Game;


import java.util.List;

public abstract class AbstractProps extends AbstractFlyingObject {
    public AbstractProps (int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    public abstract int getEffect(HeroAircraft heroAircraft, List<AbstractAircraft> enemyAircrafts, List<BaseBullet> enemyBullets);

}
