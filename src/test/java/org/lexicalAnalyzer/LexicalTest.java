package org.lexicalAnalyzer;

import org.junit.Test;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class LexicalTest {

    @Test
    public void matchIdentifier(){
        String input = "nombre nombre1 mi_nombre miNombre";
        Reader stringReader = new StringReader(input);
        LexicalAnalyzer lexer = new LexicalAnalyzer(stringReader);
        try {
            while (true) {
                Token token = lexer.yylex();
                if (token.getTokenType() == TokenType.EOF) {
                    break;
                }
                System.out.println(token);
                assertEquals(TokenType.IDENTIFIER, token.getTokenType());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void matchInteger(){
        String input = "-1 0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610";
        Reader stringReader = new StringReader(input);
        LexicalAnalyzer lexer = new LexicalAnalyzer(stringReader);
        try {
            while (true) {
                Token token = lexer.yylex();
                if (token.getTokenType() == TokenType.EOF) {
                    break;
                }
                System.out.println(token);
                assertEquals(TokenType.INTEGER, token.getTokenType());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void matchFloatingPoint(){
        String input = "-1.1 0.1 1.1 2.2 3.3 5.5 8.8 13.13 21.21 34.34 55.55 89.89 144.144 233.233 377.377 610.610";
        Reader stringReader = new StringReader(input);
        LexicalAnalyzer lexer = new LexicalAnalyzer(stringReader);
        try {
            while (true) {
                Token token = lexer.yylex();
                if (token.getTokenType() == TokenType.EOF) {
                    break;
                }
                System.out.println(token);
                assertEquals(TokenType.FLOATING_POINT, token.getTokenType());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void matchArithmeticOperator(){
        String input = "+ - * / DIV MOD";
        Reader stringReader = new StringReader(input);
        LexicalAnalyzer lexer = new LexicalAnalyzer(stringReader);
        try {
            while (true) {
                Token token = lexer.yylex();
                if (token.getTokenType() == TokenType.EOF) {
                    break;
                }
                System.out.println(token);
                assertEquals(TokenType.ARITHMETIC_OPERATOR, token.getTokenType());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void matchRelationalOperator(){
        String input = "< > <= >= = <>";
        Reader stringReader = new StringReader(input);
        LexicalAnalyzer lexer = new LexicalAnalyzer(stringReader);
        try {
            while (true) {
                Token token = lexer.yylex();
                if (token.getTokenType() == TokenType.EOF) {
                    break;
                }
                System.out.println(token);
                assertEquals(TokenType.RELATIONAL_OPERATOR, token.getTokenType());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void matchLogicalOperator(){
        String input = "AND OR NOT XOR";
        Reader stringReader = new StringReader(input);
        LexicalAnalyzer lexer = new LexicalAnalyzer(stringReader);
        try {
            while (true) {
                Token token = lexer.yylex();
                if (token.getTokenType() == TokenType.EOF) {
                    break;
                }
                System.out.println(token);
                assertEquals(TokenType.LOGICAL_OPERATOR, token.getTokenType());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void matchAssignmnetOperator(){
        String input = ":=";
        Reader stringReader = new StringReader(input);
        LexicalAnalyzer lexer = new LexicalAnalyzer(stringReader);
        try {
            while (true) {
                Token token = lexer.yylex();

                if (token.getTokenType() == TokenType.EOF) {
                    break;
                }
                System.out.println(token);
                assertEquals(TokenType.ASSIGNMENT_OPERATOR, token.getTokenType());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void matchBitwiseOperator(){
        String input = "SHL SHR";
        Reader stringReader = new StringReader(input);
        LexicalAnalyzer lexer = new LexicalAnalyzer(stringReader);
        try {
            while (true){
                Token token = lexer.yylex();
                if (token.getTokenType() == TokenType.EOF){
                    break;
                }
                System.out.println(token);
                assertEquals(TokenType.BITWISE_OPERATOR, token.getTokenType());
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void matchGroupingCharacter(){
        String input = "( ) [ ]";
        Reader stringReader = new StringReader(input);
        LexicalAnalyzer lexer = new LexicalAnalyzer(stringReader);
        try {
            while (true) {
                Token token = lexer.yylex();
                if (token.getTokenType() == TokenType.EOF) {
                    break;
                }
                System.out.println(token);
                assertEquals(TokenType.GROUPING_CHARACTER, token.getTokenType());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void matchLiteralString(){
        String input = "'' 'Hola Mundo13' 'Hola Mundo' 'Una cadena :0'";
        Reader stringReader = new StringReader(input);
        LexicalAnalyzer lexer = new LexicalAnalyzer(stringReader);
        try {
            while (true) {
                Token token = lexer.yylex();
                if (token.getTokenType() == TokenType.EOF) {
                    break;
                }
                System.out.println(token);
                assertEquals(TokenType.LITERAL_STRING, token.getTokenType());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void matchComment(){
        String input = "{un tipo de comentario :3} (*Otro tipo de comentario >:0*)";
        Reader stringReader = new StringReader(input);
        LexicalAnalyzer lexer = new LexicalAnalyzer(stringReader);
        try {
            while (true) {
                Token token = lexer.yylex();
                if (token.getTokenType() == TokenType.EOF) {
                    break;
                }
                System.out.println(token);
                assertEquals(TokenType.COMMENT, token.getTokenType());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void noMatch(){
        String input = "@ | °";
        Reader stringReader = new StringReader(input);
        LexicalAnalyzer lexer = new LexicalAnalyzer(stringReader);
        try {
            while(true){
                Token token = lexer.yylex();
                if (token.getTokenType() == TokenType.EOF){
                    break;
                }
                System.out.println(token);
                assertEquals(TokenType.ERROR, token.getTokenType());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
