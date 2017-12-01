// Generated from C:/Users/Miles/Desktop/CPEN_221/f17-mp5-mjustice_bjury/src/main/antlr\Yelp.g4 by ANTLR 4.7
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class YelpParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OR=1, AND=2, LEFT=3, RIGHT=4, NAME=5, NUM=6, INEQ=7, RATING=8, IN=9, CATEGORY=10, 
		PRICE=11, STRING=12;
	public static final int
		RULE_orExpr = 0, RULE_andExpr = 1, RULE_atom = 2, RULE_in = 3, RULE_category = 4, 
		RULE_rating = 5, RULE_price = 6, RULE_name = 7, RULE_root = 8;
	public static final String[] ruleNames = {
		"orExpr", "andExpr", "atom", "in", "category", "rating", "price", "name", 
		"root"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'||'", "'&&'", "'('", "')'", "'name'", null, null, "'rating'", 
		"'in'", "'category'", "'price'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "OR", "AND", "LEFT", "RIGHT", "NAME", "NUM", "INEQ", "RATING", "IN", 
		"CATEGORY", "PRICE", "STRING"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Yelp.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public YelpParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class OrExprContext extends ParserRuleContext {
		public List<AndExprContext> andExpr() {
			return getRuleContexts(AndExprContext.class);
		}
		public AndExprContext andExpr(int i) {
			return getRuleContext(AndExprContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(YelpParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(YelpParser.OR, i);
		}
		public OrExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YelpListener ) ((YelpListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YelpListener ) ((YelpListener)listener).exitOrExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YelpVisitor ) return ((YelpVisitor<? extends T>)visitor).visitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrExprContext orExpr() throws RecognitionException {
		OrExprContext _localctx = new OrExprContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_orExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			andExpr();
			setState(23);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(19);
				match(OR);
				setState(20);
				andExpr();
				}
				}
				setState(25);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AndExprContext extends ParserRuleContext {
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(YelpParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(YelpParser.AND, i);
		}
		public AndExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YelpListener ) ((YelpListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YelpListener ) ((YelpListener)listener).exitAndExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YelpVisitor ) return ((YelpVisitor<? extends T>)visitor).visitAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndExprContext andExpr() throws RecognitionException {
		AndExprContext _localctx = new AndExprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_andExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			atom();
			setState(31);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(27);
				match(AND);
				setState(28);
				atom();
				}
				}
				setState(33);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public InContext in() {
			return getRuleContext(InContext.class,0);
		}
		public CategoryContext category() {
			return getRuleContext(CategoryContext.class,0);
		}
		public RatingContext rating() {
			return getRuleContext(RatingContext.class,0);
		}
		public PriceContext price() {
			return getRuleContext(PriceContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode LEFT() { return getToken(YelpParser.LEFT, 0); }
		public OrExprContext orExpr() {
			return getRuleContext(OrExprContext.class,0);
		}
		public TerminalNode RIGHT() { return getToken(YelpParser.RIGHT, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YelpListener ) ((YelpListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YelpListener ) ((YelpListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YelpVisitor ) return ((YelpVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_atom);
		try {
			setState(43);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IN:
				enterOuterAlt(_localctx, 1);
				{
				setState(34);
				in();
				}
				break;
			case CATEGORY:
				enterOuterAlt(_localctx, 2);
				{
				setState(35);
				category();
				}
				break;
			case RATING:
				enterOuterAlt(_localctx, 3);
				{
				setState(36);
				rating();
				}
				break;
			case PRICE:
				enterOuterAlt(_localctx, 4);
				{
				setState(37);
				price();
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 5);
				{
				setState(38);
				name();
				}
				break;
			case LEFT:
				enterOuterAlt(_localctx, 6);
				{
				setState(39);
				match(LEFT);
				setState(40);
				orExpr();
				setState(41);
				match(RIGHT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InContext extends ParserRuleContext {
		public TerminalNode IN() { return getToken(YelpParser.IN, 0); }
		public TerminalNode LEFT() { return getToken(YelpParser.LEFT, 0); }
		public TerminalNode STRING() { return getToken(YelpParser.STRING, 0); }
		public TerminalNode RIGHT() { return getToken(YelpParser.RIGHT, 0); }
		public InContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_in; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YelpListener ) ((YelpListener)listener).enterIn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YelpListener ) ((YelpListener)listener).exitIn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YelpVisitor ) return ((YelpVisitor<? extends T>)visitor).visitIn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InContext in() throws RecognitionException {
		InContext _localctx = new InContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_in);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(IN);
			setState(46);
			match(LEFT);
			setState(47);
			match(STRING);
			setState(48);
			match(RIGHT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CategoryContext extends ParserRuleContext {
		public TerminalNode CATEGORY() { return getToken(YelpParser.CATEGORY, 0); }
		public TerminalNode LEFT() { return getToken(YelpParser.LEFT, 0); }
		public TerminalNode STRING() { return getToken(YelpParser.STRING, 0); }
		public TerminalNode RIGHT() { return getToken(YelpParser.RIGHT, 0); }
		public CategoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_category; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YelpListener ) ((YelpListener)listener).enterCategory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YelpListener ) ((YelpListener)listener).exitCategory(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YelpVisitor ) return ((YelpVisitor<? extends T>)visitor).visitCategory(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CategoryContext category() throws RecognitionException {
		CategoryContext _localctx = new CategoryContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_category);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(CATEGORY);
			setState(51);
			match(LEFT);
			setState(52);
			match(STRING);
			setState(53);
			match(RIGHT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RatingContext extends ParserRuleContext {
		public TerminalNode RATING() { return getToken(YelpParser.RATING, 0); }
		public TerminalNode LEFT() { return getToken(YelpParser.LEFT, 0); }
		public TerminalNode STRING() { return getToken(YelpParser.STRING, 0); }
		public TerminalNode RIGHT() { return getToken(YelpParser.RIGHT, 0); }
		public RatingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rating; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YelpListener ) ((YelpListener)listener).enterRating(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YelpListener ) ((YelpListener)listener).exitRating(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YelpVisitor ) return ((YelpVisitor<? extends T>)visitor).visitRating(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RatingContext rating() throws RecognitionException {
		RatingContext _localctx = new RatingContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_rating);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(RATING);
			setState(56);
			match(LEFT);
			setState(57);
			match(STRING);
			setState(58);
			match(RIGHT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PriceContext extends ParserRuleContext {
		public TerminalNode PRICE() { return getToken(YelpParser.PRICE, 0); }
		public TerminalNode INEQ() { return getToken(YelpParser.INEQ, 0); }
		public TerminalNode NUM() { return getToken(YelpParser.NUM, 0); }
		public PriceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_price; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YelpListener ) ((YelpListener)listener).enterPrice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YelpListener ) ((YelpListener)listener).exitPrice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YelpVisitor ) return ((YelpVisitor<? extends T>)visitor).visitPrice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PriceContext price() throws RecognitionException {
		PriceContext _localctx = new PriceContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_price);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(PRICE);
			setState(61);
			match(INEQ);
			setState(62);
			match(NUM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(YelpParser.NAME, 0); }
		public TerminalNode LEFT() { return getToken(YelpParser.LEFT, 0); }
		public TerminalNode STRING() { return getToken(YelpParser.STRING, 0); }
		public TerminalNode RIGHT() { return getToken(YelpParser.RIGHT, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YelpListener ) ((YelpListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YelpListener ) ((YelpListener)listener).exitName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YelpVisitor ) return ((YelpVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(NAME);
			setState(65);
			match(LEFT);
			setState(66);
			match(STRING);
			setState(67);
			match(RIGHT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RootContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TerminalNode EOF() { return getToken(YelpParser.EOF, 0); }
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YelpListener ) ((YelpListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YelpListener ) ((YelpListener)listener).exitRoot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YelpVisitor ) return ((YelpVisitor<? extends T>)visitor).visitRoot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_root);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			atom();
			setState(70);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\16K\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\3\2"+
		"\7\2\30\n\2\f\2\16\2\33\13\2\3\3\3\3\3\3\7\3 \n\3\f\3\16\3#\13\3\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4.\n\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n"+
		"\3\n\3\n\3\n\2\2\13\2\4\6\b\n\f\16\20\22\2\2\2H\2\24\3\2\2\2\4\34\3\2"+
		"\2\2\6-\3\2\2\2\b/\3\2\2\2\n\64\3\2\2\2\f9\3\2\2\2\16>\3\2\2\2\20B\3\2"+
		"\2\2\22G\3\2\2\2\24\31\5\4\3\2\25\26\7\3\2\2\26\30\5\4\3\2\27\25\3\2\2"+
		"\2\30\33\3\2\2\2\31\27\3\2\2\2\31\32\3\2\2\2\32\3\3\2\2\2\33\31\3\2\2"+
		"\2\34!\5\6\4\2\35\36\7\4\2\2\36 \5\6\4\2\37\35\3\2\2\2 #\3\2\2\2!\37\3"+
		"\2\2\2!\"\3\2\2\2\"\5\3\2\2\2#!\3\2\2\2$.\5\b\5\2%.\5\n\6\2&.\5\f\7\2"+
		"\'.\5\16\b\2(.\5\20\t\2)*\7\5\2\2*+\5\2\2\2+,\7\6\2\2,.\3\2\2\2-$\3\2"+
		"\2\2-%\3\2\2\2-&\3\2\2\2-\'\3\2\2\2-(\3\2\2\2-)\3\2\2\2.\7\3\2\2\2/\60"+
		"\7\13\2\2\60\61\7\5\2\2\61\62\7\16\2\2\62\63\7\6\2\2\63\t\3\2\2\2\64\65"+
		"\7\f\2\2\65\66\7\5\2\2\66\67\7\16\2\2\678\7\6\2\28\13\3\2\2\29:\7\n\2"+
		"\2:;\7\5\2\2;<\7\16\2\2<=\7\6\2\2=\r\3\2\2\2>?\7\r\2\2?@\7\t\2\2@A\7\b"+
		"\2\2A\17\3\2\2\2BC\7\7\2\2CD\7\5\2\2DE\7\16\2\2EF\7\6\2\2F\21\3\2\2\2"+
		"GH\5\6\4\2HI\7\2\2\3I\23\3\2\2\2\5\31!-";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}