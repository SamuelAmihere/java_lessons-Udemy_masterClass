package billy.don;

public interface ITelephone {
    void powerOn();
    void dial(int phonenumber);
    void answer();
    boolean callPhone(int phonenumber);
    boolean isRinging();
}
