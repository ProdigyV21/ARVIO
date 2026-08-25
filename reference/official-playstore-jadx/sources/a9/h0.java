package a9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: classes5.dex */
public final class h0 extends kotlin.reflect.jvm.internal.impl.protobuf.p implements j0 {
    public List A;
    public t B;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f460n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f461o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f462p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f463q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public k1 f464r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f465s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public List f466t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public k1 f467u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f468v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public List f469w;
    public List x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public List f470y;
    public u1 z;

    public static h0 i() {
        h0 h0Var = new h0();
        h0Var.f461o = 6;
        h0Var.f462p = 6;
        k1 k1Var = k1.D;
        h0Var.f464r = k1Var;
        List list = Collections.EMPTY_LIST;
        h0Var.f466t = list;
        h0Var.f467u = k1Var;
        h0Var.f469w = list;
        h0Var.x = list;
        h0Var.f470y = list;
        h0Var.z = u1.f692q;
        h0Var.A = list;
        h0Var.B = t.f670o;
        return h0Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        i0 i0VarH = h();
        if (i0VarH.isInitialized()) {
            return i0VarH;
        }
        throw new UninitializedMessageException();
    }

    public final Object clone() {
        h0 h0VarI = i();
        h0VarI.j(h());
        return h0VarI;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        j((i0) uVar);
        return this;
    }

    public final i0 h() {
        i0 i0Var = new i0(this);
        int i10 = this.f460n;
        int i11 = (i10 & 1) != 1 ? 0 : 1;
        i0Var.f487n = this.f461o;
        if ((i10 & 2) == 2) {
            i11 |= 2;
        }
        i0Var.f488o = this.f462p;
        if ((i10 & 4) == 4) {
            i11 |= 4;
        }
        i0Var.f489p = this.f463q;
        if ((i10 & 8) == 8) {
            i11 |= 8;
        }
        i0Var.f490q = this.f464r;
        if ((i10 & 16) == 16) {
            i11 |= 16;
        }
        i0Var.f491r = this.f465s;
        if ((i10 & 32) == 32) {
            this.f466t = Collections.unmodifiableList(this.f466t);
            this.f460n &= -33;
        }
        i0Var.f492s = this.f466t;
        if ((i10 & 64) == 64) {
            i11 |= 32;
        }
        i0Var.f493t = this.f467u;
        if ((i10 & 128) == 128) {
            i11 |= 64;
        }
        i0Var.f494u = this.f468v;
        if ((this.f460n & 256) == 256) {
            this.f469w = Collections.unmodifiableList(this.f469w);
            this.f460n &= -257;
        }
        i0Var.f495v = this.f469w;
        if ((this.f460n & 512) == 512) {
            this.x = Collections.unmodifiableList(this.x);
            this.f460n &= -513;
        }
        i0Var.f496w = this.x;
        if ((this.f460n & 1024) == 1024) {
            this.f470y = Collections.unmodifiableList(this.f470y);
            this.f460n &= -1025;
        }
        i0Var.f497y = this.f470y;
        if ((i10 & 2048) == 2048) {
            i11 |= 128;
        }
        i0Var.z = this.z;
        if ((this.f460n & 4096) == 4096) {
            this.A = Collections.unmodifiableList(this.A);
            this.f460n &= -4097;
        }
        i0Var.A = this.A;
        if ((i10 & 8192) == 8192) {
            i11 |= 256;
        }
        i0Var.B = this.B;
        i0Var.f486m = i11;
        return i0Var;
    }

