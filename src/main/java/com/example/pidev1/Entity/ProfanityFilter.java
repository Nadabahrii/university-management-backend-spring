package com.example.pidev1.Entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ProfanityFilter {
    private Set<String> badWords;

    public ProfanityFilter(String badWordsFilePath) {
        this.badWords = loadBadWordsFromFile(badWordsFilePath);
    }

    private Set<String> loadBadWordsFromFile(String filePath) {
        Set<String> badWords = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                badWords.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return badWords;
    }

    public boolean containsProfanity(String text) {
        String[] words = text.split("\\s+");
        for (String word : words) {
            if (badWords.contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
