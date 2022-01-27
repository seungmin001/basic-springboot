package com.example.smspring.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    // 보통 MyBatis 등에서 Dao라고 불리는 DB Layer 접근자
    // extends JpaRepository<Entity class, PK type>  : 기본적인 CRUD 메소드 자동 생성
    // Entity class와 함께 위치해야함

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
