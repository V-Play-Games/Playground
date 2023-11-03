package bluej;// Page 102
import java.util.*;
public class CameraPrice
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter printed price of Digital Camera: \u20b9");
        double mrp = in.nextDouble();
        double disc = mrp*0.1;
        double amount = mrp-disc;
        double gst = amount*0.06;
        amount+=gst;
        System.out.println("Amount to be paid: \u20b9"+amount);
    }
}