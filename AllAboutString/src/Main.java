public class Main {
    public static void main(String[] args) {
//        //The Text Block
//        String textBlock = """
//                Print a Bulleted List:
//                            \u2022 First Point
//                                \u2022 Sub Point""";
//        System.out.println(textBlock);

        // String Inspection Methods
        String myString = "Hello World";
        printInformation(myString);
        printInformation("");
        printInformation("\t   \n");
        printIndexOf(myString, 'r',false);
        printIndexOf(myString, 'l',true);
        printIndexOf(myString, 'H',false);
        printIndexOf(myString, "World",false);
        printIndexOf("", 'r',false);

        // String Comparison Methods
        String myStringLower = myString.toLowerCase();
        if(myString.equals(myStringLower)){
            System.out.println("Values match exactly");
        }
        if(myString.equalsIgnoreCase(myStringLower)){
            System.out.println("Values match ignoring case");
        }
        if (myString.startsWith("Hello")){
            System.out.println("String starts with Hello");
        }
        if (myString.endsWith("World")){
            System.out.println("String ends with World");
        }
        if (myString.contains("World")){
            System.out.println("String contains World");
        }
        if(myString.contentEquals("Hello World")){
            System.out.println("Values match exactly");
        }


        // String Manipulation Methods

    }

    public static void printInformation(String string){
        int length = string.length();
        System.out.printf("Length= %d %n", length);

        if(string.isEmpty()){
            System.out.println("String is empty");
            return;
        }

        if(string.isBlank()){
            System.out.println("String is Blank");
        }

        System.out.printf("First char= %c %n", string.charAt(0));
        System.out.printf("Last char= %c %n", string.charAt(length-1));
    }
    public static void printIndexOf(String string, char c, boolean lastIndex){
        if(string.isEmpty() || string.isBlank()){
            System.out.println("No String");
            return;
        }
        if(lastIndex){
            System.out.printf("last index of %c = %d %n", c,string.lastIndexOf(c));
        }
        System.out.printf("index of %c = %d %n", c,string.indexOf(c));
    }

    public static void printIndexOf(String string, String stringFindIndex, boolean lastIndex){
        if(string.isEmpty() || string.isBlank()){
            System.out.println("No String");
            return;
        }
        if(lastIndex){
            System.out.printf("last index of "+stringFindIndex+" = %d %n",string.lastIndexOf(stringFindIndex));
        }else{
            System.out.printf("index of "+stringFindIndex+" = %d %n",string.indexOf(stringFindIndex));
        }

    }
}