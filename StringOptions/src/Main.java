public class Main {
    public static void main(String[] args) {

        String helloWorld = "Hello "+" World";
        helloWorld.concat(" and Goodbye");

        StringBuilder helloWorldBuilder = new StringBuilder("Hello "+" World");
        helloWorldBuilder.append(" and Goodbye");

        printInformation(helloWorld);
        printInformation(helloWorldBuilder);

        StringBuilder emptyStart = new StringBuilder();
        emptyStart.append("a".repeat(17));

        StringBuilder emptyStart32 = new StringBuilder(32);
        emptyStart32.append("a".repeat(17));
        printInformation(emptyStart);
        printInformation(emptyStart32);

        StringBuilder builderPlus = new StringBuilder("Hello "+" World");
        builderPlus.append(" and Goodbye");

        //delete and insert character in StringBuilder
        builderPlus.deleteCharAt(17).insert(17,'g');
        System.out.println(builderPlus);

        //replace a string in StringBuilder
        builderPlus.replace(17,18,"G");
        System.out.println(builderPlus);

        //reverse and setLength method
        builderPlus.reverse().setLength(7);
        System.out.println(builderPlus);
    }

    public static void printInformation(String string){

        System.out.println("String= "+string);
        System.out.println("length= "+string.length());
    }

    public static void printInformation(StringBuilder builder){

        System.out.println("StringBuilder= "+builder);
        System.out.println("length= "+builder.length());
        System.out.println("capacity= "+builder.capacity());

    }
}