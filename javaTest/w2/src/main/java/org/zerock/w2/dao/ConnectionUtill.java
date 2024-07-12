package org.zerock.w2.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public enum ConnectionUtill {
    INSTANCE;

    private HikariDataSource ds;

    ConnectionUtill() {
        // HikariConfig -> HikariCP 설정을 담고 있는 객체
        HikariConfig config = new HikariConfig();

        // JDBC URL , 데이터 베이스에 연결할 사용자 이름, 접속 비밀번호 설정
        config.setDriverClassName("com.mysql.jdbc.Driver");
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
       ds = new HikariDataSource(config);
    }
    public Connection getConnection() throws Exception {
        return ds.getConnection();
    }

}
