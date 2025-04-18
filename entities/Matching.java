/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;

/**
 *
 * @author CHIN KAH JUN
 */
public class Matching implements Serializable {

    private String applicantID;
    private String matchItem; // the ID or Title that was matched
    private int matchScore;

    public Matching(String applicantID, String matchItem, int matchScore) {
        this.applicantID = applicantID;
        this.matchItem = matchItem;
        this.matchScore = matchScore;
    }

    public String getApplicantID() {
        return applicantID;
    }

    public String getMatchItem() {
        return matchItem;
    }

    public int getMatchScore() {
        return matchScore;
    }

    @Override
    public String toString() {
        return String.format("Applicant ID: %s | Matched Item: %s | Score: %d", applicantID, matchItem, matchScore);
    }
}
