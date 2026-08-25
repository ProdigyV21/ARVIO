package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.Stack;

/* JADX INFO: loaded from: classes4.dex */
public final class g1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f14541a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g1 f14542b;

    public g1(String str, g1 g1Var) {
        this.f14541a = str;
        this.f14542b = g1Var;
    }

    public static g1 c(String str) {
        String strSubstring;
        int iCodePointAt;
        int i10;
        g1 g1VarB;
        w1 w1Var = j1.f14561a;
        int length = str.length();
        if (length == 0) {
            strSubstring = str;
        } else {
            int iCharCount = 0;
            while (iCharCount < length) {
                char cCharAt = str.charAt(iCharCount);
                if (cCharAt != ' ' && cCharAt != '\n') {
                    int iCodePointAt2 = str.codePointAt(iCharCount);
                    if (!y.c(iCodePointAt2)) {
                        break;
                    }
                    iCharCount = Character.charCount(iCodePointAt2) + iCharCount;
                } else {
                    iCharCount++;
                }
            }
            while (length > iCharCount) {
                int i11 = length - 1;
                char cCharAt2 = str.charAt(i11);
                if (cCharAt2 != ' ' && cCharAt2 != '\n') {
                    if (Character.isLowSurrogate(cCharAt2)) {
                        iCodePointAt = str.codePointAt(length - 2);
                        i10 = 2;
                    } else {
                        iCodePointAt = str.codePointAt(i11);
                        i10 = 1;
                    }
                    if (!y.c(iCodePointAt)) {
                        break;
                    }
                    length -= i10;
                } else {
                    length--;
                }
            }
            strSubstring = str.substring(iCharCount, length);
        }
        int length2 = strSubstring.length();
        if (strSubstring.isEmpty() || strSubstring.charAt(0) == '.' || strSubstring.charAt(length2 - 1) == '.') {
            g1VarB = null;
        } else {
            int i12 = 0;
            boolean z = true;
            while (true) {
                if (i12 < length2) {
                    char cCharAt3 = strSubstring.charAt(i12);
                    if ((cCharAt3 >= 'a' && cCharAt3 <= 'z') || ((cCharAt3 >= 'A' && cCharAt3 <= 'Z') || cCharAt3 == '_')) {
                        z = false;
                    } else if (cCharAt3 == '.') {
                        if (z) {
                            break;
                        }
                        z = true;
                    } else if (cCharAt3 != '-' || z) {
                        break;
                    }
                    i12++;
                } else {
                    if (z) {
                        break;
                    }
                    g1VarB = j1.b(null, strSubstring, strSubstring.length());
                }
            }
            g1VarB = null;
        }
        if (g1VarB != null) {
            return g1VarB;
        }
        StringReader stringReader = new StringReader(str);
        try {
            w1 w1Var2 = j1.f14561a;
            e2 e2Var = new e2(w1Var2, stringReader, true);
            e2Var.next();
            return j1.c(e2Var, w1Var2, str, null);
        } finally {
            stringReader.close();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0027 A[EDGE_INSN: B:23:0x0027->B:16:0x0027 BREAK  A[LOOP:0: B:6:0x000a->B:13:0x001e]] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(java.lang.StringBuilder r6) {
        /*
            r5 = this;
            java.lang.String r0 = r5.f14541a
            int r1 = r0.length()
            if (r1 != 0) goto L9
            goto L21
        L9:
            r2 = 0
        La:
            if (r2 >= r1) goto L21
            char r3 = r0.charAt(r2)
            boolean r4 = java.lang.Character.isLetterOrDigit(r3)
            if (r4 != 0) goto L1e
            r4 = 45
            if (r3 == r4) goto L1e
            r4 = 95
            if (r3 != r4) goto L27
        L1e:
            int r2 = r2 + 1
            goto La
        L21:
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L2f
        L27:
            java.lang.String r0 = com.typesafe.config.impl.y.d(r0)
            r6.append(r0)
            goto L32
        L2f:
            r6.append(r0)
        L32:
            com.typesafe.config.impl.g1 r0 = r5.f14542b
            if (r0 == 0) goto L3e
            java.lang.String r1 = "."
            r6.append(r1)
            r0.a(r6)
        L3e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.typesafe.config.impl.g1.a(java.lang.StringBuilder):void");
    }

    public final int b() {
        int i10 = 1;
        for (g1 g1Var = this.f14542b; g1Var != null; g1Var = g1Var.f14542b) {
            i10++;
        }
        return i10;
    }

    public final g1 d() {
        g1 g1Var = null;
        if (this.f14542b == null) {
            return null;
        }
        Stack stack = new Stack();
        for (g1 g1Var2 = this; g1Var2.f14542b != null; g1Var2 = g1Var2.f14542b) {
            stack.push(g1Var2.f14541a);
        }
        while (!stack.isEmpty()) {
            g1Var = new g1((String) stack.pop(), g1Var);
        }
        return g1Var;
    }

    public final String e() {
        StringBuilder sb2 = new StringBuilder();
        a(sb2);
        return sb2.toString();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof g1) {
            g1 g1Var = (g1) obj;
            if (this.f14541a.equals(g1Var.f14541a) && y.a(this.f14542b, g1Var.f14542b)) {
                return true;
            }
        }
        return false;
    }

    public final g1 f(int i10) {
        g1 g1Var = null;
        if (i10 < 0) {
            throw new ConfigException.BugOrBroken(null, "bad call to subPath");
        }
        Stack stack = new Stack();
        g1 g1Var2 = this;
        int i11 = i10;
        while (i11 > 0) {
            i11--;
            stack.push(g1Var2.f14541a);
            g1Var2 = g1Var2.f14542b;
            if (g1Var2 == null) {
                throw new ConfigException.BugOrBroken(null, a0.c.i(i10, "subPath lastIndex out of range "));
            }
        }
        while (!stack.isEmpty()) {
            g1Var = new g1((String) stack.pop(), g1Var);
        }
        return g1Var;
    }

    public final int hashCode() {
        int iC = androidx.compose.foundation.c.c(41, 41, this.f14541a);
        g1 g1Var = this.f14542b;
        return iC + (g1Var == null ? 0 : g1Var.hashCode());
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Path(");
        a(sb2);
        sb2.append(")");
        return sb2.toString();
    }

    public g1(Iterator it) {
        if (it.hasNext()) {
            g1 g1Var = (g1) it.next();
            this.f14541a = g1Var.f14541a;
            h1 h1Var = new h1();
            g1 g1Var2 = g1Var.f14542b;
            if (g1Var2 != null) {
                h1Var.a(g1Var2);
            }
            while (it.hasNext()) {
                h1Var.a((g1) it.next());
            }
            this.f14542b = h1Var.b();
            return;
        }
        throw new ConfigException.BugOrBroken(null, "empty path");
    }
}
