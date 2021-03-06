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
                System.out.print("Ch???c n??ng c???n ch???n l?? 1 s??? nguy??n, y??u c???u nh???p l???i: ");
                continue;
            }
            if (functionChoice >= 1 && functionChoice <= 9) {
                break;
            }
            System.out.print("Ch???c n??ng v???a ch???n kh??ng h???p l???, vui l??ng nh???p l???i: ");
        } while (true);
        return functionChoice;
    }

    private static void printMenu() {
        System.out.println("\n--------PH???N M???M QU???N L?? ??I???M GPA C???A SINH VI??N------\n");
        System.out.println("1. Nh???p danh s??ch sinh vi??n m???i.");
        System.out.println("2. In ra danh s??ch sinh vi??n.");
        System.out.println("3. Nh???p danh s??ch m??n h???c m???i.");
        System.out.println("4. In ra danh s??ch m??n h???c.");
        System.out.println("5. L???p b???ng qu???n l?? ??i???m c???a c??c sinh vi??n.");
        System.out.println("6. Hi???n b???ng qu???n l?? ??i???m c???a c??c sinh vi??n.");
        System.out.println("7. S???p x???p danh s??ch b???ng ??i???m c???a sinh vi??n.");
        System.out.println("8. T??nh ??i???m t???ng k???t cho m???i sinh vi??n.");
        System.out.println("9. Tho??t");
        System.out.print(" Xin m???i ch???n ch???c n??ng: ");
    }
}
