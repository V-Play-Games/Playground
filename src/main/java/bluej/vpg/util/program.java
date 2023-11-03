package bluej.vpg.util;
public class program
{
    public static void showError(int en, String e)
    {
        GameText.println("Error e"+((en<10)?"0"+en:en)+": "+e);
    }
    public static void showError(int en, String e, int  b)
    {
        GameText.print("Error e");
        while (b>num.length(en))
        {
            GameText.print("0");
            b--;
        }
        GameText.print(en+": "+e+"\n\0\0\0Terminating\0\0\0.\0\0\0.\0\0\0.");
    }
    public static void terminate()
    {
        GameText.print("\0\0\0Terminating\0\0\0.\0\0\0.\0\0\0.");
        System.exit(0);
    }
}