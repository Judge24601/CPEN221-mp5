package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.json.JsonObject;
import javax.json.JsonValue;

public class Business {
	protected String name;
	protected Set<Review> reviews;
	protected boolean open;
	protected String idStr;
	protected double longitude;
	protected double latitude;
	protected double rating;
	
	public Business(JsonObject info){
		this.idStr = info.getString("business_id");
		this.name = info.getString("name");
		this.rating = info.getJsonNumber("stars").doubleValue();
		this.open = info.getBoolean("open");
		this.longitude = info.getJsonNumber("longitude").doubleValue();
		this.latitude = info.getJsonNumber("latitude").doubleValue();

		this.reviews = new HashSet<Review>();
	}
	
	public String getId() {
		return this.idStr;
	}
	
	public String getName() {
		return new String(name);
	}
	
	public double[] getLocation() {
		double[] location = {latitude, longitude};
		return location;
	}
	
	public boolean addReview(Review rev) {
		return reviews.add(rev);
	}
	
	public Set<Review> getReviews(){
		return new HashSet<>(this.reviews);
	}
	
	public Boolean isOpen() {
		return open;
	}
}
