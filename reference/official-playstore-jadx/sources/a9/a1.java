package a9;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class a1 extends kotlin.reflect.jvm.internal.impl.protobuf.u implements b1 {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final a1 f322o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final a f323p = new a(13);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f324i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public List f325l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public byte f326m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f327n;

    static {
        a1 a1Var = new a1();
        f322o = a1Var;
        a1Var.f325l = Collections.EMPTY_LIST;
    }

    public a1() {
        this.f326m = (byte) -1;
        this.f327n = -1;
        this.f324i = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.f327n;
        if (i10 != -1) {
            return i10;
        }
        int iD = 0;
        for (int i11 = 0; i11 < this.f325l.size(); i11++) {
            iD += kotlin.reflect.jvm.internal.impl.protobuf.j.d(1, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f325l.get(i11));
        }
        int size = this.f324i.size() + iD;
        this.f327n = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        v0 v0Var = new v0();
        v0Var.f706m = Collections.EMPTY_LIST;
        v0Var.h(this);
        return v0Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        v0 v0Var = new v0();
        v0Var.f706m = Collections.EMPTY_LIST;
        return v0Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        for (int i10 = 0; i10 < this.f325l.size(); i10++) {
            jVar.o(1, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f325l.get(i10));
        }
        jVar.r(this.f324i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        byte b10 = this.f326m;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        for (int i10 = 0; i10 < this.f325l.size(); i10++) {
            if (!((y0) this.f325l.get(i10)).isInitialized()) {
                this.f326m = (byte) 0;
                return false;
            }
        }
        this.f326m = (byte) 1;
        return true;
    }

    public a1(v0 v0Var) {
        this.f326m = (byte) -1;
        this.f327n = -1;
        this.f324i = v0Var.f19815i;
    }

    public a1(kotlin.reflect.jvm.internal.impl.protobuf.i iVar, kotlin.reflect.jvm.internal.impl.protobuf.l lVar) {
        this.f326m = (byte) -1;
        this.f327n = -1;
        this.f325l = Collections.EMPTY_LIST;
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        boolean z = false;
        boolean z5 = false;
        while (!z) {
            try {
                try {
                    int iN = iVar.n();
                    if (iN != 0) {
                        if (iN != 10) {
                            if (!iVar.q(iN, jVarJ)) {
                            }
                        } else {
                            if (!z5) {
                                this.f325l = new ArrayList();
                                z5 = true;
                            }
                            this.f325l.add(iVar.g(y0.f756s, lVar));
                        }
                    }
                    z = true;
                } catch (Throwable th) {
                    if (z5) {
                        this.f325l = Collections.unmodifiableList(this.f325l);
                    }
                    try {
                        jVarJ.i();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.f324i = fVar.k();
                        throw th2;
                    }
                    this.f324i = fVar.k();
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
            this.f325l = Collections.unmodifiableList(this.f325l);
        }
        try {
            jVarJ.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f324i = fVar.k();
            throw th3;
        }
        this.f324i = fVar.k();
    }
}
