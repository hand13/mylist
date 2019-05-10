package com.hand13.lisp;

/**
 * @author $Author$
 * @version $Revision$ $Date$
 */
@SuppressWarnings("ALL")
public class Eval {
    public static Object eval(Object o) {
        if (o == null) {
            return null;
        } else {
            if (o instanceof List) {
                List m = (List) o;
                if (isOpt( m.value)) {
                    // 求值list
                    if (m.value.equals("+")) {
                        return add(m.next);
                    }if (m.value.equals("-")) {
                        return sub(m.next);
                    }
                    else {
                        return m;
                    }
                } else {
                    //非求值list
                    return m;
                }
            } else {
                //值
                return Integer.valueOf((String) o);
            }
        }
    }

    private static boolean isOpt(Object o) {
        return "+".equals(o) || "-".equals(o) || "*".equals(o) || "/".equals(o);
    }

    private static Integer add(List rest) {
        Object m = eval(rest);
        int sum = 0;
        if (m instanceof Integer) {
            return (Integer) m;
        } else if (m instanceof List) {
            sum += (Integer) eval(((List) m).value);
            sum += add(((List) m).next);
        }
        return sum;
    }

    private static Integer sub(List rest) {
        Object m = eval(rest);
        int sum = 0;
        if (m instanceof Integer) {
            return sum -(Integer) m;
        } else if (m instanceof List) {
            sum -= (Integer) eval(((List) m).value);
            sum -= sub(((List) m).next);
        }
        return sum;
    }
}
