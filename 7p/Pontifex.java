/**
 * @author huan rand
 * @version 1
 */

import java.util.Arrays;



/**
 * The Pontifex (Solitaire) cipher implementation.
 * This class provides methods to encrypt and decrypt messages using the Pontifex algorithm.
 */
public class Pontifex {

    /**
     * Returns the encrypted format of the message, using
     * deck as the starting configuration of cards (ints).
     *
     * @param message The message to encrypt
     * @param deck The deck configuration to use for encryption
     * @return The encrypted message
     */
    public static String encrypt(String message, int[] deck) {
        // Create a copy of the deck to avoid modifying the original
        int[] deckCopy = deck.clone();
        StringBuilder encrypted = new StringBuilder();
        
        // Process each character of the message
        for (char c : message.toCharArray()) {
            // Only encrypt letters (both uppercase and lowercase)
            if (Character.isLetter(c)) {
                // Convert to uppercase and get the character number (A=0, B=1, ..., Z=25)
                int charNum = Character.toUpperCase(c) - 'A';
                
                // Generate keystream number
                int keystreamNum = keystream(deckCopy);
                
                // Add keystream number to character number (mod 26)
                int encryptedNum = Math.floorMod(charNum + keystreamNum, 26);
                
                // Convert back to uppercase character and append to result
                encrypted.append((char) (encryptedNum + 'A'));
            } else {
                // Non-letters remain unchanged
                encrypted.append(c);
            }
        }
        
        return encrypted.toString();
    }

    /**
     * Returns the original unencrypted message using the deck.
     * Note: deck should be the same int array used for encryption
     * or it will not decrypt into a readable message.
     *
     * @param message The encrypted message to decrypt
     * @param deck The deck configuration to use for decryption
     * @return The decrypted message
     */
    public static String decrypt(String message, int[] deck) {
        // Create a copy of the deck to avoid modifying the original
        int[] deckCopy = deck.clone();
        StringBuilder decrypted = new StringBuilder();
        
        // Process each character of the message
        for (char c : message.toCharArray()) {
            // Only decrypt letters (both uppercase and lowercase)
            if (Character.isLetter(c)) {
                // Convert to uppercase and get the character number (A=0, B=1, ..., Z=25)
                int charNum = Character.toUpperCase(c) - 'A';
                
                // Generate keystream number
                int keystreamNum = keystream(deckCopy);
                
                // Subtract keystream number from character number (mod 26)
                int decryptedNum = Math.floorMod(charNum - keystreamNum, 26);
                
                // Convert back to uppercase character and append to result
                decrypted.append((char) (decryptedNum + 'A'));
            } else {
                // Non-letters remain unchanged
                decrypted.append(c);
            }
        }
        
        return decrypted.toString();
    }

    /**
     * Returns the generated keystream number using the 
     * specified deck configuration (ints).
     *
     * @param deck The deck configuration to use for generating the keystream
     * @return The generated keystream number
     */
    public static int keystream(int[] deck) {
        // Step 1: Find Joker A (27) and move it down one card
        moveJokerA(deck);
        
        // Step 2: Find Joker B (28) and move it down two cards
        moveJokerB(deck);
        
        // Step 3: Triple cut (swap upper and lower partitions)
        tripleCut(deck);
        
        // Step 4: Count cut
        countCut(deck);
        
        // Step 5: Calculate keystream value
        int keystreamValue = calculateKeystreamValue(deck);
        
        // If the keystream value is a joker, repeat the process
        if (keystreamValue == 27 || keystreamValue == 28) {
            return keystream(deck);
        }
        
        return keystreamValue;
    }
    
    /**
     * Moves Joker A (27) down one position in the deck.
     *
     * @param deck The deck to modify
     */
    private static void moveJokerA(int[] deck) {
        int jokerIndex = -1;
        
        // Find Joker A's position
        for (int i = 0; i < deck.length; i++) {
            if (deck[i] == 27) {
                jokerIndex = i;
                break;
            }
        }
        
        // Move Joker A down one position
        if (jokerIndex == deck.length - 1) {
            // If at the bottom, wrap to position 1
            int temp = deck[0];
            for (int i = 0; i < jokerIndex; i++) {
                deck[i] = deck[i + 1];
            }
            deck[jokerIndex - 1] = 27;
            deck[jokerIndex] = temp;
        } else {
            // Standard swap with the card below
            int temp = deck[jokerIndex + 1];
            deck[jokerIndex + 1] = deck[jokerIndex];
            deck[jokerIndex] = temp;
        }
    }
    
