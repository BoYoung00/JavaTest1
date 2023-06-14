package JavaTest;

class Subject {
    private int grade; // 학년
    private String subject; // 과목명
    private int credit; // 학점

    public Subject(int grade, String subject, int credit) {
        this.grade = grade;
        this.subject = subject;
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "학년 : " + grade +
                ", 교과목명 : " + subject +
                ", 학점 : " + credit;
    }
}
