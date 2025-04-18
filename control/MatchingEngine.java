/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import ADT.LinkedList;
import ADT.ListInterface;
import dao.ApplicantDAO;
import dao.JobDAO;
import entities.Applicant;
import entities.Job;
import entities.Matching;
import Boundary.MatchingEngineUI;
import dao.matchDAO;
import java.text.SimpleDateFormat;
import utility.Utility;

import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author CHIN KAH JUN
 */
public class MatchingEngine {

    MatchingEngineUI MatchUI = new MatchingEngineUI();
    Utility ui = new Utility();

    public void runMatching() {
        ListInterface<Applicant> applicants = ApplicantDAO.loadApplicants();
        ListInterface<Job> jobList = JobDAO.loadJobs();
        int option = -1;
        do {

            option = MatchUI.displayMenu();
            switch (option) {
                case 1:
                    String applicantID = MatchUI.getApplicant();
                    ListInterface<Matching> jobMatches = matchApplicantToJobs(applicantID, jobList, applicants);
                    printResults(jobMatches, applicants, jobList, true);
                    // After printing, load old matches, add new matches, save all
                    ListInterface<Matching> oldMatches1 = matchDAO.loadMatch();
                    for (int i = 1; i <= jobMatches.getNumberOfEntries(); i++) {
                        oldMatches1.add(jobMatches.getEntry(i));
                    }
                    matchDAO.saveMatch(oldMatches1);
                    break;
                case 2:
                    String jobTitle = MatchUI.getJob();
                    ListInterface<Matching> applicantMatches = matchJobToApplicants(jobTitle, jobList, applicants);
                    printResults(applicantMatches, applicants, jobList, false);
                    // After printing, load old matches, add new matches, save all
                    ListInterface<Matching> oldMatches2 = matchDAO.loadMatch();
                    for (int i = 1; i <= applicantMatches.getNumberOfEntries(); i++) {
                        oldMatches2.add(applicantMatches.getEntry(i));
                    }
                    matchDAO.saveMatch(oldMatches2);
                    break;
                case 3:
                    generateReportPage();
                    break;
                case 0:
                    ui.displayEndManage();
                    break;
                default:
                    ui.displayInvalidChoice();
            }
        } while (option != 0);
    }

    public ListInterface<Matching> matchApplicantToJobs(String applicantID, ListInterface<Job> jobList1, ListInterface<Applicant> applicants1) {
        ListInterface<Applicant> applicants = applicants1;
        ListInterface<Job> jobList = jobList1;
        ListInterface<Matching> results = new LinkedList<>();

        ListInterface<Applicant> filteredApplicants = applicants.filter(a -> a.getId().equalsIgnoreCase(applicantID));
        if (filteredApplicants.isEmpty()) {
            return results;
        }

        Applicant target = filteredApplicants.getEntry(1);

        // Use anyMatch to check if at least one job matches location
        boolean hasLocationMatch = jobList.anyMatch(job
                -> job.getLocation() != null
                && target.getLocation() != null
                && job.getLocation().trim().equalsIgnoreCase(target.getLocation().trim())
        );

        if (!hasLocationMatch) {
            System.out.println("No jobs available at applicant's location!");
            return results;
        }

        for (int i = 1; i <= jobList.getNumberOfEntries(); i++) {
            Job job = jobList.getEntry(i);
            int score = calculateScore(target, job);
            results.add(new Matching(target.getId(), job.getJobTitle(), score));
        }

        results.bubbleSort(Comparator.comparingInt(Matching::getMatchScore).reversed());

        // Only return top 5 matches using subList
        if (results.getNumberOfEntries() > 5) {
            results = results.subList(1, 5);
        }

        return results;
    }

    public ListInterface<Matching> matchJobToApplicants(String jobTitle, ListInterface<Job> jobList1, ListInterface<Applicant> applicants1) {
        ListInterface<Applicant> applicants = applicants1;
        ListInterface<Job> jobList = jobList1;
        ListInterface<Matching> results = new LinkedList<>();

        ListInterface<Job> filteredJobs = jobList.filter(j -> j.getJobTitle().equalsIgnoreCase(jobTitle));
        if (filteredJobs.isEmpty()) {
            return results;
        }

        Job targetJob = filteredJobs.getEntry(1);

        // Use allMatch to check if all applicants have CGPA above 2.0
        boolean allApplicantsQualified = applicants.allMatch(app -> app.getCgpa() >= 2.0);
        if (!allApplicantsQualified) {
            System.out.println("Warning: Some applicants have CGPA below 2.0!");
        }

        for (int i = 1; i <= applicants.getNumberOfEntries(); i++) {
            Applicant applicant = applicants.getEntry(i);
            int score = calculateScore(applicant, targetJob);
            if (score >= 30) {
                results.add(new Matching(applicant.getId(), targetJob.getJobTitle(), score));
            }
        }

        results.bubbleSort(Comparator.comparingInt(Matching::getMatchScore).reversed());

        // Only return top 5 matches using subList
        if (results.getNumberOfEntries() > 8) {
            results = results.subList(1, 8);
        }

        return results;
    }

