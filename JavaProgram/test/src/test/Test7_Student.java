package test;

public class Test7_Student {
    String name;
    int ban;
    int kor;
    int eng;
    int math;

    public Test7_Student(String name, int ban, int kor, int eng, int math) {
        this.name = name;
        this.ban = ban;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
    }
    
    public int getTotal() {
        return kor + eng + math;
    }

    public float getAverage() {
        return getTotal() / 3.0f;
    }
}
