package org.zerock.w1.todo.service;

import org.zerock.w1.todo.dto.TodoDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// enum 타입을 만들 때는 class 키워드가 아니라 enum 키워드로 생성한다.
// Enum클래스명.상수 이름 => 객체 생성 방법
public enum TodoService {
    INSTANCE;   // TodoService 객체 생성하기 위한 키워드

    public void register(TodoDTO todoDTO) {
        System.out.println("DEBUG.............." + todoDTO);
    }

    // TodoDTO를 담고 있는 List 객체를 반환할 수 있는 메서드 getList()
    public List<TodoDTO> getList() {

        // 0부터 9까지(range(0, 10)의 정수를 순차적으로 생성하는 스트림 생성 IntStream
        // 이 스트림을 사용하여 TodoDTO 객체들의 리스트 생성을 준비
        List<TodoDTO> todoDTOS = IntStream.range(0, 10)
                // 스트림의 각 정수 i를 객체로 변환하는 mapToObj 메서드 사용
                // mapToObj : 스트림의 요소를 다른 타입의 객체로 매핑(변환)하는 작업을 수행
                // i = 0~9까지 정수
                // i -> {....} : 람다 표현식
                // i를 입력 받아 TodoDTO 객체를 반환할 수 있도록 함.
                .mapToObj(i -> {
                    // 새로운 TodoDTO() 객체 생성
                    TodoDTO dto = new TodoDTO();
                    // i를 long 타입으로 변환하여 TodoDTO 객체의 tno 멤버 변수에 설정
                    dto.setTno((long) i);
                    dto.setTitle("Todo.." + i);
                    // LocalDate.now() = 현재 날짜를 알아낼 수 있는 정적 메서드
                    dto.setDueDate(LocalDate.now());

                    return dto; // 각 필드 값이 설정된 dto 객체(TodoDTO)를 반환
                }).collect(Collectors.toList());
                // TodoDTO 객체들을 리스트로 수집
                // Collectors.toList() = 스트림의 모든 요소들을 리스트로 수집
                // 각 요소가 리스트의 요소로 변환되고, 최종적으로 만들어진 리스트를 반환한다.
        
        // 생성된 TodoDTO 객체들을 담고 있는 List 객체를 반환
        return todoDTOS;

        }
    // Controller에서 tno를 전달받아 해당하는 tno를 조회할 수 있도록 하는 메서드
    public TodoDTO get(Long tno){
        // 현재는 DB가 연결되어 있지 않기 때문에 tno에 해당하는 새로운 데이터 객체를 만들어 반환한다.
        TodoDTO dto = new TodoDTO();
        dto.setTno(tno);
        dto.setTitle("Sample Todo");
        dto.setDueDate(LocalDate.now());
        dto.setFinished(true);

        return dto;
    }
}
