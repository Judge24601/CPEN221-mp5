// Generated from C:/Users/Miles/Desktop/CPEN_221/f17-mp5-mjustice_bjury/src/main/antlr\Yelp.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link YelpParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface YelpVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link YelpParser#orExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(YelpParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link YelpParser#andExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(YelpParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link YelpParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(YelpParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link YelpParser#in}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIn(YelpParser.InContext ctx);
	/**
	 * Visit a parse tree produced by {@link YelpParser#category}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCategory(YelpParser.CategoryContext ctx);
	/**
	 * Visit a parse tree produced by {@link YelpParser#rating}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRating(YelpParser.RatingContext ctx);
	/**
	 * Visit a parse tree produced by {@link YelpParser#price}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrice(YelpParser.PriceContext ctx);
	/**
	 * Visit a parse tree produced by {@link YelpParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(YelpParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link YelpParser#root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(YelpParser.RootContext ctx);
}