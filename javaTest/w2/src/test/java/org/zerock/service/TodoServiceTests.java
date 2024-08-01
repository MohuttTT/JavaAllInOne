package org.zerock.service;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.zerock.w2.dto.TodoDTO;
import org.zerock.w2.service.TodoService;

import java.time.LocalDate;

@Nested
@Log4j2
public class TodoServiceTests {

    private TodoService todoService;

    @BeforeEach
    public void ready() {
        todoService = TodoService.INSTANCE;
    }
    // TodoService 동작 테스트 확인하기

    // 등록
    @Test
    public void testRegister() throws Exception {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("JDBC Test Title")
                .dueDate(LocalDate.now())
                .build();

        todoService.register(todoDTO);
    }
    // 수정 테스트
    @Test
    public void testModify() throws Exception {
        TodoDTO todoDTO = TodoDTO.builder()
                .tno(1L)
                .title("Update...101")
                .dueDate(LocalDate.now())
                .build();

        todoService.modify(todoDTO);
    }


    // 조회
    @Test
    public void testReadAll() throws Exception {

        Long tno = 1L;

        TodoDTO todoDTO = (TodoDTO) todoService.get(tno);
        log.info(todoDTO);

        String title = todoDTO.getTitle();
        log.info(title);
    }
    // 삭제
    @Test
    public void testRemoveAll() throws Exception {
        Long tno = 2L;

        todoService.remove(tno);
    }
}

    @Test
    public void testRegister() throws Exception {

        TodoDTO todoDTO = TodoDTO.builder().title("JDBC Test Title").dueDate(LocalDate.now()).build();


        log.info("--------------------------------------------------------");
        log.info(todoDTO);
        todoService.register(todoDTO);
    }
}