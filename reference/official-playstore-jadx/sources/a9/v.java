package a9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: classes5.dex */
public final class v extends kotlin.reflect.jvm.internal.impl.protobuf.o implements z {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f700l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public w f701m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public List f702n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public f0 f703o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public x f704p;

    public static v h() {
        v vVar = new v();
        vVar.f701m = w.RETURNS_CONSTANT;
        vVar.f702n = Collections.EMPTY_LIST;
        vVar.f703o = f0.f423v;
        vVar.f704p = x.AT_MOST_ONCE;
        return vVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        y yVarG = g();
        if (yVarG.isInitialized()) {
            return yVarG;
        }
        throw new UninitializedMessageException();
    }

    public final Object clone() {
        v vVarH = h();
        vVarH.i(g());
        return vVarH;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        i((y) uVar);
        return this;
    }

    public final y g() {
        y yVar = new y(this);
        int i10 = this.f700l;
        int i11 = (i10 & 1) != 1 ? 0 : 1;
        yVar.f749m = this.f701m;
        if ((i10 & 2) == 2) {
            this.f702n = Collections.unmodifiableList(this.f702n);
            this.f700l &= -3;
        }
        yVar.f750n = this.f702n;
        if ((i10 & 4) == 4) {
            i11 |= 2;
        }
        yVar.f751o = this.f703o;
        if ((i10 & 8) == 8) {
            i11 |= 4;
        }
        yVar.f752p = this.f704p;
        yVar.f748l = i11;
        return yVar;
    }

    public final void i(y yVar) {
        f0 f0Var;
        if (yVar == y.f745s) {
            return;
        }
        if ((yVar.f748l & 1) == 1) {
            w wVar = yVar.f749m;
            wVar.getClass();
            this.f700l = 1 | this.f700l;
            this.f701m = wVar;
        }
        if (!yVar.f750n.isEmpty()) {
            if (this.f702n.isEmpty()) {
                this.f702n = yVar.f750n;
                this.f700l &= -3;
            } else {
                if ((this.f700l & 2) != 2) {
                    this.f702n = new ArrayList(this.f702n);
                    this.f700l |= 2;
                }
                this.f702n.addAll(yVar.f750n);
            }
        }
        if ((yVar.f748l & 2) == 2) {
            f0 f0Var2 = yVar.f751o;
            if ((this.f700l & 4) != 4 || (f0Var = this.f703o) == f0.f423v) {
                this.f703o = f0Var2;
            } else {
                d0 d0VarH = d0.h();
                d0VarH.i(f0Var);
                d0VarH.i(f0Var2);
                this.f703o = d0VarH.g();
            }
            this.f700l |= 4;
        }
        if ((yVar.f748l & 4) == 4) {
            x xVar = yVar.f752p;
            xVar.getClass();
            this.f700l |= 8;
            this.f704p = xVar;
        }
        this.f19815i = this.f19815i.b(yVar.f747i);
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
            a9.a r1 = a9.y.f746t     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            a9.y r1 = new a9.y     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r2.i(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r4 = r3.f19759i     // Catch: java.lang.Throwable -> Lf
            a9.y r4 = (a9.y) r4     // Catch: java.lang.Throwable -> Lf
            throw r3     // Catch: java.lang.Throwable -> L17
        L17:
            r3 = move-exception
            r0 = r4
        L19:
            if (r0 == 0) goto L1e
            r2.i(r0)
        L1e:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: a9.v.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}
