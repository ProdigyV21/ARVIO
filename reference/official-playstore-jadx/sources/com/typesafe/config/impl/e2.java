package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes4.dex */
public final class e2 implements Iterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final w1 f14521i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Reader f14522l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final LinkedList f14523m = new LinkedList();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f14524n = 1;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public w1 f14525o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final LinkedList f14526p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final com.google.android.gms.internal.auth.d1 f14527q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f14528r;

    public e2(l6.k kVar, Reader reader, boolean z) {
        w1 w1Var = (w1) kVar;
        this.f14521i = w1Var;
        this.f14522l = reader;
        this.f14528r = z;
        this.f14525o = w1Var.b(1);
        LinkedList linkedList = new LinkedList();
        this.f14526p = linkedList;
        linkedList.add(m2.f14581a);
        this.f14527q = new com.google.android.gms.internal.auth.d1();
    }

    public static d2 b(w1 w1Var, String str, String str2, boolean z, Throwable th) {
        if (str2 == null) {
            throw new ConfigException.BugOrBroken(null, "internal error, creating bad ProblemException");
        }
        c2 c2Var = m2.f14581a;
        return new d2(new i2(w1Var, str, str2, z, th));
    }

    public final int a() {
        LinkedList linkedList = this.f14523m;
        if (!linkedList.isEmpty()) {
            return ((Integer) linkedList.pop()).intValue();
        }
        try {
            return this.f14522l.read();
        } catch (IOException e5) {
            throw new ConfigException.IO(this.f14521i, "read error: " + e5.getMessage(), e5);
        }
    }

    public final c2 c(com.google.android.gms.internal.auth.d1 d1Var) throws d2 {
        int iA;
        c2 l2Var;
        int i10;
        boolean z;
        boolean z5;
        int iA2;
        while (true) {
            iA = a();
            if (iA == -1) {
                iA = -1;
                break;
            }
            if (iA == 10 || !y.c(iA)) {
                break;
            }
            ((StringBuilder) d1Var.f13018c).appendCodePoint(iA);
        }
        if (iA == -1) {
            return m2.f14582b;
        }
        w1 w1Var = this.f14521i;
        if (iA == 10) {
            w1 w1Var2 = this.f14525o;
            c2 c2Var = m2.f14581a;
            h2 h2Var = new h2(11, w1Var2, null, null);
            int i11 = this.f14524n + 1;
            this.f14524n = i11;
            this.f14525o = w1Var.b(i11);
            return h2Var;
        }
        if (e(iA)) {
            if (iA != 47) {
                z5 = false;
            } else {
                if (a() != 47) {
                    throw new ConfigException.BugOrBroken(null, "called pullComment but // not seen");
                }
                z5 = true;
            }
            StringBuilder sb2 = new StringBuilder();
            while (true) {
                iA2 = a();
                if (iA2 == -1 || iA2 == 10) {
                    break;
                }
                sb2.appendCodePoint(iA2);
            }
            d(iA2);
            if (z5) {
                w1 w1Var3 = this.f14525o;
                String string = sb2.toString();
                c2 c2Var2 = m2.f14581a;
                return new f2(w1Var3, string, 0);
            }
            w1 w1Var4 = this.f14525o;
            String string2 = sb2.toString();
            c2 c2Var3 = m2.f14581a;
            return new f2(w1Var4, string2, 1);
        }
        int i12 = 4;
        if (iA == 34) {
            StringBuilder sb3 = new StringBuilder();
            StringBuilder sb4 = new StringBuilder();
            sb4.appendCodePoint(34);
            while (true) {
                int iA3 = a();
                if (iA3 == -1) {
                    throw b(this.f14525o, "", "End of input but string quote was still open", false, null);
                }
                if (iA3 == 92) {
                    int iA4 = a();
                    if (iA4 == -1) {
                        throw b(this.f14525o, "", "End of input but backslash in string had nothing after it", false, null);
                    }
                    sb4.appendCodePoint(92);
                    sb4.appendCodePoint(iA4);
                    if (iA4 == 34) {
                        sb3.append('\"');
                    } else if (iA4 == 47) {
                        sb3.append('/');
                    } else if (iA4 == 92) {
                        sb3.append('\\');
                    } else if (iA4 == 98) {
                        sb3.append('\b');
                    } else if (iA4 == 102) {
                        sb3.append('\f');
                    } else if (iA4 == 110) {
                        sb3.append('\n');
                    } else if (iA4 == 114) {
                        sb3.append('\r');
                    } else if (iA4 == 116) {
                        sb3.append('\t');
                    } else {
                        if (iA4 != 117) {
                            throw b(this.f14525o, r.a(iA4), a0.c.l("backslash followed by '", r.a(iA4), "', this is not a valid escape sequence (quoted strings use JSON escaping, so use double-backslash \\\\ for literal backslash)"), false, null);
                        }
                        char[] cArr = new char[i12];
                        int i13 = 0;
                        while (i13 < i12) {
                            int iA5 = a();
                            if (iA5 == -1) {
                                throw b(this.f14525o, "", "End of input but expecting 4 hex digits for \\uXXXX escape", false, null);
                            }
                            cArr[i13] = (char) iA5;
                            i13++;
                            i12 = 4;
                        }
                        String str = new String(cArr);
                        sb4.append(cArr);
                        try {
                            sb3.appendCodePoint(Integer.parseInt(str, 16));
                        } catch (NumberFormatException e5) {
                            throw b(this.f14525o, str, a0.c.l("Malformed hex digits after \\u escape in string: '", str, "'"), false, e5);
                        }
                    }
                    i10 = 4;
                } else if (iA3 == 34) {
                    sb4.appendCodePoint(iA3);
                    if (sb3.length() == 0) {
                        int iA6 = a();
                        if (iA6 == 34) {
                            sb4.appendCodePoint(iA6);
                            int i14 = 0;
                            while (true) {
                                int iA7 = a();
                                if (iA7 == 34) {
                                    i14++;
                                } else {
                                    if (i14 >= 3) {
                                        sb3.setLength(sb3.length() - 3);
                                        d(iA7);
                                        break;
                                    }
                                    if (iA7 == -1) {
                                        throw b(this.f14525o, "", "End of input but triple-quoted string was still open", false, null);
                                    }
                                    if (iA7 == 10) {
                                        int i15 = this.f14524n + 1;
                                        this.f14524n = i15;
                                        this.f14525o = w1Var.b(i15);
                                    }
                                    i14 = 0;
                                }
                                sb3.appendCodePoint(iA7);
                                sb4.appendCodePoint(iA7);
                            }
                        } else {
                            d(iA6);
                        }
                    }
                    w1 w1Var5 = this.f14525o;
                    String string3 = sb3.toString();
                    String string4 = sb4.toString();
                    c2 c2Var4 = m2.f14581a;
                    l2Var = new l2(new q0(w1Var5, string3), string4);
                } else {
                    i10 = 4;
                    if (iA3 >= 0 && iA3 <= 31) {
                        throw b(this.f14525o, r.a(iA3), "JSON does not allow unescaped " + r.a(iA3) + " in quoted strings, use a backslash escape", false, null);
                    }
                    sb3.appendCodePoint(iA3);
                    sb4.appendCodePoint(iA3);
                }
                i12 = i10;
            }
        } else if (iA == 36) {
            w1 w1Var6 = this.f14525o;
            int iA8 = a();
            if (iA8 != 123) {
                throw b(this.f14525o, r.a(iA8), "'$' not followed by {, '" + r.a(iA8) + "' not allowed after '$'", true, null);
            }
            int iA9 = a();
            if (iA9 == 63) {
                z = true;
            } else {
                d(iA9);
                z = false;
            }
            com.google.android.gms.internal.auth.d1 d1Var2 = new com.google.android.gms.internal.auth.d1();
            ArrayList arrayList = new ArrayList();
            while (true) {
                c2 c2VarC = c(d1Var2);
                if (c2VarC == m2.f14587g) {
                    l2Var = new j2(w1Var6, z, arrayList);
                    break;
                }
                if (c2VarC == m2.f14582b) {
                    throw b(w1Var6, "", "Substitution ${ was not closed with a }", false, null);
                }
                c2 c2VarA = d1Var2.a(c2VarC, w1Var6, this.f14524n);
                if (c2VarA != null) {
                    arrayList.add(c2VarA);
                }
                arrayList.add(c2VarC);
            }
        } else if (iA == 58) {
            l2Var = m2.f14585e;
        } else if (iA == 61) {
            l2Var = m2.f14584d;
        } else if (iA == 91) {
            l2Var = m2.f14588h;
        } else if (iA == 93) {
            l2Var = m2.f14589i;
        } else if (iA == 123) {
            l2Var = m2.f14586f;
        } else if (iA == 125) {
            l2Var = m2.f14587g;
        } else if (iA != 43) {
            l2Var = iA != 44 ? null : m2.f14583c;
        } else {
            int iA10 = a();
            if (iA10 != 61) {
                throw b(this.f14525o, r.a(iA10), "'+' not followed by =, '" + r.a(iA10) + "' not allowed after '+'", true, null);
            }
            l2Var = m2.f14590j;
        }
        if (l2Var != null) {
            return l2Var;
        }
        if ("0123456789-".indexOf(iA) < 0) {
            if ("$\"{}[]:=,+#`^?!@*&\\".indexOf(iA) >= 0) {
                throw b(this.f14525o, r.a(iA), "Reserved character '" + r.a(iA) + "' is not allowed outside quotes", true, null);
            }
            d(iA);
            w1 w1Var7 = this.f14525o;
            StringBuilder sb5 = new StringBuilder();
            int iA11 = a();
            while (iA11 != -1 && "$\"{}[]:=,+#`^?!@*&\\".indexOf(iA11) < 0 && !y.c(iA11) && !e(iA11)) {
                sb5.appendCodePoint(iA11);
                if (sb5.length() == 4) {
                    String string5 = sb5.toString();
                    if (string5.equals("true")) {
                        c2 c2Var5 = m2.f14581a;
                        return new l2(new i(w1Var7, true), "true");
                    }
                    if (string5.equals("null")) {
                        c2 c2Var6 = m2.f14581a;
                        return new l2(new m0(w1Var7), "null");
                    }
                } else if (sb5.length() == 5 && sb5.toString().equals("false")) {
                    c2 c2Var7 = m2.f14581a;
                    return new l2(new i(w1Var7, false), "false");
                }
                iA11 = a();
            }
            d(iA11);
            String string6 = sb5.toString();
            c2 c2Var8 = m2.f14581a;
            return new k2(w1Var7, string6);
        }
        StringBuilder sb6 = new StringBuilder();
        sb6.appendCodePoint(iA);
        int iA12 = a();
        boolean z10 = false;
        while (iA12 != -1 && "0123456789eE+-.".indexOf(iA12) >= 0) {
            if (iA12 == 46 || iA12 == 101 || iA12 == 69) {
                z10 = true;
            }
            sb6.appendCodePoint(iA12);
            iA12 = a();
        }
        d(iA12);
        String string7 = sb6.toString();
        try {
            if (!z10) {
                w1 w1Var8 = this.f14525o;
                long j10 = Long.parseLong(string7);
                c2 c2Var9 = m2.f14581a;
                return new l2((j10 > 2147483647L || j10 < -2147483648L) ? new a0(w1Var8, j10, string7) : new z(w1Var8, (int) j10, string7), string7);
            }
            w1 w1Var9 = this.f14525o;
            double d4 = Double.parseDouble(string7);
            c2 c2Var10 = m2.f14581a;
            long j11 = (long) d4;
            return new l2(((double) j11) == d4 ? (j11 > 2147483647L || j11 < -2147483648L) ? new a0(w1Var9, j11, string7) : new z(w1Var9, (int) j11, string7) : new n(w1Var9, d4, string7), string7);
        } catch (NumberFormatException unused) {
            for (char c10 : string7.toCharArray()) {
                if ("$\"{}[]:=,+#`^?!@*&\\".indexOf(c10) >= 0) {
                    throw b(this.f14525o, r.a(c10), "Reserved character '" + r.a(c10) + "' is not allowed outside quotes", true, null);
                }
            }
            w1 w1Var10 = this.f14525o;
            c2 c2Var11 = m2.f14581a;
            return new k2(w1Var10, string7);
        }
    }

    public final void d(int i10) {
        LinkedList linkedList = this.f14523m;
        if (linkedList.size() > 2) {
            throw new ConfigException.BugOrBroken(null, "bug: putBack() three times, undesirable look-ahead");
        }
        linkedList.push(Integer.valueOf(i10));
    }

    public final boolean e(int i10) {
        if (i10 != -1 && this.f14528r) {
            if (i10 == 35) {
                return true;
            }
            if (i10 == 47) {
                int iA = a();
                d(iA);
                if (iA == 47) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return !this.f14526p.isEmpty();
    }

    @Override // java.util.Iterator
    public final Object next() {
        LinkedList linkedList = this.f14526p;
        c2 c2Var = (c2) linkedList.remove();
        if (linkedList.isEmpty() && c2Var != m2.f14582b) {
            try {
                com.google.android.gms.internal.auth.d1 d1Var = this.f14527q;
                c2 c2VarC = c(d1Var);
                c2 c2VarA = d1Var.a(c2VarC, this.f14521i, this.f14524n);
                if (c2VarA != null) {
                    linkedList.add(c2VarA);
                }
                linkedList.add(c2VarC);
            } catch (d2 e5) {
                linkedList.add(e5.f14520i);
            }
            if (linkedList.isEmpty()) {
                throw new ConfigException.BugOrBroken(null, "bug: tokens queue should not be empty here");
            }
        }
        return c2Var;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Does not make sense to remove items from token stream");
    }
}
