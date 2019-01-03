package Classes;

public class Risk {

    private int riskId;
    private String riskName;
    private double probability;
    private int impact;
    private final static double CATASTROPHIC_THRESHOLD = 6.0;
    private final static double SEVERE_THRESHOLD= 4.0;
    private final static double MODERATE_THRESHOLD= 2.0;

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
        if(returnRisk() >= CATASTROPHIC_THRESHOLD) {
            description = "Catastrophic";
        }
        else if(returnRisk() >= SEVERE_THRESHOLD){
            description= "Severe";

        }
        else if(returnRisk()>= MODERATE_THRESHOLD){
            description= "Moderate";

        }
        else{
            description= "Low";
        }
        return description;
    }

    public String getRiskName(){
        return this.riskName;
    }

    public int getRiskId(){
        return this.riskId;
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



