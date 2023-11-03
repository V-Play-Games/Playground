package bluej;// Page 101
import java.util.*;
public class Pendulum
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        double t,l,g;
        System.out.println("Enter length of Pendulum.");
        l=in.nextDouble();
        System.out.println("Enter the value of g.");
        g=in.nextDouble();
        t=2*(22.0/7.0)*Math.sqrt(l/g);
        System.out.println("Time Period = "+t);
    }
}