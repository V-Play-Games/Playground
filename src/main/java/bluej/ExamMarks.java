package bluej;// Page 117
import java.util.Scanner;
public class ExamMarks
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Marks of: ");
        System.out.print("Physics: ");
        double phy = sc.nextDouble();
        System.out.print("Chemistry: ");
        double che = sc.nextDouble();
        System.out.print("Biology: ");
        double bio = sc.nextDouble();
        System.out.println("Average Marks: "+((phy+che+bio)/3)+
                           "\nRounded-off Marks: "+
                           "\nPhysics: "+Math.round(phy)+
                           "\nChemistry: "+Math.round(che)+
                           "\nBiology: "+Math.round(bio));
    }
}