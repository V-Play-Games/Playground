package bluej.vpg.util;
public class time
{
    public static void test_timer()
    {
        System.out.println("0 second passed.");
        delay(1000);
        System.out.println("1 second passed.");
        delay(1000);
        for (int i=2; i<61; i++)
        {
            System.out.println(i+ " seconds passed.");
            delay(1000);
        }
    }
    public static void delay(long ms)
    {
        long time=System.currentTimeMillis();
        while (ms>=System.currentTimeMillis()-time){}
    }
}