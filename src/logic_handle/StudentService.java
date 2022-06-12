package logic_handle;

import entity.Student;
import main.MainRun;
import util.FileUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentService {

    public static final String STUDENT_FILE_NAME = "student.dat";

    public static void createNewStudent() {
        System.out.print("Bạn muốn nhập thêm mấy sinh viên mới: ");
        int newStudentNumber = 0;
        do {
            try {
                newStudentNumber = new Scanner(System.in).nextInt();
            } catch (InputMismatchException ex) {
                System.out.print("Số lượng sinh viên phải là số nguyên, yêu cầu nhập lại: ");
                continue;
            }
            if (newStudentNumber > 0) {
                break;
            }
            System.out.print("Số lượng sinh viên KHÔNG được là số âm, yêu cầu nhập lại: ");
        } while (true);

        for (int i = 0; i < newStudentNumber; i++) {
            System.out.println("Nhập thông tin cho sinh viên thứ " + (i + 1));
            Student student = new Student();
            student.inputNewData();

            // lưu thông tin vào hệ thống  ==> lưu vào mảng sach đã khai báo bên trên
            saveStudent(student);
            FileUtil.writeDataToFile(MainRun.STUDENTS, STUDENT_FILE_NAME);
        }
    }

    private static void saveStudent(Student student) {
        for (int j = 0; j < MainRun.STUDENTS.length; j++) {
            if (MainRun.STUDENTS[j] == null) {
                MainRun.STUDENTS[j] = student;
                break;
            }
        }
    }

    public static void showStudent() {
        for (int j = 0; j < MainRun.STUDENTS.length; j++) {
            if (MainRun.STUDENTS[j] == null) {
                continue;
            }
            System.out.println(MainRun.STUDENTS[j]);
        }
    }

    public static boolean isEmptyStudent() {
        for (int i = 0; i < MainRun.STUDENTS.length; i++) {
            if (MainRun.STUDENTS[i] != null) {
                return false;
            }
        }
        return true;
    }

    public static Student findById(int studentId) {
        Student student = null;
        for (int j = 0; j < MainRun.STUDENTS.length; j++) {
            if (MainRun.STUDENTS[j] != null && MainRun.STUDENTS[j].getId() == studentId) {
                student = MainRun.STUDENTS[j];
                break;
            }
        }
        return student;
    }
}
