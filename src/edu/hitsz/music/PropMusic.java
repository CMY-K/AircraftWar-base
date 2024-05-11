package edu.hitsz.music;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class PropMusic extends MusicThread{

    public PropMusic(String filename) {
        super(filename);
    }

    @Override
    public void run(){
            // 创建音频流
            InputStream stream = new ByteArrayInputStream(this.samples);
            // 播放音乐
            play(stream);
    }
}
