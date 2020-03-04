/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mateusz.pai.controllers;

import com.mateusz.pai.dao.*; 
import com.mateusz.pai.entity.*; 
import java.security.Principal; 
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.RequestMapping; 


import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RequestParam;
 
@Controller 
public class UserController { 
 
    @Autowired     
    private PasswordEncoder passwordEncoder;     
    @Autowired     
    private userDao dao; 
    
    @Autowired     
    private postDao postdao; 
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)     
    public String loginPage() {         
    //zwrócenie nazwy widoku logowania - login.html 
    return "login";     
    } 
 
    @RequestMapping(value = "/register", method = RequestMethod.GET)  
    public String registerPage(Model m) {         
    //dodanie do modelu nowego użytkownika         
    m.addAttribute("user", new User());
    //zwrócenie nazwy widoku rejestracji - register.html         
    return "register";     
    } 
 
    //dodawanie uzytkownika do bazy danych(rejestracja)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPagePOST(@Valid @ModelAttribute(value = "user") User user,  Errors errors, Model m) { 
        if(errors.hasErrors())
        {        
            //zwrócenie nazwy widoku rejestracji - register.html         
         return "register";     
        }
        else
        {
        User ktos1 = dao.findByLogin(user.getLogin());
        User ktos2 = dao.findByEmail(user.getEmail());
        if(ktos1 != null ) //sprawdzamy czy znalazlo uzytkownika w bazie danych o takim loginie
        {
        	m.addAttribute("userExistMessage", "Użytkownik o takim loginie istnieje");
            return "register";
        }
         else
        {
        	 if(ktos2 != null) //sprawdzamy czy znalazlo uzytkownika w bazie danych o takim emailu
        	 {
        		 m.addAttribute("emailExistMessage", "Email taki juz istnieje");
                 return "register";
        	 }
        	 else
        	 {
        	 user.setPassword(passwordEncoder.encode(user.getPassword()));
        	 dao.save(user);
        	 m.addAttribute("createuser", "Konto zostało utworzone");
        	 //przekierowanie do adresu url: /login
        	 return "login";
        	 }
        }
        }
    }
    

    
    //Usuwanie swojego konta
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteUser(Principal principal) {    
    dao.delete(dao.findByLogin(principal.getName()));
    //przekierowanie do adresu url: /login         
    return "redirect:/login";     
    }
  
    //Usuwanie użytkownika 
    @RequestMapping(value = "/deletethisaccount", method = RequestMethod.POST)
    public String deletethisaccount(@RequestParam Integer id) {
    dao.deleteById(id);
    //przekierowanie do adresu url: /users        
    return "redirect:/users";     
    }
    
    //Zmiana imienia
    @RequestMapping(value = "/updatename", method = RequestMethod.GET)
    public String updatename(HttpServletRequest request,Principal principal,Model m)
    {
        //principal to jest do tego obiektu
        String name = request.getParameter("name"); //pobieramy wartosc z input o nazwie name
        if(name.length()<3)
        {
        	m.addAttribute("nullname", "Co najmniej 3 znaki");
        	return "redirect:/profile";//powracamy na strone
        }
        else{
        User cos = dao.findByLogin(principal.getName()); //tworzymy sobie obiekt klasy uzytkownik z danymi uzytkownika na ktorego jestesmy zalogowani
        cos.setName(name); //ustawiamy nowe imie uzytkownikowi
        dao.save(cos); //zapisujemy do bazy danych
        return "redirect:/profile";//powracamy na profil
        }
    }
    
    //Zmiana nazwiska
    @RequestMapping(value = "/updatesurname", method = RequestMethod.GET)
    public String updatesurname(HttpServletRequest request,Principal principal, Model m)
    {
        //principal to jest do tego obiektu
        String surname = request.getParameter("surname"); //pobieramy wartosc z input o nazwie surname
        if(surname.length()<3)
        {
        	m.addAttribute("nullsurname", "Co najmniej 3 znaki");
        	return "redirect:/profile";//powracamy na strone
        }
        else{
        User cos = dao.findByLogin(principal.getName()); //tworzymy sobie obiekt klasy uzytkownik z danymi uzytkownika na ktorego jestesmy zalogowani
        cos.setSurname(surname); //ustawiamy nowe nazwisko uzytkownikowi
        dao.save(cos); //zapisujemy do bazy danych obiekt 
        return "redirect:/profile";//powracamy na profil
        }
    }
    
    @RequestMapping(value = "/profile", method = RequestMethod.GET)     
    public String profilePage(Model m, Principal principal) {        
        //dodanie do modelu obiektu user - aktualnie zalogowanego użytkownika:         
    m.addAttribute("user", dao.findByLogin(principal.getName()));         
    //zwrócenie nazwy widoku profilu użytkownika -  profile.html         
    return "profile";     
    } 
 
    @RequestMapping(value = "/users", method = RequestMethod.GET)     
    public String profilePage1(Model m, Principal principal) {         
    //dodanie do modelu listy wszystkich użytkowników         
    m.addAttribute("userlist", dao.findAll()); 
    m.addAttribute("user", dao.findByLogin(principal.getName()));
    m.addAttribute("postlist", postdao.findAll());
    //zwrócenie nazwy widoku wyświetlającego wszystkich użytkowników         
    return "users";     
    }
  
    
} 