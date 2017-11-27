package ca.ece.ubc.cpen221.mp5;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class User {

	private String id;
	private String name;
	private Set<Review> reviews;
	
	public User(JsonObject info) {
		this.id = info.getString("user_id");
		this.name = info.getString("name");
		reviews = new HashSet<>();
	}

	public boolean addReview(Review review){
		return reviews.add(review);
	}

	public Set<Review> getReviews() {
		return new HashSet<>(reviews);
	}

	public boolean hasReviews(){
		return !reviews.isEmpty();
	}

	public String getId() {
		String copy = id;
		return copy;
	}

	public String getName() {
		String copy = name;
		return copy;
	}
}
