package bluej.vpg.util;
public class Bin
{
    public static long toDec(long bin)
    {
        int dec=0,i;
        for (i = 1; i<=num.length(bin); i++)
        {
            dec+=num.digitAt(bin,i)*(int)Math.pow(2,i-1);
        }
        return dec;
    }
    public static long toOctal(long bin)
    {
        return Dec.toOctal(toDec(bin));
    }
    public static String toHex(long bin)
    {
        return Dec.toHex(toDec(bin));
    }
}