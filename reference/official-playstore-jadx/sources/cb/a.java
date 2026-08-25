package cb;

import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public abstract class a implements ya.h {
    public abstract Object a();

    public abstract int b(Object obj);

    public abstract Iterator c(Object obj);

    public abstract int d(Object obj);

    @Override // ya.c
    public Object deserialize(bb.d dVar) {
        return e(dVar);
    }

    public final Object e(bb.d dVar) {
        Object objA = a();
        int iB = b(objA);
        bb.b bVarB = dVar.b(getDescriptor());
        while (true) {
            int iW = bVarB.w(getDescriptor());
            if (iW == -1) {
                bVarB.u(getDescriptor());
                return h(objA);
            }
            f(bVarB, iW + iB, objA);
        }
    }

    public abstract void f(bb.b bVar, int i10, Object obj);

    public abstract Object g(Object obj);

    public abstract Object h(Object obj);
}
