/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import ADT.LinkedList;
import ADT.ListInterface;
import entities.Applicant;

/**
 *
 * @author Jae Den
 */
public class ApplicantInitializer {
    
    public ListInterface<Applicant> InitApplicant(){
        ListInterface<Applicant> applicantList = new LinkedList();
        
        applicantList.add(new Applicant("A001","Lee", "cheras","accountant",4.0,"beginner", "69"));
        applicantList.add(new Applicant("A002","Chan", "rawang","IT",3.5,"beginner", "69"));
        applicantList.add(new Applicant("A003","Chen", "rawang","Business",2.0,"intermediate ", "69"));
        applicantList.add(new Applicant("A004","Cing", "rawang","Econs",3.0,"beginner", "69"));
        applicantList.add(new Applicant("A005","Chong", "shah alam","Jobless",1.0,"intermediate ", "69"));
        applicantList.add(new Applicant("A006","Ling", "new York","chef",1.5,"expert", "69"));
        applicantList.add(new Applicant("A007","Sing", "new York","teacher",1.5,"expert", "98"));
        applicantList.add(new Applicant("A008","Ting", "new York","cleaner",1.5,"expert", "78"));
        applicantList.add(new Applicant("A009","Ning", "new York","hawker",1.5,"expert", "78"));
        applicantList.add(new Applicant("A010","Tang", "new York","clerk",1.5,"beginner", "45"));
        
        return applicantList;
    }
}

