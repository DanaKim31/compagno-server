package com.project.compagnoserver.repo.ProductBoard;

import com.project.compagnoserver.domain.ProductBoard.ProductBoardRecommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProductBoardRecommendDAO extends JpaRepository<ProductBoardRecommend, Integer> {
}
