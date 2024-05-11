package edu.hitsz.Game;

import edu.hitsz.application.ImageManager;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class HardGame extends Game{
    public HardGame(){
        super();
        this.difficulty="Hard";
        this.backGround=ImageManager.BACKGROUND_IMAGE_HARD;
    }
}
