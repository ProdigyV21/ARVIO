package a9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: classes5.dex */
public final class v0 extends kotlin.reflect.jvm.internal.impl.protobuf.o implements b1 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f705l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public List f706m;

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        a1 a1VarG = g();
        if (a1VarG.isInitialized()) {
            return a1VarG;
        }
        throw new UninitializedMessageException();
    }

    public final Object clone() {
        v0 v0Var = new v0();
        v0Var.f706m = Collections.EMPTY_LIST;
        v0Var.h(g());
        return v0Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        h((a1) uVar);
        return this;
    }

    public final a1 g() {
        a1 a1Var = new a1(this);
        if ((this.f705l & 1) == 1) {
            this.f706m = Collections.unmodifiableList(this.f706m);
            this.f705l &= -2;
        }
        a1Var.f325l = this.f706m;
        return a1Var;
    }

    public final void h(a1 a1Var) {
        if (a1Var == a1.f322o) {
            return;
        }
        if (!a1Var.f325l.isEmpty()) {
            if (this.f706m.isEmpty()) {
                this.f706m = a1Var.f325l;
                this.f705l &= -2;
            } else {
                if ((this.f705l & 1) != 1) {
                    this.f706m = new ArrayList(this.f706m);
                    this.f705l |= 1;
                }
                this.f706m.addAll(a1Var.f325l);
            }
        }
        this.f19815i = this.f19815i.b(a1Var.f324i);
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
            a9.a r1 = a9.a1.f323p     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            a9.a1 r1 = new a9.a1     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r2.h(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r4 = r3.f19759i     // Catch: java.lang.Throwable -> Lf
            a9.a1 r4 = (a9.a1) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: a9.v0.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}
