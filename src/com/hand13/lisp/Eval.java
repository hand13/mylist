package com.hand13.lisp;
/**
 * @version $Revision$ $Date$
 * @author $Author$
*/
public class Eval {
    public static Object eval(Object o) {
        if(o == null) {
            return null;
        }
        if(o instanceof List) {
            List list = (List)o;
            if (list.next == null) {
                return eval(list.value);
            }
            return eval(list.value, list.next);
        }else {
            if(o instanceof String) {
                return derString((String)o);
            }
            return o;
        }
    }

    private static Object eval(Object op,List child) {
        Object result = null;
        if(op.equals("+")) {
            result = add(child);
        }
        return result;
    }

    private static Object add(List child) {
        if(child == null ){
            return 0;
        }
        int left = 0;
        if(child.value != null) {
           left = (Integer) eval(child.value);
        }

        int right =  0;
        if(child.next != null) {
            List next = child.next;
            Object result = eval(next);
            right = (Integer)result;
        }
        return right+left;
    }

    private static Object derString(String str) {
        // for now
        return Integer.valueOf(str);
//        return str;
    }
}
