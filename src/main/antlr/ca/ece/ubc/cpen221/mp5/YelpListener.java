package ca.ece.ubc.cpen221.mp5;// Generated from C:/Users/Miles/Desktop/CPEN_221/f17-mp5-mjustice_bjury/src/main/antlr\Yelp.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link YelpParser}.
 */
public interface YelpListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link YelpParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(YelpParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link YelpParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(YelpParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link YelpParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(YelpParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link YelpParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(YelpParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link YelpParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(YelpParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link YelpParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(YelpParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link YelpParser#in}.
	 * @param ctx the parse tree
	 */
	void enterIn(YelpParser.InContext ctx);
	/**
	 * Exit a parse tree produced by {@link YelpParser#in}.
	 * @param ctx the parse tree
	 */
	void exitIn(YelpParser.InContext ctx);
	/**
	 * Enter a parse tree produced by {@link YelpParser#category}.
	 * @param ctx the parse tree
	 */
	void enterCategory(YelpParser.CategoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link YelpParser#category}.
	 * @param ctx the parse tree
	 */
	void exitCategory(YelpParser.CategoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link YelpParser#rating}.
	 * @param ctx the parse tree
	 */
	void enterRating(YelpParser.RatingContext ctx);
	/**
	 * Exit a parse tree produced by {@link YelpParser#rating}.
	 * @param ctx the parse tree
	 */
	void exitRating(YelpParser.RatingContext ctx);
	/**
	 * Enter a parse tree produced by {@link YelpParser#price}.
	 * @param ctx the parse tree
	 */
	void enterPrice(YelpParser.PriceContext ctx);
	/**
	 * Exit a parse tree produced by {@link YelpParser#price}.
	 * @param ctx the parse tree
	 */
	void exitPrice(YelpParser.PriceContext ctx);
	/**
	 * Enter a parse tree produced by {@link YelpParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(YelpParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link YelpParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(YelpParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link YelpParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(YelpParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link YelpParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(YelpParser.RootContext ctx);
}