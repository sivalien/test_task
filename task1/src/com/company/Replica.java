package com.company;

public class Replica {
    private final String words;
    private final int number;

    public Replica(int number, String words) {
        this.number = number;
        this.words = words;
    }

    public String getWords() {
        return words;
    }

    public int getNumber() {
        return number;
    }
}
