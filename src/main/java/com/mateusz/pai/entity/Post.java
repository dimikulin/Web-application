package com.mateusz.pai.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity @Table(name = "Posty") 
public class Post {
	  	
		@Id     
	    @GeneratedValue(strategy = GenerationType.IDENTITY)    
	    private Integer postid;
	    
	    private Integer userid;     
	    
	    private String username;
	    
	   
	    private String tekst;
	    
	    public Post() {     } 
	    
	    public Post(Integer userid,String username, String tekst) {
	        this.userid = userid;
	        this.tekst = tekst;
	        this.username = username; 
	    }

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public Integer getPostid() {
			return postid;
		}

		public void setPostid(Integer postid) {
			this.postid = postid;
		}

		public Integer getUserid() {
			return userid;
		}

		public void setUserid(Integer userid) {
			this.userid = userid;
		}

		public String getTekst() {
			return tekst;
		}

		public void setTekst(String tekst) {
			this.tekst = tekst;
		}
	    
	    
}
