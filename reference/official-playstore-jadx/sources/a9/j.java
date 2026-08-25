package a9;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class j extends kotlin.reflect.jvm.internal.impl.protobuf.u implements k {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final j f498q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final a f499r = new a(0);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f500i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f501l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f502m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public List f503n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public byte f504o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f505p;

    static {
        j jVar = new j();
        f498q = jVar;
        jVar.f502m = 0;
        jVar.f503n = Collections.EMPTY_LIST;
    }

    public j() {
        this.f504o = (byte) -1;
        this.f505p = -1;
        this.f500i = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.f505p;
        if (i10 != -1) {
            return i10;
        }
        int iB = (this.f501l & 1) == 1 ? kotlin.reflect.jvm.internal.impl.protobuf.j.b(1, this.f502m) : 0;
        for (int i11 = 0; i11 < this.f503n.size(); i11++) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(2, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f503n.get(i11));
        }
        int size = this.f500i.size() + iB;
        this.f505p = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        i iVar = new i();
        iVar.f484n = Collections.EMPTY_LIST;
        iVar.h(this);
        return iVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        i iVar = new i();
        iVar.f484n = Collections.EMPTY_LIST;
        return iVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        if ((this.f501l & 1) == 1) {
            jVar.m(1, this.f502m);
        }
        for (int i10 = 0; i10 < this.f503n.size(); i10++) {
            jVar.o(2, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f503n.get(i10));
        }
        jVar.r(this.f500i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        byte b10 = this.f504o;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        if ((this.f501l & 1) != 1) {
            this.f504o = (byte) 0;
            return false;
        }
        for (int i10 = 0; i10 < this.f503n.size(); i10++) {
            if (!((g) this.f503n.get(i10)).isInitialized()) {
                this.f504o = (byte) 0;
                return false;
            }
        }
        this.f504o = (byte) 1;
        return true;
    }

    public j(i iVar) {
        this.f504o = (byte) -1;
        this.f505p = -1;
        this.f500i = iVar.f19815i;
    }

    public j(kotlin.reflect.jvm.internal.impl.protobuf.i iVar, kotlin.reflect.jvm.internal.impl.protobuf.l lVar) {
        this.f504o = (byte) -1;
        this.f505p = -1;
        boolean z = false;
        this.f502m = 0;
        this.f503n = Collections.EMPTY_LIST;
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        char c10 = 0;
        while (!z) {
            try {
                try {
                    int iN = iVar.n();
                    if (iN != 0) {
                        if (iN == 8) {
                            this.f501l |= 1;
                            this.f502m = iVar.k();
                        } else if (iN != 18) {
                            if (!iVar.q(iN, jVarJ)) {
                            }
                        } else {
                            if ((c10 & 2) != 2) {
                                this.f503n = new ArrayList();
                                c10 = 2;
                            }
                            this.f503n.add(iVar.g(g.f447r, lVar));
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e5) {
                    e5.f19759i = this;
                    throw e5;
                } catch (IOException e6) {
                    InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException(e6.getMessage());
                    invalidProtocolBufferException.f19759i = this;
                    throw invalidProtocolBufferException;
                }
            } catch (Throwable th) {
                if ((c10 & 2) == 2) {
                    this.f503n = Collections.unmodifiableList(this.f503n);
                }
                try {
                    jVarJ.i();
                } catch (IOException unused) {
                } catch (Throwable th2) {
                    this.f500i = fVar.k();
                    throw th2;
                }
                this.f500i = fVar.k();
                throw th;
            }
        }
        if ((c10 & 2) == 2) {
            this.f503n = Collections.unmodifiableList(this.f503n);
        }
        try {
            jVarJ.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f500i = fVar.k();
            throw th3;
        }
        this.f500i = fVar.k();
    }
}
