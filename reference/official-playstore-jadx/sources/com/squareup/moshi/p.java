package com.squareup.moshi;

import java.io.EOFException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes4.dex */
public final class p extends o {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final xb.m f14446u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final xb.m f14447v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final xb.m f14448w;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final xb.j f14449o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final xb.j f14450p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f14451q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public long f14452r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f14453s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public String f14454t;

    static {
        Charset charset = kotlin.text.a.f19924a;
        xb.m mVar = new xb.m("'\\".getBytes(charset));
        mVar.f22775m = "'\\";
        f14446u = mVar;
        xb.m mVar2 = new xb.m("\"\\".getBytes(charset));
        mVar2.f22775m = "\"\\";
        f14447v = mVar2;
        xb.m mVar3 = new xb.m("{}[]:, \n\t\r\f/\\;#=".getBytes(charset));
        mVar3.f22775m = "{}[]:, \n\t\r\f/\\;#=";
        f14448w = mVar3;
        "\n\r".getBytes(charset);
        "*/".getBytes(charset);
    }

    public p(xb.j jVar) {
        this.f14443l = new int[32];
        this.f14444m = new String[32];
        this.f14445n = new int[32];
        this.f14451q = 0;
        this.f14449o = jVar;
        this.f14450p = jVar;
        j(6);
    }

    public final void D0(xb.m mVar) throws EOFException {
        while (true) {
            long jO = this.f14449o.O(0L, mVar);
            if (jO == -1) {
                k("Unterminated string");
                throw null;
            }
            xb.j jVar = this.f14450p;
            if (jVar.x(jO) != 92) {
                jVar.skip(jO + 1);
                return;
            } else {
                jVar.skip(jO + 1);
                q0();
            }
        }
    }

