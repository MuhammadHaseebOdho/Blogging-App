package com.blog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "posts")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 5000)
    private String title;
    private String content;
    private String imageName;
    private Date createdDate;
    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;


}
