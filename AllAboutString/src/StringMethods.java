public class StringMethods {

    public static void main(String[] args) {

        String birthDay = "25/11/1982";
        //extract year from birthDay
        int startingIndex = birthDay.indexOf("1982");
        System.out.println("startingIndex = "+startingIndex);
        System.out.println("Birth year= "+birthDay.substring(startingIndex));

        //extract month from birthDay
        System.out.println("Month= "+birthDay.substring(3,5));

        //joining strings using String.join and concat method and '+'
        String newDate = String.join("/","25","11","2001");
        System.out.println("newDate= "+newDate);

        newDate = "25";
        newDate = newDate.concat("/");
        newDate = newDate.concat("12");
        newDate = newDate.concat("/");
        newDate = newDate.concat("2002");
        System.out.println("newDate= "+newDate);

        // '+' literals (more efficient than previous codes)
        newDate = "25"+"/"+"11"+"/"+"2003";
        System.out.println("newDate= "+newDate);

        // chaining concat
        newDate = "25".concat("/").concat("01").concat("/")
                .concat("2004");
        System.out.println("newDate= "+newDate);

        //replace, replaceFirst, and replaceAll
        System.out.println("newDate= "+newDate.replace('/','-'));
        System.out.println("newDate= "+newDate.replace("2","00"));

        System.out.println("newDate= "+newDate.replaceFirst("/","-"));
        System.out.println("newDate= "+newDate.replaceAll("/","---"));

        //repeat
        System.out.println("ABC\n".repeat(3));
        System.out.println("-".repeat(22));

        //repeat, indent
        System.out.println("ABC\n".repeat(3).indent(8));
        System.out.println("-".repeat(22));

        //leading spaces
        //using negative indent removes leading spaces
        System.out.println("    ABC\n".repeat(3).indent(-2));
        System.out.println(">".repeat(11));
    }


}
