package a9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: classes5.dex */
public final class s extends kotlin.reflect.jvm.internal.impl.protobuf.o implements u {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f657l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public List f658m;

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        t tVarG = g();
        if (tVarG.isInitialized()) {
            return tVarG;
        }
        throw new UninitializedMessageException();
    }

    public final Object clone() {
        s sVar = new s();
        sVar.f658m = Collections.EMPTY_LIST;
        sVar.h(g());
        return sVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        h((t) uVar);
        return this;
    }

    public final t g() {
        t tVar = new t(this);
        if ((this.f657l & 1) == 1) {
            this.f658m = Collections.unmodifiableList(this.f658m);
            this.f657l &= -2;
        }
        tVar.f673l = this.f658m;
        return tVar;
    }

    public final void h(t tVar) {
        if (tVar == t.f670o) {
            return;
        }
        if (!tVar.f673l.isEmpty()) {
            if (this.f658m.isEmpty()) {
                this.f658m = tVar.f673l;
                this.f657l &= -2;
            } else {
                if ((this.f657l & 1) != 1) {
                    this.f658m = new ArrayList(this.f658m);
                    this.f657l |= 1;
                }
                this.f658m.addAll(tVar.f673l);
            }
        }
        this.f19815i = this.f19815i.b(tVar.f672i);
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
            a9.a r1 = a9.t.f671p     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            a9.t r1 = new a9.t     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r2.h(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r4 = r3.f19759i     // Catch: java.lang.Throwable -> Lf
            a9.t r4 = (a9.t) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: a9.s.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}
