package com.soyoyoo.jpa.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soyoyoo.jpa.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.soyoyoo.jpa.model.QProduct.product;
import static com.soyoyoo.jpa.repository.ProductQueryDslRepository.SearchType.DESCRIPTION;
import static com.soyoyoo.jpa.repository.ProductQueryDslRepository.SearchType.TITLE;

@Repository
@RequiredArgsConstructor
public class ProductQueryDslRepositoryImpl implements ProductQueryDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Product> queryByKeyword(String keyword, SearchType searchType, long offset, long limit) {
        var query = jpaQueryFactory.selectFrom(product);
        if (keyword != null && searchType != null) {
            if (searchType == TITLE) {
                query.where(product.title.contains(keyword));
            } else if (searchType == DESCRIPTION) {
                query.where(product.description.contains(keyword));
            } else {
                query.where(product.title.contains(keyword).or(product.description.contains(keyword)));
            }
        }
        return query.orderBy(product.id.desc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }
}