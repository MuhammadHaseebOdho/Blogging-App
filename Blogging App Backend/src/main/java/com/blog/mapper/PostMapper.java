package com.blog.mapper;


import com.blog.entity.Post;
import com.blog.payload.PostDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostMapper {

    @Autowired
    ModelMapper modelMapper;

    public Post postDtoToPost(PostDto postDto){
        return modelMapper.map(postDto,Post.class);
    }
    public PostDto postToPostDto(Post post){
        return modelMapper.map(post,PostDto.class);
    }

    public List<Post> postDtoListToPostList(List<PostDto> postDtoList){
        return postDtoList.stream().map(
                postDto -> modelMapper.map(postDto, Post.class)
        ).collect(Collectors.toList());
    }

    public List<PostDto> postListToPostDtoList(List<Post> postList){
        return postList.stream().map(
                post -> modelMapper.map(post, PostDto.class)
        ).collect(Collectors.toList());
    }

}
