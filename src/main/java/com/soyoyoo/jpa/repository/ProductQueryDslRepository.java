package com.soyoyoo.jpa.repository;

import com.soyoyoo.jpa.model.Product;

import java.util.List;

public interface ProductQueryDslRepository {
    enum SearchType {
        TITLE,
        DESCRIPTION,
        BOTH
    }
    List<Product> queryByKeyword(String keyword, SearchType searchType, long offset, long limit);
}