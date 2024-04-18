package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class BossCreator implements EnemyCreator {

    private int locationX = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())) * 1;
    private int locationY = (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2) * 1;

    private  int speedX = 5;

    private int speedY = 0;

    private int hp = 100;

    private int score = 70;
    @Override
    public AbstractEnemy createEnemy() {
        return new BossEnemy(locationX,locationY, speedX,  speedY, hp, score);
    }


    public int getScore(){return this.score;}
    public int getHp(){return this.hp;}

    public int getLocationX(){return this.locationX;}

    public int getLocationY(){return  this.locationY;}
    public int getSpeedX(){return this.speedX;}
    public int getSpeedY(){return this.speedY;}
}
