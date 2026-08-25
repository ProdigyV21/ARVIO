package androidx.datastore.preferences.protobuf;

import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException;
import java.nio.charset.Charset;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class r implements n2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q f2512a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2513b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2514c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2515d = 0;

    public r(q qVar) {
        Charset charset = f1.f2421a;
        this.f2512a = qVar;
        qVar.f2506b = this;
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final void A(List list) throws InvalidProtocolBufferException {
        int iU;
        int i10 = this.f2513b & 7;
        q qVar = this.f2512a;
        if (i10 == 0) {
            do {
                list.add(Integer.valueOf(qVar.m()));
                if (qVar.c()) {
                    return;
                } else {
                    iU = qVar.u();
                }
            } while (iU == this.f2513b);
            this.f2515d = iU;
            return;
        }
        if (i10 != 2) {
            throw InvalidProtocolBufferException.b();
        }
        int iB = qVar.b() + qVar.v();
        do {
            list.add(Integer.valueOf(qVar.m()));
        } while (qVar.b() < iB);
        R(iB);
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final void B(List list) throws InvalidProtocolBufferException {
        int iU;
        int i10 = this.f2513b & 7;
        q qVar = this.f2512a;
        if (i10 == 2) {
            int iV = qVar.v();
            if ((iV & 3) != 0) {
                throw InvalidProtocolBufferException.e();
            }
            int iB = qVar.b() + iV;
            do {
                list.add(Integer.valueOf(qVar.j()));
            } while (qVar.b() < iB);
            return;
        }
        if (i10 != 5) {
            throw InvalidProtocolBufferException.b();
        }
        do {
            list.add(Integer.valueOf(qVar.j()));
            if (qVar.c()) {
                return;
            } else {
                iU = qVar.u();
            }
        } while (iU == this.f2513b);
        this.f2515d = iU;
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final long C() throws InvalidProtocolBufferException.InvalidWireTypeException {
        S(0);
        return this.f2512a.r();
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final String D() throws InvalidProtocolBufferException.InvalidWireTypeException {
        S(2);
        return this.f2512a.s();
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final int E() {
        int i10 = this.f2515d;
        if (i10 != 0) {
            this.f2513b = i10;
            this.f2515d = 0;
        } else {
            this.f2513b = this.f2512a.u();
        }
        int i11 = this.f2513b;
        if (i11 == 0 || i11 == this.f2514c) {
            return Integer.MAX_VALUE;
        }
        return i11 >>> 3;
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final void F(List list) throws InvalidProtocolBufferException.InvalidWireTypeException {
        Q(list, false);
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final void G(List list) throws InvalidProtocolBufferException {
        int iU;
        int i10 = this.f2513b & 7;
        q qVar = this.f2512a;
        if (i10 == 2) {
            int iV = qVar.v();
            if ((iV & 3) != 0) {
                throw InvalidProtocolBufferException.e();
            }
            int iB = qVar.b() + iV;
            do {
                list.add(Float.valueOf(qVar.l()));
            } while (qVar.b() < iB);
            return;
        }
        if (i10 != 5) {
            throw InvalidProtocolBufferException.b();
        }
        do {
            list.add(Float.valueOf(qVar.l()));
            if (qVar.c()) {
                return;
            } else {
                iU = qVar.u();
            }
        } while (iU == this.f2513b);
        this.f2515d = iU;
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final void H(List list, o2 o2Var, d0 d0Var) throws InvalidProtocolBufferException.InvalidWireTypeException {
        int iU;
        int i10 = this.f2513b;
        if ((i10 & 7) != 3) {
            throw InvalidProtocolBufferException.b();
        }
        do {
            list.add(O(o2Var, d0Var));
            q qVar = this.f2512a;
            if (qVar.c() || this.f2515d != 0) {
                return;
            } else {
                iU = qVar.u();
            }
        } while (iU == i10);
        this.f2515d = iU;
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final int I() throws InvalidProtocolBufferException.InvalidWireTypeException {
        S(5);
        return this.f2512a.o();
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final void J(List list) throws InvalidProtocolBufferException.InvalidWireTypeException {
        int iU;
        if ((this.f2513b & 7) != 2) {
            throw InvalidProtocolBufferException.b();
        }
        do {
            list.add(p());
            q qVar = this.f2512a;
            if (qVar.c()) {
                return;
            } else {
                iU = qVar.u();
            }
        } while (iU == this.f2513b);
        this.f2515d = iU;
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final void K(List list) throws InvalidProtocolBufferException {
        int iU;
        int i10 = this.f2513b & 7;
        q qVar = this.f2512a;
        if (i10 == 1) {
            do {
                list.add(Double.valueOf(qVar.h()));
                if (qVar.c()) {
                    return;
                } else {
                    iU = qVar.u();
                }
            } while (iU == this.f2513b);
            this.f2515d = iU;
            return;
        }
        if (i10 != 2) {
            throw InvalidProtocolBufferException.b();
        }
        int iV = qVar.v();
        if ((iV & 7) != 0) {
            throw InvalidProtocolBufferException.e();
        }
        int iB = qVar.b() + iV;
        do {
            list.add(Double.valueOf(qVar.h()));
        } while (qVar.b() < iB);
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final long L() throws InvalidProtocolBufferException.InvalidWireTypeException {
        S(0);
        return this.f2512a.n();
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final String M() throws InvalidProtocolBufferException.InvalidWireTypeException {
        S(2);
        return this.f2512a.t();
    }

    public final Object N(y3 y3Var, Class cls, d0 d0Var) throws InvalidProtocolBufferException.InvalidWireTypeException {
        switch (y3Var.ordinal()) {
            case 0:
                return Double.valueOf(readDouble());
            case 1:
                return Float.valueOf(readFloat());
            case 2:
                return Long.valueOf(L());
            case 3:
                return Long.valueOf(w());
            case 4:
                return Integer.valueOf(q());
            case 5:
                return Long.valueOf(c());
            case 6:
                return Integer.valueOf(y());
            case 7:
                return Boolean.valueOf(f());
            case 8:
                return M();
            case 9:
            default:
                throw new RuntimeException("unsupported field type.");
            case 10:
                S(2);
                return P(j2.f2452c.a(cls), d0Var);
            case 11:
                return p();
            case 12:
                return Integer.valueOf(i());
            case 13:
                return Integer.valueOf(l());
            case 14:
                return Integer.valueOf(I());
            case 15:
                return Long.valueOf(g());
            case 16:
                return Integer.valueOf(m());
            case 17:
                return Long.valueOf(C());
        }
    }

    public final Object O(o2 o2Var, d0 d0Var) {
        int i10 = this.f2514c;
        this.f2514c = ((this.f2513b >>> 3) << 3) | 4;
        try {
            Object objE = o2Var.e();
            o2Var.a(objE, this, d0Var);
            o2Var.b(objE);
            if (this.f2513b == this.f2514c) {
                return objE;
            }
            throw InvalidProtocolBufferException.e();
        } finally {
            this.f2514c = i10;
        }
    }

    public final Object P(o2 o2Var, d0 d0Var) throws InvalidProtocolBufferException {
        q qVar = this.f2512a;
        int iV = qVar.v();
        if (qVar.f2505a >= 100) {
            throw new InvalidProtocolBufferException("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int iE = qVar.e(iV);
        Object objE = o2Var.e();
        qVar.f2505a++;
        o2Var.a(objE, this, d0Var);
        o2Var.b(objE);
        qVar.a(0);
        qVar.f2505a--;
        qVar.d(iE);
        return objE;
    }

    public final void Q(List list, boolean z) throws InvalidProtocolBufferException.InvalidWireTypeException {
        int iU;
        int iU2;
        if ((this.f2513b & 7) != 2) {
            throw InvalidProtocolBufferException.b();
        }
        boolean z5 = list instanceof i1;
        q qVar = this.f2512a;
        if (!z5 || z) {
            do {
                list.add(z ? M() : D());
                if (qVar.c()) {
                    return;
                } else {
                    iU = qVar.u();
                }
            } while (iU == this.f2513b);
            this.f2515d = iU;
            return;
        }
        i1 i1Var = (i1) list;
        do {
            i1Var.b0(p());
            if (qVar.c()) {
                return;
            } else {
                iU2 = qVar.u();
            }
        } while (iU2 == this.f2513b);
        this.f2515d = iU2;
    }

    public final void R(int i10) throws InvalidProtocolBufferException {
        if (this.f2512a.b() != i10) {
            throw InvalidProtocolBufferException.f();
        }
    }

    public final void S(int i10) throws InvalidProtocolBufferException.InvalidWireTypeException {
        if ((this.f2513b & 7) != i10) {
            throw InvalidProtocolBufferException.b();
        }
    }

    public final boolean T() {
        int i10;
        q qVar = this.f2512a;
        if (qVar.c() || (i10 = this.f2513b) == this.f2514c) {
            return false;
        }
        return qVar.x(i10);
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final Object a(o2 o2Var, d0 d0Var) throws InvalidProtocolBufferException.InvalidWireTypeException {
        S(2);
        return P(o2Var, d0Var);
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final int b() {
        return this.f2513b;
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final long c() throws InvalidProtocolBufferException.InvalidWireTypeException {
        S(1);
        return this.f2512a.k();
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final void d(List list) throws InvalidProtocolBufferException {
        int iU;
        int i10 = this.f2513b & 7;
        q qVar = this.f2512a;
        if (i10 == 2) {
            int iV = qVar.v();
            if ((iV & 3) != 0) {
                throw InvalidProtocolBufferException.e();
            }
            int iB = qVar.b() + iV;
            do {
                list.add(Integer.valueOf(qVar.o()));
            } while (qVar.b() < iB);
            return;
        }
        if (i10 != 5) {
            throw InvalidProtocolBufferException.b();
        }
        do {
            list.add(Integer.valueOf(qVar.o()));
            if (qVar.c()) {
                return;
            } else {
                iU = qVar.u();
            }
        } while (iU == this.f2513b);
        this.f2515d = iU;
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final void e(List list) throws InvalidProtocolBufferException {
        int iU;
        int i10 = this.f2513b & 7;
        q qVar = this.f2512a;
        if (i10 == 0) {
            do {
                list.add(Long.valueOf(qVar.r()));
                if (qVar.c()) {
                    return;
                } else {
                    iU = qVar.u();
                }
            } while (iU == this.f2513b);
            this.f2515d = iU;
            return;
        }
        if (i10 != 2) {
            throw InvalidProtocolBufferException.b();
        }
        int iB = qVar.b() + qVar.v();
        do {
            list.add(Long.valueOf(qVar.r()));
        } while (qVar.b() < iB);
        R(iB);
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final boolean f() throws InvalidProtocolBufferException.InvalidWireTypeException {
        S(0);
        return this.f2512a.f();
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final long g() throws InvalidProtocolBufferException.InvalidWireTypeException {
        S(1);
        return this.f2512a.p();
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final void h(List list) throws InvalidProtocolBufferException {
        int iU;
        int i10 = this.f2513b & 7;
        q qVar = this.f2512a;
        if (i10 == 0) {
            do {
                list.add(Long.valueOf(qVar.w()));
                if (qVar.c()) {
                    return;
                } else {
                    iU = qVar.u();
                }
            } while (iU == this.f2513b);
            this.f2515d = iU;
            return;
        }
        if (i10 != 2) {
            throw InvalidProtocolBufferException.b();
        }
        int iB = qVar.b() + qVar.v();
        do {
            list.add(Long.valueOf(qVar.w()));
        } while (qVar.b() < iB);
        R(iB);
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final int i() throws InvalidProtocolBufferException.InvalidWireTypeException {
        S(0);
        return this.f2512a.v();
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final void j(List list) throws InvalidProtocolBufferException {
        int iU;
        int i10 = this.f2513b & 7;
        q qVar = this.f2512a;
        if (i10 == 0) {
            do {
                list.add(Long.valueOf(qVar.n()));
                if (qVar.c()) {
                    return;
                } else {
                    iU = qVar.u();
                }
            } while (iU == this.f2513b);
            this.f2515d = iU;
            return;
        }
        if (i10 != 2) {
            throw InvalidProtocolBufferException.b();
        }
        int iB = qVar.b() + qVar.v();
        do {
            list.add(Long.valueOf(qVar.n()));
        } while (qVar.b() < iB);
        R(iB);
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final void k(List list) throws InvalidProtocolBufferException {
        int iU;
        int i10 = this.f2513b & 7;
        q qVar = this.f2512a;
        if (i10 == 0) {
            do {
                list.add(Integer.valueOf(qVar.i()));
                if (qVar.c()) {
                    return;
                } else {
                    iU = qVar.u();
                }
            } while (iU == this.f2513b);
            this.f2515d = iU;
            return;
        }
        if (i10 != 2) {
            throw InvalidProtocolBufferException.b();
        }
        int iB = qVar.b() + qVar.v();
        do {
            list.add(Integer.valueOf(qVar.i()));
        } while (qVar.b() < iB);
        R(iB);
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final int l() throws InvalidProtocolBufferException.InvalidWireTypeException {
        S(0);
        return this.f2512a.i();
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final int m() throws InvalidProtocolBufferException.InvalidWireTypeException {
        S(0);
        return this.f2512a.q();
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final void n(List list) throws InvalidProtocolBufferException {
        int iU;
        int i10 = this.f2513b & 7;
        q qVar = this.f2512a;
        if (i10 == 0) {
            do {
                list.add(Boolean.valueOf(qVar.f()));
                if (qVar.c()) {
                    return;
                } else {
                    iU = qVar.u();
                }
            } while (iU == this.f2513b);
            this.f2515d = iU;
            return;
        }
        if (i10 != 2) {
            throw InvalidProtocolBufferException.b();
        }
        int iB = qVar.b() + qVar.v();
        do {
            list.add(Boolean.valueOf(qVar.f()));
        } while (qVar.b() < iB);
        R(iB);
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final void o(List list) throws InvalidProtocolBufferException.InvalidWireTypeException {
        Q(list, true);
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final m p() throws InvalidProtocolBufferException.InvalidWireTypeException {
        S(2);
        return this.f2512a.g();
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final int q() throws InvalidProtocolBufferException.InvalidWireTypeException {
        S(0);
        return this.f2512a.m();
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final void r(List list, o2 o2Var, d0 d0Var) throws InvalidProtocolBufferException.InvalidWireTypeException {
        int iU;
        int i10 = this.f2513b;
        if ((i10 & 7) != 2) {
            throw InvalidProtocolBufferException.b();
        }
        do {
            list.add(P(o2Var, d0Var));
            q qVar = this.f2512a;
            if (qVar.c() || this.f2515d != 0) {
                return;
            } else {
                iU = qVar.u();
            }
        } while (iU == i10);
        this.f2515d = iU;
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final double readDouble() throws InvalidProtocolBufferException.InvalidWireTypeException {
        S(1);
        return this.f2512a.h();
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final float readFloat() throws InvalidProtocolBufferException.InvalidWireTypeException {
        S(5);
        return this.f2512a.l();
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final void s(List list) throws InvalidProtocolBufferException {
        int iU;
        int i10 = this.f2513b & 7;
        q qVar = this.f2512a;
        if (i10 == 1) {
            do {
                list.add(Long.valueOf(qVar.k()));
                if (qVar.c()) {
                    return;
                } else {
                    iU = qVar.u();
                }
            } while (iU == this.f2513b);
            this.f2515d = iU;
            return;
        }
        if (i10 != 2) {
            throw InvalidProtocolBufferException.b();
        }
        int iV = qVar.v();
        if ((iV & 7) != 0) {
            throw InvalidProtocolBufferException.e();
        }
        int iB = qVar.b() + iV;
        do {
            list.add(Long.valueOf(qVar.k()));
        } while (qVar.b() < iB);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0060, code lost:
    
        r10.put(r4, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0063, code lost:
    
        r1.d(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0066, code lost:
    
        return;
     */
    @Override // androidx.datastore.preferences.protobuf.n2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void t(java.util.Map r10, androidx.appcompat.app.i1 r11, androidx.datastore.preferences.protobuf.d0 r12) throws androidx.datastore.preferences.protobuf.InvalidProtocolBufferException.InvalidWireTypeException {
        /*
            r9 = this;
            r0 = 2
            r9.S(r0)
            androidx.datastore.preferences.protobuf.q r1 = r9.f2512a
            int r2 = r1.v()
            int r2 = r1.e(r2)
            r11.getClass()
            java.lang.Object r3 = r11.f1061n
            java.lang.String r4 = ""
            r5 = r3
        L16:
            int r6 = r9.E()     // Catch: java.lang.Throwable -> L3a
            r7 = 2147483647(0x7fffffff, float:NaN)
            if (r6 == r7) goto L60
            boolean r7 = r1.c()     // Catch: java.lang.Throwable -> L3a
            if (r7 == 0) goto L26
            goto L60
        L26:
            r7 = 1
            java.lang.String r8 = "Unable to parse map entry."
            if (r6 == r7) goto L49
            if (r6 == r0) goto L3c
            boolean r6 = r9.T()     // Catch: java.lang.Throwable -> L3a androidx.datastore.preferences.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L53
            if (r6 == 0) goto L34
            goto L16
        L34:
            androidx.datastore.preferences.protobuf.InvalidProtocolBufferException r6 = new androidx.datastore.preferences.protobuf.InvalidProtocolBufferException     // Catch: java.lang.Throwable -> L3a androidx.datastore.preferences.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L53
            r6.<init>(r8)     // Catch: java.lang.Throwable -> L3a androidx.datastore.preferences.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L53
            throw r6     // Catch: java.lang.Throwable -> L3a androidx.datastore.preferences.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L53
        L3a:
            r10 = move-exception
            goto L67
        L3c:
            java.lang.Object r6 = r11.f1060m     // Catch: java.lang.Throwable -> L3a androidx.datastore.preferences.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L53
            androidx.datastore.preferences.protobuf.y3 r6 = (androidx.datastore.preferences.protobuf.y3) r6     // Catch: java.lang.Throwable -> L3a androidx.datastore.preferences.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L53
            java.lang.Class r7 = r3.getClass()     // Catch: java.lang.Throwable -> L3a androidx.datastore.preferences.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L53
            java.lang.Object r5 = r9.N(r6, r7, r12)     // Catch: java.lang.Throwable -> L3a androidx.datastore.preferences.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L53
            goto L16
        L49:
            java.lang.Object r6 = r11.f1059l     // Catch: java.lang.Throwable -> L3a androidx.datastore.preferences.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L53
            androidx.datastore.preferences.protobuf.y3 r6 = (androidx.datastore.preferences.protobuf.y3) r6     // Catch: java.lang.Throwable -> L3a androidx.datastore.preferences.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L53
            r7 = 0
            java.lang.Object r4 = r9.N(r6, r7, r7)     // Catch: java.lang.Throwable -> L3a androidx.datastore.preferences.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L53
            goto L16
        L53:
            boolean r6 = r9.T()     // Catch: java.lang.Throwable -> L3a
            if (r6 == 0) goto L5a
            goto L16
        L5a:
            androidx.datastore.preferences.protobuf.InvalidProtocolBufferException r10 = new androidx.datastore.preferences.protobuf.InvalidProtocolBufferException     // Catch: java.lang.Throwable -> L3a
            r10.<init>(r8)     // Catch: java.lang.Throwable -> L3a
            throw r10     // Catch: java.lang.Throwable -> L3a
        L60:
            r10.put(r4, r5)     // Catch: java.lang.Throwable -> L3a
            r1.d(r2)
            return
        L67:
            r1.d(r2)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.r.t(java.util.Map, androidx.appcompat.app.i1, androidx.datastore.preferences.protobuf.d0):void");
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final Object u(o2 o2Var, d0 d0Var) throws InvalidProtocolBufferException.InvalidWireTypeException {
        S(3);
        return O(o2Var, d0Var);
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final void v(List list) throws InvalidProtocolBufferException {
        int iU;
        int i10 = this.f2513b & 7;
        q qVar = this.f2512a;
        if (i10 == 0) {
            do {
                list.add(Integer.valueOf(qVar.q()));
                if (qVar.c()) {
                    return;
                } else {
                    iU = qVar.u();
                }
            } while (iU == this.f2513b);
            this.f2515d = iU;
            return;
        }
        if (i10 != 2) {
            throw InvalidProtocolBufferException.b();
        }
        int iB = qVar.b() + qVar.v();
        do {
            list.add(Integer.valueOf(qVar.q()));
        } while (qVar.b() < iB);
        R(iB);
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final long w() throws InvalidProtocolBufferException.InvalidWireTypeException {
        S(0);
        return this.f2512a.w();
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final void x(List list) throws InvalidProtocolBufferException {
        int iU;
        int i10 = this.f2513b & 7;
        q qVar = this.f2512a;
        if (i10 == 0) {
            do {
                list.add(Integer.valueOf(qVar.v()));
                if (qVar.c()) {
                    return;
                } else {
                    iU = qVar.u();
                }
            } while (iU == this.f2513b);
            this.f2515d = iU;
            return;
        }
        if (i10 != 2) {
            throw InvalidProtocolBufferException.b();
        }
        int iB = qVar.b() + qVar.v();
        do {
            list.add(Integer.valueOf(qVar.v()));
        } while (qVar.b() < iB);
        R(iB);
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final int y() throws InvalidProtocolBufferException.InvalidWireTypeException {
        S(5);
        return this.f2512a.j();
    }

    @Override // androidx.datastore.preferences.protobuf.n2
    public final void z(List list) throws InvalidProtocolBufferException {
        int iU;
        int i10 = this.f2513b & 7;
        q qVar = this.f2512a;
        if (i10 == 1) {
            do {
                list.add(Long.valueOf(qVar.p()));
                if (qVar.c()) {
                    return;
                } else {
                    iU = qVar.u();
                }
            } while (iU == this.f2513b);
            this.f2515d = iU;
            return;
        }
        if (i10 != 2) {
            throw InvalidProtocolBufferException.b();
        }
        int iV = qVar.v();
        if ((iV & 7) != 0) {
            throw InvalidProtocolBufferException.e();
        }
        int iB = qVar.b() + iV;
        do {
            list.add(Long.valueOf(qVar.p()));
        } while (qVar.b() < iB);
    }
}
