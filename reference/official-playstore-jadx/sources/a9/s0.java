package a9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: classes5.dex */
public final class s0 extends kotlin.reflect.jvm.internal.impl.protobuf.p implements u0 {
    public int A;
    public List B;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f659n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f660o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f661p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f662q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public k1 f663r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f664s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public List f665t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public k1 f666u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f667v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public List f668w;
    public List x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public x1 f669y;
    public int z;

    public static s0 i() {
        s0 s0Var = new s0();
        s0Var.f660o = 518;
        s0Var.f661p = 2054;
        k1 k1Var = k1.D;
        s0Var.f663r = k1Var;
        List list = Collections.EMPTY_LIST;
        s0Var.f665t = list;
        s0Var.f666u = k1Var;
        s0Var.f668w = list;
        s0Var.x = list;
        s0Var.f669y = x1.f733v;
        s0Var.B = list;
        return s0Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        t0 t0VarH = h();
        if (t0VarH.isInitialized()) {
            return t0VarH;
        }
        throw new UninitializedMessageException();
    }

    public final Object clone() {
        s0 s0VarI = i();
        s0VarI.j(h());
        return s0VarI;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        j((t0) uVar);
        return this;
    }

    public final t0 h() {
        t0 t0Var = new t0(this);
        int i10 = this.f659n;
        int i11 = (i10 & 1) != 1 ? 0 : 1;
        t0Var.f678n = this.f660o;
        if ((i10 & 2) == 2) {
            i11 |= 2;
        }
        t0Var.f679o = this.f661p;
        if ((i10 & 4) == 4) {
            i11 |= 4;
        }
        t0Var.f680p = this.f662q;
        if ((i10 & 8) == 8) {
            i11 |= 8;
        }
        t0Var.f681q = this.f663r;
        if ((i10 & 16) == 16) {
            i11 |= 16;
        }
        t0Var.f682r = this.f664s;
        if ((i10 & 32) == 32) {
            this.f665t = Collections.unmodifiableList(this.f665t);
            this.f659n &= -33;
        }
        t0Var.f683s = this.f665t;
        if ((i10 & 64) == 64) {
            i11 |= 32;
        }
        t0Var.f684t = this.f666u;
        if ((i10 & 128) == 128) {
            i11 |= 64;
        }
        t0Var.f685u = this.f667v;
        if ((this.f659n & 256) == 256) {
            this.f668w = Collections.unmodifiableList(this.f668w);
            this.f659n &= -257;
        }
        t0Var.f686v = this.f668w;
        if ((this.f659n & 512) == 512) {
            this.x = Collections.unmodifiableList(this.x);
            this.f659n &= -513;
        }
        t0Var.f687w = this.x;
        if ((i10 & 1024) == 1024) {
            i11 |= 128;
        }
        t0Var.f688y = this.f669y;
        if ((i10 & 2048) == 2048) {
            i11 |= 256;
        }
        t0Var.z = this.z;
        if ((i10 & 4096) == 4096) {
            i11 |= 512;
        }
        t0Var.A = this.A;
        if ((this.f659n & 8192) == 8192) {
            this.B = Collections.unmodifiableList(this.B);
            this.f659n &= -8193;
        }
        t0Var.B = this.B;
        t0Var.f677m = i11;
        return t0Var;
    }

