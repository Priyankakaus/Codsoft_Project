import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordCounter {
    private static final String COMMON_WORDS_FILE = "common_words.txt";
    private static Set<String> commonWords;

    public static void main(String[] args) {
        loadCommonWords();

        Scanner scanner = new Scanner(System.in);
        String input = getInputFromUser(scanner);
        if (input != null) {
            String[] words = splitIntoWords(input);
            int totalWordCount = countWords(words);
            int uniqueWordCount = countUniqueWords(words);
            Map<String, Integer> wordFrequencies = calculateWordFrequencies(words);

            System.out.println("Total Word Count: " + totalWordCount);
            System.out.println("Unique Word Count: " + uniqueWordCount);
            System.out.println("Word Frequencies:");
            for (Map.Entry<String, Integer> entry : wordFrequencies.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    private static void loadCommonWords() {
        commonWords = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(COMMON_WORDS_FILE))) {
            String word;
            while ((word = reader.readLine()) != null) {
                commonWords.add(word.toLowerCase());
            }
        } catch (IOException e) {
            System.out.println("Error loading common words.");
        }
    }

    private static String getInputFromUser(Scanner scanner) {
        System.out.print("Enter text or provide a file name: ");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("No input provided.");
            return null;
        }

        if (!isText(input)) {
            try {
                input = readFile(input);
            } catch (IOException e) {
                System.out.println("Error reading file: " + input);
                return null;
            }
        }

        return input;
    }

    private static boolean isText(String input) {
        return !input.endsWith(".txt");
    }

    private static String readFile(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(" ");
            }
        }
        return sb.toString();
    }

    private static String[] splitIntoWords(String input) {
        return input.split("[\\s\\p{Punct}]+");
    }

    private static int countWords(String[] words) {
        return words.length;
    }

    private static int countUniqueWords(String[] words) {
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
        uniqueWords.removeAll(commonWords);
        return uniqueWords.size();
    }

    private static Map<String, Integer> calculateWordFrequencies(String[] words) {
        Map<String, Integer> wordFrequencies = new HashMap<>();
        for (String word : words) {
            word = word.toLowerCase();
            if (!commonWords.contains(word)) {
                wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
            }
        }
        return wordFrequencies;
    }
}
