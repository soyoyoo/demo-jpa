package com.soyoyoo.jpa.repository;

import com.soyoyoo.jpa.model.Article;
import com.soyoyoo.jpa.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    public int deleteByMember(Member member);
}