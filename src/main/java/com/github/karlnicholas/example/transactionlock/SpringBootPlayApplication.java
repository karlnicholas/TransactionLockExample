package com.github.karlnicholas.example.transactionlock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@SpringBootApplication
@Configuration
public class SpringBootPlayApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPlayApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //creating and persisting an Article
        articleRepository.save(article());
    }
    @Autowired
    ArticleRepository articleRepository;
    @Bean
    public Article article() {
        return new Article(UUID.randomUUID(), "test article", 0);
    }

}
