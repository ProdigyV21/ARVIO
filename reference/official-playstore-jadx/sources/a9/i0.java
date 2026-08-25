package a9;

import androidx.media3.extractor.AacUtil;
import com.arflix.tv.ui.theme.AnimationConstants;
import com.arflix.tv.util.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class i0 extends kotlin.reflect.jvm.internal.impl.protobuf.q implements j0 {
    public static final i0 E;
    public static final a F = new a(9);
    public List A;
    public t B;
    public byte C;
    public int D;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f485l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f486m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f487n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f488o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f489p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public k1 f490q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f491r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public List f492s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public k1 f493t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f494u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public List f495v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public List f496w;
    public int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public List f497y;
    public u1 z;

    static {
        i0 i0Var = new i0();
        E = i0Var;
        i0Var.o();
    }

    public i0(h0 h0Var) {
        super(h0Var);
        this.x = -1;
        this.C = (byte) -1;
        this.D = -1;
        this.f485l = h0Var.f19815i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.D;
        if (i10 != -1) {
            return i10;
        }
        int iB = (this.f486m & 2) == 2 ? kotlin.reflect.jvm.internal.impl.protobuf.j.b(1, this.f488o) : 0;
        if ((this.f486m & 4) == 4) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(2, this.f489p);
        }
        if ((this.f486m & 8) == 8) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(3, this.f490q);
        }
        for (int i11 = 0; i11 < this.f492s.size(); i11++) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(4, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f492s.get(i11));
        }
        if ((this.f486m & 32) == 32) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(5, this.f493t);
        }
        for (int i12 = 0; i12 < this.f497y.size(); i12++) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(6, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f497y.get(i12));
        }
        if ((this.f486m & 16) == 16) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(7, this.f491r);
        }
        if ((this.f486m & 64) == 64) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(8, this.f494u);
        }
        if ((this.f486m & 1) == 1) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(9, this.f487n);
        }
        for (int i13 = 0; i13 < this.f495v.size(); i13++) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(10, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f495v.get(i13));
        }
        int iC = 0;
        for (int i14 = 0; i14 < this.f496w.size(); i14++) {
            iC += kotlin.reflect.jvm.internal.impl.protobuf.j.c(((Integer) this.f496w.get(i14)).intValue());
        }
        int iD = iB + iC;
        if (!this.f496w.isEmpty()) {
            iD = iD + 1 + kotlin.reflect.jvm.internal.impl.protobuf.j.c(iC);
        }
        this.x = iC;
        if ((this.f486m & 128) == 128) {
            iD += kotlin.reflect.jvm.internal.impl.protobuf.j.d(30, this.z);
        }
        int iC2 = 0;
        for (int i15 = 0; i15 < this.A.size(); i15++) {
            iC2 += kotlin.reflect.jvm.internal.impl.protobuf.j.c(((Integer) this.A.get(i15)).intValue());
        }
        int size = (this.A.size() * 2) + iD + iC2;
        if ((this.f486m & 256) == 256) {
            size += kotlin.reflect.jvm.internal.impl.protobuf.j.d(32, this.B);
        }
        int size2 = this.f485l.size() + i() + size;
        this.D = size2;
        return size2;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        h0 h0VarI = h0.i();
        h0VarI.j(this);
        return h0VarI;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        return h0.i();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 d() {
        return E;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        fi.iki.elonen.f fVar = new fi.iki.elonen.f(this);
        if ((this.f486m & 2) == 2) {
            jVar.m(1, this.f488o);
        }
        if ((this.f486m & 4) == 4) {
            jVar.m(2, this.f489p);
        }
        if ((this.f486m & 8) == 8) {
            jVar.o(3, this.f490q);
        }
        for (int i10 = 0; i10 < this.f492s.size(); i10++) {
            jVar.o(4, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f492s.get(i10));
        }
        if ((this.f486m & 32) == 32) {
            jVar.o(5, this.f493t);
        }
        for (int i11 = 0; i11 < this.f497y.size(); i11++) {
            jVar.o(6, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f497y.get(i11));
        }
        if ((this.f486m & 16) == 16) {
            jVar.m(7, this.f491r);
        }
        if ((this.f486m & 64) == 64) {
            jVar.m(8, this.f494u);
        }
        if ((this.f486m & 1) == 1) {
            jVar.m(9, this.f487n);
        }
        for (int i12 = 0; i12 < this.f495v.size(); i12++) {
            jVar.o(10, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f495v.get(i12));
        }
        if (this.f496w.size() > 0) {
            jVar.v(90);
            jVar.v(this.x);
        }
        for (int i13 = 0; i13 < this.f496w.size(); i13++) {
            jVar.n(((Integer) this.f496w.get(i13)).intValue());
        }
        if ((this.f486m & 128) == 128) {
            jVar.o(30, this.z);
        }
        for (int i14 = 0; i14 < this.A.size(); i14++) {
            jVar.m(31, ((Integer) this.A.get(i14)).intValue());
        }
        if ((this.f486m & 256) == 256) {
            jVar.o(32, this.B);
        }
        fVar.f0(19000, jVar);
        jVar.r(this.f485l);
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
        int i10 = this.f486m;
        if ((i10 & 4) != 4) {
            this.C = (byte) 0;
            return false;
        }
        if ((i10 & 8) == 8 && !this.f490q.isInitialized()) {
            this.C = (byte) 0;
            return false;
        }
        for (int i11 = 0; i11 < this.f492s.size(); i11++) {
            if (!((r1) this.f492s.get(i11)).isInitialized()) {
                this.C = (byte) 0;
                return false;
            }
        }
        if ((this.f486m & 32) == 32 && !this.f493t.isInitialized()) {
            this.C = (byte) 0;
            return false;
        }
        for (int i12 = 0; i12 < this.f495v.size(); i12++) {
            if (!((k1) this.f495v.get(i12)).isInitialized()) {
                this.C = (byte) 0;
                return false;
            }
        }
        for (int i13 = 0; i13 < this.f497y.size(); i13++) {
            if (!((x1) this.f497y.get(i13)).isInitialized()) {
                this.C = (byte) 0;
                return false;
            }
        }
        if ((this.f486m & 128) == 128 && !this.z.isInitialized()) {
            this.C = (byte) 0;
            return false;
        }
        if ((this.f486m & 256) == 256 && !this.B.isInitialized()) {
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
        this.f487n = 6;
        this.f488o = 6;
        this.f489p = 0;
        k1 k1Var = k1.D;
        this.f490q = k1Var;
        this.f491r = 0;
        List list = Collections.EMPTY_LIST;
        this.f492s = list;
        this.f493t = k1Var;
        this.f494u = 0;
        this.f495v = list;
        this.f496w = list;
        this.f497y = list;
        this.z = u1.f692q;
        this.A = list;
        this.B = t.f670o;
    }

    public i0() {
        this.x = -1;
        this.C = (byte) -1;
        this.D = -1;
        this.f485l = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [boolean] */
    public i0(kotlin.reflect.jvm.internal.impl.protobuf.i iVar, kotlin.reflect.jvm.internal.impl.protobuf.l lVar) {
        this.x = -1;
        this.C = (byte) -1;
        this.D = -1;
        o();
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        boolean z = false;
        int i10 = 0;
        while (true) {
            ?? M = 1024;
            if (!z) {
                try {
                    try {
                        int iN = iVar.n();
                        j1 j1VarP = null;
                        s sVar = null;
                        t1 t1VarH = null;
                        j1 j1VarP2 = null;
                        switch (iN) {
                            case 0:
                                z = true;
                                break;
                            case 8:
                                this.f486m |= 2;
                                this.f488o = iVar.k();
                                break;
                            case 16:
                                this.f486m |= 4;
                                this.f489p = iVar.k();
                                break;
                            case 26:
                                if ((this.f486m & 8) == 8) {
                                    k1 k1Var = this.f490q;
                                    k1Var.getClass();
                                    j1VarP = k1.p(k1Var);
                                }
                                k1 k1Var2 = (k1) iVar.g(k1.E, lVar);
                                this.f490q = k1Var2;
                                if (j1VarP != null) {
                                    j1VarP.j(k1Var2);
                                    this.f490q = j1VarP.h();
                                }
                                this.f486m |= 8;
                                break;
                            case 34:
                                int i11 = (i10 == true ? 1 : 0) & 32;
                                i10 = i10;
                                if (i11 != 32) {
                                    this.f492s = new ArrayList();
                                    i10 = (i10 == true ? 1 : 0) | 32;
                                }
                                this.f492s.add(iVar.g(r1.x, lVar));
                                break;
                            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                                if ((this.f486m & 32) == 32) {
                                    k1 k1Var3 = this.f493t;
                                    k1Var3.getClass();
                                    j1VarP2 = k1.p(k1Var3);
                                }
                                k1 k1Var4 = (k1) iVar.g(k1.E, lVar);
                                this.f493t = k1Var4;
                                if (j1VarP2 != null) {
                                    j1VarP2.j(k1Var4);
                                    this.f493t = j1VarP2.h();
                                }
                                this.f486m |= 32;
                                break;
                            case 50:
                                int i12 = (i10 == true ? 1 : 0) & 1024;
                                i10 = i10;
                                if (i12 != 1024) {
                                    this.f497y = new ArrayList();
                                    i10 = (i10 == true ? 1 : 0) | 1024;
                                }
                                this.f497y.add(iVar.g(x1.f734w, lVar));
                                break;
                            case 56:
                                this.f486m |= 16;
                                this.f491r = iVar.k();
                                break;
                            case 64:
                                this.f486m |= 64;
                                this.f494u = iVar.k();
                                break;
                            case 72:
                                this.f486m |= 1;
                                this.f487n = iVar.k();
                                break;
                            case 82:
                                int i13 = (i10 == true ? 1 : 0) & 256;
                                i10 = i10;
                                if (i13 != 256) {
                                    this.f495v = new ArrayList();
                                    i10 = (i10 == true ? 1 : 0) | 256;
                                }
                                this.f495v.add(iVar.g(k1.E, lVar));
                                break;
                            case 88:
                                int i14 = (i10 == true ? 1 : 0) & 512;
                                i10 = i10;
                                if (i14 != 512) {
                                    this.f496w = new ArrayList();
                                    i10 = (i10 == true ? 1 : 0) | 512;
                                }
                                this.f496w.add(Integer.valueOf(iVar.k()));
                                break;
                            case Constants.WATCHED_THRESHOLD /* 90 */:
                                int iD = iVar.d(iVar.k());
                                int i15 = (i10 == true ? 1 : 0) & 512;
                                i10 = i10;
                                if (i15 != 512) {
                                    i10 = i10;
                                    if (iVar.b() > 0) {
                                        this.f496w = new ArrayList();
                                        i10 = (i10 == true ? 1 : 0) | 512;
                                    }
                                }
                                while (iVar.b() > 0) {
                                    this.f496w.add(Integer.valueOf(iVar.k()));
                                }
                                iVar.c(iD);
                                break;
                            case 242:
                                if ((this.f486m & 128) == 128) {
                                    u1 u1Var = this.z;
                                    u1Var.getClass();
                                    t1VarH = u1.h(u1Var);
                                }
                                u1 u1Var2 = (u1) iVar.g(u1.f693r, lVar);
                                this.z = u1Var2;
                                if (t1VarH != null) {
                                    t1VarH.i(u1Var2);
                                    this.z = t1VarH.g();
                                }
                                this.f486m |= 128;
                                break;
                            case 248:
                                int i16 = (i10 == true ? 1 : 0) & 4096;
                                i10 = i10;
                                if (i16 != 4096) {
                                    this.A = new ArrayList();
                                    i10 = (i10 == true ? 1 : 0) | 4096;
                                }
                                this.A.add(Integer.valueOf(iVar.k()));
                                break;
                            case AnimationConstants.DURATION_IMAGE_CROSSFADE /* 250 */:
                                int iD2 = iVar.d(iVar.k());
                                int i17 = (i10 == true ? 1 : 0) & 4096;
                                i10 = i10;
                                if (i17 != 4096) {
                                    i10 = i10;
                                    if (iVar.b() > 0) {
                                        this.A = new ArrayList();
                                        i10 = (i10 == true ? 1 : 0) | 4096;
                                    }
                                }
                                while (iVar.b() > 0) {
                                    this.A.add(Integer.valueOf(iVar.k()));
                                }
                                iVar.c(iD2);
                                break;
                            case 258:
                                if ((this.f486m & 256) == 256) {
                                    t tVar = this.B;
                                    tVar.getClass();
                                    sVar = new s();
                                    sVar.f658m = Collections.EMPTY_LIST;
                                    sVar.h(tVar);
                                }
                                t tVar2 = (t) iVar.g(t.f671p, lVar);
                                this.B = tVar2;
                                if (sVar != null) {
                                    sVar.h(tVar2);
                                    this.B = sVar.g();
                                }
                                this.f486m |= 256;
                                break;
                            default:
                                M = m(iVar, jVarJ, lVar, iN);
                                if (M == 0) {
                                    z = true;
                                }
                                break;
                        }
                    } catch (InvalidProtocolBufferException e5) {
                        e5.f19759i = this;
                        throw e5;
                    } catch (IOException e6) {
                        InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException(e6.getMessage());
                        invalidProtocolBufferException.f19759i = this;
                        throw invalidProtocolBufferException;
                    }
                } catch (Throwable th) {
                    if (((i10 == true ? 1 : 0) & 32) == 32) {
                        this.f492s = Collections.unmodifiableList(this.f492s);
                    }
                    if (((i10 == true ? 1 : 0) & 1024) == M) {
                        this.f497y = Collections.unmodifiableList(this.f497y);
                    }
                    if (((i10 == true ? 1 : 0) & 256) == 256) {
                        this.f495v = Collections.unmodifiableList(this.f495v);
                    }
                    if (((i10 == true ? 1 : 0) & 512) == 512) {
                        this.f496w = Collections.unmodifiableList(this.f496w);
                    }
                    if (((i10 == true ? 1 : 0) & 4096) == 4096) {
                        this.A = Collections.unmodifiableList(this.A);
                    }
                    try {
                        jVarJ.i();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.f485l = fVar.k();
                        throw th2;
                    }
                    this.f485l = fVar.k();
                    l();
                    throw th;
                }
            } else {
                if (((i10 == true ? 1 : 0) & 32) == 32) {
                    this.f492s = Collections.unmodifiableList(this.f492s);
                }
                if (((i10 == true ? 1 : 0) & 1024) == 1024) {
                    this.f497y = Collections.unmodifiableList(this.f497y);
                }
                if (((i10 == true ? 1 : 0) & 256) == 256) {
                    this.f495v = Collections.unmodifiableList(this.f495v);
                }
                if (((i10 == true ? 1 : 0) & 512) == 512) {
                    this.f496w = Collections.unmodifiableList(this.f496w);
                }
                if (((i10 == true ? 1 : 0) & 4096) == 4096) {
                    this.A = Collections.unmodifiableList(this.A);
                }
                try {
                    jVarJ.i();
                } catch (IOException unused2) {
                } catch (Throwable th3) {
                    this.f485l = fVar.k();
                    throw th3;
                }
                this.f485l = fVar.k();
                l();
                return;
            }
        }
    }
}
