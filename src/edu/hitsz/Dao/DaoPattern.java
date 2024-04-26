package edu.hitsz.Dao;

import edu.hitsz.application.Game;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class DaoPattern {
    public  void DaoPatternNow(int score) {
        PlayerDao playerDao = new PlayerDaolmpl();
        Scanner scanner = new Scanner(System.in);

        System.out.print("你要存储信息吗？（y/n）");
        String choose = scanner.nextLine();
        if(Objects.equals(choose, "y")) {
            // 模拟游戏过程
            System.out.print("请输入玩家名：");
            String playerName = scanner.nextLine();

            // 获取当前时间
            Date currentTime = new Date();

            // 设置日期格式
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // 格式化当前时间为指定格式的字符串
            String formattedTime = dateFormat.format(currentTime);

            Player player = new Player(score, playerName, formattedTime);

            playerDao.doAdd(player);
            playerDao.readFromTextFile("game_scores.txt");

            System.out.print("你要删除信息吗？（y/n）");
            String chooseDelete = scanner.nextLine();

            if(Objects.equals(chooseDelete, "y")){
                System.out.print("请输入玩家名：");
                String playerDeleteName = scanner.nextLine();
                playerDao.doDelete(playerDeleteName);
            }
            playerDao.writeToTextFile("game_scores.txt");
            playerDao.printPlayersByScore();
        }
    }
}
