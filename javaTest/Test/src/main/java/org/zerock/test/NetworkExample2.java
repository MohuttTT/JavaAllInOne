package org.zerock.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkExample2 {
    public static void main(String[] args) {
        try {
            // 로컬 컴퓨터 이름과 주소 저장
            InetAddress localHost = InetAddress.getLocalHost();

            // 로컬 컴퓨터 이름과 주소 출력
            System.out.println("Local Host Name: " + localHost.getHostName());
            System.out.println("Local Host Address: " + localHost.getHostAddress());

            // 루프백 주소 저장
            InetAddress loopbackAddress = InetAddress.getLoopbackAddress();

            // 루프백 주소 출력
            System.out.println("Loopback Address: " + loopbackAddress.getHostAddress());
        } catch (UnknownHostException e) {
            // 예외 처리
            e.printStackTrace();
        }
    }
}
