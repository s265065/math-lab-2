package functions;

public class SinFunction  implements Function {

    @Override
    public double calculateY(double x) {
        return Math.sin(x);
    }

}
