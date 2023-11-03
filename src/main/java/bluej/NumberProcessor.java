package bluej;// Page 117
import java.util.Scanner;
public class NumberProcessor
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number: ");
        int n = sc.nextInt();
        System.out.print("Enter your choice: ");
        int c = sc.nextInt();
        String toPrint = "Invalid Choice";
        switch (c) {
            case 1:
                toPrint = "Natural Logarithm of "+n+" = "+Math.log(n);
                break;
            case 2:
                toPrint = "Absolute Value of "+n+" = "+Math.abs(n);
                break;
            case 3:
                toPrint = "Square Root of "+n+" = "+Math.sqrt(n);
                break;
            case 4:
                toPrint = "Cube of "+n+" = "+Math.pow(n,3);
                break;
            case 5:
                toPrint = "A Random Number: "+Math.random();
                break;
        }
        System.out.println(toPrint);
    }
}