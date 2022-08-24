package com.doan.project.web.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doan.project.web.project.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
}
