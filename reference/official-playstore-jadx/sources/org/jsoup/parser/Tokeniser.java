package org.jsoup.parser;

import androidx.media3.extractor.ts.TsExtractor;
import java.util.Arrays;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Entities;
import org.jsoup.parser.Token;

/* JADX INFO: loaded from: classes5.dex */
final class Tokeniser {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int Unset = -1;
    private static final char[] notCharRefCharsSorted;
    static final char replacementChar = 65533;
    static final int[] win1252Extensions = {8364, TsExtractor.TS_STREAM_TYPE_AC3, 8218, 402, 8222, 8230, 8224, 8225, 710, 8240, 352, 8249, 338, 141, 381, 143, 144, 8216, 8217, 8220, 8221, 8226, 8211, 8212, 732, 8482, 353, 8250, 339, 157, 382, 376};
    static final int win1252ExtensionsStart = 128;
    final Token.EndTag endPending;
    private final ParseErrorList errors;
    private String lastStartCloseSeq;
    private String lastStartTag;
    private int markupStartPos;
    private final CharacterReader reader;
    final Token.StartTag startPending;
    Token.Tag tagPending;
    private TokeniserState state = TokeniserState.Data;
    private Token emitPending = null;
    private boolean isEmitPending = false;
    private String charsString = null;
    private final StringBuilder charsBuilder = new StringBuilder(1024);
    final StringBuilder dataBuffer = new StringBuilder(1024);
    final Token.Character charPending = new Token.Character();
    final Token.Doctype doctypePending = new Token.Doctype();
    final Token.Comment commentPending = new Token.Comment();
    private int charStartPos = -1;
    private final int[] codepointHolder = new int[1];
    private final int[] multipointHolder = new int[2];

