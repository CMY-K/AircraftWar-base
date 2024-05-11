package edu.hitsz.layout;

import edu.hitsz.Game.EaasyGame;
import edu.hitsz.Game.Game;
import edu.hitsz.Game.HardGame;
import edu.hitsz.Game.MediumGame;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class StartPage {
    private JButton EasyPattern;
    private JButton NormalPattern;
    private JButton HardPattern;
    private JPanel MainPanel;
    private JPanel PatternPanel;
    private JPanel MusicPanel;
    private JComboBox MuiscBox;
    private JLabel MuiscLabel;

    public static boolean open =false;

    public StartPage() {
        MuiscBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取选中的选项
                String selectedOption = (String) MuiscBox.getSelectedItem();

                // 根据选项执行相应的操作
                switch (selectedOption) {
                    case "开":
                        // 执行选项1的操作
                        open=true;
                        //game.startMusic();
                        break;
                    case "关":
                        // 执行选项2的操作
                        open=false;
                        //System.out.println("dd");
                        break;
                    // 添加更多的选项和相应的操作
                    default:
                        open=false;
                        break;
                }
            }

        });

        EasyPattern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EaasyGame game = new EaasyGame();
                Main.cardPanel.add(game);
                Main.cardLayout.last(Main.cardPanel);
                game.action();

            }
        });
        NormalPattern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MediumGame game = new MediumGame();
                Main.cardPanel.add(game);
                Main.cardLayout.last(Main.cardPanel);
                game.action();

            }
        });

        HardPattern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HardGame game = new HardGame();
                Main.cardPanel.add(game);
                Main.cardLayout.last(Main.cardPanel);
                game.action();
            }
        });


    }

    private void createUIComponents() {

    }


    public JPanel getMainPanel() {
        return MainPanel;
    }


}
