package org.zerock.b01.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.HashSet;
import java.util.Set;

// 해당 클래스는 게시글을 나타내며, 여러 개의 이미지(BoardImage)를 포함할 수 있음
/*
    Board 엔티티는 여러 개의 BoardImage 엔티티를 가질 수 있음 -> 하나의 게시글에 여러 이미지를 첨부할 수 있음을 의미
    BoardImage 엔티티는 하나의 Board 엔티티에 속함 -> 이미지가 특정 게시글에 종속됨을 의미
    mappedBy = "board"를 통해 BoardImage 엔티티의 board 필드가 외래 키를 관리함을 명시
    CascadeType.ALL을 사용하여 Board 엔티티의 변화가 BoardImage 엔티티에도 전파되도록 함
        ex) Board 엔티티가 삭제되면 관련된 모든 BoardImage 엔티티도 삭제
*/
/*
    OneToMany 관계
    - Board(게시글)와 BoardImage(이미지)의 관계를 생각해 보자
        하나의 Board(게시글)은 여러 개의 BoardImage(이미지)를 가질 수 있지만,
        하나의 BoardImage(이미지)는 하나의 Board(게시글)에만 속할 수 있음

    mappedBy - JPA에서 양방향 관계를 설정할 때 사용하는 속성
        해당 속성을 통해 관계의 주인(owner)이 누구인지 명확하게 지정
        주인은 데이터베이스에서 "외래 키(FK)"를 관리해야 하는 측을 뜻함

    Board와 BoardImage의 관계
        - BoardImage 엔티티가 외래 키를 가지고 있기 때문에 주인 측이 됨 -> BoardImage 테이블에 외래 키 컬럼이 생성
        - Board 엔티티는 종속된 측이 됨 -> Board는 여러 개의 BoardImage를 가질 수 있음

    실제 데이터베이스
        - BoardImage 테이블은 board_id 라는 외래 키 컬럼을 가짐
        - Board 테이블에는 외래 키 컬럼이 존재하지 않음 -- Board는 관계의 주인이 아니기 때문

    예시)
        - Board는 하나의 게시글, BoardImage는 게시글에 포함된 여러 이미지
        - 이미지는 항상 특정 게시글에 속해 있음. 게시글이 여러 이미지를 가질 수 있지만, 각 이미지는 무조건 특정 게시글에 속해야 함
        - mappedBy는 해당 이미지가 속한 게시글이 무엇인가? 를 나타낼 때 사용함
        즉, BoardImage가 외래 키로 Board를 참조하고 있음
*/

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "imageSet")
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bno;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;

    public void change(String t, String c) {
        this.title = t;
        this.content = c;
    }

    // @OneToMany (일대다 관계) 설정 -- Board(1), BoardImage(N)
    /*
        mappedBy : board 관계의 주인이 BoardImage 엔티티의 board 필드임을 지정
        cascade : {CascadeType.ALL}
            부모 엔티티(Board)의 모든 상태 변화가 자식 엔티티(BoardImage)에 전파
            예를 들어, 부모 엔티티를 저장하면 자식 엔티티도 함께 저장
        fetch : FetchType.LAZY
            지연 로딩 사용. Board 엔티티를 조회할 때 BoardImage 엔티티는 즉시 로드되지 X -> 실제로 접근할 때 로드
    */

    /*
    @BathSize : 일괄 로딩(Batching Loading) 전략을 설정하는 데 사용
        연관된 엔티티를 로드할 때 N+1 문제가 발생하는 데, 해당 부분을 해결할 때 유용
        -> 한 번에 가져올 엔티티 수를 설정하여 데이터베이스에 접근 횟수를 줄일 수 있기 때문

    N+1 문제
        만약 50개의 Board 엔티티를 조회해야 하고 각 Board 엔티티마다 평균적으로 3개의 BoardImage가 포함되어 있다면,
        기본 지연 로딩을 이용할 경우 50 + 50*3 = 200 번의 데이터베이스 쿼리가 필요함
        하지만, @BathSize(size = 20)을 사용하면 JPA는 20개의 Board 엔티티와 관련된 BoardImage 엔티티를 한 번에 로드
        즉, 데이터베이스 쿼리 횟수가 크게 줄어듬
   */
    @OneToMany(mappedBy = "board",  //BoardImage의 board 변수
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @Builder.Default
    @BatchSize(size = 20)
    private Set<BoardImage> imageSet = new HashSet<>();

    /* 이미지 관리 메서드 -- addImage, clearImage */

    // 새로운 이미지 추가
    public void addImage(String uuid, String fileName) {

        BoardImage boardImage = BoardImage.builder()
                .uuid(uuid)
                .fileName(fileName)
                .board(this)
                .ord(imageSet.size())
                .build();
        imageSet.add(boardImage);
    }

    // 모든 이미지 삭제
    public void clearImages() {

        imageSet.forEach(boardImage -> boardImage.changeBoard(null));

        this.imageSet.clear();
    }
}
