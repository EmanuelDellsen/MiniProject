package Classes;

public class Risk {

    private int riskId;
    private String riskName;
    private double probability;
    private int impact;
    private final double CATASTROPHIC_Threshold = 6.0;
    private final double CATASTROPHIC = 7.0;
    private final double SEVERE_THRESHOLD= 4.0;
    private final double SEVERE=5.0 ;
    private final double MODERATE_THRESHOLD= 2.0;
    private final double MODERATE=3.0;
    private final double LOW=1.0;



    public Risk(int riskId, String riskName, double probability, int impact){
        this.riskId = riskId;
        this.riskName = riskName;
        this.probability = probability;
        this.impact = impact;
    }




    public double returnRisk(){
        return this.probability*this.impact;
    }

    public String riskDescription(){
        String description;
        if(returnRisk() == CATASTROPHIC) {
            description = "Catastrophic";
        }
        else if(returnRisk()== SEVERE){
            description= "Severe";

        }
        else if(returnRisk()== MODERATE){
            description= "Moderate";

        }
        else{
            description= "Low";
        }
        return description;
    }


    public int getRiskId(){
        return this.riskId;
    }
    public String getRiskName(){
        return this.riskName;
    }
    public double getProbability() {
        return this.probability;
    }
    public int getImpact(){
        return this.impact;
    }

    @Override
    public String toString() {
        return  "Name: "+riskName+"\n"+
                "Probability: "+probability+"\n"+
                "Impact: "+impact+"\n"+
                "Risk: "+returnRisk()+"\n";
    }
}



