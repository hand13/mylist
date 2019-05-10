package com.hand13.lisp;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author $Author$
 * @version $Revision$ $Date$
 */
public class LispParser {
    //buffered inputstream
    private InputStream in;
    private int look;
    private String token;

    private List context= null;

    private boolean isParen(String str) {
        return str.equals("(") || str.equals(")");
    }

    public LispParser(InputStream in) {
        this.in = in;
        look = read();
        getToken();
        context  = new List();
}

    private int read() {
        int m = -1;
        try {
            m = in.read();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return m;
    }

    private boolean isParen(int c) {
        return c == '(' || c == ')';
    }

    private boolean isSpace(int c) {
        return c == '\t' || c == '\n' || c == ' ';
    }

    public void getToken() {
        String str = null;
        if (isParen(look)) {
            str = Character.toString((char) look);
            look = read();
        }else if (isSpace(look)) {
            look = read();
            getToken();
            return;
        }else if(look <0){
            token = null;
        }
        else {
            str = getNextString();
        }
        token = str;
    }

    private String getNextString() {
        StringBuilder str = new StringBuilder();
        if(look == '"') {
            str.append(look);
            do{
                look = read();
                str.append((char) look);
            }while (look != '"');

        }else {
            while(!isSpace(look) && !isParen(look)) {
                str.append((char) look);
                look = read();
            }
        }
        return str.toString();
    }

    private Object getObject() {
        Object value = null;
        if(token == null || token.equals(")")) {
        }else {
            if(token.equals("(")) {
                getToken();
                value = list();
            }else {
                value = token;
            }
        }
        getToken();
        return value;
    }

    public List getList() {
        return (List)this.getObject();
    }


    private List list(){
        List list = null;
        List current = null;
        Object value = getObject();
        while (value != null) {
            List tmp = new List();
            tmp.value = value;
            if(list != null) {
                current.next = tmp;
                current = tmp;
            }else {
                current = list = tmp;
            }
            value = getObject();
        }
        return list;
    }

}
