package bluej;

import java.util.Scanner;

public class Votes {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number of voters: ");
        long voters = in.nextLong();
        long votesPolled = Math.round(voters * 0.8);
        long xVotes = Math.round(votesPolled * 0.6);
        long yVotes = votesPolled - xVotes;
        System.out.println("X got "+xVotes+" votes\nY got "+yVotes+" votes");
    }
}