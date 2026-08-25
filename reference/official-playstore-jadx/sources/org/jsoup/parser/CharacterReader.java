package org.jsoup.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import org.jsoup.UncheckedIOException;
import org.jsoup.helper.Validate;

/* JADX INFO: loaded from: classes5.dex */
public final class CharacterReader {
    static final char EOF = 65535;
    static final int maxBufferLen = 32768;
    private static final int maxStringCacheLen = 12;
    private static final int minReadAheadLen = 1024;
    static final int readAheadLimit = 24576;
    private static final int stringCacheSize = 512;
    private int bufLength;
    private int bufMark;
    private int bufPos;
    private int bufSplitPoint;
    private char[] charBuf;
    private int lastIcIndex;
    private String lastIcSeq;
    private int lineNumberOffset;
    private ArrayList<Integer> newlinePositions;
    private boolean readFully;
    private Reader reader;
    private int readerPos;
    private String[] stringCache;

    public CharacterReader(Reader reader, int i10) {
        this.bufMark = -1;
        this.stringCache = new String[512];
        this.newlinePositions = null;
        this.lineNumberOffset = 1;
        Validate.notNull(reader);
        Validate.isTrue(reader.markSupported());
        this.reader = reader;
        this.charBuf = new char[Math.min(i10, 32768)];
        bufferUp();
    }

    private void bufferUp() {
        int i10;
        int i11;
        boolean z;
        if (this.readFully || (i10 = this.bufPos) < this.bufSplitPoint) {
            return;
        }
        int i12 = this.bufMark;
        if (i12 != -1) {
            i11 = i10 - i12;
            i10 = i12;
        } else {
            i11 = 0;
        }
        try {
            long j10 = i10;
            long jSkip = this.reader.skip(j10);
            this.reader.mark(32768);
            int i13 = 0;
            while (true) {
                z = true;
                if (i13 > 1024) {
                    break;
                }
                Reader reader = this.reader;
                char[] cArr = this.charBuf;
                int i14 = reader.read(cArr, i13, cArr.length - i13);
                if (i14 == -1) {
                    this.readFully = true;
                }
                if (i14 <= 0) {
                    break;
                } else {
                    i13 += i14;
                }
            }
            this.reader.reset();
            if (i13 > 0) {
                if (jSkip != j10) {
                    z = false;
                }
                Validate.isTrue(z);
                this.bufLength = i13;
                this.readerPos += i10;
                this.bufPos = i11;
                if (this.bufMark != -1) {
                    this.bufMark = 0;
                }
                this.bufSplitPoint = Math.min(i13, readAheadLimit);
            }
            scanBufferForNewlines();
            this.lastIcSeq = null;
        } catch (IOException e5) {
            throw new UncheckedIOException(e5);
        }
    }

    private static String cacheString(char[] cArr, String[] strArr, int i10, int i11) {
        if (i11 > 12) {
            return new String(cArr, i10, i11);
        }
        if (i11 < 1) {
            return "";
        }
        int i12 = 0;
        for (int i13 = 0; i13 < i11; i13++) {
            i12 = (i12 * 31) + cArr[i10 + i13];
        }
        int i14 = i12 & 511;
        String str = strArr[i14];
        if (str != null && rangeEquals(cArr, i10, i11, str)) {
            return str;
        }
        String str2 = new String(cArr, i10, i11);
        strArr[i14] = str2;
        return str2;
    }

    private boolean isEmptyNoBufferUp() {
        return this.bufPos >= this.bufLength;
    }

    private int lineNumIndex(int i10) {
        if (!isTrackNewlines()) {
            return 0;
        }
        int iBinarySearch = Collections.binarySearch(this.newlinePositions, Integer.valueOf(i10));
        return iBinarySearch < -1 ? Math.abs(iBinarySearch) - 2 : iBinarySearch;
    }

