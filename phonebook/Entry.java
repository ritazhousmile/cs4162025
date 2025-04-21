package phonebook;
public class Entry {
    private String name;
    private String number;
    private boolean isRemoved;

    // 构造函数
    public Entry(String name, String number) {
        this.name = name;
        this.number = number;
        this.isRemoved = false; // 默认未被删除
    }

    // getter 和 setter
    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        this.isRemoved = removed;
    }

    // 打印格式
    @Override
    public String toString() {
        return name + ": " + number + (isRemoved ? " [REMOVED]" : "");
    }
}