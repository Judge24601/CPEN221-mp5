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

orExpr : andExpr (OR andExpr)*;
andExpr : atom (AND atom)*;
atom : (in|category|rating|price|name|LEFT orExpr RIGHT);
in : IN LEFT STRING RIGHT;
category : CATEGORY LEFT STRING RIGHT;
rating : RATING LEFT STRING RIGHT;
price : PRICE INEQ NUM;
name : NAME LEFT STRING RIGHT;
root : orExpr EOF;

 /*
  * Lexer rules
  */

OR : '||';
AND : '&&';
RATING : 'rating';
IN : 'in';
NAME : 'name';
CATEGORY : 'category';
PRICE : 'price';
LEFT : '(';
RIGHT : ')';
INEQ :  '>'|'>='|'<'|'<='|'=';
NUM : [1-5];
STRING : [A-Za-z0-9]+ WHT* [A-Za-z0-9]+ ;
WHT : [ \t\n\r]+ -> skip;


