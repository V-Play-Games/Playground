package bluej;// Page 102
import java.util.*;
public class NumberSwapper
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter two unequal numbers");
        System.out.print("Enter first number: ");
        int a = in.nextInt();
        System.out.print("Enter second number: ");
        int b = in.nextInt();
        System.out.println("a = "+b+" b = "+a);
    }
}