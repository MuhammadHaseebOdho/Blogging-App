package com.blog.repositories;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> getPostsByCategory(Category category);

    List<Post> getPostsByUser(User user);


}
