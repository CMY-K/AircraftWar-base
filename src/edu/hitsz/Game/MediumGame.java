package edu.hitsz.Game;

import edu.hitsz.application.ImageManager;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class MediumGame extends Game{

    public MediumGame(){
        super();
        this.difficulty="Medium";
        this.backGround=ImageManager.BACKGROUND_IMAGE_MEDIUM;
    }
}
