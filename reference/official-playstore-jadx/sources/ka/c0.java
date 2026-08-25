package ka;

/* JADX INFO: loaded from: classes5.dex */
public abstract class c0 {
    public static final d7.j a(d7.j jVar, d7.j jVar2, boolean z) {
        Boolean bool = Boolean.FALSE;
        b0 b0Var = b0.f19553i;
        boolean zBooleanValue = ((Boolean) jVar.fold(bool, b0Var)).booleanValue();
        boolean zBooleanValue2 = ((Boolean) jVar2.fold(bool, b0Var)).booleanValue();
        if (!zBooleanValue && !zBooleanValue2) {
            return jVar.plus(jVar2);
        }
        kotlin.jvm.internal.k0 k0Var = new kotlin.jvm.internal.k0();
        k0Var.f19746i = jVar2;
        a0 a0Var = new a0(k0Var, z);
        d7.k kVar = d7.k.f14688i;
        d7.j jVar3 = (d7.j) jVar.fold(kVar, a0Var);
        if (zBooleanValue2) {
            k0Var.f19746i = ((d7.j) k0Var.f19746i).fold(kVar, z.f19660i);
        }
        return jVar3.plus((d7.j) k0Var.f19746i);
    }

    public static final d7.j b(k0 k0Var, d7.j jVar) {
        d7.j jVarA = a(k0Var.getCoroutineContext(), jVar, true);
        ra.d dVar = x0.f19653b;
        return (jVarA == dVar || jVarA.get(d7.f.f14687i) != null) ? jVarA : jVarA.plus(dVar);
    }

    public static final w2 c(d7.d dVar, d7.j jVar, Object obj) {
        w2 w2Var = null;
        if ((dVar instanceof f7.d) && jVar.get(x2.f19657i) != null) {
            f7.d callerFrame = (f7.d) dVar;
            while (true) {
                if ((callerFrame instanceof v0) || (callerFrame = callerFrame.getCallerFrame()) == null) {
                    break;
                }
                if (callerFrame instanceof w2) {
                    w2Var = (w2) callerFrame;
                    break;
                }
            }
            if (w2Var != null) {
                w2Var.W(jVar, obj);
            }
        }
        return w2Var;
    }
}
