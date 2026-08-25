package a9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: classes5.dex */
public final class j1 extends kotlin.reflect.jvm.internal.impl.protobuf.p implements o1 {
    public int A;
    public int B;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f506n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public List f507o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f508p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f509q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public k1 f510r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f511s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f512t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f513u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f514v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f515w;
    public k1 x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f516y;
    public k1 z;

    public static j1 i() {
        j1 j1Var = new j1();
        j1Var.f507o = Collections.EMPTY_LIST;
        k1 k1Var = k1.D;
        j1Var.f510r = k1Var;
        j1Var.x = k1Var;
        j1Var.z = k1Var;
        return j1Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        k1 k1VarH = h();
        if (k1VarH.isInitialized()) {
            return k1VarH;
        }
        throw new UninitializedMessageException();
    }

    public final Object clone() {
        j1 j1VarI = i();
        j1VarI.j(h());
        return j1VarI;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        j((k1) uVar);
        return this;
    }

    public final k1 h() {
        k1 k1Var = new k1(this);
        int i10 = this.f506n;
        if ((i10 & 1) == 1) {
            this.f507o = Collections.unmodifiableList(this.f507o);
            this.f506n &= -2;
        }
        k1Var.f521n = this.f507o;
        int i11 = (i10 & 2) != 2 ? 0 : 1;
        k1Var.f522o = this.f508p;
        if ((i10 & 4) == 4) {
            i11 |= 2;
        }
        k1Var.f523p = this.f509q;
        if ((i10 & 8) == 8) {
            i11 |= 4;
        }
        k1Var.f524q = this.f510r;
        if ((i10 & 16) == 16) {
            i11 |= 8;
        }
        k1Var.f525r = this.f511s;
        if ((i10 & 32) == 32) {
            i11 |= 16;
        }
        k1Var.f526s = this.f512t;
        if ((i10 & 64) == 64) {
            i11 |= 32;
        }
        k1Var.f527t = this.f513u;
        if ((i10 & 128) == 128) {
            i11 |= 64;
        }
        k1Var.f528u = this.f514v;
        if ((i10 & 256) == 256) {
            i11 |= 128;
        }
        k1Var.f529v = this.f515w;
        if ((i10 & 512) == 512) {
            i11 |= 256;
        }
        k1Var.f530w = this.x;
        if ((i10 & 1024) == 1024) {
            i11 |= 512;
        }
        k1Var.x = this.f516y;
        if ((i10 & 2048) == 2048) {
            i11 |= 1024;
        }
        k1Var.f531y = this.z;
        if ((i10 & 4096) == 4096) {
            i11 |= 2048;
        }
        k1Var.z = this.A;
        if ((i10 & 8192) == 8192) {
            i11 |= 4096;
        }
        k1Var.A = this.B;
        k1Var.f520m = i11;
        return k1Var;
    }

    public final j1 j(k1 k1Var) {
        k1 k1Var2;
        k1 k1Var3;
        k1 k1Var4;
        k1 k1Var5 = k1.D;
        if (k1Var == k1Var5) {
            return this;
        }
        if (!k1Var.f521n.isEmpty()) {
            if (this.f507o.isEmpty()) {
                this.f507o = k1Var.f521n;
                this.f506n &= -2;
            } else {
                if ((this.f506n & 1) != 1) {
                    this.f507o = new ArrayList(this.f507o);
                    this.f506n |= 1;
                }
                this.f507o.addAll(k1Var.f521n);
            }
        }
        int i10 = k1Var.f520m;
        if ((i10 & 1) == 1) {
            boolean z = k1Var.f522o;
            this.f506n |= 2;
            this.f508p = z;
        }
        if ((i10 & 2) == 2) {
            int i11 = k1Var.f523p;
            this.f506n |= 4;
            this.f509q = i11;
        }
        if ((i10 & 4) == 4) {
            k1 k1Var6 = k1Var.f524q;
            if ((this.f506n & 8) != 8 || (k1Var4 = this.f510r) == k1Var5) {
                this.f510r = k1Var6;
            } else {
                j1 j1VarP = k1.p(k1Var4);
                j1VarP.j(k1Var6);
                this.f510r = j1VarP.h();
            }
            this.f506n |= 8;
        }
        int i12 = k1Var.f520m;
        if ((i12 & 8) == 8) {
            int i13 = k1Var.f525r;
            this.f506n |= 16;
            this.f511s = i13;
        }
        if ((i12 & 16) == 16) {
            int i14 = k1Var.f526s;
            this.f506n |= 32;
            this.f512t = i14;
        }
        if ((i12 & 32) == 32) {
            int i15 = k1Var.f527t;
            this.f506n |= 64;
            this.f513u = i15;
        }
        if ((i12 & 64) == 64) {
            int i16 = k1Var.f528u;
            this.f506n |= 128;
            this.f514v = i16;
        }
        if ((i12 & 128) == 128) {
            int i17 = k1Var.f529v;
            this.f506n |= 256;
            this.f515w = i17;
        }
        if ((i12 & 256) == 256) {
            k1 k1Var7 = k1Var.f530w;
            if ((this.f506n & 512) != 512 || (k1Var3 = this.x) == k1Var5) {
                this.x = k1Var7;
            } else {
                j1 j1VarP2 = k1.p(k1Var3);
                j1VarP2.j(k1Var7);
                this.x = j1VarP2.h();
            }
            this.f506n |= 512;
        }
        int i18 = k1Var.f520m;
        if ((i18 & 512) == 512) {
            int i19 = k1Var.x;
            this.f506n |= 1024;
            this.f516y = i19;
        }
        if ((i18 & 1024) == 1024) {
            k1 k1Var8 = k1Var.f531y;
            if ((this.f506n & 2048) != 2048 || (k1Var2 = this.z) == k1Var5) {
                this.z = k1Var8;
            } else {
                j1 j1VarP3 = k1.p(k1Var2);
                j1VarP3.j(k1Var8);
                this.z = j1VarP3.h();
            }
            this.f506n |= 2048;
        }
        int i20 = k1Var.f520m;
        if ((i20 & 2048) == 2048) {
            int i21 = k1Var.z;
            this.f506n |= 4096;
            this.A = i21;
        }
        if ((i20 & 4096) == 4096) {
            int i22 = k1Var.A;
            this.f506n |= 8192;
            this.B = i22;
        }
        g(k1Var);
        this.f19815i = this.f19815i.b(k1Var.f519l);
        return this;
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
            a9.a r1 = a9.k1.E     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            a9.k1 r1 = new a9.k1     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r2.j(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r4 = r3.f19759i     // Catch: java.lang.Throwable -> Lf
            a9.k1 r4 = (a9.k1) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: a9.j1.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}
