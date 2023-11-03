package bluej;// Page 102
import java.util.*;
public class TimeConverter
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter time in seconds: ");
        long secs = in.nextLong();
        long hrs = secs/3600;
        secs %= 3600;
        long mins = secs/60;
        secs %= 60;
        System.out.print(hrs+" Hour"+(hrs>1?"s":"")+" "+mins+ " Minute"+(mins>1?"s":"")+" "+secs+" Second"+(secs>1?"s":""));
    }
}