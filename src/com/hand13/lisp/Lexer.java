package com.hand13.lisp;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author $Author$
 * @version $Revision$ $Date$
 */
public abstract class Lexer {
    private InputStream in;

    private char lastCharacter;

    private boolean last = false;

    public Lexer(InputStream in){
        this.in = in;
    }

    protected void goback(){
        last = true;
    }
    protected char getNextChar() {
        int m = 0;
        if(!last) {
            try {
                m = in.read();
                if (m <= 0) {
                    return 0;
                }
                lastCharacter = (char)m;
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }else {
            last = false;
        }
        return lastCharacter;
    }

    protected char getNextClear() {
        char w;
        do {
            w = this.getNextChar();
        } while (Character.isSpaceChar(w));
        return w;
    }
}
