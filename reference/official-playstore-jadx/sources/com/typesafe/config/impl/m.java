package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.util.ArrayList;
import java.util.Stack;

/* JADX INFO: loaded from: classes4.dex */
public final class m {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final e2 f14572c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f14573d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final l6.k f14574e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f14570a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Stack f14571b = new Stack();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f14575f = 0;

    public m(int i10, l6.k kVar, e2 e2Var) {
        this.f14572c = e2Var;
        this.f14573d = i10;
        this.f14574e = kVar;
    }

    public static String b(String str, String str2, boolean z) {
        if (str.equals(m2.f14582b.toString())) {
            return str2;
        }
        return androidx.compose.material3.d.m(str2 + " (if you intended " + str + " to be part of a key or string value, try enclosing the key or value in double quotes", z ? ", or you may be able to rename the file .properties rather than .conf)" : ")");
    }

    public static boolean e(c2 c2Var) {
        c2 c2Var2 = m2.f14581a;
        if (!(c2Var instanceof k2)) {
            return false;
        }
        String strA = m2.a(c2Var);
        for (int i10 = 0; i10 < strA.length(); i10++) {
            if (!y.c(strA.charAt(i10))) {
                return false;
            }
        }
        return true;
    }

    public final String a(String str, String str2) {
        return b(str, str2, this.f14575f > 0);
    }

    public final boolean c(ArrayList arrayList) {
        boolean z = false;
        if (this.f14573d == 1) {
            c2 c2VarG = g(arrayList);
            if (c2VarG == m2.f14583c) {
                arrayList.add(new l0(c2VarG));
                return true;
            }
            l(c2VarG);
            return false;
        }
        c2 c2VarF = f();
        while (true) {
            c2 c2Var = m2.f14581a;
            if (!(c2VarF instanceof g2) && !e(c2VarF)) {
                if (!(c2VarF instanceof f2)) {
                    if (!(c2VarF instanceof h2)) {
                        break;
                    }
                    this.f14570a++;
                    arrayList.add(new l0(c2VarF));
                    z = true;
                } else {
                    arrayList.add(new c0(c2VarF));
                }
            } else {
                arrayList.add(new l0(c2VarF));
            }
            c2VarF = f();
        }
        if (c2VarF == m2.f14583c) {
            arrayList.add(new l0(c2VarF));
            return true;
        }
        l(c2VarF);
        return z;
    }

    public final b d(ArrayList arrayList) {
        b bVar = null;
        if (this.f14573d == 1) {
            return null;
        }
        ArrayList<a> arrayList2 = new ArrayList();
        c2 c2VarG = g(arrayList);
        int i10 = 0;
        while (true) {
            c2 c2Var = m2.f14581a;
            if (!(c2VarG instanceof g2)) {
                if (!(c2VarG instanceof l2) && !(c2VarG instanceof k2) && !(c2VarG instanceof j2) && c2VarG != m2.f14586f && c2VarG != m2.f14588h) {
                    break;
                }
                i10++;
                arrayList2.add(k(c2VarG));
                c2VarG = f();
            } else {
                arrayList2.add(new l0(c2VarG));
                c2VarG = f();
            }
        }
        l(c2VarG);
        if (i10 >= 2) {
            for (int size = arrayList2.size() - 1; size >= 0 && (arrayList2.get(size) instanceof l0); size--) {
                l(((l0) arrayList2.get(size)).f14568a);
                arrayList2.remove(size);
            }
            return new e0(arrayList2);
        }
        for (a aVar : arrayList2) {
            if (aVar instanceof b) {
                bVar = (b) aVar;
            } else if (bVar == null) {
                arrayList.add(aVar);
            } else {
                l((c2) new ArrayList(aVar.b()).get(0));
            }
        }
        return bVar;
    }

    public final c2 f() {
        Stack stack = this.f14571b;
        c2 c2Var = stack.isEmpty() ? (c2) this.f14572c.next() : (c2) stack.pop();
        if (this.f14573d == 1) {
            c2 c2Var2 = m2.f14581a;
            if ((c2Var instanceof k2) && !e(c2Var)) {
                throw h("Token not allowed in valid JSON: '" + m2.a(c2Var) + "'");
            }
            if (c2Var instanceof j2) {
                throw h("Substitutions (${} syntax) not allowed in JSON");
            }
        }
        return c2Var;
    }

