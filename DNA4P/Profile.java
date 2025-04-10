public class Profile {
    private int agatCount;
    private int aatgCount; 
    private int tatcCount;

    // No-arg constructor
    public Profile() {
        this.agatCount = 0;
        this.aatgCount = 0;
        this.tatcCount = 0;
    }

    // Constructor with parameters
    public Profile(int agatCount, int aatgCount, int tatcCount) {
        this.agatCount = agatCount;
        this.aatgCount = aatgCount;
        this.tatcCount = tatcCount;
    }

    // Getters
    public int getAgatCount() {
        return agatCount;
    }

    public int getAatgCount() {
        return aatgCount;
    }

    public int getTatcCount() {
        return tatcCount;
    }

    // Setters
    public void setAgatCount(int value) {
        this.agatCount = value;
    }

    public void setAatgCount(int value) {
        this.aatgCount = value;
    }

    public void setTatcCount(int value) {
        this.tatcCount = value;
    }

    // Equality check
    @Override
    public boolean equals(Object o) {
        if (o instanceof Profile) {
            Profile other = (Profile) o;
            return this.agatCount == other.agatCount 
                && this.aatgCount == other.aatgCount 
                && this.tatcCount == other.tatcCount;
        }
        return false;
    }

    // String representation
    @Override
    public String toString() {
        return "(AGAT = " + agatCount + ", AATG = " + aatgCount + ", TATC = " + tatcCount + ")";
    }
}