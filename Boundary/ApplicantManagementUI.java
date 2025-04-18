/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boundary;

import java.util.Scanner;
import entities.Applicant;

/**
 *
 * @author Jae Den
 */
public class ApplicantManagementUI {

    private static Scanner scanner = new Scanner(System.in);

    public String getSelectedId() {
        System.out.print("Enter the Applicant ID: ");
        String selectedId = scanner.nextLine();

        return selectedId;
    }

    public String[] displayLoginScreen() {
        System.out.print("Enter Applicant ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Applicant Password: ");
        String password = scanner.nextLine();
        return new String[]{id, password};  // Return credentials to control layer
    }

    public void displayLoginResult(Applicant applicant) {
        if (applicant != null) {
            System.out.println("Login Successful! Welcome! \n" + applicant.getName());
        } else {
            System.out.println("Invalid ID or Password. Please try again.");
        }
    }

    public int getSelection() {
        System.out.println("\nIntern Management");
        System.out.println("1. Register new applicant");
        System.out.println("2. Login");
        System.out.println("3. Update existing applicant");
        System.out.println("4. Delete existing applicant");
        System.out.println("5. Filter applicants");
        System.out.println("6. View applicant list");
        System.out.println("7. Exit");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        return choice;
    }

    public int getloginOrRegister() {
        System.out.println("\nIntern Management");
        System.out.println("1. Register");
        System.out.println("2. Login");

        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        return choice;
    }

    public Applicant registerApplicant() { //hi
        System.out.println("[Registering applicant]");
        System.out.println("-------------------------");
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter location: ");
        String location = scanner.nextLine();
        System.out.print("Enter job type: ");
        String jobType = scanner.nextLine();
        System.out.print("Enter cgpa: ");
        double cgpa = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter skill: ");
        String skill = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Applicant newApplicant = new Applicant(id, name, location, jobType, cgpa, skill, password);

        return newApplicant;
    }

    public int selectApplicant() {//hi
        System.out.print("\nEnter applicant number:");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    public int updateApplicant() {
        System.out.println("[Updating applicant details]");
        System.out.println("--------------------------");
        System.out.println("Select attribute to update:");
        System.out.println("1. Name");
        System.out.println("2. location");
        System.out.println("3. job type");
        System.out.println("4. cgpa");
        System.out.println("5. password");
        System.out.println("--------------------------");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        return choice;
    }

    public String updateName() {
        System.out.println("Update Information");
        System.out.print("Enter the new Name: ");
        String newName = scanner.nextLine();

        return newName;
    }

    public String updateLocation() {
        System.out.println("Update Information");
        System.out.print("Enter the new Location: ");
        String newLocation = scanner.nextLine();

        return newLocation;
    }

    public String updateJobType() {
        System.out.println("Update Information");
        System.out.print("Enter the new Job Type: ");
        String newJobType = scanner.nextLine();

        return newJobType;
    }

    public String updatePassword() {
        System.out.println("Update Information");
        System.out.print("Enter the new Password: ");
        String newPassword = scanner.nextLine();

        return newPassword;
    }

    public double updateCgpa() {//hi
        System.out.println("Update Information");
        System.out.print("Enter the new info: CGPA");
        double info = scanner.nextDouble();
        scanner.nextLine();
        return info;
    }

    public boolean confirmDelete() {
        System.out.print("Are you sure you want to delete your account? (yes/no): ");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("yes");
    }

    public void removeApplicant() {
        System.out.println("[Removing applicant]");
        System.out.print("Enter applicant ID to remove: ");
        int applicantId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Applicant ID " + applicantId + " removed successfully.");
    }

    public int getFilterType() {
        System.out.println("\n\n------Filtering------");
        System.out.println("1. Applicant ID");
        System.out.println("2. Applicant name");
        System.out.println("3. Applicant location");
        System.out.println("4. Applicant job type");
        System.out.println("5. Applicant skill");
        System.out.println("6. Applicant CGPA");
        System.out.println("--------------------------");
        System.out.print("\nselect requirement for filtering applicants:");
        int type = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        return type;
    }

    public int getSortType() {
        System.out.println("\n\n------Filtering------");
        System.out.println("1. Applicant ID");
        System.out.println("2. Applicant name");
        System.out.println("3. Applicant location");
        System.out.println("4. Applicant job type");
        System.out.println("5. Applicant skill");
        System.out.println("6. Applicant CGPA");
        System.out.println("--------------------------");
        System.out.print("\nselect requirement for filtering applicants:");
        int type = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        return type;
    }

    public int showDisplayMenu() {
        System.out.println("===== Job Listing UI =====");
        System.out.println("1. Display Applicants ");
        System.out.println("2. Filter Applicants ");
        System.out.println("3. Sort Applicants");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    // Get name filter input
    public String getIdFilter() {
        System.out.print("\nEnter ID to filter: ");
        return scanner.nextLine().trim();
    }

    public String getNameFilter() {
        System.out.print("\nEnter name to filter: ");
        return scanner.nextLine().trim();

    }

// Get location filter input
    public String getLocationFilter() {
        System.out.print("\nEnter location to filter: ");
        return scanner.nextLine().trim();
    }

// Get job type filter input
    public String getJobTypeFilter() {
        System.out.print("\nEnter job type to filter (e.g.accountant, IT, Business, Econs): ");
        return scanner.nextLine().trim();
    }

// Get skill filter input
    public String getSkillFilter() {
        System.out.print("\nEnter skill to filter (e.g. beginner, intermediate, expert): ");
        return scanner.nextLine().trim();
    }

    public double getCgpaFilter() {
        while (true) {
            try {
                System.out.print("\nEnter minimum CGPA to filter: ");
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid CGPA. Please enter a valid number.");
            }
        }
    }

}
