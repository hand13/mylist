package com.hand13.lisp;

import java.io.InputStream;

/**
 * @author $Author$
 * @version $Revision$ $Date$
 */
public class LispLexer extends Lexer {

    private Element lookElement;

    private boolean backElement = false;

    public void goBackElement() {
        backElement = true;
    }

    public LispLexer(InputStream in) {
        super(in);
    }

    public Element getNextElement() {
        if(backElement){
            backElement = false;
            return lookElement;
        }
        char m = this.getNextClear();
        if (m == 0) {
            return null;
        }
        Element element = new Element();
        switch (m) {
            case '(':
                element.type = LispTokenType.LBRA;
                break;
            case ')':
                element.type = LispTokenType.RBRA;
                break;
            case '+':
                element.type = LispTokenType.ADD;
                break;
            case '-':
                element.type = LispTokenType.SUB;
                break;
            case '*':
                element.type = LispTokenType.MUL;
                break;
            case '/':
                element.type = LispTokenType.DIV;
                break;
            case '"':
                element.type = LispTokenType.QUO;
                StringBuilder str = new StringBuilder();
                m = this.getNextChar();
                while(m != '"' && m != 0) {
                    str.append(m);
                    m = this.getNextChar();
                }
                this.goback();
                element.value = str.toString();
                break;
            default:
                element.type = LispTokenType.STRING;
                StringBuilder s = new StringBuilder();
                while(Character.isDigit(m) || Character.isLetter(m)) {
                    s.append(m);
                    m = this.getNextChar();
                }
                this.goback();
                element.value = s.toString();
                break;
        }
        lookElement = element;
        return element;
    }
}
