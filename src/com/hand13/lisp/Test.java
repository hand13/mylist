package com.hand13.lisp;

import java.io.StringBufferInputStream;

/**
 * @version $Revision$ $Date$
 * @author $Author$
*/
public class Test {
    public static void main(String[] args) {
        LispParser parser = new LispParser(new StringBufferInputStream("(+1 (- 2 3) 4 (+ 5 6))"));
        LispList lispList = parser.list();
        Node node = Eval.eval(lispList);
        System.out.println(node.value);
    }
}
