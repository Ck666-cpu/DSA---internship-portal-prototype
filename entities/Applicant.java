package entities;

import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jae Den
 */
public class Applicant implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String location;
    private String jobType;
    private double cgpa;
    private String skill;
    private String password;

    public Applicant(String id, String name, String location, String jobType, double cgpa, String skill, String password) {

        this.id = id;
        this.name = name;
        this.location = location;
        this.jobType = jobType;
        this.cgpa = cgpa;
        this.password = password;
        this.skill = skill;
    }

    public String getLocation() {
        return location;
    }

    public String getJobType() {
        return jobType;
    }

    public String getId() { // This is where getId() comes from!
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getSkill() {
        return skill;
    }

// Fix this method
    public void setSkill(String skill) {
        this.skill = skill;
    }

// Optional: add compareTo if sorting by CGPA
    public int compareTo(Applicant other) {
        return Double.compare(this.cgpa, other.cgpa);
    }

// Optional: equals and hashCode
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Applicant other = (Applicant) obj;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

// Optional: Flexible string view
    public String toString(boolean detailed) {
        if (detailed) {
            return toString(); // existing format
        } else {
            return String.format("%s - %s", id, name);
        }
    }

//    @Override
//public String toString() {
//    return String.format(
//        "ID      | Name           | Location       | Job Type       | Skills          | CGPA   \n" +
//        "--------+----------------+----------------+----------------+-----------------+--------\n" +
//        "%-8s| %-15s| %-15s| %-15s| %-17s| %-6.2f",
//        id, name, location, jobType, skill, cgpa
//    );
//}
    @Override
    public String toString() {
        return String.format(
                "%-8s| %-15s| %-15s| %-15s| %-17s| %-6.2f",
                id, name, location, jobType, skill, cgpa
        );
    }

}
