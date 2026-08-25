package a9;

import androidx.media3.extractor.AacUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class k1 extends kotlin.reflect.jvm.internal.impl.protobuf.q implements o1 {
    public static final k1 D;
    public static final a E = new a(16);
    public int A;
    public byte B;
    public int C;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f519l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f520m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public List f521n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f522o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f523p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public k1 f524q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f525r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f526s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f527t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f528u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f529v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public k1 f530w;
    public int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public k1 f531y;
    public int z;

    static {
        k1 k1Var = new k1();
        D = k1Var;
        k1Var.o();
    }

    public k1(j1 j1Var) {
        super(j1Var);
        this.B = (byte) -1;
        this.C = -1;
        this.f519l = j1Var.f19815i;
    }

    public static j1 p(k1 k1Var) {
        j1 j1VarI = j1.i();
        j1VarI.j(k1Var);
        return j1VarI;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.C;
        if (i10 != -1) {
            return i10;
        }
        int iB = (this.f520m & 4096) == 4096 ? kotlin.reflect.jvm.internal.impl.protobuf.j.b(1, this.A) : 0;
        for (int i11 = 0; i11 < this.f521n.size(); i11++) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(2, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f521n.get(i11));
        }
        if ((this.f520m & 1) == 1) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.h(3) + 1;
        }
        if ((this.f520m & 2) == 2) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(4, this.f523p);
        }
        if ((this.f520m & 4) == 4) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(5, this.f524q);
        }
        if ((this.f520m & 16) == 16) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(6, this.f526s);
        }
        if ((this.f520m & 32) == 32) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(7, this.f527t);
        }
        if ((this.f520m & 8) == 8) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(8, this.f525r);
        }
        if ((this.f520m & 64) == 64) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(9, this.f528u);
        }
        if ((this.f520m & 256) == 256) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(10, this.f530w);
        }
        if ((this.f520m & 512) == 512) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(11, this.x);
        }
        if ((this.f520m & 128) == 128) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(12, this.f529v);
        }
        if ((this.f520m & 1024) == 1024) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(13, this.f531y);
        }
        if ((this.f520m & 2048) == 2048) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(14, this.z);
        }
        int size = this.f519l.size() + i() + iB;
        this.C = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        return j1.i();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 d() {
        return D;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) throws IOException {
        a();
        fi.iki.elonen.f fVar = new fi.iki.elonen.f(this);
        if ((this.f520m & 4096) == 4096) {
            jVar.m(1, this.A);
        }
        for (int i10 = 0; i10 < this.f521n.size(); i10++) {
            jVar.o(2, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f521n.get(i10));
        }
        if ((this.f520m & 1) == 1) {
            boolean z = this.f522o;
            jVar.x(3, 0);
            jVar.q(z ? 1 : 0);
        }
        if ((this.f520m & 2) == 2) {
            jVar.m(4, this.f523p);
        }
        if ((this.f520m & 4) == 4) {
            jVar.o(5, this.f524q);
        }
        if ((this.f520m & 16) == 16) {
            jVar.m(6, this.f526s);
        }
        if ((this.f520m & 32) == 32) {
            jVar.m(7, this.f527t);
        }
        if ((this.f520m & 8) == 8) {
            jVar.m(8, this.f525r);
        }
        if ((this.f520m & 64) == 64) {
            jVar.m(9, this.f528u);
        }
        if ((this.f520m & 256) == 256) {
            jVar.o(10, this.f530w);
        }
        if ((this.f520m & 512) == 512) {
            jVar.m(11, this.x);
        }
        if ((this.f520m & 128) == 128) {
            jVar.m(12, this.f529v);
        }
        if ((this.f520m & 1024) == 1024) {
            jVar.o(13, this.f531y);
        }
        if ((this.f520m & 2048) == 2048) {
            jVar.m(14, this.z);
        }
        fVar.f0(200, jVar);
        jVar.r(this.f519l);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        byte b10 = this.B;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        for (int i10 = 0; i10 < this.f521n.size(); i10++) {
            if (!((h1) this.f521n.get(i10)).isInitialized()) {
                this.B = (byte) 0;
                return false;
            }
        }
        if ((this.f520m & 4) == 4 && !this.f524q.isInitialized()) {
            this.B = (byte) 0;
            return false;
        }
        if ((this.f520m & 256) == 256 && !this.f530w.isInitialized()) {
            this.B = (byte) 0;
            return false;
        }
        if ((this.f520m & 1024) == 1024 && !this.f531y.isInitialized()) {
            this.B = (byte) 0;
            return false;
        }
        if (h()) {
            this.B = (byte) 1;
            return true;
        }
        this.B = (byte) 0;
        return false;
    }

    public final void o() {
        this.f521n = Collections.EMPTY_LIST;
        this.f522o = false;
        this.f523p = 0;
        k1 k1Var = D;
        this.f524q = k1Var;
        this.f525r = 0;
        this.f526s = 0;
        this.f527t = 0;
        this.f528u = 0;
        this.f529v = 0;
        this.f530w = k1Var;
        this.x = 0;
        this.f531y = k1Var;
        this.z = 0;
        this.A = 0;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final j1 b() {
        return p(this);
    }

    public k1() {
        this.B = (byte) -1;
        this.C = -1;
        this.f519l = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    public k1(kotlin.reflect.jvm.internal.impl.protobuf.i iVar, kotlin.reflect.jvm.internal.impl.protobuf.l lVar) {
        this.B = (byte) -1;
        this.C = -1;
        o();
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        boolean z = false;
        boolean z5 = false;
        while (!z) {
            try {
                try {
                    int iN = iVar.n();
                    a aVar = E;
                    j1 j1VarP = null;
                    switch (iN) {
                        case 0:
                            break;
                        case 8:
                            this.f520m |= 4096;
                            this.A = iVar.k();
                            continue;
                        case 18:
                            if (!z5) {
                                this.f521n = new ArrayList();
                                z5 = true;
                            }
                            this.f521n.add(iVar.g(h1.f472s, lVar));
                            continue;
                        case 24:
                            this.f520m |= 1;
                            this.f522o = iVar.l() != 0;
                            continue;
                        case 32:
                            this.f520m |= 2;
                            this.f523p = iVar.k();
                            continue;
                        case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                            if ((this.f520m & 4) == 4) {
                                k1 k1Var = this.f524q;
                                k1Var.getClass();
                                j1VarP = p(k1Var);
                            }
                            k1 k1Var2 = (k1) iVar.g(aVar, lVar);
                            this.f524q = k1Var2;
                            if (j1VarP != null) {
                                j1VarP.j(k1Var2);
                                this.f524q = j1VarP.h();
                            }
                            this.f520m |= 4;
                            continue;
                        case 48:
                            this.f520m |= 16;
                            this.f526s = iVar.k();
                            continue;
                        case 56:
                            this.f520m |= 32;
                            this.f527t = iVar.k();
                            continue;
                        case 64:
                            this.f520m |= 8;
                            this.f525r = iVar.k();
                            continue;
                        case 72:
                            this.f520m |= 64;
                            this.f528u = iVar.k();
                            continue;
                        case 82:
                            if ((this.f520m & 256) == 256) {
                                k1 k1Var3 = this.f530w;
                                k1Var3.getClass();
                                j1VarP = p(k1Var3);
                            }
                            k1 k1Var4 = (k1) iVar.g(aVar, lVar);
                            this.f530w = k1Var4;
                            if (j1VarP != null) {
                                j1VarP.j(k1Var4);
                                this.f530w = j1VarP.h();
                            }
                            this.f520m |= 256;
                            continue;
                        case 88:
                            this.f520m |= 512;
                            this.x = iVar.k();
                            continue;
                        case 96:
                            this.f520m |= 128;
                            this.f529v = iVar.k();
                            continue;
                        case 106:
                            if ((this.f520m & 1024) == 1024) {
                                k1 k1Var5 = this.f531y;
                                k1Var5.getClass();
                                j1VarP = p(k1Var5);
                            }
                            k1 k1Var6 = (k1) iVar.g(aVar, lVar);
                            this.f531y = k1Var6;
                            if (j1VarP != null) {
                                j1VarP.j(k1Var6);
                                this.f531y = j1VarP.h();
                            }
                            this.f520m |= 1024;
                            continue;
                        case 112:
                            this.f520m |= 2048;
                            this.z = iVar.k();
                            continue;
                        default:
                            if (!m(iVar, jVarJ, lVar, iN)) {
                            }
                            break;
                    }
                    z = true;
                } catch (Throwable th) {
                    if (z5) {
                        this.f521n = Collections.unmodifiableList(this.f521n);
                    }
                    try {
                        jVarJ.i();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.f519l = fVar.k();
                        throw th2;
                    }
                    this.f519l = fVar.k();
                    l();
                    throw th;
                }
            } catch (InvalidProtocolBufferException e5) {
                e5.f19759i = this;
                throw e5;
            } catch (IOException e6) {
                InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException(e6.getMessage());
                invalidProtocolBufferException.f19759i = this;
                throw invalidProtocolBufferException;
            }
        }
        if (z5) {
            this.f521n = Collections.unmodifiableList(this.f521n);
        }
        try {
            jVarJ.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f519l = fVar.k();
            throw th3;
        }
        this.f519l = fVar.k();
        l();
    }
}
