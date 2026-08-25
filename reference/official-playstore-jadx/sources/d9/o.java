package d9;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;
import kotlin.reflect.jvm.internal.impl.protobuf.u;

/* JADX INFO: loaded from: classes5.dex */
public final class o extends u implements p {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final o f14855q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final a9.a f14856r = new a9.a(27);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f14857i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public List f14858l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public List f14859m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f14860n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public byte f14861o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f14862p;

    static {
        o oVar = new o();
        f14855q = oVar;
        List list = Collections.EMPTY_LIST;
        oVar.f14858l = list;
        oVar.f14859m = list;
    }

    public o() {
        this.f14860n = -1;
        this.f14861o = (byte) -1;
        this.f14862p = -1;
        this.f14857i = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.f14862p;
        if (i10 != -1) {
            return i10;
        }
        int iD = 0;
        for (int i11 = 0; i11 < this.f14858l.size(); i11++) {
            iD += kotlin.reflect.jvm.internal.impl.protobuf.j.d(1, (c0) this.f14858l.get(i11));
        }
        int iC = 0;
        for (int i12 = 0; i12 < this.f14859m.size(); i12++) {
            iC += kotlin.reflect.jvm.internal.impl.protobuf.j.c(((Integer) this.f14859m.get(i12)).intValue());
        }
        int iC2 = iD + iC;
        if (!this.f14859m.isEmpty()) {
            iC2 = iC2 + 1 + kotlin.reflect.jvm.internal.impl.protobuf.j.c(iC);
        }
        this.f14860n = iC;
        int size = this.f14857i.size() + iC2;
        this.f14862p = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        j jVar = new j();
        List list = Collections.EMPTY_LIST;
        jVar.f14828m = list;
        jVar.f14829n = list;
        jVar.h(this);
        return jVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        j jVar = new j();
        List list = Collections.EMPTY_LIST;
        jVar.f14828m = list;
        jVar.f14829n = list;
        return jVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        for (int i10 = 0; i10 < this.f14858l.size(); i10++) {
            jVar.o(1, (c0) this.f14858l.get(i10));
        }
        if (this.f14859m.size() > 0) {
            jVar.v(42);
            jVar.v(this.f14860n);
        }
        for (int i11 = 0; i11 < this.f14859m.size(); i11++) {
            jVar.n(((Integer) this.f14859m.get(i11)).intValue());
        }
        jVar.r(this.f14857i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        if (this.f14861o == 1) {
            return true;
        }
        this.f14861o = (byte) 1;
        return true;
    }

    public o(j jVar) {
        this.f14860n = -1;
        this.f14861o = (byte) -1;
        this.f14862p = -1;
        this.f14857i = jVar.f19815i;
    }

    public o(kotlin.reflect.jvm.internal.impl.protobuf.i iVar, kotlin.reflect.jvm.internal.impl.protobuf.l lVar) {
        this.f14860n = -1;
        this.f14861o = (byte) -1;
        this.f14862p = -1;
        List list = Collections.EMPTY_LIST;
        this.f14858l = list;
        this.f14859m = list;
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        boolean z = false;
        int i10 = 0;
        while (!z) {
            try {
                try {
                    try {
                        int iN = iVar.n();
                        if (iN != 0) {
                            if (iN == 10) {
                                if ((i10 & 1) != 1) {
                                    this.f14858l = new ArrayList();
                                    i10 |= 1;
                                }
                                this.f14858l.add(iVar.g(m.x, lVar));
                            } else if (iN == 40) {
                                if ((i10 & 2) != 2) {
                                    this.f14859m = new ArrayList();
                                    i10 |= 2;
                                }
                                this.f14859m.add(Integer.valueOf(iVar.k()));
                            } else if (iN != 42) {
                                if (!iVar.q(iN, jVarJ)) {
                                }
                            } else {
                                int iD = iVar.d(iVar.k());
                                if ((i10 & 2) != 2 && iVar.b() > 0) {
                                    this.f14859m = new ArrayList();
                                    i10 |= 2;
                                }
                                while (iVar.b() > 0) {
                                    this.f14859m.add(Integer.valueOf(iVar.k()));
                                }
                                iVar.c(iD);
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
                if ((i10 & 1) == 1) {
                    this.f14858l = Collections.unmodifiableList(this.f14858l);
                }
                if ((i10 & 2) == 2) {
                    this.f14859m = Collections.unmodifiableList(this.f14859m);
                }
                try {
                    jVarJ.i();
                } catch (IOException unused) {
                } catch (Throwable th2) {
                    this.f14857i = fVar.k();
                    throw th2;
                }
                this.f14857i = fVar.k();
                throw th;
            }
        }
        if ((i10 & 1) == 1) {
            this.f14858l = Collections.unmodifiableList(this.f14858l);
        }
        if ((i10 & 2) == 2) {
            this.f14859m = Collections.unmodifiableList(this.f14859m);
        }
        try {
            jVarJ.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f14857i = fVar.k();
            throw th3;
        }
        this.f14857i = fVar.k();
    }
}
