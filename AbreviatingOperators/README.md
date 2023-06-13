# Abbreviating Operators Example

This Java program demonstrates the usage of abbreviated operators to perform arithmetic operations on a variable and logical operations.

## Code

```java
package academy.learnprogramming;

public class Main {
    public static void main(String[] args) {
        int result = 4;
        result++;
        System.out.println(result);

        result--;
        System.out.println(result);

        result += 2;
        System.out.println(result);

        result *= 10;
        System.out.println(result);

        result /= 3;
        System.out.println(result);

        boolean isAlien = false;
        if (isAlien == false)
            System.out.println("Not Alien");
    }
}


## Output
5
4
6
60
20
Not Alien

## Explanation

The program starts by initializing an integer variable result to 4. It then performs various operations using abbreviated operators and logical operators.

    Line 6: result++; increments the value of result by 1 using the ++ operator. The updated value of result is 5.
    Line 9: result--; decrements the value of result by 1 using the -- operator. The updated value of result is 4.
    Line 12: result += 2; is equivalent to result = result + 2;. It adds 2 to the current value of result, resulting in 6.
    Line 15: result *= 10; is equivalent to result = result * 10;. It multiplies the current value of result by 10, resulting in 60.
    Line 18: result /= 3; is equivalent to result = result / 3;. It divides the current value of result by 3, resulting in 20.
    Line 21-23: The program defines a boolean variable isAlien with a value of false. It then uses an if statement to check if isAlien is false. If the condition is true, it prints "Not Alien" to the console.

The program then outputs the values of result after each operation and "Not Alien" if the condition is true.
