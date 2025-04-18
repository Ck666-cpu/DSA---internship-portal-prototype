/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import ADT.LinkedList;
import ADT.ListInterface;
import Boundary.ApplicantManagementUI;
import entities.Applicant;
import dao.ApplicantDAO;
import dao.ApplicantInitializer;
import entities.Login;
import java.util.Comparator;
import utility.Utility;

/**
 *
 * @author Jae Den
 */
public class ApplicantManagement {

    private Applicant loggedInApplicant;
    private final ApplicantManagementUI appUI = new ApplicantManagementUI();
    private Login login;
    private final Utility msg = new Utility();

//ListInterface<Applicant> applicantList = ApplicantDAO.loadApplicants();
    private ListInterface<Applicant> applicantList;

    public ApplicantManagement(ListInterface<Applicant> applicantList) {
        this.applicantList = applicantList;
    }

    public ApplicantManagement() {

    }

    public void runApplicantManagement() {
        ListInterface<Applicant> applicantList = ApplicantDAO.loadApplicants();

        if (applicantList == null || applicantList.isEmpty()) {
            ApplicantInitializer initApplicant = new ApplicantInitializer();
            applicantList = initApplicant.InitApplicant(); // only initialize if the file has no data
        }

        int choice;

        do {

            ListInterface<Applicant> displayList;
            choice = appUI.getSelection(); // Get user choice

            switch (choice) {
                case 1:
                    Applicant newApplicant = appUI.registerApplicant();

                    boolean exists = false;
                    for (int i = 1; i <= applicantList.getNumberOfEntries(); i++) {
                        Applicant a = applicantList.getEntry(i);
                        if (a.getId().equals(newApplicant.getId())) {
                            exists = true;
                            break;
                        }
                    }

                    if (exists) {
                        System.out.println("Applicant already exists in system.");
                    } else {
                        System.out.println("Registered successfully. Please login.");
                        applicantList.add(newApplicant);
                        ApplicantDAO.saveApplicants(applicantList);
                    }
                    break;

                case 2:
                    login = new Login(applicantList);
                    handleLogin(); // Only breaks loop if successful
//            
                    break;

                case 3: // Update applicant

                    handleUpdateApplicant(applicantList);

                    ApplicantDAO.saveApplicants(applicantList);
                    break;

                case 4: // Delete applicant
                    //displayList = applicantList;
                    //displayList.traverseForward();
                    handleDeleteApplicant(applicantList);

                    ApplicantDAO.saveApplicants(applicantList);
                    break;

                case 5: // Filter applicant
                    displayList = applicantList;
                    //output the report first from list

                    displayList.traverseForward();
                    displayApplicants(displayList);

                    break;

                case 6: // View applicants
                    displayList = applicantList;
                    displayList.traverseForward();

                    break;

                case 7: // Quit
                    msg.displayEndManage();
                    ApplicantDAO.saveApplicants(applicantList);

                    loggedInApplicant = null;
                    return;

                default:
                    msg.displayInvalidChoice();
                    break;
            }
        } while (choice != 7);

    }

    public void handleLogin() {

        String[] credentials = appUI.displayLoginScreen();  // Get input from UI
        loggedInApplicant = login.loginValidation(credentials[0], credentials[1]); // Call your existing login() check id and pass ma, return null if takde
        appUI.displayLoginResult(loggedInApplicant); // Show login result in UI
    }

