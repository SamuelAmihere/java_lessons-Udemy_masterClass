package billy.don;

public class ScopeCheck {

    public int publicVar=0;
    private int privateVar=1;

    public ScopeCheck() {
        System.out.println("ScopeCheck created, publicVal= "+
                publicVar+" privateVal= "+privateVar);
    }

    public int getPrivateVar() {
        return privateVar;
    }

    public void timesTwo(int len){
        int privateVar = 2;
        for ( int i=0; i<=len; i++){
            int length = Integer.toString(i).length();
            if (length==1){
                System.out.println(" "+i+ " X 2 = "+i* privateVar);
            }else {
                System.out.println(i+ " X 2 = "+i* privateVar);
            }

        }
    }

    public class InnerClass {

        public int privateVar = 3;
        public InnerClass() {
            System.out.println("InnerClass created by "+this.getClass().getName());
        }

        public void timesTwo(int len){
            for ( int i=0; i<=len; i++){
                int length = Integer.toString(i).length();
                if (length==1){
                    System.out.println(" "+i+ " X 3 = "+i* privateVar);
                }else {
                    System.out.println(i+ " X 3 = "+i* privateVar);
                }
            }
        }
    }
}
