package edu.hitsz.Game;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class EaasyGame extends Game{
    public EaasyGame(){
        super();
        this.difficulty="Easy";
        this.backGround=ImageManager.BACKGROUND_IMAGE_EASY;
    }
}
