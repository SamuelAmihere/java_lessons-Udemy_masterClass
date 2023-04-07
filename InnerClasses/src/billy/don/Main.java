package billy.don;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Button btnPrint = new Button("Print");

    public static void main(String[] args) {

        /*
        Local Class (ClickListener).
        Definition of this class inside the main method makes
        sense since it's being used only here.
         */
//        class ClickListener implements Button.IOnClickListener{
//            public ClickListener() {
//                System.out.println("I have been attached");
//            }
//            //Implement the onClick Listerner defined inside the
//            // Interface
//            @Override
//            public void onClick(String title) {
//                System.out.println(title+" was clicked");
//            }
//        }
//
//        btnPrint.setOnClickListener(new ClickListener());
//        // Simulate the listening method from a keyboard
//        listen();

        /*
        Anonymous class.
        This class, without a name, works the same as the ClickListener
        class.
         */
        btnPrint.setOnClickListener(new Button.IOnClickListener() {
            @Override
            public void onClick(String title) {
                System.out.println(title+" was clicked");
            }
        });

        // Simulate the listening method from a keyboard
        listen();


    }
    // Simulate the listening method from a keyboard
    private static void listen(){
        boolean quit = false;
        while (!quit){
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 0:
                    quit = true;
                    break;
                case 1:
                    btnPrint.onClick();
            }
        }
    }
}