    public void handleUpdateApplicant(ListInterface<Applicant> applicantList) {

        if (loggedInApplicant == null) {
            System.out.println("Please login into the system first");
            return;
        }
        ListInterface<Applicant> displayList;
        displayList = applicantList;
        displayList.traverseForward();

        String selectedId = appUI.getSelectedId();

// Search for the applicant by ID
        Applicant selectedApplicant = null;
        for (int i = 1; i <= applicantList.getNumberOfEntries(); i++) {
            Applicant applicant = applicantList.getEntry(i);
            if (applicant.getId().equalsIgnoreCase(selectedId)) {
                selectedApplicant = applicant;
                break;
            }
        }

// Check if applicant was found
        if (selectedApplicant != null) {
            System.out.println("You selected: " + selectedApplicant.getName());
            // Continue with logic here
        } else {
            System.out.println("No applicant found with ID: " + selectedId);
        }

        if (selectedApplicant != null) {

            if (loggedInApplicant.getId() != null && loggedInApplicant.getId().equals(selectedApplicant.getId())) {
                //then can choose which attr u wanna change

                // Step 4: Proceed with asking what attribute they want to update
                int choice = appUI.updateApplicant();  // Ask which attribute to update

                switch (choice) {
                    case 1: // Update Name
                        // Get the new name from the user and set it to the applicant's name
                        System.out.println(selectedApplicant + "\n");
                        String newName = appUI.updateName();
                        String tempName = selectedApplicant.getName(); // old name
                        selectedApplicant.setName(newName);
                        System.out.println(selectedApplicant + " \nName has been updated");
                        System.out.println("From " + tempName + " to " + newName);

                        ApplicantDAO.saveApplicants(applicantList);
                        break;

                    case 2: // Update Location
                        // Get the new location from the user and set it to the applicant's location
                        System.out.println(selectedApplicant + "\n");
                        String tempLocation = selectedApplicant.getLocation();
                        String newLocation = appUI.updateLocation();
                        selectedApplicant.setLocation(newLocation);
                        System.out.println(selectedApplicant + " \nLocation has been updated");
                        System.out.println("From " + tempLocation + " to " + newLocation);

                        ApplicantDAO.saveApplicants(applicantList);
                        break;

                    case 3: // Update Job Type
                        // Get the new job type from the user and set it to the applicant's job type
                        System.out.println(selectedApplicant + "\n");
                        String tempJob = selectedApplicant.getJobType();
                        String newJobType = appUI.updateJobType();
                        selectedApplicant.setJobType(newJobType);
                        System.out.println(selectedApplicant + " \nJob type has been updated");
                        System.out.println("From " + tempJob + " to " + newJobType);

                        ApplicantDAO.saveApplicants(applicantList);
                        break;

                    case 4: // Update CGPA
                        // Get the new CGPA from the user and set it to the applicant's CGPA
                        System.out.println(selectedApplicant + "\n");
                        Double tempCgpa = selectedApplicant.getCgpa();
                        double newCgpa = appUI.updateCgpa();
                        selectedApplicant.setCgpa(newCgpa);
                        System.out.println(selectedApplicant + " \nCGPA has been updated");
                        System.out.println("From " + tempCgpa + " to " + newCgpa);

                        ApplicantDAO.saveApplicants(applicantList);
                        break;

                    case 5: // Update Password
                        // Get the new password from the user and set it to the applicant's password
                        System.out.println(selectedApplicant + "\n");
                        String tempPassword = selectedApplicant.getPassword();
                        String newPassword = appUI.updatePassword();
                        selectedApplicant.setPassword(newPassword);
                        System.out.println(selectedApplicant + " \nPassword has been updated");
                        System.out.println("From " + tempPassword + " to " + newPassword);

                        ApplicantDAO.saveApplicants(applicantList);
                        break;
                    default:
                        System.out.println("Invalid");
                }

            } else {
                System.out.println("You can only update your own details.");
                // Exit the method
            }

        } else {
            System.out.println("Please reenter valid position or login first");
        }

    }

    public void handleDeleteApplicant(ListInterface<Applicant> applicantList) {

        if (loggedInApplicant == null) {
            System.out.println("Please login into the system first");
            return;
        }

        ListInterface<Applicant> displayList;
        displayList = applicantList;
        displayList.traverseForward();

        // Ask for applicant ID instead of position
        String selectedId = appUI.getSelectedId();

// Search for the applicant by ID
        Applicant selectedApplicant = null;
        int pos = 0;
        for (int i = 1; i <= applicantList.getNumberOfEntries(); i++) {
            Applicant applicant = applicantList.getEntry(i);
            if (applicant.getId().equalsIgnoreCase(selectedId)) {
                selectedApplicant = applicant;
                pos = i;
                break;
            }
        }

        if (selectedApplicant != null) {

            if (loggedInApplicant.getId() != null && loggedInApplicant.getId().equals(selectedApplicant.getId())) {
                //then can choose which attr u wanna change

                // Step 3: Display the applicant's details for confirmation
                //appUI.displayApplicantDetails(selectedApplicant);
                boolean comfirmDelete = appUI.confirmDelete();

                if (comfirmDelete) { //bro this aint running, and check for login or not
                    // Step 5: Remove the applicant from the list
                    applicantList.remove(pos); // Remove the applicant from the list

                    // Step 6: Display confirmation
                    //appUI.removeApplicant();
                    System.out.println(selectedApplicant + "\nThe applicant above has been deleted");
                    System.out.println("Your account has been deleted");
                    ApplicantDAO.saveApplicants(applicantList);

                } else {
                    return;
                }
                //step 4 logout
                loggedInApplicant = null;
                //keluar bang
                //new ApplicantManagement(applicantList).runApplicantManagement();

            } else {
                System.out.println("You can only delete your own details.");
            }

        } else {
            System.out.println("Please enter a valid position for deletion");
        }
    }

