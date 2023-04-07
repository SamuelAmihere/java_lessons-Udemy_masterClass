package academy.learnprogramming;

public class Main {
    public static void main(String[] args) {

        char myChar = 'D';
        char myUnicodeChar = '\u0044';
        System.out.println("myChar= " + myChar);
        System.out.println("myUnicodeChar= "
                 + myUnicodeChar);

        char myCopyrightChar = '\u00A9';
        System.out.println("myCopyrightChar= "
                +myCopyrightChar);

//        Boolean
        boolean myTrueBooleanValue = true;
        boolean myFalseBooleanValue = false;
        System.out.println(myTrueBooleanValue);
        System.out.println(myFalseBooleanValue);
    }
}