    private int calculateScore(Applicant applicant, Job job) {
        int score = 0;

        // Match location
        if (applicant.getLocation().equalsIgnoreCase(job.getLocation())) {
            score += 20;
        }
        // Match job type
        if (applicant.getJobType().equalsIgnoreCase(job.getJobType())) {
            score += 20;
        }
        // Match skills
        String applicantSkill = applicant.getSkill();
        if (applicantSkill != null && !applicantSkill.isEmpty()) {
            String lowerSkill = applicantSkill.toLowerCase();

            if (job.getSkillSet() != null && job.getSkillSet().toLowerCase().contains(lowerSkill)) {
                score += 20; // Best match, exact skill listed
            } else if (job.getJobDesc() != null && job.getJobDesc().toLowerCase().contains(lowerSkill)) {
                score += 20; // Skill mentioned in description
            } else if (job.getJobType() != null && job.getJobType().toLowerCase().contains(lowerSkill)) {
                score += 10; // Skill loosely related to job type
            }
        }

        // CGPA contributes more
        score += (int) (applicant.getCgpa() * 10);

        // Make sure the maximum score is capped at 100
        if (score > 100) {
            score = 100;
        }

        return score;
    }

    private void printResults(ListInterface<Matching> matches, ListInterface<Applicant> applicants, ListInterface<Job> jobs, boolean isApplicantToJob) {
        if (matches.isEmpty()) {
            System.out.println("No matches found.");
        } else {
            System.out.println("\n===== Match Results =====");
            for (int i = 1; i <= matches.getNumberOfEntries(); i++) {
                Matching match = matches.getEntry(i);
                System.out.println(match);  // Print the Matching object

                if (isApplicantToJob) { // Matching Applicant ➔ Job
                    Job matchedJob = jobs.filter(j -> j.getJobTitle().equalsIgnoreCase(match.getMatchItem())).getEntry(1);
                    if (matchedJob != null) {
                        System.out.println(matchedJob);  // Print full Job details
                    }
                } else { // Matching Job ➔ Applicant
                    Applicant matchedApplicant = applicants.filter(a -> a.getId().equalsIgnoreCase(match.getApplicantID())).getEntry(1);
                    if (matchedApplicant != null) {
                        System.out.println(matchedApplicant);  // Print full Applicant details
                    }
                }
                System.out.println("---------------------------------------------------");
            }
        }

    }

    private void generateReport(ListInterface<Matching> matches, boolean isApplicantToJob) {
        matchDAO.saveMatch(matches);
    }

    private void generateReportPage() {
        // Load matching results from the saved .dat file
        ListInterface<Matching> matchList = matchDAO.loadMatch();

        // Display the loaded report
        displayReport(matchList);
    }

    private void displayReport(ListInterface<Matching> matchList) {
        if (matchList.isEmpty()) {
            System.out.println("No matching data found.");
        } else {
            System.out.println("===== Matching Report =====");
            String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            System.out.println("Generated on: " + currentDate);
            System.out.println("---------------------------------------------------");

            String currentApplicant = "";
            int matchCount = 0;

            for (int i = 1; i <= matchList.getNumberOfEntries(); i++) {
                Matching match = matchList.getEntry(i);

                if (!match.getApplicantID().equals(currentApplicant)) {
                    if (!currentApplicant.isEmpty()) {
                        System.out.println(); // Line break between applicants
                    }
                    currentApplicant = match.getApplicantID();
                    matchCount = 1;
                    System.out.println("Applicant: " + currentApplicant);
                } else {
                    matchCount++;
                }

                System.out.printf("- Matched Job: %-20s (Score: %d)\n", match.getMatchItem(), match.getMatchScore());
            }

            System.out.println("---------------------------------------------------");
            System.out.println("Back to main menu...");
        }
    }

}
