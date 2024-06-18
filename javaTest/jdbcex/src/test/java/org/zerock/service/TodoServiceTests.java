package org.zerock.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.service.TodoService;

import java.time.LocalDate;

@Log4j2
class TodoServiceTests {
    private TodoService todoService;

    @BeforeEach
    public void ready() {
        todoService = TodoService.INSTANCE;
    }

    @Test
    public void testRegister() throws Exception {
        // 테스트 하기 위한 dto 객체 생성(실제로는 클라이언트 측에서 전송된 데이터)
        TodoDTO todoDTO = TodoDTO.builder()
                .title("JDBC Test Title")
                .dueDate(LocalDate.now())
                .build();

        log.info("---------------------------------------");
        log.info(todoDTO);

        todoService.register(todoDTO);
    }
}
