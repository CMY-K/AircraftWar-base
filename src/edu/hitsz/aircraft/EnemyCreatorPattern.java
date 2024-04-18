package edu.hitsz.aircraft;

import java.util.List;
import java.util.Random;

public class EnemyCreatorPattern {

    public void CreatorPattern(List<AbstractEnemy> enemyAircrafts,int enemyMaxNumber,int score) {
        Random rand = new Random();


        EnemyCreator enemyCreator;
        AbstractEnemy enemy = null;

        if (enemyAircrafts.size() < enemyMaxNumber && score < 100) {
            if (rand.nextDouble() < 0.5) {

                enemyCreator = new MobCreator();
                enemy = enemyCreator.createEnemy();
                enemyAircrafts.add(enemy);
            }
            else if(rand.nextDouble() < 0.65) {

                enemyCreator = new ElitePlusCreator();
                enemy = enemyCreator.createEnemy();
                enemyAircrafts.add(enemy);
            }
            else {
                enemyCreator = new EliteCreator();
                enemy = enemyCreator.createEnemy();
                enemyAircrafts.add(enemy);
            }

        } else if (enemyAircrafts.size() < enemyMaxNumber) {
            if (rand.nextDouble() < 0.1) {
                enemyCreator = new MobCreator();
                enemy = enemyCreator.createEnemy();
                enemyAircrafts.add(enemy);
            } else if (rand.nextDouble() < 0.2) {
                enemyCreator = new EliteCreator();
                enemy = enemyCreator.createEnemy();
                enemyAircrafts.add(enemy);
            }
            else if(rand.nextDouble() < 0.3) {

                enemyCreator = new ElitePlusCreator();
                enemy = enemyCreator.createEnemy();
                enemyAircrafts.add(enemy);
            }
            else {
                enemyCreator = new BossCreator();
                enemy = enemyCreator.createEnemy();
                enemyAircrafts.add(enemy);
            }
        }
    }
}
