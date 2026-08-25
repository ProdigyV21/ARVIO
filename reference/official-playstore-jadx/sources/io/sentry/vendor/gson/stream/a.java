package io.sentry.vendor.gson.stream;

import androidx.compose.material3.d;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Reader f18004i;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public long f18012s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f18013t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public String f18014u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int[] f18015v;
    public String[] x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int[] f18017y;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f18005l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final char[] f18006m = new char[1024];

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f18007n = 0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f18008o = 0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f18009p = 0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f18010q = 0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f18011r = 0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f18016w = 1;

    public a(Reader reader) {
        int[] iArr = new int[32];
        this.f18015v = iArr;
        iArr[0] = 6;
        this.x = new String[32];
        this.f18017y = new int[32];
        this.f18004i = reader;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x004a, code lost:
    
        i();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:32:0x0044. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0084  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String G() {
        /*
            r7 = this;
            r0 = 0
            r1 = 0
        L2:
            r2 = r1
        L3:
            int r3 = r7.f18007n
            int r4 = r3 + r2
            int r5 = r7.f18008o
            char[] r6 = r7.f18006m
            if (r4 >= r5) goto L4e
            int r3 = r3 + r2
            char r3 = r6[r3]
            r4 = 9
            if (r3 == r4) goto L5a
            r4 = 10
            if (r3 == r4) goto L5a
            r4 = 12
            if (r3 == r4) goto L5a
            r4 = 13
            if (r3 == r4) goto L5a
            r4 = 32
            if (r3 == r4) goto L5a
            r4 = 35
            if (r3 == r4) goto L4a
            r4 = 44
            if (r3 == r4) goto L5a
            r4 = 47
            if (r3 == r4) goto L4a
            r4 = 61
            if (r3 == r4) goto L4a
            r4 = 123(0x7b, float:1.72E-43)
            if (r3 == r4) goto L5a
            r4 = 125(0x7d, float:1.75E-43)
            if (r3 == r4) goto L5a
            r4 = 58
            if (r3 == r4) goto L5a
            r4 = 59
            if (r3 == r4) goto L4a
            switch(r3) {
                case 91: goto L5a;
                case 92: goto L4a;
                case 93: goto L5a;
                default: goto L47;
            }
        L47:
            int r2 = r2 + 1
            goto L3
        L4a:
            r7.i()
            goto L5a
        L4e:
            int r3 = r6.length
            if (r2 >= r3) goto L5c
            int r3 = r2 + 1
            boolean r3 = r7.k(r3)
            if (r3 == 0) goto L5a
            goto L3
        L5a:
            r1 = r2
            goto L7a
        L5c:
            if (r0 != 0) goto L69
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r3 = 16
            int r3 = java.lang.Math.max(r2, r3)
            r0.<init>(r3)
        L69:
            int r3 = r7.f18007n
            r0.append(r6, r3, r2)
            int r3 = r7.f18007n
            int r3 = r3 + r2
            r7.f18007n = r3
            r2 = 1
            boolean r2 = r7.k(r2)
            if (r2 != 0) goto L2
        L7a:
            if (r0 != 0) goto L84
            java.lang.String r0 = new java.lang.String
            int r2 = r7.f18007n
            r0.<init>(r6, r2, r1)
            goto L8d
        L84:
            int r2 = r7.f18007n
            r0.append(r6, r2, r1)
            java.lang.String r0 = r0.toString()
        L8d:
            int r2 = r7.f18007n
            int r2 = r2 + r1
            r7.f18007n = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.vendor.gson.stream.a.G():java.lang.String");
    }

    public final void O(int i10) {
        int i11 = this.f18016w;
        int[] iArr = this.f18015v;
        if (i11 == iArr.length) {
            int i12 = i11 * 2;
            this.f18015v = Arrays.copyOf(iArr, i12);
            this.f18017y = Arrays.copyOf(this.f18017y, i12);
            this.x = (String[]) Arrays.copyOf(this.x, i12);
        }
        int[] iArr2 = this.f18015v;
        int i13 = this.f18016w;
        this.f18016w = i13 + 1;
        iArr2[i13] = i10;
    }

    public final char R() throws MalformedJsonException {
        int i10;
        if (this.f18007n == this.f18008o && !k(1)) {
            b0("Unterminated escape sequence");
            throw null;
        }
        int i11 = this.f18007n;
        int i12 = i11 + 1;
        this.f18007n = i12;
        char[] cArr = this.f18006m;
        char c10 = cArr[i11];
        if (c10 == '\n') {
            this.f18009p++;
            this.f18010q = i12;
            return c10;
        }
        if (c10 == '\"' || c10 == '\'' || c10 == '/' || c10 == '\\') {
            return c10;
        }
        if (c10 == 'b') {
            return '\b';
        }
        if (c10 == 'f') {
            return '\f';
        }
        if (c10 == 'n') {
            return '\n';
        }
        if (c10 == 'r') {
            return '\r';
        }
        if (c10 == 't') {
            return '\t';
        }
        if (c10 != 'u') {
            b0("Invalid escape sequence");
            throw null;
        }
        if (i11 + 5 > this.f18008o && !k(4)) {
            b0("Unterminated escape sequence");
            throw null;
        }
        int i13 = this.f18007n;
        int i14 = i13 + 4;
        char c11 = 0;
        while (i13 < i14) {
            char c12 = cArr[i13];
            char c13 = (char) (c11 << 4);
            if (c12 >= '0' && c12 <= '9') {
                i10 = c12 - '0';
            } else if (c12 >= 'a' && c12 <= 'f') {
                i10 = c12 - 'W';
            } else {
                if (c12 < 'A' || c12 > 'F') {
                    throw new NumberFormatException("\\u".concat(new String(cArr, this.f18007n, 4)));
                }
                i10 = c12 - '7';
            }
            c11 = (char) (i10 + c13);
            i13++;
        }
        this.f18007n += 4;
        return c11;
    }

    public final void T(char c10) {
        do {
            int i10 = this.f18007n;
            int i11 = this.f18008o;
            while (i10 < i11) {
                int i12 = i10 + 1;
                char c11 = this.f18006m[i10];
                if (c11 == c10) {
                    this.f18007n = i12;
                    return;
                }
                if (c11 == '\\') {
                    this.f18007n = i12;
                    R();
                    i10 = this.f18007n;
                    i11 = this.f18008o;
                } else {
                    if (c11 == '\n') {
                        this.f18009p++;
                        this.f18010q = i12;
                    }
                    i10 = i12;
                }
            }
            this.f18007n = i10;
        } while (k(1));
        b0("Unterminated string");
        throw null;
    }

    public final void V() {
        char c10;
        do {
            if (this.f18007n >= this.f18008o && !k(1)) {
                return;
            }
            int i10 = this.f18007n;
            int i11 = i10 + 1;
            this.f18007n = i11;
            c10 = this.f18006m[i10];
            if (c10 == '\n') {
                this.f18009p++;
                this.f18010q = i11;
                return;
            }
        } while (c10 != '\r');
    }

    public final void b0(String str) throws MalformedJsonException {
        StringBuilder sbS = d.s(str);
        sbS.append(o());
        throw new MalformedJsonException(sbS.toString());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f18011r = 0;
        this.f18015v[0] = 8;
        this.f18016w = 1;
        this.f18004i.close();
    }

    public final boolean hasNext() {
        int iJ = this.f18011r;
        if (iJ == 0) {
            iJ = j();
        }
        return (iJ == 2 || iJ == 4) ? false : true;
    }

    public final void i() {
        if (this.f18005l) {
            return;
        }
        b0("Use JsonReader.setLenient(true) to accept malformed JSON");
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:168:0x0215, code lost:
    
        if (l(r1) != false) goto L121;
     */
    /* JADX WARN: Removed duplicated region for block: B:115:0x017d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x027c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:209:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00e8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int j() {
        /*
            Method dump skipped, instruction units count: 809
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.vendor.gson.stream.a.j():int");
    }

    public final boolean k(int i10) throws IOException {
        int i11;
        int i12;
        int i13 = this.f18010q;
        int i14 = this.f18007n;
        this.f18010q = i13 - i14;
        int i15 = this.f18008o;
        char[] cArr = this.f18006m;
        if (i15 != i14) {
            int i16 = i15 - i14;
            this.f18008o = i16;
            System.arraycopy(cArr, i14, cArr, 0, i16);
        } else {
            this.f18008o = 0;
        }
        this.f18007n = 0;
        do {
            int i17 = this.f18008o;
            int i18 = this.f18004i.read(cArr, i17, cArr.length - i17);
            if (i18 == -1) {
                return false;
            }
            i11 = this.f18008o + i18;
            this.f18008o = i11;
            if (this.f18009p == 0 && (i12 = this.f18010q) == 0 && i11 > 0 && cArr[0] == 65279) {
                this.f18007n++;
                this.f18010q = i12 + 1;
                i10++;
            }
        } while (i11 < i10);
        return true;
    }

    public final boolean l(char c10) {
        if (c10 == '\t' || c10 == '\n' || c10 == '\f' || c10 == '\r' || c10 == ' ') {
            return false;
        }
        if (c10 != '#') {
            if (c10 == ',') {
                return false;
            }
            if (c10 != '/' && c10 != '=') {
                if (c10 == '{' || c10 == '}' || c10 == ':') {
                    return false;
                }
                if (c10 != ';') {
                    switch (c10) {
                        case '[':
                        case ']':
                            return false;
                        case '\\':
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        i();
        return false;
    }

    public final double nextDouble() {
        int iJ = this.f18011r;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ == 15) {
            this.f18011r = 0;
            int[] iArr = this.f18017y;
            int i10 = this.f18016w - 1;
            iArr[i10] = iArr[i10] + 1;
            return this.f18012s;
        }
        if (iJ == 16) {
            this.f18014u = new String(this.f18006m, this.f18007n, this.f18013t);
            this.f18007n += this.f18013t;
        } else if (iJ == 8 || iJ == 9) {
            this.f18014u = y(iJ == 8 ? '\'' : '\"');
        } else if (iJ == 10) {
            this.f18014u = G();
        } else if (iJ != 11) {
            throw new IllegalStateException("Expected a double but was " + peek() + o());
        }
        this.f18011r = 11;
        double d4 = Double.parseDouble(this.f18014u);
        if (!this.f18005l && (Double.isNaN(d4) || Double.isInfinite(d4))) {
            throw new MalformedJsonException("JSON forbids NaN and infinities: " + d4 + o());
        }
        this.f18014u = null;
        this.f18011r = 0;
        int[] iArr2 = this.f18017y;
        int i11 = this.f18016w - 1;
        iArr2[i11] = iArr2[i11] + 1;
        return d4;
    }

    public final String nextName() {
        String strY;
        int iJ = this.f18011r;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ == 14) {
            strY = G();
        } else if (iJ == 12) {
            strY = y('\'');
        } else {
            if (iJ != 13) {
                throw new IllegalStateException("Expected a name but was " + peek() + o());
            }
            strY = y('\"');
        }
        this.f18011r = 0;
        this.x[this.f18016w - 1] = strY;
        return strY;
    }

    public final String o() {
        StringBuilder sbV = androidx.compose.foundation.c.v(" at line ", this.f18009p + 1, " column ", (this.f18007n - this.f18010q) + 1, " path ");
        StringBuilder sb2 = new StringBuilder("$");
        int i10 = this.f18016w;
        for (int i11 = 0; i11 < i10; i11++) {
            int i12 = this.f18015v[i11];
            if (i12 == 1 || i12 == 2) {
                sb2.append('[');
                sb2.append(this.f18017y[i11]);
                sb2.append(']');
            } else if (i12 == 3 || i12 == 4 || i12 == 5) {
                sb2.append('.');
                String str = this.x[i11];
                if (str != null) {
                    sb2.append(str);
                }
            }
        }
        sbV.append(sb2.toString());
        return sbV.toString();
    }

    public final b peek() {
        int iJ = this.f18011r;
        if (iJ == 0) {
            iJ = j();
        }
        switch (iJ) {
            case 1:
                return b.BEGIN_OBJECT;
            case 2:
                return b.END_OBJECT;
            case 3:
                return b.BEGIN_ARRAY;
            case 4:
                return b.END_ARRAY;
            case 5:
            case 6:
                return b.BOOLEAN;
            case 7:
                return b.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return b.STRING;
            case 12:
            case 13:
            case 14:
                return b.NAME;
            case 15:
            case 16:
                return b.NUMBER;
            case 17:
                return b.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public final String toString() {
        return a.class.getSimpleName() + o();
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0074, code lost:
    
        return r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int x(boolean r10) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.vendor.gson.stream.a.x(boolean):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x002d, code lost:
    
        r10.f18007n = r8;
        r8 = r8 - r3;
        r2 = r8 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0032, code lost:
    
        if (r1 != null) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0034, code lost:
    
        r1 = new java.lang.StringBuilder(java.lang.Math.max(r8 * 2, 16));
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005b, code lost:
    
        if (r1 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005d, code lost:
    
        r1 = new java.lang.StringBuilder(java.lang.Math.max((r2 - r3) * 2, 16));
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006b, code lost:
    
        r1.append(r7, r3, r2 - r3);
        r10.f18007n = r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String y(char r11) {
        /*
            r10 = this;
            r0 = 0
            r1 = r0
        L2:
            int r2 = r10.f18007n
            int r3 = r10.f18008o
        L6:
            r4 = r3
            r3 = r2
        L8:
            r5 = 16
            r6 = 1
            char[] r7 = r10.f18006m
            if (r2 >= r4) goto L5b
            int r8 = r2 + 1
            char r2 = r7[r2]
            if (r2 != r11) goto L29
            r10.f18007n = r8
            int r8 = r8 - r3
            int r8 = r8 - r6
            if (r1 != 0) goto L21
            java.lang.String r11 = new java.lang.String
            r11.<init>(r7, r3, r8)
            return r11
        L21:
            r1.append(r7, r3, r8)
            java.lang.String r11 = r1.toString()
            return r11
        L29:
            r9 = 92
            if (r2 != r9) goto L4e
            r10.f18007n = r8
            int r8 = r8 - r3
            int r2 = r8 + (-1)
            if (r1 != 0) goto L3f
            int r8 = r8 * 2
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            int r4 = java.lang.Math.max(r8, r5)
            r1.<init>(r4)
        L3f:
            r1.append(r7, r3, r2)
            char r2 = r10.R()
            r1.append(r2)
            int r2 = r10.f18007n
            int r3 = r10.f18008o
            goto L6
        L4e:
            r5 = 10
            if (r2 != r5) goto L59
            int r2 = r10.f18009p
            int r2 = r2 + r6
            r10.f18009p = r2
            r10.f18010q = r8
        L59:
            r2 = r8
            goto L8
        L5b:
            if (r1 != 0) goto L6b
            int r1 = r2 - r3
            int r1 = r1 * 2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r1 = java.lang.Math.max(r1, r5)
            r4.<init>(r1)
            r1 = r4
        L6b:
            int r4 = r2 - r3
            r1.append(r7, r3, r4)
            r10.f18007n = r2
            boolean r2 = r10.k(r6)
            if (r2 == 0) goto L79
            goto L2
        L79:
            java.lang.String r11 = "Unterminated string"
            r10.b0(r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.vendor.gson.stream.a.y(char):java.lang.String");
    }
}
