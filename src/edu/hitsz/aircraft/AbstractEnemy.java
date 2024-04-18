package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.props.AbstractProps;
import edu.hitsz.props.*;


import java.util.List;

/**
 * 敌机的抽象父类：
 * 敌机（BOSS, ELITE, MOB）
 *
 * @author hitsz
 */

public abstract class AbstractEnemy extends AbstractAircraft{
    /**
     * 歼灭该敌机可以获得的分数
     */



    protected  int score;

    public AbstractEnemy(int locationX, int locationY, int speedX, int speedY, int hp, int score){
        super(locationX,locationY,speedX,speedY,hp);
        this.score=score;
    }



    public abstract List<BaseBullet> shoot();

    @Override
    public void forward() {
        super.forward();
        if(locationY>=Main.WINDOW_HEIGHT){
            this.vanish();
        }
    }

    public abstract void CreateProp(List<AbstractProps> props);

}
