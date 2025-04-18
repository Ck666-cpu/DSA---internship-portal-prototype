/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boundary;

import java.util.Scanner;

/**
 *
 * @author CHIN KAH JUN
 */
public class MatchingEngineUI {

    private final Scanner scanner = new Scanner(System.in);

    public MatchingEngineUI() {
    }

    public int displayMenu() {
        int option;

        System.out.println("\n===== Matching Engine Menu =====");
        System.out.println("1. Match jobs for an applicant");
        System.out.println("2. Match applicants for a job");
        System.out.println("3. Generate report");
        System.out.println("0. Return to main menu");
        System.out.print("Enter choice: ");
        
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a number.");
            scanner.next(); // consume invalid input
            System.out.print("Please enter your selection: ");
        }
        option = scanner.nextInt();
        scanner.nextLine();

        return option;
    }

    public String getApplicant() {
        System.out.print("Enter Applicant ID: ");
        String applicantID = scanner.nextLine();
        return applicantID;
    }

    public String getJob() {
        System.out.print("Enter Job Title: ");
        String jobTitle = scanner.nextLine();
        return jobTitle;
    }
}
