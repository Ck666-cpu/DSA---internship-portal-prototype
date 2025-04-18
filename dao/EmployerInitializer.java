/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import ADT.LinkedList;
import ADT.ListInterface;
import entities.Employer;

/**
 *
 * @author Ching Keat
 */
public class EmployerInitializer {
    
    public ListInterface<Employer> InitEmployer(){
        ListInterface<Employer> employerList = new LinkedList();
        
        employerList.add(new Employer("E123","Lee", "ABC Sdn.Bhd.","cheras"));
        employerList.add(new Employer("E124","Tan", "Saudi Sdn.Bhd.","Kajang"));
        employerList.add(new Employer("E125","Wong", "High Tech Sdn.Bhd.","Rawang"));
        employerList.add(new Employer("E126","Nabil", "Big Firm Account Enterprise","Kelantan"));
        employerList.add(new Employer("E127","Ekaash", "doing Company","Penang"));
        employerList.add(new Employer("E128","Tong", "whiteboard Company","Penang"));
        employerList.add(new Employer("E129","Lau", "One time Company","Penang"));
        employerList.add(new Employer("E130","Shiva", "One century Company","Penang"));
        employerList.add(new Employer("E131","Sean", "just start Company","Penang"));
        employerList.add(new Employer("E132","Anthony", "BAby Company","Penang"));

        return employerList;
    }
}
