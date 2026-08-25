package cb;

import kotlinx.serialization.SerializationException;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b implements ya.h {
    public ya.c a(bb.b bVar, String str) {
        ((fb.a) bVar.a()).getClass();
        kotlin.jvm.internal.q0.f(1, null);
        return null;
    }

    public ya.s b(bb.e eVar, Object obj) {
        fb.b bVarA = eVar.a();
        kotlin.reflect.d dVarC = c();
        ((fb.a) bVarA).getClass();
        if (!dVarC.l(obj)) {
            return null;
        }
        kotlin.jvm.internal.q0.f(1, null);
        return null;
    }

    public abstract kotlin.reflect.d c();

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        ab.g descriptor = getDescriptor();
        bb.b bVarB = dVar.b(descriptor);
        Object objD = null;
        String strF = null;
        while (true) {
            int iW = bVarB.w(getDescriptor());
            if (iW == -1) {
                if (objD == null) {
                    throw new IllegalArgumentException(androidx.compose.material3.d.C("Polymorphic value has not been read for class ", strF).toString());
                }
                bVarB.u(descriptor);
                return objD;
            }
            if (iW == 0) {
                strF = bVarB.f(getDescriptor(), iW);
            } else {
                if (iW != 1) {
                    StringBuilder sb2 = new StringBuilder("Invalid index in polymorphic deserialization of ");
                    if (strF == null) {
                        strF = "unknown class";
                    }
                    sb2.append(strF);
                    sb2.append("\n Expected 0, 1 or DECODE_DONE(-1), but found ");
                    sb2.append(iW);
                    throw new SerializationException(sb2.toString());
                }
                if (strF == null) {
                    throw new IllegalArgumentException("Cannot read polymorphic value before its type token");
                }
                ya.c cVarA = a(bVarB, strF);
                if (cVarA == null) {
                    b1.i(strF, c());
                    throw null;
                }
                objD = bVarB.d(getDescriptor(), iW, cVarA, null);
            }
        }
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        ya.s sVarK = k2.c.k(this, eVar, obj);
        bb.c cVarB = eVar.b(getDescriptor());
        cVarB.x(getDescriptor(), 0, sVarK.getDescriptor().h());
        cVarB.j(getDescriptor(), 1, sVarK, obj);
        cVarB.e();
    }
}
