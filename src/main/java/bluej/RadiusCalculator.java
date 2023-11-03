package bluej;// Page 117
import java.util.Scanner;
public class RadiusCalculator
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Area: ");
        double a = sc.nextDouble();
        double r = Math.sqrt(7*a/22);
        System.out.println("Radius = "+r);
    }
}