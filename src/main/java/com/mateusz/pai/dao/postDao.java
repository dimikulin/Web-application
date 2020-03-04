package com.mateusz.pai.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mateusz.pai.entity.Post;

public interface postDao extends CrudRepository<Post, Integer> { 
 
 
    @Override     
    public List<Post> findAll();
}
