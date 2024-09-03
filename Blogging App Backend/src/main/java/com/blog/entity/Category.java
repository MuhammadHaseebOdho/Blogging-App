package com.blog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "Category title can not be empty")
    @Size(min =3,message = "Title must be greater than 3 characters")
    @Column(unique = true)
    private String categoryTitle;
    @NotEmpty(message = "Category description can not be empty")
    @Size(min =10,message = "Description must be greater than 10 characters")
    private String categoryDescription;

    @OneToMany(mappedBy = "category" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<Post> posts=new ArrayList<>();

}
