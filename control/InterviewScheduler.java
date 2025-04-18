/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package control;

import ADT.LinkedList;
import ADT.ListInterface;
import dao.InterviewDAO;
import dao.matchDAO;
import entities.Interview;
import entities.Matching;
import Boundary.InterviewSchedulerUI;

/**
 *
 * @author Xuan Yu
 */

public class InterviewScheduler {

    private ListInterface<Interview> interviewList;
    private InterviewSchedulerUI ui;

    public InterviewScheduler() {
        interviewList = InterviewDAO.loadInterviews();
        ui = new InterviewSchedulerUI();
    }

    public void runInterviewScheduler() {
        int choice;
        do {
            choice = ui.displayMenu();
            switch (choice) {
                case 1 -> scheduleInterview();
                case 2 -> viewAllInterviews();
                case 3 -> setInterviewScore();
                case 4 -> rankInterviewsByScore();
                case 5 -> filterSuccessfulCandidates();
                case 6 -> deleteInterview();
                case 7 -> generateAllInterviewsReport();
                case 8 -> generateSuccessfulCandidatesReport();
                case 9 -> ui.showMessage("Exiting Interview Scheduler...");
                default -> ui.showMessage("Invalid choice. Please try again.");
            }
        } while (choice != 9);
    }

    private void scheduleInterview() {
        ListInterface<Matching> matchList = matchDAO.loadMatch();

        if (matchList.isEmpty()) {
            ui.showMessage("No matching records found.");
            return;
        }

        // Sort match list by applicant ID
        matchList.bubbleSort((a, b) -> {
            if (a == null || b == null) {
                return 0;
            }
            return a.getApplicantID().compareTo(b.getApplicantID());
        });

        Matching selectedMatch = ui.selectMatching(matchList);
        if (selectedMatch == null) {
            ui.showMessage("No match selected.");
            return;
        }

        // Auto-generate interview ID
        String interviewId = generateNextInterviewId();

        String interviewer = ui.promptInterviewer();
        String date = ui.promptDate();
        String time = ui.promptTime();

        Interview interview = new Interview(interviewId, interviewer, "", date, time);
        interview.setApplicant(selectedMatch.getApplicantID());
        interview.setJob(selectedMatch.getMatchItem());

        interviewList.add(interview);
        InterviewDAO.saveInterviews(interviewList);

        // Remove selected match and save updated matching list
        for (int i = 1; i <= matchList.getNumberOfEntries(); i++) {
            Matching m = matchList.getEntry(i);
            if (m != null && m.equals(selectedMatch)) {
                matchList.remove(i);
                break;
            }
        }
        matchDAO.saveMatch(matchList);
        // ===============================================================

        ui.showMessage("Interview scheduled successfully with ID: " + interviewId);
    }


    private String generateNextInterviewId() {
        int max = 0;
        for (int i = 1; i <= interviewList.getNumberOfEntries(); i++) {
            Interview interview = interviewList.getEntry(i);
            if (interview != null && interview.getInterviewId().matches("I\\d{3}")) {
                String numStr = interview.getInterviewId().substring(1);
                int num = Integer.parseInt(numStr);
                if (num > max) max = num;
            }
        }
        return String.format("I%03d", max + 1);
    }

    private void viewAllInterviews() {
        ui.showInterviews(interviewList);
    }

    private void setInterviewScore() {
        String interviewId = ui.promptInterviewId();
        Interview found = null;

        for (int i = 1; i <= interviewList.getNumberOfEntries(); i++) {
            Interview interview = interviewList.getEntry(i);
            if (interview != null && interview.getInterviewId().equals(interviewId)) {
                found = interview;
                break;
            }
        }

        if (found == null) {
            ui.showMessage("Interview ID not found.");
            return;
        }

        int score = ui.getScoreInput();
        found.setScore(score);
        InterviewDAO.saveInterviews(interviewList);
        ui.showMessage("Score updated successfully.");
    }

    private void rankInterviewsByScore() {
        interviewList.bubbleSort((a, b) -> {
            if (a == null || b == null) return 0;
            return Integer.compare(b.getScore(), a.getScore()); // Descending
        });

        ui.showInterviews(interviewList);
    }

    private void filterSuccessfulCandidates() {
        int minScore = ui.getMinimumScoreForSuccess();
        ListInterface<Interview> successful = new LinkedList<>();

        for (int i = 1; i <= interviewList.getNumberOfEntries(); i++) {
            Interview interview = interviewList.getEntry(i);
            if (interview != null && interview.getScore() >= minScore) {
                successful.add(interview);
            }
        }

        if (successful.isEmpty()) {
            ui.showMessage("No successful candidates found.");
        } else {
            ui.showInterviews(successful);
        }
    }

    private void deleteInterview() {
        String interviewId = ui.promptInterviewId();
        boolean found = false;

        for (int i = 1; i <= interviewList.getNumberOfEntries(); i++) {
            Interview interview = interviewList.getEntry(i);
            if (interview != null && interview.getInterviewId().equals(interviewId)) {
                interviewList.remove(i);
                InterviewDAO.saveInterviews(interviewList);
                ui.showMessage("Interview deleted successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            ui.showMessage("Interview ID not found.");
        }
    }

    private void generateAllInterviewsReport() {
        ui.showMessage("=== ALL INTERVIEWS REPORT ===");
        ui.showInterviews(interviewList);
    }

    private void generateSuccessfulCandidatesReport() {
        int minScore = ui.getMinimumScoreForSuccess();
        ListInterface<Interview> successful = new LinkedList<>();

        for (int i = 1; i <= interviewList.getNumberOfEntries(); i++) {
            Interview interview = interviewList.getEntry(i);
            if (interview != null && interview.getScore() >= minScore) {
                successful.add(interview);
            }
        }

        ui.showMessage("=== SUCCESSFUL CANDIDATES REPORT ===");
        ui.showInterviews(successful);
    }
}
