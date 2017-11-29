package ca.ece.ubc.cpen221.mp5;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class User {

	private String id;
	private String name;
	private Set<String> reviews;
	
	public User(JsonObject info) {
		this.id = info.getString("user_id");
		this.name = info.getString("name");
		reviews = new HashSet<>();
	}

	public boolean addReview(String reviewId){
		return reviews.add(reviewId);
	}

	public Set<String> getReviews() {
		return new HashSet<>(reviews);
	}

	public boolean hasReviews(){
		return !reviews.isEmpty();
	}

	public String getId() {
		return new String(id);
	}

	public String getName() {
		return new String(name);
	}
}
