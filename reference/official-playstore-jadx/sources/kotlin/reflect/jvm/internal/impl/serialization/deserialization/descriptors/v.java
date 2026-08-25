package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import a9.i0;
import g8.u0;
import g8.v0;
import j8.o0;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class v extends o0 implements c {
    public final i0 O;
    public final c9.f P;
    public final androidx.work.impl.constraints.j Q;
    public final c9.g R;
    public final l S;

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException
        */
    public v(g8.k r8, g8.u0 r9, h8.h r10, f9.f r11, int r12, a9.i0 r13, c9.f r14, androidx.work.impl.constraints.j r15, c9.g r16, kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.l r17, g8.v0 r18) {
        /*
            r7 = this;
            if (r18 != 0) goto Lc
            g8.y0 r0 = g8.v0.f15554b
            r6 = r0
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r12
            r0 = r7
            goto L14
        Lc:
            r6 = r18
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r12
        L14:
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r7.O = r13
            r7.P = r14
            r7.Q = r15
            r1 = r16
            r7.R = r1
            r1 = r17
            r7.S = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.v.<init>(g8.k, g8.u0, h8.h, f9.f, int, a9.i0, c9.f, androidx.work.impl.constraints.j, c9.g, kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.l, g8.v0):void");
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.m
    public final androidx.work.impl.constraints.j D() {
        return this.Q;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.m
    public final c9.f G() {
        return this.P;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.m
    public final l H() {
        return this.S;
    }

    @Override // j8.o0, j8.w
    public final j8.w I0(int i10, f9.f fVar, g8.k kVar, g8.w wVar, v0 v0Var, h8.h hVar) {
        u0 u0Var = (u0) wVar;
        if (fVar == null) {
            fVar = getName();
        }
        v vVar = new v(kVar, u0Var, hVar, fVar, i10, this.O, this.P, this.Q, this.R, this.S, v0Var);
        vVar.G = this.G;
        return vVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.m
    public final c0 d0() {
        return this.O;
    }
}
