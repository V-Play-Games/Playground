package bluej.vpg.util;
public class RGB
{
    public static String toHex(int r, int g, int b)
    {
        String hex="";
        if (r>255||r<0)
            hex+="Error e01: Invalid Input. r is "+((r>255)? "more than 255":"less than 0")+"/n";
        if (g>255||g<0)
            hex+="Error e02: Invalid Input. g is"+((g>255)? "more than 255":"less than 0")+"/n";
        if (b>255||b<0)
            hex+="Error e03: Invalid Input. b is"+((b>255)? "more than 255":"less than 0");
        if (!(hex==""))
            return hex;
        if (r==0)
            hex+="00";
        else if (r<16)
            hex+="0"+Dec.toHex(r);
        else
            hex+=Dec.toHex(r);
        if (g==0)
            hex+="00";
        else if (g<16)
            hex+="0"+Dec.toHex(g);
        else
            hex+=Dec.toHex(g);
        if (b==0)
            hex+="00";
        else if (b<16)
            hex+="0"+Dec.toHex(b);
        else
            hex+=Dec.toHex(b);
        return hex;
    }
}