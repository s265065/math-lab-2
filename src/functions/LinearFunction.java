package functions;

public class LinearFunction implements Function {

    @Override
    public double calculateY(double x) {
        return 5*x -3;
    }

    @Override
    public boolean isValid(double lowerLimit, double upperLimit) {
        return true;
    }

}
