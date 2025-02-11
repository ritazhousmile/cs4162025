import java.util.Scanner;

/**
 * @author huan zhou
 * @version 02/10/2025
 */

public class ReadingScore {

    public static String formatToken(String token) {
        return token.replaceAll("[^a-zA-Z]", "");
        }
    
    
    public static String[] tokenize(String text) {
        //Split based on one or more spaces //s one white space
        return text.split("\\s+");
    }

    public static int getSentenceCount(String[] tokens) {
        /* 
        int count = 0;
        for(String token: tokens){
            if (token.matches(".*[.;?!].*")){
                count++;
            }
        }
        return count;
        */
        int sentenceCount = 0; // Set a sentence counter to 0

        // Go through each token in the string array
        for (String token : tokens) {
            // Check if the token is not empty to avoid errors with empty strings
            if (!token.isEmpty()) {
                // Get the last character of the token
                char lastChar = token.charAt(token.length() - 1);

                // If the last character is any of .:;?!, increment sentence counter
                if (lastChar == '.' || lastChar == ':' || lastChar == ';' || 
                    lastChar == '?' || lastChar == '!') {
                    sentenceCount++;
                }
            }
        }
        return sentenceCount; // Return the total count of sentences

    }


    public static int getTokenCount(String[] tokens) {
        return tokens.length;
    }

    public static int getSyllableCount(String word) {
        int count = 0;
        word = formatToken(word).toLowerCase();
        String vowels = "aeiouy";
        for (int i = 0; i < word.length();i++){
            // the character at a specific index in a string.
            char currentChar = word.charAt(i);
            //The indexOf(char) method returns the index of the first occurrence of a character in a string, or -1 if it’s not found.
            if (vowels.indexOf(currentChar) != -1){
                count++;
            }
        }

        if (word.endsWith("e") && count > 1) {
            count--;
        }

        // If syllable count is 0 or less, set it to 1 (every word has at least 1 syllable)
        if (count < 1) {
            count = 1;
        }

        return count;
    }


    public static double getTotalSyllableCount(String[] tokens) {
        int totalCount = 0;
        for(String token: tokens){
            totalCount += getSyllableCount(token);
        }
        return totalCount*0.88;

    }

    public static int getReadingScore(String text) {
        // Tokenize the text
        String[] tokens = tokenize(text);
        
        // Calculate total tokens, total sentences, and total syllables
        int totalTokens = getTokenCount(tokens);
        int totalSentences = getSentenceCount(tokens);
        double totalSyllables = getTotalSyllableCount(tokens);

        // Use the reading score formula
        double readingScore = 206.835 - 1.015 * ((double) totalTokens / totalSentences) 
                                - 84.6 * (totalSyllables / totalTokens);

        // Round the result
        int roundedScore = (int) Math.round(readingScore);

        // Ensure the score does not exceed 100
        return Math.min(roundedScore, 100);
    }

    public static void main(String[] args) {
        // Create a Scanner to read input from the user
        Scanner scanner = new Scanner(System.in);

        // Read the input text from the user
        String text = scanner.nextLine();

        // Calculate the reading score
        int readingScore = getReadingScore(text);

        // Print out the reading score
        System.out.println(readingScore);

        // Provide the explanation of reading difficulty
        if (readingScore >= 90) {
            System.out.println("Very easy to read. Easily understood by an average 11-year-old student.");
        } else if (readingScore >= 80) {
            System.out.println("Easy to read.");
        } else if (readingScore >= 70) {
            System.out.println("Fairly easy to read.");
        } else if (readingScore >= 60) {
            System.out.println("Standard. Easily understood by 13- to 15-year-old students.");
        } else if (readingScore >= 50) {
            System.out.println("Fairly difficult to read.");
        } else if (readingScore >= 30) {
            System.out.println("Difficult to read.");
        } else {
            System.out.println("Very difficult to read. Best understood by university graduates.");
        }

        // Close the scanner
        scanner.close();
    }   

}
