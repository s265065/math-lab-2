package functions;

public interface Function {

    void setLowerLimit(double limit);

    void setUpperLimit(double limit);

    void setAccuracy(double accuracy);

    double getLowerLimit();

    double getUpperLimit();

    double getAccuracy();

    double calculateY(double x);

    boolean isValid();
}
