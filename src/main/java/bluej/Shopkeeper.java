package bluej;// Page 102
import java.util.*;
public class Shopkeeper
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the selling price: \u20b9");
        double sp = in.nextDouble();
        double cp1 = (sp/(1+(20/100.0)));
        double cp2 = (sp/(1-(20/100.0)));
        double totalCP = cp1+cp2;
        System.out.println("Total Cost Price = \u20b9"+totalCP);
    }
}