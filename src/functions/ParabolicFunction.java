package functions;

public class ParabolicFunction implements Function {

    @Override
    public double calculateY(double x) {
        return Math.pow(x, 2);
    }

    @Override
    public boolean isValid(double lowerLimit, double upperLimit) {
        return true;
    }

}
