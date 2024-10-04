package com.blog.service.impl;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.mapper.PostMapper;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
import com.blog.repositories.CategoryRepository;
import com.blog.repositories.PostRepository;
import com.blog.repositories.UserRepository;
import com.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    private final String IMAGE_PATH="";

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId, MultipartFile images) {
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
    public PostResponse getPosts(int pageNumber,int pageSize,String sortBy,String sortOrder) {
        Sort sort=sortOrder.equalsIgnoreCase("DESC")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        Pageable pageable= PageRequest.of(pageNumber,pageSize, sort);
        Page<Post> postPage = postRepository.findAll(pageable);
        List<Post> posts = postPage.getContent();
        PostResponse postResponse=new PostResponse();
        postResponse.setPosts( postMapper.postListToPostDtoList(posts));
        postResponse.setTotalPosts(postPage.getTotalElements());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setLastPage(postPage.isLast());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setPageNumber(postPage.getNumber());
        return postResponse;
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

    @Override
    public List<PostDto> getPostByTitle(String title) {
        List<Post> postsByTitle = postRepository.getPostsByTitleContaining(title);
        return postMapper.postListToPostDtoList(postsByTitle);
    }
}
