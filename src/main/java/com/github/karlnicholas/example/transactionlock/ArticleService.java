package com.github.karlnicholas.example.transactionlock;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ArticleService {
    private final ArticleRepository repo;
    private final Article savedArticle;

    public ArticleService(ArticleRepository repo, Article savedArticle) {
        this.repo = repo;
        this.savedArticle = savedArticle;
    }

    public Optional<Article> update() {
        System.out.println(" -- user 1 writing Article entity --");
        long start = System.currentTimeMillis();
        try {
            Article article = repo.findById(savedArticle.getId()).map(a -> {
                System.out.println("user 1 got the lock, block time was: " + (System.currentTimeMillis() - start));
                a.setContent("updated content by user 1.");
                a.setRowNum(a.getRowNum() + 1);
                return repo.save(a);
            }).orElseThrow();
            //delay for 2 secs
            ThreadSleep(3000);
            System.out.println("User 1 updated article: " + article);
            return Optional.of(article);
        } catch (Exception e) {
            System.err.println("User 1 got exception while acquiring the database lock:\n " + e);
            return Optional.empty();
        }
    }

    private void ThreadSleep(long timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Optional<Article> read() {
        return repo.findById(savedArticle.getId());
    }
}
