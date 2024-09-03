package com.blog.service;

import com.blog.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    PostDto getPost(Integer postId);

    PostDto updatePost(PostDto postDto,Integer postId);

    void deletePost(Integer postId);

    List<PostDto> getPosts();

    List<PostDto> getPostByUser(Integer userId );

    List<PostDto> getPostByCategory(Integer categoryId);

}
