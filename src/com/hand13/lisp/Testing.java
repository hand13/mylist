package com.hand13.lisp;

import java.io.StringBufferInputStream;

/**
 * @version $Revision$ $Date$
 * @author $Author$
*/
public class Testing {
    public static void main(String[] args) {
        LispParser parser = new LispParser(new StringBufferInputStream("(+ 1 (+ 2 3  ) ( + 100 100) )"));
        List list = parser.getList();
        System.out.println(list);
        System.out.println(Eval.eval(list));
    }
}
