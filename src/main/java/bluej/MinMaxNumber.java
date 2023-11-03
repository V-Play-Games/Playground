package bluej;// Page 117
import java.util.Scanner;
public class MinMaxNumber
{
    public static void main(String[] args)
    {
        System.out.println("Enter the numbers:");
        Scanner sc = new Scanner(System.in);
        System.out.print("1. ");
        int a = sc.nextInt();
        System.out.print("2. ");
        int b = sc.nextInt();
        System.out.print("3. ");
        int c = sc.nextInt();
        int min = Math.min(Math.min(a,b),c);
        int max = Math.max(Math.max(a,b),c);
        System.out.println("Greatest Number: "+max+"\nSmallest Number: "+min);
    }
}