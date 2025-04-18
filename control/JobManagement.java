/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import ADT.LinkedList;
import ADT.ListInterface;
import Boundary.JobManagementUI;
import dao.EmployerInitializer;
import dao.JobDAO;
import entities.Job;
import entities.Employer;
import utility.Utility;
import java.util.Comparator;

/**
 *
 * @author Ching Keat
 */
public class JobManagement {

    private final JobManagementUI jobUI = new JobManagementUI();
    private final Utility msg = new Utility();

    public void runJobManagement() {
        ListInterface<Job> jobList = JobDAO.loadJobs();
        EmployerInitializer initEmploy = new EmployerInitializer();
        ListInterface<Employer> employerList = initEmploy.InitEmployer();

        Employer loggedUser = login(employerList);
        int choice;
        do {
            
            msg.makeLine();
            choice = jobUI.getSelection();
            
            switch (choice) {
                case 1: //create job, save .dat
                    Job newJob = jobUI.createJob(loggedUser.getEmployerID());
                    jobList.add(newJob);
                    JobDAO.saveJobs(jobList);
                    break;
                case 2: //update
                    jobUI.updateJobUI();
                    loggedUser.setJobs(retrieveEmployerJob(loggedUser, jobList));
                    loggedUser.getJobs().traverseForward();
                    if (!loggedUser.getJobs().isEmpty()) {
                        int select = jobUI.selectJob();
                        Job selectedJob = loggedUser.getJobs().getEntry(select);
                        select = jobList.getPosition(selectedJob);  //save the position of selected job in the overall joblist
                        selectedJob = jobList.getEntry(select);
                        if (selectedJob != null) {
                            selectedJob = updateJobDetails(jobUI.updateJob(), selectedJob);
                            jobList.replace(select, selectedJob);   //updateJob;
                            JobDAO.saveJobs(jobList);
                        } else {
                            System.out.println("Invalid selection, update failed. Please do again");
                        }
                    }
                    break;

                case 3: //delete
                    jobUI.DeleteJobUI();
                    loggedUser.setJobs(retrieveEmployerJob(loggedUser, jobList));
                    loggedUser.getJobs().traverseForward();
                    if (!loggedUser.getJobs().isEmpty()) {
                        int value = jobUI.selectJob();
                        Job selectedRemoveJob = loggedUser.getJobs().getEntry(value);
                        selectedRemoveJob = jobList.getEntry(jobList.getPosition(selectedRemoveJob));     //get entry got problem
                        if (selectedRemoveJob != null) {
                            jobList.remove(jobList.getPosition(selectedRemoveJob));
                            JobDAO.saveJobs(jobList);
                        } else {
                            System.out.println("Invalid selection, delete failed. Please do again");
                        }
                    }
                    break;

                case 4: //view & filter jobs, no updating to database
                    ListInterface<Job> displayList = jobList;
                    msg.makeLine();
                    displayList.traverseForward();
                    displayJobs(displayList);
                    break;

                case 5:
                    generateReport(jobList, loggedUser.getEmployerID());
                    break;

                case 6: //quit
                    msg.displayEndManage();
                    JobDAO.saveJobs(jobList);   //update and override the jobs finalization
                    break;

                default:
                    msg.displayInvalidChoice();
                    break;
            }
        } while (choice != 6);
    }

    //support methods below
    private Job updateJobDetails(int choice, Job job) {
        switch (choice) {
            case 1:
                job.setJobDesc(jobUI.updateContent());
                break;
            case 2:
                job.setJobType(jobUI.updateContent());
                break;
            case 3:
                job.setCompany(jobUI.updateContent());
                break;
            case 4:
                job.setLocation(jobUI.updateContent());
                break;
            case 5:
                job.setJobSalary(jobUI.updateSalary());
                break;
            default:
                System.out.println("Invalid");
        }

        return job;
    }

