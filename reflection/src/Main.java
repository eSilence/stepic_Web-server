import java.lang.reflect.*;
import java.lang.Class;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        A a = new A();

        System.out.println(a);

        Class a1 = a.getClass();
        Class a2 = A.class;
        Class a3 = Class.forName("A");


        String s = a1.getName();
        System.out.println(s);
        System.out.println("a1.getFields()");

        Field[] fields = a1.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("\t" + field.getName()+" "+ field.getType().getName() );
        }

        System.out.println();

        Class i = A.class;
        System.out.println(i);



    }
}
