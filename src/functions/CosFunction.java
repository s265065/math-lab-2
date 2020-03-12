package functions;

public class CosFunction implements Function {
    @Override
    public double calculateY(double x) {
        return Math.cos(x);
    }
}
