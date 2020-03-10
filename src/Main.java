import functions.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        System.out.println("input command. type help to get list of all available commands");
        help();
        while (true) {
            System.out.print("> ");
            String s = scanner.next();
            doCommand(s);
        }
    }

    private static void doCommand(String command) {
        switch (command) {
            case "help": {
                help();
                return;
            }

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
                "3) y = x^4 \n" +
                "4) y = sqrt(x)");
        Function function = choose();

        System.out.println("input lower limit  of the integration\n " +
                "Be careful! If you ant to input double you have to use only a dot not a comma");
        double lowerLimit = input();

        System.out.println("input upper limit  of the integration\n " +
                "Be careful! If you ant to input double you have to use only a dot not a comma");
        double upperLimit = input();

        System.out.println("Input accuracy");
        double accuracy = input();

        function.setAccuracy(accuracy);

        if (upperLimit < lowerLimit) {
            upperLimit += lowerLimit;
            lowerLimit = upperLimit - lowerLimit;
            upperLimit -= lowerLimit;
        }

        if (upperLimit == lowerLimit){
            System.out.println("Limits are equal. Integral is zero.");
            return;
        }

        function.setUpperLimit(upperLimit);
        function.setLowerLimit(lowerLimit);

        if (function.isValid()) {
           double result[] = calculate(function);
           if (result[1+1]  < accuracy)
               System.out.println("Integral  i: " + result[0] + "\n amount of div i: " + result[1]+"\n error i: "+result[1+1]);
           else System.out.println("Cannot get accuracy");
        } else {
            System.out.println("Integral diverge");
        }


    }

    private static Function choose(){
        Function result = null;

        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        String number = scanner.next();

        switch (number){
            case "1":{
                result = new LinearFunction();
                System.out.println("Your function is  y = 5x - 3 ");
                break;
            }
            case "2":{
                result = new HyperbolicFunction();
                System.out.println("Your function is  y = 4 / x ");
                break;
            }
            case "3":{
                result = new ParabolicFunction();
                System.out.println("Your function is  y = x^4 ");
                break;
            }
            case "4":{
                result = new RadicalFunction();
                System.out.println("Your function is  y = sqrt(x) ");
                break;
            }
            case "5":{
                result = new SinFunction();
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

    private static double[] calculate(Function function) {
        int amountOfDivisions = 1;
        double error = 1;
        double step = 0;
        double previousValue = 0;
        double currentValue = 0;

        while (error > function.getAccuracy() && amountOfDivisions < 1000000000) {

            amountOfDivisions *= 2;
            step = (function.getUpperLimit()-function.getLowerLimit())/amountOfDivisions;

            previousValue = (step / 3)*(function.calculateY(function.getLowerLimit()) +
                    calculateSum(function, amountOfDivisions, step) +
                    function.calculateY(function.getUpperLimit()));

            step = (function.getUpperLimit()-function.getLowerLimit())/(amountOfDivisions * 2);

            currentValue = (step / 3)*(function.calculateY(function.getLowerLimit()) +
                    calculateSum(function,amountOfDivisions * 2, step) +
                    function.calculateY(function.getUpperLimit()));

            error = (Math.abs(currentValue - previousValue))/15;

        }

        double[] array = new double[3];
        array[0]=currentValue;
        array[1]= amountOfDivisions;
        array[1+1]=error;
        return array;
    }

    private static double calculateSum(Function function, double stepCounter,  double step){
        double result = 0;
        for (int i = 1; i < stepCounter; ++i){
            result += 4 * function.calculateY(function.getLowerLimit()+step*(i));
            ++i;
            result += 2 * function.calculateY(function.getLowerLimit()+step*(i));
        }
        return result;
    }


    private static void help() {
        System.out.println("here'll be help");
    }

}
