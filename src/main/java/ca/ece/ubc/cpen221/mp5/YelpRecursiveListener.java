package ca.ece.ubc.cpen221.mp5;
// Genepried from C:/Users/Miles/Desktop/CPEN_221/f17-mp5-mjustice_bjury/src/main/antlr\Yelp.g4 by ANTLR 4.7

import ca.ece.ubc.cpen221.antlr.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * Uses two stacks of Lists of Queries to hold the current and & or expressions'
 * children - enabling recursive construction.
 * 
 * Rep Invariant:
 * orList is not null
 * andList is not null
 * database is not null
 * main is the orExpression at the root node.
 */
public class YelpRecursiveListener extends YelpBaseListener {
	/**
	 *
	 */
	private Query main;
	private Stack<List<Query>> orList = new Stack<List<Query>>();
	private Stack<List<Query>> andList = new Stack<List<Query>>();
	public YelpDB database;
	@Override public void enterOrExpr(YelpParser.OrExprContext ctx) { 
		this.orList.push(new ArrayList<Query>());
	}
	/**
	 *	When you leave an orExpression, simply pop off the top of the stack
	 * - these are your children. Congratulations!
	 * If these are the last children, congratulations again! You are the root node.
	 */
	@Override public void exitOrExpr(YelpParser.OrExprContext ctx) { 
		Query orExpr;
		orExpr = new Query(orList.pop());
		orExpr.setAsOr();
		try {
			orList.peek();
			andList.peek().add(orExpr);
		}catch(EmptyStackException e) {
			main = orExpr;
		}
	}
	/**
	 * {@inheritDoc}
	 *
	 * Add a new List onto the stack for this new andExpression.
	 */
	@Override public void enterAndExpr(YelpParser.AndExprContext ctx) {
		this.andList.push(new ArrayList<Query>());
	}
	/**
	 * Now you take it off, and add the resulting expression to the top 
	 * of the orList.
	 */
	@Override public void exitAndExpr(YelpParser.AndExprContext ctx) { 
		Query andExpr = new Query(andList.pop());
		orList.peek().add(andExpr);
	}
	
