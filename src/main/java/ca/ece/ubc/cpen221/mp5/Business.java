package ca.ece.ubc.cpen221.mp5;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Business {
	
	public long getId();
	
	public String getName();
	
	public Set<Review> getReviews();
	
	public Map<String, String> getInformationS();
	
	public Map<String, Number> getInformationN();
	
	public Map<String, List<String>> getInformationList();
	public double getLat();
	public double getLong();
	public Boolean isOpen();
}
