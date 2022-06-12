package entity;

import java.io.Serializable;
import java.util.Arrays;

public class MarkManagement implements Serializable {
    private Student student;
    private MarkDetail[] markDetails;

    public MarkManagement(Student student, MarkDetail[] markDetails) {
        this.student = student;
        this.markDetails = markDetails;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public MarkDetail[] getMarkDetails() {
        return markDetails;
    }

    public void setMarkDetails(MarkDetail[] markDetails) {
        this.markDetails = markDetails;
    }

    @Override
    public String toString() {
        return "MarkManagement{" +
                "student=" + student +
                ", markDetails=" + Arrays.toString(markDetails) +
                '}';
    }

    public float averageGPA() {
        float sumMark = 0;
        int sumUnit = 0;
        for (int i = 0; i < markDetails.length; i++) {
            sumUnit += this.markDetails[i].getSubject().getNumberUnitOfSubject();
            sumMark += this.markDetails[i].getMark() * this.markDetails[i].getSubject().getNumberUnitOfSubject();
        }
        return sumMark / sumUnit;
    }
}
