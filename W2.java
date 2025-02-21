
import 
public class W2 {
    
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


    public static void calculator(){
        Scanner in = new Scanner(System.in);
        double in1, in2;
        String op;
        System.out.println("Enter input1: ");
        in1 = in.nextDouble();
        System.out.println("Enter input 2: ");
        in2 = in.nextDouble();
        System.out.println("Enter Operator； ");
        op = in.next();
        if(op is)

    }
}
