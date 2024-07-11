package org.zerock.test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;

public class NetworkExample3 {
    public static void main(String[] args) {
        try {
            // 호스트 이름으로 InetAddress 객체 생성
            InetAddress inetAddress = InetAddress.getByName("www.naver.com");

            // 호스트 이름과 IP 주소 출력
            System.out.println("Host Name: " + inetAddress.getHostName());
            System.out.println("IP Address: " + inetAddress.getHostAddress());

            // Ping 연결 확인
            int timeout = 5000; // 타임아웃 시간 (밀리초)
            boolean isReachable = inetAddress.isReachable(timeout);

            // Ping 결과 출력
            if (isReachable) {
                System.out.println("Ping Successed: " + inetAddress.getHostName() + " (" + inetAddress.getHostAddress() + ") can connected.");
            } else {
                System.out.println("Ping failed: " + inetAddress.getHostName() + " (" + inetAddress.getHostAddress() + ") can't connected.");
            }
        } catch (UnknownHostException e) {
            // 호스트 이름을 찾을 수 없는 경우 예외 처리
            e.printStackTrace();
        } catch (IOException e) {
            // 입출력 예외 처리
            e.printStackTrace();
        }
    }
}
