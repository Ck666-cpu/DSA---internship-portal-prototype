/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.*;
import ADT.LinkedList;
import ADT.ListInterface;
import entities.Job;

/**
 *
 * @author Ching Keat
 */
public class JobDAO {

    private static final String FILE_NAME = "jobs.dat";

    // Save jobs using Serialization (ObjectOutputStream)
    public static void saveJobs(ListInterface<Job> jobList) {
        File file = new File(FILE_NAME);

        // ✅ Overwrite the file by using FileOutputStream(file, false)
        try (ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file, false))) {
            System.out.println("Saving updated job list to file...");
            ooStream.writeObject(jobList); // ✅ Write only the latest job list
            System.out.println("Successfully saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving jobs: " + e.getMessage());
        }
    }

    // Load jobs using Deserialization (ObjectInputStream)
    public static ListInterface<Job> loadJobs() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No file found, returning an empty list.");
            return new LinkedList<>();
        }

        try (ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file))) {
            System.out.println("Loading job list from file...");
            return (ListInterface<Job>) oiStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading jobs: " + e.getMessage());
            //e.printStackTrace(); // Print detailed error
        }

        return new LinkedList<>();
    }

}
