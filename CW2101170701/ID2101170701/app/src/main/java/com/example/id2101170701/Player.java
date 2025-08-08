package com.example.id2101170701;

public class Player {
    private long id;
    private String name;
    private int score;

    // Constructor without parameters
    public Player() {
    }

    // Constructor with parameters
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // Constructor with id, name, and score
    public Player(long id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    // Getter and setter for id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // Getter and setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter for score
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}


