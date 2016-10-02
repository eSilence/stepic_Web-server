import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Button btn = new Button(new ButtonClickHandler());
        btn.click();
        btn.click();
        btn.click();
        btn = new Button(new EventHnd() {
            @Override
            public void execute() {
                System.out.println("Эта необычная кнопка нажата!");
            }
        });
        btn.click();
        btn.click();

        Button btn2 = new Button(() -> System.out.println("Эта необычная кнопка опять нажата!"));
        btn2.click();
        btn2.click();

        EventHnd btn3 = ()-> System.out.println("lambda!");
        btn3.execute();


    }
}
