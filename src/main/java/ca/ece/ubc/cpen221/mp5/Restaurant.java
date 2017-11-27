package ca.ece.ubc.cpen221.mp5;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.*;
public class Restaurant implements Business{

	private long id;
	private String name;
	private Set<Review> reviews;
	private Map<String, String> informationString;
	private Map<String, Number> informationNumber;
	private Map<String, List<String>> informationList;
	private boolean open;
	private String url;
	private String idStr;
	private long longitude;
	private List<JsonValue> neighbourhoods;
	private List<JsonValue> categories;
	private double price;
	private double rating;
	
	public Restaurant(long id, String name, Set<Review> reviews, Map<String, String> informationString, Map<String, Integer> informationNumber, Map<String, List<String>> informationList, boolean open) {
		this.id = id;
		this.name = name;
		this.reviews = reviews;
		this.informationString = informationString;
		//this.informationNumber = informationNumber;
		this.informationList = informationList;
		this.open = open;
	}

	public Restaurant(JsonObject info){
		this.idStr = info.getString("business_id");
		this.name = info.getString("name");
		this.price = info.getInt("price");
		this.rating = info.getInt("stars");
		this.open = info.getBoolean("open");
		this.url = info.getString("url");
		this.longitude = info.getInt("longitude");

		this.neighbourhoods = new ArrayList<>();
		for(JsonValue jval : info.getJsonArray("neigborhoods"))neighbourhoods.add(jval);

		this.categories = new ArrayList<>();
		for(JsonValue jval : info.getJsonArray("categories"))categories.add(jval);


		//do smth abt reviews, maybe also number of reviews??
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return new String(name);
	}
	
	public Set<Review> getReviews(){
		return new HashSet<Review>(reviews);
	}
	
	public Map<String, String> getInformationS(){
		return new HashMap<String, String>(informationString);
	}
	
	public Map<String, Number> getInformationN(){
		return new HashMap<String, Number>(informationNumber);
	}
	
	public Map<String, List<String>> getInformationList(){
		return new HashMap<String, List<String>>(informationList);
	}
	
	public double getLong() {
		return (double) informationNumber.get("longitude");
	}
	
	public double getLat() {
		return (double) informationNumber.get("latitude");
	}
	
	public Boolean isOpen() {
		return open;
	}
}
