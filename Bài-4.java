import java.util.*;
import java.io.*;

public class BayesianTextGenerator {
    private static Map<String, Integer> vocab = new HashMap<>();
    private static Map<String, Integer> corpus = new HashMap<>();
    private static Map<String, Integer> pairCorpus = new HashMap<>();
    private static Map<String, Double> probs = new HashMap<>();
    private static Map<String, Double> conditionalProbs = new HashMap<>();

    public static void readFile(String filename) {
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().toLowerCase().trim();
                String[] words = line.split("\\s+");
                
                for (int i = 0; i < words.length; i++) {
                    corpus.put(words[i], corpus.getOrDefault(words[i], 0) + 1);
                    if (i < words.length - 1) {
                        String pair = words[i] + "_" + words[i + 1];
                        pairCorpus.put(pair, pairCorpus.getOrDefault(pair, 0) + 1);
                    }
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File không tồn tại!");
        }
    }

    public static void calculateProbabilities() {
        int totalWords = corpus.values().stream().mapToInt(Integer::intValue).sum();
        for (Map.Entry<String, Integer> entry : corpus.entrySet()) {
            probs.put(entry.getKey(), (double) entry.getValue() / totalWords);
        }
        
        for (Map.Entry<String, Integer> entry : pairCorpus.entrySet()) {
            String[] words = entry.getKey().split("_");
            String word1 = words[0];
            conditionalProbs.put(entry.getKey(), (double) entry.getValue() / corpus.get(word1));
        }
    }

    public static List<String> generateText(String startWord, int maxWords) {
        List<String> text = new ArrayList<>();
        text.add(startWord);
        
        String currentWord = startWord;
        for (int i = 1; i < maxWords; i++) {
            String nextWord = null;
            double maxProb = 0.0;
            
            for (String word : corpus.keySet()) {
                String pair = currentWord + "_" + word;
                if (conditionalProbs.containsKey(pair) && conditionalProbs.get(pair) > maxProb) {
                    maxProb = conditionalProbs.get(pair);
                    nextWord = word;
                }
            }
            
            if (nextWord == null) break;
            text.add(nextWord);
            currentWord = nextWord;
        }
        
        return text;
    }

    public static void main(String[] args) {
        readFile("UIT-ViOCD.txt");
        calculateProbabilities();
        List<String> sentence = generateText("hàng", 5);
        System.out.println(String.join(" ", sentence));
    }
}
