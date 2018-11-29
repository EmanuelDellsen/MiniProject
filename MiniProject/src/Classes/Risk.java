package Classes;

public class Risk {
    private int riskId;
    private String riskName;
    private double probability;
    private int impact;

    public Risk( int riskId, String riskname, double probability, int impact){
        this.riskId= riskId;
        this.riskName= riskname;
        this.probability= probability;
        this.impact= impact;

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



