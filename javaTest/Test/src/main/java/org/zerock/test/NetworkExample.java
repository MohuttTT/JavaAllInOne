package org.zerock.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkExample {
    public static void main(String[] args) {
        try {
            // 호스트 이름으로 InetAddress 객체 생성
            InetAddress inetAddress = InetAddress.getByName("www.naver.com");

            // 호스트 이름 출력
            System.out.println("Host Name: " + inetAddress.getHostName());

            // IP 주소 출력
            System.out.println("IP Address: " + inetAddress.getHostAddress());
        } catch (UnknownHostException e) {
            // 호스트 이름을 찾을 수 없는 경우 예외 처리
            e.printStackTrace();
        }
    }
}
