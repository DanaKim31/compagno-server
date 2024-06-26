package com.project.compagnoserver.repo.user;

import com.project.compagnoserver.domain.UserQnaBoard.UserQnaQuestionBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MyUserQnaDAO extends JpaRepository<UserQnaQuestionBoard, Integer>, QuerydslPredicateExecutor<UserQnaQuestionBoard> {
}
