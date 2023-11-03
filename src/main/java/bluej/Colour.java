package bluej;

import vpg.util.*;
public class Colour
{
    public static void main(String c1, String c2, int noc)
    {
        c1=Hex.toRGB(c1);
        c2=Hex.toRGB(c2);
        int[][] rgb={{0,0,0},
                     {Integer.parseInt(c1.substring(0,3)),Integer.parseInt(c1.substring(4,7)),Integer.parseInt(c1.substring(8,11))},
                     {Integer.parseInt(c2.substring(0,3)),Integer.parseInt(c2.substring(4,7)),Integer.parseInt(c2.substring(8,11))}};
        /*0 - r,g,b
          1 - r1,g1,b1
          2 - r2,g2,b2**/
        int i=0,j=0;
        while(i<=noc)
        {
            while(j<3)
            {
                rgb[0][j]=rgb[1][j]+(int)((rgb[1][j]!=rgb[2][j])?Math.floor((rgb[1][j]+rgb[2][j])*(i+1)/(noc+1))-rgb[1][j]:0);
                rgb[0][j]=(rgb[0][j]>255)?255:(rgb[0][j]<0)?0:rgb[0][j];
            }
            System.out.println("Colour no. "+(i+1)+" is #"+RGB.toHex(rgb[0][0],rgb[0][1],rgb[0][2]));
            i++;
        }
    }
}