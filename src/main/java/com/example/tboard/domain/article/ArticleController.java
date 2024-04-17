package com.example.tboard.domain.article;

import com.example.tboard.base.CommonUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleController { // Model + Controller

    CommonUtil commonUtil = new CommonUtil();

    Repository articleRepository = new ArticleMySQLRepository();

    @RequestMapping("/search")
    public String search(@RequestParam(value="keyword", defaultValue = "") String keyword,
                                        Model model) {

        ArrayList<Article> searchedList = articleRepository.findArticleByKeyword(keyword);
        model.addAttribute("articleList", searchedList);

        return "list";
    }

    @RequestMapping("/detail/{articleId}")
    public String detail(@PathVariable("articleId") int articleId, Model model) {

        Article article = articleRepository.findArticleById(articleId);

        if (article == null) {
            return "없는 게시물입니다.";
        }

        article.increaseHit();

        model.addAttribute("article", article);
        return "detail";
    }

    @RequestMapping("/delete/{articleId}")
    public String delete(@PathVariable("articleId") int articleId) {

        Article article = articleRepository.findArticleById(articleId);

        if (article == null) {
            return "없는 게시물입니다.";
        }

        articleRepository.deleteArticle(article);

        return "redirect:/list";
    }

    @GetMapping("/update/{articleId}")
    public String updateForm(@PathVariable("articleId") int articleId, Model model) {

        Article article = articleRepository.findArticleById(articleId);

        if (article == null) {
            throw new RuntimeException("없는 게시물입니다.");
        }

        model.addAttribute("article", article);
        return "updateForm";
    }
    @PostMapping("/update/{articleId}")
    public String update(@PathVariable("articleId") int articleId,
                       @RequestParam("title") String title,
                       @RequestParam("body") String body
                       ) {

        Article article = articleRepository.findArticleById(articleId);

        if (article == null) {
            throw new RuntimeException("없는 게시물입니다.");
        }

        articleRepository.updateArticle(article, title, body);

        return "redirect:/detail/%d".formatted(articleId);
    }

    @RequestMapping("/list")
    public String list(Model model) {

        ArrayList<Article> articleList = articleRepository.findAll();
        model.addAttribute("articleList", articleList);
        return "list";
    }

    // 실제 데이터 저장 처리 부분
    @PostMapping("/add")
    public String add(@RequestParam("title") String title,
                      @RequestParam("body") String body,
                      Model model) {

        articleRepository.saveArticle(title, body);

        // 문제 원인 : add 요청의 결과 화면을 list로 보여주고 있다.
        // 문제 해결 : add url을 list로 바꾸면 된다.
        // controller에서 주소를 바꾸는 법 : redirect
        return "redirect:/list"; // 브라우저의 주소가 /list로 바뀜

    }

    // 입력 화면 보여주기
    @GetMapping("/add")
    public String form(String loginId, String loginPw, HttpServletRequest request, Model model) {

        // 브라우저에 있는 쿠키 정보 가져오기
        Cookie[] cookies = request.getCookies();
        Cookie targetCookie = null;

        // username이라는 이름을 가진 쿠키 찾기
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("username")) {
                targetCookie = cookie;
            }
        }

        // username이라는 이름을 가진 쿠키를 이용해 로그인 유저 정보 확인
        model.addAttribute("loginedUser", targetCookie.getValue());

        return "form";
    }
}
