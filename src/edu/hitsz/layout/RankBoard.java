package edu.hitsz.layout;

import edu.hitsz.dao.Player;
import edu.hitsz.dao.PlayerDao;
import edu.hitsz.dao.PlayerDaolmpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class RankBoard {
    private JPanel MainPanel;
    private JPanel TopPanel;
    private JPanel BottomPanel;
    private JTable RankTable;
    private JLabel HeaderLabel;
    private JButton DeleteButton;

    private JScrollPane tableScrollPanel;

    private List<Player> players;

    private PlayerDaolmpl playerDao;

    public RankBoard(PlayerDaolmpl playerDao){
        this.playerDao=playerDao;
        playerDao.readFromTextFile("game_scores.txt");


        //显示排行榜
        DisplayRank();

        //玩家姓名提示
       // showInputDialog();

        //删除
        DeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = RankTable.getSelectedRow();
                System.out.println(row);
                int result = JOptionPane.showConfirmDialog(DeleteButton,
                        "是否确定删除选中的玩家？","选择一个选项",1);
                System.out.println(row);
                if (JOptionPane.YES_OPTION == result && row != -1) {
                    playerDao.doDelete(row);
                    playerDao.writeToTextFile("game_scores.txt");
                    players=playerDao.getAllPlayers();

                    String[] columnName = {"名次","玩家名","得分","记录时间"};

                    String[][] tableData = new String[players.size()][4]; // 假设有4列数据

                    // 将玩家数据写入到tableData中
                    for (int i = 0; i < players.size(); i++) {
                        Player player = players.get(i);
                        tableData[i][0] = String.valueOf(i+1);// 第一列是名次
                        tableData[i][1] = player.getName(); // 第二列是玩家名
                        tableData[i][2] = String.valueOf(player.getScore()); // 第三列是得分
                        tableData[i][3] = player.getTime(); // 第四列是记录时间
                    }

                    //表格模型
                    DefaultTableModel model = new DefaultTableModel(tableData, columnName){

                        @Override
                        public boolean isCellEditable(int row, int col){
                            return false;
                        }
                    };

                    //JTable并不存储自己的数据，而是从表格模型那里获取它的数据
                    RankTable.setModel(model);
                    tableScrollPanel.setViewportView(RankTable);

                }
            }
        });
    }



    // 方法用于弹出输入对话框获取玩家姓名，并将其添加到排行榜中
    public void showInputDialog() {


        // 弹出输入对话框
        String playerName = JOptionPane.showInputDialog(TopPanel, "请输入玩家姓名：");
        if (playerName != null && !playerName.isEmpty()) {
            // 在控制台输出玩家姓名
            System.out.println("玩家姓名：" + playerName);
            // 添加玩家姓名到排行榜表格中

            // 获取当前时间
            Date currentTime = new Date();

            // 设置日期格式
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // 格式化当前时间为指定格式的字符串
            String formattedTime = dateFormat.format(currentTime);

            Player player = new Player(playerDao.getScore(), playerName, formattedTime);

            playerDao.doAdd(player);
            playerDao.writeToTextFile("game_scores.txt");

        }
        DisplayRank();
    }

    public void DisplayRank() {

        players=playerDao.getAllPlayers();

        String[] columnName = {"名次","玩家名","得分","记录时间"};

        String[][] tableData = new String[players.size()][4]; // 假设有4列数据

        // 将玩家数据写入到tableData中
        for (int i = 0; i < players.size(); i++) {
            Player playerCopy = players.get(i);
            tableData[i][0] = String.valueOf(i+1);// 第一列是名次
            tableData[i][1] = playerCopy.getName(); // 第二列是玩家名
            tableData[i][2] = String.valueOf(playerCopy.getScore()); // 第三列是得分
            tableData[i][3] = playerCopy.getTime(); // 第四列是记录时间
        }
        DefaultTableModel model = new DefaultTableModel(tableData, columnName){

            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };

        RankTable.setModel(model);
        tableScrollPanel.setViewportView(RankTable);

    }

    public JPanel getMainPanel() {
        return MainPanel;
    }
}