    public final boolean G(int i10) {
        if (i10 == 9 || i10 == 10 || i10 == 12 || i10 == 13 || i10 == 32) {
            return false;
        }
        if (i10 != 35) {
            if (i10 == 44) {
                return false;
            }
            if (i10 != 47 && i10 != 61) {
                if (i10 == 123 || i10 == 125 || i10 == 58) {
                    return false;
                }
                if (i10 != 59) {
                    switch (i10) {
                        case 91:
                        case 93:
                            return false;
                        case 92:
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        l();
        throw null;
    }

    public final int O(boolean z) throws EOFException {
        int i10 = 0;
        while (true) {
            int i11 = i10 + 1;
            xb.j jVar = this.f14449o;
            if (!jVar.request(i11)) {
                if (z) {
                    throw new EOFException("End of input");
                }
                return -1;
            }
            long j10 = i10;
            xb.j jVar2 = this.f14450p;
            byte bX = jVar2.x(j10);
            if (bX != 10 && bX != 32 && bX != 13 && bX != 9) {
                jVar2.skip(j10);
                if (bX == 47) {
                    if (jVar.request(2L)) {
                        l();
                        throw null;
                    }
                } else if (bX == 35) {
                    l();
                    throw null;
                }
                return bX;
            }
            i10 = i11;
        }
    }

    public final void R() throws EOFException {
        int iO = this.f14451q;
        if (iO == 0) {
            iO = o();
        }
        if (iO == 7) {
            this.f14451q = 0;
            int[] iArr = this.f14445n;
            int i10 = this.f14442i - 1;
            iArr[i10] = iArr[i10] + 1;
            return;
        }
        throw new JsonDataException("Expected null but was " + com.arflix.tv.data.repository.g.x(b0()) + " at path " + i());
    }

    public final String T(xb.m mVar) {
        StringBuilder sb2 = null;
        while (true) {
            long jO = this.f14449o.O(0L, mVar);
            if (jO == -1) {
                k("Unterminated string");
                throw null;
            }
            xb.j jVar = this.f14450p;
            if (jVar.x(jO) != 92) {
                if (sb2 == null) {
                    String strQ0 = jVar.q0(jO, kotlin.text.a.f19924a);
                    jVar.readByte();
                    return strQ0;
                }
                sb2.append(jVar.q0(jO, kotlin.text.a.f19924a));
                jVar.readByte();
                return sb2.toString();
            }
            if (sb2 == null) {
                sb2 = new StringBuilder();
            }
            sb2.append(jVar.q0(jO, kotlin.text.a.f19924a));
            jVar.readByte();
            sb2.append(q0());
        }
    }

    public final String V() {
        long jO = this.f14449o.O(0L, f14448w);
        xb.j jVar = this.f14450p;
        if (jO == -1) {
            return jVar.t0();
        }
        jVar.getClass();
        return jVar.q0(jO, kotlin.text.a.f19924a);
    }

    public final int b0() throws EOFException {
        int iO = this.f14451q;
        if (iO == 0) {
            iO = o();
        }
        switch (iO) {
            case 1:
                return 3;
            case 2:
                return 4;
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
            case 6:
                return 8;
            case 7:
                return 9;
            case 8:
            case 9:
            case 10:
            case 11:
                return 6;
            case 12:
            case 13:
            case 14:
            case 15:
                return 5;
            case 16:
            case 17:
                return 7;
            case 18:
                return 10;
            default:
                throw new AssertionError();
        }
    }

    public final void beginArray() throws EOFException {
        int iO = this.f14451q;
        if (iO == 0) {
            iO = o();
        }
        if (iO == 3) {
            j(1);
            this.f14445n[this.f14442i - 1] = 0;
            this.f14451q = 0;
        } else {
            throw new JsonDataException("Expected BEGIN_ARRAY but was " + com.arflix.tv.data.repository.g.x(b0()) + " at path " + i());
        }
    }

    public final void beginObject() throws EOFException {
        int iO = this.f14451q;
        if (iO == 0) {
            iO = o();
        }
        if (iO == 1) {
            j(3);
            this.f14451q = 0;
        } else {
            throw new JsonDataException("Expected BEGIN_OBJECT but was " + com.arflix.tv.data.repository.g.x(b0()) + " at path " + i());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f14451q = 0;
        this.f14443l[0] = 8;
        this.f14442i = 1;
        this.f14450p.i();
    }

    public final void endArray() throws EOFException {
        int iO = this.f14451q;
        if (iO == 0) {
            iO = o();
        }
        if (iO != 4) {
            throw new JsonDataException("Expected END_ARRAY but was " + com.arflix.tv.data.repository.g.x(b0()) + " at path " + i());
        }
        int i10 = this.f14442i;
        this.f14442i = i10 - 1;
        int[] iArr = this.f14445n;
        int i11 = i10 - 2;
        iArr[i11] = iArr[i11] + 1;
        this.f14451q = 0;
    }

    public final void endObject() throws EOFException {
        int iO = this.f14451q;
        if (iO == 0) {
            iO = o();
        }
        if (iO != 2) {
            throw new JsonDataException("Expected END_OBJECT but was " + com.arflix.tv.data.repository.g.x(b0()) + " at path " + i());
        }
        int i10 = this.f14442i;
        int i11 = i10 - 1;
        this.f14442i = i11;
        this.f14444m[i11] = null;
        int[] iArr = this.f14445n;
        int i12 = i10 - 2;
        iArr[i12] = iArr[i12] + 1;
        this.f14451q = 0;
    }

    public final boolean hasNext() throws EOFException {
        int iO = this.f14451q;
        if (iO == 0) {
            iO = o();
        }
        return (iO == 2 || iO == 4 || iO == 18) ? false : true;
    }

    public final void l() {
        k("Use JsonReader.setLenient(true) to accept malformed JSON");
        throw null;
    }

    public final double nextDouble() throws EOFException, JsonEncodingException {
        int iO = this.f14451q;
        if (iO == 0) {
            iO = o();
        }
        if (iO == 16) {
            this.f14451q = 0;
            int[] iArr = this.f14445n;
            int i10 = this.f14442i - 1;
            iArr[i10] = iArr[i10] + 1;
            return this.f14452r;
        }
        if (iO == 17) {
            long j10 = this.f14453s;
            xb.j jVar = this.f14450p;
            jVar.getClass();
            this.f14454t = jVar.q0(j10, kotlin.text.a.f19924a);
        } else if (iO == 9) {
            this.f14454t = T(f14447v);
        } else if (iO == 8) {
            this.f14454t = T(f14446u);
        } else if (iO == 10) {
            this.f14454t = V();
        } else if (iO != 11) {
            throw new JsonDataException("Expected a double but was " + com.arflix.tv.data.repository.g.x(b0()) + " at path " + i());
        }
        this.f14451q = 11;
        try {
            double d4 = Double.parseDouble(this.f14454t);
            if (Double.isNaN(d4) || Double.isInfinite(d4)) {
                throw new JsonEncodingException("JSON forbids NaN and infinities: " + d4 + " at path " + i());
            }
            this.f14454t = null;
            this.f14451q = 0;
            int[] iArr2 = this.f14445n;
            int i11 = this.f14442i - 1;
            iArr2[i11] = iArr2[i11] + 1;
            return d4;
        } catch (NumberFormatException unused) {
            throw new JsonDataException("Expected a double but was " + this.f14454t + " at path " + i());
        }
    }

    public final int nextInt() throws EOFException {
        int iO = this.f14451q;
        if (iO == 0) {
            iO = o();
        }
        if (iO == 16) {
            long j10 = this.f14452r;
            int i10 = (int) j10;
            if (j10 == i10) {
                this.f14451q = 0;
                int[] iArr = this.f14445n;
                int i11 = this.f14442i - 1;
                iArr[i11] = iArr[i11] + 1;
                return i10;
            }
            throw new JsonDataException("Expected an int but was " + this.f14452r + " at path " + i());
        }
        if (iO == 17) {
            long j11 = this.f14453s;
            xb.j jVar = this.f14450p;
            jVar.getClass();
            this.f14454t = jVar.q0(j11, kotlin.text.a.f19924a);
        } else if (iO == 9 || iO == 8) {
            String strT = iO == 9 ? T(f14447v) : T(f14446u);
            this.f14454t = strT;
            try {
                int i12 = Integer.parseInt(strT);
                this.f14451q = 0;
                int[] iArr2 = this.f14445n;
                int i13 = this.f14442i - 1;
                iArr2[i13] = iArr2[i13] + 1;
                return i12;
            } catch (NumberFormatException unused) {
            }
        } else if (iO != 11) {
            throw new JsonDataException("Expected an int but was " + com.arflix.tv.data.repository.g.x(b0()) + " at path " + i());
        }
        this.f14451q = 11;
        try {
            double d4 = Double.parseDouble(this.f14454t);
            int i14 = (int) d4;
            if (i14 != d4) {
                throw new JsonDataException("Expected an int but was " + this.f14454t + " at path " + i());
            }
            this.f14454t = null;
            this.f14451q = 0;
            int[] iArr3 = this.f14445n;
            int i15 = this.f14442i - 1;
            iArr3[i15] = iArr3[i15] + 1;
            return i14;
        } catch (NumberFormatException unused2) {
            throw new JsonDataException("Expected an int but was " + this.f14454t + " at path " + i());
        }
    }

    public final String nextName() throws EOFException {
        String strT;
        int iO = this.f14451q;
        if (iO == 0) {
            iO = o();
        }
        if (iO == 14) {
            strT = V();
        } else if (iO == 13) {
            strT = T(f14447v);
        } else if (iO == 12) {
            strT = T(f14446u);
        } else {
            if (iO != 15) {
                throw new JsonDataException("Expected a name but was " + com.arflix.tv.data.repository.g.x(b0()) + " at path " + i());
            }
            strT = this.f14454t;
            this.f14454t = null;
        }
        this.f14451q = 0;
        this.f14444m[this.f14442i - 1] = strT;
        return strT;
    }

    public final String nextString() throws EOFException {
        String strQ0;
        int iO = this.f14451q;
        if (iO == 0) {
            iO = o();
        }
        if (iO == 10) {
            strQ0 = V();
        } else if (iO == 9) {
            strQ0 = T(f14447v);
        } else if (iO == 8) {
            strQ0 = T(f14446u);
        } else if (iO == 11) {
            strQ0 = this.f14454t;
            this.f14454t = null;
        } else if (iO == 16) {
            strQ0 = Long.toString(this.f14452r);
        } else {
            if (iO != 17) {
                throw new JsonDataException("Expected a string but was " + com.arflix.tv.data.repository.g.x(b0()) + " at path " + i());
            }
            long j10 = this.f14453s;
            xb.j jVar = this.f14450p;
            jVar.getClass();
            strQ0 = jVar.q0(j10, kotlin.text.a.f19924a);
        }
        this.f14451q = 0;
        int[] iArr = this.f14445n;
        int i10 = this.f14442i - 1;
        iArr[i10] = iArr[i10] + 1;
        return strQ0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:137:0x019a, code lost:
    
        if (G(r10) != false) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x019c, code lost:
    
        if (r1 != 2) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x019e, code lost:
    
        if (r4 == false) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x01a4, code lost:
    
        if (r8 != Long.MIN_VALUE) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x01a6, code lost:
    
        if (r13 == false) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x01aa, code lost:
    
        if (r8 != r16) goto L146;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x01ac, code lost:
    
        if (r13 != false) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x01ae, code lost:
    
        if (r13 == false) goto L148;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x01b1, code lost:
    
        r8 = -r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x01b2, code lost:
    
        r22.f14452r = r8;
        r7.skip(r2);
        r9 = 16;
        r22.f14451q = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x01bd, code lost:
    
        if (r1 == 2) goto L155;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x01c0, code lost:
    
        if (r1 == 4) goto L155;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x01c3, code lost:
    
        if (r1 != 7) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x01c5, code lost:
    
        r22.f14453s = r2;
        r9 = 17;
        r22.f14451q = 17;
     */
    /* JADX WARN: Removed duplicated region for block: B:174:0x01f2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:175:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x011a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x011b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int o() throws java.io.EOFException {
        /*
            Method dump skipped, instruction units count: 658
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.p.o():int");
    }

    public final char q0() throws EOFException {
        int i10;
        xb.j jVar = this.f14449o;
        if (!jVar.request(1L)) {
            k("Unterminated escape sequence");
            throw null;
        }
        xb.j jVar2 = this.f14450p;
        byte b10 = jVar2.readByte();
        if (b10 == 10 || b10 == 34 || b10 == 39 || b10 == 47 || b10 == 92) {
            return (char) b10;
        }
        if (b10 == 98) {
            return '\b';
        }
        if (b10 == 102) {
            return '\f';
        }
        if (b10 == 110) {
            return '\n';
        }
        if (b10 == 114) {
            return '\r';
        }
        if (b10 == 116) {
            return '\t';
        }
        if (b10 != 117) {
            k("Invalid escape sequence: \\" + ((char) b10));
            throw null;
        }
        if (!jVar.request(4L)) {
            throw new EOFException("Unterminated escape sequence at path " + i());
        }
        char c10 = 0;
        for (int i11 = 0; i11 < 4; i11++) {
            byte bX = jVar2.x(i11);
            char c11 = (char) (c10 << 4);
            if (bX >= 48 && bX <= 57) {
                i10 = bX - 48;
            } else if (bX >= 97 && bX <= 102) {
                i10 = bX - 87;
            } else {
                if (bX < 65 || bX > 70) {
                    k("\\u".concat(jVar2.q0(4L, kotlin.text.a.f19924a)));
                    throw null;
                }
                i10 = bX - 55;
            }
            c10 = (char) (i10 + c11);
        }
        jVar2.skip(4L);
        return c10;
    }

    public final void skipValue() throws EOFException {
        int i10 = 0;
        do {
            int iO = this.f14451q;
            if (iO == 0) {
                iO = o();
            }
            if (iO == 3) {
                j(1);
            } else if (iO == 1) {
                j(3);
            } else {
                if (iO == 4) {
                    i10--;
                    if (i10 < 0) {
                        throw new JsonDataException("Expected a value but was " + com.arflix.tv.data.repository.g.x(b0()) + " at path " + i());
                    }
                    this.f14442i--;
                } else if (iO == 2) {
                    i10--;
                    if (i10 < 0) {
                        throw new JsonDataException("Expected a value but was " + com.arflix.tv.data.repository.g.x(b0()) + " at path " + i());
                    }
                    this.f14442i--;
                } else {
                    xb.j jVar = this.f14450p;
                    if (iO == 14 || iO == 10) {
                        long jO = this.f14449o.O(0L, f14448w);
                        if (jO == -1) {
                            jO = jVar.f22759l;
                        }
                        jVar.skip(jO);
                    } else if (iO == 9 || iO == 13) {
                        D0(f14447v);
                    } else if (iO == 8 || iO == 12) {
                        D0(f14446u);
                    } else if (iO == 17) {
                        jVar.skip(this.f14453s);
                    } else if (iO == 18) {
                        throw new JsonDataException("Expected a value but was " + com.arflix.tv.data.repository.g.x(b0()) + " at path " + i());
                    }
                }
                this.f14451q = 0;
            }
            i10++;
            this.f14451q = 0;
        } while (i10 != 0);
        int[] iArr = this.f14445n;
        int i11 = this.f14442i - 1;
        iArr[i11] = iArr[i11] + 1;
        this.f14444m[i11] = "null";
    }

    public final int t0(io.sentry.internal.debugmeta.c cVar) throws EOFException {
        int iO = this.f14451q;
        if (iO == 0) {
            iO = o();
        }
        if (iO < 12 || iO > 15) {
            return -1;
        }
        if (iO == 15) {
            return x(this.f14454t, cVar);
        }
        int iD0 = this.f14449o.d0((xb.e0) cVar.f17368m);
        if (iD0 != -1) {
            this.f14451q = 0;
            this.f14444m[this.f14442i - 1] = ((String[]) cVar.f17367l)[iD0];
            return iD0;
        }
        String str = this.f14444m[this.f14442i - 1];
        String strNextName = nextName();
        int iX = x(strNextName, cVar);
        if (iX == -1) {
            this.f14451q = 15;
            this.f14454t = strNextName;
            this.f14444m[this.f14442i - 1] = str;
        }
        return iX;
    }

    public final String toString() {
        return "JsonReader(" + this.f14449o + ")";
    }

    public final void u0() throws EOFException {
        int iO = this.f14451q;
        if (iO == 0) {
            iO = o();
        }
        if (iO == 14) {
            long jO = this.f14449o.O(0L, f14448w);
            xb.j jVar = this.f14450p;
            if (jO == -1) {
                jO = jVar.f22759l;
            }
            jVar.skip(jO);
        } else if (iO == 13) {
            D0(f14447v);
        } else if (iO == 12) {
            D0(f14446u);
        } else if (iO != 15) {
            throw new JsonDataException("Expected a name but was " + com.arflix.tv.data.repository.g.x(b0()) + " at path " + i());
        }
        this.f14451q = 0;
        this.f14444m[this.f14442i - 1] = "null";
    }

    public final int x(String str, io.sentry.internal.debugmeta.c cVar) {
        int length = ((String[]) cVar.f17367l).length;
        for (int i10 = 0; i10 < length; i10++) {
            if (str.equals(((String[]) cVar.f17367l)[i10])) {
                this.f14451q = 0;
                this.f14444m[this.f14442i - 1] = str;
                return i10;
            }
        }
        return -1;
    }

    public final int y(String str, io.sentry.internal.debugmeta.c cVar) {
        int length = ((String[]) cVar.f17367l).length;
        for (int i10 = 0; i10 < length; i10++) {
            if (str.equals(((String[]) cVar.f17367l)[i10])) {
                this.f14451q = 0;
                int[] iArr = this.f14445n;
                int i11 = this.f14442i - 1;
                iArr[i11] = iArr[i11] + 1;
                return i10;
            }
        }
        return -1;
    }
}
