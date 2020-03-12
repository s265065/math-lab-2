package functions;

public class LogFunction implements Function {
    @Override
    public double calculateY(double x) {
        return Math.log(x);
    }
}
