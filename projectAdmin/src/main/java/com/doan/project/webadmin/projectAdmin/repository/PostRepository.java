package com.doan.project.webadmin.projectAdmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doan.project.webadmin.projectAdmin.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
}
