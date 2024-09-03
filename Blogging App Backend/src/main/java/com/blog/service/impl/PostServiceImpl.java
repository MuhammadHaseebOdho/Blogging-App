package com.blog.service.impl;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.mapper.PostMapper;
import com.blog.payload.PostDto;
import com.blog.repositories.CategoryRepository;
import com.blog.repositories.PostRepository;
import com.blog.repositories.UserRepository;
import com.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostMapper postMapper;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException(userId, "Requested User with Id:" + userId + " can not be found."));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(categoryId, "Requested Category with Id:" + categoryId + " can not be found."));
        System.out.println(new Date());
        postDto.setCreatedDate(new Date());
        postDto.setImageName("default.png");
        Post post = postMapper.postDtoToPost(postDto);
        post.setCategory(category);
        post.setUser(user);
        return postMapper.postToPostDto(postRepository.save(post));
    }

    @Override
    public PostDto getPost(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(postId, "Requested Post with Id:" + postId + " can not be found."));
        return postMapper.postToPostDto(post);
    }

    @Override
    public List<PostDto> getPosts() {
        List<Post> posts = postRepository.findAll();
        return postMapper.postListToPostDtoList(posts);
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException(userId, "Requested User with Id:" + userId + " can not be found.")
        );
        List<Post> postsByUser = postRepository.getPostsByUser(user);
        return postMapper.postListToPostDtoList(postsByUser);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException(categoryId, "Requested Category with Id:" + categoryId + " can not be found.")
        );
        List<Post> postsByCategory = postRepository.getPostsByCategory(category);
        return postMapper.postListToPostDtoList(postsByCategory);
    }

    @Override
    public PostDto updatePost(PostDto postDto,Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(postId, "Requested Post with Id:" + postId + " can not be found.")
        );

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        //post.setImageName(postDto.getImageName());
        //post.setCreatedDate(postDto.getCreatedDate());
        return postMapper.postToPostDto(postRepository.save(post));
    }

    @Override
    public void deletePost(Integer postId) {
        postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(postId, "Requested Post with Id:" + postId + " can not be found.")
        );
        postRepository.deleteById(postId);
    }
}
