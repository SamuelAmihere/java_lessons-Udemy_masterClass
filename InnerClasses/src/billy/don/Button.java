package billy.don;

/*
Local class Example
 */


public class Button {
    private String title;
    private IOnClickListener onClickListener;

    public Button(String title){
        this.title = title;
    }

    public void setOnClickListener(IOnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    public void onClick(){
        this.onClickListener.onClick(this.title);
    }

    // Create an Interface for OnClickListener
    public interface IOnClickListener{
        public void onClick(String title);
    }
}
