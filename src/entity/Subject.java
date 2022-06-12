package entity;

import constant.CategorySubject;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Subject implements AutoIdIncreasable, NewDataCreatable, Serializable {

    private static int AUTO_ID = 100;

    private int id;
    private String name;
    private int numberUnitOfSubject;
    private CategorySubject categorySubject;

    public Subject() {
        this.increaseId();
    }

    public Subject(int id, String name, int numberUnitOfSubject, CategorySubject categorySubject) {
        this.increaseId();
        this.name = name;
        this.numberUnitOfSubject = numberUnitOfSubject;
        this.categorySubject = categorySubject;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberUnitOfSubject() {
        return numberUnitOfSubject;
    }

    public void setNumberUnitOfSubject(int numberUnitOfSubject) {
        this.numberUnitOfSubject = numberUnitOfSubject;
    }

    public CategorySubject getCategorySubject() {
        return categorySubject;
    }

    public void setCategorySubject(CategorySubject categorySubject) {
        this.categorySubject = categorySubject;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberUnitOfSubject=" + numberUnitOfSubject +
                ", categorySubject=" + categorySubject +
                '}';
    }

    @Override
    public void increaseId() {
        this.id = AUTO_ID++;
    }

    @Override
    public void inputNewData() {
        System.out.print("Nhập tên của môn học: ");
        this.setName(new Scanner(System.in).nextLine());
        System.out.print("Nhập số đơn vị học trình của môn: ");
        int nunberUnit = 0;
        do {
            try {
                nunberUnit = new Scanner(System.in).nextInt();
            } catch (InputMismatchException ex) {
                System.out.print("Số đơn vị học trình phải là số nguyên, yêu cầu nhập lại: ");
                continue;
            }
            if (nunberUnit > 0) {
                this.setNumberUnitOfSubject(nunberUnit);
                break;
            }
            System.out.print("Số đơn vị học trình của môn mới KHÔNG được là số âm, yêu cầu nhập lại: ");
        } while (true);

        System.out.println("Nhập loại của môn học, chọn một trong các lựa trọn dưới: ");
        System.out.println("1. Đại cương.");
        System.out.println("2. Cơ sở nghành.");
        System.out.println("3. Chuyên nghành.");
        System.out.print("Mời lựa chọn: ");
        int categorySubject = -1;
        do {
            try {
                categorySubject = new Scanner(System.in).nextInt();
            } catch (InputMismatchException ex) {
                System.out.print("Chức năng cần chọn là 1 số nguyên, yêu cầu nhập lại: ");
                continue;
            }
            if (categorySubject >= 1 && categorySubject <= 3) {
                break;
            }
            System.out.print("Chức năng vừa chọn không hợp lệ, vui lòng nhập lại: ");
        } while (true);
        switch (categorySubject) {
            case 1:
                this.setCategorySubject(CategorySubject.DAI_CUONG);
                break;
            case 2:
                this.setCategorySubject(CategorySubject.CO_SO_NGHANH);
                break;
            case 3:
                this.setCategorySubject(CategorySubject.CHUYEN_NGHANH);
                break;
        }
    }
}
