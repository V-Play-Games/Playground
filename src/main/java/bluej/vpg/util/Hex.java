package bluej.vpg.util;
public class Hex
{
    public static String clarify(String s)
    {
        String hex="";
        int i;
        for (i=0; i<s.length(); i++)
        {
            switch (s.toUpperCase().charAt(i))
            {
                case '0':
                hex+="0";
                break;
                case '1':
                hex+="1";
                break;
                case '2':
                hex+="2";
                break;
                case '3':
                hex+="3";
                break;
                case '4':
                hex+="4";
                break;
                case '5':
                hex+="5";
                break;
                case '6':
                hex+="6";
                break;
                case '7':
                hex+="7";
                break;
                case '8':
                hex+="8";
                break;
                case '9':
                hex+="9";
                break;
                case 'A':
                hex+="A";
                break;
                case 'B':
                hex+="B";
                break;
                case 'C':
                hex+="C";
                break;
                case 'D':
                hex+="D";
                break;
                case 'E':
                hex+="E";
                break;
                case 'F':
                hex+="F";
                break;
            }
        }
        return hex;
    }
    public static int toDec(String hex)
    {
        int dec=0,i,face=0;
        hex=clarify(hex).toUpperCase();
        for (i = 1; i<=hex.length(); i++)
        {
            switch (hex.charAt(hex.length()-i))
            {
                case '0':
                face=0;
                break;
                case '1':
                face=1;
                break;
                case '2':
                face=2;
                break;
                case '3':
                face=3;
                break;
                case '4':
                face=4;
                break;
                case '5':
                face=5;
                break;
                case '6':
                face=6;
                break;
                case '7':
                face=7;
                break;
                case '8':
                face=8;
                break;
                case '9':
                face=9;
                break;
                case 'A':
                face=10;
                break;
                case 'B':
                face=11;
                break;
                case 'C':
                face=12;
                break;
                case 'D':
                face=13;
                break;
                case 'E':
                face=14;
                break;
                case 'F':
                face=15;
                break;
            }
            dec+=face*(int)Math.pow(16,i-1);
        }
        return dec;
    }
    public static long toOctal(String hex)
    {
        return Dec.toOctal(toDec(hex));
    }
    public static long toBin(String hex)
    {
        return Dec.toBin(toDec(hex));
    }
    public static String toRGB(String hex)
    {
        String rgb="";
        int r,g,b;
        hex = clarify(hex);
        if (hex.length()>6)
        {
            String s=hex;
            hex="";
            for (int i=s.length()-6; i<s.length(); i++)
            {
                hex+=s.charAt(i);
            }
        }
        while (hex.length()<6)
        {
            hex="0"+hex;
        }
        r=toDec(hex.charAt(0)+""+hex.charAt(1));
        g=toDec(hex.charAt(2)+""+hex.charAt(3));
        b=toDec(hex.charAt(4)+""+hex.charAt(5));
        if (r<10)
            rgb+="00"+r;
        else if (r<100)
            rgb+="0"+r;
        else
            rgb+=r;
        rgb+=" ";
        if (g<10)
            rgb+="00"+g;
        else if (g<100)
            rgb+="0"+g;
        else
            rgb+=g;
        rgb+=" ";
        if (b<10)
            rgb+="00"+b;
        else if (b<100)
            rgb+="0"+b;
        else
            rgb+=b;
        return rgb;
    }
}