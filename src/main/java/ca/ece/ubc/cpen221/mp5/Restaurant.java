package ca.ece.ubc.cpen221.mp5;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.*;

public class Restaurant implements Business{
	/**
	 *
	 * Rep Invariant:
	 * none of the fields can be null
	 * reviews, neighbourhods and categories contain no null entries
	 * each entry in reviews is a valid review id that exists in the same database Restaurant exists in
	 * Abstraction Function:
	 * Represents a restaurant as a "set" of fields
	 *
	 */
	private static Long id = 0l;
	private String name;
	private Set<String> reviews;
	private boolean open;
	private String url;
	private String idStr;
	private double longitude;
	private double latitude;
	private List<String> neighbourhoods;
	private List<String> categories;
	private List<String> schools;
	private double price;
	private double rating;
	private int reviewCount;
	private String address;
	private String photoUrl;
	private String state;
	
	public Restaurant(JsonObject info) throws NullPointerException{
		try {
			this.idStr = info.getString("business_id");
		}catch(NullPointerException e) {
			this.idStr = id.toString();
			id++;
		}
		this.name = info.getString("name");
		this.price = info.getJsonNumber("price").doubleValue();
		if(price< 1.0) {
			this.rating = 1.0;
		}else if(price > 5.0) {
			this.rating = 5.0;
		}
		try {
			this.rating = info.getInt("stars");
			if(rating< 1.0) {
				this.rating = 1.0;
			}else if(rating > 5.0) {
				this.rating = 5.0;
			}
		}catch(NullPointerException e) {
			this.rating = 1.0;
		}
		try {
			this.reviewCount = info.getInt("review_count");
		}catch(NullPointerException e) {
			this.reviewCount = 0;
		}
		this.photoUrl = info.getString("photo_url");
		this.address = info.getString("full_address");
		this.open = info.getBoolean("open");
		this.url = info.getString("url");
		this.longitude = info.getJsonNumber("longitude").doubleValue();
		this.latitude = info.getJsonNumber("latitude").doubleValue();
		this.state = info.getString("state");
		this.neighbourhoods = new ArrayList<String>();
		JsonArray arr = info.getJsonArray("neighborhoods");
		for(int i = 0; i < arr.size(); i++) {
			neighbourhoods.add(arr.getString(i));
		}

		this.categories = new ArrayList<String>();
		JsonArray arr2 = info.getJsonArray("categories");
		for(int i = 0; i < arr2.size(); i++) {
			categories.add(arr2.getString(i));
		}
		this.schools = new ArrayList<String>();
		JsonArray arr3 = info.getJsonArray("schools");
		for(int i = 0; i < arr3.size(); i++) {
			schools.add(arr2.getString(i));
		}
		

		this.reviews = new HashSet<String>();
	}
	
	public List<String> getNeighbourhoods() {
		return new ArrayList<String>(this.neighbourhoods);
	}
	
	public List<String> getCategories() {
		return new ArrayList<String>(this.categories);
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
	
	public Set<String> getReviews(){
		return new HashSet<>(this.reviews);
	}
	
	public boolean addReview(String id, double rating) {
		return reviews.add(id);
	}
	public Boolean isOpen() {
		return open;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public double getRating() {
		return this.rating;
	}
	
	@Override
	public String toString() {
		JsonObjectBuilder build = Json.createObjectBuilder();
		build.add("name", this.name);
		build.add("business_id", this.idStr);
		build.add("latitude", this.latitude);
		build.add("longitude", this.longitude);
		build.add("url", this.url);
		build.add("open", this.open);
		build.add("price", this.price);
		JsonArrayBuilder catBuild = Json.createArrayBuilder();
		for(String cate: this.categories) {
			catBuild.add(cate);
		}
		build.add("categories", catBuild.build());
		JsonArrayBuilder neighBuild = Json.createArrayBuilder();
		for(String ne: this.neighbourhoods) {
			neighBuild.add(ne);
		}
		build.add("neighborhoods", neighBuild.build());
		JsonArrayBuilder schoolBuild = Json.createArrayBuilder();
		for(String s: this.schools) {
			schoolBuild.add(s);
		}
		build.add("schools", schoolBuild.build());
		build.add("rating", this.rating);
		build.add("type", "business");
		build.add("full_address", this.address);
		build.add("state", this.state);
		build.add("photo_url", this.photoUrl);
		build.add("review_count", this.reviewCount);
		return build.build().toString();
	}
}
