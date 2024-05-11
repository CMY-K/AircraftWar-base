package edu.hitsz.Game;

import edu.hitsz.application.HeroController;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.dao.*;
import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.layout.StartPage;
import edu.hitsz.music.MusicThread;
import edu.hitsz.music.PropMusic;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import edu.hitsz.props.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public class Game extends JPanel {


    protected int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    protected final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    protected int timeInterval = 40;

    protected final HeroAircraft heroAircraft;
    protected final List<AbstractEnemy> enemyAircrafts;
    protected final List<BaseBullet> heroBullets;
    protected final List<BaseBullet> enemyBullets;

    protected final List<AbstractProps> props ;

    protected final List<Player> players ;

    protected MusicThread musicThread;
    protected MusicThread bulletHitSound;
    protected MusicThread bombExplosionSound;
    protected MusicThread supplyEffectSound;
    protected MusicThread playGameOverSound;
    protected MusicThread bossSound;

    public static boolean openMusic;

    protected String difficulty;

    protected BufferedImage backGround;


    /**
     * 屏幕中出现的敌机最大数量
     */
    protected int enemyMaxNumber = 5;

    /**
     * 当前得分
     */
    protected int score = 0;
    /**
     * 当前时刻
     */
    protected int time = 0;

    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    protected int cycleDuration = 600;
    protected int cycleTime = 0;

    /**
     * 游戏结束标志
     */
    protected boolean gameOverFlag = false;

    public Game() {
        /**
         * 单例模式创造英雄机
         */
        heroAircraft = HeroAircraft.getInstance();

        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        props = new LinkedList<>();
        players = new LinkedList<>();

        musicThread = null;
        bulletHitSound = null;
       bombExplosionSound = null;
       supplyEffectSound = null;
        playGameOverSound = null;
        /**
         * Scheduled 线程池，用于定时任务调度
         * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
         * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {


        this.startMusic();

        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;

            Random rand = new Random();

            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                System.out.println(time);
                // 新敌机产生
                EnemyCreatorPattern enemyCreatorPattern = new EnemyCreatorPattern();
                enemyCreatorPattern.CreatorPattern(
                        enemyAircrafts,
                        enemyMaxNumber,
                        score
                );
                shootAction();
            }

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            // 道具移动
            propsMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查英雄机是否存活
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                executorService.shutdown();
                gameOverFlag = true;
                System.out.println("Game Over!");
                if(this.openMusic) {
                    playGameOverSound = new PropMusic("src/videos/game_over.wav");
                    playGameOverSound.start();
                }
            }

            //游戏结束
            if(gameOverFlag){

                if(this.openMusic) {
                    this.musicThread.stopMusic();
                }

                //存储数据
                DaoPattern daoPattern = new DaoPattern();
                daoPattern.DaoPatternNow(score);


            }
        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    //***********************
    //      Action 各部分
    //***********************

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void shootAction() {
        // TODO 敌机射击
        for (AbstractEnemy enemyAircraft : enemyAircrafts) {
            enemyBullets.addAll(enemyAircraft.shoot());
        }
        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot());
    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractEnemy enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();

        }
    }

    private void propsMoveAction() {
        for (AbstractProps prop : props) {
            prop.forward();
        }
    }


    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // TODO 敌机子弹攻击英雄
        for(BaseBullet bullet:enemyBullets){
            if(bullet.notValid()){
                //子弹无效，空弹
                continue;
            }
            //英雄机只有一个
            if(heroAircraft.notValid()){
                // 已被其他子弹击毁的敌机，不再检测
                // 避免多个子弹重复击毁英雄机的判定
                continue;
            }
            if(heroAircraft.crash(bullet)){
                // 英雄机撞击到敌机子弹
                // 英雄机损失一定生命值
                heroAircraft.decreaseHp((bullet.getPower()));
                bullet.vanish();
                if(heroAircraft.notValid()){
                    for(AbstractAircraft enemyAircraft :enemyAircrafts){
                        enemyAircraft.vanish();
                    }
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }
        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractEnemy enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    if(enemyAircraft instanceof BossEnemy){
                        ((BossEnemy) enemyAircraft).endMusic();
                    }
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();

                    if (enemyAircraft.notValid()) {
                        // TODO 获得分数，产生道具补给
                        // 播放击中敌机的音效
                        if(enemyAircraft instanceof BossEnemy &&  this.openMusic){
                            //this.musicThread.stopMusic();
                        }
                        if(this.openMusic) {
                            bulletHitSound = new PropMusic("src/videos/bullet_hit.wav");
                            bulletHitSound.start();
                        }
                        enemyAircraft.CreateProp(props);
                        score+=10;
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        // Todo: 我方获得道具，道具生效


        for(AbstractProps prop:props){
            if(prop.notValid()){
                //无效
                continue;
            }
            if(heroAircraft.notValid()){
                // 已被其他子弹击毁的敌机，不再检测
                continue;
            }
            if(heroAircraft.crash(prop)) {
                if(prop instanceof Bomb &&  this.openMusic){
                    bombExplosionSound = new PropMusic("src/videos/bomb_explosion.wav");
                    bombExplosionSound.start();
                }
                else if(this.openMusic){
                    supplyEffectSound = new PropMusic("src/videos/get_supply.wav");
                    supplyEffectSound.start();
                }
                score+=prop.getEffect(heroAircraft,enemyAircrafts,enemyBullets);
            }
        }
    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        props.removeIf(AbstractFlyingObject::notValid);
    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param  g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(this.backGround, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(this.backGround, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }


        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);

        paintImageWithPositionRevised(g, enemyAircrafts);
        paintImageWithPositionRevised(g, props);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }

    public void startMusic() {
        if(StartPage.open) {
            this.musicThread = new MusicThread("src/videos/bgm.wav");
            this.musicThread.start();
            this.openMusic=true;
        }
        else{
            this.openMusic=false;
        }
    }

}