    public static boolean rangeEquals(char[] cArr, int i10, int i11, String str) {
        if (i11 != str.length()) {
            return false;
        }
        int i12 = 0;
        while (true) {
            int i13 = i11 - 1;
            if (i11 == 0) {
                return true;
            }
            int i14 = i10 + 1;
            int i15 = i12 + 1;
            if (cArr[i10] != str.charAt(i12)) {
                return false;
            }
            i10 = i14;
            i11 = i13;
            i12 = i15;
        }
    }

    private void scanBufferForNewlines() {
        if (isTrackNewlines()) {
            if (this.newlinePositions.size() > 0) {
                int iLineNumIndex = lineNumIndex(this.readerPos);
                if (iLineNumIndex == -1) {
                    iLineNumIndex = 0;
                }
                Integer num = this.newlinePositions.get(iLineNumIndex);
                num.intValue();
                this.lineNumberOffset += iLineNumIndex;
                this.newlinePositions.clear();
                this.newlinePositions.add(num);
            }
            for (int i10 = this.bufPos; i10 < this.bufLength; i10++) {
                if (this.charBuf[i10] == '\n') {
                    this.newlinePositions.add(Integer.valueOf(this.readerPos + 1 + i10));
                }
            }
        }
    }

    public void advance() {
        this.bufPos++;
    }

    public void close() {
        Reader reader = this.reader;
        if (reader == null) {
            return;
        }
        try {
            reader.close();
        } catch (IOException unused) {
        } catch (Throwable th) {
            this.reader = null;
            this.charBuf = null;
            this.stringCache = null;
            throw th;
        }
        this.reader = null;
        this.charBuf = null;
        this.stringCache = null;
    }

    public int columnNumber() {
        return columnNumber(pos());
    }

    public char consume() {
        bufferUp();
        char c10 = isEmptyNoBufferUp() ? EOF : this.charBuf[this.bufPos];
        this.bufPos++;
        return c10;
    }

    public String consumeAttributeQuoted(boolean z) {
        int i10 = this.bufPos;
        int i11 = this.bufLength;
        char[] cArr = this.charBuf;
        int i12 = i10;
        while (i12 < i11) {
            char c10 = cArr[i12];
            if (c10 == 0) {
                break;
            }
            if (c10 != '\"') {
                if (c10 == '&' || (c10 == '\'' && z)) {
                    break;
                }
                i12++;
            } else {
                if (!z) {
                    break;
                }
                i12++;
            }
        }
        this.bufPos = i12;
        return i12 > i10 ? cacheString(this.charBuf, this.stringCache, i10, i12 - i10) : "";
    }

    public String consumeData() {
        int i10 = this.bufPos;
        int i11 = this.bufLength;
        char[] cArr = this.charBuf;
        int i12 = i10;
        while (i12 < i11) {
            char c10 = cArr[i12];
            if (c10 == 0 || c10 == '&' || c10 == '<') {
                break;
            }
            i12++;
        }
        this.bufPos = i12;
        return i12 > i10 ? cacheString(this.charBuf, this.stringCache, i10, i12 - i10) : "";
    }

    public String consumeDigitSequence() {
        int i10;
        char c10;
        bufferUp();
        int i11 = this.bufPos;
        while (true) {
            i10 = this.bufPos;
            if (i10 >= this.bufLength || (c10 = this.charBuf[i10]) < '0' || c10 > '9') {
                break;
            }
            this.bufPos = i10 + 1;
        }
        return cacheString(this.charBuf, this.stringCache, i11, i10 - i11);
    }

    public String consumeHexSequence() {
        int i10;
        char c10;
        bufferUp();
        int i11 = this.bufPos;
        while (true) {
            i10 = this.bufPos;
            if (i10 >= this.bufLength || (((c10 = this.charBuf[i10]) < '0' || c10 > '9') && ((c10 < 'A' || c10 > 'F') && (c10 < 'a' || c10 > 'f')))) {
                break;
            }
            this.bufPos = i10 + 1;
        }
        return cacheString(this.charBuf, this.stringCache, i11, i10 - i11);
    }

