package ca.ece.ubc.cpen221.mp5;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.*;
public class Restaurant extends Business{

	private String url;
	private double price;
	private List<String> neighbourhoods;
	private List<String> categories;
	
	public Restaurant(JsonObject info){
		super(info);
		this.price = info.getJsonNumber("price").doubleValue();
		this.url = info.getString("url");

		this.neighbourhoods = new ArrayList<>();
		for(JsonValue jval : info.getJsonArray("neigborhoods"))neighbourhoods.add(jval.toString());

		this.categories = new ArrayList<>();
		for(JsonValue jval : info.getJsonArray("categories"))categories.add(jval.toString());

		this.reviews = new HashSet<Review>();
	}
}
