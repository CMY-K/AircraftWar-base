package edu.hitsz.aircraft;

import java.util.List;
import java.util.Random;

public class EnemyCreatorPattern {

    public void CreatorPattern(List<AbstractEnemy> enemyAircrafts,int enemyMaxNumber) {
        Random rand = new Random();


        EnemyCreator enemyCreator;
        AbstractEnemy enemy=null;

        if (enemyAircrafts.size() < enemyMaxNumber) {
            if (rand.nextDouble() < 0.6) {

                enemyCreator = new MobCreator();
                enemy = enemyCreator.createEnemy();
                enemyAircrafts.add(enemy);
            }
            else if (rand.nextDouble() < 0.9) {
                enemyCreator = new EliteCreator();
                enemy = enemyCreator.createEnemy();
                enemyAircrafts.add(enemy);
            } else{
                enemyCreator = new BossCreator();
                enemy = enemyCreator.createEnemy();
                enemyAircrafts.add(enemy);
            }
        }
    }
}
