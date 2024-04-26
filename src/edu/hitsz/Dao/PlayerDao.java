package edu.hitsz.Dao;

import java.util.List;

public interface PlayerDao {

    void findPlayer(String name);


    List<Player> getAllPlayers();

    void doAdd(Player player);

    void doDelete(String name);

    void readFromTextFile(String filename);
    void writeToTextFile(String fileName);
     void printPlayersByScore();

}


