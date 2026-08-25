package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import a9.t0;
import g8.p0;
import g8.v0;
import j8.l0;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class u extends l0 implements c {
    public final t0 K;
    public final c9.f L;
    public final androidx.work.impl.constraints.j M;
    public final c9.g N;
    public final l O;

    public u(g8.k kVar, p0 p0Var, h8.h hVar, int i10, g8.p pVar, boolean z, f9.f fVar, int i11, boolean z5, boolean z10, boolean z11, boolean z12, boolean z13, t0 t0Var, c9.f fVar2, androidx.work.impl.constraints.j jVar, c9.g gVar, l lVar) {
        super(kVar, p0Var, hVar, i10, pVar, z, fVar, i11, v0.f15554b, z5, z10, z13, z11, z12);
        this.K = t0Var;
        this.L = fVar2;
        this.M = jVar;
        this.N = gVar;
        this.O = lVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.m
    public final androidx.work.impl.constraints.j D() {
        return this.M;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.m
    public final c9.f G() {
        return this.L;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.m
    public final l H() {
        return this.O;
    }

    @Override // j8.l0
    public final l0 J0(g8.k kVar, int i10, g8.p pVar, p0 p0Var, int i11, f9.f fVar) {
        return new u(kVar, p0Var, getAnnotations(), i10, pVar, this.f19391p, fVar, i11, this.x, this.f19317y, isExternal(), this.B, this.z, this.K, this.L, this.M, this.N, this.O);
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.m
    public final c0 d0() {
        return this.K;
    }

    @Override // j8.l0, g8.a0
    public final boolean isExternal() {
        return c9.e.E.c(this.K.f678n).booleanValue();
    }
}
