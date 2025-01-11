package me.ahngeunsu.springbootdeveloper.dto;
// 서비스 메서드 코드 작성하기 01단계 중입니다.

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ahngeunsu.springbootdeveloper.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {

    private String title;
    private String content;


//    public Article toEntity() {
//        return Article.builder()
//                .title(title)
//                .content(content)
//                .build();
//    }
    // 원래 위의 상태였는데 String author 추가하고 나서
    public Article toEntity(String author) {
        return Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
    /*
        03 단계 - service 패키지의 BlogService 파일 열고 save() 메서드에서 유저 이름을 추가로 입력 받고
            toEntity의 argument로 전달 받은 유저 이름을 반환하도록 코드를 수정
     */
}
