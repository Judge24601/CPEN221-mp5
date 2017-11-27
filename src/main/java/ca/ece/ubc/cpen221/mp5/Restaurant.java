package ca.ece.ubc.cpen221.mp5;
import java.util.*;
public class Restaurant implements Business{

	private long id;
	private String name;
	private Set<Review> reviews;
	private Map<String, String> informationString;
	private Map<String, Number> informationNumber;
	private Map<String, List<String>> informationList;
	private boolean open;
	
	public Restaurant(long id, String name, Set<Review> reviews, Map<String, String> informationString, Map<String, Integer> informationNumber, Map<String, List<String>> informationList, boolean open) {
		this.id = id;
		this.name = name;
		this.reviews = reviews;
		this.informationString = informationString;
		//this.informationNumber = informationNumber;
		this.informationList = informationList;
		this.open = open;
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
