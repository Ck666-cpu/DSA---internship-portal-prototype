/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boundary;

import java.util.Scanner;
import entities.Job;

/**
 *
 * @author Ching Keat
 */
public class JobManagementUI {

    private static Scanner scanner = new Scanner(System.in);

    public JobManagementUI() {

    }

    public String login() {
        System.out.print("\nEnter employerID:");
        String id = scanner.nextLine();

        return id;
    }

    public int getSelection() {
        System.out.println("\nJob Management - Employers Module");
        System.out.println("1. Create Job Posting");
        System.out.println("2. Update Job Posting");
        System.out.println("3. Remove Job Posting");
        System.out.println("4. View Jobs");
        System.out.println("5. View Report");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");

        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a number.");
            scanner.next(); // consume invalid input
            System.out.print("Please enter your selection: ");
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        return choice;
    }

    public Job createJob(String empId) {
        System.out.println("[Creating Job Posting]");
        System.out.println("-------------------------");
        System.out.print("Enter Job Title: ");
        String jobTitle = scanner.nextLine();
        System.out.print("Enter Job Description: ");
        String jobDesc = scanner.nextLine();
        System.out.print("Enter Job Type: ");
        String jobType = scanner.nextLine();
        System.out.print("Enter Company Name: ");
        String company = scanner.nextLine();
        System.out.print("Enter Location: ");
        String location = scanner.nextLine();
        System.out.print("Enter Skillset(enter with comma ','):");
        String skillSet = scanner.nextLine();
        System.out.print("Enter Salary: ");
        int jobSalary = scanner.nextInt();
        
        scanner.nextLine(); // Consume newline

        Job newJob = new Job(jobTitle, jobDesc, jobType, company, location, jobSalary, empId, skillSet);
        System.out.println("Job created successfully ");

        return newJob;
    }

    //update UI
    public int selectJob() {
        System.out.print("\nEnter job number:");
        int id = scanner.nextInt();

        return id;
    }

    public int updateJob() {
        System.out.println("[Updating Job Posting]");
        System.out.println("--------------------------");
        System.out.println("Select attribute to update:");
        System.out.println("1. Job Description");
        System.out.println("2. Job Type");
        System.out.println("3. Company Name");
        System.out.println("4. Location");
        System.out.println("5. Salary");
        System.out.println("--------------------------");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        return choice;
    }

    public String updateContent() {
        System.out.println("Update Information");
        System.out.print("Enter the new info:");
        String info = scanner.nextLine();

        return info;
    }

    public double updateSalary() {
        System.out.println("Update Information");
        System.out.print("Enter the new info: RM");
        double info = scanner.nextDouble();

        return info;
    }

    public void removeJob() {
        System.out.println("[Removing Job Posting]");
        System.out.print("Enter job ID to remove: ");
        int jobId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Job ID " + jobId + " removed successfully.");
    }

    public void updateJobUI() {
        System.out.println("------Update Job------");
        System.out.println("Job posted by you:");
    }

    //filter UI
    public int getFilterType() {
        System.out.println("\n\n\n\n\n------Filtering------");
        System.out.println("1. Job Description");
        System.out.println("2. Job Type");
        System.out.println("3. Company Name");
        System.out.println("4. Location");
        System.out.println("5. Salary");
        System.out.println("6. Back");
        System.out.println("--------------------------");
        System.out.print("select requirement for filtering:");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        return choice;
    }

    public int getSortType() {
        System.out.println("\n\n\n\n\n------Sorting------");
        System.out.println("1. Job Description");
        System.out.println("2. Job Type");
        System.out.println("3. Company Name");
        System.out.println("4. Location");
        System.out.println("5. Salary");
        System.out.println("6. Back");
        System.out.println("--------------------------");
        System.out.print("select requirement for sorting:");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        return choice;
    }

    public String getFilterValue() {
        System.out.print("\n\nEnter the filter requirement:");
        String value = scanner.nextLine();

        return value;
    }

    public double getSalaryValue() {
        System.out.print("\n\nEnter the minimum salary:");
        double value = scanner.nextDouble();

        return value;
    }

    public boolean filterJobList() {
        boolean validInput;
        boolean isFilter = true;
        do {
            validInput = true;
            System.out.print("\nJob filtering? (Yes/No):");
            String filter = scanner.nextLine();
            filter = filter.toLowerCase();
            isFilter = false;
            switch (filter) {
                case "yes":
                    isFilter = true;
                    break;
                case "no":
                    isFilter = false;
                    break;
                default:
                    System.out.println("Invalid input");
                    validInput = false;
                    break;
            }
        } while (validInput == false);
        return isFilter;
    }

    public boolean continueFilter() {
        boolean validInput;
        boolean isFilter = true;
        do {
            validInput = true;
            System.out.print("\nContinue Job filtering? (Yes/No):");
            String filter = scanner.nextLine();
            filter = filter.toLowerCase();
            isFilter = false;
            switch (filter) {
                case "yes":
                    isFilter = true;
                    break;
                case "no":
                    isFilter = false;
                    break;
                default:
                    System.out.println("Invalid input");
                    validInput = false;
                    break;
            }
        } while (validInput == false);
        return isFilter;
    }

    //display UI
    public int showDisplayMenu() {
        System.out.println("===== Job Listing UI =====");
        System.out.println("1. Display Jobs (ascending)");
        System.out.println("2. Display Jobs (descending)");
        System.out.println("3. Filter Jobs ");
        System.out.println("4. Sort Jobs");
        System.out.println("5. Clear filter");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a number.");
            scanner.next(); // consume invalid input
            System.out.print("Please enter your selection: ");
        }
        int choice = scanner.nextInt();
        return choice;
    }

    public void DeleteJobUI() {
        System.out.println("------Delete Job------");
        System.out.println("Job posted by you:");
    }

}
