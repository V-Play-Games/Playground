package bluej.vpg.util;
public class Octal
{
    public static long toDec(long octal)
    {
        int dec=0,i;
        for (i = 1; i<=num.length(octal); i++)
        {
            dec+=num.digitAt(octal,i)*(int)Math.pow(8,i-1);
        }
        return dec;
    }
    public static long toBin(long octal)
    {
        return Dec.toBin(toDec(octal));
    }
    public static String toHex(long octal)
    {
        return Dec.toHex(toDec(octal));
    }
}