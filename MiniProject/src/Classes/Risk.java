package Classes;

public class Risk {
    private int riskId;
    private String riskName;
    private double probability;
    private int impact;
    protected final double CATESTROPHIC_LEVEL= 4.0;
    protected final double SEVERE_LEVEL= 3.0;
    protected final double MODERATE_LEVEL= 2.0;


    public Risk( int riskId, String riskName, double probability, int impact){
        this.riskId= riskId;
        this.riskName= riskName;
        this.probability= probability;
        this.impact= impact;

    }
    public String returnRisk(){
        String risk="";
        double riskLevel= this.probability*this.impact;

        if(riskLevel>=CATESTROPHIC_LEVEL){
            risk="Catastrophic";
        }
        else if(riskLevel<CATESTROPHIC_LEVEL && riskLevel>=SEVERE_LEVEL) {
            risk = "Severe";
        }
        else if(riskLevel< SEVERE_LEVEL && riskLevel>= MODERATE_LEVEL) {
            risk = "Moderate";
        }
        else if(riskLevel<MODERATE_LEVEL){
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
}



