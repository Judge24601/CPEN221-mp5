package ca.ece.ubc.cpen221.mp5;
// Generated from C:/Users/Miles/Desktop/CPEN_221/f17-mp5-mjustice_bjury/src/main/antlr\Yelp.g4 by ANTLR 4.7

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * This class provides an empty implementation of {@link YelpListener},
 * which can be extended to create a listener which only needs to handle a subset
 * of the available methods.
 */
public class YelpBaseListener implements YelpListener {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	private Query main;
	private Stack<List<Query>> orList = new Stack<List<Query>>();
	private Stack<List<Query>> andList = new Stack<List<Query>>();
	public YelpDB database;
	@Override public void enterOrExpr(YelpParser.OrExprContext ctx) { 
		this.orList.push(new ArrayList<Query>());
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitOrExpr(YelpParser.OrExprContext ctx) { 
		Query orExpr = new Query(orList.pop());
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterAndExpr(YelpParser.AndExprContext ctx) {
		this.andList.push(new ArrayList<Query>());
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitAndExpr(YelpParser.AndExprContext ctx) { 
		Query andExpr = new Query(andList.pop());
		orList.peek().add(andExpr);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterAtom(YelpParser.AtomContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitAtom(YelpParser.AtomContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterIn(YelpParser.InContext ctx) { 
		Query in = new Query("in", ctx.getText());
		in.foundSet =(database.businesses.values().stream()
				.map(x -> (Restaurant)x)
				.filter(x -> x.getNeighbourhoods().contains(in.searchFor))
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
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterCategory(YelpParser.CategoryContext ctx) { 
		Query cat = new Query("category", ctx.getText());
		cat.foundSet =(database.businesses.values().stream()
				.map(x -> (Restaurant)x)
				.filter(x -> x.getCategories().contains(cat.searchFor))
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
	 * <p>The default implementation does nothing.</p>
	 */
	//not right
	@Override public void enterRating(YelpParser.RatingContext ctx) {
		Query rat = new Query("rating", ctx.getText());
		rat.foundSet =(database.businesses.values().stream()
				.map(x -> (Restaurant)x)
				.filter(x -> x.getCategories().contains(rat.searchFor))
				.collect(Collectors.toSet()));
		andList.peek().add(rat);
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
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterPrice(YelpParser.PriceContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitPrice(YelpParser.PriceContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterName(YelpParser.NameContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitName(YelpParser.NameContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterRoot(YelpParser.RootContext ctx) {
		main = new Query(new ArrayList<Query>());
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitRoot(YelpParser.RootContext ctx) { 
		try {
			for(Query andExpr: andList.pop()) {
				main.addChild(andExpr);
			}
		}catch(EmptyStackException e) {
			
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterEveryRule(ParserRuleContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitEveryRule(ParserRuleContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void visitTerminal(TerminalNode node) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void visitErrorNode(ErrorNode node) { }
}