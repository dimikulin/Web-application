package com.mateusz.pai.controllers;

import com.mateusz.pai.dao.*; 
import com.mateusz.pai.entity.*; 
import java.security.Principal; 
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 


import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RequestParam;
 
@Controller 
public class PostController { 
 
      
    @Autowired     
    private userDao dao; 
    
    @Autowired     
    private postDao postdao; 
     
  //dodawanie postu do bazy danych
    @RequestMapping(value = "/sendpost", method = RequestMethod.POST)
    public String sendPost(HttpServletRequest request, Model m, Principal principal) { 
        String name = request.getParameter("text"); //pobiera tekst ktory wstawi
        if(name.length()<3)
        {
        	m.addAttribute("nulltekst", "Co najmniej 3 znaki");
        	return "redirect:/users";//powracamy na strone
        }
        else{
	        User moj = dao.findByLogin(principal.getName());
	        Post jakis = new Post(moj.getUserid(),moj.getLogin(), name);
	        postdao.save(jakis); //zapisujemy do bazy danych
	        return "redirect:/users";//powracamy na strone
        }     
    }
    
  //Usuwanie swojego posta
    @RequestMapping(value = "/deletethispost", method = RequestMethod.POST)
    public String deletethisPost(@RequestParam Integer id) {    
    postdao.deleteById(id);
    //przekierowanie do adresu url: /login         
    return "redirect:/users";     
    }
  
    
} 