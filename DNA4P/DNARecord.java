
public class DNARecord {
    private String name;
    private Profile profile;

    public DNARecord(String name, Profile profile){
        this.name = name;
        this.profile = profile;
    }
    
    //getters
    public String getName(){
        return this.name;
    }

    
    public Profile getProfile(){
        return this.profile;
    }

    @Override
    public String toString(){
        //"Fatema: (AGAT = 3, AATG = 5, TATC = 4)"
        return name + ": " + profile.toString();
    }

}
