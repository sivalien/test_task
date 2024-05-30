package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileManager {
    public static Map<String, List<Replica>> read(String filePath) {
        Map<String, List<Replica>> data = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String character = parts[0].trim();
                    String words = parts[1].trim();

                    Replica replica = new Replica(lineNumber, words);
                    data.computeIfAbsent(character, k -> new ArrayList<>()).add(replica);

                    lineNumber++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
