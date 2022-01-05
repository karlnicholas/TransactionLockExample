package com.github.karlnicholas.example.transactionlock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import javax.persistence.LockModeType;
import java.util.Optional;
import java.util.UUID;


public interface ArticleRepository extends JpaRepository<Article, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Article> findById(@NonNull UUID id);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select a from Article a where a.id = :id")
    Article findArticleForRead(@Param("id") UUID id);
}