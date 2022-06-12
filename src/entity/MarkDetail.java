package entity;

import java.io.Serializable;

public class MarkDetail implements Serializable {
    private Subject subject;
    private float mark;

    public MarkDetail(Subject subject, int mark) {
        this.subject = subject;
        this.mark = mark;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "MarkDetail{" +
                "subject=" + subject +
                ", mark=" + mark +
                '}';
    }
}
