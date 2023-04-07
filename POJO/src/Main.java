import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        for(int i=1;i<=5;i++){
            LPAStudent s = new LPAStudent("S92300"+i,
                    switch (i){
                        case 1 -> "Samuel";
                        case 2 -> "Felicia";
                        case 3 -> "Kingsford";
                        case 4 -> "Johnson";
                        case 5 -> "Alice";
                        default -> "Anonymous";
                    },
                    "15/03/1990",
                    "Java Masterclass");
            System.out.println(s);
        }

        Student pojoStudent = new Student("S923006","Ann",
                "05/11/1985","Java Masterclass");
        LPAStudent recordStudent = new LPAStudent("S923007","Bill",
                "05/11/1985","Java Masterclass");
        System.out.println("---------------------------------------");
        System.out.println(pojoStudent);
        System.out.println(recordStudent);

        System.out.println("---------------------------------------");

        // using accessor methods in both POJO and record
        System.out.println("POJO STYLE: "+pojoStudent.getName()+" is taking "+
                pojoStudent.getClassList());
        System.out.println("record STYLE: "+recordStudent.name()+" is taking "+
                recordStudent.classList());

        System.out.println("---------------------------------------");

        //Setting data on both cases of students
        pojoStudent.setClassList(pojoStudent.getClassList()+
                ", Java OCP Exam 829");
//        recordStudent.setClassList(recordStudent.classList()+
//                ", Java OCP Exam 829");

        // using accessor methods in both POJO and record
        System.out.println("POJO STYLE: "+pojoStudent.getName()+" is taking "+
                pojoStudent.getClassList());
        System.out.println("record STYLE: "+recordStudent.name()+" is taking "+
                recordStudent.classList());
    }
}