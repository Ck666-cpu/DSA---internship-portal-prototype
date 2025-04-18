/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import ADT.ListInterface;

/**
 *
 * @author Jae Den
 */
public class Login {

    private ListInterface<Applicant> applicantList;

    public Login(ListInterface<Applicant> applicantList) {
        this.applicantList = applicantList;
    }

    public Applicant loginValidation(String id, String password) {
        for (int i = 1; i <= applicantList.getNumberOfEntries(); i++) {  // Assuming 1-based index
            Applicant applicant = applicantList.getEntry(i); //loop tru ma
            //System.out.println("id" + applicant.getId() + "pass" + applicant.getPassword());
            if (applicant.getId().equals(id) && applicant.getPassword().equals(password)) {
                //System.out.println("id" + applicant.getId() + "pass" + applicant.getPassword());
                return applicant;  // Successful login
            }
        }
        return null;  // Login failed
    }
}
