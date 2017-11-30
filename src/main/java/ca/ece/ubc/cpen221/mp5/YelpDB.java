package ca.ece.ubc.cpen221.mp5;
import java.util.*;
import java.io.*;
import javax.json.*;
import javax.json.stream.JsonParsingException;


public class YelpDB extends BusinessDB{

	public static void main(String[] args){
		try {
			YelpDB myDB = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
			System.out.println(myDB.kMeansClusters_json(4));
		}catch(IOException e){
			System.out.println("sdfweoifhohfofhofh");
		}
	}

	//TODO: Figure out how to construct Businesses (Abstract class???)
	/**
	 * Constructs the database from 3 Json Files
	 * Currently only works for a database of business (constructs restaurants booooooo)
	 * Will see if can make more generic later
	 * @param restaurantFile - name of JSON file containing restaurant data
	 * @param reviewFile - name of JSON file containing review data
	 * @param userFile - name of JSON file containing user data
	 * @modifies this - creates business/review/user map, and creates the individual
	 * businesses/users/reviews as objects
	 */
	
	public YelpDB(String restaurantFile, String reviewFile, String userFile) throws IOException{  
		super(reviewFile, userFile);

		//parse each file to get list of JSON objects, then store those in a map name (or id?) --> object

		List<JsonObject> temp = new ArrayList<>();
		temp = jsonParse(restaurantFile);
		temp.stream()
			.map(x -> buildBusiness(x))
			.forEach((x -> businesses.put(x.getId(), x)));
		List<JsonObject>temp1 = jsonParse(reviewFile);
		for(JsonObject obj : temp1){
			String review = obj.getString("review_id");
			String businessID = obj.getString("business_id");
			Business currentBus = (Business) businesses.get(businessID);
			this.busLookup.put(review, businessID);
			currentBus.addReview(review, this.reviews.get(review).rating);
		}
	}
	
	//private List<String> specialTypes = Arrays.asList("open", "business_id", "name");
	public String getRestaurant(String id) {
		try {
			return businesses.get(id.trim()).toString();
		}catch(NullPointerException e) {
			return "ERR: NO_SUCH_RESTAURANT";
		}
	}
	
	public String addRestaurant(String id) {
		Reader strRead = new StringReader(id);
		JsonReader reader = Json.createReader(strRead);
		try {
		Restaurant rest = buildBusiness(reader.readObject());
		this.businesses.put(rest.getId(), rest);
		System.out.println(rest.toString());
		return rest.toString();
		}catch(JsonParsingException | NullPointerException e) {
			return "ERR: INVALID_RESTAURANT_STRING";
		}
	}
	
	public String addReview(String id) {
		Reader strRead = new StringReader(id);
		JsonReader reader = Json.createReader(strRead);
		try {
			JsonObject obj = reader.readObject();
			Review rev = new Review(obj);
			if(users.containsKey(obj.getString("user_id"))) {
				this.userLookup.put(rev.getId(), obj.getString("user_id"));
			}else {
				return "ERR: NO_SUCH_USER";
			}
			if(businesses.containsKey(obj.getString("business_id"))) {
				this.busLookup.put(rev.getId(), obj.getString("business_id"));
			}else {
				return "ERR: NO_SUCH_RESTAURANT";
			}
			this.reviews.put(rev.getId(), rev);
			System.out.println(rev.toString());
			return rev.toString();
		}catch(JsonParsingException | NullPointerException e) {
			return "ERR: INVALID_REVIEW_STRING";
		}
	}
	
	public String addUser(String id) {
		Reader strRead = new StringReader(id);
		System.out.println(id);
		JsonReader reader = Json.createReader(strRead);
		try {
			User use = new User(reader.readObject());
			this.users.put(use.getId(), use);
			System.out.println(use.toString());
			return use.toString();
		}catch(JsonParsingException| NullPointerException e) {
			return "ERR: INVALID_USER_STRING";
		}
	}
	
	private Restaurant buildBusiness(JsonObject obj) {
		return new Restaurant(obj);
	}	
	
	public String request(String queryString) {
		return "Yooooo"; //Change this
	}
}
