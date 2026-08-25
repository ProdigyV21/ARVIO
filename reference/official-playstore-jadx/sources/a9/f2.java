package a9;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class f2 extends kotlin.reflect.jvm.internal.impl.protobuf.u implements g2 {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final f2 f440o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final a f441p = new a(23);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f442i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public List f443l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public byte f444m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f445n;

    static {
        f2 f2Var = new f2();
        f440o = f2Var;
        f2Var.f443l = Collections.EMPTY_LIST;
    }

    public f2() {
        this.f444m = (byte) -1;
        this.f445n = -1;
        this.f442i = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.f445n;
        if (i10 != -1) {
            return i10;
        }
        int iD = 0;
        for (int i11 = 0; i11 < this.f443l.size(); i11++) {
            iD += kotlin.reflect.jvm.internal.impl.protobuf.j.d(1, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f443l.get(i11));
        }
        int size = this.f442i.size() + iD;
        this.f445n = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        e2 e2Var = new e2();
        e2Var.f422m = Collections.EMPTY_LIST;
        e2Var.h(this);
        return e2Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        e2 e2Var = new e2();
        e2Var.f422m = Collections.EMPTY_LIST;
        return e2Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        for (int i10 = 0; i10 < this.f443l.size(); i10++) {
            jVar.o(1, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f443l.get(i10));
        }
        jVar.r(this.f442i);
    }

    public final e2 h() {
        e2 e2Var = new e2();
        e2Var.f422m = Collections.EMPTY_LIST;
        e2Var.h(this);
        return e2Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        if (this.f444m == 1) {
            return true;
        }
        this.f444m = (byte) 1;
        return true;
    }

    public f2(e2 e2Var) {
        this.f444m = (byte) -1;
        this.f445n = -1;
        this.f442i = e2Var.f19815i;
    }

    public f2(kotlin.reflect.jvm.internal.impl.protobuf.i iVar, kotlin.reflect.jvm.internal.impl.protobuf.l lVar) {
        this.f444m = (byte) -1;
        this.f445n = -1;
        this.f443l = Collections.EMPTY_LIST;
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
                                this.f443l = new ArrayList();
                                z5 = true;
                            }
                            this.f443l.add(iVar.g(c2.f363v, lVar));
                        }
                    }
                    z = true;
                } catch (Throwable th) {
                    if (z5) {
                        this.f443l = Collections.unmodifiableList(this.f443l);
                    }
                    try {
                        jVarJ.i();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.f442i = fVar.k();
                        throw th2;
                    }
                    this.f442i = fVar.k();
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
            this.f443l = Collections.unmodifiableList(this.f443l);
        }
        try {
            jVarJ.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f442i = fVar.k();
            throw th3;
        }
        this.f442i = fVar.k();
    }
}
