package entity;

import java.util.Scanner;

public class Student extends Person implements AutoIdIncreasable, NewDataCreatable {
    private static int AUTO_ID = 10000;

    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String grade;

    public Student() {
        this.increaseId();
    }

    public Student(String name, String address, String phoneNumber, String grade) {
        super(name, address, phoneNumber);
        this.increaseId();
        this.grade = grade;
    }

    @Override
    public final void increaseId() {
        this.id = AUTO_ID++;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", grade='" + grade + '\'' +
                '}';
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    @Override
    public void inputNewData() {
        System.out.print("Nhập tên của sinh viên: ");
        this.setName(new Scanner(System.in).nextLine());
        System.out.print("Nhập địa chỉ của sinh viên: ");
        this.setAddress(new Scanner(System.in).nextLine());
        System.out.print("Nhập số điện thoại của sinh viên: ");
        this.setPhoneNumber(new Scanner(System.in).nextLine());
        System.out.print("Nhập lớp của sinh viên: ");
        this.setGrade(new Scanner(System.in).nextLine());
    }
}
