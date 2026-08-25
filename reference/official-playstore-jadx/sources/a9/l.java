package a9;

import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.session.MediaUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: classes5.dex */
public final class l extends kotlin.reflect.jvm.internal.impl.protobuf.p implements o {
    public List A;
    public List B;
    public List C;
    public int D;
    public k1 E;
    public int F;
    public List G;
    public List H;
    public List I;
    public u1 J;
    public List K;
    public f2 L;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f532n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f533o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f534p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f535q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public List f536r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public List f537s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public List f538t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public List f539u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public List f540v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public List f541w;
    public List x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public List f542y;
    public List z;

    public static l i() {
        l lVar = new l();
        lVar.f533o = 6;
        List list = Collections.EMPTY_LIST;
        lVar.f536r = list;
        lVar.f537s = list;
        lVar.f538t = list;
        lVar.f539u = list;
        lVar.f540v = list;
        lVar.f541w = list;
        lVar.x = list;
        lVar.f542y = list;
        lVar.z = list;
        lVar.A = list;
        lVar.B = list;
        lVar.C = list;
        lVar.E = k1.D;
        lVar.G = list;
        lVar.H = list;
        lVar.I = list;
        lVar.J = u1.f692q;
        lVar.K = list;
        lVar.L = f2.f440o;
        return lVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        n nVarH = h();
        if (nVarH.isInitialized()) {
            return nVarH;
        }
        throw new UninitializedMessageException();
    }

    public final Object clone() {
        l lVarI = i();
        lVarI.j(h());
        return lVarI;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        j((n) uVar);
        return this;
    }

    public final n h() {
        n nVar = new n(this);
        int i10 = this.f532n;
        int i11 = (i10 & 1) != 1 ? 0 : 1;
        nVar.f583n = this.f533o;
        if ((i10 & 2) == 2) {
            i11 |= 2;
        }
        nVar.f584o = this.f534p;
        if ((i10 & 4) == 4) {
            i11 |= 4;
        }
        nVar.f585p = this.f535q;
        if ((i10 & 8) == 8) {
            this.f536r = Collections.unmodifiableList(this.f536r);
            this.f532n &= -9;
        }
        nVar.f586q = this.f536r;
        if ((this.f532n & 16) == 16) {
            this.f537s = Collections.unmodifiableList(this.f537s);
            this.f532n &= -17;
        }
        nVar.f587r = this.f537s;
        if ((this.f532n & 32) == 32) {
            this.f538t = Collections.unmodifiableList(this.f538t);
            this.f532n &= -33;
        }
        nVar.f588s = this.f538t;
        if ((this.f532n & 64) == 64) {
            this.f539u = Collections.unmodifiableList(this.f539u);
            this.f532n &= -65;
        }
        nVar.f590u = this.f539u;
        if ((this.f532n & 128) == 128) {
            this.f540v = Collections.unmodifiableList(this.f540v);
            this.f532n &= -129;
        }
        nVar.f592w = this.f540v;
        if ((this.f532n & 256) == 256) {
            this.f541w = Collections.unmodifiableList(this.f541w);
            this.f532n &= -257;
        }
        nVar.x = this.f541w;
        if ((this.f532n & 512) == 512) {
            this.x = Collections.unmodifiableList(this.x);
            this.f532n &= -513;
        }
        nVar.z = this.x;
        if ((this.f532n & 1024) == 1024) {
            this.f542y = Collections.unmodifiableList(this.f542y);
            this.f532n &= -1025;
        }
        nVar.A = this.f542y;
        if ((this.f532n & 2048) == 2048) {
            this.z = Collections.unmodifiableList(this.z);
            this.f532n &= -2049;
        }
        nVar.B = this.z;
        if ((this.f532n & 4096) == 4096) {
            this.A = Collections.unmodifiableList(this.A);
            this.f532n &= -4097;
        }
        nVar.C = this.A;
        if ((this.f532n & 8192) == 8192) {
            this.B = Collections.unmodifiableList(this.B);
            this.f532n &= -8193;
        }
        nVar.D = this.B;
        if ((this.f532n & 16384) == 16384) {
            this.C = Collections.unmodifiableList(this.C);
            this.f532n &= -16385;
        }
        nVar.E = this.C;
        if ((i10 & 32768) == 32768) {
            i11 |= 8;
        }
        nVar.G = this.D;
        if ((i10 & 65536) == 65536) {
            i11 |= 16;
        }
        nVar.H = this.E;
        if ((i10 & 131072) == 131072) {
            i11 |= 32;
        }
        nVar.I = this.F;
        if ((this.f532n & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) == 262144) {
            this.G = Collections.unmodifiableList(this.G);
            this.f532n &= -262145;
        }
        nVar.J = this.G;
        if ((this.f532n & 524288) == 524288) {
            this.H = Collections.unmodifiableList(this.H);
            this.f532n &= -524289;
        }
        nVar.L = this.H;
        if ((this.f532n & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) == 1048576) {
            this.I = Collections.unmodifiableList(this.I);
            this.f532n &= -1048577;
        }
        nVar.M = this.I;
        if ((i10 & 2097152) == 2097152) {
            i11 |= 64;
        }
        nVar.O = this.J;
        if ((this.f532n & 4194304) == 4194304) {
            this.K = Collections.unmodifiableList(this.K);
            this.f532n &= -4194305;
        }
        nVar.P = this.K;
        if ((i10 & 8388608) == 8388608) {
            i11 |= 128;
        }
        nVar.Q = this.L;
        nVar.f582m = i11;
        return nVar;
    }

