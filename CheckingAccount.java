/**
 * @author huan zhou
 * @version version
 */
public class CheckingAccount implements Account {

    private int balance;
    private String id;
    private String name;

    //constructor
    public CheckingAccount(String id, String name, int startingBalance) {
        this.id = id;
        this.name = name;
        this.balance = startingBalance;
    }

    //implement deposit
    public void deposit(int amount) {
        if ( amount > 0 ) {
            balance += amount;
        }
    }

    //implement withdraw method
    public boolean withdraw (int amount) {
        if (amount > 0 && balance >= amount ) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public int getBalance() {
        return balance;
    }

    // getter for id
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public String toString() {
        return id + " " + name + " $" + balance;
    }
    
}