    public String consumeLetterSequence() {
        char c10;
        bufferUp();
        int i10 = this.bufPos;
        while (true) {
            int i11 = this.bufPos;
            if (i11 >= this.bufLength || (((c10 = this.charBuf[i11]) < 'A' || c10 > 'Z') && ((c10 < 'a' || c10 > 'z') && !Character.isLetter(c10)))) {
                break;
            }
            this.bufPos++;
        }
        return cacheString(this.charBuf, this.stringCache, i10, this.bufPos - i10);
    }

    public String consumeLetterThenDigitSequence() {
        char c10;
        bufferUp();
        int i10 = this.bufPos;
        while (true) {
            int i11 = this.bufPos;
            if (i11 >= this.bufLength || (((c10 = this.charBuf[i11]) < 'A' || c10 > 'Z') && ((c10 < 'a' || c10 > 'z') && !Character.isLetter(c10)))) {
                break;
            }
            this.bufPos++;
        }
        while (!isEmptyNoBufferUp()) {
            char[] cArr = this.charBuf;
            int i12 = this.bufPos;
            char c11 = cArr[i12];
            if (c11 < '0' || c11 > '9') {
                break;
            }
            this.bufPos = i12 + 1;
        }
        return cacheString(this.charBuf, this.stringCache, i10, this.bufPos - i10);
    }

    public String consumeRawData() {
        int i10 = this.bufPos;
        int i11 = this.bufLength;
        char[] cArr = this.charBuf;
        int i12 = i10;
        while (i12 < i11) {
            char c10 = cArr[i12];
            if (c10 == 0 || c10 == '<') {
                break;
            }
            i12++;
        }
        this.bufPos = i12;
        return i12 > i10 ? cacheString(this.charBuf, this.stringCache, i10, i12 - i10) : "";
    }

    public String consumeTagName() {
        bufferUp();
        int i10 = this.bufPos;
        int i11 = this.bufLength;
        char[] cArr = this.charBuf;
        int i12 = i10;
        while (i12 < i11) {
            char c10 = cArr[i12];
            if (c10 == '\t' || c10 == '\n' || c10 == '\f' || c10 == '\r' || c10 == ' ' || c10 == '/' || c10 == '<' || c10 == '>') {
                break;
            }
            i12++;
        }
        this.bufPos = i12;
        return i12 > i10 ? cacheString(this.charBuf, this.stringCache, i10, i12 - i10) : "";
    }

    public String consumeTo(char c10) {
        int iNextIndexOf = nextIndexOf(c10);
        if (iNextIndexOf == -1) {
            return consumeToEnd();
        }
        String strCacheString = cacheString(this.charBuf, this.stringCache, this.bufPos, iNextIndexOf);
        this.bufPos += iNextIndexOf;
        return strCacheString;
    }

    public String consumeToAny(char... cArr) {
        bufferUp();
        int i10 = this.bufPos;
        int i11 = this.bufLength;
        char[] cArr2 = this.charBuf;
        int i12 = i10;
        loop0: while (i12 < i11) {
            for (char c10 : cArr) {
                if (cArr2[i12] == c10) {
                    break loop0;
                }
            }
            i12++;
        }
        this.bufPos = i12;
        return i12 > i10 ? cacheString(this.charBuf, this.stringCache, i10, i12 - i10) : "";
    }

    public String consumeToAnySorted(char... cArr) {
        bufferUp();
        int i10 = this.bufPos;
        int i11 = this.bufLength;
        char[] cArr2 = this.charBuf;
        int i12 = i10;
        while (i12 < i11 && Arrays.binarySearch(cArr, cArr2[i12]) < 0) {
            i12++;
        }
        this.bufPos = i12;
        return i12 > i10 ? cacheString(this.charBuf, this.stringCache, i10, i12 - i10) : "";
    }

    public String consumeToEnd() {
        bufferUp();
        char[] cArr = this.charBuf;
        String[] strArr = this.stringCache;
        int i10 = this.bufPos;
        String strCacheString = cacheString(cArr, strArr, i10, this.bufLength - i10);
        this.bufPos = this.bufLength;
        return strCacheString;
    }

