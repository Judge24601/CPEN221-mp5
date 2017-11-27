package ca.ece.ubc.cpen221.mp5;
import java.util.*;
import java.io.*;
import java.util.function.ToDoubleBiFunction;
import javax.json.*;

public class YelpDB<T> implements MP5Db{

	private Map<String, T> objects;
	private Map<String, User> users;
	private Map<String, Review> reviews;
	
	/**
	 * Constructs the database from 3 Json Files
	 * Currently only works for a database of businesses
	 * Will see if can make more generic later
	 * @param restaurantFile - name of JSON file containing restaurant data
	 * @param reviewFile - name of JSON file containing review data
	 * @param userFile - name of JSON file containing user data
	 * @modifies this - creates business/review/user map, and creates the individual
	 * businesses/users/reviews as objects
	 */
	
	public YelpDB(String restaurantFile, String reviewFile, String userFile) throws IOException{
		this.objects = new HashMap<>();
		this.reviews = new HashMap<>();
		this.users = new HashMap<>();

		//parse each file to get list of JSON objects, then store those in a map name (or id?) --> object

		List<JsonObject> temp = new ArrayList<>();
		temp = jsonParse(restaurantFile);
		for(JsonObject obj : temp){
			Business business =  new Restaurant(obj);
			objects.put(business.getId(), (T) business);
		}

		temp = jsonParse(userFile);
		for(JsonObject obj : temp){
			User user = new User(obj);
			users.put(user.getId(), user);
		}

		temp = jsonParse(reviewFile);
		for(JsonObject obj : temp){
			Review review = new Review(obj);
			reviews.put(obj.getString("review_id"), review);

			//putting reviews into users, restaurants
			String userID = obj.getString("user_id");
			users.get(userID).addReview(review);

			String businessID = obj.getString("business_id");
			Business currentBus = (Business) objects.get(businessID);
			currentBus.addReview(review);
		}
	}
	
	//private List<String> specialTypes = Arrays.asList("open", "business_id", "name");
	/**
	 * 
	 * @param fileName - path for file
	 * @return List of JSONObjects from the file
	 * @throws IOException if file does not exist
	 */
	private static List<JsonObject> jsonParse(String fileName) throws IOException {
		Scanner sc = new Scanner(new FileInputStream(fileName));
		JsonReader readJson;
		List<JsonObject> jsonList = new ArrayList<>();

		while(sc.hasNext()){
			readJson = Json.createReader(new StringReader(sc.nextLine()));
			JsonObject temp = readJson.readObject();
			jsonList.add(temp);
		}

		return jsonList;
	}
	
	public Set<Business> getMatches(String queryString){
		return null; //Change this
	}
	
	public String kMeansClusters_json(int k) {
		return null; //Change this
	}
	
	public ToDoubleBiFunction<MP5Db<Business>, String> getPredictorFunction(String user){
		return null; //Change this
	}
}
