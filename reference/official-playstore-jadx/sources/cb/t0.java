package cb;

import java.util.Map;
import kotlinx.serialization.SerializationException;

/* JADX INFO: loaded from: classes5.dex */
public final class t0 implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ya.h f7559a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ya.h f7560b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f7561c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ab.i f7562d;

    public t0(ya.h hVar, ya.h hVar2, byte b10) {
        this.f7559a = hVar;
        this.f7560b = hVar2;
    }

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        Object r0Var;
        Object obj = b1.f7465c;
        ab.g descriptor = getDescriptor();
        bb.b bVarB = dVar.b(descriptor);
        Object objD = obj;
        Object objD2 = objD;
        while (true) {
            int iW = bVarB.w(getDescriptor());
            if (iW == -1) {
                if (objD == obj) {
                    throw new SerializationException("Element 'key' is missing");
                }
                if (objD2 == obj) {
                    throw new SerializationException("Element 'value' is missing");
                }
                switch (this.f7561c) {
                    case 0:
                        r0Var = new r0(objD, objD2);
                        break;
                    default:
                        r0Var = new x6.x(objD, objD2);
                        break;
                }
                bVarB.u(descriptor);
                return r0Var;
            }
            if (iW == 0) {
                objD = bVarB.d(getDescriptor(), 0, this.f7559a, null);
            } else {
                if (iW != 1) {
                    throw new SerializationException(a0.c.i(iW, "Invalid index: "));
                }
                objD2 = bVarB.d(getDescriptor(), 1, this.f7560b, null);
            }
        }
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        switch (this.f7561c) {
        }
        return this.f7562d;
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        Object key;
        Object value;
        bb.c cVarB = eVar.b(getDescriptor());
        ab.g descriptor = getDescriptor();
        switch (this.f7561c) {
            case 0:
                key = ((Map.Entry) obj).getKey();
                break;
            default:
                key = ((x6.x) obj).f22608i;
                break;
        }
        cVarB.j(descriptor, 0, this.f7559a, key);
        ab.g descriptor2 = getDescriptor();
        switch (this.f7561c) {
            case 0:
                value = ((Map.Entry) obj).getValue();
                break;
            default:
                value = ((x6.x) obj).f22609l;
                break;
        }
        cVarB.j(descriptor2, 1, this.f7560b, value);
        getDescriptor();
        cVarB.e();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public t0(ya.h hVar, ya.h hVar2, int i10) {
        this(hVar, hVar2, (byte) 0);
        this.f7561c = i10;
        switch (i10) {
            case 1:
                this(hVar, hVar2, (byte) 0);
                this.f7562d = xc.d.I("kotlin.Pair", new ab.g[0], new s0(hVar, hVar2, 1));
                break;
            default:
                this.f7562d = xc.d.J("kotlin.collections.Map.Entry", ab.n.f820d, new ab.g[0], new s0(hVar, hVar2, 0));
                break;
        }
    }
}
