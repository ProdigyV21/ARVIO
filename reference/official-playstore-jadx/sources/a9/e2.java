package a9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class e2 extends kotlin.reflect.jvm.internal.impl.protobuf.o implements g2 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f421l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public List f422m;

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        f2 f2VarG = g();
        f2VarG.isInitialized();
        return f2VarG;
    }

    public final Object clone() {
        e2 e2Var = new e2();
        e2Var.f422m = Collections.EMPTY_LIST;
        e2Var.h(g());
        return e2Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        h((f2) uVar);
        return this;
    }

    public final f2 g() {
        f2 f2Var = new f2(this);
        if ((this.f421l & 1) == 1) {
            this.f422m = Collections.unmodifiableList(this.f422m);
            this.f421l &= -2;
        }
        f2Var.f443l = this.f422m;
        return f2Var;
    }

    public final void h(f2 f2Var) {
        if (f2Var == f2.f440o) {
            return;
        }
        if (!f2Var.f443l.isEmpty()) {
            if (this.f422m.isEmpty()) {
                this.f422m = f2Var.f443l;
                this.f421l &= -2;
            } else {
                if ((this.f421l & 1) != 1) {
                    this.f422m = new ArrayList(this.f422m);
                    this.f421l |= 1;
                }
                this.f422m.addAll(f2Var.f443l);
            }
        }
        this.f19815i = this.f19815i.b(f2Var.f442i);
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
            a9.a r1 = a9.f2.f441p     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            a9.f2 r1 = new a9.f2     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r2.h(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r4 = r3.f19759i     // Catch: java.lang.Throwable -> Lf
            a9.f2 r4 = (a9.f2) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: a9.e2.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}
