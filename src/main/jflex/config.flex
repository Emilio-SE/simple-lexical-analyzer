package org.lexicalAnalyzer;

import java.io.*;

%%
%public
%class LexicalAnalyzer

// Palabras reservadas para Pascal
reservedWords = PROGRAM|VAR|CONST|TYPE|FUNCTION|
                     PROCEDURE|BEGIN|END|ARRAY|OF|
                     RECORD|IF|THEN|ELSE|WHILE|DO|REPEAT|
                     UNTIL|FOR|TO|DOWNTO|WRITE|WRITELN|
                     READ|READLN|USES|INTEGER|REAL|CHAR|
                     BOOLEAN|STRING|CASE|FILE|GOTO|IN|
                     LABEL|NIL|PACKED|SET|UNIT|WITH|ABSOLUTE|
                     DESTRUCTOR|CONSTRUCTOR|EXPORTS|FINALLY|
                     INITIALIZATION|IMPLEMENTATION|INTERFACE|
                     LOCAL|MODULE|OBJECT|INLINE|INHERITED|
                     SELF|ENUMERATOR|EXPORT|IMPLEMENTS|NORETURN|
                     PUBLIC|PROTECTED|PRIVATE|PROPERTY|RAISE
reservedWordsMin = program|var|const|type|function|
                        procedure|begin|end|array|of|
                        record|if|then|else|while|do|repeat|
                        until|for|to|downto|write|writeln|
                        read|readln|uses|integer|real|char|
                        boolean|string|case|file|goto|in|
                        label|nil|packed|set|unit|with|absolute|
                        destructor|constructor|exports|finally|
                        initialization|implementation|interface|
                        local|module|object|inline|inherited|
                        self|enumerator|export|implements|noreturn|
                        public|protected|private|property|raise

logicalOperators = [aA][nN][dD]|[oO][rR]|[nN][oO][tT]|[xX][oO][rR]
arithmeticOperators = [+\-\/*]|[mM][oO][dD]|[dD][iI][vV]
relationalOperator = [<>]|<=|>=|=|<>
assignmentOperator = :=
bitwiseOperator = [sS][hH][rR]|[sS][hH][lL]
groupingCharacter = \( | \) | \[ | \]
specialCharacter = [;,.:]|[.]{2}
literalString = '[^']*'
comments = \(\*[\s\S]*?\*\) | \{[\s\S]*?\} | \/\/.*
identifier = [a-zA-Z][a-zA-Z0-9_]*
integer = -?[0-9]+
floatingPoint = -?[0-9]+\.[0-9]+
space = [ \t\n\r\f]

%type Token

%eofval{
    return new Token(TokenType.EOF, null);
%eofval}

%%

{reservedWords} | {reservedWordsMin} { return new Token(TokenType.RESERVED_WORD, yytext()); }
{logicalOperators} { return new Token(TokenType.LOGICAL_OPERATOR, yytext()); }
{arithmeticOperators} { return new Token(TokenType.ARITHMETIC_OPERATOR, yytext()); }
{relationalOperator} { return new Token(TokenType.RELATIONAL_OPERATOR, yytext()); }
{groupingCharacter} { return new Token(TokenType.GROUPING_CHARACTER, yytext()); }
{assignmentOperator} { return new Token(TokenType.ASSIGNMENT_OPERATOR, yytext()); }
{bitwiseOperator} { return new Token(TokenType.BITWISE_OPERATOR, yytext()); }
{literalString} { return new Token(TokenType.LITERAL_STRING, yytext()); }
{specialCharacter} { return new Token(TokenType.SPECIAL_CHARACTER, yytext()); }
{comments} { return new Token(TokenType.COMMENT, yytext()); }
{identifier} { return new Token(TokenType.IDENTIFIER, yytext()); }
{integer} { return new Token(TokenType.INTEGER, yytext()); }
{floatingPoint} { return new Token(TokenType.FLOATING_POINT, yytext()); }
{space} { /* ignore */ }
[^] { return new Token(TokenType.ERROR, yytext()); }