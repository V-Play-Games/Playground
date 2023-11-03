package bluej.vpg.util;
public class num
{
    public static int length(long a)
    {
        int l=0;
        while (a>0)
        {
            a /= 10;
            l++;
        }
        return l;
    }
    public static long reverse(long a)
    {
        long r=0;
        while (a>0)
        {
            r = r*10+a%10;
            a /= 10;
        }
        return r;
    }
    public static double digitAt(double n, int p)
    {
        return Math.floor(n%Math.pow(10,p)/Math.pow(10,p-1));
    }
}