    private ListInterface<Job> filterJob(int filterType, ListInterface<Job> jobList) {
        ListInterface<Job> filteredList = new LinkedList<>();
        String value;
        switch (filterType) {
            case 1: // Filter by Job Description
                value = jobUI.getFilterValue();
                filteredList = jobList.filter(job -> job.getJobDesc().equals(value));
                if (filteredList.isEmpty()) {
                    System.out.println("No matches job found.");
                }
                break;

            case 2: // Filter by job type
                value = jobUI.getFilterValue();
                filteredList = jobList.filter(job -> job.getJobType().equals(value));
                if (filteredList.isEmpty()) {
                    System.out.println("No matches job found.");
                }
                break;

            case 3: // Filter by company Name
                value = jobUI.getFilterValue();
                filteredList = jobList.filter(job -> job.getCompany().equals(value));
                if (filteredList.isEmpty()) {
                    System.out.println("No matches job found.");
                }
                break;

            case 4: // Filter by Job Location
                value = jobUI.getFilterValue();
                filteredList = jobList.filter(job -> job.getLocation().equals(value));
                if (filteredList.isEmpty()) {
                    System.out.println("No matches job found.");
                }
                break;

            case 5: // Filter by Job Salary
                value = jobUI.getFilterValue();
                try {
                    double salaryFilter = Double.parseDouble(value);
                    filteredList = jobList.filter(job -> job.getJobSalary() > salaryFilter);
                    if (filteredList.isEmpty()) {
                        System.out.println("No matches job found.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid salary input. Please enter a valid number.");
                }
                break;

            case 6: //stop filter
                System.out.println("returning to the previous page");
                filteredList = jobList;
                break;

            default:
                System.out.println("Invalid selection.");
        }
        return filteredList;
    }

    private ListInterface<Job> sortJob(int filterType, ListInterface<Job> jobList) {
        ListInterface<Job> sortedList;
        sortedList = jobList;
        String value;
        switch (filterType) {
            case 1: // sort by Job Description
                sortedList.bubbleSort(Comparator.comparing(Job::getJobDesc));
                if (sortedList.isEmpty()) {
                    System.out.println("No matches job found.");
                }
                break;

            case 2: // sort by job type
                sortedList.bubbleSort(Comparator.comparing(Job::getJobType));
                if (sortedList.isEmpty()) {
                    System.out.println("No matches job found.");
                }
                break;

            case 3: // sort by company Name
                sortedList.bubbleSort(Comparator.comparing(Job::getCompany));
                if (sortedList.isEmpty()) {
                    System.out.println("No matches job found.");
                }
                break;

            case 4: // sort by Job Location
                sortedList.bubbleSort(Comparator.comparing(Job::getLocation));
                if (sortedList.isEmpty()) {
                    System.out.println("No matches job found.");
                }
                break;

            case 5: // sort by Job Salary
                sortedList.bubbleSort(Comparator.comparing(Job::getJobSalary));
                break;

            case 6:
                System.out.println("returning to the previous page...");
                sortedList = jobList;
                break;

            default:
                System.out.println("Invalid selection.");
        }
        return sortedList;
    }

    private void displayJobs(ListInterface<Job> jobList) {
        boolean keepDisplay = true;
        ListInterface<Job> displayList = jobList;
        do {
            int choice = jobUI.showDisplayMenu();
            switch (choice) {
                case 1:
                    displayList.traverseForward();
                    break;
                case 2:
                    displayList.traverseBackward();
                    break;
                case 3:
                    displayList = filterJob(jobUI.getFilterType(), displayList);
                    displayList.traverseForward();
                    break;
                case 4:
                    displayList = sortJob(jobUI.getSortType(), displayList);
                    displayList.traverseForward();
                    break;
                case 5:
                    displayList = jobList;
                    System.out.println("All filter cleared");
                    break;
                case 6:
                    System.out.println("end display job...");
                    keepDisplay = false;
                    break;
                default:
                    msg.displayInvalidChoice();
                    break;
            }
        } while (keepDisplay);

    }

    //login 
    private Employer login(ListInterface<Employer> employerList) {
        boolean notEmployer = true;
        ListInterface<Employer> tempList = employerList;
        Employer current = null;
        do {
            String empId = jobUI.login();
            tempList = employerList.filter(employer -> employer.getEmployerID().equalsIgnoreCase(empId));
            if (tempList.contains(tempList.getEntry(1))) {
                current = tempList.getEntry(1);
                notEmployer = false;
            } else {
                System.out.println("Employer account does not exist. Please try again");
            }
        } while (notEmployer == true);

        return current;
    }

    private ListInterface<Job> retrieveEmployerJob(Employer employer, ListInterface<Job> jobList) {
        ListInterface<Job> filteredList = jobList.filter(job -> job.getEmployerID().equalsIgnoreCase(employer.getEmployerID()));

        return filteredList;
    }

    //report
    private void generateReport(ListInterface<Job> jobList, String employerId) {
        System.out.println("\n Job Summary Report for Employer ID: " + employerId);
        System.out.println("-------------------------------------");

        boolean found = false;
        ListInterface<String> countedJobTypes = new LinkedList<>(); // Stores counted job types

        for (int i = 1; i <= jobList.getNumberOfEntries(); i++) {
            Job currentJob = jobList.getEntry(i);

            if (currentJob.getEmployerID().equals(employerId) && !countedJobTypes.contains(currentJob.getJobType())) {
                int count = 0;

                // Count occurrences of the current job type
                for (int j = 1; j <= jobList.getNumberOfEntries(); j++) {
                    Job jobToCompare = jobList.getEntry(j);
                    if (jobToCompare.getEmployerID().equals(employerId)
                            && jobToCompare.getJobType().equals(currentJob.getJobType())) {
                        count++;
                    }
                }

                // Display the count for this job type
                System.out.println(currentJob.getJobType() + ": " + count + " job(s)");
                countedJobTypes.add(currentJob.getJobType()); // Mark job type as counted
                found = true;
            }
        }

        if (!found) {
            System.out.println("No jobs found for this employer.");
        }

        generateReportByJobType(jobList);
        generateReportByCompany(jobList);
        generateReportByLocation(jobList);
        generateReportBySalaryRange(jobList);
    }

    private void generateReportByJobType(ListInterface<Job> jobs) {
        System.out.println("\n=== Report: Number of Jobs by Job Type ===");
        ListInterface<String> checkedTypes = new ADT.LinkedList<>();

        for (int i = 1; i <= jobs.getNumberOfEntries(); i++) {
            Job job = jobs.getEntry(i);
            String jobType = job.getJobType();

            if (!checkedTypes.contains(jobType)) {
                int count = jobs.filter(j -> j.getJobType().equalsIgnoreCase(jobType)).getNumberOfEntries();
                System.out.println("Job Type: " + jobType + " | Number of Jobs: " + count);
                checkedTypes.add(jobType);
            }
        }

    }

    private void generateReportByCompany(ListInterface<Job> jobs) {
        System.out.println("\n=== Report: Number of Jobs by Company ===");
        ListInterface<String> checkedCompanies = new ADT.LinkedList<>();

        for (int i = 1; i <= jobs.getNumberOfEntries(); i++) {
            Job job = jobs.getEntry(i);
            String company = job.getCompany();

            if (!checkedCompanies.contains(company)) {
                int count = jobs.filter(j -> j.getCompany().equalsIgnoreCase(company)).getNumberOfEntries();
                System.out.println("Company: " + company + " | Number of Jobs: " + count);
                checkedCompanies.add(company);
            }
        }
    }

    private void generateReportByLocation(ListInterface<Job> jobs) {
        System.out.println("\n=== Report: Number of Jobs by Location ===");
        ListInterface<String> checkedLocations = new ADT.LinkedList<>();

        for (int i = 1; i <= jobs.getNumberOfEntries(); i++) {
            Job job = jobs.getEntry(i);
            String location = job.getLocation();

            if (!checkedLocations.contains(location)) {
                int count = jobs.filter(j -> j.getLocation().equalsIgnoreCase(location)).getNumberOfEntries();
                System.out.println("Location: " + location + " | Number of Jobs: " + count);
                checkedLocations.add(location);
            }
        }
    }

    private void generateReportBySalaryRange(ListInterface<Job> jobs) {
        System.out.println("\n=== Report: Number of Jobs by Salary Range ===");
        int below3000 = jobs.filter(j -> j.getJobSalary() < 3000).getNumberOfEntries();
        int between3000And5000 = jobs.filter(j -> j.getJobSalary() >= 3000 && j.getJobSalary() <= 5000).getNumberOfEntries();
        int above5000 = jobs.filter(j -> j.getJobSalary() > 5000).getNumberOfEntries();

        System.out.println("Below RM3000: " + below3000 + " jobs");
        System.out.println("RM3000 - RM5000: " + between3000And5000 + " jobs");
        System.out.println("Above RM5000: " + above5000 + " jobs");
    }

}
