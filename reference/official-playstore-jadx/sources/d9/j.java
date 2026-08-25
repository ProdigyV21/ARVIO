package d9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;
import kotlin.reflect.jvm.internal.impl.protobuf.u;

/* JADX INFO: loaded from: classes5.dex */
public final class j extends kotlin.reflect.jvm.internal.impl.protobuf.o implements p {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f14827l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public List f14828m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public List f14829n;

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final c0 build() {
        o oVarG = g();
        oVarG.isInitialized();
        return oVarG;
    }

    public final Object clone() {
        j jVar = new j();
        List list = Collections.EMPTY_LIST;
        jVar.f14828m = list;
        jVar.f14829n = list;
        jVar.h(g());
        return jVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(u uVar) {
        h((o) uVar);
        return this;
    }

    public final o g() {
        o oVar = new o(this);
        if ((this.f14827l & 1) == 1) {
            this.f14828m = Collections.unmodifiableList(this.f14828m);
            this.f14827l &= -2;
        }
        oVar.f14858l = this.f14828m;
        if ((this.f14827l & 2) == 2) {
            this.f14829n = Collections.unmodifiableList(this.f14829n);
            this.f14827l &= -3;
        }
        oVar.f14859m = this.f14829n;
        return oVar;
    }

    public final void h(o oVar) {
        if (oVar == o.f14855q) {
            return;
        }
        if (!oVar.f14858l.isEmpty()) {
            if (this.f14828m.isEmpty()) {
                this.f14828m = oVar.f14858l;
                this.f14827l &= -2;
            } else {
                if ((this.f14827l & 1) != 1) {
                    this.f14828m = new ArrayList(this.f14828m);
                    this.f14827l |= 1;
                }
                this.f14828m.addAll(oVar.f14858l);
            }
        }
        if (!oVar.f14859m.isEmpty()) {
            if (this.f14829n.isEmpty()) {
                this.f14829n = oVar.f14859m;
                this.f14827l &= -3;
            } else {
                if ((this.f14827l & 2) != 2) {
                    this.f14829n = new ArrayList(this.f14829n);
                    this.f14827l |= 2;
                }
                this.f14829n.addAll(oVar.f14859m);
            }
        }
        this.f19815i = this.f19815i.b(oVar.f14857i);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001b  */
    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0.a y(kotlin.reflect.jvm.internal.impl.protobuf.i r3, kotlin.reflect.jvm.internal.impl.protobuf.l r4) throws java.lang.Throwable {
        /*
            r2 = this;
            r0 = 0
            a9.a r1 = d9.o.f14856r     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            d9.o r1 = new d9.o     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r2.h(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r4 = r3.f19759i     // Catch: java.lang.Throwable -> Lf
            d9.o r4 = (d9.o) r4     // Catch: java.lang.Throwable -> Lf
            throw r3     // Catch: java.lang.Throwable -> L17
        L17:
            r3 = move-exception
            r0 = r4
        L19:
            if (r0 == 0) goto L1e
            r2.h(r0)
        L1e:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: d9.j.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}
