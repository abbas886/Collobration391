package com.niit.colloboration.dao;

import java.util.List;

import com.niit.colloboration.model.Blog;

public interface BlogDAO {
	
	
	public boolean save(Blog blog);
	public boolean update(Blog blog);
	public Blog get(int id);
	public List<Blog>    list();
	
	//adming can accept/reject the blog
	//we can use update(Blog blog) method.
	
	//comment on a particular blog
	
	//one to many ->  N number of user can comment on
	// a particular blog.
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
