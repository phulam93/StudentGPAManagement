package logic_handle;

import entity.Student;
import entity.Subject;
import main.MainRun;
import util.FileUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SubjectService {

    public static final String SUBJECT_FILE_NAME = "subject.dat";

    public static void createNewSubject() {
        System.out.print("Bạn muốn nhập thêm mấy môn học mới: ");
        int newSubjectNumber = 0;
        do {
            try {
                newSubjectNumber = new Scanner(System.in).nextInt();
            } catch (InputMismatchException ex) {
                System.out.print("Số lượng môn học phải là số nguyên, yêu cầu nhập lại: ");
                continue;
            }
            if (newSubjectNumber > 0) {
                break;
            }
            System.out.print("Số lượng môn học KHÔNG được là số âm, yêu cầu nhập lại: ");
        } while (true);

        for (int i = 0; i < newSubjectNumber; i++) {
            System.out.println("Nhập thông tin cho môn học thứ " + (i + 1));
            Subject subject = new Subject();
            subject.inputNewData();

            // lưu thông tin vào hệ thống  ==> lưu vào mảng sach đã khai báo bên trên
            saveSubject(subject);
            FileUtil.writeDataToFile(MainRun.SUBJECTS, SUBJECT_FILE_NAME);
        }
    }

    private static void saveSubject(Subject subject) {
        for (int j = 0; j < MainRun.SUBJECTS.length; j++) {
            if (MainRun.SUBJECTS[j] == null) {
                MainRun.SUBJECTS[j] = subject;
                break;
            }
        }
    }

    public static void showSubject() {
        for (int j = 0; j < MainRun.SUBJECTS.length; j++) {
            if (MainRun.SUBJECTS[j] == null) {
                continue;
            }
            System.out.println(MainRun.SUBJECTS[j]);
        }
    }

    public static boolean isEmptySubject() {
        for (int i = 0; i < MainRun.SUBJECTS.length; i++) {
            if (MainRun.SUBJECTS[i] != null) {
                return false;
            }
        }
        return true;
    }

    public static Subject findById(int subjectID) {
        Subject subject = null;
        do {
            for (int j = 0; j < MainRun.SUBJECTS.length; j++) {
                if (MainRun.SUBJECTS[j] != null && MainRun.SUBJECTS[j].getId() == subjectID) {
                    subject = MainRun.SUBJECTS[j];
                    break;
                }
            }
            if (subject != null) {
                break;
            }
            System.out.println("Không tìm thấy môn học mang mã vừa nhập, vui lòng nhập lại: ");
        } while (true);
        return subject;
    }
}
