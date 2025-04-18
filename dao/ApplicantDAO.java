/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.*;
import ADT.LinkedList;
import ADT.ListInterface;
import entities.Applicant;

/**
 *
 * @author Jae Den
 */
public class ApplicantDAO {

    private static final String FILE_NAME = "applicants.dat";

    // Save applicants using Serialization
    public static void saveApplicants(ListInterface<Applicant> applicantList) {
        File file = new File(FILE_NAME);
        try (ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file))) {
            System.out.println("Saving applicant list to file...");
            ooStream.writeObject(applicantList);
            System.out.println("Successfully saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving applicants: " + e.getMessage());
        }
    }

    // Load applicants using Deserialization
    public static ListInterface<Applicant> loadApplicants() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No file found, returning an empty list.");
            return new LinkedList<>();
        }

        try (ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file))) {
            System.out.println("Loading applicant list from file...");
            return (ListInterface<Applicant>) oiStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading applicants: " + e.getMessage());
        }

        return new LinkedList<>();
    }
}
