package functions;

public class HyperbolicFunction implements Function {
    private double lowerLimit;
    private double upperLimit;
    private double accuracy;

    @Override
    public void setLowerLimit(double limit) {
        this.lowerLimit = limit;
    }

    @Override
    public void setUpperLimit(double limit) {
        this.upperLimit = limit;
    }

    @Override
    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    @Override
    public double calculateY(double x)
    {
    return 2 / x;
    }

    @Override
    public boolean isValid() {

        return upperLimit*lowerLimit>0;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public double getLowerLimit() {
        return lowerLimit;
    }

    public double getUpperLimit() {
        return upperLimit;
    }
}
