/*
//<orExpr> ::= <andExpr>(<or><andExpr>)*
//<andExpr> ::= <atom>(<and><atom>)*
//<atom> ::= <in>|<category>|<rating>|<price>|<name>|<LParen><orExpr><RParen>
//<or> ::= "||"
//<and> ::= "&&"
//ineq ::= <gt>|<gte>|<lt>|<lte>|<eq>
//<gt> ::= ">"
//<gte> ::= ">="
//<lt> ::= "<"
//<lte> ::= "<="
//<eq> ::= "="
//<num> ::= [1-5]
//<in> ::= "in" <LParen><string><RParen>
//<category> ::= "category" <LParen><string><RParen>
//<name> ::= "name" <LParen><string><RParen>
//<rating> ::= "rating" <ineq><num>
//<price> ::= "price" <ineq><num>
//<LParen> ::= "("
//<RParen> ::= ")"
*/

grammar Yelp;

/*
 * Parser rules
 */

orExpr : andExpr(OR andExpr)*;
andExpr : atom(AND atom)*;
atom : in|category|rating|price|name|LEFT orExpr RIGHT;
in : IN LEFT STRING RIGHT;
category : CATEGORY LEFT STRING RIGHT;
rating : RATING LEFT STRING RIGHT;
price : PRICE INEQ NUM;
name : NAME LEFT STRING RIGHT;
root : atom EOF;

 /*
  * Lexer rules
  */

OR : '||';
AND : '&&';
LEFT : '(';
RIGHT : ')';

NAME : 'name';
NUM : [0-9]+;
INEQ :  '>'|'>='|'<'|'<='|'=';
RATING : 'rating';
IN : 'in';
CATEGORY : 'category';
PRICE : 'price';

STRING : [A-Za-z' ']+;