import java.io.*;
import java.util.*;

class Student {
    private String name;
    private int rollNumber;
    private String grade;
    private Map<String, String> details;

    public Student(String name, int rollNumber, String grade, Map<String, String> details) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public void displayDetails() {
        // System.out.println("=================================");
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Grade: " + grade);
        System.out.println("Additional Details:");
        for (Map.Entry<String, String> entry : details.entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + entry.getValue());
        }
    }
}

class StudentManagementSystem {
    private List<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully.");
    }

    public void removeStudent(int rollNumber) {
        System.out.println("=================================");
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                students.remove(student);
                System.out.println("Student removed successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void searchStudent(int rollNumber) {
        System.out.println("=================================");
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                System.out.println("Student found:");
                student.displayDetails();
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void displayAllStudents() {
        System.out.println("=================================");
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("All Students:");
            for (Student student : students) {
                student.displayDetails();
                System.out.println();
            }
        }
    }

    public void saveDataToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Student student : students) {
                writer.println(student.getName() + "," + student.getRollNumber() + "," + student.getGrade());
                for (Map.Entry<String, String> entry : student.getDetails().entrySet()) {
                    writer.println(entry.getKey() + "," + entry.getValue());
                }
                writer.println();
            }
            System.out.println("Data saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data to file.");
        }
    }

    public void loadDataFromFile(String fileName) {
        students.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String name = null;
            int rollNumber = 0;
            String grade = null;
            Map<String, String> details = new HashMap<>();

            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    if (name != null && grade != null) {
                        Student student = new Student(name, rollNumber, grade, details);
                        students.add(student);
                    }
                    name = null;
                    rollNumber = 0;
                    grade = null;
                    details.clear();
                } else {
                    String[] parts = line.split(",");
                    if (name == null && parts.length >= 3) {
                        name = parts[0];
                        rollNumber = Integer.parseInt(parts[1]);
                        grade = parts[2];
                    } else if (parts.length >= 2) {
                        details.put(parts[0], parts[1]);
                    }
                }
            }
            System.out.println("Data loaded from file successfully.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading data from file.");
        }
    }
}

public class StudentManagementSystemApp {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentManagementSystem system = new StudentManagementSystem();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    saveDataToFile();
                    break;
                case 6:
                    loadDataFromFile();
                    break;
                case 7:
                    System.out.println();
                    System.out.println("***** Thank you for Using StudentManagementSystem *****");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            System.out.println();
        }
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("##########___Student Management System___##########");
        System.out.println("1. Add Student");
        System.out.println("2. Remove Student");
        System.out.println("3. Search Student");
        System.out.println("4. Display All Students");
        System.out.println("5. Save Data to File");
        System.out.println("6. Load Data from File");
        System.out.println("7. Exit");
        System.out.print("Select an option: ");
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter roll number: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter grade: ");
        String grade = scanner.nextLine();

        Map<String, String> details = new HashMap<>();
        System.out.println("Enter additional details (key-value pairs, empty line to finish):");
        while (true) {
            System.out.print("Key: ");
            String key = scanner.nextLine();
            if (key.isEmpty()) {
                break;
            }
            System.out.print("Value: ");
            String value = scanner.nextLine();
            details.put(key, value);
        }

        Student student = new Student(name, rollNumber, grade, details);
        system.addStudent(student);
    }

    private static void removeStudent() {
        System.out.print("Enter roll number of the student to remove: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); 

        system.removeStudent(rollNumber);
    }

    private static void searchStudent() {
        System.out.print("Enter roll number of the student to search: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();

        system.searchStudent(rollNumber);
    }

    private static void displayAllStudents() {
        system.displayAllStudents();
    }

    private static void saveDataToFile() {
        System.out.print("Enter file name to save data: ");
        String fileName = scanner.nextLine();

        system.saveDataToFile(fileName);
    }

    private static void loadDataFromFile() {
        System.out.print("Enter file name to load data: ");
        String fileName = scanner.nextLine();

        system.loadDataFromFile(fileName);
    }
}