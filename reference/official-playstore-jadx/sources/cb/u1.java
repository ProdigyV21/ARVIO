package cb;

import kotlinx.serialization.SerializationException;

/* JADX INFO: loaded from: classes5.dex */
public final class u1 implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ya.h f7569a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ya.h f7570b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ya.h f7571c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ab.i f7572d = xc.d.I("kotlin.Triple", new ab.g[0], new ab.h(this, 11));

    public u1(ya.h hVar, ya.h hVar2, ya.h hVar3) {
        this.f7569a = hVar;
        this.f7570b = hVar2;
        this.f7571c = hVar3;
    }

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        ab.i iVar = this.f7572d;
        bb.b bVarB = dVar.b(iVar);
        Object obj = b1.f7465c;
        Object objD = obj;
        Object objD2 = objD;
        Object objD3 = objD2;
        while (true) {
            int iW = bVarB.w(iVar);
            if (iW == -1) {
                bVarB.u(iVar);
                if (objD == obj) {
                    throw new SerializationException("Element 'first' is missing");
                }
                if (objD2 == obj) {
                    throw new SerializationException("Element 'second' is missing");
                }
                if (objD3 != obj) {
                    return new x6.j0(objD, objD2, objD3);
                }
                throw new SerializationException("Element 'third' is missing");
            }
            if (iW == 0) {
                objD = bVarB.d(iVar, 0, this.f7569a, null);
            } else if (iW == 1) {
                objD2 = bVarB.d(iVar, 1, this.f7570b, null);
            } else {
                if (iW != 2) {
                    throw new SerializationException(a0.c.i(iW, "Unexpected index "));
                }
                objD3 = bVarB.d(iVar, 2, this.f7571c, null);
            }
        }
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return this.f7572d;
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        x6.j0 j0Var = (x6.j0) obj;
        ab.i iVar = this.f7572d;
        bb.c cVarB = eVar.b(iVar);
        cVarB.j(iVar, 0, this.f7569a, j0Var.f22587i);
        cVarB.j(iVar, 1, this.f7570b, j0Var.f22588l);
        cVarB.j(iVar, 2, this.f7571c, j0Var.f22589m);
        cVarB.e();
    }
}
