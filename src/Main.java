import functions.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final double EPSILON = 1e-9;

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        System.out.println("input command. type help to get list of all available commands");
        System.out.println("Type input for choosing function to integrate or type quit to end working");
        while (true) {
            System.out.print("> ");
            String s = scanner.next();
            doCommand(s);
        }
    }

    private static void doCommand(String command) {
        switch (command) {

            case "input": {
                manualInput();
                return;
            }

            case "quit": {
                System.exit(0);
            }
            default: {
                System.out.println("undefined command. use help");
            }
        }
    }

    private static void manualInput() {

        System.out.println("Choose on of these functions. Input number of function you ant to integrate \n" +
                "1) y = 5x - 3 \n" +
                "2) y = 4 / x \n" +
                "3) y = x^2 \n" +
                "4) y = sqrt(x) \n"+
                "5) y = sin(x) \n" +
                "6) y = cos(x) \n"+
                "7) y = ln(x) \n"+
                "8) y = e^x \n");
        Function function = choose();

        System.out.println("input lower limit  of the integration");
        double lowerLimit = input();

        System.out.println("input upper limit  of the integration");
        double upperLimit = input();

        System.out.println("Input accuracy");
        double accuracy = input();

        boolean validInterval = true;

        if (upperLimit < lowerLimit) {
            validInterval = false;
            upperLimit += lowerLimit;
            lowerLimit = upperLimit - lowerLimit;
            upperLimit -= lowerLimit;
        }

        if (upperLimit == lowerLimit){
            System.out.println("Limits are equal. Integral is zero.");
            return;
        }


        double result[] = calculate(function, lowerLimit, upperLimit, accuracy);
        if (Double.isFinite(result[0])) {
            if  (!validInterval) result[0] *= -1;
            if (result[2] < accuracy)
                System.out.println("Integral  is: " + result[0] + "\n amount of div is: " + result[1] + "\n error is: " + result[1 + 1]);
            else System.out.println("Cannot get accuracy");
        }
        else System.out.println("Integral is not convergence");


    }

    private static Function choose(){
        Function result = null;

        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        String number = scanner.next();

        switch (number){
            case "1":{
                result = new LinearFunction();
                break;
            }
            case "2":{
                result = new HyperbolicFunction();
                break;
            }
            case "3":{
                result = new ParabolicFunction();
                break;
            }
            case "4":{
                result = new RadicalFunction();
                break;
            }
            case "5":{
                result = new SinFunction();
                break;
            }
            case "6":{
                result = new CosFunction();
                break;
            }
            case "7":{
                result = new LogFunction();
                break;
            }
            case "8":{
                result = new ExpFunction();
                break;
            }
            default:{
                System.out.println("Please try again");
                choose();
            }
        }
        return result;
    }

    private static double input(){
        double result = 0;
        Scanner scanner = new Scanner(System.in);
        boolean checkinput = true;
        while (checkinput) {
            System.out.print("> ");
            String s = scanner.next();
            try {
                result = Double.parseDouble(s);
                checkinput = false;
            }
            catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Please try again");
            }
        }
        return result;
    }

    private static double[] calculate(Function function, double lowerLimit, double upperLimit, double accuracy) {
        int amountOfDivisions = 2;
        double error = 2;
        double step = (upperLimit - lowerLimit)/amountOfDivisions;

        double lowValue = function.calculateY(lowerLimit);
        if (!Double.isFinite(lowValue)) lowValue = function.calculateY(lowerLimit + EPSILON);

        double upValue = function.calculateY(upperLimit);
        if (!Double.isFinite(lowValue)) upValue = function.calculateY(upperLimit - EPSILON);

        double value = 4 * function.calculateY(lowerLimit + step);
        if (!Double.isFinite(value))
            value = 4 *(function.calculateY((lowerLimit + step + EPSILON)) + function.calculateY(lowerLimit + step - EPSILON))/2;

        double previousValue = (step / 3)*(lowValue + value + upValue);
        double currentValue = 0;

        while (error > accuracy && amountOfDivisions < 1000000000) {

            amountOfDivisions *= 2;

            step = (upperLimit - lowerLimit)/amountOfDivisions;

            currentValue = (step / 3)*(calculateSum(function,amountOfDivisions, step, lowerLimit));

            error = (Math.abs(currentValue - previousValue)/15);

            previousValue = currentValue;
        }

        double[] array = { currentValue, amountOfDivisions, error};
        return array;
    }

    private static double calculateSum(Function function, double stepCounter,  double step, double lowerLimit){
        double result = 0;

        double value;
        for (int i = 0; i < stepCounter; i+=2){

            double tmp = 0;

            value = function.calculateY(lowerLimit + step*(i-1));
            if (!Double.isFinite(value))
                if (i==0)
                    value = function.calculateY(lowerLimit + EPSILON);
                else
                    value = (function.calculateY(lowerLimit + step*(i-1) + EPSILON) + function.calculateY(lowerLimit + step*(i-1) - EPSILON))/2;
            tmp += value;


            value = function.calculateY(lowerLimit + step*i);
            if (!Double.isFinite(value))
                value = (function.calculateY(lowerLimit + step*i + EPSILON) + function.calculateY(lowerLimit + step*i - EPSILON))/2;
            tmp += 4 * value;


            value = function.calculateY(lowerLimit + step*(i+1));
            if (!Double.isFinite(value))
                if (i==stepCounter)
                    value = function.calculateY(lowerLimit + EPSILON);
                else
                    value = (function.calculateY(lowerLimit + step*(i+1) + EPSILON) + function.calculateY(lowerLimit + step*(i+1) - EPSILON))/2;
            tmp += value;

            result += tmp;
        }
        return result;
    }

}
