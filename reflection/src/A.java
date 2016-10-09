/**
 * Created by elena on 09.10.2016.
 */
public class A {
    int i;
    String s;
    static int n = 0;

    public A() {
        i = n;
        s= "#"+i;
        n++;

    }

    @Override
    public String toString() {
        return":"+n +" "+i+" "+s;
    }
}
