package ca.ece.ubc.cpen221.mp5;
// Generated from C:/Users/Miles/Desktop/CPEN_221/f17-mp5-mjustice_bjury/src/main/antlr\Yelp.g4 by ANTLR 4.7
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class YelpLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OR=1, AND=2, LEFT=3, RIGHT=4, NAME=5, NUM=6, INEQ=7, RATING=8, IN=9, CATEGORY=10, 
		PRICE=11, STRING=12;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"OR", "AND", "LEFT", "RIGHT", "NAME", "NUM", "INEQ", "RATING", "IN", "CATEGORY", 
		"PRICE", "STRING"
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


	public YelpLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Yelp.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\16V\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\7\6\7,\n\7\r\7\16\7-\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\67\n"+
		"\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\6\rS\n\r\r\r\16\rT\2\2"+
		"\16\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\3\2\4\3\2"+
		"\62;\6\2\"\"))C\\c|\2[\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\3\33\3\2\2\2\5\36\3\2\2\2\7!\3\2\2"+
		"\2\t#\3\2\2\2\13%\3\2\2\2\r+\3\2\2\2\17\66\3\2\2\2\218\3\2\2\2\23?\3\2"+
		"\2\2\25B\3\2\2\2\27K\3\2\2\2\31R\3\2\2\2\33\34\7~\2\2\34\35\7~\2\2\35"+
		"\4\3\2\2\2\36\37\7(\2\2\37 \7(\2\2 \6\3\2\2\2!\"\7*\2\2\"\b\3\2\2\2#$"+
		"\7+\2\2$\n\3\2\2\2%&\7p\2\2&\'\7c\2\2\'(\7o\2\2()\7g\2\2)\f\3\2\2\2*,"+
		"\t\2\2\2+*\3\2\2\2,-\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\16\3\2\2\2/\67\7@\2"+
		"\2\60\61\7@\2\2\61\67\7?\2\2\62\67\7>\2\2\63\64\7>\2\2\64\67\7?\2\2\65"+
		"\67\7?\2\2\66/\3\2\2\2\66\60\3\2\2\2\66\62\3\2\2\2\66\63\3\2\2\2\66\65"+
		"\3\2\2\2\67\20\3\2\2\289\7t\2\29:\7c\2\2:;\7v\2\2;<\7k\2\2<=\7p\2\2=>"+
		"\7i\2\2>\22\3\2\2\2?@\7k\2\2@A\7p\2\2A\24\3\2\2\2BC\7e\2\2CD\7c\2\2DE"+
		"\7v\2\2EF\7g\2\2FG\7i\2\2GH\7q\2\2HI\7t\2\2IJ\7{\2\2J\26\3\2\2\2KL\7r"+
		"\2\2LM\7t\2\2MN\7k\2\2NO\7e\2\2OP\7g\2\2P\30\3\2\2\2QS\t\3\2\2RQ\3\2\2"+
		"\2ST\3\2\2\2TR\3\2\2\2TU\3\2\2\2U\32\3\2\2\2\6\2-\66T\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}