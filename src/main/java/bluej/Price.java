package bluej;

import java.util.Scanner;

public class Price {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the printed price: ");
        long printedPrice = in.nextInt();
        double pricePaid = printedPrice * (1-0.1)*(1+0.09);
        System.out.println("Price to be paid: "+pricePaid);
    }
}