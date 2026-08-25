package cb;

import kotlinx.serialization.SerializationException;

/* JADX INFO: loaded from: classes5.dex */
public final class y0 implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f7593a = f4.f.o(2, new a8.g0(this, 15));

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        ab.g descriptor = getDescriptor();
        bb.b bVarB = dVar.b(descriptor);
        int iW = bVarB.w(getDescriptor());
        if (iW != -1) {
            throw new SerializationException(a0.c.i(iW, "Unexpected index "));
        }
        bVarB.u(descriptor);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return (ab.g) this.f7593a.getValue();
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        bb.c cVarB = eVar.b(getDescriptor());
        getDescriptor();
        cVarB.e();
    }
}
