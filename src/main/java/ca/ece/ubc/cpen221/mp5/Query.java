package ca.ece.ubc.cpen221.mp5;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Query {
	//List<List<Query>> or;
	List<Query> children;
	String exp;
	String searchFor;
	Set<Restaurant> foundSet;
	boolean orFlag = false;
	//base case
	public Query(String exp, String searchFor) {
		this.exp = exp; 
		this.searchFor = searchFor;
	}
	
	public Query(List<Query> children) {
		this.children = children;
	}
	
	public void setAsOr() {
		this.orFlag = true;
	}
	
	
	//ONLY Eval OrExps
	public Set<Restaurant> eval() {
		Set<Restaurant> results = new HashSet<Restaurant>();
		for(Query andExp: this.children) {
			Set<Restaurant> baseSet = andExp.children.get(0).foundSet;
			for(Query atom: andExp.children) {
				if(atom.orFlag) {
					atom.foundSet = atom.eval();
				}
				baseSet.retainAll(atom.foundSet);
			}
			results.addAll(baseSet);
		}
		return results; 
	}
	
	public String baseRequest() {
		return this.exp + ": " + this.searchFor;
	}
	
	public void baseResult(Set<Restaurant> res) {
		this.foundSet = res;
	}
}
