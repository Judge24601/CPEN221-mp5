package ca.ece.ubc.cpen221.mp5;
import java.util.*;
import java.io.*;
import java.util.function.ToDoubleBiFunction;
import javax.json.*;

public class YelpDB<T> implements MP5Db{

	private Map<String, T> objects;
	private Map<String, User> users;
	private Map<String, Review> reviews;
	private Map<Review, T> tLookup;
	private Map<Review, User> userLookup;
	
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
		this.tLookup = new HashMap<>();
		this.userLookup = new HashMap<>();

		//parse each file to get list of JSON objects, then store those in a map name (or id?) --> object

		List<JsonObject> temp = new ArrayList<>();
		temp = jsonParse(restaurantFile);
		for(JsonObject obj : temp){
			if(obj instanceof Business) {
				Business business = buildRestaurant(obj);
				objects.put(business.getId(), (T) business);
			}
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
			this.userLookup.put(review, users.get(userID));
			
			String businessID = obj.getString("business_id");
			Business currentBus = (Business) objects.get(businessID);
			this.tLookup.put(review, objects.get(businessID));
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
	
	private Business buildRestaurant(JsonObject obj) {
		return new Restaurant(obj);
	}	
	
	public Set<T> getMatches(String queryString){
		return null; //Change this
	}
	
	public String kMeansClusters_json(int k) {
		return null; //Change this
	}
	
	public ToDoubleBiFunction<MP5Db<Business>, String> getPredictorFunction(String user){
		/*
		 * x = priciness (probably?)
		 * y = rating
		 * Sxx = (sum) (xi - mean(x))2
		 * Syy = (sum) (yi - mean(y))2
		 * Sxy = (sum) (xi - mean(x))(yi - mean(y))
		 * 
		 * b = Sxy / Sxx
		 * a = mean(y) - b * mean(x)
		 * R2 = Sxy2 / (Sxx Syy)
		 */
		User dude = users.get(user);
		double meanY = (dude.getReviews().stream()
				.map(x -> x.rating)
				.reduce(0.0, (x, y) -> x+y))/dude.getReviews().size();
		double syy = (dude.getReviews().stream()
				.map(x -> x.rating)
				.reduce(0.0, (x, y) -> x + Math.pow((y - meanY), 2.0)));
		Map<String, Business> businesses = (Map<String, Business>) objects;
		double meanX = 0.0;
		List<Double> prices = new ArrayList<Double>();
		for(Review rev: dude.getReviews()) {
			Business business = (Business) tLookup.get(rev.id);
			double price = business.getPrice();
			meanX += price;
			prices.add(price);
		}
		double sxx = 0.0;
		for(Double price: prices) {
			sxx += Math.pow(price - meanX, 2.0);
		}
		
		return null; //Change this
	}
}
