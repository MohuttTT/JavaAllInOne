package org.zerock.b01.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roleSet")
public class Member extends BaseEntity {

    @Id
    private String mid;     // 회원 ID

    private String mpw;     // Password
    private String email;   // 이메일
    private boolean del;    // 탈퇴 여부

    private boolean social; // 소셜 회원 확인

    /* @ElementCollection
        해당 엔티티 클래스 내의 컬렉션 필드가 데이터베이스에 별도 테이블로 매핑되어 저장
    */
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void changePassword(String mpw) {
        this.mpw = mpw;
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public void changeDel(boolean del) {    // 탈퇴 여부 설정
        this.del = del;
    }

    public void addRole(MemberRole memberRole) {    // 권한 추가
        this.roleSet.add(memberRole);
    }

    public void clearRoles() {  // 권한 전체 삭제
        this.roleSet.clear();
    }

    public void changeSocial(boolean social) {  // 소셜 회원인지 아닌지 여부 확인
        this.social = social;
    }
}
