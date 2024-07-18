package org.zerock.b01.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity // -- JPA 엔티티임을 나타냄 (데이터베이스 테이블과 매핑)
@Getter // -- 모든 필드에 대한 Getter 메서드(getXXX()) 생성
@Builder    // -- 빌더 패턴을 사용하여 객체 생성할 수 있게 함
@AllArgsConstructor // -- 모든 필드를 인자로 받는 생성자 자동 생성
@NoArgsConstructor  // -- 파라미터가 없는 기본 생성자 자동 생성
@ToString(exclude = "board")    // -- toString() 생성 시, 순환 참조를 피하기 위해 board 필드 제외
public class BoardImage implements Comparable<BoardImage>{

    @Id // -- 엔티티 고유 식별자 설정 어노테이션 (데이터베이스와 엔티티의 기본 키)
    private String uuid;    // UUID를 사용하여 각 이미지를 유일하게 식별

    private String fileName;    // 원본 파일 이름 (사용자가 업로드한 파일 이름)

    private int ord;    // 이미지의 순서
    // 동일한 게시글 내에 여러 이미지가 있을 경우, 이미지 순서 지정하여 정렬
    // 정렬의 경우 compareTo()를 사용하여 이미지 순서 비교

    @ManyToOne  // 해당 클래스가 "다"(Many)에 속함. (= Board 클래스가 "하나"(One) 쪽에 있다는 것을 나타냄)
    private Board board;    // Board 클래스와의 관계를 설정하여 JPA가 Join을 수행함

    @Override
    public int compareTo(BoardImage other) {
        // ord 필드 기준으로 이미지 비교
        return this.ord - other.ord;
    }

    public void changeBoard(Board board) {
        this.board = board;
    }
}
