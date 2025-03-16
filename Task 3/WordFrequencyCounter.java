import java.io.*;
import java.util.*;

public class WordFrequencyCounter {
    public static void main(String[] args) {
        String fileName = "input.txt";
        Map<String, Integer> wordFrequency = countWordFrequency(fileName);
        List<Map.Entry<String, Integer>> sortedList = sortByFrequency(wordFrequency);
        displayTopWords(sortedList, 5);
    }

    private static Map<String, Integer> countWordFrequency(String fileName) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\W+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        word = word.toLowerCase();
                        wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordFrequency;
    }

    private static List<Map.Entry<String, Integer>> sortByFrequency(Map<String, Integer> wordFrequency) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordFrequency.entrySet());
        list.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        return list;
    }

    private static void displayTopWords(List<Map.Entry<String, Integer>> sortedList, int topN) {
        int count = 0;
        for (Map.Entry<String, Integer> entry : sortedList) {
            if (count >= topN) break;
            System.out.println(entry.getKey() + ": " + entry.getValue());
            count++;
        }
    }
}