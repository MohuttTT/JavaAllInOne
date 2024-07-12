package org.zerock.springex.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.zerock.springex.mapper")
public class MyBatisMapperConfig {
}
