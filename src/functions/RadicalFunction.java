package functions;

public class RadicalFunction implements Function {

    @Override
    public double calculateY(double x) {
        return Math.sqrt(x);
    }

    @Override
    public boolean isValid(double lowerLimit, double upperLimit) {
        return (upperLimit > 0) && (lowerLimit > 0);
    }

}
