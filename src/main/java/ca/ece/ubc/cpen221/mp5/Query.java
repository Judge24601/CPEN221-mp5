package ca.ece.ubc.cpen221.mp5;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Query {
	//List<List<Query>> or;
	List<Query> children;
	String exp;
	String searchFor;
	Set<Business> foundSet;
	boolean orFlag = false;
	//base case
	public Query(String exp, String searchFor) {
		this.exp = exp; 
		this.searchFor = searchFor;
		this.foundSet = new HashSet<Business>();
	}
	
	public Query(List<Query> children) {
		this.children = children;
	}
	public void addChild(Query child) {
		this.children.add(child);
	}
	
	public void setAsOr() {
		this.orFlag = true;
	}
	
	
	//ONLY Eval OrExps
	public Set<Business> eval() {
		Set<Business> results = new HashSet<Business>();
		for(Query andExp: this.children) {
			Set<Business> baseSet = andExp.children.get(0).foundSet;
			for(Query atom: andExp.children) {
				if(atom.orFlag) {
					atom.foundSet = atom.eval();
				}
				System.out.println(atom.foundSet);
				baseSet.retainAll(atom.foundSet);
			}
			results.addAll(baseSet);
		}
		return results; 
	}
}
