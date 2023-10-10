package sample;
import java.util.ArrayList;
import java.util.List;

class Student {
    private int studentID;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    public Student(int studentID, String name, String email, String phoneNumber, String address) {
        this.studentID = studentID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // Getters and setters for student attributes

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + ", Name: " + name;
    }
}

class Course {
    private int courseID;
    private String courseName;
    private String instructor;
    private int credits;
    private int maxCapacity;
    private List<Student> enrolledStudents;

    public Course(int courseID, String courseName, String instructor, int credits, int maxCapacity) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.instructor = instructor;
        this.credits = credits;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new ArrayList<>();
    }

    // Getters and setters for course attributes

    public int getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public int getCredits() {
        return credits;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean isCourseFull() {
        return enrolledStudents.size() >= maxCapacity;
    }

    public void enrollStudent(Student student) {
        if (!isCourseFull()) {
            enrolledStudents.add(student);
            System.out.println(student.getName() + " has been enrolled in " + courseName);
        } else {
            System.out.println("Sorry, " + courseName + " is full. Cannot enroll " + student.getName());
        }
    }

    @Override
    public String toString() {
        return "Course ID: " + courseID + ", Name: " + courseName + ", Instructor: " + instructor;
    }
}

class Enrollment {
	public int enrollmentID;
    public int studentID;
    public int courseID;
    public String enrollmentDate;

    public Enrollment(int enrollmentID, int studentID, int courseID, String enrollmentDate) {
        this.enrollmentID = enrollmentID;
        this.studentID = studentID;
        this.courseID = courseID;
        this.enrollmentDate = enrollmentDate;
    }
}

class CollegeManager {
    private List<Student> students;
    private List<Course> courses;

    public CollegeManager() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
        System.out.println(student.getName() + " has been added to the system.");
    }

    public void addCourse(Course course) {
        courses.add(course);
        System.out.println(course.getCourseName() + " has been added to the system.");
    }

    public void enrollStudentInCourse(int studentID, int courseID) {
        Student student = findStudentByID(studentID);
        Course course = findCourseByID(courseID);

        if (student != null && course != null) {
            course.enrollStudent(student);
        } else {
            System.out.println("Student or course not found.");
        }
    }

    public void displayStudentsInCourse(int courseID) {
        Course course = findCourseByID(courseID);
        if (course != null) {
            System.out.println("Students enrolled in " + course.getCourseName() + ":");
            for (Student student : course.getEnrolledStudents()) {
                System.out.println(student);
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    public void displayCoursesForStudent(int studentID) {
        Student student = findStudentByID(studentID);
        if (student != null) {
            System.out.println(student.getName() + " is enrolled in the following courses:");
            for (Course course : courses) {
                if (course.getEnrolledStudents().contains(student)) {
                    System.out.println(course);
                }
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private Student findStudentByID(int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }
        return null;
    }

    private Course findCourseByID(int courseID) {
        for (Course course : courses) {
            if (course.getCourseID() == courseID) {
                return course;
            }
        }
        return null;
    }
}

public class CollegeSystemDemo {
    public static void main(String[] args) {
        CollegeManager collegeManager = new CollegeManager();

        Student student1 = new Student(1, "John Doe", "john@example.com", "123-456-7890", "123 Main St");
        Student student2 = new Student(2, "Jane Smith", "jane@example.com", "987-654-3210", "456 Oak St");
        collegeManager.addStudent(student1);
        collegeManager.addStudent(student2);

        Course course1 = new Course(101, "Math 101", "Dr. Johnson", 3, 20);
        Course course2 = new Course(102, "History 101", "Dr. Brown", 3, 25);
        collegeManager.addCourse(course1);
        collegeManager.addCourse(course2);

        collegeManager.enrollStudentInCourse(1, 101);
        collegeManager.enrollStudentInCourse(2, 101);
        collegeManager.enrollStudentInCourse(1, 102);

        collegeManager.displayStudentsInCourse(101);
        collegeManager.displayCoursesForStudent(1);
        collegeManager.displayCoursesForStudent(2);
    }
}
