package com.example.tboard.domain.article.article;


import com.example.tboard.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 번호

    private String title; // 제목

    private String body; // 내용

    private int hit; // 조회수

    public Article(int articleId, String title, String body, int hit, String regDate) {
        super();
    }

    public void increaseHit() {
        this.hit++;
    }

}
