package cb;

import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public abstract class t extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ya.h f7558a;

    public t(ya.h hVar) {
        this.f7558a = hVar;
    }

    @Override // cb.a
    public void f(bb.b bVar, int i10, Object obj) {
        i(i10, obj, bVar.d(getDescriptor(), i10, this.f7558a, null));
    }

    public abstract void i(int i10, Object obj, Object obj2);

    @Override // ya.s
    public void serialize(bb.e eVar, Object obj) {
        int iD = d(obj);
        bb.c cVarV = eVar.v(getDescriptor());
        Iterator itC = c(obj);
        for (int i10 = 0; i10 < iD; i10++) {
            cVarV.j(getDescriptor(), i10, this.f7558a, itC.next());
        }
        cVarV.e();
    }
}