    /**
     * Moves Joker B (28) down two positions in the deck.
     *
     * @param deck The deck to modify
     */
    private static void moveJokerB(int[] deck) {
        int jokerIndex = -1;
        
        // Find Joker B's position
        for (int i = 0; i < deck.length; i++) {
            if (deck[i] == 28) {
                jokerIndex = i;
                break;
            }
        }
        
        // Move Joker B down two positions with proper wrapping
        for (int i = 0; i < 2; i++) {
            if (jokerIndex == deck.length - 1) {
                // If at the bottom, wrap to position 1
                int temp = deck[0];
                for (int j = 0; j < jokerIndex; j++) {
                    deck[j] = deck[j + 1];
                }
                deck[jokerIndex - 1] = 28;
                deck[jokerIndex] = temp;
                jokerIndex = 0;
            } else {
                // Standard swap with the card below
                int temp = deck[jokerIndex + 1];
                deck[jokerIndex + 1] = deck[jokerIndex];
                deck[jokerIndex] = temp;
                jokerIndex++;
            }
        }
    }
    
    /**
     * Performs the triple cut operation.
     *
     * @param deck The deck to modify
     */
    private static void tripleCut(int[] deck) {
        // Find positions of both jokers
        int firstJokerIndex = -1;
        int secondJokerIndex = -1;
        
        for (int i = 0; i < deck.length; i++) {
            if (deck[i] == 27 || deck[i] == 28) {
                if (firstJokerIndex == -1) {
                    firstJokerIndex = i;
                } else {
                    secondJokerIndex = i;
                    break;
                }
            }
        }
        
        // Ensure firstJokerIndex <= secondJokerIndex
        if (firstJokerIndex > secondJokerIndex) {
            int temp = firstJokerIndex;
            firstJokerIndex = secondJokerIndex;
            secondJokerIndex = temp;
        }
        
        // Create new array to hold the result of the triple cut
        int[] result = new int[deck.length];
        
        // Copy lower part (below second joker) to the top
        int resultIndex = 0;
        for (int i = secondJokerIndex + 1; i < deck.length; i++) {
            result[resultIndex++] = deck[i];
        }
        
        // Copy middle part (including jokers)
        for (int i = firstJokerIndex; i <= secondJokerIndex; i++) {
            result[resultIndex++] = deck[i];
        }
        
        // Copy upper part (above first joker) to the bottom
        for (int i = 0; i < firstJokerIndex; i++) {
            result[resultIndex++] = deck[i];
        }
        
        // Copy result back to deck
        System.arraycopy(result, 0, deck, 0, deck.length);
    }
    
    /**
     * Performs the count cut operation.
     *
     * @param deck The deck to modify
     */
    private static void countCut(int[] deck) {
        // Get value of bottom card
        int bottomValue = deck[deck.length - 1];
        
        // If the bottom card is a joker, use value 27
        if (bottomValue == 27 || bottomValue == 28) {
            bottomValue = 27;
        }
        
        // Create new array to hold the result
        int[] result = new int[deck.length];
        
        // First, copy the cards that will remain at the top (after removing the counted cards)
        int resultIndex = 0;
        for (int i = bottomValue; i < deck.length - 1; i++) {
            result[resultIndex++] = deck[i];
        }
        
        // Next, add the counted cards just above the bottom card
        for (int i = 0; i < bottomValue; i++) {
            result[resultIndex++] = deck[i];
        }
        
        // Bottom card stays at the bottom
        result[deck.length - 1] = deck[deck.length - 1];
        
        // Copy result back to deck
        System.arraycopy(result, 0, deck, 0, deck.length);
    }
    
