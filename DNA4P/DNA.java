
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * DNA sequence analyzer.
 * Reads DNA profiles from a database and matches against a given DNA sequence.
 * 
 * @author Huan Rand
 * @version 04102025
 */
public class DNA {

    // Reads the DNA database file and returns an array of DNARecord objects
    public static DNARecord[] readData(String fileName) {
        List<DNARecord> records = new ArrayList<>();
        try (Scanner s = new Scanner(new File(fileName))) {
            s.nextLine(); // Skip header line
            while (s.hasNextLine()) {
                String[] fileData = s.nextLine().split(" ");
                String name = fileData[0];
                int agatCount = Integer.parseInt(fileData[1]);
                int aatgCount = Integer.parseInt(fileData[2]);
                int tatcCount = Integer.parseInt(fileData[3]);
                Profile profile = new Profile(agatCount, aatgCount, tatcCount);
                records.add(new DNARecord(name, profile));
            }
            return records.toArray(new DNARecord[0]);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
            return null;
        }
    }

    // Counts the maximum number of consecutive times 'match' appears in 'sequence'
    public static int countMaximumConsecutiveMatches(String sequence, String match) {
        int maxCount = 0;
        int matchLength = match.length();

        for (int i = 0; i <= sequence.length() - matchLength; i++) {
            int count = 0;
            while (sequence.startsWith(match, i + count * matchLength)) {
                count++;
            }
            maxCount = Math.max(maxCount, count);
        }

        return maxCount;
    }

    // Reads DNA sequence file and returns the Profile created from the sequence
    public static Profile readSequence(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            String sequence = scanner.nextLine().toUpperCase();

            int agatCount = countMaximumConsecutiveMatches(sequence, "AGAT");
            int aatgCount = countMaximumConsecutiveMatches(sequence, "AATG");
            int tatcCount = countMaximumConsecutiveMatches(sequence, "TATC");

            return new Profile(agatCount, aatgCount, tatcCount);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
            return null;
        }
    }

    // Main method to identify DNA match from given data and sequence files
    public static void main(String[] args) {
        if (args.length < 2 || args[0].equals("--help")) {
            System.out.println("Usage: java DNA4P.DNA <data_file.txt> <sequence_file.txt>");
            return;
        }

        String dataFileName = args[0];
        String sequenceFileName = args[1];

        DNARecord[] records = readData(dataFileName);
        Profile sampleProfile = readSequence(sequenceFileName);

        if (records == null || sampleProfile == null) {
            System.out.println("Error reading files.");
            return;
        }

        boolean matchFound = false;
        for (DNARecord record : records) {
            if (record != null && record.getProfile().equals(sampleProfile)) {
                System.out.println("Matched " + record.toString());
                matchFound = true;
                break;
            }
        }

        if (!matchFound) {
            System.out.println("No match for " + sampleProfile.toString());
        }
    }
}