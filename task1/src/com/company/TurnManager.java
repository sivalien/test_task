package com.company;

public class TurnManager {
    private int number = 0;

    public int getNumber() {
        return number;
    }

    public synchronized void nextReplica() {
        number++;
    }

    public void reset() {
        number = 0;
    }
}
