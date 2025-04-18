/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Ching Keat
 */
public class Job implements Serializable {

    private static final long serialVersionUID = 1L; // Best practice
    private String jobTitle;
    private String jobDesc;
    private String jobType;
    private String company;
    private String location;
    private double jobSalary;
    private String skillSet;
    private LocalDateTime timeCreated;
    private String EmployerID;

    public Job(String jobTitle, String jobDesc, /*type*/ String jobType, String company, String location, double jobSalary, String empID, String skillSet) {
        this.jobTitle = jobTitle;
        this.jobDesc = jobDesc;
        this.jobType = jobType;
        this.company = company;
        this.location = location;
        this.jobSalary = jobSalary;
        this.EmployerID = empID;
        this.skillSet = skillSet;
        this.timeCreated = LocalDateTime.now();
    }

    public Job(String jobTitle, String jobDesc, String jobType, String company, String location, double jobSalary) {
        this.jobTitle = jobTitle;
        this.jobDesc = jobDesc;
        this.jobType = jobType;
        this.company = company;
        this.location = location;
        this.jobSalary = jobSalary;
    }

    public Job() {

    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(double jobSalary) {
        this.jobSalary = jobSalary;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getEmployerID() {
        return EmployerID;
    }

    public void setEmployerID(String EmployerID) {
        this.EmployerID = EmployerID;
    }

    public String getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(String skillSet) {
        this.skillSet = skillSet;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    @Override
    public String toString() {
        // Format timestamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = timeCreated.format(formatter);
        return String.format("Job: %s | Description: %s | Type: %s | Company: %s | Location: %s | Salary: RM%.2f | SkillSet: %s | Time posted: %s",
                jobTitle, jobDesc, jobType, company, location, jobSalary, skillSet, formattedTime);
    }
}
