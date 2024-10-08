package com.blog.controller;

import com.blog.exception.ApiResponse;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
import com.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping(path = "/user/{userId}/category/{categoryId}/post",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostDto> createPost(@PathVariable Integer categoryId,
                                              @PathVariable Integer userId,
                                              @ModelAttribute PostDto postDto,
                                              @RequestParam("images")MultipartFile images
                                              ) throws IOException {
        PostDto post = postService.createPost(postDto, userId, categoryId,images);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Integer postId){
        PostDto post = postService.getPost(postId);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @GetMapping("/post")
    public ResponseEntity<PostResponse> getPosts(
        @RequestParam (value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
        @RequestParam (value = "pageSize",defaultValue = "10",required = false) Integer pageSize,
        @RequestParam (value = "sortBy",defaultValue = "id",required = false) String sortBy,
        @RequestParam (value = "sortOrder",defaultValue = "DESC",required = false) String sortOrder
        ){
        PostResponse posts = postService.getPosts(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }
    @GetMapping("/user/{userId}/post")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
        List<PostDto> posts = postService.getPostByUser(userId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}/post")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
        List<PostDto> posts = postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post Deleted",true),HttpStatus.OK);
    }
    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto updatedPost = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    @GetMapping("/post/search/{search}")
    public ResponseEntity<List<PostDto>> postSearch(@PathVariable String search){
        List<PostDto> posts = postService.getPostByTitle(search);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

}