    /**
     * Calculates the keystream value from the deck.
     *
     * @param deck The deck to use for calculation
     * @return The calculated keystream value
     */
    private static int calculateKeystreamValue(int[] deck) {
        // Get value of the top card
        int topValue = deck[0];
        
        // If the top card is a joker, use value 27
        if (topValue == 27 || topValue == 28) {
            topValue = 27;
        }
        
        // The value is the position to look at (1-indexed)
        // If the value is 28, it wraps around to position 28 (the bottom card)
        int position = topValue % deck.length;
        int keystreamCard = deck[position];
        
        return keystreamCard;
    }

    /**
     * Use this main function for your local tests.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Test 1: Hello World encryption with provided deck
        int[] deck1 = {22, 8, 25, 23, 15, 5, 1, 12, 13, 26, 9, 18, 14, 6, 28, 19, 21, 17, 20, 3, 10, 16, 27, 24, 11, 7, 4, 2};
        String message1 = "Hello World";
        String encrypted1 = encrypt(message1, deck1.clone());
        System.out.println("Test 1:");
        System.out.println("Message: " + message1);
        System.out.println("Expected: UVCCU MNUFA");
        System.out.println("Encrypted: " + encrypted1);
        System.out.println("Match: " + encrypted1.equals("UVCCU MNUFA"));
        
        // Test 2: Hello World decryption
        int[] deck2 = {22, 8, 25, 23, 15, 5, 1, 12, 13, 26, 9, 18, 14, 6, 28, 19, 21, 17, 20, 3, 10, 16, 27, 24, 11, 7, 4, 2};
        String encrypted2 = "UVCCU MNUFA";
        String decrypted2 = decrypt(encrypted2, deck2.clone());
        System.out.println("\nTest 2:");
        System.out.println("Encrypted: " + encrypted2);
        System.out.println("Decrypted: " + decrypted2);
        System.out.println("Expected: HELLO WORLD");
        System.out.println("Match: " + decrypted2.equals("HELLO WORLD"));
        
        // Test 3: Longer message with mixed case and punctuation
        int[] deck3 = {26, 12, 19, 1, 9, 7, 27, 13, 10, 6, 5, 16, 17, 23, 11, 21, 2, 15, 20, 3, 8, 14, 4, 28, 25, 22, 18, 24};
        String message3 = "the quick brown fox jumped over the lazy dog";
        String encrypted3 = encrypt(message3, deck3.clone());
        System.out.println("\nTest 3:");
        System.out.println("Message: " + message3);
        System.out.println("Expected: JQY RYRUZ WHFLS DTE YNBYYJ WHRI OUV UPCO ZCK");
        System.out.println("Encrypted: " + encrypted3);
        System.out.println("Match: " + encrypted3.equals("JQY RYRUZ WHFLS DTE YNBYYJ WHRI OUV UPCO ZCK"));
        
        // Test 4: Deck with Jokers at different positions
        int[] deck4 = {14, 26, 27, 24, 18, 17, 4, 20, 16, 28, 25, 6, 7, 9, 10, 13, 5, 12, 11, 21, 22, 15, 23, 19, 1, 2, 8, 3};
        String message4 = "Hello World";
        String encrypted4 = encrypt(message4, deck4.clone());
        System.out.println("\nTest 4:");
        System.out.println("Message: " + message4);
        System.out.println("Expected: FQWBI UDKFL");
        System.out.println("Encrypted: " + encrypted4);
        System.out.println("Match: " + encrypted4.equals("FQWBI UDKFL"));
        
        // Test 5: Message with numbers and special characters
        int[] deck5 = {21, 18, 11, 10, 24, 9, 15, 27, 1, 19, 23, 14, 28, 4, 5, 8, 16, 22, 2, 20, 6, 17, 12, 25, 7, 13, 26, 3};
        String message5 = "computer SCIENCE RuleS!";
        String encrypted5 = encrypt(message5, deck5.clone());
        System.out.println("\nTest 5:");
        System.out.println("Message: " + message5);
        System.out.println("Expected: GOQSDAQF ANOZOEG TXWAL!");
        System.out.println("Encrypted: " + encrypted5);
        System.out.println("Match: " + encrypted5.equals("GOQSDAQF ANOZOEG TXWAL!"));
    }
}