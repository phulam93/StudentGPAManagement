package logic_handle;

import entity.MarkDetail;
import entity.MarkManagement;
import entity.Student;
import entity.Subject;
import main.MainRun;
import util.DataUtil;
import util.FileUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MarkManagementService {
    public static final String MARK_FILE_NAME = "mark.dat";
    public static void createGPAManagement() {
        if (checkData()) {
            System.out.println("Bạn cần nhập thông tin cho sinh viên và môn học trước khi nhập bảng điểm.");
            return;
        }
        System.out.print("Nhập số lượng sinh viên mà bạn muốn nhập điểm: ");
        int numberStudent = 0;

        do {
            try {
                numberStudent = new Scanner(System.in).nextInt();
            } catch (InputMismatchException ex) {
                System.out.print("Số lượng sinh viên phải là số nguyên, yêu cầu nhập lại: ");
                continue;
            }
            if (numberStudent > 0) {
                break;
            }
            System.out.print("Số lượng sinh viên KHÔNG được là số âm, yêu cầu nhập lại: ");
        } while (true);

        for (int i = 0; i < numberStudent; i++) {
            Student student = inputStudent(i);

            System.out.print("Sinh viên thứ " + (i + 1) + " có số môn học là: ");
            int numberSubject = 0;
            do {
                try {
                    numberSubject = new Scanner(System.in).nextInt();
                } catch (InputMismatchException ex) {
                    System.out.print("Số lượng môn học phải là số nguyên, yêu cầu nhập lại: ");
                    continue;
                }
                if (numberSubject > 0) {
                    break;
                }
                System.out.print("Số lượng môn học KHÔNG được là số âm, yêu cầu nhập lại: ");
            } while (true);

            MarkDetail[] markDetails = inputMarkDetail(numberSubject);


            MarkManagement markManagement = new MarkManagement(student, markDetails);
            saveMarkManagement(markManagement);

            FileUtil.writeDataToFile(MainRun.MARK_MANAGEMENT, MARK_FILE_NAME);
        }
        showData();
    }

    private static MarkDetail[] inputMarkDetail(int numberSubject) {
        MarkDetail[] markDetails = new MarkDetail[numberSubject];
        for (int j = 0; j < numberSubject; j++) {
            Subject subject = inputSubject(j);
            int checkDouble = 0;
            for (int k = 0; k < markDetails.length; k++) {
                if (markDetails[k] != null && markDetails[k].getSubject().getId() == subject.getId()) {
                    System.out.println("Môn học này đã tồn tại, mời nhập lại");
                    checkDouble = 1;
                }
            }
            if (checkDouble == 1) {
                continue;
            }

            System.out.print("Điểm của môn học thứ " + (j + 1) + " là: ");
            int mark = 0;
            do {
                try {
                    mark = new Scanner(System.in).nextInt();
                } catch (InputMismatchException ex) {
                    System.out.print("Điểm số phải là số nguyên, yêu cầu nhập lại: ");
                    continue;
                }
                if (mark >= 0 && mark <= 10) {
                    break;
                }
                System.out.print("Điểm số KHÔNG được là số âm và nhỏ hơn 10, yêu cầu nhập lại: ");
            } while (true);

            MarkDetail markDetail = new MarkDetail(subject, mark);
            for (int k = 0; k < markDetails.length; k++) {
                if (markDetails[k] == null) {
                    markDetails[k] = markDetail;
                    break;
                }
            }
        }
        return markDetails;
    }

    private static void saveMarkManagement(MarkManagement markManagement) {
        for (int j = 0; j < MainRun.MARK_MANAGEMENT.length; j++) {
            if (MainRun.MARK_MANAGEMENT[j] == null) {
                MainRun.MARK_MANAGEMENT[j] = markManagement;
                break;
            }
        }
    }

    private static boolean checkData() {
        return StudentService.isEmptyStudent() || SubjectService.isEmptySubject();
    }

    public static void sortMarkOfStudent() {
        System.out.println("Nhập lựa chọn của bạn.");
        System.out.println("1. Sắp xếp theo tên sinh viên.");
        System.out.println("2. Sắp xếp theo số tên môn học.");
        int choice = 0;
        do {
            try {
                choice = new Scanner(System.in).nextInt();
            } catch (InputMismatchException ex){
                System.out.println("Lựa chọn của ban phải là một số nguyên, mời nhập lại: ");
            }
            if (choice >= 1 && choice <= 2) {
                break;
            }
            System.out.println("Lựa chọn của bạn không hợp lệ, vui lòng nhập lại:");
        } while (true);

        if (choice == 1) {
            //sắp xếp theo tên sinh viên
            for (int i = 0; i < MainRun.MARK_MANAGEMENT.length - 1; i++) {
                if (MainRun.MARK_MANAGEMENT[i] != null) {
                    for (int j = i + 1; j < MainRun.MARK_MANAGEMENT.length; j++) {
                        if (MainRun.MARK_MANAGEMENT[j] != null) {
                            if (MainRun.MARK_MANAGEMENT[i].getStudent().getName().compareTo(MainRun.MARK_MANAGEMENT[j]
                                    .getStudent().getName()) > 0) {
                                MarkManagement temp = MainRun.MARK_MANAGEMENT[i];
                                MainRun.MARK_MANAGEMENT[i] = MainRun.MARK_MANAGEMENT[j];
                                MainRun.MARK_MANAGEMENT[j] = temp;
                            }
                        }
                    }
                }
            }

        } else if (choice == 2) {
            //sắp xếp theo tên môn học

        }
        showData();

    }

    public static void calculateGPAAverage() {
        System.out.println("Điểm tổng kết chung cho mỗi sinh viên là:");
        for (int i = 0; i < MainRun.MARK_MANAGEMENT.length; i++) {
            if (MainRun.MARK_MANAGEMENT[i] != null) {
                System.out.println(MainRun.MARK_MANAGEMENT[i].getStudent() + " có GPA là: " + MainRun.MARK_MANAGEMENT[i].averageGPA());
            }
        }
    }

    private static Subject inputSubject(int j) {
        System.out.print("Nhập mã môn học thứ " + (j + 1) + " mà sinh viên học: ");
        Subject subject = null;
        do {
            int subjectID = -1;
            do {
                try {
                    subjectID = new Scanner(System.in).nextInt();

                } catch (InputMismatchException ex) {
                    System.out.print("Mã mônhojc là số nguyên, yêu cầu nhập lại: ");
                    continue;
                }
                if (subjectID > 0) {
                    break;
                }
                System.out.print("Mã tuyến xe KHÔNG được là số âm, yêu cầu nhập lại: ");
            } while (true);

            for (int k = 0; k < MainRun.SUBJECTS.length; k++) {
                if (MainRun.SUBJECTS[k] != null && MainRun.SUBJECTS[k].getId() == subjectID) {
                    subject = MainRun.SUBJECTS[k];
                    break;
                }
            }
            if (subject != null) {
                break;
            }
            System.out.println("Không tìm thấy môn học mang mã môn họ vừa nhập, vui lòng nhập lại: ");
        } while (true);
        return subject;
    }

    private static Student inputStudent(int i) {
        System.out.print("Nhập mã sinh viên thứ " + (i + 1) + " cần nhập điểm: ");
        Student student = null;
        do {
            int studentId = 0;
            do {
                try {
                    studentId = new Scanner(System.in).nextInt();
                } catch (InputMismatchException ex) {
                    System.out.print("Mã sinh viên phải là số nguyên, yêu cầu nhập lại: ");
                    continue;
                }
                if (studentId > 0) {
                    break;
                }
                System.out.print("Mã sinh viên KHÔNG được là số âm, yêu cầu nhập lại: ");
            } while (true);

            // tìm kiếm xem có sinh viên trong danh sách hay không, nếu không có yêu cầu nhập lại
            student = StudentService.findById(studentId);
            if (!DataUtil.isNull(student)) {
                break;
            }
            System.out.print("Không tìm thấy sinh viên có mã vừa nhập, vui lòng nhập lại: ");
        } while (true);


        // kiểm tra xem sinh viên này đã tồn tại trong bảng điểm hay chưa

        return student;
    }

    public static void showData() {
        for (int j = 0; j < MainRun.MARK_MANAGEMENT.length; j++) {
            if (MainRun.MARK_MANAGEMENT[j] == null) {
                continue;
            }
            System.out.println(MainRun.MARK_MANAGEMENT[j]);
        }
    }

}
