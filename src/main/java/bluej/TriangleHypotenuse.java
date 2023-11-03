package bluej;// Page 117
import java.util.Scanner;
public class TriangleHypotenuse
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter base: ");
        int b = sc.nextInt();
        System.out.print("Enter perpendicular: ");
        int p = sc.nextInt();
        double h = Math.sqrt(Math.pow(b,2)+Math.pow(p,2));
        System.out.println("Hypotenuse = "+h);
    }
}