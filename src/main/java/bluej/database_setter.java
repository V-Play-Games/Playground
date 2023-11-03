package bluej;

import java.util.*;
import vpg.util.*;
public class database_setter
{
    public static void main()
    {
        int j = 0;
        Scanner in = new Scanner(System.in);
        GameText.print("Enter:");
        String[][] args = new String[10][];
        String inp = in.nextLine();
        while(inp.equals("0"))
        {
            args[j] = new String[0];
            inp = in.nextLine();
            j++;
        }
        int[] arr = new int[200];
        for (int i=0; i<arr.length; i++)
            arr[i]=Integer.parseInt(args[i][0]);
        GameText.print("{");
        for(int i=0; j<arr.length; i++)
        {
            GameText.print("{");
            while(arr[j]/1000%1000==i+1) {
                try {
                    if(j<arr.length) {
                        GameText.print(arr[j]+"");
                        GameText.print(((arr[j+1]/1000==i+1)?",":""));
                        j++;
                    }
                } catch(Exception e) {
                    j++;
                    break;
                }
            }
            GameText.print((j==arr.length)?"":"},");
        }
        GameText.print("}}");
    }
}