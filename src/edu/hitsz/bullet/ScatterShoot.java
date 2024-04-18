package edu.hitsz.bullet;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;

import java.util.LinkedList;
import java.util.List;

public class ScatterShoot implements ShootStrategy{

    @Override
    public List<BaseBullet> executeShooting(AbstractAircraft aircraft){
        List<BaseBullet> res = new LinkedList<>();
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() + aircraft.getDirection()*2;
        int speedX = 0;
        int speedY = aircraft.getSpeedY() + aircraft.getDirection()*5;
        BaseBullet bullet;
        for(int i=0; i<3; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            if(aircraft instanceof HeroAircraft)
            {
                bullet = new HeroBullet(x + (i*2 - 2)*10, y, speedX+ (i-1)*2  , speedY, aircraft.getPower());
            }
            else{
                bullet = new EnemyBullet(x + (i*2 - 2)*10, y, speedX+ (i-1)*2 , speedY, aircraft.getPower());
            }
            res.add(bullet);
        }
        return res;
    }
}
