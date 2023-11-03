package bluej.vpg.util;
public class GameText
{
    public static void print(String s)
    {
        for (int i = 0; i<s.length(); i++)
        {
            System.out.print(s.charAt(i));
            time.delay(2);
        }
    }
    public static void println(String s)
    {
        print(s+"\n\0\0\0");
    }
    public static void GameText(String s)
    {
        s+="\n\0\0\0";
        long time;
        for (int i = 0; i<s.length(); i++)
        {
            System.out.print(s.charAt(i));
            time=System.currentTimeMillis();
            while (25>=System.currentTimeMillis()-time){}
        }
    }
}