/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import ADT.LinkedList;
import ADT.ListInterface;

/**
 *
 * @author Ching Keat
 */
public class Employer {

    private String employerID;
    private String employerName;
    private String company;
    private String location;
    private ListInterface<Job> jobs;

    public Employer(String employerID, String employerName, String company, String location) {
        this.employerID = employerID;
        this.employerName = employerName;
        this.company = company;
        this.location = location;
        this.jobs = new LinkedList();
    }

    public Employer() {

    }

    public Employer(String employerID) {
        this.employerID = employerID;
    }

    public Employer(String employerID, String employerName) {
        this.employerID = employerID;
        this.employerName = employerName;
    }

    public Employer(String employerID, String employerName, String company) {
        this.employerID = employerID;
        this.employerName = employerName;
        this.company = company;
    }

    public String getEmployerID() {
        return employerID;
    }

    public void setEmployerID(String employerID) {
        this.employerID = employerID;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
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

    public ListInterface<Job> getJobs() {
        return jobs;
    }

    public void setJobs(ListInterface<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public String toString() {
        return "Employer{" + "employerID=" + employerID + ", employerName=" + employerName + ", company=" + company + ", location=" + location + ", jobs=" + jobs + '}';
    }

    public void displayEmployerJobs() {
        int position = 1;
        System.out.println("");
        for (int i = 0; i < jobs.getNumberOfEntries(); i++) {
            System.out.println("list:");
            System.out.println(i + ". " + jobs.getEntry(position).toString());
        }
    }

}
