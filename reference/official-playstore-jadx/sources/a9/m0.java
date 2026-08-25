package a9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: classes5.dex */
public final class m0 extends kotlin.reflect.jvm.internal.impl.protobuf.p implements r0 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f562n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public List f563o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public List f564p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public List f565q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public u1 f566r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public f2 f567s;

    public static m0 i() {
        m0 m0Var = new m0();
        List list = Collections.EMPTY_LIST;
        m0Var.f563o = list;
        m0Var.f564p = list;
        m0Var.f565q = list;
        m0Var.f566r = u1.f692q;
        m0Var.f567s = f2.f440o;
        return m0Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        n0 n0VarH = h();
        if (n0VarH.isInitialized()) {
            return n0VarH;
        }
        throw new UninitializedMessageException();
    }

    public final Object clone() {
        m0 m0VarI = i();
        m0VarI.j(h());
        return m0VarI;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        j((n0) uVar);
        return this;
    }

    public final n0 h() {
        n0 n0Var = new n0(this);
        int i10 = this.f562n;
        if ((i10 & 1) == 1) {
            this.f563o = Collections.unmodifiableList(this.f563o);
            this.f562n &= -2;
        }
        n0Var.f598n = this.f563o;
        if ((this.f562n & 2) == 2) {
            this.f564p = Collections.unmodifiableList(this.f564p);
            this.f562n &= -3;
        }
        n0Var.f599o = this.f564p;
        if ((this.f562n & 4) == 4) {
            this.f565q = Collections.unmodifiableList(this.f565q);
            this.f562n &= -5;
        }
        n0Var.f600p = this.f565q;
        int i11 = (i10 & 8) != 8 ? 0 : 1;
        n0Var.f601q = this.f566r;
        if ((i10 & 16) == 16) {
            i11 |= 2;
        }
        n0Var.f602r = this.f567s;
        n0Var.f597m = i11;
        return n0Var;
    }

    public final void j(n0 n0Var) {
        f2 f2Var;
        u1 u1Var;
        if (n0Var == n0.f594u) {
            return;
        }
        if (!n0Var.f598n.isEmpty()) {
            if (this.f563o.isEmpty()) {
                this.f563o = n0Var.f598n;
                this.f562n &= -2;
            } else {
                if ((this.f562n & 1) != 1) {
                    this.f563o = new ArrayList(this.f563o);
                    this.f562n |= 1;
                }
                this.f563o.addAll(n0Var.f598n);
            }
        }
        if (!n0Var.f599o.isEmpty()) {
            if (this.f564p.isEmpty()) {
                this.f564p = n0Var.f599o;
                this.f562n &= -3;
            } else {
                if ((this.f562n & 2) != 2) {
                    this.f564p = new ArrayList(this.f564p);
                    this.f562n |= 2;
                }
                this.f564p.addAll(n0Var.f599o);
            }
        }
        if (!n0Var.f600p.isEmpty()) {
            if (this.f565q.isEmpty()) {
                this.f565q = n0Var.f600p;
                this.f562n &= -5;
            } else {
                if ((this.f562n & 4) != 4) {
                    this.f565q = new ArrayList(this.f565q);
                    this.f562n |= 4;
                }
                this.f565q.addAll(n0Var.f600p);
            }
        }
        if ((n0Var.f597m & 1) == 1) {
            u1 u1Var2 = n0Var.f601q;
            if ((this.f562n & 8) != 8 || (u1Var = this.f566r) == u1.f692q) {
                this.f566r = u1Var2;
            } else {
                t1 t1VarH = u1.h(u1Var);
                t1VarH.i(u1Var2);
                this.f566r = t1VarH.g();
            }
            this.f562n |= 8;
        }
        if ((n0Var.f597m & 2) == 2) {
            f2 f2Var2 = n0Var.f602r;
            if ((this.f562n & 16) != 16 || (f2Var = this.f567s) == f2.f440o) {
                this.f567s = f2Var2;
            } else {
                e2 e2Var = new e2();
                e2Var.f422m = Collections.EMPTY_LIST;
                e2Var.h(f2Var);
                e2Var.h(f2Var2);
                this.f567s = e2Var.g();
            }
            this.f562n |= 16;
        }
        g(n0Var);
        this.f19815i = this.f19815i.b(n0Var.f596l);
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
            a9.a r1 = a9.n0.f595v     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            a9.n0 r1 = new a9.n0     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r2.j(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r4 = r3.f19759i     // Catch: java.lang.Throwable -> Lf
            a9.n0 r4 = (a9.n0) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: a9.m0.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}
