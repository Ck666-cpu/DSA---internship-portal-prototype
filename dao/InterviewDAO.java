/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import ADT.LinkedList;
import ADT.ListInterface;
import entities.Interview;

import java.io.*;

/**
 *
 * @author Xuan Yu
 */

public class InterviewDAO {
    private static final String FILE_NAME = "interviews.dat";

    public static void saveInterviews(ListInterface<Interview> interviewList) {
        try (ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            ooStream.writeObject(interviewList);
        } catch (IOException e) {
            System.out.println("Error saving interviews: " + e.getMessage());
        }
    }

    public static ListInterface<Interview> loadInterviews() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new LinkedList<>();
        }

        try (ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file))) {
            return (ListInterface<Interview>) oiStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading interviews: " + e.getMessage());
        }

        return new LinkedList<>();
    }
}
