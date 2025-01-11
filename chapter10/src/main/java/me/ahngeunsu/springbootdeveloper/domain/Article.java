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

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "author", nullable = false)
    private String author;

    // 위에 필드 추가하고, 밑에 생성자 수정하세요.
    @Builder
    public Article(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }
    /*
        위에거 수정한 뒤에

        02 단계 - 기존 글을 작성하는 API에서 작성자를 추가해 저장하기 위해 DTO 패키지의 AddArticleRequest 파일을 열고,
            toEntity() 메서드를 수정하세요.
     */

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