    public boolean containsIgnoreCase(String str) {
        if (str.equals(this.lastIcSeq)) {
            int i10 = this.lastIcIndex;
            if (i10 == -1) {
                return false;
            }
            if (i10 >= this.bufPos) {
                return true;
            }
        }
        this.lastIcSeq = str;
        Locale locale = Locale.ENGLISH;
        int iNextIndexOf = nextIndexOf(str.toLowerCase(locale));
        if (iNextIndexOf > -1) {
            this.lastIcIndex = this.bufPos + iNextIndexOf;
            return true;
        }
        int iNextIndexOf2 = nextIndexOf(str.toUpperCase(locale));
        boolean z = iNextIndexOf2 > -1;
        this.lastIcIndex = z ? this.bufPos + iNextIndexOf2 : -1;
        return z;
    }

    public char current() {
        bufferUp();
        return isEmptyNoBufferUp() ? EOF : this.charBuf[this.bufPos];
    }

    public boolean isEmpty() {
        bufferUp();
        return this.bufPos >= this.bufLength;
    }

    public boolean isTrackNewlines() {
        return this.newlinePositions != null;
    }

    public int lineNumber() {
        return lineNumber(pos());
    }

    public void mark() {
        if (this.bufLength - this.bufPos < 1024) {
            this.bufSplitPoint = 0;
        }
        bufferUp();
        this.bufMark = this.bufPos;
    }

    public boolean matchConsume(String str) {
        bufferUp();
        if (!matches(str)) {
            return false;
        }
        this.bufPos = str.length() + this.bufPos;
        return true;
    }

    public boolean matchConsumeIgnoreCase(String str) {
        if (!matchesIgnoreCase(str)) {
            return false;
        }
        this.bufPos = str.length() + this.bufPos;
        return true;
    }

    public boolean matches(char c10) {
        return !isEmpty() && this.charBuf[this.bufPos] == c10;
    }

    public boolean matchesAny(char... cArr) {
        if (isEmpty()) {
            return false;
        }
        bufferUp();
        char c10 = this.charBuf[this.bufPos];
        for (char c11 : cArr) {
            if (c11 == c10) {
                return true;
            }
        }
        return false;
    }

    public boolean matchesAnySorted(char[] cArr) {
        bufferUp();
        return !isEmpty() && Arrays.binarySearch(cArr, this.charBuf[this.bufPos]) >= 0;
    }

    public boolean matchesAsciiAlpha() {
        if (isEmpty()) {
            return false;
        }
        char c10 = this.charBuf[this.bufPos];
        if (c10 < 'A' || c10 > 'Z') {
            return c10 >= 'a' && c10 <= 'z';
        }
        return true;
    }

    public boolean matchesDigit() {
        char c10;
        return !isEmpty() && (c10 = this.charBuf[this.bufPos]) >= '0' && c10 <= '9';
    }

    public boolean matchesIgnoreCase(String str) {
        bufferUp();
        int length = str.length();
        if (length > this.bufLength - this.bufPos) {
            return false;
        }
        for (int i10 = 0; i10 < length; i10++) {
            if (Character.toUpperCase(str.charAt(i10)) != Character.toUpperCase(this.charBuf[this.bufPos + i10])) {
                return false;
            }
        }
        return true;
    }

    public boolean matchesLetter() {
        if (isEmpty()) {
            return false;
        }
        char c10 = this.charBuf[this.bufPos];
        if (c10 < 'A' || c10 > 'Z') {
            return (c10 >= 'a' && c10 <= 'z') || Character.isLetter(c10);
        }
        return true;
    }

    public int nextIndexOf(char c10) {
        bufferUp();
        for (int i10 = this.bufPos; i10 < this.bufLength; i10++) {
            if (c10 == this.charBuf[i10]) {
                return i10 - this.bufPos;
            }
        }
        return -1;
    }

    public int pos() {
        return this.readerPos + this.bufPos;
    }

