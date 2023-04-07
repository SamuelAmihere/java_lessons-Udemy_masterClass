package academy.learnprogramming;

public class Main {
    public static void main(String[] args) {
        byte byteValue = 10;
        short shortValue = 20;
        int intValue = 50;

        long lonTotal = 50000L + 10L * (byteValue + shortValue
                + intValue);
        System.out.println(lonTotal);

        short shortTotal = (short) (1000 + 10 *
                (byteValue + shortValue + intValue));
        System.out.println(shortTotal);
    }
}