package test;

public class Test8_StudentMain {
    public static void main(String[] args) {
        Test7_Student studentKim = new Test7_Student("김유신", 1, 90, 85, 70);

        Test7_Student studentLee = new Test7_Student("이순신", 1, 60, 89, 85);

        System.out.println(studentKim.name + "의 총점은 " + studentKim.getTotal() + "이고 평균은 " + studentKim.getAverage() + "입니다.");
        System.out.println(studentLee.name + "의 총점은 " + studentLee.getTotal() + "이고 평균은 " + studentLee.getAverage() + "입니다.");
    }
}
