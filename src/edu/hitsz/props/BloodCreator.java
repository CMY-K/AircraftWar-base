package edu.hitsz.props;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class BloodCreator implements PropCreator {

    private int locationX = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())) * 1;
    private int locationY = (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2) * 1;

    private  int speedX = 0;

    private int speedY = 10;

    @Override
    public AbstractProps createProp() {
        return new Blood(this.locationX,this.locationY, this.speedX,  this.speedY);
    }
}
