import java.util.*;

public class Student_Grade_System {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the student's name: ");
        String studentName = sc.nextLine();
        
        System.out.print("Enter the number of subjects: ");
        int subjs = sc.nextInt();
        
        double totalMarks = 0;
        for (int i = 1; i <= subjs; i++) {
            System.out.print("Enter marks for subject " + i + ": ");
            totalMarks += sc.nextDouble();
        }
        
        System.out.print("Enter the attendance percentage: ");
        double attendancePercentage = sc.nextDouble();
        
        double averageMarks = totalMarks / subjs;
        double totalPercentage = (averageMarks * 100) / 100;
        
        if (attendancePercentage < 75) {
            totalPercentage -= 5;
        }
        
        String grade = grade(totalPercentage);
        
        System.out.println("\n---- Student Report ----");
        System.out.println("Student Name: " + studentName);
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Marks: " + averageMarks);
        System.out.println("Attendance: " + attendancePercentage + "%");
        System.out.println("Total Percentage: " + totalPercentage + "%");
        System.out.println("Grade: " + grade);
        
        sc.close();
    }
    
    public static String grade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B+";
        } else if (percentage >= 60) {
            return "B";
        } else if (percentage >= 50) {
            return "C";
        } else if (percentage >= 40) {
            return "D";
        } else {
            return "F";
        }
    }
}
