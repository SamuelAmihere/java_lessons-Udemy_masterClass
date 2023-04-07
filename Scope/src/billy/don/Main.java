package billy.don;

public class Main {
    public static void main(String[] args) {
        String privateVar = "This is private to main()";

        ScopeCheck sc = new ScopeCheck();
        System.out.println("sc privateVar is "+sc.getPrivateVar());
        System.out.println(privateVar);

        sc.timesTwo(15);
        System.out.println("=================");

        ScopeCheck.InnerClass innerClass = sc.new InnerClass();
        innerClass.timesTwo(15);

    }
}