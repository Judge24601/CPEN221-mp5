package ca.ece.ubc.cpen221.mp5;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
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
		Set<Centroid> centres = new HashSet<>();
		Map<Centroid, Cluster> clusters = new HashMap<>();
		Map<Centroid, Cluster> oldClusters = new HashMap<>();

		//make k random centroids
		Iterator<String> itr = businesses.keySet().iterator();
		for(int i = 0; i < k; i++){
			Centroid newCentre;

			//assigning first centroids to first restaurant locations, to ensure no empties
			//also, makes sure that no two centroids have the same location
			do{
				if(itr.hasNext()) {
					Business temp = businesses.get(itr.next());
					newCentre = new Centroid(temp.getLocation());
				}else {
					return "too many clusters!";
				}
			}while(centres.contains(newCentre));

			centres.add(newCentre);
		}

		//ASSIGNING RESTAURANTS TO CLUSTERS
		clusters = assign(centres);
		oldClusters.putAll(clusters);

		///SORTING THE CLUSTERS + REASSIGNING/////////////////
		while(true){
			//find the new centre of each cluster, make new set of centroids
			centres.clear();
			for(Centroid centroid : clusters.keySet()){
				//get the cluster, find its centre, add new centroid at that point to list
				double[] location = clusters.get(centroid).getCentre();
				centres.add(new Centroid(location));
			}

			clusters = assign(centres);
			if(clusters.equals(oldClusters)) break;
			oldClusters.putAll(clusters);
		}
		
		//convert clusters to JSON

		return null; //Change this
	}

	/**
	 * Assigns restaurants to clusters based on location
	 * @param centres
	 * @return
	 */
	private Map<Centroid, Cluster> assign(Set<Centroid> centres){
		Map<Centroid, Cluster> clusters = new HashMap<>();
		for(Centroid centroid : centres){
			clusters.put(centroid, new Cluster(centroid));
		}

		for(String businessID : businesses.keySet()){
			Business tempBusiness = businesses.get(businessID);
			Centroid tempCentre = getClosestCentroid(tempBusiness, clusters.keySet());

			//add to cluster
			clusters.get(tempCentre).addBusiness(tempBusiness);
		}

		return clusters;
	}

	private Centroid getClosestCentroid(Business business, Set<Centroid> centroids){
		double minLocationDiff = -1;
		double locationDiff;
		double latDiff;
		double lonDiff;

		double[] loc = business.getLocation();
		double latitude = loc[0];
		double longitude = loc[1];

		Centroid closest = new Centroid(null); //temporary

		for(Centroid centroid : centroids){
			//get location difference and compare it to the minimum
			latDiff = centroid.latitude - latitude;
			lonDiff = centroid.longitude - longitude;
			locationDiff = Math.sqrt(latDiff*latDiff + lonDiff*lonDiff);

			//if this is the first, set it as the thing to compare to.
			//otherwise, compare and swap if smaller.
			if(minLocationDiff == -1){
				minLocationDiff = locationDiff;
				closest = centroid;
			}else if(locationDiff < minLocationDiff){
				minLocationDiff = locationDiff;
				closest = centroid;
			}
		}

		return closest;
	}

	private class Cluster{

		private Centroid centroid;
		private Set<Business> children;

		public Cluster(Centroid centroid){
			this.centroid = centroid;
			children = new HashSet<>();
		}

		public boolean addBusiness(Business business){
			return children.add(business);
		}

		public boolean isEmpty(){
			return children.isEmpty();
		}

		public double[] getCentre(){
			double latitudeSum = 0;
			double longitudeSum = 0;

			for(Business bus : children){
				double [] location = bus.getLocation();
				latitudeSum += location[0];
				longitudeSum += location[1];
			}

			double[] newLocation = {latitudeSum/children.size(), longitudeSum/children.size()};
			return newLocation;
		}

	}

	private class Centroid{

		final double latitude;
		final double longitude;

		public Centroid(double [] location){
			this.latitude = location[0];
			this.longitude = location[1];
		}

		public boolean equals(Centroid other){
			return (this.longitude == other.longitude && this.latitude == other.latitude);
		}
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
