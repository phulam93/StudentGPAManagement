package main;

import entity.MarkManagement;
import entity.Student;
import entity.Subject;
import logic_handle.MarkManagementService;
import logic_handle.StudentService;
import logic_handle.SubjectService;
import util.DataUtil;
import util.FileUtil;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainRun {

    public static Student[] STUDENTS = new Student[100];
    public static Subject[] SUBJECTS = new Subject[100];
    public static MarkManagement[] MARK_MANAGEMENT = new MarkManagement[100];

    public static void main(String[] args) {
        initData();
        menu();
    }

    private static void initData() {
        STUDENTS = (Student[]) FileUtil.readDataFromFile(StudentService.STUDENT_FILE_NAME);
        if (DataUtil.isEmptyCollection(STUDENTS)) {
            STUDENTS = new Student[100];
        }

        SUBJECTS = (Subject[]) FileUtil.readDataFromFile(SubjectService.SUBJECT_FILE_NAME);
        if (DataUtil.isEmptyCollection(SUBJECTS)) {
            SUBJECTS = new Subject[100];
        }

        MARK_MANAGEMENT = (MarkManagement[]) FileUtil.readDataFromFile(MarkManagementService.MARK_FILE_NAME);
        if (DataUtil.isEmptyCollection(MARK_MANAGEMENT)) {
            MARK_MANAGEMENT = new MarkManagement[100];
        }
    }

    private static void menu() {
        do {
            int functionChoice = showMenu();
            switch (functionChoice) {
                case 1:
                    StudentService.createNewStudent();
                    break;
                case 2:
                    StudentService.showStudent();
                    break;
                case 3:
                    SubjectService.createNewSubject();
                    break;
                case 4:
                    SubjectService.showSubject();
                    break;
                case 5:
                    MarkManagementService.createGPAManagement();
                    break;
                case 6:
                    MarkManagementService.showData();
                    break;
                case 7:
                    MarkManagementService.sortMarkOfStudent();
                    break;
                case 8:
                    MarkManagementService.calculateGPAAverage();
                    break;
                case 9:
                    // System.exit(0);
                    return;
            }
        } while (true);
    }

    private static int showMenu() {
        printMenu();
        int functionChoice = -1;
        do {
            try {
                functionChoice = new Scanner(System.in).nextInt();
            } catch (InputMismatchException ex) {
                System.out.print("Chức năng cần chọn là 1 số nguyên, yêu cầu nhập lại: ");
                continue;
            }
            if (functionChoice >= 1 && functionChoice <= 9) {
                break;
            }
            System.out.print("Chức năng vừa chọn không hợp lệ, vui lòng nhập lại: ");
        } while (true);
        return functionChoice;
    }

    private static void printMenu() {
        System.out.println("\n--------PHẦN MỀM QUẢN LÝ ĐIỂM GPA CỦA SINH VIÊN------\n");
        System.out.println("1. Nhập danh sách sinh viên mới.");
        System.out.println("2. In ra danh sách sinh viên.");
        System.out.println("3. Nhập danh sách môn học mới.");
        System.out.println("4. In ra danh sách môn học.");
        System.out.println("5. Lập bảng quản lý điểm của các sinh viên.");
        System.out.println("6. Hiện bảng quản lý điểm của các sinh viên.");
        System.out.println("7. Sắp xếp danh sách bảng điểm của sinh viên.");
        System.out.println("8. Tính điểm tổng kết cho mỗi sinh viên.");
        System.out.println("9. Thoát");
        System.out.print(" Xin mời chọn chức năng: ");
    }
}
