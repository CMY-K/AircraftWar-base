package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class EliteCreator implements EnemyCreator {


    private int locationX = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())) * 1;
    private int locationY = (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2) * 1;

    private  int speedX = 5;

    private int speedY = 5;

    private int hp = 50;

    private int score = 50;

    @Override
    public AbstractEnemy createEnemy() {
        return new EliteEnemy(this.locationX,this.locationY, this.speedX,  this.speedY, this.hp, this.score);
    }

}
