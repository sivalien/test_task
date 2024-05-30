package com.company;

import java.util.List;

public class Person implements Runnable {
    private final String name;
    private final List<Replica> replicaList;
    private final TurnManager manager;


    public Person(String name, List<Replica> replicaList, TurnManager manager) {
        this.name = name;
        this.replicaList = replicaList;
        this.manager = manager;
    }

    public List<Replica> getReplicaList() {
        return replicaList;
    }

    public String getName() {
        return name;
    }

    public TurnManager getManager() {
        return manager;
    }

    @Override
    public void run() {
        for (Replica replica : replicaList) {
            synchronized (manager) {
                while (manager.getNumber() != replica.getNumber()) {
                    try {
                        manager.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println(name + ": " + replica.getWords());
                manager.nextReplica();
                manager.notifyAll();
            }
        }
    }
}
