package dsa;

import control.ApplicantManagement;
import control.JobManagement;
import control.InterviewScheduler;
import control.MatchingEngine;
import java.util.Scanner;

/**
 *
 * @author Ching Keat
 */
public class Dsa {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean exit = false;

        do {
            System.out.println("\n\n\n\nWelcome to Internship Portal");
            System.out.println("-----------------------------");
            System.out.println("Please select a Service");
            System.out.println("1. Job Management");
            System.out.println("2. Applicant Management");
            System.out.println("3. Matching Engine");
            System.out.println("4. Interview Scheduler");
            System.out.println("5. Exit Portal");
            System.out.println("------------------------------");
            System.out.print("Please enter your selection: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // consume invalid input
                System.out.print("Please enter your selection: ");
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    new JobManagement().runJobManagement();
                    break;
                case 2:
                    new ApplicantManagement().runApplicantManagement();
                    break;
                case 3:
                    new MatchingEngine().runMatching();
                    break;
                case 4:
                    new InterviewScheduler().runInterviewScheduler();
                    break;
                case 5:
                    System.out.println("Exiting Portal...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid input choice!!!");
            }
        } while (!exit);

        scanner.close();
    }
}
