package bluej;

public class Punctuate
{
    public static String Punctuate(String s)
    {
        String r="";
        char c_bi,c_i,c_ai;
        int i,cap=0,pun=0;
        for (i=0;i<s.length();i++)
        {
            if (i==0)
            {
                r=r+s.toUpperCase().charAt(0);
                continue;
            }
            if (i==s.length()-1)
            {
                r=r+s.charAt(s.length()-1);
                continue;
            }
            c_bi=s.charAt(i-1);
            c_i=s.charAt(i);
            c_ai=s.charAt(i+1);
            if (c_bi==' '&&(c_i=='i'||c_i=='I')&&(c_ai==' '||c_bi=='.'||c_bi=='?'||c_bi=='!'))
                cap=1;
            if (cap==1)
                r=r+s.toUpperCase().charAt(i);
            if (cap==0)
                r=r+s.toLowerCase().charAt(i);
            if (pun==1)
                r=r+" "+s.toUpperCase().charAt(i);
            cap=0;
            pun=0;
            if((c_bi=='.'||c_bi=='?'||c_bi=='!')&&(c_i==' '))
            {
                cap=1;
                pun=0;
            }
            if ((c_i=='.'||c_i=='?'||c_i=='!')&&(c_ai=='.'))
            {
                cap=2;
                pun=0;
            }
            else if((c_i=='.'||c_i=='?'||c_i=='!')&&!(c_ai==' '))
            {
                cap=2;
                pun=1;
            }
            if ((c_ai==' '&&(s.charAt(i+2)=='.'||s.charAt(i+2)=='?'||s.charAt(i+2)=='!'||s.charAt(i+2)==' '))||(c_ai=='.'&&(s.charAt(i+2)=='.'||s.charAt(i+2)=='?'||s.charAt(i+2)=='!')))
            {
                cap=2;
                pun=0;
            }
            if (c_ai==' '&&s.charAt(i+2)==',')
            {
                cap=2;
                pun=0;
            }
        }
        return r;
    }
    public static void credits()
    {
        System.out.println("Presented to you by Vaibhav Nargwani.\nTHIS IS CURRENTLY IN BETA!!!\nVersion:  a5.1.0 Last Edit: 08-07-20");
    }
}