package me.ahngeunsu.springbootdeveloper.domain;
// 01 단계 진행 중입니다.

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate // 엔티티가 생성될 때 생성 시간 저장
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate   // 엔티티가 수정될 때 수정 시간 저장
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /*
        @CreatedDate 애너테이션을 사용하면 엔티티가 생성될 때 생성 시간을 created_at 컬럼에 저장
        @LastModifiedDate 애너테이션을 사용하면 엔티티가 수정될 때 마지막으로 수정된 시간을 updated_at 컬럼에 저장

        또한 @Entity위에 엔티티의 생성 및 수정 시간을 자동으로 감시하고 기록하기 위해
        @EntityListeners(AuditingEntityListener.class) 애너테이션을 추가합니다.

            02 단계 - 엔티티를 생성하면 생성 시간과 수정 시간이 자동으로 저장됩니다. 하지만 스프링 부트 서버를 실행할 때마다
                SQL문으로 데이터를 넣는 data.sql 파일은 created_at과 updated_at을 바꾸지 않습니다. 최초 생성 파일에도
                이 값을 수정하도록 data.sql 파일을 수정해 실행할 때마다 created_at, updated_at이 바뀌도록 해봅시다.

                data.sql로 이동하여 sql문을 수정하고,
                SpringBootDeveloperApplication 파일을 열어 엔티티의 created_at, updated_at을 자동으로 추가하기 위한
                애너테이션을 추가합니다.

                SpringBootDeveloperApplication으로 이동
     */


    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
