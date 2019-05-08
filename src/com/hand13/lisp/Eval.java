package com.hand13.lisp;

/**
 * @author $Author$
 * @version $Revision$ $Date$
 */
public class Eval {
    public static Node eval(LispList lispList) {
        if(lispList == null) {
            return null;
        }
        Node node = lispList.node;
        if (node instanceof LispList) {
            lispList.node = eval((LispList) node);
            return eval(lispList);
        } else {
            Element e = node.element;
            switch (e.type) {
                case ADD:
                    int m = 0;
                    LispList cdr = lispList.next;
                    while (cdr != null) {
                        // for now
                        Object value = cdr.node.value = getValue(cdr.node.element.value, Integer.class);
                        if (value == null) {
                            continue;
                        }
                        m += (Integer) value;
                        cdr = cdr.next;
                    }
                    Node n = new Node();
                    n.value = m;
                    return n;
            }
        }
        return null;
    }

    private static Object getValue(String str, Class<?> ts) {
        if (ts == Integer.class) {
            return Integer.valueOf(str);
        }
        return null;
    }
}