    private void displayApplicants(ListInterface<Applicant> applicantList) { //display list here is affected
        boolean keepDisplay = true;
        //ListInterface<Applicant> displayList = applicantList;

        do {
            ListInterface<Applicant> displayList = applicantList;
            ListInterface<Applicant> sortedList;
            int choice = appUI.showDisplayMenu();
            switch (choice) {
                case 1:
                    displayList.traverseForward();
                    break;
                case 2:
                    displayList = filterJob(appUI.getFilterType(), displayList);
                    displayList.traverseForward();
                    break;
                case 3:
                    sortedList = sortApplicant(appUI.getSortType(), displayList);
                    sortedList.traverseForward();
                    break;

                case 4:
                    System.out.println("end display job...");
                    keepDisplay = false;
                    break;

                default:
                    //msg.displayInvalidChoice();
                    break;
            }
        } while (keepDisplay);

    }

    private ListInterface<Applicant> filterJob(int filterType, ListInterface<Applicant> applicantList) {
        ListInterface<Applicant> filteredList = new LinkedList<>();
        ListInterface<Applicant> tempList = new LinkedList<>();
        String value; //value means value of any attr
        Double cgpa;
        switch (filterType) {

            case 1: // Filter by Job Description
                value = appUI.getIdFilter(); // value of jobDesc
                System.out.println("noe" + applicantList.getNumberOfEntries());
                tempList = applicantList.filter(applicant -> applicant.getId().equals(value)); // Filter the list

                if (tempList.isEmpty()) {
                    System.out.println("No matches of applicant found.");
                } else {
                    filteredList = tempList; // Update filteredList only if matches are found
                }
                break;

            case 2: // Filter by job type
                value = appUI.getNameFilter();
                filteredList = applicantList.filter(applicant -> applicant.getName().equalsIgnoreCase(value));
                if (filteredList.isEmpty()) {
                    System.out.println("No matches of applicant found.");
                }
                break;

            case 3: // Filter by company Name
                value = appUI.getLocationFilter();
                filteredList = applicantList.filter(applicant -> applicant.getLocation().equalsIgnoreCase(value));
                if (filteredList.isEmpty()) {
                    System.out.println("No matches of applicant found.");
                }
                break;

            case 4: // Filter by Job Location
                value = appUI.getJobTypeFilter();
                filteredList = applicantList.filter(applicant -> applicant.getJobType().equalsIgnoreCase(value));
                if (filteredList.isEmpty()) {
                    System.out.println("No matches of applicant found.");
                }
                break;

            case 5: // Filter by Job Location
                value = appUI.getSkillFilter();
                filteredList = applicantList.filter(applicant -> applicant.getSkill().equalsIgnoreCase(value));
                if (filteredList.isEmpty()) {
                    System.out.println("No matches of applicant found.");
                }
                break;

            case 6: // Filter by CGPA

                cgpa = appUI.getCgpaFilter(); // Already validated inside this method
                filteredList = applicantList.filter(applicant -> applicant.getCgpa() >= cgpa);

                if (filteredList.isEmpty()) {
                    System.out.println("No matches of applicant found.");
                }
                break;
            default:
                System.out.println("Invalid selection.");
        }
        return filteredList;
    }

    private ListInterface<Applicant> sortApplicant(int filterType, ListInterface<Applicant> applicantList) {
        ListInterface<Applicant> sortedList = applicantList;  // Initialize the sortedList with applicantList to allow for filtering after sorting
        String value;
        switch (filterType) {
            case 1: // Sort by ID
                
                sortedList.bubbleSort(Comparator.comparing(Applicant::getId));  // Sort by ID
                System.out.println("Sorting done!");
                if (sortedList.isEmpty()) {
                    System.out.println("No applicants found.");
                }
                break;

            case 2: // Sort by Name
                sortedList.bubbleSort(Comparator.comparing(Applicant::getName));  // Sort by Name
                if (sortedList.isEmpty()) {
                    System.out.println("No applicants found.");
                }
                break;

            case 3: // Sort by Location
                sortedList.bubbleSort(Comparator.comparing(Applicant::getLocation));  // Sort by Location
                if (sortedList.isEmpty()) {
                    System.out.println("No applicants found.");
                }
                break;

            case 5: // Sort by Skills
                sortedList.bubbleSort(Comparator.comparing(Applicant::getSkill));  // Sort by Skills
                if (sortedList.isEmpty()) {
                    System.out.println("No applicants found.");
                }
                break;

            case 6: // Sort by CGPA
                sortedList.bubbleSort(Comparator.comparingDouble(Applicant::getCgpa));  // Sort by CGPA (double comparison)
                if (sortedList.isEmpty()) {
                    System.out.println("No applicants found.");
                }
                break;

            case 4: // Sort by Job Type or any other attribute you want
                sortedList.bubbleSort(Comparator.comparing(Applicant::getJobType));  // Sort by Job Type
                if (sortedList.isEmpty()) {
                    System.out.println("No applicants found.");
                }
                break;

            default:
                System.out.println("Invalid selection.");
                break;
        }
        return sortedList;
    }

}
