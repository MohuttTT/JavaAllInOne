package org.zerock.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectTest {
    @Test
    public void test1() {
        int v1 = 10;
        int v2 = 10;

        Assertions.assertEquals(v1, v2);
    }

    @Test
    public void testConnection() throws Exception{
        // JDBC 드라이버 클래스를 로드한다.
        // Class.forName : 문자열로 주어진 클래스이름을 찾아 메모리에 로드(객체 생성)
        Class.forName("com.mysql.jdbc.Driver");// MySQL JDBC 드라이버 클래스
        /* 해당 호출로 인해 드라이버 클래스와 정적 초기화 블록이 실행
        * 드라이버에 DriverManager 등록
        * */

        // 실제 데이터 베이스에 연결을 설정 : DriverManager.getConnection()
        //getConnection() : 연결 할때 데이터베이스URL 사용자 이름 비밀번호를 매개변수로 받아 데이터 베이스 연결
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/webdb",
                "root",
                "1234"
        );
        // 연결 객체가 null 이 아님을 확인 = 해당 DB와 잘 연결 되었는지 확인(검증)
        Assertions.assertNotNull(connection);
        // 데이터 베이스 연결 종료 : 데이터 베이스 연결은 제한된 리소스 이므로 사용이 끝난 후 반드시 닫아야 한다.
        connection.close();
    }

    @Test
    public void testHikariCP() throws Exception{
        // 데이터 커넥션 풀을 사용하여 MySQL 데이터베이스에 연결

        // HikariConfig -> HikariCP 설정을 담고 있는 객체
        HikariConfig config = new HikariConfig();

        // JDBC URL , 데이터 베이스에 연결할 사용자 이름, 접속 비밀번호 설정
        config.setJdbcUrl("jdbc:mysql://localhost:3306/webdb");
        config.setUsername("root");
        config.setPassword("1234");

        // addDataSourceProperty() : 데이터 소스에 특정 속성 추가

        // cachePreStmts : 캐시 여부 설정
        config.addDataSourceProperty("cachePrepStmts", "true");
        // PrepStmtCacheSize : 캐시 크기 설정(최대 250개 캐시 할 수 있도록 설정)
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        // prepStmtCacheSqlLimit : SQL 문자열 길이 제한(2048자의 SQL 문자열을 캐시할 수 있도록 설정)
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        // HikariDataSource: 데이터 베이스 커넥션 풀을 관리하는 객체
        // HikariDataSource => 설정된 HikariConfig를 사용하여 실제 데이터베이스 연결 관리를 함
        HikariDataSource ds = new HikariDataSource(config);
        Connection connection = ds.getConnection();
        System.out.println(connection);

        connection.close();
    }

}
