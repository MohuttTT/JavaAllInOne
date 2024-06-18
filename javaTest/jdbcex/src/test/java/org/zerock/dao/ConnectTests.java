package org.zerock.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectTests {
    @Test
    public void test1() {
        int v1 = 10;
        int v2 = 10;
        Assertions.assertEquals(v1,v2);
    }

    @Test
    public void testConnection() throws Exception {
        // JDBC 드라이버 클래스를 로드한다.
        // Class.forName : 문자열로 주어진 클래스 이름을 찾아 메모리에 로드 (객체 생성)
        Class.forName("com.mysql.jdbc.Driver");
        /* 해당 호출로 인해 드라이버 클래스의 정적 초기화 블록이 실행
        * 드라이버 DriverManager에 등록
        */

        // 실제 데이터베이스에 연결을 설정 : DriverManager.getConnection()
        // getConnection() : 연결할 데이터베이스 URL, 사용자 이름, 비밀번호를 매개변수로 받아 데이터베이스 연결
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/webdb",
                "root",
                "1234"
        );

        // 연결 객체가 null이 아님을 확인 = 해당 DB와 잘 연결되었는지 확인(검증)
        Assertions.assertNotNull(connection);

        // 데이터베이스 연결 종료 : 데이터베이스 연결은 제한된 리소스이므로 사용이 끝난 후 반드시 닫아야 한다.
        connection.close();
    }

    @Test
    public void testHikariCP() throws Exception {
        // 데이터 커넥션 풀을 사용하여 MySQL 데이터베이스에 연결

        // HikariConfig -> HikariCP 설저을 담고 있는 객체
        HikariConfig config = new HikariConfig();

        // JDBC URL, 데이터베이스에 연결할 사용자 이름, 접속 비밀번호 설정
        config.setJdbcUrl("jdbc:mysql://localhost:3306/webdb");
        config.setUsername("root");
        config.setPassword("1234");

        // addDataSourceProperty() : 데이터 소스에 특정 속성 추가
        /*
            Prepared Statements를 캐시하면, 동일한 쿼리를 여러 번 실행할 때 매번 SQL 쿼리를 컴파일하지 않아도 된다. => 데이터베이스 서버의 부하를 줄이고 응답 시간을 단축한다. => 성능 향상
        */
        /*
            Cache(캐시) : 데이터를 일시적으로 저장하는 메모리 영역
            => 자주 접근하는 데이터나 계산 결과를 저장하여 빠르게 접근할 수 있게 해 준다. => 성능 향상

            [ Cache 작동 메커니즘 ]
            1. 저장 : 처음 데이터를 요청할 때, 데이터를 캐시에 저장
            2. 검색 : 동일한 데이터 요청이 다시 발생하면, 캐시에 데이터를 가져온다.
                이를 통해 데이터베이스나 원본 저장소에 다시 접근할 필요가 없이 빠르게 데이터를 제공
            3. 갱신 : 데이터가 변경되면, 캐시의 값 역시 갱신된다. 캐시가 항상 최신 상태를 유지한다.
        */
        // cachePrepStmts : 캐시 여부 설정
        config.addDataSourceProperty("cachePrepStmts", "true");
        // prepStmtCacheSize : 캐시 크기 설정 (최대 250개 캐시할 수 있도록 설정)
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        // prepStmtCacheSqlLimit : SQL 문자열 길이 제한 (2048자의 SQL 문자열을 캐시할 수 있도록 설정)
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        // HikariDataSource : 데이터베이스 커넥션 풀을 관리하는 객체
        // HikariDataSource => 설정된 HikariConfig를 사용하여 실제 데이터베이스 연결 관리를 함
        /*
            DataSource : 에플리케이션이 데이터베이스에 접근하기 위해 사용하는 설정 메커니즘
            => 데이터베이스 연결 정보를 포함, 데이터베이스 연결 생성하고 관리하는 데 사용
            => 현재 DataSource 라이브러리인 HikariCP를 이용하여 Connection Pool 사용 중
        */
        HikariDataSource ds = new HikariDataSource(config);

        // getConnection() : 커넥션 풀에서 사용 가능한 데이터베이스 연결을 가져옴
        Connection connection = ds.getConnection();

        System.out.println(connection);

        connection.close();
    }
}
