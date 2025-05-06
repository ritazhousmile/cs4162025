

public class W2 {
    /* 
    public static boolean matchingBrackets(String s){
        int open = 0;
        int closed = 0;
        for (int i = 0; i< s.length();i++){
            if (s.charAt(i) == '['){
                open ++;
            }else if (s.charAt(i)==']'){
                closed ++;

            }
        }      
        return open == closed;
    }
*/

    public static void main(String[] args){
        int x = 6;
        int y = x ++;
        int z = ++x;

        System.out.println(x);
        System.out.println(y);
        System.out.println(z);

    }
    
}
