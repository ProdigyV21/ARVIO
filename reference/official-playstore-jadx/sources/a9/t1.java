package a9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: classes5.dex */
public final class t1 extends kotlin.reflect.jvm.internal.impl.protobuf.o implements v1 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f689l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public List f690m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f691n;

    public static t1 h() {
        t1 t1Var = new t1();
        t1Var.f690m = Collections.EMPTY_LIST;
        t1Var.f691n = -1;
        return t1Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        u1 u1VarG = g();
        if (u1VarG.isInitialized()) {
            return u1VarG;
        }
        throw new UninitializedMessageException();
    }

    public final Object clone() {
        t1 t1VarH = h();
        t1VarH.i(g());
        return t1VarH;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        i((u1) uVar);
        return this;
    }

    public final u1 g() {
        u1 u1Var = new u1(this);
        int i10 = this.f689l;
        if ((i10 & 1) == 1) {
            this.f690m = Collections.unmodifiableList(this.f690m);
            this.f689l &= -2;
        }
        u1Var.f696m = this.f690m;
        int i11 = (i10 & 2) != 2 ? 0 : 1;
        u1Var.f697n = this.f691n;
        u1Var.f695l = i11;
        return u1Var;
    }

    public final void i(u1 u1Var) {
        if (u1Var == u1.f692q) {
            return;
        }
        if (!u1Var.f696m.isEmpty()) {
            if (this.f690m.isEmpty()) {
                this.f690m = u1Var.f696m;
                this.f689l &= -2;
            } else {
                if ((this.f689l & 1) != 1) {
                    this.f690m = new ArrayList(this.f690m);
                    this.f689l |= 1;
                }
                this.f690m.addAll(u1Var.f696m);
            }
        }
        if ((u1Var.f695l & 1) == 1) {
            int i10 = u1Var.f697n;
            this.f689l |= 2;
            this.f691n = i10;
        }
        this.f19815i = this.f19815i.b(u1Var.f694i);
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
            a9.a r1 = a9.u1.f693r     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            a9.u1 r1 = new a9.u1     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r2.i(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r4 = r3.f19759i     // Catch: java.lang.Throwable -> Lf
            a9.u1 r4 = (a9.u1) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: a9.t1.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}
