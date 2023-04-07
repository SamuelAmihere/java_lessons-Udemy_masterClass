package billiy.don;

import java.nio.channels.Pipe;

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("Peace");
        dog.eat();
        dog.breathe();
        System.out.println("-------------------------");

        Parrot parrot = new Parrot("African Gray");
        parrot.eat();
        parrot.breathe();
        parrot.fly();
        System.out.println("-------------------------");

        Penguin penguin = new Penguin("Emperor");
        penguin.eat();
        penguin.breathe();
        penguin.fly();
    }
}