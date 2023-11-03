package bluej;

import java.util.*;
public class Factorization
{
    public static void main()
    {
        int a,b,c,i,ii;
        String ta="",tb="",tc="",ti="",tii="";
        System.out.println("I will help you solve questions in the form ax2 + bx +c or ax2 + bxy + cy2");
        System.out.println("Enter a.");
        Scanner in = new Scanner(System.in);
        a = Integer.parseInt(in.next());
        if (a!=Math.abs(a))
            ta="("+Integer.toString(a)+")";
        else
            ta=Integer.toString(a);
        System.out.println("Enter b.");
        b = Integer.parseInt(in.next());
        if (b!=Math.abs(b))
            tb="("+Integer.toString(b)+")";
        else
            tb=Integer.toString(b);
        System.out.println("Enter c.");
        c = Integer.parseInt(in.next());
        if (c!=Math.abs(c))
            tc="("+Integer.toString(c)+")";
        else
            tc=Integer.toString(c);
        System.out.print("I will find 2 numbers (A & B) such that A+B=b & AB=ac");
        for (i=1; i<=Math.abs(a*c/2); i++)
        {
            float c1 = (float)((a*c)/i);
            ii=(a*c)/i;
            if ((c1==Math.round(c1))&&((b==ii+i)||(b==ii-i&&i==Math.abs(i))||(b==i-ii&&ii==Math.abs(ii))))
            {
                if (ii*i==a*c)
                {
                    if (i!=Math.abs(i))
                        ti="("+Integer.toString(i)+")";
                    else
                        ti=Integer.toString(i);
                    if (ii!=Math.abs(ii))
                        tii="("+Integer.toString(ii)+")";
                    else
                        tii=Integer.toString(ii);
                    System.out.println("\n\nThe two numbers are: "+ti+" & "+tii+"\n");
                    System.out.print("Because...\n"+ta+" x "+tc+" = "+ti+" x "+tii+" = "+(i*ii)+"\n"+tb+" = ");
                    if (b==ii+i)
                        System.out.print(ti+" + "+tii);
                    else if (b==ii-i&&i==Math.abs(i))
                    {
                        System.out.print(tii+" - "+ti);
                    }
                    else if (b==i-ii&&ii==Math.abs(ii))
                    {
                        System.out.print(ti+" - "+tii);
                    }
                    else {}
                }
            }
        }
        System.out.println("\n\nBYE!");
    }
}