package a9;

import androidx.media3.extractor.AacUtil;
import com.arflix.tv.ui.theme.AnimationConstants;
import com.google.android.gms.cast.MediaError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class t0 extends kotlin.reflect.jvm.internal.impl.protobuf.q implements u0 {
    public static final t0 E;
    public static final a F = new a(12);
    public int A;
    public List B;
    public byte C;
    public int D;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f676l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f677m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f678n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f679o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f680p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public k1 f681q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f682r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public List f683s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public k1 f684t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f685u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public List f686v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public List f687w;
    public int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public x1 f688y;
    public int z;

    static {
        t0 t0Var = new t0();
        E = t0Var;
        t0Var.o();
    }

    public t0(s0 s0Var) {
        super(s0Var);
        this.x = -1;
        this.C = (byte) -1;
        this.D = -1;
        this.f676l = s0Var.f19815i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.D;
        if (i10 != -1) {
            return i10;
        }
        int iB = (this.f677m & 2) == 2 ? kotlin.reflect.jvm.internal.impl.protobuf.j.b(1, this.f679o) : 0;
        if ((this.f677m & 4) == 4) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(2, this.f680p);
        }
        if ((this.f677m & 8) == 8) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(3, this.f681q);
        }
        for (int i11 = 0; i11 < this.f683s.size(); i11++) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(4, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f683s.get(i11));
        }
        if ((this.f677m & 32) == 32) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(5, this.f684t);
        }
        if ((this.f677m & 128) == 128) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(6, this.f688y);
        }
        if ((this.f677m & 256) == 256) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(7, this.z);
        }
        if ((this.f677m & 512) == 512) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(8, this.A);
        }
        if ((this.f677m & 16) == 16) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(9, this.f682r);
        }
        if ((this.f677m & 64) == 64) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(10, this.f685u);
        }
        if ((this.f677m & 1) == 1) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(11, this.f678n);
        }
        for (int i12 = 0; i12 < this.f686v.size(); i12++) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(12, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f686v.get(i12));
        }
        int iC = 0;
        for (int i13 = 0; i13 < this.f687w.size(); i13++) {
            iC += kotlin.reflect.jvm.internal.impl.protobuf.j.c(((Integer) this.f687w.get(i13)).intValue());
        }
        int iC2 = iB + iC;
        if (!this.f687w.isEmpty()) {
            iC2 = iC2 + 1 + kotlin.reflect.jvm.internal.impl.protobuf.j.c(iC);
        }
        this.x = iC;
        int iC3 = 0;
        for (int i14 = 0; i14 < this.B.size(); i14++) {
            iC3 += kotlin.reflect.jvm.internal.impl.protobuf.j.c(((Integer) this.B.get(i14)).intValue());
        }
        int size = this.f676l.size() + i() + (this.B.size() * 2) + iC2 + iC3;
        this.D = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        s0 s0VarI = s0.i();
        s0VarI.j(this);
        return s0VarI;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        return s0.i();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 d() {
        return E;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        fi.iki.elonen.f fVar = new fi.iki.elonen.f(this);
        if ((this.f677m & 2) == 2) {
            jVar.m(1, this.f679o);
        }
        if ((this.f677m & 4) == 4) {
            jVar.m(2, this.f680p);
        }
        if ((this.f677m & 8) == 8) {
            jVar.o(3, this.f681q);
        }
        for (int i10 = 0; i10 < this.f683s.size(); i10++) {
            jVar.o(4, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f683s.get(i10));
        }
        if ((this.f677m & 32) == 32) {
            jVar.o(5, this.f684t);
        }
        if ((this.f677m & 128) == 128) {
            jVar.o(6, this.f688y);
        }
        if ((this.f677m & 256) == 256) {
            jVar.m(7, this.z);
        }
        if ((this.f677m & 512) == 512) {
            jVar.m(8, this.A);
        }
        if ((this.f677m & 16) == 16) {
            jVar.m(9, this.f682r);
        }
        if ((this.f677m & 64) == 64) {
            jVar.m(10, this.f685u);
        }
        if ((this.f677m & 1) == 1) {
            jVar.m(11, this.f678n);
        }
        for (int i11 = 0; i11 < this.f686v.size(); i11++) {
            jVar.o(12, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f686v.get(i11));
        }
        if (this.f687w.size() > 0) {
            jVar.v(106);
            jVar.v(this.x);
        }
        for (int i12 = 0; i12 < this.f687w.size(); i12++) {
            jVar.n(((Integer) this.f687w.get(i12)).intValue());
        }
        for (int i13 = 0; i13 < this.B.size(); i13++) {
            jVar.m(31, ((Integer) this.B.get(i13)).intValue());
        }
        fVar.f0(19000, jVar);
        jVar.r(this.f676l);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        byte b10 = this.C;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        int i10 = this.f677m;
        if ((i10 & 4) != 4) {
            this.C = (byte) 0;
            return false;
        }
        if ((i10 & 8) == 8 && !this.f681q.isInitialized()) {
            this.C = (byte) 0;
            return false;
        }
        for (int i11 = 0; i11 < this.f683s.size(); i11++) {
            if (!((r1) this.f683s.get(i11)).isInitialized()) {
                this.C = (byte) 0;
                return false;
            }
        }
        if ((this.f677m & 32) == 32 && !this.f684t.isInitialized()) {
            this.C = (byte) 0;
            return false;
        }
        for (int i12 = 0; i12 < this.f686v.size(); i12++) {
            if (!((k1) this.f686v.get(i12)).isInitialized()) {
                this.C = (byte) 0;
                return false;
            }
        }
        if ((this.f677m & 128) == 128 && !this.f688y.isInitialized()) {
            this.C = (byte) 0;
            return false;
        }
        if (h()) {
            this.C = (byte) 1;
            return true;
        }
        this.C = (byte) 0;
        return false;
    }

    public final void o() {
        this.f678n = 518;
        this.f679o = 2054;
        this.f680p = 0;
        k1 k1Var = k1.D;
        this.f681q = k1Var;
        this.f682r = 0;
        List list = Collections.EMPTY_LIST;
        this.f683s = list;
        this.f684t = k1Var;
        this.f685u = 0;
        this.f686v = list;
        this.f687w = list;
        this.f688y = x1.f733v;
        this.z = 0;
        this.A = 0;
        this.B = list;
    }

    public t0() {
        this.x = -1;
        this.C = (byte) -1;
        this.D = -1;
        this.f676l = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [boolean] */
    public t0(kotlin.reflect.jvm.internal.impl.protobuf.i iVar, kotlin.reflect.jvm.internal.impl.protobuf.l lVar) {
        this.x = -1;
        this.C = (byte) -1;
        this.D = -1;
        o();
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        boolean z = false;
        int i10 = 0;
        while (true) {
            ?? M = 256;
            if (!z) {
                try {
                    try {
                        try {
                            int iN = iVar.n();
                            j1 j1VarP = null;
                            w1 w1Var = null;
                            j1 j1VarP2 = null;
                            switch (iN) {
                                case 0:
                                    z = true;
                                    break;
                                case 8:
                                    this.f677m |= 2;
                                    this.f679o = iVar.k();
                                    break;
                                case 16:
                                    this.f677m |= 4;
                                    this.f680p = iVar.k();
                                    break;
                                case 26:
                                    if ((this.f677m & 8) == 8) {
                                        k1 k1Var = this.f681q;
                                        k1Var.getClass();
                                        j1VarP = k1.p(k1Var);
                                    }
                                    k1 k1Var2 = (k1) iVar.g(k1.E, lVar);
                                    this.f681q = k1Var2;
                                    if (j1VarP != null) {
                                        j1VarP.j(k1Var2);
                                        this.f681q = j1VarP.h();
                                    }
                                    this.f677m |= 8;
                                    break;
                                case 34:
                                    int i11 = (i10 == true ? 1 : 0) & 32;
                                    i10 = i10;
                                    if (i11 != 32) {
                                        this.f683s = new ArrayList();
                                        i10 = (i10 == true ? 1 : 0) | 32;
                                    }
                                    this.f683s.add(iVar.g(r1.x, lVar));
                                    break;
                                case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                                    if ((this.f677m & 32) == 32) {
                                        k1 k1Var3 = this.f684t;
                                        k1Var3.getClass();
                                        j1VarP2 = k1.p(k1Var3);
                                    }
                                    k1 k1Var4 = (k1) iVar.g(k1.E, lVar);
                                    this.f684t = k1Var4;
                                    if (j1VarP2 != null) {
                                        j1VarP2.j(k1Var4);
                                        this.f684t = j1VarP2.h();
                                    }
                                    this.f677m |= 32;
                                    break;
                                case 50:
                                    if ((this.f677m & 128) == 128) {
                                        x1 x1Var = this.f688y;
                                        x1Var.getClass();
                                        w1Var = new w1();
                                        k1 k1Var5 = k1.D;
                                        w1Var.f719q = k1Var5;
                                        w1Var.f721s = k1Var5;
                                        w1Var.i(x1Var);
                                    }
                                    x1 x1Var2 = (x1) iVar.g(x1.f734w, lVar);
                                    this.f688y = x1Var2;
                                    if (w1Var != null) {
                                        w1Var.i(x1Var2);
                                        this.f688y = w1Var.h();
                                    }
                                    this.f677m |= 128;
                                    break;
                                case 56:
                                    this.f677m |= 256;
                                    this.z = iVar.k();
                                    break;
                                case 64:
                                    this.f677m |= 512;
                                    this.A = iVar.k();
                                    break;
                                case 72:
                                    this.f677m |= 16;
                                    this.f682r = iVar.k();
                                    break;
                                case AnimationConstants.STAGGER_SECTION /* 80 */:
                                    this.f677m |= 64;
                                    this.f685u = iVar.k();
                                    break;
                                case 88:
                                    this.f677m |= 1;
                                    this.f678n = iVar.k();
                                    break;
                                case 98:
                                    int i12 = (i10 == true ? 1 : 0) & 256;
                                    i10 = i10;
                                    if (i12 != 256) {
                                        this.f686v = new ArrayList();
                                        i10 = (i10 == true ? 1 : 0) | 256;
                                    }
                                    this.f686v.add(iVar.g(k1.E, lVar));
                                    break;
                                case MediaError.DetailedErrorCode.MEDIA_SRC_NOT_SUPPORTED /* 104 */:
                                    int i13 = (i10 == true ? 1 : 0) & 512;
                                    i10 = i10;
                                    if (i13 != 512) {
                                        this.f687w = new ArrayList();
                                        i10 = (i10 == true ? 1 : 0) | 512;
                                    }
                                    this.f687w.add(Integer.valueOf(iVar.k()));
                                    break;
                                case 106:
                                    int iD = iVar.d(iVar.k());
                                    int i14 = (i10 == true ? 1 : 0) & 512;
                                    i10 = i10;
                                    if (i14 != 512) {
                                        i10 = i10;
                                        if (iVar.b() > 0) {
                                            this.f687w = new ArrayList();
                                            i10 = (i10 == true ? 1 : 0) | 512;
                                        }
                                    }
                                    while (iVar.b() > 0) {
                                        this.f687w.add(Integer.valueOf(iVar.k()));
                                    }
                                    iVar.c(iD);
                                    break;
                                case 248:
                                    int i15 = (i10 == true ? 1 : 0) & 8192;
                                    i10 = i10;
                                    if (i15 != 8192) {
                                        this.B = new ArrayList();
                                        i10 = (i10 == true ? 1 : 0) | 8192;
                                    }
                                    this.B.add(Integer.valueOf(iVar.k()));
                                    break;
                                case AnimationConstants.DURATION_IMAGE_CROSSFADE /* 250 */:
                                    int iD2 = iVar.d(iVar.k());
                                    int i16 = (i10 == true ? 1 : 0) & 8192;
                                    i10 = i10;
                                    if (i16 != 8192) {
                                        i10 = i10;
                                        if (iVar.b() > 0) {
                                            this.B = new ArrayList();
                                            i10 = (i10 == true ? 1 : 0) | 8192;
                                        }
                                    }
                                    while (iVar.b() > 0) {
                                        this.B.add(Integer.valueOf(iVar.k()));
                                    }
                                    iVar.c(iD2);
                                    break;
                                default:
                                    M = m(iVar, jVarJ, lVar, iN);
                                    if (M == 0) {
                                        z = true;
                                    }
                                    break;
                            }
                        } catch (IOException e5) {
                            InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException(e5.getMessage());
                            invalidProtocolBufferException.f19759i = this;
                            throw invalidProtocolBufferException;
                        }
                    } catch (InvalidProtocolBufferException e6) {
                        e6.f19759i = this;
                        throw e6;
                    }
                } catch (Throwable th) {
                    if (((i10 == true ? 1 : 0) & 32) == 32) {
                        this.f683s = Collections.unmodifiableList(this.f683s);
                    }
                    if (((i10 == true ? 1 : 0) & 256) == M) {
                        this.f686v = Collections.unmodifiableList(this.f686v);
                    }
                    if (((i10 == true ? 1 : 0) & 512) == 512) {
                        this.f687w = Collections.unmodifiableList(this.f687w);
                    }
                    if (((i10 == true ? 1 : 0) & 8192) == 8192) {
                        this.B = Collections.unmodifiableList(this.B);
                    }
                    try {
                        jVarJ.i();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.f676l = fVar.k();
                        throw th2;
                    }
                    this.f676l = fVar.k();
                    l();
                    throw th;
                }
            } else {
                if (((i10 == true ? 1 : 0) & 32) == 32) {
                    this.f683s = Collections.unmodifiableList(this.f683s);
                }
                if (((i10 == true ? 1 : 0) & 256) == 256) {
                    this.f686v = Collections.unmodifiableList(this.f686v);
                }
                if (((i10 == true ? 1 : 0) & 512) == 512) {
                    this.f687w = Collections.unmodifiableList(this.f687w);
                }
                if (((i10 == true ? 1 : 0) & 8192) == 8192) {
                    this.B = Collections.unmodifiableList(this.B);
                }
                try {
                    jVarJ.i();
                } catch (IOException unused2) {
                } catch (Throwable th3) {
                    this.f676l = fVar.k();
                    throw th3;
                }
                this.f676l = fVar.k();
                l();
                return;
            }
        }
    }
}
