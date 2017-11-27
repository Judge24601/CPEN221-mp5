package ca.ece.ubc.cpen221.mp5;
import java.util.*;
import java.io.*;
import java.util.function.ToDoubleBiFunction;
import javax.json.*;

public class YelpDB<Business> implements MP5Db{

	private Map<String, Restaurant> restaurants;
	private Map<String, User> users;
	private Map<String, Review> reviews;

	public static void main(String [] args){
		//YelpDB<Restaurant> myDB = new YelpDB<>("restaurants.json", "reviews.json", "users.json");

		try{
			List<JsonObject> myList = jsonParse("data/restaurants.json");
			for(JsonObject obj : myList){
				//System.out.println(obj);
				System.out.println(obj.getString("name"));
			}
		}catch (IOException e){
			System.out.println("whoopsie");
		}
	}
	
	public YelpDB(String restaurantFile, String reviewFile, String userFile) throws IOException{
		this.restaurants = new HashMap<>();
		this.reviews = new HashMap<>();
		this.users = new HashMap<>();

		//parse each file to get list of JSON objects, then store those in a map name (or id?) --> object

		List<JsonObject> temp = new ArrayList<>();
		temp = jsonParse(restaurantFile);
		for(JsonObject obj : temp){
			Restaurant restaurant = new Restaurant(obj);
			restaurants.put(obj.getString("business_id"), restaurant);
		}

		temp = jsonParse(userFile);
		for(JsonObject obj : temp){
			User user = new User(obj)
			users.put(obj.getString("user_id"), user);
		}

		temp = jsonParse(reviewFile);
		for(JsonObject obj : temp){
			Review review = new Review(obj);
			reviews.put(obj.getString("review_id"), review);

			//putting reviews into users, restaurants
			String userID = obj.getString("user_id");
			users.get(userID).addReview(review);

			String businessID = obj.getString("business_id");
			restaurants.get(businessID).addReview(review);
		}
	}
	
	private List<String> specialTypes = Arrays.asList("open", "business_id", "name");
	
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
