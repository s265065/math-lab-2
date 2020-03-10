package functions;

public class SinFunction  implements Function {
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
    public double calculateY(double x) {
        return Math.sin(x);
    }

    @Override
    public boolean isValid() {
        return true;
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
