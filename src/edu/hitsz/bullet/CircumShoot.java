package edu.hitsz.bullet;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;

import java.util.LinkedList;
import java.util.List;

public class CircumShoot implements ShootStrategy{
    @Override
    public List<BaseBullet> executeShooting(AbstractAircraft aircraft){
        List<BaseBullet> res = new LinkedList<>();
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() ;
        int speedX = 0;
        int speedY = aircraft.getSpeedY() + aircraft.getDirection()*5;
        BaseBullet bullet;
        double radius = 150; // 环的半径
        for (int i = 0; i < 20; i++) {
            if(aircraft instanceof HeroAircraft)
            {
                bullet = new HeroBullet((int)(x + Math.sin(Math.PI/10*i)*radius ), (int)(y + Math.cos(Math.PI/10*i)*radius), (int)( Math.sin(Math.PI/10*i)*3 ) , (int)( Math.cos(Math.PI/10*i)*3), aircraft.getPower());
            }
            else{
                bullet = new EnemyBullet((int)(x + Math.sin(Math.PI/10*i)*radius ), (int)(y + Math.cos(Math.PI/10*i)*radius), (int)( Math.sin(Math.PI/10*i)*3 ) , (int)( Math.cos(Math.PI/10*i)*3), aircraft.getPower());
            }

            res.add(bullet);
        }

        return res;
    }
}
