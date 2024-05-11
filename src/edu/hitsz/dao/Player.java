package edu.hitsz.dao;

public class Player {
    private int score;

    private String name;

    private String time;

    public Player(int score, String name, String time) {
        this.name = name;
        this.score = score;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime(){return time;}

    public void setScore(int score){
        this.score=score;
    }

    public void setTime(String time){
        this.time=time;
    }

}