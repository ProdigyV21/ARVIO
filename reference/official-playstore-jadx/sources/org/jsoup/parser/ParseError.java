package org.jsoup.parser;

/* JADX INFO: loaded from: classes5.dex */
public class ParseError {
    private final String cursorPos;
    private final String errorMsg;
    private final int pos;

    public ParseError(CharacterReader characterReader, String str) {
        this.pos = characterReader.pos();
        this.cursorPos = characterReader.posLineCol();
        this.errorMsg = str;
    }

    public String getCursorPos() {
        return this.cursorPos;
    }

    public String getErrorMessage() {
        return this.errorMsg;
    }

    public int getPosition() {
        return this.pos;
    }

    public String toString() {
        return "<" + this.cursorPos + ">: " + this.errorMsg;
    }

    public ParseError(CharacterReader characterReader, String str, Object... objArr) {
        this.pos = characterReader.pos();
        this.cursorPos = characterReader.posLineCol();
        this.errorMsg = String.format(str, objArr);
    }

    public ParseError(int i10, String str) {
        this.pos = i10;
        this.cursorPos = String.valueOf(i10);
        this.errorMsg = str;
    }

    public ParseError(int i10, String str, Object... objArr) {
        this.pos = i10;
        this.cursorPos = String.valueOf(i10);
        this.errorMsg = String.format(str, objArr);
    }
}