    public final void j(n nVar) {
        f2 f2Var;
        u1 u1Var;
        k1 k1Var;
        if (nVar == n.T) {
            return;
        }
        int i10 = nVar.f582m;
        if ((i10 & 1) == 1) {
            int i11 = nVar.f583n;
            this.f532n = 1 | this.f532n;
            this.f533o = i11;
        }
        if ((i10 & 2) == 2) {
            int i12 = nVar.f584o;
            this.f532n = 2 | this.f532n;
            this.f534p = i12;
        }
        if ((i10 & 4) == 4) {
            int i13 = nVar.f585p;
            this.f532n = 4 | this.f532n;
            this.f535q = i13;
        }
        if (!nVar.f586q.isEmpty()) {
            if (this.f536r.isEmpty()) {
                this.f536r = nVar.f586q;
                this.f532n &= -9;
            } else {
                if ((this.f532n & 8) != 8) {
                    this.f536r = new ArrayList(this.f536r);
                    this.f532n |= 8;
                }
                this.f536r.addAll(nVar.f586q);
            }
        }
        if (!nVar.f587r.isEmpty()) {
            if (this.f537s.isEmpty()) {
                this.f537s = nVar.f587r;
                this.f532n &= -17;
            } else {
                if ((this.f532n & 16) != 16) {
                    this.f537s = new ArrayList(this.f537s);
                    this.f532n |= 16;
                }
                this.f537s.addAll(nVar.f587r);
            }
        }
        if (!nVar.f588s.isEmpty()) {
            if (this.f538t.isEmpty()) {
                this.f538t = nVar.f588s;
                this.f532n &= -33;
            } else {
                if ((this.f532n & 32) != 32) {
                    this.f538t = new ArrayList(this.f538t);
                    this.f532n |= 32;
                }
                this.f538t.addAll(nVar.f588s);
            }
        }
        if (!nVar.f590u.isEmpty()) {
            if (this.f539u.isEmpty()) {
                this.f539u = nVar.f590u;
                this.f532n &= -65;
            } else {
                if ((this.f532n & 64) != 64) {
                    this.f539u = new ArrayList(this.f539u);
                    this.f532n |= 64;
                }
                this.f539u.addAll(nVar.f590u);
            }
        }
        if (!nVar.f592w.isEmpty()) {
            if (this.f540v.isEmpty()) {
                this.f540v = nVar.f592w;
                this.f532n &= -129;
            } else {
                if ((this.f532n & 128) != 128) {
                    this.f540v = new ArrayList(this.f540v);
                    this.f532n |= 128;
                }
                this.f540v.addAll(nVar.f592w);
            }
        }
        if (!nVar.x.isEmpty()) {
            if (this.f541w.isEmpty()) {
                this.f541w = nVar.x;
                this.f532n &= -257;
            } else {
                if ((this.f532n & 256) != 256) {
                    this.f541w = new ArrayList(this.f541w);
                    this.f532n |= 256;
                }
                this.f541w.addAll(nVar.x);
            }
        }
        if (!nVar.z.isEmpty()) {
            if (this.x.isEmpty()) {
                this.x = nVar.z;
                this.f532n &= -513;
            } else {
                if ((this.f532n & 512) != 512) {
                    this.x = new ArrayList(this.x);
                    this.f532n |= 512;
                }
                this.x.addAll(nVar.z);
            }
        }
        if (!nVar.A.isEmpty()) {
            if (this.f542y.isEmpty()) {
                this.f542y = nVar.A;
                this.f532n &= -1025;
            } else {
                if ((this.f532n & 1024) != 1024) {
                    this.f542y = new ArrayList(this.f542y);
                    this.f532n |= 1024;
                }
                this.f542y.addAll(nVar.A);
            }
        }
        if (!nVar.B.isEmpty()) {
            if (this.z.isEmpty()) {
                this.z = nVar.B;
                this.f532n &= -2049;
            } else {
                if ((this.f532n & 2048) != 2048) {
                    this.z = new ArrayList(this.z);
                    this.f532n |= 2048;
                }
                this.z.addAll(nVar.B);
            }
        }
        if (!nVar.C.isEmpty()) {
            if (this.A.isEmpty()) {
                this.A = nVar.C;
                this.f532n &= -4097;
            } else {
                if ((this.f532n & 4096) != 4096) {
                    this.A = new ArrayList(this.A);
                    this.f532n |= 4096;
                }
                this.A.addAll(nVar.C);
            }
        }
        if (!nVar.D.isEmpty()) {
            if (this.B.isEmpty()) {
                this.B = nVar.D;
                this.f532n &= -8193;
            } else {
                if ((this.f532n & 8192) != 8192) {
                    this.B = new ArrayList(this.B);
                    this.f532n |= 8192;
                }
                this.B.addAll(nVar.D);
            }
        }
        if (!nVar.E.isEmpty()) {
            if (this.C.isEmpty()) {
                this.C = nVar.E;
                this.f532n &= -16385;
            } else {
                if ((this.f532n & 16384) != 16384) {
                    this.C = new ArrayList(this.C);
                    this.f532n |= 16384;
                }
                this.C.addAll(nVar.E);
            }
        }
        int i14 = nVar.f582m;
        if ((i14 & 8) == 8) {
            int i15 = nVar.G;
            this.f532n |= 32768;
            this.D = i15;
        }
        if ((i14 & 16) == 16) {
            k1 k1Var2 = nVar.H;
            if ((this.f532n & 65536) != 65536 || (k1Var = this.E) == k1.D) {
                this.E = k1Var2;
            } else {
                j1 j1VarP = k1.p(k1Var);
                j1VarP.j(k1Var2);
                this.E = j1VarP.h();
            }
            this.f532n |= 65536;
        }
        if ((nVar.f582m & 32) == 32) {
            int i16 = nVar.I;
            this.f532n |= 131072;
            this.F = i16;
        }
        if (!nVar.J.isEmpty()) {
            if (this.G.isEmpty()) {
                this.G = nVar.J;
                this.f532n &= -262145;
            } else {
                if ((this.f532n & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 262144) {
                    this.G = new ArrayList(this.G);
                    this.f532n |= MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES;
                }
                this.G.addAll(nVar.J);
            }
        }
        if (!nVar.L.isEmpty()) {
            if (this.H.isEmpty()) {
                this.H = nVar.L;
                this.f532n &= -524289;
            } else {
                if ((this.f532n & 524288) != 524288) {
                    this.H = new ArrayList(this.H);
                    this.f532n |= 524288;
                }
                this.H.addAll(nVar.L);
            }
        }
        if (!nVar.M.isEmpty()) {
            if (this.I.isEmpty()) {
                this.I = nVar.M;
                this.f532n &= -1048577;
            } else {
                if ((this.f532n & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 1048576) {
                    this.I = new ArrayList(this.I);
                    this.f532n |= ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES;
                }
                this.I.addAll(nVar.M);
            }
        }
        if ((nVar.f582m & 64) == 64) {
            u1 u1Var2 = nVar.O;
            if ((this.f532n & 2097152) != 2097152 || (u1Var = this.J) == u1.f692q) {
                this.J = u1Var2;
            } else {
                t1 t1VarH = u1.h(u1Var);
                t1VarH.i(u1Var2);
                this.J = t1VarH.g();
            }
            this.f532n |= 2097152;
        }
        if (!nVar.P.isEmpty()) {
            if (this.K.isEmpty()) {
                this.K = nVar.P;
                this.f532n &= -4194305;
            } else {
                if ((this.f532n & 4194304) != 4194304) {
                    this.K = new ArrayList(this.K);
                    this.f532n |= 4194304;
                }
                this.K.addAll(nVar.P);
            }
        }
        if ((nVar.f582m & 128) == 128) {
            f2 f2Var2 = nVar.Q;
            if ((this.f532n & 8388608) != 8388608 || (f2Var = this.L) == f2.f440o) {
                this.L = f2Var2;
            } else {
                e2 e2Var = new e2();
                e2Var.f422m = Collections.EMPTY_LIST;
                e2Var.h(f2Var);
                e2Var.h(f2Var2);
                this.L = e2Var.g();
            }
            this.f532n |= 8388608;
        }
        g(nVar);
        this.f19815i = this.f19815i.b(nVar.f581l);
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
            a9.a r1 = a9.n.U     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            a9.n r1 = new a9.n     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r2.j(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r4 = r3.f19759i     // Catch: java.lang.Throwable -> Lf
            a9.n r4 = (a9.n) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: a9.l.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}
