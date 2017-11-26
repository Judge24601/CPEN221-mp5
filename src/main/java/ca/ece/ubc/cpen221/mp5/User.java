package ca.ece.ubc.cpen221.mp5;

import java.util.Set;

public class User {

	public long id;
	public String name;
	public Set<Review> reviews;
	
	public User(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
}
