package bluej;// Page 102
import java.util.*;
public class Interest
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Amount: \u20b9");
        double p = in.nextDouble();
        double si = p*10*3/100.0;
        double am = p*Math.pow(1+(10/100.0),3);
        double ci = am-p;
        System.out.print("Difference between CI & SI: \u20b9"+(ci-si));
    }
}