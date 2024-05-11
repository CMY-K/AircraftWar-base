package edu.hitsz.dao;

import edu.hitsz.application.Main;
import edu.hitsz.layout.RankBoard;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Scanner;


public class DaoPattern {

    public static final CardLayout cardLayout = new CardLayout(0,0);
    public static final JPanel cardPanel = new JPanel(cardLayout);

    private List<Player> players;

    private int score;
    private  PlayerDaolmpl playerDao;

    public  void DaoPatternNow(int score) {

        this.score=score;
        Scanner scanner = new Scanner(System.in);
        playerDao = new PlayerDaolmpl(score);
        System.out.println("hh");
        this.Display();

    }

    public void Display(){
        RankBoard rankBoard = new RankBoard(playerDao);
        Main.cardPanel.add(rankBoard.getMainPanel());
        Main.cardLayout.last(Main.cardPanel);
        rankBoard.showInputDialog();
    }
}

