package functions;

public class HyperbolicFunction implements Function {

    @Override
    public double calculateY(double x)
    {
    return 2 / x;
    }

    @Override
    public boolean isValid(double lowerLimit, double upperLimit) {

        return upperLimit*lowerLimit>0;
    }

}