    public final c2 g(ArrayList arrayList) {
        c2 c2VarF;
        while (true) {
            c2VarF = f();
            c2 c2Var = m2.f14581a;
            if (!(c2VarF instanceof g2) && !(c2VarF instanceof h2) && !e(c2VarF)) {
                if (!(c2VarF instanceof f2)) {
                    break;
                }
                arrayList.add(new c0(c2VarF));
            } else {
                arrayList.add(new l0(c2VarF));
                if (c2VarF instanceof h2) {
                    this.f14570a = c2VarF.b() + 1;
                }
            }
        }
        int iB = c2VarF.b();
        if (iB >= 0) {
            this.f14570a = iB;
        }
        return c2VarF;
    }

    public final ConfigException.Parse h(String str) {
        return new ConfigException.Parse(this.f14574e.b(this.f14570a), str, null);
    }

    public final g0 i(ArrayList arrayList, boolean z) {
        int i10;
        c2 c2VarG = g(arrayList);
        c2 c2Var = m2.f14581a;
        if (!(c2VarG instanceof k2)) {
            if (m2.c(c2VarG)) {
                arrayList.add(new k0(c2VarG));
                return new g0(4, arrayList, z);
            }
            throw h("include keyword is not followed by a quoted string, but by: " + c2VarG);
        }
        String strA = m2.a(c2VarG);
        String str = "url(";
        if (strA.startsWith("url(")) {
            i10 = 1;
        } else {
            str = "file(";
            if (strA.startsWith("file(")) {
                i10 = 2;
            } else {
                str = "classpath(";
                if (!strA.startsWith("classpath(")) {
                    throw h("expecting include parameter to be quoted filename, file(), classpath(), or url(). No spaces are allowed before the open paren. Not expecting: " + c2VarG);
                }
                i10 = 3;
            }
        }
        String strReplaceFirst = strA.replaceFirst("[^(]*\\(", "");
        if (strReplaceFirst.length() > 0) {
            l(new k2(c2VarG.d(), strReplaceFirst));
        }
        arrayList.add(new l0(c2VarG));
        c2 c2VarG2 = g(arrayList);
        if (!m2.c(c2VarG2)) {
            throw h("expecting include " + str + ") parameter to be a quoted string, rather than: " + c2VarG2);
        }
        arrayList.add(new k0(c2VarG2));
        c2 c2VarG3 = g(arrayList);
        if (!(c2VarG3 instanceof k2) || !m2.a(c2VarG3).startsWith(")")) {
            throw h("expecting a close parentheses ')' here, not: " + c2VarG3);
        }
        String strSubstring = m2.a(c2VarG3).substring(1);
        if (strSubstring.length() > 0) {
            l(new k2(c2VarG3.d(), strSubstring));
        }
        return new g0(i10, arrayList, z);
    }

    /* JADX WARN: Code restructure failed: missing block: B:115:0x0260, code lost:
    
        return new com.typesafe.config.impl.h0(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00df, code lost:
    
        throw h("expecting a close parentheses ')' here, not: " + r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x01b8, code lost:
    
        throw h(a(r3.toString(), "Key '" + r5.a() + "' may not be followed by token: " + r3));
     */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01d3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.typesafe.config.impl.h0 j(boolean r15) {
        /*
            Method dump skipped, instruction units count: 698
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.typesafe.config.impl.m.j(boolean):com.typesafe.config.impl.h0");
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x0139 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x013a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.typesafe.config.impl.b k(com.typesafe.config.impl.c2 r9) {
        /*
            Method dump skipped, instruction units count: 323
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.typesafe.config.impl.m.k(com.typesafe.config.impl.c2):com.typesafe.config.impl.b");
    }

    public final void l(c2 c2Var) {
        this.f14571b.push(c2Var);
    }
}
