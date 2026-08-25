package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import g8.v0;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends j8.k implements c {
    public final a9.q P;
    public final c9.f Q;
    public final androidx.work.impl.constraints.j R;
    public final c9.g S;
    public final l T;

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException
        */
    public d(g8.f r8, g8.j r9, h8.h r10, boolean r11, int r12, a9.q r13, c9.f r14, androidx.work.impl.constraints.j r15, c9.g r16, kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.l r17, g8.v0 r18) {
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
            r7.P = r13
            r7.Q = r14
            r7.R = r15
            r1 = r16
            r7.S = r1
            r1 = r17
            r7.T = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.d.<init>(g8.f, g8.j, h8.h, boolean, int, a9.q, c9.f, androidx.work.impl.constraints.j, c9.g, kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.l, g8.v0):void");
    }

    @Override // j8.w, g8.w
    public final boolean A() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.m
    public final androidx.work.impl.constraints.j D() {
        return this.R;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.m
    public final c9.f G() {
        return this.Q;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.m
    public final l H() {
        return this.T;
    }

    @Override // j8.k, j8.w
    public final j8.w I0(int i10, f9.f fVar, g8.k kVar, g8.w wVar, v0 v0Var, h8.h hVar) {
        d dVar = new d((g8.f) kVar, (g8.j) wVar, hVar, this.O, i10, this.P, this.Q, this.R, this.S, this.T, v0Var);
        dVar.G = this.G;
        return dVar;
    }

    @Override // j8.k
    /* JADX INFO: renamed from: R0 */
    public final j8.k I0(int i10, f9.f fVar, g8.k kVar, g8.w wVar, v0 v0Var, h8.h hVar) {
        d dVar = new d((g8.f) kVar, (g8.j) wVar, hVar, this.O, i10, this.P, this.Q, this.R, this.S, this.T, v0Var);
        dVar.G = this.G;
        return dVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.m
    public final c0 d0() {
        return this.P;
    }

    @Override // j8.w, g8.a0
    public final boolean isExternal() {
        return false;
    }

    @Override // j8.w, g8.w
    public final boolean isInline() {
        return false;
    }

    @Override // j8.w, g8.w
    public final boolean isSuspend() {
        return false;
    }
}
