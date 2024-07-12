package test;
import java.util.Scanner;

public class Test4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 닉네임과 비밀번호 입력 받기
        System.out.print("등록할 닉네임을 입력해 주세요 : ");
        String nickname = scanner.nextLine();

        System.out.print("비밀번호를 입력해 주세요 : ");
        String password = scanner.nextLine();

        // 메뉴 출력
        int choice;
        do {
            System.out.println("<메뉴 목록>");
            System.out.println("1. 회원 정보");
            System.out.println("2. 회원 수정");
            System.out.println("3. 회원 탈퇴");
            System.out.println("4. 종료");
            System.out.print("실행할 메뉴 번호를 입력하세요 : ");

            choice = scanner.nextInt();
            scanner.nextLine();  // nextInt 후에 남아있는 개행 문자 제거

            switch (choice) {
                case 1:
                    // 회원 정보 조회 로직
                    if (nickname != null) {
                        System.out.println("닉네임: " + nickname);
                    } else {
                        System.out.println("회원 정보가 존재하지 않습니다.");
                    }
                    break;
                case 2:
                	// 회원 수정 로직
                    System.out.print("닉네임을 입력해 주세요 : ");
                    String inputNickname = scanner.nextLine();

                    // 닉네임 확인
                    if (nickname != null && nickname.equals(inputNickname)) {
                        System.out.print("비밀번호를 입력해 주세요 : ");
                        String inputPassword = scanner.nextLine();

                        // 비밀번호 확인
                        if (password != null && password.equals(inputPassword)) {
                            System.out.print("변경할 닉네임을 입력해 주세요 : ");
                            nickname = scanner.nextLine();

                            System.out.println("닉네임 : " + nickname + " !변경 완료!");
                        } else {
                            System.out.println("비밀번호가 일치하지 않아 변경할 수 없습니다.");
                        }
                    } else {
                        System.out.println("닉네임이 일치하지 않아 변경할 수가 없습니다.");
                    }
                    break;
                case 3:
                	 // 회원 탈퇴 로직
                    System.out.print("닉네임을 입력해 주세요 : ");
                    String delNickname = scanner.nextLine();

                    // 닉네임 확인
                    if (nickname != null && nickname.equals(delNickname)) {
                        System.out.print("비밀번호를 입력해 주세요 : ");
                        String delPassword = scanner.nextLine();

                        // 비밀번호 확인
                        if (password != null && password.equals(delPassword)) {
                            nickname = null;
                            password = null;
                            System.out.println("회원 삭제가 완료되었습니다.");
                        } else {
                            System.out.println("비밀번호가 일치하지 않아 삭제할 수 없습니다.");
                        }
                    } else {
                        System.out.println("닉네임이 일치하지 않아 삭제할 수가 없습니다.");
                    }
                    break;
                case 4:
                    // 종료 로직
                    System.out.println("실행을 종료합니다.");
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택하세요.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
