package a9;

import com.arflix.tv.ui.theme.AnimationConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class m1 extends kotlin.reflect.jvm.internal.impl.protobuf.q implements n1 {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final m1 f568y;
    public static final a z = new a(18);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f569l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f570m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f571n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f572o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public List f573p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public k1 f574q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f575r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public k1 f576s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f577t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public List f578u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public List f579v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public byte f580w;
    public int x;

    static {
        m1 m1Var = new m1();
        f568y = m1Var;
        m1Var.f571n = 6;
        m1Var.f572o = 0;
        List list = Collections.EMPTY_LIST;
        m1Var.f573p = list;
        k1 k1Var = k1.D;
        m1Var.f574q = k1Var;
        m1Var.f575r = 0;
        m1Var.f576s = k1Var;
        m1Var.f577t = 0;
        m1Var.f578u = list;
        m1Var.f579v = list;
    }

    public m1(l1 l1Var) {
        super(l1Var);
        this.f580w = (byte) -1;
        this.x = -1;
        this.f569l = l1Var.f19815i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.x;
        if (i10 != -1) {
            return i10;
        }
        int iB = (this.f570m & 1) == 1 ? kotlin.reflect.jvm.internal.impl.protobuf.j.b(1, this.f571n) : 0;
        if ((this.f570m & 2) == 2) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(2, this.f572o);
        }
        for (int i11 = 0; i11 < this.f573p.size(); i11++) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(3, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f573p.get(i11));
        }
        if ((this.f570m & 4) == 4) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(4, this.f574q);
        }
        if ((this.f570m & 8) == 8) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(5, this.f575r);
        }
        if ((this.f570m & 16) == 16) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(6, this.f576s);
        }
        if ((this.f570m & 32) == 32) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(7, this.f577t);
        }
        for (int i12 = 0; i12 < this.f578u.size(); i12++) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(8, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f578u.get(i12));
        }
        int iC = 0;
        for (int i13 = 0; i13 < this.f579v.size(); i13++) {
            iC += kotlin.reflect.jvm.internal.impl.protobuf.j.c(((Integer) this.f579v.get(i13)).intValue());
        }
        int size = this.f569l.size() + i() + (this.f579v.size() * 2) + iB + iC;
        this.x = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        l1 l1VarI = l1.i();
        l1VarI.j(this);
        return l1VarI;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        return l1.i();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 d() {
        return f568y;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        fi.iki.elonen.f fVar = new fi.iki.elonen.f(this);
        if ((this.f570m & 1) == 1) {
            jVar.m(1, this.f571n);
        }
        if ((this.f570m & 2) == 2) {
            jVar.m(2, this.f572o);
        }
        for (int i10 = 0; i10 < this.f573p.size(); i10++) {
            jVar.o(3, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f573p.get(i10));
        }
        if ((this.f570m & 4) == 4) {
            jVar.o(4, this.f574q);
        }
        if ((this.f570m & 8) == 8) {
            jVar.m(5, this.f575r);
        }
        if ((this.f570m & 16) == 16) {
            jVar.o(6, this.f576s);
        }
        if ((this.f570m & 32) == 32) {
            jVar.m(7, this.f577t);
        }
        for (int i11 = 0; i11 < this.f578u.size(); i11++) {
            jVar.o(8, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f578u.get(i11));
        }
        for (int i12 = 0; i12 < this.f579v.size(); i12++) {
            jVar.m(31, ((Integer) this.f579v.get(i12)).intValue());
        }
        fVar.f0(200, jVar);
        jVar.r(this.f569l);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        byte b10 = this.f580w;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        if ((this.f570m & 2) != 2) {
            this.f580w = (byte) 0;
            return false;
        }
        for (int i10 = 0; i10 < this.f573p.size(); i10++) {
            if (!((r1) this.f573p.get(i10)).isInitialized()) {
                this.f580w = (byte) 0;
                return false;
            }
        }
        if ((this.f570m & 4) == 4 && !this.f574q.isInitialized()) {
            this.f580w = (byte) 0;
            return false;
        }
        if ((this.f570m & 16) == 16 && !this.f576s.isInitialized()) {
            this.f580w = (byte) 0;
            return false;
        }
        for (int i11 = 0; i11 < this.f578u.size(); i11++) {
            if (!((j) this.f578u.get(i11)).isInitialized()) {
                this.f580w = (byte) 0;
                return false;
            }
        }
        if (h()) {
            this.f580w = (byte) 1;
            return true;
        }
        this.f580w = (byte) 0;
        return false;
    }

    public m1() {
        this.f580w = (byte) -1;
        this.x = -1;
        this.f569l = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [boolean] */
    public m1(kotlin.reflect.jvm.internal.impl.protobuf.i iVar, kotlin.reflect.jvm.internal.impl.protobuf.l lVar) {
        this.f580w = (byte) -1;
        this.x = -1;
        this.f571n = 6;
        boolean z5 = false;
        this.f572o = 0;
        List list = Collections.EMPTY_LIST;
        this.f573p = list;
        k1 k1Var = k1.D;
        this.f574q = k1Var;
        this.f575r = 0;
        this.f576s = k1Var;
        this.f577t = 0;
        this.f578u = list;
        this.f579v = list;
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        int i10 = 0;
        while (true) {
            ?? M = 128;
            if (!z5) {
                try {
                    try {
                        int iN = iVar.n();
                        j1 j1VarP = null;
                        switch (iN) {
                            case 0:
                                z5 = true;
                                break;
                            case 8:
                                this.f570m |= 1;
                                this.f571n = iVar.k();
                                break;
                            case 16:
                                this.f570m |= 2;
                                this.f572o = iVar.k();
                                break;
                            case 26:
                                if ((i10 & 4) != 4) {
                                    this.f573p = new ArrayList();
                                    i10 |= 4;
                                }
                                this.f573p.add(iVar.g(r1.x, lVar));
                                break;
                            case 34:
                                if ((this.f570m & 4) == 4) {
                                    k1 k1Var2 = this.f574q;
                                    k1Var2.getClass();
                                    j1VarP = k1.p(k1Var2);
                                }
                                k1 k1Var3 = (k1) iVar.g(k1.E, lVar);
                                this.f574q = k1Var3;
                                if (j1VarP != null) {
                                    j1VarP.j(k1Var3);
                                    this.f574q = j1VarP.h();
                                }
                                this.f570m |= 4;
                                break;
                            case 40:
                                this.f570m |= 8;
                                this.f575r = iVar.k();
                                break;
                            case 50:
                                if ((this.f570m & 16) == 16) {
                                    k1 k1Var4 = this.f576s;
                                    k1Var4.getClass();
                                    j1VarP = k1.p(k1Var4);
                                }
                                k1 k1Var5 = (k1) iVar.g(k1.E, lVar);
                                this.f576s = k1Var5;
                                if (j1VarP != null) {
                                    j1VarP.j(k1Var5);
                                    this.f576s = j1VarP.h();
                                }
                                this.f570m |= 16;
                                break;
                            case 56:
                                this.f570m |= 32;
                                this.f577t = iVar.k();
                                break;
                            case 66:
                                if ((i10 & 128) != 128) {
                                    this.f578u = new ArrayList();
                                    i10 |= 128;
                                }
                                this.f578u.add(iVar.g(j.f499r, lVar));
                                break;
                            case 248:
                                if ((i10 & 256) != 256) {
                                    this.f579v = new ArrayList();
                                    i10 |= 256;
                                }
                                this.f579v.add(Integer.valueOf(iVar.k()));
                                break;
                            case AnimationConstants.DURATION_IMAGE_CROSSFADE /* 250 */:
                                int iD = iVar.d(iVar.k());
                                if ((i10 & 256) != 256 && iVar.b() > 0) {
                                    this.f579v = new ArrayList();
                                    i10 |= 256;
                                }
                                while (iVar.b() > 0) {
                                    this.f579v.add(Integer.valueOf(iVar.k()));
                                }
                                iVar.c(iD);
                                break;
                            default:
                                M = m(iVar, jVarJ, lVar, iN);
                                if (M == 0) {
                                    z5 = true;
                                }
                                break;
                        }
                    } catch (Throwable th) {
                        if ((i10 & 4) == 4) {
                            this.f573p = Collections.unmodifiableList(this.f573p);
                        }
                        if ((i10 & 128) == M) {
                            this.f578u = Collections.unmodifiableList(this.f578u);
                        }
                        if ((i10 & 256) == 256) {
                            this.f579v = Collections.unmodifiableList(this.f579v);
                        }
                        try {
                            jVarJ.i();
                        } catch (IOException unused) {
                        } catch (Throwable th2) {
                            this.f569l = fVar.k();
                            throw th2;
                        }
                        this.f569l = fVar.k();
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
            } else {
                if ((i10 & 4) == 4) {
                    this.f573p = Collections.unmodifiableList(this.f573p);
                }
                if ((i10 & 128) == 128) {
                    this.f578u = Collections.unmodifiableList(this.f578u);
                }
                if ((i10 & 256) == 256) {
                    this.f579v = Collections.unmodifiableList(this.f579v);
                }
                try {
                    jVarJ.i();
                } catch (IOException unused2) {
                } catch (Throwable th3) {
                    this.f569l = fVar.k();
                    throw th3;
                }
                this.f569l = fVar.k();
                l();
                return;
            }
        }
    }
}
