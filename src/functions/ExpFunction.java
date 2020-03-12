package functions;

public class ExpFunction implements Function {
    @Override
    public double calculateY(double x) {
        return Math.exp(x);
    }
}
