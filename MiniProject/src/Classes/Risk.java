package Classes;

public class Risk {

    private int riskId;
    private String riskName;
    private double probability;
    private int impact;
    private final double CATASTROPHIC_LEVEL = 4.0;
    private final double SEVERE_LEVEL= 3.0;
    private final double MODERATE_LEVEL= 2.0;

    public Risk(int riskId, String riskName, double probability, int impact){
        this.riskId = riskId;
        this.riskName = riskName;
        this.probability = probability;
        this.impact = impact;
    }

    public String returnRisk(){

        String risk;
        double riskLevel = this.probability*this.impact;

        if(riskLevel >= CATASTROPHIC_LEVEL){
            risk = "Catastrophic";
        }
        else if(riskLevel >= SEVERE_LEVEL) {
            risk = "Severe";
        }
        else if(riskLevel >= MODERATE_LEVEL) {
            risk = "Moderate";
        }
        else {
            risk="Low";
        }
        return risk;
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
                "Risk: "+returnRisk();
    }
}



