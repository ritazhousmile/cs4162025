package findFakeCoins;

public class Scales {
    public int find(Purse p, int start, int end){
        int half = start + (end -start)/2;
        int leftWeight = p.weight(start, half);
        int rightWeight = p.weight(half,end);
        //if there is only one item in the rance , it must be fake
        if(end == start + 1){
            return start;
        }
        //cut it in half
        //compare two halves
        //whichever is lighter by weight has the fake one
        if(leftWeight< rightWeight){
            return find(p, start, half);
        } else {
            return find(p, half, end);
        }

        return -1;

    }
    
}
