package com.browserStack.utilities;

import java.util.*;

public class HeaderAnalyzer {

    public static void analyzeRepeatedWords(List<String> translatedTitles) {
        Map<String, Integer> wordCount = new HashMap<>();

        for (String title : translatedTitles) {
            String cleaned = title.toLowerCase().replaceAll("[^a-zA-Z ]", "");
            String[] words = cleaned.split("\\s+");

            for (String word : words) {
                if (!word.isBlank()) {
                    wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                }
            }
        }

        System.out.println("Repeated words (more than twice):");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            if (entry.getValue() > 2) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}
