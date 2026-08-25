package a9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: classes5.dex */
public final class o0 extends kotlin.reflect.jvm.internal.impl.protobuf.p implements q0 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f605n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public d1 f606o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public a1 f607p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public n0 f608q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public List f609r;

    public static o0 i() {
        o0 o0Var = new o0();
        o0Var.f606o = d1.f396o;
        o0Var.f607p = a1.f322o;
        o0Var.f608q = n0.f594u;
        o0Var.f609r = Collections.EMPTY_LIST;
        return o0Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        p0 p0VarH = h();
        if (p0VarH.isInitialized()) {
            return p0VarH;
        }
        throw new UninitializedMessageException();
    }

    public final Object clone() {
        o0 o0VarI = i();
        o0VarI.j(h());
        return o0VarI;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        j((p0) uVar);
        return this;
    }

    public final p0 h() {
        p0 p0Var = new p0(this);
        int i10 = this.f605n;
        int i11 = (i10 & 1) != 1 ? 0 : 1;
        p0Var.f618n = this.f606o;
        if ((i10 & 2) == 2) {
            i11 |= 2;
        }
        p0Var.f619o = this.f607p;
        if ((i10 & 4) == 4) {
            i11 |= 4;
        }
        p0Var.f620p = this.f608q;
        if ((i10 & 8) == 8) {
            this.f609r = Collections.unmodifiableList(this.f609r);
            this.f605n &= -9;
        }
        p0Var.f621q = this.f609r;
        p0Var.f617m = i11;
        return p0Var;
    }

    public final void j(p0 p0Var) {
        n0 n0Var;
        a1 a1Var;
        d1 d1Var;
        if (p0Var == p0.f614t) {
            return;
        }
        if ((p0Var.f617m & 1) == 1) {
            d1 d1Var2 = p0Var.f618n;
            if ((this.f605n & 1) != 1 || (d1Var = this.f606o) == d1.f396o) {
                this.f606o = d1Var2;
            } else {
                c1 c1Var = new c1();
                c1Var.f361m = kotlin.reflect.jvm.internal.impl.protobuf.y.f19849l;
                c1Var.h(d1Var);
                c1Var.h(d1Var2);
                this.f606o = c1Var.g();
            }
            this.f605n |= 1;
        }
        if ((p0Var.f617m & 2) == 2) {
            a1 a1Var2 = p0Var.f619o;
            if ((this.f605n & 2) != 2 || (a1Var = this.f607p) == a1.f322o) {
                this.f607p = a1Var2;
            } else {
                v0 v0Var = new v0();
                v0Var.f706m = Collections.EMPTY_LIST;
                v0Var.h(a1Var);
                v0Var.h(a1Var2);
                this.f607p = v0Var.g();
            }
            this.f605n |= 2;
        }
        if ((p0Var.f617m & 4) == 4) {
            n0 n0Var2 = p0Var.f620p;
            if ((this.f605n & 4) != 4 || (n0Var = this.f608q) == n0.f594u) {
                this.f608q = n0Var2;
            } else {
                m0 m0VarI = m0.i();
                m0VarI.j(n0Var);
                m0VarI.j(n0Var2);
                this.f608q = m0VarI.h();
            }
            this.f605n |= 4;
        }
        if (!p0Var.f621q.isEmpty()) {
            if (this.f609r.isEmpty()) {
                this.f609r = p0Var.f621q;
                this.f605n &= -9;
            } else {
                if ((this.f605n & 8) != 8) {
                    this.f609r = new ArrayList(this.f609r);
                    this.f605n |= 8;
                }
                this.f609r.addAll(p0Var.f621q);
            }
        }
        g(p0Var);
        this.f19815i = this.f19815i.b(p0Var.f616l);
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
            a9.a r1 = a9.p0.f615u     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            a9.p0 r1 = new a9.p0     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r2.j(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r4 = r3.f19759i     // Catch: java.lang.Throwable -> Lf
            a9.p0 r4 = (a9.p0) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: a9.o0.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}
