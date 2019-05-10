package com.hand13.lisp;
/**
 * @version $Revision$ $Date$
 * @author $Author$
*/
public class List {
    Object value;
    List next;

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("(");
        if(value != null) {
            str.append(value);
        }
        if(next!= null) {
            str.append(" ").append(next.toString());
        }
        str.append(")");
        return str.toString();
    }
}