    public final void j(i0 i0Var) {
        t tVar;
        u1 u1Var;
        k1 k1Var;
        k1 k1Var2;
        if (i0Var == i0.E) {
            return;
        }
        int i10 = i0Var.f486m;
        if ((i10 & 1) == 1) {
            int i11 = i0Var.f487n;
            this.f460n = 1 | this.f460n;
            this.f461o = i11;
        }
        if ((i10 & 2) == 2) {
            int i12 = i0Var.f488o;
            this.f460n = 2 | this.f460n;
            this.f462p = i12;
        }
        if ((i10 & 4) == 4) {
            int i13 = i0Var.f489p;
            this.f460n = 4 | this.f460n;
            this.f463q = i13;
        }
        if ((i10 & 8) == 8) {
            k1 k1Var3 = i0Var.f490q;
            if ((this.f460n & 8) != 8 || (k1Var2 = this.f464r) == k1.D) {
                this.f464r = k1Var3;
            } else {
                j1 j1VarP = k1.p(k1Var2);
                j1VarP.j(k1Var3);
                this.f464r = j1VarP.h();
            }
            this.f460n |= 8;
        }
        if ((i0Var.f486m & 16) == 16) {
            int i14 = i0Var.f491r;
            this.f460n = 16 | this.f460n;
            this.f465s = i14;
        }
        if (!i0Var.f492s.isEmpty()) {
            if (this.f466t.isEmpty()) {
                this.f466t = i0Var.f492s;
                this.f460n &= -33;
            } else {
                if ((this.f460n & 32) != 32) {
                    this.f466t = new ArrayList(this.f466t);
                    this.f460n |= 32;
                }
                this.f466t.addAll(i0Var.f492s);
            }
        }
        if ((i0Var.f486m & 32) == 32) {
            k1 k1Var4 = i0Var.f493t;
            if ((this.f460n & 64) != 64 || (k1Var = this.f467u) == k1.D) {
                this.f467u = k1Var4;
            } else {
                j1 j1VarP2 = k1.p(k1Var);
                j1VarP2.j(k1Var4);
                this.f467u = j1VarP2.h();
            }
            this.f460n |= 64;
        }
        if ((i0Var.f486m & 64) == 64) {
            int i15 = i0Var.f494u;
            this.f460n |= 128;
            this.f468v = i15;
        }
        if (!i0Var.f495v.isEmpty()) {
            if (this.f469w.isEmpty()) {
                this.f469w = i0Var.f495v;
                this.f460n &= -257;
            } else {
                if ((this.f460n & 256) != 256) {
                    this.f469w = new ArrayList(this.f469w);
                    this.f460n |= 256;
                }
                this.f469w.addAll(i0Var.f495v);
            }
        }
        if (!i0Var.f496w.isEmpty()) {
            if (this.x.isEmpty()) {
                this.x = i0Var.f496w;
                this.f460n &= -513;
            } else {
                if ((this.f460n & 512) != 512) {
                    this.x = new ArrayList(this.x);
                    this.f460n |= 512;
                }
                this.x.addAll(i0Var.f496w);
            }
        }
        if (!i0Var.f497y.isEmpty()) {
            if (this.f470y.isEmpty()) {
                this.f470y = i0Var.f497y;
                this.f460n &= -1025;
            } else {
                if ((this.f460n & 1024) != 1024) {
                    this.f470y = new ArrayList(this.f470y);
                    this.f460n |= 1024;
                }
                this.f470y.addAll(i0Var.f497y);
            }
        }
        if ((i0Var.f486m & 128) == 128) {
            u1 u1Var2 = i0Var.z;
            if ((this.f460n & 2048) != 2048 || (u1Var = this.z) == u1.f692q) {
                this.z = u1Var2;
            } else {
                t1 t1VarH = u1.h(u1Var);
                t1VarH.i(u1Var2);
                this.z = t1VarH.g();
            }
            this.f460n |= 2048;
        }
        if (!i0Var.A.isEmpty()) {
            if (this.A.isEmpty()) {
                this.A = i0Var.A;
                this.f460n &= -4097;
            } else {
                if ((this.f460n & 4096) != 4096) {
                    this.A = new ArrayList(this.A);
                    this.f460n |= 4096;
                }
                this.A.addAll(i0Var.A);
            }
        }
        if ((i0Var.f486m & 256) == 256) {
            t tVar2 = i0Var.B;
            if ((this.f460n & 8192) != 8192 || (tVar = this.B) == t.f670o) {
                this.B = tVar2;
            } else {
                s sVar = new s();
                sVar.f658m = Collections.EMPTY_LIST;
                sVar.h(tVar);
                sVar.h(tVar2);
                this.B = sVar.g();
            }
            this.f460n |= 8192;
        }
        g(i0Var);
        this.f19815i = this.f19815i.b(i0Var.f485l);
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
            a9.a r1 = a9.i0.F     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            a9.i0 r1 = new a9.i0     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r2.j(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r4 = r3.f19759i     // Catch: java.lang.Throwable -> Lf
            a9.i0 r4 = (a9.i0) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: a9.h0.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}
