package ca.ece.ubc.cpen221.mp5;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.*;
public class Restaurant implements Business{

	private String name;
	private Set<Review> reviews;
	private boolean open;
	private String url;
	private String idStr;
	private double longitude;
	private double latitude;
	private List<String> neighbourhoods;
	private List<String> categories;
	private double price;
	private double rating;
	
	public Restaurant(JsonObject info){
		this.idStr = info.getString("business_id");
		this.name = info.getString("name");
		this.price = info.getInt("price");
		this.rating = info.getInt("stars");
		this.open = info.getBoolean("open");
		this.url = info.getString("url");
		this.longitude = info.getJsonNumber("longitude").doubleValue();
		this.latitude = info.getJsonNumber("latitude").doubleValue();

		this.neighbourhoods = new ArrayList<>();
		for(JsonValue jval : info.getJsonArray("neigborhoods"))neighbourhoods.add(jval.toString());

		this.categories = new ArrayList<>();
		for(JsonValue jval : info.getJsonArray("categories"))categories.add(jval.toString());

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
	
	public Set<Review> getReviews(){
		return new HashSet<>(this.reviews);
	}
	
	public boolean addReview(Review rev) {
		return reviews.add(rev);
	}
	public Boolean isOpen() {
		return open;
	}
}
