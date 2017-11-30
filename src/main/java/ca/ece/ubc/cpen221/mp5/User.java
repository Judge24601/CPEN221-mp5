package ca.ece.ubc.cpen221.mp5;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import java.util.HashSet;
import java.util.Set;
/**
 * 
 * Rep Invariant: None of the fields can be null
 * each entry in reviews is not null and a valid review id that exists in the same database Restaurant exists in
 * Abstraction Function:
 * Represents a user as a "set" of fields
 *
 */
public class User {
	private static Long baseId = 0l;
	private String id;
	private String name;
	private int reviewCount;
	private String url;
	private double averageStars;
	private Set<String> reviews;
	
	public User(JsonObject info) throws NullPointerException{
		try {
			this.id = info.getString("user_id");
		}catch(NullPointerException e){
			this.id = baseId.toString();
			baseId++;
		}
		this.name = info.getString("name");
		try {
			this.reviewCount = info.getInt("review_count");
		}catch(NullPointerException e) {
			this.reviewCount = 0;
		}
		try {
			this.url = info.getString("url");
		}catch(NullPointerException e){
			this.url = "http://www.yelp.com/user_details?userid="+ this.id;
		}
		try {
			this.averageStars = info.getJsonNumber("average_stars").doubleValue();
		}catch(NullPointerException e) {
			this.averageStars = 0.0;
		}
		reviews = new HashSet<>();
	}

	public boolean addReview(String reviewId, double rating){
		reviewCount++;
		averageStars = (averageStars + rating)/reviewCount; 
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
	
	@Override
	public String toString() {
		JsonObjectBuilder build = Json.createObjectBuilder();
		build.add("name", this.name);
		build.add("user_id", this.id);
		build.add("url", this.url);
		build.add("review_count", this.reviewCount);
		build.add("average_stars", this.averageStars);
		return build.build().toString();
	}
}
