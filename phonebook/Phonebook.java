
package phonebook;
public class Phonebook {

    Entry[] table;

    public Phonebook(int initialSize) {
        table = new Entry[initialSize];
    }

    class Entry {
        String name;
        String number;
        boolean deleted;

        public Entry(String name, String number) {
            this.name = name;
            this.number = number;
            this.deleted = false;
        }

        public String toString() {
            return name + ": " + number;
        }
    }

    public boolean insert(String name, String number) {
        int index = Math.abs(name.hashCode()) % table.length;
        int startIndex = index;

        while (table[index] != null) {
            if (!table[index].deleted && table[index].name.equals(name)) {
                return false; // 已存在
            }

            if (table[index].deleted && table[index].name.equals(name)) {
                table[index].number = number;
                table[index].deleted = false;
                return true; // 复活已删除项
            }

            index = (index + 1) % table.length;
            if (index == startIndex) return false; // 表已满
        }

        table[index] = new Entry(name, number);
        return true;
    }

    // 查找
    public String find(String name) {
        int index = Math.abs(name.hashCode()) % table.length;
        int startIndex = index;

        while (table[index] != null) {
            if (!table[index].deleted && table[index].name.equals(name)) {
                return table[index].number;
            }
            index = (index + 1) % table.length;
            if (index == startIndex) break;
        }

        return null;
    }

    // 删除
    public boolean remove(String name) {
        int index = Math.abs(name.hashCode()) % table.length;
        int startIndex = index;

        while (table[index] != null) {
            if (!table[index].deleted && table[index].name.equals(name)) {
                table[index].deleted = true;
                return true;
            }
            index = (index + 1) % table.length;
            if (index == startIndex) break;
        }

        return false;
    }

    public String toString() {
        String ret = "";
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !table[i].deleted) {
                ret += String.format("table[%d] = %s%n", i, table[i]);
            } else {
                ret += String.format("table[%d] = %n", i);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Phonebook book = new Phonebook(10);

        System.out.println(book);

        book.insert("Mary", "123");
        assert book.find("Mary").equals("123");

        System.out.println(book);

        book.insert("Tom", "456");
        assert book.find("Tom").equals("456");

        System.out.println(book);

        book.insert("Robbie", "789");
        assert book.find("Robbie").equals("789");

        System.out.println(book);

        book.insert("Ralph", "1");
        assert book.find("Ralph").equals("1");

        System.out.println(book);

        book.insert("Rudy", "1");
        assert book.find("Rudy").equals("1");

        System.out.println(book);

        book.insert("Jennifer", "1");
        assert book.find("Jennifer").equals("1");

        System.out.println(book);

        assert book.insert("Jennifer", "1") == false;
        assert book.find("Jennifer").equals("1");

        System.out.println(book);

        assert book.remove("Mary") == true;
        System.out.println(book);

        assert book.remove("Frank") == false;
        System.out.println(book);

        assert book.insert("Mary", "123");
        assert book.find("Mary").equals("123");
        System.out.println(book);

        assert book.insert("Zoe", "123");
        assert book.find("Zoe").equals("123");
        System.out.println(book);

        assert book.remove("Jennifer");
        System.out.println(book);

        assert book.remove("Jennifer") == false;

        assert book.insert("A", "1");
        assert book.find("A").equals("1");

        assert book.insert("B", "1");
        assert book.find("B").equals("1");

        assert book.insert("C", "1");
        assert book.find("C").equals("1");

        assert book.insert("D", "1");
        assert book.find("D").equals("1");
        System.out.println(book);

        assert book.insert("E", "1") == false;
    }
}