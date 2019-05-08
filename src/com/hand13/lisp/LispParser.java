package com.hand13.lisp;

import java.io.InputStream;

/**
 * @author $Author$
 * @version $Revision$ $Date$
 */
public class LispParser {
    private LispLexer lexer;

    public LispParser(InputStream tokenStream) {
        lexer = new LispLexer(tokenStream);
    }

    public LispList list() {
        Element e = this.lexer.getNextElement();
        if (e == null) {
            return null;
        }
        if (e.type == LispTokenType.RBRA) {
            throw new RuntimeException("should not be r");
        }
        if (e.type != LispTokenType.LBRA) {
            return this.innerList(e);
        }
        LispList lispList = new LispList();
        Node node;
        Element element = lexer.getNextElement();
        if (element.type == LispTokenType.LBRA) {
            lexer.goBackElement();
            node = this.list();
        } else {
            node = new Node();
            node.element = element;
        }
        lispList.node = node;
        lispList.next = this.list();
        if (lexer.getNextElement().type != LispTokenType.RBRA) {
           LispList nextList = new LispList();
           nextList.node = lispList.next;
           nextList.next = this.list();
           lispList.next = nextList;
        }
        return lispList;
    }

    private LispList innerList(Element firstElement) {
        LispList lispList = new LispList();
        Node node = new Node();
        node.element = firstElement;
        lispList.node = node;
        Element nextElement = lexer.getNextElement();
        lexer.goBackElement();
        if (nextElement.type == LispTokenType.RBRA) {
            lispList.next = null;
        } else {
            lispList.next = this.list();
        }
        return lispList;
    }

}
