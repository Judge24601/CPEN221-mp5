package ca.ece.ubc.cpen221.mp5;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Business {
	
	public String getId();
	
	public String getName();
	
	public Set<Review> getReviews();
	
	public boolean addReview(Review rev);
	
	public double[] getLocation();
	
	public Boolean isOpen();
}
