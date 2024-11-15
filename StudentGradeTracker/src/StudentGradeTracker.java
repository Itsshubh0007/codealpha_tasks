import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();

        String[] studentNames = new String[numStudents];
        double[][] studentGrades = new double[numStudents][];

        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter student " + (i + 1) + "'s name: ");
            studentNames[i] = scanner.next();

            System.out.print("Enter the number of grades for " + studentNames[i] + ": ");
            int numGrades = scanner.nextInt();

            studentGrades[i] = new double[numGrades];

            for (int j = 0; j < numGrades; j++) {
                System.out.print("Enter grade " + (j + 1) + " for " + studentNames[i] + ": ");
                studentGrades[i][j] = scanner.nextDouble();
            }
        }

        for (int i = 0; i < numStudents; i++) {
            double average = calculateAverage(studentGrades[i]);
            double highest = findHighestGrade(studentGrades[i]);
            double lowest = findLowestGrade(studentGrades[i]);

            System.out.println("\nGrade Report for " + studentNames[i] + ":");
            System.out.println("Average grade: " + String.format("%.2f", average));
            System.out.println("Highest grade: " + String.format("%.2f", highest));
            System.out.println("Lowest grade: " + String.format("%.2f", lowest));
        }
    }

    private static double calculateAverage(double[] grades) {
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.length;
    }

    private static double findHighestGrade(double[] grades) {
        double highest = grades[0];
        for (int i = 1; i < grades.length; i++) {
            if (grades[i] > highest) {
                highest = grades[i];
            }
        }
        return highest;
    }

    private static double findLowestGrade(double[] grades) {
        double lowest = grades[0];
        for (int i = 1; i < grades.length; i++) {
            if (grades[i] < lowest) {
                lowest = grades[i];
            }
        }
        return lowest;
    }
}