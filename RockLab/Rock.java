
/**
*@author:Huan(Rita)Zhou
* 02/11/2025
*/

public class Rock {
    public String name;
    public double NumberOfPounds;
    public double VolumeOfRock;


    public Rock(String thisName) {
        name = thisName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNumPounds(){
        return NumberOfPounds;


    }
    public void setNumPounds(double m){
        this.NumberOfPounds = m;

    }

    public double getVolume(){
        return VolumeOfRock;

    }
    public void setVolume(double n){

        VolumeOfRock = n;

    }

    public int calculateDensity (){
        if (VolumeOfRock == 0) {
            throw new ArithmeticException("Volume cannot be zero.");
        }
        return (int) (NumberOfPounds / VolumeOfRock); // Density = pounds / volume, cast to int

    }

      // Method to increase the number of pounds of the rock
      public void increasePounds(double additionalPounds) {
        this.NumberOfPounds += additionalPounds;
    }

     // Method to decrease the number of pounds of the rock
     public void decreasePounds(double minusPounds) {
        this.NumberOfPounds -= minusPounds;
    }


    public String toString() {
        return String.format("Rock %s weighs %.3f pounds with a density of %d", name, NumberOfPounds, calculateDensity());
    }
    public static void main(String[] args) {

        Rock r = new Rock("pet");
        r.setVolume(2.0);
        r.setNumPounds(10.0);
        System.out.println(r.calculateDensity());



        
    }
    

}
