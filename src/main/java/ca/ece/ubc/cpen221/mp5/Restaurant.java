package ca.ece.ubc.cpen221.mp5;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.*;
/**
 * Abstraction function: Represents a restaurant business. Holds all the data
 * that is important for a restaurant to have, such as name, location, and rating.
 */

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
	private double price;
	private double rating;
	private int reviewCount;
	private String address;
	private String photoUrl;
	
	public Restaurant(JsonObject info) throws NullPointerException{
		try {
			this.idStr = info.getString("business_id");
		}catch(NullPointerException e) {
			this.idStr = id.toString();
			id++;
		}
		this.name = info.getString("name");
		this.price = info.getJsonNumber("price").doubleValue();
		try {
			this.rating = info.getInt("stars");
		}catch(NullPointerException e) {
			this.rating = 0.0;
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

		this.neighbourhoods = new ArrayList<String>();
		JsonArray arr = info.getJsonArray("neighborhoods");
		for(int i = 0; i < arr.size(); i++) {
			neighbourhoods.add(arr.getString(i));
		}

		this.categories = new ArrayList<String>();
		JsonArray arr2 = info.getJsonArray("categories");
		for(int i = 0; i < arr2.size(); i++) {
			neighbourhoods.add(arr2.getString(i));
		}

		this.reviews = new HashSet<String>();
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
		build.add("rating", this.rating);
		build.add("photo_url", this.photoUrl);
		build.add("review_count", this.reviewCount);
		build.add("full_address", this.address);
		return build.build().toString();
	}
}
