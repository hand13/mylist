package com.hand13.lisp;

import java.io.StringBufferInputStream;

/**
 * @version $Revision$ $Date$
 * @author $Author$
*/
public class Testing {
    public static void main(String[] args) {
        LispParser parser = new LispParser(new StringBufferInputStream("(+ 12 (+ 1 2)"));
        List list = parser.list();
        System.out.println(list);
        System.out.println(Eval.eval(list));
    }
}
