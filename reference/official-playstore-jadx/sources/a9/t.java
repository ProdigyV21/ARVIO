package a9;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class t extends kotlin.reflect.jvm.internal.impl.protobuf.u implements u {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final t f670o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final a f671p = new a(5);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f672i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public List f673l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public byte f674m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f675n;

    static {
        t tVar = new t();
        f670o = tVar;
        tVar.f673l = Collections.EMPTY_LIST;
    }

    public t() {
        this.f674m = (byte) -1;
        this.f675n = -1;
        this.f672i = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.f675n;
        if (i10 != -1) {
            return i10;
        }
        int iD = 0;
        for (int i11 = 0; i11 < this.f673l.size(); i11++) {
            iD += kotlin.reflect.jvm.internal.impl.protobuf.j.d(1, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f673l.get(i11));
        }
        int size = this.f672i.size() + iD;
        this.f675n = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        s sVar = new s();
        sVar.f658m = Collections.EMPTY_LIST;
        sVar.h(this);
        return sVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        s sVar = new s();
        sVar.f658m = Collections.EMPTY_LIST;
        return sVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        for (int i10 = 0; i10 < this.f673l.size(); i10++) {
            jVar.o(1, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f673l.get(i10));
        }
        jVar.r(this.f672i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        byte b10 = this.f674m;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        for (int i10 = 0; i10 < this.f673l.size(); i10++) {
            if (!((y) this.f673l.get(i10)).isInitialized()) {
                this.f674m = (byte) 0;
                return false;
            }
        }
        this.f674m = (byte) 1;
        return true;
    }

    public t(s sVar) {
        this.f674m = (byte) -1;
        this.f675n = -1;
        this.f672i = sVar.f19815i;
    }

    public t(kotlin.reflect.jvm.internal.impl.protobuf.i iVar, kotlin.reflect.jvm.internal.impl.protobuf.l lVar) {
        this.f674m = (byte) -1;
        this.f675n = -1;
        this.f673l = Collections.EMPTY_LIST;
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
                                this.f673l = new ArrayList();
                                z5 = true;
                            }
                            this.f673l.add(iVar.g(y.f746t, lVar));
                        }
                    }
                    z = true;
                } catch (Throwable th) {
                    if (z5) {
                        this.f673l = Collections.unmodifiableList(this.f673l);
                    }
                    try {
                        jVarJ.i();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.f672i = fVar.k();
                        throw th2;
                    }
                    this.f672i = fVar.k();
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
            this.f673l = Collections.unmodifiableList(this.f673l);
        }
        try {
            jVarJ.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f672i = fVar.k();
            throw th3;
        }
        this.f672i = fVar.k();
    }
}
