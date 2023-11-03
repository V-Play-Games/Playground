package bluej;// Page 102
import java.util.*;
public class CompoundInterest
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter sum of money: \u20b9");
        double p = in.nextDouble();
        double interest = p*5*1/100.0;
        p += interest;
        System.out.println("Interest for the first year = \u20b9" + interest);
        interest = p*5*1/100.0;
        p += interest;
        System.out.println("Interest for the second year = \u20b9" + interest);
        interest = p*5*1/100.0;
        p += interest;
        System.out.println("Amount after three years = \u20b9" + interest);
    }
}