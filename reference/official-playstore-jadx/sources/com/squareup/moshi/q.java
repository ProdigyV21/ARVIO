package com.squareup.moshi;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class q extends r {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final String[] f14455s = new String[128];

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final xb.j f14456q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f14457r;

    static {
        for (int i10 = 0; i10 <= 31; i10++) {
            f14455s[i10] = String.format("\\u%04x", Integer.valueOf(i10));
        }
        String[] strArr = f14455s;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
    }

    public q(xb.j jVar) {
        int[] iArr = new int[32];
        this.f14459l = iArr;
        this.f14460m = new String[32];
        this.f14461n = new int[32];
        this.f14463p = -1;
        this.f14456q = jVar;
        this.f14458i = 1;
        iArr[0] = 6;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void R(xb.j r6, java.lang.String r7) {
        /*
            r0 = 34
            r6.M0(r0)
            int r1 = r7.length()
            r2 = 0
            r3 = r2
        Lb:
            if (r2 >= r1) goto L36
            char r4 = r7.charAt(r2)
            r5 = 128(0x80, float:1.8E-43)
            if (r4 >= r5) goto L1c
            java.lang.String[] r5 = com.squareup.moshi.q.f14455s
            r4 = r5[r4]
            if (r4 != 0) goto L29
            goto L33
        L1c:
            r5 = 8232(0x2028, float:1.1535E-41)
            if (r4 != r5) goto L23
            java.lang.String r4 = "\\u2028"
            goto L29
        L23:
            r5 = 8233(0x2029, float:1.1537E-41)
            if (r4 != r5) goto L33
            java.lang.String r4 = "\\u2029"
        L29:
            if (r3 >= r2) goto L2e
            r6.R0(r3, r2, r7)
        L2e:
            r6.S0(r4)
            int r3 = r2 + 1
        L33:
            int r2 = r2 + 1
            goto Lb
        L36:
            if (r3 >= r1) goto L3b
            r6.R0(r3, r1, r7)
        L3b:
            r6.M0(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.q.R(xb.j, java.lang.String):void");
    }

    public final q G() {
        if (this.f14462o) {
            throw new IllegalStateException("null cannot be used as a map key in JSON at path " + i());
        }
        if (this.f14457r != null) {
            this.f14457r = null;
            return this;
        }
        k();
        this.f14456q.S0("null");
        int[] iArr = this.f14461n;
        int i10 = this.f14458i - 1;
        iArr[i10] = iArr[i10] + 1;
        return this;
    }

    public final void O(int i10, int i11, char c10) {
        int i12;
        int i13 = this.f14458i;
        int i14 = this.f14463p;
        if (i13 == i14 && ((i12 = this.f14459l[i13 - 1]) == i10 || i12 == i11)) {
            this.f14463p = ~i14;
            return;
        }
        k();
        int i15 = this.f14458i;
        int[] iArr = this.f14459l;
        if (i15 == iArr.length) {
            if (i15 == 256) {
                throw new JsonDataException("Nesting too deep at " + i() + ": circular reference?");
            }
            this.f14459l = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.f14460m;
            this.f14460m = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
            int[] iArr2 = this.f14461n;
            this.f14461n = Arrays.copyOf(iArr2, iArr2.length * 2);
        }
        int[] iArr3 = this.f14459l;
        int i16 = this.f14458i;
        this.f14458i = i16 + 1;
        iArr3[i16] = i10;
        this.f14461n[i16] = 0;
        this.f14456q.M0(c10);
    }

    public final q T(long j10) {
        if (this.f14462o) {
            this.f14462o = false;
            y(Long.toString(j10));
            return this;
        }
        b0();
        k();
        this.f14456q.S0(Long.toString(j10));
        int[] iArr = this.f14461n;
        int i10 = this.f14458i - 1;
        iArr[i10] = iArr[i10] + 1;
        return this;
    }

    public final q V(String str) {
        if (str == null) {
            G();
            return this;
        }
        if (this.f14462o) {
            this.f14462o = false;
            y(str);
            return this;
        }
        b0();
        k();
        R(this.f14456q, str);
        int[] iArr = this.f14461n;
        int i10 = this.f14458i - 1;
        iArr[i10] = iArr[i10] + 1;
        return this;
    }

    public final void b0() {
        if (this.f14457r != null) {
            int iJ = j();
            xb.j jVar = this.f14456q;
            if (iJ == 5) {
                jVar.M0(44);
            } else if (iJ != 3) {
                throw new IllegalStateException("Nesting problem.");
            }
            this.f14459l[this.f14458i - 1] = 4;
            R(jVar, this.f14457r);
            this.f14457r = null;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        int i10 = this.f14458i;
        if (i10 > 1 || (i10 == 1 && this.f14459l[i10 - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.f14458i = 0;
    }

    @Override // java.io.Flushable
    public final void flush() {
        if (this.f14458i == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
    }

    public final void k() {
        int iJ = j();
        int i10 = 2;
        if (iJ != 1) {
            xb.j jVar = this.f14456q;
            if (iJ == 2) {
                jVar.M0(44);
            } else if (iJ == 4) {
                jVar.S0(":");
                i10 = 5;
            } else {
                if (iJ == 9) {
                    throw new IllegalStateException("Sink from valueSink() was not closed");
                }
                if (iJ != 6) {
                    if (iJ != 7) {
                        throw new IllegalStateException("Nesting problem.");
                    }
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
                i10 = 7;
            }
        }
        this.f14459l[this.f14458i - 1] = i10;
    }

    public final q l() {
        if (this.f14462o) {
            throw new IllegalStateException("Array cannot be used as a map key in JSON at path " + i());
        }
        b0();
        O(1, 2, '[');
        return this;
    }

    public final q o() {
        if (this.f14462o) {
            throw new IllegalStateException("Object cannot be used as a map key in JSON at path " + i());
        }
        b0();
        O(3, 5, '{');
        return this;
    }

    public final void x(int i10, int i11, char c10) {
        int iJ = j();
        if (iJ != i11 && iJ != i10) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.f14457r != null) {
            throw new IllegalStateException("Dangling name: " + this.f14457r);
        }
        int i12 = this.f14458i;
        int i13 = ~this.f14463p;
        if (i12 == i13) {
            this.f14463p = i13;
            return;
        }
        int i14 = i12 - 1;
        this.f14458i = i14;
        this.f14460m[i14] = null;
        int[] iArr = this.f14461n;
        int i15 = i12 - 2;
        iArr[i15] = iArr[i15] + 1;
        this.f14456q.M0(c10);
    }

    public final q y(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        if (this.f14458i == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        int iJ = j();
        if ((iJ != 3 && iJ != 5) || this.f14457r != null || this.f14462o) {
            throw new IllegalStateException("Nesting problem.");
        }
        this.f14457r = str;
        this.f14460m[this.f14458i - 1] = str;
        return this;
    }
}
