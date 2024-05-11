package edu.hitsz.music;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.BossEnemy;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class BossMusic extends MusicThread{

    public BossMusic(String filename) {
        super(filename);
    }

    @Override
    public void run() {
        boolean flag=false;
        // 循环播放音乐，直到线程被停止
        for(AbstractEnemy enemyAircraft : enemyAircrafts){
            if(enemyAircraft instanceof BossEnemy){
                flag=true;
            }
        }
        if(!flag) {
            this.stopMusic();
        }else{
            while (isRunning) {
                // 创建音频流
                InputStream stream = new ByteArrayInputStream(samples);
                // 播放音乐
                play(stream);
            }
        }
    }
}
