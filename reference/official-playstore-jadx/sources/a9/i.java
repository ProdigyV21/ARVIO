package a9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends kotlin.reflect.jvm.internal.impl.protobuf.o implements k {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f482l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f483m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public List f484n;

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        j jVarG = g();
        if (jVarG.isInitialized()) {
            return jVarG;
        }
        throw new UninitializedMessageException();
    }

    public final Object clone() {
        i iVar = new i();
        iVar.f484n = Collections.EMPTY_LIST;
        iVar.h(g());
        return iVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        h((j) uVar);
        return this;
    }

    public final j g() {
        j jVar = new j(this);
        int i10 = this.f482l;
        int i11 = (i10 & 1) != 1 ? 0 : 1;
        jVar.f502m = this.f483m;
        if ((i10 & 2) == 2) {
            this.f484n = Collections.unmodifiableList(this.f484n);
            this.f482l &= -3;
        }
        jVar.f503n = this.f484n;
        jVar.f501l = i11;
        return jVar;
    }

    public final void h(j jVar) {
        if (jVar == j.f498q) {
            return;
        }
        if ((jVar.f501l & 1) == 1) {
            int i10 = jVar.f502m;
            this.f482l = 1 | this.f482l;
            this.f483m = i10;
        }
        if (!jVar.f503n.isEmpty()) {
            if (this.f484n.isEmpty()) {
                this.f484n = jVar.f503n;
                this.f482l &= -3;
            } else {
                if ((this.f482l & 2) != 2) {
                    this.f484n = new ArrayList(this.f484n);
                    this.f482l |= 2;
                }
                this.f484n.addAll(jVar.f503n);
            }
        }
        this.f19815i = this.f19815i.b(jVar.f500i);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0019  */
    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0.a y(kotlin.reflect.jvm.internal.impl.protobuf.i r3, kotlin.reflect.jvm.internal.impl.protobuf.l r4) throws java.lang.Throwable {
        /*
            r2 = this;
            r0 = 0
            a9.a r1 = a9.j.f499r     // Catch: java.lang.Throwable -> Ld kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> Lf
            java.lang.Object r3 = r1.b(r3, r4)     // Catch: java.lang.Throwable -> Ld kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> Lf
            a9.j r3 = (a9.j) r3     // Catch: java.lang.Throwable -> Ld kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> Lf
            r2.h(r3)
            return r2
        Ld:
            r3 = move-exception
            goto L17
        Lf:
            r3 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r4 = r3.f19759i     // Catch: java.lang.Throwable -> Ld
            a9.j r4 = (a9.j) r4     // Catch: java.lang.Throwable -> Ld
            throw r3     // Catch: java.lang.Throwable -> L15
        L15:
            r3 = move-exception
            r0 = r4
        L17:
            if (r0 == 0) goto L1c
            r2.h(r0)
        L1c:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: a9.i.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}
