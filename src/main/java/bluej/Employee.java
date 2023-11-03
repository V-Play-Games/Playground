package bluej;// Page 101-102
import java.util.*;
public class Employee
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Basic Pay: \u20b9");
        long bp = in.nextLong();
        double da = 0.3*bp;
        double hra = 0.15*bp;
        double pf = 0.125*bp;
        double gp = bp+da+hra;
        double np = gp-pf;
        System.out.println("Gross Pay = \u20b9"+gp+"\nNet Pay = \u20b9"+np);
    }
}