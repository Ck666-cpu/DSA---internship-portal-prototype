/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Boundary;

import java.util.Scanner;

import ADT.ListInterface;
import entities.Interview;
import entities.Matching;

/**
 *
 * @author Xuan Yu
 */

public class InterviewSchedulerUI {
    private Scanner scanner = new Scanner(System.in);

    public int displayMenu() {
        System.out.println("\n=== INTERVIEW SCHEDULER MENU ===");
        System.out.println("1. Schedule Interview");
        System.out.println("2. View All Interviews");
        System.out.println("3. Set Interview Score");
        System.out.println("4. View Ranked Interviews");
        System.out.println("5. Filter Successful Candidates");
        System.out.println("6. Delete Interview");
        System.out.println("7. Generate All Interviews Report");
        System.out.println("8. Generate Successful Candidates Report");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public String promptInterviewId() {
        System.out.print("Enter Interview ID: ");
        return scanner.nextLine().trim();
    }

    public String promptInterviewer() {
        System.out.print("Enter Interviewer Name: ");
        return scanner.nextLine().trim();
    }

    public String promptDate() {
        System.out.print("Enter Date (YYYY-MM-DD): ");
        return scanner.nextLine().trim();
    }

    public String promptTime() {
        System.out.print("Enter Time (HH:MM): ");
        return scanner.nextLine().trim();
    }

    public int getScoreInput() {
        int score = -1;
        while (score < 0 || score > 100) {
            System.out.print("Enter score (0-100): ");
            try {
                score = Integer.parseInt(scanner.nextLine().trim());
                if (score < 0 || score > 100) {
                    System.out.println("Score must be between 0 and 100.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid score format.");
            }
        }
        return score;
    }

    public int getMinimumScoreForSuccess() {
        int score = -1;
        while (score < 0 || score > 100) {
            System.out.print("Enter minimum score to filter successful candidates: ");
            try {
                score = Integer.parseInt(scanner.nextLine().trim());
                if (score < 0 || score > 100) {
                    System.out.println("Score must be between 0 and 100.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
        return score;
    }

    public void showInterviews(ListInterface<Interview> list) {
        System.out.println("\n=== INTERVIEW LIST ===");
        if (list.isEmpty()) {
            System.out.println("No interviews to display.");
            return;
        }

        for (int i = 1; i <= list.getNumberOfEntries(); i++) {
            Interview interview = list.getEntry(i);
            if (interview != null) {
                System.out.println(interview);
            }
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public Matching selectMatching(ListInterface<Matching> matchList) {
        if (matchList.isEmpty()) {
            System.out.println("No matchings available.");
            return null;
        }

        System.out.println("\n=== AVAILABLE MATCHINGS ===");
        for (int i = 1; i <= matchList.getNumberOfEntries(); i++) {
            Matching match = matchList.getEntry(i);
            System.out.println(i + ". Applicant ID: " + match.getApplicantID() + " | Job: " + match.getMatchItem());
        }

        System.out.print("Select a matching (1-" + matchList.getNumberOfEntries() + ", or 0 to cancel): ");
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice == 0) return null;
            if (choice >= 1 && choice <= matchList.getNumberOfEntries()) {
                return matchList.getEntry(choice);
            }
        } catch (NumberFormatException e) {
            // fall through
        }

        System.out.println("Invalid selection.");
        return null;
    }
}