    public String posLineCol() {
        return lineNumber() + ":" + columnNumber();
    }

    public boolean readFully() {
        return this.readFully;
    }

    public void rewindToMark() {
        int i10 = this.bufMark;
        if (i10 == -1) {
            throw new UncheckedIOException(new IOException("Mark invalid"));
        }
        this.bufPos = i10;
        unmark();
    }

    public String toString() {
        int i10 = this.bufLength;
        int i11 = this.bufPos;
        return i10 - i11 < 0 ? "" : new String(this.charBuf, i11, i10 - i11);
    }

    public void trackNewlines(boolean z) {
        if (z && this.newlinePositions == null) {
            this.newlinePositions = new ArrayList<>(409);
            scanBufferForNewlines();
        } else {
            if (z) {
                return;
            }
            this.newlinePositions = null;
        }
    }

    public void unconsume() {
        int i10 = this.bufPos;
        if (i10 < 1) {
            throw new UncheckedIOException(new IOException("WTF: No buffer left to unconsume."));
        }
        this.bufPos = i10 - 1;
    }

    public void unmark() {
        this.bufMark = -1;
    }

    public int columnNumber(int i10) {
        int iLineNumIndex;
        if (isTrackNewlines() && (iLineNumIndex = lineNumIndex(i10)) != -1) {
            return (i10 - this.newlinePositions.get(iLineNumIndex).intValue()) + 1;
        }
        return i10 + 1;
    }

    public int lineNumber(int i10) {
        if (!isTrackNewlines()) {
            return 1;
        }
        int iLineNumIndex = lineNumIndex(i10);
        return iLineNumIndex == -1 ? this.lineNumberOffset : iLineNumIndex + this.lineNumberOffset + 1;
    }

    public boolean matches(String str) {
        bufferUp();
        int length = str.length();
        if (length > this.bufLength - this.bufPos) {
            return false;
        }
        for (int i10 = 0; i10 < length; i10++) {
            if (str.charAt(i10) != this.charBuf[this.bufPos + i10]) {
                return false;
            }
        }
        return true;
    }

    public boolean rangeEquals(int i10, int i11, String str) {
        return rangeEquals(this.charBuf, i10, i11, str);
    }

    public String consumeTo(String str) {
        int iNextIndexOf = nextIndexOf(str);
        if (iNextIndexOf != -1) {
            String strCacheString = cacheString(this.charBuf, this.stringCache, this.bufPos, iNextIndexOf);
            this.bufPos += iNextIndexOf;
            return strCacheString;
        }
        if (this.bufLength - this.bufPos < str.length()) {
            return consumeToEnd();
        }
        int length = (this.bufLength - str.length()) + 1;
        char[] cArr = this.charBuf;
        String[] strArr = this.stringCache;
        int i10 = this.bufPos;
        String strCacheString2 = cacheString(cArr, strArr, i10, length - i10);
        this.bufPos = length;
        return strCacheString2;
    }

    public int nextIndexOf(CharSequence charSequence) {
        bufferUp();
        char cCharAt = charSequence.charAt(0);
        int i10 = this.bufPos;
        while (i10 < this.bufLength) {
            if (cCharAt != this.charBuf[i10]) {
                do {
                    i10++;
                    if (i10 >= this.bufLength) {
                        break;
                    }
                } while (cCharAt != this.charBuf[i10]);
            }
            int i11 = i10 + 1;
            int length = (charSequence.length() + i11) - 1;
            int i12 = this.bufLength;
            if (i10 < i12 && length <= i12) {
                int i13 = i11;
                for (int i14 = 1; i13 < length && charSequence.charAt(i14) == this.charBuf[i13]; i14++) {
                    i13++;
                }
                if (i13 == length) {
                    return i10 - this.bufPos;
                }
            }
            i10 = i11;
        }
        return -1;
    }

    public CharacterReader(Reader reader) {
        this(reader, 32768);
    }

    public CharacterReader(String str) {
        this(new StringReader(str), str.length());
    }
}
