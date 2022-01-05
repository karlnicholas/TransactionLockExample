package com.github.karlnicholas.example.transactionlock;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(indexes = {@Index(unique = true, columnList = "rowNum"),
})
public class Article {
    @Id
    private UUID id;
    private String content;
    private Integer rowNum;

    public Article() {
    }

    public Article(UUID id, String content, Integer rowNum) {
        this.id = id;
        this.content = content;
        this.rowNum = rowNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", rowNum='" + rowNum + '\'' +
                '}';
    }

}