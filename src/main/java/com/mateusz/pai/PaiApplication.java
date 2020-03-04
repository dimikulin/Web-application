package com.mateusz.pai;

import com.mateusz.pai.entity.*;
import com.mateusz.pai.dao.*;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PaiApplication {
@Autowired     
private userDao dao;
@Autowired     
private postDao daopost;   
@Autowired     
private PasswordEncoder passwordEncoder; 

	public static void main(String[] args) {
		SpringApplication.run(PaiApplication.class, args);
	}
 @PostConstruct     
 public void init() {         
     dao.save(new User("admin", "admin", "admin",       
             passwordEncoder.encode("passwd"), "admin@op.pl"));
 dao.save(new User("admin1", "admin1", "admin1",       
             passwordEncoder.encode("passwd"), "admin1@op.pl")); 
  dao.save(new User("admin2", "admin2", "admin2",       
             passwordEncoder.encode("passwd"), "admin2@op.pl")); 
   dao.save(new User("admin3", "admin3", "admin3",       
             passwordEncoder.encode("passwd"), "admin1@op.pl")); 
    dao.save(new User("admin4", "admin4", "admin4",       
             passwordEncoder.encode("passwd"), "admin4@op.pl")); 
    daopost.save(new Post(20,"Java","Post wstawiony przez aplikacje"));

 }
}
