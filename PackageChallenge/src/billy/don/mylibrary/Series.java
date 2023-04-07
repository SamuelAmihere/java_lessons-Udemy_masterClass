package billy.don.mylibrary;

public class Series {

    public static long nSum(int n){
        return (n*(n+1))/2;
    }

    public static long factorial(int n){
        if (n==0){
            return 1;
        }
        long fact = 1;
        for (int i=1; i<=n; i++){
            fact*=i;
        }
        return fact;
    }
    public static long fibonacci(int n){
        if (n==0){
            return 0;
        }else if (n == 1) {
            return 1;
        }
        long t2 = 0;
        long t1 = 1;
        long current=0;
        long sum = 1;

        for (int i=2; i<=n; i++){
            long t2Copy = t1;
            current = t2+t1;
            t2 = t1;
            t1 = current;
        }
        return current;
    }
}
