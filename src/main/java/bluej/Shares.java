package bluej;

// Page 102
public class Shares
{
    public static void main(String[] args)
    {
        int sharesHeld = (2000*100)/(10*10);
        System.out.println("No. of shares held currently = "+sharesHeld);
        int sharesRequired = 3000-sharesHeld;
        System.out.println("No. of shares to purchase = "+sharesRequired);
    }
}