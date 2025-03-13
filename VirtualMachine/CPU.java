/**
 * A class to model a CPU in a simple Virtual Machine
 */
public class CPU {
    // Renamed to follow Java naming conventions
    private int ip;
    private int is;
    private int r0;
    private int r1;
    private Boolean running;
    
    /**
     * Default constructor.
     * Initializes the CPU object state with all registers set to 0.
     */
    public CPU() {
        ip = 0;
        is = 0;
        r0 = 0;
        r1 = 0;
        running = true;
    }

    /**
     * Runs the CPU with the given memory.
     * 
     * @param mem The memory to use
     * @param debugMode Whether to print debug information
     * @throws IllegalStateException If an unknown instruction is encountered
     */
    public void run(Memory mem, boolean debugMode) throws IllegalStateException {
        int step = 0; 
        while (running) {
            // Wrap around the IP instead of stopping execution
            if (ip < 0 || ip >= Memory.DEFAULT_SIZE) {
                ip = ip & 0xF; // Wrap around to keep IP in range 0-15
            }
            
            is = mem.read(ip);
            if (debugMode) {
                System.out.println("STEP " + step + ":");
                System.out.println(this);
                System.out.println(mem);
                System.out.println("----------");
            }

            // Execute: Execute the instruction based on the IS value
            switch (is) {
                case 0: // Halt
                    running = false;
                    break;
                case 1: // R0 = R0 + R1
                    r0 = (r0 + r1) & 0xF; // Ensure wrap-around using & 0xF
                    ip++;
                    break;
                case 2: // R0 = R0 - R1
                    r0 = (r0 - r1) & 0xF;
                    ip++;
                    break;
                case 3: // Increment R0
                    r0 = (r0 + 1) & 0xF;
                    ip++;
                    break;
                case 4: // Increment R1
                    r1 = (r1 + 1) & 0xF;
                    ip++;
                    break;
                case 5: // Decrement R0
                    r0 = (r0 - 1) & 0xF;
                    ip++;
                    break;
                case 6: // Decrement R1
                    r1 = (r1 - 1) & 0xF;
                    ip++;
                    break;
                case 7: 
                    ip++;
                    break;
                case 8: // Print X
                    ip++;
                    int x = mem.read(ip);
                    System.out.println(x);
                    ip++;
                    break;
                case 9: // R0 = MEMORY[X]
                    ip++;
                    x = mem.read(ip);
                    r0 = mem.read(x) & 0xF;
                    ip++;
                    break;
                case 10: // R1 = MEMORY[X]
                    ip++;
                    x = mem.read(ip);
                    r1 = mem.read(x) & 0xF;
                    ip++;
                    break;
                case 11: // MEMORY[X] = R0
                    ip++;
                    x = mem.read(ip);
                    mem.write(x, r0);
                    ip++;
                    break;
                case 12: // MEMORY[X] = R1
                    ip++;
                    x = mem.read(ip);
                    mem.write(x, r1);
                    ip++;
                    break;
                case 13: // JUMP X
                    ip++;
                    x = mem.read(ip);
                    ip = x;
                    break;
                case 14: // JUMP X IF R0 == 0
                    ip++;
                    x = mem.read(ip);
                    if (r0 == 0) {
                        ip = x;
                    } else {
                        ip++;
                    }
                    break;
                case 15: // JUMP X IF R0 != 0
                    ip++;
                    x = mem.read(ip);
                    if (r0 != 0) {
                        ip = x;
                    } else {
                        ip++;
                    }
                    break;
                default:
                    throw new IllegalStateException("Unknown instruction: " + is);
            }
            step++;
        }
    }

    /**
     * Returns a string representation of the CPU state.
     * 
     * @return A string with IP, IS, R0, and R1 values
     */
    public String toString() {
        return String.format("IP=%2d, IS=%2d, R0=%2d, R1=%2d", ip, is, r0, r1);
    }
}