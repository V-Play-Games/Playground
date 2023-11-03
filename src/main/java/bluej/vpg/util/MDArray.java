package bluej.vpg.util;
public class MDArray
{
    public static int maxLength(int[][] a)
    {
        int b=0,i=0;
        while(i<a.length){b=Math.max(b,a[i].length);i++;}
        return b;
    }
    public static int[][] setAll(int[][] a, int b)
    {
        int[][] c = new int[a.length][];
        for (int i=0; i<a.length; i++)
            c[i]=Array.setAll(a[i],b);
        return c;
    }
    public static int maxLength(String[][] a)
    {
        int b=0,i=0;
        while(i<a.length){b=Math.max(b,a[i].length);i++;}
        return b;
    }
    public static String[][] setAll(String[][] a, String b)
    {
        String[][] c = new String[a.length][];
        for (int i=0; i<a.length; i++)
            c[i]=Array.setAll(a[i],b);
        return c;
    }
    public static int maxLength(int[][][] a)
    {
        int b=0,i=0;
        while(i<a.length){b=Math.max(b,a[i].length);i++;}
        return b;
    }
    public static int[][][] setAll(int[][][] a, int b)
    {
        int[][][] c = new int[a.length][][];
        for (int i=0; i<a.length; i++)
            c[i]=setAll(a[i],b);
        return c;
    }
    public static int maxLength(String[][][] a)
    {
        int b=0,i=0;
        while(i<a.length){b=Math.max(b,a[i].length);i++;}
        return b;
    }
    public static String[][][] setAll(String[][][] a, String b)
    {
        String[][][] c = new String[a.length][][];
        for (int i=0; i<a.length; i++)
            c[i]=setAll(a[i],b);
        return c;
    }
}