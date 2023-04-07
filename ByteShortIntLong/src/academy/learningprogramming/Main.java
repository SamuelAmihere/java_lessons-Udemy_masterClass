package academy.learningprogramming;

public class Main {
    public static void main(String[] args) {
//        Integer, int
        System.out.println("--------------int-----------------");
        int myValue = 2_147_483_647;
        System.out.println(myValue);

        int myMinIntValue = Integer.MIN_VALUE;
        int myMaxIntValue = Integer.MAX_VALUE;
        System.out.println("Integer Minimum Value = " + myMinIntValue);
        System.out.println("Integer Maximum Value = " + myMaxIntValue);

//        byte
        System.out.println("---------------byte----------------");
        byte myMinByteValue = Byte.MIN_VALUE;
        byte myMaxByteValue = Byte.MAX_VALUE;

        System.out.println("Byte Minimum Value = " + myMinByteValue);
        System.out.println("Byte Maximum Value = " + myMaxByteValue);

//        short
        System.out.println("------------------short--------------");

        short myMinShortValue = Short.MIN_VALUE;
        short myMaxShortValue = Short.MAX_VALUE;

        System.out.println("Short Minimum Value = " + myMinShortValue);
        System.out.println("Short Maximum Value = " + myMaxShortValue);

        //        long
        System.out.println("------------------long--------------");
        long myLongValue = 100L;
        System.out.println(myLongValue);

        long myMinLongValue = Long.MIN_VALUE;
        long myMaxLongValue = Long.MAX_VALUE;

        System.out.println("Long Minimum Value = " + myMinLongValue);
        System.out.println("Long Maximum Value = " + myMaxLongValue);

//        casting in java
        System.out.println("------------------casting--------------");

        int myTotal = (myMinIntValue / 2);

        byte myNewByteValue = (byte) (myMinByteValue / 2);
        System.out.println("Casted Byte value = " + myNewByteValue);


    }
}