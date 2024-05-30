package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersonManager {
    private List<Person> people;
    private final TurnManager manager;

    public PersonManager(Map<String, List<Replica>> map) {
        this.manager = new TurnManager();
        this.people = new ArrayList<>();
        for (Map.Entry<String, List<Replica>> entry :map.entrySet()) {
            people.add(new Person(entry.getKey(), entry.getValue(), manager));
        }
    }

    public void play() {
        List<Thread> threads = new ArrayList<>();
        for (Person person : people) {
            threads.add(new Thread(person));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        manager.reset();
    }
}
