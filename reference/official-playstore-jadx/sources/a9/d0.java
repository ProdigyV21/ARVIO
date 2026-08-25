package a9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: classes5.dex */
public final class d0 extends kotlin.reflect.jvm.internal.impl.protobuf.o implements g0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f388l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f389m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f390n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public e0 f391o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public k1 f392p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f393q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public List f394r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public List f395s;

    public static d0 h() {
        d0 d0Var = new d0();
        d0Var.f391o = e0.TRUE;
        d0Var.f392p = k1.D;
        List list = Collections.EMPTY_LIST;
        d0Var.f394r = list;
        d0Var.f395s = list;
        return d0Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        f0 f0VarG = g();
        if (f0VarG.isInitialized()) {
            return f0VarG;
        }
        throw new UninitializedMessageException();
    }

    public final Object clone() {
        d0 d0VarH = h();
        d0VarH.i(g());
        return d0VarH;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        i((f0) uVar);
        return this;
    }

    public final f0 g() {
        f0 f0Var = new f0(this);
        int i10 = this.f388l;
        int i11 = (i10 & 1) != 1 ? 0 : 1;
        f0Var.f427m = this.f389m;
        if ((i10 & 2) == 2) {
            i11 |= 2;
        }
        f0Var.f428n = this.f390n;
        if ((i10 & 4) == 4) {
            i11 |= 4;
        }
        f0Var.f429o = this.f391o;
        if ((i10 & 8) == 8) {
            i11 |= 8;
        }
        f0Var.f430p = this.f392p;
        if ((i10 & 16) == 16) {
            i11 |= 16;
        }
        f0Var.f431q = this.f393q;
        if ((i10 & 32) == 32) {
            this.f394r = Collections.unmodifiableList(this.f394r);
            this.f388l &= -33;
        }
        f0Var.f432r = this.f394r;
        if ((this.f388l & 64) == 64) {
            this.f395s = Collections.unmodifiableList(this.f395s);
            this.f388l &= -65;
        }
        f0Var.f433s = this.f395s;
        f0Var.f426l = i11;
        return f0Var;
    }

    public final void i(f0 f0Var) {
        k1 k1Var;
        if (f0Var == f0.f423v) {
            return;
        }
        int i10 = f0Var.f426l;
        if ((i10 & 1) == 1) {
            int i11 = f0Var.f427m;
            this.f388l = 1 | this.f388l;
            this.f389m = i11;
        }
        if ((i10 & 2) == 2) {
            int i12 = f0Var.f428n;
            this.f388l = 2 | this.f388l;
            this.f390n = i12;
        }
        if ((i10 & 4) == 4) {
            e0 e0Var = f0Var.f429o;
            e0Var.getClass();
            this.f388l = 4 | this.f388l;
            this.f391o = e0Var;
        }
        if ((f0Var.f426l & 8) == 8) {
            k1 k1Var2 = f0Var.f430p;
            if ((this.f388l & 8) != 8 || (k1Var = this.f392p) == k1.D) {
                this.f392p = k1Var2;
            } else {
                j1 j1VarP = k1.p(k1Var);
                j1VarP.j(k1Var2);
                this.f392p = j1VarP.h();
            }
            this.f388l |= 8;
        }
        if ((f0Var.f426l & 16) == 16) {
            int i13 = f0Var.f431q;
            this.f388l = 16 | this.f388l;
            this.f393q = i13;
        }
        if (!f0Var.f432r.isEmpty()) {
            if (this.f394r.isEmpty()) {
                this.f394r = f0Var.f432r;
                this.f388l &= -33;
            } else {
                if ((this.f388l & 32) != 32) {
                    this.f394r = new ArrayList(this.f394r);
                    this.f388l |= 32;
                }
                this.f394r.addAll(f0Var.f432r);
            }
        }
        if (!f0Var.f433s.isEmpty()) {
            if (this.f395s.isEmpty()) {
                this.f395s = f0Var.f433s;
                this.f388l &= -65;
            } else {
                if ((this.f388l & 64) != 64) {
                    this.f395s = new ArrayList(this.f395s);
                    this.f388l |= 64;
                }
                this.f395s.addAll(f0Var.f433s);
            }
        }
        this.f19815i = this.f19815i.b(f0Var.f425i);
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
            a9.a r1 = a9.f0.f424w     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            a9.f0 r1 = new a9.f0     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r2.i(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r4 = r3.f19759i     // Catch: java.lang.Throwable -> Lf
            a9.f0 r4 = (a9.f0) r4     // Catch: java.lang.Throwable -> Lf
            throw r3     // Catch: java.lang.Throwable -> L17
        L17:
            r3 = move-exception
            r0 = r4
        L19:
            if (r0 == 0) goto L1e
            r2.i(r0)
        L1e:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: a9.d0.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}
