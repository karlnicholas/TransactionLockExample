package com.github.karlnicholas.example.transactionlock;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class AppController {

    private final ArticleService articleService;

    public AppController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/api/write1")
    public ResponseEntity<Article> write1() {
        return ResponseEntity.ok(articleService.update().orElseThrow());
    }

    @GetMapping("/api/write2")
    public ResponseEntity<Article> write2() {
        return ResponseEntity.ok(articleService.update().orElseThrow());
    }

    @GetMapping("/api/read")
    public ResponseEntity<Article> read() {
        return ResponseEntity.ok(articleService.read().orElseThrow());
    }
}
