package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;

import java.util.List;

import static java.lang.Math.min;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITE, MOB），英雄飞机
 *
 * @author hitsz
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {
    /**
     * 生命值
     */
    protected int maxHp;
    protected int hp;
    protected int score;

    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */
    protected int shootNum = 1;

    /**
     * 子弹伤害
     */
    protected int power = 30;

    /**
     * 子弹射击方向 (向下发射：1，向上发射：-1)
     */
    protected int direction = 1;



    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp,int score) {
        super(locationX, locationY, speedX, speedY);
        this.hp=hp;
        this.score=score;
    }


    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
    }

    public void decreaseHp(int decrease){
        hp -= decrease;
        if(hp <= 0){
            hp=0;
            vanish();
        }

        hp=min(hp,maxHp);
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp(){return maxHp;}

    public int getPower(){return power;}

    public int getScore(){return score;}

    public int getShootNum(){return shootNum;}
    public int getDirection(){return direction;}

    /**
     * 飞机射击方法，可射击对象必须实现
     * @return
     *  可射击对象需实现，返回子弹
     *  非可射击对象空实现，返回null
     */
    public abstract List<BaseBullet> shoot();

}


