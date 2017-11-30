package ca.ece.ubc.cpen221.mp5;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Business {
	
	public String getId();
	
	public String getName();
	
	public Set<String> getReviews();
	
	public boolean addReview(String id, double rating);
	
	public double[] getLocation();
	
	public double getPrice();
	
	public Boolean isOpen();
}
