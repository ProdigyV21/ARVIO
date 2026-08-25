package a9;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class u1 extends kotlin.reflect.jvm.internal.impl.protobuf.u implements v1 {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final u1 f692q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final a f693r = new a(20);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f694i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f695l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public List f696m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f697n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public byte f698o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f699p;

    static {
        u1 u1Var = new u1();
        f692q = u1Var;
        u1Var.f696m = Collections.EMPTY_LIST;
        u1Var.f697n = -1;
    }

    public u1() {
        this.f698o = (byte) -1;
        this.f699p = -1;
        this.f694i = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    public static t1 h(u1 u1Var) {
        t1 t1VarH = t1.h();
        t1VarH.i(u1Var);
        return t1VarH;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.f699p;
        if (i10 != -1) {
            return i10;
        }
        int iB = 0;
        for (int i11 = 0; i11 < this.f696m.size(); i11++) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(1, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f696m.get(i11));
        }
        if ((this.f695l & 1) == 1) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(2, this.f697n);
        }
        int size = this.f694i.size() + iB;
        this.f699p = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        return h(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        return t1.h();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        for (int i10 = 0; i10 < this.f696m.size(); i10++) {
            jVar.o(1, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f696m.get(i10));
        }
        if ((this.f695l & 1) == 1) {
            jVar.m(2, this.f697n);
        }
        jVar.r(this.f694i);
    }

    public final t1 i() {
        return h(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        byte b10 = this.f698o;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        for (int i10 = 0; i10 < this.f696m.size(); i10++) {
            if (!((k1) this.f696m.get(i10)).isInitialized()) {
                this.f698o = (byte) 0;
                return false;
            }
        }
        this.f698o = (byte) 1;
        return true;
    }

    public u1(t1 t1Var) {
        this.f698o = (byte) -1;
        this.f699p = -1;
        this.f694i = t1Var.f19815i;
    }

    public u1(kotlin.reflect.jvm.internal.impl.protobuf.i iVar, kotlin.reflect.jvm.internal.impl.protobuf.l lVar) {
        this.f698o = (byte) -1;
        this.f699p = -1;
        this.f696m = Collections.EMPTY_LIST;
        this.f697n = -1;
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        boolean z = false;
        boolean z5 = false;
        while (!z) {
            try {
                try {
                    try {
                        int iN = iVar.n();
                        if (iN != 0) {
                            if (iN == 10) {
                                if (!z5) {
                                    this.f696m = new ArrayList();
                                    z5 = true;
                                }
                                this.f696m.add(iVar.g(k1.E, lVar));
                            } else if (iN != 16) {
                                if (!iVar.q(iN, jVarJ)) {
                                }
                            } else {
                                this.f695l |= 1;
                                this.f697n = iVar.k();
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e5) {
                        e5.f19759i = this;
                        throw e5;
                    }
                } catch (IOException e6) {
                    InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException(e6.getMessage());
                    invalidProtocolBufferException.f19759i = this;
                    throw invalidProtocolBufferException;
                }
            } catch (Throwable th) {
                if (z5) {
                    this.f696m = Collections.unmodifiableList(this.f696m);
                }
                try {
                    jVarJ.i();
                } catch (IOException unused) {
                } catch (Throwable th2) {
                    this.f694i = fVar.k();
                    throw th2;
                }
                this.f694i = fVar.k();
                throw th;
            }
        }
        if (z5) {
            this.f696m = Collections.unmodifiableList(this.f696m);
        }
        try {
            jVarJ.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f694i = fVar.k();
            throw th3;
        }
        this.f694i = fVar.k();
    }
}
