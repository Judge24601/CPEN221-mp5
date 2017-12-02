package ca.ece.ubc.cpen221.mp5;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * Represents a Query to the database. Can hold instances of itself (And Expression/ Or Expression)
 * Rep Invariant:
 * Either exp and search for cannot be null,
 * Or children cannot be null.
 * FoundSet must only contain Businesses in YelpDB.
 * Abstraction Function:
 * Represents a parsed query from ANTLR as an object with either a String to 
 * query the database with, or a List of other queries to compare.
 * If it is a List & orFlag is true - represents an OrExpression
 * If it is a List & orFlag is false -represents an AndExpression.
 *
 */

public class Query {
	//List<List<Query>> or;
	List<Query> children;
	String exp;
	String searchFor;
	Set<Business> foundSet;
	boolean orFlag = false;
	/**
	 * Constructs a basic query.
	 * 
	 * @param exp - Expression - the type of query
	 * @param searchFor - what to query the database for
	 */
	public Query(String exp, String searchFor) {
		this.exp = exp; 
		this.searchFor = searchFor;
		this.foundSet = new HashSet<Business>();
	}
	/**
	 * Constructs an Or or And Expression
	 * @param children - andExps or atoms, respectively.
	 */
	public Query(List<Query> children) {
		this.children = children;
	}
	public void addChild(Query child) {
		this.children.add(child);
	}
	
	public void setAsOr() {
		this.orFlag = true;
	}
	
	
	/**
	 * Evaluates orExpressions by taking the intersection of all their
	 * andExpression children, and the andExpressions taking the union of all 
	 * their atom children. Since atom can be an orExpression, works recursively to
	 * end up with only simple queries.
	 * @return Set of Businesses matching the query
	 */
	public Set<Business> eval() {
		Set<Business> results = new HashSet<Business>();
		for(Query andExp: this.children) {
			Set<Business> baseSet = andExp.children.get(0).foundSet;
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
}
