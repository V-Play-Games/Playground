package bluej.vpg.util;
public class Char
{
    public static int toInt (char a)
    {
        int i = -1;
        switch (a)
        {
            case '0':
            i = 0;
            break;
            case '1':
            i = 1;
            break;
            case '2':
            i = 2;
            break;
            case '3':
            i = 3;
            break;
            case '4':
            i = 4;
            break;
            case '5':
            i = 5;
            break;
            case '6':
            i = 6;
            break;
            case '7':
            i = 7;
            break;
            case '8':
            i = 8;
            break;
            case '9':
            i = 9;
            break;
            case '-':
            i = -2;
            break;
            case '.':
            i = -3;
        }
        return i;
    }
}