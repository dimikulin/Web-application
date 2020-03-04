/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mateusz.pai.dao;

import com.mateusz.pai.entity.User; 
import java.util.List; 
import org.springframework.data.repository.CrudRepository; 
 
public interface userDao extends CrudRepository<User, Integer> { 
 
    public User findByLogin(String login); 
    public User findByEmail(String email); 
 
    @Override     
    public List< User> findAll(); 
}