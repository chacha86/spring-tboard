package com.example.tboard.domain.article;

import com.example.tboard.base.CommonUtil;
import com.example.tboard.domain.member.Member;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
    public String detail(@PathVariable("articleId") int articleId, Model model, HttpServletRequest request) {

        Article article = articleRepository.findArticleById(articleId);

        if (article == null) {
            return "없는 게시물입니다.";
        }

        article.increaseHit();
        String username = getUsernameByCookie(request);

        model.addAttribute("loginedUser", username);
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
    public String list(Model model, HttpServletRequest request) {

        ArrayList<Article> articleList = articleRepository.findAll();
        model.addAttribute("articleList", articleList);

        return "list";
    }

    // 실제 데이터 저장 처리 부분
    @PostMapping("/add")
    public String add(@RequestParam("title") String title,
                      @RequestParam("body") String body,
                      Model model, HttpSession session) {

        Member member = (Member)session.getAttribute("loginedUser");

        if(member == null) {
            throw new RuntimeException("로그인 후 이용해주세요.");
        }

        articleRepository.saveArticle(title, body);

        // 문제 원인 : add 요청의 결과 화면을 list로 보여주고 있다.
        // 문제 해결 : add url을 list로 바꾸면 된다.
        // controller에서 주소를 바꾸는 법 : redirect
        return "redirect:/list"; // 브라우저의 주소가 /list로 바뀜

    }

    // 입력 화면 보여주기
    @GetMapping("/add")
    public String form(String loginId, String loginPw, HttpServletRequest request, Model model, HttpSession session) {
        return "form";
    }

    private String getUsernameByCookie(HttpServletRequest request) {

        if(request.getCookies() == null) {
            return null;
        }

        Cookie[] cookies = request.getCookies();
        // username이라는 이름을 가진 쿠키 찾기
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("username")) {
                return cookie.getValue();
            }
        }

        return null;
    }
}
