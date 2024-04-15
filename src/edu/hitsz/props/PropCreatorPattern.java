package edu.hitsz.props;

import java.util.List;
import java.util.Random;

public class PropCreatorPattern {

    public void CreatorPattern(List<AbstractProps> props) {
        Random rand = new Random();
        double dropProbability = 0.05; // 设定道具下落的概率为20%，每种道具的概率为0.2

        PropCreator propCreator;
        AbstractProps prop=null;

        if (rand.nextDouble() < 0.05) {
            // 生成第一种道具
            propCreator = new BloodCreator();
            prop = propCreator.createProp();

        } else if (rand.nextDouble() < 0.1) {
            // 生成第二种道具
            propCreator = new BombCreator();
            prop = propCreator.createProp();

        } else if (rand.nextDouble() < 0.2){
            // 生成第三种道具
            propCreator = new BulletCreator();
            prop = propCreator.createProp();
        }
        if(prop!=null) props.add(prop);
    }
}
