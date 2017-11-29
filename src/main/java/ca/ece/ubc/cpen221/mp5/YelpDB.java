package ca.ece.ubc.cpen221.mp5;
import java.util.*;
import java.io.*;
import java.util.function.Function;
import java.util.function.ToDoubleBiFunction;
import javax.json.*;

public class YelpDB extends BusinessDB{
	//TODO: Figure out how to construct Businesses (Abstract class???)
	/**
	 * Constructs the database from 3 Json Files
	 * Currently only works for a database of business (constructs restaurants booooooo)
	 * Will see if can make more generic later
	 * @param restaurantFile - name of JSON file containing restaurant data
	 * @param reviewFile - name of JSON file containing review data
	 * @param userFile - name of JSON file containing user data
	 * @modifies this - creates business/review/user map, and creates the individual
	 * businesses/users/reviews as businesses
	 */
	
	public YelpDB(String restaurantFile, String reviewFile, String userFile) throws IOException{
		super(reviewFile, userFile);
		this.businesses = new HashMap<>();
		this.reviews = new HashMap<>();
		this.users = new HashMap<>();
		this.busLookup = new HashMap<>();
		this.userLookup = new HashMap<>();

		//parse each file to get list of JSON businesses, then store those in a map name (or id?) --> object

		List<JsonObject> temp = new ArrayList<>();
		temp = jsonParse(restaurantFile);
		temp.parallelStream()
			.map(x -> buildBusiness(x))
			.forEach((x -> businesses.put(x.getId(), x)));
		List<JsonObject>temp1 = jsonParse(reviewFile);
		for(JsonObject obj : temp1){
			String review = obj.getString("review_id");
			String businessID = obj.getString("business_id");
			Business currentBus = (Business) businesses.get(businessID);
			this.busLookup.put(review, businessID);
			currentBus.addReview(review);
		}
	}
	
	//private List<String> specialTypes = Arrays.asList("open", "business_id", "name");

	
	private Restaurant buildBusiness(JsonObject obj) {
		return new Restaurant(obj);
	}	
	
	public String request(String queryString) {
		return "Yooooo"; //Change this
	}
	
}
