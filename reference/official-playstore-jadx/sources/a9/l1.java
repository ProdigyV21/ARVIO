package a9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: classes5.dex */
public final class l1 extends kotlin.reflect.jvm.internal.impl.protobuf.p implements n1 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f545n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f546o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f547p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public List f548q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public k1 f549r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f550s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public k1 f551t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f552u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public List f553v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public List f554w;

    public static l1 i() {
        l1 l1Var = new l1();
        l1Var.f546o = 6;
        List list = Collections.EMPTY_LIST;
        l1Var.f548q = list;
        k1 k1Var = k1.D;
        l1Var.f549r = k1Var;
        l1Var.f551t = k1Var;
        l1Var.f553v = list;
        l1Var.f554w = list;
        return l1Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        m1 m1VarH = h();
        if (m1VarH.isInitialized()) {
            return m1VarH;
        }
        throw new UninitializedMessageException();
    }

    public final Object clone() {
        l1 l1VarI = i();
        l1VarI.j(h());
        return l1VarI;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        j((m1) uVar);
        return this;
    }

    public final m1 h() {
        m1 m1Var = new m1(this);
        int i10 = this.f545n;
        int i11 = (i10 & 1) != 1 ? 0 : 1;
        m1Var.f571n = this.f546o;
        if ((i10 & 2) == 2) {
            i11 |= 2;
        }
        m1Var.f572o = this.f547p;
        if ((i10 & 4) == 4) {
            this.f548q = Collections.unmodifiableList(this.f548q);
            this.f545n &= -5;
        }
        m1Var.f573p = this.f548q;
        if ((i10 & 8) == 8) {
            i11 |= 4;
        }
        m1Var.f574q = this.f549r;
        if ((i10 & 16) == 16) {
            i11 |= 8;
        }
        m1Var.f575r = this.f550s;
        if ((i10 & 32) == 32) {
            i11 |= 16;
        }
        m1Var.f576s = this.f551t;
        if ((i10 & 64) == 64) {
            i11 |= 32;
        }
        m1Var.f577t = this.f552u;
        if ((this.f545n & 128) == 128) {
            this.f553v = Collections.unmodifiableList(this.f553v);
            this.f545n &= -129;
        }
        m1Var.f578u = this.f553v;
        if ((this.f545n & 256) == 256) {
            this.f554w = Collections.unmodifiableList(this.f554w);
            this.f545n &= -257;
        }
        m1Var.f579v = this.f554w;
        m1Var.f570m = i11;
        return m1Var;
    }

    public final void j(m1 m1Var) {
        k1 k1Var;
        k1 k1Var2;
        if (m1Var == m1.f568y) {
            return;
        }
        int i10 = m1Var.f570m;
        if ((i10 & 1) == 1) {
            int i11 = m1Var.f571n;
            this.f545n = 1 | this.f545n;
            this.f546o = i11;
        }
        if ((i10 & 2) == 2) {
            int i12 = m1Var.f572o;
            this.f545n = 2 | this.f545n;
            this.f547p = i12;
        }
        if (!m1Var.f573p.isEmpty()) {
            if (this.f548q.isEmpty()) {
                this.f548q = m1Var.f573p;
                this.f545n &= -5;
            } else {
                if ((this.f545n & 4) != 4) {
                    this.f548q = new ArrayList(this.f548q);
                    this.f545n |= 4;
                }
                this.f548q.addAll(m1Var.f573p);
            }
        }
        if ((m1Var.f570m & 4) == 4) {
            k1 k1Var3 = m1Var.f574q;
            if ((this.f545n & 8) != 8 || (k1Var2 = this.f549r) == k1.D) {
                this.f549r = k1Var3;
            } else {
                j1 j1VarP = k1.p(k1Var2);
                j1VarP.j(k1Var3);
                this.f549r = j1VarP.h();
            }
            this.f545n |= 8;
        }
        int i13 = m1Var.f570m;
        if ((i13 & 8) == 8) {
            int i14 = m1Var.f575r;
            this.f545n |= 16;
            this.f550s = i14;
        }
        if ((i13 & 16) == 16) {
            k1 k1Var4 = m1Var.f576s;
            if ((this.f545n & 32) != 32 || (k1Var = this.f551t) == k1.D) {
                this.f551t = k1Var4;
            } else {
                j1 j1VarP2 = k1.p(k1Var);
                j1VarP2.j(k1Var4);
                this.f551t = j1VarP2.h();
            }
            this.f545n |= 32;
        }
        if ((m1Var.f570m & 32) == 32) {
            int i15 = m1Var.f577t;
            this.f545n |= 64;
            this.f552u = i15;
        }
        if (!m1Var.f578u.isEmpty()) {
            if (this.f553v.isEmpty()) {
                this.f553v = m1Var.f578u;
                this.f545n &= -129;
            } else {
                if ((this.f545n & 128) != 128) {
                    this.f553v = new ArrayList(this.f553v);
                    this.f545n |= 128;
                }
                this.f553v.addAll(m1Var.f578u);
            }
        }
        if (!m1Var.f579v.isEmpty()) {
            if (this.f554w.isEmpty()) {
                this.f554w = m1Var.f579v;
                this.f545n &= -257;
            } else {
                if ((this.f545n & 256) != 256) {
                    this.f554w = new ArrayList(this.f554w);
                    this.f545n |= 256;
                }
                this.f554w.addAll(m1Var.f579v);
            }
        }
        g(m1Var);
        this.f19815i = this.f19815i.b(m1Var.f569l);
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
            a9.a r1 = a9.m1.z     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            a9.m1 r1 = new a9.m1     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r2.j(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r4 = r3.f19759i     // Catch: java.lang.Throwable -> Lf
            a9.m1 r4 = (a9.m1) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: a9.l1.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}
