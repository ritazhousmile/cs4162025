import java.util.Scanner;

/**
 * A class to model a Simple Virtual machine Memory
 */
public class Memory {
    /**
     * Default size of the VM memory.
     */
    public static final int DEFAULT_SIZE = 16;
    
    private int[] memory;

    /**
     * Default constructor.
     * Creates an array of integers to represent the VM memory and initializes each memory cell to 0.
     */
    public Memory() {
        memory = new int[DEFAULT_SIZE];
    }

    /**
     * One argument constructor.
     * Creates an array of integers to represent the VM memory and initializes each
     * memory cell to a value from the Scanner.
     * 
     * @param s Scanner containing the input values
     * @throws IllegalStateException if there are too many values in the input
     */
    public Memory(Scanner s) {
        memory = new int[DEFAULT_SIZE]; 
        int count = 0;
    
        while (s.hasNextInt()) { 
            if (count >= DEFAULT_SIZE) {
                throw new IllegalStateException("Memory overflow: Too many values in input");
            }
            memory[count] = s.nextInt();
            count++;
        }
    }
    
    /**
     * Returns the value stored at the specified address.
     * 
     * @param address the memory address to read from
     * @return the value at the specified address
     * @throws IllegalStateException if the address is outside the valid range
     */
    public int read(int address) throws IllegalStateException {
        if (address < 0 || address >= memory.length) {
            throw new IllegalStateException("Memory access error: Invalid address " + address);
        }
        return memory[address];
    }

    /**
     * Writes a value to the specified address.
     * 
     * @param address the memory address to write to
     * @param value the value to write
     * @throws IllegalStateException if the address is outside the valid range
     */
    public void write(int address, int value) throws IllegalStateException {
        if (address < 0 || address >= memory.length) {
            throw new IllegalStateException("Memory access error: Invalid address " + address);
        }
        memory[address] = value;
    }

    /**
     * Returns a string representation of the memory.
     * 
     * @return a string showing addresses and values in a table format
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Address row
        sb.append(" address: |");
        for (int i = 0; i < memory.length; i++) {
            sb.append(String.format(" %2d |", i));  
        }
        sb.append("\n");

        // Value row
        sb.append("   value: |");
        for (int i = 0; i < memory.length; i++) {
            sb.append(String.format(" %2d |", memory[i]));
        }

        return sb.toString(); 
    }

    /**
     * Returns a copy of the raw memory array.
     * 
     * @return a copy of the memory array
     */
    public int[] rawMemory() {
        return memory.clone();  
    }
}