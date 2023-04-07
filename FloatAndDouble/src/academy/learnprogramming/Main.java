package academy.learnprogramming;
public class Main {
    public static void main(String[] args) {

//        float
        System.out.println("--------------float------------");

        float myMinFloatValue = Float.MIN_VALUE;
        float myMaxFloatValue = Float.MAX_VALUE;
        System.out.println("Float minimum value = " +
                myMinFloatValue);
        System.out.println("Float maximum value = " +
                myMaxFloatValue);

//        double
        System.out.println("-------------double-----------");
        double myMinDoubleValue = Double.MIN_VALUE;
        double myMaxDoubleValue = Double.MAX_VALUE;
        System.out.println("Double minimum value = " +
                myMinDoubleValue);
        System.out.println("Double maximum value = " +
                myMaxDoubleValue);

//        Declare
        System.out.println("---Initializing float & double--");

        int myIntValue = 5;
        float myFloatValue = 5f;
        float myNewFloatValue = (float) 5.25; //Not recommended
                                            // of late
        double myDoubleValue = 5d;

        System.out.println("MyIntValue= " + myIntValue);
        System.out.println("myFloatValue= " + myFloatValue);
        System.out.println("myDoubleValue= " + myDoubleValue);

//        Divisions
        System.out.println("------------Divisions-----------");

        int myIntValueDiv = 5 / 2;
        float myFloatValueDiv = 5f / 2f;
        double myDoubleValueDiv = 5d / 2d;

        System.out.println("myIntValueDiv= " + myIntValueDiv);
        System.out.println("myFloatValueDiv= " + myFloatValueDiv);
        System.out.println("myDoubleValueDiv= " + myDoubleValueDiv);


//        FloatDouble Challenge

/* Convert a given number of pounds to kilograms
* 1. Create a variable with the appropriate type to store
* the number of pounds to be converted to kilograms.
* 2. Calculate the result i.e. the number of kilograms based
* on the contents of the variable above and store the result in
* a 2nd appropriate variable.
* 3. Print out the result.
* HINT: 1 pound = 0.45359237 of a kilogram. This should help
* you perform the calculation.
* */
        System.out.println("------------FloatDouble " +
                "Challenge-----------");

    double numberOfPounds = 200d;
    double convertedKilograms = numberOfPounds * 0.45359237d;
        System.out.println("Converted kilograms= "
                + convertedKilograms);
    }
}