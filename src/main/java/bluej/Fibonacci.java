package bluej;

import java.util.*;

import vpg.util.GameText;
import vpg.util.program;
public class Fibonacci
{
    public static void main()
    {
        int not,i=0,j=0;
        long[] num={0,0,1};
        Scanner in = new Scanner(System.in);
        GameText.println("Welcome to Fibonacci!\0\0\0\nHow many numbers in the series should be printed?");
        not = in.nextInt();
        if (not>90||not<3)
        {
             program.showError(1,"Invalid Input. That's a very "+((not<3)?"small":"large")+" number to process.");
             program.terminate();
        }
        while(i<not/5)
        {
            while(j<5)
            {
                num[0]=num[1];
                num[1]=num[2];
                num[2]=num[0]+num[1];
                GameText.print(num[0]+" ");
                j++;
            }
            GameText.println("");
            j=0;
            i++;
        }
        for (i=1; i<=not%5; i++)
        {
            num[0]=num[1];
            num[1]=num[2];
            num[2]=num[0]+num[1];
            GameText.print(num[0]+" ");
        }
    }
}