    public final void j(t0 t0Var) {
        x1 x1Var;
        k1 k1Var;
        k1 k1Var2;
        if (t0Var == t0.E) {
            return;
        }
        int i10 = t0Var.f677m;
        if ((i10 & 1) == 1) {
            int i11 = t0Var.f678n;
            this.f659n = 1 | this.f659n;
            this.f660o = i11;
        }
        if ((i10 & 2) == 2) {
            int i12 = t0Var.f679o;
            this.f659n = 2 | this.f659n;
            this.f661p = i12;
        }
        if ((i10 & 4) == 4) {
            int i13 = t0Var.f680p;
            this.f659n = 4 | this.f659n;
            this.f662q = i13;
        }
        if ((i10 & 8) == 8) {
            k1 k1Var3 = t0Var.f681q;
            if ((this.f659n & 8) != 8 || (k1Var2 = this.f663r) == k1.D) {
                this.f663r = k1Var3;
            } else {
                j1 j1VarP = k1.p(k1Var2);
                j1VarP.j(k1Var3);
                this.f663r = j1VarP.h();
            }
            this.f659n |= 8;
        }
        if ((t0Var.f677m & 16) == 16) {
            int i14 = t0Var.f682r;
            this.f659n = 16 | this.f659n;
            this.f664s = i14;
        }
        if (!t0Var.f683s.isEmpty()) {
            if (this.f665t.isEmpty()) {
                this.f665t = t0Var.f683s;
                this.f659n &= -33;
            } else {
                if ((this.f659n & 32) != 32) {
                    this.f665t = new ArrayList(this.f665t);
                    this.f659n |= 32;
                }
                this.f665t.addAll(t0Var.f683s);
            }
        }
        if ((t0Var.f677m & 32) == 32) {
            k1 k1Var4 = t0Var.f684t;
            if ((this.f659n & 64) != 64 || (k1Var = this.f666u) == k1.D) {
                this.f666u = k1Var4;
            } else {
                j1 j1VarP2 = k1.p(k1Var);
                j1VarP2.j(k1Var4);
                this.f666u = j1VarP2.h();
            }
            this.f659n |= 64;
        }
        if ((t0Var.f677m & 64) == 64) {
            int i15 = t0Var.f685u;
            this.f659n |= 128;
            this.f667v = i15;
        }
        if (!t0Var.f686v.isEmpty()) {
            if (this.f668w.isEmpty()) {
                this.f668w = t0Var.f686v;
                this.f659n &= -257;
            } else {
                if ((this.f659n & 256) != 256) {
                    this.f668w = new ArrayList(this.f668w);
                    this.f659n |= 256;
                }
                this.f668w.addAll(t0Var.f686v);
            }
        }
        if (!t0Var.f687w.isEmpty()) {
            if (this.x.isEmpty()) {
                this.x = t0Var.f687w;
                this.f659n &= -513;
            } else {
                if ((this.f659n & 512) != 512) {
                    this.x = new ArrayList(this.x);
                    this.f659n |= 512;
                }
                this.x.addAll(t0Var.f687w);
            }
        }
        if ((t0Var.f677m & 128) == 128) {
            x1 x1Var2 = t0Var.f688y;
            if ((this.f659n & 1024) != 1024 || (x1Var = this.f669y) == x1.f733v) {
                this.f669y = x1Var2;
            } else {
                w1 w1Var = new w1();
                k1 k1Var5 = k1.D;
                w1Var.f719q = k1Var5;
                w1Var.f721s = k1Var5;
                w1Var.i(x1Var);
                w1Var.i(x1Var2);
                this.f669y = w1Var.h();
            }
            this.f659n |= 1024;
        }
        int i16 = t0Var.f677m;
        if ((i16 & 256) == 256) {
            int i17 = t0Var.z;
            this.f659n |= 2048;
            this.z = i17;
        }
        if ((i16 & 512) == 512) {
            int i18 = t0Var.A;
            this.f659n |= 4096;
            this.A = i18;
        }
        if (!t0Var.B.isEmpty()) {
            if (this.B.isEmpty()) {
                this.B = t0Var.B;
                this.f659n &= -8193;
            } else {
                if ((this.f659n & 8192) != 8192) {
                    this.B = new ArrayList(this.B);
                    this.f659n |= 8192;
                }
                this.B.addAll(t0Var.B);
            }
        }
        g(t0Var);
        this.f19815i = this.f19815i.b(t0Var.f676l);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001b  */
    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0.a y(kotlin.reflect.jvm.internal.impl.protobuf.i r3, kotlin.reflect.jvm.internal.impl.protobuf.l r4) throws java.lang.Throwable {
        /*
            r2 = this;
            r0 = 0
            a9.a r1 = a9.t0.F     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            a9.t0 r1 = new a9.t0     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r2.j(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r4 = r3.f19759i     // Catch: java.lang.Throwable -> Lf
            a9.t0 r4 = (a9.t0) r4     // Catch: java.lang.Throwable -> Lf
            throw r3     // Catch: java.lang.Throwable -> L17
        L17:
            r3 = move-exception
            r0 = r4
        L19:
            if (r0 == 0) goto L1e
            r2.j(r0)
        L1e:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: a9.s0.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}
