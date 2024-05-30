package com.company;


public class Main {

    public static void main(String[] args) {
        PersonManager manager = new PersonManager(FileManager.read("task1/scenario.txt"));
        manager.play();
    }
}
