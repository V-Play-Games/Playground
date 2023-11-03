package bluej;

import java.util.*;
import vpg.util.*;
public class Day_Calc
{
    public static int dayToYears (int days)
    {
        int years = 0;
        int i = 0;
        for (i = 0; i==0; i++)
        {
            for (int j = 1; !(days<365||j>3); j++)
            {
                days = days-365;
                years += 1;
                System.out.println("days = "+days+"year ="+years+"  j ="+j);
            }
            if ((true)&&(days>=366))
            {
                days -= 366;
                years += 1;
                System.out.println("dayp = "+days+"yearp ="+years);
            }
            if (days>365)
                i--;
        }
        return years*1000+days;
    }
    public static String toMonth (int a)
    {
        String month = "Error: Invalid Month.";
        switch (a)
        {
            case 1:
            month = "January";
            break;
            case 2:
            month = "February";
            break;
            case 3:
            month = "March";
            break;
            case 4:
            month = "April";
            break;
            case 5:
            month = "May";
            break;
            case 6:
            month = "June";
            break;
            case 7:
            month = "July";
            break;
            case 8:
            month = "August";
            break;
            case 9:
            month = "September";
            break;
            case 10:
            month = "October";
            break;
            case 11:
            month = "November";
            break;
            case 12:
            month = "December";
        }
        return month;
    }
    public static void main()
    {
        int a=0;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Date.");
        int date = Integer.parseInt(in.next());
        if (date>31||date<1)
        {
            System.out.println("Error e01: Invalid Date.");
            System.exit(0);
        }
        System.out.println("Enter Month.");
        int month = Integer.parseInt(in.next());
        if (month>12||month<1)
        {
            System.out.println("Error e11: Invalid Month.");
            System.exit(0);
        }
        if ((month==4||month==6||month==9||month==11)&&(!(month==2)))
        {
            System.out.println("Error e02: Invalid date. "+toMonth(month)+" has 30 days!");
        }
        System.out.println("Enter Year.");
        int year = Integer.parseInt(in.next());
        if ((!(year%4==0)||((year%100==0)&&(!(year%400==0))))&&(month==2)&&(date>28))
        {
            System.out.println("Error e03: Invalid date. February has 28 days in "+year+"!");
        }
        System.out.println("Enter the no. of days.");
        int days = Integer.parseInt(in.next());
    }
}