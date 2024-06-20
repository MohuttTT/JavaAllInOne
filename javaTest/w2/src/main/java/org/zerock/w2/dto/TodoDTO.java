package org.zerock.w2.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
// TodoDTO 클래스 선언
public class TodoDTO {
    private  Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
