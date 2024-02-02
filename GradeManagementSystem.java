package StudentGradeManagement;

import java.util.ArrayList;
import java.util.Scanner;

class GradeManagementSystem {
    private static ArrayList<Student> students = new ArrayList<>();
    private static final int NUM_SUBJECTS = 5; // Assuming 5 subjects

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\nStudent Grade Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. View All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    updateStudent(scanner);
                    break;
                case 3:
                    deleteStudent(scanner);
                    break;
                case 4:
                    viewAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }


private static void addStudent(Scanner scanner) {
    System.out.print("Enter student name: ");
    String name = scanner.next();

    System.out.print("Enter student roll number: ");
    int rollNumber = scanner.nextInt();

    int[] marks = new int[NUM_SUBJECTS];
    System.out.println("Enter marks for each subject:");
    for (int i = 0; i < NUM_SUBJECTS; i++) {
        System.out.print("Subject " + (i + 1) + ": ");
        marks[i] = scanner.nextInt();
    }

    students.add(new Student(name, rollNumber, marks));
    System.out.println("Student added successfully!");
}
private static void updateStudent(Scanner scanner) {
    System.out.print("Enter the roll number of the student to update: ");
    int rollNumber = scanner.nextInt();

    for (Student student : students) {
        if (student.getRollNumber() == rollNumber) {
            System.out.println("Enter updated marks for each subject:");
            for (int i = 0; i < NUM_SUBJECTS; i++) {
                System.out.print("Subject " + (i + 1) + ": ");
                student.getMarks()[i] = scanner.nextInt();
            }
            System.out.println("Student information updated successfully!");
            return;
        }
    }

    System.out.println("Student with roll number " + rollNumber + " not found.");
}

private static void deleteStudent(Scanner scanner) {
    System.out.print("Enter the roll number of the student to delete: ");
    int rollNumber = scanner.nextInt();

    for (int i = 0; i < students.size(); i++) {
        if (students.get(i).getRollNumber() == rollNumber) {
            students.remove(i);
            System.out.println("Student deleted successfully!");
            return;
        }
    }

    System.out.println("Student with roll number " + rollNumber + " not found.");
}

private static void viewAllStudents() {
    System.out.println("\nAll Students:");

    for (Student student : students) {
        System.out.println("Name: " + student.getName());
        System.out.println("Roll Number: " + student.getRollNumber());
        System.out.println("Marks: " + arrayToString(student.getMarks()));
        double percentage = calculatePercentage(student.getMarks());
        System.out.println("Percentage: " + percentage + "%");
        System.out.println("Grade: " + calculateGrade(percentage));
        System.out.println("----------------------");
    }
}
private static String arrayToString(int[] array) {
    StringBuilder result = new StringBuilder("[");
    for (int i = 0; i < array.length; i++) {
        result.append(array[i]);
        if (i < array.length - 1) {
            result.append(", ");
        }
    }
    result.append("]");
    return result.toString();
}

private static double calculatePercentage(int[] marks) {
    int totalMarks = 0;
    for (int mark : marks) {
        totalMarks += mark;
    }
    return (double) totalMarks / NUM_SUBJECTS;
}

private static String calculateGrade(double percentage) {
    if (percentage >= 90) {
        return "A";
    } else if (percentage >= 80) {
        return "B";
    } else if (percentage >= 70) {
        return "C";
    } else if (percentage >= 60) {
        return "D";
    } else {
        return "F";
    }
}
}