    /* JADX INFO: renamed from: org.jsoup.parser.Tokeniser$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jsoup$parser$TokeniserState;

        static {
            int[] iArr = new int[TokeniserState.values().length];
            $SwitchMap$org$jsoup$parser$TokeniserState = iArr;
            try {
                iArr[TokeniserState.TagOpen.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jsoup$parser$TokeniserState[TokeniserState.Data.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    static {
        char[] cArr = {'\t', '\n', '\r', '\f', ' ', '<', '&'};
        notCharRefCharsSorted = cArr;
        Arrays.sort(cArr);
    }

    public Tokeniser(TreeBuilder treeBuilder) {
        Token.StartTag startTag = new Token.StartTag(treeBuilder);
        this.startPending = startTag;
        this.tagPending = startTag;
        this.endPending = new Token.EndTag(treeBuilder);
        this.reader = treeBuilder.reader;
        this.errors = treeBuilder.parser.getErrors();
    }

    private void characterReferenceError(String str, Object... objArr) {
        if (this.errors.canAddError()) {
            this.errors.add(new ParseError(this.reader, String.format("Invalid character reference: " + str, objArr)));
        }
    }

    public static boolean currentNodeInHtmlNS() {
        return true;
    }

    public void advanceTransition(TokeniserState tokeniserState) {
        transition(tokeniserState);
        this.reader.advance();
    }

    public String appropriateEndTagName() {
        return this.lastStartTag;
    }

    public String appropriateEndTagSeq() {
        if (this.lastStartCloseSeq == null) {
            this.lastStartCloseSeq = "</" + this.lastStartTag;
        }
        return this.lastStartCloseSeq;
    }

    public int[] consumeCharacterReference(Character ch, boolean z) {
        int iIntValue;
        if (this.reader.isEmpty()) {
            return null;
        }
        if ((ch != null && ch.charValue() == this.reader.current()) || this.reader.matchesAnySorted(notCharRefCharsSorted)) {
            return null;
        }
        int[] iArr = this.codepointHolder;
        this.reader.mark();
        if (this.reader.matchConsume("#")) {
            boolean zMatchConsumeIgnoreCase = this.reader.matchConsumeIgnoreCase("X");
            CharacterReader characterReader = this.reader;
            String strConsumeHexSequence = zMatchConsumeIgnoreCase ? characterReader.consumeHexSequence() : characterReader.consumeDigitSequence();
            if (strConsumeHexSequence.length() == 0) {
                characterReferenceError("numeric reference with no numerals", new Object[0]);
                this.reader.rewindToMark();
                return null;
            }
            this.reader.unmark();
            if (!this.reader.matchConsume(";")) {
                characterReferenceError("missing semicolon on [&#%s]", strConsumeHexSequence);
            }
            try {
                iIntValue = Integer.valueOf(strConsumeHexSequence, zMatchConsumeIgnoreCase ? 16 : 10).intValue();
            } catch (NumberFormatException unused) {
                iIntValue = -1;
            }
            if (iIntValue == -1 || iIntValue > 1114111) {
                characterReferenceError("character [%s] outside of valid range", Integer.valueOf(iIntValue));
                iArr[0] = 65533;
            } else {
                if (iIntValue >= 128) {
                    int[] iArr2 = win1252Extensions;
                    if (iIntValue < iArr2.length + 128) {
                        characterReferenceError("character [%s] is not a valid unicode code point", Integer.valueOf(iIntValue));
                        iIntValue = iArr2[iIntValue - 128];
                    }
                }
                iArr[0] = iIntValue;
            }
            return iArr;
        }
        String strConsumeLetterThenDigitSequence = this.reader.consumeLetterThenDigitSequence();
        boolean zMatches = this.reader.matches(';');
        if (!Entities.isBaseNamedEntity(strConsumeLetterThenDigitSequence) && (!Entities.isNamedEntity(strConsumeLetterThenDigitSequence) || !zMatches)) {
            this.reader.rewindToMark();
            if (zMatches) {
                characterReferenceError("invalid named reference [%s]", strConsumeLetterThenDigitSequence);
            }
            return null;
        }
        if (z && (this.reader.matchesLetter() || this.reader.matchesDigit() || this.reader.matchesAny('=', '-', '_'))) {
            this.reader.rewindToMark();
            return null;
        }
        this.reader.unmark();
        if (!this.reader.matchConsume(";")) {
            characterReferenceError("missing semicolon on [&%s]", strConsumeLetterThenDigitSequence);
        }
        int iCodepointsForName = Entities.codepointsForName(strConsumeLetterThenDigitSequence, this.multipointHolder);
        if (iCodepointsForName == 1) {
            iArr[0] = this.multipointHolder[0];
            return iArr;
        }
        if (iCodepointsForName == 2) {
            return this.multipointHolder;
        }
        Validate.fail("Unexpected characters returned for " + strConsumeLetterThenDigitSequence);
        return this.multipointHolder;
    }

    public void createBogusCommentPending() {
        this.commentPending.reset();
        this.commentPending.bogus = true;
    }

    public void createCommentPending() {
        this.commentPending.reset();
    }

    public void createDoctypePending() {
        this.doctypePending.reset();
    }

    public Token.Tag createTagPending(boolean z) {
        Token.Tag tagReset = z ? this.startPending.reset() : this.endPending.reset();
        this.tagPending = tagReset;
        return tagReset;
    }

    public void createTempBuffer() {
        Token.reset(this.dataBuffer);
    }

    public void emit(Token token) {
        Validate.isFalse(this.isEmitPending);
        this.emitPending = token;
        this.isEmitPending = true;
        token.startPos(this.markupStartPos);
        token.endPos(this.reader.pos());
        this.charStartPos = -1;
        Token.TokenType tokenType = token.type;
        if (tokenType == Token.TokenType.StartTag) {
            this.lastStartTag = ((Token.StartTag) token).tagName;
            this.lastStartCloseSeq = null;
        } else if (tokenType == Token.TokenType.EndTag) {
            Token.EndTag endTag = (Token.EndTag) token;
            if (endTag.hasAttributes()) {
                error("Attributes incorrectly present on end tag [/%s]", endTag.normalName());
            }
        }
    }

    public void emitCommentPending() {
        emit(this.commentPending);
    }

    public void emitDoctypePending() {
        emit(this.doctypePending);
    }

    public void emitTagPending() {
        this.tagPending.finaliseTag();
        emit(this.tagPending);
    }

    public void eofError(TokeniserState tokeniserState) {
        if (this.errors.canAddError()) {
            this.errors.add(new ParseError(this.reader, "Unexpectedly reached end of file (EOF) in input state [%s]", tokeniserState));
        }
    }

    public void error(TokeniserState tokeniserState) {
        if (this.errors.canAddError()) {
            ParseErrorList parseErrorList = this.errors;
            CharacterReader characterReader = this.reader;
            parseErrorList.add(new ParseError(characterReader, "Unexpected character '%s' in input state [%s]", Character.valueOf(characterReader.current()), tokeniserState));
        }
    }

    public TokeniserState getState() {
        return this.state;
    }

    public boolean isAppropriateEndTagToken() {
        return this.lastStartTag != null && this.tagPending.name().equalsIgnoreCase(this.lastStartTag);
    }

    public Token read() {
        while (!this.isEmitPending) {
            this.state.read(this, this.reader);
        }
        StringBuilder sb2 = this.charsBuilder;
        if (sb2.length() != 0) {
            String string = sb2.toString();
            sb2.delete(0, sb2.length());
            Token.Character characterData = this.charPending.data(string);
            this.charsString = null;
            return characterData;
        }
        String str = this.charsString;
        if (str == null) {
            this.isEmitPending = false;
            return this.emitPending;
        }
        Token.Character characterData2 = this.charPending.data(str);
        this.charsString = null;
        return characterData2;
    }

    public void transition(TokeniserState tokeniserState) {
        int i10 = AnonymousClass1.$SwitchMap$org$jsoup$parser$TokeniserState[tokeniserState.ordinal()];
        if (i10 == 1) {
            this.markupStartPos = this.reader.pos();
        } else if (i10 == 2 && this.charStartPos == -1) {
            this.charStartPos = this.reader.pos();
        }
        this.state = tokeniserState;
    }

    public String unescapeEntities(boolean z) {
        StringBuilder sbBorrowBuilder = StringUtil.borrowBuilder();
        while (!this.reader.isEmpty()) {
            sbBorrowBuilder.append(this.reader.consumeTo('&'));
            if (this.reader.matches('&')) {
                this.reader.consume();
                int[] iArrConsumeCharacterReference = consumeCharacterReference(null, z);
                if (iArrConsumeCharacterReference == null || iArrConsumeCharacterReference.length == 0) {
                    sbBorrowBuilder.append('&');
                } else {
                    sbBorrowBuilder.appendCodePoint(iArrConsumeCharacterReference[0]);
                    if (iArrConsumeCharacterReference.length == 2) {
                        sbBorrowBuilder.appendCodePoint(iArrConsumeCharacterReference[1]);
                    }
                }
            }
        }
        return StringUtil.releaseBuilder(sbBorrowBuilder);
    }

    public void error(String str) {
        if (this.errors.canAddError()) {
            this.errors.add(new ParseError(this.reader, str));
        }
    }

    public void error(String str, Object... objArr) {
        if (this.errors.canAddError()) {
            this.errors.add(new ParseError(this.reader, str, objArr));
        }
    }

    public void emit(String str) {
        if (this.charsString == null) {
            this.charsString = str;
        } else {
            if (this.charsBuilder.length() == 0) {
                this.charsBuilder.append(this.charsString);
            }
            this.charsBuilder.append(str);
        }
        this.charPending.startPos(this.charStartPos);
        this.charPending.endPos(this.reader.pos());
    }

    public void emit(StringBuilder sb2) {
        if (this.charsString == null) {
            this.charsString = sb2.toString();
        } else {
            if (this.charsBuilder.length() == 0) {
                this.charsBuilder.append(this.charsString);
            }
            this.charsBuilder.append((CharSequence) sb2);
        }
        this.charPending.startPos(this.charStartPos);
        this.charPending.endPos(this.reader.pos());
    }

    public void emit(char c10) {
        if (this.charsString == null) {
            this.charsString = String.valueOf(c10);
        } else {
            if (this.charsBuilder.length() == 0) {
                this.charsBuilder.append(this.charsString);
            }
            this.charsBuilder.append(c10);
        }
        this.charPending.startPos(this.charStartPos);
        this.charPending.endPos(this.reader.pos());
    }

    public void emit(char[] cArr) {
        emit(String.valueOf(cArr));
    }

    public void emit(int[] iArr) {
        emit(new String(iArr, 0, iArr.length));
    }
}
