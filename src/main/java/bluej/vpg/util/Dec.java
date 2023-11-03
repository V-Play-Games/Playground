package bluej.vpg.util;
public class Dec
{
    public static String toHex(long dec)
    {
        String hex = "";
        int digit,i=0;
        while (dec>=Math.pow(16,i))
            i++;
        i--;
        while (i>=0)
        {
            digit=(int)(dec/(int)Math.pow(16,i));
            switch (digit)
            {
                case 1:
                hex+="1";
                break;
                case 2:
                hex+="2";
                break;
                case 3:
                hex+="3";
                break;
                case 4:
                hex+="4";
                break;
                case 5:
                hex+="5";
                break;
                case 6:
                hex+="6";
                break;
                case 7:
                hex+="7";
                break;
                case 8:
                hex+="8";
                break;
                case 9:
                hex+="9";
                break;
                case 10:
                hex+="A";
                break;
                case 11:
                hex+="B";
                break;
                case 12:
                hex+="C";
                break;
                case 13:
                hex+="D";
                break;
                case 14:
                hex+="E";
                break;
                case 15:
                hex+="F";
                break;
                default:
                hex+=0;
            }
            dec%=(int)Math.pow(16,i);
            i--;
        }
        return hex;
    }
    public static long toOctal(long dec)
    {
        long octal=0;
        int i=0;
        while (dec>0)
        {
            octal = ((dec%8)*(long)Math.pow(10,i))+octal;
            dec /= 8;
            i++;
        }
        return octal;
    }
    public static long toBin(long dec)
    {
        long bin=0;
        int i=0;
        while (dec>0)
        {
            bin = ((dec%2)*(long)Math.pow(10,i))+bin;
            dec /= 2;
            i++;
        }
        return bin;
    }
}