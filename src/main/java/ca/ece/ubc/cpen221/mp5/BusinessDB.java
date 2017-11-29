package ca.ece.ubc.cpen221.mp5;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public abstract class BusinessDB implements MP5Db<Business>{
	protected Map<String, Business> businesses;
	protected Map<String, User> users;
	protected Map<String, Review> reviews;
	protected Map<String, String> busLookup; //Review to Business
	protected Map<String, String> userLookup; //Review to User
	
	public BusinessDB(String reviewFile, String userFile) throws IOException {
		this.businesses = new HashMap<>();
		this.reviews = new HashMap<>();
		this.users = new HashMap<>();
		this.busLookup = new HashMap<>();
		this.userLookup = new HashMap<>();
		List<JsonObject> temp1 = jsonParse(userFile);
		temp1.stream()
			.map(x -> new User(x))
			.forEach(x -> users.put(x.getId(), x));
		/*
		for(JsonObject obj : temp1){
			User user = new User(obj);g
			users.put(user.getId(), user);
		}
		*/
		List<JsonObject>temp = jsonParse(reviewFile);
		for(JsonObject obj : temp){
			Review review = new Review(obj);
			reviews.put(obj.getString("review_id"), review);
			String userID = obj.getString("user_id");
			users.get(userID).addReview(review.id);
			this.userLookup.put(review.id, userID);
		}
	}
	/**
	 * 
	 * @param fileName - path for file
	 * @return List of JSONbusinesses from the file
	 * @throws IOException if file does not exist
	 */
	protected static List<JsonObject> jsonParse(String fileName) throws IOException {
		Scanner sc = new Scanner(new FileInputStream(fileName));
		JsonReader readJson;
		List<JsonObject> jsonList = new ArrayList<>();

		while(sc.hasNext()){
			readJson = Json.createReader(new StringReader(sc.nextLine()));
			JsonObject temp = readJson.readObject();
			jsonList.add(temp);
		}
		sc.close();
		return jsonList;
	}
	
	public Set<Business> getMatches(String queryString){
		return null; //Change this
	}
	
	public String kMeansClusters_json(int k) {
		return null; //Change this
	}
	
	/**
	 * Takes the current data from a specified user's reviews, and generates
	 * a predictor function based on the price of a restaurant, to determine
	 * that user's general rating for a restaurant of x price.
	 * @param user - user to predict behaviour of
	 * @returns function of two paramaters, a database and string, to determine 
	 * @throws IllegalArgumentException if a prediction cannot be made
	 */
	@SuppressWarnings("unchecked")
	public ToDoubleBiFunction<MP5Db<Business>, String> getPredictorFunction(String user){
		/*
		 * x = priciness 
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
						.map(x -> reviews.get(x))
						.map(x -> x.rating)
						.reduce(0.0, (x, y) -> x+y))/dude.getReviews().size();
				double syy = (dude.getReviews().stream()
						.map(x -> reviews.get(x))
						.map(x -> x.rating)
						.reduce(0.0, (x, y) -> x + Math.pow((y - meanY), 2.0)));
				double meanX = 0.0;
				List<Double> prices = new ArrayList<Double>();
				List<Double> ratings = new ArrayList<Double>();
				for(String rev: dude.getReviews()) {
					Business business = businesses.get(busLookup.get(rev));
					double price = business.getPrice();
					meanX += price;
					prices.add(price);
					ratings.add(reviews.get(rev).rating);
				}
				double sxx = 0.0;
				double sxy = 0.0;
				for(int i = 0; i < prices.size(); i++) {
					sxx += Math.pow(prices.get(i) - meanX, 2.0);
					sxy += ((prices.get(i)) - meanX)*(ratings.get(i) - meanY);
				}
				double b = sxy/sxx;
				if(b == Double.NaN) {
					throw new IllegalArgumentException();
				}
				double a = meanY - b*meanX;
				double r_2 = (sxy*sxy)/(sxx*syy);
				return (x, y) -> a*((BusinessDB)x).businesses.get(y).getPrice() + b;
	}
}
