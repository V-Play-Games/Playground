package bluej;// Page 102
import java.util.*;
public class Discounts
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter price of article: \u20b9");
        double price = in.nextDouble();
        double d1 = price*0.3;
        double amt1 = price-d1;
        System.out.println("30% discount = \u20b9"+d1);
        System.out.println("Amount after 30% discount = \u20b9"+amt1);
        double d2 = price*0.2;
        double amt2 = price-d2;
        double d3 = amt2*0.1;
        amt2 -= d3;
        System.out.println("20% discount = \u20b9"+d2);
        System.out.println("10% discount = \u20b9"+d3);
        System.out.println("Amount after successive discounts = \u20b9"+amt2);
    }
}