package bluej.vpg.util;
public class Array
{
    public static String[] toLowerCase(String[] a)
    {
        String[] b = new String[a.length];
        for (int i = 0; i<a.length; i++)
            b[i] = a[i].toLowerCase();
        return b;
    }
    public static String[] toUpperCase(String[] a)
    {
        String[] b = new String[a.length];
        for (int i = 0; i<a.length; i++)
            b[i] = a[i].toUpperCase();
        return b;
    }
    public static int returnID(String[] a, String b)
    {
        for (int i = 0; i<a.length; i++)
        {
            if (b.equalsIgnoreCase(a[i]))
                return i;
        }
        return a.length-1;
    }
    public static int returnID(int[] a, int b)
    {
        for (int i = 0; i<a.length; i++)
        {
            if (b==a[i])
                return i;
        }
        return a.length-1;
    }
    public static byte[] setAll(byte[] a, byte b)
    {
        for (int i = 0; i<a.length; i++)
            a[i]=b;
        return a;
    }
    public static char[] setAll(char[] a, char b)
    {
        for (int i = 0; i<a.length; i++)
            a[i]=b;
        return a;
    }
    public static int[] setAll(int[] a, int b)
    {
        for (int i = 0; i<a.length; i++)
            a[i]=b;
        return a;
    }
    public static long[] setAll(long[] a, long b)
    {
        for (int i = 0; i<a.length; i++)
            a[i]=b;
        return a;
    }
    public static float[] setAll(float[] a, float b)
    {
        for (int i = 0; i<a.length; i++)
            a[i]=b;
        return a;
    }
    public static double[] setAll(double[] a, double b)
    {
        for (int i = 0; i<a.length; i++)
            a[i]=b;
        return a;
    }
    public static String[] setAll(String[] a, String b)
    {
        for (int i = 0; i<a.length; i++)
            a[i]=b;
        return a;
    }
    public static byte[] subarray(byte[] a, int x, int y)
    {
        byte[] b = new byte[y-x];
        int j=0;
        while (x<y)
        {
            b[j]=a[x];
            j++;
            x++;
        }
        return b;
    }
    public static int[] subarray(int[] a, int x, int y)
    {
        int[] b = new int[y-x];
        int j=0;
        while (x<y)
        {
            b[j]=a[x];
            j++;
            x++;
        }
        return b;
    }
    public static long[] subarray(long[] a, int x, int y)
    {
        long[] b = new long[y-x];
        int j=0;
        while (x<y)
        {
            b[j]=a[x];
            j++;
            x++;
        }
        return b;
    }
    public static char[] subarray(char[] a, int x, int y)
    {
        char[] b = new char[y-x];
        int j=0;
        while (x<y)
        {
            b[j]=a[x];
            j++;
            x++;
        }
        return b;
    }
    public static float[] subarray(float[] a, int x, int y)
    {
        float[] b = new float[y-x];
        int j=0;
        while (x<y)
        {
            b[j]=a[x];
            j++;
            x++;
        }
        return b;
    }
    public static double[] subarray(double[] a, int x, int y)
    {
        double[] b = new double[y-x];
        int j=0;
        while (x<y)
        {
            b[j]=a[x];
            j++;
            x++;
        }
        return b;
    }
    public static String[] subarray(String[] a, int x, int y)
    {
        String[] b = new String[y-x];
        int j=0;
        while (x<y)
        {
            b[j]=a[x];
            j++;
            x++;
        }
        return b;
    }
    public static int sumAll(int[] a)
    {
        int b=0;
        for (int i=0; i<a.length; i++)
            b+=a[i];
        return b;
    }
    public static int sumAllPositive(int[] a)
    {
        int b=0;
        for (int i=0; i<a.length&&a[i]>0; i++)
            b+=a[i];
        return b;
    }
    public static int sumAllNegative(int[] a)
    {
        int b=0;
        for (int i=0; i<a.length&&a[i]<0; i++)
            b+=a[i];
        return b;
    }
    public static int[] stringToIntArray(String[] a)
    {
        int[] b = new int[a.length];
        for (int i=0; i<a.length; i++)
            b[i]=Integer.parseInt(a[i]);
        return b;
    }
}