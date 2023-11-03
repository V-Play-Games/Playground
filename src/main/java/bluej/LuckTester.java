package bluej;

import java.util.*;
public class LuckTester
{
    private static void GameText(String St)
    {
        for (int i = 0; i<St.length(); i++)
        {
            System.out.print(St.charAt(i));
            for (int j = 1; j<=35000000; j+=2)
                j-=1;
        }
    }
    public static void main()
    {
        String n3 = "\0\0\0";
        String nl = "\n"+n3;
        String d = "Disclaimer:- This program's source code is originally written by me."+nl+"It is not meant to hurt anyone's feelings.";
        String ynd = "(Type either \"Yes\" to continue or \"No\" to stop. Press Enter to confirm your choice.)";
        String s1 = "Hello, there! How are ya doin'?"+n3+n3;
        String s2 = "Welcome to Luck Tester!";
        String s3 = "Do you wish to continue? "+ynd;
        String s4 = "What is your name?";
        String w1 = "Warning: This is comletely random.";
        String w2 = "This shows your luck at particular point of time.";
        String w3 = "This can take a few minutes. Please have patience.";
        String w4 = "If the program is causing problems, try closing the program.";
        String w = w1+nl+w2+nl+w3+nl+w4;
        String p1 = "Please wait"+n3+"."+n3+"."+n3+".";
        String p2 = "Getting Source Code"+n3+"."+n3+"."+n3+".";
        String p3 = "Processing your request"+n3+"."+n3+"."+n3+".";
        String p = p1+nl+p2+nl+p3;
        String comp = n3+n3+"0% complete."+n3+n3+nl+"20% complete."+n3+n3+nl+"40% complete."+n3+n3+nl+"60% complete."+n3+n3+nl+"80% complete."+n3+n3+nl+"99% complete."+n3+n3+n3+n3+nl+"100% complete.";
        String r0 = "Alas! & uhhh... Congratulations!"+nl+"You are the rarest 0.000000000000000001% of people at this point of time."+nl+"There are only about a dozen of people born on this earth have such a bad luck as yours"+nl+"Your luck is at its worst now!";
        String r1 = "Your luck is bad at this point of time.";
        String r2 = "Your luck is below average, but not bad at this point of time.";
        String r3 = "Your luck is above average at this point of time.";
        String r4 = "Your luck is very good at this point of time.";
        String r5 = "Your luck is excellent at this point of time.";
        String r6 = "Congratulations!"+nl+"You are a rare 0.01% of people at this point of time."+nl+"Your luck is really good now!";
        String r7 = "Congratulations!"+nl+"You are the rarest 0.000000000000000001% of people at this point of time."+nl+"There are only about a dozen of people born on this earth have such a heavenly luck as yours"+nl+"Your luck is at its best now!";
        String c1 = "\t\tCredits";
        String c2 = "    Device's AI (for \"Luck Testing\")";
        String c3 = "By - Vaibhav Nargwani, A IX Grade Student";
        String c4 = "\tV Productions (2020-2020)";
        String ty = c1+nl+c2+nl+c3+nl+c4+nl+nl+"Thanks for using Luck Tester. Hope to see you soon!\nBye!";
        GameText(d+nl+nl+s1+nl+s2+nl+nl+s3+nl);
        Scanner in = new Scanner(System.in);
        char yn1 = in.next().toLowerCase().charAt(0); //BIG Brainz!
        if (yn1=='n')
        {
            GameText(nl+ty);
            System.exit(0);
        } else if (yn1=='y')
        {
            GameText(nl+s4+nl);
            String name = in.next();
            GameText(nl+"Thank you for participating, "+name+"."+nl+nl+n3+n3+w+n3+n3+n3+n3+n3+n3+nl+nl+p+nl);
            for (int i = 1; i<2; i++)
            {
                int l1 = (int) (Math.random()*10001)+1;
                int l2 = (int) (Math.random()*10001)+1;
                int l3 = (int) (Math.random()*10001)+1;
                int l4 = (int) (Math.random()*10001)+1;
                int l5 = (int) (Math.random()*10001)+1;
                GameText(nl+comp+nl+nl);
                if (l1*l2*l3*l4*l5==1)
                {
                    GameText(r0);
                } else if (l1<=2000)
                {
                    GameText(r1);
                } else if (l1>2000&&l2<=5000)
                {
                    GameText(r2);
                } else if (l1>2000&&l2>5000&&l3<=7500)
                {
                    GameText(r3);
                } else if (l1>2000&&l2>5000&&l3>7500&&l4<9000)
                {
                    GameText(r4);
                } else if (l1>2000&&l2>5000&&l3>7500&&l4>=9000&&l5<9900)
                {
                    GameText(r5);
                } else if (l1>2000&&l2>5000&&l3>7500&&l4>=9000&&l5>=9900)
                {
                    GameText(r6);
                } else if (l1*l2*l3*l4*l5==10000*10000*10000*10000*10000)
                {
                    GameText(r7);
                } else
                {
                    GameText("e002: An unknown error has occured."+nl+nl+ty+nl+nl+"Terminating"+n3+"."+n3+"."+n3+".");
                    System.exit(0);
                }
                GameText(nl+nl+"Do you wanna re-test your luck? "+ynd+nl);
                char yn2 = in.next().toLowerCase().charAt(0); //BIG Brain!
                if (yn2=='n')
                {
                    GameText(nl+"As you wish!"+nl);
                } else if (yn2=='y')
                {
                    i = 0;
                } else {
                    GameText(nl+"e001: Invalid Input. Terminating"+n3+"."+n3+"."+n3+".");
                    System.exit(0);
                }
            }
            GameText(nl+"Thanks again for paricipating, "+name+"."+nl+nl+ty);
        } else {
            GameText(nl+"e001: Invalid Input. Terminating"+n3+"."+n3+"."+n3+".");
            System.exit(0);
        }
    }
}