public class Main {
    public static void main(String[] args) {
        // Create a simple interface (tip: name it Saveable or Isaveable)
        // That interface allows an object to be saved to some sort of storage medium (tip: use arraylist)
        // The exact type of medium is not known to the interface (nor to the class that implement it).
        // The interface will just specify two methods, one to return an ArrayList of values to be saved
        // and the other to populate the object's fields from an ArrayList (parameter).
        //
        // Create sample classes that implement your Saveable interface (we've used the idea of game,
        // with Players and Monsters, but you can create any type of classes that you want).
        //
        // Override the toString() method for each of your classes so that they can easily be printed to enable
        // the program to be tested easier.
        //
        // In Main, write a method that takes an object that implements the interface as a parameter and
        // "saves" the values e.g. calls the method defined in the interface.

        // I/O isn't covered yet, so your method should just print the values to the screen
        // Also in the Main class, write a method that restores the values to a Saveable object
        // e.g. calls the method from the interface for populating fields (see above).

        // Again, we are not going to use Java I/O: Instead use the readValues() below to
        // simulate getting values from a file - this allows you to type as many values as your class
        // requires, and returns an ArrayList.
        // There's a whole Java I/O section later in the course where you will get to use files, etc
    }
}