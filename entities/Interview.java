/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package entities;

import java.io.Serializable;

/**
 *
 * @author Xuan Yu
 */

public class Interview implements Serializable {

    private String interviewId;
    private String interviewer;
    private String applicant;
    private String job;
    private String date;
    private String time;
    private int score;

    public Interview(String interviewId, String interviewer, String applicant, String date, String time) {
        this.interviewId = interviewId;
        this.interviewer = interviewer;
        this.applicant = applicant;
        this.date = date;
        this.time = time;
        this.score = 0;
        this.job = "";
    }

    public String getInterviewId() {
        return interviewId;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public String getApplicant() {
        return applicant;
    }

    public String getJob() {
        return job;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "-------------------"
                + "\nInterview ID: " + interviewId
                + "\nInterviewer: " + interviewer
                + "\nApplicant: " + applicant
                + "\nJob: " + job
                + "\nDate: " + date
                + "\nTime: " + time
                + "\nScore: " + score;
    }
}
