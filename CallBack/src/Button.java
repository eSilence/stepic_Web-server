
public class Button  {
    EventHnd handler;
    public Button(EventHnd handler){
        this.handler =  handler;
    }
    public void click(){
        handler.execute();
    }

}

