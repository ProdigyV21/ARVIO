package d9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;
import kotlin.reflect.jvm.internal.impl.protobuf.u;

/* JADX INFO: loaded from: classes5.dex */
public final class k extends kotlin.reflect.jvm.internal.impl.protobuf.o implements n {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f14830l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f14831m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f14832n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Object f14833o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public l f14834p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public List f14835q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public List f14836r;

    public static k h() {
        k kVar = new k();
        kVar.f14831m = 1;
        kVar.f14833o = "";
        kVar.f14834p = l.NONE;
        List list = Collections.EMPTY_LIST;
        kVar.f14835q = list;
        kVar.f14836r = list;
        return kVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final c0 build() {
        m mVarG = g();
        mVarG.isInitialized();
        return mVarG;
    }

    public final Object clone() {
        k kVarH = h();
        kVarH.i(g());
        return kVarH;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(u uVar) {
        i((m) uVar);
        return this;
    }

    public final m g() {
        m mVar = new m(this);
        int i10 = this.f14830l;
        int i11 = (i10 & 1) != 1 ? 0 : 1;
        mVar.f14845m = this.f14831m;
        if ((i10 & 2) == 2) {
            i11 |= 2;
        }
        mVar.f14846n = this.f14832n;
        if ((i10 & 4) == 4) {
            i11 |= 4;
        }
        mVar.f14847o = this.f14833o;
        if ((i10 & 8) == 8) {
            i11 |= 8;
        }
        mVar.f14848p = this.f14834p;
        if ((i10 & 16) == 16) {
            this.f14835q = Collections.unmodifiableList(this.f14835q);
            this.f14830l &= -17;
        }
        mVar.f14849q = this.f14835q;
        if ((this.f14830l & 32) == 32) {
            this.f14836r = Collections.unmodifiableList(this.f14836r);
            this.f14830l &= -33;
        }
        mVar.f14851s = this.f14836r;
        mVar.f14844l = i11;
        return mVar;
    }

    public final void i(m mVar) {
        if (mVar == m.f14842w) {
            return;
        }
        int i10 = mVar.f14844l;
        if ((i10 & 1) == 1) {
            int i11 = mVar.f14845m;
            this.f14830l = 1 | this.f14830l;
            this.f14831m = i11;
        }
        if ((i10 & 2) == 2) {
            int i12 = mVar.f14846n;
            this.f14830l = 2 | this.f14830l;
            this.f14832n = i12;
        }
        if ((i10 & 4) == 4) {
            this.f14830l |= 4;
            this.f14833o = mVar.f14847o;
        }
        if ((i10 & 8) == 8) {
            l lVar = mVar.f14848p;
            lVar.getClass();
            this.f14830l = 8 | this.f14830l;
            this.f14834p = lVar;
        }
        if (!mVar.f14849q.isEmpty()) {
            if (this.f14835q.isEmpty()) {
                this.f14835q = mVar.f14849q;
                this.f14830l &= -17;
            } else {
                if ((this.f14830l & 16) != 16) {
                    this.f14835q = new ArrayList(this.f14835q);
                    this.f14830l |= 16;
                }
                this.f14835q.addAll(mVar.f14849q);
            }
        }
        if (!mVar.f14851s.isEmpty()) {
            if (this.f14836r.isEmpty()) {
                this.f14836r = mVar.f14851s;
                this.f14830l &= -33;
            } else {
                if ((this.f14830l & 32) != 32) {
                    this.f14836r = new ArrayList(this.f14836r);
                    this.f14830l |= 32;
                }
                this.f14836r.addAll(mVar.f14851s);
            }
        }
        this.f19815i = this.f19815i.b(mVar.f14843i);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001b  */
    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0.a y(kotlin.reflect.jvm.internal.impl.protobuf.i r2, kotlin.reflect.jvm.internal.impl.protobuf.l r3) throws java.lang.Throwable {
        /*
            r1 = this;
            r3 = 0
            a9.a r0 = d9.m.x     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r0.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            d9.m r0 = new d9.m     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r0.<init>(r2)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.i(r0)
            return r1
        Lf:
            r2 = move-exception
            goto L19
        L11:
            r2 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r0 = r2.f19759i     // Catch: java.lang.Throwable -> Lf
            d9.m r0 = (d9.m) r0     // Catch: java.lang.Throwable -> Lf
            throw r2     // Catch: java.lang.Throwable -> L17
        L17:
            r2 = move-exception
            r3 = r0
        L19:
            if (r3 == 0) goto L1e
            r1.i(r3)
        L1e:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: d9.k.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}
