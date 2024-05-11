package edu.hitsz.Dao;

import java.io.*;
import java.util.*;

public class PlayerDaolmpl implements PlayerDao{

    // 从文件中读取玩家列表
    private List<Player> players;

    public PlayerDaolmpl(){
        players = new LinkedList<>();
    }
    // 从文件中读取玩家列表
    @Override
    public void readFromTextFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String playerName = data[0].trim();
                    int score = Integer.parseInt(data[1].trim());
                    String recordTime = data[2].trim();
                    players.add(new Player(score,playerName, recordTime));
                }
            }
            System.out.println("成功从文件读取玩家列表：" + fileName);
        } catch (IOException e) {
            System.err.println("读取文件时出错: " + e.getMessage());
        }
    }

    // 将玩家列表写入文件
    @Override
    public void writeToTextFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Player player : players) {
                writer.println(player.getName() + "," + player.getScore() + "," + player.getTime());
            }
            System.out.println("玩家列表已成功写入文件：" + fileName);
        } catch (IOException e) {
            System.err.println("写入文件时出错: " + e.getMessage());
        }
    }

    // 打印玩家列表（按分数从高到低）
    @Override
    public void printPlayersByScore() {
        // 先按分数降序排序
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Integer.compare(p2.getScore(), p1.getScore());
            }
        });
        int cnt=0;
        // 打印排序后的玩家列表
        for (Player player : players) {
            cnt++;
            System.out.println("rank" + cnt + " " + player.getName()+" "+player.getScore()+" "+ player.getTime());
        }
    }


    // 添加玩家
    public void doAdd(Player player) {
        this.players.add(player);
    }

    // 根据玩家名查找玩家
    public void findPlayer(String name) {
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(name)) {
                System.out.println("找到玩家：" + player);
                return;
            }
        }
        System.out.println("未找到名为 " + name + " 的玩家。");
    }

    // 获取所有玩家
    public List<Player> getAllPlayers() {
        return players;
    }


    // 删除玩家
    public void doDelete(String name) {
        Iterator<Player> iterator = players.iterator();
        while (iterator.hasNext()) {
            Player player = iterator.next();
            if (player.getName().equalsIgnoreCase(name)) {
                iterator.remove();
                System.out.println("已删除玩家：" + player);
                return;
            }
        }
        System.out.println("未找到名为 " + name + " 的玩家，无法删除。");
    }

}
