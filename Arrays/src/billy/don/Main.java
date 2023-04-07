package billy.don;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        int[] myIntArray = new int[10];
        double[] myDoubleArray = new double[10];

//        printIntArray(myIntArray);
//        System.out.println("-".repeat(13));
//
//        printDoubleArray(myDoubleArray);
//        System.out.println("-".repeat(13));

        int[] scannerIntegers = getIntegers(5);
        printIntValues(scannerIntegers);

    }

    public static void printIntArray(int[] array){
        for(int i=0; i<array.length;i++){
            array[i] = 3+i;
        }
        printIntValues(array);
    }

    public static void printDoubleArray(double[] array){
        for(int i=0; i<array.length;i++){
            array[i] = 3.00+i;
        }
        printDoubleValues(array);
    }

    public static int[] getIntegers(int numberofIntegers){
        System.out.println("Enter "+numberofIntegers+" integer values:");

        int[] myIntArrays = new int[numberofIntegers];
        int counter = 0;

        while(true){

            System.out.print("Enter integer(#"+(counter+1)+"): ");
            myIntArrays[counter] = scanner.nextInt();

            counter += 1;

            if (counter == numberofIntegers){
                System.out.println("=".repeat(21));
                break;
            }
        }

        return myIntArrays;
    }

    public static void printIntValues(int[] arr){
        for(int i=0; i<arr.length;i++){
            System.out.println("index "+i+" ="+arr[i]);
        }
        System.out.println("Average of array= "+ getAverageIntegers(arr));

    }

    public static void printDoubleValues(double[] arr){
        for(int i=0; i<arr.length;i++){
            System.out.println("index "+i+" ="+arr[i]);
        }
    }

    public static double getAverageIntegers(int[] arrs){
        int sum = 0;
        for(int i=0;i<arrs.length;i++){
            sum += arrs[i];
        }
        return (double) sum/arrs.length;
    }
}