	/**
	 * Filter by those in the parsed neighbourhoods - add to query.
	 */
	@Override public void enterIn(YelpParser.InContext ctx) { 
		Query in = new Query("in", ctx.getText());
		System.out.println(in.searchFor.replaceAll("in", "").replaceAll("[\\(\\)]", ""));
		in.foundSet =(database.businesses.values().stream()
				.map(x -> (Restaurant)x)
				.filter(x -> x.getNeighbourhoods().contains(in.searchFor.replaceAll("in", "").replaceAll("[\\(\\)]", "")))
				.collect(Collectors.toSet()));
		andList.peek().add(in);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitIn(YelpParser.InContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * Filter by those in the parsed categories - add to query.
	 */
	@Override public void enterCategory(YelpParser.CategoryContext ctx) { 
		Query cat = new Query("category", ctx.getText());
		System.out.println(cat.searchFor.replaceAll("category", "").replaceAll("[\\(\\)]", ""));
		cat.foundSet =(database.businesses.values().stream()
				.map(x -> (Restaurant)x)
				.filter(x -> x.getCategories().contains(cat.searchFor.replaceAll("category", "").replaceAll("[\\(\\)]", "")))
				.collect(Collectors.toSet()));
		andList.peek().add(cat);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitCategory(YelpParser.CategoryContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * Filter by those with ratings satisfying inequality - add to query.
	 */
	@Override public void enterRating(YelpParser.RatingContext ctx) {
		Query pri = new Query("rating", ctx.getText());
		String ineq = pri.searchFor.replaceAll("rating", "").replaceAll("[0-9 ]+", "");
		switch(ineq) {
		case "<":
			pri.foundSet =(database.businesses.values().stream()
					.map(x -> (Restaurant)x)
					.filter(x -> x.getRating() < Integer.parseInt(pri.searchFor.replaceAll("[^0-9]", "")))
					.collect(Collectors.toSet()));
			break;
		case "<=":
			pri.foundSet =(database.businesses.values().stream()
					.map(x -> (Restaurant)x)
					.filter(x -> x.getRating() <= Integer.parseInt(pri.searchFor.replaceAll("[^0-9]", "")))
					.collect(Collectors.toSet()));
			break;
		case ">":
			pri.foundSet =(database.businesses.values().stream()
					.map(x -> (Restaurant)x)
					.filter(x -> x.getRating() > Integer.parseInt(pri.searchFor.replaceAll("[^0-9]", "")))
					.collect(Collectors.toSet()));
			break;
		case ">=":
			pri.foundSet =(database.businesses.values().stream()
					.map(x -> (Restaurant)x)
					.filter(x -> x.getRating() >= Integer.parseInt(pri.searchFor.replaceAll("[^0-9]", "")))
					.collect(Collectors.toSet()));
			break;
		case "=":
			pri.foundSet =(database.businesses.values().stream()
					.map(x -> (Restaurant)x)
					.filter(x -> x.getRating() == Integer.parseInt(pri.searchFor.replaceAll("[^0-9]", "")))
					.collect(Collectors.toSet()));
			break;
		}
		
		andList.peek().add(pri);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitRating(YelpParser.RatingContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * Filter by those with prices satisfying inequality - add to query.
	 */
	@Override public void enterPrice(YelpParser.PriceContext ctx) { 
		Query pri = new Query("priing", ctx.getText());
		String ineq = pri.searchFor.replaceAll("price", "").replaceAll("[0-9 ]+", "");
		switch(ineq) {
		case "<":
			pri.foundSet =(database.businesses.values().stream()
					.map(x -> (Restaurant)x)
					.filter(x -> x.getPrice() < Integer.parseInt(pri.searchFor.replaceAll("[^0-9]", "")))
					.collect(Collectors.toSet()));
			break;
		case "<=":
			System.out.println(pri.searchFor.replaceAll("[^0-9]", ""));
			pri.foundSet =(database.businesses.values().stream()
					.map(x -> (Restaurant)x)
					.filter(x -> x.getPrice() <= Integer.parseInt(pri.searchFor.replaceAll("[^0-9]", "")))
					.collect(Collectors.toSet()));
			break;
		case ">":
			pri.foundSet =(database.businesses.values().stream()
					.map(x -> (Restaurant)x)
					.filter(x -> x.getPrice() > Integer.parseInt(pri.searchFor.replaceAll("[^0-9]", "")))
					.collect(Collectors.toSet()));
			break;
		case ">=":
			pri.foundSet =(database.businesses.values().stream()
					.map(x -> (Restaurant)x)
					.filter(x -> x.getPrice() >= Integer.parseInt(pri.searchFor.replaceAll("[^0-9]", "")))
					.collect(Collectors.toSet()));
			break;
		case "=":
			pri.foundSet =(database.businesses.values().stream()
					.map(x -> (Restaurant)x)
					.filter(x -> x.getPrice() == Integer.parseInt(pri.searchFor.replaceAll("[^0-9]", "")))
					.collect(Collectors.toSet()));
			break;
		}
		andList.peek().add(pri);
	}
	/**
	 * {@inheritDoc}
	 *
	 * Filter by those with names matching string - add to query.
	 */
	@Override public void enterName(YelpParser.NameContext ctx) { 
		Query nam = new Query("name", ctx.getText());
		nam.foundSet =(database.businesses.values().stream()
				.map(x -> (Restaurant)x)
				.filter(x -> x.getName().equals((nam.searchFor.replaceAll("name", "").replaceAll("[\\(\\)]", ""))))
				.collect(Collectors.toSet()));
		andList.peek().add(nam);
	}
	
	public Set<Business> getResult(){
		return main.eval();
	}

}