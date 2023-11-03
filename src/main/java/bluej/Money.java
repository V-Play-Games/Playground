package bluej;

import java.util.Scanner;

public class Money {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Salary: ");
        long salary = in.nextLong();
        double food = salary/2.0;
        double rent = salary/15.0;
        double misc = salary/10.0;
        double save = salary - (food + rent + misc);
        System.out.println("Money paid in:\nFood: "+food+"\nRent: "+rent+"\nMisc: "+misc+"\nTotal Savings = "+save);
    }
}