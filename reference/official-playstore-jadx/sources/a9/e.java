package a9;

import com.arflix.tv.ui.theme.AnimationConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class e extends kotlin.reflect.jvm.internal.impl.protobuf.u implements f {
    public static final a A = new a(2);
    public static final e z;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f402i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f403l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public d f404m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f405n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f406o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public double f407p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f408q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f409r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f410s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public j f411t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public List f412u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f413v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f414w;
    public byte x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f415y;

    static {
        e eVar = new e();
        z = eVar;
        eVar.h();
    }

    public e() {
        this.x = (byte) -1;
        this.f415y = -1;
        this.f402i = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.f415y;
        if (i10 != -1) {
            return i10;
        }
        int iA = (this.f403l & 1) == 1 ? kotlin.reflect.jvm.internal.impl.protobuf.j.a(1, this.f404m.f387i) : 0;
        if ((this.f403l & 2) == 2) {
            long j10 = this.f405n;
            iA += kotlin.reflect.jvm.internal.impl.protobuf.j.g((j10 >> 63) ^ (j10 << 1)) + kotlin.reflect.jvm.internal.impl.protobuf.j.h(2);
        }
        if ((this.f403l & 4) == 4) {
            iA += kotlin.reflect.jvm.internal.impl.protobuf.j.h(3) + 4;
        }
        if ((this.f403l & 8) == 8) {
            iA += kotlin.reflect.jvm.internal.impl.protobuf.j.h(4) + 8;
        }
        if ((this.f403l & 16) == 16) {
            iA += kotlin.reflect.jvm.internal.impl.protobuf.j.b(5, this.f408q);
        }
        if ((this.f403l & 32) == 32) {
            iA += kotlin.reflect.jvm.internal.impl.protobuf.j.b(6, this.f409r);
        }
        if ((this.f403l & 64) == 64) {
            iA += kotlin.reflect.jvm.internal.impl.protobuf.j.b(7, this.f410s);
        }
        if ((this.f403l & 128) == 128) {
            iA += kotlin.reflect.jvm.internal.impl.protobuf.j.d(8, this.f411t);
        }
        for (int i11 = 0; i11 < this.f412u.size(); i11++) {
            iA += kotlin.reflect.jvm.internal.impl.protobuf.j.d(9, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f412u.get(i11));
        }
        if ((this.f403l & 512) == 512) {
            iA += kotlin.reflect.jvm.internal.impl.protobuf.j.b(10, this.f414w);
        }
        if ((this.f403l & 256) == 256) {
            iA += kotlin.reflect.jvm.internal.impl.protobuf.j.b(11, this.f413v);
        }
        int size = this.f402i.size() + iA;
        this.f415y = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        c cVarH = c.h();
        cVarH.i(this);
        return cVarH;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        return c.h();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        if ((this.f403l & 1) == 1) {
            jVar.l(1, this.f404m.f387i);
        }
        if ((this.f403l & 2) == 2) {
            long j10 = this.f405n;
            jVar.x(2, 0);
            jVar.w((j10 >> 63) ^ (j10 << 1));
        }
        if ((this.f403l & 4) == 4) {
            float f10 = this.f406o;
            jVar.x(3, 5);
            jVar.t(Float.floatToRawIntBits(f10));
        }
        if ((this.f403l & 8) == 8) {
            double d4 = this.f407p;
            jVar.x(4, 1);
            jVar.u(Double.doubleToRawLongBits(d4));
        }
        if ((this.f403l & 16) == 16) {
            jVar.m(5, this.f408q);
        }
        if ((this.f403l & 32) == 32) {
            jVar.m(6, this.f409r);
        }
        if ((this.f403l & 64) == 64) {
            jVar.m(7, this.f410s);
        }
        if ((this.f403l & 128) == 128) {
            jVar.o(8, this.f411t);
        }
        for (int i10 = 0; i10 < this.f412u.size(); i10++) {
            jVar.o(9, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f412u.get(i10));
        }
        if ((this.f403l & 512) == 512) {
            jVar.m(10, this.f414w);
        }
        if ((this.f403l & 256) == 256) {
            jVar.m(11, this.f413v);
        }
        jVar.r(this.f402i);
    }

    public final void h() {
        this.f404m = d.BYTE;
        this.f405n = 0L;
        this.f406o = 0.0f;
        this.f407p = 0.0d;
        this.f408q = 0;
        this.f409r = 0;
        this.f410s = 0;
        this.f411t = j.f498q;
        this.f412u = Collections.EMPTY_LIST;
        this.f413v = 0;
        this.f414w = 0;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        byte b10 = this.x;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        if ((this.f403l & 128) == 128 && !this.f411t.isInitialized()) {
            this.x = (byte) 0;
            return false;
        }
        for (int i10 = 0; i10 < this.f412u.size(); i10++) {
            if (!((e) this.f412u.get(i10)).isInitialized()) {
                this.x = (byte) 0;
                return false;
            }
        }
        this.x = (byte) 1;
        return true;
    }

    public e(c cVar) {
        this.x = (byte) -1;
        this.f415y = -1;
        this.f402i = cVar.f19815i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [boolean] */
    public e(kotlin.reflect.jvm.internal.impl.protobuf.i iVar, kotlin.reflect.jvm.internal.impl.protobuf.l lVar) {
        i iVar2;
        this.x = (byte) -1;
        this.f415y = -1;
        h();
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        boolean z5 = false;
        char c10 = 0;
        while (true) {
            ?? Q = 256;
            if (!z5) {
                try {
                    try {
                        int iN = iVar.n();
                        switch (iN) {
                            case 0:
                                z5 = true;
                                break;
                            case 8:
                                int iK = iVar.k();
                                d dVarA = d.a(iK);
                                if (dVarA == null) {
                                    jVarJ.v(iN);
                                    jVarJ.v(iK);
                                } else {
                                    this.f403l |= 1;
                                    this.f404m = dVarA;
                                }
                                break;
                            case 16:
                                this.f403l |= 2;
                                long jL = iVar.l();
                                this.f405n = (-(jL & 1)) ^ (jL >>> 1);
                                break;
                            case 29:
                                this.f403l |= 4;
                                this.f406o = Float.intBitsToFloat(iVar.i());
                                break;
                            case 33:
                                this.f403l |= 8;
                                this.f407p = Double.longBitsToDouble(iVar.j());
                                break;
                            case 40:
                                this.f403l |= 16;
                                this.f408q = iVar.k();
                                break;
                            case 48:
                                this.f403l |= 32;
                                this.f409r = iVar.k();
                                break;
                            case 56:
                                this.f403l |= 64;
                                this.f410s = iVar.k();
                                break;
                            case 66:
                                if ((this.f403l & 128) == 128) {
                                    j jVar = this.f411t;
                                    jVar.getClass();
                                    iVar2 = new i();
                                    iVar2.f484n = Collections.EMPTY_LIST;
                                    iVar2.h(jVar);
                                } else {
                                    iVar2 = null;
                                }
                                j jVar2 = (j) iVar.g(j.f499r, lVar);
                                this.f411t = jVar2;
                                if (iVar2 != null) {
                                    iVar2.h(jVar2);
                                    this.f411t = iVar2.g();
                                }
                                this.f403l |= 128;
                                break;
                            case 74:
                                if ((c10 & 256) != 256) {
                                    this.f412u = new ArrayList();
                                    c10 = 256;
                                }
                                this.f412u.add(iVar.g(A, lVar));
                                break;
                            case AnimationConstants.STAGGER_SECTION /* 80 */:
                                this.f403l |= 512;
                                this.f414w = iVar.k();
                                break;
                            case 88:
                                this.f403l |= 256;
                                this.f413v = iVar.k();
                                break;
                            default:
                                Q = iVar.q(iN, jVarJ);
                                if (Q == 0) {
                                    z5 = true;
                                }
                                break;
                        }
                    } catch (Throwable th) {
                        if ((c10 & 256) == Q) {
                            this.f412u = Collections.unmodifiableList(this.f412u);
                        }
                        try {
                            jVarJ.i();
                        } catch (IOException unused) {
                        } catch (Throwable th2) {
                            this.f402i = fVar.k();
                            throw th2;
                        }
                        this.f402i = fVar.k();
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
            } else {
                if ((c10 & 256) == 256) {
                    this.f412u = Collections.unmodifiableList(this.f412u);
                }
                try {
                    jVarJ.i();
                } catch (IOException unused2) {
                } catch (Throwable th3) {
                    this.f402i = fVar.k();
                    throw th3;
                }
                this.f402i = fVar.k();
                return;
            }
        }
    }
}
