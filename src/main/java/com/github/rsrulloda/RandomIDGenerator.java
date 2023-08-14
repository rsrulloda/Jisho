package com.github.rsrulloda;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomIDGenerator {
    private int max;
    private Map<LocalDate, Integer> generatedIntegers;
    private File file;

    public RandomIDGenerator(int max) {
        this.max = max;
        generatedIntegers = new HashMap<>();
        file = new File("C:\\Users\\ronel\\Documents\\Jisho\\src\\main\\resources\\usedIntegers.txt");
        loadIntegersFromFile();
    }

    public int getRandomID() {
        LocalDate today = LocalDate.now();

        if (generatedIntegers.containsKey(today)) {
            return generatedIntegers.get(today);
        } else {
            int randomID = generateNewRandomID();
            generatedIntegers.put(today, randomID);
            saveIntegersToFile();
            return randomID;
        }
    }

    private int generateNewRandomID() {
        int newRandomID;
        Random random = new Random();

        do {
            newRandomID = random.nextInt(max) + 1;
        } while (generatedIntegers.containsValue(newRandomID));

        return newRandomID;
    }

    private void loadIntegersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    LocalDate date = LocalDate.parse(parts[0]);
                    int value = Integer.parseInt(parts[1]);
                    generatedIntegers.put(date, value);
                }
            }
        } catch (IOException e) {
            // Handle file reading error
        }
    }

    private void saveIntegersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<LocalDate, Integer> entry : generatedIntegers.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            // Handle file writing error
        }
    }
}